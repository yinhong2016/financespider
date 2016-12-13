package com.sevencolor.entity;

import java.util.Date;

import com.sevencolor.utils.DateParser;
import com.sevencolor.utils.EmptyObject;

/**
 * @Description: 股票实体
 */
public class StockEntity implements DeepCopy<StockEntity> {

  private static final long serialVersionUID = -7758111106173845808L;

  private static final String Stock_Page_PreFix = "http://xueqiu.com/S/";

  private final String stockName; // 股票名称
  private final String stockNo; // 股票代码

  private String currency_unit = EmptyObject.emptyString;// 币种
  private String current = EmptyObject.emptyString; // 当前价
  private String volume = EmptyObject.emptyString;// 成交量
  private String percentage = EmptyObject.emptyString;// 百分比
  private String change = EmptyObject.emptyString;// 涨跌幅
  private String open = EmptyObject.emptyString;// 开盘价
  private String high = EmptyObject.emptyString;// 最高
  private String low = EmptyObject.emptyString;// 最低
  private String amplitude = EmptyObject.emptyString;// 振幅
  private String fall_stop = EmptyObject.emptyString;// 跌停价
  private String rise_stop = EmptyObject.emptyString;// 涨停价
  private String close = EmptyObject.emptyString;// 收盘价
  private String last_close = EmptyObject.emptyString;// 昨收
  private String high52Week = EmptyObject.emptyString;// 52周最高
  private String low52week = EmptyObject.emptyString;// 52周最低
  private String marketCapital = EmptyObject.emptyString;// 总市值
  private String float_market_capital = EmptyObject.emptyString;// 流通值
  private String float_shares = EmptyObject.emptyString;// 流通股本
  private String totalShares = EmptyObject.emptyString;// 总股本
  private String eps = EmptyObject.emptyString;// 每股收益
  private String net_assets = EmptyObject.emptyString;// 每股净资产
  private String pe_ttm = EmptyObject.emptyString;// 动态市盈率
  private String pe_lyr = EmptyObject.emptyString;// 静态市盈率
  private String dividend = EmptyObject.emptyString;// 每股股息
  private String psr = EmptyObject.emptyString;// 市销率
  private String turnover_rate = EmptyObject.emptyString;// 换手
  private String amount = EmptyObject.emptyString;// 成交额
  private Date time = EmptyObject.emptyDate;// 雪球系统时间

  private Date stockQueryDate = EmptyObject.emptyDate;

  private StockTrendEntity stockTrend = EmptyObject.emptyStockTrend;// 股票走势
  private ShareHoldersTrendEntity shareHoldersTrend = EmptyObject.emptyShareHoldersTrend;// 股票股东变动情况
  private CompanyEntity companyInfo = EmptyObject.emptyCompanyInfo;// 公司信息
  private IndustryEntity industry = EmptyObject.emptyIndustry;// 所属行业


  /**
   *
   * @param stockName 股票名称
   * @param stockNo 股票代码
   */
  public StockEntity(final String stockName, final String stockNo) {
    this.stockName = stockName;
    this.stockNo = stockNo;
  }



  // 股票名称
  public String getStockName() {
    return stockName;
  }

  public String getStockNo() {
    return stockNo;
  }

  // 币种
  public String getCurrency_unit() {
    return currency_unit;
  }

  // 币种
  public void setCurrency_unit(String currency_unit) {
    this.currency_unit = currency_unit;
  }

  // 当前价
  public String getCurrent() {
    return current;
  }

  // 当前价
  public void setCurrent(String current) {
    this.current = current;
  }

  // 成交量
  public String getVolume() {
    return volume;
  }

  // 成交量
  public void setVolume(String volume) {
    this.volume = volume;
  }

  // 涨幅百分比
  public String getPercentage() {
    return percentage;
  }

  // 涨幅百分比
  public void setPercentage(String percentage) {
    this.percentage = percentage;
  }

  // 涨跌幅
  public String getChange() {
    return change;
  }

  // 涨跌幅
  public void setChange(String change) {
    this.change = change;
  }

  // 开盘价
  public String getOpen() {
    return open;
  }

  // 开盘价
  public void setOpen(String open) {
    this.open = open;
  }

  // 最高
  public String getHigh() {
    return high;
  }

  // 最高
  public void setHigh(String high) {
    this.high = high;
  }

  // 最低
  public String getLow() {
    return low;
  }

  // 最低
  public void setLow(String low) {
    this.low = low;
  }

  // 振幅
  public String getAmplitude() {
    return amplitude;
  }

  // 换手
  public void setTurnover_rate(String turnover_rate) {
    this.turnover_rate = turnover_rate;
  }

  // 换手
  public String getTurnover_rate() {
    return turnover_rate;
  }

  // 振幅
  public void setAmplitude(String amplitude) {
    this.amplitude = amplitude;
  }

  // 跌停价
  public String getFall_stop() {
    return fall_stop;
  }

  // 跌停价
  public void setFall_stop(String fall_stop) {
    this.fall_stop = fall_stop;
  }

  // 涨停价
  public String getRise_stop() {
    return rise_stop;
  }

  // 涨停价
  public void setRise_stop(String rise_stop) {
    this.rise_stop = rise_stop;
  }

  // 收盘价
  public String getClose() {
    return close;
  }

  // 收盘价
  public void setClose(String close) {
    this.close = close;
  }

  // 昨收
  public String getLast_close() {
    return last_close;
  }

