package com.snail.framework.amqp.consumer;

import com.snail.framework.amqp.model.MQServiceBean;
import org.springframework.amqp.rabbit.core.RabbitAdmin;

/**
 * @author snail
 * @create 2019/8/1.
 **/
public interface ServiceBeanRegister {

    /**
     * 注册
     * @param serviceBean 服务端(消费者)相关参数
     * @param rabbitAdmin
     */
    void register(MQServiceBean serviceBean , RabbitAdmin rabbitAdmin);

    /**
     * 是否支持
     * @param serviceBean 服务端(消费者)相关参数
     * @return
     */
    boolean isSupport(MQServiceBean serviceBean);
}
