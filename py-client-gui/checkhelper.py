import register_socket
import base_info_socket
import autoruns_socket
import install_soft_socket
import network_info_socket
import policy_info_socket
import service_info_socket
import update_soft_socket

target_server_ip = '175.24.114.232'
#target_server_ip = '127.0.0.1'
Task_map = {"hostinfo":"主机信息检查",
        "autorun":"自启动项检查",
        "inssoft":"已安装软件检查",
        "network":"网络信息检查",
        "policy":"策略检查",
        "service":"服务信息检查",
        "update":"更新信息检查"}

#提供软件打印信息中文翻译
def en2ch(task_en):
    return Task_map[task_en]

def do_checkhelper(taskitem):
    if taskitem in Task_map:
        try:
            do_(taskitem)
        except:
            print(taskitem," error")
        else:
            print(taskitem," ok")

def do_registerhelper():
    try:
        register_socket.register_(target_server_ip)
    except:
        print("register error")
        return 'error'
    else:
        print("register ok")
        return 'ok'

def do_(task):
    if task == 'hostinfo':
        base_info_socket.baseinfo_check(target_server_ip)
    elif task == 'autorun':
        autoruns_socket.autorun_check(target_server_ip)
    elif task == 'inssoft':
        install_soft_socket.inssoft_check(target_server_ip)
    elif task == 'network':
        network_info_socket.network_check(target_server_ip)
    elif task == "policy":
        policy_info_socket.policy_check(target_server_ip)
    elif task == "service":
        service_info_socket.service_check(target_server_ip)
    elif task == "update":
        update_soft_socket.update_check(target_server_ip)
    else:
        raise Exception
