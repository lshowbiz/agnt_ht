package com.joymain.jecs.sys.model;
// Generated 2009-11-6 15:54:42 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JSYS_LOGIN_LOG"
 *     
 */

public class SysLoginLog extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long llId;
    private String userCode;
    private String operaterCode;
    private String ipAddr;
    private Date operateTime;
    private String operationType;


    // Constructors

    /** default constructor */
    public SysLoginLog() {
    }

    
    /** full constructor */
    public SysLoginLog(String userCode, String operaterCode, String ipAddr, Date operateTime, String operationType) {
        this.userCode = userCode;
        this.operaterCode = operaterCode;
        this.ipAddr = ipAddr;
        this.operateTime = operateTime;
        this.operationType = operationType;
    }
    

   
    // Property accessors
    /**
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="LL_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_LOG"
     */

    public Long getLlId() {
        return this.llId;
    }
    
    public void setLlId(Long llId) {
        this.llId = llId;
    }
    /**       
     *      *            @hibernate.property
     *             column="USER_CODE"
     *             length="100"
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
     *             column="OPERATER_CODE"
     *             length="100"
     *         
     */

    public String getOperaterCode() {
        return this.operaterCode;
    }
    
    public void setOperaterCode(String operaterCode) {
        this.operaterCode = operaterCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="IP_ADDR"
     *             length="30"
     *         
     */

    public String getIpAddr() {
        return this.ipAddr;
    }
    
    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }
    /**       
     *      *            @hibernate.property
     *             column="OPERATE_TIME"
     *             length="7"
     *         
     */

    public Date getOperateTime() {
        return this.operateTime;
    }
    
    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="OPERATION_TYPE"
     *             length="5"
     *         
     */

    public String getOperationType() {
        return this.operationType;
    }
    
    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("operaterCode").append("='").append(getOperaterCode()).append("' ");			
      buffer.append("ipAddr").append("='").append(getIpAddr()).append("' ");			
      buffer.append("operateTime").append("='").append(getOperateTime()).append("' ");			
      buffer.append("operationType").append("='").append(getOperationType()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SysLoginLog) ) return false;
		 SysLoginLog castOther = ( SysLoginLog ) other; 
         
		 return ( (this.getLlId()==castOther.getLlId()) || ( this.getLlId()!=null && castOther.getLlId()!=null && this.getLlId().equals(castOther.getLlId()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getOperaterCode()==castOther.getOperaterCode()) || ( this.getOperaterCode()!=null && castOther.getOperaterCode()!=null && this.getOperaterCode().equals(castOther.getOperaterCode()) ) )
 && ( (this.getIpAddr()==castOther.getIpAddr()) || ( this.getIpAddr()!=null && castOther.getIpAddr()!=null && this.getIpAddr().equals(castOther.getIpAddr()) ) )
 && ( (this.getOperateTime()==castOther.getOperateTime()) || ( this.getOperateTime()!=null && castOther.getOperateTime()!=null && this.getOperateTime().equals(castOther.getOperateTime()) ) )
 && ( (this.getOperationType()==castOther.getOperationType()) || ( this.getOperationType()!=null && castOther.getOperationType()!=null && this.getOperationType().equals(castOther.getOperationType()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getLlId() == null ? 0 : this.getLlId().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getOperaterCode() == null ? 0 : this.getOperaterCode().hashCode() );
         result = 37 * result + ( getIpAddr() == null ? 0 : this.getIpAddr().hashCode() );
         result = 37 * result + ( getOperateTime() == null ? 0 : this.getOperateTime().hashCode() );
         result = 37 * result + ( getOperationType() == null ? 0 : this.getOperationType().hashCode() );
         return result;
   }   





}
