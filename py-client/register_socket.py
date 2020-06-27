import socket

from const.Type import Type
from scan.Base import Base

# 准备ip和端口
address = ('127.0.0.1', 9526)
# 创建socket对象
client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
# 连接
client_socket.connect(address)
print('连接服务端成功')
# 获取注册信息
data = Base.get_register_info()
# 组装发送的类型
send_data = '{"typeCode":%d,"data":"%s"}' % (Type.REGISTER, data)
# 发送
client_socket.send(send_data.encode('utf-8'))
print('发送成功')

# 关闭
client_socket.close()


