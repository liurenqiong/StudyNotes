package com.snail.framework.jdbc.base.dao;


import com.snail.framework.jdbc.base.pojo.BasePojo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class BaseDao<P extends BasePojo> extends AbsBaseDao<P> {

    /**
     * @设置 单条sql操作模板
     */
    @Override
    @Autowired
    @Qualifier("masterDBSqlSessionTemplate")
    protected void setTemplate(SqlSessionTemplate template) {
        this.template = template;
    }

    /**
     * @设置 批量sql操作模板
     */
    @Override
    @Autowired
    @Qualifier("masterDBSqlSessionBatchTemplate")
    protected void setBatchTemplate(SqlSessionTemplate batchTemplate) {
        this.batchTemplate = batchTemplate;
    }

}
