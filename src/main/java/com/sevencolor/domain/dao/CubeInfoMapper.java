package com.sevencolor.domain.dao;

import org.springframework.stereotype.Repository;

import com.sevencolor.domain.pojo.CubeInfo;

/**
 * @Description: 雪球组合实体数据库操作
 */
@Repository
public interface CubeInfoMapper {
  int deleteByPrimaryKey(Integer dbid);

  int insert(CubeInfo record);

  int insertSelective(CubeInfo record);

  CubeInfo selectByPrimaryKey(Integer dbid);

  CubeInfo selectByCubeSymbol(String cubeSymbol);

  int updateByPrimaryKeySelective(CubeInfo record);

  int updateByPrimaryKey(CubeInfo record);
  
  void truncateCube();
}
