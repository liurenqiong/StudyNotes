/** */
package com.snail.framework.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.snail.framework.demo.dao.DemoActivityAwardDao;
import com.snail.framework.demo.pojo.DemoActivityAwardPojo;
import com.snail.framework.demo.request.GetActivityAwardRequest;
import com.snail.framework.demo.response.GetActivityAwardResponse;
import com.snail.framework.demo.response.GetActivityAwardResponse.ActivityAwardVo;
import com.snail.framework.demo.service.DemoActivityAwardService;
import com.snail.framework.jdbc.base.service.BaseService;

/**
 * @功能:【demo_activity_award 活动奖品表】Service
 * @项目名:
 * @作者:lrq
 * @日期:2019-11-05 11:34:21
 * @说明：<pre></pre>
 */
@Service
public class DemoActivityAwardServiceImpl extends BaseService<DemoActivityAwardPojo, DemoActivityAwardDao> implements DemoActivityAwardService {

	@Override
	public GetActivityAwardResponse getActivityAward(GetActivityAwardRequest request) {

		GetActivityAwardResponse response = new GetActivityAwardResponse();
		List<ActivityAwardVo> responseList = new ArrayList<>();

		DemoActivityAwardPojo parameter = new DemoActivityAwardPojo();

		if (StringUtils.isNotBlank(request.getActivityCode())) {
			parameter.setActivityCode(request.getActivityCode());
		}

		List<DemoActivityAwardPojo> list = this.selectList(parameter);

		if (list.size() > 0) {
			list.forEach(pojo -> {
				ActivityAwardVo awardVo = new ActivityAwardVo();
				BeanUtils.copyProperties(pojo, awardVo);

				responseList.add(awardVo);
			});

		}

		response.setList(responseList);
		return response;
	}

}
