package com.snail.framework.redis.data;

import com.snail.framework.redis.util.JsonTool;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.redisson.client.protocol.ScoredEntry;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Tuple;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author snail
 * @create 2019/8/28.
 **/
@Slf4j
public class SnailRedissonTemplate implements IRedisTemplate {

    private RedissonClient redissonClient;

    public SnailRedissonTemplate(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    /**
     * 是否存在
     *
     * @param key
     * @return
     */
    @Override
    public boolean exists(String key) {
        RBucket<String> bucket = null;

        try {
            bucket =  redissonClient.getBucket(key);
            return bucket.isExists();
        } catch (Exception e) {

            log.info("redisson设置数据异常e:{}" , e);
            return false;
        }
    }

    /**
     * 设置对象过期
     *
     * @param key
     * @param expSecond
     * @param timeUnit
     * @return
     */
    @Override
    public boolean expire(String key, int expSecond, TimeUnit timeUnit) {

        RBucket<String> bucket = null;

        try {
            bucket =  redissonClient.getBucket(key);
            if(bucket != null && bucket.isExists()) {
                bucket.expire(expSecond , timeUnit);
                return true;
            }
        } catch (Exception e) {

            log.info("redisson设置过期时间异常:{}" , e);
        }

        return false;
    }

    /**
     * 保存或者更新
     *
     * @param key
     * @param obj
     * @return
     */
    @Override
    public long saveOrUpdate(String key, Object obj) {
        return saveOrUpdate(key , obj , 0);
    }

    /**
     * 保存或者更新
     *
     * @param key
     * @param obj
     * @param expSecond
     * @return
     */
    @Override
    public long saveOrUpdate(String key, Object obj, int expSecond) {
        RBucket<String> bucket = null;

        try {
            bucket =  redissonClient.getBucket(key);
            if(obj != null & expSecond >0 ) {
                bucket.set(JsonTool.getString(obj) , expSecond , TimeUnit.SECONDS);

            } else if(expSecond <= 0) {
                bucket.set(JsonTool.getString(obj));
            }
            return 1;
        } catch (Exception e) {

            log.info("redisson设置数据异常e:{}" , e);
            return 0L;
        }
    }

    /**
     * 保存或者更新
     *
     * @param key
     * @return
     */
    @Override
    public long delete(String key) {
        RBucket<Object> bucket = null;

        try {
            bucket = redissonClient.getBucket(key);
            if(bucket != null && bucket.isExists()) {
                bucket.delete();
            }
        } catch (Exception e) {

            log.info("redisson删除数据异常e:{}" , e);
            return 0L;
        }
        return 1L;
    }


    /**
     * 获取数据
     *
     * @param key
     * @param cls
     * @return
     */
    @Override
    public <T> T get(String key, Class<T> cls) {
        return get(key  , cls , 0);
    }


    /**
     * 获取数据
     *
     * @param key
     * @param cls
     * @param expSecond
     * @return
     */
    @Override
    public <T> T get(String key, Class<T> cls, int expSecond) {
        RBucket<String> bucket = null;

        try {
            bucket =  redissonClient.getBucket(key);
            if(bucket != null && bucket.isExists()) {
                String value = bucket.get();
                return JsonTool.getObj(value , cls);
            }
        } catch (Exception e) {

            log.info("redisson获取数据异常e:{}" , e);
            return null;
        }
        return null;
    }

    /**
     * 上锁
     * @param lockKey
     * @param unit 时间单位
     * @param leaseTime 上锁后自动释放锁时间
     */
    @Override
    public boolean lock(String lockKey, TimeUnit unit, int leaseTime) {
        RLock lock = redissonClient.getLock(lockKey);
        try {
            return lock.tryLock(0 , leaseTime , unit);
        } catch (Exception e) {
            log.info("创建锁出现未知异常,e：{}" , e);
            return false;
        }
    }

    /**
     * 尝试获取锁(会转变为同步锁)
     * @param lockKey
     * @param unit      时间单位
     * @param waitTime  最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @return
     * @throws Exception
     */
    @Override
    public boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime) throws Exception {
        RLock lock = redissonClient.getLock(lockKey);
        try {
            return lock.tryLock(waitTime, leaseTime, unit);
        } catch (Exception e) {
            log.info("创建锁出现未知异常,e：{}" , e);
            return false;
        }
    }

    /**
     * 释放锁
     * @param lockKey
     */
    public void unlock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        if (lock != null && lock.isHeldByCurrentThread()) {
            lock.unlock();
        }
    }

    public void setRedissonClient(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    /**
     * 排名初初始化数据
     *
     * @param key
     * @param num
     * @param member
     * @return
     */
    @Override
    public Long zadd(String key, double num, String member) {
        RScoredSortedSet<String> set = redissonClient.getScoredSortedSet(key);
        boolean flag = set.add(num , member);
        return flag ? 1L : 0L;
    }

    /**
     * 倒序获取set列表
     *
     * @param key
     * @param start
     * @param stop
     * @return
     */
    @Override
    public Set<String> zrevrange(String key, int start, int stop) {
        RScoredSortedSet<String> sortedSet = redissonClient.getScoredSortedSet(key);
        return new HashSet<>(sortedSet.valueRangeReversed(start , stop));
    }

    /**
     * 倒序获取set列表 , 获取member 和 score
     *
     * @param key   map key
     * @param start 开始值 获取全量用 0
     * @param stop  结束值 获取全量用 -1
     * @return
     */
    @Override
    public List<Element> zrevrangeWithScores(String key, int start, int stop) {
        RScoredSortedSet<String> sortedSet = redissonClient.getScoredSortedSet(key);
        return convert(sortedSet.entryRangeReversed( start , stop));
    }

    /**
     * 给指定的排行榜加分数
     *
     * @param key    map key
     * @param num    指定的票数或者分数
     * @param member 成员
     * @return
     */
    @Override
    public Double zincrby(String key, double num, String member) {
        RScoredSortedSet<String> sortedSet = redissonClient.getScoredSortedSet(key);
        return sortedSet.addScore(member , num);
    }

    /**
     * 除有序集合中的一个或多个成员
     *
     * @param key    set key
     * @param member 一个或者多个成员
     * @return
     */
    @Override
    public Long zrem(String key, String... member) {
        RScoredSortedSet<String> sortedSet = redissonClient.getScoredSortedSet(key);
        boolean flag = sortedSet.removeAll(CollectionUtils.arrayToList(member));
        if(flag) {
            return 1L;
        } else {
            return 0L;
        }
    }

    private List<Element> convert(Collection<ScoredEntry<String>> tuples) {
        List<Element> set = new ArrayList<>();
        Iterator<ScoredEntry<String>> iterator = tuples.iterator();
        while (iterator.hasNext()) {
            ScoredEntry<String> tuple = iterator.next();
            Element element = new Element();
            element.setMember(tuple.getValue());
            element.setScore(tuple.getScore());
            set.add(element);
        }
        return set;
    }
}
