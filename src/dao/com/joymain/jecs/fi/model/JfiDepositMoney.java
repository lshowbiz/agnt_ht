package com.joymain.jecs.fi.model;
// Generated 2015-10-30 10:26:22 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JFI_DEPOSIT_MONEY"
 *     
 */

public class JfiDepositMoney extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private String userCode;
    private String userName;
    private Integer wyear;
    private Integer wweek;
    private BigDecimal sendMoney;
    private BigDecimal invoicePayable;
    private BigDecimal depositMoney;
    private String remark;
    private String createNo;
    private Date createTime;
    private String companyCode;


    // Constructors

    /** default constructor */
    public JfiDepositMoney() {
    }

    
    /** full constructor */
    public JfiDepositMoney(String userCode, String userName, Integer wyear, Integer wweek, BigDecimal sendMoney, BigDecimal invoicePayable, BigDecimal depositMoney, String remark, String createNo, Date createTime, String companyCode) {
        this.userCode = userCode;
        this.userName = userName;
        this.wyear = wyear;
        this.wweek = wweek;
        this.sendMoney = sendMoney;
        this.invoicePayable = invoicePayable;
        this.depositMoney = depositMoney;
        this.remark = remark;
        this.createNo = createNo;
        this.createTime = createTime;
        this.companyCode = companyCode;
    }
    

   
    // Property accessors

    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ID"
     *@hibernate.generator-param name="sequence" value="SEQ_FI"
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
     *             column="USER_NAME"
     *             length="200"
     *         
     */

    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**       
     *      *            @hibernate.property
     *             column="W_YEAR"
     *             length="22"
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
     *             column="W_WEEK"
     *             length="22"
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
     *             column="INVOICE_PAYABLE"
     *             length="22"
     *         
     */

    public BigDecimal getInvoicePayable() {
        return this.invoicePayable;
    }
    
    public void setInvoicePayable(BigDecimal invoicePayable) {
        this.invoicePayable = invoicePayable;
    }
    /**       
     *      *            @hibernate.property
     *             column="DEPOSIT_MONEY"
     *             length="22"
     *         
     */

    public BigDecimal getDepositMoney() {
        return this.depositMoney;
    }
    
    public void setDepositMoney(BigDecimal depositMoney) {
        this.depositMoney = depositMoney;
    }
    /**       
     *      *            @hibernate.property
     *             column="REMARK"
     *             length="2000"
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
     *             column="CREATE_NO"
     *             length="20"
     *         
     */

    public String getCreateNo() {
        return this.createNo;
    }
    
    public void setCreateNo(String createNo) {
        this.createNo = createNo;
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
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("userName").append("='").append(getUserName()).append("' ");			
      buffer.append("wyear").append("='").append(getWyear()).append("' ");			
      buffer.append("wweek").append("='").append(getWweek()).append("' ");			
      buffer.append("sendMoney").append("='").append(getSendMoney()).append("' ");			
      buffer.append("invoicePayable").append("='").append(getInvoicePayable()).append("' ");			
      buffer.append("depositMoney").append("='").append(getDepositMoney()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");			
      buffer.append("createNo").append("='").append(getCreateNo()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JfiDepositMoney) ) return false;
		 JfiDepositMoney castOther = ( JfiDepositMoney ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getUserName()==castOther.getUserName()) || ( this.getUserName()!=null && castOther.getUserName()!=null && this.getUserName().equals(castOther.getUserName()) ) )
 && ( (this.getWyear()==castOther.getWyear()) || ( this.getWyear()!=null && castOther.getWyear()!=null && this.getWyear().equals(castOther.getWyear()) ) )
 && ( (this.getWweek()==castOther.getWweek()) || ( this.getWweek()!=null && castOther.getWweek()!=null && this.getWweek().equals(castOther.getWweek()) ) )
 && ( (this.getSendMoney()==castOther.getSendMoney()) || ( this.getSendMoney()!=null && castOther.getSendMoney()!=null && this.getSendMoney().equals(castOther.getSendMoney()) ) )
 && ( (this.getInvoicePayable()==castOther.getInvoicePayable()) || ( this.getInvoicePayable()!=null && castOther.getInvoicePayable()!=null && this.getInvoicePayable().equals(castOther.getInvoicePayable()) ) )
 && ( (this.getDepositMoney()==castOther.getDepositMoney()) || ( this.getDepositMoney()!=null && castOther.getDepositMoney()!=null && this.getDepositMoney().equals(castOther.getDepositMoney()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
 && ( (this.getCreateNo()==castOther.getCreateNo()) || ( this.getCreateNo()!=null && castOther.getCreateNo()!=null && this.getCreateNo().equals(castOther.getCreateNo()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getUserName() == null ? 0 : this.getUserName().hashCode() );
         result = 37 * result + ( getWyear() == null ? 0 : this.getWyear().hashCode() );
         result = 37 * result + ( getWweek() == null ? 0 : this.getWweek().hashCode() );
         result = 37 * result + ( getSendMoney() == null ? 0 : this.getSendMoney().hashCode() );
         result = 37 * result + ( getInvoicePayable() == null ? 0 : this.getInvoicePayable().hashCode() );
         result = 37 * result + ( getDepositMoney() == null ? 0 : this.getDepositMoney().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         result = 37 * result + ( getCreateNo() == null ? 0 : this.getCreateNo().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         return result;
   }   





}
