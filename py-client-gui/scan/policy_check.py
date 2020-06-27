# -*- coding: utf-8 -*-
"""
策略核查
本地计算机 策略/计算机配置/安全设置/账户策略
本地计算机 策略/计算机配置/安全设置/本地策略
"""
import subprocess
import ctypes, sys
import codecs
import sqlite3
import re
import json

# SID转换为账户名
convert = {
    "*S-1-0-0": "Nobody",
    "*S-1-1-0": "Evryone",
    "*S-1-5-32-544": "Administrators",
    "*S-1-5-11": "Authenticated Users",
    "*S-1-5-19": "LOCAL SERVICE",
    "*S-1-5-20": "NETWORK SERVICE",
    "*S-1-5-32-555": "Remote Desktop Users",
    "*S-1-5-32-545": "Users",
    "*S-1-5-6": "SERVICE",
    "*S-1-5-83-0": "NT VIRTUAL MACHINE\\Virtual Machines",
    "*S-1-5-32-546": "Guests",
    "*S-1-2-0": "Local account",
    "*S-1-5-80-3139157870-2983391045-3678747466-658725712-1809340420": "NT SERVICE\\WdiServiceHost",
    "*S-1-5-80": "NT Service",
    "*S-1-5-32-551": "Backup Operators"
}


# SID转换为账户名并排序
def sort_convert(value):
    # 取出SID
    vals = value.split(",")
    # 账户名列表
    results = []
    # 将SID转换为账户名
    for val in vals:
        if convert.get(val):
            results.append(convert.get(val))
        else:
            results.append(val)
    # 账户名排序
    results.sort()
    # 创建字符串
    result_str = ""
    for result in results:
        result_str += result + ','
    # 删除最后一个逗号
    result_str = result_str[:-1]
    return result_str


# 小于规则
def lt(value, proposed_val):
    return int(value) < int(proposed_val)


# 大于规则
def bt(value, proposed_val):
    return int(value) > int(proposed_val)


# 等于规则
def eq(value, proposed_val):
    return value == proposed_val


# between规则
def between(value, proposed_val):
    val = proposed_val[1:-1].split(',')
    return int(val[0]) <= int(value) <= int(val[1])


# 不等于规则
def ne(value, proposed_val):
    return value != proposed_val


# 字符串匹配规则
def str_noorder_eq(value, proposed_val):
    # 对账户名进行排序
    proposed_vals = proposed_val.split(",")
    proposed_vals.sort()
    # 创建字符串
    result_str = ""
    for str in proposed_vals:
        result_str += str + ','
    # 删除最后一个逗号
    result_str = result_str[:-1]
    # print(value == result_str)
    # print(value + ' = ' + result_str)
    return value == result_str


# 规则匹配
switch = {
    'lt': lt,
    'bt': bt,
    'eq': eq,
    'between': between,
    'str_noorder_eq': str_noorder_eq,
    'ne': ne,
}


# 获取管理员权限
def is_admin():
    try:
        return ctypes.windll.shell32.IsUserAnAdmin()
    except:
        return False


# 运行cmd
def run_code(cmd):
    return subprocess.Popen(cmd, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)


# 通过策略文件获取策略信息，更新数据库，并将结果保存为csv文件
def get_policy_info():
    # cmd命令
    cmd = "secedit /export /cfg secpolicy.cfg"

    if is_admin():
        run_code(cmd)

    else:
        ctypes.windll.shell32.ShellExecuteW(None, "runas", sys.executable, __file__, None, 1)

    file_name = 'secpolicy.cfg'

    # 将文件中的信息读出来，存入字典
    dic = {}
    with codecs.open(file_name, 'r+', 'utf-16') as fd:
        for line in fd.readlines():
            line = line.strip('\r\n')  # 去除换行
            name = line.split('=')[0].strip()
            try:
                val = line.split('=')[1].strip()
                dic[name] = val
            except:
                continue

    policy_list = []

    # 连接数据库
    conn = sqlite3.connect('resource/local.db')

    # 创建cursor
    cursor = conn.cursor()

    # 取出数据库中的所有规则
    cursor.execute('select * from cnftxt')
    results = cursor.fetchall()
    for result in results:

        # 策略在策略文件中存在
        if result[4] in dic:
            # 在字典中策略对应的值
            value = dic.get(result[4])
            # 有正则匹配正则
            if result[11] != 'None':
                value = ''.join(re.findall(result[11], value))
            # 如果是用户权限分配
            if result[7] == 'str_noorder_eq':
                # 值要进行转换
                value = sort_convert(value)
            # output[2] = value
            # 进行核查
            if switch.get(result[7])(value, result[6]):
                eval_result = "符合"
            else:
                eval_result = "不符合"
        # 在策略文件中不存在，但是是用户权限分配，此时策略的值为Nobody
        elif result[7] == 'str_noorder_eq':
            value = 'Nobody'
            # 建议值为Nobody，则策略文件中可以不存在
            if result[6] == 'Nobody':
                eval_result = "符合"
            # 建议值不为Nobody，则策略文件中不可以不存在
            else:
                eval_result = "不符合"
        # 在策略文件中不存在，也不是用户权限分配，此时策略的值是None
        else:
            value = "None"
            if result[7] == 'eq':
                eval_result = "符合"
            else:
                eval_result = "不符合"

        # 更新数据库
        cursor.execute('update cnftxt set checked_val = ? where id = ?', (value, result[0],))
        cursor.execute('update cnftxt set eval_result = ? where id = ?', (eval_result, result[0],))

        value = value.replace(",", "，")
        result_r = result[10].replace("\n", " ")
        result_r = result_r.replace("\r", " ")
        # 加入列表
        # '策略编号','策略名称','检测值','建议值','核查结果','加固提示','策略说明'
        policy_list.append([result[0], result[1], value, result[5], eval_result, result[9], result_r])

    # 关闭Cursor:
    cursor.close()
    # 提交事务：
    conn.commit()
    # 关闭connection：
    conn.close()

    return json.dumps(policy_list)
