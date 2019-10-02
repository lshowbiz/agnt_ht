package com.joymain.jecs.bd.model;
// Generated 2009-10-5 16:57:05 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JBD_DAY_BOUNS_CALC_HIST"
 *     
 */

public class JbdDayBounsCalcHist extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private Integer wyear;
    private Integer wmonth;
    private Integer wweek;
    private String userCode;
    private String companyCode;
    private String recommendNo;
    private String linkNo;
    private String name;
    private String petName;
    private String cardType;
    private String isstore;
    private String bank;
    private String bankaddress;
    private String bankbook;
    private String bankcard;
    private Date exitDate;
    private BigDecimal firstMonth;
    private BigDecimal linkNum;
    private BigDecimal pendingLinkNum;
    private BigDecimal recommendNum;
    private BigDecimal pendingRecommendNum;
    private BigDecimal monthConsumerPv;
    private BigDecimal pendingPv;
    private BigDecimal monthAreaTotalPv;
    private BigDecimal weekGroupPv;
    private BigDecimal monthGroupPv;
    private BigDecimal pendingGroupPv;
    private BigDecimal monthRecommendPv;
    private BigDecimal pendingRecommendPv;
    private Integer passStar;
    private Integer passGrade;
    private BigDecimal consumerAmount;
    private BigDecimal pendingConsumerAmount;
    private BigDecimal franchisePv;
    private BigDecimal pendingFranchisePv;


    // Constructors

    /** default constructor */
    public JbdDayBounsCalcHist() {
    }

	/** minimal constructor */
    public JbdDayBounsCalcHist(String userCode) {
        this.userCode = userCode;
    }
    
    /** full constructor */
    public JbdDayBounsCalcHist(Integer wyear, Integer wmonth, Integer wweek, String userCode, String companyCode, String recommendNo, String linkNo, String name, String petName, String cardType, String isstore, String bank, String bankaddress, String bankbook, String bankcard, Date exitDate, BigDecimal firstMonth, BigDecimal linkNum, BigDecimal pendingLinkNum, BigDecimal recommendNum, BigDecimal pendingRecommendNum, BigDecimal monthConsumerPv, BigDecimal pendingPv, BigDecimal monthAreaTotalPv, BigDecimal weekGroupPv, BigDecimal monthGroupPv, BigDecimal pendingGroupPv, BigDecimal monthRecommendPv, BigDecimal pendingRecommendPv, Integer passStar, Integer passGrade, BigDecimal consumerAmount, BigDecimal pendingConsumerAmount, BigDecimal franchisePv, BigDecimal pendingFranchisePv) {
        this.wyear = wyear;
        this.wmonth = wmonth;
        this.wweek = wweek;
        this.userCode = userCode;
        this.companyCode = companyCode;
        this.recommendNo = recommendNo;
        this.linkNo = linkNo;
        this.name = name;
        this.petName = petName;
        this.cardType = cardType;
        this.isstore = isstore;
        this.bank = bank;
        this.bankaddress = bankaddress;
        this.bankbook = bankbook;
        this.bankcard = bankcard;
        this.exitDate = exitDate;
        this.firstMonth = firstMonth;
        this.linkNum = linkNum;
        this.pendingLinkNum = pendingLinkNum;
        this.recommendNum = recommendNum;
        this.pendingRecommendNum = pendingRecommendNum;
        this.monthConsumerPv = monthConsumerPv;
        this.pendingPv = pendingPv;
        this.monthAreaTotalPv = monthAreaTotalPv;
        this.weekGroupPv = weekGroupPv;
        this.monthGroupPv = monthGroupPv;
        this.pendingGroupPv = pendingGroupPv;
        this.monthRecommendPv = monthRecommendPv;
        this.pendingRecommendPv = pendingRecommendPv;
        this.passStar = passStar;
        this.passGrade = passGrade;
        this.consumerAmount = consumerAmount;
        this.pendingConsumerAmount = pendingConsumerAmount;
        this.franchisePv = franchisePv;
        this.pendingFranchisePv = pendingFranchisePv;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.Long"
     *             column="ID"
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
     *             column="W_YEAR"
     *             length="8"
     *         
     */

    public Integer getWyear() {
        return this.wyear;
    }
    
    public void setWyear(Integer wyear) {
        this.wyear = wyear;
    }
    /**       
     *      *            @hibernate.property
     *             column="W_MONTH"
     *             length="8"
     *         
     */

    public Integer getWmonth() {
        return this.wmonth;
    }
    
    public void setWmonth(Integer wmonth) {
        this.wmonth = wmonth;
    }
    /**       
     *      *            @hibernate.property
     *             column="W_WEEK"
     *             length="8"
     *         
     */

    public Integer getWweek() {
        return this.wweek;
    }
    
    public void setWweek(Integer wweek) {
        this.wweek = wweek;
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
     *             column="RECOMMEND_NO"
     *             length="20"
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
     *             column="LINK_NO"
     *             length="20"
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
     *             column="NAME"
     *             length="200"
     *         
     */

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    /**       
     *      *            @hibernate.property
     *             column="PET_NAME"
     *             length="200"
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
     *             column="BANK"
     *             length="200"
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
     *             column="BANKBOOK"
     *             length="200"
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
     *             column="BANKCARD"
     *             length="200"
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
     *             column="FIRST_MONTH"
     *             length="22"
     *         
     */

    public BigDecimal getFirstMonth() {
        return this.firstMonth;
    }
    
    public void setFirstMonth(BigDecimal firstMonth) {
        this.firstMonth = firstMonth;
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
     *             column="MONTH_CONSUMER_PV"
     *             length="22"
     *         
     */

    public BigDecimal getMonthConsumerPv() {
        return this.monthConsumerPv;
    }
    
    public void setMonthConsumerPv(BigDecimal monthConsumerPv) {
        this.monthConsumerPv = monthConsumerPv;
    }
    /**       
     *      *            @hibernate.property
     *             column="PENDING_PV"
     *             length="22"
     *         
     */

    public BigDecimal getPendingPv() {
        return this.pendingPv;
    }
    
    public void setPendingPv(BigDecimal pendingPv) {
        this.pendingPv = pendingPv;
    }
    /**       
     *      *            @hibernate.property
     *             column="MONTH_AREA_TOTAL_PV"
     *             length="22"
     *         
     */

    public BigDecimal getMonthAreaTotalPv() {
        return this.monthAreaTotalPv;
    }
    
    public void setMonthAreaTotalPv(BigDecimal monthAreaTotalPv) {
        this.monthAreaTotalPv = monthAreaTotalPv;
    }
    /**       
     *      *            @hibernate.property
     *             column="WEEK_GROUP_PV"
     *             length="22"
     *         
     */

    public BigDecimal getWeekGroupPv() {
        return this.weekGroupPv;
    }
    
    public void setWeekGroupPv(BigDecimal weekGroupPv) {
        this.weekGroupPv = weekGroupPv;
    }
    /**       
     *      *            @hibernate.property
     *             column="MONTH_GROUP_PV"
     *             length="22"
     *         
     */

    public BigDecimal getMonthGroupPv() {
        return this.monthGroupPv;
    }
    
    public void setMonthGroupPv(BigDecimal monthGroupPv) {
        this.monthGroupPv = monthGroupPv;
    }
    /**       
     *      *            @hibernate.property
     *             column="PENDING_GROUP_PV"
     *             length="22"
     *         
     */

    public BigDecimal getPendingGroupPv() {
        return this.pendingGroupPv;
    }
    
    public void setPendingGroupPv(BigDecimal pendingGroupPv) {
        this.pendingGroupPv = pendingGroupPv;
    }
    /**       
     *      *            @hibernate.property
     *             column="MONTH_RECOMMEND_PV"
     *             length="22"
     *         
     */

    public BigDecimal getMonthRecommendPv() {
        return this.monthRecommendPv;
    }
    
    public void setMonthRecommendPv(BigDecimal monthRecommendPv) {
        this.monthRecommendPv = monthRecommendPv;
    }
    /**       
     *      *            @hibernate.property
     *             column="PENDING_RECOMMEND_PV"
     *             length="22"
     *         
     */

    public BigDecimal getPendingRecommendPv() {
        return this.pendingRecommendPv;
    }
    
    public void setPendingRecommendPv(BigDecimal pendingRecommendPv) {
        this.pendingRecommendPv = pendingRecommendPv;
    }
    /**       
     *      *            @hibernate.property
     *             column="PASS_STAR"
     *             length="2"
     *         
     */

    public Integer getPassStar() {
        return this.passStar;
    }
    
    public void setPassStar(Integer passStar) {
        this.passStar = passStar;
    }
    /**       
     *      *            @hibernate.property
     *             column="PASS_GRADE"
     *             length="2"
     *         
     */

    public Integer getPassGrade() {
        return this.passGrade;
    }
    
    public void setPassGrade(Integer passGrade) {
        this.passGrade = passGrade;
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
     *             column="PENDING_CONSUMER_AMOUNT"
     *             length="22"
     *         
     */

    public BigDecimal getPendingConsumerAmount() {
        return this.pendingConsumerAmount;
    }
    
    public void setPendingConsumerAmount(BigDecimal pendingConsumerAmount) {
        this.pendingConsumerAmount = pendingConsumerAmount;
    }
    /**       
     *      *            @hibernate.property
     *             column="FRANCHISE_PV"
     *             length="22"
     *         
     */

    public BigDecimal getFranchisePv() {
        return this.franchisePv;
    }
    
    public void setFranchisePv(BigDecimal franchisePv) {
        this.franchisePv = franchisePv;
    }
    /**       
     *      *            @hibernate.property
     *             column="PENDING_FRANCHISE_PV"
     *             length="22"
     *         
     */

    public BigDecimal getPendingFranchisePv() {
        return this.pendingFranchisePv;
    }
    
    public void setPendingFranchisePv(BigDecimal pendingFranchisePv) {
        this.pendingFranchisePv = pendingFranchisePv;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("wyear").append("='").append(getWyear()).append("' ");			
      buffer.append("wmonth").append("='").append(getWmonth()).append("' ");			
      buffer.append("wweek").append("='").append(getWweek()).append("' ");			
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("recommendNo").append("='").append(getRecommendNo()).append("' ");			
      buffer.append("linkNo").append("='").append(getLinkNo()).append("' ");			
      buffer.append("name").append("='").append(getName()).append("' ");			
      buffer.append("petName").append("='").append(getPetName()).append("' ");			
      buffer.append("cardType").append("='").append(getCardType()).append("' ");			
      buffer.append("isstore").append("='").append(getIsstore()).append("' ");			
      buffer.append("bank").append("='").append(getBank()).append("' ");			
      buffer.append("bankaddress").append("='").append(getBankaddress()).append("' ");			
      buffer.append("bankbook").append("='").append(getBankbook()).append("' ");			
      buffer.append("bankcard").append("='").append(getBankcard()).append("' ");			
      buffer.append("exitDate").append("='").append(getExitDate()).append("' ");			
      buffer.append("firstMonth").append("='").append(getFirstMonth()).append("' ");			
      buffer.append("linkNum").append("='").append(getLinkNum()).append("' ");			
      buffer.append("pendingLinkNum").append("='").append(getPendingLinkNum()).append("' ");			
      buffer.append("recommendNum").append("='").append(getRecommendNum()).append("' ");			
      buffer.append("pendingRecommendNum").append("='").append(getPendingRecommendNum()).append("' ");			
      buffer.append("monthConsumerPv").append("='").append(getMonthConsumerPv()).append("' ");			
      buffer.append("pendingPv").append("='").append(getPendingPv()).append("' ");			
      buffer.append("monthAreaTotalPv").append("='").append(getMonthAreaTotalPv()).append("' ");			
      buffer.append("weekGroupPv").append("='").append(getWeekGroupPv()).append("' ");			
      buffer.append("monthGroupPv").append("='").append(getMonthGroupPv()).append("' ");			
      buffer.append("pendingGroupPv").append("='").append(getPendingGroupPv()).append("' ");			
      buffer.append("monthRecommendPv").append("='").append(getMonthRecommendPv()).append("' ");			
      buffer.append("pendingRecommendPv").append("='").append(getPendingRecommendPv()).append("' ");			
      buffer.append("passStar").append("='").append(getPassStar()).append("' ");			
      buffer.append("passGrade").append("='").append(getPassGrade()).append("' ");			
      buffer.append("consumerAmount").append("='").append(getConsumerAmount()).append("' ");			
      buffer.append("pendingConsumerAmount").append("='").append(getPendingConsumerAmount()).append("' ");			
      buffer.append("franchisePv").append("='").append(getFranchisePv()).append("' ");			
      buffer.append("pendingFranchisePv").append("='").append(getPendingFranchisePv()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JbdDayBounsCalcHist) ) return false;
		 JbdDayBounsCalcHist castOther = ( JbdDayBounsCalcHist ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getWyear()==castOther.getWyear()) || ( this.getWyear()!=null && castOther.getWyear()!=null && this.getWyear().equals(castOther.getWyear()) ) )
 && ( (this.getWmonth()==castOther.getWmonth()) || ( this.getWmonth()!=null && castOther.getWmonth()!=null && this.getWmonth().equals(castOther.getWmonth()) ) )
 && ( (this.getWweek()==castOther.getWweek()) || ( this.getWweek()!=null && castOther.getWweek()!=null && this.getWweek().equals(castOther.getWweek()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getRecommendNo()==castOther.getRecommendNo()) || ( this.getRecommendNo()!=null && castOther.getRecommendNo()!=null && this.getRecommendNo().equals(castOther.getRecommendNo()) ) )
 && ( (this.getLinkNo()==castOther.getLinkNo()) || ( this.getLinkNo()!=null && castOther.getLinkNo()!=null && this.getLinkNo().equals(castOther.getLinkNo()) ) )
 && ( (this.getName()==castOther.getName()) || ( this.getName()!=null && castOther.getName()!=null && this.getName().equals(castOther.getName()) ) )
 && ( (this.getPetName()==castOther.getPetName()) || ( this.getPetName()!=null && castOther.getPetName()!=null && this.getPetName().equals(castOther.getPetName()) ) )
 && ( (this.getCardType()==castOther.getCardType()) || ( this.getCardType()!=null && castOther.getCardType()!=null && this.getCardType().equals(castOther.getCardType()) ) )
 && ( (this.getIsstore()==castOther.getIsstore()) || ( this.getIsstore()!=null && castOther.getIsstore()!=null && this.getIsstore().equals(castOther.getIsstore()) ) )
 && ( (this.getBank()==castOther.getBank()) || ( this.getBank()!=null && castOther.getBank()!=null && this.getBank().equals(castOther.getBank()) ) )
 && ( (this.getBankaddress()==castOther.getBankaddress()) || ( this.getBankaddress()!=null && castOther.getBankaddress()!=null && this.getBankaddress().equals(castOther.getBankaddress()) ) )
 && ( (this.getBankbook()==castOther.getBankbook()) || ( this.getBankbook()!=null && castOther.getBankbook()!=null && this.getBankbook().equals(castOther.getBankbook()) ) )
 && ( (this.getBankcard()==castOther.getBankcard()) || ( this.getBankcard()!=null && castOther.getBankcard()!=null && this.getBankcard().equals(castOther.getBankcard()) ) )
 && ( (this.getExitDate()==castOther.getExitDate()) || ( this.getExitDate()!=null && castOther.getExitDate()!=null && this.getExitDate().equals(castOther.getExitDate()) ) )
 && ( (this.getFirstMonth()==castOther.getFirstMonth()) || ( this.getFirstMonth()!=null && castOther.getFirstMonth()!=null && this.getFirstMonth().equals(castOther.getFirstMonth()) ) )
 && ( (this.getLinkNum()==castOther.getLinkNum()) || ( this.getLinkNum()!=null && castOther.getLinkNum()!=null && this.getLinkNum().equals(castOther.getLinkNum()) ) )
 && ( (this.getPendingLinkNum()==castOther.getPendingLinkNum()) || ( this.getPendingLinkNum()!=null && castOther.getPendingLinkNum()!=null && this.getPendingLinkNum().equals(castOther.getPendingLinkNum()) ) )
 && ( (this.getRecommendNum()==castOther.getRecommendNum()) || ( this.getRecommendNum()!=null && castOther.getRecommendNum()!=null && this.getRecommendNum().equals(castOther.getRecommendNum()) ) )
 && ( (this.getPendingRecommendNum()==castOther.getPendingRecommendNum()) || ( this.getPendingRecommendNum()!=null && castOther.getPendingRecommendNum()!=null && this.getPendingRecommendNum().equals(castOther.getPendingRecommendNum()) ) )
 && ( (this.getMonthConsumerPv()==castOther.getMonthConsumerPv()) || ( this.getMonthConsumerPv()!=null && castOther.getMonthConsumerPv()!=null && this.getMonthConsumerPv().equals(castOther.getMonthConsumerPv()) ) )
 && ( (this.getPendingPv()==castOther.getPendingPv()) || ( this.getPendingPv()!=null && castOther.getPendingPv()!=null && this.getPendingPv().equals(castOther.getPendingPv()) ) )
 && ( (this.getMonthAreaTotalPv()==castOther.getMonthAreaTotalPv()) || ( this.getMonthAreaTotalPv()!=null && castOther.getMonthAreaTotalPv()!=null && this.getMonthAreaTotalPv().equals(castOther.getMonthAreaTotalPv()) ) )
 && ( (this.getWeekGroupPv()==castOther.getWeekGroupPv()) || ( this.getWeekGroupPv()!=null && castOther.getWeekGroupPv()!=null && this.getWeekGroupPv().equals(castOther.getWeekGroupPv()) ) )
 && ( (this.getMonthGroupPv()==castOther.getMonthGroupPv()) || ( this.getMonthGroupPv()!=null && castOther.getMonthGroupPv()!=null && this.getMonthGroupPv().equals(castOther.getMonthGroupPv()) ) )
 && ( (this.getPendingGroupPv()==castOther.getPendingGroupPv()) || ( this.getPendingGroupPv()!=null && castOther.getPendingGroupPv()!=null && this.getPendingGroupPv().equals(castOther.getPendingGroupPv()) ) )
 && ( (this.getMonthRecommendPv()==castOther.getMonthRecommendPv()) || ( this.getMonthRecommendPv()!=null && castOther.getMonthRecommendPv()!=null && this.getMonthRecommendPv().equals(castOther.getMonthRecommendPv()) ) )
 && ( (this.getPendingRecommendPv()==castOther.getPendingRecommendPv()) || ( this.getPendingRecommendPv()!=null && castOther.getPendingRecommendPv()!=null && this.getPendingRecommendPv().equals(castOther.getPendingRecommendPv()) ) )
 && ( (this.getPassStar()==castOther.getPassStar()) || ( this.getPassStar()!=null && castOther.getPassStar()!=null && this.getPassStar().equals(castOther.getPassStar()) ) )
 && ( (this.getPassGrade()==castOther.getPassGrade()) || ( this.getPassGrade()!=null && castOther.getPassGrade()!=null && this.getPassGrade().equals(castOther.getPassGrade()) ) )
 && ( (this.getConsumerAmount()==castOther.getConsumerAmount()) || ( this.getConsumerAmount()!=null && castOther.getConsumerAmount()!=null && this.getConsumerAmount().equals(castOther.getConsumerAmount()) ) )
 && ( (this.getPendingConsumerAmount()==castOther.getPendingConsumerAmount()) || ( this.getPendingConsumerAmount()!=null && castOther.getPendingConsumerAmount()!=null && this.getPendingConsumerAmount().equals(castOther.getPendingConsumerAmount()) ) )
 && ( (this.getFranchisePv()==castOther.getFranchisePv()) || ( this.getFranchisePv()!=null && castOther.getFranchisePv()!=null && this.getFranchisePv().equals(castOther.getFranchisePv()) ) )
 && ( (this.getPendingFranchisePv()==castOther.getPendingFranchisePv()) || ( this.getPendingFranchisePv()!=null && castOther.getPendingFranchisePv()!=null && this.getPendingFranchisePv().equals(castOther.getPendingFranchisePv()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getWyear() == null ? 0 : this.getWyear().hashCode() );
         result = 37 * result + ( getWmonth() == null ? 0 : this.getWmonth().hashCode() );
         result = 37 * result + ( getWweek() == null ? 0 : this.getWweek().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getRecommendNo() == null ? 0 : this.getRecommendNo().hashCode() );
         result = 37 * result + ( getLinkNo() == null ? 0 : this.getLinkNo().hashCode() );
         result = 37 * result + ( getName() == null ? 0 : this.getName().hashCode() );
         result = 37 * result + ( getPetName() == null ? 0 : this.getPetName().hashCode() );
         result = 37 * result + ( getCardType() == null ? 0 : this.getCardType().hashCode() );
         result = 37 * result + ( getIsstore() == null ? 0 : this.getIsstore().hashCode() );
         result = 37 * result + ( getBank() == null ? 0 : this.getBank().hashCode() );
         result = 37 * result + ( getBankaddress() == null ? 0 : this.getBankaddress().hashCode() );
         result = 37 * result + ( getBankbook() == null ? 0 : this.getBankbook().hashCode() );
         result = 37 * result + ( getBankcard() == null ? 0 : this.getBankcard().hashCode() );
         result = 37 * result + ( getExitDate() == null ? 0 : this.getExitDate().hashCode() );
         result = 37 * result + ( getFirstMonth() == null ? 0 : this.getFirstMonth().hashCode() );
         result = 37 * result + ( getLinkNum() == null ? 0 : this.getLinkNum().hashCode() );
         result = 37 * result + ( getPendingLinkNum() == null ? 0 : this.getPendingLinkNum().hashCode() );
         result = 37 * result + ( getRecommendNum() == null ? 0 : this.getRecommendNum().hashCode() );
         result = 37 * result + ( getPendingRecommendNum() == null ? 0 : this.getPendingRecommendNum().hashCode() );
         result = 37 * result + ( getMonthConsumerPv() == null ? 0 : this.getMonthConsumerPv().hashCode() );
         result = 37 * result + ( getPendingPv() == null ? 0 : this.getPendingPv().hashCode() );
         result = 37 * result + ( getMonthAreaTotalPv() == null ? 0 : this.getMonthAreaTotalPv().hashCode() );
         result = 37 * result + ( getWeekGroupPv() == null ? 0 : this.getWeekGroupPv().hashCode() );
         result = 37 * result + ( getMonthGroupPv() == null ? 0 : this.getMonthGroupPv().hashCode() );
         result = 37 * result + ( getPendingGroupPv() == null ? 0 : this.getPendingGroupPv().hashCode() );
         result = 37 * result + ( getMonthRecommendPv() == null ? 0 : this.getMonthRecommendPv().hashCode() );
         result = 37 * result + ( getPendingRecommendPv() == null ? 0 : this.getPendingRecommendPv().hashCode() );
         result = 37 * result + ( getPassStar() == null ? 0 : this.getPassStar().hashCode() );
         result = 37 * result + ( getPassGrade() == null ? 0 : this.getPassGrade().hashCode() );
         result = 37 * result + ( getConsumerAmount() == null ? 0 : this.getConsumerAmount().hashCode() );
         result = 37 * result + ( getPendingConsumerAmount() == null ? 0 : this.getPendingConsumerAmount().hashCode() );
         result = 37 * result + ( getFranchisePv() == null ? 0 : this.getFranchisePv().hashCode() );
         result = 37 * result + ( getPendingFranchisePv() == null ? 0 : this.getPendingFranchisePv().hashCode() );
         return result;
   }   





}
