package com.snail.framework.amqp.model;

/**
 * @author snail
 * @create 2019/11/11.
 **/
public class MQConstant {

    // 默认直接交换机
    public static final String DEFAULT_DIRECT_EXCHANGE_NAME = "snail.default.direct.exchange";

    // 默认TOPIC交换机
    public static final String DEFAULT_TOPIC_EXCHANGE_NAME = "snail.default.topic.exchange";

    // 默认广播交换机
    public static final String DEFAULT_FANOUT_EXCHANGE_NAME = "snail.default.fanout.exchange";

    // 默认的交换机类型
    public static final MQExchangeType DEFAULT_EXCHANGE_TYPE = MQExchangeType.DIRECT;
}
