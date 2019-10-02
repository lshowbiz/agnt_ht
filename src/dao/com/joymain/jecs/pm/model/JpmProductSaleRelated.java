package com.joymain.jecs.pm.model;
// Generated 2013-6-3 18:04:21 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JPM_PRODUCT_SALE_RELATED"
 *     
 */

public class JpmProductSaleRelated extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


	// Fields    

	private Long id;
	private Long uniNo;
	private Long relationUniNo;
	private String status;

	private JpmProductSaleNew jpmProductSaleNew = new JpmProductSaleNew();
	private JpmProductSaleNew relationJpmProductSaleNew = new JpmProductSaleNew();
	// Constructors

	/** default constructor */
	public JpmProductSaleRelated() {
	}


	/** full constructor */
	public JpmProductSaleRelated(Long uniNo, Long relationUniNo, String status) {
		this.uniNo = uniNo;
		this.relationUniNo = relationUniNo;
		this.status = status;
	}



	// Property accessors
	/**       
	 *      *            @hibernate.id
	 *             generator-class="sequence"
	 *             type="java.lang.Long"
	 *             column="ID"
	 *         @hibernate.generator-param name="sequence" value="SEQ_PM" 
	 */
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**       
	 *      *            @hibernate.property
	 *             column="UNI_NO"
	 *             length="10"
	 *         
	 */

	public Long getUniNo() {
		return this.uniNo;
	}

	
	public void setUniNo(Long uniNo) {
		this.uniNo = uniNo;
	}

	/**       
	 *      *            @hibernate.property
	 *             column="RELATION_UNI_NO"
	 *             length="10"
	 *         
	 */

	public Long getRelationUniNo() {
		return this.relationUniNo;
	}

	
	public void setRelationUniNo(Long relationUniNo) {
		this.relationUniNo = relationUniNo;
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

	/**
	 * @spring.validator type="required"
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * toString
	 * @return String
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();

		buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
		buffer.append("uniNo").append("='").append(getUniNo()).append("' ");			
		buffer.append("relationUniNo").append("='").append(getRelationUniNo()).append("' ");			
		buffer.append("status").append("='").append(getStatus()).append("' ");			
		buffer.append("]");

		return buffer.toString();
	}


	public boolean equals(Object other) {
		if ( (this == other ) ) return true;
		if ( (other == null ) ) return false;
		if ( !(other instanceof JpmProductSaleRelated) ) return false;
		JpmProductSaleRelated castOther = ( JpmProductSaleRelated ) other; 

		return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
		&& ( (this.getUniNo()==castOther.getUniNo()) || ( this.getUniNo()!=null && castOther.getUniNo()!=null && this.getUniNo().equals(castOther.getUniNo()) ) )
		&& ( (this.getRelationUniNo()==castOther.getRelationUniNo()) || ( this.getRelationUniNo()!=null && castOther.getRelationUniNo()!=null && this.getRelationUniNo().equals(castOther.getRelationUniNo()) ) )
		&& ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) );
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
		result = 37 * result + ( getUniNo() == null ? 0 : this.getUniNo().hashCode() );
		result = 37 * result + ( getRelationUniNo() == null ? 0 : this.getRelationUniNo().hashCode() );
		result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
		return result;
	}

	/**
	 * *
	 * @hibernate.many-to-one not-null="true" inverse="true" insert="false" update="false" 
	 * @hibernate.column name="UNI_NO"
	 * 
	 */
	public JpmProductSaleNew getJpmProductSaleNew() {
		return jpmProductSaleNew;
	}

	
	public void setJpmProductSaleNew(JpmProductSaleNew jpmProductSaleNew) {
		this.jpmProductSaleNew = jpmProductSaleNew;
	}

	/**
	 * *
	 * @hibernate.many-to-one not-null="true" inverse="true" insert="false" update="false"
	 * @hibernate.column name="RELATION_UNI_NO"
	 * 
	 */
	public JpmProductSaleNew getRelationJpmProductSaleNew() {
		return relationJpmProductSaleNew;
	}

	
	public void setRelationJpmProductSaleNew(
			JpmProductSaleNew relationJpmProductSaleNew) {
		this.relationJpmProductSaleNew = relationJpmProductSaleNew;
	}   
}
