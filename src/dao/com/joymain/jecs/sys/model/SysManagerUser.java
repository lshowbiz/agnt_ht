package com.joymain.jecs.sys.model;
// Generated 2008-7-30 10:44:51 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JSYS_MANAGER_USER"
 *     
 */

public class SysManagerUser extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long rollId;
    private String masterUserCode;
    private String slaveUserCode;

    // Constructors

    /** default constructor */
    public SysManagerUser() {
    }

    
    /** full constructor */
    public SysManagerUser(String masterUserCode, String slaveUserCode) {
        this.masterUserCode = masterUserCode;
        this.slaveUserCode = slaveUserCode;
    }
    

   
    // Property accessors
    /**
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ROLL_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_SYS"
     */
    public Long getRollId() {
        return this.rollId;
    }
    
    public void setRollId(Long rollId) {
        this.rollId = rollId;
    }
    /**       
     *      *            @hibernate.property
     *             column="MASTER_USER_CODE"
     *             length="30"
     *             not-null="true"
     *         
     */
    
    public String getMasterUserCode() {
        return this.masterUserCode;
    }
    
    public void setMasterUserCode(String masterUserCode) {
        this.masterUserCode = masterUserCode;
    }
    /**       
     *@hibernate.property
     *             column="SLAVE_USER_CODE"
     *             length="30"
     *             not-null="true"
     *         
     */

    public String getSlaveUserCode() {
        return this.slaveUserCode;
    }
    
    public void setSlaveUserCode(String slaveUserCode) {
        this.slaveUserCode = slaveUserCode;
    }

	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("masterUserCode").append("='").append(getMasterUserCode()).append("' ");			
      buffer.append("slaveUserCode").append("='").append(getSlaveUserCode()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SysManagerUser) ) return false;
		 SysManagerUser castOther = ( SysManagerUser ) other; 
         
		 return ( (this.getRollId()==castOther.getRollId()) || ( this.getRollId()!=null && castOther.getRollId()!=null && this.getRollId().equals(castOther.getRollId()) ) )
 && ( (this.getMasterUserCode()==castOther.getMasterUserCode()) || ( this.getMasterUserCode()!=null && castOther.getMasterUserCode()!=null && this.getMasterUserCode().equals(castOther.getMasterUserCode()) ) )
 && ( (this.getSlaveUserCode()==castOther.getSlaveUserCode()) || ( this.getSlaveUserCode()!=null && castOther.getSlaveUserCode()!=null && this.getSlaveUserCode().equals(castOther.getSlaveUserCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getRollId() == null ? 0 : this.getRollId().hashCode() );
         result = 37 * result + ( getMasterUserCode() == null ? 0 : this.getMasterUserCode().hashCode() );
         result = 37 * result + ( getSlaveUserCode() == null ? 0 : this.getSlaveUserCode().hashCode() );
         return result;
   }   





}
