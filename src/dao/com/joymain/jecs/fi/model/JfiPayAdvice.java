package com.joymain.jecs.fi.model;
// Generated 2009-9-14 16:19:37 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;

import com.joymain.jecs.sys.model.SysUser;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JFI_PAY_ADVICE"
 *     
 */

public class JfiPayAdvice extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private String adviceCode;
    private String companyCode;
    private SysUser sysUser;

	private String accountCode;
    private Date payDate;
    private BigDecimal payMoney;
    private String notice;
    private String dealType;
    private String telephone;
    private Integer status;
    private String checkerCode;
    private String checkerName;
    private Date checkTime;
    private String remark;
    private Date createTime;
    private String traceLog;
    private BigDecimal arrivedMoney;
    private String createrCode;
    private String createrName;


    // Constructors

    /** default constructor */
    public JfiPayAdvice() {
    }

	/** minimal constructor */
    public JfiPayAdvice(String companyCode, String userCode) {
        this.companyCode = companyCode;
        //this.userCode = userCode;
    }
    
    /** full constructor */
    public JfiPayAdvice(String companyCode, String userCode, String accountCode, Date payDate, BigDecimal payMoney, String notice, String dealType, String telephone, Integer status, String checkerCode, String checkerName, Date checkTime, String remark, Date createTime, String traceLog, BigDecimal arrivedMoney, String createrCode, String createrName) {
        this.companyCode = companyCode;
        //this.userCode = userCode;
        this.accountCode = accountCode;
        this.payDate = payDate;
        this.payMoney = payMoney;
        this.notice = notice;
        this.dealType = dealType;
        this.telephone = telephone;
        this.status = status;
        this.checkerCode = checkerCode;
        this.checkerName = checkerName;
        this.checkTime = checkTime;
        this.remark = remark;
        this.createTime = createTime;
        this.traceLog = traceLog;
        this.arrivedMoney = arrivedMoney;
        this.createrCode = createrCode;
        this.createrName = createrName;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.String"
     *             column="ADVICE_CODE"
     *         
     */

    public String getAdviceCode() {
        return this.adviceCode;
    }
    
    public void setAdviceCode(String adviceCode) {
        this.adviceCode = adviceCode;
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
     *             column="ACCOUNT_CODE"
     *             length="30"
     *         
     */

    public String getAccountCode() {
        return this.accountCode;
    }

	/**
	 * @spring.validator type="required"
	 */
    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="PAY_DATE"
     *             length="7"
     *         
     */

    public Date getPayDate() {
        return this.payDate;
    }

	/**
	 * @spring.validator type="required"
	 */
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }
    /**       
     *      *            @hibernate.property
     *             column="PAY_MONEY"
     *             length="18"
     *         
     */

    public BigDecimal getPayMoney() {
        return this.payMoney;
    }

	/**
	 * @spring.validator type="required"
	 */
    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }
    /**       
     *      *            @hibernate.property
     *             column="NOTICE"
     *             length="4000"
     *         
     */

    public String getNotice() {
        return this.notice;
    }

	/**
	 * @spring.validator type="required"
	 */
    public void setNotice(String notice) {
        this.notice = notice;
    }
    /**       
     *      *            @hibernate.property
     *             column="DEAL_TYPE"
     *             length="100"
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
     *             column="TELEPHONE"
     *             length="250"
     *         
     */

    public String getTelephone() {
        return this.telephone;
    }

	/**
	 * @spring.validator type="required"
	 */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
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
     *             column="CHECK_TIME"
     *             length="7"
     *         
     */

    public Date getCheckTime() {
        return this.checkTime;
    }
    
    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
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
     *             column="TRACE_LOG"
     *             length="4000"
     *         
     */

    public String getTraceLog() {
        return this.traceLog;
    }
    
    public void setTraceLog(String traceLog) {
        this.traceLog = traceLog;
    }
    /**       
     *      *            @hibernate.property
     *             column="ARRIVED_MONEY"
     *             length="18"
     *         
     */

    public BigDecimal getArrivedMoney() {
        return this.arrivedMoney;
    }
    
    public void setArrivedMoney(BigDecimal arrivedMoney) {
        this.arrivedMoney = arrivedMoney;
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
     *             length="300"
     *         
     */

    public String getCreaterName() {
        return this.createrName;
    }
    
    public void setCreaterName(String createrName) {
        this.createrName = createrName;
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
      buffer.append("accountCode").append("='").append(getAccountCode()).append("' ");			
      buffer.append("payDate").append("='").append(getPayDate()).append("' ");			
      buffer.append("payMoney").append("='").append(getPayMoney()).append("' ");			
      buffer.append("notice").append("='").append(getNotice()).append("' ");			
      buffer.append("dealType").append("='").append(getDealType()).append("' ");			
      buffer.append("telephone").append("='").append(getTelephone()).append("' ");			
      buffer.append("status").append("='").append(getStatus()).append("' ");			
      buffer.append("checkerCode").append("='").append(getCheckerCode()).append("' ");			
      buffer.append("checkerName").append("='").append(getCheckerName()).append("' ");			
      buffer.append("checkTime").append("='").append(getCheckTime()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("traceLog").append("='").append(getTraceLog()).append("' ");			
      buffer.append("arrivedMoney").append("='").append(getArrivedMoney()).append("' ");			
      buffer.append("createrCode").append("='").append(getCreaterCode()).append("' ");			
      buffer.append("createrName").append("='").append(getCreaterName()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JfiPayAdvice) ) return false;
		 JfiPayAdvice castOther = ( JfiPayAdvice ) other; 
         
		 return ( (this.getAdviceCode()==castOther.getAdviceCode()) || ( this.getAdviceCode()!=null && castOther.getAdviceCode()!=null && this.getAdviceCode().equals(castOther.getAdviceCode()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
// && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getAccountCode()==castOther.getAccountCode()) || ( this.getAccountCode()!=null && castOther.getAccountCode()!=null && this.getAccountCode().equals(castOther.getAccountCode()) ) )
 && ( (this.getPayDate()==castOther.getPayDate()) || ( this.getPayDate()!=null && castOther.getPayDate()!=null && this.getPayDate().equals(castOther.getPayDate()) ) )
 && ( (this.getPayMoney()==castOther.getPayMoney()) || ( this.getPayMoney()!=null && castOther.getPayMoney()!=null && this.getPayMoney().equals(castOther.getPayMoney()) ) )
 && ( (this.getNotice()==castOther.getNotice()) || ( this.getNotice()!=null && castOther.getNotice()!=null && this.getNotice().equals(castOther.getNotice()) ) )
 && ( (this.getDealType()==castOther.getDealType()) || ( this.getDealType()!=null && castOther.getDealType()!=null && this.getDealType().equals(castOther.getDealType()) ) )
 && ( (this.getTelephone()==castOther.getTelephone()) || ( this.getTelephone()!=null && castOther.getTelephone()!=null && this.getTelephone().equals(castOther.getTelephone()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
 && ( (this.getCheckerCode()==castOther.getCheckerCode()) || ( this.getCheckerCode()!=null && castOther.getCheckerCode()!=null && this.getCheckerCode().equals(castOther.getCheckerCode()) ) )
 && ( (this.getCheckerName()==castOther.getCheckerName()) || ( this.getCheckerName()!=null && castOther.getCheckerName()!=null && this.getCheckerName().equals(castOther.getCheckerName()) ) )
 && ( (this.getCheckTime()==castOther.getCheckTime()) || ( this.getCheckTime()!=null && castOther.getCheckTime()!=null && this.getCheckTime().equals(castOther.getCheckTime()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getTraceLog()==castOther.getTraceLog()) || ( this.getTraceLog()!=null && castOther.getTraceLog()!=null && this.getTraceLog().equals(castOther.getTraceLog()) ) )
 && ( (this.getArrivedMoney()==castOther.getArrivedMoney()) || ( this.getArrivedMoney()!=null && castOther.getArrivedMoney()!=null && this.getArrivedMoney().equals(castOther.getArrivedMoney()) ) )
 && ( (this.getCreaterCode()==castOther.getCreaterCode()) || ( this.getCreaterCode()!=null && castOther.getCreaterCode()!=null && this.getCreaterCode().equals(castOther.getCreaterCode()) ) )
 && ( (this.getCreaterName()==castOther.getCreaterName()) || ( this.getCreaterName()!=null && castOther.getCreaterName()!=null && this.getCreaterName().equals(castOther.getCreaterName()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getAdviceCode() == null ? 0 : this.getAdviceCode().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
//         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getAccountCode() == null ? 0 : this.getAccountCode().hashCode() );
         result = 37 * result + ( getPayDate() == null ? 0 : this.getPayDate().hashCode() );
         result = 37 * result + ( getPayMoney() == null ? 0 : this.getPayMoney().hashCode() );
         result = 37 * result + ( getNotice() == null ? 0 : this.getNotice().hashCode() );
         result = 37 * result + ( getDealType() == null ? 0 : this.getDealType().hashCode() );
         result = 37 * result + ( getTelephone() == null ? 0 : this.getTelephone().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         result = 37 * result + ( getCheckerCode() == null ? 0 : this.getCheckerCode().hashCode() );
         result = 37 * result + ( getCheckerName() == null ? 0 : this.getCheckerName().hashCode() );
         result = 37 * result + ( getCheckTime() == null ? 0 : this.getCheckTime().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getTraceLog() == null ? 0 : this.getTraceLog().hashCode() );
         result = 37 * result + ( getArrivedMoney() == null ? 0 : this.getArrivedMoney().hashCode() );
         result = 37 * result + ( getCreaterCode() == null ? 0 : this.getCreaterCode().hashCode() );
         result = 37 * result + ( getCreaterName() == null ? 0 : this.getCreaterName().hashCode() );
         return result;
   }   





}
