package com.joymain.jecs.fi.model;
// Generated 2009-9-14 16:10:06 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JFI_BANKBOOK_BALANCE"
 *     
 */

public class JfiBankbookBalance extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private String userCode;
    private BigDecimal balance;
    private Long lastJournalId;


    // Constructors

    /** default constructor */
    public JfiBankbookBalance() {
    }

	/** minimal constructor */
    public JfiBankbookBalance(BigDecimal balance) {
        this.balance = balance;
    }
    
    /** full constructor */
    public JfiBankbookBalance(BigDecimal balance, Long lastJournalId) {
        this.balance = balance;
        this.lastJournalId = lastJournalId;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.String"
     *             column="USER_CODE"
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
     *             not-null="true"
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
     *             column="LAST_JOURNAL_ID"
     *             length="12"
     *         
     */

    public Long getLastJournalId() {
        return this.lastJournalId;
    }
    
    public void setLastJournalId(Long lastJournalId) {
        this.lastJournalId = lastJournalId;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("balance").append("='").append(getBalance()).append("' ");			
      buffer.append("lastJournalId").append("='").append(getLastJournalId()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JfiBankbookBalance) ) return false;
		 JfiBankbookBalance castOther = ( JfiBankbookBalance ) other; 
         
		 return ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getBalance()==castOther.getBalance()) || ( this.getBalance()!=null && castOther.getBalance()!=null && this.getBalance().equals(castOther.getBalance()) ) )
 && ( (this.getLastJournalId()==castOther.getLastJournalId()) || ( this.getLastJournalId()!=null && castOther.getLastJournalId()!=null && this.getLastJournalId().equals(castOther.getLastJournalId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getBalance() == null ? 0 : this.getBalance().hashCode() );
         result = 37 * result + ( getLastJournalId() == null ? 0 : this.getLastJournalId().hashCode() );
         return result;
   }   





}
