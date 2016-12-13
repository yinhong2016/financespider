/**
 * Copyright © 2016 Seven Color. All rights reserved.
 *
 * @Description: 雪球组合务接口
 * @author: yinhong
 * @date: 2016年12月1日 下午5:42:49
 * @version: V1.0
 */
package com.sevencolor.service;

/**
 * @Description: 雪球组合务接口
 */
public interface XQCubeServiceI {

  public void getTop10ProfHSCubeByDay();

  public void getTop10ProfHSCubeByMonth();

  public void getTop10ProfHSCubeByYear();

  public void getTop10ProfHSCubeInfo();

  // public void getTop20ProfActualCubeByMonth();
  //
  // public void getTop20ProfActualCubeByWeek();
  //
  // public void getTop20ProfActualCubeByYear();

}
