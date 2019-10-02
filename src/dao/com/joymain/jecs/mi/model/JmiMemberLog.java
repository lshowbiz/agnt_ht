package com.joymain.jecs.mi.model;
// Generated 2015-12-28 15:49:05 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JMI_MEMBER_LOG"
 *     
 */

public class JmiMemberLog extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long logId;
    private String userCode;
    private String userName;
    private String beforeBank;
    private String beforeBankaddress;
    private String beforeBankbook;
    private String beforeBankcard;
    private String beforeBankprovince;
    private String beforeBankcity;
    private Date logTime;
    private String logType;
    private String logUserCode;
    private String afterBank;
    private String afterBankaddress;
    private String afterBankbook;
    private String afterBankcard;
    private String afterBankprovince;
    private String afterBankcity;


    // Constructors

    /** default constructor */
    public JmiMemberLog() {
    }

    
    /** full constructor */
    public JmiMemberLog(String userCode, String userName, String beforeBank, String beforeBankaddress, String beforeBankbook, String beforeBankcard, String beforeBankprovince, String beforeBankcity, Date logTime, String logType, String logUserCode, String afterBank, String afterBankaddress, String afterBankbook, String afterBankcard, String afterBankprovince, String afterBankcity) {
        this.userCode = userCode;
        this.userName = userName;
        this.beforeBank = beforeBank;
        this.beforeBankaddress = beforeBankaddress;
        this.beforeBankbook = beforeBankbook;
        this.beforeBankcard = beforeBankcard;
        this.beforeBankprovince = beforeBankprovince;
        this.beforeBankcity = beforeBankcity;
        this.logTime = logTime;
        this.logType = logType;
        this.logUserCode = logUserCode;
        this.afterBank = afterBank;
        this.afterBankaddress = afterBankaddress;
        this.afterBankbook = afterBankbook;
        this.afterBankcard = afterBankcard;
        this.afterBankprovince = afterBankprovince;
        this.afterBankcity = afterBankcity;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="LOG_ID" 
     *@hibernate.generator-param name="sequence" value="SEQ_MI"
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
     *             column="USER_NAME"
     *             length="100"
     *         
     */

    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**       
     *      *            @hibernate.property
     *             column="BEFORE_BANK"
     *             length="80"
     *         
     */

    public String getBeforeBank() {
        return this.beforeBank;
    }
    
    public void setBeforeBank(String beforeBank) {
        this.beforeBank = beforeBank;
    }
    /**       
     *      *            @hibernate.property
     *             column="BEFORE_BANKADDRESS"
     *             length="300"
     *         
     */

    public String getBeforeBankaddress() {
        return this.beforeBankaddress;
    }
    
    public void setBeforeBankaddress(String beforeBankaddress) {
        this.beforeBankaddress = beforeBankaddress;
    }
    /**       
     *      *            @hibernate.property
     *             column="BEFORE_BANKBOOK"
     *             length="100"
     *         
     */

    public String getBeforeBankbook() {
        return this.beforeBankbook;
    }
    
    public void setBeforeBankbook(String beforeBankbook) {
        this.beforeBankbook = beforeBankbook;
    }
    /**       
     *      *            @hibernate.property
     *             column="BEFORE_BANKCARD"
     *             length="100"
     *         
     */

    public String getBeforeBankcard() {
        return this.beforeBankcard;
    }
    
    public void setBeforeBankcard(String beforeBankcard) {
        this.beforeBankcard = beforeBankcard;
    }
    /**       
     *      *            @hibernate.property
     *             column="BEFORE_BANKPROVINCE"
     *             length="20"
     *         
     */

    public String getBeforeBankprovince() {
        return this.beforeBankprovince;
    }
    
    public void setBeforeBankprovince(String beforeBankprovince) {
        this.beforeBankprovince = beforeBankprovince;
    }
    /**       
     *      *            @hibernate.property
     *             column="BEFORE_BANKCITY"
     *             length="20"
     *         
     */

    public String getBeforeBankcity() {
        return this.beforeBankcity;
    }
    
    public void setBeforeBankcity(String beforeBankcity) {
        this.beforeBankcity = beforeBankcity;
    }
    /**       
     *      *            @hibernate.property
     *             column="LOG_TIME"
     *             length="7"
     *         
     */

    public Date getLogTime() {
        return this.logTime;
    }
    
    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="LOG_TYPE"
     *             length="20"
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
     *             column="LOG_USER_CODE"
     *             length="50"
     *         
     */

    public String getLogUserCode() {
        return this.logUserCode;
    }
    
    public void setLogUserCode(String logUserCode) {
        this.logUserCode = logUserCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="AFTER_BANK"
     *             length="80"
     *         
     */

    public String getAfterBank() {
        return this.afterBank;
    }
    
    public void setAfterBank(String afterBank) {
        this.afterBank = afterBank;
    }
    /**       
     *      *            @hibernate.property
     *             column="AFTER_BANKADDRESS"
     *             length="300"
     *         
     */

    public String getAfterBankaddress() {
        return this.afterBankaddress;
    }
    
    public void setAfterBankaddress(String afterBankaddress) {
        this.afterBankaddress = afterBankaddress;
    }
    /**       
     *      *            @hibernate.property
     *             column="AFTER_BANKBOOK"
     *             length="100"
     *         
     */

    public String getAfterBankbook() {
        return this.afterBankbook;
    }
    
    public void setAfterBankbook(String afterBankbook) {
        this.afterBankbook = afterBankbook;
    }
    /**       
     *      *            @hibernate.property
     *             column="AFTER_BANKCARD"
     *             length="100"
     *         
     */

    public String getAfterBankcard() {
        return this.afterBankcard;
    }
    
    public void setAfterBankcard(String afterBankcard) {
        this.afterBankcard = afterBankcard;
    }
    /**       
     *      *            @hibernate.property
     *             column="AFTER_BANKPROVINCE"
     *             length="20"
     *         
     */

    public String getAfterBankprovince() {
        return this.afterBankprovince;
    }
    
    public void setAfterBankprovince(String afterBankprovince) {
        this.afterBankprovince = afterBankprovince;
    }
    /**       
     *      *            @hibernate.property
     *             column="AFTER_BANKCITY"
     *             length="20"
     *         
     */

    public String getAfterBankcity() {
        return this.afterBankcity;
    }
    
    public void setAfterBankcity(String afterBankcity) {
        this.afterBankcity = afterBankcity;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("userName").append("='").append(getUserName()).append("' ");			
      buffer.append("beforeBank").append("='").append(getBeforeBank()).append("' ");			
      buffer.append("beforeBankaddress").append("='").append(getBeforeBankaddress()).append("' ");			
      buffer.append("beforeBankbook").append("='").append(getBeforeBankbook()).append("' ");			
      buffer.append("beforeBankcard").append("='").append(getBeforeBankcard()).append("' ");			
      buffer.append("beforeBankprovince").append("='").append(getBeforeBankprovince()).append("' ");			
      buffer.append("beforeBankcity").append("='").append(getBeforeBankcity()).append("' ");			
      buffer.append("logTime").append("='").append(getLogTime()).append("' ");			
      buffer.append("logType").append("='").append(getLogType()).append("' ");			
      buffer.append("logUserCode").append("='").append(getLogUserCode()).append("' ");			
      buffer.append("afterBank").append("='").append(getAfterBank()).append("' ");			
      buffer.append("afterBankaddress").append("='").append(getAfterBankaddress()).append("' ");			
      buffer.append("afterBankbook").append("='").append(getAfterBankbook()).append("' ");			
      buffer.append("afterBankcard").append("='").append(getAfterBankcard()).append("' ");			
      buffer.append("afterBankprovince").append("='").append(getAfterBankprovince()).append("' ");			
      buffer.append("afterBankcity").append("='").append(getAfterBankcity()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JmiMemberLog) ) return false;
		 JmiMemberLog castOther = ( JmiMemberLog ) other; 
         
		 return ( (this.getLogId()==castOther.getLogId()) || ( this.getLogId()!=null && castOther.getLogId()!=null && this.getLogId().equals(castOther.getLogId()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getUserName()==castOther.getUserName()) || ( this.getUserName()!=null && castOther.getUserName()!=null && this.getUserName().equals(castOther.getUserName()) ) )
 && ( (this.getBeforeBank()==castOther.getBeforeBank()) || ( this.getBeforeBank()!=null && castOther.getBeforeBank()!=null && this.getBeforeBank().equals(castOther.getBeforeBank()) ) )
 && ( (this.getBeforeBankaddress()==castOther.getBeforeBankaddress()) || ( this.getBeforeBankaddress()!=null && castOther.getBeforeBankaddress()!=null && this.getBeforeBankaddress().equals(castOther.getBeforeBankaddress()) ) )
 && ( (this.getBeforeBankbook()==castOther.getBeforeBankbook()) || ( this.getBeforeBankbook()!=null && castOther.getBeforeBankbook()!=null && this.getBeforeBankbook().equals(castOther.getBeforeBankbook()) ) )
 && ( (this.getBeforeBankcard()==castOther.getBeforeBankcard()) || ( this.getBeforeBankcard()!=null && castOther.getBeforeBankcard()!=null && this.getBeforeBankcard().equals(castOther.getBeforeBankcard()) ) )
 && ( (this.getBeforeBankprovince()==castOther.getBeforeBankprovince()) || ( this.getBeforeBankprovince()!=null && castOther.getBeforeBankprovince()!=null && this.getBeforeBankprovince().equals(castOther.getBeforeBankprovince()) ) )
 && ( (this.getBeforeBankcity()==castOther.getBeforeBankcity()) || ( this.getBeforeBankcity()!=null && castOther.getBeforeBankcity()!=null && this.getBeforeBankcity().equals(castOther.getBeforeBankcity()) ) )
 && ( (this.getLogTime()==castOther.getLogTime()) || ( this.getLogTime()!=null && castOther.getLogTime()!=null && this.getLogTime().equals(castOther.getLogTime()) ) )
 && ( (this.getLogType()==castOther.getLogType()) || ( this.getLogType()!=null && castOther.getLogType()!=null && this.getLogType().equals(castOther.getLogType()) ) )
 && ( (this.getLogUserCode()==castOther.getLogUserCode()) || ( this.getLogUserCode()!=null && castOther.getLogUserCode()!=null && this.getLogUserCode().equals(castOther.getLogUserCode()) ) )
 && ( (this.getAfterBank()==castOther.getAfterBank()) || ( this.getAfterBank()!=null && castOther.getAfterBank()!=null && this.getAfterBank().equals(castOther.getAfterBank()) ) )
 && ( (this.getAfterBankaddress()==castOther.getAfterBankaddress()) || ( this.getAfterBankaddress()!=null && castOther.getAfterBankaddress()!=null && this.getAfterBankaddress().equals(castOther.getAfterBankaddress()) ) )
 && ( (this.getAfterBankbook()==castOther.getAfterBankbook()) || ( this.getAfterBankbook()!=null && castOther.getAfterBankbook()!=null && this.getAfterBankbook().equals(castOther.getAfterBankbook()) ) )
 && ( (this.getAfterBankcard()==castOther.getAfterBankcard()) || ( this.getAfterBankcard()!=null && castOther.getAfterBankcard()!=null && this.getAfterBankcard().equals(castOther.getAfterBankcard()) ) )
 && ( (this.getAfterBankprovince()==castOther.getAfterBankprovince()) || ( this.getAfterBankprovince()!=null && castOther.getAfterBankprovince()!=null && this.getAfterBankprovince().equals(castOther.getAfterBankprovince()) ) )
 && ( (this.getAfterBankcity()==castOther.getAfterBankcity()) || ( this.getAfterBankcity()!=null && castOther.getAfterBankcity()!=null && this.getAfterBankcity().equals(castOther.getAfterBankcity()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getLogId() == null ? 0 : this.getLogId().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getUserName() == null ? 0 : this.getUserName().hashCode() );
         result = 37 * result + ( getBeforeBank() == null ? 0 : this.getBeforeBank().hashCode() );
         result = 37 * result + ( getBeforeBankaddress() == null ? 0 : this.getBeforeBankaddress().hashCode() );
         result = 37 * result + ( getBeforeBankbook() == null ? 0 : this.getBeforeBankbook().hashCode() );
         result = 37 * result + ( getBeforeBankcard() == null ? 0 : this.getBeforeBankcard().hashCode() );
         result = 37 * result + ( getBeforeBankprovince() == null ? 0 : this.getBeforeBankprovince().hashCode() );
         result = 37 * result + ( getBeforeBankcity() == null ? 0 : this.getBeforeBankcity().hashCode() );
         result = 37 * result + ( getLogTime() == null ? 0 : this.getLogTime().hashCode() );
         result = 37 * result + ( getLogType() == null ? 0 : this.getLogType().hashCode() );
         result = 37 * result + ( getLogUserCode() == null ? 0 : this.getLogUserCode().hashCode() );
         result = 37 * result + ( getAfterBank() == null ? 0 : this.getAfterBank().hashCode() );
         result = 37 * result + ( getAfterBankaddress() == null ? 0 : this.getAfterBankaddress().hashCode() );
         result = 37 * result + ( getAfterBankbook() == null ? 0 : this.getAfterBankbook().hashCode() );
         result = 37 * result + ( getAfterBankcard() == null ? 0 : this.getAfterBankcard().hashCode() );
         result = 37 * result + ( getAfterBankprovince() == null ? 0 : this.getAfterBankprovince().hashCode() );
         result = 37 * result + ( getAfterBankcity() == null ? 0 : this.getAfterBankcity().hashCode() );
         return result;
   }   





}
