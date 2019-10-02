package com.joymain.jecs.fi.model;
// Generated 2015-11-6 10:47:42 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="FI_AVAILABLE_INVOICE"
 *     
 */

public class FiAvailableInvoice extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private String id;
    private String userCode;
    private String userName;
    private BigDecimal invoiceValue;
    private BigDecimal bond;
    private Integer jyear;
    private Integer jmonth;
    private String ownedCompany;
    private Date createTime;
    private String logicalDelete;
    private String remark;
    private Integer status;



    // Constructors

    /** default constructor */
    public FiAvailableInvoice() {
    }

    
    /** full constructor */
    public FiAvailableInvoice(String userCode, String userName, BigDecimal invoiceValue, BigDecimal bond, Integer jyear, Integer jmonth, String ownedCompany, Date createTime, String logicalDelete, String remark,Integer status) {
        this.userCode = userCode;
        this.userName = userName;
        this.invoiceValue = invoiceValue;
        this.bond = bond;
        this.jyear = jyear;
        this.jmonth = jmonth;
        this.ownedCompany = ownedCompany;
        this.createTime = createTime;
        this.logicalDelete = logicalDelete;
        this.remark = remark;
        this.status = status;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.String"
     *             column="ID"
     *         
     */

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
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
     *             length="100"
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
     *             column="INVOICE_VALUE"
     *             length="18"
     *         
     */

    public BigDecimal getInvoiceValue() {
        return this.invoiceValue;
    }
    
    public void setInvoiceValue(BigDecimal invoiceValue) {
        this.invoiceValue = invoiceValue;
    }
    /**       
     *      *            @hibernate.property
     *             column="BOND"
     *             length="18"
     *         
     */

    public BigDecimal getBond() {
        return this.bond;
    }
    
    public void setBond(BigDecimal bond) {
        this.bond = bond;
    }
    /**       
     *      *            @hibernate.property
     *             column="J_YEAR"
     *             length="8"
     *         
     */

    public Integer getJyear() {
        return this.jyear;
    }
    
    public void setJyear(Integer jyear) {
        this.jyear = jyear;
    }
    /**       
     *      *            @hibernate.property
     *             column="J_MONTH"
     *             length="8"
     *         
     */

    public Integer getJmonth() {
        return this.jmonth;
    }
    
    public void setJmonth(Integer jmonth) {
        this.jmonth = jmonth;
    }
    /**       
     *      *            @hibernate.property
     *             column="OWNED_COMPANY"
     *             length="200"
     *         
     */

    public String getOwnedCompany() {
        return this.ownedCompany;
    }
    
    public void setOwnedCompany(String ownedCompany) {
        this.ownedCompany = ownedCompany;
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
     *             column="LOGICAL_DELETE"
     *             length="10"
     *         
     */

    public String getLogicalDelete() {
        return this.logicalDelete;
    }
    
    public void setLogicalDelete(String logicalDelete) {
        this.logicalDelete = logicalDelete;
    }
    /**       
     *      *            @hibernate.property
     *             column="REMARK"
     *             length="500"
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
     *             column="STATUS"
     *             length="2"
     */
    public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
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
      buffer.append("invoiceValue").append("='").append(getInvoiceValue()).append("' ");			
      buffer.append("bond").append("='").append(getBond()).append("' ");			
      buffer.append("jyear").append("='").append(getJyear()).append("' ");			
      buffer.append("jmonth").append("='").append(getJmonth()).append("' ");			
      buffer.append("ownedCompany").append("='").append(getOwnedCompany()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("logicalDelete").append("='").append(getLogicalDelete()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");
      buffer.append("status").append("='").append(getStatus()).append("' ");
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof FiAvailableInvoice) ) return false;
		 FiAvailableInvoice castOther = ( FiAvailableInvoice ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getUserName()==castOther.getUserName()) || ( this.getUserName()!=null && castOther.getUserName()!=null && this.getUserName().equals(castOther.getUserName()) ) )
 && ( (this.getInvoiceValue()==castOther.getInvoiceValue()) || ( this.getInvoiceValue()!=null && castOther.getInvoiceValue()!=null && this.getInvoiceValue().equals(castOther.getInvoiceValue()) ) )
 && ( (this.getBond()==castOther.getBond()) || ( this.getBond()!=null && castOther.getBond()!=null && this.getBond().equals(castOther.getBond()) ) )
 && ( (this.getJyear()==castOther.getJyear()) || ( this.getJyear()!=null && castOther.getJyear()!=null && this.getJyear().equals(castOther.getJyear()) ) )
 && ( (this.getJmonth()==castOther.getJmonth()) || ( this.getJmonth()!=null && castOther.getJmonth()!=null && this.getJmonth().equals(castOther.getJmonth()) ) )
 && ( (this.getOwnedCompany()==castOther.getOwnedCompany()) || ( this.getOwnedCompany()!=null && castOther.getOwnedCompany()!=null && this.getOwnedCompany().equals(castOther.getOwnedCompany()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getLogicalDelete()==castOther.getLogicalDelete()) || ( this.getLogicalDelete()!=null && castOther.getLogicalDelete()!=null && this.getLogicalDelete().equals(castOther.getLogicalDelete()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
;
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getUserName() == null ? 0 : this.getUserName().hashCode() );
         result = 37 * result + ( getInvoiceValue() == null ? 0 : this.getInvoiceValue().hashCode() );
         result = 37 * result + ( getBond() == null ? 0 : this.getBond().hashCode() );
         result = 37 * result + ( getJyear() == null ? 0 : this.getJyear().hashCode() );
         result = 37 * result + ( getJmonth() == null ? 0 : this.getJmonth().hashCode() );
         result = 37 * result + ( getOwnedCompany() == null ? 0 : this.getOwnedCompany().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getLogicalDelete() == null ? 0 : this.getLogicalDelete().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );

         return result;
   }   





}
