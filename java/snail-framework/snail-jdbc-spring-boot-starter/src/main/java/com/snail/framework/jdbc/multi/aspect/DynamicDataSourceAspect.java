package com.snail.framework.jdbc.multi.aspect;

import com.snail.framework.jdbc.multi.annotation.SnailDataSource;
import com.snail.framework.jdbc.multi.autoconfiguration.DynamicDataSourceHolder;
import com.snail.framework.jdbc.multi.exception.SnailDynamicDataSourceException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

/**
 * @author snail
 * @create 2019/9/18.
 **/
@Slf4j
@Aspect
@Component
@EnableAspectJAutoProxy
@Order(1)
public class DynamicDataSourceAspect {

    /**
     * 切入点 所有 com.snail.framework.jdbc.multi.annotation.SnailDataSource 的方法
     */
    @Pointcut("@annotation(com.snail.framework.jdbc.multi.annotation.SnailDataSource)")
    public void dynamicDataSourcePointCut() {

    }

    /**
     * slave的数据源，暂时不支持，插入操作，只支持查询，查询是必须是read-only的，并且事物是request_new的
     * 进入方法之前进行设置数据源
     */
    @Before("dynamicDataSourcePointCut()")
    public void before (JoinPoint point){
        Method method = ((MethodSignature)point.getSignature()).getMethod();

        SnailDataSource snailDataSource = method.getAnnotation(SnailDataSource.class);
        if(snailDataSource == null) {
            return;
        }

        //是否是master数据源
        boolean master = snailDataSource.master();
        String dataSourceKey = snailDataSource.datasource();
        if(!master) {

            // slave数据源
            if(!dataSourceKey.startsWith("slave")) {
                log.info("slave数据源数据源key异常，必须为slave前缀开始");
                throw new SnailDynamicDataSourceException("slave数据源数据源key异常，必须为slave前缀开始");
            }

            // slave数据源，必须添加@Transactional(propagation = Propagation.REQUIRES_NEW , readOnly = true)
            Transactional transactional = method.getAnnotation(Transactional.class);
            if(transactional == null) {
                log.info("slave数据源，必须添加@Transactional注解");
                throw new SnailDynamicDataSourceException("slave数据源，必须添加@Transactional注解");
            }

            boolean readOnly = transactional.readOnly();
            Propagation propagation = transactional.propagation();
            if(!readOnly) {
                log.info("slave数据源，必须是只读的");
                throw new SnailDynamicDataSourceException("slave数据源，必须是只读的");
            }

            if(propagation.value() != Propagation.REQUIRES_NEW.value()) {
                log.info("slave数据源，Propagation必须是Propagation.REQUIRES_NEW");
                throw new SnailDynamicDataSourceException("slave数据源，Propagation必须是Propagation.REQUIRES_NEW");
            }
        } else {

            // master数据源
            if(!dataSourceKey.startsWith("master")) {
                log.info("master数据源数据源key异常，必须为master前缀开始");
                throw new SnailDynamicDataSourceException("master数据源数据源key异常，必须为master前缀开始");
            }
        }

        DynamicDataSourceHolder.set(dataSourceKey);

        log.info("AOP动态切换数据源:methodName:{} , dataSourceKey:{}" , method.getName() , dataSourceKey);
    }

    /**
     * 之后清除的数据源
     */
    @After("dynamicDataSourcePointCut()")
    public void after() {
        log.info("清楚该线程的数据源。。。");
        DynamicDataSourceHolder.clear();
    }
}
