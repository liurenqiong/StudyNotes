package com.snail.framework.redis.autoconfiguration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * @author snail
 * @create 2019-07-20 16:20
 */
@Slf4j
@ConfigurationProperties(prefix = "snail.spring.data.redis")
public class SnailRedisProperties {

    // redis连接池配置参数
    private Map<String, Object> redisPoolCfg;

    // redis配置参数
    private Map<String, Object> redisShardCfg;

    // 数据源参数
    private List<String> hostsCfg;

    // db索引
    private Integer dbIndex;


    public Map<String, Object> getRedisPoolCfg() {
        return redisPoolCfg;
    }

    /**
     * @设置 redis连接池配置参数
     */
    public void setRedisPoolCfg(Map<String, Object> redisPoolCfg) {
        this.redisPoolCfg = redisPoolCfg;
    }

    /**
     * @取得 redis配置参数
     */
    public Map<String, Object> getRedisShardCfg() {
        return redisShardCfg;
    }

    /**
     * @设置 redis配置参数
     */
    public void setRedisShardCfg(Map<String, Object> redisShardCfg) {
        this.redisShardCfg = redisShardCfg;
    }

    /**
     * @取得 数据源参数
     */
    public List<String> getHostsCfg() {
        return hostsCfg;
    }

    /**
     * @设置 数据源参数
     */
    public void setHostsCfg(List<String> hostsCfg) {
        this.hostsCfg = hostsCfg;
    }

    public Integer getDbIndex() {
        return dbIndex;
    }

    public void setDbIndex(Integer dbIndex) {
        this.dbIndex = dbIndex;
    }
}
