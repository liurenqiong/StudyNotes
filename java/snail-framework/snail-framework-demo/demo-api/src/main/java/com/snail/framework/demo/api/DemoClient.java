package com.snail.framework.demo.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.snail.framework.common.model.AppResponse;
import com.snail.framework.demo.request.GetActivityAwardRequest;
import com.snail.framework.demo.request.LotteryRequest;
import com.snail.framework.demo.request.TestMQRequest;
import com.snail.framework.demo.response.GetActivityAwardResponse;
import com.snail.framework.interceptor.FeginInterceptor;

@FeignClient(value = "snail-demo" , path = "/snail-demo",configuration = FeginInterceptor.class)
public interface DemoClient {
	
	 /**
     * @author lrq
     * @date 2019/9/26
     * 获取活动奖品表列表
     */
    @PostMapping(value = "/getActivityAward")
    AppResponse<GetActivityAwardResponse> getActivityAward(@RequestBody GetActivityAwardRequest request);
    
    /**
     * 抽奖
     * @author lrq
     * @param request
     * @return
     */
    @PostMapping(value = "/lottery")
    AppResponse lottery(@RequestBody LotteryRequest request);
    
    /**
     * 测试Mq
     * @param request
     * @return
     */
    @PostMapping(value = "/lottery")
    AppResponse testMq(@RequestBody TestMQRequest request);


}
