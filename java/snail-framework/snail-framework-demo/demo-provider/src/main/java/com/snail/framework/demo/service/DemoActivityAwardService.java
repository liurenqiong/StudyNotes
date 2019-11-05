/** */
package com.snail.framework.demo.service;

import com.snail.framework.demo.pojo.DemoActivityAwardPojo;
import com.snail.framework.demo.request.GetActivityAwardRequest;
import com.snail.framework.demo.response.GetActivityAwardResponse;
import com.snail.framework.jdbc.base.service.iservice.IBaseService;

/**
 * @功能:【demo_activity_award 活动奖品表】IService
 * @项目名:
 * @作者:lrq
 * @日期:2019-11-05 11:34:21
 * @说明：<pre></pre>
 */
public interface DemoActivityAwardService extends IBaseService<DemoActivityAwardPojo> {
	
	/**
	 * 获取奖品列表
	 * @param request
	 * @return
	 */
	GetActivityAwardResponse getActivityAward(GetActivityAwardRequest request);

}
