package com.joymain.jecs.bd.model;
// Generated 2009-10-11 15:08:36 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JBD_VENTURE_LEADER_SUB_HIST"
 *     
 */

public class JbdVentureLeaderSubHist extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private Integer wyear;
    private Integer wmonth;
    private Integer wweek;
    private String companyCode;
    private String userCode;
    private String bounsType;
    private BigDecimal nlevel;
    private String recommendedCode;
    private BigDecimal passGroupPv;
    private BigDecimal bounsPoint;
    private BigDecimal bounsMoney;


    // Constructors

    /** default constructor */
    public JbdVentureLeaderSubHist() {
    }

    
    /** full constructor */
    public JbdVentureLeaderSubHist(Integer wyear, Integer wmonth, Integer wweek, String companyCode, String userCode, String bounsType, BigDecimal nlevel, String recommendedCode, BigDecimal passGroupPv, BigDecimal bounsPoint, BigDecimal bounsMoney) {
        this.wyear = wyear;
        this.wmonth = wmonth;
        this.wweek = wweek;
        this.companyCode = companyCode;
        this.userCode = userCode;
        this.bounsType = bounsType;
        this.nlevel = nlevel;
        this.recommendedCode = recommendedCode;
        this.passGroupPv = passGroupPv;
        this.bounsPoint = bounsPoint;
        this.bounsMoney = bounsMoney;
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
     *             column="COMPANY_CODE"
     *             length="2"
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
     *             column="BOUNS_TYPE"
     *             length="2"
     *         
     */

    public String getBounsType() {
        return this.bounsType;
    }
    
    public void setBounsType(String bounsType) {
        this.bounsType = bounsType;
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
     *             column="PASS_GROUP_PV"
     *             length="22"
     *         
     */

    public BigDecimal getPassGroupPv() {
        return this.passGroupPv;
    }
    
    public void setPassGroupPv(BigDecimal passGroupPv) {
        this.passGroupPv = passGroupPv;
    }
    /**       
     *      *            @hibernate.property
     *             column="BOUNS_POINT"
     *             length="22"
     *         
     */

    public BigDecimal getBounsPoint() {
        return this.bounsPoint;
    }
    
    public void setBounsPoint(BigDecimal bounsPoint) {
        this.bounsPoint = bounsPoint;
    }
    /**       
     *      *            @hibernate.property
     *             column="BOUNS_MONEY"
     *             length="22"
     *         
     */

    public BigDecimal getBounsMoney() {
        return this.bounsMoney;
    }
    
    public void setBounsMoney(BigDecimal bounsMoney) {
        this.bounsMoney = bounsMoney;
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
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("bounsType").append("='").append(getBounsType()).append("' ");			
      buffer.append("nlevel").append("='").append(getNlevel()).append("' ");			
      buffer.append("recommendedCode").append("='").append(getRecommendedCode()).append("' ");			
      buffer.append("passGroupPv").append("='").append(getPassGroupPv()).append("' ");			
      buffer.append("bounsPoint").append("='").append(getBounsPoint()).append("' ");			
      buffer.append("bounsMoney").append("='").append(getBounsMoney()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JbdVentureLeaderSubHist) ) return false;
		 JbdVentureLeaderSubHist castOther = ( JbdVentureLeaderSubHist ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getWyear()==castOther.getWyear()) || ( this.getWyear()!=null && castOther.getWyear()!=null && this.getWyear().equals(castOther.getWyear()) ) )
 && ( (this.getWmonth()==castOther.getWmonth()) || ( this.getWmonth()!=null && castOther.getWmonth()!=null && this.getWmonth().equals(castOther.getWmonth()) ) )
 && ( (this.getWweek()==castOther.getWweek()) || ( this.getWweek()!=null && castOther.getWweek()!=null && this.getWweek().equals(castOther.getWweek()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getBounsType()==castOther.getBounsType()) || ( this.getBounsType()!=null && castOther.getBounsType()!=null && this.getBounsType().equals(castOther.getBounsType()) ) )
 && ( (this.getNlevel()==castOther.getNlevel()) || ( this.getNlevel()!=null && castOther.getNlevel()!=null && this.getNlevel().equals(castOther.getNlevel()) ) )
 && ( (this.getRecommendedCode()==castOther.getRecommendedCode()) || ( this.getRecommendedCode()!=null && castOther.getRecommendedCode()!=null && this.getRecommendedCode().equals(castOther.getRecommendedCode()) ) )
 && ( (this.getPassGroupPv()==castOther.getPassGroupPv()) || ( this.getPassGroupPv()!=null && castOther.getPassGroupPv()!=null && this.getPassGroupPv().equals(castOther.getPassGroupPv()) ) )
 && ( (this.getBounsPoint()==castOther.getBounsPoint()) || ( this.getBounsPoint()!=null && castOther.getBounsPoint()!=null && this.getBounsPoint().equals(castOther.getBounsPoint()) ) )
 && ( (this.getBounsMoney()==castOther.getBounsMoney()) || ( this.getBounsMoney()!=null && castOther.getBounsMoney()!=null && this.getBounsMoney().equals(castOther.getBounsMoney()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getWyear() == null ? 0 : this.getWyear().hashCode() );
         result = 37 * result + ( getWmonth() == null ? 0 : this.getWmonth().hashCode() );
         result = 37 * result + ( getWweek() == null ? 0 : this.getWweek().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getBounsType() == null ? 0 : this.getBounsType().hashCode() );
         result = 37 * result + ( getNlevel() == null ? 0 : this.getNlevel().hashCode() );
         result = 37 * result + ( getRecommendedCode() == null ? 0 : this.getRecommendedCode().hashCode() );
         result = 37 * result + ( getPassGroupPv() == null ? 0 : this.getPassGroupPv().hashCode() );
         result = 37 * result + ( getBounsPoint() == null ? 0 : this.getBounsPoint().hashCode() );
         result = 37 * result + ( getBounsMoney() == null ? 0 : this.getBounsMoney().hashCode() );
         return result;
   }   





}
