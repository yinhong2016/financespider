package com.sevencolor.utils;

/**
 * @Description: URL增加请求参数
 */
public class RequestParaBuilder {

  private StringBuilder config;

  public RequestParaBuilder(String target) {
    this.config = new StringBuilder(target);
    this.config.append('?');
  }

  public RequestParaBuilder addParameter(String key, String val) {
    this.config.append(key).append("=").append(val).append("&");
    return this;
  }

  public RequestParaBuilder addParameter(String key, int val) {
    this.config.append(key).append("=").append(val).append("&");
    return this;
  }

  public RequestParaBuilder addParameter(String key, long val) {
    this.config.append(key).append("=").append(val).append("&");
    return this;
  }

  public String build() {
    return this.config.substring(0, config.length() - 1);
  }

}
