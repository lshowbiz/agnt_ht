package com.joymain.jecs.fi.model;
// Generated 2011-5-16 15:38:41 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;

import com.joymain.jecs.sys.model.SysUser;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="FI_BCOIN_BALANCE"
 *     
 */

public class FiBcoinBalance extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private String userCode;
//    private SysUser sysUser;
    private BigDecimal balance;

    /**
     * *
     * @hibernate.many-to-one not-null="true"
     * @hibernate.column name="USER_CODE"
     * 
     */
//    public SysUser getSysUser() {
//		return sysUser;
//	}
//
//	public void setSysUser(SysUser sysUser) {
//		this.sysUser = sysUser;
//	}
	
    // Constructors

    /** default constructor */
    public FiBcoinBalance() {
    }

    
    /** full constructor */
    public FiBcoinBalance(BigDecimal balance) {
        this.balance = balance;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
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
      buffer.append("balance").append("='").append(getBalance()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof FiBcoinBalance) ) return false;
		 FiBcoinBalance castOther = ( FiBcoinBalance ) other; 
         
		 return 
		 ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ))
&& 
 ( (this.getBalance()==castOther.getBalance()) || ( this.getBalance()!=null && castOther.getBalance()!=null && this.getBalance().equals(castOther.getBalance()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getBalance() == null ? 0 : this.getBalance().hashCode() );
         return result;
   }   





}
