/** */
package com.snail.framework.demo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.snail.framework.amqp.model.MessageModel;
import com.snail.framework.common.model.AppResponse;
import com.snail.framework.common.util.AppResponseUtil;
import com.snail.framework.demo.api.DemoClient;
import com.snail.framework.demo.mq.LoginMQ;
import com.snail.framework.demo.request.GetActivityAwardRequest;
import com.snail.framework.demo.request.LotteryRequest;
import com.snail.framework.demo.request.TestMQRequest;
import com.snail.framework.demo.response.GetActivityAwardResponse;
import com.snail.framework.demo.service.DemoActivityAwardService;
import com.snail.framework.log.annotation.LoggerManage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


/**
 * @功能:【demo_activity_award 活动奖品表】controller
 * @项目名:
 * @作者:lrq
 * @日期:2019-11-05 11:34:21
 * @说明：<pre></pre>
 */
@RestController
@Api(value = "奖品信息相关接口" , tags = {"奖品信息相关接口"})
public class DemoActivityAwardController implements DemoClient{
	/** demo_activity_award 活动奖品表service*/
	
	
    @Autowired
    private DemoActivityAwardService demoActivityAwardService;
    
    @Autowired
    private LoginMQ loginMQ; 

	@Override
	@LoggerManage(description = "获取奖品列表")
	@PostMapping(value = "/getActivityAward")
	@ApiOperation(value = "getActivityAward", notes = "获取奖品列表")
	public AppResponse<GetActivityAwardResponse> getActivityAward(
			@ApiParam(name = "request", value = "getActivityAward 接口", required = true) @Validated @RequestBody GetActivityAwardRequest request) {

		GetActivityAwardResponse response = demoActivityAwardService.getActivityAward(request);
		return AppResponseUtil.responseSuccess(response);

	}

	@Override
	@LoggerManage(description = "抽奖")
	@PostMapping(value = "/lottery")
	@ApiOperation(value = "lottery", notes = "抽奖")
	public AppResponse lottery(
			@ApiParam(name = "request", value = "lottery 接口", required = true) @Validated @RequestBody LotteryRequest request) {
		demoActivityAwardService.lottery(request);
		return AppResponseUtil.responseSuccess(null);
	}

	@Override
	@LoggerManage(description = "测试Mq")
	@PostMapping(value = "/testMq")
	@ApiOperation(value = "testMq", notes = "测试Mq")
	public AppResponse testMq(@ApiParam(name = "request", value = "lottery 接口", required = true) @Validated @RequestBody TestMQRequest request) {

		MessageModel model = new MessageModel();
		model.setDate(new Date());
		model.setMsg(request.getMsg());
		model.setMsgKey(request.getMsgKey());
		model.setRouteKey(LoginMQ.LOGIN_QUEUE_NAME);
		model.setQueueDesc(request.getQueueDesc());
		loginMQ.pushMessage(model);

		return AppResponseUtil.responseSuccess(null);
	}

}
