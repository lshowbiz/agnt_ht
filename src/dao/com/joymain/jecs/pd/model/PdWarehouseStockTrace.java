package com.joymain.jecs.pd.model;

// Generated 2009-9-21 11:01:55 by Hibernate Tools 3.1.0.beta4

import java.util.Date;

import com.joymain.jecs.sys.model.SysUser;

/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="PD_WAREHOUSE_STOCK_TRACE"
 * 
 */

public class PdWarehouseStockTrace extends com.joymain.jecs.model.BaseObject
		implements java.io.Serializable {

	// Fields

	private Long uniNo;
	private String companyCode;
	PdWarehouse pdWarehouse = new PdWarehouse();
	private Date createTime;
	private String productNo;
	private Long beforeQty;
	private Long changeQty;
	private Long behindQty;
	private String orderNo;
	private String actionType;
	private String operatorCode;
	
	private String orderType;
	private String subType="0";
	private String remark;

	private String ip;
	 private String customCode;
//	private SysUser customer=new SysUser();
	
	 
	 /**
		 * * @hibernate.property column="CUSTOM_CODE" 
		 * 
		 */
	public String getCustomCode() {
		return customCode;
	}

	public void setCustomCode(String customCode) {
		this.customCode = customCode;
	}

	/**
	 * * @hibernate.property column="OPERATOR_CODE" 
	 * 
	 */
	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	

	

	

	// Constructors

	/** default constructor */
	public PdWarehouseStockTrace() {
	}

	/** full constructor */
	public PdWarehouseStockTrace(String companyCode, Date createTime,
			String productNo, Long beforeQty, Long changeQty, Long behindQty,
			String orderNo, String actionType, String usrCode, String ip) {
		this.companyCode = companyCode;
		this.createTime = createTime;
		this.productNo = productNo;
		this.beforeQty = beforeQty;
		this.changeQty = changeQty;
		this.behindQty = behindQty;
		this.orderNo = orderNo;
		this.actionType = actionType;
		// this.usrCode = usrCode;
		this.ip = ip;
	}

	/**
	 * *
	 * 
	 * @hibernate.many-to-one not-null="true" inverse="true"
	 * @hibernate.column name="WAREHOUSE_NO"
	 * 
	 */
	public PdWarehouse getPdWarehouse() {
		return pdWarehouse;
	}

	public void setPdWarehouse(PdWarehouse pdWarehouse) {
		this.pdWarehouse = pdWarehouse;
	}

	// Property accessors
	/**
	 * * @hibernate.id generator-class="native" type="java.lang.Long"
	 * column="UNI_NO"
	 * 
	 */

	public Long getUniNo() {
		return this.uniNo;
	}

	public void setUniNo(Long uniNo) {
		this.uniNo = uniNo;
	}

	/**
	 * * @hibernate.property column="COMPANY_CODE" length="2"
	 * 
	 */

	public String getCompanyCode() {
		return this.companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	/**
	 * * @hibernate.property column="CREATE_TIME" length="7"
	 * 
	 */

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * * @hibernate.property column="PRODUCT_NO" length="20"
	 * 
	 */

	public String getProductNo() {
		return this.productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	/**
	 * * @hibernate.property column="BEFORE_QTY" length="10"
	 * 
	 */

	public Long getBeforeQty() {
		return this.beforeQty;
	}

	public void setBeforeQty(Long beforeQty) {
		this.beforeQty = beforeQty;
	}

	/**
	 * * @hibernate.property column="CHANGE_QTY" length="10"
	 * 
	 */

	public Long getChangeQty() {
		return this.changeQty;
	}

	public void setChangeQty(Long changeQty) {
		this.changeQty = changeQty;
	}

	/**
	 * * @hibernate.property column="BEHIND_QTY" length="10"
	 * 
	 */

	public Long getBehindQty() {
		return this.behindQty;
	}

	public void setBehindQty(Long behindQty) {
		this.behindQty = behindQty;
	}

	/**
	 * * @hibernate.property column="ORDER_NO" length="30"
	 * 
	 */

	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * * @hibernate.property column="ACTION_TYPE" length="5"
	 * 
	 */

	public String getActionType() {
		return this.actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	/**
	 * * @hibernate.property column="IP" length="15"
	 * 
	 */

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	
	/**
	 * * @hibernate.property column="ORDER_TYPE" length="15"
	 * 
	 */
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	/**
	 * * @hibernate.property column="SUB_TYPE" length="15"
	 * 
	 */
	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	/**
	 * * @hibernate.property column="REMARK" length="2500"
	 * 
	 */
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * toString
	 * 
	 * @return String
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();

		buffer.append(getClass().getName()).append("@").append(
				Integer.toHexString(hashCode())).append(" [");
		buffer.append("companyCode").append("='").append(getCompanyCode())
				.append("' ");
		buffer.append("createTime").append("='").append(getCreateTime())
				.append("' ");
		buffer.append("productNo").append("='").append(getProductNo()).append(
				"' ");
		buffer.append("beforeQty").append("='").append(getBeforeQty()).append(
				"' ");
		buffer.append("changeQty").append("='").append(getChangeQty()).append(
				"' ");
		buffer.append("behindQty").append("='").append(getBehindQty()).append(
				"' ");
		buffer.append("orderNo").append("='").append(getOrderNo()).append("' ");
		buffer.append("actionType").append("='").append(getActionType())
				.append("' ");
		// buffer.append("usrCode").append("='").append(getUsrCode()).append("' ");
		buffer.append("ip").append("='").append(getIp()).append("' ");
		buffer.append("]");

		return buffer.toString();
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PdWarehouseStockTrace))
			return false;
		PdWarehouseStockTrace castOther = (PdWarehouseStockTrace) other;

		return ((this.getUniNo() == castOther.getUniNo()) || (this.getUniNo() != null
				&& castOther.getUniNo() != null && this.getUniNo().equals(
				castOther.getUniNo())))
				&& ((this.getCompanyCode() == castOther.getCompanyCode()) || (this
						.getCompanyCode() != null
						&& castOther.getCompanyCode() != null && this
						.getCompanyCode().equals(castOther.getCompanyCode())))
				&& ((this.getCreateTime() == castOther.getCreateTime()) || (this
						.getCreateTime() != null
						&& castOther.getCreateTime() != null && this
						.getCreateTime().equals(castOther.getCreateTime())))
				&& ((this.getProductNo() == castOther.getProductNo()) || (this
						.getProductNo() != null
						&& castOther.getProductNo() != null && this
						.getProductNo().equals(castOther.getProductNo())))
				&& ((this.getBeforeQty() == castOther.getBeforeQty()) || (this
						.getBeforeQty() != null
						&& castOther.getBeforeQty() != null && this
						.getBeforeQty().equals(castOther.getBeforeQty())))
				&& ((this.getChangeQty() == castOther.getChangeQty()) || (this
						.getChangeQty() != null
						&& castOther.getChangeQty() != null && this
						.getChangeQty().equals(castOther.getChangeQty())))
				&& ((this.getBehindQty() == castOther.getBehindQty()) || (this
						.getBehindQty() != null
						&& castOther.getBehindQty() != null && this
						.getBehindQty().equals(castOther.getBehindQty())))
				&& ((this.getOrderNo() == castOther.getOrderNo()) || (this
						.getOrderNo() != null
						&& castOther.getOrderNo() != null && this.getOrderNo()
						.equals(castOther.getOrderNo())))
				&& ((this.getActionType() == castOther.getActionType()) || (this
						.getActionType() != null
						&& castOther.getActionType() != null && this
						.getActionType().equals(castOther.getActionType())))
				// && ( (this.getUsrCode()==castOther.getUsrCode()) || (
				// this.getUsrCode()!=null && castOther.getUsrCode()!=null &&
				// this.getUsrCode().equals(castOther.getUsrCode()) ) )
				&& ((this.getIp() == castOther.getIp()) || (this.getIp() != null
						&& castOther.getIp() != null && this.getIp().equals(
						castOther.getIp())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUniNo() == null ? 0 : this.getUniNo().hashCode());
		result = 37
				* result
				+ (getCompanyCode() == null ? 0 : this.getCompanyCode()
						.hashCode());
		result = 37
				* result
				+ (getCreateTime() == null ? 0 : this.getCreateTime()
						.hashCode());
		result = 37 * result
				+ (getProductNo() == null ? 0 : this.getProductNo().hashCode());
		result = 37 * result
				+ (getBeforeQty() == null ? 0 : this.getBeforeQty().hashCode());
		result = 37 * result
				+ (getChangeQty() == null ? 0 : this.getChangeQty().hashCode());
		result = 37 * result
				+ (getBehindQty() == null ? 0 : this.getBehindQty().hashCode());
		result = 37 * result
				+ (getOrderNo() == null ? 0 : this.getOrderNo().hashCode());
		result = 37
				* result
				+ (getActionType() == null ? 0 : this.getActionType()
						.hashCode());
		// result = 37 * result + ( getUsrCode() == null ? 0 :
		// this.getUsrCode().hashCode() );
		result = 37 * result + (getIp() == null ? 0 : this.getIp().hashCode());
		return result;
	}

}
