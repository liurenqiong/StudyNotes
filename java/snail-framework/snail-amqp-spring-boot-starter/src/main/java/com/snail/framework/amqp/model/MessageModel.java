package com.snail.framework.amqp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageModel implements Serializable {

    private String routeKey;

    private String queueDesc;

    private Date date;
    /** 响应消息 */
    private String msg;

    private String msgKey;

    private String msgId;
}