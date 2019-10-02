package com.joymain.jecs.po.model;

// Generated 2009-11-3 17:52:39 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.joymain.jecs.sys.model.SysUser;

/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="JPO_COUNTER_ORDER"
 * 
 */

public class JpoCounterOrder extends com.joymain.jecs.model.BaseObject
		implements java.io.Serializable {

	// Fields

	private String coNo;
	private String companyCode;
	private SysUser sysUser = new SysUser();
	private String warehouseNo;
	private BigDecimal amount=new BigDecimal(0);
	private Date confirmTime;
	private String confirmUserNo;
	private String remark;
	private String createUserNo;
	private Date createTime;
	private Integer csStatus;
	private String stockFlag="0";
	private Set jpoCounterOrderDetails = new HashSet(0);
	private SysUser shipper;
	private Date shipTime;
	private SysUser repealerCode;
	private Date repealTime;
	// Constructors

	/** default constructor */
	public JpoCounterOrder() {
	}

	/** minimal constructor */
	public JpoCounterOrder(String companyCode, String userCode,
			String warehouseNo, BigDecimal amount, String createUserNo,
			Date createTime, int csStatus) {
		this.companyCode = companyCode;
		this.warehouseNo = warehouseNo;
		this.amount = amount;
		this.createUserNo = createUserNo;
		this.createTime = createTime;
		this.csStatus = csStatus;
	}

	/** full constructor */
	public JpoCounterOrder(String companyCode, String userCode,
			String warehouseNo, BigDecimal amount, Date confirmTime,
			String confirmUserNo, String remark, String createUserNo,
			Date createTime, int csStatus, String stockFlag) {
		this.companyCode = companyCode;
		this.warehouseNo = warehouseNo;
		this.amount = amount;
		this.confirmTime = confirmTime;
		this.confirmUserNo = confirmUserNo;
		this.remark = remark;
		this.createUserNo = createUserNo;
		this.createTime = createTime;
		this.csStatus = csStatus;
		this.stockFlag = stockFlag;
	}

	// Property accessors
	/**
	 * * @hibernate.id generator-class="assigned" type="java.lang.String"
	 * column="CO_NO"
	 * 
	 */

	public String getCoNo() {
		return this.coNo;
	}

	public void setCoNo(String coNo) {
		this.coNo = coNo;
	}

	/**
	 * * @hibernate.property column="COMPANY_CODE" length="2" not-null="true"
	 * 
	 */

	public String getCompanyCode() {
		return this.companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	/**
	 * * @hibernate.property column="WAREHOUSE_NO" length="20" not-null="true"
	 * 
	 */

	public String getWarehouseNo() {
		return this.warehouseNo;
	}
    /**
     * @spring.validator type="required"
     */
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	/**
	 * * @hibernate.property column="AMOUNT" length="18" not-null="true"
	 * 
	 */

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * * @hibernate.property column="CONFIRM_TIME" length="7"
	 * 
	 */

	public Date getConfirmTime() {
		return this.confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	/**
	 * * @hibernate.property column="SHIP_TIME" length="7"
	 * 
	 */
	public Date getShipTime() {
		return shipTime;
	}

	public void setShipTime(Date shipTime) {
		this.shipTime = shipTime;
	}

	/**
	 * * @hibernate.property column="CONFIRM_USER_NO" length="20"
	 * 
	 */

	public String getConfirmUserNo() {
		return this.confirmUserNo;
	}

	public void setConfirmUserNo(String confirmUserNo) {
		this.confirmUserNo = confirmUserNo;
	}

	/**
	 * * @hibernate.property column="REMARK" length="500"
	 * 
	 */

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * * @hibernate.property column="CREATE_USER_NO" length="20" not-null="true"
	 * 
	 */

	public String getCreateUserNo() {
		return this.createUserNo;
	}

	public void setCreateUserNo(String createUserNo) {
		this.createUserNo = createUserNo;
	}

	/**
	 * * @hibernate.property column="CREATE_TIME" length="7" not-null="true"
	 * 
	 */

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * * @hibernate.property column="CS_STATUS" length="1" not-null="true"
	 * 
	 */
	public Integer getCsStatus() {
		return csStatus;
	}

	public void setCsStatus(Integer csStatus) {
		this.csStatus = csStatus;
	}

	/**
	 * * @hibernate.property column="STOCK_FLAG" length="1"
	 * 
	 */

	public String getStockFlag() {
		return this.stockFlag;
	}

	public void setStockFlag(String stockFlag) {
		this.stockFlag = stockFlag;
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
		buffer.append("warehouseNo").append("='").append(getWarehouseNo())
				.append("' ");
		buffer.append("amount").append("='").append(getAmount()).append("' ");
		buffer.append("confirmTime").append("='").append(getConfirmTime())
				.append("' ");
		buffer.append("confirmUserNo").append("='").append(getConfirmUserNo())
				.append("' ");
		buffer.append("remark").append("='").append(getRemark()).append("' ");
		buffer.append("createUserNo").append("='").append(getCreateUserNo())
				.append("' ");
		buffer.append("createTime").append("='").append(getCreateTime())
				.append("' ");
		buffer.append("csStatus").append("='").append(getCsStatus()).append(
				"' ");
		buffer.append("stockFlag").append("='").append(getStockFlag()).append(
				"' ");
		buffer.append("]");

		return buffer.toString();
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof JpoCounterOrder))
			return false;
		JpoCounterOrder castOther = (JpoCounterOrder) other;

		return ((this.getCoNo() == castOther.getCoNo()) || (this.getCoNo() != null
				&& castOther.getCoNo() != null && this.getCoNo().equals(
				castOther.getCoNo())))
				&& ((this.getCompanyCode() == castOther.getCompanyCode()) || (this
						.getCompanyCode() != null
						&& castOther.getCompanyCode() != null && this
						.getCompanyCode().equals(castOther.getCompanyCode())))

				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getAmount() == castOther.getAmount()) || (this
						.getAmount() != null
						&& castOther.getAmount() != null && this.getAmount()
						.equals(castOther.getAmount())))
				&& ((this.getConfirmTime() == castOther.getConfirmTime()) || (this
						.getConfirmTime() != null
						&& castOther.getConfirmTime() != null && this
						.getConfirmTime().equals(castOther.getConfirmTime())))
				&& ((this.getConfirmUserNo() == castOther.getConfirmUserNo()) || (this
						.getConfirmUserNo() != null
						&& castOther.getConfirmUserNo() != null && this
						.getConfirmUserNo()
						.equals(castOther.getConfirmUserNo())))
				&& ((this.getRemark() == castOther.getRemark()) || (this
						.getRemark() != null
						&& castOther.getRemark() != null && this.getRemark()
						.equals(castOther.getRemark())))
				&& ((this.getCreateUserNo() == castOther.getCreateUserNo()) || (this
						.getCreateUserNo() != null
						&& castOther.getCreateUserNo() != null && this
						.getCreateUserNo().equals(castOther.getCreateUserNo())))
				&& ((this.getCreateTime() == castOther.getCreateTime()) || (this
						.getCreateTime() != null
						&& castOther.getCreateTime() != null && this
						.getCreateTime().equals(castOther.getCreateTime())))
				&& (this.getCsStatus() == castOther.getCsStatus())
				&& ((this.getStockFlag() == castOther.getStockFlag()) || (this
						.getStockFlag() != null
						&& castOther.getStockFlag() != null && this
						.getStockFlag().equals(castOther.getStockFlag())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getCoNo() == null ? 0 : this.getCoNo().hashCode());
		result = 37
				* result
				+ (getCompanyCode() == null ? 0 : this.getCompanyCode()
						.hashCode());
		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37 * result
				+ (getAmount() == null ? 0 : this.getAmount().hashCode());
		result = 37
				* result
				+ (getConfirmTime() == null ? 0 : this.getConfirmTime()
						.hashCode());
		result = 37
				* result
				+ (getConfirmUserNo() == null ? 0 : this.getConfirmUserNo()
						.hashCode());
		result = 37 * result
				+ (getRemark() == null ? 0 : this.getRemark().hashCode());
		result = 37
				* result
				+ (getCreateUserNo() == null ? 0 : this.getCreateUserNo()
						.hashCode());
		result = 37
				* result
				+ (getCreateTime() == null ? 0 : this.getCreateTime()
						.hashCode());
		result = 37 * result + this.getCsStatus();
		result = 37 * result
				+ (getStockFlag() == null ? 0 : this.getStockFlag().hashCode());
		return result;
	}

	/**
	 * *
	 * 
	 * @hibernate.many-to-one 
	 * @hibernate.column name="SHIPPER_CODE"
	 * 
	 */
	public SysUser getShipper() {
		return shipper;
	}

	public void setShipper(SysUser shipper) {
		this.shipper = shipper;
	}

	/**
	 * *
	 * 
	 * @hibernate.many-to-one not-null="true"
	 * @hibernate.column name="USER_CODE"
	 * 
	 */
	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	/**
	 * *
	 * 
	 * @hibernate.set lazy="true" inverse="true" cascade="all-delete-orphan"
	 * @hibernate.collection-key column="CO_NO"
	 * @hibernate.collection-one-to-many 
	 *                                   class="com.joymain.jecs.po.model.JpoCounterOrderDetail"
	 * 
	 */
	public Set getJpoCounterOrderDetails() {
		return jpoCounterOrderDetails;
	}

	public void setJpoCounterOrderDetails(Set jpoCounterOrderDetails) {
		this.jpoCounterOrderDetails = jpoCounterOrderDetails;
	}
	/**
	 * *
	 * 
	 * @hibernate.many-to-one 
	 * @hibernate.column name="REPEALER_CODE"
	 * 
	 */
	public SysUser getRepealerCode() {
		return repealerCode;
	}

	public void setRepealerCode(SysUser repealerCode) {
		this.repealerCode = repealerCode;
	}
	/**
	 * * @hibernate.property column="REPEAL_TIME" length="7"
	 * 
	 */
	public Date getRepealTime() {
		return repealTime;
	}

	public void setRepealTime(Date repealTime) {
		this.repealTime = repealTime;
	}

}
