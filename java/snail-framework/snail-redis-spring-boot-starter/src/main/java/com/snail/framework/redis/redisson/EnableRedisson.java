package com.snail.framework.redis.redisson;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author snail
 * @create 2019/8/28.
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({RedissonAutoConfiguration.class})
@Documented
public @interface EnableRedisson {
}
