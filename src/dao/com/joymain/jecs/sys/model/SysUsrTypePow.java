package com.joymain.jecs.sys.model;
// Generated 2008-7-29 15:45:19 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JSYS_USR_TYPE_POW"
 *     
 */

public class SysUsrTypePow extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private SysModule sysModule;
    private String userType;


    // Constructors

    /** default constructor */
    public SysUsrTypePow() {
    }

	/** minimal constructor */
    public SysUsrTypePow(String userType) {
        this.userType = userType;
    }
    // Property accessors
    /**
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ID"
     *@hibernate.generator-param name="sequence" value="SEQ_SYS"
     */
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    /**
	 * *
	 * @hibernate.many-to-one
	 * @hibernate.column name="MODULE_ID" not-null="false"
	 * 
	 */
    public SysModule getSysModule() {
    	return sysModule;
    }

	public void setSysModule(SysModule sysModule) {
    	this.sysModule = sysModule;
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
    
    public void setUserType(String userType) {
        this.userType = userType;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("userType").append("='").append(getUserType()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SysUsrTypePow) ) return false;
		 SysUsrTypePow castOther = ( SysUsrTypePow ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getUserType()==castOther.getUserType()) || ( this.getUserType()!=null && castOther.getUserType()!=null && this.getUserType().equals(castOther.getUserType()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getUserType() == null ? 0 : this.getUserType().hashCode() );
         return result;
   }   





}
