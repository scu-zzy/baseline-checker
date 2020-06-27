package com.group4.javaserver.socket;

import com.group4.javaserver.service.Impl.ProcessDataService;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Socket线程
 */
public class ServerThread2 extends Thread{

    /**
     * socket连接对象
     */
    private Socket socket;

    private InputStreamReader inputStreamReader;

    private ProcessDataService processDataService;

    public ServerThread2(Socket socket){
        this.socket = socket;
    }

    public ServerThread2(Socket socket, ProcessDataService processDataService){
        this.socket = socket;
        this.processDataService = processDataService;
    }



    @Override
    public void run() {
        try {
            //获取客户端信息
            InetAddress inetAddress = socket.getInetAddress();
            //ip地址
            String ip = inetAddress.getHostAddress();
            System.out.println("客户端："+ip+"连接成功");
            //输入流包装
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            //接受客户端消息
            String msg = getData(inputStreamReader);
            //处理数据
            processDataService.process(msg);

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            closeResources(inputStreamReader, socket);
        }

    }

    /**
     * 获取输入数据的方法
     * @param inputStreamReader
     * @return String
     */
    private String getData(InputStreamReader inputStreamReader) throws IOException{

        char[] chars = new char[1024];

        //读取的量
        int read;

        //循环 拼接 使用StringBuilder
        StringBuilder stringBuilder = new StringBuilder();
        while ((read = inputStreamReader.read(chars)) != -1){
            System.out.println(read+"个字符");
            stringBuilder.append(new String(chars,0,read));
        }

        return stringBuilder.toString();
    }

    /**
     * 清空资源
     * @param inputStreamReader
     * @param socket
     */
    private void closeResources(InputStreamReader inputStreamReader, Socket socket){

        try {
            if(inputStreamReader != null){
                inputStreamReader.close();
            }
            if(socket != null){
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
