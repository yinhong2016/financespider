package com.sevencolor.entity;

import java.util.List;

import com.sevencolor.utils.StringUtil;

/**
 * @Description: 大盘历史走线
 */
public class MarketIndexTrendEntity extends TrendEntity<MarketIndexTrendEntity.TrendBlock, MarketIndexTrendEntity> {

  private static final long serialVersionUID = -102938989983824204L;
  private final String symbol;
  private final String name;
  private final String from;
  private final String to;

  /**
   *
   * @param symbol 大盘代码
   * @param name 大盘名字
   * @param from 走线计算起始时间
   * @param to 走线计算结束时间
   * @param history 走线集合
   */
  public MarketIndexTrendEntity(String symbol, String name, String from, String to,
      List<TrendBlock> history) {
    super(history);

    if (symbol == null || name == null)
      throw new IllegalArgumentException();

    this.name = name;
    this.symbol = symbol;
    this.from = from;
    this.to = to;
  }


  /**
   * 趋势集合单元
   */
  public static class TrendBlock implements ITrendBlock {
    private static final long serialVersionUID = 8469263327148223978L;
    private final String time;
    private final String date;
    private final String value;
    private final String percent;


    /**
     *
     * @param time 节点时间
     * @param date 节点日期
     * @param value 涨幅
     * @param percent 涨幅（%）
     */
    public TrendBlock(String time, String date, String value, String percent) {
      if (StringUtil.nullOrEmpty(time, date, value, percent))
        throw new IllegalArgumentException();
      this.time = time;
      this.date = date;
      this.value = value;
      this.percent = percent;
    }

    public String getTime() {
      return time;
    }

    public String getDate() {
      return date;
    }

    public String getValue() {
      return value;
    }

    public String getPercent() {
      return percent;
    }
  }

  public String getSymbol() {
    return symbol;
  }

  public String getName() {
    return name;
  }

  @Override
  public MarketIndexTrendEntity copy() {
    return new MarketIndexTrendEntity(symbol, from, to, name, history);
  }

}
