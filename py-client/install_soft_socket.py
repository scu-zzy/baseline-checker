import socket

from const.Type import Type
from scan.Base import Base
import ctypes
import sys


# 获取管理员权限
def is_admin():
    try:
        return ctypes.windll.shell32.IsUserAnAdmin()

    except:
        return False


if is_admin():
    address = ('127.0.0.1', 9526)
    # 创建socket对象
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    # 连接
    client_socket.connect(address)
    print('连接服务端成功')

    # 获取主板id
    board_id = Base.get_board_id()

    # 获取安装信息
    data = Base.get_install_soft()
    # print(data)
    # data的双引号转换为单引号
    data = data.replace('"', "'")
    # 组装发送的类型
    send_data = '{"typeCode":%d,"boardId":"%s","data":"%s"}' % (Type.INSTALLED_SOFT, board_id, data)
    # print(send_data)
    # 发送
    client_socket.send(send_data.encode('utf-8'))
    print('发送成功')

    # 关闭
    client_socket.close()

else:
    ctypes.windll.shell32.ShellExecuteW(None, "runas", sys.executable, __file__, None, 1)
