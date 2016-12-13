package com.sevencolor.domain.pojo;

/**
 * @Description: 组合持仓股票
 */
public class RebalanceBlockInfo {
  private Integer dbid;

  private String cubeid;

  private String stockname;

  private String stocksymbol;

  private String createdtime;

  private String prevprice;

  private String currentprice;

  private String prevweight;

  private String targetweight;

  private String currentweight;

  private String rebalancingid;

  public Integer getDbid() {
    return dbid;
  }

  public void setDbid(Integer dbid) {
    this.dbid = dbid;
  }

  public String getCubeid() {
    return cubeid;
  }

  public void setCubeid(String cubeid) {
    this.cubeid = cubeid == null ? null : cubeid.trim();
  }

  public String getStockname() {
    return stockname;
  }

  public void setStockname(String stockname) {
    this.stockname = stockname == null ? null : stockname.trim();
  }

  public String getStocksymbol() {
    return stocksymbol;
  }

  public void setStocksymbol(String stocksymbol) {
    this.stocksymbol = stocksymbol == null ? null : stocksymbol.trim();
  }

  public String getCreatedtime() {
    return createdtime;
  }

  public void setCreatedtime(String createdtime) {
    this.createdtime = createdtime;
  }

  public String getPrevprice() {
    return prevprice;
  }

  public void setPrevprice(String prevprice) {
    this.prevprice = prevprice == null ? null : prevprice.trim();
  }

  public String getCurrentprice() {
    return currentprice;
  }

  public void setCurrentprice(String currentprice) {
    this.currentprice = currentprice == null ? null : currentprice.trim();
  }

  public String getPrevweight() {
    return prevweight;
  }

  public void setPrevweight(String prevweight) {
    this.prevweight = prevweight == null ? null : prevweight.trim();
  }

  public String getTargetweight() {
    return targetweight;
  }

  public void setTargetweight(String targetweight) {
    this.targetweight = targetweight == null ? null : targetweight.trim();
  }

  public String getCurrentweight() {
    return currentweight;
  }

  public void setCurrentweight(String currentweight) {
    this.currentweight = currentweight == null ? null : currentweight.trim();
  }

  public String getRebalancingid() {
    return rebalancingid;
  }

  public void setRebalancingid(String rebalancingid) {
    this.rebalancingid = rebalancingid == null ? null : rebalancingid.trim();
  }
}
