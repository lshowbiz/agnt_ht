package com.joymain.jecs.sys.model;

import com.joymain.jecs.util.tree.TreeNode;

// Generated 2008-3-8 15:17:34 by Hibernate Tools 3.1.0.beta4

/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="JSYS_MENU"
 * 
 */

public class SysMenu extends com.joymain.jecs.model.BaseObject implements java.io.Serializable, TreeNode {

	// Fields

	private Long menuId;

	private Long parentId;

	private String menuName;

	private String menuDes;

	private String leaf;

	private String type;

	private String address;

	private String title;

	private Long menuOrder;

	private String status;

	private String remark;

	private String isA;

	private String isC;

	private String isQ;

	private String isM;

	// Constructors

	/** default constructor */
	public SysMenu() {
	}

	/** minimal constructor */
	public SysMenu(Long parentId, String menuName, String menuDes, String leaf, String type, String title, String status, String companyCode) {
		this.parentId = parentId;
		this.menuName = menuName;
		this.menuDes = menuDes;
		this.leaf = leaf;
		this.type = type;
		this.title = title;
		this.status = status;
	}

	/** full constructor */
	public SysMenu(Long parentId, String menuName, String menuDes, String leaf, String type, String address, String title, Long morder,
			String status, String remark, String companyCode) {
		this.parentId = parentId;
		this.menuName = menuName;
		this.menuDes = menuDes;
		this.leaf = leaf;
		this.type = type;
		this.address = address;
		this.title = title;
		this.menuOrder = morder;
		this.status = status;
		this.remark = remark;
	}

	// Property accessors
	/**
	 * *
	 * 
	 * @hibernate.id generator-class="sequence" type="java.lang.Long"
	 *               column="MENU_ID"
	 * @hibernate.generator-param name="sequence" value="SEQ_SYS"
	 */

	public Long getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	/**
	 * *
	 * 
	 * @hibernate.property column="PARENT_ID" length="22" not-null="true"
	 * 
	 */

	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * *
	 * 
	 * @hibernate.property column="MENU_NAME" length="100" not-null="true"
	 * 
	 */

	public String getMenuName() {
		return this.menuName;
	}

	/**
	 * *
	 * 
	 * @spring.validator type="required,maxlength"
	 * @spring.validator-var name="maxlength" value="50"
	 * @spring.validator-args arg1value="50"
	 * @param menuName
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	/**
	 * *
	 * 
	 * @hibernate.property column="MENU_DES" length="100"
	 * 
	 */

	public String getMenuDes() {
		return this.menuDes;
	}

	/**
	 * *
	 * 
	 * @spring.validator type="required,maxlength"
	 * @spring.validator-var name="maxlength" value="50"
	 * @spring.validator-args arg1value="50"
	 * @param menuDes
	 */
	public void setMenuDes(String menuDes) {
		this.menuDes = menuDes;
	}

	/**
	 * *
	 * 
	 * @hibernate.property column="LEAF" length="1" not-null="true"
	 * 
	 */

	public String getLeaf() {
		return this.leaf;
	}

