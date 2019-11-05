package com.snail.framework.demo.request;

import com.snail.framework.common.model.AbsAppResquest;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetActivityAwardRequest extends AbsAppResquest {
	
	@ApiModelProperty(value = "任务code", required = true)
    private String activityCode;

}
