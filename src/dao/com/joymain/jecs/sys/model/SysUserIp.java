package com.joymain.jecs.sys.model;
// Generated 2008-12-11 14:19:27 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JSYS_USER_IP"
 *     
 */

public class SysUserIp extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long ipId;
    private String userCode;
    private String ipAddress;


    // Constructors

    /** default constructor */
    public SysUserIp() {
    }

    
    /** full constructor */
    public SysUserIp(String userCode, String ipAddress) {
        this.userCode = userCode;
        this.ipAddress = ipAddress;
    }
    

   
    // Property accessors
    /**
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="IP_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_SYS"
     */
    public Long getIpId() {
        return this.ipId;
    }
    
    public void setIpId(Long ipId) {
        this.ipId = ipId;
    }
    /**       
     *      *            @hibernate.property
     *             column="USER_CODE"
     *             length="20"
     *             not-null="true"
     *         
     */

    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="IP_ADDRESS"
     *             length="20"
     *             not-null="true"
     *         
     */

    public String getIpAddress() {
        return this.ipAddress;
    }
    
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("ipAddress").append("='").append(getIpAddress()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SysUserIp) ) return false;
		 SysUserIp castOther = ( SysUserIp ) other; 
         
		 return ( (this.getIpId()==castOther.getIpId()) || ( this.getIpId()!=null && castOther.getIpId()!=null && this.getIpId().equals(castOther.getIpId()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getIpAddress()==castOther.getIpAddress()) || ( this.getIpAddress()!=null && castOther.getIpAddress()!=null && this.getIpAddress().equals(castOther.getIpAddress()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getIpId() == null ? 0 : this.getIpId().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getIpAddress() == null ? 0 : this.getIpAddress().hashCode() );
         return result;
   }   





}
