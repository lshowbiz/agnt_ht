package com.joymain.jecs.pd.model;

// Generated 2009-9-24 14:20:32 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.joymain.jecs.sys.model.SysUser;

/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="PD_RETURN_PURCHASE"
 * 
 */

public class PdReturnPurchase extends com.joymain.jecs.model.BaseObject
		implements java.io.Serializable {

	// Fields

	private String rpNo;
	private String companyCode;

	private SysUser customer = new SysUser();
	// private String customerCode;

	private BigDecimal amount;
	private String returnWarehouseNo;
	private Date createTime;
	private String createUsrCode;
	private String remark;
	private Date checkTime;
	private String checkUsrCode;
	private String checkRemark;
	private Date financeTime;
	private String financeUsrCode;//临时字段:有用，存储发货退回单的会员编号
	private String financeRemark;//发货退回单消息是否推送出去标识：isNew表明发货退回单没有推送出去;noNew表明发货退回单已经推送出去
	private Date okTime;
	private String okUsrCode;
	private String okRemark;
	private Date editTime;
	private String editUsrCode;
	private Integer orderFlag=-1;
	private String stockFlag;
	private Set<PdReturnPurchaseDetail> pdReturnPurchaseDetails = new HashSet();

	// 新增收货地址
	private String firstName;
	private String lastName;
	private String province;
	private String city;
	private String district;
	private String address;
	private String postalcode;
	private String phone;
	private String email;
	private String mobiletele;
	
	//modify by fu 2016-04-19 发货退回
	private String returnType;
	
	
	

	// Constructors

	/**
	 * *
	 * 
	 * @hibernate.set lazy="true" inverse="true" cascade="all-delete-orphan"
	 *                order-by="product_No desc"
	 * @hibernate.collection-key column="RP_NO"
	 * @hibernate.collection-one-to-many 
	 *                                   class="com.joymain.jecs.pd.model.PdReturnPurchaseDetail"
	 * 
	 */
	public Set<PdReturnPurchaseDetail> getPdReturnPurchaseDetails() {
		return pdReturnPurchaseDetails;
	}

	public void setPdReturnPurchaseDetails(
			Set<PdReturnPurchaseDetail> pdReturnPurchaseDetails) {
		this.pdReturnPurchaseDetails = pdReturnPurchaseDetails;
	}

	/** default constructor */
	public PdReturnPurchase() {
	}

	/** minimal constructor */
	public PdReturnPurchase(String companyCode, String customerNo,
			BigDecimal amount, String returnWarehouseNo, Date createTime,
			String createUsrNo) {
		this.companyCode = companyCode;
		// this.customerCode = customerNo;
		this.amount = amount;
		this.returnWarehouseNo = returnWarehouseNo;
		this.createTime = createTime;
		this.createUsrCode = createUsrNo;
	}

	/** full constructor */
	public PdReturnPurchase(String companyCode, String customerNo,
			BigDecimal amount, String returnWarehouseNo, Date createTime,
			String createUsrNo, String remark, Date checkTime,
			String checkUsrNo, String checkRemark, Date financeTime,
			String financeUsrNo, String financeRemark, Date okTime,
			String okUsrNo, String okRemark, Date editTime, String editUsrNo,
			Integer orderFlag, String stockFlag,String returnType) {
		this.companyCode = companyCode;
		// this.customerCode = customerNo;
		this.amount = amount;
		this.returnWarehouseNo = returnWarehouseNo;
		this.createTime = createTime;
		this.createUsrCode = createUsrNo;
		this.remark = remark;
		this.checkTime = checkTime;
		this.checkUsrCode = checkUsrNo;
		this.checkRemark = checkRemark;
		this.financeTime = financeTime;
		this.financeUsrCode = financeUsrNo;
		this.financeRemark = financeRemark;
		this.okTime = okTime;
		this.okUsrCode = okUsrNo;
		this.okRemark = okRemark;
		this.editTime = editTime;
		this.editUsrCode = editUsrNo;
		this.orderFlag = orderFlag;
		this.stockFlag = stockFlag;
		this.returnType = returnType;
		
	}

	/**
	 * *
	 * 
	 * @hibernate.many-to-one not-null="true"
	 * @hibernate.column name="CUSTOMER_CODE"
	 * 
	 */
	public SysUser getCustomer() {
		return customer;
	}
	
	public void setCustomer(SysUser customer) {
		this.customer = customer;
	}

	// Property accessors
	/**
	 * * @hibernate.id generator-class="assigned" type="java.lang.String"
	 * column="RP_NO"
	 * 
	 */

	public String getRpNo() {
		return this.rpNo;
	}

	public void setRpNo(String rpNo) {
		this.rpNo = rpNo;
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
	 * * @hibernate.property column="AMOUNT" length="20" not-null="true"
	 * 
	 */

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * * @hibernate.property column="RETURN_WAREHOUSE_NO" length="20"
	 * not-null="true"
	 * 
	 */

	public String getReturnWarehouseNo() {
		return this.returnWarehouseNo;
	}

	/**
	 * @spring.validator type="required"
	 */
	public void setReturnWarehouseNo(String returnWarehouseNo) {
		this.returnWarehouseNo = returnWarehouseNo;
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
	 * * @hibernate.property column="CREATE_USR_CODE" length="20"
	 * not-null="true"
	 * 
	 */

	public String getCreateUsrCode() {
		return this.createUsrCode;
	}

	public void setCreateUsrCode(String createUsrNo) {
		this.createUsrCode = createUsrNo;
	}

	/**
	 * * @hibernate.property column="REMARK" length="200"
	 * 
	 */

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * * @hibernate.property column="CHECK_TIME" length="7"
	 * 
	 */

	public Date getCheckTime() {
		return this.checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	/**
	 * * @hibernate.property column="CHECK_USR_CODE" length="20"
	 * 
	 */

	public String getCheckUsrCode() {
		return this.checkUsrCode;
	}

	public void setCheckUsrCode(String checkUsrNo) {
		this.checkUsrCode = checkUsrNo;
	}

	/**
	 * * @hibernate.property column="CHECK_REMARK" length="200"
	 * 
	 */

	public String getCheckRemark() {
		return this.checkRemark;
	}

	public void setCheckRemark(String checkRemark) {
		this.checkRemark = checkRemark;
	}

	/**
	 * * @hibernate.property column="FINANCE_TIME" length="7"
	 * 
	 */

	public Date getFinanceTime() {
		return this.financeTime;
	}

	public void setFinanceTime(Date financeTime) {
		this.financeTime = financeTime;
	}

	/**
	 * * @hibernate.property column="FINANCE_USR_CODE" length="20"
	 * 
	 */

	public String getFinanceUsrCode() {
		return this.financeUsrCode;
	}

	public void setFinanceUsrCode(String financeUsrNo) {
		this.financeUsrCode = financeUsrNo;
	}

	/**
	 * * @hibernate.property column="FINANCE_REMARK" length="200"
	 * 
	 */

	public String getFinanceRemark() {
		return this.financeRemark;
	}

	public void setFinanceRemark(String financeRemark) {
		this.financeRemark = financeRemark;
	}

	/**
	 * * @hibernate.property column="OK_TIME" length="7"
	 * 
	 */

	public Date getOkTime() {
		return this.okTime;
	}

	public void setOkTime(Date okTime) {
		this.okTime = okTime;
	}

	/**
	 * * @hibernate.property column="OK_USR_CODE" length="20"
	 * 
	 */

	public String getOkUsrCode() {
		return this.okUsrCode;
	}

	public void setOkUsrCode(String okUsrNo) {
		this.okUsrCode = okUsrNo;
	}

	/**
	 * * @hibernate.property column="OK_REMARK" length="200"
	 * 
	 */

	public String getOkRemark() {
		return this.okRemark;
	}

	public void setOkRemark(String okRemark) {
		this.okRemark = okRemark;
	}

	/**
	 * * @hibernate.property column="EDIT_TIME" length="7"
	 * 
	 */

	public Date getEditTime() {
		return this.editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	/**
	 * * @hibernate.property column="EDIT_USR_CODE" length="20"
	 * 
	 */

	public String getEditUsrCode() {
		return this.editUsrCode;
	}

	public void setEditUsrCode(String editUsrNo) {
		this.editUsrCode = editUsrNo;
	}

	/**
	 * * @hibernate.property column="ORDER_FLAG" length="2"
	 * 
	 */

	public Integer getOrderFlag() {
		return this.orderFlag;
	}

	public void setOrderFlag(Integer orderFlag) {
		this.orderFlag = orderFlag;
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
	 * * @hibernate.property column="RETURN_TYPE" length="50"
	 * 
	 */
	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
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
		// buffer.append("customerCode").append("='").append(getCustomerCode()).append("' ");
		buffer.append("amount").append("='").append(getAmount()).append("' ");
		buffer.append("returnWarehouseNo").append("='").append(
				getReturnWarehouseNo()).append("' ");
		buffer.append("createTime").append("='").append(getCreateTime())
				.append("' ");
		buffer.append("createUsrNo").append("='").append(getCreateUsrCode())
				.append("' ");
		buffer.append("remark").append("='").append(getRemark()).append("' ");
		buffer.append("checkTime").append("='").append(getCheckTime()).append(
				"' ");
		buffer.append("checkUsrCode").append("='").append(getCheckUsrCode())
				.append("' ");
		buffer.append("checkRemark").append("='").append(getCheckRemark())
				.append("' ");
		buffer.append("financeTime").append("='").append(getFinanceTime())
				.append("' ");
		buffer.append("financeUsrCode").append("='")
				.append(getFinanceUsrCode()).append("' ");
		buffer.append("financeRemark").append("='").append(getFinanceRemark())
				.append("' ");
		buffer.append("okTime").append("='").append(getOkTime()).append("' ");
		buffer.append("okUsrCode").append("='").append(getOkUsrCode()).append(
				"' ");
		buffer.append("okRemark").append("='").append(getOkRemark()).append(
				"' ");
		buffer.append("editTime").append("='").append(getEditTime()).append(
				"' ");
		buffer.append("editUsrCode").append("='").append(getEditUsrCode())
				.append("' ");
		buffer.append("orderFlag").append("='").append(getOrderFlag()).append(
				"' ");
		buffer.append("stockFlag").append("='").append(getStockFlag()).append(
				"' ");
		buffer.append("returnType").append("='").append(getReturnType()).append(
		"' ");
		buffer.append("]");

		return buffer.toString();
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PdReturnPurchase))
			return false;
		PdReturnPurchase castOther = (PdReturnPurchase) other;

		return ((this.getRpNo() == castOther.getRpNo()) || (this.getRpNo() != null
				&& castOther.getRpNo() != null && this.getRpNo().equals(
				castOther.getRpNo())))
				&& ((this.getCompanyCode() == castOther.getCompanyCode()) || (this
						.getCompanyCode() != null
						&& castOther.getCompanyCode() != null && this
						.getCompanyCode().equals(castOther.getCompanyCode())))
				// && ( (this.getCustomerCode()==castOther.getCustomerCode()) ||
				// ( this.getCustomerCode()!=null &&
				// castOther.getCustomerCode()!=null &&
				// this.getCustomerCode().equals(castOther.getCustomerCode()) )
				// )
				&& ((this.getAmount() == castOther.getAmount()) || (this
						.getAmount() != null
						&& castOther.getAmount() != null && this.getAmount()
						.equals(castOther.getAmount())))
				&& ((this.getReturnWarehouseNo() == castOther
						.getReturnWarehouseNo()) || (this
						.getReturnWarehouseNo() != null
						&& castOther.getReturnWarehouseNo() != null && this
						.getReturnWarehouseNo().equals(
								castOther.getReturnWarehouseNo())))
				&& ((this.getCreateTime() == castOther.getCreateTime()) || (this
						.getCreateTime() != null
						&& castOther.getCreateTime() != null && this
						.getCreateTime().equals(castOther.getCreateTime())))
				&& ((this.getCreateUsrCode() == castOther.getCreateUsrCode()) || (this
						.getCreateUsrCode() != null
						&& castOther.getCreateUsrCode() != null && this
						.getCreateUsrCode()
						.equals(castOther.getCreateUsrCode())))
				&& ((this.getRemark() == castOther.getRemark()) || (this
						.getRemark() != null
						&& castOther.getRemark() != null && this.getRemark()
						.equals(castOther.getRemark())))
				&& ((this.getCheckTime() == castOther.getCheckTime()) || (this
						.getCheckTime() != null
						&& castOther.getCheckTime() != null && this
						.getCheckTime().equals(castOther.getCheckTime())))
				&& ((this.getCheckUsrCode() == castOther.getCheckUsrCode()) || (this
						.getCheckUsrCode() != null
						&& castOther.getCheckUsrCode() != null && this
						.getCheckUsrCode().equals(castOther.getCheckUsrCode())))
				&& ((this.getCheckRemark() == castOther.getCheckRemark()) || (this
						.getCheckRemark() != null
						&& castOther.getCheckRemark() != null && this
						.getCheckRemark().equals(castOther.getCheckRemark())))
				&& ((this.getFinanceTime() == castOther.getFinanceTime()) || (this
						.getFinanceTime() != null
						&& castOther.getFinanceTime() != null && this
						.getFinanceTime().equals(castOther.getFinanceTime())))
				&& ((this.getFinanceUsrCode() == castOther.getFinanceUsrCode()) || (this
						.getFinanceUsrCode() != null
						&& castOther.getFinanceUsrCode() != null && this
						.getFinanceUsrCode().equals(
								castOther.getFinanceUsrCode())))
				&& ((this.getFinanceRemark() == castOther.getFinanceRemark()) || (this
						.getFinanceRemark() != null
						&& castOther.getFinanceRemark() != null && this
						.getFinanceRemark()
						.equals(castOther.getFinanceRemark())))
				&& ((this.getOkTime() == castOther.getOkTime()) || (this
						.getOkTime() != null
						&& castOther.getOkTime() != null && this.getOkTime()
						.equals(castOther.getOkTime())))
				&& ((this.getOkUsrCode() == castOther.getOkUsrCode()) || (this
						.getOkUsrCode() != null
						&& castOther.getOkUsrCode() != null && this
						.getOkUsrCode().equals(castOther.getOkUsrCode())))
				&& ((this.getOkRemark() == castOther.getOkRemark()) || (this
						.getOkRemark() != null
						&& castOther.getOkRemark() != null && this
						.getOkRemark().equals(castOther.getOkRemark())))
				&& ((this.getEditTime() == castOther.getEditTime()) || (this
						.getEditTime() != null
						&& castOther.getEditTime() != null && this
						.getEditTime().equals(castOther.getEditTime())))
				&& ((this.getEditUsrCode() == castOther.getEditUsrCode()) || (this
						.getEditUsrCode() != null
						&& castOther.getEditUsrCode() != null && this
						.getEditUsrCode().equals(castOther.getEditUsrCode())))
				&& ((this.getOrderFlag() == castOther.getOrderFlag()) || (this
						.getOrderFlag() != null
						&& castOther.getOrderFlag() != null && this
						.getOrderFlag().equals(castOther.getOrderFlag())))
				&& ((this.getStockFlag() == castOther.getStockFlag()) || (this
						.getStockFlag() != null
						&& castOther.getStockFlag() != null && this
						.getStockFlag().equals(castOther.getStockFlag())))
				&& ((this.getReturnType() == castOther.getReturnType()) || (this
						.getReturnType() != null
						&& castOther.getReturnType() != null && this
						.getReturnType().equals(castOther.getReturnType())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRpNo() == null ? 0 : this.getRpNo().hashCode());
		result = 37
				* result
				+ (getCompanyCode() == null ? 0 : this.getCompanyCode()
						.hashCode());
		// result = 37 * result + ( getCustomerCode() == null ? 0 :
		// this.getCustomerCode().hashCode() );
		result = 37 * result
				+ (getAmount() == null ? 0 : this.getAmount().hashCode());
		result = 37
				* result
				+ (getReturnWarehouseNo() == null ? 0 : this
						.getReturnWarehouseNo().hashCode());
		result = 37
				* result
				+ (getCreateTime() == null ? 0 : this.getCreateTime()
						.hashCode());
		result = 37
				* result
				+ (getCreateUsrCode() == null ? 0 : this.getCreateUsrCode()
						.hashCode());
		result = 37 * result
				+ (getRemark() == null ? 0 : this.getRemark().hashCode());
		result = 37 * result
				+ (getCheckTime() == null ? 0 : this.getCheckTime().hashCode());
		result = 37
				* result
				+ (getCheckUsrCode() == null ? 0 : this.getCheckUsrCode()
						.hashCode());
		result = 37
				* result
				+ (getCheckRemark() == null ? 0 : this.getCheckRemark()
						.hashCode());
		result = 37
				* result
				+ (getFinanceTime() == null ? 0 : this.getFinanceTime()
						.hashCode());
		result = 37
				* result
				+ (getFinanceUsrCode() == null ? 0 : this.getFinanceUsrCode()
						.hashCode());
		result = 37
				* result
				+ (getFinanceRemark() == null ? 0 : this.getFinanceRemark()
						.hashCode());
		result = 37 * result
				+ (getOkTime() == null ? 0 : this.getOkTime().hashCode());
		result = 37 * result
				+ (getOkUsrCode() == null ? 0 : this.getOkUsrCode().hashCode());
		result = 37 * result
				+ (getOkRemark() == null ? 0 : this.getOkRemark().hashCode());
		result = 37 * result
				+ (getEditTime() == null ? 0 : this.getEditTime().hashCode());
		result = 37
				* result
				+ (getEditUsrCode() == null ? 0 : this.getEditUsrCode()
						.hashCode());
		result = 37 * result
				+ (getOrderFlag() == null ? 0 : this.getOrderFlag().hashCode());
		result = 37 * result
				+ (getStockFlag() == null ? 0 : this.getStockFlag().hashCode());
		result = 37 * result
		        + (getReturnType() == null ? 0 : this.getReturnType().hashCode());
		return result;
	}

	/**
	 * * @hibernate.property column="FIRST_NAME" length="100"
	 * 
	 */

	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * @spring.validator type="required,maxlength"
	 * @spring.validator-args arg0resource="shipping.firstName"
	 * @spring.validator-var name="maxlength" value="100"
	 * @spring.validator-args arg1value="100"
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * * @hibernate.property column="LAST_NAME" length="100"
	 * 
	 */

	public String getLastName() {
		return this.lastName;
	}

	/**
	 * @spring.validator type="required,maxlength"
	 * @spring.validator-args arg0resource="shipping.lastName"
	 * @spring.validator-var name="maxlength" value="100"
	 * @spring.validator-args arg1value="100"
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * * @hibernate.property column="PROVINCE" length="20"
	 * 
	 */

	public String getProvince() {
		return this.province;
	}

	/**
	 * @spring.validator type="required,maxlength"
	 * @spring.validator-args arg0resource="shipping.province"
	 * @spring.validator-var name="maxlength" value="20"
	 * @spring.validator-args arg1value="20"
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * * @hibernate.property column="CITY" length="20"
	 * 
	 */

	public String getCity() {
		return this.city;
	}

	/**
	 * @spring.validator type="required,maxlength"
	 * @spring.validator-args arg0resource="shipping.city"
	 * @spring.validator-var name="maxlength" value="20"
	 * @spring.validator-args arg1value="20"
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * * @hibernate.property column="ADDRESS" length="500"
	 * 
	 */

	public String getAddress() {
		return this.address;
	}

	/**
	 * @spring.validator type="required,maxlength"
	 * @spring.validator-args arg0resource="shipping.address"
	 * @spring.validator-var name="maxlength" value="500"
	 * @spring.validator-args arg1value="500"
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * * @hibernate.property column="POSTALCODE" length="10"
	 * 
	 */

	public String getPostalcode() {
		return this.postalcode;
	}

	/**
	 * @spring.validator type="required,maxlength"
	 * @spring.validator-args arg0resource="shipping.postalcode"
	 * @spring.validator-var name="maxlength" value="20"
	 * @spring.validator-args arg1value="20"
	 */
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	/**
	 * * @hibernate.property column="PHONE" length="30"
	 *  not-null="true"
	 * 
	 */

	public String getPhone() {
		return this.phone;
	}

	///**
	 //* @spring.validator type="maxlength"
	// * @spring.validator-var name="maxlength" value="20"
	// * @spring.validator-args arg1value="20"
	// */
	/**
	 * @spring.validator type="required"
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * * @hibernate.property column="EMAIL" length="30"
	 * 
	 */

	public String getEmail() {
		return this.email;
	}

	/**
	 * @spring.validator type="email,maxlength"
	 * @spring.validator-var name="maxlength" value="100"
	 * @spring.validator-args arg1value="100"
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * * @hibernate.property column="MOBILETELE" length="20"
	 * 
	 */

	public String getMobiletele() {
		return this.mobiletele;
	}

	/**
	 * @spring.validator type="maxlength"
	 * @spring.validator-var name="maxlength" value="20"
	 * @spring.validator-args arg1value="20"
	 */
	public void setMobiletele(String mobiletele) {
		this.mobiletele = mobiletele;
	}

	/**
	 * * @hibernate.property column="DISTRICT" length="20"
	 * 
	 */
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

}
