package com.joymain.jecs.pm.model;

public class JpmSalePorductBind implements java.io.Serializable{
	private String proName;
	private String proCategory;
	private String proPrice;
	private String proNo;
	private String pttid;
	
	
	public String getPttid() {
		return pttid;
	}
	public void setPttid(String pttid) {
		this.pttid = pttid;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getProCategory() {
		return proCategory;
	}
	public void setProCategory(String proCategory) {
		this.proCategory = proCategory;
	}
	public String getProPrice() {
		return proPrice;
	}
	public void setProPrice(String proPrice) {
		this.proPrice = proPrice;
	}
	public String getProNo() {
		return proNo;
	}
	public void setProNo(String proNo) {
		this.proNo = proNo;
	}
	
	
}
