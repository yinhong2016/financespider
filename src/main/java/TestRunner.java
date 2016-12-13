import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.sevencolor.collector.MostProfHSCubeCollector;
import com.sevencolor.entity.CubeEntity;
import com.sevencolor.mapper.HSCubeLastBalancingMapper;

/**
 * @Description: TODO
 */
public class TestRunner {

  /**
   * @Description: TODO
   * @return: void
   */
  public static void main(String[] args) {
    // Runner runner = new Runner();
    // IntiProperties.init();
    // runner.mostProfitableCubeDetail();

    System.out.println(System.currentTimeMillis());

    String dateString = "1481247334053";
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    java.util.Date dt = new Date(Long.valueOf(dateString));
    String sDateTime = sdf.format(dt);
    System.out.println(sDateTime);
  }


  /**
   * * 把毫秒转化成日期 * @param dateFormat(日期格式，例如：MM/ dd/yyyy HH:mm:ss) * @param millSec(毫秒数) * @return
   */
  private static String transferLongToDate(String dateFormat, Long millSec) {
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
    Date date = new Date(millSec);
    return sdf.format(date);
  }

  // 最赚钱组合最新持仓以及收益走势、大盘走势
  private void mostProfitableCubeDetail() {
    Calendar calendar = Calendar.getInstance();
    calendar.set(2016, Calendar.OCTOBER, 30);
    Date from = calendar.getTime();
    calendar.set(2016, Calendar.NOVEMBER, 30);
    Date to = calendar.getTime();

    MostProfHSCubeCollector cubeCollector = new MostProfHSCubeCollector(
        MostProfHSCubeCollector.Market.CN, MostProfHSCubeCollector.Order_By.DAILY);
    HSCubeLastBalancingMapper mapper = new HSCubeLastBalancingMapper();

    List<CubeEntity> cubes =
        cubeCollector.get().parallelStream().map(mapper).collect(Collectors.toList());

    for (CubeEntity cube : cubes) {
      System.out.print(cube.getName() + " 总收益: " + cube.getTotal_gain());
      System.out.println(" 最新持仓 " + cube.getRebalancing().getHistory().get(1).toString());
    }
  }

}
