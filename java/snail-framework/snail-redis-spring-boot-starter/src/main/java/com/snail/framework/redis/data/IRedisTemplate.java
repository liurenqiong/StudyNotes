package com.snail.framework.redis.data;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author snail
 * @create 2019/8/28.
 **/
public interface IRedisTemplate {

    /**
     * 保存或者更新
     * @param key
     * @param obj
     * @param expSecond
     * @return
     */
    long saveOrUpdate(String key, Object obj, int expSecond);

    /**
     * 根据key删除数据
     * @param key
     * @return
     */
    long delete(String key);

    /**
     * 保存或者更新
     * @param key
     * @param obj
     * @return
     */
    long saveOrUpdate(String key, Object obj);

    /**
     * 获取数据
     * @param key
     * @param cls
     * @param <T>
     * @return
     */
    <T> T get(String key, Class<T> cls);

    /**
     * 获取数据
     * @param key
     * @param cls
     * @param expSecond
     * @param <T>
     * @return
     */
    <T> T get(String key, Class<T> cls, int expSecond);

    /**
     * 是否存在
     * @param key
     * @return
     */
    boolean exists(String key);

    /**
     * 设置对象过期
     * @param key
     * @param expSecond
     * @return
     */
    boolean expire(String key, int expSecond , TimeUnit timeUnit);

    /**
     * 加锁
     * @param lockKey
     * @param unit
     * @param leaseTime
     */
    boolean lock(String lockKey, TimeUnit unit, int leaseTime);

    /**
     * 尝试获取锁
     * @param lockKey
     * @param unit
     * @param waitTime
     * @param leaseTime
     * @return
     * @throws Exception
     */
    boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime) throws Exception;

    /**
     * 释放锁
     * @param lockKey
     */
    void unlock(String lockKey);

    /**
     * 排名初始化数据
     * @param key
     * @param num
     * @param member
     * @return
     */
    Long zadd(String key , double num , String member);

    /**
     * 倒叙获取set列表 , 只能获取member
     * @param key map key
     * @param start 开始值 获取全量用 0
     * @param stop  结束值 获取全量用 -1
     * @return
     */
    Set<String> zrevrange(String key , int start , int stop);

    /**
     * 倒叙获取set列表 , 获取member 和 score
     * @param key map key
     * @param start 开始值 获取全量用 0
     * @param stop  结束值 获取全量用 -1
     * @return
     */
    List<Element> zrevrangeWithScores(String key , int start , int stop);

    /**
     * 给指定的排行榜加分数
     * @param key set key
     * @param num 指定的票数或者分数
     * @param member 成员
     * @return
     */
    Double zincrby(String key , double num , String member);

    /**
     * 除有序集合中的一个或多个成员
     * @param key     set key
     * @param member  一个或者多个成员
     * @return
     */
    public Long zrem(String key , String... member);
}
