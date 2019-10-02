package com.joymain.jecs.fi.model;
// Generated 2015-10-30 10:26:06 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JFI_DEPOSIT_JOURNAL"
 *     
 */

public class JfiDepositJournal extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long journalId;
    private String companyCode;
    private String userCode;
    private String dealType;
    private BigDecimal money;
    private String notes;
    private BigDecimal balance;
    private String remark;
    private String createrNo;
    private Date createTime;
    private Integer moneyType;
    private String uniqueCode;
    private String depositType;

    private BigDecimal addMoney;
    private BigDecimal decMoney;

    // Constructors

    /** default constructor */
    public JfiDepositJournal() {
    }

    
    /** full constructor */
    public JfiDepositJournal( String companyCode, String userCode, String dealType, BigDecimal money, String notes, BigDecimal balance, String remark, String createrNo, Date createTime, Integer moneyType, String uniqueCode, String depositType) {

        this.companyCode = companyCode;
        this.userCode = userCode;
        this.dealType = dealType;
        this.money = money;
        this.notes = notes;
        this.balance = balance;
        this.remark = remark;
        this.createrNo = createrNo;
        this.createTime = createTime;
        this.moneyType = moneyType;
        this.uniqueCode = uniqueCode;
        this.depositType = depositType;
    }
    

	public BigDecimal getAddMoney() {
		if("A".equals(this.dealType)){
			return this.money; 
		}else{
			return null;
		}
    }

	public void setAddMoney(BigDecimal addMoney) {
    	this.addMoney = addMoney;
    }

	public BigDecimal getDecMoney() {
		if("D".equals(this.dealType)){
			return this.money; 
		}else{
			return null;
		}
    }

	public void setDecMoney(BigDecimal decMoney) {
    	this.decMoney = decMoney;
    }
   
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="JOURNAL_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_FI"
     *         
     */
    public Long getJournalId() {
        return this.journalId;
    }
    
    public void setJournalId(Long journalId) {
        this.journalId = journalId;
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
     *             column="DEAL_TYPE"
     *             length="1"
     *         
     */

    public String getDealType() {
        return this.dealType;
    }
    
    public void setDealType(String dealType) {
        this.dealType = dealType;
    }
    /**       
     *      *            @hibernate.property
     *             column="MONEY"
     *             length="18"
     *         
     */

    public BigDecimal getMoney() {
        return this.money;
    }
    
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    /**       
     *      *            @hibernate.property
     *             column="NOTES"
     *             length="4000"
     *         
     */

    public String getNotes() {
        return this.notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    /**       
     *      *            @hibernate.property
     *             column="BALANCE"
     *             length="18"
     *         
     */

    public BigDecimal getBalance() {
        return this.balance;
    }
    
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
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
     *             column="CREATER_NO"
     *             length="20"
     *         
     */

    public String getCreaterNo() {
        return this.createrNo;
    }
    
    public void setCreaterNo(String createrNo) {
        this.createrNo = createrNo;
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
     *             column="MONEY_TYPE"
     *             length="4"
     *         
     */

    public Integer getMoneyType() {
        return this.moneyType;
    }
    
    public void setMoneyType(Integer moneyType) {
        this.moneyType = moneyType;
    }
    /**       
     *      *            @hibernate.property
     *             column="UNIQUE_CODE"
     *             length="200"
     *         
     */

    public String getUniqueCode() {
        return this.uniqueCode;
    }
    
    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="DEPOSIT_TYPE"
     *             length="1"
     *         
     */

    public String getDepositType() {
        return this.depositType;
    }
    
    public void setDepositType(String depositType) {
        this.depositType = depositType;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");	
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("dealType").append("='").append(getDealType()).append("' ");			
      buffer.append("money").append("='").append(getMoney()).append("' ");			
      buffer.append("notes").append("='").append(getNotes()).append("' ");			
      buffer.append("balance").append("='").append(getBalance()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");			
      buffer.append("createrNo").append("='").append(getCreaterNo()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("moneyType").append("='").append(getMoneyType()).append("' ");			
      buffer.append("uniqueCode").append("='").append(getUniqueCode()).append("' ");			
      buffer.append("depositType").append("='").append(getDepositType()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JfiDepositJournal) ) return false;
		 JfiDepositJournal castOther = ( JfiDepositJournal ) other; 
         
		 return ( (this.getJournalId()==castOther.getJournalId()) || ( this.getJournalId()!=null && castOther.getJournalId()!=null && this.getJournalId().equals(castOther.getJournalId()) ) )

 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getDealType()==castOther.getDealType()) || ( this.getDealType()!=null && castOther.getDealType()!=null && this.getDealType().equals(castOther.getDealType()) ) )
 && ( (this.getMoney()==castOther.getMoney()) || ( this.getMoney()!=null && castOther.getMoney()!=null && this.getMoney().equals(castOther.getMoney()) ) )
 && ( (this.getNotes()==castOther.getNotes()) || ( this.getNotes()!=null && castOther.getNotes()!=null && this.getNotes().equals(castOther.getNotes()) ) )
 && ( (this.getBalance()==castOther.getBalance()) || ( this.getBalance()!=null && castOther.getBalance()!=null && this.getBalance().equals(castOther.getBalance()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
 && ( (this.getCreaterNo()==castOther.getCreaterNo()) || ( this.getCreaterNo()!=null && castOther.getCreaterNo()!=null && this.getCreaterNo().equals(castOther.getCreaterNo()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getMoneyType()==castOther.getMoneyType()) || ( this.getMoneyType()!=null && castOther.getMoneyType()!=null && this.getMoneyType().equals(castOther.getMoneyType()) ) )
 && ( (this.getUniqueCode()==castOther.getUniqueCode()) || ( this.getUniqueCode()!=null && castOther.getUniqueCode()!=null && this.getUniqueCode().equals(castOther.getUniqueCode()) ) )
 && ( (this.getDepositType()==castOther.getDepositType()) || ( this.getDepositType()!=null && castOther.getDepositType()!=null && this.getDepositType().equals(castOther.getDepositType()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getJournalId() == null ? 0 : this.getJournalId().hashCode() );

         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getDealType() == null ? 0 : this.getDealType().hashCode() );
         result = 37 * result + ( getMoney() == null ? 0 : this.getMoney().hashCode() );
         result = 37 * result + ( getNotes() == null ? 0 : this.getNotes().hashCode() );
         result = 37 * result + ( getBalance() == null ? 0 : this.getBalance().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         result = 37 * result + ( getCreaterNo() == null ? 0 : this.getCreaterNo().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getMoneyType() == null ? 0 : this.getMoneyType().hashCode() );
         result = 37 * result + ( getUniqueCode() == null ? 0 : this.getUniqueCode().hashCode() );
         result = 37 * result + ( getDepositType() == null ? 0 : this.getDepositType().hashCode() );
         return result;
   }   





}
