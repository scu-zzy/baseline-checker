"""
服务核查
通过cmd
"""
import subprocess
import sqlite3
import re
import json


# 获取cmd运行结果
def run_code(cmd):
    res = subprocess.Popen(cmd, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    for line in res.communicate():
        return line.decode("gb2312")


# 核查是否合格
def check(value, proposed_state_val):
    if value in proposed_state_val:
        return True
    else:
        return False


# 通过cmd搜索服务信息，更新数据库
def get_service_info():

    service_list = []

    # 连接数据库
    conn = sqlite3.connect('resource/local.db')
    # 创建cursor
    cursor = conn.cursor()
    # 执行SQL语句
    cursor.execute('select * from service')
    # 获取所有信息
    values = cursor.fetchall()

    # 通过服务在命令行里搜索
    for value in values:

        # cmd获取服务信息
        cmd = "sc qc " + value[2]
        result = run_code(cmd)
        # 没安装服务
        if "失败" in result:
            result = '0'
        # 安装了服务，则搜索启动类型
        else:
            pattern = r"START_TYPE         : (\d)"
            results = re.findall(pattern, result)
            result = results[0]

        # 更新数据库
        cursor.execute('update service set checked_state_val = ? where id = ?', (result, value[0],))

        # 核查结果
        if check(result, value[5]):
            eval_result = "符合"
        else:
            eval_result = "不符合"

        # 更新数据库
        cursor.execute('update service set eval_result = ? where id = ?', (eval_result, value[0],))
        value_r = value[3].replace("'", '')
        # 存入列表中
        # 依次为'服务编号','服务展示名称','服务名称','检测值','建议值','核查结果','加固提示','说明'
        service_list.append([value[0], value[1], value[2], result, value_r, eval_result, value[7], value[8]])

    # 关闭Cursor:
    cursor.close()
    # 提交事务：
    conn.commit()
    # 关闭connection：
    conn.close()

    return json.dumps(service_list)
