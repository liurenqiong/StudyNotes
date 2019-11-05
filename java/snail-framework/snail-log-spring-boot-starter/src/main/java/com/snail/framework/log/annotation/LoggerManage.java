package com.snail.framework.log.annotation;

import java.lang.annotation.*;

/**
 * @author snail
 * @create 2019/2/23.
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoggerManage {

    String description();
}
