package com.tan.store.handler;

import com.tan.store.model.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        log.error("服务器内部异常，{}",e.getMessage(),e);
        return BaseResponse.failure(e.getMessage());
    }
}
