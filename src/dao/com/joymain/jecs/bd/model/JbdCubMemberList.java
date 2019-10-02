package com.joymain.jecs.bd.model;
// Generated 2015-8-10 18:16:18 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;

public class JbdCubMemberList  {

//	W_MONTH    NUMBER       Y                财月                               
//	TEAM_CODE  VARCHAR2(20) Y                星级特使编号                       
//	USER_CODE  VARCHAR2(20) Y                点位编号                           
//	PASS_STAR  VARCHAR2(2)  Y                点位奖衔                           
//	DEPARTMENT NUMBER       Y                部门：1基础部门以外最大部门，2基础部门以外第二大部门，99其他部门 
//	HG_COUNT   NUMBER       Y                达到皇冠的次数                     
//	 
	private BigDecimal wMonth;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	private String teamCode;
	private String userCode;
	private String userName;
	private String passStar;
	private BigDecimal dapartment;
	private BigDecimal hgCount;
	public BigDecimal getwMonth() {
		return wMonth;
	}
	public void setwMonth(BigDecimal wMonth) {
		this.wMonth = wMonth;
	}
	public String getTeamCode() {
		return teamCode;
	}
	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getPassStar() {
		return passStar;
	}
	public void setPassStar(String passStar) {
		this.passStar = passStar;
	}
	public BigDecimal getDapartment() {
		return dapartment;
	}
	public void setDapartment(BigDecimal dapartment) {
		this.dapartment = dapartment;
	}
	public BigDecimal getHgCount() {
		return hgCount;
	}
	public void setHgCount(BigDecimal hgCount) {
		this.hgCount = hgCount;
	}

	
	

}
