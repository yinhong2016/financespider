package com.sevencolor.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import com.sevencolor.entity.CompanyEntity;
import com.sevencolor.entity.CubeEntity;
import com.sevencolor.entity.CubeTrendEntity;
import com.sevencolor.entity.IndustryEntity;
import com.sevencolor.entity.LongHuBangEntity;
import com.sevencolor.entity.MarketIndexTrendEntity;
import com.sevencolor.entity.RebalancingEntity;
import com.sevencolor.entity.ShareHoldersTrendEntity;
import com.sevencolor.entity.StockEntity;
import com.sevencolor.entity.StockTrendEntity;

/**
 * @Description: 空对象类，有效防止空指针错误
 */
public abstract class EmptyObject {

  private static class EmptyCompanyInfo extends CompanyEntity {

    private static final long serialVersionUID = -9035520348806229782L;

    public EmptyCompanyInfo() {
      super(emptyString, emptyString, emptyString, emptyString, emptyString, emptyString,
          new ArrayList<>());
    }
  }

  private static class EmptyLongHuBangInfo extends LongHuBangEntity {

    private static final long serialVersionUID = -6405343484406905517L;

    public EmptyLongHuBangInfo() {
      super(emptyStock, new Date(), new HashSet<>(), new HashSet<>());
    }

    @Override
    public LongHuBangEntity copy() {
      return this;
    }
  }

  private static class EmptyRebalancing extends RebalancingEntity {

    private static final long serialVersionUID = 3854245301337583394L;

    public EmptyRebalancing() {
      super(new ArrayList<>());
    }

    @Override
    public EmptyRebalancing copy() {
      return this;
    }
  }

  private static class EmptyShareHoldersTrend extends ShareHoldersTrendEntity {

    private static final long serialVersionUID = -1831416543760947610L;

    public EmptyShareHoldersTrend() {
      super(new ArrayList<>());
    }

    @Override
    public EmptyShareHoldersTrend copy() {
      return this;
    }
  }

  private static class EmptyMarketIndexTrend extends MarketIndexTrendEntity {

    private static final long serialVersionUID = 486550446415875276L;

    public EmptyMarketIndexTrend() {
      super(emptyString, emptyString, emptyString, emptyString, new ArrayList<>());
    }

    @Override
    public EmptyMarketIndexTrend copy() {
      return this;
    }
  }

  private static class EmptyStockTrend extends StockTrendEntity {

    private static final long serialVersionUID = 6278242299142336269L;

    public EmptyStockTrend() {
      super(emptyString, Period.DAY, new ArrayList<>());
    }

    @Override
    public StockTrendEntity copy() {
      return this;
    }
  }

  private static class EmptyCubeTrend extends CubeTrendEntity {

    private static final long serialVersionUID = 168675823754530864L;

    public EmptyCubeTrend() {
      super(emptyString, emptyString, emptyString, emptyString, new ArrayList<>());
    }

    @Override
    public CubeTrendEntity copy() {
      return this;
    }
  }

  private static class EmptyIndustry extends IndustryEntity {

    private static final long serialVersionUID = -4834232759347779638L;

    public EmptyIndustry() {
      super(emptyString, emptyString);
    }

    @Override
    public IndustryEntity copy() {
      return this;
    }
  }

  private static class EmptyStock extends StockEntity {

    private static final long serialVersionUID = 8190306184161417834L;

    public EmptyStock() {
      super(emptyString, emptyString);
    }

    @Override
    public StockEntity copy() {
      return this;
    }
  }

  private static class EmptyCube extends CubeEntity {

    private static final long serialVersionUID = -3665384577736997766L;

    public EmptyCube() {
      super(emptyString, emptyString, emptyString);
    }

    @Override
    public CubeEntity copy() {
      return this;
    }
  }

  public static StockTrendEntity emptyStockTrend = new EmptyStockTrend();
  public static String emptyString = "*";
  public static Date emptyDate = new Date(0);
  public static IndustryEntity emptyIndustry = new EmptyIndustry();
  public static StockEntity emptyStock = new EmptyStock();
  public static CubeEntity emptyCube = new EmptyCube();
  public static CubeTrendEntity emptyCubeTrend = new EmptyCubeTrend();
  public static MarketIndexTrendEntity emptyMarketIndexTrend = new EmptyMarketIndexTrend();
  public static RebalancingEntity emptyRebalancing = new EmptyRebalancing();
  public static EmptyShareHoldersTrend emptyShareHoldersTrend = new EmptyShareHoldersTrend();
  public static EmptyLongHuBangInfo emptyLongHuBangInfo = new EmptyLongHuBangInfo();
  public static EmptyCompanyInfo emptyCompanyInfo = new EmptyCompanyInfo();
}
