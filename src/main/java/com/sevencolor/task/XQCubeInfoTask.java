/**
 * Copyright © 2016 Seven Color. All rights reserved.
 *
 * @Description: 雪球组合任务体
 * @author: yinhong
 * @date: 2016年11月29日 下午3:34:19
 * @version: V1.0
 */
package com.sevencolor.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sevencolor.collector.MostProfHSCubeCollector;
import com.sevencolor.service.XQCubeServiceI;

/**
 * @Description: 雪球组合任务体
 */
@Component
public class XQCubeInfoTask {

	@Autowired
	private XQCubeServiceI xqCubeService;

	/**
	 * @Description: 获取最赚钱雪球组合详细信息
	 * @return: void
	 */
	public void mostProfitableCubeDetail() {
		xqCubeService.getTopNProfHSCubeInfo(MostProfHSCubeCollector.CUBE_SIZE_SHRESHOLD);
	}

}
