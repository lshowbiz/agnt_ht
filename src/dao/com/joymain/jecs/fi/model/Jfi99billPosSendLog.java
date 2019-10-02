package com.joymain.jecs.fi.model;
// Generated 2010-1-6 2:48:04 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JFI_99BILL_POS_SEND_LOG"
 *     
 */

public class Jfi99billPosSendLog extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long logId;
    private String companyCode;
    private String userCode;
    private String actionType;
    private String actionNo;
    private String createCode;
    private Date createTime;
    private int returnTime;
    private String hashMd5Code;
    private String url;


    // Constructors


	/** default constructor */
    public Jfi99billPosSendLog() {
    }

    
    /** full constructor */
    public Jfi99billPosSendLog(String companyCode, String userCode, String actionType, String actionNo, String createCode, Date createTime, int returnTime, String hashMd5Code) {
        this.companyCode = companyCode;
        this.userCode = userCode;
        this.actionType = actionType;
        this.actionNo = actionNo;
        this.createCode = createCode;
        this.createTime = createTime;
        this.returnTime = returnTime;
        this.hashMd5Code = hashMd5Code;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.Long"
     *             column="LOG_ID"
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
     *             column="ACTION_TYPE"
     *             length="1"
     *             not-null="true"
     *         
     */

    public String getActionType() {
        return this.actionType;
    }
    
    public void setActionType(String actionType) {
        this.actionType = actionType;
    }
    /**       
     *      *            @hibernate.property
     *             column="ACTION_NO"
     *             length="20"
     *             not-null="true"
     *         
     */

    public String getActionNo() {
        return this.actionNo;
    }
    
    public void setActionNo(String actionNo) {
        this.actionNo = actionNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="CREATE_CODE"
     *             length="20"
     *             not-null="true"
     *         
     */

    public String getCreateCode() {
        return this.createCode;
    }
    
    public void setCreateCode(String createCode) {
        this.createCode = createCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="CREATE_TIME"
     *             length="7"
     *             not-null="true"
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
     *             column="RETURN_TIME"
     *             length="6"
     *             not-null="true"
     *         
     */

    public int getReturnTime() {
        return this.returnTime;
    }
    
    public void setReturnTime(int returnTime) {
        this.returnTime = returnTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="HASH_MD5_CODE"
     *             length="32"
     *         
     */

    public String getHashMd5Code() {
        return this.hashMd5Code;
    }
    
    public void setHashMd5Code(String hashMd5Code) {
        this.hashMd5Code = hashMd5Code;
    }

    /**       
     *      *            @hibernate.property
     *             column="URL"
     *             length="4000"
     *         
     */
    public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("actionType").append("='").append(getActionType()).append("' ");			
      buffer.append("actionNo").append("='").append(getActionNo()).append("' ");			
      buffer.append("createCode").append("='").append(getCreateCode()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("returnTime").append("='").append(getReturnTime()).append("' ");			
      buffer.append("hashMd5Code").append("='").append(getHashMd5Code()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Jfi99billPosSendLog) ) return false;
		 Jfi99billPosSendLog castOther = ( Jfi99billPosSendLog ) other; 
         
		 return ( (this.getLogId()==castOther.getLogId()) || ( this.getLogId()!=null && castOther.getLogId()!=null && this.getLogId().equals(castOther.getLogId()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getActionType()==castOther.getActionType()) || ( this.getActionType()!=null && castOther.getActionType()!=null && this.getActionType().equals(castOther.getActionType()) ) )
 && ( (this.getActionNo()==castOther.getActionNo()) || ( this.getActionNo()!=null && castOther.getActionNo()!=null && this.getActionNo().equals(castOther.getActionNo()) ) )
 && ( (this.getCreateCode()==castOther.getCreateCode()) || ( this.getCreateCode()!=null && castOther.getCreateCode()!=null && this.getCreateCode().equals(castOther.getCreateCode()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && (this.getReturnTime()==castOther.getReturnTime())
 && ( (this.getHashMd5Code()==castOther.getHashMd5Code()) || ( this.getHashMd5Code()!=null && castOther.getHashMd5Code()!=null && this.getHashMd5Code().equals(castOther.getHashMd5Code()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getLogId() == null ? 0 : this.getLogId().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getActionType() == null ? 0 : this.getActionType().hashCode() );
         result = 37 * result + ( getActionNo() == null ? 0 : this.getActionNo().hashCode() );
         result = 37 * result + ( getCreateCode() == null ? 0 : this.getCreateCode().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + this.getReturnTime();
         result = 37 * result + ( getHashMd5Code() == null ? 0 : this.getHashMd5Code().hashCode() );
         return result;
   }   





}
