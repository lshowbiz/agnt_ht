package com.joymain.jecs.sys.model;

// Generated 2008-7-30 11:10:33 by Hibernate Tools 3.1.0.beta4


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="JSYS_ROLE_POWER"
 * 
 */

public class SysRolePower extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {

	// Fields    

	private Long rpId;
	private Long roleId;
	private Long moduleId;

	// Constructors

	/** default constructor */
	public SysRolePower() {
	}

	// Property accessors
	/**
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="RP_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_SYS"
     */
	public Long getRpId() {
		return this.rpId;
	}

	public void setRpId(Long rpId) {
		this.rpId = rpId;
	}

	/**       
     *      *            @hibernate.property
     *             column="ROLE_ID"
     *             length="22"
     *             not-null="true"
     *         
     */
	public Long getRoleId() {
    	return roleId;
    }

	public void setRoleId(Long roleId) {
    	this.roleId = roleId;
    }

	/**       
     *      *            @hibernate.property
     *             column="MODULE_ID"
     *             length="22"
     *             not-null="true"
     *         
     */
	public Long getModuleId() {
    	return moduleId;
    }

	public void setModuleId(Long moduleId) {
    	this.moduleId = moduleId;
    }

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SysRolePower))
			return false;
		SysRolePower castOther = (SysRolePower) other;

		return ((this.getRpId() == castOther.getRpId()) || (this.getRpId() != null && castOther.getRpId() != null && this.getRpId().equals(castOther.getRpId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getRpId() == null ? 0 : this.getRpId().hashCode());
		result = 37 * result + (getRoleId() == null ? 0 : this.getRoleId().hashCode());
		result = 37 * result + (getModuleId() == null ? 0 : this.getModuleId().hashCode());
		return result;
	}

	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("roleId").append("='").append(getRoleId()).append("' ");			
      buffer.append("moduleId").append("='").append(getModuleId()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }

}
