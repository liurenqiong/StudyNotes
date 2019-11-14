package com.snail.framework.amqp.autoconfig;

import com.snail.framework.amqp.annoation.SnailQueue;
import com.snail.framework.amqp.consumer.MQServiceBeanRegister;
import com.snail.framework.amqp.exchange.MQExchangeBeanRegister;
import com.snail.framework.amqp.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author snail
 * @create 2019/11/11.
 **/
@Slf4j
public class MQInitConfiguration {

    @Autowired
    private RabbitAdmin rabbitAdmin;

    @Autowired
    MQServiceBeanRegister mqServiceBeanRegister;

    @Autowired
    MQExchangeBeanRegister mqExchangeBeanRegister;

    @Autowired(required = false)
    private Map<String , MQueueClient> serviceBeanMap;

    /**
     * 交换机缓存列表
     */
    private ConcurrentHashMap<String , String> exchangeCache = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        this.register();
    }

    /**
     * 注册所有的交换机和队列
     * @return
     */
    private String register() {

        // 注册默认交换机
        this.registerDefaultExchange();

        // 注册所有的交换机，并且和队列进行绑定
        if(serviceBeanMap != null && serviceBeanMap.size() > 0) {
            for(Map.Entry<String , MQueueClient> entry: serviceBeanMap.entrySet()) {
                MQueueClient queueClient = entry.getValue();
                SnailQueue snailQueue = queueClient.getClass().getAnnotation(SnailQueue.class);
                if(snailQueue != null) {
                    this.registerExchange(snailQueue);
                    this.registerQueue(snailQueue);
                }
            }
        }

        return "";
    }


    /**
     * 注册交换机
     * @param snailQueue
     */
    private void registerQueue(SnailQueue snailQueue) {
        MQServiceBean mqServiceBean = new MQServiceBean();
        mqServiceBean.setExchangeName(snailQueue.exchangeName());
        mqServiceBean.setQueueName(snailQueue.queue());
        mqServiceBeanRegister.register(mqServiceBean , rabbitAdmin);
    }

    /**
     * 注册交换机
     * @param snailQueue
     */
    private void registerExchange(SnailQueue snailQueue) {
        String exchangeName = snailQueue.exchangeName();
        String exchangeType = snailQueue.exchangeType().name();
        String key = createExchangeKey(exchangeName , exchangeType);
        if(!exchangeCache.containsKey(key)) {
            this.registerExchange(snailQueue.exchangeName() , snailQueue.exchangeType());
        }
    }

    /**
     * 注册交换机
     * @param exchangeName
     * @param exchangeType
     */
    private void registerExchange(String exchangeName , MQExchangeType exchangeType) {
        String key = createExchangeKey(exchangeName , exchangeType.name());
        MQExchangeBean mqExchangeBean = new MQExchangeBean();
        mqExchangeBean.setMqExchangeType(exchangeType);
        mqExchangeBean.setExchangeName(exchangeName);
        mqExchangeBeanRegister.register(mqExchangeBean , rabbitAdmin);
        exchangeCache.put(key , key);
    }

    private String createExchangeKey(String exchangeName , String exchangeType) {
        return exchangeName + "_" + exchangeType;
    }

    /**
     * 注入默认的交换机
     */
    private void registerDefaultExchange() {
        this.registerExchange(MQConstant.DEFAULT_DIRECT_EXCHANGE_NAME , MQExchangeType.DIRECT);
        this.registerExchange(MQConstant.DEFAULT_TOPIC_EXCHANGE_NAME , MQExchangeType.TOPIC);
        this.registerExchange(MQConstant.DEFAULT_FANOUT_EXCHANGE_NAME , MQExchangeType.FANOUT);
    }
}
