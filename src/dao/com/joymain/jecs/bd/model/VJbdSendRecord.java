package com.joymain.jecs.bd.model;
// Generated 2009-9-23 11:32:51 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;

import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.sys.model.SysUser;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="V_JBD_SEND_RECORD"
 *     
 */

public class VJbdSendRecord extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


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
    private String registerStatus;
    private String reissueStatus;
    private Date supplyTime;
    private String sendTrace;
    private String sendRemark;
    private BigDecimal sendMoney;
    private Date sendDate;
    private String active;
    private Integer freezeStatus;
    // Constructors
   private Integer memberLevel;
    private BigDecimal currentDev;//

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
     *             column="MEMBER_LEVEL"
     *         
     */

    public Integer getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(Integer memberLevel) {
		this.memberLevel = memberLevel;
	}
    /** default constructor */
    public VJbdSendRecord() {
    }

    
    /** full constructor */
    public VJbdSendRecord(Integer wyear, Integer wmonth, Integer wweek, String companyCode, String userCode, String recommendNo, String linkNo, String name, String petName, String cardType, String bank, String bankaddress, String bankbook, String bankcard, Date exitDate, String sendLateCause, String sendLateRemark, BigDecimal remittanceMoney, String remittanceBNum, String operaterCode, Date operaterTime, String registerStatus, String reissueStatus, Date supplyTime, String sendTrace, String sendRemark, BigDecimal sendMoney, BigDecimal totalMoney) {
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
        this.registerStatus = registerStatus;
        this.reissueStatus = reissueStatus;
        this.supplyTime = supplyTime;
        this.sendTrace = sendTrace;
        this.sendRemark = sendRemark;
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
		return petName;
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
      buffer.append("registerStatus").append("='").append(getRegisterStatus()).append("' ");			
      buffer.append("reissueStatus").append("='").append(getReissueStatus()).append("' ");			
      buffer.append("supplyTime").append("='").append(getSupplyTime()).append("' ");			
      buffer.append("sendTrace").append("='").append(getSendTrace()).append("' ");			
      buffer.append("sendRemark").append("='").append(getSendRemark()).append("' ");			
      buffer.append("sendMoney").append("='").append(getSendMoney()).append("' ");				
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof VJbdSendRecord) ) return false;
		 VJbdSendRecord castOther = ( VJbdSendRecord ) other; 
         
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
 && ( (this.getRegisterStatus()==castOther.getRegisterStatus()) || ( this.getRegisterStatus()!=null && castOther.getRegisterStatus()!=null && this.getRegisterStatus().equals(castOther.getRegisterStatus()) ) )
 && ( (this.getReissueStatus()==castOther.getReissueStatus()) || ( this.getReissueStatus()!=null && castOther.getReissueStatus()!=null && this.getReissueStatus().equals(castOther.getReissueStatus()) ) )
 && ( (this.getSupplyTime()==castOther.getSupplyTime()) || ( this.getSupplyTime()!=null && castOther.getSupplyTime()!=null && this.getSupplyTime().equals(castOther.getSupplyTime()) ) )
 && ( (this.getSendTrace()==castOther.getSendTrace()) || ( this.getSendTrace()!=null && castOther.getSendTrace()!=null && this.getSendTrace().equals(castOther.getSendTrace()) ) )
 && ( (this.getSendRemark()==castOther.getSendRemark()) || ( this.getSendRemark()!=null && castOther.getSendRemark()!=null && this.getSendRemark().equals(castOther.getSendRemark()) ) )
 && ( (this.getSendMoney()==castOther.getSendMoney()) || ( this.getSendMoney()!=null && castOther.getSendMoney()!=null && this.getSendMoney().equals(castOther.getSendMoney()) ) );
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
         result = 37 * result + ( getRegisterStatus() == null ? 0 : this.getRegisterStatus().hashCode() );
         result = 37 * result + ( getReissueStatus() == null ? 0 : this.getReissueStatus().hashCode() );
         result = 37 * result + ( getSupplyTime() == null ? 0 : this.getSupplyTime().hashCode() );
         result = 37 * result + ( getSendTrace() == null ? 0 : this.getSendTrace().hashCode() );
         result = 37 * result + ( getSendRemark() == null ? 0 : this.getSendRemark().hashCode() );
         result = 37 * result + ( getSendMoney() == null ? 0 : this.getSendMoney().hashCode() );
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
	



    /**       
     *      *            @hibernate.property
     *             column="SEND_DATE"
     *             length="7"
     *         
     */
	public Date getSendDate() {
		return sendDate;
	}


	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
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







}
