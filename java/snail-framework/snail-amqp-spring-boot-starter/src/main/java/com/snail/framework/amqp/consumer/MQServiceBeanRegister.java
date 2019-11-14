package com.snail.framework.amqp.consumer;

import com.snail.framework.amqp.autoconfig.MQBeanRegister;
import com.snail.framework.amqp.model.MQServiceBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitAdmin;

/**
 * MQ服务端注册器
 * @author snail
 * @create 2019/8/1.
 **/
@Slf4j
public class MQServiceBeanRegister extends MQBeanRegister implements ServiceBeanRegister {

    /**
     * 初始化
     * @param rabbitAdmin
     */
    public MQServiceBeanRegister(RabbitAdmin rabbitAdmin) {

        super.init(rabbitAdmin);
    }

    /**
     * 注册
     *
     * @param serviceBean 服务端(消费者)相关参数
     * @param rabbitAdmin
     */
    @Override
    public void register(MQServiceBean serviceBean , RabbitAdmin rabbitAdmin) {

        String queueName = serviceBean.getQueueName();
        String routingKey = serviceBean.getQueueName();

        // 声明队列
        this.declareQueue(queueName);

        // 绑定队列
        this.declareBinding(serviceBean.getExchangeName() , routingKey , queueName);
        log.info("交换机和队列进行绑定queueName:{},exchangeName:{}" , queueName ,serviceBean.getExchangeName());
    }

    /**
     * 是否支持
     *
     * @param serviceBean 服务端(消费者)相关参数
     * @return
     */
    @Override
    public boolean isSupport(MQServiceBean serviceBean) {
        return true;
    }
}
