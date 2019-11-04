package com.snail.framework.common.annotation;

import java.lang.annotation.*;

/**
 *
 * <dependency>
 *      <groupId>com.snail.framework</groupId>
 *      <artifactId>snail-log-spring-boot-starter</artifactId>
 *      <version>${framework.version}</version>
 * </dependency>
 *
 * 过期了 , 如果使用需要引用 com.snail.framework.log.annotation.LoggerManage
 * @author snail
 * @create 2019/2/23.
 **/
@Deprecated
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoggerManage {

    String description();
}
