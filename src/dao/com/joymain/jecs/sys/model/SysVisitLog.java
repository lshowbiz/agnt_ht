package com.joymain.jecs.sys.model;
// Generated 2008-12-4 14:46:22 by Hibernate Tools 3.1.0.beta4

import java.sql.Timestamp;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JSYS_VISIT_LOG"
 *     
 */

public class SysVisitLog extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long logId;
    private String moduleCode;
    private String userCode;
    private String visitUrl;
    private Timestamp visitTime;
    private String queryString;

    // Constructors

    /** default constructor */
    public SysVisitLog() {
    }

	/** minimal constructor */
    public SysVisitLog(String moduleCode, String userCode) {
        this.moduleCode = moduleCode;
        this.userCode = userCode;
    }
    
    /** full constructor */
    public SysVisitLog(String moduleCode, String userCode, String visitUrl, Timestamp visitTime) {
        this.moduleCode = moduleCode;
        this.userCode = userCode;
        this.visitUrl = visitUrl;
        this.visitTime = visitTime;
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
     *             column="MODULE_CODE"
     *             length="50"
     *             not-null="true"
     *         
     */

    public String getModuleCode() {
        return this.moduleCode;
    }
    
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="USER_CODE"
     *             length="50"
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
     *             column="VISIT_URL"
     *             length="250"
     *         
     */

    public String getVisitUrl() {
        return this.visitUrl;
    }
    
    public void setVisitUrl(String visitUrl) {
        this.visitUrl = visitUrl;
    }
    /**       
     *      *            @hibernate.property
     *             column="VISIT_TIME"
     *             length="7"
     *         
     */

    public Timestamp getVisitTime() {
        return this.visitTime;
    }
    
    public void setVisitTime(Timestamp visitTime) {
        this.visitTime = visitTime;
    }
   
    /**       
     *      *            @hibernate.property
     *             column="QUERY_STRING"
     *             length="2000"
     *         
     */
    public String getQueryString() {
    	return queryString;
    }

	public void setQueryString(String queryString) {
    	this.queryString = queryString;
    }

	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("moduleCode").append("='").append(getModuleCode()).append("' ");			
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("visitUrl").append("='").append(getVisitUrl()).append("' ");			
      buffer.append("visitTime").append("='").append(getVisitTime()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SysVisitLog) ) return false;
		 SysVisitLog castOther = ( SysVisitLog ) other; 
         
		 return ( (this.getLogId()==castOther.getLogId()) || ( this.getLogId()!=null && castOther.getLogId()!=null && this.getLogId().equals(castOther.getLogId()) ) )
 && ( (this.getModuleCode()==castOther.getModuleCode()) || ( this.getModuleCode()!=null && castOther.getModuleCode()!=null && this.getModuleCode().equals(castOther.getModuleCode()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getVisitUrl()==castOther.getVisitUrl()) || ( this.getVisitUrl()!=null && castOther.getVisitUrl()!=null && this.getVisitUrl().equals(castOther.getVisitUrl()) ) )
 && ( (this.getVisitTime()==castOther.getVisitTime()) || ( this.getVisitTime()!=null && castOther.getVisitTime()!=null && this.getVisitTime().equals(castOther.getVisitTime()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getLogId() == null ? 0 : this.getLogId().hashCode() );
         result = 37 * result + ( getModuleCode() == null ? 0 : this.getModuleCode().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getVisitUrl() == null ? 0 : this.getVisitUrl().hashCode() );
         result = 37 * result + ( getVisitTime() == null ? 0 : this.getVisitTime().hashCode() );
         return result;
   }   





}
