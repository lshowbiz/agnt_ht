package com.joymain.jecs.sys.model;
// Generated 2008-12-24 13:44:54 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JSYS_REPORT_LOG"
 *     
 */

public class SysReportLog extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {
    // Fields    
    private String reportId;
    private String reportName;
    private String companyCode;
    private String userCode;
    private String fileName;
    private Date createTime;
    private Long usedTime;
    private Long recordsCount;
    private Integer status;


    // Constructors

    /** default constructor */
    public SysReportLog() {
    }
   
    // Property accessors
    /**
	 * *
	 * 
	 * @hibernate.id generator-class="assigned" type="java.lang.String"
	 *               column="REPORT_ID"
	 * 
	 */
    public String getReportId() {
        return this.reportId;
    }
    
    public void setReportId(String reportId) {
        this.reportId = reportId;
    }
    /**       
     *      *            @hibernate.property
     *             column="REPORT_NAME"
     *             length="150"
     *             not-null="true"
     *         
     */

    public String getReportName() {
        return this.reportName;
    }
    
    public void setReportName(String reportName) {
        this.reportName = reportName;
    }
    /**       
     *      *            @hibernate.property
     *             column="COMPANY_CODE"
     *             length="2"
     *             not-null="true"
     *         
     */

    public String getCompanyCode() {
        return this.companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="USER_CODE"
     *             length="30"
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
     *             column="FILE_NAME"
     *             length="250"
     *         
     */

    public String getFileName() {
        return this.fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
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
     *             column="USED_TIME"
     *             length="12"
     *         
     */

    public Long getUsedTime() {
        return this.usedTime;
    }
    
    public void setUsedTime(Long usedTime) {
        this.usedTime = usedTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="RECORDS_COUNT"
     *             length="12"
     *         
     */

    public Long getRecordsCount() {
        return this.recordsCount;
    }
    
    public void setRecordsCount(Long recordsCount) {
        this.recordsCount = recordsCount;
    }
    /**       
     *      *            @hibernate.property
     *             column="STATUS"
     *             length="4"
     *         
     */

    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("reportName").append("='").append(getReportName()).append("' ");			
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("fileName").append("='").append(getFileName()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("usedTime").append("='").append(getUsedTime()).append("' ");			
      buffer.append("recordsCount").append("='").append(getRecordsCount()).append("' ");			
      buffer.append("status").append("='").append(getStatus()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SysReportLog) ) return false;
		 SysReportLog castOther = ( SysReportLog ) other; 
         
		 return ( (this.getReportId()==castOther.getReportId()) || ( this.getReportId()!=null && castOther.getReportId()!=null && this.getReportId().equals(castOther.getReportId()) ) )
 && ( (this.getReportName()==castOther.getReportName()) || ( this.getReportName()!=null && castOther.getReportName()!=null && this.getReportName().equals(castOther.getReportName()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getFileName()==castOther.getFileName()) || ( this.getFileName()!=null && castOther.getFileName()!=null && this.getFileName().equals(castOther.getFileName()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getUsedTime()==castOther.getUsedTime()) || ( this.getUsedTime()!=null && castOther.getUsedTime()!=null && this.getUsedTime().equals(castOther.getUsedTime()) ) )
 && ( (this.getRecordsCount()==castOther.getRecordsCount()) || ( this.getRecordsCount()!=null && castOther.getRecordsCount()!=null && this.getRecordsCount().equals(castOther.getRecordsCount()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getReportId() == null ? 0 : this.getReportId().hashCode() );
         result = 37 * result + ( getReportName() == null ? 0 : this.getReportName().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getFileName() == null ? 0 : this.getFileName().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getUsedTime() == null ? 0 : this.getUsedTime().hashCode() );
         result = 37 * result + ( getRecordsCount() == null ? 0 : this.getRecordsCount().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         return result;
   }   





}
