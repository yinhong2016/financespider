package com.sevencolor.collector;

import java.io.IOException;
import java.net.URL;
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
 * @Description: 实盘雪球最赚钱组合收集器
 */
public class MostProfActualCubeCollector extends AbstractCollector<List<CubeEntity>> {

  private static final Logger logger = LoggerFactory.getLogger(XQCubeInfoTask.class);

  private Order_By order_by;

  public enum Order_By {
    WEEKLY("WEEK"), // 按周收益排序
    MONTHLY("MONTH"), // 按月收益排序
    YEARLY("YEAR"); // 按年收益

    private String val;

    Order_By(String val) {
      this.val = val;
    }

    @Override
    public String toString() {
      return val;
    }
  }

  public MostProfActualCubeCollector() {
    this(Order_By.MONTHLY);
  }

  /**
   * @param order_by 收益排序规则
   */
  public MostProfActualCubeCollector(Order_By order_by) {
    super();
    this.order_by = order_by == null ? Order_By.MONTHLY : order_by;
  }

  @Override
  public List<CubeEntity> collectLogic() {

    String target = URLMapper.XQ_ACTUAL_CUBE_JSON.toString();
    RequestParaBuilder builder = new RequestParaBuilder(target).addParameter("tid", "PAMID")
        .addParameter("period", order_by.toString()).addParameter("page", "1");

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
    JsonNode result_data = node.get("result_data");
    JsonNode list = result_data.get("list");
    List<CubeEntity> cubes = new ArrayList<>();

    for (JsonNode jsonNode : list) {

      JsonNode user = jsonNode.get("user");
      String userId = user.get("id").asText();
      String userName = user.get("screen_name").asText();

      String symbol = jsonNode.get("symbol").asText();
      String cubeName = jsonNode.get("name").asText();
      String rate = jsonNode.get("rate").asText();

      CubeEntity cube = new CubeEntity(null, cubeName, symbol);
      if (order_by.toString().equalsIgnoreCase(Order_By.MONTHLY.toString())) {
        cube.setMonthly_gain(Double.toString(Double.parseDouble(rate) * 100));
      } else if (order_by.toString().equalsIgnoreCase(Order_By.WEEKLY.toString())) {
        cube.setWeekly_gain(Double.toString(Double.parseDouble(rate) * 100));
      } else {
        cube.setAnnualized_gain_rate(Double.toString(Double.parseDouble(rate) * 100));
      }
      cube.setUserID(userId);
      cube.setUserName(userName);

      cubes.add(cube);
    }
    return cubes;
  }

  public Order_By getOrder_by() {
    return order_by;
  }

}
