package com.joymain.jecs.fi.model;
// Generated 2015-10-12 9:35:15 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JFI_INVOICE_DEPOSIT"
 *     
 */

public class JfiInvoiceDeposit extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private Long invoiceId;
    private Long depositId;
    private BigDecimal invoiceMoney;
    private String createNo;
    private Date createTime;


    // Constructors

    /** default constructor */
    public JfiInvoiceDeposit() {
    }

    
    /** full constructor */
    public JfiInvoiceDeposit(Long invoiceId, Long depositId, BigDecimal invoiceMoney, String createNo, Date createTime) {
        this.invoiceId = invoiceId;
        this.depositId = depositId;
        this.invoiceMoney = invoiceMoney;
        this.createNo = createNo;
        this.createTime = createTime;
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
     *             column="INVOICE_ID"
     *             length="22"
     *         
     */

    public Long getInvoiceId() {
        return this.invoiceId;
    }
    
    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }
    /**       
     *      *            @hibernate.property
     *             column="DEPOSIT_ID"
     *             length="22"
     *         
     */

    public Long getDepositId() {
        return this.depositId;
    }
    
    public void setDepositId(Long depositId) {
        this.depositId = depositId;
    }
    /**       
     *      *            @hibernate.property
     *             column="INVOICE_MONEY"
     *             length="22"
     *         
     */

    public BigDecimal getInvoiceMoney() {
        return this.invoiceMoney;
    }
    
    public void setInvoiceMoney(BigDecimal invoiceMoney) {
        this.invoiceMoney = invoiceMoney;
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
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("invoiceId").append("='").append(getInvoiceId()).append("' ");			
      buffer.append("depositId").append("='").append(getDepositId()).append("' ");			
      buffer.append("invoiceMoney").append("='").append(getInvoiceMoney()).append("' ");			
      buffer.append("createNo").append("='").append(getCreateNo()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JfiInvoiceDeposit) ) return false;
		 JfiInvoiceDeposit castOther = ( JfiInvoiceDeposit ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getInvoiceId()==castOther.getInvoiceId()) || ( this.getInvoiceId()!=null && castOther.getInvoiceId()!=null && this.getInvoiceId().equals(castOther.getInvoiceId()) ) )
 && ( (this.getDepositId()==castOther.getDepositId()) || ( this.getDepositId()!=null && castOther.getDepositId()!=null && this.getDepositId().equals(castOther.getDepositId()) ) )
 && ( (this.getInvoiceMoney()==castOther.getInvoiceMoney()) || ( this.getInvoiceMoney()!=null && castOther.getInvoiceMoney()!=null && this.getInvoiceMoney().equals(castOther.getInvoiceMoney()) ) )
 && ( (this.getCreateNo()==castOther.getCreateNo()) || ( this.getCreateNo()!=null && castOther.getCreateNo()!=null && this.getCreateNo().equals(castOther.getCreateNo()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getInvoiceId() == null ? 0 : this.getInvoiceId().hashCode() );
         result = 37 * result + ( getDepositId() == null ? 0 : this.getDepositId().hashCode() );
         result = 37 * result + ( getInvoiceMoney() == null ? 0 : this.getInvoiceMoney().hashCode() );
         result = 37 * result + ( getCreateNo() == null ? 0 : this.getCreateNo().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         return result;
   }   





}
