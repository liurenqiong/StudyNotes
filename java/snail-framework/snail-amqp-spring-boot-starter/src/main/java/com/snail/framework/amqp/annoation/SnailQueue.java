package com.snail.framework.amqp.annoation;

import com.snail.framework.amqp.model.MQConstant;
import com.snail.framework.amqp.model.MQExchangeType;

import java.lang.annotation.*;

/**
 * @author snail
 * @create 2019/8/1.
 **/
@Target({ElementType.TYPE , ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SnailQueue {

    /**
     * 队列名称
     * @return
     */
    String queue() default "";

    /**
     * 交换机名称
     * @return
     */
    String exchangeName() default MQConstant.DEFAULT_DIRECT_EXCHANGE_NAME;

    /**
     * 交换机类型
     * @return
     */
    MQExchangeType exchangeType() default MQExchangeType.DIRECT;

    /**
     * MQ工作模式
     * @return
     */
    //MQMode mode() default MQMode.ASYNC;
}
