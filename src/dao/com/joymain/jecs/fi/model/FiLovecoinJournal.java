package com.joymain.jecs.fi.model;
// Generated 2011-5-16 15:33:05 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;

import com.joymain.jecs.sys.model.SysUser;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="FI_LOVECOIN_JOURNAL"
 *     
 */

public class FiLovecoinJournal extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long journalId;
    //private String userCode;
    private SysUser sysUser;
    private Integer serial;
    private String dealType;
    private Date dealDate;
    private BigDecimal coin;
    private String notes;
    private BigDecimal balance;
    private String remark;
    private String createrCode;
    private String createrName;
    private Date createTime;
    private Integer moneyType;
    private String uniqueCode;
    private Long appId;
    
    private BigDecimal addMoney;
    private BigDecimal decMoney;
    
    public BigDecimal getAddMoney() {
		if("A".equals(this.dealType)){
			return this.coin; 
		}else{
			return null;
		}
    }

	public void setAddMoney(BigDecimal addMoney) {
    	this.addMoney = addMoney;
    }

	public BigDecimal getDecMoney() {
		if("D".equals(this.dealType)){
			return this.coin; 
		}else{
			return null;
		}
    }

	public void setDecMoney(BigDecimal decMoney) {
    	this.decMoney = decMoney;
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


    // Constructors

    /** default constructor */
    public FiLovecoinJournal() {
    }

	/** minimal constructor */
//    public FiBcoinJournal(String userCode) {
//        this.userCode = userCode;
//    }
    
    /** full constructor */
    public FiLovecoinJournal(String userCode, Integer serial, String dealType, Date dealDate, BigDecimal coin, String notes, BigDecimal balance, String remark, String createrCode, String createrName, Date createTime, Integer moneyType, String uniqueCode, Long appId) {
//        this.userCode = userCode;
        this.serial = serial;
        this.dealType = dealType;
        this.dealDate = dealDate;
        this.coin = coin;
        this.notes = notes;
        this.balance = balance;
        this.remark = remark;
        this.createrCode = createrCode;
        this.createrName = createrName;
        this.createTime = createTime;
        this.moneyType = moneyType;
        this.uniqueCode = uniqueCode;
        this.appId = appId;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="JOURNAL_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_POINT"
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
     *             column="USER_CODE"
     *             length="20"
     *             not-null="true"
     *         
     */

//    public String getUserCode() {
//        return this.userCode;
//    }
//    
//    public void setUserCode(String userCode) {
//        this.userCode = userCode;
//    }
    /**       
     *      *            @hibernate.property
     *             column="SERIAL"
     *             length="8"
     *         
     */

    public Integer getSerial() {
        return this.serial;
    }
    
    public void setSerial(Integer serial) {
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
     *             column="COIN"
     *             length="18"
     *         
     */

    public BigDecimal getCoin() {
        return this.coin;
    }
    
    public void setCoin(BigDecimal coin) {
        this.coin = coin;
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
     *             length="20"
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
     *             length="30"
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
     *             column="APP_ID"
     *             length="12"
     *         
     */

    public Long getAppId() {
        return this.appId;
    }
    
    public void setAppId(Long appId) {
        this.appId = appId;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
//      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("serial").append("='").append(getSerial()).append("' ");			
      buffer.append("dealType").append("='").append(getDealType()).append("' ");			
      buffer.append("dealDate").append("='").append(getDealDate()).append("' ");			
      buffer.append("coin").append("='").append(getCoin()).append("' ");			
      buffer.append("notes").append("='").append(getNotes()).append("' ");			
      buffer.append("balance").append("='").append(getBalance()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");			
      buffer.append("createrCode").append("='").append(getCreaterCode()).append("' ");			
      buffer.append("createrName").append("='").append(getCreaterName()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("moneyType").append("='").append(getMoneyType()).append("' ");			
      buffer.append("uniqueCode").append("='").append(getUniqueCode()).append("' ");			
      buffer.append("appId").append("='").append(getAppId()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof FiLovecoinJournal) ) return false;
		 FiLovecoinJournal castOther = ( FiLovecoinJournal ) other; 
         
		 return ( (this.getJournalId()==castOther.getJournalId()) || ( this.getJournalId()!=null && castOther.getJournalId()!=null && this.getJournalId().equals(castOther.getJournalId()) ) )
// && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getSerial()==castOther.getSerial()) || ( this.getSerial()!=null && castOther.getSerial()!=null && this.getSerial().equals(castOther.getSerial()) ) )
 && ( (this.getDealType()==castOther.getDealType()) || ( this.getDealType()!=null && castOther.getDealType()!=null && this.getDealType().equals(castOther.getDealType()) ) )
 && ( (this.getDealDate()==castOther.getDealDate()) || ( this.getDealDate()!=null && castOther.getDealDate()!=null && this.getDealDate().equals(castOther.getDealDate()) ) )
 && ( (this.getCoin()==castOther.getCoin()) || ( this.getCoin()!=null && castOther.getCoin()!=null && this.getCoin().equals(castOther.getCoin()) ) )
 && ( (this.getNotes()==castOther.getNotes()) || ( this.getNotes()!=null && castOther.getNotes()!=null && this.getNotes().equals(castOther.getNotes()) ) )
 && ( (this.getBalance()==castOther.getBalance()) || ( this.getBalance()!=null && castOther.getBalance()!=null && this.getBalance().equals(castOther.getBalance()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
 && ( (this.getCreaterCode()==castOther.getCreaterCode()) || ( this.getCreaterCode()!=null && castOther.getCreaterCode()!=null && this.getCreaterCode().equals(castOther.getCreaterCode()) ) )
 && ( (this.getCreaterName()==castOther.getCreaterName()) || ( this.getCreaterName()!=null && castOther.getCreaterName()!=null && this.getCreaterName().equals(castOther.getCreaterName()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getMoneyType()==castOther.getMoneyType()) || ( this.getMoneyType()!=null && castOther.getMoneyType()!=null && this.getMoneyType().equals(castOther.getMoneyType()) ) )
 && ( (this.getUniqueCode()==castOther.getUniqueCode()) || ( this.getUniqueCode()!=null && castOther.getUniqueCode()!=null && this.getUniqueCode().equals(castOther.getUniqueCode()) ) )
 && ( (this.getAppId()==castOther.getAppId()) || ( this.getAppId()!=null && castOther.getAppId()!=null && this.getAppId().equals(castOther.getAppId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getJournalId() == null ? 0 : this.getJournalId().hashCode() );
//         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getSerial() == null ? 0 : this.getSerial().hashCode() );
         result = 37 * result + ( getDealType() == null ? 0 : this.getDealType().hashCode() );
         result = 37 * result + ( getDealDate() == null ? 0 : this.getDealDate().hashCode() );
         result = 37 * result + ( getCoin() == null ? 0 : this.getCoin().hashCode() );
         result = 37 * result + ( getNotes() == null ? 0 : this.getNotes().hashCode() );
         result = 37 * result + ( getBalance() == null ? 0 : this.getBalance().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         result = 37 * result + ( getCreaterCode() == null ? 0 : this.getCreaterCode().hashCode() );
         result = 37 * result + ( getCreaterName() == null ? 0 : this.getCreaterName().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getMoneyType() == null ? 0 : this.getMoneyType().hashCode() );
         result = 37 * result + ( getUniqueCode() == null ? 0 : this.getUniqueCode().hashCode() );
         result = 37 * result + ( getAppId() == null ? 0 : this.getAppId().hashCode() );
         return result;
   }   





}
