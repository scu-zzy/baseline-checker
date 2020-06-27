package com.group4.javaserver.exception;

import com.group4.javaserver.dto.Response;
import com.group4.javaserver.dto.ResultDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class SystemExceptionHandler {

    /**
     * 日志记录器
     */
    private Logger logger = LoggerFactory.getLogger(SystemExceptionHandler.class);
    /**
     * 捕获登录异常
     * @param e
     * @return
     */
    @ExceptionHandler(LoginException.class)
    public ResultDO loginExceptionHandler(LoginException e){

        //记录日志
        logger.info("登录异常，状态码={}",e.getCode());
        logger.info("登录异常，信息={}",e.getMsg());
        return Response.error(e.getCode(),e.getMsg());
    }

    /**
     * 捕获数据验证异常
     * @param e
     * @return
     */
    @ExceptionHandler(DataException.class)
    public ResultDO dataExceptionHandler(DataException e){

        //记录日志
        logger.info("数据校验异常，状态码={}",e.getCode());
        logger.info("数据校验异常，信息={}",e.getMsg());
        return Response.error(e.getCode(),e.getMsg());

    }
}
