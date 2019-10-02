package com.joymain.jecs.sys.model;
// Generated 2008-7-29 15:46:49 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JSYS_MANAGER_MODL_POW"
 *     
 */

public class SysManagerModlPow extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


	
    // Fields    

    private Long powerId;
    private String userCode;
    private Long moduleId;
    private String companyCode;

    // Constructors

    /** default constructor */
    public SysManagerModlPow() {
    }

    
    /** full constructor */
    public SysManagerModlPow(Long powerId) {
    	this.powerId=powerId;
    }
    

   
    // Property accessors
    /**
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="POWER_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_SYS"
     */
    public Long getPowerId() {
        return this.powerId;
    }
    
    public void setPowerId(Long powerId) {
        this.powerId = powerId;
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

	/**       
     *      *            @hibernate.property
     *             column="COMPANY_CODE"
     *             length="2"
     *             not-null="true"
     *         
     */
	public String getCompanyCode() {
    	return companyCode;
    }

	public void setCompanyCode(String companyCode) {
    	this.companyCode = companyCode;
    }


	public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SysManagerModlPow) ) return false;
		 SysManagerModlPow castOther = ( SysManagerModlPow ) other; 
         
		 return ( (this.getPowerId()==castOther.getPowerId()) || ( this.getPowerId()!=null && castOther.getPowerId()!=null && this.getPowerId().equals(castOther.getPowerId()) ) ) 
		 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
		 && ( (this.getModuleId()==castOther.getModuleId()) || ( this.getModuleId()!=null && castOther.getModuleId()!=null && this.getModuleId().equals(castOther.getModuleId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getPowerId() == null ? 0 : this.getPowerId().hashCode() );
         result = 37 * result + ( getModuleId() == null ? 0 : this.getModuleId().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         return result;
   }



	public String toString() {
		StringBuffer buffer = new StringBuffer();

	      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
	      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
	      buffer.append("moduleId").append("='").append(getModuleId()).append("' ");			
	      buffer.append("]");
	      
	      return buffer.toString();
	}   





}
