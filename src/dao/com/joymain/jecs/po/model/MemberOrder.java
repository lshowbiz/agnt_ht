package com.joymain.jecs.po.model;

/**
 * 会员订单信息
 * @author houxyu
 * @date 2016-3-27
 *
 */
public class MemberOrder implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;

	private String memberUserCode;//会员编号
	private String memberUserName;//会员姓名
	private String oldLevel;//旧级别
	private String newLevel;//新级别
	private String orderCode;//订单编号
	private String orderType;//订单类型
	private String totalMoney;//总金额
	private String fundTotalMoney;//基金总金额
	private String credit;//抵扣积分
	private String totalPv;//会员编号
	private String orderPeriod;//订单期别
	private Float showOrder;//排序
	public Float getShowOrder() {
		return showOrder;
	}
	public void setShowOrder(Float showOrder) {
		this.showOrder = showOrder;
	}
	public String getMemberUserCode() {
		return memberUserCode;
	}
	public void setMemberUserCode(String memberUserCode) {
		this.memberUserCode = memberUserCode;
	}
	public String getMemberUserName() {
		return memberUserName;
	}
	public void setMemberUserName(String memberUserName) {
		this.memberUserName = memberUserName;
	}
	public String getOldLevel() {
		return oldLevel;
	}
	public void setOldLevel(String oldLevel) {
		this.oldLevel = oldLevel;
	}
	public String getNewLevel() {
		return newLevel;
	}
	public void setNewLevel(String newLevel) {
		this.newLevel = newLevel;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getFundTotalMoney() {
		return fundTotalMoney;
	}
	public void setFundTotalMoney(String fundTotalMoney) {
		this.fundTotalMoney = fundTotalMoney;
	}
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	public String getTotalPv() {
		return totalPv;
	}
	public void setTotalPv(String totalPv) {
		this.totalPv = totalPv;
	}
	public String getOrderPeriod() {
		return orderPeriod;
	}
	public void setOrderPeriod(String orderPeriod) {
		this.orderPeriod = orderPeriod;
	}
   
	
}