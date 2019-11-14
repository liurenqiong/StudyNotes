package com.snail.framework.amqp.exchange;

import com.snail.framework.amqp.autoconfig.MQBeanRegister;
import com.snail.framework.amqp.model.MQExchangeBean;
import com.snail.framework.amqp.model.MQExchangeType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitAdmin;

/**
 * @author snail
 * @create 2019/8/1.
 **/
@Slf4j
public class MQExchangeBeanRegister extends MQBeanRegister implements ExchangeBeanRegister {

    /**
     * 注册默认的交换机
     */
    public MQExchangeBeanRegister(RabbitAdmin rabbitAdmin) {
        super.init(rabbitAdmin);
    }

    /**
     * 注册
     *
     * @param exchangeBean  客户端相关参数
     * @param rabbitAdmin
     */
    @Override
    public void register(MQExchangeBean exchangeBean , RabbitAdmin rabbitAdmin) {

        //注册自定义交换机
        this.registerDefineExchange(exchangeBean.getMqExchangeType() , exchangeBean.getExchangeName());
    }

    /**
     * 注册自定义交换机
     * @param exchangeType
     */
    private void registerDefineExchange(MQExchangeType exchangeType , String exchangeName) {
        switch (exchangeType) {
            case DIRECT:
                this.declareDirectExchange(exchangeName);
                break;
            case FANOUT:
                this.declareFanoutExchange(exchangeName);
                break;
            case TOPIC:
                this.declareTopicExchange(exchangeName);
                break;
            default:
                this.declareDirectExchange(exchangeName);
        }

    }

    /**
     * 是否支持
     *
     * @param exchangeBean 客户端相关参数
     * @return
     */
    @Override
    public boolean isSupport(MQExchangeBean exchangeBean) {
        return true;
    }
}
