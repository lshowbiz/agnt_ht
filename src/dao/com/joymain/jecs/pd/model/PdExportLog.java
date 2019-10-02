package com.joymain.jecs.pd.model;
// Generated 2014-7-22 16:20:54 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="PD_EXPORT_LOG"
 *     
 */

public class PdExportLog extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {

    // Fields    

    private Long logId;
    private String logName;
    private String logType;
    private String logStatus;
    private String logUserCode;
    private Date logStartTime;
    private Date logEndTime;
    private String logDesc;

    // Constructors

    /** default constructor */
    public PdExportLog() {
    }

    
   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="LOG_ID"
     *             @hibernate.generator-param name="sequence" value="SEQ_PD"
     *         
     */
    public Long getLogId() {
        return this.logId;
    }
    
    public void setLogId(Long logId) {
        this.logId = logId;
    }
    
    /**       
     *    @hibernate.property
     *     column="LOG_NAME"
     *     length="100"
     *         
     */

    public String getLogName() {
        return this.logName;
    }
    
    public void setLogName(String logName) {
        this.logName = logName;
    }
    

    /**       
     *    @hibernate.property
     *     column="LOG_TYPE"
     *     length="3"
     *         
     */

    public String getLogType() {
        return this.logType;
    }
    
    public void setLogType(String logType) {
        this.logType = logType;
    }
    
    
    /**       
     *   @hibernate.property
     *    column="LOG_STATUS"
     *    length="1"
     *         
     */

    public String getLogStatus() {
        return this.logStatus;
    }
    
    public void setLogStatus(String logStatus) {
        this.logStatus = logStatus;
    }
    
    /**       
     *   @hibernate.property
     *    column="LOG_USER_CODE"
     *    length="20"
     *         
     */

    public String getLogUserCode() {
        return this.logUserCode;
    }
    
    public void setLogUserCode(String logUserCode) {
        this.logUserCode = logUserCode;
    }
    

    /**       
     *   @hibernate.property
     *    column="LOG_START_TIME"
     *    length="7"
     *         
     */

    public Date getLogStartTime() {
        return this.logStartTime;
    }
    
    public void setLogStartTime(Date logStartTime) {
        this.logStartTime = logStartTime;
    }
   
    /**       
     *   @hibernate.property
     *    column="LOG_END_TIME"
     *    length="7"
     *         
     */

    public Date getLogEndTime() {
        return this.logEndTime;
    }
    
    public void setLogEndTime(Date logEndTime) {
        this.logEndTime = logEndTime;
    }

    /**       
     *   @hibernate.property
     *    column="LOG_DESC"
     *    length="200"
     *         
     */

    public String getLogDesc() {
        return this.logDesc;
    }
    
    public void setLogDesc(String logDesc) {
        this.logDesc = logDesc;
    }
    
    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("logId").append("='").append(getLogId()).append("' ");			
      buffer.append("logName").append("='").append(getLogName()).append("' ");			
      buffer.append("logType").append("='").append(getLogType()).append("' ");			
      buffer.append("logStatus").append("='").append(getLogStatus()).append("' ");		
      buffer.append("logUserCode").append("='").append(getLogStatus()).append("' ");		
      buffer.append("logStartTime").append("='").append(getLogStartTime()).append("' ");	
      buffer.append("logEndTime").append("='").append(getLogEndTime()).append("' ");	
      buffer.append("logDesc").append("='").append(getLogDesc()).append("' ");	
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PdExportLog) ) return false;
		 PdExportLog castOther = ( PdExportLog ) other; 
         
		 return ( (this.getLogId()==castOther.getLogId()) || ( this.getLogId()!=null && castOther.getLogId()!=null && this.getLogId().equals(castOther.getLogId()) ) )
 && ( (this.getLogName()==castOther.getLogName()) || ( this.getLogName()!=null && castOther.getLogName()!=null && this.getLogName().equals(castOther.getLogName()) ) )
 && ( (this.getLogType()==castOther.getLogType()) || ( this.getLogType()!=null && castOther.getLogType()!=null && this.getLogType().equals(castOther.getLogType()) ) )
 && ( (this.getLogStatus()==castOther.getLogStatus()) || ( this.getLogStatus()!=null && castOther.getLogStatus()!=null && this.getLogStatus().equals(castOther.getLogStatus()) ) )
 && ( (this.getLogUserCode()==castOther.getLogUserCode()) || ( this.getLogUserCode()!=null && castOther.getLogUserCode()!=null && this.getLogUserCode().equals(castOther.getLogUserCode()) ) )
 && ( (this.getLogStartTime()==castOther.getLogStartTime()) || ( this.getLogStartTime()!=null && castOther.getLogStartTime()!=null && this.getLogStartTime().equals(castOther.getLogStartTime()) ) )
 && ( (this.getLogEndTime()==castOther.getLogEndTime()) || ( this.getLogEndTime()!=null && castOther.getLogEndTime()!=null && this.getLogEndTime().equals(castOther.getLogEndTime()) ) )
 && ( (this.getLogDesc()==castOther.getLogDesc()) || ( this.getLogDesc()!=null && castOther.getLogDesc()!=null && this.getLogDesc().equals(castOther.getLogDesc()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getLogId() == null ? 0 : this.getLogId().hashCode() );
         result = 37 * result + ( getLogName() == null ? 0 : this.getLogName().hashCode() );
         result = 37 * result + ( getLogType() == null ? 0 : this.getLogType().hashCode() );
         result = 37 * result + ( getLogStatus() == null ? 0 : this.getLogStatus().hashCode() );
         result = 37 * result + ( getLogUserCode() == null ? 0 : this.getLogUserCode().hashCode() );
         result = 37 * result + ( getLogStartTime() == null ? 0 : this.getLogStartTime().hashCode() );
         result = 37 * result + ( getLogEndTime() == null ? 0 : this.getLogEndTime().hashCode() );
         result = 37 * result + ( getLogDesc() == null ? 0 : this.getLogDesc().hashCode() );
         return result;
   }   





}
