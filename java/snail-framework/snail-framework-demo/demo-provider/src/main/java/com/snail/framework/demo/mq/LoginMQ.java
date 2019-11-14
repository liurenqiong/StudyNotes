package com.snail.framework.demo.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.snail.framework.amqp.annoation.SnailQueue;
import com.snail.framework.amqp.model.MQExchangeType;
import com.snail.framework.amqp.model.MQueueClient;
import com.snail.framework.amqp.model.MessageModel;

@Component
@SnailQueue(queue = LoginMQ.LOGIN_QUEUE_NAME , exchangeName = LoginMQ.LOGIN_EXCHANGE_NAME , exchangeType = MQExchangeType.DIRECT)
public class LoginMQ implements MQueueClient{

	public final static String LOGIN_QUEUE_NAME = "direct.login.queue";

    public final static String LOGIN_EXCHANGE_NAME = "direct.login.exchange";

    @Autowired
    private RabbitTemplate rabbitTemplate;
    
	@Override
	public void pushMessage(MessageModel o) {
		   rabbitTemplate.convertAndSend(LOGIN_EXCHANGE_NAME , o.getRouteKey() , o);
	}

}
