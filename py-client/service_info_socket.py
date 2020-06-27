import socket

from const.Type import Type
from scan.Base import Base
from scan.service_check import get_service_info

# 准备ip和端口
address = ('127.0.0.1', 9526)
# 创建socket对象
client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
# 连接
client_socket.connect(address)
print('连接服务端成功')
# 获取主板id
board_id = Base.get_board_id()
# 获取基本信息
data = get_service_info()
# data的双引号转换为单引号
data = data.replace('"', "'")
# 将\'转换为空
# data = data.replace("\\'", '')
# 组装发送的类型
send_data = '{"typeCode":%d,"boardId":"%s","data":"%s"}' % (Type.SERVICE_INFO, board_id, data)
# print(send_data.encode('utf-8'))
# 发送
client_socket.send(send_data.encode('utf-8'))
print('发送成功')

# 关闭
client_socket.close()


