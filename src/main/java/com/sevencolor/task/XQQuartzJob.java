/**
 * Copyright © 2016 Seven Color. All rights reserved.
 *
 * @Description: 用于定时执行某些任务
 * @author: yinhong
 * @date: 2016年11月29日 下午3:50:13
 * @version: V1.0
 */
package com.sevencolor.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 定时获取雪球网相关信息
 */
public class XQQuartzJob {

  private static final Logger logger = LoggerFactory.getLogger(XQQuartzJob.class);

  @Autowired
  private XQCubeInfoTask xqCubeInfoTask;

  /**
   * @Description: 定时获取雪球组合相关信息
   * @return: void
   */
  public void getCubeInfo() {
    try {
      logger.info("start to collect cube info.....");
      xqCubeInfoTask.mostProfitableCubeDetail();
      logger.info("collect cube info over.");
    } catch (Exception e) {
      logger.error("get most profitable cube detail failed.", e);
    }
  }
}
