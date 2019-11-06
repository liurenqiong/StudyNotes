package com.snail.framework.redis.autoconfiguration;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author snail
 * @create 2019-07-20 16:10
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({SnailRedisAutoConfiguration.class})
@Documented
public @interface EnableSnailRedis {

}
