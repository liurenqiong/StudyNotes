package com.snail.framework.jdbc.base.service.iservice;


import com.snail.framework.jdbc.base.pojo.BasePojo;

import java.util.List;

public interface IBaseService<P extends BasePojo>{

    /**
     * 根据主键或唯一键查找数据
     *
     * @param p
     * @return
     */
    public P selectUnique(P p);

    /**
     * 根据条件查询记录
     *
     * @param p
     * @return
     */
    public List<P> selectList(P p);

    /**
     * 插入一条数据
     *
     * @param p
     * @return
     */
    public int insert(P p);

    /**
     * 修改一条数据
     *
     * @param p
     * @return
     */
    public int update(P p);

    /**
     * 批量插入数据
     *
     * @param list
     * @return
     */
    public int insertList(List<P> list);

    /**
     * 批量修改数据
     *
     * @param list
     * @return
     */
    public int updateList(List<P> list);

    /**
     * 根据ID删除记录
     * @param p
     * @return
     */
    public int deleteById(P p);
}
