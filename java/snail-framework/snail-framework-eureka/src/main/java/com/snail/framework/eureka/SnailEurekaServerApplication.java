package com.snail.framework.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * 服务注册中心
 * 
 * @author snail
 *
 */
@EnableEurekaServer
@EnableWebSecurity
@SpringBootApplication
public class SnailEurekaServerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SnailEurekaServerApplication.class, args);
	}

}
