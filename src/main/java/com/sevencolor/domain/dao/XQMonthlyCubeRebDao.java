package com.sevencolor.domain.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sevencolor.domain.pojo.CubeRebalanceInfo;
import com.sevencolor.domain.pojo.CubeRebalanceStatisticsInfo;

@Repository
public interface XQMonthlyCubeRebDao {
	int deleteByPrimaryKey(Integer dbid);

	int insert(CubeRebalanceInfo record);

	int insertSelective(CubeRebalanceInfo record);

	CubeRebalanceInfo selectByPrimaryKey(Integer dbid);

	int updateByPrimaryKeySelective(CubeRebalanceInfo record);

	int updateByPrimaryKey(CubeRebalanceInfo record);

	void truncateRebalance();

	List<CubeRebalanceStatisticsInfo> selectRebalanceStatistics();

}
