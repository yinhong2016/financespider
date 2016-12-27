/**
 * Copyright © 2016 Seven Color. All rights reserved.
 *
 * @Description: 雪球组合业务类
 * @author: yinhong
 * @date: 2016年12月1日 下午5:47:04
 * @version: V1.0
 */
package com.sevencolor.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sevencolor.collector.MostProfHSCubeCollector;
import com.sevencolor.domain.dao.CubeInfoMapper;
import com.sevencolor.domain.dao.RebalanceBlockInfoMapper;
import com.sevencolor.domain.dao.RebalanceStatisticsInfoMapper;
import com.sevencolor.domain.pojo.CubeInfo;
import com.sevencolor.domain.pojo.RebalanceBlockInfo;
import com.sevencolor.domain.pojo.RebalanceStatisticsInfo;
import com.sevencolor.entity.CubeEntity;
import com.sevencolor.entity.RebalancingEntity;
import com.sevencolor.mapper.HSCubeLastBalancingMapper;
import com.sevencolor.service.XQCubeServiceI;

/**
 * @Description: 雪球组合业务类
 */
@Service("xqCubeService")
public class XQCubeServiceImpl implements XQCubeServiceI {

	@Autowired
	private CubeInfoMapper cubeInfoMapper;
	@Autowired
	private RebalanceBlockInfoMapper rebalanceBlockInfoMapper;
	@Autowired
	private RebalanceStatisticsInfoMapper rebalanceStatisticsInfoMapper;

	/**
	 * @Description: 获取最赚钱雪球组合（沪深按天、月、年排序前十的组合）详细信息
	 * @return: void
	 */
	public void getTop10ProfHSCubeInfo() {

		// 清除原有数据
		truncateCubeInfo();

		// 保存新数据
		getTop10ProfHSCubeByDay();
		getTop10ProfHSCubeByMonth();
		getTop10ProfHSCubeByYear();

		// 保存组合在仓weight>100的股票信息
		saveRebalanceStatistics();
	}

	/**
	 * @Description: 保存组合在仓weight>100的股票信息
	 * @return: void
	 */
	@Transactional
	private void saveRebalanceStatistics() {

		// 汇总统计并保存weight总和>=100以上的股票
		List<RebalanceStatisticsInfo> rebalanceStatisticsList = rebalanceBlockInfoMapper.selectRebalanceStatistics();

		if (rebalanceStatisticsList != null) {
			// 设置创建时间
			for (RebalanceStatisticsInfo temp : rebalanceStatisticsList) {
				temp.setCreatetime(new Date().getTime());
				rebalanceStatisticsInfoMapper.insert(temp);
			}
		}

	}

	/**
	 * @Description: 获取最赚钱雪球组合（沪深按天排序前十的组合）详细信息
	 * @return: void
	 */
	public void getTop10ProfHSCubeByDay() {

		// 获取沪深股市按天排序最赚钱前十雪球组合
		MostProfHSCubeCollector cubeCollector = new MostProfHSCubeCollector(MostProfHSCubeCollector.Market.CN,
				MostProfHSCubeCollector.Order_By.DAILY);

		// 雪球组合历史前十五股票持仓记录
		HSCubeLastBalancingMapper mapper = new HSCubeLastBalancingMapper();

		// 获取组合对象
		List<CubeEntity> cubeList = cubeCollector.get().parallelStream().map(mapper).collect(Collectors.toList());

		// 保存组合信息以及目前组合里面的在仓股票信息
		saveCubeLastBalances(cubeList);

	}

	/**
	 * @Description: 获取最赚钱雪球组合（沪深按月份排序前十的组合）详细信息
	 * @return: void
	 */
	public void getTop10ProfHSCubeByMonth() {

		// 获取沪深股市按月排序最赚钱前十雪球组合
		MostProfHSCubeCollector cubeCollector = new MostProfHSCubeCollector(MostProfHSCubeCollector.Market.CN,
				MostProfHSCubeCollector.Order_By.MONTHLY);

		// 雪球组合历史前十五股票持仓记录
		HSCubeLastBalancingMapper mapper = new HSCubeLastBalancingMapper();

		// 获取组合对象
		List<CubeEntity> cubeList = cubeCollector.get().parallelStream().map(mapper).collect(Collectors.toList());

		// 保存组合信息以及目前组合里面的在仓股票信息
		saveCubeLastBalances(cubeList);

	}

