package com.sevencolor.domain.dao;

import com.sevencolor.domain.pojo.RebalanceStatisticsInfo;

public interface RebalanceStatisticsInfoMapper {
  int deleteByPrimaryKey(Integer dbid);

  int insert(RebalanceStatisticsInfo record);

  int insertSelective(RebalanceStatisticsInfo record);

  RebalanceStatisticsInfo selectByPrimaryKey(Integer dbid);

  int updateByPrimaryKeySelective(RebalanceStatisticsInfo record);

  int updateByPrimaryKey(RebalanceStatisticsInfo record);
}
