package com.joymain.jecs.fi.model;
// Generated 2014-6-24 11:48:25 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="FI_BILL_ACCOUNT_WARNING"
 *     
 */

public class FiBillAccountWarning extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private String billAccountCode;
    private BigDecimal nowTotalAmount;


    // Constructors

    /** default constructor */
    public FiBillAccountWarning() {
    }

    
    /** full constructor */
    public FiBillAccountWarning(BigDecimal nowTotalAmount) {
        this.nowTotalAmount = nowTotalAmount;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.String"
     *             column="BILL_ACCOUNT_CODE"
     *         
     */

    public String getBillAccountCode() {
        return this.billAccountCode;
    }
    
    public void setBillAccountCode(String billAccountCode) {
        this.billAccountCode = billAccountCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="NOW_TOTAL_AMOUNT"
     *             length="18"
     *             not-null="true"
     *         
     */

    public BigDecimal getNowTotalAmount() {
        return this.nowTotalAmount;
    }
    
    public void setNowTotalAmount(BigDecimal nowTotalAmount) {
        this.nowTotalAmount = nowTotalAmount;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("nowTotalAmount").append("='").append(getNowTotalAmount()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof FiBillAccountWarning) ) return false;
		 FiBillAccountWarning castOther = ( FiBillAccountWarning ) other; 
         
		 return ( (this.getBillAccountCode()==castOther.getBillAccountCode()) || ( this.getBillAccountCode()!=null && castOther.getBillAccountCode()!=null && this.getBillAccountCode().equals(castOther.getBillAccountCode()) ) )
 && ( (this.getNowTotalAmount()==castOther.getNowTotalAmount()) || ( this.getNowTotalAmount()!=null && castOther.getNowTotalAmount()!=null && this.getNowTotalAmount().equals(castOther.getNowTotalAmount()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getBillAccountCode() == null ? 0 : this.getBillAccountCode().hashCode() );
         result = 37 * result + ( getNowTotalAmount() == null ? 0 : this.getNowTotalAmount().hashCode() );
         return result;
   }   





}
