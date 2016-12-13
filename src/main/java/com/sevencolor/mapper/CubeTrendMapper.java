package com.sevencolor.mapper;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.sevencolor.entity.CubeEntity;
import com.sevencolor.entity.CubeTrendEntity;
import com.sevencolor.entity.MarketIndexTrendEntity;
import com.sevencolor.utils.EmptyObject;
import com.sevencolor.utils.RequestParaBuilder;
import com.sevencolor.utils.URLMapper;

/**
 * @Description: 股票组合历史走势装配器
 */
public class CubeTrendMapper extends AbstractMapper<CubeEntity, CubeEntity> {

  private final long since;
  private final long until;

  /**
   *
   * @param strategy 超时等待策略（null则设置为默认等待策略）
   * @param since 走线计算起始时间
   * @param until 走线计算结束时间
   */
  public CubeTrendMapper(Date since, Date until) {
    super();
    if (since == null || until == null || since.after(until))
      throw new IllegalArgumentException("Null Pointer");

    this.since = since.getTime();
    this.until = until.getTime();
  }

  @Override
  public CubeEntity mapLogic(CubeEntity cube) throws Exception {
    if (cube == null || cube == EmptyObject.emptyCube)
      return EmptyObject.emptyCube;

    String target = URLMapper.XQ_CUBE_TREND_JSON.toString();
    RequestParaBuilder builder =
        new RequestParaBuilder(target).addParameter("cube_symbol", cube.getSymbol())
            .addParameter("since", since).addParameter("until", until);

    URL url = new URL(builder.build());
    String json = request(url);

    JsonNode node = mapper.readTree(json);
    processCube(cube, node);
    return cube;
  }

  private void processCube(CubeEntity cube, JsonNode node) {

    JsonNode cubeNode = node.get(0);
    JsonNode SH300Node = node.get(1);

    CubeTrendEntity cubeTrend = processCubeNode(cubeNode);
    MarketIndexTrendEntity marketIndexTrend = processSH00Node(SH300Node);
    cube.setCubeTrend(cubeTrend);
    cube.setMarketIndexTrend(marketIndexTrend);
  }

  private CubeTrendEntity processCubeNode(JsonNode node) {

    JsonNode trendNode = node.get("list");
    List<CubeTrendEntity.TrendBlock> blocks = new ArrayList<>();

    for (JsonNode jsonNode : trendNode) {
      String time = jsonNode.get("time").asText();
      String date = jsonNode.get("date").asText();
      String value = jsonNode.get("value").asText();
      String percent = jsonNode.get("percent").asText();
      CubeTrendEntity.TrendBlock trendBlock =
          new CubeTrendEntity.TrendBlock(time, date, value, percent);
      blocks.add(trendBlock);
    }

    if (blocks.isEmpty())
      return EmptyObject.emptyCubeTrend;

    return new CubeTrendEntity(node.get("symbol").asText(), node.get("name").asText(),
        blocks.get(0).getTime(), blocks.get(blocks.size() - 1).getTime(), blocks);

  }

  private MarketIndexTrendEntity processSH00Node(JsonNode node) {

    JsonNode trendNode = node.get("list");
    List<MarketIndexTrendEntity.TrendBlock> blocks = new ArrayList<>();

    for (JsonNode jsonNode : trendNode) {
      String time = jsonNode.get("time").asText();
      String date = jsonNode.get("date").asText();
      String value = jsonNode.get("value").asText();
      String percent = jsonNode.get("percent").asText();
      MarketIndexTrendEntity.TrendBlock trendBlock =
          new MarketIndexTrendEntity.TrendBlock(time, date, value, percent);
      blocks.add(trendBlock);
    }

    if (blocks.isEmpty())
      return EmptyObject.emptyMarketIndexTrend;

    return new MarketIndexTrendEntity(node.get("symbol").asText(), node.get("name").asText(),
        blocks.get(0).getTime(), blocks.get(blocks.size() - 1).getTime(), blocks);
  }

}
