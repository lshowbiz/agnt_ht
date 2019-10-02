package com.joymain.jecs.sys.model;

import java.sql.Timestamp;

// Generated 2008-5-28 11:12:33 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JSYS_OPERATION_LOG"
 *     
 */

public class SysOperationLog extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long logId;
    private String operaterCode;
    private String operaterName;
    private String ipAddr;
    private Timestamp operateTime;
    private String companyCode;
    private String visitUrl;
    private String operateData;
    private String moduleName;
    private Integer doType;
    private String userType;
    private Integer doResult;
    private String userCode;
    private String userName;

    // Constructors

    /** default constructor */
    public SysOperationLog() {
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
     *             column="OPERATER_NAME"
     *             length="100"
     *         
     */

    public String getOperaterName() {
        return this.operaterName;
    }
    
    public void setOperaterName(String operaterName) {
        this.operaterName = operaterName;
    }
    /**       
     *      *            @hibernate.property
     *             column="IP_ADDR"
     *             length="100"
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
     *             length="30"
     *         
     */

    public Timestamp getOperateTime() {
        return this.operateTime;
    }
    
    public void setOperateTime(Timestamp operateTime) {
        this.operateTime = operateTime;
    }
    
    /**       
     *      *            @hibernate.property
     *             column="COMPANY_CODE"
     *             length="2"
     *         
     */
    public String getCompanyCode() {
    	return companyCode;
    }

	public void setCompanyCode(String companyCode) {
    	this.companyCode = companyCode;
    }

	/**       
     *      *            @hibernate.property
     *             column="VISIT_URL"
     *             length="200"
     *         
     */
	public String getVisitUrl() {
    	return visitUrl;
    }

	public void setVisitUrl(String visitUrl) {
    	this.visitUrl = visitUrl;
    }

	/**       
     *      *            @hibernate.property
     *             column="OPERATE_DATA"
     *         
     */
	public String getOperateData() {
    	return operateData;
    }

	public void setOperateData(String operateData) {
    	this.operateData = operateData;
    }

	/**       
     *      *            @hibernate.property
     *             column="MODULE_NAME"
     *             length="150"
     *         
     */
	public String getModuleName() {
    	return moduleName;
    }

	public void setModuleName(String moduleName) {
    	this.moduleName = moduleName;
    }

	/**       
     *      *            @hibernate.property
     *             column="DO_TYPE"
     *             length="2"
     *         
     */
	public Integer getDoType() {
    	return doType;
    }

	public void setDoType(Integer doType) {
    	this.doType = doType;
    }

	/**       
     *      *            @hibernate.property
     *             column="DO_RESULT"
     *             length="2"
     *         
     */
	public Integer getDoResult() {
    	return doResult;
    }

	public void setDoResult(Integer doResult) {
    	this.doResult = doResult;
    }

	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("operaterCode").append("='").append(getOperaterCode()).append("' ");			
      buffer.append("operaterName").append("='").append(getOperaterName()).append("' ");			
      buffer.append("ipAddr").append("='").append(getIpAddr()).append("' ");			
      buffer.append("operateTime").append("='").append(getOperateTime()).append("' ");			
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("visitUrl").append("='").append(getVisitUrl()).append("' ");			
      buffer.append("operateData").append("='").append(getOperateData()).append("' ");				
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SysOperationLog) ) return false;
		 SysOperationLog castOther = ( SysOperationLog ) other; 
         
		 return ( (this.getLogId()==castOther.getLogId()) || ( this.getLogId()!=null && castOther.getLogId()!=null && this.getLogId().equals(castOther.getLogId()) ) )
 && ( (this.getOperaterCode()==castOther.getOperaterCode()) || ( this.getOperaterCode()!=null && castOther.getOperaterCode()!=null && this.getOperaterCode().equals(castOther.getOperaterCode()) ) )
 && ( (this.getOperaterName()==castOther.getOperaterName()) || ( this.getOperaterName()!=null && castOther.getOperaterName()!=null && this.getOperaterName().equals(castOther.getOperaterName()) ) )
 && ( (this.getIpAddr()==castOther.getIpAddr()) || ( this.getIpAddr()!=null && castOther.getIpAddr()!=null && this.getIpAddr().equals(castOther.getIpAddr()) ) )
 && ( (this.getOperateTime()==castOther.getOperateTime()) || ( this.getOperateTime()!=null && castOther.getOperateTime()!=null && this.getOperateTime().equals(castOther.getOperateTime()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getVisitUrl()==castOther.getVisitUrl()) || ( this.getVisitUrl()!=null && castOther.getVisitUrl()!=null && this.getVisitUrl().equals(castOther.getVisitUrl()) ) )
 && ( (this.getOperateData()==castOther.getOperateData()) || ( this.getOperateData()!=null && castOther.getOperateData()!=null && this.getOperateData().equals(castOther.getOperateData()) ) )
 ;
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getLogId() == null ? 0 : this.getLogId().hashCode() );
         result = 37 * result + ( getOperaterCode() == null ? 0 : this.getOperaterCode().hashCode() );
         result = 37 * result + ( getOperaterName() == null ? 0 : this.getOperaterName().hashCode() );
         result = 37 * result + ( getIpAddr() == null ? 0 : this.getIpAddr().hashCode() );
         result = 37 * result + ( getOperateTime() == null ? 0 : this.getOperateTime().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getVisitUrl() == null ? 0 : this.getVisitUrl().hashCode() );
         result = 37 * result + ( getOperateData() == null ? 0 : this.getOperateData().hashCode() );
         return result;
   }
   /**
	 * *
	 * @hibernate.property column="USER_TYPE"
	 * 
	 */
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * *
	 * @hibernate.property column="USER_CODE"
	 * 
	 */
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	/**
	 * *
	 * @hibernate.property column="USER_NAME"
	 * 
	 */
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}   
}