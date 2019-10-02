package com.joymain.jecs.sys.model;
// Generated 2008-3-25 15:27:46 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JSYS_CLICK_LOG"
 *     
 */

public class SysClickLog extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long logId;
    private String logType;
    private String remoteUser;
    private Date runDate;
    private String ipAddress;
    private String hostName;
    private String clickUri;
    private String content;
    private Integer isValid;


    // Constructors

    /** default constructor */
    public SysClickLog() {
    }

    
    /** full constructor */
    public SysClickLog(String logType, String remoteUser, Date runDate, String ipAddress, String hostName, String clickUri, String content, Integer isValid) {
        this.logType = logType;
        this.remoteUser = remoteUser;
        this.runDate = runDate;
        this.ipAddress = ipAddress;
        this.hostName = hostName;
        this.clickUri = clickUri;
        this.content = content;
        this.isValid = isValid;
    }
    

   
    // Property accessors
    /**
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="LOG_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_LOG"
     */
    public Long getLogId() {
        return this.logId;
    }
    
    public void setLogId(Long logId) {
        this.logId = logId;
    }
    /**       
     *      *            @hibernate.property
     *             column="LOG_TYPE"
     *             length="30"
     *         
     */

    public String getLogType() {
        return this.logType;
    }
    
    public void setLogType(String logType) {
        this.logType = logType;
    }
    /**       
     *      *            @hibernate.property
     *             column="REMOTE_USER"
     *             length="30"
     *         
     */

    public String getRemoteUser() {
        return this.remoteUser;
    }
    
    public void setRemoteUser(String remoteUser) {
        this.remoteUser = remoteUser;
    }
    /**       
     *      *            @hibernate.property
     *             column="RUN_DATE"
     *             length="7"
     *         
     */

    public Date getRunDate() {
        return this.runDate;
    }
    
    public void setRunDate(Date runDate) {
        this.runDate = runDate;
    }
    /**       
     *      *            @hibernate.property
     *             column="IP_ADDRESS"
     *             length="18"
     *         
     */

    public String getIpAddress() {
        return this.ipAddress;
    }
    
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    /**       
     *      *            @hibernate.property
     *             column="HOST_NAME"
     *             length="30"
     *         
     */

    public String getHostName() {
        return this.hostName;
    }
    
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
    /**       
     *      *            @hibernate.property
     *             column="CLICK_URI"
     *             length="250"
     *         
     */

    public String getClickUri() {
        return this.clickUri;
    }
    
    public void setClickUri(String clickUri) {
        this.clickUri = clickUri;
    }
    /**       
     *      *            @hibernate.property
     *             column="CONTENT"
     *             length="500"
     *         
     */

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    /**       
     *      *            @hibernate.property
     *             column="IS_VALID"
     *             length="4"
     *         
     */

    public Integer getIsValid() {
        return this.isValid;
    }
    
    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("logType").append("='").append(getLogType()).append("' ");			
      buffer.append("remoteUser").append("='").append(getRemoteUser()).append("' ");			
      buffer.append("runDate").append("='").append(getRunDate()).append("' ");			
      buffer.append("ipAddress").append("='").append(getIpAddress()).append("' ");			
      buffer.append("hostName").append("='").append(getHostName()).append("' ");			
      buffer.append("clickUri").append("='").append(getClickUri()).append("' ");			
      buffer.append("content").append("='").append(getContent()).append("' ");			
      buffer.append("isValid").append("='").append(getIsValid()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SysClickLog) ) return false;
		 SysClickLog castOther = ( SysClickLog ) other; 
         
		 return ( (this.getLogId()==castOther.getLogId()) || ( this.getLogId()!=null && castOther.getLogId()!=null && this.getLogId().equals(castOther.getLogId()) ) )
 && ( (this.getLogType()==castOther.getLogType()) || ( this.getLogType()!=null && castOther.getLogType()!=null && this.getLogType().equals(castOther.getLogType()) ) )
 && ( (this.getRemoteUser()==castOther.getRemoteUser()) || ( this.getRemoteUser()!=null && castOther.getRemoteUser()!=null && this.getRemoteUser().equals(castOther.getRemoteUser()) ) )
 && ( (this.getRunDate()==castOther.getRunDate()) || ( this.getRunDate()!=null && castOther.getRunDate()!=null && this.getRunDate().equals(castOther.getRunDate()) ) )
 && ( (this.getIpAddress()==castOther.getIpAddress()) || ( this.getIpAddress()!=null && castOther.getIpAddress()!=null && this.getIpAddress().equals(castOther.getIpAddress()) ) )
 && ( (this.getHostName()==castOther.getHostName()) || ( this.getHostName()!=null && castOther.getHostName()!=null && this.getHostName().equals(castOther.getHostName()) ) )
 && ( (this.getClickUri()==castOther.getClickUri()) || ( this.getClickUri()!=null && castOther.getClickUri()!=null && this.getClickUri().equals(castOther.getClickUri()) ) )
 && ( (this.getContent()==castOther.getContent()) || ( this.getContent()!=null && castOther.getContent()!=null && this.getContent().equals(castOther.getContent()) ) )
 && ( (this.getIsValid()==castOther.getIsValid()) || ( this.getIsValid()!=null && castOther.getIsValid()!=null && this.getIsValid().equals(castOther.getIsValid()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getLogId() == null ? 0 : this.getLogId().hashCode() );
         result = 37 * result + ( getLogType() == null ? 0 : this.getLogType().hashCode() );
         result = 37 * result + ( getRemoteUser() == null ? 0 : this.getRemoteUser().hashCode() );
         result = 37 * result + ( getRunDate() == null ? 0 : this.getRunDate().hashCode() );
         result = 37 * result + ( getIpAddress() == null ? 0 : this.getIpAddress().hashCode() );
         result = 37 * result + ( getHostName() == null ? 0 : this.getHostName().hashCode() );
         result = 37 * result + ( getClickUri() == null ? 0 : this.getClickUri().hashCode() );
         result = 37 * result + ( getContent() == null ? 0 : this.getContent().hashCode() );
         result = 37 * result + ( getIsValid() == null ? 0 : this.getIsValid().hashCode() );
         return result;
   }   





}
