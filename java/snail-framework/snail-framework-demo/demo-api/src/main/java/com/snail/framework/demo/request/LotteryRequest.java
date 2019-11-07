package com.snail.framework.demo.request;

import com.snail.framework.common.model.AbsAppResquest;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LotteryRequest extends AbsAppResquest {

	@ApiModelProperty(value = "手机号", required = true)
	private String mobile;
}
