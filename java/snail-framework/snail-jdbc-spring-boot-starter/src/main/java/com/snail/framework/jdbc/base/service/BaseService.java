package com.snail.framework.jdbc.base.service;


import com.snail.framework.jdbc.base.dao.AbsBaseDao;
import com.snail.framework.jdbc.base.pojo.BasePojo;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService<P extends BasePojo, D extends AbsBaseDao<P>> extends AbsBaseService<P , D> {

    /**
     * @设置 dao工具
     */
    @Override
    @Autowired
    protected void setDao(D dao) {
        this.dao = dao;
    }
}
