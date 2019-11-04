package com.snail.framework.swagger.autoconfiguration;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author snail
 * @create 2019/8/27.
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({SnailSwaggerAutoConfiguration.class})
@Documented
public @interface EnableSnailSwagger {
}
