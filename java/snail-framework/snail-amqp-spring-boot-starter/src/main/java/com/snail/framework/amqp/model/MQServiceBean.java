package com.snail.framework.amqp.model;

import lombok.Data;

/**
 * @author snail
 * @create 2019/8/1.
 **/
@Data
public class MQServiceBean extends MQBean {

    private String exchangeName;
}
