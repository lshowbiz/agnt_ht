package com.joymain.jecs.pd.model;
// Generated 2009-9-24 14:24:23 by Hibernate Tools 3.1.0.beta4

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="PD_FLIT_WAREHOUSE"
 *     
 */

public class PdFlitWarehouse extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


	// Fields    

	private String fwNo;
	private String createUsrCode;
	private Date createTime;
	private String remark;
	private String checkUsrCode;
	private Date checkTime;
	private String checkRemark;
	private Date okTime;
	private String okUsrCode;
	private String okRemark;
	private Date toTime;
	private String toUsrCode;
	private String toRemark;
	private String editUsrCode;
	private Date editTime;
	private Integer orderFlag;
	private String stockFlag="0";

	private Set<PdFlitWarehouseDetail> pdFlitWarehouseDetails = new HashSet();

	private PdWarehouse outWarehouse = new PdWarehouse();
	private PdWarehouse inWarehouse = new PdWarehouse();

	private String outCompanyCode;
	private String inCompanyCode;

	private String trackingNo;

	private byte[] file;

	//Add By WuCF 20130514
	private String recipientName;
	private String recipientZip;
	private String recipientCaNo;
	private String city;
	private String recipientAddr;
	private String recipientPhone;
	private String recipientMobile;

	public void setFile(byte[] file) {
		this.file = file;
	}

	public byte[] getFile() {
		return file;
	}

	/**       
	 *      *            @hibernate.property
	 *             column="OUT_COMPANY_CODE"
	 *             length="20"
	 *            
	 *         
	 */
	public String getOutCompanyCode() {
		return outCompanyCode;
	}

	public void setOutCompanyCode(String outCompanyCode) {
		this.outCompanyCode = outCompanyCode;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="IN_COMPANY_CODE"
	 *             length="20"
	 *             
	 *         
	 */
	public String getInCompanyCode() {
		return inCompanyCode;
	}

	public void setInCompanyCode(String inCompanyCode) {
		this.inCompanyCode = inCompanyCode;
	}
	/**
	 * * @hibernate.property column="TRACKING_NO" length="200"
	 * 
	 */
	public String getTrackingNo() {
		return trackingNo;
	}

	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}

	// Constructors
	/**
	 * *
	 * @hibernate.many-to-one not-null="true"  cascade="none" inverse="true"
	 * @hibernate.column name="OUT_WAREHOUSE_NO"
	 * 
	 */
	public PdWarehouse getOutWarehouse() {
		return outWarehouse;
	}

	public void setOutWarehouse(PdWarehouse outWarehouse) {
		this.outWarehouse = outWarehouse;
	}
	/**
	 * *
	 * @hibernate.many-to-one not-null="true"  inverse="true" cascade="none"
	 * @hibernate.column name="IN_WAREHOUSE_NO"
	 * 
	 */
	public PdWarehouse getInWarehouse() {
		return inWarehouse;
	}

	public void setInWarehouse(PdWarehouse inWarehouse) {
		this.inWarehouse = inWarehouse;
	}

	/**
	 * *
	 * 
	 * @hibernate.set lazy="true" inverse="true" cascade="all-delete-orphan"
	 *                order-by="product_No"
	 * @hibernate.collection-key column="FW_NO"
	 * @hibernate.collection-one-to-many 
	 * class="com.joymain.jecs.pd.model.PdFlitWarehouseDetail"
	 * 
	 */
	public Set<PdFlitWarehouseDetail> getPdFlitWarehouseDetails() {
		return pdFlitWarehouseDetails;
	}

	public void setPdFlitWarehouseDetails(
			Set<PdFlitWarehouseDetail> pdFlitWarehouseDetails) {
		this.pdFlitWarehouseDetails = pdFlitWarehouseDetails;
	}

	/** default constructor */
	public PdFlitWarehouse() {
	}

	/** minimal constructor */
	public PdFlitWarehouse(String createUsrCode, Date createTime) {
		this.createUsrCode = createUsrCode;
		this.createTime = createTime;
	}

	/** full constructor */
	public PdFlitWarehouse(String createUsrCode, Date createTime, String remark, String checkUsrCode, Date checkTime, String checkRemark, Date okTime, String okUsrCode, String okRemark, Date toTime, String toUsrCode, String toRemark, String editUNo, Date editTime, Integer orderFlag, String stockFlag) {
		this.createUsrCode = createUsrCode;
		this.createTime = createTime;
		this.remark = remark;
		this.checkUsrCode = checkUsrCode;
		this.checkTime = checkTime;
		this.checkRemark = checkRemark;
		this.okTime = okTime;
		this.okUsrCode = okUsrCode;
		this.okRemark = okRemark;
		this.toTime = toTime;
		this.toUsrCode = toUsrCode;
		this.toRemark = toRemark;
		this.editUsrCode = editUNo;
		this.editTime = editTime;
		this.orderFlag = orderFlag;
		this.stockFlag = stockFlag;
	}



	// Property accessors
	/**       
	 *      *            @hibernate.id
	 *             generator-class="assigned"
	 *             type="java.lang.String"
	 *             column="FW_NO"
	 *         
	 */

	public String getFwNo() {
		return this.fwNo;
	}

	public void setFwNo(String fwNo) {
		this.fwNo = fwNo;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="CREATE_USR_CODE"
	 *             length="20"
	 *             not-null="true"
	 *         
	 */

	public String getCreateUsrCode() {
		return this.createUsrCode;
	}

	public void setCreateUsrCode(String createUsrCode) {
		this.createUsrCode = createUsrCode;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="CREATE_TIME"
	 *             length="7"
	 *             not-null="true"
	 *         
	 */

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="REMARK"
	 *             length="500"
	 *         
	 */

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="CHECK_USR_CODE"
	 *             length="20"
	 *         
	 */

	public String getCheckUsrCode() {
		return this.checkUsrCode;
	}

	public void setCheckUsrCode(String checkUsrCode) {
		this.checkUsrCode = checkUsrCode;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="CHECK_TIME"
	 *             length="7"
	 *         
	 */

	public Date getCheckTime() {
		return this.checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="CHECK_REMARK"
	 *             length="500"
	 *         
	 */

	public String getCheckRemark() {
		return this.checkRemark;
	}

	public void setCheckRemark(String checkRemark) {
		this.checkRemark = checkRemark;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="OK_TIME"
	 *             length="7"
	 *         
	 */

	public Date getOkTime() {
		return this.okTime;
	}

	public void setOkTime(Date okTime) {
		this.okTime = okTime;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="OK_USR_CODE"
	 *             length="20"
	 *         
	 */

	public String getOkUsrCode() {
		return this.okUsrCode;
	}

	public void setOkUsrCode(String okUsrCode) {
		this.okUsrCode = okUsrCode;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="OK_REMARK"
	 *             length="500"
	 *         
	 */

	public String getOkRemark() {
		return this.okRemark;
	}

	public void setOkRemark(String okRemark) {
		this.okRemark = okRemark;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="TO_TIME"
	 *             length="7"
	 *         
	 */

	public Date getToTime() {
		return this.toTime;
	}

	public void setToTime(Date toTime) {
		this.toTime = toTime;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="TO_USR_CODE"
	 *             length="20"
	 *         
	 */

	public String getToUsrCode() {
		return this.toUsrCode;
	}

	public void setToUsrCode(String toUsrCode) {
		this.toUsrCode = toUsrCode;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="TO_REMARK"
	 *             length="500"
	 *         
	 */

	public String getToRemark() {
		return this.toRemark;
	}

	public void setToRemark(String toRemark) {
		this.toRemark = toRemark;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="EDIT_USR_CODE"
	 *             length="20"
	 *         
	 */
	public String getEditUsrCode() {
		return editUsrCode;
	}

	public void setEditUsrCode(String editUsrCode) {
		this.editUsrCode = editUsrCode;
	}

	/**       
	 *      *            @hibernate.property
	 *             column="EDIT_TIME"
	 *             length="7"
	 *         
	 */

	public Date getEditTime() {
		return this.editTime;
	}



	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="ORDER_FLAG"
	 *             length="2"
	 *         
	 */

	public Integer getOrderFlag() {
		return this.orderFlag;
	}

	public void setOrderFlag(Integer orderFlag) {
		this.orderFlag = orderFlag;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="STOCK_FLAG"
	 *             length="1"
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
	 * @return String
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();

		buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
		buffer.append("createUsrCode").append("='").append(getCreateUsrCode()).append("' ");			
		buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
		buffer.append("remark").append("='").append(getRemark()).append("' ");			
		buffer.append("checkUsrCode").append("='").append(getCheckUsrCode()).append("' ");			
		buffer.append("checkTime").append("='").append(getCheckTime()).append("' ");			
		buffer.append("checkRemark").append("='").append(getCheckRemark()).append("' ");			
		buffer.append("okTime").append("='").append(getOkTime()).append("' ");			
		buffer.append("okUsrCode").append("='").append(getOkUsrCode()).append("' ");			
		buffer.append("okRemark").append("='").append(getOkRemark()).append("' ");			
		buffer.append("toTime").append("='").append(getToTime()).append("' ");			
		buffer.append("toUsrCode").append("='").append(getToUsrCode()).append("' ");			
		buffer.append("toRemark").append("='").append(getToRemark()).append("' ");			
		buffer.append("editUNo").append("='").append(getEditUsrCode()).append("' ");			
		buffer.append("editTime").append("='").append(getEditTime()).append("' ");			
		buffer.append("orderFlag").append("='").append(getOrderFlag()).append("' ");			
		buffer.append("stockFlag").append("='").append(getStockFlag()).append("' ");			
		buffer.append("]");

		return buffer.toString();
	}


	public boolean equals(Object other) {
		if ( (this == other ) ) return true;
		if ( (other == null ) ) return false;
		if ( !(other instanceof PdFlitWarehouse) ) return false;
		PdFlitWarehouse castOther = ( PdFlitWarehouse ) other; 

		return ( (this.getFwNo()==castOther.getFwNo()) || ( this.getFwNo()!=null && castOther.getFwNo()!=null && this.getFwNo().equals(castOther.getFwNo()) ) )
		&& ( (this.getCreateUsrCode()==castOther.getCreateUsrCode()) || ( this.getCreateUsrCode()!=null && castOther.getCreateUsrCode()!=null && this.getCreateUsrCode().equals(castOther.getCreateUsrCode()) ) )
		&& ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
		&& ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
		&& ( (this.getCheckUsrCode()==castOther.getCheckUsrCode()) || ( this.getCheckUsrCode()!=null && castOther.getCheckUsrCode()!=null && this.getCheckUsrCode().equals(castOther.getCheckUsrCode()) ) )
		&& ( (this.getCheckTime()==castOther.getCheckTime()) || ( this.getCheckTime()!=null && castOther.getCheckTime()!=null && this.getCheckTime().equals(castOther.getCheckTime()) ) )
		&& ( (this.getCheckRemark()==castOther.getCheckRemark()) || ( this.getCheckRemark()!=null && castOther.getCheckRemark()!=null && this.getCheckRemark().equals(castOther.getCheckRemark()) ) )
		&& ( (this.getOkTime()==castOther.getOkTime()) || ( this.getOkTime()!=null && castOther.getOkTime()!=null && this.getOkTime().equals(castOther.getOkTime()) ) )
		&& ( (this.getOkUsrCode()==castOther.getOkUsrCode()) || ( this.getOkUsrCode()!=null && castOther.getOkUsrCode()!=null && this.getOkUsrCode().equals(castOther.getOkUsrCode()) ) )
		&& ( (this.getOkRemark()==castOther.getOkRemark()) || ( this.getOkRemark()!=null && castOther.getOkRemark()!=null && this.getOkRemark().equals(castOther.getOkRemark()) ) )
		&& ( (this.getToTime()==castOther.getToTime()) || ( this.getToTime()!=null && castOther.getToTime()!=null && this.getToTime().equals(castOther.getToTime()) ) )
		&& ( (this.getToUsrCode()==castOther.getToUsrCode()) || ( this.getToUsrCode()!=null && castOther.getToUsrCode()!=null && this.getToUsrCode().equals(castOther.getToUsrCode()) ) )
		&& ( (this.getToRemark()==castOther.getToRemark()) || ( this.getToRemark()!=null && castOther.getToRemark()!=null && this.getToRemark().equals(castOther.getToRemark()) ) )
		&& ( (this.getEditUsrCode()==castOther.getEditUsrCode()) || ( this.getEditUsrCode()!=null && castOther.getEditUsrCode()!=null && this.getEditUsrCode().equals(castOther.getEditUsrCode()) ) )
		&& ( (this.getEditTime()==castOther.getEditTime()) || ( this.getEditTime()!=null && castOther.getEditTime()!=null && this.getEditTime().equals(castOther.getEditTime()) ) )
		&& ( (this.getOrderFlag()==castOther.getOrderFlag()) || ( this.getOrderFlag()!=null && castOther.getOrderFlag()!=null && this.getOrderFlag().equals(castOther.getOrderFlag()) ) )
		&& ( (this.getStockFlag()==castOther.getStockFlag()) || ( this.getStockFlag()!=null && castOther.getStockFlag()!=null && this.getStockFlag().equals(castOther.getStockFlag()) ) );
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + ( getFwNo() == null ? 0 : this.getFwNo().hashCode() );
		result = 37 * result + ( getCreateUsrCode() == null ? 0 : this.getCreateUsrCode().hashCode() );
		result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
		result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
		result = 37 * result + ( getCheckUsrCode() == null ? 0 : this.getCheckUsrCode().hashCode() );
		result = 37 * result + ( getCheckTime() == null ? 0 : this.getCheckTime().hashCode() );
		result = 37 * result + ( getCheckRemark() == null ? 0 : this.getCheckRemark().hashCode() );
		result = 37 * result + ( getOkTime() == null ? 0 : this.getOkTime().hashCode() );
		result = 37 * result + ( getOkUsrCode() == null ? 0 : this.getOkUsrCode().hashCode() );
		result = 37 * result + ( getOkRemark() == null ? 0 : this.getOkRemark().hashCode() );
		result = 37 * result + ( getToTime() == null ? 0 : this.getToTime().hashCode() );
		result = 37 * result + ( getToUsrCode() == null ? 0 : this.getToUsrCode().hashCode() );
		result = 37 * result + ( getToRemark() == null ? 0 : this.getToRemark().hashCode() );
		result = 37 * result + ( getEditUsrCode() == null ? 0 : this.getEditUsrCode().hashCode() );
		result = 37 * result + ( getEditTime() == null ? 0 : this.getEditTime().hashCode() );
		result = 37 * result + ( getOrderFlag() == null ? 0 : this.getOrderFlag().hashCode() );
		result = 37 * result + ( getStockFlag() == null ? 0 : this.getStockFlag().hashCode() );
		return result;
	}
	
	/**
	 * * @hibernate.property column="RECIPIENT_NAME" length="20"
	 * 
	 */
	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}
	
	/**
	 * * @hibernate.property column="RECIPIENT_ZIP" length="10"
	 * 
	 */
	public String getRecipientZip() {
		return recipientZip;
	}

	public void setRecipientZip(String recipientZip) {
		this.recipientZip = recipientZip;
	}

	/**
	 * * @hibernate.property column="RECIPIENT_CA_NO" length="10"
	 *
	 * 
	 */
	public String getRecipientCaNo() {
		return recipientCaNo;
	}

	public void setRecipientCaNo(String recipientCaNo) {
		this.recipientCaNo = recipientCaNo;
	}

	/**
	 * * @hibernate.property column="CITY" length="200"
	 * 
	 */
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * * @hibernate.property column="RECIPIENT_ADDR" length="100"
	 *
	 * 
	 */
	public String getRecipientAddr() {
		return recipientAddr;
	}

	public void setRecipientAddr(String recipientAddr) {
		this.recipientAddr = recipientAddr;
	}

	/**
	 * * @hibernate.property column="RECIPIENT_PHONE" length="254"
	 *
	 * 
	 */
	public String getRecipientPhone() {
		return recipientPhone;
	}

	public void setRecipientPhone(String recipientPhone) {
		this.recipientPhone = recipientPhone;
	}

	/**
	 * * @hibernate.property column="RECIPIENT_MOBILE"  
	 * 
	 */
	public String getRecipientMobile() {
		return recipientMobile;
	}

	public void setRecipientMobile(String recipientMobile) {
		this.recipientMobile = recipientMobile;
	}   
}
