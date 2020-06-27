import socket

from const.Type import Type
from scan.Base import Base

# 准备ip和端口
from utils.get_admin import is_admin

def update_check(target_server_ip):
    address = (target_server_ip, 9526)
    # 创建socket对象
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    # 连接
    client_socket.connect(address)
    print('连接服务端成功')

    # 获取主板id
    board_id = Base.get_board_id()

    # 获取安装信息
    data = Base.get_update_info()
    # print(data)
    # data的双引号转换为单引号
    data = data.replace('"', "'")
    # 组装发送的类型
    send_data = '{"typeCode":%d,"boardId":"%s","data":"%s"}' % (Type.UPDATE_INFO, board_id, data)
    # print(send_data)
    # 发送
    client_socket.send(send_data.encode('utf-8'))
    print('发送成功')

    # 关闭
    client_socket.close()

if __name__ == "__main__":
    update_check('127.0.0.1')
