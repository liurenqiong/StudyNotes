package com.snail.framework.redis.redisson;


import com.snail.framework.redis.autoconfiguration.SnailRedisProperties;
import com.snail.framework.redis.data.SnailRedissonTemplate;
import com.snail.framework.redis.data.IRedisTemplate;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.List;


/**
 * Redisson
 */
@Configuration
@EnableConfigurationProperties(SnailRedisProperties.class)
@ConditionalOnClass({Redisson.class})
public class RedissonAutoConfiguration {

    @Bean
    RedissonClient redissonClient(SnailRedisProperties redisProperties) {
        Config config = new Config();
        String node = create(redisProperties.getHostsCfg());
        node = node.startsWith("redis://") ? node : "redis://" + node;
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress(node)
                .setTimeout((Integer) redisProperties.getRedisShardCfg().get("connectionTimeout"))
                .setConnectionMinimumIdleSize((Integer) redisProperties.getRedisPoolCfg().get("maxIdle"))
                .setDatabase(redisProperties.getDbIndex())
                ;

        Object password = redisProperties.getRedisShardCfg().get("password");
        if (!StringUtils.isEmpty(password)) {
            serverConfig.setPassword(password.toString());
        }

        return Redisson.create(config);
    }

    private String create(List<String> hostsCfg) {
        StringBuilder nodes = new StringBuilder();
        for(String host : hostsCfg) {
            nodes.append(host);
        }
        return nodes.toString();
    }

    @Bean(name = "snailRedissonTemplate")
    IRedisTemplate redissonTemplate(@Qualifier("redissonClient") RedissonClient redissonClient) {
        return new SnailRedissonTemplate(redissonClient);
    }
}
