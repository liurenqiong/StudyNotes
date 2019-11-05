package com.snail.framework.jdbc.autoconfiguration;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author snail
 * @create 2019/8/26.
 **/
@Slf4j
@Configuration
@EnableConfigurationProperties(SnailJDBCProperties.class)
public class SnailJDBCAutoConfiguration {

    /**
     * 得到数据源
     *
     * @return
     * @throws Exception
     */
    @Bean(name = "masterDataSource")
    public DataSource dataSource(SnailJDBCProperties properties) throws Exception {
        log.info("====================配置操作mysql数据库=====================");
        DataSource ds = DruidDataSourceFactory.createDataSource(properties.getDruidDBCfg());
        log.info("DataSource创建成功");
        return ds;
    }

    /**
     * 创建mybatis SqlSessionFactory
     *
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "masterSqlSessionFactory")
    public SqlSessionFactory getSqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource ,
                                                  SnailJDBCProperties properties) throws Exception {
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
    public PlatformTransactionManager getTransactionManager(@Qualifier("masterDataSource") DataSource dataSource) {
        PlatformTransactionManager transactionTxManager = new DataSourceTransactionManager(dataSource);
        log.info("PlatformTransactionManager事务管理器创建成功");
        return transactionTxManager;
    }
}
