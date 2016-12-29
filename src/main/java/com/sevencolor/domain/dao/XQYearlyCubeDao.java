package com.sevencolor.domain.dao;

import org.springframework.stereotype.Repository;

import com.sevencolor.domain.pojo.CubeInfo;

@Repository
public interface XQYearlyCubeDao {
	int deleteByPrimaryKey(Integer dbid);

	int insert(CubeInfo record);

	int insertSelective(CubeInfo record);

	CubeInfo selectByPrimaryKey(Integer dbid);

	CubeInfo selectByCubeSymbol(String cubeSymbol);

	int updateByPrimaryKeySelective(CubeInfo record);

	int updateByPrimaryKey(CubeInfo record);

	void truncateCube();
}
