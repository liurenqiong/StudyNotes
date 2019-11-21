package com.snail.framework.eureka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

//	@Bean
//	public HttpFirewall httpFirewall() {
//		return new DefaultHttpFirewall();
//	}
//
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//
//		super.configure(web);
//		web.httpFirewall(httpFirewall());
//
//	}
	
//	@Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();     //关闭跨域
//        http.authorizeRequests()   //认证请求
//                .anyRequest()      //对任何请求
//                .authenticated()   //都需要认证
//                .and()
//                .httpBasic();      //使用Spring Security提供的登录界面
//    }

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); // 关闭csrf
        http.authorizeRequests().anyRequest().authenticated().and().httpBasic(); // 开启认证
    }

}
