package com.joymain.jecs.fi.model;
// Generated 2015-7-23 14:19:39 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="FI_PAY_ACCOUNT"
 *     
 */

public class FiPayAccount extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long accountId;
    private String createUserCode;
    private String createUserName;
    private Date createTime;
    private String remark;
    private String accountName;
    private String registerEmail;
    
    private String providerType;//平台：1：快钱、2：畅捷通、3：盛付通、4：宝易互通、5平安橙E、6：汇付天下
    private String accountCode;//商户号
    private String accountType;//终端类型：1，PC；2，移动终端
    private String isDefault;//1:默认
    private String status;
    private String passKey;//密钥


    // Constructors

    /** default constructor */
    public FiPayAccount() {
    }

    
    /** full constructor */
    public FiPayAccount(String createUserCode, String createUserName, Date createTime, String remark, String accountName, String registerEmail, String providerType, String accountCode, String accountType, String isDefault, String status, String passKey) {
        this.createUserCode = createUserCode;
        this.createUserName = createUserName;
        this.createTime = createTime;
        this.remark = remark;
        this.accountName = accountName;
        this.registerEmail = registerEmail;
        this.providerType = providerType;
        this.accountCode = accountCode;
        this.accountType = accountType;
        this.isDefault = isDefault;
        this.status = status;
        this.passKey = passKey;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ACCOUNT_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_FI"
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
     *             column="CREATE_USER_CODE"
     *             length="50"
     *         
     */

    public String getCreateUserCode() {
        return this.createUserCode;
    }
    
    public void setCreateUserCode(String createUserCode) {
        this.createUserCode = createUserCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="CREATE_USER_NAME"
     *             length="50"
     *         
     */

    public String getCreateUserName() {
        return this.createUserName;
    }
    
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
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
     *             column="REMARK"
     *             length="200"
     *         
     */

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    /**       
     *      *            @hibernate.property
     *             column="ACCOUNT_NAME"
     *             length="50"
     *         
     */

    public String getAccountName() {
        return this.accountName;
    }
    
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    /**       
     *      *            @hibernate.property
     *             column="REGISTER_EMAIL"
     *             length="50"
     *         
     */

    public String getRegisterEmail() {
        return this.registerEmail;
    }
    
    public void setRegisterEmail(String registerEmail) {
        this.registerEmail = registerEmail;
    }
    /**       
     *      *            @hibernate.property
     *             column="PROVIDER_TYPE"
     *             length="1"
     *         
     */

    public String getProviderType() {
        return this.providerType;
    }
    
    public void setProviderType(String providerType) {
        this.providerType = providerType;
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
    
    /**
	 * @spring.validator type="required"
	 */
    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="ACCOUNT_TYPE"
     *             length="1"
     *         
     */

    public String getAccountType() {
        return this.accountType;
    }
    
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    /**       
     *      *            @hibernate.property
     *             column="IS_DEFAULT"
     *             length="1"
     *         
     */

    public String getIsDefault() {
        return this.isDefault;
    }
    
    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
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
     *             column="PASS_KEY"
     *             length="50"
     *         
     */

    public String getPassKey() {
        return this.passKey;
    }
    
    /**
	 * @spring.validator type="required"
	 */
    public void setPassKey(String passKey) {
        this.passKey = passKey;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("createUserCode").append("='").append(getCreateUserCode()).append("' ");			
      buffer.append("createUserName").append("='").append(getCreateUserName()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");			
      buffer.append("accountName").append("='").append(getAccountName()).append("' ");			
      buffer.append("registerEmail").append("='").append(getRegisterEmail()).append("' ");			
      buffer.append("providerType").append("='").append(getProviderType()).append("' ");			
      buffer.append("accountCode").append("='").append(getAccountCode()).append("' ");			
      buffer.append("accountType").append("='").append(getAccountType()).append("' ");			
      buffer.append("isDefault").append("='").append(getIsDefault()).append("' ");			
      buffer.append("status").append("='").append(getStatus()).append("' ");			
      buffer.append("passKey").append("='").append(getPassKey()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof FiPayAccount) ) return false;
		 FiPayAccount castOther = ( FiPayAccount ) other; 
         
		 return ( (this.getAccountId()==castOther.getAccountId()) || ( this.getAccountId()!=null && castOther.getAccountId()!=null && this.getAccountId().equals(castOther.getAccountId()) ) )
 && ( (this.getCreateUserCode()==castOther.getCreateUserCode()) || ( this.getCreateUserCode()!=null && castOther.getCreateUserCode()!=null && this.getCreateUserCode().equals(castOther.getCreateUserCode()) ) )
 && ( (this.getCreateUserName()==castOther.getCreateUserName()) || ( this.getCreateUserName()!=null && castOther.getCreateUserName()!=null && this.getCreateUserName().equals(castOther.getCreateUserName()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
 && ( (this.getAccountName()==castOther.getAccountName()) || ( this.getAccountName()!=null && castOther.getAccountName()!=null && this.getAccountName().equals(castOther.getAccountName()) ) )
 && ( (this.getRegisterEmail()==castOther.getRegisterEmail()) || ( this.getRegisterEmail()!=null && castOther.getRegisterEmail()!=null && this.getRegisterEmail().equals(castOther.getRegisterEmail()) ) )
 && ( (this.getProviderType()==castOther.getProviderType()) || ( this.getProviderType()!=null && castOther.getProviderType()!=null && this.getProviderType().equals(castOther.getProviderType()) ) )
 && ( (this.getAccountCode()==castOther.getAccountCode()) || ( this.getAccountCode()!=null && castOther.getAccountCode()!=null && this.getAccountCode().equals(castOther.getAccountCode()) ) )
 && ( (this.getAccountType()==castOther.getAccountType()) || ( this.getAccountType()!=null && castOther.getAccountType()!=null && this.getAccountType().equals(castOther.getAccountType()) ) )
 && ( (this.getIsDefault()==castOther.getIsDefault()) || ( this.getIsDefault()!=null && castOther.getIsDefault()!=null && this.getIsDefault().equals(castOther.getIsDefault()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
 && ( (this.getPassKey()==castOther.getPassKey()) || ( this.getPassKey()!=null && castOther.getPassKey()!=null && this.getPassKey().equals(castOther.getPassKey()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getAccountId() == null ? 0 : this.getAccountId().hashCode() );
         result = 37 * result + ( getCreateUserCode() == null ? 0 : this.getCreateUserCode().hashCode() );
         result = 37 * result + ( getCreateUserName() == null ? 0 : this.getCreateUserName().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         result = 37 * result + ( getAccountName() == null ? 0 : this.getAccountName().hashCode() );
         result = 37 * result + ( getRegisterEmail() == null ? 0 : this.getRegisterEmail().hashCode() );
         result = 37 * result + ( getProviderType() == null ? 0 : this.getProviderType().hashCode() );
         result = 37 * result + ( getAccountCode() == null ? 0 : this.getAccountCode().hashCode() );
         result = 37 * result + ( getAccountType() == null ? 0 : this.getAccountType().hashCode() );
         result = 37 * result + ( getIsDefault() == null ? 0 : this.getIsDefault().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         result = 37 * result + ( getPassKey() == null ? 0 : this.getPassKey().hashCode() );
         return result;
   }   





}
