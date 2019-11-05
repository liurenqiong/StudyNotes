package com.snail.framework.demo.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.snail.framework.common.model.AppResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * @author snail
 * @create 2019/9/3.
 **/
@Slf4j
@RestControllerAdvice
public class DemoExceptionHandler {

	@ExceptionHandler(DemoException.class)
	public AppResponse handleBadRequest(final DemoException ex, final WebRequest request) {
		log.info("系统出现异常，code：" + ex.getCode() + ",msg:" + ex.getMsg());

		return new AppResponse(ex.getCode(), ex.getMsg());
	}
	
}
