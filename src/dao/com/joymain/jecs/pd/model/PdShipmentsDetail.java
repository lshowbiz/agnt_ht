package com.joymain.jecs.pd.model;

// Generated 2009-9-21 11:37:55 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;

import com.joymain.jecs.sys.model.SysUser;

/**
 * @struts.form include-all="true" extends="BaseForm"
  * @hibernate.class
 *         table="PD_SHIPMENTS_DETAIL"
 *       	dynamic-update="true"
 *		dynamic-insert="true"
 *		optimistic-lock="version"
 */ 

public class PdShipmentsDetail extends com.joymain.jecs.model.BaseObject
		implements java.io.Serializable {

	// Fields

	private Long sdId;
	private String companyCode;

	private SysUser customer=new SysUser();//订单归属人
	private SysUser consignee=new SysUser();//收获人
	
	

	// private String userCode;
	private String orderNo;
	private String orderType;
	private String productNo;
	private BigDecimal price;
	private Long quantity;
	private Long remainQuantity;
	private Date createTime;
	private String stateCode;
	private Long version=new Long(0);
	// Constructors

	
	/**       
	 *      *            @hibernate.version
	 *             column="VERSION"
	 *             length="5"
	 *         
	 */
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	/** default constructor */
	public PdShipmentsDetail() {
	}

	/** full constructor */
	public PdShipmentsDetail(String companyCode, String userCode,
			String orderNo, String orderType, String productNo,
			BigDecimal price, Long quantity, Long remainQuantity,
			Date createTime) {
		this.companyCode = companyCode;
		// this.userCode = userCode;
		this.orderNo = orderNo;
		this.orderType = orderType;
		this.productNo = productNo;
		this.price = price;
		this.quantity = quantity;
		this.remainQuantity = remainQuantity;
		this.createTime = createTime;
	}

	
	/**
	 * *
	 * 
	 * @hibernate.many-to-one not-null="true"
	 * @hibernate.column name="CONSIGNEE_CODE"
	 * 
	 */
	public SysUser getConsignee() {
		return consignee;
	}

	public void setConsignee(SysUser consignee) {
		this.consignee = consignee;
	}
	/**
	 * *
	 * 
	 * @hibernate.many-to-one not-null="true"
	 * @hibernate.column name="CUSTOM_CODE"
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
	 * * @hibernate.id generator-class="sequence" type="java.lang.Long"
	 * column="SD_ID"
	 * @hibernate.generator-param name="sequence" value="SEQ_PD" 
	 */

	public Long getSdId() {
		return this.sdId;
	}

	public void setSdId(Long sdId) {
		this.sdId = sdId;
	}

	
	/**
	 * * @hibernate.property column="STATE_CODE" length="20"
	 * 
	 */
	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
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
	 * * @hibernate.property column="ORDER_NO" length="20"
	 * 
	 */

	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * * @hibernate.property column="ORDER_TYPE" length="5"
	 * 
	 */

	public String getOrderType() {
		return this.orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
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
	 * * @hibernate.property column="PRICE" length="18"
	 * 
	 */

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * * @hibernate.property column="QUANTITY" length="8"
	 * 
	 */

	public Long getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	/**
	 * * @hibernate.property column="REMAIN_QUANTITY" length="8"
	 * 
	 */

	public Long getRemainQuantity() {
		return this.remainQuantity;
	}

	public void setRemainQuantity(Long remainQuantity) {
		this.remainQuantity = remainQuantity;
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
		// buffer.append("userCode").append("='").append(getUserCode()).append("' ");
		buffer.append("orderNo").append("='").append(getOrderNo()).append("' ");
		buffer.append("orderType").append("='").append(getOrderType()).append(
				"' ");
		buffer.append("productNo").append("='").append(getProductNo()).append(
				"' ");
		buffer.append("price").append("='").append(getPrice()).append("' ");
		buffer.append("quantity").append("='").append(getQuantity()).append(
				"' ");
		buffer.append("remainQuantity").append("='")
				.append(getRemainQuantity()).append("' ");
		buffer.append("createTime").append("='").append(getCreateTime())
				.append("' ");
		buffer.append("]");

		return buffer.toString();
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PdShipmentsDetail))
			return false;
		PdShipmentsDetail castOther = (PdShipmentsDetail) other;

		return ((this.getSdId() == castOther.getSdId()) || (this.getSdId() != null
				&& castOther.getSdId() != null && this.getSdId().equals(
				castOther.getSdId())))
				&& ((this.getCompanyCode() == castOther.getCompanyCode()) || (this
						.getCompanyCode() != null
						&& castOther.getCompanyCode() != null && this
						.getCompanyCode().equals(castOther.getCompanyCode())))
				// && ( (this.getUserCode()==castOther.getUserCode()) || (
				// this.getUserCode()!=null && castOther.getUserCode()!=null &&
				// this.getUserCode().equals(castOther.getUserCode()) ) )
				&& ((this.getOrderNo() == castOther.getOrderNo()) || (this
						.getOrderNo() != null
						&& castOther.getOrderNo() != null && this.getOrderNo()
						.equals(castOther.getOrderNo())))
				&& ((this.getOrderType() == castOther.getOrderType()) || (this
						.getOrderType() != null
						&& castOther.getOrderType() != null && this
						.getOrderType().equals(castOther.getOrderType())))
				&& ((this.getProductNo() == castOther.getProductNo()) || (this
						.getProductNo() != null
						&& castOther.getProductNo() != null && this
						.getProductNo().equals(castOther.getProductNo())))
				&& ((this.getPrice() == castOther.getPrice()) || (this
						.getPrice() != null
						&& castOther.getPrice() != null && this.getPrice()
						.equals(castOther.getPrice())))
				&& ((this.getQuantity() == castOther.getQuantity()) || (this
						.getQuantity() != null
						&& castOther.getQuantity() != null && this
						.getQuantity().equals(castOther.getQuantity())))
				&& ((this.getRemainQuantity() == castOther.getRemainQuantity()) || (this
						.getRemainQuantity() != null
						&& castOther.getRemainQuantity() != null && this
						.getRemainQuantity().equals(
								castOther.getRemainQuantity())))
				&& ((this.getCreateTime() == castOther.getCreateTime()) || (this
						.getCreateTime() != null
						&& castOther.getCreateTime() != null && this
						.getCreateTime().equals(castOther.getCreateTime())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getSdId() == null ? 0 : this.getSdId().hashCode());
		result = 37
				* result
				+ (getCompanyCode() == null ? 0 : this.getCompanyCode()
						.hashCode());
		// result = 37 * result + ( getUserCode() == null ? 0 :
		// this.getUserCode().hashCode() );
		result = 37 * result
				+ (getOrderNo() == null ? 0 : this.getOrderNo().hashCode());
		result = 37 * result
				+ (getOrderType() == null ? 0 : this.getOrderType().hashCode());
		result = 37 * result
				+ (getProductNo() == null ? 0 : this.getProductNo().hashCode());
		result = 37 * result
				+ (getPrice() == null ? 0 : this.getPrice().hashCode());
		result = 37 * result
				+ (getQuantity() == null ? 0 : this.getQuantity().hashCode());
		result = 37
				* result
				+ (getRemainQuantity() == null ? 0 : this.getRemainQuantity()
						.hashCode());
		result = 37
				* result
				+ (getCreateTime() == null ? 0 : this.getCreateTime()
						.hashCode());
		return result;
	}

}