	/**
	 * @Description: 获取最赚钱雪球组合（沪深按年排序前十的组合）详细信息
	 * @return: void
	 */
	public void getTop10ProfHSCubeByYear() {

		// 获取沪深股市按月排序最赚钱前十雪球组合
		MostProfHSCubeCollector cubeCollector = new MostProfHSCubeCollector(MostProfHSCubeCollector.Market.CN,
				MostProfHSCubeCollector.Order_By.YEARLY);

		// 雪球组合历史前十五股票持仓记录
		HSCubeLastBalancingMapper mapper = new HSCubeLastBalancingMapper();

		// 获取组合对象
		List<CubeEntity> cubeList = cubeCollector.get().parallelStream().map(mapper).collect(Collectors.toList());

		// 保存组合信息以及目前组合里面的在仓股票信息
		saveCubeLastBalances(cubeList);

	}

	/**
	 * @Description: 保存组合信息以及最新仓位信息
	 * @return: void
	 */
	private void saveCubeLastBalances(List<CubeEntity> cubeList) {

		for (CubeEntity cube : cubeList) {

			// 将网站获取的对象转换为数据库对象方便存储
			CubeInfo cubeInfo = new CubeInfo();
			cubeInfo.setCubeid(cube.getCubeID());
			cubeInfo.setCubename(cube.getName());
			cubeInfo.setAnnualizedgainrate(cube.getAnnualized_gain_rate());
			cubeInfo.setCubesymbol(cube.getSymbol());
			cubeInfo.setDailygain(cube.getDaily_gain());
			cubeInfo.setMonthlygain(cube.getMonthly_gain());
			cubeInfo.setTotalgain(cube.getTotal_gain());
			cubeInfo.setUserid(cube.getUserID());
			cubeInfo.setUsername(cube.getUserName());

			// 组合所有已经查询出来的历史持仓记录
			List<RebalanceBlockInfo> rebalanceBlockInfoList = new ArrayList<RebalanceBlockInfo>();
			if (cube.getRebalancing() != null) {
				RebalancingEntity rebalancingEntity = cube.getRebalancing();
				List<RebalancingEntity.TrendBlock> trendEntityList = rebalancingEntity.getHistory();

				if (trendEntityList != null) {
					for (RebalancingEntity.TrendBlock temp : trendEntityList) {
						RebalanceBlockInfo rebalanceBlockInfo = new RebalanceBlockInfo();
						rebalanceBlockInfo.setCubeid(cube.getCubeID());

						// 转换日期
						SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
						java.util.Date dt = new Date(Long.valueOf(temp.getCreated_at()));
						String sDateTime = sdf.format(dt);
						rebalanceBlockInfo.setCreatedtime(sDateTime);

						rebalanceBlockInfo.setCurrentprice(temp.getPrice());
						rebalanceBlockInfo.setCurrentweight(temp.getWeight());
						rebalanceBlockInfo.setPrevprice(temp.getPrev_price());
						rebalanceBlockInfo.setRebalancingid(temp.getRebalancing_id());
						rebalanceBlockInfo.setStockname(temp.getStock_name());
						rebalanceBlockInfo.setStocksymbol(temp.getStock_symbol());
						rebalanceBlockInfo.setTargetweight(temp.getTarget_weight());
						rebalanceBlockInfo.setPrevweight(temp.getPrev_weight());
						rebalanceBlockInfoList.add(rebalanceBlockInfo);
					}
				}
			}

			/** 去除已经空仓的股票(取所有涉及股票的最新调仓记录，在该记录集合中留下实际仓位>0的股票) **/
			// 所有股票代码集合（调仓历史去重，只获得所有涉及股票的编码）
			Set<String> stockSymbolSet = new HashSet<String>();
			for (RebalanceBlockInfo temp : rebalanceBlockInfoList) {
				stockSymbolSet.add(temp.getStocksymbol());
			}

			// 组合最新调仓股票集合(去重相同编码的股票)
			List<RebalanceBlockInfo> newBlockInfoList = new ArrayList<RebalanceBlockInfo>();

			// 取所有涉及股票的最新调仓记录(去重相同编码的股票)
			for (String stockSymbol : stockSymbolSet) {

				// 最近时间股票
				RebalanceBlockInfo newRebalanceBlockInfo = null;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				for (RebalanceBlockInfo blockInfo : rebalanceBlockInfoList) {
					if (stockSymbol.equalsIgnoreCase(blockInfo.getStocksymbol())) {
						if (newRebalanceBlockInfo == null) {
							newRebalanceBlockInfo = blockInfo;
						} else {
							try {
								long newRebalanceBlockCreateTime = sdf.parse(newRebalanceBlockInfo.getCreatedtime())
										.getTime();
								long blockInfoCreateTime = sdf.parse(blockInfo.getCreatedtime()).getTime();
								if (newRebalanceBlockCreateTime <= blockInfoCreateTime) {
									newRebalanceBlockInfo = blockInfo;
								}
							} catch (ParseException e) {
							}
						}
					}
				}

				newBlockInfoList.add(newRebalanceBlockInfo);
			}

			// 保留目前仓位不为空的股票
			// List<RebalanceBlockInfo> lastBlockInfoList = new
			// ArrayList<RebalanceBlockInfo>();
			// for (RebalanceBlockInfo blockInfo : newBlockInfoList) {
			// if (!blockInfo.getCurrentweight().equalsIgnoreCase("0.0")) {
			// lastBlockInfoList.add(blockInfo);
			// }
			// }

			// 保存组合以及最新仓位信息
			saveCubeBalancesToDB(cubeInfo, newBlockInfoList);

		}
	}

