package com.snail.framework.amqp.model;

import lombok.Data;

/**
 * @author snail
 * @create 2019/8/1.
 **/
@Data
public abstract class MQBean {


   private String queueName;

   private MQMode mode;

   private MQProtocol protocol;

    MQBean() {
        this.mode = MQMode.SYNC;
        this.protocol = MQProtocol.MQ;
    }

    protected String pointConvertUpper(String str) {
        boolean isPoint = false;
        int index = -1;
        char[] chars = str.toCharArray();
        char[] arr = str.toCharArray();
        int len = arr.length;

        for(int i = 0; i < len; ++i) {
            Character c = arr[i];
            ++index;
            if (c.equals('.')) {
                isPoint = true;
            } else if (isPoint) {
                chars[index] = Character.toUpperCase(c);
                isPoint = false;
            }
        }

        return (new String(chars)).replaceAll("\\.", "");
    }
}
