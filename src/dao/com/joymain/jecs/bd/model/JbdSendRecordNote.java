package com.joymain.jecs.bd.model;
// Generated 2013-9-11 9:44:45 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;

import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.sys.model.SysUser;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JBD_SEND_RECORD_NOTE"
 *     
 */

public class JbdSendRecordNote extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private Integer wyear;
    private Integer wmonth;
    private Integer wweek;
    private String companyCode;
    private JmiMember jmiMember;
    private String recommendNo;
    private String linkNo;
    private String name;
    private String petName;
    private String cardType;
    private String bank;
    private String bankaddress;
    private String bankbook;
    private String bankcard;
    private Date exitDate;
    private String sendLateCause;
    private String sendLateRemark;
    private BigDecimal remittanceMoney;
    private String remittanceBNum;
    private SysUser operaterSysUser;
    private Date operaterTime;
    private Date sendDate;
    private String registerStatus;
    private String reissueStatus;
    private Date supplyTime;
    private String sendTrace;
    private String sendRemark;
    private BigDecimal sendMoney;
    private String active;
    private Integer status;
    private String memberType;
    private Integer startWeek;
    private Integer validWeek;
    private Integer freezeStatus;
    private Integer beforeFreezeStatus;
    private BigDecimal totalDev;
    private BigDecimal deductedDev;
    private BigDecimal currentDev;
    private BigDecimal leaderDev;
    private BigDecimal leaderDevPv;
    private String sendStatusDev;
    private Date sendDateDev;
    private String sendUserDev;

    private Integer memberLevel;
    

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

    /** default constructor */
    public JbdSendRecordNote() {
    }
    public JbdSendRecordNote(JbdSendRecordHist jbdSendRecordHist){
    	 this.wyear = jbdSendRecordHist.getWyear();
         this.wmonth = jbdSendRecordHist.getWmonth();
         this.wweek = jbdSendRecordHist.getWweek();
         this.companyCode = jbdSendRecordHist.getCompanyCode();
         this.recommendNo = jbdSendRecordHist.getRecommendNo();
         this.linkNo = jbdSendRecordHist.getLinkNo();
         this.name = jbdSendRecordHist.getName();
         this.petName = jbdSendRecordHist.getPetName();
         this.cardType = jbdSendRecordHist.getCardType();
         this.bank = jbdSendRecordHist.getBank();
         this.bankaddress = jbdSendRecordHist.getBankaddress();
         this.bankbook = jbdSendRecordHist.getBankbook();
         this.bankcard = jbdSendRecordHist.getBankcard();
         this.exitDate = jbdSendRecordHist.getExitDate();
         this.sendLateCause = jbdSendRecordHist.getSendLateCause();
         this.sendLateRemark = jbdSendRecordHist.getSendLateRemark();
         this.remittanceMoney = jbdSendRecordHist.getRemittanceMoney();
         this.remittanceBNum = jbdSendRecordHist.getRemittanceBNum();
         this.operaterTime = jbdSendRecordHist.getOperaterTime();
         this.sendDate = jbdSendRecordHist.getSendDate();
         this.registerStatus = jbdSendRecordHist.getRegisterStatus();
         this.reissueStatus = jbdSendRecordHist.getReissueStatus();
         this.supplyTime = jbdSendRecordHist.getSupplyTime();
         this.sendTrace = jbdSendRecordHist.getSendTrace();
         this.sendRemark = jbdSendRecordHist.getSendRemark();
         this.sendMoney = jbdSendRecordHist.getSendMoney();
         this.active = jbdSendRecordHist.getActive();
         this.status = jbdSendRecordHist.getStatus();
         this.memberType = jbdSendRecordHist.getMemberType();
         this.startWeek = jbdSendRecordHist.getStartWeek();
         this.validWeek = jbdSendRecordHist.getValidWeek();
         this.freezeStatus = jbdSendRecordHist.getFreezeStatus();
         this.beforeFreezeStatus = jbdSendRecordHist.getBeforeFreezeStatus();
         this.totalDev = jbdSendRecordHist.getTotalDev();
         this.deductedDev = jbdSendRecordHist.getDeductedDev();
         this.currentDev = jbdSendRecordHist.getCurrentDev();
         this.leaderDev = jbdSendRecordHist.getLeaderDev();
         this.leaderDevPv = jbdSendRecordHist.getLeaderDevPv();
         this.sendStatusDev = jbdSendRecordHist.getSendStatusDev();
         this.sendDateDev = jbdSendRecordHist.getSendDateDev();
         this.sendUserDev = jbdSendRecordHist.getSendUserDev();
         this.operaterSysUser=jbdSendRecordHist.getOperaterSysUser();
         this.jmiMember=jbdSendRecordHist.getJmiMember();
         this.id=jbdSendRecordHist.getId();
    }
    
    /** full constructor */
    public JbdSendRecordNote(Integer wyear, Integer wmonth, Integer wweek, String companyCode, String userCode, String recommendNo, String linkNo, String name, String petName, String cardType, String bank, String bankaddress, String bankbook, String bankcard, Date exitDate, String sendLateCause, String sendLateRemark, BigDecimal remittanceMoney, String remittanceBNum, String operaterCode, Date operaterTime, Date sendDate, String registerStatus, String reissueStatus, Date supplyTime, String sendTrace, String sendRemark, BigDecimal sendMoney, String active, Integer status, String memberType, BigDecimal startWeek, BigDecimal validWeek, BigDecimal freezeStatus, BigDecimal beforeFreezeStatus, BigDecimal totalDev, BigDecimal deductedDev, BigDecimal currentDev, BigDecimal leaderDev, BigDecimal leaderDevPv, String sendStatusDev, Date sendDateDev, String sendUserDev) {
        this.wyear = wyear;
        this.wmonth = wmonth;
        this.wweek = wweek;
        this.companyCode = companyCode;
        this.recommendNo = recommendNo;
        this.linkNo = linkNo;
        this.name = name;
        this.petName = petName;
        this.cardType = cardType;
        this.bank = bank;
        this.bankaddress = bankaddress;
        this.bankbook = bankbook;
        this.bankcard = bankcard;
        this.exitDate = exitDate;
        this.sendLateCause = sendLateCause;
        this.sendLateRemark = sendLateRemark;
        this.remittanceMoney = remittanceMoney;
        this.remittanceBNum = remittanceBNum;
        this.operaterTime = operaterTime;
        this.sendDate = sendDate;
        this.registerStatus = registerStatus;
        this.reissueStatus = reissueStatus;
        this.supplyTime = supplyTime;
        this.sendTrace = sendTrace;
        this.sendRemark = sendRemark;
        this.sendMoney = sendMoney;
        this.active = active;
        this.status = status;
        this.memberType = memberType;
        this.totalDev = totalDev;
        this.deductedDev = deductedDev;
        this.currentDev = currentDev;
        this.leaderDev = leaderDev;
        this.leaderDevPv = leaderDevPv;
        this.sendStatusDev = sendStatusDev;
        this.sendDateDev = sendDateDev;
        this.sendUserDev = sendUserDev;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
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
     *             column="SEND_LATE_CAUSE"
     *             length="4000"
     *         
     */

    public String getSendLateCause() {
        return this.sendLateCause;
    }
    
    public void setSendLateCause(String sendLateCause) {
        this.sendLateCause = sendLateCause;
    }
    /**       
     *      *            @hibernate.property
     *             column="SEND_LATE_REMARK"
     *             length="4000"
     *         
     */

    public String getSendLateRemark() {
        return this.sendLateRemark;
    }
    
    public void setSendLateRemark(String sendLateRemark) {
        this.sendLateRemark = sendLateRemark;
    }
    /**       
     *      *            @hibernate.property
     *             column="REMITTANCE_MONEY"
     *             length="22"
     *         
     */

    public BigDecimal getRemittanceMoney() {
        return this.remittanceMoney;
    }
    
    public void setRemittanceMoney(BigDecimal remittanceMoney) {
        this.remittanceMoney = remittanceMoney;
    }
    /**       
     *      *            @hibernate.property
     *             column="REMITTANCE_B_NUM"
     *             length="100"
     *         
     */

    public String getRemittanceBNum() {
        return this.remittanceBNum;
    }
    
    public void setRemittanceBNum(String remittanceBNum) {
        this.remittanceBNum = remittanceBNum;
    }

    /**       
     *      *            @hibernate.property
     *             column="OPERATER_TIME"
     *             length="7"
     *         
     */

    public Date getOperaterTime() {
        return this.operaterTime;
    }
    
    public void setOperaterTime(Date operaterTime) {
        this.operaterTime = operaterTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="SEND_DATE"
     *             length="7"
     *         
     */

    public Date getSendDate() {
        return this.sendDate;
    }
    
    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }
    /**       
     *      *            @hibernate.property
     *             column="REGISTER_STATUS"
     *             length="1"
     *         
     */

    public String getRegisterStatus() {
        return this.registerStatus;
    }
    
    public void setRegisterStatus(String registerStatus) {
        this.registerStatus = registerStatus;
    }
    /**       
     *      *            @hibernate.property
     *             column="REISSUE_STATUS"
     *             length="1"
     *         
     */

    public String getReissueStatus() {
        return this.reissueStatus;
    }
    
    public void setReissueStatus(String reissueStatus) {
        this.reissueStatus = reissueStatus;
    }
    /**       
     *      *            @hibernate.property
     *             column="SUPPLY_TIME"
     *             length="7"
     *         
     */

    public Date getSupplyTime() {
        return this.supplyTime;
    }
    
    public void setSupplyTime(Date supplyTime) {
        this.supplyTime = supplyTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="SEND_TRACE"
     *             length="4000"
     *         
     */

    public String getSendTrace() {
        return this.sendTrace;
    }
    
    public void setSendTrace(String sendTrace) {
        this.sendTrace = sendTrace;
    }
    /**       
     *      *            @hibernate.property
     *             column="SEND_REMARK"
     *             length="4000"
     *         
     */

    public String getSendRemark() {
        return this.sendRemark;
    }
    
    public void setSendRemark(String sendRemark) {
        this.sendRemark = sendRemark;
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
        return this.active;
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
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
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
     *             column="START_WEEK"
     *             length="22"
     *         
     */

    public Integer getStartWeek() {
        return this.startWeek;
    }
    
    public void setStartWeek(Integer startWeek) {
        this.startWeek = startWeek;
    }
    /**       
     *      *            @hibernate.property
     *             column="VALID_WEEK"
     *             length="22"
     *         
     */

    public Integer getValidWeek() {
        return this.validWeek;
    }
    
    public void setValidWeek(Integer validWeek) {
        this.validWeek = validWeek;
    }
    /**       
     *      *            @hibernate.property
     *             column="FREEZE_STATUS"
     *             length="22"
     *         
     */

    public Integer getFreezeStatus() {
        return this.freezeStatus;
    }
    
    public void setFreezeStatus(Integer freezeStatus) {
        this.freezeStatus = freezeStatus;
    }
    /**       
     *      *            @hibernate.property
     *             column="BEFORE_FREEZE_STATUS"
     *             length="22"
     *         
     */

    public Integer getBeforeFreezeStatus() {
        return this.beforeFreezeStatus;
    }
    
    public void setBeforeFreezeStatus(Integer beforeFreezeStatus) {
        this.beforeFreezeStatus = beforeFreezeStatus;
    }
    /**       
     *      *            @hibernate.property
     *             column="TOTAL_DEV"
     *             length="22"
     *         
     */

    public BigDecimal getTotalDev() {
        return this.totalDev;
    }
    
    public void setTotalDev(BigDecimal totalDev) {
        this.totalDev = totalDev;
    }
    /**       
     *      *            @hibernate.property
     *             column="DEDUCTED_DEV"
     *             length="22"
     *         
     */

    public BigDecimal getDeductedDev() {
        return this.deductedDev;
    }
    
    public void setDeductedDev(BigDecimal deductedDev) {
        this.deductedDev = deductedDev;
    }
    /**       
     *      *            @hibernate.property
     *             column="CURRENT_DEV"
     *             length="22"
     *         
     */

    public BigDecimal getCurrentDev() {
        return this.currentDev;
    }
    
    public void setCurrentDev(BigDecimal currentDev) {
        this.currentDev = currentDev;
    }
    /**       
     *      *            @hibernate.property
     *             column="LEADER_DEV"
     *             length="22"
     *         
     */

    public BigDecimal getLeaderDev() {
        return this.leaderDev;
    }
    
    public void setLeaderDev(BigDecimal leaderDev) {
        this.leaderDev = leaderDev;
    }
    /**       
     *      *            @hibernate.property
     *             column="LEADER_DEV_PV"
     *             length="22"
     *         
     */

    public BigDecimal getLeaderDevPv() {
        return this.leaderDevPv;
    }
    
    public void setLeaderDevPv(BigDecimal leaderDevPv) {
        this.leaderDevPv = leaderDevPv;
    }
    /**       
     *      *            @hibernate.property
     *             column="SEND_STATUS_DEV"
     *             length="1"
     *         
     */

    public String getSendStatusDev() {
        return this.sendStatusDev;
    }
    
    public void setSendStatusDev(String sendStatusDev) {
        this.sendStatusDev = sendStatusDev;
    }
    /**       
     *      *            @hibernate.property
     *             column="SEND_DATE_DEV"
     *             length="7"
     *         
     */

    public Date getSendDateDev() {
        return this.sendDateDev;
    }
    
    public void setSendDateDev(Date sendDateDev) {
        this.sendDateDev = sendDateDev;
    }
    /**       
     *      *            @hibernate.property
     *             column="SEND_USER_DEV"
     *             length="20"
     *         
     */

    public String getSendUserDev() {
        return this.sendUserDev;
    }
    
    public void setSendUserDev(String sendUserDev) {
        this.sendUserDev = sendUserDev;
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
      buffer.append("recommendNo").append("='").append(getRecommendNo()).append("' ");			
      buffer.append("linkNo").append("='").append(getLinkNo()).append("' ");			
      buffer.append("name").append("='").append(getName()).append("' ");			
      buffer.append("petName").append("='").append(getPetName()).append("' ");			
      buffer.append("cardType").append("='").append(getCardType()).append("' ");			
      buffer.append("bank").append("='").append(getBank()).append("' ");			
      buffer.append("bankaddress").append("='").append(getBankaddress()).append("' ");			
      buffer.append("bankbook").append("='").append(getBankbook()).append("' ");			
      buffer.append("bankcard").append("='").append(getBankcard()).append("' ");			
      buffer.append("exitDate").append("='").append(getExitDate()).append("' ");			
      buffer.append("sendLateCause").append("='").append(getSendLateCause()).append("' ");			
      buffer.append("sendLateRemark").append("='").append(getSendLateRemark()).append("' ");			
      buffer.append("remittanceMoney").append("='").append(getRemittanceMoney()).append("' ");			
      buffer.append("remittanceBNum").append("='").append(getRemittanceBNum()).append("' ");		
      buffer.append("operaterTime").append("='").append(getOperaterTime()).append("' ");			
      buffer.append("sendDate").append("='").append(getSendDate()).append("' ");			
      buffer.append("registerStatus").append("='").append(getRegisterStatus()).append("' ");			
      buffer.append("reissueStatus").append("='").append(getReissueStatus()).append("' ");			
      buffer.append("supplyTime").append("='").append(getSupplyTime()).append("' ");			
      buffer.append("sendTrace").append("='").append(getSendTrace()).append("' ");			
      buffer.append("sendRemark").append("='").append(getSendRemark()).append("' ");			
      buffer.append("sendMoney").append("='").append(getSendMoney()).append("' ");			
      buffer.append("active").append("='").append(getActive()).append("' ");			
      buffer.append("status").append("='").append(getStatus()).append("' ");			
      buffer.append("memberType").append("='").append(getMemberType()).append("' ");			
      buffer.append("startWeek").append("='").append(getStartWeek()).append("' ");			
      buffer.append("validWeek").append("='").append(getValidWeek()).append("' ");			
      buffer.append("freezeStatus").append("='").append(getFreezeStatus()).append("' ");			
      buffer.append("beforeFreezeStatus").append("='").append(getBeforeFreezeStatus()).append("' ");			
      buffer.append("totalDev").append("='").append(getTotalDev()).append("' ");			
      buffer.append("deductedDev").append("='").append(getDeductedDev()).append("' ");			
      buffer.append("currentDev").append("='").append(getCurrentDev()).append("' ");			
      buffer.append("leaderDev").append("='").append(getLeaderDev()).append("' ");			
      buffer.append("leaderDevPv").append("='").append(getLeaderDevPv()).append("' ");			
      buffer.append("sendStatusDev").append("='").append(getSendStatusDev()).append("' ");			
      buffer.append("sendDateDev").append("='").append(getSendDateDev()).append("' ");			
      buffer.append("sendUserDev").append("='").append(getSendUserDev()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JbdSendRecordNote) ) return false;
		 JbdSendRecordNote castOther = ( JbdSendRecordNote ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getWyear()==castOther.getWyear()) || ( this.getWyear()!=null && castOther.getWyear()!=null && this.getWyear().equals(castOther.getWyear()) ) )
 && ( (this.getWmonth()==castOther.getWmonth()) || ( this.getWmonth()!=null && castOther.getWmonth()!=null && this.getWmonth().equals(castOther.getWmonth()) ) )
 && ( (this.getWweek()==castOther.getWweek()) || ( this.getWweek()!=null && castOther.getWweek()!=null && this.getWweek().equals(castOther.getWweek()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getRecommendNo()==castOther.getRecommendNo()) || ( this.getRecommendNo()!=null && castOther.getRecommendNo()!=null && this.getRecommendNo().equals(castOther.getRecommendNo()) ) )
 && ( (this.getLinkNo()==castOther.getLinkNo()) || ( this.getLinkNo()!=null && castOther.getLinkNo()!=null && this.getLinkNo().equals(castOther.getLinkNo()) ) )
 && ( (this.getName()==castOther.getName()) || ( this.getName()!=null && castOther.getName()!=null && this.getName().equals(castOther.getName()) ) )
 && ( (this.getPetName()==castOther.getPetName()) || ( this.getPetName()!=null && castOther.getPetName()!=null && this.getPetName().equals(castOther.getPetName()) ) )
 && ( (this.getCardType()==castOther.getCardType()) || ( this.getCardType()!=null && castOther.getCardType()!=null && this.getCardType().equals(castOther.getCardType()) ) )
 && ( (this.getBank()==castOther.getBank()) || ( this.getBank()!=null && castOther.getBank()!=null && this.getBank().equals(castOther.getBank()) ) )
 && ( (this.getBankaddress()==castOther.getBankaddress()) || ( this.getBankaddress()!=null && castOther.getBankaddress()!=null && this.getBankaddress().equals(castOther.getBankaddress()) ) )
 && ( (this.getBankbook()==castOther.getBankbook()) || ( this.getBankbook()!=null && castOther.getBankbook()!=null && this.getBankbook().equals(castOther.getBankbook()) ) )
 && ( (this.getBankcard()==castOther.getBankcard()) || ( this.getBankcard()!=null && castOther.getBankcard()!=null && this.getBankcard().equals(castOther.getBankcard()) ) )
 && ( (this.getExitDate()==castOther.getExitDate()) || ( this.getExitDate()!=null && castOther.getExitDate()!=null && this.getExitDate().equals(castOther.getExitDate()) ) )
 && ( (this.getSendLateCause()==castOther.getSendLateCause()) || ( this.getSendLateCause()!=null && castOther.getSendLateCause()!=null && this.getSendLateCause().equals(castOther.getSendLateCause()) ) )
 && ( (this.getSendLateRemark()==castOther.getSendLateRemark()) || ( this.getSendLateRemark()!=null && castOther.getSendLateRemark()!=null && this.getSendLateRemark().equals(castOther.getSendLateRemark()) ) )
 && ( (this.getRemittanceMoney()==castOther.getRemittanceMoney()) || ( this.getRemittanceMoney()!=null && castOther.getRemittanceMoney()!=null && this.getRemittanceMoney().equals(castOther.getRemittanceMoney()) ) )
 && ( (this.getRemittanceBNum()==castOther.getRemittanceBNum()) || ( this.getRemittanceBNum()!=null && castOther.getRemittanceBNum()!=null && this.getRemittanceBNum().equals(castOther.getRemittanceBNum()) ) )
 && ( (this.getOperaterTime()==castOther.getOperaterTime()) || ( this.getOperaterTime()!=null && castOther.getOperaterTime()!=null && this.getOperaterTime().equals(castOther.getOperaterTime()) ) )
 && ( (this.getSendDate()==castOther.getSendDate()) || ( this.getSendDate()!=null && castOther.getSendDate()!=null && this.getSendDate().equals(castOther.getSendDate()) ) )
 && ( (this.getRegisterStatus()==castOther.getRegisterStatus()) || ( this.getRegisterStatus()!=null && castOther.getRegisterStatus()!=null && this.getRegisterStatus().equals(castOther.getRegisterStatus()) ) )
 && ( (this.getReissueStatus()==castOther.getReissueStatus()) || ( this.getReissueStatus()!=null && castOther.getReissueStatus()!=null && this.getReissueStatus().equals(castOther.getReissueStatus()) ) )
 && ( (this.getSupplyTime()==castOther.getSupplyTime()) || ( this.getSupplyTime()!=null && castOther.getSupplyTime()!=null && this.getSupplyTime().equals(castOther.getSupplyTime()) ) )
 && ( (this.getSendTrace()==castOther.getSendTrace()) || ( this.getSendTrace()!=null && castOther.getSendTrace()!=null && this.getSendTrace().equals(castOther.getSendTrace()) ) )
 && ( (this.getSendRemark()==castOther.getSendRemark()) || ( this.getSendRemark()!=null && castOther.getSendRemark()!=null && this.getSendRemark().equals(castOther.getSendRemark()) ) )
 && ( (this.getSendMoney()==castOther.getSendMoney()) || ( this.getSendMoney()!=null && castOther.getSendMoney()!=null && this.getSendMoney().equals(castOther.getSendMoney()) ) )
 && ( (this.getActive()==castOther.getActive()) || ( this.getActive()!=null && castOther.getActive()!=null && this.getActive().equals(castOther.getActive()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
 && ( (this.getMemberType()==castOther.getMemberType()) || ( this.getMemberType()!=null && castOther.getMemberType()!=null && this.getMemberType().equals(castOther.getMemberType()) ) )
 && ( (this.getStartWeek()==castOther.getStartWeek()) || ( this.getStartWeek()!=null && castOther.getStartWeek()!=null && this.getStartWeek().equals(castOther.getStartWeek()) ) )
 && ( (this.getValidWeek()==castOther.getValidWeek()) || ( this.getValidWeek()!=null && castOther.getValidWeek()!=null && this.getValidWeek().equals(castOther.getValidWeek()) ) )
 && ( (this.getFreezeStatus()==castOther.getFreezeStatus()) || ( this.getFreezeStatus()!=null && castOther.getFreezeStatus()!=null && this.getFreezeStatus().equals(castOther.getFreezeStatus()) ) )
 && ( (this.getBeforeFreezeStatus()==castOther.getBeforeFreezeStatus()) || ( this.getBeforeFreezeStatus()!=null && castOther.getBeforeFreezeStatus()!=null && this.getBeforeFreezeStatus().equals(castOther.getBeforeFreezeStatus()) ) )
 && ( (this.getTotalDev()==castOther.getTotalDev()) || ( this.getTotalDev()!=null && castOther.getTotalDev()!=null && this.getTotalDev().equals(castOther.getTotalDev()) ) )
 && ( (this.getDeductedDev()==castOther.getDeductedDev()) || ( this.getDeductedDev()!=null && castOther.getDeductedDev()!=null && this.getDeductedDev().equals(castOther.getDeductedDev()) ) )
 && ( (this.getCurrentDev()==castOther.getCurrentDev()) || ( this.getCurrentDev()!=null && castOther.getCurrentDev()!=null && this.getCurrentDev().equals(castOther.getCurrentDev()) ) )
 && ( (this.getLeaderDev()==castOther.getLeaderDev()) || ( this.getLeaderDev()!=null && castOther.getLeaderDev()!=null && this.getLeaderDev().equals(castOther.getLeaderDev()) ) )
 && ( (this.getLeaderDevPv()==castOther.getLeaderDevPv()) || ( this.getLeaderDevPv()!=null && castOther.getLeaderDevPv()!=null && this.getLeaderDevPv().equals(castOther.getLeaderDevPv()) ) )
 && ( (this.getSendStatusDev()==castOther.getSendStatusDev()) || ( this.getSendStatusDev()!=null && castOther.getSendStatusDev()!=null && this.getSendStatusDev().equals(castOther.getSendStatusDev()) ) )
 && ( (this.getSendDateDev()==castOther.getSendDateDev()) || ( this.getSendDateDev()!=null && castOther.getSendDateDev()!=null && this.getSendDateDev().equals(castOther.getSendDateDev()) ) )
 && ( (this.getSendUserDev()==castOther.getSendUserDev()) || ( this.getSendUserDev()!=null && castOther.getSendUserDev()!=null && this.getSendUserDev().equals(castOther.getSendUserDev()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getWyear() == null ? 0 : this.getWyear().hashCode() );
         result = 37 * result + ( getWmonth() == null ? 0 : this.getWmonth().hashCode() );
         result = 37 * result + ( getWweek() == null ? 0 : this.getWweek().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getRecommendNo() == null ? 0 : this.getRecommendNo().hashCode() );
         result = 37 * result + ( getLinkNo() == null ? 0 : this.getLinkNo().hashCode() );
         result = 37 * result + ( getName() == null ? 0 : this.getName().hashCode() );
         result = 37 * result + ( getPetName() == null ? 0 : this.getPetName().hashCode() );
         result = 37 * result + ( getCardType() == null ? 0 : this.getCardType().hashCode() );
         result = 37 * result + ( getBank() == null ? 0 : this.getBank().hashCode() );
         result = 37 * result + ( getBankaddress() == null ? 0 : this.getBankaddress().hashCode() );
         result = 37 * result + ( getBankbook() == null ? 0 : this.getBankbook().hashCode() );
         result = 37 * result + ( getBankcard() == null ? 0 : this.getBankcard().hashCode() );
         result = 37 * result + ( getExitDate() == null ? 0 : this.getExitDate().hashCode() );
         result = 37 * result + ( getSendLateCause() == null ? 0 : this.getSendLateCause().hashCode() );
         result = 37 * result + ( getSendLateRemark() == null ? 0 : this.getSendLateRemark().hashCode() );
         result = 37 * result + ( getRemittanceMoney() == null ? 0 : this.getRemittanceMoney().hashCode() );
         result = 37 * result + ( getRemittanceBNum() == null ? 0 : this.getRemittanceBNum().hashCode() );
         result = 37 * result + ( getOperaterTime() == null ? 0 : this.getOperaterTime().hashCode() );
         result = 37 * result + ( getSendDate() == null ? 0 : this.getSendDate().hashCode() );
         result = 37 * result + ( getRegisterStatus() == null ? 0 : this.getRegisterStatus().hashCode() );
         result = 37 * result + ( getReissueStatus() == null ? 0 : this.getReissueStatus().hashCode() );
         result = 37 * result + ( getSupplyTime() == null ? 0 : this.getSupplyTime().hashCode() );
         result = 37 * result + ( getSendTrace() == null ? 0 : this.getSendTrace().hashCode() );
         result = 37 * result + ( getSendRemark() == null ? 0 : this.getSendRemark().hashCode() );
         result = 37 * result + ( getSendMoney() == null ? 0 : this.getSendMoney().hashCode() );
         result = 37 * result + ( getActive() == null ? 0 : this.getActive().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         result = 37 * result + ( getMemberType() == null ? 0 : this.getMemberType().hashCode() );
         result = 37 * result + ( getStartWeek() == null ? 0 : this.getStartWeek().hashCode() );
         result = 37 * result + ( getValidWeek() == null ? 0 : this.getValidWeek().hashCode() );
         result = 37 * result + ( getFreezeStatus() == null ? 0 : this.getFreezeStatus().hashCode() );
         result = 37 * result + ( getBeforeFreezeStatus() == null ? 0 : this.getBeforeFreezeStatus().hashCode() );
         result = 37 * result + ( getTotalDev() == null ? 0 : this.getTotalDev().hashCode() );
         result = 37 * result + ( getDeductedDev() == null ? 0 : this.getDeductedDev().hashCode() );
         result = 37 * result + ( getCurrentDev() == null ? 0 : this.getCurrentDev().hashCode() );
         result = 37 * result + ( getLeaderDev() == null ? 0 : this.getLeaderDev().hashCode() );
         result = 37 * result + ( getLeaderDevPv() == null ? 0 : this.getLeaderDevPv().hashCode() );
         result = 37 * result + ( getSendStatusDev() == null ? 0 : this.getSendStatusDev().hashCode() );
         result = 37 * result + ( getSendDateDev() == null ? 0 : this.getSendDateDev().hashCode() );
         result = 37 * result + ( getSendUserDev() == null ? 0 : this.getSendUserDev().hashCode() );
         return result;
   }   

   /**
    * *
    * @hibernate.many-to-one
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
	    * *
	    * @hibernate.many-to-one 
	    * @hibernate.column name="OPERATER_CODE"
	    * 
	    */

	public SysUser getOperaterSysUser() {
		return operaterSysUser;
	}


	public void setOperaterSysUser(SysUser operaterSysUser) {
		this.operaterSysUser = operaterSysUser;
	}



}
