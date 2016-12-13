package com.sevencolor.entity;

/**
 * @Description: 行业板块对象
 */
public class IndustryEntity implements DeepCopy<IndustryEntity> {

  private static final long serialVersionUID = 6395005453413745636L;
  private final String industryName;// 板块名字
  private final String industrySite;// 板块站点

  public IndustryEntity(final String industryName, final String industrySiteURL) {
    this.industryName = industryName;
    this.industrySite = industrySiteURL;
  }

  public String getIndustryName() {
    return industryName;
  }

  public String getIndustryInfo() {
    return industrySite;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    IndustryEntity industry = (IndustryEntity) o;

    return industryName.equals(industry.industryName) && industrySite.equals(industry.industrySite);

  }

  @Override
  public int hashCode() {
    int result = industryName.hashCode();
    result = 31 * result + industrySite.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "industryName = " + industryName + "  " + "industryInfo = " + industrySite;
  }

  @Override
  public IndustryEntity copy() {
    return new IndustryEntity(industryName, industrySite);
  }
}
