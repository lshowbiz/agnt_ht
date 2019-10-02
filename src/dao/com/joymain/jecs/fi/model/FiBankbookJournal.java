package com.joymain.jecs.fi.model;
// Generated 2011-8-18 10:15:23 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;

import com.joymain.jecs.sys.model.SysUser;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="FI_BANKBOOK_JOURNAL"
 *     
 */
@SuppressWarnings({"serial","unused"})
public class FiBankbookJournal extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long journalId;
    private String companyCode;
    private SysUser sysUser;
    private FiBankbookTemp fiBankbookTemp;
    
    private Long serial;
    private String dealType;
    private Date dealDate;
    private BigDecimal money;
    private String notes;
    private BigDecimal balance;
    private String remark;
    private String createrCode;
    private String createrName;
    private Date createTime;
    private Integer moneyType;
    private BigDecimal addMoney;
    private BigDecimal decMoney;
    private String uniqueCode;
    private String bankbookType;
    private Long lastJournalId;
    private BigDecimal lastMoney;
    private String dataType;
    private String description;

    // Constructors

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
	
    /** default constructor */
    public FiBankbookJournal() {
    }

	/** minimal constructor */
    public FiBankbookJournal(String companyCode, String userCode, String bankbookType) {
        this.companyCode = companyCode;
        //this.userCode = userCode;
        this.bankbookType = bankbookType;
    }
    
    /** full constructor */
    public FiBankbookJournal(Long tempId, String companyCode, String userCode, Long serial, String dealType, Date dealDate, BigDecimal money, String notes, BigDecimal balance, String remark, String createrCode, String createrName, Date createTime, Integer moneyType, String uniqueCode, String bankbookType, Long lastJournalId, BigDecimal lastMoney,String dataType) {
        //this.tempId = tempId;
        this.companyCode = companyCode;
        //this.userCode = userCode;
        this.serial = serial;
        this.dealType = dealType;
        this.dealDate = dealDate;
        this.money = money;
        this.notes = notes;
        this.balance = balance;
        this.remark = remark;
        this.createrCode = createrCode;
        this.createrName = createrName;
        this.createTime = createTime;
        this.moneyType = moneyType;
        this.uniqueCode = uniqueCode;
        this.bankbookType = bankbookType;
        this.lastJournalId = lastJournalId;
        this.lastMoney = lastMoney;
        this.dataType=dataType;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="JOURNAL_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_BANKBOOK"
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
     *             column="DESCRIPTION"
     *             length="4000"
     *         
     */
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
    /**       
     *      *            @hibernate.property
     *             column="COMPANY_CODE"
     *             length="2"
     *             not-null="true"
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
     *             column="SERIAL"
     *             length="8"
     *         
     */

    public Long getSerial() {
        return this.serial;
    }
    
    public void setSerial(Long serial) {
        this.serial = serial;
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
     *             column="DEAL_DATE"
     *             length="7"
     *         
     */

    public Date getDealDate() {
        return this.dealDate;
    }
    
    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
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
     *             column="CREATER_CODE"
     *             length="200"
     *         
     */

    public String getCreaterCode() {
        return this.createrCode;
    }
    
    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="CREATER_NAME"
     *             length="500"
     *         
     */

    public String getCreaterName() {
        return this.createrName;
    }
    
    public void setCreaterName(String createrName) {
        this.createrName = createrName;
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
     *             column="BANKBOOK_TYPE"
     *             length="10"
     *             not-null="true"
     *         
     */

    public String getBankbookType() {
        return this.bankbookType;
    }
    
    public void setBankbookType(String bankbookType) {
        this.bankbookType = bankbookType;
    }

    /**
     * *
     * @hibernate.many-to-one not-null="true"
     * @hibernate.column name="TEMP_ID"
     * 
     */
    public FiBankbookTemp getFiBankbookTemp() {
    	return fiBankbookTemp;
    }

    public void setFiBankbookTemp(FiBankbookTemp fiBankbookTemp) {
    	this.fiBankbookTemp = fiBankbookTemp;
    }

    /**
     * *
     * @hibernate.many-to-one not-null="true"
     * @hibernate.column name="USER_CODE"
     * 
     */
    public SysUser getSysUser() {
    	return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
    	this.sysUser = sysUser;
    }   
    /**       
     *      *            @hibernate.property
     *             column="LAST_JOURNAL_ID"
     *             length="12"
     *         
     */

    public Long getLastJournalId() {
        return this.lastJournalId;
    }
    
    public void setLastJournalId(Long lastJournalId) {
        this.lastJournalId = lastJournalId;
    }
    /**       
     *      *            @hibernate.property
     *             column="LAST_MONEY"
     *             length="18"
     *         
     */

    public BigDecimal getLastMoney() {
        return this.lastMoney;
    }
    
    public void setLastMoney(BigDecimal lastMoney) {
        this.lastMoney = lastMoney;
    }
   
    /**       
     *      *            @hibernate.property
     *             column="DATA_TYPE"
     *             length="1"
     *         
     */
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      //buffer.append("tempId").append("='").append(getTempId()).append("' ");			
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      //buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("serial").append("='").append(getSerial()).append("' ");			
      buffer.append("dealType").append("='").append(getDealType()).append("' ");			
      buffer.append("dealDate").append("='").append(getDealDate()).append("' ");			
      buffer.append("money").append("='").append(getMoney()).append("' ");			
      buffer.append("notes").append("='").append(getNotes()).append("' ");			
      buffer.append("balance").append("='").append(getBalance()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");			
      buffer.append("createrCode").append("='").append(getCreaterCode()).append("' ");			
      buffer.append("createrName").append("='").append(getCreaterName()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("moneyType").append("='").append(getMoneyType()).append("' ");			
      buffer.append("uniqueCode").append("='").append(getUniqueCode()).append("' ");			
      buffer.append("bankbookType").append("='").append(getBankbookType()).append("' ");			
      buffer.append("lastJournalId").append("='").append(getLastJournalId()).append("' ");			
      buffer.append("lastMoney").append("='").append(getLastMoney()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof FiBankbookJournal) ) return false;
		 FiBankbookJournal castOther = ( FiBankbookJournal ) other; 
         
		 return ( (this.getJournalId()==castOther.getJournalId()) || ( this.getJournalId()!=null && castOther.getJournalId()!=null && this.getJournalId().equals(castOther.getJournalId()) ) )
// && ( (this.getTempId()==castOther.getTempId()) || ( this.getTempId()!=null && castOther.getTempId()!=null && this.getTempId().equals(castOther.getTempId()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
// && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getSerial()==castOther.getSerial()) || ( this.getSerial()!=null && castOther.getSerial()!=null && this.getSerial().equals(castOther.getSerial()) ) )
 && ( (this.getDealType()==castOther.getDealType()) || ( this.getDealType()!=null && castOther.getDealType()!=null && this.getDealType().equals(castOther.getDealType()) ) )
 && ( (this.getDealDate()==castOther.getDealDate()) || ( this.getDealDate()!=null && castOther.getDealDate()!=null && this.getDealDate().equals(castOther.getDealDate()) ) )
 && ( (this.getMoney()==castOther.getMoney()) || ( this.getMoney()!=null && castOther.getMoney()!=null && this.getMoney().equals(castOther.getMoney()) ) )
 && ( (this.getNotes()==castOther.getNotes()) || ( this.getNotes()!=null && castOther.getNotes()!=null && this.getNotes().equals(castOther.getNotes()) ) )
 && ( (this.getBalance()==castOther.getBalance()) || ( this.getBalance()!=null && castOther.getBalance()!=null && this.getBalance().equals(castOther.getBalance()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
 && ( (this.getCreaterCode()==castOther.getCreaterCode()) || ( this.getCreaterCode()!=null && castOther.getCreaterCode()!=null && this.getCreaterCode().equals(castOther.getCreaterCode()) ) )
 && ( (this.getCreaterName()==castOther.getCreaterName()) || ( this.getCreaterName()!=null && castOther.getCreaterName()!=null && this.getCreaterName().equals(castOther.getCreaterName()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getMoneyType()==castOther.getMoneyType()) || ( this.getMoneyType()!=null && castOther.getMoneyType()!=null && this.getMoneyType().equals(castOther.getMoneyType()) ) )
 && ( (this.getUniqueCode()==castOther.getUniqueCode()) || ( this.getUniqueCode()!=null && castOther.getUniqueCode()!=null && this.getUniqueCode().equals(castOther.getUniqueCode()) ) )
 && ( (this.getBankbookType()==castOther.getBankbookType()) || ( this.getBankbookType()!=null && castOther.getBankbookType()!=null && this.getBankbookType().equals(castOther.getBankbookType()) ) )
 && ( (this.getLastJournalId()==castOther.getLastJournalId()) || ( this.getLastJournalId()!=null && castOther.getLastJournalId()!=null && this.getLastJournalId().equals(castOther.getLastJournalId()) ) )
 && ( (this.getLastMoney()==castOther.getLastMoney()) || ( this.getLastMoney()!=null && castOther.getLastMoney()!=null && this.getLastMoney().equals(castOther.getLastMoney()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getJournalId() == null ? 0 : this.getJournalId().hashCode() );
//         result = 37 * result + ( getTempId() == null ? 0 : this.getTempId().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
//         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getSerial() == null ? 0 : this.getSerial().hashCode() );
         result = 37 * result + ( getDealType() == null ? 0 : this.getDealType().hashCode() );
         result = 37 * result + ( getDealDate() == null ? 0 : this.getDealDate().hashCode() );
         result = 37 * result + ( getMoney() == null ? 0 : this.getMoney().hashCode() );
         result = 37 * result + ( getNotes() == null ? 0 : this.getNotes().hashCode() );
         result = 37 * result + ( getBalance() == null ? 0 : this.getBalance().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         result = 37 * result + ( getCreaterCode() == null ? 0 : this.getCreaterCode().hashCode() );
         result = 37 * result + ( getCreaterName() == null ? 0 : this.getCreaterName().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getMoneyType() == null ? 0 : this.getMoneyType().hashCode() );
         result = 37 * result + ( getUniqueCode() == null ? 0 : this.getUniqueCode().hashCode() );
         result = 37 * result + ( getBankbookType() == null ? 0 : this.getBankbookType().hashCode() );
         result = 37 * result + ( getLastJournalId() == null ? 0 : this.getLastJournalId().hashCode() );
         result = 37 * result + ( getLastMoney() == null ? 0 : this.getLastMoney().hashCode() );
         return result;
   }   





}
