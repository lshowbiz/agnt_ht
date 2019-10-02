package com.joymain.jecs.bd.model;
// Generated 2009-10-11 15:09:17 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JBD_SELL_CALC_SUB_DETAIL_HIST"
 *     
 */

public class JbdSellCalcSubDetailHist extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private Integer wyear;
    private Integer wmonth;
    private Integer wweek;
    private String userCode;
    private BigDecimal nlevel;
    private String recommendedCode;
    private String recommendedCompanyCode;
    private String recommendedOrderNo;
    private String recommendedOrderClass;
    private String recommendedOrderType;
    private BigDecimal pv;


    // Constructors

    /** default constructor */
    public JbdSellCalcSubDetailHist() {
    }

    
    /** full constructor */
    public JbdSellCalcSubDetailHist(Integer wyear, Integer wmonth, Integer wweek, String userCode, BigDecimal nlevel, String recommendedCode, String recommendedCompanyCode, String recommendedOrderNo, String recommendedOrderClass, String recommendedOrderType, BigDecimal pv) {
        this.wyear = wyear;
        this.wmonth = wmonth;
        this.wweek = wweek;
        this.userCode = userCode;
        this.nlevel = nlevel;
        this.recommendedCode = recommendedCode;
        this.recommendedCompanyCode = recommendedCompanyCode;
        this.recommendedOrderNo = recommendedOrderNo;
        this.recommendedOrderClass = recommendedOrderClass;
        this.recommendedOrderType = recommendedOrderType;
        this.pv = pv;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.Long"
     *             column="ID"
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
     *             column="NLEVEL"
     *             length="22"
     *         
     */

    public BigDecimal getNlevel() {
        return this.nlevel;
    }
    
    public void setNlevel(BigDecimal nlevel) {
        this.nlevel = nlevel;
    }
    /**       
     *      *            @hibernate.property
     *             column="RECOMMENDED_CODE"
     *             length="20"
     *         
     */

    public String getRecommendedCode() {
        return this.recommendedCode;
    }
    
    public void setRecommendedCode(String recommendedCode) {
        this.recommendedCode = recommendedCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="RECOMMENDED_COMPANY_CODE"
     *             length="2"
     *         
     */

    public String getRecommendedCompanyCode() {
        return this.recommendedCompanyCode;
    }
    
    public void setRecommendedCompanyCode(String recommendedCompanyCode) {
        this.recommendedCompanyCode = recommendedCompanyCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="RECOMMENDED_ORDER_NO"
     *             length="20"
     *         
     */

    public String getRecommendedOrderNo() {
        return this.recommendedOrderNo;
    }
    
    public void setRecommendedOrderNo(String recommendedOrderNo) {
        this.recommendedOrderNo = recommendedOrderNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="RECOMMENDED_ORDER_CLASS"
     *             length="2"
     *         
     */

    public String getRecommendedOrderClass() {
        return this.recommendedOrderClass;
    }
    
    public void setRecommendedOrderClass(String recommendedOrderClass) {
        this.recommendedOrderClass = recommendedOrderClass;
    }
    /**       
     *      *            @hibernate.property
     *             column="RECOMMENDED_ORDER_TYPE"
     *             length="2"
     *         
     */

    public String getRecommendedOrderType() {
        return this.recommendedOrderType;
    }
    
    public void setRecommendedOrderType(String recommendedOrderType) {
        this.recommendedOrderType = recommendedOrderType;
    }
    /**       
     *      *            @hibernate.property
     *             column="PV"
     *             length="22"
     *         
     */

    public BigDecimal getPv() {
        return this.pv;
    }
    
    public void setPv(BigDecimal pv) {
        this.pv = pv;
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
      buffer.append("nlevel").append("='").append(getNlevel()).append("' ");			
      buffer.append("recommendedCode").append("='").append(getRecommendedCode()).append("' ");			
      buffer.append("recommendedCompanyCode").append("='").append(getRecommendedCompanyCode()).append("' ");			
      buffer.append("recommendedOrderNo").append("='").append(getRecommendedOrderNo()).append("' ");			
      buffer.append("recommendedOrderClass").append("='").append(getRecommendedOrderClass()).append("' ");			
      buffer.append("recommendedOrderType").append("='").append(getRecommendedOrderType()).append("' ");			
      buffer.append("pv").append("='").append(getPv()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JbdSellCalcSubDetailHist) ) return false;
		 JbdSellCalcSubDetailHist castOther = ( JbdSellCalcSubDetailHist ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getWyear()==castOther.getWyear()) || ( this.getWyear()!=null && castOther.getWyear()!=null && this.getWyear().equals(castOther.getWyear()) ) )
 && ( (this.getWmonth()==castOther.getWmonth()) || ( this.getWmonth()!=null && castOther.getWmonth()!=null && this.getWmonth().equals(castOther.getWmonth()) ) )
 && ( (this.getWweek()==castOther.getWweek()) || ( this.getWweek()!=null && castOther.getWweek()!=null && this.getWweek().equals(castOther.getWweek()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getNlevel()==castOther.getNlevel()) || ( this.getNlevel()!=null && castOther.getNlevel()!=null && this.getNlevel().equals(castOther.getNlevel()) ) )
 && ( (this.getRecommendedCode()==castOther.getRecommendedCode()) || ( this.getRecommendedCode()!=null && castOther.getRecommendedCode()!=null && this.getRecommendedCode().equals(castOther.getRecommendedCode()) ) )
 && ( (this.getRecommendedCompanyCode()==castOther.getRecommendedCompanyCode()) || ( this.getRecommendedCompanyCode()!=null && castOther.getRecommendedCompanyCode()!=null && this.getRecommendedCompanyCode().equals(castOther.getRecommendedCompanyCode()) ) )
 && ( (this.getRecommendedOrderNo()==castOther.getRecommendedOrderNo()) || ( this.getRecommendedOrderNo()!=null && castOther.getRecommendedOrderNo()!=null && this.getRecommendedOrderNo().equals(castOther.getRecommendedOrderNo()) ) )
 && ( (this.getRecommendedOrderClass()==castOther.getRecommendedOrderClass()) || ( this.getRecommendedOrderClass()!=null && castOther.getRecommendedOrderClass()!=null && this.getRecommendedOrderClass().equals(castOther.getRecommendedOrderClass()) ) )
 && ( (this.getRecommendedOrderType()==castOther.getRecommendedOrderType()) || ( this.getRecommendedOrderType()!=null && castOther.getRecommendedOrderType()!=null && this.getRecommendedOrderType().equals(castOther.getRecommendedOrderType()) ) )
 && ( (this.getPv()==castOther.getPv()) || ( this.getPv()!=null && castOther.getPv()!=null && this.getPv().equals(castOther.getPv()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getWyear() == null ? 0 : this.getWyear().hashCode() );
         result = 37 * result + ( getWmonth() == null ? 0 : this.getWmonth().hashCode() );
         result = 37 * result + ( getWweek() == null ? 0 : this.getWweek().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getNlevel() == null ? 0 : this.getNlevel().hashCode() );
         result = 37 * result + ( getRecommendedCode() == null ? 0 : this.getRecommendedCode().hashCode() );
         result = 37 * result + ( getRecommendedCompanyCode() == null ? 0 : this.getRecommendedCompanyCode().hashCode() );
         result = 37 * result + ( getRecommendedOrderNo() == null ? 0 : this.getRecommendedOrderNo().hashCode() );
         result = 37 * result + ( getRecommendedOrderClass() == null ? 0 : this.getRecommendedOrderClass().hashCode() );
         result = 37 * result + ( getRecommendedOrderType() == null ? 0 : this.getRecommendedOrderType().hashCode() );
         result = 37 * result + ( getPv() == null ? 0 : this.getPv().hashCode() );
         return result;
   }   





}
