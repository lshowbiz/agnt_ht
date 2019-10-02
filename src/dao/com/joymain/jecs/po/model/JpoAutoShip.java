package com.joymain.jecs.po.model;
// Generated 2010-6-23 3:13:32 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JPO_AUTO_SHIP"
 *     
 */

public class JpoAutoShip extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long jasId;
    private String companyCode;
    private String userCode;
    private Date jasActionTime;
    private long moId;
    private String createUser;
    private Date createTime;
    private String status;
    private Date checkTime;
    private String remark;
    private String firstName;
    private String lastName;
    private String cardCvv;
    private String cardNumber;
    private String expireDate;
    private int payCause;
    private String returnData;


    // Constructors

    /** default constructor */
    public JpoAutoShip() {
    }

	/** minimal constructor */
    public JpoAutoShip(String companyCode, String userCode, Date jasActionTime, long moId, String createUser, Date createTime, String status, String firstName, String lastName, String cardCvv, String cardNumber, String expireDate, int payCause) {
        this.companyCode = companyCode;
        this.userCode = userCode;
        this.jasActionTime = jasActionTime;
        this.moId = moId;
        this.createUser = createUser;
        this.createTime = createTime;
        this.status = status;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cardCvv = cardCvv;
        this.cardNumber = cardNumber;
        this.expireDate = expireDate;
        this.payCause = payCause;
    }
    
    /** full constructor */
    public JpoAutoShip(String companyCode, String userCode, Date jasActionTime, long moId, String createUser, Date createTime, String status, Date checkTime, String remark, String firstName, String lastName, String cardCvv, String cardNumber, String expireDate, int payCause, String returnData) {
        this.companyCode = companyCode;
        this.userCode = userCode;
        this.jasActionTime = jasActionTime;
        this.moId = moId;
        this.createUser = createUser;
        this.createTime = createTime;
        this.status = status;
        this.checkTime = checkTime;
        this.remark = remark;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cardCvv = cardCvv;
        this.cardNumber = cardNumber;
        this.expireDate = expireDate;
        this.payCause = payCause;
        this.returnData = returnData;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.Long"
     *             column="JAS_ID"
     *         
     */

    public Long getJasId() {
        return this.jasId;
    }
    
    public void setJasId(Long jasId) {
        this.jasId = jasId;
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
     *             column="JAS_ACTION_TIME"
     *             length="7"
     *             not-null="true"
     *         
     */

    public Date getJasActionTime() {
        return this.jasActionTime;
    }
    
    public void setJasActionTime(Date jasActionTime) {
        this.jasActionTime = jasActionTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="MO_ID"
     *             length="10"
     *             not-null="true"
     *         
     */

    public long getMoId() {
        return this.moId;
    }
    
    public void setMoId(long moId) {
        this.moId = moId;
    }
    /**       
     *      *            @hibernate.property
     *             column="CREATE_USER"
     *             length="30"
     *             not-null="true"
     *         
     */

    public String getCreateUser() {
        return this.createUser;
    }
    
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
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
     *             column="STATUS"
     *             length="2"
     *             not-null="true"
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
     *             column="CHECK_TIME"
     *             length="7"
     *         
     */

    public Date getCheckTime() {
        return this.checkTime;
    }
    
    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="REMARK"
     *             length="3500"
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
     *             column="FIRST_NAME"
     *             length="100"
     *             not-null="true"
     *         
     */

    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**       
     *      *            @hibernate.property
     *             column="LAST_NAME"
     *             length="100"
     *             not-null="true"
     *         
     */

    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**       
     *      *            @hibernate.property
     *             column="CARD_CVV"
     *             length="50"
     *             not-null="true"
     *         
     */

    public String getCardCvv() {
        return this.cardCvv;
    }
    
    public void setCardCvv(String cardCvv) {
        this.cardCvv = cardCvv;
    }
    /**       
     *      *            @hibernate.property
     *             column="CARD_NUMBER"
     *             length="50"
     *             not-null="true"
     *         
     */

    public String getCardNumber() {
        return this.cardNumber;
    }
    
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    /**       
     *      *            @hibernate.property
     *             column="EXPIRE_DATE"
     *             length="20"
     *             not-null="true"
     *         
     */

    public String getExpireDate() {
        return this.expireDate;
    }
    
    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }
    /**       
     *      *            @hibernate.property
     *             column="PAY_CAUSE"
     *             length="4"
     *             not-null="true"
     *         
     */

    public int getPayCause() {
        return this.payCause;
    }
    
    public void setPayCause(int payCause) {
        this.payCause = payCause;
    }
    /**       
     *      *            @hibernate.property
     *             column="RETURN_DATA"
     *             length="3500"
     *         
     */

    public String getReturnData() {
        return this.returnData;
    }
    
    public void setReturnData(String returnData) {
        this.returnData = returnData;
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
      buffer.append("jasActionTime").append("='").append(getJasActionTime()).append("' ");			
      buffer.append("moId").append("='").append(getMoId()).append("' ");			
      buffer.append("createUser").append("='").append(getCreateUser()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("status").append("='").append(getStatus()).append("' ");			
      buffer.append("checkTime").append("='").append(getCheckTime()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");			
      buffer.append("firstName").append("='").append(getFirstName()).append("' ");			
      buffer.append("lastName").append("='").append(getLastName()).append("' ");			
      buffer.append("cardCvv").append("='").append(getCardCvv()).append("' ");			
      buffer.append("cardNumber").append("='").append(getCardNumber()).append("' ");			
      buffer.append("expireDate").append("='").append(getExpireDate()).append("' ");			
      buffer.append("payCause").append("='").append(getPayCause()).append("' ");			
      buffer.append("returnData").append("='").append(getReturnData()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JpoAutoShip) ) return false;
		 JpoAutoShip castOther = ( JpoAutoShip ) other; 
         
		 return ( (this.getJasId()==castOther.getJasId()) || ( this.getJasId()!=null && castOther.getJasId()!=null && this.getJasId().equals(castOther.getJasId()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getJasActionTime()==castOther.getJasActionTime()) || ( this.getJasActionTime()!=null && castOther.getJasActionTime()!=null && this.getJasActionTime().equals(castOther.getJasActionTime()) ) )
 && (this.getMoId()==castOther.getMoId())
 && ( (this.getCreateUser()==castOther.getCreateUser()) || ( this.getCreateUser()!=null && castOther.getCreateUser()!=null && this.getCreateUser().equals(castOther.getCreateUser()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
 && ( (this.getCheckTime()==castOther.getCheckTime()) || ( this.getCheckTime()!=null && castOther.getCheckTime()!=null && this.getCheckTime().equals(castOther.getCheckTime()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
 && ( (this.getFirstName()==castOther.getFirstName()) || ( this.getFirstName()!=null && castOther.getFirstName()!=null && this.getFirstName().equals(castOther.getFirstName()) ) )
 && ( (this.getLastName()==castOther.getLastName()) || ( this.getLastName()!=null && castOther.getLastName()!=null && this.getLastName().equals(castOther.getLastName()) ) )
 && ( (this.getCardCvv()==castOther.getCardCvv()) || ( this.getCardCvv()!=null && castOther.getCardCvv()!=null && this.getCardCvv().equals(castOther.getCardCvv()) ) )
 && ( (this.getCardNumber()==castOther.getCardNumber()) || ( this.getCardNumber()!=null && castOther.getCardNumber()!=null && this.getCardNumber().equals(castOther.getCardNumber()) ) )
 && ( (this.getExpireDate()==castOther.getExpireDate()) || ( this.getExpireDate()!=null && castOther.getExpireDate()!=null && this.getExpireDate().equals(castOther.getExpireDate()) ) )
 && (this.getPayCause()==castOther.getPayCause())
 && ( (this.getReturnData()==castOther.getReturnData()) || ( this.getReturnData()!=null && castOther.getReturnData()!=null && this.getReturnData().equals(castOther.getReturnData()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getJasId() == null ? 0 : this.getJasId().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getJasActionTime() == null ? 0 : this.getJasActionTime().hashCode() );
         result = 37 * result + (int) this.getMoId();
         result = 37 * result + ( getCreateUser() == null ? 0 : this.getCreateUser().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         result = 37 * result + ( getCheckTime() == null ? 0 : this.getCheckTime().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         result = 37 * result + ( getFirstName() == null ? 0 : this.getFirstName().hashCode() );
         result = 37 * result + ( getLastName() == null ? 0 : this.getLastName().hashCode() );
         result = 37 * result + ( getCardCvv() == null ? 0 : this.getCardCvv().hashCode() );
         result = 37 * result + ( getCardNumber() == null ? 0 : this.getCardNumber().hashCode() );
         result = 37 * result + ( getExpireDate() == null ? 0 : this.getExpireDate().hashCode() );
         result = 37 * result + this.getPayCause();
         result = 37 * result + ( getReturnData() == null ? 0 : this.getReturnData().hashCode() );
         return result;
   }   





}
