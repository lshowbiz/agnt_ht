package com.joymain.jecs.pm.model;

public class JpmPresentPorductBind {
	
	private Long id;
	private String presentName;
	private String presentNo;
	private int spNum;
	private Long pttid;
	
	public Long getPttid() {
		return pttid;
	}
	public void setPttid(Long pttid) {
		this.pttid = pttid;
	}
	public String getPresentName() {
		return presentName;
	}
	public void setPresentName(String presentName) {
		this.presentName = presentName;
	}
	public String getPresentNo() {
		return presentNo;
	}
	public void setPresentNo(String presentNo) {
		this.presentNo = presentNo;
	}
	public int getSpNum() {
		return spNum;
	}
	public void setSpNum(int spNum) {
		this.spNum = spNum;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