	/**
	 * *
	 * 
	 * @spring.validator type="required"
	 * @param leaf
	 */
	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}

	/**
	 * *
	 * 
	 * @hibernate.property column="TYPE" length="10" not-null="true"
	 * 
	 */

	public String getType() {
		return this.type;
	}

	/**
	 * *
	 * 
	 * @spring.validator type="required"
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * *
	 * 
	 * @hibernate.property column="ADDRESS" length="100"
	 * 
	 */

	public String getAddress() {
		return this.address;
	}

	/**
	 * *
	 * 
	 * @spring.validator type="maxlength"
	 * @spring.validator-var name="maxlength" value="100"
	 * @spring.validator-args arg1value="100"
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * *
	 * 
	 * @hibernate.property column="TITLE" length="100"
	 * 
	 */

	public String getTitle() {
		return this.title;
	}

	/**
	 * *
	 * 
	 * @spring.validator type="required,maxlength"
	 * @spring.validator-var name="maxlength" value="50"
	 * @spring.validator-args arg1value="50"
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * *
	 * 
	 * @hibernate.property column="MENU_ORDER" length="22"
	 * 
	 */

	public Long getMenuOrder() {
		return this.menuOrder;
	}

	/**
	 * *
	 * 
	 * @spring.validator type="required,maxlength"
	 * @spring.validator-var name="maxlength" value="3"
	 * @spring.validator-args arg1value="3"
	 */
	public void setMenuOrder(Long mOrder) {
		this.menuOrder = mOrder;
	}

	/**
	 * *
	 * 
	 * @hibernate.property column="STATUS" length="1" not-null="true"
	 * 
	 */

	public String getStatus() {
		return this.status;
	}

	/**
	 * *
	 * 
	 * @spring.validator type="required"
	 * @spring.validator-args arg0resource="sys.common.status"
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * *
	 * 
	 * @hibernate.property column="REMARK" length="60"
	 * 
	 */

	public String getRemark() {
		return this.remark;
	}

	/**
	 * *
	 * 
	 * @spring.validator type="maxlength"
	 * @spring.validator-var name="maxlength" value="100"
	 * @spring.validator-args arg0resource="sys.common.remark"
	 * @spring.validator-args arg1value="100"
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * toString
	 * 
	 * @return String
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();

		buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
		buffer.append("parentId").append("='").append(getParentId()).append("' ");
		buffer.append("menuName").append("='").append(getMenuName()).append("' ");
		buffer.append("menuDes").append("='").append(getMenuDes()).append("' ");
		buffer.append("leaf").append("='").append(getLeaf()).append("' ");
		buffer.append("type").append("='").append(getType()).append("' ");
		buffer.append("address").append("='").append(getAddress()).append("' ");
		buffer.append("title").append("='").append(getTitle()).append("' ");
		buffer.append("morder").append("='").append(getMenuOrder()).append("' ");
		buffer.append("valid").append("='").append(getStatus()).append("' ");
		buffer.append("remark").append("='").append(getRemark()).append("' ");
		buffer.append("]");

		return buffer.toString();
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SysMenu))
			return false;
		SysMenu castOther = (SysMenu) other;

		return ((this.getMenuId() == castOther.getMenuId()) || (this.getMenuId() != null && castOther.getMenuId() != null && this.getMenuId()
				.equals(castOther.getMenuId())))
				&& ((this.getParentId() == castOther.getParentId()) || (this.getParentId() != null && castOther.getParentId() != null && this
						.getParentId().equals(castOther.getParentId())))
				&& ((this.getMenuName() == castOther.getMenuName()) || (this.getMenuName() != null && castOther.getMenuName() != null && this
						.getMenuName().equals(castOther.getMenuName())))
				&& ((this.getMenuDes() == castOther.getMenuDes()) || (this.getMenuDes() != null && castOther.getMenuDes() != null && this
						.getMenuDes().equals(castOther.getMenuDes())))
				&& ((this.getLeaf() == castOther.getLeaf()) || (this.getLeaf() != null && castOther.getLeaf() != null && this.getLeaf().equals(
						castOther.getLeaf())))
				&& ((this.getType() == castOther.getType()) || (this.getType() != null && castOther.getType() != null && this.getType().equals(
						castOther.getType())))
				&& ((this.getAddress() == castOther.getAddress()) || (this.getAddress() != null && castOther.getAddress() != null && this
						.getAddress().equals(castOther.getAddress())))
				&& ((this.getTitle() == castOther.getTitle()) || (this.getTitle() != null && castOther.getTitle() != null && this.getTitle()
						.equals(castOther.getTitle())))
				&& ((this.getMenuOrder() == castOther.getMenuOrder()) || (this.getMenuOrder() != null && castOther.getMenuOrder() != null && this
						.getMenuOrder().equals(castOther.getMenuOrder())))
				&& ((this.getStatus() == castOther.getStatus()) || (this.getStatus() != null && castOther.getStatus() != null && this.getStatus()
						.equals(castOther.getStatus())))
				&& ((this.getRemark() == castOther.getRemark()) || (this.getRemark() != null && castOther.getRemark() != null && this.getRemark()
						.equals(castOther.getRemark())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getMenuId() == null ? 0 : this.getMenuId().hashCode());
		result = 37 * result + (getParentId() == null ? 0 : this.getParentId().hashCode());
		result = 37 * result + (getMenuName() == null ? 0 : this.getMenuName().hashCode());
		result = 37 * result + (getMenuDes() == null ? 0 : this.getMenuDes().hashCode());
		result = 37 * result + (getLeaf() == null ? 0 : this.getLeaf().hashCode());
		result = 37 * result + (getType() == null ? 0 : this.getType().hashCode());
		result = 37 * result + (getAddress() == null ? 0 : this.getAddress().hashCode());
		result = 37 * result + (getTitle() == null ? 0 : this.getTitle().hashCode());
		result = 37 * result + (getMenuOrder() == null ? 0 : this.getMenuOrder().hashCode());
		result = 37 * result + (getStatus() == null ? 0 : this.getStatus().hashCode());
		result = 37 * result + (getRemark() == null ? 0 : this.getRemark().hashCode());
		return result;
	}

	public Object getEntity() {

		return this;
	}

	public Object getEntityName() {

		return menuName;
	}

	public Object getKey() {

		return menuId;
	}

	public Object getParentKey() {

		return parentId;
	}

	/**
	 * @hibernate.property column="is_a"
	 * @return
	 */
	public String getIsA() {
		return isA;
	}

	public void setIsA(String isA) {
		this.isA = isA;
	}

	/**
	 * @hibernate.property column="is_c"
	 * @return
	 */
	public String getIsC() {
		return isC;
	}

	public void setIsC(String isC) {
		this.isC = isC;
	}

	/**
	 * @hibernate.property column="is_m"
	 * @return
	 */
	public String getIsM() {
		return isM;
	}

	public void setIsM(String isM) {
		this.isM = isM;
	}

	/**
	 * @hibernate.property column="is_q"
	 * @return
	 */
	public String getIsQ() {
		return isQ;
	}

	public void setIsQ(String isQ) {
		this.isQ = isQ;
	}
}
