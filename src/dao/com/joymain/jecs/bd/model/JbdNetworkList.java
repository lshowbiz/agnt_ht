package com.joymain.jecs.bd.model;
// Generated 2011-1-13 9:30:51 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JBD_NETWORK_LIST"
 *     
 */

public class JbdNetworkList extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private Integer wyear;
    private Integer wmonth;
    private Integer wweek;
    private String userCode;
    private String companyCode;
    private Integer startMonth;
    private Integer endMonth;
    private BigDecimal networkMoney;
    private BigDecimal balanceMoney;
    private BigDecimal deductMoney;
    private Integer calcWeek;


    // Constructors

    /** default constructor */
    public JbdNetworkList() {
    }

    
    /** full constructor */
    public JbdNetworkList(Integer wyear, Integer wmonth, Integer wweek, String userCode, String companyCode, Integer startMonth, Integer endMonth, BigDecimal networkMoney, BigDecimal balanceMoney, BigDecimal deductMoney, Integer calcWeek) {
        this.wyear = wyear;
        this.wmonth = wmonth;
        this.wweek = wweek;
        this.userCode = userCode;
        this.companyCode = companyCode;
        this.startMonth = startMonth;
        this.endMonth = endMonth;
        this.networkMoney = networkMoney;
        this.balanceMoney = balanceMoney;
        this.deductMoney = deductMoney;
        this.calcWeek = calcWeek;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ID"
     *@hibernate.generator-param name="sequence" value="SEQ_BD"
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
     *             column="W_YEAR"
     *             length="8"
     *         
     */

    public Integer getWyear() {
        return this.wyear;
    }
    
    public void setWyear(Integer wyear) {
        this.wyear = wyear;
    }
    /**       
     *      *            @hibernate.property
     *             column="W_MONTH"
     *             length="8"
     *         
     */

    public Integer getWmonth() {
        return this.wmonth;
    }
    
    public void setWmonth(Integer wmonth) {
        this.wmonth = wmonth;
    }
    /**       
     *      *            @hibernate.property
     *             column="W_WEEK"
     *             length="8"
     *         
     */

    public Integer getWweek() {
        return this.wweek;
    }
    
    public void setWweek(Integer wweek) {
        this.wweek = wweek;
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
     *             column="COMPANY_CODE"
     *             length="4"
     *         
     */

    public String getCompanyCode() {
        return this.companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="START_MONTH"
     *             length="8"
     *         
     */

    public Integer getStartMonth() {
        return this.startMonth;
    }
    
    public void setStartMonth(Integer startMonth) {
        this.startMonth = startMonth;
    }
    /**       
     *      *            @hibernate.property
     *             column="END_MONTH"
     *             length="8"
     *         
     */

    public Integer getEndMonth() {
        return this.endMonth;
    }
    
    public void setEndMonth(Integer endMonth) {
        this.endMonth = endMonth;
    }
    /**       
     *      *            @hibernate.property
     *             column="NETWORK_MONEY"
     *             length="22"
     *         
     */

    public BigDecimal getNetworkMoney() {
        return this.networkMoney;
    }
    
    public void setNetworkMoney(BigDecimal networkMoney) {
        this.networkMoney = networkMoney;
    }
    /**       
     *      *            @hibernate.property
     *             column="BALANCE_MONEY"
     *             length="22"
     *         
     */

    public BigDecimal getBalanceMoney() {
        return this.balanceMoney;
    }
    
    public void setBalanceMoney(BigDecimal balanceMoney) {
        this.balanceMoney = balanceMoney;
    }
    /**       
     *      *            @hibernate.property
     *             column="DEDUCT_MONEY"
     *             length="22"
     *         
     */

    public BigDecimal getDeductMoney() {
        return this.deductMoney;
    }
    
    public void setDeductMoney(BigDecimal deductMoney) {
        this.deductMoney = deductMoney;
    }
    /**       
     *      *            @hibernate.property
     *             column="CALC_WEEK"
     *             length="8"
     *         
     */

    public Integer getCalcWeek() {
        return this.calcWeek;
    }
    
    public void setCalcWeek(Integer calcWeek) {
        this.calcWeek = calcWeek;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("wyear").append("='").append(getWyear()).append("' ");			
      buffer.append("wmonth").append("='").append(getWmonth()).append("' ");			
      buffer.append("wweek").append("='").append(getWweek()).append("' ");			
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("startMonth").append("='").append(getStartMonth()).append("' ");			
      buffer.append("endMonth").append("='").append(getEndMonth()).append("' ");			
      buffer.append("networkMoney").append("='").append(getNetworkMoney()).append("' ");			
      buffer.append("balanceMoney").append("='").append(getBalanceMoney()).append("' ");			
      buffer.append("deductMoney").append("='").append(getDeductMoney()).append("' ");			
      buffer.append("calcWeek").append("='").append(getCalcWeek()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JbdNetworkList) ) return false;
		 JbdNetworkList castOther = ( JbdNetworkList ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getWyear()==castOther.getWyear()) || ( this.getWyear()!=null && castOther.getWyear()!=null && this.getWyear().equals(castOther.getWyear()) ) )
 && ( (this.getWmonth()==castOther.getWmonth()) || ( this.getWmonth()!=null && castOther.getWmonth()!=null && this.getWmonth().equals(castOther.getWmonth()) ) )
 && ( (this.getWweek()==castOther.getWweek()) || ( this.getWweek()!=null && castOther.getWweek()!=null && this.getWweek().equals(castOther.getWweek()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getStartMonth()==castOther.getStartMonth()) || ( this.getStartMonth()!=null && castOther.getStartMonth()!=null && this.getStartMonth().equals(castOther.getStartMonth()) ) )
 && ( (this.getEndMonth()==castOther.getEndMonth()) || ( this.getEndMonth()!=null && castOther.getEndMonth()!=null && this.getEndMonth().equals(castOther.getEndMonth()) ) )
 && ( (this.getNetworkMoney()==castOther.getNetworkMoney()) || ( this.getNetworkMoney()!=null && castOther.getNetworkMoney()!=null && this.getNetworkMoney().equals(castOther.getNetworkMoney()) ) )
 && ( (this.getBalanceMoney()==castOther.getBalanceMoney()) || ( this.getBalanceMoney()!=null && castOther.getBalanceMoney()!=null && this.getBalanceMoney().equals(castOther.getBalanceMoney()) ) )
 && ( (this.getDeductMoney()==castOther.getDeductMoney()) || ( this.getDeductMoney()!=null && castOther.getDeductMoney()!=null && this.getDeductMoney().equals(castOther.getDeductMoney()) ) )
 && ( (this.getCalcWeek()==castOther.getCalcWeek()) || ( this.getCalcWeek()!=null && castOther.getCalcWeek()!=null && this.getCalcWeek().equals(castOther.getCalcWeek()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getWyear() == null ? 0 : this.getWyear().hashCode() );
         result = 37 * result + ( getWmonth() == null ? 0 : this.getWmonth().hashCode() );
         result = 37 * result + ( getWweek() == null ? 0 : this.getWweek().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getStartMonth() == null ? 0 : this.getStartMonth().hashCode() );
         result = 37 * result + ( getEndMonth() == null ? 0 : this.getEndMonth().hashCode() );
         result = 37 * result + ( getNetworkMoney() == null ? 0 : this.getNetworkMoney().hashCode() );
         result = 37 * result + ( getBalanceMoney() == null ? 0 : this.getBalanceMoney().hashCode() );
         result = 37 * result + ( getDeductMoney() == null ? 0 : this.getDeductMoney().hashCode() );
         result = 37 * result + ( getCalcWeek() == null ? 0 : this.getCalcWeek().hashCode() );
         return result;
   }   





}
