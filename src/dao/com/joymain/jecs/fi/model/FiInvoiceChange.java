package com.joymain.jecs.fi.model;
// Generated 2015-11-29 17:40:03 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="FI_INVOICE_CHANGE"
 *     
 */

public class FiInvoiceChange extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private String userCode;
    private String foreignId;//外键（唯一码）
    private BigDecimal money;
    private String changeType;//'变化类型(+表示初始化赋值，也即表示会员欠公司的发票，也即未提交发票；-表示会员已交公司可以发票，也即已提交发票)
    private String jyear;
    private String jmonth;
    private String createUserCode;
    private Date createTime;
    private String remark;//备注(摘要)
    private BigDecimal balance;//余额


    // Constructors

    /** default constructor */
    public FiInvoiceChange() {
    }

    
    /** full constructor */
    public FiInvoiceChange(String userCode, String foreignId, BigDecimal money, String changeType, String jyear, String jmonth, String createUserCode, Date createTime, String remark, BigDecimal balance) {
        this.userCode = userCode;
        this.foreignId = foreignId;
        this.money = money;
        this.changeType = changeType;
        this.jyear = jyear;
        this.jmonth = jmonth;
        this.createUserCode = createUserCode;
        this.createTime = createTime;
        this.remark = remark;
        this.balance = balance;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ID"
     *         @hibernate.generator-param name="sequence" value="SEQ_FI" 
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
     *             column="FOREIGN_ID"
     *             length="20"
     *         
     */

    public String getForeignId() {
        return this.foreignId;
    }
    
    public void setForeignId(String foreignId) {
        this.foreignId = foreignId;
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
     *             column="CHANGE_TYPE"
     *             length="10"
     *         
     */

    public String getChangeType() {
        return this.changeType;
    }
    
    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }
    /**       
     *      *            @hibernate.property
     *             column="J_YEAR"
     *             length="8"
     *         
     */

    public String getJyear() {
        return this.jyear;
    }
    
    public void setJyear(String jyear) {
        this.jyear = jyear;
    }
    /**       
     *      *            @hibernate.property
     *             column="J_MONTH"
     *             length="8"
     *         
     */

    public String getJmonth() {
        return this.jmonth;
    }
    
    public void setJmonth(String jmonth) {
        this.jmonth = jmonth;
    }
    /**       
     *      *            @hibernate.property
     *             column="CREATE_USER_CODE"
     *             length="20"
     *         
     */

    public String getCreateUserCode() {
        return this.createUserCode;
    }
    
    public void setCreateUserCode(String createUserCode) {
        this.createUserCode = createUserCode;
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
     *             column="REMARK"
     *             length="200"
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
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("foreignId").append("='").append(getForeignId()).append("' ");			
      buffer.append("money").append("='").append(getMoney()).append("' ");			
      buffer.append("changeType").append("='").append(getChangeType()).append("' ");			
      buffer.append("jyear").append("='").append(getJyear()).append("' ");			
      buffer.append("jmonth").append("='").append(getJmonth()).append("' ");			
      buffer.append("createUserCode").append("='").append(getCreateUserCode()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");	
      buffer.append("balance").append("='").append(getBalance()).append("' ");	
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof FiInvoiceChange) ) return false;
		 FiInvoiceChange castOther = ( FiInvoiceChange ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getForeignId()==castOther.getForeignId()) || ( this.getForeignId()!=null && castOther.getForeignId()!=null && this.getForeignId().equals(castOther.getForeignId()) ) )
 && ( (this.getMoney()==castOther.getMoney()) || ( this.getMoney()!=null && castOther.getMoney()!=null && this.getMoney().equals(castOther.getMoney()) ) )
 && ( (this.getChangeType()==castOther.getChangeType()) || ( this.getChangeType()!=null && castOther.getChangeType()!=null && this.getChangeType().equals(castOther.getChangeType()) ) )
 && ( (this.getJyear()==castOther.getJyear()) || ( this.getJyear()!=null && castOther.getJyear()!=null && this.getJyear().equals(castOther.getJyear()) ) )
 && ( (this.getJmonth()==castOther.getJmonth()) || ( this.getJmonth()!=null && castOther.getJmonth()!=null && this.getJmonth().equals(castOther.getJmonth()) ) )
 && ( (this.getCreateUserCode()==castOther.getCreateUserCode()) || ( this.getCreateUserCode()!=null && castOther.getCreateUserCode()!=null && this.getCreateUserCode().equals(castOther.getCreateUserCode()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
 && ( (this.getBalance()==castOther.getBalance()) || ( this.getBalance()!=null && castOther.getBalance()!=null && this.getBalance().equals(castOther.getBalance()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getForeignId() == null ? 0 : this.getForeignId().hashCode() );
         result = 37 * result + ( getMoney() == null ? 0 : this.getMoney().hashCode() );
         result = 37 * result + ( getChangeType() == null ? 0 : this.getChangeType().hashCode() );
         result = 37 * result + ( getJyear() == null ? 0 : this.getJyear().hashCode() );
         result = 37 * result + ( getJmonth() == null ? 0 : this.getJmonth().hashCode() );
         result = 37 * result + ( getCreateUserCode() == null ? 0 : this.getCreateUserCode().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         result = 37 * result + ( getBalance() == null ? 0 : this.getBalance().hashCode() );

         return result;
   }   





}
