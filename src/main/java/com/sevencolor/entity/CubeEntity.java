package com.sevencolor.entity;

import com.sevencolor.utils.EmptyObject;

/**
 * @Description: 组合对象
 */
public class CubeEntity implements DeepCopy<CubeEntity> {

  private static final long serialVersionUID = 7405950293362678527L;

  private final String cubeID;
  private final String name;
  private final String symbol;

  private String userID = EmptyObject.emptyString;// 创建用户ID
  private String userName = EmptyObject.emptyString;// 创建用户名

  private String daily_gain = EmptyObject.emptyString; // 日收益
  private String weekly_gain = EmptyObject.emptyString;// 周收益
  private String monthly_gain = EmptyObject.emptyString;// 月收益
  private String annualized_gain_rate = EmptyObject.emptyString;// 年收益
  private String total_gain = EmptyObject.emptyString;// 总收益

  private CubeTrendEntity cubeTrend = EmptyObject.emptyCubeTrend;// 组合收益历史走线
  private MarketIndexTrendEntity marketIndexTrend = EmptyObject.emptyMarketIndexTrend;// 大盘历史走线
  private RebalancingEntity rebalancing = EmptyObject.emptyRebalancing;// 调仓记录

  /**
   *
   * @param cubeID 组合代码
   * @param name 组合名称
   * @param symbol 组合代码
   */
  public CubeEntity(String cubeID, String name, String symbol) {
    this.cubeID = cubeID;
    this.name = name;
    this.symbol = symbol;
  }

  @Override
  public CubeEntity copy() {
    CubeEntity cube = new CubeEntity(cubeID, name, symbol);

    cube.setDaily_gain(daily_gain);
    cube.setWeekly_gain(weekly_gain);
    cube.setMonthly_gain(monthly_gain);
    cube.setAnnualized_gain_rate(annualized_gain_rate);
    cube.setTotal_gain(total_gain);

    cube.setCubeTrend(cubeTrend.copy());
    cube.setMarketIndexTrend(marketIndexTrend.copy());
    cube.setRebalancing(rebalancing);

    cube.setUserID(userID);
    cube.setUserName(userName);

    return cube;
  }

  public void setUserID(String userID) {
    this.userID = userID;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
  
  public String getWeekly_gain() {
    return weekly_gain;
  }

  public void setWeekly_gain(String weekly_gain) {
    this.weekly_gain = weekly_gain;
  }

  // 日收益
  public void setDaily_gain(String daily_gain) {
    this.daily_gain = daily_gain;
  }

  // 月收益
  public void setMonthly_gain(String monthly_gain) {
    this.monthly_gain = monthly_gain;
  }

  // 总收益
  public void setTotal_gain(String total_gain) {
    this.total_gain = total_gain;
  }

  // 组合收益历史走线
  public void setCubeTrend(CubeTrendEntity cubeTrend) {
    this.cubeTrend = cubeTrend;
  }

  // 大盘历史走线
  public void setMarketIndexTrend(MarketIndexTrendEntity marketIndexTrend) {
    this.marketIndexTrend = marketIndexTrend;
  }

  // 调仓记录
  public void setRebalancing(RebalancingEntity rebalancing) {
    this.rebalancing = rebalancing;
  }

  // 年收益
  public void setAnnualized_gain_rate(String annualized_gain_rate) {
    this.annualized_gain_rate = annualized_gain_rate;
  }

  public String getUserID() {
    return userID;
  }

  public String getUserName() {
    return userName;
  }

  public String getDaily_gain() {
    return daily_gain;
  }

  public String getMonthly_gain() {
    return monthly_gain;
  }

  public String getAnnualized_gain_rate() {
    return annualized_gain_rate;
  }

  public String getTotal_gain() {
    return total_gain;
  }

  public String getCubeID() {
    return cubeID;
  }

  public String getName() {
    return name;
  }

  public String getSymbol() {
    return symbol;
  }

  public RebalancingEntity getRebalancing() {
    return rebalancing;
  }

  public CubeTrendEntity getCubeTrend() {
    return cubeTrend;
  }

  public MarketIndexTrendEntity getMarketIndexTrend() {
    return marketIndexTrend;
  }
}
