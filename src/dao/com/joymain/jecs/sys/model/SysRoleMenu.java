package com.joymain.jecs.sys.model;

import com.joymain.jecs.util.tree.TreeNode;

// Generated 2008-3-8 15:17:37 by Hibernate Tools 3.1.0.beta4

/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="JSYS_ROLE_MENU"
 * 
 */

public class SysRoleMenu extends com.joymain.jecs.model.BaseObject implements java.io.Serializable, TreeNode {

	// Fields

	private Long rmId;

	private Long roleId;

	private Long menuId;

	public SysRoleMenu(Long roleId, Long menuId) {
		this.roleId = roleId;
		this.menuId = menuId;
	}

	// Constructors
	/**
	 * *
	 * 
	 * @hibernate.id generator-class="sequence" type="java.lang.Long"
	 *               column="RM_ID"
	 * @hibernate.generator-param name="sequence" value="SEQ_SYS"
	 * 
	 */

	public Long getRmId() {
		return this.rmId;
	}

	public void setRmId(Long rmId) {
		this.rmId = rmId;
	}

	/**
	 * @hibernate.property column="MENU_ID" length="22" not_null="true"
	 */
	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	/**
	 * @hibernate.property column="ROLE_ID" length="22" not_null="true"
	 * 
	 */
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/** default constructor */
	public SysRoleMenu() {
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	// Property accessors

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SysRoleMenu))
			return false;
		SysRoleMenu castOther = (SysRoleMenu) other;

		return ((this.getRmId() == castOther.getRmId()) || (this.getRmId() != null && castOther.getRmId() != null && this.getRmId().equals(
				castOther.getRmId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getRmId() == null ? 0 : this.getRmId().hashCode());
		return result;
	}

	public Object getEntity() {

		return this;
	}

	public Object getEntityName() {

		return "null";
	}

	public Object getKey() {

		return menuId;
	}

	public Object getParentKey() {

		return roleId;
	}
}
