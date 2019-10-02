package com.joymain.jecs.fi.model;
// Generated 2013-12-2 16:18:24 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="FI_SECURITY_DEPOSIT"
 *     
 */

public class FiSecurityDeposit extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long fsdId;
    private String userCode;
    private String userName;
    private String tel;
    private String email;
    private BigDecimal balance;
    private String dept;

    private BigDecimal jfiBalance;
    
    // Constructors

    /** default constructor */
    public FiSecurityDeposit() {
    }

	/** minimal constructor */
    public FiSecurityDeposit(String userCode) {
        this.userCode = userCode;
    }
    
    /** full constructor */
    public FiSecurityDeposit(String userCode, String userName, String tel, String email, BigDecimal balance, String dept) {
        this.userCode = userCode;
        this.userName = userName;
        this.tel = tel;
        this.email = email;
        this.balance = balance;
        this.dept = dept;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="FSD_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_BANKBOOK"         
     */

    public Long getFsdId() {
        return this.fsdId;
    }
    
    public void setFsdId(Long fsdId) {
        this.fsdId = fsdId;
    }
    /**       
     *      *            @hibernate.property
     *             column="USER_CODE"
     *             length="40"
     *             not-null="true"
     *         
     */

    public String getUserCode() {
        return this.userCode;
    }
    
    /**
	 * @spring.validator type="required"
	 */
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="USER_NAME"
     *             length="40"
     *         
     */

    public String getUserName() {
        return this.userName;
    }
    
    /**
	 * @spring.validator type="required"
	 */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**       
     *      *            @hibernate.property
     *             column="TEL"
     *             length="40"
     *         
     */

    public String getTel() {
        return this.tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }
    /**       
     *      *            @hibernate.property
     *             column="EMAIL"
     *             length="80"
     *         
     */

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
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
     *             column="DEPT"
     *             length="20"
     *         
     */

    public String getDept() {
        return this.dept;
    }
    
    /**
	 * @spring.validator type="required"
	 */
    public void setDept(String dept) {
        this.dept = dept;
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
      buffer.append("tel").append("='").append(getTel()).append("' ");			
      buffer.append("email").append("='").append(getEmail()).append("' ");			
      buffer.append("balance").append("='").append(getBalance()).append("' ");			
      buffer.append("dept").append("='").append(getDept()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof FiSecurityDeposit) ) return false;
		 FiSecurityDeposit castOther = ( FiSecurityDeposit ) other; 
         
		 return ( (this.getFsdId()==castOther.getFsdId()) || ( this.getFsdId()!=null && castOther.getFsdId()!=null && this.getFsdId().equals(castOther.getFsdId()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getUserName()==castOther.getUserName()) || ( this.getUserName()!=null && castOther.getUserName()!=null && this.getUserName().equals(castOther.getUserName()) ) )
 && ( (this.getTel()==castOther.getTel()) || ( this.getTel()!=null && castOther.getTel()!=null && this.getTel().equals(castOther.getTel()) ) )
 && ( (this.getEmail()==castOther.getEmail()) || ( this.getEmail()!=null && castOther.getEmail()!=null && this.getEmail().equals(castOther.getEmail()) ) )
 && ( (this.getBalance()==castOther.getBalance()) || ( this.getBalance()!=null && castOther.getBalance()!=null && this.getBalance().equals(castOther.getBalance()) ) )
 && ( (this.getDept()==castOther.getDept()) || ( this.getDept()!=null && castOther.getDept()!=null && this.getDept().equals(castOther.getDept()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getFsdId() == null ? 0 : this.getFsdId().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getUserName() == null ? 0 : this.getUserName().hashCode() );
         result = 37 * result + ( getTel() == null ? 0 : this.getTel().hashCode() );
         result = 37 * result + ( getEmail() == null ? 0 : this.getEmail().hashCode() );
         result = 37 * result + ( getBalance() == null ? 0 : this.getBalance().hashCode() );
         result = 37 * result + ( getDept() == null ? 0 : this.getDept().hashCode() );
         return result;
   }

public BigDecimal getJfiBalance() {
	return jfiBalance;
}

public void setJfiBalance(BigDecimal jfiBalance) {
	this.jfiBalance = jfiBalance;
}   





}
