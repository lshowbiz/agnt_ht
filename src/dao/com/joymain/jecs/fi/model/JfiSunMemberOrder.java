package com.joymain.jecs.fi.model;
// Generated 2010-1-14 14:34:27 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.sql.Clob;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JFI_SUN_MEMBER_ORDER"
 *     
 */

public class JfiSunMemberOrder extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long moId;
    private String companyCode;
    private String countryCode;
    private String memberOrderNo;
    private String orderType;
    private String userCode;
    private String userName;
    private String agentNo;
    private String agentName;
    private String userSpCode;
    private BigDecimal amount;
    private BigDecimal pvAmt;
    private BigDecimal consumerAmount;
    private String payMode;
    private String orderUserCode;
    private Date orderTime;
    private String status;
    private Date checkTime;
    private String checkUserCode;
    private Date checkDate;
    private Clob remark;
    private String pickup;
    private Date submitTime;
    private String submitUserCode;
    private String submitStatus;
    private String firstName;
    private String lastName;
    private String province;
    private String city;
    private String address;
    private String postalcode;
    private String phone;
    private String email;
    private String mobiletele;
    private String district;
	private Set jfiSunMemberOrderList = new HashSet(0);
    private Set jfiSunMemberOrderFee = new HashSet(0);


    // Constructors

	/** default constructor */
    public JfiSunMemberOrder() {
    }

	/** minimal constructor */
    public JfiSunMemberOrder(String userCode, String userName, String agentNo, String agentName) {
        this.userCode = userCode;
        this.userName = userName;
        this.agentNo = agentNo;
        this.agentName = agentName;
    }
    
    /** full constructor */
    public JfiSunMemberOrder(String companyCode, String countryCode, String memberOrderNo, String orderType, String userCode, String userName, String agentNo, String agentName, String userSpCode, BigDecimal amount, BigDecimal pvAmt, BigDecimal consumerAmount, String payMode, String orderUserCode, Date orderTime, String status, Date checkTime, String checkUserCode, Date checkDate, Clob remark, String pickup, Date submitTime, String submitUserCode, String submitStatus, String firstName, String lastName, String province, String city, String address, String postalcode, String phone, String email, String mobiletele, String district) {
        this.companyCode = companyCode;
        this.countryCode = countryCode;
        this.memberOrderNo = memberOrderNo;
        this.orderType = orderType;
        this.userCode = userCode;
        this.userName = userName;
        this.agentNo = agentNo;
        this.agentName = agentName;
        this.userSpCode = userSpCode;
        this.amount = amount;
        this.pvAmt = pvAmt;
        this.consumerAmount = consumerAmount;
        this.payMode = payMode;
        this.orderUserCode = orderUserCode;
        this.orderTime = orderTime;
        this.status = status;
        this.checkTime = checkTime;
        this.checkUserCode = checkUserCode;
        this.checkDate = checkDate;
        this.remark = remark;
        this.pickup = pickup;
        this.submitTime = submitTime;
        this.submitUserCode = submitUserCode;
        this.submitStatus = submitStatus;
        this.firstName = firstName;
        this.lastName = lastName;
        this.province = province;
        this.city = city;
        this.address = address;
        this.postalcode = postalcode;
        this.phone = phone;
        this.email = email;
        this.mobiletele = mobiletele;
        this.district = district;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.Long"
     *             column="MO_ID"
     *         
     */

    public Long getMoId() {
        return this.moId;
    }
    
    public void setMoId(Long moId) {
        this.moId = moId;
    }
    /**       
     *      *            @hibernate.property
     *             column="COMPANY_CODE"
     *             length="2"
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
     *             column="COUNTRY_CODE"
     *             length="2"
     *         
     */

    public String getCountryCode() {
        return this.countryCode;
    }
    
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="MEMBER_ORDER_NO"
     *             unique="true"
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
     *             column="ORDER_TYPE"
     *             length="1"
     *         
     */

    public String getOrderType() {
        return this.orderType;
    }
    
    public void setOrderType(String orderType) {
        this.orderType = orderType;
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
     *             column="USER_NAME"
     *             length="300"
     *             not-null="true"
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
     *             column="AGENT_NO"
     *             length="20"
     *             not-null="true"
     *         
     */

    public String getAgentNo() {
        return this.agentNo;
    }
    
    public void setAgentNo(String agentNo) {
        this.agentNo = agentNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="AGENT_NAME"
     *             length="300"
     *             not-null="true"
     *         
     */

    public String getAgentName() {
        return this.agentName;
    }
    
    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }
    /**       
     *      *            @hibernate.property
     *             column="USER_SP_CODE"
     *             length="20"
     *         
     */

    public String getUserSpCode() {
        return this.userSpCode;
    }
    
    public void setUserSpCode(String userSpCode) {
        this.userSpCode = userSpCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="AMOUNT"
     *             length="18"
     *         
     */

    public BigDecimal getAmount() {
        return this.amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    /**       
     *      *            @hibernate.property
     *             column="PV_AMT"
     *             length="18"
     *         
     */

    public BigDecimal getPvAmt() {
        return this.pvAmt;
    }
    
    public void setPvAmt(BigDecimal pvAmt) {
        this.pvAmt = pvAmt;
    }
    /**       
     *      *            @hibernate.property
     *             column="CONSUMER_AMOUNT"
     *             length="18"
     *         
     */

    public BigDecimal getConsumerAmount() {
        return this.consumerAmount;
    }
    
    public void setConsumerAmount(BigDecimal consumerAmount) {
        this.consumerAmount = consumerAmount;
    }
    /**       
     *      *            @hibernate.property
     *             column="PAY_MODE"
     *             length="1"
     *         
     */

    public String getPayMode() {
        return this.payMode;
    }
    
    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }
    /**       
     *      *            @hibernate.property
     *             column="ORDER_USER_CODE"
     *             length="20"
     *         
     */

    public String getOrderUserCode() {
        return this.orderUserCode;
    }
    
    public void setOrderUserCode(String orderUserCode) {
        this.orderUserCode = orderUserCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="ORDER_TIME"
     *             length="7"
     *         
     */

    public Date getOrderTime() {
        return this.orderTime;
    }
    
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
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
     *             column="CHECK_USER_CODE"
     *             length="20"
     *         
     */

    public String getCheckUserCode() {
        return this.checkUserCode;
    }
    
    public void setCheckUserCode(String checkUserCode) {
        this.checkUserCode = checkUserCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="CHECK_DATE"
     *             length="7"
     *         
     */

    public Date getCheckDate() {
        return this.checkDate;
    }
    
    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }
    /**       
     *      *            @hibernate.property
     *             column="REMARK"
     *             length="4000"
     *         
     */

    public Clob getRemark() {
        return this.remark;
    }
    
    public void setRemark(Clob remark) {
        this.remark = remark;
    }
    /**       
     *      *            @hibernate.property
     *             column="PICKUP"
     *             length="1"
     *         
     */

    public String getPickup() {
        return this.pickup;
    }
    
    public void setPickup(String pickup) {
        this.pickup = pickup;
    }
    /**       
     *      *            @hibernate.property
     *             column="SUBMIT_TIME"
     *             length="7"
     *         
     */

    public Date getSubmitTime() {
        return this.submitTime;
    }
    
    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="SUBMIT_USER_CODE"
     *             length="20"
     *         
     */

    public String getSubmitUserCode() {
        return this.submitUserCode;
    }
    
    public void setSubmitUserCode(String submitUserCode) {
        this.submitUserCode = submitUserCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="SUBMIT_STATUS"
     *             length="1"
     *         
     */

    public String getSubmitStatus() {
        return this.submitStatus;
    }
    
    public void setSubmitStatus(String submitStatus) {
        this.submitStatus = submitStatus;
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
     *             column="PROVINCE"
     *             length="500"
     *         
     */

    public String getProvince() {
        return this.province;
    }
    
    public void setProvince(String province) {
        this.province = province;
    }
    /**       
     *      *            @hibernate.property
     *             column="CITY"
     *             length="500"
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
     *             column="ADDRESS"
     *             length="500"
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
     *             column="POSTALCODE"
     *             length="10"
     *         
     */

    public String getPostalcode() {
        return this.postalcode;
    }
    
    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }
    /**       
     *      *            @hibernate.property
     *             column="PHONE"
     *             length="30"
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
     *             length="30"
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
     *             column="MOBILETELE"
     *             length="20"
     *         
     */

    public String getMobiletele() {
        return this.mobiletele;
    }
    
    public void setMobiletele(String mobiletele) {
        this.mobiletele = mobiletele;
    }
    /**       
     *      *            @hibernate.property
     *             column="DISTRICT"
     *             length="20"
     *         
     */

    public String getDistrict() {
        return this.district;
    }
    
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
 	 * *
 	 * 
 	 * @hibernate.set lazy="true" inverse="true" cascade="all"
 	 * @hibernate.collection-key column="MO_ID"
 	 * @hibernate.collection-one-to-many class="com.joymain.jecs.fi.model.JfiSunMemberOrderList"
 	 * 
 	 */
	public Set getJfiSunMemberOrderList() {
		return jfiSunMemberOrderList;
	}

	public void setJfiSunMemberOrderList(Set jfiSunMemberOrderList) {
		this.jfiSunMemberOrderList = jfiSunMemberOrderList;
	}

	   /**
		 * *
		 * 
		 * @hibernate.set lazy="true" inverse="true" cascade="all"
		 * @hibernate.collection-key column="MO_ID"
		 * @hibernate.collection-one-to-many class="com.joymain.jecs.fi.model.JfiSunMemberOrderFee"
		 * 
		 */
	public Set getJfiSunMemberOrderFee() {
		return jfiSunMemberOrderFee;
	}

	public void setJfiSunMemberOrderFee(Set jfiSunMemberOrderFee) {
		this.jfiSunMemberOrderFee = jfiSunMemberOrderFee;
	}
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("countryCode").append("='").append(getCountryCode()).append("' ");			
      buffer.append("memberOrderNo").append("='").append(getMemberOrderNo()).append("' ");			
      buffer.append("orderType").append("='").append(getOrderType()).append("' ");			
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("userName").append("='").append(getUserName()).append("' ");			
      buffer.append("agentNo").append("='").append(getAgentNo()).append("' ");			
      buffer.append("agentName").append("='").append(getAgentName()).append("' ");			
      buffer.append("userSpCode").append("='").append(getUserSpCode()).append("' ");			
      buffer.append("amount").append("='").append(getAmount()).append("' ");			
      buffer.append("pvAmt").append("='").append(getPvAmt()).append("' ");			
      buffer.append("consumerAmount").append("='").append(getConsumerAmount()).append("' ");			
      buffer.append("payMode").append("='").append(getPayMode()).append("' ");			
      buffer.append("orderUserCode").append("='").append(getOrderUserCode()).append("' ");			
      buffer.append("orderTime").append("='").append(getOrderTime()).append("' ");			
      buffer.append("status").append("='").append(getStatus()).append("' ");			
      buffer.append("checkTime").append("='").append(getCheckTime()).append("' ");			
      buffer.append("checkUserCode").append("='").append(getCheckUserCode()).append("' ");			
      buffer.append("checkDate").append("='").append(getCheckDate()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");			
      buffer.append("pickup").append("='").append(getPickup()).append("' ");			
      buffer.append("submitTime").append("='").append(getSubmitTime()).append("' ");			
      buffer.append("submitUserCode").append("='").append(getSubmitUserCode()).append("' ");			
      buffer.append("submitStatus").append("='").append(getSubmitStatus()).append("' ");			
      buffer.append("firstName").append("='").append(getFirstName()).append("' ");			
      buffer.append("lastName").append("='").append(getLastName()).append("' ");			
      buffer.append("province").append("='").append(getProvince()).append("' ");			
      buffer.append("city").append("='").append(getCity()).append("' ");			
      buffer.append("address").append("='").append(getAddress()).append("' ");			
      buffer.append("postalcode").append("='").append(getPostalcode()).append("' ");			
      buffer.append("phone").append("='").append(getPhone()).append("' ");			
      buffer.append("email").append("='").append(getEmail()).append("' ");			
      buffer.append("mobiletele").append("='").append(getMobiletele()).append("' ");			
      buffer.append("district").append("='").append(getDistrict()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JfiSunMemberOrder) ) return false;
		 JfiSunMemberOrder castOther = ( JfiSunMemberOrder ) other; 
         
		 return ( (this.getMoId()==castOther.getMoId()) || ( this.getMoId()!=null && castOther.getMoId()!=null && this.getMoId().equals(castOther.getMoId()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getCountryCode()==castOther.getCountryCode()) || ( this.getCountryCode()!=null && castOther.getCountryCode()!=null && this.getCountryCode().equals(castOther.getCountryCode()) ) )
 && ( (this.getMemberOrderNo()==castOther.getMemberOrderNo()) || ( this.getMemberOrderNo()!=null && castOther.getMemberOrderNo()!=null && this.getMemberOrderNo().equals(castOther.getMemberOrderNo()) ) )
 && ( (this.getOrderType()==castOther.getOrderType()) || ( this.getOrderType()!=null && castOther.getOrderType()!=null && this.getOrderType().equals(castOther.getOrderType()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getUserName()==castOther.getUserName()) || ( this.getUserName()!=null && castOther.getUserName()!=null && this.getUserName().equals(castOther.getUserName()) ) )
 && ( (this.getAgentNo()==castOther.getAgentNo()) || ( this.getAgentNo()!=null && castOther.getAgentNo()!=null && this.getAgentNo().equals(castOther.getAgentNo()) ) )
 && ( (this.getAgentName()==castOther.getAgentName()) || ( this.getAgentName()!=null && castOther.getAgentName()!=null && this.getAgentName().equals(castOther.getAgentName()) ) )
 && ( (this.getUserSpCode()==castOther.getUserSpCode()) || ( this.getUserSpCode()!=null && castOther.getUserSpCode()!=null && this.getUserSpCode().equals(castOther.getUserSpCode()) ) )
 && ( (this.getAmount()==castOther.getAmount()) || ( this.getAmount()!=null && castOther.getAmount()!=null && this.getAmount().equals(castOther.getAmount()) ) )
 && ( (this.getPvAmt()==castOther.getPvAmt()) || ( this.getPvAmt()!=null && castOther.getPvAmt()!=null && this.getPvAmt().equals(castOther.getPvAmt()) ) )
 && ( (this.getConsumerAmount()==castOther.getConsumerAmount()) || ( this.getConsumerAmount()!=null && castOther.getConsumerAmount()!=null && this.getConsumerAmount().equals(castOther.getConsumerAmount()) ) )
 && ( (this.getPayMode()==castOther.getPayMode()) || ( this.getPayMode()!=null && castOther.getPayMode()!=null && this.getPayMode().equals(castOther.getPayMode()) ) )
 && ( (this.getOrderUserCode()==castOther.getOrderUserCode()) || ( this.getOrderUserCode()!=null && castOther.getOrderUserCode()!=null && this.getOrderUserCode().equals(castOther.getOrderUserCode()) ) )
 && ( (this.getOrderTime()==castOther.getOrderTime()) || ( this.getOrderTime()!=null && castOther.getOrderTime()!=null && this.getOrderTime().equals(castOther.getOrderTime()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
 && ( (this.getCheckTime()==castOther.getCheckTime()) || ( this.getCheckTime()!=null && castOther.getCheckTime()!=null && this.getCheckTime().equals(castOther.getCheckTime()) ) )
 && ( (this.getCheckUserCode()==castOther.getCheckUserCode()) || ( this.getCheckUserCode()!=null && castOther.getCheckUserCode()!=null && this.getCheckUserCode().equals(castOther.getCheckUserCode()) ) )
 && ( (this.getCheckDate()==castOther.getCheckDate()) || ( this.getCheckDate()!=null && castOther.getCheckDate()!=null && this.getCheckDate().equals(castOther.getCheckDate()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
 && ( (this.getPickup()==castOther.getPickup()) || ( this.getPickup()!=null && castOther.getPickup()!=null && this.getPickup().equals(castOther.getPickup()) ) )
 && ( (this.getSubmitTime()==castOther.getSubmitTime()) || ( this.getSubmitTime()!=null && castOther.getSubmitTime()!=null && this.getSubmitTime().equals(castOther.getSubmitTime()) ) )
 && ( (this.getSubmitUserCode()==castOther.getSubmitUserCode()) || ( this.getSubmitUserCode()!=null && castOther.getSubmitUserCode()!=null && this.getSubmitUserCode().equals(castOther.getSubmitUserCode()) ) )
 && ( (this.getSubmitStatus()==castOther.getSubmitStatus()) || ( this.getSubmitStatus()!=null && castOther.getSubmitStatus()!=null && this.getSubmitStatus().equals(castOther.getSubmitStatus()) ) )
 && ( (this.getFirstName()==castOther.getFirstName()) || ( this.getFirstName()!=null && castOther.getFirstName()!=null && this.getFirstName().equals(castOther.getFirstName()) ) )
 && ( (this.getLastName()==castOther.getLastName()) || ( this.getLastName()!=null && castOther.getLastName()!=null && this.getLastName().equals(castOther.getLastName()) ) )
 && ( (this.getProvince()==castOther.getProvince()) || ( this.getProvince()!=null && castOther.getProvince()!=null && this.getProvince().equals(castOther.getProvince()) ) )
 && ( (this.getCity()==castOther.getCity()) || ( this.getCity()!=null && castOther.getCity()!=null && this.getCity().equals(castOther.getCity()) ) )
 && ( (this.getAddress()==castOther.getAddress()) || ( this.getAddress()!=null && castOther.getAddress()!=null && this.getAddress().equals(castOther.getAddress()) ) )
 && ( (this.getPostalcode()==castOther.getPostalcode()) || ( this.getPostalcode()!=null && castOther.getPostalcode()!=null && this.getPostalcode().equals(castOther.getPostalcode()) ) )
 && ( (this.getPhone()==castOther.getPhone()) || ( this.getPhone()!=null && castOther.getPhone()!=null && this.getPhone().equals(castOther.getPhone()) ) )
 && ( (this.getEmail()==castOther.getEmail()) || ( this.getEmail()!=null && castOther.getEmail()!=null && this.getEmail().equals(castOther.getEmail()) ) )
 && ( (this.getMobiletele()==castOther.getMobiletele()) || ( this.getMobiletele()!=null && castOther.getMobiletele()!=null && this.getMobiletele().equals(castOther.getMobiletele()) ) )
 && ( (this.getDistrict()==castOther.getDistrict()) || ( this.getDistrict()!=null && castOther.getDistrict()!=null && this.getDistrict().equals(castOther.getDistrict()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getMoId() == null ? 0 : this.getMoId().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getCountryCode() == null ? 0 : this.getCountryCode().hashCode() );
         result = 37 * result + ( getMemberOrderNo() == null ? 0 : this.getMemberOrderNo().hashCode() );
         result = 37 * result + ( getOrderType() == null ? 0 : this.getOrderType().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getUserName() == null ? 0 : this.getUserName().hashCode() );
         result = 37 * result + ( getAgentNo() == null ? 0 : this.getAgentNo().hashCode() );
         result = 37 * result + ( getAgentName() == null ? 0 : this.getAgentName().hashCode() );
         result = 37 * result + ( getUserSpCode() == null ? 0 : this.getUserSpCode().hashCode() );
         result = 37 * result + ( getAmount() == null ? 0 : this.getAmount().hashCode() );
         result = 37 * result + ( getPvAmt() == null ? 0 : this.getPvAmt().hashCode() );
         result = 37 * result + ( getConsumerAmount() == null ? 0 : this.getConsumerAmount().hashCode() );
         result = 37 * result + ( getPayMode() == null ? 0 : this.getPayMode().hashCode() );
         result = 37 * result + ( getOrderUserCode() == null ? 0 : this.getOrderUserCode().hashCode() );
         result = 37 * result + ( getOrderTime() == null ? 0 : this.getOrderTime().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         result = 37 * result + ( getCheckTime() == null ? 0 : this.getCheckTime().hashCode() );
         result = 37 * result + ( getCheckUserCode() == null ? 0 : this.getCheckUserCode().hashCode() );
         result = 37 * result + ( getCheckDate() == null ? 0 : this.getCheckDate().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         result = 37 * result + ( getPickup() == null ? 0 : this.getPickup().hashCode() );
         result = 37 * result + ( getSubmitTime() == null ? 0 : this.getSubmitTime().hashCode() );
         result = 37 * result + ( getSubmitUserCode() == null ? 0 : this.getSubmitUserCode().hashCode() );
         result = 37 * result + ( getSubmitStatus() == null ? 0 : this.getSubmitStatus().hashCode() );
         result = 37 * result + ( getFirstName() == null ? 0 : this.getFirstName().hashCode() );
         result = 37 * result + ( getLastName() == null ? 0 : this.getLastName().hashCode() );
         result = 37 * result + ( getProvince() == null ? 0 : this.getProvince().hashCode() );
         result = 37 * result + ( getCity() == null ? 0 : this.getCity().hashCode() );
         result = 37 * result + ( getAddress() == null ? 0 : this.getAddress().hashCode() );
         result = 37 * result + ( getPostalcode() == null ? 0 : this.getPostalcode().hashCode() );
         result = 37 * result + ( getPhone() == null ? 0 : this.getPhone().hashCode() );
         result = 37 * result + ( getEmail() == null ? 0 : this.getEmail().hashCode() );
         result = 37 * result + ( getMobiletele() == null ? 0 : this.getMobiletele().hashCode() );
         result = 37 * result + ( getDistrict() == null ? 0 : this.getDistrict().hashCode() );
         return result;
   }   





}
