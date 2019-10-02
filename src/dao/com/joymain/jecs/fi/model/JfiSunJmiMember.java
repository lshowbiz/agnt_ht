package com.joymain.jecs.fi.model;
// Generated 2010-11-16 11:19:08 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JFI_SUN_JMI_MEMBER"
 *     
 */

public class JfiSunJmiMember extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


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
    private Date createTime;
    private Date deadlineDate;
    private String pbNo;
    private String pbNo1;
    private String pbNo2;
    private String active;
    private String miUserName;
    private String spouseName;
    private String spouseIdno;
    private String bankCity;
    private String bankProvince;
    private String district;
    private BigDecimal linkNum;
    private BigDecimal pendingLinkNum;
    private BigDecimal recommendNum;
    private BigDecimal pendingRecommendNum;
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
    private String commAddr;
    private String villageAddr;
    private String companyName;
    private String personCharge;
    private String companyAddr;
    private String uniteNumber;
    private BigDecimal startWeek;
    private BigDecimal validWeek;
    private BigDecimal freezeStatus;
    private BigDecimal beforeFreezeStatus;
    private String commDistrict;
    private String town;
    private String isDiscount;
    private String isSubStore;
    private String activeStatus;


    // Constructors

    /** default constructor */
    public JfiSunJmiMember() {
    }

	/** minimal constructor */
    public JfiSunJmiMember(String linkNo, String recommendNo, String active) {
        this.linkNo = linkNo;
        this.recommendNo = recommendNo;
        this.active = active;
    }
    
    /** full constructor */
    public JfiSunJmiMember(String linkNo, String recommendNo, String cardType, String firstName, String lastName, String petName, String sex, Date birthday, String email, String papertype, String papernumber, String bank, String bankaddress, String bankcard, String bankbook, String phone, String faxtele, String mobiletele, String officetele, String countryCode, String province, String city, String address, String postalcode, Date exitDate, String remark, String isstore, String companyCode, String checkNo, Date checkDate, String createNo, Date createTime, Date deadlineDate, String pbNo, String pbNo1, String pbNo2, String active, String miUserName, String spouseName, String spouseIdno, String bankCity, String bankProvince, String district, BigDecimal linkNum, BigDecimal pendingLinkNum, BigDecimal recommendNum, BigDecimal pendingRecommendNum, Date activeTime, String memberType, String oriCard, String flag, BigDecimal oriPv, String changeStatus, String subStoreStatus, String subRecommendStore, Date subStoreCheckDate, String identityType, String townAddr, String commPostalcode, String commProvince, String commCity, String commAddr, String villageAddr, String companyName, String personCharge, String companyAddr, String uniteNumber, BigDecimal startWeek, BigDecimal validWeek, BigDecimal freezeStatus, BigDecimal beforeFreezeStatus, String commDistrict, String town, String isDiscount, String isSubStore, String activeStatus) {
        this.linkNo = linkNo;
        this.recommendNo = recommendNo;
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
        this.miUserName = miUserName;
        this.spouseName = spouseName;
        this.spouseIdno = spouseIdno;
        this.bankCity = bankCity;
        this.bankProvince = bankProvince;
        this.district = district;
        this.linkNum = linkNum;
        this.pendingLinkNum = pendingLinkNum;
        this.recommendNum = recommendNum;
        this.pendingRecommendNum = pendingRecommendNum;
        this.activeTime = activeTime;
        this.memberType = memberType;
        this.oriCard = oriCard;
        this.flag = flag;
        this.oriPv = oriPv;
        this.changeStatus = changeStatus;
        this.subStoreStatus = subStoreStatus;
        this.subRecommendStore = subRecommendStore;
        this.subStoreCheckDate = subStoreCheckDate;
        this.identityType = identityType;
        this.townAddr = townAddr;
        this.commPostalcode = commPostalcode;
        this.commProvince = commProvince;
        this.commCity = commCity;
        this.commAddr = commAddr;
        this.villageAddr = villageAddr;
        this.companyName = companyName;
        this.personCharge = personCharge;
        this.companyAddr = companyAddr;
        this.uniteNumber = uniteNumber;
        this.startWeek = startWeek;
        this.validWeek = validWeek;
        this.freezeStatus = freezeStatus;
        this.beforeFreezeStatus = beforeFreezeStatus;
        this.commDistrict = commDistrict;
        this.town = town;
        this.isDiscount = isDiscount;
        this.isSubStore = isSubStore;
        this.activeStatus = activeStatus;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
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
     *             column="LINK_NO"
     *             length="20"
     *             not-null="true"
     *         
     */

    public String getLinkNo() {
        return this.linkNo;
    }
    
    public void setLinkNo(String linkNo) {
        this.linkNo = linkNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="RECOMMEND_NO"
     *             length="20"
     *             not-null="true"
     *         
     */

    public String getRecommendNo() {
        return this.recommendNo;
    }
    
    public void setRecommendNo(String recommendNo) {
        this.recommendNo = recommendNo;
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
     *             length="100"
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
     *             length="300"
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
     *             length="100"
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
     *             length="100"
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
     *             column="MI_USER_NAME"
     *             length="300"
     *         
     */

    public String getMiUserName() {
        return this.miUserName;
    }
    
    public void setMiUserName(String miUserName) {
        this.miUserName = miUserName;
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
     *             column="SPOUSE_IDNO"
     *             length="30"
     *         
     */

    public String getSpouseIdno() {
        return this.spouseIdno;
    }
    
    public void setSpouseIdno(String spouseIdno) {
        this.spouseIdno = spouseIdno;
    }
    /**       
     *      *            @hibernate.property
     *             column="BANK_CITY"
     *             length="20"
     *         
     */

    public String getBankCity() {
        return this.bankCity;
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
        return this.bankProvince;
    }
    
    public void setBankProvince(String bankProvince) {
        this.bankProvince = bankProvince;
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
     *             column="LINK_NUM"
     *             length="22"
     *         
     */

    public BigDecimal getLinkNum() {
        return this.linkNum;
    }
    
    public void setLinkNum(BigDecimal linkNum) {
        this.linkNum = linkNum;
    }
    /**       
     *      *            @hibernate.property
     *             column="PENDING_LINK_NUM"
     *             length="22"
     *         
     */

    public BigDecimal getPendingLinkNum() {
        return this.pendingLinkNum;
    }
    
    public void setPendingLinkNum(BigDecimal pendingLinkNum) {
        this.pendingLinkNum = pendingLinkNum;
    }
    /**       
     *      *            @hibernate.property
     *             column="RECOMMEND_NUM"
     *             length="22"
     *         
     */

    public BigDecimal getRecommendNum() {
        return this.recommendNum;
    }
    
    public void setRecommendNum(BigDecimal recommendNum) {
        this.recommendNum = recommendNum;
    }
    /**       
     *      *            @hibernate.property
     *             column="PENDING_RECOMMEND_NUM"
     *             length="22"
     *         
     */

    public BigDecimal getPendingRecommendNum() {
        return this.pendingRecommendNum;
    }
    
    public void setPendingRecommendNum(BigDecimal pendingRecommendNum) {
        this.pendingRecommendNum = pendingRecommendNum;
    }
    /**       
     *      *            @hibernate.property
     *             column="ACTIVE_TIME"
     *             length="7"
     *         
     */

    public Date getActiveTime() {
        return this.activeTime;
    }
    
    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="MEMBER_TYPE"
     *             length="1"
     *         
     */

    public String getMemberType() {
        return this.memberType;
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
        return this.oriCard;
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
        return this.flag;
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
        return this.oriPv;
    }
    
    public void setOriPv(BigDecimal oriPv) {
        this.oriPv = oriPv;
    }
    /**       
     *      *            @hibernate.property
     *             column="CHANGE_STATUS"
     *             length="1"
     *         
     */

    public String getChangeStatus() {
        return this.changeStatus;
    }
    
    public void setChangeStatus(String changeStatus) {
        this.changeStatus = changeStatus;
    }
    /**       
     *      *            @hibernate.property
     *             column="SUB_STORE_STATUS"
     *             length="1"
     *         
     */

    public String getSubStoreStatus() {
        return this.subStoreStatus;
    }
    
    public void setSubStoreStatus(String subStoreStatus) {
        this.subStoreStatus = subStoreStatus;
    }
    /**       
     *      *            @hibernate.property
     *             column="SUB_RECOMMEND_STORE"
     *             length="20"
     *         
     */

    public String getSubRecommendStore() {
        return this.subRecommendStore;
    }
    
    public void setSubRecommendStore(String subRecommendStore) {
        this.subRecommendStore = subRecommendStore;
    }
    /**       
     *      *            @hibernate.property
     *             column="SUB_STORE_CHECK_DATE"
     *             length="7"
     *         
     */

    public Date getSubStoreCheckDate() {
        return this.subStoreCheckDate;
    }
    
    public void setSubStoreCheckDate(Date subStoreCheckDate) {
        this.subStoreCheckDate = subStoreCheckDate;
    }
    /**       
     *      *            @hibernate.property
     *             column="IDENTITY_TYPE"
     *             length="1"
     *         
     */

    public String getIdentityType() {
        return this.identityType;
    }
    
    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }
    /**       
     *      *            @hibernate.property
     *             column="TOWN_ADDR"
     *             length="300"
     *         
     */

    public String getTownAddr() {
        return this.townAddr;
    }
    
    public void setTownAddr(String townAddr) {
        this.townAddr = townAddr;
    }
    /**       
     *      *            @hibernate.property
     *             column="COMM_POSTALCODE"
     *             length="20"
     *         
     */

    public String getCommPostalcode() {
        return this.commPostalcode;
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
        return this.commProvince;
    }
    
    public void setCommProvince(String commProvince) {
        this.commProvince = commProvince;
    }
    /**       
     *      *            @hibernate.property
     *             column="COMM_CITY"
     *             length="20"
     *         
     */

    public String getCommCity() {
        return this.commCity;
    }
    
    public void setCommCity(String commCity) {
        this.commCity = commCity;
    }
    /**       
     *      *            @hibernate.property
     *             column="COMM_ADDR"
     *             length="300"
     *         
     */

    public String getCommAddr() {
        return this.commAddr;
    }
    
    public void setCommAddr(String commAddr) {
        this.commAddr = commAddr;
    }
    /**       
     *      *            @hibernate.property
     *             column="VILLAGE_ADDR"
     *             length="300"
     *         
     */

    public String getVillageAddr() {
        return this.villageAddr;
    }
    
    public void setVillageAddr(String villageAddr) {
        this.villageAddr = villageAddr;
    }
    /**       
     *      *            @hibernate.property
     *             column="COMPANY_NAME"
     *             length="200"
     *         
     */

    public String getCompanyName() {
        return this.companyName;
    }
    
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    /**       
     *      *            @hibernate.property
     *             column="PERSON_CHARGE"
     *             length="100"
     *         
     */

    public String getPersonCharge() {
        return this.personCharge;
    }
    
    public void setPersonCharge(String personCharge) {
        this.personCharge = personCharge;
    }
    /**       
     *      *            @hibernate.property
     *             column="COMPANY_ADDR"
     *             length="300"
     *         
     */

    public String getCompanyAddr() {
        return this.companyAddr;
    }
    
    public void setCompanyAddr(String companyAddr) {
        this.companyAddr = companyAddr;
    }
    /**       
     *      *            @hibernate.property
     *             column="UNITE_NUMBER"
     *             length="100"
     *         
     */

    public String getUniteNumber() {
        return this.uniteNumber;
    }
    
    public void setUniteNumber(String uniteNumber) {
        this.uniteNumber = uniteNumber;
    }
    /**       
     *      *            @hibernate.property
     *             column="START_WEEK"
     *             length="22"
     *         
     */

    public BigDecimal getStartWeek() {
        return this.startWeek;
    }
    
    public void setStartWeek(BigDecimal startWeek) {
        this.startWeek = startWeek;
    }
    /**       
     *      *            @hibernate.property
     *             column="VALID_WEEK"
     *             length="22"
     *         
     */

    public BigDecimal getValidWeek() {
        return this.validWeek;
    }
    
    public void setValidWeek(BigDecimal validWeek) {
        this.validWeek = validWeek;
    }
    /**       
     *      *            @hibernate.property
     *             column="FREEZE_STATUS"
     *             length="22"
     *         
     */

    public BigDecimal getFreezeStatus() {
        return this.freezeStatus;
    }
    
    public void setFreezeStatus(BigDecimal freezeStatus) {
        this.freezeStatus = freezeStatus;
    }
    /**       
     *      *            @hibernate.property
     *             column="BEFORE_FREEZE_STATUS"
     *             length="22"
     *         
     */

    public BigDecimal getBeforeFreezeStatus() {
        return this.beforeFreezeStatus;
    }
    
    public void setBeforeFreezeStatus(BigDecimal beforeFreezeStatus) {
        this.beforeFreezeStatus = beforeFreezeStatus;
    }
    /**       
     *      *            @hibernate.property
     *             column="COMM_DISTRICT"
     *             length="20"
     *         
     */

    public String getCommDistrict() {
        return this.commDistrict;
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
        return this.town;
    }
    
    public void setTown(String town) {
        this.town = town;
    }
    /**       
     *      *            @hibernate.property
     *             column="IS_DISCOUNT"
     *             length="1"
     *         
     */

    public String getIsDiscount() {
        return this.isDiscount;
    }
    
    public void setIsDiscount(String isDiscount) {
        this.isDiscount = isDiscount;
    }
    /**       
     *      *            @hibernate.property
     *             column="IS_SUB_STORE"
     *             length="1"
     *         
     */

    public String getIsSubStore() {
        return this.isSubStore;
    }
    
    public void setIsSubStore(String isSubStore) {
        this.isSubStore = isSubStore;
    }
    /**       
     *      *            @hibernate.property
     *             column="ACTIVE_STATUS"
     *             length="1"
     *         
     */

    public String getActiveStatus() {
        return this.activeStatus;
    }
    
    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("linkNo").append("='").append(getLinkNo()).append("' ");			
      buffer.append("recommendNo").append("='").append(getRecommendNo()).append("' ");			
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
      buffer.append("miUserName").append("='").append(getMiUserName()).append("' ");			
      buffer.append("spouseName").append("='").append(getSpouseName()).append("' ");			
      buffer.append("spouseIdno").append("='").append(getSpouseIdno()).append("' ");			
      buffer.append("bankCity").append("='").append(getBankCity()).append("' ");			
      buffer.append("bankProvince").append("='").append(getBankProvince()).append("' ");			
      buffer.append("district").append("='").append(getDistrict()).append("' ");			
      buffer.append("linkNum").append("='").append(getLinkNum()).append("' ");			
      buffer.append("pendingLinkNum").append("='").append(getPendingLinkNum()).append("' ");			
      buffer.append("recommendNum").append("='").append(getRecommendNum()).append("' ");			
      buffer.append("pendingRecommendNum").append("='").append(getPendingRecommendNum()).append("' ");			
      buffer.append("activeTime").append("='").append(getActiveTime()).append("' ");			
      buffer.append("memberType").append("='").append(getMemberType()).append("' ");			
      buffer.append("oriCard").append("='").append(getOriCard()).append("' ");			
      buffer.append("flag").append("='").append(getFlag()).append("' ");			
      buffer.append("oriPv").append("='").append(getOriPv()).append("' ");			
      buffer.append("changeStatus").append("='").append(getChangeStatus()).append("' ");			
      buffer.append("subStoreStatus").append("='").append(getSubStoreStatus()).append("' ");			
      buffer.append("subRecommendStore").append("='").append(getSubRecommendStore()).append("' ");			
      buffer.append("subStoreCheckDate").append("='").append(getSubStoreCheckDate()).append("' ");			
      buffer.append("identityType").append("='").append(getIdentityType()).append("' ");			
      buffer.append("townAddr").append("='").append(getTownAddr()).append("' ");			
      buffer.append("commPostalcode").append("='").append(getCommPostalcode()).append("' ");			
      buffer.append("commProvince").append("='").append(getCommProvince()).append("' ");			
      buffer.append("commCity").append("='").append(getCommCity()).append("' ");			
      buffer.append("commAddr").append("='").append(getCommAddr()).append("' ");			
      buffer.append("villageAddr").append("='").append(getVillageAddr()).append("' ");			
      buffer.append("companyName").append("='").append(getCompanyName()).append("' ");			
      buffer.append("personCharge").append("='").append(getPersonCharge()).append("' ");			
      buffer.append("companyAddr").append("='").append(getCompanyAddr()).append("' ");			
      buffer.append("uniteNumber").append("='").append(getUniteNumber()).append("' ");			
      buffer.append("startWeek").append("='").append(getStartWeek()).append("' ");			
      buffer.append("validWeek").append("='").append(getValidWeek()).append("' ");			
      buffer.append("freezeStatus").append("='").append(getFreezeStatus()).append("' ");			
      buffer.append("beforeFreezeStatus").append("='").append(getBeforeFreezeStatus()).append("' ");			
      buffer.append("commDistrict").append("='").append(getCommDistrict()).append("' ");			
      buffer.append("town").append("='").append(getTown()).append("' ");			
      buffer.append("isDiscount").append("='").append(getIsDiscount()).append("' ");			
      buffer.append("isSubStore").append("='").append(getIsSubStore()).append("' ");			
      buffer.append("activeStatus").append("='").append(getActiveStatus()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JfiSunJmiMember) ) return false;
		 JfiSunJmiMember castOther = ( JfiSunJmiMember ) other; 
         
		 return ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getLinkNo()==castOther.getLinkNo()) || ( this.getLinkNo()!=null && castOther.getLinkNo()!=null && this.getLinkNo().equals(castOther.getLinkNo()) ) )
 && ( (this.getRecommendNo()==castOther.getRecommendNo()) || ( this.getRecommendNo()!=null && castOther.getRecommendNo()!=null && this.getRecommendNo().equals(castOther.getRecommendNo()) ) )
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
 && ( (this.getMiUserName()==castOther.getMiUserName()) || ( this.getMiUserName()!=null && castOther.getMiUserName()!=null && this.getMiUserName().equals(castOther.getMiUserName()) ) )
 && ( (this.getSpouseName()==castOther.getSpouseName()) || ( this.getSpouseName()!=null && castOther.getSpouseName()!=null && this.getSpouseName().equals(castOther.getSpouseName()) ) )
 && ( (this.getSpouseIdno()==castOther.getSpouseIdno()) || ( this.getSpouseIdno()!=null && castOther.getSpouseIdno()!=null && this.getSpouseIdno().equals(castOther.getSpouseIdno()) ) )
 && ( (this.getBankCity()==castOther.getBankCity()) || ( this.getBankCity()!=null && castOther.getBankCity()!=null && this.getBankCity().equals(castOther.getBankCity()) ) )
 && ( (this.getBankProvince()==castOther.getBankProvince()) || ( this.getBankProvince()!=null && castOther.getBankProvince()!=null && this.getBankProvince().equals(castOther.getBankProvince()) ) )
 && ( (this.getDistrict()==castOther.getDistrict()) || ( this.getDistrict()!=null && castOther.getDistrict()!=null && this.getDistrict().equals(castOther.getDistrict()) ) )
 && ( (this.getLinkNum()==castOther.getLinkNum()) || ( this.getLinkNum()!=null && castOther.getLinkNum()!=null && this.getLinkNum().equals(castOther.getLinkNum()) ) )
 && ( (this.getPendingLinkNum()==castOther.getPendingLinkNum()) || ( this.getPendingLinkNum()!=null && castOther.getPendingLinkNum()!=null && this.getPendingLinkNum().equals(castOther.getPendingLinkNum()) ) )
 && ( (this.getRecommendNum()==castOther.getRecommendNum()) || ( this.getRecommendNum()!=null && castOther.getRecommendNum()!=null && this.getRecommendNum().equals(castOther.getRecommendNum()) ) )
 && ( (this.getPendingRecommendNum()==castOther.getPendingRecommendNum()) || ( this.getPendingRecommendNum()!=null && castOther.getPendingRecommendNum()!=null && this.getPendingRecommendNum().equals(castOther.getPendingRecommendNum()) ) )
 && ( (this.getActiveTime()==castOther.getActiveTime()) || ( this.getActiveTime()!=null && castOther.getActiveTime()!=null && this.getActiveTime().equals(castOther.getActiveTime()) ) )
 && ( (this.getMemberType()==castOther.getMemberType()) || ( this.getMemberType()!=null && castOther.getMemberType()!=null && this.getMemberType().equals(castOther.getMemberType()) ) )
 && ( (this.getOriCard()==castOther.getOriCard()) || ( this.getOriCard()!=null && castOther.getOriCard()!=null && this.getOriCard().equals(castOther.getOriCard()) ) )
 && ( (this.getFlag()==castOther.getFlag()) || ( this.getFlag()!=null && castOther.getFlag()!=null && this.getFlag().equals(castOther.getFlag()) ) )
 && ( (this.getOriPv()==castOther.getOriPv()) || ( this.getOriPv()!=null && castOther.getOriPv()!=null && this.getOriPv().equals(castOther.getOriPv()) ) )
 && ( (this.getChangeStatus()==castOther.getChangeStatus()) || ( this.getChangeStatus()!=null && castOther.getChangeStatus()!=null && this.getChangeStatus().equals(castOther.getChangeStatus()) ) )
 && ( (this.getSubStoreStatus()==castOther.getSubStoreStatus()) || ( this.getSubStoreStatus()!=null && castOther.getSubStoreStatus()!=null && this.getSubStoreStatus().equals(castOther.getSubStoreStatus()) ) )
 && ( (this.getSubRecommendStore()==castOther.getSubRecommendStore()) || ( this.getSubRecommendStore()!=null && castOther.getSubRecommendStore()!=null && this.getSubRecommendStore().equals(castOther.getSubRecommendStore()) ) )
 && ( (this.getSubStoreCheckDate()==castOther.getSubStoreCheckDate()) || ( this.getSubStoreCheckDate()!=null && castOther.getSubStoreCheckDate()!=null && this.getSubStoreCheckDate().equals(castOther.getSubStoreCheckDate()) ) )
 && ( (this.getIdentityType()==castOther.getIdentityType()) || ( this.getIdentityType()!=null && castOther.getIdentityType()!=null && this.getIdentityType().equals(castOther.getIdentityType()) ) )
 && ( (this.getTownAddr()==castOther.getTownAddr()) || ( this.getTownAddr()!=null && castOther.getTownAddr()!=null && this.getTownAddr().equals(castOther.getTownAddr()) ) )
 && ( (this.getCommPostalcode()==castOther.getCommPostalcode()) || ( this.getCommPostalcode()!=null && castOther.getCommPostalcode()!=null && this.getCommPostalcode().equals(castOther.getCommPostalcode()) ) )
 && ( (this.getCommProvince()==castOther.getCommProvince()) || ( this.getCommProvince()!=null && castOther.getCommProvince()!=null && this.getCommProvince().equals(castOther.getCommProvince()) ) )
 && ( (this.getCommCity()==castOther.getCommCity()) || ( this.getCommCity()!=null && castOther.getCommCity()!=null && this.getCommCity().equals(castOther.getCommCity()) ) )
 && ( (this.getCommAddr()==castOther.getCommAddr()) || ( this.getCommAddr()!=null && castOther.getCommAddr()!=null && this.getCommAddr().equals(castOther.getCommAddr()) ) )
 && ( (this.getVillageAddr()==castOther.getVillageAddr()) || ( this.getVillageAddr()!=null && castOther.getVillageAddr()!=null && this.getVillageAddr().equals(castOther.getVillageAddr()) ) )
 && ( (this.getCompanyName()==castOther.getCompanyName()) || ( this.getCompanyName()!=null && castOther.getCompanyName()!=null && this.getCompanyName().equals(castOther.getCompanyName()) ) )
 && ( (this.getPersonCharge()==castOther.getPersonCharge()) || ( this.getPersonCharge()!=null && castOther.getPersonCharge()!=null && this.getPersonCharge().equals(castOther.getPersonCharge()) ) )
 && ( (this.getCompanyAddr()==castOther.getCompanyAddr()) || ( this.getCompanyAddr()!=null && castOther.getCompanyAddr()!=null && this.getCompanyAddr().equals(castOther.getCompanyAddr()) ) )
 && ( (this.getUniteNumber()==castOther.getUniteNumber()) || ( this.getUniteNumber()!=null && castOther.getUniteNumber()!=null && this.getUniteNumber().equals(castOther.getUniteNumber()) ) )
 && ( (this.getStartWeek()==castOther.getStartWeek()) || ( this.getStartWeek()!=null && castOther.getStartWeek()!=null && this.getStartWeek().equals(castOther.getStartWeek()) ) )
 && ( (this.getValidWeek()==castOther.getValidWeek()) || ( this.getValidWeek()!=null && castOther.getValidWeek()!=null && this.getValidWeek().equals(castOther.getValidWeek()) ) )
 && ( (this.getFreezeStatus()==castOther.getFreezeStatus()) || ( this.getFreezeStatus()!=null && castOther.getFreezeStatus()!=null && this.getFreezeStatus().equals(castOther.getFreezeStatus()) ) )
 && ( (this.getBeforeFreezeStatus()==castOther.getBeforeFreezeStatus()) || ( this.getBeforeFreezeStatus()!=null && castOther.getBeforeFreezeStatus()!=null && this.getBeforeFreezeStatus().equals(castOther.getBeforeFreezeStatus()) ) )
 && ( (this.getCommDistrict()==castOther.getCommDistrict()) || ( this.getCommDistrict()!=null && castOther.getCommDistrict()!=null && this.getCommDistrict().equals(castOther.getCommDistrict()) ) )
 && ( (this.getTown()==castOther.getTown()) || ( this.getTown()!=null && castOther.getTown()!=null && this.getTown().equals(castOther.getTown()) ) )
 && ( (this.getIsDiscount()==castOther.getIsDiscount()) || ( this.getIsDiscount()!=null && castOther.getIsDiscount()!=null && this.getIsDiscount().equals(castOther.getIsDiscount()) ) )
 && ( (this.getIsSubStore()==castOther.getIsSubStore()) || ( this.getIsSubStore()!=null && castOther.getIsSubStore()!=null && this.getIsSubStore().equals(castOther.getIsSubStore()) ) )
 && ( (this.getActiveStatus()==castOther.getActiveStatus()) || ( this.getActiveStatus()!=null && castOther.getActiveStatus()!=null && this.getActiveStatus().equals(castOther.getActiveStatus()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getLinkNo() == null ? 0 : this.getLinkNo().hashCode() );
         result = 37 * result + ( getRecommendNo() == null ? 0 : this.getRecommendNo().hashCode() );
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
         result = 37 * result + ( getMiUserName() == null ? 0 : this.getMiUserName().hashCode() );
         result = 37 * result + ( getSpouseName() == null ? 0 : this.getSpouseName().hashCode() );
         result = 37 * result + ( getSpouseIdno() == null ? 0 : this.getSpouseIdno().hashCode() );
         result = 37 * result + ( getBankCity() == null ? 0 : this.getBankCity().hashCode() );
         result = 37 * result + ( getBankProvince() == null ? 0 : this.getBankProvince().hashCode() );
         result = 37 * result + ( getDistrict() == null ? 0 : this.getDistrict().hashCode() );
         result = 37 * result + ( getLinkNum() == null ? 0 : this.getLinkNum().hashCode() );
         result = 37 * result + ( getPendingLinkNum() == null ? 0 : this.getPendingLinkNum().hashCode() );
         result = 37 * result + ( getRecommendNum() == null ? 0 : this.getRecommendNum().hashCode() );
         result = 37 * result + ( getPendingRecommendNum() == null ? 0 : this.getPendingRecommendNum().hashCode() );
         result = 37 * result + ( getActiveTime() == null ? 0 : this.getActiveTime().hashCode() );
         result = 37 * result + ( getMemberType() == null ? 0 : this.getMemberType().hashCode() );
         result = 37 * result + ( getOriCard() == null ? 0 : this.getOriCard().hashCode() );
         result = 37 * result + ( getFlag() == null ? 0 : this.getFlag().hashCode() );
         result = 37 * result + ( getOriPv() == null ? 0 : this.getOriPv().hashCode() );
         result = 37 * result + ( getChangeStatus() == null ? 0 : this.getChangeStatus().hashCode() );
         result = 37 * result + ( getSubStoreStatus() == null ? 0 : this.getSubStoreStatus().hashCode() );
         result = 37 * result + ( getSubRecommendStore() == null ? 0 : this.getSubRecommendStore().hashCode() );
         result = 37 * result + ( getSubStoreCheckDate() == null ? 0 : this.getSubStoreCheckDate().hashCode() );
         result = 37 * result + ( getIdentityType() == null ? 0 : this.getIdentityType().hashCode() );
         result = 37 * result + ( getTownAddr() == null ? 0 : this.getTownAddr().hashCode() );
         result = 37 * result + ( getCommPostalcode() == null ? 0 : this.getCommPostalcode().hashCode() );
         result = 37 * result + ( getCommProvince() == null ? 0 : this.getCommProvince().hashCode() );
         result = 37 * result + ( getCommCity() == null ? 0 : this.getCommCity().hashCode() );
         result = 37 * result + ( getCommAddr() == null ? 0 : this.getCommAddr().hashCode() );
         result = 37 * result + ( getVillageAddr() == null ? 0 : this.getVillageAddr().hashCode() );
         result = 37 * result + ( getCompanyName() == null ? 0 : this.getCompanyName().hashCode() );
         result = 37 * result + ( getPersonCharge() == null ? 0 : this.getPersonCharge().hashCode() );
         result = 37 * result + ( getCompanyAddr() == null ? 0 : this.getCompanyAddr().hashCode() );
         result = 37 * result + ( getUniteNumber() == null ? 0 : this.getUniteNumber().hashCode() );
         result = 37 * result + ( getStartWeek() == null ? 0 : this.getStartWeek().hashCode() );
         result = 37 * result + ( getValidWeek() == null ? 0 : this.getValidWeek().hashCode() );
         result = 37 * result + ( getFreezeStatus() == null ? 0 : this.getFreezeStatus().hashCode() );
         result = 37 * result + ( getBeforeFreezeStatus() == null ? 0 : this.getBeforeFreezeStatus().hashCode() );
         result = 37 * result + ( getCommDistrict() == null ? 0 : this.getCommDistrict().hashCode() );
         result = 37 * result + ( getTown() == null ? 0 : this.getTown().hashCode() );
         result = 37 * result + ( getIsDiscount() == null ? 0 : this.getIsDiscount().hashCode() );
         result = 37 * result + ( getIsSubStore() == null ? 0 : this.getIsSubStore().hashCode() );
         result = 37 * result + ( getActiveStatus() == null ? 0 : this.getActiveStatus().hashCode() );
         return result;
   }   





}
