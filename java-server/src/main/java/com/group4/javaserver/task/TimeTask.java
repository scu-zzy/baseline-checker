package com.group4.javaserver.task;

import com.group4.javaserver.socket.ServerThread2;
import com.group4.javaserver.service.Impl.ProcessDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class TimeTask {

    @Autowired
    private ProcessDataService processDataService;
    /**
     * socket服务
     */
    @Scheduled(fixedDelay = 40000L)
    public void task01(){
        try {
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
                new ServerThread2(accept,processDataService).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
