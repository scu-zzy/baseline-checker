package com.group4.javaserver.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    public Server() throws IOException {
        //指定端口
        Integer port = 9526;
        //注册服务端的socket对象
        ServerSocket serverSocket = new ServerSocket(port);

        System.out.println("socket服务注册成功");
        System.out.println("等待客户端连接……");

        while (true){
            //监听-阻塞
            Socket accept = serverSocket.accept();
            //创建并启动线程
            new ServerThread2(accept).start();
        }
    }

}
