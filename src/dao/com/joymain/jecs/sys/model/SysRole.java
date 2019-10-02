package com.joymain.jecs.sys.model;

import java.util.HashSet;
import java.util.Set;
// Generated 2008-7-30 10:50:13 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JSYS_ROLE"
 *     
 */

public class SysRole extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long roleId;
    private String roleName;
    private String roleDes;
    private String companyCode;
    private Integer available;
    private String userType;
    private String roleCode;

    Set sysUsers = new HashSet();
    // Constructors

    /** default constructor */
    public SysRole() {
    }

	/** minimal constructor */
    public SysRole(String roleName, String companyCode, Integer available, String userType) {
        this.roleName = roleName;
        this.companyCode = companyCode;
        this.available = available;
        this.userType = userType;
    }
    
    /** full constructor */
    public SysRole(String roleName, String roleDes, String companyCode, Integer available, String userType) {
        this.roleName = roleName;
        this.roleDes = roleDes;
        this.companyCode = companyCode;
        this.available = available;
        this.userType = userType;
    }
    

   
    // Property accessors
    /**
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ROLE_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_SYS"
     */
    public Long getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    /**       
     *      *            @hibernate.property
     *             column="ROLE_NAME"
     *             length="100"
     *             not-null="true"
     *         
     */

    public String getRoleName() {
        return this.roleName;
    }
    
    /**
	 * @spring.validator type="required"
	 */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    /**       
     *      *            @hibernate.property
     *             column="ROLE_DES"
     *             length="100"
     *         
     */

    public String getRoleDes() {
        return this.roleDes;
    }
    
    public void setRoleDes(String roleDes) {
        this.roleDes = roleDes;
    }
    /**       
     *      *            @hibernate.property
     *             column="COMPANY_CODE"
     *             length="2"
     *             not-null="true"
     *         
     */

    public String getCompanyCode() {
        return this.companyCode;
    }
    
    /**
	 * @spring.validator type="required"
	 */
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="AVAILABLE"
     *             length="2"
     *             not-null="true"
     *         
     */

    public Integer getAvailable() {
        return this.available;
    }
    
    public void setAvailable(Integer available) {
        this.available = available;
    }
    /**       
     *      *            @hibernate.property
     *             column="USER_TYPE"
     *             length="2"
     *             not-null="true"
     *         
     */
    public String getUserType() {
        return this.userType;
    }
    
    /**
	 * @spring.validator type="required"
	 */
    public void setUserType(String userType) {
        this.userType = userType;
    }
   
    /**       
     *      *            @hibernate.property
     *             column="ROLE_CODE"
     *             length="30"
     *             not-null="true"
     *         
     */
    public String getRoleCode() {
    	return roleCode;
    }

    /**
	 * @spring.validator type="required"
	 */
	public void setRoleCode(String roleCode) {
    	this.roleCode = roleCode;
    }

	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("roleName").append("='").append(getRoleName()).append("' ");			
      buffer.append("roleDes").append("='").append(getRoleDes()).append("' ");			
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("available").append("='").append(getAvailable()).append("' ");			
      buffer.append("userType").append("='").append(getUserType()).append("' ");
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SysRole) ) return false;
		 SysRole castOther = ( SysRole ) other; 
         
		 return ( (this.getRoleId()==castOther.getRoleId()) || ( this.getRoleId()!=null && castOther.getRoleId()!=null && this.getRoleId().equals(castOther.getRoleId()) ) )
 && ( (this.getRoleName()==castOther.getRoleName()) || ( this.getRoleName()!=null && castOther.getRoleName()!=null && this.getRoleName().equals(castOther.getRoleName()) ) )
 && ( (this.getRoleDes()==castOther.getRoleDes()) || ( this.getRoleDes()!=null && castOther.getRoleDes()!=null && this.getRoleDes().equals(castOther.getRoleDes()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getAvailable()==castOther.getAvailable()) || ( this.getAvailable()!=null && castOther.getAvailable()!=null && this.getAvailable().equals(castOther.getAvailable()) ) )
 && ( (this.getUserType()==castOther.getUserType()) || ( this.getUserType()!=null && castOther.getUserType()!=null && this.getUserType().equals(castOther.getUserType()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getRoleId() == null ? 0 : this.getRoleId().hashCode() );
         result = 37 * result + ( getRoleName() == null ? 0 : this.getRoleName().hashCode() );
         result = 37 * result + ( getRoleDes() == null ? 0 : this.getRoleDes().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getAvailable() == null ? 0 : this.getAvailable().hashCode() );
         result = 37 * result + ( getUserType() == null ? 0 : this.getUserType().hashCode() );
         return result;
   }

   /**
    * @hibernate.set table="JSYS_USER_ROLE" cascade="save-update" lazy="true"
    * @hibernate.collection-key column="ROLE_ID"
    * @hibernate.collection-many-to-many class="com.joymain.jecs.sys.model.SysUser" column="USER_CODE"
    */
public Set getSysUsers() {
	return sysUsers;
}

public void setSysUsers(Set sysUsers) {
	this.sysUsers = sysUsers;
}   
}