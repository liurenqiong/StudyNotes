package com.snail.framework.lock;

import com.snail.framework.lock.annotation.LockType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author snail
 * @create 2019/8/29.
 **/
@Component
public class DistributedFactory {

    @Autowired(required = false)
    private Map<String , DistributedLock> distributedLockFactory;

    public DistributedLock distributedLock(LockType lockType) {
        String name = lockType.name();
        if(distributedLockFactory == null || !distributedLockFactory.containsKey(name)) {
            return null;
        }
        return distributedLockFactory.get(name);
    }
}
