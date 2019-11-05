package com.snail.framework.jdbc.multi.annotation;

import java.lang.annotation.*;

/**
 * @author snail
 * @create 2019/9/18.
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SnailDataSource {

    /**
     * 使用数据库的key值
     * @return
     */
    String datasource() default "" ;

    /**
     * 是否是master数据源
     * @return
     */
    boolean master() default true;
}
