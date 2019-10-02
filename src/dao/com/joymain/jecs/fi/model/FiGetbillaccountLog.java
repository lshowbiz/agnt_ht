package com.joymain.jecs.fi.model;
// Generated 2014-3-21 15:33:27 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="FI_GETBILLACCOUNT_LOG"
 *     
 */

public class FiGetbillaccountLog extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long logId;
    private String userCode;
    private String accountCode;
    private String status;
    private Date createTime;
    private String logDes;


    // Constructors

    /** default constructor */
    public FiGetbillaccountLog() {
    }

	/** minimal constructor */
    public FiGetbillaccountLog(String userCode) {
        this.userCode = userCode;
    }
    
    /** full constructor */
    public FiGetbillaccountLog(String userCode, String accountCode, String status, Date createTime, String logDes) {
        this.userCode = userCode;
        this.accountCode = accountCode;
        this.status = status;
        this.createTime = createTime;
        this.logDes = logDes;
    }
    

   
    // Property accessors    
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="LOG_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_FI"
     *         
     */

    public Long getLogId() {
        return this.logId;
    }
    
    public void setLogId(Long logId) {
        this.logId = logId;
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
     *             column="ACCOUNT_CODE"
     *             length="50"
     *         
     */

    public String getAccountCode() {
        return this.accountCode;
    }
    
    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="STATUS"
     *             length="1"
     *         
     */

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    /**       
     *      *            @hibernate.property
     *             column="CREATE_TIME"
     *             length="7"
     *         
     */

    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="LOG_DES"
     *             length="200"
     *         
     */

    public String getLogDes() {
        return this.logDes;
    }
    
    public void setLogDes(String logDes) {
        this.logDes = logDes;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("accountCode").append("='").append(getAccountCode()).append("' ");			
      buffer.append("status").append("='").append(getStatus()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("logDes").append("='").append(getLogDes()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof FiGetbillaccountLog) ) return false;
		 FiGetbillaccountLog castOther = ( FiGetbillaccountLog ) other; 
         
		 return ( (this.getLogId()==castOther.getLogId()) || ( this.getLogId()!=null && castOther.getLogId()!=null && this.getLogId().equals(castOther.getLogId()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getAccountCode()==castOther.getAccountCode()) || ( this.getAccountCode()!=null && castOther.getAccountCode()!=null && this.getAccountCode().equals(castOther.getAccountCode()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getLogDes()==castOther.getLogDes()) || ( this.getLogDes()!=null && castOther.getLogDes()!=null && this.getLogDes().equals(castOther.getLogDes()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getLogId() == null ? 0 : this.getLogId().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getAccountCode() == null ? 0 : this.getAccountCode().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getLogDes() == null ? 0 : this.getLogDes().hashCode() );
         return result;
   }   





}
