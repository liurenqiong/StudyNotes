package com.snail.framework.amqp.model;

/**
 * @author snail
 * @create 2019/8/1.
 **/
public interface MQueueClient {

     /**
     * 发送消息
     * @param o
     */
   public void pushMessage(MessageModel o);
}
