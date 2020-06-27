package com.group4.javaserver.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Socket线程
 */
public class ServerThread extends Thread{

    /**
     * socket连接对象
     */
    private Socket socket;

    private DataOutputStream dataOutputStream;

    private DataInputStream dataInputStream;

    public ServerThread(Socket socket){
        this.socket = socket;
    }


    @Override
    public void run() {
        try {
            //获取客户端信息
            InetAddress inetAddress = socket.getInetAddress();
            //ip地址
            String ip = inetAddress.getHostAddress();
            System.out.println("客户端："+ip+"连接成功");
            //输入输出流
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            //接受客户端消息
            String msg = dataInputStream.readUTF();
            System.out.println("客户端："+msg);

            //向客户端发送消息
            dataOutputStream.writeUTF("收到");


        }catch (IOException e){
            e.printStackTrace();
        }finally {
            closeResources(dataInputStream, dataOutputStream, socket);
        }

    }

    private void closeResources(DataInputStream dataInputStream, DataOutputStream dataOutputStream, Socket socket){

        try {
            if(dataInputStream != null){
                dataInputStream.close();
            }
            if(dataOutputStream != null){
                dataOutputStream.close();
            }
            if(socket != null){
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
