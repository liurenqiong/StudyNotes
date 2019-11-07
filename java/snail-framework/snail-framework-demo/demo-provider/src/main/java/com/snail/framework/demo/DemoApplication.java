package com.snail.framework.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.snail.framework.demo.interceptor.AuthInterceptor;
import com.snail.framework.jdbc.autoconfiguration.EnableSnailJDBC;
import com.snail.framework.lock.annotation.EnableSnailLock;
import com.snail.framework.log.autoconfiguration.EnableSnailLogAdvice;
import com.snail.framework.swagger.autoconfiguration.EnableSnailSwagger;


/**
 * @author snail
 * @create 2019/9/2.
 **/
//@EnableFeignClients({"",""})
@EnableDiscoveryClient
@EnableSnailJDBC
@EnableSnailSwagger
@SpringBootApplication
@EnableTransactionManagement
@EnableSnailLogAdvice
@EnableSnailLock
public class DemoApplication extends WebMvcConfigurerAdapter {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/swagger-resources","/v2/**","/doc*","/webjars/**","/error","/login");
    }

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
