package com.snail.framework.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.snail.framework.redis.data.IRedisTemplate;

import lombok.extern.slf4j.Slf4j;

/**
 * @ProjectName：mgmt-provider
 * @ClassName：AuthInterceptor 
 * @Description： 请求校验
 * @author：lrq
 * @Date：2019年9月3日 下午7:10:47
 * @version
 */
@Component
@Slf4j
public class AuthInterceptor extends HandlerInterceptorAdapter {

	
	@Autowired
	private IRedisTemplate snailRedisTemplate;

	/**
	 * 处理请求
	 * 
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		return true;

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

}
