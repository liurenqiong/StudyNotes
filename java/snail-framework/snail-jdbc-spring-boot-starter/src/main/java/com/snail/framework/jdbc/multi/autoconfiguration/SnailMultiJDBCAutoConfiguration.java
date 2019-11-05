package com.snail.framework.jdbc.multi.autoconfiguration;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author snail
 * @create 2019/8/26.
 **/
@Slf4j
@Configuration
@EnableConfigurationProperties(SnailMultiJDBCProperties.class)
public class SnailMultiJDBCAutoConfiguration {

    private final static String DRIVER_CLASS_NAME = "driverClassName";
    private final static String URL = "url";
    private final static String USERNAME = "username";
    private final static String PASSWORD = "password";

    final SnailMultiJDBCProperties properties;

    @Autowired
    public SnailMultiJDBCAutoConfiguration(SnailMultiJDBCProperties properties) {
        this.properties = properties;
    }

    /**
     * 创建数据源
     * @param info
     * @return
     * @throws Exception
     */
    private DataSource createDataSource(DataSourceInfo info) throws Exception {
        Map<String , String> druidConfig = properties.getDruidDBCfg();
        druidConfig.put(DRIVER_CLASS_NAME , info.getDriverClassName());
        druidConfig.put(URL , info.getUrl());
        druidConfig.put(USERNAME , info.getUsername());
        druidConfig.put(PASSWORD , info.getPassword());
        log.info("====================开始配置{}数据库=====================" , info.getDataSourceName());
        DataSource ds = DruidDataSourceFactory.createDataSource(druidConfig);
        log.info("====================配置{}数据库成功=====================" , info.getDataSourceName());
        return ds;
    }

    /**
     * 得到数据源
     *
     * @return
     * @throws Exception
     */
    @Bean(name = "dynamicDataSource")
    public DataSource dataSource(SnailMultiJDBCProperties properties) throws Exception {
        log.info("====================开始配置多数据源=====================");

        // 检查是否有多个master数据源，并获取master数据源
        check(properties.getDbs());

        DynamicDataSource dynamicDataSource = new DynamicDataSource();

        Map<Object , Object> datasourceMap = new HashMap<>();

        for(DataSourceInfo info : properties.getDbs()) {
            DataSource dataSource = createDataSource(info);
            if(info.getMaster().equals(1)) {

                // 设置默认数据源
                dynamicDataSource.setDefaultTargetDataSource(dataSource);
            }
            datasourceMap.put(info.getDataSourceName() ,dataSource);
        }

        dynamicDataSource.setTargetDataSources(datasourceMap);
        log.info("====================多数据源配置成功=====================");
        return dynamicDataSource;
    }

    /**
     * 创建mybatis SqlSessionFactory
     *
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "masterSqlSessionFactory")
    public SqlSessionFactory getSqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dataSource ,
                                                  SnailMultiJDBCProperties properties) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactory.setMapperLocations(resolver.getResources(properties.getMapperLocations()));
        sqlSessionFactory.setConfigLocation(resolver.getResource(properties.getConfigLocation()));
        sqlSessionFactory.setDataSource(dataSource);
        SqlSessionFactory factory = sqlSessionFactory.getObject();
        log.info("SqlSessionFactory创建成功");
        return factory;
    }

    /**
     * 得到单条sql语句操作模板
     *
     * @param sqlSessionFactory
     * @return
     */
    @Bean(name = "masterDBSqlSessionTemplate")
    public SqlSessionTemplate getSqlSessionTemplate(@Qualifier("masterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        log.info("SqlSessionTemplate创建成功");
        return sqlSessionTemplate;
    }

    /**
     * 得到批量sql操作模板
     *
     * @param sqlSessionFactory
     * @return
     */
    @Bean(name = "masterDBSqlSessionBatchTemplate")
    public SqlSessionTemplate getSqlSessionBatchTemplate(@Qualifier("masterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        SqlSessionTemplate sqlSessionBatchTemplate = new SqlSessionTemplate(sqlSessionFactory, ExecutorType.BATCH);
        log.info("SqlSessionBatchTemplate创建成功");
        return sqlSessionBatchTemplate;
    }

    /**
     * 事务管理
     *
     * @param dataSource
     * @return
     */
    @Bean
    public PlatformTransactionManager getTransactionManager(@Qualifier("dynamicDataSource") DataSource dataSource) {
        PlatformTransactionManager transactionTxManager = new DataSourceTransactionManager(dataSource);
        log.info("PlatformTransactionManager事务管理器创建成功");
        return transactionTxManager;
    }

    private void check(List<DataSourceInfo> infos) throws Exception {

        int masterNum = 0;
        for(DataSourceInfo info : infos) {
            String dataSourceName = info.getDataSourceName();
            if(isMaster(info.getMaster())) {
                masterNum = masterNum + 1;

                // master数据源，对DataSourceName做校验
                if(!dataSourceName.startsWith("master")) {
                    throw new Exception("master数据源数据源对DataSourceName做校验异常，必须为master前缀开始");
                }

            } else {

                // slave数据源，对DataSourceName做校验
                if(!dataSourceName.startsWith("slave")) {
                    throw new Exception("slave数据源数据源DataSourceName异常，必须为slave前缀开始");
                }
            }
        }

        if(masterNum > 1) {
            throw new Exception("存在多个master数据源，加载失败");
        }
    }

    private boolean isMaster(Integer master) {
        return master.equals(1);
    }
}