	/**
	 * @Description: 保存数据到数据库
	 * @return: void
	 */
	@Transactional
	private void saveCubeBalancesToDB(CubeInfo cubeInfo, List<RebalanceBlockInfo> lastBlockInfoList) {

		CubeInfo selectedCube = cubeInfoMapper.selectByCubeSymbol(cubeInfo.getCubesymbol());

		// 如果数据库已经记录Cube，则不再保存
		if (selectedCube == null) {
			cubeInfoMapper.insert(cubeInfo);
			for (RebalanceBlockInfo blockInfo : lastBlockInfoList) {
				rebalanceBlockInfoMapper.insert(blockInfo);
			}
		}

	}

	@SuppressWarnings("static-access")
	@Transactional
	private void truncateCubeInfo() {
		cubeInfoMapper.truncateCube();
		rebalanceBlockInfoMapper.truncateRebalance();
		// 只保留当天最后一次存入数据
		Date date = new Date();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		Date date2 = new Date(date.getTime() - gc.get(gc.HOUR_OF_DAY) * 60 * 60 * 1000 - gc.get(gc.MINUTE) * 60 * 1000
				- gc.get(gc.SECOND) * 1000);
		rebalanceStatisticsInfoMapper.deleteByCreateTime(date2.getTime());
	}

	/// **
	// * @Description: 获取最赚钱雪球组合（实盘按月份排序前二十的组合）详细信息
	// * @return: void
	// */
	// public void getTop20ProfActualCubeByMonth() {
	//
	// // 获取实盘股市按月排序最赚钱前二十雪球组合
	// MostProfActualCubeCollector cubeCollector =
	// new
	/// MostProfActualCubeCollector(MostProfActualCubeCollector.Order_By.MONTHLY);
	//
	// // 雪球组合历史前十五股票持仓记录
	// ActualCubeLastBalancingMapper mapper = new
	/// ActualCubeLastBalancingMapper();
	//
	// // 获取组合对象
	// List<CubeEntity> cubeList =
	// cubeCollector.get().parallelStream().map(mapper).collect(Collectors.toList());
	//
	// // 保存组合信息以及目前组合里面的在仓股票信息
	// saveCubeLastBalances(cubeList);
	//
	// }
	//
	// /**
	// * @Description: 获取最赚钱雪球组合（实盘按周排序前二十的组合）详细信息
	// * @return: void
	// */
	// public void getTop20ProfActualCubeByWeek() {
	//
	// // 获取实盘股市按周排序最赚钱前二十雪球组合
	// MostProfActualCubeCollector cubeCollector =
	// new
	/// MostProfActualCubeCollector(MostProfActualCubeCollector.Order_By.WEEKLY);
	//
	// // 雪球组合历史前十五股票持仓记录
	// ActualCubeLastBalancingMapper mapper = new
	/// ActualCubeLastBalancingMapper();
	//
	// // 获取组合对象
	// List<CubeEntity> cubeList =
	// cubeCollector.get().parallelStream().map(mapper).collect(Collectors.toList());
	//
	// // 保存组合信息以及目前组合里面的在仓股票信息
	// saveCubeLastBalances(cubeList);
	//
	// }
	//
	// /**
	// * @Description: 获取最赚钱雪球组合（实盘按年排序前二十的组合）详细信息
	// * @return: void
	// */
	// public void getTop20ProfActualCubeByYear() {
	//
	// // 获取实盘股市按年排序最赚钱前二十雪球组合
	// MostProfActualCubeCollector cubeCollector =
	// new
	/// MostProfActualCubeCollector(MostProfActualCubeCollector.Order_By.YEARLY);
	//
	// // 雪球组合历史前十五股票持仓记录
	// ActualCubeLastBalancingMapper mapper = new
	/// ActualCubeLastBalancingMapper();
	//
	// // 获取组合对象
	// List<CubeEntity> cubeList =
	// cubeCollector.get().parallelStream().map(mapper).collect(Collectors.toList());
	//
	// // 保存组合信息以及目前组合里面的在仓股票信息
	// saveCubeLastBalances(cubeList);
	//
	// }

}
