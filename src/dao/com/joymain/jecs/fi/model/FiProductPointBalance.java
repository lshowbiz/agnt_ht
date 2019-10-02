package com.joymain.jecs.fi.model;
// Generated 2011-8-18 10:14:26 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="FI_PRODUCT_POINT_BALANCE"
 *     
 */

public class FiProductPointBalance extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long fbbId;
    private String userCode;
    private BigDecimal balance;
    private String productPointType;


    // Constructors

    /** default constructor */
    public FiProductPointBalance() {
    }

    
    /** full constructor */
    public FiProductPointBalance(String userCode, BigDecimal balance, String productPointType) {
        this.userCode = userCode;
        this.balance = balance;
        this.productPointType = productPointType;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="FBB_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_BANKBOOK"
     *         
     */

    public Long getFbbId() {
        return this.fbbId;
    }
    
    public void setFbbId(Long fbbId) {
        this.fbbId = fbbId;
    }
    /**       
     *      *            @hibernate.property
     *             column="USER_CODE"
     *             length="200"
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
     *             column="PRODUCT_POINT_TYPE"
     *             length="10"
     *             not-null="true"
     *         
     */

    public String getProductPointType() {
        return this.productPointType;
    }
    
    public void setProductPointType(String productPointType) {
        this.productPointType = productPointType;
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
      buffer.append("bankbookType").append("='").append(getProductPointType()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof FiProductPointBalance) ) return false;
		 FiProductPointBalance castOther = ( FiProductPointBalance ) other; 
         
		 return ( (this.getFbbId()==castOther.getFbbId()) || ( this.getFbbId()!=null && castOther.getFbbId()!=null && this.getFbbId().equals(castOther.getFbbId()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getBalance()==castOther.getBalance()) || ( this.getBalance()!=null && castOther.getBalance()!=null && this.getBalance().equals(castOther.getBalance()) ) )
 && ( (this.getProductPointType()==castOther.getProductPointType()) || ( this.getProductPointType()!=null && castOther.getProductPointType()!=null && this.getProductPointType().equals(castOther.getProductPointType()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getFbbId() == null ? 0 : this.getFbbId().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getBalance() == null ? 0 : this.getBalance().hashCode() );
         result = 37 * result + ( getProductPointType() == null ? 0 : this.getProductPointType().hashCode() );
         return result;
   }   





}
