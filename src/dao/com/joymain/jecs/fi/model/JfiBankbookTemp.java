package com.joymain.jecs.fi.model;
// Generated 2009-9-14 16:19:02 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;

import com.joymain.jecs.sys.model.SysUser;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JFI_BANKBOOK_TEMP"
 *     
 */
@SuppressWarnings({ "unused", "serial" })
public class JfiBankbookTemp extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long tempId;
	private String companyCode;
    private SysUser sysUser;
    private Long serial;
    private String dealType;
    private Integer moneyType;
    private BigDecimal money;
    private String notes;
    private Integer status;
    private String createrCode;
    private String createrName;
    private Date createTime;
    private String lastUpdaterCode;
    private String lastUpdaterName;
    private Date lastUpdateTime;
    private String checkerCode;
    private String checkerName;
    private Date checkeTime;
    private Integer checkType;
    private Date dealDate;
    private String checkMsg;
    private String createNo;
    private String billpospayNum;

	private BigDecimal addMoney;
    private BigDecimal decMoney;

    private String description;
    // Constructors

    /** default constructor */
    public JfiBankbookTemp() {
    }

	/** minimal constructor */
    public JfiBankbookTemp(String companyCode, String userCode) {
        this.companyCode = companyCode;
        //this.userCode = userCode;
    }
    
    /** full constructor */
    public JfiBankbookTemp(String companyCode, String userCode, Long serial, String dealType, Integer moneyType, BigDecimal money, String notes, Integer status, String createrCode, String createrName, Date createTime, String lastUpdaterCode, String lastUpdaterName, Date lastUpdateTime, String checkerCode, String checkerName, Date checkeTime, Integer checkType, Date dealDate, String checkMsg,String billpospayNum) {
        this.companyCode = companyCode;
        //this.userCode = userCode;
        this.serial = serial;
        this.dealType = dealType;
        this.moneyType = moneyType;
        this.money = money;
        this.notes = notes;
        this.status = status;
        this.createrCode = createrCode;
        this.createrName = createrName;
        this.createTime = createTime;
        this.lastUpdaterCode = lastUpdaterCode;
        this.lastUpdaterName = lastUpdaterName;
        this.lastUpdateTime = lastUpdateTime;
        this.checkerCode = checkerCode;
        this.checkerName = checkerName;
        this.checkeTime = checkeTime;
        this.checkType = checkType;
        this.dealDate = dealDate;
        this.checkMsg = checkMsg;
        this.billpospayNum=billpospayNum;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="TEMP_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_FI"
     *         
     */

    public Long getTempId() {
        return this.tempId;
    }
    
    public void setTempId(Long tempId) {
        this.tempId = tempId;
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
     *             column="MONEY_TYPE"
     *             length="4"
     *         
     */

    public Integer getMoneyType() {
        return this.moneyType;
    }
    /**
	 * @spring.validator type="required"
	 */
    public void setMoneyType(Integer moneyType) {
        this.moneyType = moneyType;
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
     *             column="STATUS"
     *             length="2"
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
     *             column="LAST_UPDATER_CODE"
     *             length="20"
     *         
     */

    public String getLastUpdaterCode() {
        return this.lastUpdaterCode;
    }
    
    public void setLastUpdaterCode(String lastUpdaterCode) {
        this.lastUpdaterCode = lastUpdaterCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="LAST_UPDATER_NAME"
     *             length="30"
     *         
     */

    public String getLastUpdaterName() {
        return this.lastUpdaterName;
    }
    
    public void setLastUpdaterName(String lastUpdaterName) {
        this.lastUpdaterName = lastUpdaterName;
    }
    /**       
     *      *            @hibernate.property
     *             column="LAST_UPDATE_TIME"
     *             length="7"
     *         
     */

    public Date getLastUpdateTime() {
        return this.lastUpdateTime;
    }
    
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="CHECKER_CODE"
     *             length="20"
     *         
     */

    public String getCheckerCode() {
        return this.checkerCode;
    }
    
    public void setCheckerCode(String checkerCode) {
        this.checkerCode = checkerCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="CHECKER_NAME"
     *             length="30"
     *         
     */

    public String getCheckerName() {
        return this.checkerName;
    }
    
    public void setCheckerName(String checkerName) {
        this.checkerName = checkerName;
    }
    /**       
     *      *            @hibernate.property
     *             column="CHECKE_TIME"
     *             length="20"
     *         
     */

    public Date getCheckeTime() {
        return this.checkeTime;
    }
    
    public void setCheckeTime(Date checkeTime) {
        this.checkeTime = checkeTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="CHECK_TYPE"
     *             length="2"
     *         
     */

    public Integer getCheckType() {
        return this.checkType;
    }
    
    public void setCheckType(Integer checkType) {
        this.checkType = checkType;
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
     *             column="CHECK_MSG"
     *             length="30"
     *         
     */

    public String getCheckMsg() {
        return this.checkMsg;
    }
    
    public void setCheckMsg(String checkMsg) {
        this.checkMsg = checkMsg;
    }

    /**       
     *      *            @hibernate.property
     *             column="CREATE_NO"
     *             length="30"
     *         
     */
    public String getCreateNo() {
		return createNo;
	}

	public void setCreateNo(String createNo) {
		this.createNo = createNo;
	}
    
	/**       
     *      *            @hibernate.property
     *             column="BILLPOSPAY_NUM"
     *             length="30"
     *         
     */
    public String getBillpospayNum() {
		return billpospayNum;
	}

	public void setBillpospayNum(String billpospayNum) {
		this.billpospayNum = billpospayNum;
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
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
//      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("serial").append("='").append(getSerial()).append("' ");			
      buffer.append("dealType").append("='").append(getDealType()).append("' ");			
      buffer.append("moneyType").append("='").append(getMoneyType()).append("' ");			
      buffer.append("money").append("='").append(getMoney()).append("' ");			
      buffer.append("notes").append("='").append(getNotes()).append("' ");			
      buffer.append("status").append("='").append(getStatus()).append("' ");			
      buffer.append("createrCode").append("='").append(getCreaterCode()).append("' ");			
      buffer.append("createrName").append("='").append(getCreaterName()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("lastUpdaterCode").append("='").append(getLastUpdaterCode()).append("' ");			
      buffer.append("lastUpdaterName").append("='").append(getLastUpdaterName()).append("' ");			
      buffer.append("lastUpdateTime").append("='").append(getLastUpdateTime()).append("' ");			
      buffer.append("checkerCode").append("='").append(getCheckerCode()).append("' ");			
      buffer.append("checkerName").append("='").append(getCheckerName()).append("' ");			
      buffer.append("checkeTime").append("='").append(getCheckeTime()).append("' ");			
      buffer.append("checkType").append("='").append(getCheckType()).append("' ");			
      buffer.append("dealDate").append("='").append(getDealDate()).append("' ");			
      buffer.append("checkMsg").append("='").append(getCheckMsg()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JfiBankbookTemp) ) return false;
		 JfiBankbookTemp castOther = ( JfiBankbookTemp ) other; 
         
		 return ( (this.getTempId()==castOther.getTempId()) || ( this.getTempId()!=null && castOther.getTempId()!=null && this.getTempId().equals(castOther.getTempId()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
// && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getSerial()==castOther.getSerial()) || ( this.getSerial()!=null && castOther.getSerial()!=null && this.getSerial().equals(castOther.getSerial()) ) )
 && ( (this.getDealType()==castOther.getDealType()) || ( this.getDealType()!=null && castOther.getDealType()!=null && this.getDealType().equals(castOther.getDealType()) ) )
 && ( (this.getMoneyType()==castOther.getMoneyType()) || ( this.getMoneyType()!=null && castOther.getMoneyType()!=null && this.getMoneyType().equals(castOther.getMoneyType()) ) )
 && ( (this.getMoney()==castOther.getMoney()) || ( this.getMoney()!=null && castOther.getMoney()!=null && this.getMoney().equals(castOther.getMoney()) ) )
 && ( (this.getNotes()==castOther.getNotes()) || ( this.getNotes()!=null && castOther.getNotes()!=null && this.getNotes().equals(castOther.getNotes()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
 && ( (this.getCreaterCode()==castOther.getCreaterCode()) || ( this.getCreaterCode()!=null && castOther.getCreaterCode()!=null && this.getCreaterCode().equals(castOther.getCreaterCode()) ) )
 && ( (this.getCreaterName()==castOther.getCreaterName()) || ( this.getCreaterName()!=null && castOther.getCreaterName()!=null && this.getCreaterName().equals(castOther.getCreaterName()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getLastUpdaterCode()==castOther.getLastUpdaterCode()) || ( this.getLastUpdaterCode()!=null && castOther.getLastUpdaterCode()!=null && this.getLastUpdaterCode().equals(castOther.getLastUpdaterCode()) ) )
 && ( (this.getLastUpdaterName()==castOther.getLastUpdaterName()) || ( this.getLastUpdaterName()!=null && castOther.getLastUpdaterName()!=null && this.getLastUpdaterName().equals(castOther.getLastUpdaterName()) ) )
 && ( (this.getLastUpdateTime()==castOther.getLastUpdateTime()) || ( this.getLastUpdateTime()!=null && castOther.getLastUpdateTime()!=null && this.getLastUpdateTime().equals(castOther.getLastUpdateTime()) ) )
 && ( (this.getCheckerCode()==castOther.getCheckerCode()) || ( this.getCheckerCode()!=null && castOther.getCheckerCode()!=null && this.getCheckerCode().equals(castOther.getCheckerCode()) ) )
 && ( (this.getCheckerName()==castOther.getCheckerName()) || ( this.getCheckerName()!=null && castOther.getCheckerName()!=null && this.getCheckerName().equals(castOther.getCheckerName()) ) )
 && ( (this.getCheckeTime()==castOther.getCheckeTime()) || ( this.getCheckeTime()!=null && castOther.getCheckeTime()!=null && this.getCheckeTime().equals(castOther.getCheckeTime()) ) )
 && ( (this.getCheckType()==castOther.getCheckType()) || ( this.getCheckType()!=null && castOther.getCheckType()!=null && this.getCheckType().equals(castOther.getCheckType()) ) )
 && ( (this.getDealDate()==castOther.getDealDate()) || ( this.getDealDate()!=null && castOther.getDealDate()!=null && this.getDealDate().equals(castOther.getDealDate()) ) )
 && ( (this.getCheckMsg()==castOther.getCheckMsg()) || ( this.getCheckMsg()!=null && castOther.getCheckMsg()!=null && this.getCheckMsg().equals(castOther.getCheckMsg()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getTempId() == null ? 0 : this.getTempId().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
//         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getSerial() == null ? 0 : this.getSerial().hashCode() );
         result = 37 * result + ( getDealType() == null ? 0 : this.getDealType().hashCode() );
         result = 37 * result + ( getMoneyType() == null ? 0 : this.getMoneyType().hashCode() );
         result = 37 * result + ( getMoney() == null ? 0 : this.getMoney().hashCode() );
         result = 37 * result + ( getNotes() == null ? 0 : this.getNotes().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         result = 37 * result + ( getCreaterCode() == null ? 0 : this.getCreaterCode().hashCode() );
         result = 37 * result + ( getCreaterName() == null ? 0 : this.getCreaterName().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getLastUpdaterCode() == null ? 0 : this.getLastUpdaterCode().hashCode() );
         result = 37 * result + ( getLastUpdaterName() == null ? 0 : this.getLastUpdaterName().hashCode() );
         result = 37 * result + ( getLastUpdateTime() == null ? 0 : this.getLastUpdateTime().hashCode() );
         result = 37 * result + ( getCheckerCode() == null ? 0 : this.getCheckerCode().hashCode() );
         result = 37 * result + ( getCheckerName() == null ? 0 : this.getCheckerName().hashCode() );
         result = 37 * result + ( getCheckeTime() == null ? 0 : this.getCheckeTime().hashCode() );
         result = 37 * result + ( getCheckType() == null ? 0 : this.getCheckType().hashCode() );
         result = 37 * result + ( getDealDate() == null ? 0 : this.getDealDate().hashCode() );
         result = 37 * result + ( getCheckMsg() == null ? 0 : this.getCheckMsg().hashCode() );
         return result;
   }   





}
