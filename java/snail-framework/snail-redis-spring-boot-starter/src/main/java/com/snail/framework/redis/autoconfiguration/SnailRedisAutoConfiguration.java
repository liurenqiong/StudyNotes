package com.snail.framework.redis.autoconfiguration;

import com.snail.framework.redis.data.SnailRedisTemplate;
import com.snail.framework.redis.data.IRedisTemplate;
import com.snail.framework.redis.util.ObjectTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author snail
 * @create 2019-07-20 16:10
 */
@Slf4j
@Configuration
@ConditionalOnMissingClass({"org.redisson.Redisson"})
@EnableConfigurationProperties(SnailRedisProperties.class)
public class SnailRedisAutoConfiguration {

    @Bean(name = "snailRedisTemplate")
    public IRedisTemplate snailRedisTemplate(SnailRedisProperties properties) {
        return this.generateBaseRedisDao(properties.getRedisPoolCfg() , properties.getRedisShardCfg() , properties.getDbIndex() , properties.getHostsCfg());
    }

    /**
     * 产生BaseRedisDao
     *
     * @param dbIndex
     * @return
     * @throws Exception
     */
    private SnailRedisTemplate generateBaseRedisDao(Map<String, Object> redisPoolCfg , Map<String, Object> redisShardCfg ,
                                                  int dbIndex , List<String> hostsCfg){
        log.info("====================配置redis dbIndex=" + dbIndex + "=====================");

        SnailRedisTemplate dao = new SnailRedisTemplate();
        try {
            JedisPoolConfig cfg = new JedisPoolConfig();
            ObjectTool.setValue(cfg, redisPoolCfg);

            List<JedisShardInfo> shardInfoList = new ArrayList<JedisShardInfo>();
            JedisShardInfo shardInfo = null;
            for (String host : hostsCfg) {
                shardInfo = new JedisShardInfo(host);
                redisShardCfg.put("db", dbIndex);
                ObjectTool.setValue(shardInfo, redisShardCfg);
                shardInfoList.add(shardInfo);
            }
            ShardedJedisPool pool = new ShardedJedisPool(cfg, shardInfoList);

            log.info("ShardedJedisPool创建成功");

            dao.setShardedJedisPool(pool);
            log.info("BaseRedisDao创建成功");
        } catch (Exception e) {
            throw new RuntimeException("初始化redis失败" , e);
        }
        return dao;
    }

}
