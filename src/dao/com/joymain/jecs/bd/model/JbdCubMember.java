package com.joymain.jecs.bd.model;
// Generated 2015-8-10 18:16:18 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;

public class JbdCubMember  {


    // Fields    

	//财年
	private BigDecimal fyear;
	
	//用户编号
    private String userCode;
    
    private String userName;
    
    //星特级别
    private String passStar;
    
    public String getPassStar() {
		return passStar;
	}

	public void setPassStar(String passStar) {
		this.passStar = passStar;
	}

	/**
     *  标志：1创办人，0非创办人 
     */
    private Integer status;
    private BigDecimal departmentNum;
    private BigDecimal fstHgtsNum;
    private BigDecimal fstHgdsNum;
    private BigDecimal fstHg4Num;
    private BigDecimal secHgtsNum;
    private BigDecimal secHgdsNum;
    private BigDecimal secHg4Num;
    private BigDecimal elsHgtsNum;
    private BigDecimal elsHgdsNum;
    private BigDecimal elsHg4Num;


	public BigDecimal getFyear() {
		return fyear;
	}

	public void setFyear(BigDecimal fyear) {
		this.fyear = fyear;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getDepartmentNum() {
		return departmentNum;
	}

	public void setDepartmentNum(BigDecimal departmentNum) {
		this.departmentNum = departmentNum;
	}

	public BigDecimal getFstHgtsNum() {
		return fstHgtsNum;
	}

	public void setFstHgtsNum(BigDecimal fstHgtsNum) {
		this.fstHgtsNum = fstHgtsNum;
	}

	public BigDecimal getFstHgdsNum() {
		return fstHgdsNum;
	}

	public void setFstHgdsNum(BigDecimal fstHgdsNum) {
		this.fstHgdsNum = fstHgdsNum;
	}

	public BigDecimal getFstHg4Num() {
		return fstHg4Num;
	}

	public void setFstHg4Num(BigDecimal fstHg4Num) {
		this.fstHg4Num = fstHg4Num;
	}

	public BigDecimal getSecHgtsNum() {
		return secHgtsNum;
	}

	public void setSecHgtsNum(BigDecimal secHgtsNum) {
		this.secHgtsNum = secHgtsNum;
	}

	public BigDecimal getSecHgdsNum() {
		return secHgdsNum;
	}

	public void setSecHgdsNum(BigDecimal secHgdsNum) {
		this.secHgdsNum = secHgdsNum;
	}

	public BigDecimal getSecHg4Num() {
		return secHg4Num;
	}

	public void setSecHg4Num(BigDecimal secHg4Num) {
		this.secHg4Num = secHg4Num;
	}

	public BigDecimal getElsHgtsNum() {
		return elsHgtsNum;
	}

	public void setElsHgtsNum(BigDecimal elsHgtsNum) {
		this.elsHgtsNum = elsHgtsNum;
	}

	public BigDecimal getElsHgdsNum() {
		return elsHgdsNum;
	}

	public void setElsHgdsNum(BigDecimal elsHgdsNum) {
		this.elsHgdsNum = elsHgdsNum;
	}

	public BigDecimal getElsHg4Num() {
		return elsHg4Num;
	}

	public void setElsHg4Num(BigDecimal elsHg4Num) {
		this.elsHg4Num = elsHg4Num;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
   


}
