package com.joymain.jecs.po.model;
// Generated 2010-10-19 15:41:58 by Hibernate Tools 3.1.0.beta4

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JPO_MEMBER_ORDER_TASK"
 *     
 */

public class JpoMemberOrderTask extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long motId;
    private String companyCode;
    private String countryCode;
    private String userCode;
    private String status;
    private String taskType;
    private String remark;
    private String retMsg;
    private String firstName;
    private String lastName;
    private String province;
    private String city;
    private String district;
    private String address;
    private String postalcode;
    private String phone;
    private String email;
    private String mobiletele;
    private Date createTime;
    private String createUser;
    private Long actionTime;
    private String town;
    private Integer actionDate;
    private Integer actionWeek;
    private String cardCcNumber;
    private String cardCcExp;
    private String cardCvv;
    private String cardFirstName;
    private String cardLastName;
    private String cardAddress;
    private String cardCity;
    private String cardState;
    private String cardZip;
	private Set jpoMemberOrderListTask = new HashSet(0);


    // Constructors

    /** default constructor */
    public JpoMemberOrderTask() {
    }

	/** minimal constructor */
    public JpoMemberOrderTask(String companyCode, String countryCode, String userCode, String status, String taskType, String firstName, String province, String city, String address, String postalcode, Date createTime, String createUser) {
        this.companyCode = companyCode;
        this.countryCode = countryCode;
        this.userCode = userCode;
        this.status = status;
        this.taskType = taskType;
        this.firstName = firstName;
        this.province = province;
        this.city = city;
        this.address = address;
        this.postalcode = postalcode;
        this.createTime = createTime;
        this.createUser = createUser;
    }
    
    /** full constructor */
    public JpoMemberOrderTask(String companyCode, String countryCode, String userCode, String status, String taskType, String remark, String retMsg, String firstName, String lastName, String province, String city, String district, String address, String postalcode, String phone, String email, String mobiletele, Date createTime, String createUser) {
        this.companyCode = companyCode;
        this.countryCode = countryCode;
        this.userCode = userCode;
        this.status = status;
        this.taskType = taskType;
        this.remark = remark;
        this.retMsg = retMsg;
        this.firstName = firstName;
        this.lastName = lastName;
        this.province = province;
        this.city = city;
        this.district = district;
        this.address = address;
        this.postalcode = postalcode;
        this.phone = phone;
        this.email = email;
        this.mobiletele = mobiletele;
        this.createTime = createTime;
        this.createUser = createUser;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="MOT_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_PO"
     *         
     */

    public Long getMotId() {
        return this.motId;
    }
    
    public void setMotId(Long motId) {
        this.motId = motId;
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
     *             column="COUNTRY_CODE"
     *             length="2"
     *             not-null="true"
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
     *             column="TASK_TYPE"
     *             length="20"
     *             not-null="true"
     *         
     */

    public String getTaskType() {
        return this.taskType;
    }
    
    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }
    /**       
     *      *            @hibernate.property
     *             column="REMARK"
     *             length="4000"
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
     *             column="RET_MSG"
     *             length="4000"
     *         
     */

    public String getRetMsg() {
        return this.retMsg;
    }
    
    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
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
     *             length="20"
     *             not-null="true"
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
     *             length="20"
     *             not-null="true"
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
     *      *            @hibernate.property
     *             column="TOWN"
     *             length="20"
     *         
     */

    public String getTown() {
    	return town;
    }

    public void setTown(String town) {
    	this.town = town;
    }
    /**       
     *      *            @hibernate.property
     *             column="ADDRESS"
     *             length="500"
     *             not-null="true"
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
     *             not-null="true"
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
     *             column="ACTION_TIME"
     *             length="8"
     *             not-null="true"
     *         
     */
    public Long getActionTime() {
    	return actionTime;
    }

    public void setActionTime(Long actionTime) {
    	this.actionTime = actionTime;
    } 
    
    /**       
     *      *            @hibernate.property
     *             column="CREATE_USER"
     *             length="20"
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
     *             column="ACTION_DATE"
     *             length="2"
     *             not-null="true"
     *         
     */
    public Integer getActionDate() {
    	return actionDate;
    }

    public void setActionDate(Integer actionDate) {
    	this.actionDate = actionDate;
    }

    /**
 	 * *
 	 * 
 	 * @hibernate.set lazy="true" inverse="true" cascade="all"
 	 * @hibernate.collection-key column="MOT_ID"
 	 * @hibernate.collection-one-to-many class="com.joymain.jecs.po.model.JpoMemberOrderListTask"
 	 * 
 	 */
    public Set getJpoMemberOrderListTask() {
    	return jpoMemberOrderListTask;
    }

    public void setJpoMemberOrderListTask(Set jpoMemberOrderListTask) {
    	this.jpoMemberOrderListTask = jpoMemberOrderListTask;
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
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("status").append("='").append(getStatus()).append("' ");			
      buffer.append("taskType").append("='").append(getTaskType()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");			
      buffer.append("retMsg").append("='").append(getRetMsg()).append("' ");			
      buffer.append("firstName").append("='").append(getFirstName()).append("' ");			
      buffer.append("lastName").append("='").append(getLastName()).append("' ");			
      buffer.append("province").append("='").append(getProvince()).append("' ");			
      buffer.append("city").append("='").append(getCity()).append("' ");			
      buffer.append("district").append("='").append(getDistrict()).append("' ");			
      buffer.append("address").append("='").append(getAddress()).append("' ");			
      buffer.append("postalcode").append("='").append(getPostalcode()).append("' ");			
      buffer.append("phone").append("='").append(getPhone()).append("' ");			
      buffer.append("email").append("='").append(getEmail()).append("' ");			
      buffer.append("mobiletele").append("='").append(getMobiletele()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("actionTime").append("='").append(getActionTime()).append("' ");			
      buffer.append("createUser").append("='").append(getCreateUser()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JpoMemberOrderTask) ) return false;
		 JpoMemberOrderTask castOther = ( JpoMemberOrderTask ) other; 
         
		 return ( (this.getMotId()==castOther.getMotId()) || ( this.getMotId()!=null && castOther.getMotId()!=null && this.getMotId().equals(castOther.getMotId()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getCountryCode()==castOther.getCountryCode()) || ( this.getCountryCode()!=null && castOther.getCountryCode()!=null && this.getCountryCode().equals(castOther.getCountryCode()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
 && ( (this.getTaskType()==castOther.getTaskType()) || ( this.getTaskType()!=null && castOther.getTaskType()!=null && this.getTaskType().equals(castOther.getTaskType()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
 && ( (this.getRetMsg()==castOther.getRetMsg()) || ( this.getRetMsg()!=null && castOther.getRetMsg()!=null && this.getRetMsg().equals(castOther.getRetMsg()) ) )
 && ( (this.getFirstName()==castOther.getFirstName()) || ( this.getFirstName()!=null && castOther.getFirstName()!=null && this.getFirstName().equals(castOther.getFirstName()) ) )
 && ( (this.getLastName()==castOther.getLastName()) || ( this.getLastName()!=null && castOther.getLastName()!=null && this.getLastName().equals(castOther.getLastName()) ) )
 && ( (this.getProvince()==castOther.getProvince()) || ( this.getProvince()!=null && castOther.getProvince()!=null && this.getProvince().equals(castOther.getProvince()) ) )
 && ( (this.getCity()==castOther.getCity()) || ( this.getCity()!=null && castOther.getCity()!=null && this.getCity().equals(castOther.getCity()) ) )
 && ( (this.getDistrict()==castOther.getDistrict()) || ( this.getDistrict()!=null && castOther.getDistrict()!=null && this.getDistrict().equals(castOther.getDistrict()) ) )
 && ( (this.getAddress()==castOther.getAddress()) || ( this.getAddress()!=null && castOther.getAddress()!=null && this.getAddress().equals(castOther.getAddress()) ) )
 && ( (this.getPostalcode()==castOther.getPostalcode()) || ( this.getPostalcode()!=null && castOther.getPostalcode()!=null && this.getPostalcode().equals(castOther.getPostalcode()) ) )
 && ( (this.getPhone()==castOther.getPhone()) || ( this.getPhone()!=null && castOther.getPhone()!=null && this.getPhone().equals(castOther.getPhone()) ) )
 && ( (this.getEmail()==castOther.getEmail()) || ( this.getEmail()!=null && castOther.getEmail()!=null && this.getEmail().equals(castOther.getEmail()) ) )
 && ( (this.getMobiletele()==castOther.getMobiletele()) || ( this.getMobiletele()!=null && castOther.getMobiletele()!=null && this.getMobiletele().equals(castOther.getMobiletele()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getActionTime()==castOther.getActionTime()) || ( this.getActionTime()!=null && castOther.getActionTime()!=null && this.getActionTime().equals(castOther.getActionTime()) ) )
 && ( (this.getCreateUser()==castOther.getCreateUser()) || ( this.getCreateUser()!=null && castOther.getCreateUser()!=null && this.getCreateUser().equals(castOther.getCreateUser()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getMotId() == null ? 0 : this.getMotId().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getCountryCode() == null ? 0 : this.getCountryCode().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         result = 37 * result + ( getTaskType() == null ? 0 : this.getTaskType().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         result = 37 * result + ( getRetMsg() == null ? 0 : this.getRetMsg().hashCode() );
         result = 37 * result + ( getFirstName() == null ? 0 : this.getFirstName().hashCode() );
         result = 37 * result + ( getLastName() == null ? 0 : this.getLastName().hashCode() );
         result = 37 * result + ( getProvince() == null ? 0 : this.getProvince().hashCode() );
         result = 37 * result + ( getCity() == null ? 0 : this.getCity().hashCode() );
         result = 37 * result + ( getDistrict() == null ? 0 : this.getDistrict().hashCode() );
         result = 37 * result + ( getAddress() == null ? 0 : this.getAddress().hashCode() );
         result = 37 * result + ( getPostalcode() == null ? 0 : this.getPostalcode().hashCode() );
         result = 37 * result + ( getPhone() == null ? 0 : this.getPhone().hashCode() );
         result = 37 * result + ( getEmail() == null ? 0 : this.getEmail().hashCode() );
         result = 37 * result + ( getMobiletele() == null ? 0 : this.getMobiletele().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getActionTime() == null ? 0 : this.getActionTime().hashCode() );
         result = 37 * result + ( getCreateUser() == null ? 0 : this.getCreateUser().hashCode() );
         return result;
   }
   /**       
    *      *            @hibernate.property
    *             column="ACTION_WEEK"
    *             length="2"
    *         
    */
public Integer getActionWeek() {
	return actionWeek;
}

public void setActionWeek(Integer actionWeek) {
	this.actionWeek = actionWeek;
}

/**       
 *      *            @hibernate.property
 *             column="CARD_ADDRESS"
 *             length="4000"
 *         
 */
public String getCardAddress() {
	return cardAddress;
}

public void setCardAddress(String cardAddress) {
	this.cardAddress = cardAddress;
}

/**       
 *      *            @hibernate.property
 *             column="CARD_CC_EXP"
 *             length="4000"
 *         
 */
public String getCardCcExp() {
	return cardCcExp;
}

public void setCardCcExp(String cardCcExp) {
	this.cardCcExp = cardCcExp;
}

/**       
 *      *            @hibernate.property
 *             column="CARD_CC_NUMBER"
 *             length="4000"
 *         
 */
public String getCardCcNumber() {
	return cardCcNumber;
}

public void setCardCcNumber(String cardCcNumber) {
	this.cardCcNumber = cardCcNumber;
}

/**       
 *      *            @hibernate.property
 *             column="CARD_CITY"
 *             length="4000"
 *         
 */
public String getCardCity() {
	return cardCity;
}

public void setCardCity(String cardCity) {
	this.cardCity = cardCity;
}

/**       
 *      *            @hibernate.property
 *             column="CARD_CVV"
 *             length="4000"
 *         
 */
public String getCardCvv() {
	return cardCvv;
}

public void setCardCvv(String cardCvv) {
	this.cardCvv = cardCvv;
}

/**       
 *      *            @hibernate.property
 *             column="CARD_FIRST_NAME"
 *             length="4000"
 *         
 */
public String getCardFirstName() {
	return cardFirstName;
}

public void setCardFirstName(String cardFirstName) {
	this.cardFirstName = cardFirstName;
}

/**       
 *      *            @hibernate.property
 *             column="CARD_LAST_NAME"
 *             length="4000"
 *         
 */
public String getCardLastName() {
	return cardLastName;
}

public void setCardLastName(String cardLastName) {
	this.cardLastName = cardLastName;
}

/**       
 *      *            @hibernate.property
 *             column="CARD_STATE"
 *             length="4000"
 *         
 */
public String getCardState() {
	return cardState;
}

public void setCardState(String cardState) {
	this.cardState = cardState;
}

/**       
 *      *            @hibernate.property
 *             column="CARD_ZIP"
 *             length="4000"
 *         
 */
public String getCardZip() {
	return cardZip;
}

public void setCardZip(String cardZip) {
	this.cardZip = cardZip;
}





}
