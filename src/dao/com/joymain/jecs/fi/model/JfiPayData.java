package com.joymain.jecs.fi.model;
// Generated 2009-9-14 16:20:44 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;

import com.joymain.jecs.sys.model.SysUser;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JFI_PAY_DATA"
 *     
 */

public class JfiPayData extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long dataId;
    private String companyCode;
    private String accountCode;
    private Date dealDate;
    private BigDecimal incomeMoney;
    private String excerpt;
    private String adviceCode;
    private String checkerCode;
    private String checkerName;
    private Date checkTime;
    private String createNo;
    private SysUser sysUser;

	private Integer status;
    private String remark;
    private String traceLog;
    private String createrCode;
    private String createrName;
    private Date createTime;


    // Constructors

    /** default constructor */
    public JfiPayData() {
    }

	/** minimal constructor */
    public JfiPayData(String companyCode) {
        this.companyCode = companyCode;
    }
    
    /** full constructor */
    public JfiPayData(String companyCode, String accountCode, Date dealDate, BigDecimal incomeMoney, String excerpt, String adviceCode, String checkerCode, String checkerName, Date checkTime, String createNo, String userCode, Integer status, String remark, String traceLog, String createrCode, String createrName, Date createTime) {
        this.companyCode = companyCode;
        this.accountCode = accountCode;
        this.dealDate = dealDate;
        this.incomeMoney = incomeMoney;
        this.excerpt = excerpt;
        this.adviceCode = adviceCode;
        this.checkerCode = checkerCode;
        this.checkerName = checkerName;
        this.checkTime = checkTime;
        this.createNo = createNo;
        //this.userCode = userCode;
        this.status = status;
        this.remark = remark;
        this.traceLog = traceLog;
        this.createrCode = createrCode;
        this.createrName = createrName;
        this.createTime = createTime;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="DATA_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_FI"
     *         
     */

    public Long getDataId() {
        return this.dataId;
    }
    
    public void setDataId(Long dataId) {
        this.dataId = dataId;
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
     *             column="ACCOUNT_CODE"
     *             length="30"
     *         
     */

    public String getAccountCode() {
        return this.accountCode;
    }
    
    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
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
     *             column="INCOME_MONEY"
     *             length="18"
     *         
     */

    public BigDecimal getIncomeMoney() {
        return this.incomeMoney;
    }
    
    public void setIncomeMoney(BigDecimal incomeMoney) {
        this.incomeMoney = incomeMoney;
    }
    /**       
     *      *            @hibernate.property
     *             column="EXCERPT"
     *             length="4000"
     *         
     */

    public String getExcerpt() {
        return this.excerpt;
    }
    
    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }
    /**       
     *      *            @hibernate.property
     *             column="ADVICE_CODE"
     *             length="30"
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
     *             column="CREATE_NO"
     *             length="30"
     *         
     */

    public String getCreateNo() {
        return this.createNo;
    }
    
    public void setCreateNo(String createNo) {
        this.createNo = createNo;
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
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("accountCode").append("='").append(getAccountCode()).append("' ");			
      buffer.append("dealDate").append("='").append(getDealDate()).append("' ");			
      buffer.append("incomeMoney").append("='").append(getIncomeMoney()).append("' ");			
      buffer.append("excerpt").append("='").append(getExcerpt()).append("' ");			
      buffer.append("adviceCode").append("='").append(getAdviceCode()).append("' ");			
      buffer.append("checkerCode").append("='").append(getCheckerCode()).append("' ");			
      buffer.append("checkerName").append("='").append(getCheckerName()).append("' ");			
      buffer.append("checkTime").append("='").append(getCheckTime()).append("' ");			
      buffer.append("createNo").append("='").append(getCreateNo()).append("' ");			
//      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("status").append("='").append(getStatus()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");			
      buffer.append("traceLog").append("='").append(getTraceLog()).append("' ");			
      buffer.append("createrCode").append("='").append(getCreaterCode()).append("' ");			
      buffer.append("createrName").append("='").append(getCreaterName()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JfiPayData) ) return false;
		 JfiPayData castOther = ( JfiPayData ) other; 
         
		 return ( (this.getDataId()==castOther.getDataId()) || ( this.getDataId()!=null && castOther.getDataId()!=null && this.getDataId().equals(castOther.getDataId()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getAccountCode()==castOther.getAccountCode()) || ( this.getAccountCode()!=null && castOther.getAccountCode()!=null && this.getAccountCode().equals(castOther.getAccountCode()) ) )
 && ( (this.getDealDate()==castOther.getDealDate()) || ( this.getDealDate()!=null && castOther.getDealDate()!=null && this.getDealDate().equals(castOther.getDealDate()) ) )
 && ( (this.getIncomeMoney()==castOther.getIncomeMoney()) || ( this.getIncomeMoney()!=null && castOther.getIncomeMoney()!=null && this.getIncomeMoney().equals(castOther.getIncomeMoney()) ) )
 && ( (this.getExcerpt()==castOther.getExcerpt()) || ( this.getExcerpt()!=null && castOther.getExcerpt()!=null && this.getExcerpt().equals(castOther.getExcerpt()) ) )
 && ( (this.getAdviceCode()==castOther.getAdviceCode()) || ( this.getAdviceCode()!=null && castOther.getAdviceCode()!=null && this.getAdviceCode().equals(castOther.getAdviceCode()) ) )
 && ( (this.getCheckerCode()==castOther.getCheckerCode()) || ( this.getCheckerCode()!=null && castOther.getCheckerCode()!=null && this.getCheckerCode().equals(castOther.getCheckerCode()) ) )
 && ( (this.getCheckerName()==castOther.getCheckerName()) || ( this.getCheckerName()!=null && castOther.getCheckerName()!=null && this.getCheckerName().equals(castOther.getCheckerName()) ) )
 && ( (this.getCheckTime()==castOther.getCheckTime()) || ( this.getCheckTime()!=null && castOther.getCheckTime()!=null && this.getCheckTime().equals(castOther.getCheckTime()) ) )
 && ( (this.getCreateNo()==castOther.getCreateNo()) || ( this.getCreateNo()!=null && castOther.getCreateNo()!=null && this.getCreateNo().equals(castOther.getCreateNo()) ) )
// && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
 && ( (this.getTraceLog()==castOther.getTraceLog()) || ( this.getTraceLog()!=null && castOther.getTraceLog()!=null && this.getTraceLog().equals(castOther.getTraceLog()) ) )
 && ( (this.getCreaterCode()==castOther.getCreaterCode()) || ( this.getCreaterCode()!=null && castOther.getCreaterCode()!=null && this.getCreaterCode().equals(castOther.getCreaterCode()) ) )
 && ( (this.getCreaterName()==castOther.getCreaterName()) || ( this.getCreaterName()!=null && castOther.getCreaterName()!=null && this.getCreaterName().equals(castOther.getCreaterName()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getDataId() == null ? 0 : this.getDataId().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getAccountCode() == null ? 0 : this.getAccountCode().hashCode() );
         result = 37 * result + ( getDealDate() == null ? 0 : this.getDealDate().hashCode() );
         result = 37 * result + ( getIncomeMoney() == null ? 0 : this.getIncomeMoney().hashCode() );
         result = 37 * result + ( getExcerpt() == null ? 0 : this.getExcerpt().hashCode() );
         result = 37 * result + ( getAdviceCode() == null ? 0 : this.getAdviceCode().hashCode() );
         result = 37 * result + ( getCheckerCode() == null ? 0 : this.getCheckerCode().hashCode() );
         result = 37 * result + ( getCheckerName() == null ? 0 : this.getCheckerName().hashCode() );
         result = 37 * result + ( getCheckTime() == null ? 0 : this.getCheckTime().hashCode() );
         result = 37 * result + ( getCreateNo() == null ? 0 : this.getCreateNo().hashCode() );
//         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         result = 37 * result + ( getTraceLog() == null ? 0 : this.getTraceLog().hashCode() );
         result = 37 * result + ( getCreaterCode() == null ? 0 : this.getCreaterCode().hashCode() );
         result = 37 * result + ( getCreaterName() == null ? 0 : this.getCreaterName().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         return result;
   }   





}
