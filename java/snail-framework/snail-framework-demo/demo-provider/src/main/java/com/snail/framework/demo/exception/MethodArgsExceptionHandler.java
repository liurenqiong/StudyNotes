package com.snail.framework.demo.exception;

import java.text.MessageFormat;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.snail.framework.common.model.AppResponse;
import com.snail.framework.demo.constant.DemoErrorMsg;

import lombok.extern.slf4j.Slf4j;

/**
 * @author snail
 * @create 2019/9/3.
 **/
@Slf4j
@RestControllerAdvice
public class MethodArgsExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public AppResponse handleBadRequest(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        String filed = bindingResult.getFieldError().getField();
        String message = bindingResult.getFieldError().getDefaultMessage();
        String errorMsg = MessageFormat.format(DemoErrorMsg.BAD_REQUEST_COLUMN.getMsg() , filed , message);
        log.info("参数发生异常，filed:{} , message:{}" , filed , message);
        return new AppResponse(DemoErrorMsg.BAD_REQUEST_COLUMN.getCode() , errorMsg);
    }
}
