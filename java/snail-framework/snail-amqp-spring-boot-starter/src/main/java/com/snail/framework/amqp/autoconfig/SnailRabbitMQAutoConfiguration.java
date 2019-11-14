package com.snail.framework.amqp.autoconfig;

import com.snail.framework.amqp.consumer.MQServiceBeanRegister;
import com.snail.framework.amqp.exchange.MQExchangeBeanRegister;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  RabbitMQ 启动封装
 * @author snail
 * @create 2019/7/30.
 **/
@Slf4j
@Configuration
@EnableConfigurationProperties(SnailRabbitMQProperties.class)
public class SnailRabbitMQAutoConfiguration {

    @Autowired
    SnailRabbitMQProperties properties;

    /**
     * 初始化connectionFactory
     * @return
     * @throws Exception
     */
    @Bean(name = "snailConnectionFactory")
    ConnectionFactory connectionFactory() {
        log.info("开始初始化connectionFactory，host：" + properties.getHost());
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(properties.getHost() , properties.getPort());
        connectionFactory.setUsername(properties.getUsername());
        connectionFactory.setPassword(properties.getPassword());
        connectionFactory.setVirtualHost(properties.getVirtualHost());
        connectionFactory.setPublisherConfirms(true); // 必须要设置
        log.info("connectionFactory初始化完成，host：" + properties.getHost());
        return connectionFactory;
    }

    /**
     * 创建rabbitTemplate
     * @return
     * @throws Exception
     */
    @Bean(name = "snailRabbitTemplate")
    RabbitTemplate rabbitTemplate(@Qualifier("snailConnectionFactory") ConnectionFactory connectionFactory)  {
        log.info("开始创建rabbit template，host：" + connectionFactory.getHost());
        RabbitTemplate template =  new RabbitTemplate(connectionFactory);
        log.info("rabbit template创建完成，host：" + connectionFactory.getHost());
        return template;
    }

    /**
     * 创建 rabbitAdmin
     * @param connectionFactory
     * @return
     */
    @Bean(name = "snailRabbitAdmin")
    RabbitAdmin rabbitAdmin(@Qualifier("snailConnectionFactory") ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin =  new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    /**
     * 队列注册器
     * @return
     */
    @Bean
    MQServiceBeanRegister mqServiceBeanRegister(@Qualifier("snailRabbitAdmin") RabbitAdmin rabbitAdmin) {
        return new MQServiceBeanRegister(rabbitAdmin);
    }

    /**
     * 交换机注册器
     * @return
     */
    @Bean
    MQExchangeBeanRegister mqExchangeBeanRegister(@Qualifier("snailRabbitAdmin") RabbitAdmin rabbitAdmin) {
        return new MQExchangeBeanRegister(rabbitAdmin);
    }

    /**
     * 初始化注册器
     * @return
     */
    @Bean
    MQInitConfiguration mqInitConfiguration() {
        return new MQInitConfiguration();
    }
}
