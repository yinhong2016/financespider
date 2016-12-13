package com.sevencolor.domain.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sevencolor.domain.pojo.RebalanceBlockInfo;
import com.sevencolor.domain.pojo.RebalanceStatisticsInfo;

@Repository
public interface RebalanceBlockInfoMapper {
  int deleteByPrimaryKey(Integer dbid);

  int insert(RebalanceBlockInfo record);

  int insertSelective(RebalanceBlockInfo record);

  RebalanceBlockInfo selectByPrimaryKey(Integer dbid);

  int updateByPrimaryKeySelective(RebalanceBlockInfo record);

  int updateByPrimaryKey(RebalanceBlockInfo record);

  void truncateRebalance();

  List<RebalanceStatisticsInfo> selectRebalanceStatistics();
}