  // 昨收
  public void setLast_close(String last_close) {
    this.last_close = last_close;
  }

  // 52周最高
  public String getHigh52Week() {
    return high52Week;
  }

  // 52周最高
  public void setHigh52Week(String high52Week) {
    this.high52Week = high52Week;
  }

  // 52周最低
  public String getLow52week() {
    return low52week;
  }

  // 52周最低
  public void setLow52week(String low52week) {
    this.low52week = low52week;
  }

  // 总市值
  public String getMarketCapital() {
    return marketCapital;
  }

  // 总市值
  public void setMarketCapital(String marketCapital) {
    this.marketCapital = marketCapital;
  }

  // 流通值
  public String getFloat_market_capital() {
    return float_market_capital;
  }

  // 流通值
  public void setFloat_market_capital(String float_market_capital) {
    this.float_market_capital = float_market_capital;
  }

  // 流通股本
  public String getFloat_shares() {
    return float_shares;
  }

  // 流通股本
  public void setFloat_shares(String float_shares) {
    this.float_shares = float_shares;
  }

  // 总股本
  public String getTotalShares() {
    return totalShares;
  }

  // 总股本
  public void setTotalShares(String totalShares) {
    this.totalShares = totalShares;
  }

  // 每股收益
  public String getEps() {
    return eps;
  }

  // 每股收益
  public void setEps(String eps) {
    this.eps = eps;
  }

  // 每股净资产
  public String getNet_assets() {
    return net_assets;
  }

  // 每股净资产
  public void setNet_assets(String net_assets) {
    this.net_assets = net_assets;

  }

  // 动态市盈率
  public String getPe_ttm() {
    return pe_ttm;
  }

  // 动态市盈率
  public void setPe_ttm(String pe_ttm) {
    this.pe_ttm = pe_ttm;
  }

  // 静态市盈率
  public String getPe_lyr() {
    return pe_lyr;
  }

  // 静态市盈率
  public void setPe_lyr(String pe_lyr) {
    this.pe_lyr = pe_lyr;
  }

  // 每股股息
  public String getDividend() {
    return dividend;
  }

  // 每股股息
  public void setDividend(String dividend) {
    this.dividend = dividend;
  }

  // 市销率
  public String getPsr() {
    return psr;
  }

  // 市销率
  public void setPsr(String psr) {
    this.psr = psr;
  }


  // 雪球系统时间
  public void setTime(String time) {
    this.time = DateParser.parseToDate(time);
  }


  // 雪球系统时间
  public void setTime(Date time) {
    this.time = time;
  }

  // 雪球系统时间
  public Date getTime() {
    return time;
  }

  // 股票走势
  public void setStockTrend(StockTrendEntity stockTrend) {
    this.stockTrend = stockTrend;
  }

  // 股票走势
  public StockTrendEntity getStockTrend() {
    return stockTrend;
  }


  public String getStockPageSite() {
    return Stock_Page_PreFix + stockNo;
  }

  // 成交额
  public String getAmount() {
    return amount;
  }

  // 成交额
  public void setAmount(String amount) {
    this.amount = amount;
  }

  // 股票收集时间（程序发起请求时间）
  public void setStockQueryDate(Date stockQueryDate) {
    this.stockQueryDate = stockQueryDate;
  }

  // 股票收集时间（程序发起请求时间）
  public Date getStockQueryDate() {
    return stockQueryDate;
  }

  // 股票股东变动情况
  public ShareHoldersTrendEntity getShareHoldersTrend() {
    return shareHoldersTrend;
  }

  // 股票股东变动情况
  public void setShareHoldersTrend(ShareHoldersTrendEntity shareHoldersTrend) {
    this.shareHoldersTrend = shareHoldersTrend;
  }

  // 公司信息
  public CompanyEntity getCompanyInfo() {
    return companyInfo;
  }

  // 公司信息
  public void setCompanyInfo(CompanyEntity companyInfo) {
    this.companyInfo = companyInfo;
  }

  // 所属行业
  public IndustryEntity getIndustry() {
    return industry;
  }

  // 所属行业
  public void setIndustry(IndustryEntity industry) {
    this.industry = industry;
  }

  @Override
  public StockEntity copy() {
    StockEntity stock = new StockEntity(this.stockName, this.stockNo);
    stock.currency_unit = currency_unit;
    stock.current = current;
    stock.volume = volume;
    stock.percentage = percentage;
    stock.change = change;
    stock.open = open;
    stock.high = high;
    stock.low = low;
    stock.amplitude = amplitude;
    stock.rise_stop = rise_stop;
    stock.fall_stop = fall_stop;
    stock.close = close;
    stock.last_close = last_close;
    stock.high52Week = high52Week;
    stock.low52week = low52week;
    stock.marketCapital = marketCapital;
    stock.float_market_capital = float_market_capital;
    stock.float_shares = float_shares;
    stock.totalShares = totalShares;
    stock.eps = eps;
    stock.net_assets = net_assets;
    stock.pe_ttm = pe_ttm;
    stock.pe_lyr = pe_lyr;
    stock.dividend = dividend;
    stock.psr = psr;
    stock.time = time;
    stock.amount = amount;
    stock.turnover_rate = turnover_rate;
    stock.stockTrend = stockTrend.copy();
    stock.shareHoldersTrend = shareHoldersTrend.copy();
    stock.stockQueryDate = stockQueryDate;
    stock.companyInfo = companyInfo;
    stock.industry = industry;
    return stock;
  }


}
