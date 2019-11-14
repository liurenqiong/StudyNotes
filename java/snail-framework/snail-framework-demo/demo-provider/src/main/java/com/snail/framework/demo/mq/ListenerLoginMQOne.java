package com.snail.framework.demo.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.snail.framework.amqp.model.MQueueServer;
import com.snail.framework.amqp.model.MessageModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RabbitListener(queues = LoginMQ.LOGIN_QUEUE_NAME)
public class ListenerLoginMQOne implements MQueueServer {

	/**
     * 监听消息
     *
     * @param message
     */
    @Override
    @RabbitHandler
    public void onMessage(MessageModel message) {
        log.info("ListenerLoginMQOne接收到的消息为,message:{}" , message.toString());
    }

}
