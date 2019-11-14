package com.snail.framework.amqp.autoconfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;

/**
 * MQBean注册器
 * @author snail
 * @create 2019/8/1.
 **/
@Slf4j
public abstract class MQBeanRegister {

    private RabbitAdmin rabbitAdmin;

    public MQBeanRegister() {

    }

    protected void init(RabbitAdmin rabbitAdmin) {
        this.rabbitAdmin = rabbitAdmin;
    }

    /**
     * 初始化直连交换机
     * @param exchangeName 交换机名称
     * @param durable 是否实例化
     * @param autoDelete 是否自动删除
     * @return
     */
    protected String declareDirectExchange(String exchangeName , boolean durable , boolean autoDelete) {
        log.info("初始化Direct交换机,exchangeName:{}" , exchangeName);
        return this.declareExchange(new DirectExchange(exchangeName , durable , autoDelete));
    }

    /**
     * 初始化直连交换机
     * @param exchangeName 交换机名称
     * @return
     */
    protected String declareDirectExchange(String exchangeName) {
        return declareDirectExchange(exchangeName , true , false);
    }

    /**
     * 初始化TOPIC交换机
     * @param exchangeName 交换机名称
     * @param durable 是否实例化
     * @param autoDelete 是否自动删除
     * @return
     */
    protected String declareTopicExchange(String exchangeName , boolean durable , boolean autoDelete) {
        log.info("初始化Topic交换机,exchangeName:{}" , exchangeName);
        return this.declareExchange(new TopicExchange(exchangeName , durable , autoDelete));
    }

    /**
     * 初始化TOPIC交换机
     * @param exchangeName 交换机名称
     * @return
     */
    protected String declareTopicExchange(String exchangeName) {
        return declareTopicExchange(exchangeName , true , false);
    }

    /**
     * 初始化TOPIC交换机
     * @param exchangeName 交换机名称
     * @param durable 是否实例化
     * @param autoDelete 是否自动删除
     * @return
     */
    protected String declareFanoutExchange(String exchangeName , boolean durable , boolean autoDelete) {
        log.info("初始化Fanout交换机,exchangeName:{}" , exchangeName);
        return this.declareExchange(new FanoutExchange(exchangeName , durable , autoDelete));
    }

    /**
     * 初始化FANOUT交换机,广播交换机
     * @param exchangeName 交换机名称
     * @return
     */
    protected String declareFanoutExchange(String exchangeName) {
        return declareFanoutExchange(exchangeName , true , false);
    }


    /**
     * 初始化交换机
     * @return
     */
    protected String declareExchange(AbstractExchange exchange) {
        exchange.setShouldDeclare(false);
        this.rabbitAdmin.declareExchange(exchange);
        return exchange.getName();
    }

    /**
     * 初始化队列
     * @param queueName
     * @return
     */
    protected String declareQueue(String queueName) {
        return declareQueue(queueName , true , false , false);
    }

    /**
     * 初始化队列
     * @param queueName
     * @param durable
     * @param exclusive
     * @param autoDelete
     * @return
     */
    protected String declareQueue(String queueName, boolean durable, boolean exclusive, boolean autoDelete) {
        Queue queue = new Queue(queueName , durable , exclusive , autoDelete);
        queue.setShouldDeclare(false);
        this.rabbitAdmin.declareQueue(queue);
        return queue.getClass().getCanonicalName();
    }

    /**
     * 进行绑定
     * @param exchangeName
     * @param routingKey
     * @param queueName
     * @return
     */
    protected String declareBinding(String exchangeName , String routingKey , String queueName) {
        Binding binding = new Binding(queueName, Binding.DestinationType.QUEUE, exchangeName , routingKey, null);
        binding.setShouldDeclare(false);
        this.rabbitAdmin.declareBinding(binding);
        return binding.getClass().getCanonicalName();
    }
}
