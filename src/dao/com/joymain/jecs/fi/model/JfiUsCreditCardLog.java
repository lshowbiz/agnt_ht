package com.joymain.jecs.fi.model;

import java.util.Date;
// Generated 2010-11-11 9:52:47 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JFI_US_CREDIT_CARD_LOG"
 *     
 */

public class JfiUsCreditCardLog extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long jucclId;
    private String jiType;
    private String userName;
    private String password;
    private String ccNumber;
    private String ccExp;
    private String amount;
    private String cvv;
    private String payment;
    
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;
    
    private String country;
    private String phone;
    private String email;
    private String response;
    private String responseText;
    private String authCode;
    private String transactionId;
    private String avsResponse;
    private String cvvResponse;
    private String orderId;
    private String typeResponse;
    private String responseCode;
    private String inc;
    private String responseStr;
    private String userCode;
    private Date createTime;


    // Constructors

    /** default constructor */
    public JfiUsCreditCardLog() {
    }

    
    /** full constructor */
    public JfiUsCreditCardLog(String jiType, String userName, String password, String ccNumber, String ccExp, String amount, String cvv, String payment, String firstName, String lastName, String address, String city, String state, String zip, String country, String phone, String email, String response, String responseText, String authCode, String transactionId, String avsResponse, String cvvResponse, String orderId, String typeResponse, String responseCode, String inc, String responseStr) {
        this.jiType = jiType;
        this.userName = userName;
        this.password = password;
        this.ccNumber = ccNumber;
        this.ccExp = ccExp;
        this.amount = amount;
        this.cvv = cvv;
        this.payment = payment;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.phone = phone;
        this.email = email;
        this.response = response;
        this.responseText = responseText;
        this.authCode = authCode;
        this.transactionId = transactionId;
        this.avsResponse = avsResponse;
        this.cvvResponse = cvvResponse;
        this.orderId = orderId;
        this.typeResponse = typeResponse;
        this.responseCode = responseCode;
        this.inc = inc;
        this.responseStr = responseStr;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.Long"
     *             column="JUCCL_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_FI"
     *         
     */

    public Long getJucclId() {
        return this.jucclId;
    }
    
    public void setJucclId(Long jucclId) {
        this.jucclId = jucclId;
    }
    /**       
     *      *            @hibernate.property
     *             column="JI_TYPE"
     *             length="4000"
     *         
     */

    public String getJiType() {
        return this.jiType;
    }
    
    public void setJiType(String jiType) {
        this.jiType = jiType;
    }
    /**       
     *      *            @hibernate.property
     *             column="USER_NAME"
     *             length="4000"
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
     *             column="PASSWORD"
     *             length="4000"
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
     *             column="CC_NUMBER"
     *             length="4000"
     *         
     */

    public String getCcNumber() {
        return this.ccNumber;
    }
    
    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }
    /**       
     *      *            @hibernate.property
     *             column="CC_EXP"
     *             length="4000"
     *         
     */

    public String getCcExp() {
        return this.ccExp;
    }
    
    public void setCcExp(String ccExp) {
        this.ccExp = ccExp;
    }
    /**       
     *      *            @hibernate.property
     *             column="AMOUNT"
     *             length="4000"
     *         
     */

    public String getAmount() {
        return this.amount;
    }
    
    public void setAmount(String amount) {
        this.amount = amount;
    }
    /**       
     *      *            @hibernate.property
     *             column="CVV"
     *             length="4000"
     *         
     */

    public String getCvv() {
        return this.cvv;
    }
    
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
    /**       
     *      *            @hibernate.property
     *             column="PAYMENT"
     *             length="4000"
     *         
     */

    public String getPayment() {
        return this.payment;
    }
    
    public void setPayment(String payment) {
        this.payment = payment;
    }
    /**       
     *      *            @hibernate.property
     *             column="FIRST_NAME"
     *             length="4000"
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
     *             length="4000"
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
     *             column="ADDRESS"
     *             length="4000"
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
     *             column="CITY"
     *             length="4000"
     *         
     */

    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    /**       
     *      *            @hibernate.property
     *             column="STATE"
     *             length="4000"
     *         
     */

    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    /**       
     *      *            @hibernate.property
     *             column="ZIP"
     *             length="4000"
     *         
     */

    public String getZip() {
        return this.zip;
    }
    
    public void setZip(String zip) {
        this.zip = zip;
    }
    /**       
     *      *            @hibernate.property
     *             column="COUNTRY"
     *             length="4000"
     *         
     */

    public String getCountry() {
        return this.country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    /**       
     *      *            @hibernate.property
     *             column="PHONE"
     *             length="4000"
     *         
     */

    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    /**       
     *      *            @hibernate.property
     *             column="EMAIL"
     *             length="4000"
     *         
     */

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    /**       
     *      *            @hibernate.property
     *             column="RESPONSE"
     *             length="4000"
     *         
     */

    public String getResponse() {
        return this.response;
    }
    
    public void setResponse(String response) {
        this.response = response;
    }
    /**       
     *      *            @hibernate.property
     *             column="RESPONSE_TEXT"
     *             length="4000"
     *         
     */

    public String getResponseText() {
        return this.responseText;
    }
    
    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }
    /**       
     *      *            @hibernate.property
     *             column="AUTH_CODE"
     *             length="4000"
     *         
     */

    public String getAuthCode() {
        return this.authCode;
    }
    
    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="TRANSACTION_ID"
     *             length="4000"
     *         
     */

    public String getTransactionId() {
        return this.transactionId;
    }
    
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    /**       
     *      *            @hibernate.property
     *             column="AVS_RESPONSE"
     *             length="4000"
     *         
     */

    public String getAvsResponse() {
        return this.avsResponse;
    }
    
    public void setAvsResponse(String avsResponse) {
        this.avsResponse = avsResponse;
    }
    /**       
     *      *            @hibernate.property
     *             column="CVV_RESPONSE"
     *             length="4000"
     *         
     */

    public String getCvvResponse() {
        return this.cvvResponse;
    }
    
    public void setCvvResponse(String cvvResponse) {
        this.cvvResponse = cvvResponse;
    }
    /**       
     *      *            @hibernate.property
     *             column="ORDER_ID"
     *             length="4000"
     *         
     */

    public String getOrderId() {
        return this.orderId;
    }
    
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    /**       
     *      *            @hibernate.property
     *             column="TYPE_RESPONSE"
     *             length="4000"
     *         
     */

    public String getTypeResponse() {
        return this.typeResponse;
    }
    
    public void setTypeResponse(String typeResponse) {
        this.typeResponse = typeResponse;
    }
    /**       
     *      *            @hibernate.property
     *             column="RESPONSE_CODE"
     *             length="4000"
     *         
     */

    public String getResponseCode() {
        return this.responseCode;
    }
    
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="INC"
     *             length="4000"
     *         
     */

    public String getInc() {
        return this.inc;
    }
    
    public void setInc(String inc) {
        this.inc = inc;
    }
    /**       
     *      *            @hibernate.property
     *             column="RESPONSE_STR"
     *             length="4000"
     *         
     */

    public String getResponseStr() {
        return this.responseStr;
    }
    
    public void setResponseStr(String responseStr) {
        this.responseStr = responseStr;
    }

    /**       
     *      *            @hibernate.property
     *             column="USER_CODE"
     *             length="20"
     *         
     */
    public String getUserCode() {
    	return userCode;
    }


    public void setUserCode(String userCode) {
    	this.userCode = userCode;
    }   

    /**       
     *      *            @hibernate.property
     *             column="CREATE_TIME"
     *             length="7"
     *         
     */
    public Date getCreateTime() {
    	return createTime;
    }


    public void setCreateTime(Date createTime) {
    	this.createTime = createTime;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("jiType").append("='").append(getJiType()).append("' ");			
      buffer.append("userName").append("='").append(getUserName()).append("' ");			
      buffer.append("password").append("='").append(getPassword()).append("' ");			
      buffer.append("ccNumber").append("='").append(getCcNumber()).append("' ");			
      buffer.append("ccExp").append("='").append(getCcExp()).append("' ");			
      buffer.append("amount").append("='").append(getAmount()).append("' ");			
      buffer.append("cvv").append("='").append(getCvv()).append("' ");			
      buffer.append("payment").append("='").append(getPayment()).append("' ");			
      buffer.append("firstName").append("='").append(getFirstName()).append("' ");			
      buffer.append("lastName").append("='").append(getLastName()).append("' ");			
      buffer.append("address").append("='").append(getAddress()).append("' ");			
      buffer.append("city").append("='").append(getCity()).append("' ");			
      buffer.append("state").append("='").append(getState()).append("' ");			
      buffer.append("zip").append("='").append(getZip()).append("' ");			
      buffer.append("country").append("='").append(getCountry()).append("' ");			
      buffer.append("phone").append("='").append(getPhone()).append("' ");			
      buffer.append("email").append("='").append(getEmail()).append("' ");			
      buffer.append("response").append("='").append(getResponse()).append("' ");			
      buffer.append("responseText").append("='").append(getResponseText()).append("' ");			
      buffer.append("authCode").append("='").append(getAuthCode()).append("' ");			
      buffer.append("transactionId").append("='").append(getTransactionId()).append("' ");			
      buffer.append("avsResponse").append("='").append(getAvsResponse()).append("' ");			
      buffer.append("cvvResponse").append("='").append(getCvvResponse()).append("' ");			
      buffer.append("orderId").append("='").append(getOrderId()).append("' ");			
      buffer.append("typeResponse").append("='").append(getTypeResponse()).append("' ");			
      buffer.append("responseCode").append("='").append(getResponseCode()).append("' ");			
      buffer.append("inc").append("='").append(getInc()).append("' ");			
      buffer.append("responseStr").append("='").append(getResponseStr()).append("' ");	
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JfiUsCreditCardLog) ) return false;
		 JfiUsCreditCardLog castOther = ( JfiUsCreditCardLog ) other; 
         
		 return ( (this.getJucclId()==castOther.getJucclId()) || ( this.getJucclId()!=null && castOther.getJucclId()!=null && this.getJucclId().equals(castOther.getJucclId()) ) )
 && ( (this.getJiType()==castOther.getJiType()) || ( this.getJiType()!=null && castOther.getJiType()!=null && this.getJiType().equals(castOther.getJiType()) ) )
 && ( (this.getUserName()==castOther.getUserName()) || ( this.getUserName()!=null && castOther.getUserName()!=null && this.getUserName().equals(castOther.getUserName()) ) )
 && ( (this.getPassword()==castOther.getPassword()) || ( this.getPassword()!=null && castOther.getPassword()!=null && this.getPassword().equals(castOther.getPassword()) ) )
 && ( (this.getCcNumber()==castOther.getCcNumber()) || ( this.getCcNumber()!=null && castOther.getCcNumber()!=null && this.getCcNumber().equals(castOther.getCcNumber()) ) )
 && ( (this.getCcExp()==castOther.getCcExp()) || ( this.getCcExp()!=null && castOther.getCcExp()!=null && this.getCcExp().equals(castOther.getCcExp()) ) )
 && ( (this.getAmount()==castOther.getAmount()) || ( this.getAmount()!=null && castOther.getAmount()!=null && this.getAmount().equals(castOther.getAmount()) ) )
 && ( (this.getCvv()==castOther.getCvv()) || ( this.getCvv()!=null && castOther.getCvv()!=null && this.getCvv().equals(castOther.getCvv()) ) )
 && ( (this.getPayment()==castOther.getPayment()) || ( this.getPayment()!=null && castOther.getPayment()!=null && this.getPayment().equals(castOther.getPayment()) ) )
 && ( (this.getFirstName()==castOther.getFirstName()) || ( this.getFirstName()!=null && castOther.getFirstName()!=null && this.getFirstName().equals(castOther.getFirstName()) ) )
 && ( (this.getLastName()==castOther.getLastName()) || ( this.getLastName()!=null && castOther.getLastName()!=null && this.getLastName().equals(castOther.getLastName()) ) )
 && ( (this.getAddress()==castOther.getAddress()) || ( this.getAddress()!=null && castOther.getAddress()!=null && this.getAddress().equals(castOther.getAddress()) ) )
 && ( (this.getCity()==castOther.getCity()) || ( this.getCity()!=null && castOther.getCity()!=null && this.getCity().equals(castOther.getCity()) ) )
 && ( (this.getState()==castOther.getState()) || ( this.getState()!=null && castOther.getState()!=null && this.getState().equals(castOther.getState()) ) )
 && ( (this.getZip()==castOther.getZip()) || ( this.getZip()!=null && castOther.getZip()!=null && this.getZip().equals(castOther.getZip()) ) )
 && ( (this.getCountry()==castOther.getCountry()) || ( this.getCountry()!=null && castOther.getCountry()!=null && this.getCountry().equals(castOther.getCountry()) ) )
 && ( (this.getPhone()==castOther.getPhone()) || ( this.getPhone()!=null && castOther.getPhone()!=null && this.getPhone().equals(castOther.getPhone()) ) )
 && ( (this.getEmail()==castOther.getEmail()) || ( this.getEmail()!=null && castOther.getEmail()!=null && this.getEmail().equals(castOther.getEmail()) ) )
 && ( (this.getResponse()==castOther.getResponse()) || ( this.getResponse()!=null && castOther.getResponse()!=null && this.getResponse().equals(castOther.getResponse()) ) )
 && ( (this.getResponseText()==castOther.getResponseText()) || ( this.getResponseText()!=null && castOther.getResponseText()!=null && this.getResponseText().equals(castOther.getResponseText()) ) )
 && ( (this.getAuthCode()==castOther.getAuthCode()) || ( this.getAuthCode()!=null && castOther.getAuthCode()!=null && this.getAuthCode().equals(castOther.getAuthCode()) ) )
 && ( (this.getTransactionId()==castOther.getTransactionId()) || ( this.getTransactionId()!=null && castOther.getTransactionId()!=null && this.getTransactionId().equals(castOther.getTransactionId()) ) )
 && ( (this.getAvsResponse()==castOther.getAvsResponse()) || ( this.getAvsResponse()!=null && castOther.getAvsResponse()!=null && this.getAvsResponse().equals(castOther.getAvsResponse()) ) )
 && ( (this.getCvvResponse()==castOther.getCvvResponse()) || ( this.getCvvResponse()!=null && castOther.getCvvResponse()!=null && this.getCvvResponse().equals(castOther.getCvvResponse()) ) )
 && ( (this.getOrderId()==castOther.getOrderId()) || ( this.getOrderId()!=null && castOther.getOrderId()!=null && this.getOrderId().equals(castOther.getOrderId()) ) )
 && ( (this.getTypeResponse()==castOther.getTypeResponse()) || ( this.getTypeResponse()!=null && castOther.getTypeResponse()!=null && this.getTypeResponse().equals(castOther.getTypeResponse()) ) )
 && ( (this.getResponseCode()==castOther.getResponseCode()) || ( this.getResponseCode()!=null && castOther.getResponseCode()!=null && this.getResponseCode().equals(castOther.getResponseCode()) ) )
 && ( (this.getInc()==castOther.getInc()) || ( this.getInc()!=null && castOther.getInc()!=null && this.getInc().equals(castOther.getInc()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getResponseStr()==castOther.getResponseStr()) || ( this.getResponseStr()!=null && castOther.getResponseStr()!=null && this.getResponseStr().equals(castOther.getResponseStr()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getJucclId() == null ? 0 : this.getJucclId().hashCode() );
         result = 37 * result + ( getJiType() == null ? 0 : this.getJiType().hashCode() );
         result = 37 * result + ( getUserName() == null ? 0 : this.getUserName().hashCode() );
         result = 37 * result + ( getPassword() == null ? 0 : this.getPassword().hashCode() );
         result = 37 * result + ( getCcNumber() == null ? 0 : this.getCcNumber().hashCode() );
         result = 37 * result + ( getCcExp() == null ? 0 : this.getCcExp().hashCode() );
         result = 37 * result + ( getAmount() == null ? 0 : this.getAmount().hashCode() );
         result = 37 * result + ( getCvv() == null ? 0 : this.getCvv().hashCode() );
         result = 37 * result + ( getPayment() == null ? 0 : this.getPayment().hashCode() );
         result = 37 * result + ( getFirstName() == null ? 0 : this.getFirstName().hashCode() );
         result = 37 * result + ( getLastName() == null ? 0 : this.getLastName().hashCode() );
         result = 37 * result + ( getAddress() == null ? 0 : this.getAddress().hashCode() );
         result = 37 * result + ( getCity() == null ? 0 : this.getCity().hashCode() );
         result = 37 * result + ( getState() == null ? 0 : this.getState().hashCode() );
         result = 37 * result + ( getZip() == null ? 0 : this.getZip().hashCode() );
         result = 37 * result + ( getCountry() == null ? 0 : this.getCountry().hashCode() );
         result = 37 * result + ( getPhone() == null ? 0 : this.getPhone().hashCode() );
         result = 37 * result + ( getEmail() == null ? 0 : this.getEmail().hashCode() );
         result = 37 * result + ( getResponse() == null ? 0 : this.getResponse().hashCode() );
         result = 37 * result + ( getResponseText() == null ? 0 : this.getResponseText().hashCode() );
         result = 37 * result + ( getAuthCode() == null ? 0 : this.getAuthCode().hashCode() );
         result = 37 * result + ( getTransactionId() == null ? 0 : this.getTransactionId().hashCode() );
         result = 37 * result + ( getAvsResponse() == null ? 0 : this.getAvsResponse().hashCode() );
         result = 37 * result + ( getCvvResponse() == null ? 0 : this.getCvvResponse().hashCode() );
         result = 37 * result + ( getOrderId() == null ? 0 : this.getOrderId().hashCode() );
         result = 37 * result + ( getTypeResponse() == null ? 0 : this.getTypeResponse().hashCode() );
         result = 37 * result + ( getResponseCode() == null ? 0 : this.getResponseCode().hashCode() );
         result = 37 * result + ( getInc() == null ? 0 : this.getInc().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getResponseStr() == null ? 0 : this.getResponseStr().hashCode() );
         return result;
   }






}
