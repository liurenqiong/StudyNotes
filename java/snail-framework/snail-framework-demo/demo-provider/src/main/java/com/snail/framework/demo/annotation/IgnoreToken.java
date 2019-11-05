package com.snail.framework.demo.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
* @ProjectName：mgmt-provider   
* @ClassName：IgnoreToken   
* @Description：忽略令牌标签   
* @author：lrq   
* @Date：2019年9月3日 下午7:03:43   
* @version
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreToken {

	String value() default "";
}

