package com.joymain.jecs.fi.model;

import java.math.BigDecimal;

// Generated 2013-11-4 15:30:25 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="FOUNDATION_ITEM"
 *     
 */

public class FoundationItem extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


	// Fields    

	private Long fiId;
	private String name;//项目名称
	private BigDecimal amount;//单价
	private String status;//状态：0 启用 ；1：禁用
	private String remark;//备注
	private String field1;//图片链接
	private String field2;//排序字段
	private String unit;//单位：元/所，元/个，元/份  对应参数列表中添加
	private String prompt;//提示
	private String type;//项目类型 1：爱心365,2：爱心积分认购活动


	// Constructors

	/** default constructor */
	public FoundationItem() {
	}

	/** minimal constructor */
	public FoundationItem(String name, BigDecimal amount, String status, String unit) {
		this.name = name;
		this.amount = amount;
		this.status = status;
		this.unit = unit;
	}

	/** full constructor */
	public FoundationItem(String name, BigDecimal amount, String status, String remark, String field1, String field2, String unit) {
		this.name = name;
		this.amount = amount;
		this.status = status;
		this.remark = remark;
		this.field1 = field1;
		this.field2 = field2;
		this.unit = unit;
	}



	// Property accessors
	/**       
	 *      *            @hibernate.id
	 *             generator-class="sequence"
	 *             type="java.lang.Long"
	 *             column="FI_ID"
	 *         @hibernate.generator-param name="sequence" value="SEQ_FI" 
	 */
	public Long getFiId() {
		return this.fiId;
	}


	public void setFiId(Long fiId) {
		this.fiId = fiId;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="NAME"
	 *             length="50"
	 *             not-null="true"
	 *         
	 */

	public String getName() {
		return this.name;
	}

	/**
	 * @spring.validator type="required"
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="AMOUNT"
	 *             length="10"
	 *             not-null="true"
	 *         
	 */

	public BigDecimal getAmount() {
		return this.amount;
	}

	/**
	 * @spring.validator type="required"
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**       
	 *      *            @hibernate.property
	 *             column="STATUS"
	 *             length="1"
	 *             not-null="true"
	 *         
	 */

	public String getStatus() {
		return this.status;
	}

	/**
	 * @spring.validator type="required"
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="REMARK"
	 *             length="300"
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
	 *             length="50"
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
	 *             column="UNIT"
	 *             length="20"
	 *             not-null="true"
	 *         
	 */

	public String getUnit() {
		return this.unit;
	}

	/**
	 * @spring.validator type="required"
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}


	/**
	 * toString
	 * @return String
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();

		buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
		buffer.append("name").append("='").append(getName()).append("' ");			
		buffer.append("amount").append("='").append(getAmount()).append("' ");			
		buffer.append("status").append("='").append(getStatus()).append("' ");			
		buffer.append("remark").append("='").append(getRemark()).append("' ");			
		buffer.append("field1").append("='").append(getField1()).append("' ");			
		buffer.append("field2").append("='").append(getField2()).append("' ");			
		buffer.append("unit").append("='").append(getUnit()).append("' ");			
		buffer.append("]");

		return buffer.toString();
	}


	public boolean equals(Object other) {
		if ( (this == other ) ) return true;
		if ( (other == null ) ) return false;
		if ( !(other instanceof FoundationItem) ) return false;
		FoundationItem castOther = ( FoundationItem ) other; 

		return ( (this.getFiId()==castOther.getFiId()) || ( this.getFiId()!=null && castOther.getFiId()!=null && this.getFiId().equals(castOther.getFiId()) ) )
		&& ( (this.getName()==castOther.getName()) || ( this.getName()!=null && castOther.getName()!=null && this.getName().equals(castOther.getName()) ) )
		&& ( (this.getAmount()==castOther.getAmount()) || ( this.getAmount()!=null && castOther.getAmount()!=null && this.getAmount().equals(castOther.getAmount()) ) )
		&& ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
		&& ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
		&& ( (this.getField1()==castOther.getField1()) || ( this.getField1()!=null && castOther.getField1()!=null && this.getField1().equals(castOther.getField1()) ) )
		&& ( (this.getField2()==castOther.getField2()) || ( this.getField2()!=null && castOther.getField2()!=null && this.getField2().equals(castOther.getField2()) ) )
		&& ( (this.getUnit()==castOther.getUnit()) || ( this.getUnit()!=null && castOther.getUnit()!=null && this.getUnit().equals(castOther.getUnit()) ) );
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + ( getFiId() == null ? 0 : this.getFiId().hashCode() );
		result = 37 * result + ( getName() == null ? 0 : this.getName().hashCode() );
		result = 37 * result + ( getAmount() == null ? 0 : this.getAmount().hashCode() );
		result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
		result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
		result = 37 * result + ( getField1() == null ? 0 : this.getField1().hashCode() );
		result = 37 * result + ( getField2() == null ? 0 : this.getField2().hashCode() );
		result = 37 * result + ( getUnit() == null ? 0 : this.getUnit().hashCode() );
		return result;
	}

	/**       
	 *  @hibernate.property
	 *   column="PROMPT"
	 *   length="200"
	 *         
	 */
	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	/**       
	 *  @hibernate.property
	 *   column="TYPE"
	 *   length="2"
	 *   not-null="true"
	 */
	public String getType() {
		return type;
	}

	/**
	 * @spring.validator type="required"
	 */
	public void setType(String type) {
		this.type = type;
	}   
}
