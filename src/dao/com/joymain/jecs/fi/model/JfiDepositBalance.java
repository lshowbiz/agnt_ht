package com.joymain.jecs.fi.model;
// Generated 2015-10-30 10:52:21 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JFI_DEPOSIT_BALANCE"
 *     
 */

public class JfiDepositBalance extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long fdbId;
    private String userCode;
    private BigDecimal balance;
    private String depositType;


    // Constructors

    /** default constructor */
    public JfiDepositBalance() {
    }

	/** minimal constructor */
    public JfiDepositBalance(String userCode) {
        this.userCode = userCode;
    }
    
    /** full constructor */
    public JfiDepositBalance(String userCode, BigDecimal balance, String depositType) {
        this.userCode = userCode;
        this.balance = balance;
        this.depositType = depositType;
    }
    

   
    // Property accessors

    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="FDB_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_FI"
     *         
     */
    public Long getFdbId() {
        return this.fdbId;
    }
    
    public void setFdbId(Long fdbId) {
        this.fdbId = fdbId;
    }
    /**       
     *      *            @hibernate.property
     *             column="USER_CODE"
     *             length="20"
     *             not-null="true"
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
     *             column="DEPOSIT_TYPE"
     *             length="1"
     *         
     */

    public String getDepositType() {
        return this.depositType;
    }
    
    public void setDepositType(String depositType) {
        this.depositType = depositType;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("balance").append("='").append(getBalance()).append("' ");			
      buffer.append("depositType").append("='").append(getDepositType()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JfiDepositBalance) ) return false;
		 JfiDepositBalance castOther = ( JfiDepositBalance ) other; 
         
		 return ( (this.getFdbId()==castOther.getFdbId()) || ( this.getFdbId()!=null && castOther.getFdbId()!=null && this.getFdbId().equals(castOther.getFdbId()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getBalance()==castOther.getBalance()) || ( this.getBalance()!=null && castOther.getBalance()!=null && this.getBalance().equals(castOther.getBalance()) ) )
 && ( (this.getDepositType()==castOther.getDepositType()) || ( this.getDepositType()!=null && castOther.getDepositType()!=null && this.getDepositType().equals(castOther.getDepositType()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getFdbId() == null ? 0 : this.getFdbId().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getBalance() == null ? 0 : this.getBalance().hashCode() );
         result = 37 * result + ( getDepositType() == null ? 0 : this.getDepositType().hashCode() );
         return result;
   }   





}
