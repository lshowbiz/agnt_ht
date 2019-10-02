package com.joymain.jecs.fi.model;
// Generated 2013-11-4 15:15:28 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;

import com.joymain.jecs.mi.model.JmiMember;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="FOUNDATION_ORDER"
 *     
 */

public class FoundationOrder extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


	// Fields    

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long orderId;
	//private String userCode;
	private JmiMember jmiMember;
//	private String fiId;
	private String payType;
	private long fiNum;
	private Date createTime;
	private String status;
	private String field1;
	private String field2;//附言
	private BigDecimal amount;
	private Date endTime;
    
	private Date startTime;//支付时间

	private FoundationItem foundationItem;
	
	// Constructors

	/** default constructor */
	public FoundationOrder() {
	}

	/** minimal constructor */
	public FoundationOrder(String userCode, String fiId, String payType, long fiNum, Date createTime, String status, BigDecimal amount, Date endTime) {
		//this.userCode = userCode;
		this.payType = payType;
		this.fiNum = fiNum;
		this.createTime = createTime;
		this.status = status;
		this.amount = amount;
		this.endTime = endTime;
	}

	/** full constructor */
	public FoundationOrder(String userCode, String fiId, String payType, long fiNum, Date createTime, String status, String field1, String field2, BigDecimal amount, Date endTime) {
		//this.userCode = userCode;
		this.payType = payType;
		this.fiNum = fiNum;
		this.createTime = createTime;
		this.status = status;
		this.field1 = field1;
		this.field2 = field2;
		this.amount = amount;
		this.endTime = endTime;
	}



	// Property accessors
	/**       
	 *      *            @hibernate.id
	 *             generator-class="sequence"
	 *             type="java.lang.Long"
	 *             column="ORDER_ID"
	 *         @hibernate.generator-param name="sequence" value="SEQ_FI" 
	 */
	public Long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
  /**
    * *
    * @hibernate.many-to-one not-null="true"
    * @hibernate.column name="USER_CODE"
    * 
    */
	public JmiMember getJmiMember() {
		return jmiMember;
	}

	public void setJmiMember(JmiMember jmiMember) {
		this.jmiMember = jmiMember;
	}

	/**       
	 *      *            @hibernate.property
	 *             column="PAY_TYPE"
	 *             length="1"
	 *         
	 */

	public String getPayType() {
		return this.payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="FI_NUM"
	 *             length="12"
	 *         
	 */

	public long getFiNum() {
		return this.fiNum;
	}

	public void setFiNum(long fiNum) {
		this.fiNum = fiNum;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="CREATE_TIME"
	 *             length="7"
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
	 *             column="STATUS"
	 *             length="1"
	 *         
	 */

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="FIELD1"
	 *             length="50"
	 *         
	 */

	public String getField1() {
		return this.field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="FIELD2"
	 *             length="200"
	 *         
	 */

	public String getField2() {
		return this.field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="AMOUNT"
	 *             length="12"
	 *         
	 */

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="END_TIME"
	 *             length="7"
	 *         
	 */

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	/**
	 * toString
	 * @return String
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();

		buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
	//	buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
		buffer.append("payType").append("='").append(getPayType()).append("' ");			
		buffer.append("fiNum").append("='").append(getFiNum()).append("' ");			
		buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
		buffer.append("status").append("='").append(getStatus()).append("' ");			
		buffer.append("field1").append("='").append(getField1()).append("' ");			
		buffer.append("field2").append("='").append(getField2()).append("' ");			
		buffer.append("amount").append("='").append(getAmount()).append("' ");			
		buffer.append("endTime").append("='").append(getEndTime()).append("' ");			
		buffer.append("]");

		return buffer.toString();
	}


	public boolean equals(Object other) {
		if ( (this == other ) ) return true;
		if ( (other == null ) ) return false;
		if ( !(other instanceof FoundationOrder) ) return false;
		FoundationOrder castOther = ( FoundationOrder ) other; 

		return ( (this.getOrderId()==castOther.getOrderId()) || ( this.getOrderId()!=null && castOther.getOrderId()!=null && this.getOrderId().equals(castOther.getOrderId()) ) )
		//&& ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
		&& ( (this.getPayType()==castOther.getPayType()) || ( this.getPayType()!=null && castOther.getPayType()!=null && this.getPayType().equals(castOther.getPayType()) ) )
		&& (this.getFiNum()==castOther.getFiNum())
		&& ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
		&& ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
		&& ( (this.getField1()==castOther.getField1()) || ( this.getField1()!=null && castOther.getField1()!=null && this.getField1().equals(castOther.getField1()) ) )
		&& ( (this.getField2()==castOther.getField2()) || ( this.getField2()!=null && castOther.getField2()!=null && this.getField2().equals(castOther.getField2()) ) )
		&& (this.getAmount()==castOther.getAmount())
		&& ( (this.getEndTime()==castOther.getEndTime()) || ( this.getEndTime()!=null && castOther.getEndTime()!=null && this.getEndTime().equals(castOther.getEndTime()) ) );
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + ( getOrderId() == null ? 0 : this.getOrderId().hashCode() );
	//	result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
		result = 37 * result + ( getPayType() == null ? 0 : this.getPayType().hashCode() );
		result = 37 * result + (int) this.getFiNum();
		result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
		result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
		result = 37 * result + ( getField1() == null ? 0 : this.getField1().hashCode() );
		result = 37 * result + ( getField2() == null ? 0 : this.getField2().hashCode() );
		//result = 37 * result + (int) this.getAmount();
		result = 37 * result + ( getEndTime() == null ? 0 : this.getEndTime().hashCode() );
		return result;
	}

	/**       
	 * @hibernate.property
	 *  column="START_TIME"
	 *  length="7"
	 *         
	 */
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * *
	 * @hibernate.many-to-one not-null="true" inverse="true"  
	 * @hibernate.column name="FI_ID"
	 * 
	 */	
	public FoundationItem getFoundationItem() {
		return foundationItem;
	}

	public void setFoundationItem(FoundationItem foundationItem) {
		this.foundationItem = foundationItem;
	}
}
