import BaseLineCheckerUI
import sys
from PyQt5.QtWidgets import QApplication, QWidget
#from PyQt5 import QtCore, QtGui, QtWidgets
from PyQt5.QtCore import QUrl
from PyQt5.QtGui import QDesktopServices
import datetime
import checkhelper

class my_gui(BaseLineCheckerUI.Ui_Form):#从自动生成的界面类继承

    def __init__(self, form):
        super(my_gui, self).setupUi(form)
        self.pushButton_check.clicked.connect(self.do_check)
        self.rm_msg.clicked.connect(self.clear_output)
        self.url.clicked.connect(self.visitpage)
        self.register_btn.clicked.connect(self.register)
        self.check_counter=0
        self.register_ok=False

    def register(self):
        self.msg_output.append('<p>'+("="*30)+'</p>')
        self.msg_output.append("<p><b>设备注册</b></p>")
        now_time = datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')
        self.msg_output.append("<p>当前时间:{0}</p>".format(now_time))
        res = checkhelper.do_registerhelper()
        if res == 'error':
            self.msg_output.append("<p style='color:red'>设备注册失败!</p>")
        elif res == 'ok':
            self.register_ok = True
            self.msg_output.append("<p style='color:green'>设备注册完成!</p>")
        self.cursor = self.msg_output.textCursor()
        self.msg_output.moveCursor(self.cursor.End)
        self.msg_output.append('<p>'+("="*30)+'</p>')
    
    def visitpage(self):
        QDesktopServices.openUrl(QUrl("https://www.baidu.com"))

    def clear_output(self):
        self.msg_output.clear()
        self.check_counter=0

    def do_check(self):
        #获取检查项勾选情况
        do_list = []
        if self.checkBox_hostinfo.isChecked():
            do_list.append("hostinfo")
        if self.checkBox_autorun.isChecked():
            do_list.append("autorun")
        if self.checkBox_inssoft.isChecked():
            do_list.append("inssoft")
        if self.checkBox_network.isChecked():
            do_list.append("network")
        if self.checkBox_policy.isChecked():
            do_list.append("policy")
        if self.checkBox_service.isChecked():
            do_list.append("service")
        if self.checkBox_update.isChecked():
            do_list.append("update")
        if do_list and self.register_ok:
            self.check_counter += 1
        elif not do_list:
            self.msg_output.append("<p style='color:red'><b>提示</b></p>")
            now_time = datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')
            self.msg_output.append("<p>当前时间:{0}</p>".format(now_time))
            self.msg_output.append("<p>检查项不能为空!</p>")
            self.cursor = self.msg_output.textCursor()
            self.msg_output.moveCursor(self.cursor.End)
            return
        elif not self.register_ok:
            self.msg_output.append("<p><b style='color:red'>提示</b></p>")
            now_time = datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')
            self.msg_output.append("<p>当前时间:{0}</p>".format(now_time))
            self.msg_output.append("<p>请先完成设备注册!</p>")
            self.cursor = self.msg_output.textCursor()
            self.msg_output.moveCursor(self.cursor.End)
            return
        #开始检查
        self.msg_output.append('<p>'+("="*30)+'</p>')
        self.msg_output.append("<p><b>第{0}次扫描</b></p>".format(self.check_counter))
        now_time = datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')
        self.msg_output.append("<p>当前时间:{0}</p>".format(now_time))
        for checkitem in do_list:
            checkhelper.do_checkhelper(checkitem)
            self.msg_output.append("<p>{0}完成!</p>".format(checkhelper.en2ch(checkitem)))
            self.cursor = self.msg_output.textCursor()
            self.msg_output.moveCursor(self.cursor.End)
        self.msg_output.append("<a href='http://wwww.baidu.com'>baidu</a>")
        self.msg_output.append('<p>'+("="*30)+'</p>')

 
if __name__ == '__main__':
    #每一pyqt5应用程序必须创建一个应用程序对象。sys.argv参数是一个列表，从命令行输入参数。
    app = QApplication(sys.argv)
    #QWidget部件是pyqt5所有用户界面对象的基类。他为QWidget提供默认构造函数。默认构造函数没有父类。
    w = QWidget()
    form = my_gui(w)
    w.show()
    sys.exit(app.exec_())
