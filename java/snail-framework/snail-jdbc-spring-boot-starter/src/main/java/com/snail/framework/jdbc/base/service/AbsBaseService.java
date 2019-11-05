package com.snail.framework.jdbc.base.service;

import com.snail.framework.jdbc.base.dao.AbsBaseDao;
import com.snail.framework.jdbc.base.pojo.BasePojo;
import com.snail.framework.jdbc.base.service.iservice.IBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
public abstract class AbsBaseService<P extends BasePojo, D extends AbsBaseDao<P>> implements IBaseService<P> {

    /** dao工具 */
    protected D dao;

    /**
     * @设置 dao工具
     */
    protected abstract void setDao(D dao);

    /**
     * 根据主键或唯一键查找数据
     *
     * @param p
     * @return
     */
    public P selectUnique(P p) {
        return dao.selectUnique(p);
    }

    /**
     * 根据条件查询记录
     *
     * @param p
     * @return
     */
    public List<P> selectList(P p) {
        return dao.selectList(p);
    }

    /**
     * 插入一条数据
     *
     * @param p
     * @return
     */
    @Transactional
    public int insert(P p) {
        int count = dao.insert(p);
        return count;
    }

    /**
     * 修改一条数据
     *
     * @param p
     * @return
     */
    @Transactional
    public int update(P p) {
        return dao.update(p);
    }

    /**
     * 批量插入数据
     *
     * @param list
     * @return
     */
    public int insertList(List<P> list) {
        return dao.insertList(list);
    }

    /**
     * 批量修改数据
     *
     * @param list
     * @return
     */
    public int updateList(List<P> list) {
        return dao.updateList(list);
    }

    /**
     * 根据ID删除记录
     *
     * @param p
     * @return
     */
    @Override
    @Transactional
    public int deleteById(P p) {
        return dao.delete(p);
    }
}
