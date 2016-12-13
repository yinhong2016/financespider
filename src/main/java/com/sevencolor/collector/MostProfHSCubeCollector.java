package com.sevencolor.collector;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.helper.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.sevencolor.entity.CubeEntity;
import com.sevencolor.task.XQCubeInfoTask;
import com.sevencolor.utils.RequestParaBuilder;
import com.sevencolor.utils.URLMapper;

/**
 * @Description: 沪深雪球最赚钱组合收集器
 */
public class MostProfHSCubeCollector extends AbstractCollector<List<CubeEntity>> {

  private static final Logger logger = LoggerFactory.getLogger(XQCubeInfoTask.class);

  // 获取组合的阀值
  public static final int CUBE_SIZE_SHRESHOLD = 20;
  // 默认获取前十组合
  public static final int CUBE_SIZE_DEFAULT = 10;

  private Market market;
  private Order_By order_by;
  private int topK;

  public enum Market {
    CN("cn"), // 沪深组合
    US("us"), // 美股组合
    HK("hk");// 港股组合

    private String val;

    Market(String val) {
      this.val = val;
    }

    @Override
    public String toString() {
      return val;
    }
  }

  public enum Order_By {
    DAILY("daily_gain"), // 按日收益排序
    MONTHLY("monthly_gain"), // 按月收益排序
    YEARLY("annualized_gain_rate"), // 按年收益
    TOTAL("total_gain");// 按历来所有收益

    private String val;

    Order_By(String val) {
      this.val = val;
    }

    @Override
    public String toString() {
      return val;
    }
  }

  public MostProfHSCubeCollector() throws RemoteException {
    this(Market.CN);
  }

  public MostProfHSCubeCollector(Market market) {
    this(market, Order_By.MONTHLY);
  }

  public MostProfHSCubeCollector(Market market, Order_By order_by) {
    this(market, order_by, CUBE_SIZE_DEFAULT);
  }

  /**
   *
   * @param market 组合所在市场
   * @param order_by 收益排序规则
   * @param topK 排名前K的组合
   */
  public MostProfHSCubeCollector(Market market, Order_By order_by, int topK) {
    super();
    this.market = market == null ? Market.CN : market;
    this.order_by = order_by == null ? Order_By.MONTHLY : order_by;
    if (topK <= 0) {
      this.topK = CUBE_SIZE_DEFAULT;
    } else {
      this.topK = Math.min(topK, CUBE_SIZE_SHRESHOLD);
    }
  }

  /*
   * @return
   * 
   * @throws Exception
   * 
   * @see com.sevencolor.collector.AbstractCollector#collectLogic()
   */
  @Override
  public List<CubeEntity> collectLogic() {

    String target = URLMapper.XQ_CUBES_RANK_JSON.toString();
    RequestParaBuilder builder =
        new RequestParaBuilder(target).addParameter("category", 12).addParameter("count", topK)
            .addParameter("market", market.toString()).addParameter("profit", order_by.toString());

    List<CubeEntity> cubeList = null;
    try {
      URL url = new URL(builder.build());
      String json = request(url);
      if (!StringUtil.isBlank(json)) {
        JsonNode node = mapper.readTree(json);
        cubeList = processNode(node);
      }
    } catch (IOException e) {
      logger.error("Collect URL json failed. URL: [" + builder.build() + "]", e);
    }

    return cubeList;
  }

  private List<CubeEntity> processNode(JsonNode node) {
    JsonNode list = node.get("list");
    List<CubeEntity> cubes = new ArrayList<>();
    for (JsonNode jsonNode : list) {
      String id = jsonNode.get("id").asText();
      String name = jsonNode.get("name").asText();
      String symbol = jsonNode.get("symbol").asText();
      String daily_gain = jsonNode.get("daily_gain").asText();
      String monthly_gain = jsonNode.get("monthly_gain").asText();
      String annualized_gain_rate = jsonNode.get("annualized_gain_rate").asText();
      String total_gain = jsonNode.get("total_gain").asText();
      CubeEntity cube = new CubeEntity(id, name, symbol);
      cube.setDaily_gain(daily_gain);
      cube.setMonthly_gain(monthly_gain);
      cube.setAnnualized_gain_rate(annualized_gain_rate);
      cube.setTotal_gain(total_gain);
      cubes.add(cube);
    }
    return cubes;
  }

  public Market getMarket() {
    return market;
  }

  public Order_By getOrder_by() {
    return order_by;
  }
}
