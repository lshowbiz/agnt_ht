package com.joymain.jecs.mi.model;
// Generated 2010-7-28 11:24:13 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JMI_STORE"
 *     
 */

public class JmiStore extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private String postalcode;
    private String mailAddr;
    private String mobiletele;
    private String faxtele;
    private String email;
    private String subStoreAddr;
    private Long province;
    private Long city;
    private Long district;
    private BigDecimal businessArea;
    private BigDecimal personTotal;
    private String cityType;
    private BigDecimal averageIncome;
    private String isdeal;
    private String business;
    private Date createTime;
    private String createUser;
    private Date checkTime;
    private String checkUser;
    private Date confirmTime;
    private String confirmUser;
    private String checkStatus;
    private String confirmStatus;
    private String checkRemark;
    private String confirmRemark;
    private Date editTime;

    private JmiMember jmiMember;
    
    private Date orderTime;
    private Integer honorStar;
    private String address;

    private Date orderDate;
    // Constructors

    /** default constructor */
    public JmiStore() {
    }

    
    /** full constructor */
    public JmiStore(String userCode, String postalcode, String mailAddr, String mobiletele, String faxtele, String email, String subStoreAddr, Long province, Long city, Long district, BigDecimal businessArea, BigDecimal personTotal, String cityType, BigDecimal averageIncome, String isdeal, String business, Date createTime, String createUser, Date checkTime, String checkUser, Date confirmTime, String confirmUser, String checkStatus, String confirmStatus, String checkRemark, String confirmRemark, Date editTime) {
        this.postalcode = postalcode;
        this.mailAddr = mailAddr;
        this.mobiletele = mobiletele;
        this.faxtele = faxtele;
        this.email = email;
        this.subStoreAddr = subStoreAddr;
        this.province = province;
        this.city = city;
        this.district = district;
        this.businessArea = businessArea;
        this.personTotal = personTotal;
        this.cityType = cityType;
        this.averageIncome = averageIncome;
        this.isdeal = isdeal;
        this.business = business;
        this.createTime = createTime;
        this.createUser = createUser;
        this.checkTime = checkTime;
        this.checkUser = checkUser;
        this.confirmTime = confirmTime;
        this.confirmUser = confirmUser;
        this.checkStatus = checkStatus;
        this.confirmStatus = confirmStatus;
        this.checkRemark = checkRemark;
        this.confirmRemark = confirmRemark;
        this.editTime = editTime;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ID"
     *@hibernate.generator-param name="sequence" value="SEQ_MI"
     */

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
     *             column="MAIL_ADDR"
     *             length="500"
     *         
     */

    public String getMailAddr() {
        return this.mailAddr;
    }
    
    public void setMailAddr(String mailAddr) {
        this.mailAddr = mailAddr;
    }
    /**       
     *      *            @hibernate.property
     *             column="MOBILETELE"
     *             length="30"
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
     *             column="FAXTELE"
     *             length="30"
     *         
     */

    public String getFaxtele() {
        return this.faxtele;
    }
    
    public void setFaxtele(String faxtele) {
        this.faxtele = faxtele;
    }
    /**       
     *      *            @hibernate.property
     *             column="EMAIL"
     *             length="40"
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
     *             column="SUB_STORE_ADDR"
     *             length="500"
     *         
     */

    public String getSubStoreAddr() {
        return this.subStoreAddr;
    }
    
    public void setSubStoreAddr(String subStoreAddr) {
        this.subStoreAddr = subStoreAddr;
    }
    /**       
     *      *            @hibernate.property
     *             column="PROVINCE"
     *             length="12"
     *         
     */

    public Long getProvince() {
        return this.province;
    }
    
    public void setProvince(Long province) {
        this.province = province;
    }
    /**       
     *      *            @hibernate.property
     *             column="CITY"
     *             length="12"
     *         
     */

    public Long getCity() {
        return this.city;
    }
    
    public void setCity(Long city) {
        this.city = city;
    }
    /**       
     *      *            @hibernate.property
     *             column="DISTRICT"
     *             length="12"
     *         
     */

    public Long getDistrict() {
        return this.district;
    }
    
    public void setDistrict(Long district) {
        this.district = district;
    }
    /**       
     *      *            @hibernate.property
     *             column="BUSINESS_AREA"
     *             length="22"
     *         
     */

    public BigDecimal getBusinessArea() {
        return this.businessArea;
    }
    
    public void setBusinessArea(BigDecimal businessArea) {
        this.businessArea = businessArea;
    }
    /**       
     *      *            @hibernate.property
     *             column="PERSON_TOTAL"
     *             length="22"
     *         
     */

    public BigDecimal getPersonTotal() {
        return this.personTotal;
    }
    
    public void setPersonTotal(BigDecimal personTotal) {
        this.personTotal = personTotal;
    }
    /**       
     *      *            @hibernate.property
     *             column="CITY_TYPE"
     *             length="1"
     *         
     */

    public String getCityType() {
        return this.cityType;
    }
    
    public void setCityType(String cityType) {
        this.cityType = cityType;
    }
    /**       
     *      *            @hibernate.property
     *             column="AVERAGE_INCOME"
     *             length="22"
     *         
     */

    public BigDecimal getAverageIncome() {
        return this.averageIncome;
    }
    
    public void setAverageIncome(BigDecimal averageIncome) {
        this.averageIncome = averageIncome;
    }
    /**       
     *      *            @hibernate.property
     *             column="ISDEAL"
     *             length="1"
     *         
     */

    public String getIsdeal() {
        return this.isdeal;
    }
    
    public void setIsdeal(String isdeal) {
        this.isdeal = isdeal;
    }
    /**       
     *      *            @hibernate.property
     *             column="BUSINESS"
     *             length="100"
     *         
     */

    public String getBusiness() {
        return this.business;
    }
    
    public void setBusiness(String business) {
        this.business = business;
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
     *             column="CREATE_USER"
     *             length="20"
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
     *             column="CHECK_USER"
     *             length="20"
     *         
     */

    public String getCheckUser() {
        return this.checkUser;
    }
    
    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser;
    }
    /**       
     *      *            @hibernate.property
     *             column="CONFIRM_TIME"
     *             length="7"
     *         
     */

    public Date getConfirmTime() {
        return this.confirmTime;
    }
    
    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="CONFIRM_USER"
     *             length="20"
     *         
     */

    public String getConfirmUser() {
        return this.confirmUser;
    }
    
    public void setConfirmUser(String confirmUser) {
        this.confirmUser = confirmUser;
    }
    /**       
     *      *            @hibernate.property
     *             column="CHECK_STATUS"
     *             length="1"
     *         
     */

    public String getCheckStatus() {
        return this.checkStatus;
    }
    
    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }
    /**       
     *      *            @hibernate.property
     *             column="CONFIRM_STATUS"
     *             length="1"
     *         
     */

    public String getConfirmStatus() {
        return this.confirmStatus;
    }
    
    public void setConfirmStatus(String confirmStatus) {
        this.confirmStatus = confirmStatus;
    }
    /**       
     *      *            @hibernate.property
     *             column="CHECK_REMARK"
     *             length="500"
     *         
     */

    public String getCheckRemark() {
        return this.checkRemark;
    }
    
    public void setCheckRemark(String checkRemark) {
        this.checkRemark = checkRemark;
    }
    /**       
     *      *            @hibernate.property
     *             column="CONFIRM_REMARK"
     *             length="500"
     *         
     */

    public String getConfirmRemark() {
        return this.confirmRemark;
    }
    
    public void setConfirmRemark(String confirmRemark) {
        this.confirmRemark = confirmRemark;
    }
    /**       
     *      *            @hibernate.property
     *             column="EDIT_TIME"
     *             length="7"
     *         
     */

    public Date getEditTime() {
        return this.editTime;
    }
    
    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");		
      buffer.append("postalcode").append("='").append(getPostalcode()).append("' ");			
      buffer.append("mailAddr").append("='").append(getMailAddr()).append("' ");			
      buffer.append("mobiletele").append("='").append(getMobiletele()).append("' ");			
      buffer.append("faxtele").append("='").append(getFaxtele()).append("' ");			
      buffer.append("email").append("='").append(getEmail()).append("' ");			
      buffer.append("subStoreAddr").append("='").append(getSubStoreAddr()).append("' ");			
      buffer.append("province").append("='").append(getProvince()).append("' ");			
      buffer.append("city").append("='").append(getCity()).append("' ");			
      buffer.append("district").append("='").append(getDistrict()).append("' ");			
      buffer.append("businessArea").append("='").append(getBusinessArea()).append("' ");			
      buffer.append("personTotal").append("='").append(getPersonTotal()).append("' ");			
      buffer.append("cityType").append("='").append(getCityType()).append("' ");			
      buffer.append("averageIncome").append("='").append(getAverageIncome()).append("' ");			
      buffer.append("isdeal").append("='").append(getIsdeal()).append("' ");			
      buffer.append("business").append("='").append(getBusiness()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("createUser").append("='").append(getCreateUser()).append("' ");			
      buffer.append("checkTime").append("='").append(getCheckTime()).append("' ");			
      buffer.append("checkUser").append("='").append(getCheckUser()).append("' ");			
      buffer.append("confirmTime").append("='").append(getConfirmTime()).append("' ");			
      buffer.append("confirmUser").append("='").append(getConfirmUser()).append("' ");			
      buffer.append("checkStatus").append("='").append(getCheckStatus()).append("' ");			
      buffer.append("confirmStatus").append("='").append(getConfirmStatus()).append("' ");			
      buffer.append("checkRemark").append("='").append(getCheckRemark()).append("' ");			
      buffer.append("confirmRemark").append("='").append(getConfirmRemark()).append("' ");			
      buffer.append("editTime").append("='").append(getEditTime()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JmiStore) ) return false;
		 JmiStore castOther = ( JmiStore ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getPostalcode()==castOther.getPostalcode()) || ( this.getPostalcode()!=null && castOther.getPostalcode()!=null && this.getPostalcode().equals(castOther.getPostalcode()) ) )
 && ( (this.getMailAddr()==castOther.getMailAddr()) || ( this.getMailAddr()!=null && castOther.getMailAddr()!=null && this.getMailAddr().equals(castOther.getMailAddr()) ) )
 && ( (this.getMobiletele()==castOther.getMobiletele()) || ( this.getMobiletele()!=null && castOther.getMobiletele()!=null && this.getMobiletele().equals(castOther.getMobiletele()) ) )
 && ( (this.getFaxtele()==castOther.getFaxtele()) || ( this.getFaxtele()!=null && castOther.getFaxtele()!=null && this.getFaxtele().equals(castOther.getFaxtele()) ) )
 && ( (this.getEmail()==castOther.getEmail()) || ( this.getEmail()!=null && castOther.getEmail()!=null && this.getEmail().equals(castOther.getEmail()) ) )
 && ( (this.getSubStoreAddr()==castOther.getSubStoreAddr()) || ( this.getSubStoreAddr()!=null && castOther.getSubStoreAddr()!=null && this.getSubStoreAddr().equals(castOther.getSubStoreAddr()) ) )
 && ( (this.getProvince()==castOther.getProvince()) || ( this.getProvince()!=null && castOther.getProvince()!=null && this.getProvince().equals(castOther.getProvince()) ) )
 && ( (this.getCity()==castOther.getCity()) || ( this.getCity()!=null && castOther.getCity()!=null && this.getCity().equals(castOther.getCity()) ) )
 && ( (this.getDistrict()==castOther.getDistrict()) || ( this.getDistrict()!=null && castOther.getDistrict()!=null && this.getDistrict().equals(castOther.getDistrict()) ) )
 && ( (this.getBusinessArea()==castOther.getBusinessArea()) || ( this.getBusinessArea()!=null && castOther.getBusinessArea()!=null && this.getBusinessArea().equals(castOther.getBusinessArea()) ) )
 && ( (this.getPersonTotal()==castOther.getPersonTotal()) || ( this.getPersonTotal()!=null && castOther.getPersonTotal()!=null && this.getPersonTotal().equals(castOther.getPersonTotal()) ) )
 && ( (this.getCityType()==castOther.getCityType()) || ( this.getCityType()!=null && castOther.getCityType()!=null && this.getCityType().equals(castOther.getCityType()) ) )
 && ( (this.getAverageIncome()==castOther.getAverageIncome()) || ( this.getAverageIncome()!=null && castOther.getAverageIncome()!=null && this.getAverageIncome().equals(castOther.getAverageIncome()) ) )
 && ( (this.getIsdeal()==castOther.getIsdeal()) || ( this.getIsdeal()!=null && castOther.getIsdeal()!=null && this.getIsdeal().equals(castOther.getIsdeal()) ) )
 && ( (this.getBusiness()==castOther.getBusiness()) || ( this.getBusiness()!=null && castOther.getBusiness()!=null && this.getBusiness().equals(castOther.getBusiness()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getCreateUser()==castOther.getCreateUser()) || ( this.getCreateUser()!=null && castOther.getCreateUser()!=null && this.getCreateUser().equals(castOther.getCreateUser()) ) )
 && ( (this.getCheckTime()==castOther.getCheckTime()) || ( this.getCheckTime()!=null && castOther.getCheckTime()!=null && this.getCheckTime().equals(castOther.getCheckTime()) ) )
 && ( (this.getCheckUser()==castOther.getCheckUser()) || ( this.getCheckUser()!=null && castOther.getCheckUser()!=null && this.getCheckUser().equals(castOther.getCheckUser()) ) )
 && ( (this.getConfirmTime()==castOther.getConfirmTime()) || ( this.getConfirmTime()!=null && castOther.getConfirmTime()!=null && this.getConfirmTime().equals(castOther.getConfirmTime()) ) )
 && ( (this.getConfirmUser()==castOther.getConfirmUser()) || ( this.getConfirmUser()!=null && castOther.getConfirmUser()!=null && this.getConfirmUser().equals(castOther.getConfirmUser()) ) )
 && ( (this.getCheckStatus()==castOther.getCheckStatus()) || ( this.getCheckStatus()!=null && castOther.getCheckStatus()!=null && this.getCheckStatus().equals(castOther.getCheckStatus()) ) )
 && ( (this.getConfirmStatus()==castOther.getConfirmStatus()) || ( this.getConfirmStatus()!=null && castOther.getConfirmStatus()!=null && this.getConfirmStatus().equals(castOther.getConfirmStatus()) ) )
 && ( (this.getCheckRemark()==castOther.getCheckRemark()) || ( this.getCheckRemark()!=null && castOther.getCheckRemark()!=null && this.getCheckRemark().equals(castOther.getCheckRemark()) ) )
 && ( (this.getConfirmRemark()==castOther.getConfirmRemark()) || ( this.getConfirmRemark()!=null && castOther.getConfirmRemark()!=null && this.getConfirmRemark().equals(castOther.getConfirmRemark()) ) )
 && ( (this.getEditTime()==castOther.getEditTime()) || ( this.getEditTime()!=null && castOther.getEditTime()!=null && this.getEditTime().equals(castOther.getEditTime()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getPostalcode() == null ? 0 : this.getPostalcode().hashCode() );
         result = 37 * result + ( getMailAddr() == null ? 0 : this.getMailAddr().hashCode() );
         result = 37 * result + ( getMobiletele() == null ? 0 : this.getMobiletele().hashCode() );
         result = 37 * result + ( getFaxtele() == null ? 0 : this.getFaxtele().hashCode() );
         result = 37 * result + ( getEmail() == null ? 0 : this.getEmail().hashCode() );
         result = 37 * result + ( getSubStoreAddr() == null ? 0 : this.getSubStoreAddr().hashCode() );
         result = 37 * result + ( getProvince() == null ? 0 : this.getProvince().hashCode() );
         result = 37 * result + ( getCity() == null ? 0 : this.getCity().hashCode() );
         result = 37 * result + ( getDistrict() == null ? 0 : this.getDistrict().hashCode() );
         result = 37 * result + ( getBusinessArea() == null ? 0 : this.getBusinessArea().hashCode() );
         result = 37 * result + ( getPersonTotal() == null ? 0 : this.getPersonTotal().hashCode() );
         result = 37 * result + ( getCityType() == null ? 0 : this.getCityType().hashCode() );
         result = 37 * result + ( getAverageIncome() == null ? 0 : this.getAverageIncome().hashCode() );
         result = 37 * result + ( getIsdeal() == null ? 0 : this.getIsdeal().hashCode() );
         result = 37 * result + ( getBusiness() == null ? 0 : this.getBusiness().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getCreateUser() == null ? 0 : this.getCreateUser().hashCode() );
         result = 37 * result + ( getCheckTime() == null ? 0 : this.getCheckTime().hashCode() );
         result = 37 * result + ( getCheckUser() == null ? 0 : this.getCheckUser().hashCode() );
         result = 37 * result + ( getConfirmTime() == null ? 0 : this.getConfirmTime().hashCode() );
         result = 37 * result + ( getConfirmUser() == null ? 0 : this.getConfirmUser().hashCode() );
         result = 37 * result + ( getCheckStatus() == null ? 0 : this.getCheckStatus().hashCode() );
         result = 37 * result + ( getConfirmStatus() == null ? 0 : this.getConfirmStatus().hashCode() );
         result = 37 * result + ( getCheckRemark() == null ? 0 : this.getCheckRemark().hashCode() );
         result = 37 * result + ( getConfirmRemark() == null ? 0 : this.getConfirmRemark().hashCode() );
         result = 37 * result + ( getEditTime() == null ? 0 : this.getEditTime().hashCode() );
         return result;
   }


	/**
    * *
    * @hibernate.many-to-one not-null="true" unique="true" cascade="save-update"
    * @hibernate.column name="USER_CODE"
    * 
    */
public JmiMember getJmiMember() {
	return jmiMember;
}


public void setJmiMember(JmiMember jmiMember) {
	this.jmiMember = jmiMember;
}

/**       
 *      *            @hibernate.property
 *             column="HONOR_STAR"
 *             length="2"
 *         
 */
public Integer getHonorStar() {
	return honorStar;
}


public void setHonorStar(Integer honorStar) {
	this.honorStar = honorStar;
}


/**       
 *      *            @hibernate.property
 *             column="ORDER_TIME"
 *             length="7"
 *         
 */
public Date getOrderTime() {
	return orderTime;
}


public void setOrderTime(Date orderTime) {
	this.orderTime = orderTime;
}



/**       
 *      *            @hibernate.property
 *             column="ADDRESS"
 *             length="300"
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
 *             column="ORDER_DATE"
 *             length="7"
 *         
 */
public Date getOrderDate() {
	return orderDate;
}


public void setOrderDate(Date orderDate) {
	this.orderDate = orderDate;
}   





}
