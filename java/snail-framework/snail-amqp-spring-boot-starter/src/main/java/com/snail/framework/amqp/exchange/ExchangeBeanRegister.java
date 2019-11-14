package com.snail.framework.amqp.exchange;

import com.snail.framework.amqp.model.MQExchangeBean;
import org.springframework.amqp.rabbit.core.RabbitAdmin;

/**
 * @author snail
 * @create 2019/8/1.
 **/
public interface ExchangeBeanRegister {

    /**
     * 注册
     * @param exchangeBean 交换机相关参数
     * @param rabbitAdmin
     */
    void register(MQExchangeBean exchangeBean , RabbitAdmin rabbitAdmin);

    /**
     * 是否支持
     * @param exchangeBean 交换机相关参数
     * @return
     */
    boolean isSupport(MQExchangeBean exchangeBean);
}
