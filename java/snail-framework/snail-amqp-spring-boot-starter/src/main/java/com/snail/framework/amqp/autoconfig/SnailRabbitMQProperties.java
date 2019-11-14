package com.snail.framework.amqp.autoconfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author snail
 * @create 2019/7/30.
 **/
@Data
@ConfigurationProperties(prefix = SnailRabbitMQProperties.SNAIL_RABBIT_PREFIX)
public class SnailRabbitMQProperties {

    public final static String SNAIL_RABBIT_PREFIX = "snail.rabbit";

    private String username;

    private String password;

    private String virtualHost;

    private String host;

    private int port;

    private String defaultExchangeName = "snail.default.direct.exchange";
}
