package com.laohan.handler.exceptionHandler;

import com.laohan.domain.ResponseResult;
import com.laohan.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandler(SystemException e){
        //打印异常信息,方便调试
        log.error("出现了异常!",e);
        //从异常对象中获取提示信息,封装返回
        return ResponseResult.errorResult(e.getCode(),e.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult ExceptionHandler(Exception e){
        //打印异常信息,方便调试
        log.error("出现了异常!",e);
        //从异常对象中获取提示信息,封装返回
        return ResponseResult.errorResult(500,e.getMessage());
    }
}
