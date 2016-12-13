package com.sevencolor.collector;

import java.net.URL;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sevencolor.utils.HttpRequestHelper;

/**
 * @Description: 整个框架数据收集生命周期的起点，负责对数据进行原始定位与收集 通过收集器可以定位你所关注的数据类型，继而进行进一步的数据挖掘
 * @param <T>
 */
public abstract class AbstractCollector<T> implements Supplier<T> {

	private static final Logger logger = LoggerFactory.getLogger(AbstractCollector.class);

	// 帅选数据的MAPPER
	protected ObjectMapper mapper;

	public AbstractCollector() {
		this.mapper = new ObjectMapper();
	}

	/**
	 * @Description: 请求采取财经网站具体URL，并返回数据
	 * @return: String
	 */
	protected String request(URL url) {
		return new HttpRequestHelper(url).request();
	}

	@Override
	public T get() {
		T res = null;
		try {
			res = collectLogic();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
		return res;
	}

	/**
	 * 收集器收集逻辑,由子类实现
	 */
	protected abstract T collectLogic() throws Exception;

}
