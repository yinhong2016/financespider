package com.sevencolor.mapper;

import java.net.URL;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sevencolor.utils.HttpRequestHelper;

public abstract class AbstractMapper<T, R> implements Function<T, R> {

	private static final Logger logger = LoggerFactory.getLogger(AbstractMapper.class);

	protected ObjectMapper mapper;

	public AbstractMapper() {
		this.mapper = new ObjectMapper();
	}

	/**
	 * @Description: 请求网站具体URL，并返回数据
	 * @return: String
	 */
	protected String request(URL url) {
		return new HttpRequestHelper(url).request();
	}

	@Override
	public R apply(T t) {

		R res = null;
		try {
			res = mapLogic(t);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
		return res;
	}

	protected abstract R mapLogic(T t) throws Exception;

}
