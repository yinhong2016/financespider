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
import java.util.Calendar;
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
import com.sevencolor.domain.dao.XQDailyCubeDao;
import com.sevencolor.domain.dao.XQDailyCubeRebDao;
import com.sevencolor.domain.dao.XQDailyCubeRebStatDao;
import com.sevencolor.domain.dao.XQMonthlyCubeDao;
import com.sevencolor.domain.dao.XQMonthlyCubeRebDao;
import com.sevencolor.domain.dao.XQMonthlyCubeRebStatDao;
import com.sevencolor.domain.dao.XQSummeryCubeDao;
import com.sevencolor.domain.dao.XQYearlyCubeDao;
import com.sevencolor.domain.dao.XQYearlyCubeRebDao;
import com.sevencolor.domain.dao.XQYearlyCubeRebStatDao;
import com.sevencolor.domain.pojo.CubeInfo;
import com.sevencolor.domain.pojo.CubeRebalanceInfo;
import com.sevencolor.domain.pojo.CubeRebalanceStatisticsInfo;
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
	private XQDailyCubeDao xqDailyCubeDao;
	@Autowired
	private XQDailyCubeRebDao xqDailyCubeRebalanceDao;
	@Autowired
	private XQDailyCubeRebStatDao xqDailyCubeRebStatDao;

	@Autowired
	private XQYearlyCubeDao xqYearlyCubeDao;
	@Autowired
	private XQYearlyCubeRebDao xqYearlyCubeRebalanceDao;
	@Autowired
	private XQYearlyCubeRebStatDao xqYearlyCubeRebStatDao;

	@Autowired
	private XQMonthlyCubeDao xqMonthlyCubeDao;
	@Autowired
	private XQMonthlyCubeRebDao xqMonthlyCubeRebalanceDao;
	@Autowired
	private XQMonthlyCubeRebStatDao xqMonthlyCubeRebStatDao;

	@Autowired
	private XQSummeryCubeDao xqSummeryCubeDao;

	/**
	 * @Description: 获取最赚钱雪球组合（沪深按天、月、年排序前十的组合）详细信息
	 * @return: void
	 */
	public void getTop10ProfHSCubeInfo() {
		getTop10ProfHSCubeByDay();
		getTop10ProfHSCubeByMonth();
		getTop10ProfHSCubeByYear();
		calXQCommCubeRebStat();
	}

	/**
	 * @Description: 获取最赚钱雪球组合（沪深按天排序前十的组合）详细信息
	 * @return: void
	 */
	public void getTop10ProfHSCubeByDay() {

		// 清除原有数据
		truncateDailyCubeInfo();

		// 获取沪深股市按天排序最赚钱前十雪球组合
		MostProfHSCubeCollector cubeCollector = new MostProfHSCubeCollector(MostProfHSCubeCollector.Market.CN,
				MostProfHSCubeCollector.Order_By.DAILY);

		// 雪球组合历史前十五股票持仓记录
		HSCubeLastBalancingMapper mapper = new HSCubeLastBalancingMapper();

		// 获取组合以及持仓信息
		List<CubeEntity> cubeList = cubeCollector.get().parallelStream().map(mapper).collect(Collectors.toList());

		// 保存组合信息以及目前组合里面的在仓股票信息
		for (CubeEntity cube : cubeList) {

			// 将网站获取的对象转换为数据库对象方便存储
			CubeInfo cubeInfo = createCubeInfo(cube);

			// 组合的所有已经查询出来的历史持仓记录
			List<CubeRebalanceInfo> cubeRebalanceAllList = getCubeRebalanceList(cube);

			// 去除已经空仓的股票(取所有涉及股票的最新调仓记录，在该记录集合中留下实际仓位>0的股票)
			List<CubeRebalanceInfo> notNullCubeRebalanceList = getNotNullCubeRebalanceList(cubeRebalanceAllList);

			// 保存组合以及最新仓位信息
			saveXQDailyCubeRebalanceToDB(cubeInfo, notNullCubeRebalanceList);
		}

		// 统计组合在仓weight>50的股票信息
		saveXqDailyCubeRebStat();

	}

	/**
	 * @Description: 获取最赚钱雪球组合（沪深按月份排序前十的组合）详细信息
	 * @return: void
	 */
	public void getTop10ProfHSCubeByMonth() {

		// 清除原有数据
		truncateMonthlyCubeInfo();

		// 获取沪深股市按月排序最赚钱前十雪球组合
		MostProfHSCubeCollector cubeCollector = new MostProfHSCubeCollector(MostProfHSCubeCollector.Market.CN,
				MostProfHSCubeCollector.Order_By.MONTHLY);

		// 雪球组合历史前十五股票持仓记录
		HSCubeLastBalancingMapper mapper = new HSCubeLastBalancingMapper();

		// 获取组合对象
		List<CubeEntity> cubeList = cubeCollector.get().parallelStream().map(mapper).collect(Collectors.toList());

		// 保存组合信息以及目前组合里面的在仓股票信息
		for (CubeEntity cube : cubeList) {

			// 将网站获取的对象转换为数据库对象方便存储
			CubeInfo cubeInfo = createCubeInfo(cube);

			// 组合的所有已经查询出来的历史持仓记录
			List<CubeRebalanceInfo> cubeRebalanceAllList = getCubeRebalanceList(cube);

			// 去除已经空仓的股票(取所有涉及股票的最新调仓记录，在该记录集合中留下实际仓位>0的股票)
			List<CubeRebalanceInfo> notNullCubeRebalanceList = getNotNullCubeRebalanceList(cubeRebalanceAllList);

			// 保存组合以及最新仓位信息
			saveXQMonthlyCubeRebalanceToDB(cubeInfo, notNullCubeRebalanceList);
		}

		// 统计组合在仓weight>10的股票信息
		saveXqMonthlyCubeRebStat();

	}

	/**
	 * @Description: 获取最赚钱雪球组合（沪深按年排序前十的组合）详细信息
	 * @return: void
	 */
	public void getTop10ProfHSCubeByYear() {

		// 清除原有数据
		truncateYearlyCubeInfo();

		// 获取沪深股市按年排序最赚钱前十雪球组合
		MostProfHSCubeCollector cubeCollector = new MostProfHSCubeCollector(MostProfHSCubeCollector.Market.CN,
				MostProfHSCubeCollector.Order_By.YEARLY);

		// 雪球组合历史前十五股票持仓记录
		HSCubeLastBalancingMapper mapper = new HSCubeLastBalancingMapper();

		// 获取组合对象
		List<CubeEntity> cubeList = cubeCollector.get().parallelStream().map(mapper).collect(Collectors.toList());

		// 保存组合信息以及目前组合里面的在仓股票信息
		for (CubeEntity cube : cubeList) {

			// 将网站获取的对象转换为数据库对象方便存储
			CubeInfo cubeInfo = createCubeInfo(cube);

			// 组合的所有已经查询出来的历史持仓记录
			List<CubeRebalanceInfo> cubeRebalanceAllList = getCubeRebalanceList(cube);

			// 去除已经空仓的股票(取所有涉及股票的最新调仓记录，在该记录集合中留下实际仓位>0的股票)
			List<CubeRebalanceInfo> notNullCubeRebalanceList = getNotNullCubeRebalanceList(cubeRebalanceAllList);

			// 保存组合以及最新仓位信息
			saveXQYearlyCubeRebalanceToDB(cubeInfo, notNullCubeRebalanceList);
		}

		// 统计组合在仓weight>10的股票信息
		saveXqYearlyCubeRebStat();

	}

	/**
	 * 
	 * @Description: 将年收益和月收益排名靠前的组合的股票信息汇总，取出一周内共同的股票并保存入库
	 * @return: void
	 */
	@Transactional
	public void calXQCommCubeRebStat() {

		xqSummeryCubeDao.truncateSummeryCubeRebalanceStatistics();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		// 设置为前一周
		calendar.add(Calendar.DAY_OF_MONTH, -7);
		// 得到前一周某一天的时间
		Date dBefore = calendar.getTime();

		// 查询一周前年收益和月收益靠前的组合涉及的股票（已经经过统计）集合
		List<CubeRebalanceStatisticsInfo> monthlyCubeRebList = xqSummeryCubeDao
				.selectMonthlyCubeRebalanceStatistics(dBefore.getTime());
		List<CubeRebalanceStatisticsInfo> yearlyCubeRebList = xqSummeryCubeDao
				.selectYearlyCubeRebalanceStatistics(dBefore.getTime());

		// 获取重复的股票信息,并将Weight相加
		List<CubeRebalanceStatisticsInfo> result = new ArrayList<CubeRebalanceStatisticsInfo>();
		if (monthlyCubeRebList != null && yearlyCubeRebList != null && monthlyCubeRebList.size() > 0
				&& yearlyCubeRebList.size() > 0) {
			for (CubeRebalanceStatisticsInfo monthly : monthlyCubeRebList) {
				for (CubeRebalanceStatisticsInfo yearly : yearlyCubeRebList) {
					if (monthly.getStocksymbol().equalsIgnoreCase(yearly.getStocksymbol())) {
						double monthlyWeight = Double.parseDouble(monthly.getTotalweight());
						double yearlyWeight = Double.parseDouble(yearly.getTotalweight());
						yearly.setTotalweight(Double.toString(monthlyWeight + yearlyWeight));
						result.add(yearly);
					}
				}
			}
		}

		// 保存数据库
		for (CubeRebalanceStatisticsInfo insertObeject : result) {
			xqSummeryCubeDao.insertSelective(insertObeject);
		}
	}

	@Transactional
	private void saveXQDailyCubeRebalanceToDB(CubeInfo cubeInfo, List<CubeRebalanceInfo> cubeRebalanceList) {

		CubeInfo selectedCube = xqDailyCubeDao.selectByCubeSymbol(cubeInfo.getCubesymbol());

		// 如果数据库已经记录Cube，则不再保存
		if (selectedCube == null) {
			xqDailyCubeDao.insert(cubeInfo);
			for (CubeRebalanceInfo blockInfo : cubeRebalanceList) {
				xqDailyCubeRebalanceDao.insert(blockInfo);
			}
		}

	}

	@Transactional
	private void saveXQYearlyCubeRebalanceToDB(CubeInfo cubeInfo, List<CubeRebalanceInfo> cubeRebalanceList) {

		CubeInfo selectedCube = xqYearlyCubeDao.selectByCubeSymbol(cubeInfo.getCubesymbol());

		// 如果数据库已经记录Cube，则不再保存
		if (selectedCube == null) {
			xqYearlyCubeDao.insert(cubeInfo);
			for (CubeRebalanceInfo blockInfo : cubeRebalanceList) {
				xqYearlyCubeRebalanceDao.insert(blockInfo);
			}
		}

	}

	@Transactional
	private void saveXQMonthlyCubeRebalanceToDB(CubeInfo cubeInfo, List<CubeRebalanceInfo> cubeRebalanceList) {

		CubeInfo selectedCube = xqMonthlyCubeDao.selectByCubeSymbol(cubeInfo.getCubesymbol());

		// 如果数据库已经记录Cube，则不再保存
		if (selectedCube == null) {
			xqMonthlyCubeDao.insert(cubeInfo);
			for (CubeRebalanceInfo blockInfo : cubeRebalanceList) {
				xqMonthlyCubeRebalanceDao.insert(blockInfo);
			}
		}

	}

	@Transactional
	@SuppressWarnings("static-access")
	private void truncateDailyCubeInfo() {

		xqDailyCubeDao.truncateCube();
		xqDailyCubeRebalanceDao.truncateRebalance();

		// 只保留当天最后一次存入数据
		Date date = new Date();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		Date date2 = new Date(date.getTime() - gc.get(gc.HOUR_OF_DAY) * 60 * 60 * 1000 - gc.get(gc.MINUTE) * 60 * 1000
				- gc.get(gc.SECOND) * 1000);
		xqDailyCubeRebStatDao.deleteByCreateTime(date2.getTime());
	}

	@Transactional
	@SuppressWarnings("static-access")
	private void truncateYearlyCubeInfo() {

		xqYearlyCubeDao.truncateCube();
		xqYearlyCubeRebalanceDao.truncateRebalance();

		// 只保留当天最后一次存入数据
		Date date = new Date();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		Date date2 = new Date(date.getTime() - gc.get(gc.HOUR_OF_DAY) * 60 * 60 * 1000 - gc.get(gc.MINUTE) * 60 * 1000
				- gc.get(gc.SECOND) * 1000);
		xqYearlyCubeRebStatDao.deleteByCreateTime(date2.getTime());
	}

	@Transactional
	@SuppressWarnings("static-access")
	private void truncateMonthlyCubeInfo() {

		xqMonthlyCubeDao.truncateCube();
		xqMonthlyCubeRebalanceDao.truncateRebalance();

		// 只保留当天最后一次存入数据
		Date date = new Date();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		Date date2 = new Date(date.getTime() - gc.get(gc.HOUR_OF_DAY) * 60 * 60 * 1000 - gc.get(gc.MINUTE) * 60 * 1000
				- gc.get(gc.SECOND) * 1000);
		xqMonthlyCubeRebStatDao.deleteByCreateTime(date2.getTime());
	}

	private CubeRebalanceInfo createCubeRebalanceInfo(CubeEntity cube, RebalancingEntity.TrendBlock temp) {
		CubeRebalanceInfo rebalanceBlockInfo = new CubeRebalanceInfo();
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
		return rebalanceBlockInfo;
	}

	private CubeInfo createCubeInfo(CubeEntity cube) {
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
		return cubeInfo;
	}

	@Transactional
	private void saveXqDailyCubeRebStat() {
		// 汇总统计并保存weight总和>=50以上的股票
		List<CubeRebalanceStatisticsInfo> rebalanceStatisticsList = xqDailyCubeRebalanceDao.selectRebalanceStatistics();

		if (rebalanceStatisticsList != null) {
			// 设置创建时间
			for (CubeRebalanceStatisticsInfo temp : rebalanceStatisticsList) {
				temp.setCreatetime(new Date().getTime());
				xqDailyCubeRebStatDao.insert(temp);
			}
		}
	}

	@Transactional
	private void saveXqYearlyCubeRebStat() {
		// 汇总统计并保存weight总和>=10以上的股票
		List<CubeRebalanceStatisticsInfo> rebalanceStatisticsList = xqYearlyCubeRebalanceDao
				.selectRebalanceStatistics();

		if (rebalanceStatisticsList != null) {
			// 设置创建时间
			for (CubeRebalanceStatisticsInfo temp : rebalanceStatisticsList) {
				temp.setCreatetime(new Date().getTime());
				xqYearlyCubeRebStatDao.insert(temp);
			}
		}
	}

	@Transactional
	private void saveXqMonthlyCubeRebStat() {
		// 汇总统计并保存weight总和>=10以上的股票
		List<CubeRebalanceStatisticsInfo> rebalanceStatisticsList = xqMonthlyCubeRebalanceDao
				.selectRebalanceStatistics();

		if (rebalanceStatisticsList != null) {
			// 设置创建时间
			for (CubeRebalanceStatisticsInfo temp : rebalanceStatisticsList) {
				temp.setCreatetime(new Date().getTime());
				xqMonthlyCubeRebStatDao.insert(temp);
			}
		}
	}

	private List<CubeRebalanceInfo> getNotNullCubeRebalanceList(List<CubeRebalanceInfo> rebalanceBlockInfoList) {
		// 所有股票代码集合（调仓历史去重，只获得所有涉及股票的编码）
		Set<String> stockSymbolSet = new HashSet<String>();
		for (CubeRebalanceInfo temp : rebalanceBlockInfoList) {
			stockSymbolSet.add(temp.getStocksymbol());
		}

		// 组合最新调仓股票集合(去重相同编码的股票)
		List<CubeRebalanceInfo> newBlockInfoList = new ArrayList<CubeRebalanceInfo>();

		// 取所有涉及股票的最新调仓记录(去重相同编码的股票)
		for (String stockSymbol : stockSymbolSet) {

			// 最近时间股票
			CubeRebalanceInfo newRebalanceBlockInfo = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (CubeRebalanceInfo blockInfo : rebalanceBlockInfoList) {
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

		// 只保留目前仓位不为空的股票
		List<CubeRebalanceInfo> lastBlockInfoList = new ArrayList<CubeRebalanceInfo>();
		for (CubeRebalanceInfo blockInfo : newBlockInfoList) {
			if (!blockInfo.getCurrentweight().equalsIgnoreCase("0.0")) {
				lastBlockInfoList.add(blockInfo);
			}
		}
		return lastBlockInfoList;
	}

	private List<CubeRebalanceInfo> getCubeRebalanceList(CubeEntity cube) {
		List<CubeRebalanceInfo> rebalanceBlockInfoList = new ArrayList<CubeRebalanceInfo>();
		if (cube.getRebalancing() != null) {
			RebalancingEntity rebalancingEntity = cube.getRebalancing();
			List<RebalancingEntity.TrendBlock> trendEntityList = rebalancingEntity.getHistory();
			if (trendEntityList != null) {
				for (RebalancingEntity.TrendBlock temp : trendEntityList) {
					CubeRebalanceInfo rebalanceBlockInfo = createCubeRebalanceInfo(cube, temp);
					rebalanceBlockInfoList.add(rebalanceBlockInfo);
				}
			}
		}
		return rebalanceBlockInfoList;
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
