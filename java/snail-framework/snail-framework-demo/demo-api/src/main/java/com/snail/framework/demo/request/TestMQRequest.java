package com.snail.framework.demo.request;

import com.snail.framework.common.model.AbsAppResquest;

import lombok.Data;

@Data
public class TestMQRequest extends AbsAppResquest {

	private String msg;
	
	private String msgKey;
	
	private String queueDesc;
}
