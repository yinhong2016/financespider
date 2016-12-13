package com.sevencolor.entity;

import java.util.ArrayList;
import java.util.List;

import com.sevencolor.utils.StringUtil;

/**
 * @Description: 股票组合收益历史走线
 */
public class CubeTrendEntity extends TrendEntity<CubeTrendEntity.TrendBlock, CubeTrendEntity> {

  private static final long serialVersionUID = 1333573603325089845L;
  private final String symbol;
  private final String name;
  private final String from;
  private final String to;

  /**
   *
   * @param symbol 组合代码
   * @param name 组合名字
   * @param from 走线计算起始时间
   * @param to 走线计算结束时间
   * @param history 走线集合
   */
  public CubeTrendEntity(String symbol, String name, String from, String to, List<TrendBlock> history) {
    super(history);
    if (symbol == null || name == null || from == null || to == null)
      throw new IllegalArgumentException();
    this.symbol = symbol;
    this.name = name;
    this.from = from;
    this.to = to;
  }

  public String getSymbol() {
    return symbol;
  }

  public String getName() {
    return name;
  }

  public String getFrom() {
    return from;
  }

  public String getTo() {
    return to;
  }

  /**
   * 趋势集合单元
   */
  public static class TrendBlock implements ITrendBlock {

    private static final long serialVersionUID = 3524416602243644398L;
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

  @Override
  public CubeTrendEntity copy() {
    return new CubeTrendEntity(symbol, name, from, to, new ArrayList<>(history));
  }

}
