package com.sevencolor.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.sevencolor.utils.StringUtil;

/**
 * @Description: 龙虎榜实体
 */
public class LongHuBangEntity implements DeepCopy<LongHuBangEntity> {

  private static final long serialVersionUID = 3016472367307645773L;
  private final StockEntity stock;
  private final Date date;

  private final Set<BizsunitInfo> topBuyList;
  private final Set<BizsunitInfo> topSaleList;

  public static class BizsunitInfo implements Serializable {

    private static final long serialVersionUID = -824153628899963517L;

    private final String bizsunitcode; // 营业部编号
    private final String bizsunitname; // 营业部名称
    private final String buyamt; // 买入额度
    private final String saleamt; // 卖出额度
    private final String tradedate; // 交易日期 yyyymmdd

    public BizsunitInfo(String bizsunitcode, String bizsunitname, String buyamt, String saleamt,
        String tradedate) {

      if (StringUtil.nullOrEmpty(bizsunitcode, bizsunitname, buyamt, saleamt, tradedate))
        throw new IllegalArgumentException();

      this.bizsunitcode = bizsunitcode;
      this.bizsunitname = bizsunitname;
      this.buyamt = buyamt;
      this.saleamt = saleamt;
      this.tradedate = tradedate;
    }

    public String getBizsunitcode() {
      return bizsunitcode;
    }

    public String getBizsunitname() {
      return bizsunitname;
    }

    public String getBuyamt() {
      return buyamt;
    }

    public String getSaleamt() {
      return saleamt;
    }

    public String getTradedate() {
      return tradedate;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o)
        return true;
      if (o == null || getClass() != o.getClass())
        return false;

      BizsunitInfo info = (BizsunitInfo) o;

      return bizsunitname.equals(info.bizsunitname);

    }

    @Override
    public int hashCode() {
      return bizsunitname.hashCode();
    }
  }

  public LongHuBangEntity(StockEntity stock, Date date, Set<BizsunitInfo> topBuyList,
      Set<BizsunitInfo> topSaleList) {
    this.stock = stock;
    this.date = date;
    this.topBuyList = topBuyList;
    this.topSaleList = topSaleList;
  }



  public StockEntity getStock() {
    return stock;
  }

  public Date getDate() {
    return date;
  }

  // 龙虎榜买入是否有该营业部出现
  public boolean bizsunitInBuyList(String name) {
    return bizsunitInBuyList(name, false);
  }

  // 龙虎榜买入是否有该营业部出现
  public boolean bizsunitInBuyList(String name, boolean partlySearch) {
    if (partlySearch) {
      contains(topBuyList, name);
    }
    return topBuyList.contains(new BizsunitInfo("xx", name, "xx", "xx", "xx"));
  }


  // 龙虎榜卖出是否有该营业部出现
  public boolean bizsunitInSaleList(String name) {
    return bizsunitInSaleList(name, false);
  }

  // 龙虎榜卖出是否有该营业部出现
  public boolean bizsunitInSaleList(String name, boolean partlySearch) {
    if (partlySearch) {
      contains(topSaleList, name);
    }
    return topSaleList.contains(new BizsunitInfo("xx", name, "xx", "xx", "xx"));
  }

  private boolean contains(Set<BizsunitInfo> set, String name) {
    for (BizsunitInfo info : set) {
      if (info.getBizsunitname().contains(name))
        return true;
    }
    return false;
  }

  public Set<BizsunitInfo> getTopBuyList() {
    return topBuyList;
  }

  public Set<BizsunitInfo> getTopSaleList() {
    return topSaleList;
  }

  @Override
  public LongHuBangEntity copy() {
    return new LongHuBangEntity(stock.copy(), date, new HashSet<>(topBuyList),
        new HashSet<>(topBuyList));
  }
}
