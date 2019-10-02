package com.joymain.jecs.fi.model;
// Generated 2016-2-15 16:53:34 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JFI_BUSINESS_NUM"
 *     
 */

public class JfiBusinessNum extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long businessId;
    private Integer paymentCompany;
    private String merchantId;
    private String merchantName;
    private Integer merchantType;
    private String password;
    private String password2;
    private String busicode;
    private String address;
    private Long maxMoney;
    private Integer merchantStatus;
    private String createName;
    private String createTime;
    private String operateName;
    private String operateTime;


    // Constructors

    /** default constructor */
    public JfiBusinessNum() {
    }

    
    /** full constructor */
    public JfiBusinessNum(Integer paymentCompany, String merchantId, String merchantName, Integer merchantType, String password, String password2, String busicode, String address, Long maxMoney, Integer merchantStatus, String createName, String createTime, String operateName, String operateTime) {
        this.paymentCompany = paymentCompany;
        this.merchantId = merchantId;
        this.merchantName = merchantName;
        this.merchantType = merchantType;
        this.password = password;
        this.password2 = password2;
        this.busicode = busicode;
        this.address = address;
        this.maxMoney = maxMoney;
        this.merchantStatus = merchantStatus;
        this.createName = createName;
        this.createTime = createTime;
        this.operateName = operateName;
        this.operateTime = operateTime;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.Long"
     *             column="BUSINESS_ID"
     *         
     */

    public Long getBusinessId() {
        return this.businessId;
    }
    
    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }
    /**       
     *      *            @hibernate.property
     *             column="PAYMENT_COMPANY"
     *             length="2"
     *         
     */

    public Integer getPaymentCompany() {
        return this.paymentCompany;
    }
    
    public void setPaymentCompany(Integer paymentCompany) {
        this.paymentCompany = paymentCompany;
    }
    /**       
     *      *            @hibernate.property
     *             column="MERCHANT_ID"
     *             length="128"
     *         
     */

    public String getMerchantId() {
        return this.merchantId;
    }
    
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
    /**       
     *      *            @hibernate.property
     *             column="MERCHANT_NAME"
     *             length="256"
     *         
     */

    public String getMerchantName() {
        return this.merchantName;
    }
    
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }
    /**       
     *      *            @hibernate.property
     *             column="MERCHANT_TYPE"
     *             length="2"
     *         
     */

    public Integer getMerchantType() {
        return this.merchantType;
    }
    
    public void setMerchantType(Integer merchantType) {
        this.merchantType = merchantType;
    }
    /**       
     *      *            @hibernate.property
     *             column="PASSWORD"
     *             length="1024"
     *         
     */

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    /**       
     *      *            @hibernate.property
     *             column="PASSWORD2"
     *             length="1024"
     *         
     */

    public String getPassword2() {
        return this.password2;
    }
    
    public void setPassword2(String password2) {
        this.password2 = password2;
    }
    /**       
     *      *            @hibernate.property
     *             column="BUSICODE"
     *             length="256"
     *         
     */

    public String getBusicode() {
        return this.busicode;
    }
    
    public void setBusicode(String busicode) {
        this.busicode = busicode;
    }
    /**       
     *      *            @hibernate.property
     *             column="ADDRESS"
     *             length="256"
     *         
     */

    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    /**       
     *      *            @hibernate.property
     *             column="MAX_MONEY"
     *             length="12"
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
     *             column="MERCHANT_STATUS"
     *             length="2"
     *         
     */

    public Integer getMerchantStatus() {
        return this.merchantStatus;
    }
    
    public void setMerchantStatus(Integer merchantStatus) {
        this.merchantStatus = merchantStatus;
    }
    /**       
     *      *            @hibernate.property
     *             column="CREATE_NAME"
     *             length="256"
     *         
     */

    public String getCreateName() {
        return this.createName;
    }
    
    public void setCreateName(String createName) {
        this.createName = createName;
    }
    /**       
     *      *            @hibernate.property
     *             column="CREATE_TIME"
     *             length="256"
     *         
     */

    public String getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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
     *             length="256"
     *         
     */

    public String getOperateTime() {
        return this.operateTime;
    }
    
    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("paymentCompany").append("='").append(getPaymentCompany()).append("' ");			
      buffer.append("merchantId").append("='").append(getMerchantId()).append("' ");			
      buffer.append("merchantName").append("='").append(getMerchantName()).append("' ");			
      buffer.append("merchantType").append("='").append(getMerchantType()).append("' ");			
      buffer.append("password").append("='").append(getPassword()).append("' ");			
      buffer.append("password2").append("='").append(getPassword2()).append("' ");			
      buffer.append("busicode").append("='").append(getBusicode()).append("' ");			
      buffer.append("address").append("='").append(getAddress()).append("' ");			
      buffer.append("maxMoney").append("='").append(getMaxMoney()).append("' ");			
      buffer.append("merchantStatus").append("='").append(getMerchantStatus()).append("' ");			
      buffer.append("createName").append("='").append(getCreateName()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("operateName").append("='").append(getOperateName()).append("' ");			
      buffer.append("operateTime").append("='").append(getOperateTime()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JfiBusinessNum) ) return false;
		 JfiBusinessNum castOther = ( JfiBusinessNum ) other; 
         
		 return ( (this.getBusinessId()==castOther.getBusinessId()) || ( this.getBusinessId()!=null && castOther.getBusinessId()!=null && this.getBusinessId().equals(castOther.getBusinessId()) ) )
 && ( (this.getPaymentCompany()==castOther.getPaymentCompany()) || ( this.getPaymentCompany()!=null && castOther.getPaymentCompany()!=null && this.getPaymentCompany().equals(castOther.getPaymentCompany()) ) )
 && ( (this.getMerchantId()==castOther.getMerchantId()) || ( this.getMerchantId()!=null && castOther.getMerchantId()!=null && this.getMerchantId().equals(castOther.getMerchantId()) ) )
 && ( (this.getMerchantName()==castOther.getMerchantName()) || ( this.getMerchantName()!=null && castOther.getMerchantName()!=null && this.getMerchantName().equals(castOther.getMerchantName()) ) )
 && ( (this.getMerchantType()==castOther.getMerchantType()) || ( this.getMerchantType()!=null && castOther.getMerchantType()!=null && this.getMerchantType().equals(castOther.getMerchantType()) ) )
 && ( (this.getPassword()==castOther.getPassword()) || ( this.getPassword()!=null && castOther.getPassword()!=null && this.getPassword().equals(castOther.getPassword()) ) )
 && ( (this.getPassword2()==castOther.getPassword2()) || ( this.getPassword2()!=null && castOther.getPassword2()!=null && this.getPassword2().equals(castOther.getPassword2()) ) )
 && ( (this.getBusicode()==castOther.getBusicode()) || ( this.getBusicode()!=null && castOther.getBusicode()!=null && this.getBusicode().equals(castOther.getBusicode()) ) )
 && ( (this.getAddress()==castOther.getAddress()) || ( this.getAddress()!=null && castOther.getAddress()!=null && this.getAddress().equals(castOther.getAddress()) ) )
 && ( (this.getMaxMoney()==castOther.getMaxMoney()) || ( this.getMaxMoney()!=null && castOther.getMaxMoney()!=null && this.getMaxMoney().equals(castOther.getMaxMoney()) ) )
 && ( (this.getMerchantStatus()==castOther.getMerchantStatus()) || ( this.getMerchantStatus()!=null && castOther.getMerchantStatus()!=null && this.getMerchantStatus().equals(castOther.getMerchantStatus()) ) )
 && ( (this.getCreateName()==castOther.getCreateName()) || ( this.getCreateName()!=null && castOther.getCreateName()!=null && this.getCreateName().equals(castOther.getCreateName()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getOperateName()==castOther.getOperateName()) || ( this.getOperateName()!=null && castOther.getOperateName()!=null && this.getOperateName().equals(castOther.getOperateName()) ) )
 && ( (this.getOperateTime()==castOther.getOperateTime()) || ( this.getOperateTime()!=null && castOther.getOperateTime()!=null && this.getOperateTime().equals(castOther.getOperateTime()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getBusinessId() == null ? 0 : this.getBusinessId().hashCode() );
         result = 37 * result + ( getPaymentCompany() == null ? 0 : this.getPaymentCompany().hashCode() );
         result = 37 * result + ( getMerchantId() == null ? 0 : this.getMerchantId().hashCode() );
         result = 37 * result + ( getMerchantName() == null ? 0 : this.getMerchantName().hashCode() );
         result = 37 * result + ( getMerchantType() == null ? 0 : this.getMerchantType().hashCode() );
         result = 37 * result + ( getPassword() == null ? 0 : this.getPassword().hashCode() );
         result = 37 * result + ( getPassword2() == null ? 0 : this.getPassword2().hashCode() );
         result = 37 * result + ( getBusicode() == null ? 0 : this.getBusicode().hashCode() );
         result = 37 * result + ( getAddress() == null ? 0 : this.getAddress().hashCode() );
         result = 37 * result + ( getMaxMoney() == null ? 0 : this.getMaxMoney().hashCode() );
         result = 37 * result + ( getMerchantStatus() == null ? 0 : this.getMerchantStatus().hashCode() );
         result = 37 * result + ( getCreateName() == null ? 0 : this.getCreateName().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getOperateName() == null ? 0 : this.getOperateName().hashCode() );
         result = 37 * result + ( getOperateTime() == null ? 0 : this.getOperateTime().hashCode() );
         return result;
   }   





}
