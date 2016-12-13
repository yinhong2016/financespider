package com.sevencolor.domain.dao;

import org.springframework.stereotype.Repository;

import com.sevencolor.domain.pojo.StockInfo;

@Repository
public interface StockInfoMapper {
  int deleteByPrimaryKey(Integer dbid);

  int insert(StockInfo record);

  int insertSelective(StockInfo record);

  StockInfo selectByPrimaryKey(Integer dbid);

  int updateByPrimaryKeySelective(StockInfo record);

  int updateByPrimaryKey(StockInfo record);
}
