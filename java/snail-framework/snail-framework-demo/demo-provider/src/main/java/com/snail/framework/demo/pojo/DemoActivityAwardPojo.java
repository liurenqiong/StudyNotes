/** */
package com.snail.framework.demo.pojo;

import java.sql.Timestamp;
import com.snail.framework.jdbc.base.pojo.BasePojo;

/**
 * @功能:【demo_activity_award 活动奖品表】PO
 * @项目名:
 * @作者:lrq
 * @日期:2019-11-05 11:34:21
 * @说明：<pre></pre>
 */
public class DemoActivityAwardPojo extends BasePojo {
	/** 序列化UID */
	private static final long serialVersionUID = 1L;
	/** 活动ID */
	private Long activityId;
	/** 活动代码 */
	private String activityCode;
	/** 活动名称 */
	private String activityName;
	/** 奖品ID */
	private Long awardId;
	/** 奖品代码 */
	private String awardCode;
	/** 奖品名称 */
	private String awardName;
	/** 奖品库存 */
	private Integer awardStock;
	/** 中奖概率 */
	private Double awardRate;
	/** 起投金额以万元为单位 */
	private Integer awardStartAmount;
	/** 奖品有效期开始时间 */
	private Timestamp awardStartTime;
	/** 奖品有效期结束时间 */
	private Timestamp awardEndTime;
	/** 备注 */
	private String remark;
	/** 是否删除 */
	private Integer delFlag;
	/** 版本号 */
	private String awardVersion;
	/**  */
	private String createUser;
	/**  */
	private Timestamp createTime;
	/**  */
	private String updateUser;
	/**  */
	private Timestamp updateTime;
	/** 排序 */
	private Integer awardIndex;

	
	/** @取得 活动ID */
	public Long getActivityId(){
		return activityId;
	}
	
	/** @设置 活动ID */
	public void setActivityId(Long activityId){
		this.activityId = activityId;
	}
	
	/** @取得 活动代码 */
	public String getActivityCode(){
		return activityCode;
	}
	
	/** @设置 活动代码 */
	public void setActivityCode(String activityCode){
		this.activityCode = activityCode;
	}
	
	/** @取得 活动名称 */
	public String getActivityName(){
		return activityName;
	}
	
	/** @设置 活动名称 */
	public void setActivityName(String activityName){
		this.activityName = activityName;
	}
	
	/** @取得 奖品ID */
	public Long getAwardId(){
		return awardId;
	}
	
	/** @设置 奖品ID */
	public void setAwardId(Long awardId){
		this.awardId = awardId;
	}
	
	/** @取得 奖品代码 */
	public String getAwardCode(){
		return awardCode;
	}
	
	/** @设置 奖品代码 */
	public void setAwardCode(String awardCode){
		this.awardCode = awardCode;
	}
	
	/** @取得 奖品名称 */
	public String getAwardName(){
		return awardName;
	}
	
	/** @设置 奖品名称 */
	public void setAwardName(String awardName){
		this.awardName = awardName;
	}
	
	/** @取得 奖品库存 */
	public Integer getAwardStock(){
		return awardStock;
	}
	
	/** @设置 奖品库存 */
	public void setAwardStock(Integer awardStock){
		this.awardStock = awardStock;
	}
	
	/** @取得 中奖概率 */
	public Double getAwardRate(){
		return awardRate;
	}
	
	/** @设置 中奖概率 */
	public void setAwardRate(Double awardRate){
		this.awardRate = awardRate;
	}
	
	/** @取得 起投金额以万元为单位 */
	public Integer getAwardStartAmount(){
		return awardStartAmount;
	}
	
	/** @设置 起投金额以万元为单位 */
	public void setAwardStartAmount(Integer awardStartAmount){
		this.awardStartAmount = awardStartAmount;
	}
	
	/** @取得 奖品有效期开始时间 */
	public Timestamp getAwardStartTime(){
		return awardStartTime;
	}
	
	/** @设置 奖品有效期开始时间 */
	public void setAwardStartTime(Timestamp awardStartTime){
		this.awardStartTime = awardStartTime;
	}
	
	/** @取得 奖品有效期结束时间 */
	public Timestamp getAwardEndTime(){
		return awardEndTime;
	}
	
	/** @设置 奖品有效期结束时间 */
	public void setAwardEndTime(Timestamp awardEndTime){
		this.awardEndTime = awardEndTime;
	}
	
	/** @取得 备注 */
	public String getRemark(){
		return remark;
	}
	
	/** @设置 备注 */
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	/** @取得 是否删除 */
	public Integer getDelFlag(){
		return delFlag;
	}
	
	/** @设置 是否删除 */
	public void setDelFlag(Integer delFlag){
		this.delFlag = delFlag;
	}
	
	/** @取得 版本号 */
	public String getAwardVersion(){
		return awardVersion;
	}
	
	/** @设置 版本号 */
	public void setAwardVersion(String awardVersion){
		this.awardVersion = awardVersion;
	}
	
	/** @取得  */
	public String getCreateUser(){
		return createUser;
	}
	
	/** @设置  */
	public void setCreateUser(String createUser){
		this.createUser = createUser;
	}
	
	/** @取得  */
	public Timestamp getCreateTime(){
		return createTime;
	}
	
	/** @设置  */
	public void setCreateTime(Timestamp createTime){
		this.createTime = createTime;
	}
	
	/** @取得  */
	public String getUpdateUser(){
		return updateUser;
	}
	
	/** @设置  */
	public void setUpdateUser(String updateUser){
		this.updateUser = updateUser;
	}
	
	/** @取得  */
	public Timestamp getUpdateTime(){
		return updateTime;
	}
	
	/** @设置  */
	public void setUpdateTime(Timestamp updateTime){
		this.updateTime = updateTime;
	}
	
	/** @取得 排序 */
	public Integer getAwardIndex(){
		return awardIndex;
	}
	
	/** @设置 排序 */
	public void setAwardIndex(Integer awardIndex){
		this.awardIndex = awardIndex;
	}

}
