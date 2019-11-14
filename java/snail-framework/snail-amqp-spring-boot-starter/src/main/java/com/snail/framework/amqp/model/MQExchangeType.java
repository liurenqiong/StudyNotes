package com.snail.framework.amqp.model;

/**
 * 交换机类型
 * @author snail
 * @create 2019/8/1.
 **/
public enum MQExchangeType {

    DIRECT("直连交换机"),

    TOPIC("主题交换机"),

    FANOUT("广播交换机"),

    ;

    private String desc;

    private MQExchangeType(String desc) {
        this.desc = desc;
    }

    public String desc() {
        return this.desc;
    }
}
