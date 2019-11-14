package com.snail.framework.amqp.model;

/**
 * @author snail
 * @create 2019/8/1.
 **/
public enum MQMode {

    SYNC("同步"),

    ASYNC("异步"),

    BROADCAST("广播"),

    TOPIC("多播"),
    ;

    private String desc;

    private MQMode(String desc) {
        this.desc = desc;
    }

    public String desc() {
        return this.desc;
    }
}
