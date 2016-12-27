package com.sevencolor.domain.dao;

import com.sevencolor.domain.pojo.RebalanceStatisticsInfo;

public interface RebalanceStatisticsInfoMapper {
  int deleteByPrimaryKey(Long dbid);
  
  int deleteByCreateTime(Long createtime);

  int insert(RebalanceStatisticsInfo record);

  int insertSelective(RebalanceStatisticsInfo record);

  RebalanceStatisticsInfo selectByPrimaryKey(Long dbid);

  int updateByPrimaryKeySelective(RebalanceStatisticsInfo record);

  int updateByPrimaryKey(RebalanceStatisticsInfo record);
}
