package com.sevencolor.domain.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sevencolor.domain.pojo.CubeInfo;
import com.sevencolor.domain.pojo.CubeRebalanceStatisticsInfo;

@Repository
public interface XQMonthlyCubeDao {
	int deleteByPrimaryKey(Integer dbid);

	int insert(CubeInfo record);

	int insertSelective(CubeInfo record);

	CubeInfo selectByPrimaryKey(Integer dbid);

	CubeInfo selectByCubeSymbol(String cubeSymbol);

	int updateByPrimaryKeySelective(CubeInfo record);

	int updateByPrimaryKey(CubeInfo record);

	void truncateCube();

	List<CubeRebalanceStatisticsInfo> selectRebalanceStatistics();
}
