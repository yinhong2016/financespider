package com.sevencolor.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: HTTP工具类
 */
public class HttpRequestHelper {

  private static final Logger logger = LoggerFactory.getLogger(HttpRequestHelper.class);

  private Map<String, String> config;
  private boolean post;
  private boolean gzip;
  private URL url;

  public HttpRequestHelper(URL url) {
    this.url = url;
    this.config = new HashMap<>();
    setHeader();
  }

  /**
   * @Description: 设置HTTP响应头
   * @return: void
   */
  private void setHeader() {
    this.gzipDecode().addToHeader("Referer", getWebSite(this.url.toString()))
        .addToHeader("Cookie", PropertiesUtil.getProperty("XQcookies"))
        .addToHeader("Host", getDomainName(this.url.toString()))
        .addToHeader("Upgrade-Insecure-Requests",
            PropertiesUtil.getProperty("Upgrade-Insecure-Requests"))
        .addToHeader("User-Agent", PropertiesUtil.getProperty("User-Agent"))
        .addToHeader("Accept-Language", PropertiesUtil.getProperty("Accept-Language"))
        .addToHeader("Accept-Encoding", PropertiesUtil.getProperty("Accept-Encoding"));
  }

  /**
   * @Description: 设置HTTP为POST请求
   * @return: HttpRequestHelper
   */
  public HttpRequestHelper post() {
    this.post = true;
    return this;
  }

  /**
   * @Description: 发送HTTP请求，并获取返回值
   * @return: String
   */
  public String request() {
    return request(this.url, this.config);
  }

  private String request(URL url, Map<String, String> config) {

    StringBuilder builder = new StringBuilder();
    HttpURLConnection httpURLConn = null;

    try {
      httpURLConn = (HttpURLConnection) url.openConnection();
      if (post) {
        httpURLConn.setRequestMethod("POST");
      }
      httpURLConn.setDoOutput(true);

      // 设置http头参数
      for (Map.Entry<String, String> entry : config.entrySet()) {
        httpURLConn.setRequestProperty(entry.getKey(), entry.getValue());
      }

      httpURLConn.connect();
      InputStream in = httpURLConn.getInputStream();
      if (gzip) {
        in = new GZIPInputStream(in);
      }
      BufferedReader bd = new BufferedReader(new InputStreamReader(in));
      String text;
      while ((text = bd.readLine()) != null) {
        builder.append(text);
      }

    } catch (IOException e) {
      logger.error("Request [" + url.toString() + "] failed.", e);
    } finally {
      if (httpURLConn != null) {
        httpURLConn.disconnect();
      }
    }

    return builder.toString();
  }

  /**
   * @Description: 通过URL获取域名
   * @return: String
   */
  private String getDomainName(String url) {
    if (!(StringUtils.startsWithIgnoreCase(url, "http://")
        || StringUtils.startsWithIgnoreCase(url, "https://"))) {
      url = "http://" + url;
    }

    String returnVal = "";
    try {
      URI uri = new URI(url);
      returnVal = uri.getHost();
    } catch (Exception e) {
      logger.error("Get domain name failed. URL: [" + url + "]", e);
    }
    return returnVal;
  }

  /**
   * @Description: 通过URL获取根站点全路径
   * @return: String
   */
  private String getWebSite(String url) {

    if (!(StringUtils.startsWithIgnoreCase(url, "http://")
        || StringUtils.startsWithIgnoreCase(url, "https://"))) {
      url = "http://" + url;
    }

    String returnVal = "";
    try {
      URI uri = new URI(url);
      returnVal = uri.getHost();

      if (StringUtils.startsWithIgnoreCase(url, "http://")) {
        returnVal = "http://" + returnVal;
      } else if (StringUtils.startsWithIgnoreCase(url, "https://")) {
        returnVal = "https://" + returnVal;
      } else {
        returnVal = "http://" + returnVal;
      }
    } catch (Exception e) {
      logger.error("Get website failed. URL: [" + url + "]", e);
    }

    return returnVal;
  }

  private HttpRequestHelper gzipDecode() {
    this.gzip = true;
    return this;
  }

  private HttpRequestHelper addToHeader(String key, String val) {
    this.config.put(key, val);
    return this;
  }

}
