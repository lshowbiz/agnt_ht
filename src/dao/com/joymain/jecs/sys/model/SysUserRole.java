package com.joymain.jecs.sys.model;
// Generated 2008-7-30 11:02:46 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JSYS_USER_ROLE"
 *     
 */

public class SysUserRole extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long ruId;
    private Long roleId;
    private String userCode;

    // Constructors

    /** default constructor */
    public SysUserRole() {
    }
   
    // Property accessors
    /**
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="RU_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_SYS"
     */
    public Long getRuId() {
        return this.ruId;
    }
    
    public void setRuId(Long ruId) {
        this.ruId = ruId;
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
     *             column="USER_CODE"
     *             length="30"
     *             not-null="true"
     *         
     */
   public String getUserCode() {
    	return userCode;
    }

	public void setUserCode(String userCode) {
    	this.userCode = userCode;
    }

	public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SysUserRole) ) return false;
		 SysUserRole castOther = ( SysUserRole ) other; 
         
		 return ( (this.getRuId()==castOther.getRuId()) || ( this.getRuId()!=null && castOther.getRuId()!=null && this.getRuId().equals(castOther.getRuId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getRuId() == null ? 0 : this.getRuId().hashCode() );
         result = 37 * result + (getRoleId() == null ? 0 : this.getRoleId().hashCode());
 		result = 37 * result + (getUserCode() == null ? 0 : this.getUserCode().hashCode());
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
     buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
     buffer.append("]");
     
     return buffer.toString();
    }
}