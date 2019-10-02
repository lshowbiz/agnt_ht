package com.joymain.jecs.mi.model;
// Generated 2010-3-17 14:07:06 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JMI_TAIWAN_TRAVEL"
 *     
 */

public class JmiTaiwanTravel extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private String userCode;
    private Date filingDate;
    private String userName;
    private String petName;
    private String sex;
    private String idNo;
    private Long idProvince;
    private Long idCity;
    private String phone;
    private String applyType;
    private Long commonProvince;
    private Long commonCity;
    private String commonFromCity;
    private String specialFromCity;
    private String address;
    private String postalcode;
    private Date remittanceTime;
    private String remittanceName;
    private String remittanceCard;
    private String remittanceBank;
    private String spouseName;
    private String spousePetName;
    private String spouseSex;
    private String spouseIdNo;
    private String spouseUserCode;
    private Long spouseIdProvince;
    private Long spouseIdCity;
    private String spousePhone;
    private String spouseApplyType;
    private String createUser;
    private Date createTime;


    // Constructors

    /** default constructor */
    public JmiTaiwanTravel() {
    }

    
    /** full constructor */
    public JmiTaiwanTravel(Date filingDate, String userName, String petName, String sex, String idNo, Long idProvince, Long idCity, String phone, String applyType, Long commonProvince, Long commonCity, String commonFromCity, String specialFromCity, String address, String postalcode, Date remittanceTime, String remittanceName, String remittanceCard, String remittanceBank, String spouseName, String spousePetName, String spouseSex, String spouseIdNo, String spouseUserCode, Long spouseIdProvince, Long spouseIdCity, String spousePhone, String spouseApplyType, String createUser, Date createTime) {
        this.filingDate = filingDate;
        this.userName = userName;
        this.petName = petName;
        this.sex = sex;
        this.idNo = idNo;
        this.idProvince = idProvince;
        this.idCity = idCity;
        this.phone = phone;
        this.applyType = applyType;
        this.commonProvince = commonProvince;
        this.commonCity = commonCity;
        this.commonFromCity = commonFromCity;
        this.specialFromCity = specialFromCity;
        this.address = address;
        this.postalcode = postalcode;
        this.remittanceTime = remittanceTime;
        this.remittanceName = remittanceName;
        this.remittanceCard = remittanceCard;
        this.remittanceBank = remittanceBank;
        this.spouseName = spouseName;
        this.spousePetName = spousePetName;
        this.spouseSex = spouseSex;
        this.spouseIdNo = spouseIdNo;
        this.spouseUserCode = spouseUserCode;
        this.spouseIdProvince = spouseIdProvince;
        this.spouseIdCity = spouseIdCity;
        this.spousePhone = spousePhone;
        this.spouseApplyType = spouseApplyType;
        this.createUser = createUser;
        this.createTime = createTime;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *      	generator-class="assigned"
     *             type="java.lang.String"
     *             column="USER_CODE"
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
     *             column="FILING_DATE"
     *             length="7"
     *         
     */

    public Date getFilingDate() {
        return this.filingDate;
    }
    
    public void setFilingDate(Date filingDate) {
        this.filingDate = filingDate;
    }
    /**       
     *      *            @hibernate.property
     *             column="USER_NAME"
     *             length="300"
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
     *             column="PET_NAME"
     *             length="100"
     *         
     */

    public String getPetName() {
        return this.petName;
    }
    
    public void setPetName(String petName) {
        this.petName = petName;
    }
    /**       
     *      *            @hibernate.property
     *             column="SEX"
     *             length="1"
     *         
     */

    public String getSex() {
        return this.sex;
    }
    
    public void setSex(String sex) {
        this.sex = sex;
    }
    /**       
     *      *            @hibernate.property
     *             column="ID_NO"
     *             length="100"
     *         
     */

    public String getIdNo() {
        return this.idNo;
    }
    
    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="ID_PROVINCE"
     *             length="12"
     *         
     */

    public Long getIdProvince() {
        return this.idProvince;
    }
    
    public void setIdProvince(Long idProvince) {
        this.idProvince = idProvince;
    }
    /**       
     *      *            @hibernate.property
     *             column="ID_CITY"
     *             length="12"
     *         
     */

    public Long getIdCity() {
        return this.idCity;
    }
    
    public void setIdCity(Long idCity) {
        this.idCity = idCity;
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
     *             column="APPLY_TYPE"
     *             length="1"
     *         
     */

    public String getApplyType() {
        return this.applyType;
    }
    
    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }
    /**       
     *      *            @hibernate.property
     *             column="COMMON_PROVINCE"
     *             length="12"
     *         
     */

    public Long getCommonProvince() {
        return this.commonProvince;
    }
    
    public void setCommonProvince(Long commonProvince) {
        this.commonProvince = commonProvince;
    }
    /**       
     *      *            @hibernate.property
     *             column="COMMON_CITY"
     *             length="12"
     *         
     */

    public Long getCommonCity() {
    	return commonCity;
    }


    public void setCommonCity(Long commonCity) {
    	this.commonCity = commonCity;
    }  
    /**       
     *      *            @hibernate.property
     *             column="COMMON_FROM_CITY"
     *             length="20"
     *         
     */

    public String getCommonFromCity() {
        return this.commonFromCity;
    }
    
    public void setCommonFromCity(String commonFromCity) {
        this.commonFromCity = commonFromCity;
    }
    /**       
     *      *            @hibernate.property
     *             column="SPECIAL_FROM_CITY"
     *             length="20"
     *         
     */

    public String getSpecialFromCity() {
        return this.specialFromCity;
    }
    
    public void setSpecialFromCity(String specialFromCity) {
        this.specialFromCity = specialFromCity;
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
     *             column="REMITTANCE_TIME"
     *             length="7"
     *         
     */

    public Date getRemittanceTime() {
        return this.remittanceTime;
    }
    
    public void setRemittanceTime(Date remittanceTime) {
        this.remittanceTime = remittanceTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="REMITTANCE_NAME"
     *             length="100"
     *         
     */

    public String getRemittanceName() {
        return this.remittanceName;
    }
    
    public void setRemittanceName(String remittanceName) {
        this.remittanceName = remittanceName;
    }
    /**       
     *      *            @hibernate.property
     *             column="REMITTANCE_CARD"
     *             length="100"
     *         
     */

    public String getRemittanceCard() {
        return this.remittanceCard;
    }
    
    public void setRemittanceCard(String remittanceCard) {
        this.remittanceCard = remittanceCard;
    }
    /**       
     *      *            @hibernate.property
     *             column="REMITTANCE_BANK"
     *             length="100"
     *         
     */

    public String getRemittanceBank() {
        return this.remittanceBank;
    }
    
    public void setRemittanceBank(String remittanceBank) {
        this.remittanceBank = remittanceBank;
    }
    /**       
     *      *            @hibernate.property
     *             column="SPOUSE_NAME"
     *             length="300"
     *         
     */

    public String getSpouseName() {
        return this.spouseName;
    }
    
    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }
    /**       
     *      *            @hibernate.property
     *             column="SPOUSE_PET_NAME"
     *             length="100"
     *         
     */

    public String getSpousePetName() {
        return this.spousePetName;
    }
    
    public void setSpousePetName(String spousePetName) {
        this.spousePetName = spousePetName;
    }
    /**       
     *      *            @hibernate.property
     *             column="SPOUSE_SEX"
     *             length="1"
     *         
     */

    public String getSpouseSex() {
        return this.spouseSex;
    }
    
    public void setSpouseSex(String spouseSex) {
        this.spouseSex = spouseSex;
    }
    /**       
     *      *            @hibernate.property
     *             column="SPOUSE_ID_NO"
     *             length="100"
     *         
     */

    public String getSpouseIdNo() {
        return this.spouseIdNo;
    }
    
    public void setSpouseIdNo(String spouseIdNo) {
        this.spouseIdNo = spouseIdNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="SPOUSE_USER_CODE"
     *             length="20"
     *         
     */

    public String getSpouseUserCode() {
        return this.spouseUserCode;
    }
    
    public void setSpouseUserCode(String spouseUserCode) {
        this.spouseUserCode = spouseUserCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="SPOUSE_ID_PROVINCE"
     *             length="12"
     *         
     */

    public Long getSpouseIdProvince() {
        return this.spouseIdProvince;
    }
    
    public void setSpouseIdProvince(Long spouseIdProvince) {
        this.spouseIdProvince = spouseIdProvince;
    }
    /**       
     *      *            @hibernate.property
     *             column="SPOUSE_ID_CITY"
     *             length="12"
     *         
     */

    public Long getSpouseIdCity() {
        return this.spouseIdCity;
    }
    
    public void setSpouseIdCity(Long spouseIdCity) {
        this.spouseIdCity = spouseIdCity;
    }
    /**       
     *      *            @hibernate.property
     *             column="SPOUSE_PHONE"
     *             length="30"
     *         
     */

    public String getSpousePhone() {
        return this.spousePhone;
    }
    
    public void setSpousePhone(String spousePhone) {
        this.spousePhone = spousePhone;
    }
    /**       
     *      *            @hibernate.property
     *             column="SPOUSE_APPLY_TYPE"
     *             length="1"
     *         
     */

    public String getSpouseApplyType() {
        return this.spouseApplyType;
    }
    
    public void setSpouseApplyType(String spouseApplyType) {
        this.spouseApplyType = spouseApplyType;
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
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("filingDate").append("='").append(getFilingDate()).append("' ");			
      buffer.append("userName").append("='").append(getUserName()).append("' ");			
      buffer.append("petName").append("='").append(getPetName()).append("' ");			
      buffer.append("sex").append("='").append(getSex()).append("' ");			
      buffer.append("idNo").append("='").append(getIdNo()).append("' ");			
      buffer.append("idProvince").append("='").append(getIdProvince()).append("' ");			
      buffer.append("idCity").append("='").append(getIdCity()).append("' ");			
      buffer.append("phone").append("='").append(getPhone()).append("' ");			
      buffer.append("applyType").append("='").append(getApplyType()).append("' ");			
      buffer.append("commonProvince").append("='").append(getCommonProvince()).append("' ");			
      buffer.append("commonCity").append("='").append(getCommonCity()).append("' ");			
      buffer.append("commonFromCity").append("='").append(getCommonFromCity()).append("' ");			
      buffer.append("specialFromCity").append("='").append(getSpecialFromCity()).append("' ");			
      buffer.append("address").append("='").append(getAddress()).append("' ");			
      buffer.append("postalcode").append("='").append(getPostalcode()).append("' ");			
      buffer.append("remittanceTime").append("='").append(getRemittanceTime()).append("' ");			
      buffer.append("remittanceName").append("='").append(getRemittanceName()).append("' ");			
      buffer.append("remittanceCard").append("='").append(getRemittanceCard()).append("' ");			
      buffer.append("remittanceBank").append("='").append(getRemittanceBank()).append("' ");			
      buffer.append("spouseName").append("='").append(getSpouseName()).append("' ");			
      buffer.append("spousePetName").append("='").append(getSpousePetName()).append("' ");			
      buffer.append("spouseSex").append("='").append(getSpouseSex()).append("' ");			
      buffer.append("spouseIdNo").append("='").append(getSpouseIdNo()).append("' ");			
      buffer.append("spouseUserCode").append("='").append(getSpouseUserCode()).append("' ");			
      buffer.append("spouseIdProvince").append("='").append(getSpouseIdProvince()).append("' ");			
      buffer.append("spouseIdCity").append("='").append(getSpouseIdCity()).append("' ");			
      buffer.append("spousePhone").append("='").append(getSpousePhone()).append("' ");			
      buffer.append("spouseApplyType").append("='").append(getSpouseApplyType()).append("' ");			
      buffer.append("createUser").append("='").append(getCreateUser()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JmiTaiwanTravel) ) return false;
		 JmiTaiwanTravel castOther = ( JmiTaiwanTravel ) other; 
         
		 return ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getFilingDate()==castOther.getFilingDate()) || ( this.getFilingDate()!=null && castOther.getFilingDate()!=null && this.getFilingDate().equals(castOther.getFilingDate()) ) )
 && ( (this.getUserName()==castOther.getUserName()) || ( this.getUserName()!=null && castOther.getUserName()!=null && this.getUserName().equals(castOther.getUserName()) ) )
 && ( (this.getPetName()==castOther.getPetName()) || ( this.getPetName()!=null && castOther.getPetName()!=null && this.getPetName().equals(castOther.getPetName()) ) )
 && ( (this.getSex()==castOther.getSex()) || ( this.getSex()!=null && castOther.getSex()!=null && this.getSex().equals(castOther.getSex()) ) )
 && ( (this.getIdNo()==castOther.getIdNo()) || ( this.getIdNo()!=null && castOther.getIdNo()!=null && this.getIdNo().equals(castOther.getIdNo()) ) )
 && ( (this.getIdProvince()==castOther.getIdProvince()) || ( this.getIdProvince()!=null && castOther.getIdProvince()!=null && this.getIdProvince().equals(castOther.getIdProvince()) ) )
 && ( (this.getIdCity()==castOther.getIdCity()) || ( this.getIdCity()!=null && castOther.getIdCity()!=null && this.getIdCity().equals(castOther.getIdCity()) ) )
 && ( (this.getPhone()==castOther.getPhone()) || ( this.getPhone()!=null && castOther.getPhone()!=null && this.getPhone().equals(castOther.getPhone()) ) )
 && ( (this.getApplyType()==castOther.getApplyType()) || ( this.getApplyType()!=null && castOther.getApplyType()!=null && this.getApplyType().equals(castOther.getApplyType()) ) )
 && ( (this.getCommonProvince()==castOther.getCommonProvince()) || ( this.getCommonProvince()!=null && castOther.getCommonProvince()!=null && this.getCommonProvince().equals(castOther.getCommonProvince()) ) )
 && ( (this.getCommonCity()==castOther.getCommonCity()) || ( this.getCommonCity()!=null && castOther.getCommonCity()!=null && this.getCommonCity().equals(castOther.getCommonCity()) ) )
 && ( (this.getCommonFromCity()==castOther.getCommonFromCity()) || ( this.getCommonFromCity()!=null && castOther.getCommonFromCity()!=null && this.getCommonFromCity().equals(castOther.getCommonFromCity()) ) )
 && ( (this.getSpecialFromCity()==castOther.getSpecialFromCity()) || ( this.getSpecialFromCity()!=null && castOther.getSpecialFromCity()!=null && this.getSpecialFromCity().equals(castOther.getSpecialFromCity()) ) )
 && ( (this.getAddress()==castOther.getAddress()) || ( this.getAddress()!=null && castOther.getAddress()!=null && this.getAddress().equals(castOther.getAddress()) ) )
 && ( (this.getPostalcode()==castOther.getPostalcode()) || ( this.getPostalcode()!=null && castOther.getPostalcode()!=null && this.getPostalcode().equals(castOther.getPostalcode()) ) )
 && ( (this.getRemittanceTime()==castOther.getRemittanceTime()) || ( this.getRemittanceTime()!=null && castOther.getRemittanceTime()!=null && this.getRemittanceTime().equals(castOther.getRemittanceTime()) ) )
 && ( (this.getRemittanceName()==castOther.getRemittanceName()) || ( this.getRemittanceName()!=null && castOther.getRemittanceName()!=null && this.getRemittanceName().equals(castOther.getRemittanceName()) ) )
 && ( (this.getRemittanceCard()==castOther.getRemittanceCard()) || ( this.getRemittanceCard()!=null && castOther.getRemittanceCard()!=null && this.getRemittanceCard().equals(castOther.getRemittanceCard()) ) )
 && ( (this.getRemittanceBank()==castOther.getRemittanceBank()) || ( this.getRemittanceBank()!=null && castOther.getRemittanceBank()!=null && this.getRemittanceBank().equals(castOther.getRemittanceBank()) ) )
 && ( (this.getSpouseName()==castOther.getSpouseName()) || ( this.getSpouseName()!=null && castOther.getSpouseName()!=null && this.getSpouseName().equals(castOther.getSpouseName()) ) )
 && ( (this.getSpousePetName()==castOther.getSpousePetName()) || ( this.getSpousePetName()!=null && castOther.getSpousePetName()!=null && this.getSpousePetName().equals(castOther.getSpousePetName()) ) )
 && ( (this.getSpouseSex()==castOther.getSpouseSex()) || ( this.getSpouseSex()!=null && castOther.getSpouseSex()!=null && this.getSpouseSex().equals(castOther.getSpouseSex()) ) )
 && ( (this.getSpouseIdNo()==castOther.getSpouseIdNo()) || ( this.getSpouseIdNo()!=null && castOther.getSpouseIdNo()!=null && this.getSpouseIdNo().equals(castOther.getSpouseIdNo()) ) )
 && ( (this.getSpouseUserCode()==castOther.getSpouseUserCode()) || ( this.getSpouseUserCode()!=null && castOther.getSpouseUserCode()!=null && this.getSpouseUserCode().equals(castOther.getSpouseUserCode()) ) )
 && ( (this.getSpouseIdProvince()==castOther.getSpouseIdProvince()) || ( this.getSpouseIdProvince()!=null && castOther.getSpouseIdProvince()!=null && this.getSpouseIdProvince().equals(castOther.getSpouseIdProvince()) ) )
 && ( (this.getSpouseIdCity()==castOther.getSpouseIdCity()) || ( this.getSpouseIdCity()!=null && castOther.getSpouseIdCity()!=null && this.getSpouseIdCity().equals(castOther.getSpouseIdCity()) ) )
 && ( (this.getSpousePhone()==castOther.getSpousePhone()) || ( this.getSpousePhone()!=null && castOther.getSpousePhone()!=null && this.getSpousePhone().equals(castOther.getSpousePhone()) ) )
 && ( (this.getSpouseApplyType()==castOther.getSpouseApplyType()) || ( this.getSpouseApplyType()!=null && castOther.getSpouseApplyType()!=null && this.getSpouseApplyType().equals(castOther.getSpouseApplyType()) ) )
 && ( (this.getCreateUser()==castOther.getCreateUser()) || ( this.getCreateUser()!=null && castOther.getCreateUser()!=null && this.getCreateUser().equals(castOther.getCreateUser()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getFilingDate() == null ? 0 : this.getFilingDate().hashCode() );
         result = 37 * result + ( getUserName() == null ? 0 : this.getUserName().hashCode() );
         result = 37 * result + ( getPetName() == null ? 0 : this.getPetName().hashCode() );
         result = 37 * result + ( getSex() == null ? 0 : this.getSex().hashCode() );
         result = 37 * result + ( getIdNo() == null ? 0 : this.getIdNo().hashCode() );
         result = 37 * result + ( getIdProvince() == null ? 0 : this.getIdProvince().hashCode() );
         result = 37 * result + ( getIdCity() == null ? 0 : this.getIdCity().hashCode() );
         result = 37 * result + ( getPhone() == null ? 0 : this.getPhone().hashCode() );
         result = 37 * result + ( getApplyType() == null ? 0 : this.getApplyType().hashCode() );
         result = 37 * result + ( getCommonProvince() == null ? 0 : this.getCommonProvince().hashCode() );
         result = 37 * result + ( getCommonCity() == null ? 0 : this.getCommonCity().hashCode() );
         result = 37 * result + ( getCommonFromCity() == null ? 0 : this.getCommonFromCity().hashCode() );
         result = 37 * result + ( getSpecialFromCity() == null ? 0 : this.getSpecialFromCity().hashCode() );
         result = 37 * result + ( getAddress() == null ? 0 : this.getAddress().hashCode() );
         result = 37 * result + ( getPostalcode() == null ? 0 : this.getPostalcode().hashCode() );
         result = 37 * result + ( getRemittanceTime() == null ? 0 : this.getRemittanceTime().hashCode() );
         result = 37 * result + ( getRemittanceName() == null ? 0 : this.getRemittanceName().hashCode() );
         result = 37 * result + ( getRemittanceCard() == null ? 0 : this.getRemittanceCard().hashCode() );
         result = 37 * result + ( getRemittanceBank() == null ? 0 : this.getRemittanceBank().hashCode() );
         result = 37 * result + ( getSpouseName() == null ? 0 : this.getSpouseName().hashCode() );
         result = 37 * result + ( getSpousePetName() == null ? 0 : this.getSpousePetName().hashCode() );
         result = 37 * result + ( getSpouseSex() == null ? 0 : this.getSpouseSex().hashCode() );
         result = 37 * result + ( getSpouseIdNo() == null ? 0 : this.getSpouseIdNo().hashCode() );
         result = 37 * result + ( getSpouseUserCode() == null ? 0 : this.getSpouseUserCode().hashCode() );
         result = 37 * result + ( getSpouseIdProvince() == null ? 0 : this.getSpouseIdProvince().hashCode() );
         result = 37 * result + ( getSpouseIdCity() == null ? 0 : this.getSpouseIdCity().hashCode() );
         result = 37 * result + ( getSpousePhone() == null ? 0 : this.getSpousePhone().hashCode() );
         result = 37 * result + ( getSpouseApplyType() == null ? 0 : this.getSpouseApplyType().hashCode() );
         result = 37 * result + ( getCreateUser() == null ? 0 : this.getCreateUser().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         return result;
   }

 





}
