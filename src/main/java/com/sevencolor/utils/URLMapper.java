package com.sevencolor.utils;

/**
 * @Description: 将访问的JSON数据的网页地址
 */
public enum URLMapper {

    XQ_COMPREHENSIVE_PAGE("https://xueqiu.com/hq"),//雪球行情
    XQ_HU_SHEN_NEWS_REF_JSON("https://xueqiu.com/statuses/topic.json"),
    XQ_LONGHUBANG_JSON("https://xueqiu.com/stock/f10/bizunittrdinfo.json"),
    XQ_STOCK_INDUSTRY_JSON("https://xueqiu.com/stock/f10/compinfo.json"),
    XQ_CUBE_REBALANCING_JSON("https://xueqiu.com/cubes/rebalancing/history.json"),//需求网组合股票持仓历史记录
    XQ_CUBE_TREND_JSON("https://xueqiu.com/cubes/nav_daily/all.json"),
    XQ_CUBES_RANK_JSON("https://xueqiu.com/cubes/discover/rank/cube/list.json"),//雪球网沪深组合列表
    XQ_MARKET_QUOTATIONS_RANK_JSON("https://xueqiu.com/stock/quote_order.json"),
    XQ_SCOPE_STOCK_RANK_JSON("https://xueqiu.com/stock/rank.json"),
    XQ_STOCK_TREND_JSON("https://xueqiu.com/stock/forchartk/stocklist.json"),
    XQ_STOCK_JSON("https://xueqiu.com/v4/stock/quote.json"),
    XQ_INDUSTRY_JSON("https://xueqiu.com/industry/quote_order.json"),
    XQ_ACTUAL_CUBE_JSON("https://xueqiu.com/service/tc/snowx/PAMID/cubes/rank"),//雪球网实盘组合列表
    XQ_ACTUAL_CUBE_REBALANCING_JSON("https://xueqiu.com/service/tc/snowx/PAMID/cubes/rebalancing/history");//雪球网实盘组合股票持仓历史记录
  
    private String URL;
  
    URLMapper(String URL) {
        this.URL = URL;
    }

    @Override
    public String toString() {
        return URL;
    }
}
