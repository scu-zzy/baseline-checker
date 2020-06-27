package com.group4.javaserver.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client2 {

    public static void main(String[] args) throws IOException {
        //端口
        Integer port = 9527;
        //ip
        String ip = "127.0.0.1";
        //创建socket对象
        Socket socket = new Socket(ip, port);
        //准备输入输出流,包装
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        //发送消息
        dataOutputStream.writeUTF("zzy2");

        //接受消息
        String msg = dataInputStream.readUTF();
        System.out.println("服务端："+msg);

        //释放资源
        dataInputStream.close();
        dataOutputStream.close();
        socket.close();

    }
}
