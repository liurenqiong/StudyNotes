package com.snail.framework.demo.response;

import java.util.List;

import com.snail.framework.common.model.AbsAppResponse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class GetActivityAwardResponse extends AbsAppResponse {
	
	@ApiModelProperty(value = "奖品列表明细", required = true)
	private List<ActivityAwardVo> list;

	@Data
	public static class ActivityAwardVo {

		@ApiModelProperty(value = "活动ID", required = true)
		private Integer activityId;

		@ApiModelProperty(value = " 活动代码", required = true)
		private String activityCode;

		@ApiModelProperty(value = " 任务标题", required = true)
		private String activityName;

	}
}
