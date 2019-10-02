package com.joymain.jecs.fi.model;
// Generated 2011-4-26 15:34:36 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JFI_CREDIT_CARD_LOG"
 *     
 */

public class JfiCreditCardLog extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long logId;
    private String companyCode;
    private String userCode;
    
    private String firstName;
    private String lastName;
    private String company;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String country;
    
    private String sFirstName;
    private String sLastName;
    private String sCompany;
    private String sAddress;
    private String sCity;
    private String sState;
    private String sZip;
    private String sCountry;
    
    private String phone;
    private String fax;
    private String email;
    private String customerIp;
    private String cardType;
    private String cardNumber;
    private String expireDate;
    private Double payAmount;
    private Date payTime;
    private Integer payResult;
    private String memberOrderNo;
    private Integer payCause;
    private String returnData;


    // Constructors

    /** default constructor */
    public JfiCreditCardLog() {
    }
   
    // Property accessors
    /**
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="LOG_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_FI"
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
     *             column="FIRST_NAME"
     *             length="100"
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
     *             column="COMPANY"
     *             length="50"
     *         
     */
    public String getCompany() {
    	return company;
    }

	public void setCompany(String company) {
    	this.company = company;
    }

	/**       
     *      *            @hibernate.property
     *             column="ADDRESS"
     *             length="60"
     *         
     */
	public String getAddress() {
    	return address;
    }

	public void setAddress(String address) {
    	this.address = address;
    }

	/**       
     *      *            @hibernate.property
     *             column="CITY"
     *             length="40"
     *         
     */
	public String getCity() {
    	return city;
    }

	public void setCity(String city) {
    	this.city = city;
    }

	/**       
     *      *            @hibernate.property
     *             column="STATE"
     *             length="40"
     *         
     */
	public String getState() {
    	return state;
    }

	public void setState(String state) {
    	this.state = state;
    }

	/**       
     *      *            @hibernate.property
     *             column="ZIP"
     *             length="20"
     *         
     */
	public String getZip() {
    	return zip;
    }

	public void setZip(String zip) {
    	this.zip = zip;
    }

	/**       
     *      *            @hibernate.property
     *             column="COUNTRY"
     *             length="60"
     *         
     */
	public String getCountry() {
    	return country;
    }

	public void setCountry(String country) {
    	this.country = country;
    }

	/**       
     *      *            @hibernate.property
     *             column="S_FIRST_NAME"
     *             length="50"
     *         
     */
	public String getSFirstName() {
    	return sFirstName;
    }

	public void setSFirstName(String firstName) {
    	sFirstName = firstName;
    }

	/**       
     *      *            @hibernate.property
     *             column="S_LAST_NAME"
     *             length="50"
     *         
     */
	public String getSLastName() {
    	return sLastName;
    }

	public void setSLastName(String lastName) {
    	sLastName = lastName;
    }

	/**       
     *      *            @hibernate.property
     *             column="S_COMPANY"
     *             length="50"
     *         
     */
	public String getSCompany() {
    	return sCompany;
    }

	public void setSCompany(String company) {
    	sCompany = company;
    }

	/**       
     *      *            @hibernate.property
     *             column="S_ADDRESS"
     *             length="50"
     *         
     */
	public String getSAddress() {
    	return sAddress;
    }

	public void setSAddress(String address) {
    	sAddress = address;
    }

	/**       
     *      *            @hibernate.property
     *             column="S_CITY"
     *             length="50"
     *         
     */
	public String getSCity() {
    	return sCity;
    }

	public void setSCity(String city) {
    	sCity = city;
    }

	/**       
     *      *            @hibernate.property
     *             column="S_STATE"
     *             length="50"
     *         
     */
	public String getSState() {
    	return sState;
    }

	public void setSState(String state) {
    	sState = state;
    }

	/**       
     *      *            @hibernate.property
     *             column="S_ZIP"
     *             length="50"
     *         
     */
	public String getSZip() {
    	return sZip;
    }

	public void setSZip(String zip) {
    	sZip = zip;
    }

	/**       
     *      *            @hibernate.property
     *             column="S_COUNTRY"
     *             length="50"
     *         
     */
	public String getSCountry() {
    	return sCountry;
    }

	public void setSCountry(String country) {
    	sCountry = country;
    }

	/**       
     *      *            @hibernate.property
     *             column="PHONE"
     *             length="25"
     *         
     */
	public String getPhone() {
    	return phone;
    }

	public void setPhone(String phone) {
    	this.phone = phone;
    }

	/**       
     *      *            @hibernate.property
     *             column="FAX"
     *             length="25"
     *         
     */
	public String getFax() {
    	return fax;
    }

	public void setFax(String fax) {
    	this.fax = fax;
    }

	/**       
     *      *            @hibernate.property
     *             column="EMAIL"
     *             length="255"
     *         
     */
	public String getEmail() {
    	return email;
    }

	public void setEmail(String email) {
    	this.email = email;
    }

	/**       
     *      *            @hibernate.property
     *             column="CUSTOMER_IP"
     *             length="15"
     *         
     */
	public String getCustomerIp() {
    	return customerIp;
    }

	public void setCustomerIp(String customerIp) {
    	this.customerIp = customerIp;
    }

	/**       
     *      *            @hibernate.property
     *             column="CARD_TYPE"
     *             length="2"
     *         
     */
    public String getCardType() {
    	return cardType;
    }

	public void setCardType(String cardType) {
    	this.cardType = cardType;
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
     *             column="PAY_AMOUNT"
     *             length="18"
     *             not-null="true"
     *         
     */

    public Double getPayAmount() {
        return this.payAmount;
    }
    
    public void setPayAmount(Double payAmount) {
        this.payAmount = payAmount;
    }
    /**       
     *      *            @hibernate.property
     *             column="PAY_TIME"
     *             length="7"
     *             not-null="true"
     *         
     */

    public Date getPayTime() {
        return this.payTime;
    }
    
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="PAY_RESULT"
     *             length="2"
     *             not-null="true"
     *         
     */

    public Integer getPayResult() {
        return this.payResult;
    }
    
    public void setPayResult(Integer payResult) {
        this.payResult = payResult;
    }
    /**       
     *      *            @hibernate.property
     *             column="MEMBER_ORDER_NO"
     *             length="20"
     *         
     */

    public String getMemberOrderNo() {
        return this.memberOrderNo;
    }
    
    public void setMemberOrderNo(String memberOrderNo) {
        this.memberOrderNo = memberOrderNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="PAY_CAUSE"
     *             length="4"
     *             not-null="true"
     *         
     */

    public Integer getPayCause() {
        return this.payCause;
    }
    
    public void setPayCause(Integer payCause) {
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
      buffer.append("firstName").append("='").append(getFirstName()).append("' ");			
      buffer.append("lastName").append("='").append(getLastName()).append("' ");			
      buffer.append("cardNumber").append("='").append(getCardNumber()).append("' ");			
      buffer.append("expireDate").append("='").append(getExpireDate()).append("' ");			
      buffer.append("payAmount").append("='").append(getPayAmount()).append("' ");			
      buffer.append("payTime").append("='").append(getPayTime()).append("' ");			
      buffer.append("payResult").append("='").append(getPayResult()).append("' ");			
      buffer.append("memberOrderNo").append("='").append(getMemberOrderNo()).append("' ");			
      buffer.append("payCause").append("='").append(getPayCause()).append("' ");			
      buffer.append("returnData").append("='").append(getReturnData()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JfiCreditCardLog) ) return false;
		 JfiCreditCardLog castOther = ( JfiCreditCardLog ) other; 
         
		 return ( (this.getLogId()==castOther.getLogId()) || ( this.getLogId()!=null && castOther.getLogId()!=null && this.getLogId().equals(castOther.getLogId()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getFirstName()==castOther.getFirstName()) || ( this.getFirstName()!=null && castOther.getFirstName()!=null && this.getFirstName().equals(castOther.getFirstName()) ) )
 && ( (this.getLastName()==castOther.getLastName()) || ( this.getLastName()!=null && castOther.getLastName()!=null && this.getLastName().equals(castOther.getLastName()) ) )
 && ( (this.getCardNumber()==castOther.getCardNumber()) || ( this.getCardNumber()!=null && castOther.getCardNumber()!=null && this.getCardNumber().equals(castOther.getCardNumber()) ) )
 && ( (this.getExpireDate()==castOther.getExpireDate()) || ( this.getExpireDate()!=null && castOther.getExpireDate()!=null && this.getExpireDate().equals(castOther.getExpireDate()) ) )
 && ( (this.getPayAmount()==castOther.getPayAmount()) || ( this.getPayAmount()!=null && castOther.getPayAmount()!=null && this.getPayAmount().equals(castOther.getPayAmount()) ) )
 && ( (this.getPayTime()==castOther.getPayTime()) || ( this.getPayTime()!=null && castOther.getPayTime()!=null && this.getPayTime().equals(castOther.getPayTime()) ) )
 && (this.getPayResult()==castOther.getPayResult())
 && ( (this.getMemberOrderNo()==castOther.getMemberOrderNo()) || ( this.getMemberOrderNo()!=null && castOther.getMemberOrderNo()!=null && this.getMemberOrderNo().equals(castOther.getMemberOrderNo()) ) )
 && (this.getPayCause()==castOther.getPayCause())
 && ( (this.getReturnData()==castOther.getReturnData()) || ( this.getReturnData()!=null && castOther.getReturnData()!=null && this.getReturnData().equals(castOther.getReturnData()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getLogId() == null ? 0 : this.getLogId().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getFirstName() == null ? 0 : this.getFirstName().hashCode() );
         result = 37 * result + ( getLastName() == null ? 0 : this.getLastName().hashCode() );
         result = 37 * result + ( getCardNumber() == null ? 0 : this.getCardNumber().hashCode() );
         result = 37 * result + ( getExpireDate() == null ? 0 : this.getExpireDate().hashCode() );
         result = 37 * result + ( getPayAmount() == null ? 0 : this.getPayAmount().hashCode() );
         result = 37 * result + ( getPayTime() == null ? 0 : this.getPayTime().hashCode() );
         result = 37 * result + this.getPayResult();
         result = 37 * result + ( getMemberOrderNo() == null ? 0 : this.getMemberOrderNo().hashCode() );
         result = 37 * result + this.getPayCause();
         result = 37 * result + ( getReturnData() == null ? 0 : this.getReturnData().hashCode() );
         return result;
   }   





}
