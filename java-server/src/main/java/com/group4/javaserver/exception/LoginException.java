package com.group4.javaserver.exception;

/**
 * 登录异常
 */
public class LoginException extends RuntimeException{

    private Integer code;

    private String msg;

    public LoginException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
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
