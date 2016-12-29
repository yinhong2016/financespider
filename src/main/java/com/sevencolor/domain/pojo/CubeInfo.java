package com.sevencolor.domain.pojo;

/**
 * @Description: 股票持仓组合
 */
public class CubeInfo {
	private Long dbid;

	private String cubeid;

	private String cubename;

	private String cubesymbol;

	private String dailygain;

	private String monthlygain;

	private String annualizedgainrate;

	private String totalgain;

	private String userid;

	private String username;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getDbid() {
		return dbid;
	}

	public void setDbid(Long dbid) {
		this.dbid = dbid;
	}

	public String getCubeid() {
		return cubeid;
	}

	public void setCubeid(String cubeid) {
		this.cubeid = cubeid == null ? null : cubeid.trim();
	}

	public String getCubename() {
		return cubename;
	}

	public void setCubename(String cubename) {
		this.cubename = cubename == null ? null : cubename.trim();
	}

	public String getCubesymbol() {
		return cubesymbol;
	}

	public void setCubesymbol(String cubesymbol) {
		this.cubesymbol = cubesymbol == null ? null : cubesymbol.trim();
	}

	public String getDailygain() {
		return dailygain;
	}

	public void setDailygain(String dailygain) {
		this.dailygain = dailygain == null ? null : dailygain.trim();
	}

	public String getMonthlygain() {
		return monthlygain;
	}

	public void setMonthlygain(String monthlygain) {
		this.monthlygain = monthlygain == null ? null : monthlygain.trim();
	}

	public String getAnnualizedgainrate() {
		return annualizedgainrate;
	}

	public void setAnnualizedgainrate(String annualizedgainrate) {
		this.annualizedgainrate = annualizedgainrate == null ? null : annualizedgainrate.trim();
	}

	public String getTotalgain() {
		return totalgain;
	}

	public void setTotalgain(String totalgain) {
		this.totalgain = totalgain == null ? null : totalgain.trim();
	}
}
