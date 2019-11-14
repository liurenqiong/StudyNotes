package com.snail.framework.amqp.model;

/**
 * @author snail
 * @create 2019/11/11.
 **/
public interface MQueueServer {

    /**
     * 监听消息
     * @param message
     */
    public void onMessage(MessageModel message);
}
