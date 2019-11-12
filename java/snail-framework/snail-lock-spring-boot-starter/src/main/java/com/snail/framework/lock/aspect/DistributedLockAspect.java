package com.snail.framework.lock.aspect;

import com.snail.framework.lock.DistributedFactory;
import com.snail.framework.lock.DistributedLock;
import com.snail.framework.lock.DistributedLockException;
import com.snail.framework.lock.EDistributedLockMsg;
import com.snail.framework.lock.annotation.SnailLock;
import com.snail.framework.lock.annotation.LockType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author snail
 * @create 2019/8/29.
 **/
@Slf4j
@Aspect
@Component
@EnableAspectJAutoProxy
public class DistributedLockAspect {

    @Autowired
    private DistributedFactory distributedFactory;

    /**
     * 用于SpEL表达式解析.
     */
    private ExpressionParser parser = new SpelExpressionParser();
    /**
     * 用于获取方法参数定义名字.
     */
    private DefaultParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();


    /**
     * 切入点 所有 com.snail.framework.lock.annotation.SnailLock 的方法
     */
    @Pointcut("@annotation(com.snail.framework.lock.annotation.SnailLock)")
    public void distributionLockPointCut() {

    }

    /**
     * 环绕通知 ， 通知 + distributionLockPointCut() = 切面
     * @param pjp
     * @return
     * @throws Exception
     */
    @Around("distributionLockPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {

        // 获取切点所在的Method
        Method method = ((MethodSignature)pjp.getSignature()).getMethod();
        log.info("进入切点，methodName:{}" , method.getName());
        // 根据注解获取lockKey
        String lockKey = getLockKey(method , pjp.getArgs());
        String actualLockKey = this.generateKeyBySpel(lockKey, pjp, method);
        log.info("lockKey【{}】, 解析结果【{}】", lockKey, actualLockKey);
        // 获取锁
        return lock(actualLockKey , pjp , method);
    }

    private String generateKeyBySpel(String spelString, ProceedingJoinPoint joinPoint, Method method){
        // 使用spring的DefaultParameterNameDiscoverer获取方法形参名数组
        String[] paramNames = nameDiscoverer.getParameterNames(method);
        // 解析过后的Spring表达式对象
        Expression expression = parser.parseExpression(spelString);
        // spring的表达式上下文对象
        EvaluationContext context = new StandardEvaluationContext();
        // 通过joinPoint获取被注解方法的形参
        Object[] args = joinPoint.getArgs();
        // 给上下文赋值
        for(int i = 0 ; i < args.length ; i++) {
            assert paramNames != null;
            context.setVariable(paramNames[i], args[i]);
        }
        return Objects.requireNonNull(expression.getValue(context)).toString();
    }

    /**
     * 获取锁
     * @param lockKey
     * @param pjp
     * @param method
     * @return
     */
    private Object lock(final String lockKey, ProceedingJoinPoint pjp, Method method) throws Throwable {

        // 获取注解
        SnailLock lock = method.getAnnotation(SnailLock.class);
        log.info("获取的LOCK信息,info:{}" , lockString(lock));

        // 是否尝试获取锁
        boolean isTry = lock.tryLock();

        if(isTry) {
            return tryLock(pjp , lock , lockKey);
        } else {

            return lock(pjp , lock , lockKey);
        }
    }

    /**
     * 直接获取锁
     * @param pjp
     * @param lock
     * @param lockKey
     */
    private Object lock(ProceedingJoinPoint pjp , SnailLock lock , String lockKey) throws Throwable {
        int leaseTime = lock.leaseTime(); // 锁失效时间
        TimeUnit timeUnit = lock.timeUnit(); //
        DistributedLock distributedLock = defineLock(lock.lockType());

        if(distributedLock == null) {
            log.info("获取到相应的锁的信息");
            throw new Exception("获取到相应的锁的信息出现异常");
        }

        boolean flag = false;

        try {
            flag = distributedLock.lock(lockKey, timeUnit, leaseTime);
        } catch (Exception e) {
            throw new DistributedLockException(EDistributedLockMsg.TRY_LOCK_FAIL);
        }

        try {
            if (flag) {
                log.info("获取锁成功，开始执行业务逻辑，lockKey:{}", lockKey);
                return pjp.proceed();
            } else {
                log.info("获取锁失败，开始执行业务逻辑，lockKey:{}", lockKey);
                throw new DistributedLockException(EDistributedLockMsg.TRY_LOCK_TIME_OUT);
            }
        } finally {
            distributedLock.unlock(lockKey);
        }
    }

    /**
     * 尝试获取锁
     */
    private Object tryLock(ProceedingJoinPoint pjp , SnailLock lock , String lockKey) throws Throwable {
        int leaseTime = lock.leaseTime();
        int waitTime = lock.waitTime();
        TimeUnit timeUnit = lock.timeUnit();
        DistributedLock distributedLock = defineLock(lock.lockType());

        if(distributedLock == null) {
            log.info("获取到相应的锁的信息");
            throw new Exception("获取到相应的锁的信息出现异常");
        }

        boolean flag = false;
        try {
            flag = distributedLock.tryLock(lockKey, timeUnit, waitTime, leaseTime);
        } catch (Exception e) {
            log.info("尝试获取锁出现未知异常，抛出异常,key:{} ", lockKey);
            throw new DistributedLockException(EDistributedLockMsg.TRY_LOCK_FAIL);
        }

		if (!flag) {
			log.info("尝试获取锁超时，抛出异常,key:{} , flag:{}", lockKey, flag);
			throw new DistributedLockException(EDistributedLockMsg.TRY_LOCK_TIME_OUT);
		}

		try {
			// 如果等待时间不等于0，则redis最长的等待时间为waitTime
			// 如果没有获得锁则放回false
			// 如果获得锁则放回true，让后上锁后自动释放锁时间leaseTime

			log.info("尝试获取锁成功，开始执行业务逻辑，key:{} , flag:{}", lockKey, flag);
			// 执行业务方法
			return pjp.proceed();

		} finally {
			distributedLock.unlock(lockKey);
		}
	}

    /**
     * 获取lockKey
     *
     * @param method
     * @param args
     * @return
     */
    private String getLockKey(Method method, Object[] args) {
        SnailLock lock = method.getAnnotation(SnailLock.class);
        return lock.lockKey();
    }


    private String lockString(SnailLock lock) {
        StringBuilder sb = new StringBuilder();
        sb.append(lock.lockPrefix()).append("#");
        sb.append(lock.lockKey()).append("#");
        sb.append(lock.lockSuffix()).append("#");
        sb.append(lock.leaseTime()).append("#");
        sb.append(lock.lockType()).append("#");
        sb.append(lock.tryLock()).append("#");
        sb.append(lock.separator()).append("#");
        sb.append(lock.waitTime()).append("#");
        sb.append(lock.timeUnit());
        return sb.toString();
    }

    /**
     * 获取锁的实现方法
     * @param lockType
     * @return
     */
    private DistributedLock defineLock(LockType lockType) {
        return distributedFactory.distributedLock(lockType);
    }
}
