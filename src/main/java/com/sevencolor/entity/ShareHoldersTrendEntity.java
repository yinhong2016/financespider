package com.sevencolor.entity;

import java.util.ArrayList;
import java.util.List;

import com.sevencolor.utils.StringUtil;

/**
 * @Description: 股票股东情况
 */
public class ShareHoldersTrendEntity extends TrendEntity<ShareHoldersTrendEntity.TrendBlock, ShareHoldersTrendEntity> {

  private static final long serialVersionUID = -7936123249307163578L;

  public ShareHoldersTrendEntity(List<TrendBlock> history) {
    super(history);
  }

  public static class TrendBlock implements ITrendBlock {

    private static final long serialVersionUID = 8433697266382723030L;
    private final String enddate;// 统计日期
    private final String totalshamt;// 股东总户数
    private final String holdproportionpacc;// 户均持股比例
    private final String totalshrto;// 股东总户数较上期增减
    private final String proportionchg;// 户均持股比例环比变化
    private final String proportiongrhalfyear;// 户均持股比例半年增长率
    private final String proportiongrq;// 户均持股比例季度增长率
    private final String avgholdsumgrhalfyear;// A股户均持股数半年增长率
    private final String avgholdsumgrq;// A股户均持股数季度增长率

    /**
     *
     * @param enddate 统计日期
     * @param totalshamt 股东总户数
     * @param holdproportionpacc 户均持股比例
     * @param totalshrto 股东总户数较上期增减
     * @param proportionchg 户均持股比例环比变化
     * @param proportiongrhalfyear 户均持股比例半年增长率
     * @param proportiongrq 户均持股比例季度增长率
     * @param avgholdsumgrhalfyear A股户均持股数半年增长率
     * @param avgholdsumgrq A股户均持股数季度增长率
     */
    public TrendBlock(String enddate, String totalshamt, String holdproportionpacc,
        String totalshrto, String proportionchg, String proportiongrhalfyear, String proportiongrq,
        String avgholdsumgrhalfyear, String avgholdsumgrq) {

      if (StringUtil.nullOrEmpty(enddate, totalshamt, holdproportionpacc, totalshrto, proportionchg,
          proportiongrhalfyear, proportiongrq, avgholdsumgrhalfyear, avgholdsumgrq))
        throw new IllegalArgumentException();

      this.enddate = enddate;
      this.totalshamt = totalshamt;
      this.holdproportionpacc = holdproportionpacc;
      this.totalshrto = totalshrto;
      this.proportionchg = proportionchg;
      this.proportiongrhalfyear = proportiongrhalfyear;
      this.proportiongrq = proportiongrq;
      this.avgholdsumgrhalfyear = avgholdsumgrhalfyear;
      this.avgholdsumgrq = avgholdsumgrq;
    }

    public String getEnddate() {
      return enddate;
    }

    public String getTotalshamt() {
      return totalshamt;
    }

    public String getHoldproportionpacc() {
      return holdproportionpacc;
    }

    public String getTotalshrto() {
      return totalshrto;
    }

    public String getProportionchg() {
      return proportionchg;
    }

    public String getProportiongrhalfyear() {
      return proportiongrhalfyear;
    }

    public String getProportiongrq() {
      return proportiongrq;
    }

    public String getAvgholdsumgrhalfyear() {
      return avgholdsumgrhalfyear;
    }

    public String getAvgholdsumgrq() {
      return avgholdsumgrq;
    }
  }



  @Override
  public ShareHoldersTrendEntity copy() {
    return new ShareHoldersTrendEntity(new ArrayList<>(history));
  }

}
