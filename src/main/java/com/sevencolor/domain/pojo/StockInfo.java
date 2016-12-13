package com.sevencolor.domain.pojo;

/**
 * @Description: 股票信息
 */
public class StockInfo {
    private Integer dbid;

    private String stocknum;

    private String stockname;

    private String currencyunit;

    private String currentprice;

    private String tradingvolume;

    private String percentage;

    private String change;

    private String open;

    private String high;

    private String low;

    private String amplitude;

    private String fallstop;

    private String risestop;

    private String lastclose;

    private String close;

    private String high52week;

    private String low52week;

    private String marketcapital;

    private String floatmarketcapital;

    private String floatshares;

    private String totalshares;

    private String eps;

    private String netAssets;

    private String pettm;

    private String pelyr;

    private String dividend;

    private String psr;

    private String turnoverrate;

    private String amount;

    public Integer getDbid() {
        return dbid;
    }

    public void setDbid(Integer dbid) {
        this.dbid = dbid;
    }

    public String getStocknum() {
        return stocknum;
    }

    public void setStocknum(String stocknum) {
        this.stocknum = stocknum == null ? null : stocknum.trim();
    }

    public String getStockname() {
        return stockname;
    }

    public void setStockname(String stockname) {
        this.stockname = stockname == null ? null : stockname.trim();
    }

    public String getCurrencyunit() {
        return currencyunit;
    }

    public void setCurrencyunit(String currencyunit) {
        this.currencyunit = currencyunit == null ? null : currencyunit.trim();
    }

    public String getCurrentprice() {
        return currentprice;
    }

    public void setCurrentprice(String currentprice) {
        this.currentprice = currentprice == null ? null : currentprice.trim();
    }

    public String getTradingvolume() {
        return tradingvolume;
    }

    public void setTradingvolume(String tradingvolume) {
        this.tradingvolume = tradingvolume == null ? null : tradingvolume.trim();
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage == null ? null : percentage.trim();
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change == null ? null : change.trim();
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open == null ? null : open.trim();
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high == null ? null : high.trim();
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low == null ? null : low.trim();
    }

    public String getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(String amplitude) {
        this.amplitude = amplitude == null ? null : amplitude.trim();
    }

    public String getFallstop() {
        return fallstop;
    }

    public void setFallstop(String fallstop) {
        this.fallstop = fallstop == null ? null : fallstop.trim();
    }

    public String getRisestop() {
        return risestop;
    }

    public void setRisestop(String risestop) {
        this.risestop = risestop == null ? null : risestop.trim();
    }

    public String getLastclose() {
        return lastclose;
    }

    public void setLastclose(String lastclose) {
        this.lastclose = lastclose == null ? null : lastclose.trim();
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close == null ? null : close.trim();
    }

    public String getHigh52week() {
        return high52week;
    }

    public void setHigh52week(String high52week) {
        this.high52week = high52week == null ? null : high52week.trim();
    }

    public String getLow52week() {
        return low52week;
    }

    public void setLow52week(String low52week) {
        this.low52week = low52week == null ? null : low52week.trim();
    }

    public String getMarketcapital() {
        return marketcapital;
    }

    public void setMarketcapital(String marketcapital) {
        this.marketcapital = marketcapital == null ? null : marketcapital.trim();
    }

    public String getFloatmarketcapital() {
        return floatmarketcapital;
    }

    public void setFloatmarketcapital(String floatmarketcapital) {
        this.floatmarketcapital = floatmarketcapital == null ? null : floatmarketcapital.trim();
    }

    public String getFloatshares() {
        return floatshares;
    }

    public void setFloatshares(String floatshares) {
        this.floatshares = floatshares == null ? null : floatshares.trim();
    }

    public String getTotalshares() {
        return totalshares;
    }

    public void setTotalshares(String totalshares) {
        this.totalshares = totalshares == null ? null : totalshares.trim();
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps == null ? null : eps.trim();
    }

    public String getNetAssets() {
        return netAssets;
    }

    public void setNetAssets(String netAssets) {
        this.netAssets = netAssets == null ? null : netAssets.trim();
    }

    public String getPettm() {
        return pettm;
    }

    public void setPettm(String pettm) {
        this.pettm = pettm == null ? null : pettm.trim();
    }

    public String getPelyr() {
        return pelyr;
    }

    public void setPelyr(String pelyr) {
        this.pelyr = pelyr == null ? null : pelyr.trim();
    }

    public String getDividend() {
        return dividend;
    }

    public void setDividend(String dividend) {
        this.dividend = dividend == null ? null : dividend.trim();
    }

    public String getPsr() {
        return psr;
    }

    public void setPsr(String psr) {
        this.psr = psr == null ? null : psr.trim();
    }

    public String getTurnoverrate() {
        return turnoverrate;
    }

    public void setTurnoverrate(String turnoverrate) {
        this.turnoverrate = turnoverrate == null ? null : turnoverrate.trim();
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount == null ? null : amount.trim();
    }
}