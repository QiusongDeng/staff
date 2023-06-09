package com.deng.exception;

import com.deng.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result exception(Exception e) {
        e.printStackTrace();
        return Result.error("操作异常！");
    }
}
