package com.group4.javaserver.dto;

public class Response {

    public static ResultDO success(){
        return new ResultDO(200,"处理成功");
    }

    public static ResultDO success(String msg){
        return new ResultDO(200,msg);
    }

    public static ResultDO success(String msg, Object data){
        return new ResultDO(200, msg, data);
    }

    public static ResultDO error(){ return new ResultDO(400,"处理错误"); }

    public static ResultDO error(Integer code, String msg){
        return new ResultDO(code, msg);
    }
}
