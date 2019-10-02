package com.joymain.jecs.sys.model;

// Generated 2008-3-8 15:17:37 by Hibernate Tools 3.1.0.beta4

import com.joymain.jecs.util.tree.TreeNode;

/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="JSYS_ROLE_USER"
 * 
 */

public class SysRoleUser extends com.joymain.jecs.model.BaseObject implements java.io.Serializable, TreeNode {

	// Fields

	private Long ruId;

	private Long roleId;

	private String loginCode;

	// Constructors

	/** default constructor */
	public SysRoleUser() {
	}

	public SysRoleUser(Long roleId, String loginCode) {
		this.roleId = roleId;
		this.loginCode = loginCode;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	// Property accessors
	/**
	 * *
	 * 
	 * @hibernate.id generator-class="sequence" type="java.lang.Long"
	 *               column="RU_ID"
	 * @hibernate.generator-param name="sequesce" value="SEQ_SYS"
	 * 
	 */

	public Long getRuId() {
		return this.ruId;
	}

	public void setRuId(Long ruId) {
		this.ruId = ruId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SysRoleUser))
			return false;
		SysRoleUser castOther = (SysRoleUser) other;

		return ((this.getRuId() == castOther.getRuId()) || (this.getRuId() != null && castOther.getRuId() != null && this.getRuId().equals(
				castOther.getRuId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getRuId() == null ? 0 : this.getRuId().hashCode());
		return result;
	}

	/**
	 * *
	 * 
	 * @hibernate.property column="ROLE_ID" length="22" not-null="true"
	 * 
	 */
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * *
	 * 
	 * @hibernate.property column="LOGIN_CODE" length="20" not-null="true"
	 * 
	 */
	public String getLoginCode() {
		return loginCode;
	}

	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	public Object getEntity() {

		return this;
	}

	public Object getEntityName() {

		return "null";
	}

	public Object getKey() {

		return roleId;
	}

	public Object getParentKey() {

		return loginCode;
	}

}
