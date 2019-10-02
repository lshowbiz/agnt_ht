package com.joymain.jecs.bd.model;
// Generated 2009-9-26 10:23:41 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="V_JBD_MEMBER_LINK_CALC"
 *     
 */

public class VJbdMemberLinkCalc extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private Integer wyear;
    private Integer wmonth;
    private Integer wweek;
    private String companyCode;
    private String userCode;
    private String recommendNo;
    private String linkNo;
    private String name;
    private String petName;
    private String isstore;
    private String cardType;
    private String bank;
    private String bankaddress;
    private String bankbook;
    private String bankcard;
    private Date exitDate;
    private Integer passStatus;
    private Integer honorStar;
    private Integer passStar;
    private Integer honorGrade;
    private Integer passGrade;
    private Integer honorPosition;
    private BigDecimal monthConsumerPv;
    private BigDecimal monthAreaTotalPv;
    private BigDecimal weekGroupPv;
    private BigDecimal monthGroupPv;
    private BigDecimal successSalesRate;
    private BigDecimal monthRecommendGroupPv;
    private BigDecimal franchisePv;
    private BigDecimal franchiseMoney;
    private BigDecimal consumerAmount;
    private BigDecimal ventureSalesPv;
    private BigDecimal ventureLeaderPv;
    private BigDecimal successSalesPv;
    private BigDecimal successLeaderPv;
    private BigDecimal deductMoney;
    private BigDecimal deductTax;
    private BigDecimal exchangeRate;
    private BigDecimal keepPv;
    private String keepUserCode;
    private BigDecimal lastKeepPv;
    private String lastKeepUserCode;
    private BigDecimal departmenKeepPv;
    private BigDecimal passStarGroupPv;
    private BigDecimal passGradeGroupPv;
    private BigDecimal firstMonth;
    private BigDecimal bounsPv;
    private BigDecimal bounsMoney;
    private BigDecimal sendMoney;
    private String active;
    private Integer status;
    private BigDecimal recommendBonusPv;
    private BigDecimal storeExpandPv;
    private BigDecimal storeServePv;
    private BigDecimal storeRecommendPv;
    private BigDecimal ventureFund;
    private Integer freezeStatus;
    private BigDecimal areaConsumption;

    private BigDecimal networkMoney;

    private BigDecimal totalDev;
    private BigDecimal deductedDev;
    private BigDecimal currentDev;
    private BigDecimal monthMaxPv;
    
    private Integer qualifyStar;//资格奖衔
    private String qualifyRemark;//资格奖衔备注

    private Integer memberLevel;
    private Integer retainLevel;
    private BigDecimal retainGroup;
    private BigDecimal monthOwnPv;
    private BigDecimal totalGroup;
    
    
    private BigDecimal firstPv;
    
    private Integer cioType;
    private Integer zyType;
    
    private Integer gradeType;
    

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
     *             column="ZY_TYPE"
     *         
     */
	public Integer getZyType() {
		return zyType;
	}

	public void setZyType(Integer zyType) {
		this.zyType = zyType;
	}

	/**       
     *      *            @hibernate.property
     *             column="CIO_TYPE"
     *         
     */
	public Integer getCioType() {
		return cioType;
	}

	public void setCioType(Integer cioType) {
		this.cioType = cioType;
	}

	/**       
     *      *            @hibernate.property
     *             column="FIRST_PV"
     *         
     */
	public BigDecimal getFirstPv() {
		return firstPv;
	}

	public void setFirstPv(BigDecimal firstPv) {
		this.firstPv = firstPv;
	}

    

	/**       
     *      *            @hibernate.property
     *             column="TOTAL_GROUP"
     *         
     */
	public BigDecimal getTotalGroup() {
		return totalGroup;
	}


	public void setTotalGroup(BigDecimal totalGroup) {
		this.totalGroup = totalGroup;
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

	/**       
     *      *            @hibernate.property
     *             column="RETAIN_LEVEL"
     *         
     */

	public Integer getRetainLevel() {
		return retainLevel;
	}


	public void setRetainLevel(Integer retainLevel) {
		this.retainLevel = retainLevel;
	}


	/**       
     *      *            @hibernate.property
     *             column="RETAIN_GROUP"
     *         
     */
	public BigDecimal getRetainGroup() {
		return retainGroup;
	}


	public void setRetainGroup(BigDecimal retainGroup) {
		this.retainGroup = retainGroup;
	}


	/**       
     *      *            @hibernate.property
     *             column="MONTH_OWN_PV"
     *         
     */
	public BigDecimal getMonthOwnPv() {
		return monthOwnPv;
	}


	public void setMonthOwnPv(BigDecimal monthOwnPv) {
		this.monthOwnPv = monthOwnPv;
	}


	/**       
     *      *            @hibernate.property
     *             column="MONTH_MAX_PV"
     *         
     */
    public BigDecimal getMonthMaxPv() {
		return monthMaxPv;
	}


	public void setMonthMaxPv(BigDecimal monthMaxPv) {
		this.monthMaxPv = monthMaxPv;
	}


	// Constructors
	/**       
     *      *            @hibernate.property
     *             column="AREA_CONSUMPTION"
     *         
     */
    public BigDecimal getAreaConsumption() {
		return areaConsumption;
	}


	public void setAreaConsumption(BigDecimal areaConsumption) {
		this.areaConsumption = areaConsumption;
	}


	/**       
     *      *            @hibernate.property
     *             column="FREEZE_STATUS"
     *             length="1"
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
     *             column="VENTURE_FUND"
     *         
     */
    public BigDecimal getVentureFund() {
		return ventureFund;
	}


	public void setVentureFund(BigDecimal ventureFund) {
		this.ventureFund = ventureFund;
	}


	/** default constructor */
    public VJbdMemberLinkCalc() {
    }

    
    /** full constructor */
    public VJbdMemberLinkCalc(Integer wyear, Integer wmonth, Integer wweek, String companyCode, String userCode, String recommendNo, String linkNo, String name, String petName, String isstore, String cardType, String bank, String bankaddress, String bankbook, String bankcard, Date exitDate, Integer passStatus, Integer honorStar, Integer passStar, Integer honorGrade, Integer passGrade, Integer honorPosition, BigDecimal monthConsumerPv, BigDecimal monthAreaTotalPv, BigDecimal weekGroupPv, BigDecimal monthGroupPv, BigDecimal successSalesRate, BigDecimal monthRecommendGroupPv, BigDecimal franchisePv, BigDecimal franchiseMoney, BigDecimal consumerAmount, BigDecimal ventureSalesPv, BigDecimal ventureLeaderPv, BigDecimal successSalesPv, BigDecimal successLeaderPv, BigDecimal deductMoney, BigDecimal deductTax, BigDecimal exchangeRate, BigDecimal keepPv, String keepUserCode, BigDecimal lastKeepPv, String lastKeepUserCode, BigDecimal departmenKeepPv, BigDecimal passStarGroupPv, BigDecimal passGradeGroupPv, BigDecimal firstMonth, BigDecimal bounsPv, BigDecimal bounsMoney, BigDecimal sendMoney) {
        this.wyear = wyear;
        this.wmonth = wmonth;
        this.wweek = wweek;
        this.companyCode = companyCode;
        this.userCode = userCode;
        this.recommendNo = recommendNo;
        this.linkNo = linkNo;
        this.name = name;
        this.petName = petName;
        this.isstore = isstore;
        this.cardType = cardType;
        this.bank = bank;
        this.bankaddress = bankaddress;
        this.bankbook = bankbook;
        this.bankcard = bankcard;
        this.exitDate = exitDate;
        this.passStatus = passStatus;
        this.honorStar = honorStar;
        this.passStar = passStar;
        this.honorGrade = honorGrade;
        this.passGrade = passGrade;
        this.honorPosition = honorPosition;
        this.monthConsumerPv = monthConsumerPv;
        this.monthAreaTotalPv = monthAreaTotalPv;
        this.weekGroupPv = weekGroupPv;
        this.monthGroupPv = monthGroupPv;
        this.successSalesRate = successSalesRate;
        this.monthRecommendGroupPv = monthRecommendGroupPv;
        this.franchisePv = franchisePv;
        this.franchiseMoney = franchiseMoney;
        this.consumerAmount = consumerAmount;
        this.ventureSalesPv = ventureSalesPv;
        this.ventureLeaderPv = ventureLeaderPv;
        this.successSalesPv = successSalesPv;
        this.successLeaderPv = successLeaderPv;
        this.deductMoney = deductMoney;
        this.deductTax = deductTax;
        this.exchangeRate = exchangeRate;
        this.keepPv = keepPv;
        this.keepUserCode = keepUserCode;
        this.lastKeepPv = lastKeepPv;
        this.lastKeepUserCode = lastKeepUserCode;
        this.departmenKeepPv = departmenKeepPv;
        this.passStarGroupPv = passStarGroupPv;
        this.passGradeGroupPv = passGradeGroupPv;
        this.firstMonth = firstMonth;
        this.bounsPv = bounsPv;
        this.bounsMoney = bounsMoney;
        this.sendMoney = sendMoney;
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
     *             column="USER_CODE"
     *             length="20"
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
     *             column="PASS_STATUS"
     *             length="2"
     *         
     */

    public Integer getPassStatus() {
        return this.passStatus;
    }
    
    public void setPassStatus(Integer passStatus) {
        this.passStatus = passStatus;
    }
    /**       
     *      *            @hibernate.property
     *             column="HONOR_STAR"
     *             length="2"
     *         
     */

    public Integer getHonorStar() {
        return this.honorStar;
    }
    
    public void setHonorStar(Integer honorStar) {
        this.honorStar = honorStar;
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
     *             column="HONOR_GRADE"
     *             length="2"
     *         
     */

    public Integer getHonorGrade() {
        return this.honorGrade;
    }
    
    public void setHonorGrade(Integer honorGrade) {
        this.honorGrade = honorGrade;
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
     *             column="HONOR_POSITION"
     *             length="2"
     *         
     */

    public Integer getHonorPosition() {
        return this.honorPosition;
    }
    
    public void setHonorPosition(Integer honorPosition) {
        this.honorPosition = honorPosition;
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
     *             column="SUCCESS_SALES_RATE"
     *             length="22"
     *         
     */

    public BigDecimal getSuccessSalesRate() {
        return this.successSalesRate;
    }
    
    public void setSuccessSalesRate(BigDecimal successSalesRate) {
        this.successSalesRate = successSalesRate;
    }
    /**       
     *      *            @hibernate.property
     *             column="MONTH_RECOMMEND_GROUP_PV"
     *             length="22"
     *         
     */

    public BigDecimal getMonthRecommendGroupPv() {
        return this.monthRecommendGroupPv;
    }
    
    public void setMonthRecommendGroupPv(BigDecimal monthRecommendGroupPv) {
        this.monthRecommendGroupPv = monthRecommendGroupPv;
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
     *             column="FRANCHISE_MONEY"
     *             length="22"
     *         
     */

    public BigDecimal getFranchiseMoney() {
        return this.franchiseMoney;
    }
    
    public void setFranchiseMoney(BigDecimal franchiseMoney) {
        this.franchiseMoney = franchiseMoney;
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
     *             column="VENTURE_SALES_PV"
     *             length="22"
     *         
     */

    public BigDecimal getVentureSalesPv() {
        return this.ventureSalesPv;
    }
    
    public void setVentureSalesPv(BigDecimal ventureSalesPv) {
        this.ventureSalesPv = ventureSalesPv;
    }
    /**       
     *      *            @hibernate.property
     *             column="VENTURE_LEADER_PV"
     *             length="22"
     *         
     */

    public BigDecimal getVentureLeaderPv() {
        return this.ventureLeaderPv;
    }
    
    public void setVentureLeaderPv(BigDecimal ventureLeaderPv) {
        this.ventureLeaderPv = ventureLeaderPv;
    }
    /**       
     *      *            @hibernate.property
     *             column="SUCCESS_SALES_PV"
     *             length="22"
     *         
     */

    public BigDecimal getSuccessSalesPv() {
        return this.successSalesPv;
    }
    
    public void setSuccessSalesPv(BigDecimal successSalesPv) {
        this.successSalesPv = successSalesPv;
    }
    /**       
     *      *            @hibernate.property
     *             column="SUCCESS_LEADER_PV"
     *             length="22"
     *         
     */

    public BigDecimal getSuccessLeaderPv() {
        return this.successLeaderPv;
    }
    
    public void setSuccessLeaderPv(BigDecimal successLeaderPv) {
        this.successLeaderPv = successLeaderPv;
    }
    /**       
     *      *            @hibernate.property
     *             column="DEDUCT_MONEY"
     *             length="22"
     *         
     */

    public BigDecimal getDeductMoney() {
        return this.deductMoney;
    }
    
    public void setDeductMoney(BigDecimal deductMoney) {
        this.deductMoney = deductMoney;
    }
    /**       
     *      *            @hibernate.property
     *             column="DEDUCT_TAX"
     *             length="22"
     *         
     */

    public BigDecimal getDeductTax() {
        return this.deductTax;
    }
    
    public void setDeductTax(BigDecimal deductTax) {
        this.deductTax = deductTax;
    }
    /**       
     *      *            @hibernate.property
     *             column="EXCHANGE_RATE"
     *             length="22"
     *         
     */

    public BigDecimal getExchangeRate() {
        return this.exchangeRate;
    }
    
    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
    /**       
     *      *            @hibernate.property
     *             column="KEEP_PV"
     *             length="22"
     *         
     */

    public BigDecimal getKeepPv() {
        return this.keepPv;
    }
    
    public void setKeepPv(BigDecimal keepPv) {
        this.keepPv = keepPv;
    }
    /**       
     *      *            @hibernate.property
     *             column="KEEP_USER_CODE"
     *             length="20"
     *         
     */

    public String getKeepUserCode() {
        return this.keepUserCode;
    }
    
    public void setKeepUserCode(String keepUserCode) {
        this.keepUserCode = keepUserCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="LAST_KEEP_PV"
     *             length="22"
     *         
     */

    public BigDecimal getLastKeepPv() {
        return this.lastKeepPv;
    }
    
    public void setLastKeepPv(BigDecimal lastKeepPv) {
        this.lastKeepPv = lastKeepPv;
    }
    /**       
     *      *            @hibernate.property
     *             column="LAST_KEEP_USER_CODE"
     *             length="20"
     *         
     */

    public String getLastKeepUserCode() {
        return this.lastKeepUserCode;
    }
    
    public void setLastKeepUserCode(String lastKeepUserCode) {
        this.lastKeepUserCode = lastKeepUserCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="DEPARTMEN_KEEP_PV"
     *             length="22"
     *         
     */

    public BigDecimal getDepartmenKeepPv() {
        return this.departmenKeepPv;
    }
    
    public void setDepartmenKeepPv(BigDecimal departmenKeepPv) {
        this.departmenKeepPv = departmenKeepPv;
    }
    /**       
     *      *            @hibernate.property
     *             column="PASS_STAR_GROUP_PV"
     *             length="22"
     *         
     */

    public BigDecimal getPassStarGroupPv() {
        return this.passStarGroupPv;
    }
    
    public void setPassStarGroupPv(BigDecimal passStarGroupPv) {
        this.passStarGroupPv = passStarGroupPv;
    }
    /**       
     *      *            @hibernate.property
     *             column="PASS_GRADE_GROUP_PV"
     *             length="22"
     *         
     */

    public BigDecimal getPassGradeGroupPv() {
        return this.passGradeGroupPv;
    }
    
    public void setPassGradeGroupPv(BigDecimal passGradeGroupPv) {
        this.passGradeGroupPv = passGradeGroupPv;
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
     *             column="BOUNS_PV"
     *             length="22"
     *         
     */

    public BigDecimal getBounsPv() {
        return this.bounsPv;
    }
    
    public void setBounsPv(BigDecimal bounsPv) {
        this.bounsPv = bounsPv;
    }
    /**       
     *      *            @hibernate.property
     *             column="BOUNS_MONEY"
     *             length="22"
     *         
     */

    public BigDecimal getBounsMoney() {
        return this.bounsMoney;
    }
    
    public void setBounsMoney(BigDecimal bounsMoney) {
        this.bounsMoney = bounsMoney;
    }
    /**       
     *      *            @hibernate.property
     *             column="SEND_MONEY"
     *             length="22"
     *         
     */

    public BigDecimal getSendMoney() {
        return this.sendMoney;
    }
    
    public void setSendMoney(BigDecimal sendMoney) {
        this.sendMoney = sendMoney;
    }
    /**       
     *      *            @hibernate.property
     *             column="ACTIVE"
     *             length="1"
     *         
     */
    public String getActive() {
		return active;
	}


	public void setActive(String active) {
		this.active = active;
	}


	/**       
     *      *            @hibernate.property
     *             column="STATUS"
     *             length="1"
     *         
     */
	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
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
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("recommendNo").append("='").append(getRecommendNo()).append("' ");			
      buffer.append("linkNo").append("='").append(getLinkNo()).append("' ");			
      buffer.append("name").append("='").append(getName()).append("' ");			
      buffer.append("petName").append("='").append(getPetName()).append("' ");			
      buffer.append("isstore").append("='").append(getIsstore()).append("' ");			
      buffer.append("cardType").append("='").append(getCardType()).append("' ");			
      buffer.append("bank").append("='").append(getBank()).append("' ");			
      buffer.append("bankaddress").append("='").append(getBankaddress()).append("' ");			
      buffer.append("bankbook").append("='").append(getBankbook()).append("' ");			
      buffer.append("bankcard").append("='").append(getBankcard()).append("' ");			
      buffer.append("exitDate").append("='").append(getExitDate()).append("' ");			
      buffer.append("passStatus").append("='").append(getPassStatus()).append("' ");			
      buffer.append("honorStar").append("='").append(getHonorStar()).append("' ");			
      buffer.append("passStar").append("='").append(getPassStar()).append("' ");			
      buffer.append("honorGrade").append("='").append(getHonorGrade()).append("' ");			
      buffer.append("passGrade").append("='").append(getPassGrade()).append("' ");			
      buffer.append("honorPosition").append("='").append(getHonorPosition()).append("' ");			
      buffer.append("monthConsumerPv").append("='").append(getMonthConsumerPv()).append("' ");			
      buffer.append("monthAreaTotalPv").append("='").append(getMonthAreaTotalPv()).append("' ");			
      buffer.append("weekGroupPv").append("='").append(getWeekGroupPv()).append("' ");			
      buffer.append("monthGroupPv").append("='").append(getMonthGroupPv()).append("' ");			
      buffer.append("successSalesRate").append("='").append(getSuccessSalesRate()).append("' ");			
      buffer.append("monthRecommendGroupPv").append("='").append(getMonthRecommendGroupPv()).append("' ");			
      buffer.append("franchisePv").append("='").append(getFranchisePv()).append("' ");			
      buffer.append("franchiseMoney").append("='").append(getFranchiseMoney()).append("' ");			
      buffer.append("consumerAmount").append("='").append(getConsumerAmount()).append("' ");			
      buffer.append("ventureSalesPv").append("='").append(getVentureSalesPv()).append("' ");			
      buffer.append("ventureLeaderPv").append("='").append(getVentureLeaderPv()).append("' ");			
      buffer.append("successSalesPv").append("='").append(getSuccessSalesPv()).append("' ");			
      buffer.append("successLeaderPv").append("='").append(getSuccessLeaderPv()).append("' ");			
      buffer.append("deductMoney").append("='").append(getDeductMoney()).append("' ");			
      buffer.append("deductTax").append("='").append(getDeductTax()).append("' ");			
      buffer.append("exchangeRate").append("='").append(getExchangeRate()).append("' ");			
      buffer.append("keepPv").append("='").append(getKeepPv()).append("' ");			
      buffer.append("keepUserCode").append("='").append(getKeepUserCode()).append("' ");			
      buffer.append("lastKeepPv").append("='").append(getLastKeepPv()).append("' ");			
      buffer.append("lastKeepUserCode").append("='").append(getLastKeepUserCode()).append("' ");			
      buffer.append("departmenKeepPv").append("='").append(getDepartmenKeepPv()).append("' ");			
      buffer.append("passStarGroupPv").append("='").append(getPassStarGroupPv()).append("' ");			
      buffer.append("passGradeGroupPv").append("='").append(getPassGradeGroupPv()).append("' ");			
      buffer.append("firstMonth").append("='").append(getFirstMonth()).append("' ");			
      buffer.append("bounsPv").append("='").append(getBounsPv()).append("' ");			
      buffer.append("bounsMoney").append("='").append(getBounsMoney()).append("' ");			
      buffer.append("sendMoney").append("='").append(getSendMoney()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof VJbdMemberLinkCalc) ) return false;
		 VJbdMemberLinkCalc castOther = ( VJbdMemberLinkCalc ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getWyear()==castOther.getWyear()) || ( this.getWyear()!=null && castOther.getWyear()!=null && this.getWyear().equals(castOther.getWyear()) ) )
 && ( (this.getWmonth()==castOther.getWmonth()) || ( this.getWmonth()!=null && castOther.getWmonth()!=null && this.getWmonth().equals(castOther.getWmonth()) ) )
 && ( (this.getWweek()==castOther.getWweek()) || ( this.getWweek()!=null && castOther.getWweek()!=null && this.getWweek().equals(castOther.getWweek()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getRecommendNo()==castOther.getRecommendNo()) || ( this.getRecommendNo()!=null && castOther.getRecommendNo()!=null && this.getRecommendNo().equals(castOther.getRecommendNo()) ) )
 && ( (this.getLinkNo()==castOther.getLinkNo()) || ( this.getLinkNo()!=null && castOther.getLinkNo()!=null && this.getLinkNo().equals(castOther.getLinkNo()) ) )
 && ( (this.getName()==castOther.getName()) || ( this.getName()!=null && castOther.getName()!=null && this.getName().equals(castOther.getName()) ) )
 && ( (this.getPetName()==castOther.getPetName()) || ( this.getPetName()!=null && castOther.getPetName()!=null && this.getPetName().equals(castOther.getPetName()) ) )
 && ( (this.getIsstore()==castOther.getIsstore()) || ( this.getIsstore()!=null && castOther.getIsstore()!=null && this.getIsstore().equals(castOther.getIsstore()) ) )
 && ( (this.getCardType()==castOther.getCardType()) || ( this.getCardType()!=null && castOther.getCardType()!=null && this.getCardType().equals(castOther.getCardType()) ) )
 && ( (this.getBank()==castOther.getBank()) || ( this.getBank()!=null && castOther.getBank()!=null && this.getBank().equals(castOther.getBank()) ) )
 && ( (this.getBankaddress()==castOther.getBankaddress()) || ( this.getBankaddress()!=null && castOther.getBankaddress()!=null && this.getBankaddress().equals(castOther.getBankaddress()) ) )
 && ( (this.getBankbook()==castOther.getBankbook()) || ( this.getBankbook()!=null && castOther.getBankbook()!=null && this.getBankbook().equals(castOther.getBankbook()) ) )
 && ( (this.getBankcard()==castOther.getBankcard()) || ( this.getBankcard()!=null && castOther.getBankcard()!=null && this.getBankcard().equals(castOther.getBankcard()) ) )
 && ( (this.getExitDate()==castOther.getExitDate()) || ( this.getExitDate()!=null && castOther.getExitDate()!=null && this.getExitDate().equals(castOther.getExitDate()) ) )
 && ( (this.getPassStatus()==castOther.getPassStatus()) || ( this.getPassStatus()!=null && castOther.getPassStatus()!=null && this.getPassStatus().equals(castOther.getPassStatus()) ) )
 && ( (this.getHonorStar()==castOther.getHonorStar()) || ( this.getHonorStar()!=null && castOther.getHonorStar()!=null && this.getHonorStar().equals(castOther.getHonorStar()) ) )
 && ( (this.getPassStar()==castOther.getPassStar()) || ( this.getPassStar()!=null && castOther.getPassStar()!=null && this.getPassStar().equals(castOther.getPassStar()) ) )
 && ( (this.getHonorGrade()==castOther.getHonorGrade()) || ( this.getHonorGrade()!=null && castOther.getHonorGrade()!=null && this.getHonorGrade().equals(castOther.getHonorGrade()) ) )
 && ( (this.getPassGrade()==castOther.getPassGrade()) || ( this.getPassGrade()!=null && castOther.getPassGrade()!=null && this.getPassGrade().equals(castOther.getPassGrade()) ) )
 && ( (this.getHonorPosition()==castOther.getHonorPosition()) || ( this.getHonorPosition()!=null && castOther.getHonorPosition()!=null && this.getHonorPosition().equals(castOther.getHonorPosition()) ) )
 && ( (this.getMonthConsumerPv()==castOther.getMonthConsumerPv()) || ( this.getMonthConsumerPv()!=null && castOther.getMonthConsumerPv()!=null && this.getMonthConsumerPv().equals(castOther.getMonthConsumerPv()) ) )
 && ( (this.getMonthAreaTotalPv()==castOther.getMonthAreaTotalPv()) || ( this.getMonthAreaTotalPv()!=null && castOther.getMonthAreaTotalPv()!=null && this.getMonthAreaTotalPv().equals(castOther.getMonthAreaTotalPv()) ) )
 && ( (this.getWeekGroupPv()==castOther.getWeekGroupPv()) || ( this.getWeekGroupPv()!=null && castOther.getWeekGroupPv()!=null && this.getWeekGroupPv().equals(castOther.getWeekGroupPv()) ) )
 && ( (this.getMonthGroupPv()==castOther.getMonthGroupPv()) || ( this.getMonthGroupPv()!=null && castOther.getMonthGroupPv()!=null && this.getMonthGroupPv().equals(castOther.getMonthGroupPv()) ) )
 && ( (this.getSuccessSalesRate()==castOther.getSuccessSalesRate()) || ( this.getSuccessSalesRate()!=null && castOther.getSuccessSalesRate()!=null && this.getSuccessSalesRate().equals(castOther.getSuccessSalesRate()) ) )
 && ( (this.getMonthRecommendGroupPv()==castOther.getMonthRecommendGroupPv()) || ( this.getMonthRecommendGroupPv()!=null && castOther.getMonthRecommendGroupPv()!=null && this.getMonthRecommendGroupPv().equals(castOther.getMonthRecommendGroupPv()) ) )
 && ( (this.getFranchisePv()==castOther.getFranchisePv()) || ( this.getFranchisePv()!=null && castOther.getFranchisePv()!=null && this.getFranchisePv().equals(castOther.getFranchisePv()) ) )
 && ( (this.getFranchiseMoney()==castOther.getFranchiseMoney()) || ( this.getFranchiseMoney()!=null && castOther.getFranchiseMoney()!=null && this.getFranchiseMoney().equals(castOther.getFranchiseMoney()) ) )
 && ( (this.getConsumerAmount()==castOther.getConsumerAmount()) || ( this.getConsumerAmount()!=null && castOther.getConsumerAmount()!=null && this.getConsumerAmount().equals(castOther.getConsumerAmount()) ) )
 && ( (this.getVentureSalesPv()==castOther.getVentureSalesPv()) || ( this.getVentureSalesPv()!=null && castOther.getVentureSalesPv()!=null && this.getVentureSalesPv().equals(castOther.getVentureSalesPv()) ) )
 && ( (this.getVentureLeaderPv()==castOther.getVentureLeaderPv()) || ( this.getVentureLeaderPv()!=null && castOther.getVentureLeaderPv()!=null && this.getVentureLeaderPv().equals(castOther.getVentureLeaderPv()) ) )
 && ( (this.getSuccessSalesPv()==castOther.getSuccessSalesPv()) || ( this.getSuccessSalesPv()!=null && castOther.getSuccessSalesPv()!=null && this.getSuccessSalesPv().equals(castOther.getSuccessSalesPv()) ) )
 && ( (this.getSuccessLeaderPv()==castOther.getSuccessLeaderPv()) || ( this.getSuccessLeaderPv()!=null && castOther.getSuccessLeaderPv()!=null && this.getSuccessLeaderPv().equals(castOther.getSuccessLeaderPv()) ) )
 && ( (this.getDeductMoney()==castOther.getDeductMoney()) || ( this.getDeductMoney()!=null && castOther.getDeductMoney()!=null && this.getDeductMoney().equals(castOther.getDeductMoney()) ) )
 && ( (this.getDeductTax()==castOther.getDeductTax()) || ( this.getDeductTax()!=null && castOther.getDeductTax()!=null && this.getDeductTax().equals(castOther.getDeductTax()) ) )
 && ( (this.getExchangeRate()==castOther.getExchangeRate()) || ( this.getExchangeRate()!=null && castOther.getExchangeRate()!=null && this.getExchangeRate().equals(castOther.getExchangeRate()) ) )
 && ( (this.getKeepPv()==castOther.getKeepPv()) || ( this.getKeepPv()!=null && castOther.getKeepPv()!=null && this.getKeepPv().equals(castOther.getKeepPv()) ) )
 && ( (this.getKeepUserCode()==castOther.getKeepUserCode()) || ( this.getKeepUserCode()!=null && castOther.getKeepUserCode()!=null && this.getKeepUserCode().equals(castOther.getKeepUserCode()) ) )
 && ( (this.getLastKeepPv()==castOther.getLastKeepPv()) || ( this.getLastKeepPv()!=null && castOther.getLastKeepPv()!=null && this.getLastKeepPv().equals(castOther.getLastKeepPv()) ) )
 && ( (this.getLastKeepUserCode()==castOther.getLastKeepUserCode()) || ( this.getLastKeepUserCode()!=null && castOther.getLastKeepUserCode()!=null && this.getLastKeepUserCode().equals(castOther.getLastKeepUserCode()) ) )
 && ( (this.getDepartmenKeepPv()==castOther.getDepartmenKeepPv()) || ( this.getDepartmenKeepPv()!=null && castOther.getDepartmenKeepPv()!=null && this.getDepartmenKeepPv().equals(castOther.getDepartmenKeepPv()) ) )
 && ( (this.getPassStarGroupPv()==castOther.getPassStarGroupPv()) || ( this.getPassStarGroupPv()!=null && castOther.getPassStarGroupPv()!=null && this.getPassStarGroupPv().equals(castOther.getPassStarGroupPv()) ) )
 && ( (this.getPassGradeGroupPv()==castOther.getPassGradeGroupPv()) || ( this.getPassGradeGroupPv()!=null && castOther.getPassGradeGroupPv()!=null && this.getPassGradeGroupPv().equals(castOther.getPassGradeGroupPv()) ) )
 && ( (this.getFirstMonth()==castOther.getFirstMonth()) || ( this.getFirstMonth()!=null && castOther.getFirstMonth()!=null && this.getFirstMonth().equals(castOther.getFirstMonth()) ) )
 && ( (this.getBounsPv()==castOther.getBounsPv()) || ( this.getBounsPv()!=null && castOther.getBounsPv()!=null && this.getBounsPv().equals(castOther.getBounsPv()) ) )
 && ( (this.getBounsMoney()==castOther.getBounsMoney()) || ( this.getBounsMoney()!=null && castOther.getBounsMoney()!=null && this.getBounsMoney().equals(castOther.getBounsMoney()) ) )
 && ( (this.getSendMoney()==castOther.getSendMoney()) || ( this.getSendMoney()!=null && castOther.getSendMoney()!=null && this.getSendMoney().equals(castOther.getSendMoney()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getWyear() == null ? 0 : this.getWyear().hashCode() );
         result = 37 * result + ( getWmonth() == null ? 0 : this.getWmonth().hashCode() );
         result = 37 * result + ( getWweek() == null ? 0 : this.getWweek().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getRecommendNo() == null ? 0 : this.getRecommendNo().hashCode() );
         result = 37 * result + ( getLinkNo() == null ? 0 : this.getLinkNo().hashCode() );
         result = 37 * result + ( getName() == null ? 0 : this.getName().hashCode() );
         result = 37 * result + ( getPetName() == null ? 0 : this.getPetName().hashCode() );
         result = 37 * result + ( getIsstore() == null ? 0 : this.getIsstore().hashCode() );
         result = 37 * result + ( getCardType() == null ? 0 : this.getCardType().hashCode() );
         result = 37 * result + ( getBank() == null ? 0 : this.getBank().hashCode() );
         result = 37 * result + ( getBankaddress() == null ? 0 : this.getBankaddress().hashCode() );
         result = 37 * result + ( getBankbook() == null ? 0 : this.getBankbook().hashCode() );
         result = 37 * result + ( getBankcard() == null ? 0 : this.getBankcard().hashCode() );
         result = 37 * result + ( getExitDate() == null ? 0 : this.getExitDate().hashCode() );
         result = 37 * result + ( getPassStatus() == null ? 0 : this.getPassStatus().hashCode() );
         result = 37 * result + ( getHonorStar() == null ? 0 : this.getHonorStar().hashCode() );
         result = 37 * result + ( getPassStar() == null ? 0 : this.getPassStar().hashCode() );
         result = 37 * result + ( getHonorGrade() == null ? 0 : this.getHonorGrade().hashCode() );
         result = 37 * result + ( getPassGrade() == null ? 0 : this.getPassGrade().hashCode() );
         result = 37 * result + ( getHonorPosition() == null ? 0 : this.getHonorPosition().hashCode() );
         result = 37 * result + ( getMonthConsumerPv() == null ? 0 : this.getMonthConsumerPv().hashCode() );
         result = 37 * result + ( getMonthAreaTotalPv() == null ? 0 : this.getMonthAreaTotalPv().hashCode() );
         result = 37 * result + ( getWeekGroupPv() == null ? 0 : this.getWeekGroupPv().hashCode() );
         result = 37 * result + ( getMonthGroupPv() == null ? 0 : this.getMonthGroupPv().hashCode() );
         result = 37 * result + ( getSuccessSalesRate() == null ? 0 : this.getSuccessSalesRate().hashCode() );
         result = 37 * result + ( getMonthRecommendGroupPv() == null ? 0 : this.getMonthRecommendGroupPv().hashCode() );
         result = 37 * result + ( getFranchisePv() == null ? 0 : this.getFranchisePv().hashCode() );
         result = 37 * result + ( getFranchiseMoney() == null ? 0 : this.getFranchiseMoney().hashCode() );
         result = 37 * result + ( getConsumerAmount() == null ? 0 : this.getConsumerAmount().hashCode() );
         result = 37 * result + ( getVentureSalesPv() == null ? 0 : this.getVentureSalesPv().hashCode() );
         result = 37 * result + ( getVentureLeaderPv() == null ? 0 : this.getVentureLeaderPv().hashCode() );
         result = 37 * result + ( getSuccessSalesPv() == null ? 0 : this.getSuccessSalesPv().hashCode() );
         result = 37 * result + ( getSuccessLeaderPv() == null ? 0 : this.getSuccessLeaderPv().hashCode() );
         result = 37 * result + ( getDeductMoney() == null ? 0 : this.getDeductMoney().hashCode() );
         result = 37 * result + ( getDeductTax() == null ? 0 : this.getDeductTax().hashCode() );
         result = 37 * result + ( getExchangeRate() == null ? 0 : this.getExchangeRate().hashCode() );
         result = 37 * result + ( getKeepPv() == null ? 0 : this.getKeepPv().hashCode() );
         result = 37 * result + ( getKeepUserCode() == null ? 0 : this.getKeepUserCode().hashCode() );
         result = 37 * result + ( getLastKeepPv() == null ? 0 : this.getLastKeepPv().hashCode() );
         result = 37 * result + ( getLastKeepUserCode() == null ? 0 : this.getLastKeepUserCode().hashCode() );
         result = 37 * result + ( getDepartmenKeepPv() == null ? 0 : this.getDepartmenKeepPv().hashCode() );
         result = 37 * result + ( getPassStarGroupPv() == null ? 0 : this.getPassStarGroupPv().hashCode() );
         result = 37 * result + ( getPassGradeGroupPv() == null ? 0 : this.getPassGradeGroupPv().hashCode() );
         result = 37 * result + ( getFirstMonth() == null ? 0 : this.getFirstMonth().hashCode() );
         result = 37 * result + ( getBounsPv() == null ? 0 : this.getBounsPv().hashCode() );
         result = 37 * result + ( getBounsMoney() == null ? 0 : this.getBounsMoney().hashCode() );
         result = 37 * result + ( getSendMoney() == null ? 0 : this.getSendMoney().hashCode() );
         return result;
   }

   /**       
    *      *            @hibernate.property
    *             column="RECOMMEND_BONUS_PV"
    *             length="22"
    *         
    */
public BigDecimal getRecommendBonusPv() {
	return recommendBonusPv;
}


public void setRecommendBonusPv(BigDecimal recommendBonusPv) {
	this.recommendBonusPv = recommendBonusPv;
}

/**       
 *      *            @hibernate.property
 *             column="STORE_EXPAND_PV"
 *             length="22"
 *         
 */
public BigDecimal getStoreExpandPv() {
	return storeExpandPv;
}


public void setStoreExpandPv(BigDecimal storeExpandPv) {
	this.storeExpandPv = storeExpandPv;
}

/**       
 *      *            @hibernate.property
 *             column="STORE_RECOMMEND_PV"
 *             length="22"
 *         
 */
public BigDecimal getStoreRecommendPv() {
	return storeRecommendPv;
}


public void setStoreRecommendPv(BigDecimal storeRecommendPv) {
	this.storeRecommendPv = storeRecommendPv;
}

/**       
 *      *            @hibernate.property
 *             column="STORE_SERVE_PV"
 *             length="22"
 *         
 */
public BigDecimal getStoreServePv() {
	return storeServePv;
}


public void setStoreServePv(BigDecimal storeServePv) {
	this.storeServePv = storeServePv;
}   


/**       
*      *            @hibernate.property
*             column="NETWORK_MONEY"
*         
*/
public BigDecimal getNetworkMoney() {
	return networkMoney;
}


public void setNetworkMoney(BigDecimal networkMoney) {
	this.networkMoney = networkMoney;
}   

/**       
*      *            @hibernate.property
*             column="CURRENT_DEV"
*         
*/
public BigDecimal getCurrentDev() {
	return currentDev;
}


public void setCurrentDev(BigDecimal currentDev) {
	this.currentDev = currentDev;
}


/**       
*      *            @hibernate.property
*             column="DEDUCTED_DEV"
*         
*/
public BigDecimal getDeductedDev() {
	return deductedDev;
}


public void setDeductedDev(BigDecimal deductedDev) {
	this.deductedDev = deductedDev;
}

/**       
*      *            @hibernate.property
*             column="TOTAL_DEV"
*         
*/
public BigDecimal getTotalDev() {
	return totalDev;
}


public void setTotalDev(BigDecimal totalDev) {
	this.totalDev = totalDev;
}   

/**       
*      *            @hibernate.property
*             column="QUALIFY_STAR"
*             length="2"
*         
*/
public Integer getQualifyStar()
{
 return qualifyStar;
}


public void setQualifyStar(Integer qualifyStar)
{
 this.qualifyStar = qualifyStar;
}

/**       
*      *            @hibernate.property
*             column="QUALIFY_REMARK"
*             length="4000"
*         
*/
public String getQualifyRemark()
{
 return qualifyRemark;
}

public void setQualifyRemark(String qualifyRemark)
{
    this.qualifyRemark = qualifyRemark;
}

}
