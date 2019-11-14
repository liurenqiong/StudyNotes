package com.snail.framework.amqp.autoconfig;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author snail
 * @create 2019/7/30.
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({SnailRabbitMQAutoConfiguration.class})
@Documented
public @interface EnableSnailRabbit {
}
