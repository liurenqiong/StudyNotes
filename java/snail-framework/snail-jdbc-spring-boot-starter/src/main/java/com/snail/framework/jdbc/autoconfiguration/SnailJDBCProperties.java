package com.snail.framework.jdbc.autoconfiguration;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author snail
 * @create 2019/8/26.
 **/
@Data
@Slf4j
@ConfigurationProperties(prefix = SnailJDBCProperties.PREFIX)
public class SnailJDBCProperties {

    public final static String PREFIX = "snail.spring.data.jdbc";

    /** 数据源参数 */
    private Map<String, String> druidDBCfg = new HashMap<String, String>();
    /** 方法事务配置：什么方法是、否使用事务、何时回滚 */
    private Properties txAttributes = new Properties();
    /** 事务作用的类表达式 */
    private String manageMethod;
    /** mybatis 主配置 */
    private String configLocation;
    /** mybatis sql文件 */
    private String mapperLocations;
}
