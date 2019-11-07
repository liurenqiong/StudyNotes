package com.snail.framework.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author snail
 * @create 2019/8/29.
 **/
public interface DistributedLock {

    /**
     * 上锁
     * @param lockKey
     * @param unit
     * @param timeout
     * @throws DistributedLockException
     */
    boolean lock(String lockKey, TimeUnit unit, int timeout) throws DistributedLockException;

    /**
     * 尝试获取锁
     *
     * @param lockKey
     * @param unit      时间单位
     * @param waitTime  最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @throws DistributedLockException
     * @return
     */
    boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime) throws DistributedLockException;

    /**
     * 释放锁
     * @param lockKey
     * @throws DistributedLockException
     */
    void unlock(String lockKey) throws DistributedLockException;
}
