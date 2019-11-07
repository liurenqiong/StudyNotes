package com.snail.framework.lock.annotation;

import com.snail.framework.lock.DistributedFactory;
import com.snail.framework.lock.aspect.DistributedLockAspect;
import com.snail.framework.lock.redis.DistributedRedisLock;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author snail
 * @create 2019/9/18.
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({DistributedLockAspect.class , DistributedFactory.class , DistributedRedisLock.class})
@Documented
public @interface EnableSnailLock {
}
