package com.joymain.jecs.pm.model;
// Generated 2013-6-3 16:15:47 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JMI_MEMBER_TEAM"
 *     
 */

public class JmiMemberTeam extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


	// Fields    

	private String code;
	private String name;
	private String status;
	private String isBuy;//是否事业锦囊
	private String fullName;//团队全名
	private String isPromotions;//是否促销

	// Constructors

	/** default constructor */
	public JmiMemberTeam() {
	}


	/** full constructor */
	public JmiMemberTeam(String name, String status) {
		this.name = name;
		this.status = status;
	}



	// Property accessors
	/**       
	 *      *            @hibernate.id
	 *             generator-class="assigned"
	 *             type="java.lang.String"
	 *             column="CODE"
	 *         
	 */

	public String getCode() {
		return this.code;
	}

	/**
	 * @spring.validator type="required"
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="NAME"
	 *             length="200"
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
		buffer.append("name").append("='").append(getName()).append("' ");			
		buffer.append("status").append("='").append(getStatus()).append("' ");			
		buffer.append("]");

		return buffer.toString();
	}


	public boolean equals(Object other) {
		if ( (this == other ) ) return true;
		if ( (other == null ) ) return false;
		if ( !(other instanceof JmiMemberTeam) ) return false;
		JmiMemberTeam castOther = ( JmiMemberTeam ) other; 

		return ( (this.getCode()==castOther.getCode()) || ( this.getCode()!=null && castOther.getCode()!=null && this.getCode().equals(castOther.getCode()) ) )
		&& ( (this.getName()==castOther.getName()) || ( this.getName()!=null && castOther.getName()!=null && this.getName().equals(castOther.getName()) ) )
		&& ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) );
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + ( getCode() == null ? 0 : this.getCode().hashCode() );
		result = 37 * result + ( getName() == null ? 0 : this.getName().hashCode() );
		result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
		return result;
	}

	/**       
	 *      *            @hibernate.property
	 *             column="IS_BUY"
	 *             length="1"
	 *         
	 */
	public String getIsBuy() {
		return isBuy;
	}

	 
	public void setIsBuy(String isBuy) {
		this.isBuy = isBuy;
	}

	/**       
	 *    @hibernate.property
	 *        column="FULL_NAME"
	 *             length="200"
	 *         
	 */
	public String getFullName() {
		return fullName;
	}

 
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**       
	 *      *            @hibernate.property
	 *             column="IS_PROMOTIONS"
	 *             length="1"
	 *         
	 */
	public String getIsPromotions() {
		return isPromotions;
	}


	public void setIsPromotions(String isPromotions) {
		this.isPromotions = isPromotions;
	}   
}
