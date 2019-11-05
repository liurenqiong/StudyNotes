package com.snail.framework.jdbc.base.pojo;

import java.io.Serializable;

/**
 * @功能:数据基础类
 * @作者:snail
 */
public class BasePojo implements Serializable {

    private Long id;

    private Integer deleteFlag = 0;

    /** 分页：第几页 */
    private Integer pageNum;
    /** 分页：每页显示条数 */
    private Integer pageSize;
    /** dataTabel分页：第几页 */
    private Integer start;
    /** dataTabel分页：每页显示条数 */
    private Integer length;
    /** 分页：查询记录总数 */
    private Long totalRowCount;

    /**
     * @取得 分页：第几页
     */
    public Integer getPageNum() {
        return pageNum;
    }

    /**
     * @设置 分页：第几页
     */
    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    /**
     * @取得 分页：每页显示条数
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * @设置 分页：每页显示条数
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /** @取得 dataTabel分页：第几页 */
    public Integer getStart() {
        return start;
    }

    /** @设置 dataTabel分页：第几页 */
    public void setStart(Integer start) {
        this.start = start;
        if (this.start != null && this.length != null && this.length > 0) {
            this.pageNum = this.start / this.length + 1;
        }
    }

    /** @取得 dataTabel分页：每页显示条数 */
    public Integer getLength() {
        return length;
    }

    /** @设置 dataTabel分页：每页显示条数 */
    public void setLength(Integer length) {
        this.length = length;
        this.pageSize = length;
        if (this.start != null && this.length != null && this.length > 0) {
            this.pageNum = this.start / this.length + 1;
        }
    }

    /**
     * @取得 分页：查询记录总数
     */
    public Long getTotalRowCount() {
        return totalRowCount;
    }

    /**
     * @设置 分页：查询记录总数
     */
    public void setTotalRowCount(Long totalRowCount) {
        this.totalRowCount = totalRowCount;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
