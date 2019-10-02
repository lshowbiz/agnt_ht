package com.joymain.jecs.mi.model;
// Generated 2009-9-14 16:07:31 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import com.joymain.jecs.bd.model.JbdUserValidList;
import com.joymain.jecs.sys.model.SysUser;



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JMI_MEMBER"
 *     
 */

public class JmiMember extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private String userCode;
    private String linkNo;
    private String recommendNo;
    private String cardType;
    private String firstName;
    private String lastName;
    private String petName;
    private String sex;
    private Date birthday;
    private String email;
    private String papertype;
    private String papernumber;
    private String bank;
    private String bankaddress;
    private String bankcard;
    private String bankbook;
    private String phone;
    private String faxtele;
    private String mobiletele;
    private String officetele;
    private String countryCode;
    private String province;
    private String city;
    private String address;
    private String postalcode;
    private Date exitDate;
    private String remark;
    private String isstore;
    private String companyCode;
    private String checkNo;
    private Date checkDate;
    private String createNo;
    private Long linkNum;
    private Date createTime;
    private Date deadlineDate;
    private String pbNo;
    private String pbNo1;
    private String pbNo2;
    private String active;
    private SysUser sysUser;
    private Set<JmiAddrBook> jmiAddrBooks= new HashSet<JmiAddrBook>(0);
    private JmiLinkRef jmiLinkRef;
    private JmiRecommendRef jmiRecommendRef;
    private String miUserName;
    private String spouseName;
    private String spouseIdno;
    private String bankProvince;
    private String bankCity;
    private String district;
    private Date activeTime;
    private String memberType;
    private String oriCard;
    private String flag;
    private BigDecimal oriPv;
    private String changeStatus;
    private String subStoreStatus;
    private String subRecommendStore;
    private Date subStoreCheckDate;
    
    

    private String identityType;
    private String townAddr;
    private String commPostalcode;
    private String commProvince;
    private String commCity;
    private String commDistrict;
    private String commAddr;

    private String villageAddr;
    private String companyName;
    private String personCharge;
    private String companyAddr;
    private String uniteNumber;
    
    private Integer startWeek;
    private Integer validWeek;
    private Integer freezeStatus;
    private Integer beforeFreezeStatus;
    private Set<JbdUserValidList> jbdUserValidList= new HashSet<JbdUserValidList>(0);
    
    private String town;
    private String isDiscount;
    private String isSubStore;

    private String firstNameKana;
    private String lastNameKana;
    private String bankType;
    
    private String building;
    
    private String bankCode;
    private String bankKana;
    
    private String customerLevel;
    
    private String nationality;
    private String recommendStore;
    
    private String shopType;
    
    private String titleRemark;
    

    private String firstNamePy;
    private String lastNamePy;
    private String clAddress;
    private String ecMallPhone;
    private String ecMallStatus;
    private Integer memberLevel;
    
    private Integer notFirst;    
    
    
    private Integer gradeType;
    
    private String isCloudshop;
    private String cloudshopPhone;
    
    private Date cloudStartdate;
    private Date cloudEnddate;
    private Date standardTime;
    private String memberUserType;

    private Date standardMkTime;
    private Integer memberStar;
    
	/**       
     *      *            @hibernate.property
     *             column="STANDARD_MK_TIME"
     *         		
     */
	public Date getStandardMkTime() {
		return standardMkTime;
	}

	public void setStandardMkTime(Date standardMkTime) {
		this.standardMkTime = standardMkTime;
	}
  
    
    /**       
     *      *            @hibernate.property
     *             column="MEMBER_USER_TYPE"
     *         		
     */
	public String getMemberUserType() {
		return memberUserType;
	}

	public void setMemberUserType(String memberUserType) {
		this.memberUserType = memberUserType;
	}

	/**       
     *      *            @hibernate.property
     *             column="CLOUD_STARTDATE"
     *         		
     */
	public Date getCloudStartdate() {
		return cloudStartdate;
	}

	public void setCloudStartdate(Date cloudStartdate) {
		this.cloudStartdate = cloudStartdate;
	}

	/**       
     *      *            @hibernate.property
     *             column="CLOUD_ENDDATE"
     *         		
     */
	public Date getCloudEnddate() {
		return cloudEnddate;
	}

	public void setCloudEnddate(Date cloudEnddate) {
		this.cloudEnddate = cloudEnddate;
	}

	/**       
     *      *            @hibernate.property
     *             column="STANDARD_TIME"
     *         		
     */
	public Date getStandardTime() {
		return standardTime;
	}

	public void setStandardTime(Date standardTime) {
		this.standardTime = standardTime;
	}

	/**       
     *      *            @hibernate.property
     *             column="IS_CLOUDSHOP"
     *         		
     */
	public String getIsCloudshop() {
		return isCloudshop;
	}

	public void setIsCloudshop(String isCloudshop) {
		this.isCloudshop = isCloudshop;
	}
    
    
	/**       
     *      *            @hibernate.property
     *             column="GRADE_TYPE"
     *         		
     */
	public Integer getGradeType() {
		return gradeType;
	}

	public void setGradeType(Integer gradeType) {
		this.gradeType = gradeType;
	}
	/**       
     *      *            @hibernate.property
     *             column="NOT_FIRST"
     *             length="2"
     *  </br>0:�����׹���, 1:������
     */
	public Integer getNotFirst() {
		return notFirst;
	}

	public void setNotFirst(Integer notFirst) {
		this.notFirst = notFirst;
	}
    /**       
     *      *            @hibernate.property
     *             column="MEMBER_LEVEL"
     *         
     */

    public Integer getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(Integer memberLevel) {
		this.memberLevel = memberLevel;
	}
    // Constructors


    /**       
     *      *            @hibernate.property
     *             column="CL_ADDRESS"
     *         
     */
	public String getClAddress() {
		return clAddress;
	}

	public void setClAddress(String clAddress) {
		this.clAddress = clAddress;
	}

    /**       
     *      *            @hibernate.property
     *             column="EC_MALL_PHONE"
     *         
     */
	public String getEcMallPhone() {
		return ecMallPhone;
	}

	public void setEcMallPhone(String ecMallPhone) {
		this.ecMallPhone = ecMallPhone;
	}

    /**       
     *      *            @hibernate.property
     *             column="EC_MALL_STATUS"
     *         
     */
	public String getEcMallStatus() {
		return ecMallStatus;
	}

	public void setEcMallStatus(String ecMallStatus) {
		this.ecMallStatus = ecMallStatus;
	}

    /**       
     *      *            @hibernate.property
     *             column="FIRST_NAME_PY"
     *         
     */
	public String getFirstNamePy() {
		return firstNamePy;
	}

	public void setFirstNamePy(String firstNamePy) {
		this.firstNamePy = firstNamePy;
	}

    /**       
     *      *            @hibernate.property
     *             column="LAST_NAME_PY"
     *         
     */
	public String getLastNamePy() {
		return lastNamePy;
	}

	public void setLastNamePy(String lastNamePy) {
		this.lastNamePy = lastNamePy;
	}

	/**       
     *      *            @hibernate.property
     *             column="TITLE_REMARK"
     *         
     */

	public String getTitleRemark() {
		return titleRemark;
	}

	public void setTitleRemark(String titleRemark) {
		this.titleRemark = titleRemark;
	}

	/**       
     *      *            @hibernate.property
     *             column="SHOP_TYPE"
     *         
     */
	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}

	/**       
     *      *            @hibernate.property
     *             column="RECOMMEND_STORE"
     *         
     */
	public String getRecommendStore() {
		return recommendStore;
	}

	public void setRecommendStore(String recommendStore) {
		this.recommendStore = recommendStore;
	}

	/**       
     *      *            @hibernate.property
     *             column="NATIONALITY"
     *             length="2"
     *         
     */
	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**       
     *      *            @hibernate.property
     *             column="CUSTOMER_LEVEL"
     *             length="3"
     *         
     */
	public String getCustomerLevel() {
		return customerLevel;
	}

	public void setCustomerLevel(String customerLevel) {
		this.customerLevel = customerLevel;
	}

	/**       
     *      *            @hibernate.property
     *             column="BUILDING"
     *             length="500"
     *         
     */
	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	/**       
     *      *            @hibernate.property
     *             column="BANK_TYPE"
     *             length="1"
     *         
     */
	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	/**       
     *      *            @hibernate.property
     *             column="IS_DISCOUNT"
     *             length="1"
     *         
     */
	public String getIsDiscount() {
		return isDiscount;
	}

	public void setIsDiscount(String isDiscount) {
		this.isDiscount = isDiscount;
	}

	/**       
     *      *            @hibernate.property
     *             column="CHANGE_STATUS"
     *             length="1"
     *         
     */
    public String getChangeStatus() {
		return changeStatus;
	}

	public void setChangeStatus(String changeStatus) {
		this.changeStatus = changeStatus;
	}

	/**       
     *      *            @hibernate.property
     *             column="ACTIVE_TIME"
     *             length="1"
     *         
     */
    public Date getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(Date activeTime) {
		this.activeTime = activeTime;
	}

	/**       
     *      *            @hibernate.property
     *             column="DISTRICT"
     *             length="20"
     *         
     */
    public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}
	
	/**       
     *      *            @hibernate.property
     *             column="CLOUDSHOP_PHONE"
     *         		
     */
	public String getCloudshopPhone() {
		return cloudshopPhone;
	}

	public void setCloudshopPhone(String cloudshopPhone) {
		this.cloudshopPhone = cloudshopPhone;
	}

	/** default constructor */
    public JmiMember() {
    }

	/** minimal constructor */
    public JmiMember(String linkNo, String recommendNo, String active) {
        this.active = active;
    }
    
    /** full constructor */
    public JmiMember(String linkNo, String recommendNo, String cardType, String firstName, String lastName, String petName, String sex, Date birthday, String email, String papertype, String papernumber, String bank, String bankaddress, String bankcard, String bankbook, String phone, String faxtele, String mobiletele, String officetele, String countryCode, String province, String city, String address, String postalcode, Date exitDate, String remark, String isstore, String companyCode, String checkNo, Date checkDate, String createNo, Date createTime, Date deadlineDate, String pbNo, String pbNo1, String pbNo2, String active) {

        this.cardType = cardType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.petName = petName;
        this.sex = sex;
        this.birthday = birthday;
        this.email = email;
        this.papertype = papertype;
        this.papernumber = papernumber;
        this.bank = bank;
        this.bankaddress = bankaddress;
        this.bankcard = bankcard;
        this.bankbook = bankbook;
        this.phone = phone;
        this.faxtele = faxtele;
        this.mobiletele = mobiletele;
        this.officetele = officetele;
        this.countryCode = countryCode;
        this.province = province;
        this.city = city;
        this.address = address;
        this.postalcode = postalcode;
        this.exitDate = exitDate;
        this.remark = remark;
        this.isstore = isstore;
        this.companyCode = companyCode;
        this.checkNo = checkNo;
        this.checkDate = checkDate;
        this.createNo = createNo;
        this.createTime = createTime;
        this.deadlineDate = deadlineDate;
        this.pbNo = pbNo;
        this.pbNo1 = pbNo1;
        this.pbNo2 = pbNo2;
        this.active = active;
    }
    

   
    // Property accessors
   
	/**
	 * @hibernate.one-to-one cascade="all"
	 */
    public SysUser getSysUser() {
		return sysUser;
	}
	public void setSysUser(SysUser sysUser) {
		
		this.sysUser = sysUser;
	}
	
    /**       
     *      *            @hibernate.id
     *      	generator-class="assigned"
     *             column="USER_CODE"
     *         @hibernate.generator-param
				name="property"
				value="sysUser"
     */

    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    


    /**       
     *      *            @hibernate.property
     *             column="CARD_TYPE"
     *             length="1"
     *         
     */

    public String getCardType() {
        return this.cardType;
    }
    
    public void setCardType(String cardType) {
        this.cardType = cardType;
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
     *             column="BIRTHDAY"
     *             length="7"
     *         
     */

    public Date getBirthday() {
        return this.birthday;
    }
    
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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
     *             column="PAPERTYPE"
     *             length="20"
     *         
     */

    public String getPapertype() {
        return this.papertype;
    }
    
    public void setPapertype(String papertype) {
        this.papertype = papertype;
    }
    /**       
     *      *            @hibernate.property
     *             column="PAPERNUMBER"
     *             length="30"
     *         
     */

    public String getPapernumber() {
        return this.papernumber;
    }
    
    public void setPapernumber(String papernumber) {
        this.papernumber = papernumber;
    }
    /**       
     *      *            @hibernate.property
     *             column="BANK"
     *             length="80"
     *         
     */

    public String getBank() {
        return this.bank;
    }
    
    public void setBank(String bank) {
        this.bank = bank;
    }
    /**       
     *      *            @hibernate.property
     *             column="BANKADDRESS"
     *             length="200"
     *         
     */

    public String getBankaddress() {
        return this.bankaddress;
    }
    
    public void setBankaddress(String bankaddress) {
        this.bankaddress = bankaddress;
    }
    /**       
     *      *            @hibernate.property
     *             column="BANKCARD"
     *             length="50"
     *         
     */

    public String getBankcard() {
        return this.bankcard;
    }
    
    public void setBankcard(String bankcard) {
        this.bankcard = bankcard;
    }
    /**       
     *      *            @hibernate.property
     *             column="BANKBOOK"
     *             length="50"
     *         
     */

    public String getBankbook() {
        return this.bankbook;
    }
    
    public void setBankbook(String bankbook) {
        this.bankbook = bankbook;
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
     *             column="OFFICETELE"
     *             length="30"
     *         
     */

    public String getOfficetele() {
        return this.officetele;
    }
    
    public void setOfficetele(String officetele) {
        this.officetele = officetele;
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
     *             column="PROVINCE"
     *             length="20"
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
     *             length="30"
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
     *             column="EXIT_DATE"
     *             length="7"
     *         
     */

    public Date getExitDate() {
        return this.exitDate;
    }
    
    public void setExitDate(Date exitDate) {
        this.exitDate = exitDate;
    }
    /**       
     *      *            @hibernate.property
     *             column="REMARK"
     *             length="1000"
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
     *             column="ISSTORE"
     *             length="1"
     *         
     */

    public String getIsstore() {
        return this.isstore;
    }
    
    public void setIsstore(String isstore) {
        this.isstore = isstore;
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
     *             column="CHECK_NO"
     *             length="20"
     *         
     */

    public String getCheckNo() {
        return this.checkNo;
    }
    
    public void setCheckNo(String checkNo) {
        this.checkNo = checkNo;
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
     *             column="CREATE_NO"
     *             length="20"
     *         
     */

    public String getCreateNo() {
        return this.createNo;
    }
    
    public void setCreateNo(String createNo) {
        this.createNo = createNo;
    }
    
    /**       
     *      *            @hibernate.property
     *             column="LINK_NUM"
     *            
     *         
     */
    
    public Long getLinkNum() {
        return this.linkNum;
    }
    
    public void setLinkNum(Long linkNum) {
    	if (linkNum == null) {
    		this.linkNum = 0L;
		}else{
			this.linkNum = linkNum;
		}
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
     *             column="DEADLINE_DATE"
     *             length="7"
     *         
     */

    public Date getDeadlineDate() {
        return this.deadlineDate;
    }
    
    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }
    /**       
     *      *            @hibernate.property
     *             column="PB_NO"
     *             length="30"
     *         
     */

    public String getPbNo() {
        return this.pbNo;
    }
    
    public void setPbNo(String pbNo) {
        this.pbNo = pbNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="PB_NO1"
     *             length="30"
     *         
     */

    public String getPbNo1() {
        return this.pbNo1;
    }
    
    public void setPbNo1(String pbNo1) {
        this.pbNo1 = pbNo1;
    }
    /**       
     *      *            @hibernate.property
     *             column="PB_NO2"
     *             length="30"
     *         
     */

    public String getPbNo2() {
        return this.pbNo2;
    }
    
    public void setPbNo2(String pbNo2) {
        this.pbNo2 = pbNo2;
    }
    /**       
     *      *            @hibernate.property
     *             column="ACTIVE"
     *             length="1"
     *             not-null="true"
     *         
     */

    public String getActive() {
        return this.active;
    }
    
    public void setActive(String active) {
        this.active = active;
    }
    /**       
     *      *            @hibernate.property
     *             column="MEMBER_STAR"
     *         
     */
    
    public Integer getMemberStar() {
    	return this.memberStar;
    }
    
    public void setMemberStar(Integer memberStar) {
    	this.memberStar = memberStar;
    }


	


	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");	
      buffer.append("cardType").append("='").append(getCardType()).append("' ");			
      buffer.append("firstName").append("='").append(getFirstName()).append("' ");			
      buffer.append("lastName").append("='").append(getLastName()).append("' ");			
      buffer.append("petName").append("='").append(getPetName()).append("' ");			
      buffer.append("sex").append("='").append(getSex()).append("' ");			
      buffer.append("birthday").append("='").append(getBirthday()).append("' ");			
      buffer.append("email").append("='").append(getEmail()).append("' ");			
      buffer.append("papertype").append("='").append(getPapertype()).append("' ");			
      buffer.append("papernumber").append("='").append(getPapernumber()).append("' ");			
      buffer.append("bank").append("='").append(getBank()).append("' ");			
      buffer.append("bankaddress").append("='").append(getBankaddress()).append("' ");			
      buffer.append("bankcard").append("='").append(getBankcard()).append("' ");			
      buffer.append("bankbook").append("='").append(getBankbook()).append("' ");			
      buffer.append("phone").append("='").append(getPhone()).append("' ");			
      buffer.append("faxtele").append("='").append(getFaxtele()).append("' ");			
      buffer.append("mobiletele").append("='").append(getMobiletele()).append("' ");			
      buffer.append("officetele").append("='").append(getOfficetele()).append("' ");			
      buffer.append("countryCode").append("='").append(getCountryCode()).append("' ");			
      buffer.append("province").append("='").append(getProvince()).append("' ");			
      buffer.append("city").append("='").append(getCity()).append("' ");			
      buffer.append("address").append("='").append(getAddress()).append("' ");			
      buffer.append("postalcode").append("='").append(getPostalcode()).append("' ");			
      buffer.append("exitDate").append("='").append(getExitDate()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");			
      buffer.append("isstore").append("='").append(getIsstore()).append("' ");			
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("checkNo").append("='").append(getCheckNo()).append("' ");			
      buffer.append("checkDate").append("='").append(getCheckDate()).append("' ");			
      buffer.append("createNo").append("='").append(getCreateNo()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("deadlineDate").append("='").append(getDeadlineDate()).append("' ");			
      buffer.append("pbNo").append("='").append(getPbNo()).append("' ");			
      buffer.append("pbNo1").append("='").append(getPbNo1()).append("' ");			
      buffer.append("pbNo2").append("='").append(getPbNo2()).append("' ");			
      buffer.append("active").append("='").append(getActive()).append("' ");			
      buffer.append("memberStar").append("='").append(getMemberStar()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JmiMember) ) return false;
		 JmiMember castOther = ( JmiMember ) other; 
         
		 return ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getCardType()==castOther.getCardType()) || ( this.getCardType()!=null && castOther.getCardType()!=null && this.getCardType().equals(castOther.getCardType()) ) )
 && ( (this.getFirstName()==castOther.getFirstName()) || ( this.getFirstName()!=null && castOther.getFirstName()!=null && this.getFirstName().equals(castOther.getFirstName()) ) )
 && ( (this.getLastName()==castOther.getLastName()) || ( this.getLastName()!=null && castOther.getLastName()!=null && this.getLastName().equals(castOther.getLastName()) ) )
 && ( (this.getPetName()==castOther.getPetName()) || ( this.getPetName()!=null && castOther.getPetName()!=null && this.getPetName().equals(castOther.getPetName()) ) )
 && ( (this.getSex()==castOther.getSex()) || ( this.getSex()!=null && castOther.getSex()!=null && this.getSex().equals(castOther.getSex()) ) )
 && ( (this.getBirthday()==castOther.getBirthday()) || ( this.getBirthday()!=null && castOther.getBirthday()!=null && this.getBirthday().equals(castOther.getBirthday()) ) )
 && ( (this.getEmail()==castOther.getEmail()) || ( this.getEmail()!=null && castOther.getEmail()!=null && this.getEmail().equals(castOther.getEmail()) ) )
 && ( (this.getPapertype()==castOther.getPapertype()) || ( this.getPapertype()!=null && castOther.getPapertype()!=null && this.getPapertype().equals(castOther.getPapertype()) ) )
 && ( (this.getPapernumber()==castOther.getPapernumber()) || ( this.getPapernumber()!=null && castOther.getPapernumber()!=null && this.getPapernumber().equals(castOther.getPapernumber()) ) )
 && ( (this.getBank()==castOther.getBank()) || ( this.getBank()!=null && castOther.getBank()!=null && this.getBank().equals(castOther.getBank()) ) )
 && ( (this.getBankaddress()==castOther.getBankaddress()) || ( this.getBankaddress()!=null && castOther.getBankaddress()!=null && this.getBankaddress().equals(castOther.getBankaddress()) ) )
 && ( (this.getBankcard()==castOther.getBankcard()) || ( this.getBankcard()!=null && castOther.getBankcard()!=null && this.getBankcard().equals(castOther.getBankcard()) ) )
 && ( (this.getBankbook()==castOther.getBankbook()) || ( this.getBankbook()!=null && castOther.getBankbook()!=null && this.getBankbook().equals(castOther.getBankbook()) ) )
 && ( (this.getPhone()==castOther.getPhone()) || ( this.getPhone()!=null && castOther.getPhone()!=null && this.getPhone().equals(castOther.getPhone()) ) )
 && ( (this.getFaxtele()==castOther.getFaxtele()) || ( this.getFaxtele()!=null && castOther.getFaxtele()!=null && this.getFaxtele().equals(castOther.getFaxtele()) ) )
 && ( (this.getMobiletele()==castOther.getMobiletele()) || ( this.getMobiletele()!=null && castOther.getMobiletele()!=null && this.getMobiletele().equals(castOther.getMobiletele()) ) )
 && ( (this.getOfficetele()==castOther.getOfficetele()) || ( this.getOfficetele()!=null && castOther.getOfficetele()!=null && this.getOfficetele().equals(castOther.getOfficetele()) ) )
 && ( (this.getCountryCode()==castOther.getCountryCode()) || ( this.getCountryCode()!=null && castOther.getCountryCode()!=null && this.getCountryCode().equals(castOther.getCountryCode()) ) )
 && ( (this.getProvince()==castOther.getProvince()) || ( this.getProvince()!=null && castOther.getProvince()!=null && this.getProvince().equals(castOther.getProvince()) ) )
 && ( (this.getCity()==castOther.getCity()) || ( this.getCity()!=null && castOther.getCity()!=null && this.getCity().equals(castOther.getCity()) ) )
 && ( (this.getAddress()==castOther.getAddress()) || ( this.getAddress()!=null && castOther.getAddress()!=null && this.getAddress().equals(castOther.getAddress()) ) )
 && ( (this.getPostalcode()==castOther.getPostalcode()) || ( this.getPostalcode()!=null && castOther.getPostalcode()!=null && this.getPostalcode().equals(castOther.getPostalcode()) ) )
 && ( (this.getExitDate()==castOther.getExitDate()) || ( this.getExitDate()!=null && castOther.getExitDate()!=null && this.getExitDate().equals(castOther.getExitDate()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
 && ( (this.getIsstore()==castOther.getIsstore()) || ( this.getIsstore()!=null && castOther.getIsstore()!=null && this.getIsstore().equals(castOther.getIsstore()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getCheckNo()==castOther.getCheckNo()) || ( this.getCheckNo()!=null && castOther.getCheckNo()!=null && this.getCheckNo().equals(castOther.getCheckNo()) ) )
 && ( (this.getCheckDate()==castOther.getCheckDate()) || ( this.getCheckDate()!=null && castOther.getCheckDate()!=null && this.getCheckDate().equals(castOther.getCheckDate()) ) )
 && ( (this.getCreateNo()==castOther.getCreateNo()) || ( this.getCreateNo()!=null && castOther.getCreateNo()!=null && this.getCreateNo().equals(castOther.getCreateNo()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getDeadlineDate()==castOther.getDeadlineDate()) || ( this.getDeadlineDate()!=null && castOther.getDeadlineDate()!=null && this.getDeadlineDate().equals(castOther.getDeadlineDate()) ) )
 && ( (this.getPbNo()==castOther.getPbNo()) || ( this.getPbNo()!=null && castOther.getPbNo()!=null && this.getPbNo().equals(castOther.getPbNo()) ) )
 && ( (this.getPbNo1()==castOther.getPbNo1()) || ( this.getPbNo1()!=null && castOther.getPbNo1()!=null && this.getPbNo1().equals(castOther.getPbNo1()) ) )
 && ( (this.getPbNo2()==castOther.getPbNo2()) || ( this.getPbNo2()!=null && castOther.getPbNo2()!=null && this.getPbNo2().equals(castOther.getPbNo2()) ) )
 && ( (this.getActive()==castOther.getActive()) || ( this.getActive()!=null && castOther.getActive()!=null && this.getActive().equals(castOther.getActive()) ) )
 && ( (this.getMemberStar()==castOther.getMemberStar()) || ( this.getMemberStar()!=null && castOther.getMemberStar()!=null && this.getMemberStar().equals(castOther.getMemberStar()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getCardType() == null ? 0 : this.getCardType().hashCode() );
         result = 37 * result + ( getFirstName() == null ? 0 : this.getFirstName().hashCode() );
         result = 37 * result + ( getLastName() == null ? 0 : this.getLastName().hashCode() );
         result = 37 * result + ( getPetName() == null ? 0 : this.getPetName().hashCode() );
         result = 37 * result + ( getSex() == null ? 0 : this.getSex().hashCode() );
         result = 37 * result + ( getBirthday() == null ? 0 : this.getBirthday().hashCode() );
         result = 37 * result + ( getEmail() == null ? 0 : this.getEmail().hashCode() );
         result = 37 * result + ( getPapertype() == null ? 0 : this.getPapertype().hashCode() );
         result = 37 * result + ( getPapernumber() == null ? 0 : this.getPapernumber().hashCode() );
         result = 37 * result + ( getBank() == null ? 0 : this.getBank().hashCode() );
         result = 37 * result + ( getBankaddress() == null ? 0 : this.getBankaddress().hashCode() );
         result = 37 * result + ( getBankcard() == null ? 0 : this.getBankcard().hashCode() );
         result = 37 * result + ( getBankbook() == null ? 0 : this.getBankbook().hashCode() );
         result = 37 * result + ( getPhone() == null ? 0 : this.getPhone().hashCode() );
         result = 37 * result + ( getFaxtele() == null ? 0 : this.getFaxtele().hashCode() );
         result = 37 * result + ( getMobiletele() == null ? 0 : this.getMobiletele().hashCode() );
         result = 37 * result + ( getOfficetele() == null ? 0 : this.getOfficetele().hashCode() );
         result = 37 * result + ( getCountryCode() == null ? 0 : this.getCountryCode().hashCode() );
         result = 37 * result + ( getProvince() == null ? 0 : this.getProvince().hashCode() );
         result = 37 * result + ( getCity() == null ? 0 : this.getCity().hashCode() );
         result = 37 * result + ( getAddress() == null ? 0 : this.getAddress().hashCode() );
         result = 37 * result + ( getPostalcode() == null ? 0 : this.getPostalcode().hashCode() );
         result = 37 * result + ( getExitDate() == null ? 0 : this.getExitDate().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         result = 37 * result + ( getIsstore() == null ? 0 : this.getIsstore().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getCheckNo() == null ? 0 : this.getCheckNo().hashCode() );
         result = 37 * result + ( getCheckDate() == null ? 0 : this.getCheckDate().hashCode() );
         result = 37 * result + ( getCreateNo() == null ? 0 : this.getCreateNo().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getDeadlineDate() == null ? 0 : this.getDeadlineDate().hashCode() );
         result = 37 * result + ( getPbNo() == null ? 0 : this.getPbNo().hashCode() );
         result = 37 * result + ( getPbNo1() == null ? 0 : this.getPbNo1().hashCode() );
         result = 37 * result + ( getPbNo2() == null ? 0 : this.getPbNo2().hashCode() );
         result = 37 * result + ( getActive() == null ? 0 : this.getActive().hashCode() );
         result = 37 * result + ( getMemberStar() == null ? 0 : this.getMemberStar().hashCode() );
         return result;
   }
   /**
    *      *            @hibernate.one-to-one
    *					cascade="all"
    *             
    *                  
    */
	public JmiLinkRef getJmiLinkRef() {
		return jmiLinkRef;
	}
	
	public void setJmiLinkRef(JmiLinkRef jmiLinkRef) {
		this.jmiLinkRef = jmiLinkRef;
	}
	   /**
	    *      *            @hibernate.one-to-one
	    *					cascade="all"
	    *             
	    *                  
	    */
	public JmiRecommendRef getJmiRecommendRef() {
		return jmiRecommendRef;
	}
	
	public void setJmiRecommendRef(JmiRecommendRef jmiRecommendRef) {
		this.jmiRecommendRef = jmiRecommendRef;
	}
	
	/**
	 * *
	 * 
	 * @hibernate.set lazy="true" inverse="true" cascade="all"
	 * @hibernate.collection-key column="USER_CODE"
	 * @hibernate.collection-one-to-many class="com.joymain.jecs.mi.model.JmiAddrBook"
	 * 
	 */
	public Set<JmiAddrBook> getJmiAddrBooks() {
		return jmiAddrBooks;
	}
	
	public void setJmiAddrBooks(Set<JmiAddrBook> jmiAddrBooks) {
		this.jmiAddrBooks = jmiAddrBooks;
	}

    /**       
     *      *            @hibernate.property
     *             column="LINK_NO"
     *             length="20"
     *         
     */

	public String getLinkNo() {
		return linkNo;
	}

	public void setLinkNo(String linkNo) {
		this.linkNo = linkNo;
	}

    /**       
     *      *            @hibernate.property
     *             column="RECOMMEND_NO"
     *             length="20"
     *         
     */

	public String getRecommendNo() {
		return recommendNo;
	}

	public void setRecommendNo(String recommendNo) {
		this.recommendNo = recommendNo;
	}


    /**       
     *      *            @hibernate.property
     *             column="MI_USER_NAME"
     *             length="20"
     *         
     */
	public String getMiUserName() {
		return miUserName;
	}

	public void setMiUserName(String miUserName) {
		this.miUserName = miUserName;
	}

    /**       
     *      *            @hibernate.property
     *             column="SPOUSE_IDNO"
     *             length="300"
     *         
     */
	public String getSpouseIdno() {
		return spouseIdno;
	}

	public void setSpouseIdno(String spouseIdno) {
		this.spouseIdno = spouseIdno;
	}

    /**       
     *      *            @hibernate.property
     *             column="SPOUSE_NAME"
     *             length="20"
     *         
     */
	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}
    /**       
     *      *            @hibernate.property
     *             column="BANK_CITY"
     *             length="20"
     *         
     */
	public String getBankCity() {
		return bankCity;
	}

	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}
    /**       
     *      *            @hibernate.property
     *             column="BANK_PROVINCE"
     *             length="20"
     *         
     */
	public String getBankProvince() {
		return bankProvince;
	}

	public void setBankProvince(String bankProvince) {
		this.bankProvince = bankProvince;
	}

	/**       
     *      *            @hibernate.property
     *             column="MEMBER_TYPE"
     *             length="1"
     *         
     */
	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	/**       
     *      *            @hibernate.property
     *             column="ORI_CARD"
     *             length="1"
     *         
     */
	public String getOriCard() {
		return oriCard;
	}

	public void setOriCard(String oriCard) {
		this.oriCard = oriCard;
	}

    /**       
     *      *            @hibernate.property
     *             column="FLAG"
     *             length="1"
     *         
     */
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

    /**       
     *      *            @hibernate.property
     *             column="ORI_PV"
     *             length="18"
     *         
     */
	public BigDecimal getOriPv() {
		return oriPv;
	}

	public void setOriPv(BigDecimal oriPv) {
		this.oriPv = oriPv;
	}

    /**       
     *      *            @hibernate.property
     *             column="SUB_RECOMMEND_STORE"
     *             length="20"
     *         
     */
	public String getSubRecommendStore() {
		return subRecommendStore;
	}

	public void setSubRecommendStore(String subRecommendStore) {
		this.subRecommendStore = subRecommendStore;
	}


	/**       
     *      *            @hibernate.property
     *             column="SUB_STORE_CHECK_DATE"
     *             length="1"
     *         
     */
	public Date getSubStoreCheckDate() {
		return subStoreCheckDate;
	}

	public void setSubStoreCheckDate(Date subStoreCheckDate) {
		this.subStoreCheckDate = subStoreCheckDate;
	}
	/**       
     *      *            @hibernate.property
     *             column="SUB_STORE_STATUS"
     *             length="1"
     *         
     */
	public String getSubStoreStatus() {
		return subStoreStatus;
	}

	public void setSubStoreStatus(String subStoreStatus) {
		this.subStoreStatus = subStoreStatus;
	}

	/**       
     *      *            @hibernate.property
     *             column="COMM_ADDR"
     *             length="300"
     *         
     */
	public String getCommAddr() {
		return commAddr;
	}

	public void setCommAddr(String commAddr) {
		this.commAddr = commAddr;
	}

	/**       
     *      *            @hibernate.property
     *             column="COMM_CITY"
     *             length="20"
     *         
     */
	public String getCommCity() {
		return commCity;
	}

	public void setCommCity(String commCity) {
		this.commCity = commCity;
	}

	/**       
     *      *            @hibernate.property
     *             column="COMM_POSTALCODE"
     *             length="20"
     *         
     */
	public String getCommPostalcode() {
		return commPostalcode;
	}

	public void setCommPostalcode(String commPostalcode) {
		this.commPostalcode = commPostalcode;
	}

	/**       
     *      *            @hibernate.property
     *             column="COMM_PROVINCE"
     *             length="20"
     *         
     */
	public String getCommProvince() {
		return commProvince;
	}

	public void setCommProvince(String commProvince) {
		this.commProvince = commProvince;
	}

	/**       
     *      *            @hibernate.property
     *             column="COMPANY_ADDR"
     *             length="300"
     *         
     */
	public String getCompanyAddr() {
		return companyAddr;
	}

	public void setCompanyAddr(String companyAddr) {
		this.companyAddr = companyAddr;
	}

	/**       
     *      *            @hibernate.property
     *             column="COMPANY_NAME"
     *             length="200"
     *         
     */
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**       
     *      *            @hibernate.property
     *             column="IDENTITY_TYPE"
     *             length="1"
     *         
     */
	public String getIdentityType() {
		return identityType;
	}

	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}

	/**       
     *      *            @hibernate.property
     *             column="PERSON_CHARGE"
     *             length="100"
     *         
     */
	public String getPersonCharge() {
		return personCharge;
	}

	public void setPersonCharge(String personCharge) {
		this.personCharge = personCharge;
	}

	/**       
     *      *            @hibernate.property
     *             column="TOWN_ADDR"
     *             length="300"
     *         
     */
	public String getTownAddr() {
		return townAddr;
	}

	public void setTownAddr(String townAddr) {
		this.townAddr = townAddr;
	}

	/**       
     *      *            @hibernate.property
     *             column="UNITE_NUMBER"
     *             length="100"
     *         
     */
	public String getUniteNumber() {
		return uniteNumber;
	}

	public void setUniteNumber(String uniteNumber) {
		this.uniteNumber = uniteNumber;
	}

	/**       
     *      *            @hibernate.property
     *             column="VILLAGE_ADDR"
     *             length="300"
     *         
     */
	public String getVillageAddr() {
		return villageAddr;
	}

	public void setVillageAddr(String villageAddr) {
		this.villageAddr = villageAddr;
	}

	/**       
     *      *            @hibernate.property
     *             column="BEFORE_FREEZE_STATUS"
     *             length="8"
     *         
     */
	public Integer getBeforeFreezeStatus() {
		return beforeFreezeStatus;
	}

	public void setBeforeFreezeStatus(Integer beforeFreezeStatus) {
		this.beforeFreezeStatus = beforeFreezeStatus;
	}

	/**       
     *      *            @hibernate.property
     *             column="FREEZE_STATUS"
     *             length="8"
     *         
     */
	public Integer getFreezeStatus() {
		return freezeStatus;
	}

	public void setFreezeStatus(Integer freezeStatus) {
		this.freezeStatus = freezeStatus;
	}

	/**       
     *      *            @hibernate.property
     *             column="START_WEEK"
     *             length="8"
     *         
     */
	public Integer getStartWeek() {
		return startWeek;
	}

	public void setStartWeek(Integer startWeek) {
		this.startWeek = startWeek;
	}
	/**       
     *      *            @hibernate.property
     *             column="VALID_WEEK"
     *             length="8"
     *         
     */
	public Integer getValidWeek() {
		return validWeek;
	}

	public void setValidWeek(Integer validWeek) {
		this.validWeek = validWeek;
	}
	
	/**
	 * *
	 * 
	 * @hibernate.set lazy="true" inverse="true" cascade="all"
	 * @hibernate.collection-key column="USER_CODE"
	 * @hibernate.collection-one-to-many class="com.joymain.jecs.bd.model.JbdUserValidList"
	 * 
	 */
	public Set<JbdUserValidList> getJbdUserValidList() {
		return jbdUserValidList;
	}

	public void setJbdUserValidList(Set<JbdUserValidList> jbdUserValidList) {
		this.jbdUserValidList = jbdUserValidList;
	}

	/**       
     *      *            @hibernate.property
     *             column="COMM_DISTRICT"
     *             length="20"
     *         
     */
	public String getCommDistrict() {
		return commDistrict;
	}

	public void setCommDistrict(String commDistrict) {
		this.commDistrict = commDistrict;
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
     *             column="IS_SUB_STORE"
     *             length="1"
     *         
     */
	public String getIsSubStore() {
		return isSubStore;
	}

	public void setIsSubStore(String isSubStore) {
		this.isSubStore = isSubStore;
	}

    /**       
     *      *            @hibernate.property
     *             column="FIRST_NAME_KANA"
     *             length="100"
     *         
     */
	public String getFirstNameKana() {
		return firstNameKana;
	}

	public void setFirstNameKana(String firstNameKana) {
		this.firstNameKana = firstNameKana;
	}


    /**       
     *      *            @hibernate.property
     *             column="LAST_NAME_KANA"
     *             length="100"
     *         
     */
	public String getLastNameKana() {
		return lastNameKana;
	}

	public void setLastNameKana(String lastNameKana) {
		this.lastNameKana = lastNameKana;
	}
    /**       
     *      *            @hibernate.property
     *             column="BANK_CODE"
     *             length="100"
     *         
     */
	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
    /**       
     *      *            @hibernate.property
     *             column="BANK_KANA"
     *             length="100"
     *         
     */
	public String getBankKana() {
		return bankKana;
	}

	public void setBankKana(String bankKana) {
		this.bankKana = bankKana;
	}
}
