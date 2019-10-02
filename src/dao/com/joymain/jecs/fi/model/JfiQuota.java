package com.joymain.jecs.fi.model;

import java.util.Date;

// Generated 2016-2-17 18:41:29 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JFI_QUOTA"
 *     
 */

public class JfiQuota extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long quotaId;
    private String validityPeriod;
    private Long accountId;
    private String status;
    private Long maxMoney;
    private String operateName;
    private Date operateTime;
    private String remark;
    private FiBillAccount fiBillAccount = new FiBillAccount();
    private Long oldMaxMoney;
    // Constructors

    /** default constructor */
    public JfiQuota() {
    }

    
    /** full constructor */
    public JfiQuota(String validityPeriod, Long accountId, String status, Long maxMoney, String operateName, Date operateTime, String remark) {
        this.validityPeriod = validityPeriod;
        this.accountId = accountId;
        this.status = status;
        this.maxMoney = maxMoney;
        this.operateName = operateName;
        this.operateTime = operateTime;
        this.remark = remark;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="QUOTA_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_FI"
     *         
     */
    public Long getQuotaId() {
        return this.quotaId;
    }
    
    public void setQuotaId(Long quotaId) {
        this.quotaId = quotaId;
    }
    /**       
     *      *            @hibernate.property
     *             column="VALIDITY_PERIOD"
     *             length="256"
     *         
     */

    public String getValidityPeriod() {
        return this.validityPeriod;
    }
    
    public void setValidityPeriod(String validityPeriod) {
        this.validityPeriod = validityPeriod;
    }
    /**       
     *      *            @hibernate.property
     *             column="ACCOUNT_ID"
     *             length="12"
     *         
     */

    public Long getAccountId() {
        return this.accountId;
    }
    
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
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
     *             column="MAX_MONEY"
     *             length="18"
     *         
     */

    public Long getMaxMoney() {
        return this.maxMoney;
    }
    
    public void setMaxMoney(Long maxMoney) {
        this.maxMoney = maxMoney;
    }
    /**       
     *      *            @hibernate.property
     *             column="OPERATE_NAME"
     *             length="256"
     *         
     */

    public String getOperateName() {
        return this.operateName;
    }
    
    public void setOperateName(String operateName) {
        this.operateName = operateName;
    }
    /**       
     *      *            @hibernate.property
     *             column="OPERATE_TIME"
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
     *             column="REMARK"
     *             length="2000"
     *         
     */

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("validityPeriod").append("='").append(getValidityPeriod()).append("' ");			
      buffer.append("accountId").append("='").append(getAccountId()).append("' ");			
      buffer.append("status").append("='").append(getStatus()).append("' ");			
      buffer.append("maxMoney").append("='").append(getMaxMoney()).append("' ");			
      buffer.append("operateName").append("='").append(getOperateName()).append("' ");			
      buffer.append("operateTime").append("='").append(getOperateTime()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JfiQuota) ) return false;
		 JfiQuota castOther = ( JfiQuota ) other; 
         
		 return ( (this.getQuotaId()==castOther.getQuotaId()) || ( this.getQuotaId()!=null && castOther.getQuotaId()!=null && this.getQuotaId().equals(castOther.getQuotaId()) ) )
 && ( (this.getValidityPeriod()==castOther.getValidityPeriod()) || ( this.getValidityPeriod()!=null && castOther.getValidityPeriod()!=null && this.getValidityPeriod().equals(castOther.getValidityPeriod()) ) )
 && ( (this.getAccountId()==castOther.getAccountId()) || ( this.getAccountId()!=null && castOther.getAccountId()!=null && this.getAccountId().equals(castOther.getAccountId()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
 && ( (this.getMaxMoney()==castOther.getMaxMoney()) || ( this.getMaxMoney()!=null && castOther.getMaxMoney()!=null && this.getMaxMoney().equals(castOther.getMaxMoney()) ) )
 && ( (this.getOperateName()==castOther.getOperateName()) || ( this.getOperateName()!=null && castOther.getOperateName()!=null && this.getOperateName().equals(castOther.getOperateName()) ) )
 && ( (this.getOperateTime()==castOther.getOperateTime()) || ( this.getOperateTime()!=null && castOther.getOperateTime()!=null && this.getOperateTime().equals(castOther.getOperateTime()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getQuotaId() == null ? 0 : this.getQuotaId().hashCode() );
         result = 37 * result + ( getValidityPeriod() == null ? 0 : this.getValidityPeriod().hashCode() );
         result = 37 * result + ( getAccountId() == null ? 0 : this.getAccountId().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         result = 37 * result + ( getMaxMoney() == null ? 0 : this.getMaxMoney().hashCode() );
         result = 37 * result + ( getOperateName() == null ? 0 : this.getOperateName().hashCode() );
         result = 37 * result + ( getOperateTime() == null ? 0 : this.getOperateTime().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         return result;
   }

   /**
	 * *
	 * @hibernate.many-to-one not-null="true" inverse="true" insert="false" update="false" lazy="false"
	 * @hibernate.column name="ACCOUNT_ID"
	 * 
	 */
	public FiBillAccount getFiBillAccount() {
		return fiBillAccount;
	}
	
	
	public void setFiBillAccount(FiBillAccount fiBillAccount) {
		this.fiBillAccount = fiBillAccount;
	}


	public Long getOldMaxMoney() {
		return oldMaxMoney;
	}


	public void setOldMaxMoney(Long oldMaxMoney) {
		this.oldMaxMoney = oldMaxMoney;
	}   
}
