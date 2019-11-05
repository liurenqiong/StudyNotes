package com.snail.framework.jdbc.autoconfiguration;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author snail
 * @create 2019/8/26.
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({SnailJDBCAutoConfiguration.class})
@Documented
public @interface EnableSnailJDBC {

}
