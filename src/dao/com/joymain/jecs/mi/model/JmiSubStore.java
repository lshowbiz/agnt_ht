package com.joymain.jecs.mi.model;
// Generated 2010-4-7 17:18:11 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JMI_SUB_STORE"
 *     
 */

public class JmiSubStore extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private Long province;
    private Long city;
    private Long district;
    private String address;
    private String postalcode;
    private String phone;
    private String mobiletele;
    private String email;
    private Long personQty;
    private String storePhone;
    private String businessArea;
    private Long averageIncome;
    private Long investAmount;
    private Date startDate;
    private String isdeal;
    private String specificBusiness;
    private JmiMember jmiMember;
    private String checkRemark;
    private String confirmRemark;
    private String confirmStatus;
    private String checkUser;
    private String confirmUser;
    private Date confirmTime;
    private Date createTime;
    private Date checkTime;
    private Date noticeTime;
    private String businessLicese;
    private String contract;
    private String storePic;
    private String addrConfirm;
    private String addrCheck;
    private Date editTime;
    

    private String addrConfirmUser;
    private Date addrConfirmTime;
    private String addrCheckUser;
    private Date addrCheckTime;
    // Constructors
    /**       
     *      *            @hibernate.property
     *             column="BUSINESS_LICENSE"
     *             length="1"
     *         
     */
    public String getBusinessLicese() {
		return businessLicese;
	}


	public void setBusinessLicese(String businessLicese) {
		this.businessLicese = businessLicese;
	}
    /**       
     *      *            @hibernate.property
     *             column="CONTRACT"
     *             length="1"
     *         
     */
	public String getContract() {
		return contract;
	}


	public void setContract(String contract) {
		this.contract = contract;
	}


	/**       
	 *      *            @hibernate.property
	 *             column="NOTICE_TIME"
	 *             length="7"
	 *         
	 */
	public Date getNoticeTime() {
		return noticeTime;
	}


	public void setNoticeTime(Date noticeTime) {
		this.noticeTime = noticeTime;
	}


    /**       
     *      *            @hibernate.property
     *             column="STORE_PIC"
     *             length="1"
     *         
     */
	public String getStorePic() {
		return storePic;
	}


	public void setStorePic(String storePic) {
		this.storePic = storePic;
	}


	/** default constructor */
    public JmiSubStore() {
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


	/** full constructor */
    public JmiSubStore(String userCode, Long province, Long city, Long district, String address, String postalcode, String phone, String mobiletele, String email, Long personQty, String storePhone, String businessArea, Long averageIncome, Long investAmount, Date startDate, String isdeal, String specificBusiness) {
        this.province = province;
        this.city = city;
        this.district = district;
        this.address = address;
        this.postalcode = postalcode;
        this.phone = phone;
        this.mobiletele = mobiletele;
        this.email = email;
        this.personQty = personQty;
        this.storePhone = storePhone;
        this.businessArea = businessArea;
        this.averageIncome = averageIncome;
        this.investAmount = investAmount;
        this.startDate = startDate;
        this.isdeal = isdeal;
        this.specificBusiness = specificBusiness;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ID"
     *@hibernate.generator-param name="sequence" value="SEQ_MI"
     *         
     */

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
     *             length="20"
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
     *             length="10"
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
     *             column="PERSON_QTY"
     *             length="12"
     *         
     */

    public Long getPersonQty() {
        return this.personQty;
    }
    
    public void setPersonQty(Long personQty) {
        this.personQty = personQty;
    }
    /**       
     *      *            @hibernate.property
     *             column="STORE_PHONE"
     *             length="30"
     *         
     */

    public String getStorePhone() {
        return this.storePhone;
    }
    
    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
    }
    /**       
     *      *            @hibernate.property
     *             column="BUSINESS_AREA"
     *             length="100"
     *         
     */

    public String getBusinessArea() {
        return this.businessArea;
    }
    
    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }
    /**       
     *      *            @hibernate.property
     *             column="AVERAGE_INCOME"
     *             length="12"
     *         
     */

    public Long getAverageIncome() {
        return this.averageIncome;
    }
    
    public void setAverageIncome(Long averageIncome) {
        this.averageIncome = averageIncome;
    }
    /**       
     *      *            @hibernate.property
     *             column="INVEST_AMOUNT"
     *             length="12"
     *         
     */

    public Long getInvestAmount() {
        return this.investAmount;
    }
    
    public void setInvestAmount(Long investAmount) {
        this.investAmount = investAmount;
    }
    /**       
     *      *            @hibernate.property
     *             column="START_DATE"
     *             length="7"
     *         
     */

    public Date getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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
     *             column="SPECIFIC_BUSINESS"
     *             length="500"
     *         
     */

    public String getSpecificBusiness() {
        return this.specificBusiness;
    }
    
    public void setSpecificBusiness(String specificBusiness) {
        this.specificBusiness = specificBusiness;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");		
      buffer.append("province").append("='").append(getProvince()).append("' ");			
      buffer.append("city").append("='").append(getCity()).append("' ");			
      buffer.append("district").append("='").append(getDistrict()).append("' ");			
      buffer.append("address").append("='").append(getAddress()).append("' ");			
      buffer.append("postalcode").append("='").append(getPostalcode()).append("' ");			
      buffer.append("phone").append("='").append(getPhone()).append("' ");			
      buffer.append("mobiletele").append("='").append(getMobiletele()).append("' ");			
      buffer.append("email").append("='").append(getEmail()).append("' ");			
      buffer.append("personQty").append("='").append(getPersonQty()).append("' ");			
      buffer.append("storePhone").append("='").append(getStorePhone()).append("' ");			
      buffer.append("businessArea").append("='").append(getBusinessArea()).append("' ");			
      buffer.append("averageIncome").append("='").append(getAverageIncome()).append("' ");			
      buffer.append("investAmount").append("='").append(getInvestAmount()).append("' ");			
      buffer.append("startDate").append("='").append(getStartDate()).append("' ");			
      buffer.append("isdeal").append("='").append(getIsdeal()).append("' ");			
      buffer.append("specificBusiness").append("='").append(getSpecificBusiness()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JmiSubStore) ) return false;
		 JmiSubStore castOther = ( JmiSubStore ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getProvince()==castOther.getProvince()) || ( this.getProvince()!=null && castOther.getProvince()!=null && this.getProvince().equals(castOther.getProvince()) ) )
 && ( (this.getCity()==castOther.getCity()) || ( this.getCity()!=null && castOther.getCity()!=null && this.getCity().equals(castOther.getCity()) ) )
 && ( (this.getDistrict()==castOther.getDistrict()) || ( this.getDistrict()!=null && castOther.getDistrict()!=null && this.getDistrict().equals(castOther.getDistrict()) ) )
 && ( (this.getAddress()==castOther.getAddress()) || ( this.getAddress()!=null && castOther.getAddress()!=null && this.getAddress().equals(castOther.getAddress()) ) )
 && ( (this.getPostalcode()==castOther.getPostalcode()) || ( this.getPostalcode()!=null && castOther.getPostalcode()!=null && this.getPostalcode().equals(castOther.getPostalcode()) ) )
 && ( (this.getPhone()==castOther.getPhone()) || ( this.getPhone()!=null && castOther.getPhone()!=null && this.getPhone().equals(castOther.getPhone()) ) )
 && ( (this.getMobiletele()==castOther.getMobiletele()) || ( this.getMobiletele()!=null && castOther.getMobiletele()!=null && this.getMobiletele().equals(castOther.getMobiletele()) ) )
 && ( (this.getEmail()==castOther.getEmail()) || ( this.getEmail()!=null && castOther.getEmail()!=null && this.getEmail().equals(castOther.getEmail()) ) )
 && ( (this.getPersonQty()==castOther.getPersonQty()) || ( this.getPersonQty()!=null && castOther.getPersonQty()!=null && this.getPersonQty().equals(castOther.getPersonQty()) ) )
 && ( (this.getStorePhone()==castOther.getStorePhone()) || ( this.getStorePhone()!=null && castOther.getStorePhone()!=null && this.getStorePhone().equals(castOther.getStorePhone()) ) )
 && ( (this.getBusinessArea()==castOther.getBusinessArea()) || ( this.getBusinessArea()!=null && castOther.getBusinessArea()!=null && this.getBusinessArea().equals(castOther.getBusinessArea()) ) )
 && ( (this.getAverageIncome()==castOther.getAverageIncome()) || ( this.getAverageIncome()!=null && castOther.getAverageIncome()!=null && this.getAverageIncome().equals(castOther.getAverageIncome()) ) )
 && ( (this.getInvestAmount()==castOther.getInvestAmount()) || ( this.getInvestAmount()!=null && castOther.getInvestAmount()!=null && this.getInvestAmount().equals(castOther.getInvestAmount()) ) )
 && ( (this.getStartDate()==castOther.getStartDate()) || ( this.getStartDate()!=null && castOther.getStartDate()!=null && this.getStartDate().equals(castOther.getStartDate()) ) )
 && ( (this.getIsdeal()==castOther.getIsdeal()) || ( this.getIsdeal()!=null && castOther.getIsdeal()!=null && this.getIsdeal().equals(castOther.getIsdeal()) ) )
 && ( (this.getSpecificBusiness()==castOther.getSpecificBusiness()) || ( this.getSpecificBusiness()!=null && castOther.getSpecificBusiness()!=null && this.getSpecificBusiness().equals(castOther.getSpecificBusiness()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getProvince() == null ? 0 : this.getProvince().hashCode() );
         result = 37 * result + ( getCity() == null ? 0 : this.getCity().hashCode() );
         result = 37 * result + ( getDistrict() == null ? 0 : this.getDistrict().hashCode() );
         result = 37 * result + ( getAddress() == null ? 0 : this.getAddress().hashCode() );
         result = 37 * result + ( getPostalcode() == null ? 0 : this.getPostalcode().hashCode() );
         result = 37 * result + ( getPhone() == null ? 0 : this.getPhone().hashCode() );
         result = 37 * result + ( getMobiletele() == null ? 0 : this.getMobiletele().hashCode() );
         result = 37 * result + ( getEmail() == null ? 0 : this.getEmail().hashCode() );
         result = 37 * result + ( getPersonQty() == null ? 0 : this.getPersonQty().hashCode() );
         result = 37 * result + ( getStorePhone() == null ? 0 : this.getStorePhone().hashCode() );
         result = 37 * result + ( getBusinessArea() == null ? 0 : this.getBusinessArea().hashCode() );
         result = 37 * result + ( getAverageIncome() == null ? 0 : this.getAverageIncome().hashCode() );
         result = 37 * result + ( getInvestAmount() == null ? 0 : this.getInvestAmount().hashCode() );
         result = 37 * result + ( getStartDate() == null ? 0 : this.getStartDate().hashCode() );
         result = 37 * result + ( getIsdeal() == null ? 0 : this.getIsdeal().hashCode() );
         result = 37 * result + ( getSpecificBusiness() == null ? 0 : this.getSpecificBusiness().hashCode() );
         return result;
   }


   /**       
    *      *            @hibernate.property
    *             column="CHECK_REMARK"
    *             length="500"
    *         
    */
public String getCheckRemark() {
	return checkRemark;
}


public void setCheckRemark(String checkRemark) {
	this.checkRemark = checkRemark;
}

/**       
 *      *            @hibernate.property
 *             column="CHECK_TIME"
 *             length="7"
 *         
 */
public Date getCheckTime() {
	return checkTime;
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
	return checkUser;
}


public void setCheckUser(String checkUser) {
	this.checkUser = checkUser;
}


/**       
 *      *            @hibernate.property
 *             column="CONFIRM_REMARK"
 *             length="500"
 *         
 */
public String getConfirmRemark() {
	return confirmRemark;
}


public void setConfirmRemark(String confirmRemark) {
	this.confirmRemark = confirmRemark;
}

/**       
 *      *            @hibernate.property
 *             column="CONFIRM_STATUS"
 *             length="1"
 *         
 */
public String getConfirmStatus() {
	return confirmStatus;
}


public void setConfirmStatus(String confirmStatus) {
	this.confirmStatus = confirmStatus;
}

/**       
 *      *            @hibernate.property
 *             column="CONFIRM_TIME"
 *             length="7"
 *         
 */
public Date getConfirmTime() {
	return confirmTime;
}


public void setConfirmTime(Date confirmTime) {
	this.confirmTime = confirmTime;
}

/**       
 *      *            @hibernate.property
 *             column="CONFIRM_USER"
 *             length="7"
 *         
 */
public String getConfirmUser() {
	return confirmUser;
}


public void setConfirmUser(String confirmUser) {
	this.confirmUser = confirmUser;
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
 *      *            @hibernate.property
 *             column="ADDR_CONFIRM"
 *             length="1"
 *         
 */
public String getAddrConfirm() {
	return addrConfirm;
}


public void setAddrConfirm(String addrConfirm) {
	this.addrConfirm = addrConfirm;
}


/**       
 *      *            @hibernate.property
 *             column="ADDR_CHECK"
 *             length="1"
 *         
 */
public String getAddrCheck() {
	return addrCheck;
}


public void setAddrCheck(String addrCheck) {
	this.addrCheck = addrCheck;
}


/**       
 *      *            @hibernate.property
 *             column="EDIT_TIME"
 *             length="8"
 *         
 */
public Date getEditTime() {
	return editTime;
}


public void setEditTime(Date editTime) {
	this.editTime = editTime;
}

/**       
 *      *            @hibernate.property
 *             column="ADDR_CHECK_TIME"
 *             length="8"
 *         
 */
public Date getAddrCheckTime() {
	return addrCheckTime;
}


public void setAddrCheckTime(Date addrCheckTime) {
	this.addrCheckTime = addrCheckTime;
}

/**       
 *      *            @hibernate.property
 *             column="ADDR_CHECK_USER"
 *             length="20"
 *         
 */
public String getAddrCheckUser() {
	return addrCheckUser;
}


public void setAddrCheckUser(String addrCheckUser) {
	this.addrCheckUser = addrCheckUser;
}

/**       
 *      *            @hibernate.property
 *             column="ADDR_CONFIRM_TIME"
 *             length="8"
 *         
 */
public Date getAddrConfirmTime() {
	return addrConfirmTime;
}


public void setAddrConfirmTime(Date addrConfirmTime) {
	this.addrConfirmTime = addrConfirmTime;
}

/**       
 *      *            @hibernate.property
 *             column="ADDR_CONFIRM_USER"
 *             length="20"
 *         
 */
public String getAddrConfirmUser() {
	return addrConfirmUser;
}


public void setAddrConfirmUser(String addrConfirmUser) {
	this.addrConfirmUser = addrConfirmUser;
}   





}
