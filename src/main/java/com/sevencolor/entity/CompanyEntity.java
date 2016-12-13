package com.sevencolor.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 公司信息
 */
public class CompanyEntity implements DeepCopy<CompanyEntity> {

  private static final long serialVersionUID = -2111064252976647435L;

  private final String compsname;// 公司名称
  private final String orgtype;// 组织形式
  private final String founddate;// 成立日期
  private final String bizscope;// 经营范围
  private final String majorbiz;// 主营业务
  private final String region;// 地区代码
  private final List<IndustryEntity> tqCompIndustryList;// 所属板块

  /**
   *
   * @param compsname 公司名称
   * @param orgtype 组织形式
   * @param founddate 成立日期
   * @param bizscope 经营范围
   * @param majorbiz 主营业务
   * @param region 地区代码
   * @param tqCompIndustryList 所属板块
   */
  public CompanyEntity(String compsname, String orgtype, String founddate, String bizscope,
      String majorbiz, String region, List<IndustryEntity> tqCompIndustryList) {
    this.compsname = compsname;
    this.orgtype = orgtype;
    this.founddate = founddate;
    this.bizscope = bizscope;
    this.majorbiz = majorbiz;
    this.region = region;
    this.tqCompIndustryList = tqCompIndustryList;
  }

  public String getCompsname() {
    return compsname;
  }

  public String getOrgtype() {
    return orgtype;
  }

  public String getFounddate() {
    return founddate;
  }

  public String getBizscope() {
    return bizscope;
  }

  public String getMajorbiz() {
    return majorbiz;
  }

  public String getRegion() {
    return region;
  }

  public List<IndustryEntity> getTqCompIndustryList() {
    return tqCompIndustryList;
  }

  @Override
  public CompanyEntity copy() {
    return new CompanyEntity(compsname, orgtype, founddate, bizscope, majorbiz, region,
        new ArrayList<>(tqCompIndustryList));
  }

}
