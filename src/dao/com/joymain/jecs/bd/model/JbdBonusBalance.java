package com.joymain.jecs.bd.model;
// Generated 2009-9-23 11:35:08 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JBD_BONUS_BALANCE"
 *     
 */

public class JbdBonusBalance extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private String userCode;
    private BigDecimal bonusBalance;
    private BigDecimal assignedBonus;
    private String flag;
    private String status;
    private String checkUser;
    private Date checkTime;
    private Date flagTime;
    // Constructors


    /**       
     *      *            @hibernate.property
     *             column="FLAG_TIME"
     *             length="8"
     *         
     */
    public Date getFlagTime() {
		return flagTime;
	}


	public void setFlagTime(Date flagTime) {
		this.flagTime = flagTime;
	}


	/** default constructor */
    public JbdBonusBalance() {
    }

    
    /** full constructor */
    public JbdBonusBalance(String companyCode, String name, String cardType, String bank, String bankaddress, String bankbook, String bankcard, BigDecimal bonusBalance) {

        this.bonusBalance = bonusBalance;
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
     *             column="BONUS_BALANCE"
     *             length="22"
     *         
     */

    public BigDecimal getBonusBalance() {
        return this.bonusBalance;
    }
    
    public void setBonusBalance(BigDecimal bonusBalance) {
        this.bonusBalance = bonusBalance;
    }
   

    /**       
     *      *            @hibernate.property
     *             column="ASSIGNED_BONUS"
     *             length="22"
     *         
     */

    public BigDecimal getAssignedBonus() {
		return assignedBonus;
	}


	public void setAssignedBonus(BigDecimal assignedBonus) {
		this.assignedBonus = assignedBonus;
	}


	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("bonusBalance").append("='").append(getBonusBalance()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JbdBonusBalance) ) return false;
		 JbdBonusBalance castOther = ( JbdBonusBalance ) other; 
         
		 return ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getBonusBalance()==castOther.getBonusBalance()) || ( this.getBonusBalance()!=null && castOther.getBonusBalance()!=null && this.getBonusBalance().equals(castOther.getBonusBalance()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getBonusBalance() == null ? 0 : this.getBonusBalance().hashCode() );
         return result;
   }

   /**       
    *      *            @hibernate.property
    *             column="FLAG"
    *             length="1"
    *         
    */
public String getFlag() {
	return flag;
}


public void setFlag(String flag) {
	this.flag = flag;
}


/**       
 *      *            @hibernate.property
 *             column="CHECK_TIME"
 *             length="8"
 *         
 */
public Date getCheckTime() {
	return checkTime;
}


public void setCheckTime(Date checkTime) {
	this.checkTime = checkTime;
}


/**       
 *      *            @hibernate.property
 *             column="CHECK_USER"
 *             length="20"
 *         
 */
public String getCheckUser() {
	return checkUser;
}


public void setCheckUser(String checkUser) {
	this.checkUser = checkUser;
}


/**       
 *      *            @hibernate.property
 *             column="STATUS"
 *             length="1"
 *         
 */
public String getStatus() {
	return status;
}


public void setStatus(String status) {
	this.status = status;
}   





}
