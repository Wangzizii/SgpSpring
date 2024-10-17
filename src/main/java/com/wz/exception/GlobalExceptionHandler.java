package com.wz.exception;

import ch.qos.logback.core.util.StringUtil;
import com.wz.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result exception(Exception e) {
        e.printStackTrace();
        return Result.error(StringUtils.hasLength( e.getMessage())?e.getMessage():"The operation failure");
    }
}
