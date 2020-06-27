package com.group4.javaserver.exception;

/**
 * 数据校验异常
 */
public class DataException extends RuntimeException{

    private Integer code;

    private String msg;

    public DataException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public DataException() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
