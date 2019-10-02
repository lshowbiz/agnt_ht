package com.joymain.jecs.sys.model;

import com.joymain.jecs.util.tree.TreeBranch;

// Generated 2008-3-8 15:17:37 by Hibernate Tools 3.1.0.beta4

/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="JSYS_POWER_CATEGORY"
 * 
 */

public class SysPowerCategory extends TreeBranch implements java.io.Serializable {

	// Fields

	private Long pcId;

	private Long parentId;

	private String categoryName;

	private String companyCode;

	// Constructors

	/** default constructor */
	public SysPowerCategory() {
	}

	/** minimal constructor */
	public SysPowerCategory(String categoryName, String companyCode) {
		this.categoryName = categoryName;
		this.companyCode = companyCode;
	}

	/** full constructor */
	public SysPowerCategory(Long parentId, String categoryName, String companyCode) {
		this.parentId = parentId;
		this.categoryName = categoryName;
		this.companyCode = companyCode;
	}

	/**
	 * *
	 * 
	 * @hibernate.id generator-class="sequence" type="java.lang.Long"
	 *               column="PC_ID"
	 * @hibernate.generator-param name="sequence" value="SEQ_SYS"
	 */

	public Long getPcId() {
		return this.pcId;
	}

	public void setPcId(Long pcId) {
		this.pcId = pcId;
	}

	/**
	 * @hibernate.property column="PARENT_ID" not-null="true"
	 */
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * *
	 * 
	 * @hibernate.property column="CATEGORY_NAME" length="100" not-null="true"
	 * 
	 */

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * *
	 * 
	 * @hibernate.property column="COMPANY_CODE" length="2"
	 * 
	 */

	public String getCompanyCode() {
		return this.companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	/**
	 * toString
	 * 
	 * @return String
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();

		buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
		buffer.append("categoryName").append("='").append(getCategoryName()).append("' ");
		buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");
		buffer.append("]");

		return buffer.toString();
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SysPowerCategory))
			return false;
		SysPowerCategory castOther = (SysPowerCategory) other;

		return ((this.getPcId() == castOther.getPcId()) || (this.getPcId() != null && castOther.getPcId() != null && this.getPcId().equals(
				castOther.getPcId())))
				&& ((this.getCategoryName() == castOther.getCategoryName()) || (this.getCategoryName() != null
						&& castOther.getCategoryName() != null && this.getCategoryName().equals(castOther.getCategoryName())))
				&& ((this.getCompanyCode() == castOther.getCompanyCode()) || (this.getCompanyCode() != null
						&& castOther.getCompanyCode() != null && this.getCompanyCode().equals(castOther.getCompanyCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getPcId() == null ? 0 : this.getPcId().hashCode());
		result = 37 * result + (getCategoryName() == null ? 0 : this.getCategoryName().hashCode());
		result = 37 * result + (getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode());
		return result;
	}

	public Object getEntity() {

		return this;
	}

	public Object getEntityName() {

		return categoryName;
	}

	public Object getKey() {

		return pcId;
	}

	public Object getParentKey() {

		return parentId;
	}
}
