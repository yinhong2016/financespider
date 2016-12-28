package com.sevencolor.mapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.sevencolor.entity.CubeEntity;
import com.sevencolor.entity.RebalancingEntity;
import com.sevencolor.task.XQCubeInfoTask;
import com.sevencolor.utils.EmptyObject;
import com.sevencolor.utils.RequestParaBuilder;
import com.sevencolor.utils.URLMapper;

/**
 * @Description: 沪深股票组合持仓记录装配器
 */
public class HSCubeLastBalancingMapper extends AbstractMapper<CubeEntity, CubeEntity> {

	private static final Logger logger = LoggerFactory.getLogger(XQCubeInfoTask.class);

	private static final int COUNT_THRESHOLD = 20;
	private static final int COUNT_DEFAULT = 15;
	private final int count;

	public HSCubeLastBalancingMapper() {
		this(COUNT_DEFAULT);
	}

	public HSCubeLastBalancingMapper(int count) {
		super();
		if (count <= 0) {
			this.count = COUNT_DEFAULT;
		} else {
			this.count = Math.min(COUNT_THRESHOLD, count);
		}
	}

	@Override
	public CubeEntity mapLogic(CubeEntity cube) {

		if (cube == null || cube == EmptyObject.emptyCube) {
			return EmptyObject.emptyCube;
		}

		// 雪球组合历史持仓股票记录
		String target = URLMapper.XQ_CUBE_REBALANCING_JSON.toString();
		RequestParaBuilder builder = new RequestParaBuilder(target).addParameter("cube_symbol", cube.getSymbol())
				.addParameter("count", count).addParameter("page", 1);

		try {
			URL url = new URL(builder.build());
			String json = request(url);
			JsonNode node = mapper.readTree(json);
			processCube(cube, node);
		} catch (IOException e) {
			logger.error(e.getLocalizedMessage());
		}

		return cube;
	}

	private void processCube(CubeEntity cube, JsonNode node) {
		JsonNode list = node.get("list");
		List<RebalancingEntity.TrendBlock> history = new ArrayList<>();
		for (JsonNode jsonNode : list) {
			for (JsonNode jn : jsonNode.get("rebalancing_histories")) {
				String stock_name;
				try {
					stock_name = new String(jn.get("stock_name").asText().getBytes("UTF-8"));
				} catch (UnsupportedEncodingException e) {
					stock_name = jn.get("stock_name").asText();
				}
				String stock_symbol = jn.get("stock_symbol").asText();
				String created_at = jn.get("created_at").asText();
				String prev_price = jn.get("prev_price").asText();
				String price = jn.get("price").asText();
				String prev_weight = jn.get("prev_weight").asText();
				String target_weight = jn.get("target_weight").asText();
				String weight = jn.get("weight").asText();
				String rebalancing_id = jn.get("rebalancing_id").asText();
				RebalancingEntity.TrendBlock trendBlock = new RebalancingEntity.TrendBlock(stock_name, stock_symbol,
						created_at, prev_price, price, prev_weight, target_weight, weight, rebalancing_id);
				history.add(trendBlock);
			}
		}
		RebalancingEntity rebalancing = new RebalancingEntity(history);
		cube.setRebalancing(rebalancing);
	}

}
