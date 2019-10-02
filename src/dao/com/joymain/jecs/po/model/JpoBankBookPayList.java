package com.joymain.jecs.po.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @hibernate.class table="JPO_BANKBOOK_PAY_LIST"
 */
public class JpoBankBookPayList implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	private Long id;
	/** 用户编号 */
	private String userCode;
	/** 代付人编号 */
	private String userSPcode;
	/** 订单编号 */
	private String memberOrderNo;
	/** 订单类型 */
	private String orderType;
	/**
	 * 类型: </br>
	 * 1 第三方支付  			2 电子存折支付 </br>
	 * 3 基金支付  			4 电子存折+积分</br>
	 * 5 电子存折+基金  		6 电子存折+第三方 </br>
	 * 7 积分 8 充值（电子存折） 	9 基金条目存入
	 */
	private Integer inType;
	/** 创建时间 */
	private Date createTime;
	/** 订单总金额 */
	private BigDecimal amount = new BigDecimal(0);
	/** 电子存折金额 */
	private BigDecimal dzAmt = new BigDecimal(0);
	/** 基金金额 */
	private BigDecimal jjAmt = new BigDecimal(0);
	/** 积分金额 */
	private BigDecimal jfAmt = new BigDecimal(0);
	/** 快钱金额 */
	private BigDecimal kqAmt = new BigDecimal(0);
	/** 订单所属人的团队顶点编号  */
	private String teamCode;
	/**
	 * 0 待分派		1 分派扣款  </br>
	 * 2 已扣款或充值 	3 分派审单 </br>
	 * 4 扣款失败 余额不足 	5 扣款失败 账户不存在</br> 
	 * 6 重复订单 		7 已审单
	 */
	private String flag = new String("0");
	/** 数据来源 1：PC，2：手机 */
	private String dataType = new String("1");
	/**资金类别  电子存折支付*/
	private Integer moneyType;
	/**资金类别 第三方支付 基金支付 积分*/
	private Integer moneyType1;
	/**资金类别 第三方支付手续费*/
	private Integer moneyType2;
	/**数据库函数填值*/
	private Date dealDate;
	/** 资金用途描述*/
	private String notes;
	private Long cpId;//会员拥有的代金券主键
	private BigDecimal cpValue;//使用代金券金额
	private BigDecimal fee; 
	private String checkUserCode;


	/**
	 *@hibernate.property column="CHECK_USER_CODE" 
	 */
	public String getCheckUserCode() {
		return checkUserCode;
	}
	public void setCheckUserCode(String checkUserCode) {
		this.checkUserCode = checkUserCode;
	}
	
	
	/**
	 *@hibernate.property column="FEE" 
	 */
	public BigDecimal getFee() {
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	/**
	 * * @hibernate.id generator-class="sequence" 
	 * type="java.lang.Long" column="ID"
	 * @hibernate.generator-param name="sequence" value="SEQ_LIST"
	 * 
	 */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 *@hibernate.property column="USER_CODE" length="20"
	 */
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	/**
	 *@hibernate.property column="USER_SP_CODE" length="20"
	 */
	public String getUserSPcode() {
		return userSPcode;
	}
	public void setUserSPcode(String userSPcode) {
		this.userSPcode = userSPcode;
	}
	/**
	 *@hibernate.property column="MEMBER_ORDER_NO" length="20"
	 */
	public String getMemberOrderNo() {
		return memberOrderNo;
	}
	public void setMemberOrderNo(String memberOrderNo) {
		this.memberOrderNo = memberOrderNo;
	}
	/**
	 *@hibernate.property column="ORDER_TYPE" length="2"
	 */
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	/**
	 *@hibernate.property column="IN_TYPE"
	 */
	public Integer getInType() {
		return inType;
	}
	public void setInType(Integer inType) {
		this.inType = inType;
	}
	/**
	 *@hibernate.property column="CREATE_TIME"
	 */
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 *@hibernate.property column="AMOUNT"
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**
	 *@hibernate.property column="DZ_AMT"
	 */
	public BigDecimal getDzAmt() {
		return dzAmt;
	}
	public void setDzAmt(BigDecimal dzAmt) {
		this.dzAmt = dzAmt;
	}
	/**
	 *@hibernate.property column="JJ_AMT"
	 */
	public BigDecimal getJjAmt() {
		return jjAmt;
	}
	public void setJjAmt(BigDecimal jjAmt) {
		this.jjAmt = jjAmt;
	}
	/**
	 *@hibernate.property column="JF_AMT"
	 */
	public BigDecimal getJfAmt() {
		return jfAmt;
	}
	public void setJfAmt(BigDecimal jfAmt) {
		this.jfAmt = jfAmt;
	}
	/**
	 *@hibernate.property column="KQ_AMT"
	 */
	public BigDecimal getKqAmt() {
		return kqAmt;
	}
	public void setKqAmt(BigDecimal kqAmt) {
		this.kqAmt = kqAmt;
	}
	/**
	 *@hibernate.property column="TEAM_CODE"
	 */
	public String getTeamCode() {
		return teamCode;
	}
	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}
	/**
	 *@hibernate.property column="FLAG"
	 */
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	/**
	 *@hibernate.property column="DATA_TYPE"
	 */
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	/**
	 *@hibernate.property column="MONEY_TYPE"
	 */
	public Integer getMoneyType() {
		return moneyType;
	}
	public void setMoneyType(Integer moneyType) {
		this.moneyType = moneyType;
	}
	/**
	 *@hibernate.property column="MONEY_TYPE1"
	 */
	public Integer getMoneyType1() {
		return moneyType1;
	}
	public void setMoneyType1(Integer moneyType1) {
		this.moneyType1 = moneyType1;
	}
	/**
	 *@hibernate.property column="MONEY_TYPE2"
	 */
	public Integer getMoneyType2() {
		return moneyType2;
	}
	public void setMoneyType2(Integer moneyType2) {
		this.moneyType2 = moneyType2;
	}
	/**
	 *@hibernate.property column="DEAL_DATE"
	 */
	public Date getDealDate() {
		return dealDate;
	}
	public void setDealDate(Date dealDate) {
		this.dealDate = dealDate;
	}
	/**
	 *@hibernate.property column="NOTES"
	 */
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	/**
	 *@hibernate.property column="CP_ID"
	 */
	public Long getCpId() {
		return cpId;
	}
	public void setCpId(Long cpId) {
		this.cpId = cpId;
	}
	
	/**
	 *@hibernate.property column="CP_VALUE"
	 */
	public BigDecimal getCpValue() {
		return cpValue;
	}
	public void setCpValue(BigDecimal cpValue) {
		this.cpValue = cpValue;
	}
}
