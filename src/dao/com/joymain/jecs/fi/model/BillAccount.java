package com.joymain.jecs.fi.model;
// Generated 2009-10-27 10:56:43 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="BILL_ACCOUNT"
 *     
 */

public class BillAccount extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long baId;
    private String creditName;
    private String creditContact;
    private BigDecimal persent;
    private String baCode;

    // Constructors

    /** default constructor */
    public BillAccount() {
    }

    
    /** full constructor */
    public BillAccount(String creditName, String creditContact, BigDecimal persent) {
        this.creditName = creditName;
        this.creditContact = creditContact;
        this.persent = persent;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="BA_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_FI"
     *         
     */

    public Long getBaId() {
        return this.baId;
    }
    
    public void setBaId(Long baId) {
        this.baId = baId;
    }
    /**       
     *      *            @hibernate.property
     *             column="CREDIT_NAME"
     *             length="50"
     *             not-null="true"
     *         
     */

    public String getCreditName() {
        return this.creditName;
    }
    
    public void setCreditName(String creditName) {
        this.creditName = creditName;
    }
    /**       
     *      *            @hibernate.property
     *             column="CREDIT_CONTACT"
     *             length="50"
     *             not-null="true"
     *         
     */

    public String getCreditContact() {
        return this.creditContact;
    }
    
    public void setCreditContact(String creditContact) {
        this.creditContact = creditContact;
    }
    /**       
     *      *            @hibernate.property
     *             column="PERSENT"
     *             length="2"
     *             not-null="true"
     *         
     */

    public BigDecimal getPersent() {
        return this.persent;
    }
    
    public void setPersent(BigDecimal persent) {
        this.persent = persent;
    }
    /**       
     *      *            @hibernate.property
     *             column="BA_CODE"
     *             length="200"
     *             not-null="true"
     *         
     */
    public String getBaCode() {
		return baCode;
	}


	public void setBaCode(String baCode) {
		this.baCode = baCode;
	}


	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("creditName").append("='").append(getCreditName()).append("' ");			
      buffer.append("creditContact").append("='").append(getCreditContact()).append("' ");			
      buffer.append("persent").append("='").append(getPersent()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof BillAccount) ) return false;
		 BillAccount castOther = ( BillAccount ) other; 
         
		 return ( (this.getBaId()==castOther.getBaId()) || ( this.getBaId()!=null && castOther.getBaId()!=null && this.getBaId().equals(castOther.getBaId()) ) )
 && ( (this.getCreditName()==castOther.getCreditName()) || ( this.getCreditName()!=null && castOther.getCreditName()!=null && this.getCreditName().equals(castOther.getCreditName()) ) )
 && ( (this.getCreditContact()==castOther.getCreditContact()) || ( this.getCreditContact()!=null && castOther.getCreditContact()!=null && this.getCreditContact().equals(castOther.getCreditContact()) ) )
 && ( (this.getPersent()==castOther.getPersent()) || ( this.getPersent()!=null && castOther.getPersent()!=null && this.getPersent().equals(castOther.getPersent()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getBaId() == null ? 0 : this.getBaId().hashCode() );
         result = 37 * result + ( getCreditName() == null ? 0 : this.getCreditName().hashCode() );
         result = 37 * result + ( getCreditContact() == null ? 0 : this.getCreditContact().hashCode() );
         result = 37 * result + ( getPersent() == null ? 0 : this.getPersent().hashCode() );
         return result;
   }   





}
