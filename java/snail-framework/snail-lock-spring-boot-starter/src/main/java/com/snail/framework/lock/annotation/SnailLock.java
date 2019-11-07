package com.snail.framework.lock.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author snail
 * @create 2019/8/29.
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SnailLock {


    /**
     * 锁的键值
     * SPEL表达式
     * @return
     */
    String lockKey() default "" ;

    /**
     * 锁的后缀
     * @return
     */
    String lockSuffix() default "";

    /**
     * 锁的前缀
     * @return
     */
    String lockPrefix() default "";

    /**
     * 分隔符
     * @return
     */
    String separator() default "#";

    /**
     * 使用什么实现锁，Redis OR zookeeper
     * @return
     */
    LockType lockType() default LockType.REDIS;

    /**
     * 是否尝试获取锁
     * @return
     */
    boolean tryLock() default false;

    /**
     * 等待时间
     * 该字段只有当tryLock()返回true才有效。
     * @return
     */
    int waitTime() default 0;

    /**
     * 锁超时时间。
     * 超时时间过后，锁自动释放。
     * 建议：
     * 尽量缩简需要加锁的逻辑。
     */
    int leaseTime() default 30;

    /**
     * 时间单位
     * @return
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;


}
