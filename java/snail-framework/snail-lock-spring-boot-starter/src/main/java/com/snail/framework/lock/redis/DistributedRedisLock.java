package com.snail.framework.lock.redis;

import com.snail.framework.lock.DistributedLock;
import com.snail.framework.lock.DistributedLockException;
import com.snail.framework.redis.data.IRedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author snail
 * @create 2019/8/29.
 **/
@Component("REDIS")
public class DistributedRedisLock implements DistributedLock {

    @Autowired
    private IRedisTemplate redisTemplate;

    /**
     * 上锁
     *
     * @param lockKey
     * @param unit
     * @param timeout
     * @throws DistributedLockException
     */
    @Override
    public boolean lock(String lockKey, TimeUnit unit, int timeout) throws DistributedLockException {
        return redisTemplate.lock(lockKey , unit , timeout);
    }

    /**
     * 尝试获取锁
     *
     * @param lockKey
     * @param unit      时间单位
     * @param waitTime  最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @return
     * @throws DistributedLockException
     */
    @Override
    public boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime) throws DistributedLockException {
        try {
           return redisTemplate.tryLock(lockKey , unit , waitTime , leaseTime);
        } catch (Exception e) {
            throw new DistributedLockException("尝试获取锁失败，e:{}" , e);
        }
    }

    /**
     * 释放锁
     *
     * @param lockKey
     * @throws DistributedLockException
     */
    @Override
    public void unlock(String lockKey) throws DistributedLockException {
        redisTemplate.unlock(lockKey);
    }
}
