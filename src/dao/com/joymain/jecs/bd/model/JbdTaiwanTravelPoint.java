package com.joymain.jecs.bd.model;
// Generated 2010-1-30 11:32:36 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JBD_TAIWAN_TRAVEL_POINT"
 *     
 */

public class JbdTaiwanTravelPoint extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private BigDecimal id;
    private String companyCode;
    private String userCode;
    private String userName;
    private String phone;
    private BigDecimal passStar1;
    private BigDecimal passStar2;
    private BigDecimal passStar3;
    private BigDecimal passStar4;
    private BigDecimal recommendZuanshi;
    private BigDecimal recommendShenghuoguan;
    private BigDecimal shenghuoguanCheck;
    private BigDecimal total;


    // Constructors

    /** default constructor */
    public JbdTaiwanTravelPoint() {
    }

    
    /** full constructor */
    public JbdTaiwanTravelPoint(String companyCode, String userCode, String userName, String phone, BigDecimal passStar1, BigDecimal passStar2, BigDecimal passStar3, BigDecimal passStar4, BigDecimal recommendZuanshi, BigDecimal recommendShenghuoguan, BigDecimal shenghuoguanCheck, BigDecimal total) {
        this.companyCode = companyCode;
        this.userCode = userCode;
        this.userName = userName;
        this.phone = phone;
        this.passStar1 = passStar1;
        this.passStar2 = passStar2;
        this.passStar3 = passStar3;
        this.passStar4 = passStar4;
        this.recommendZuanshi = recommendZuanshi;
        this.recommendShenghuoguan = recommendShenghuoguan;
        this.shenghuoguanCheck = shenghuoguanCheck;
        this.total = total;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.math.BigDecimal"
     *             column="ID"
     *         
     */

    public BigDecimal getId() {
        return this.id;
    }
    
    public void setId(BigDecimal id) {
        this.id = id;
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
     *             column="USER_NAME"
     *             length="500"
     *         
     */

    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**       
     *      *            @hibernate.property
     *             column="PHONE"
     *             length="500"
     *         
     */

    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    /**       
     *      *            @hibernate.property
     *             column="PASS_STAR1"
     *             length="22"
     *         
     */

    public BigDecimal getPassStar1() {
        return this.passStar1;
    }
    
    public void setPassStar1(BigDecimal passStar1) {
        this.passStar1 = passStar1;
    }
    /**       
     *      *            @hibernate.property
     *             column="PASS_STAR2"
     *             length="22"
     *         
     */

    public BigDecimal getPassStar2() {
        return this.passStar2;
    }
    
    public void setPassStar2(BigDecimal passStar2) {
        this.passStar2 = passStar2;
    }
    /**       
     *      *            @hibernate.property
     *             column="PASS_STAR3"
     *             length="22"
     *         
     */

    public BigDecimal getPassStar3() {
        return this.passStar3;
    }
    
    public void setPassStar3(BigDecimal passStar3) {
        this.passStar3 = passStar3;
    }
    /**       
     *      *            @hibernate.property
     *             column="PASS_STAR4"
     *             length="22"
     *         
     */

    public BigDecimal getPassStar4() {
        return this.passStar4;
    }
    
    public void setPassStar4(BigDecimal passStar4) {
        this.passStar4 = passStar4;
    }
    /**       
     *      *            @hibernate.property
     *             column="RECOMMEND_ZUANSHI"
     *             length="22"
     *         
     */

    public BigDecimal getRecommendZuanshi() {
        return this.recommendZuanshi;
    }
    
    public void setRecommendZuanshi(BigDecimal recommendZuanshi) {
        this.recommendZuanshi = recommendZuanshi;
    }
    /**       
     *      *            @hibernate.property
     *             column="RECOMMEND_SHENGHUOGUAN"
     *             length="22"
     *         
     */

    public BigDecimal getRecommendShenghuoguan() {
        return this.recommendShenghuoguan;
    }
    
    public void setRecommendShenghuoguan(BigDecimal recommendShenghuoguan) {
        this.recommendShenghuoguan = recommendShenghuoguan;
    }
    /**       
     *      *            @hibernate.property
     *             column="SHENGHUOGUAN_CHECK"
     *             length="22"
     *         
     */

    public BigDecimal getShenghuoguanCheck() {
        return this.shenghuoguanCheck;
    }
    
    public void setShenghuoguanCheck(BigDecimal shenghuoguanCheck) {
        this.shenghuoguanCheck = shenghuoguanCheck;
    }
    /**       
     *      *            @hibernate.property
     *             column="TOTAL"
     *             length="22"
     *         
     */

    public BigDecimal getTotal() {
        return this.total;
    }
    
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("userName").append("='").append(getUserName()).append("' ");			
      buffer.append("phone").append("='").append(getPhone()).append("' ");			
      buffer.append("passStar1").append("='").append(getPassStar1()).append("' ");			
      buffer.append("passStar2").append("='").append(getPassStar2()).append("' ");			
      buffer.append("passStar3").append("='").append(getPassStar3()).append("' ");			
      buffer.append("passStar4").append("='").append(getPassStar4()).append("' ");			
      buffer.append("recommendZuanshi").append("='").append(getRecommendZuanshi()).append("' ");			
      buffer.append("recommendShenghuoguan").append("='").append(getRecommendShenghuoguan()).append("' ");			
      buffer.append("shenghuoguanCheck").append("='").append(getShenghuoguanCheck()).append("' ");			
      buffer.append("total").append("='").append(getTotal()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JbdTaiwanTravelPoint) ) return false;
		 JbdTaiwanTravelPoint castOther = ( JbdTaiwanTravelPoint ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getUserName()==castOther.getUserName()) || ( this.getUserName()!=null && castOther.getUserName()!=null && this.getUserName().equals(castOther.getUserName()) ) )
 && ( (this.getPhone()==castOther.getPhone()) || ( this.getPhone()!=null && castOther.getPhone()!=null && this.getPhone().equals(castOther.getPhone()) ) )
 && ( (this.getPassStar1()==castOther.getPassStar1()) || ( this.getPassStar1()!=null && castOther.getPassStar1()!=null && this.getPassStar1().equals(castOther.getPassStar1()) ) )
 && ( (this.getPassStar2()==castOther.getPassStar2()) || ( this.getPassStar2()!=null && castOther.getPassStar2()!=null && this.getPassStar2().equals(castOther.getPassStar2()) ) )
 && ( (this.getPassStar3()==castOther.getPassStar3()) || ( this.getPassStar3()!=null && castOther.getPassStar3()!=null && this.getPassStar3().equals(castOther.getPassStar3()) ) )
 && ( (this.getPassStar4()==castOther.getPassStar4()) || ( this.getPassStar4()!=null && castOther.getPassStar4()!=null && this.getPassStar4().equals(castOther.getPassStar4()) ) )
 && ( (this.getRecommendZuanshi()==castOther.getRecommendZuanshi()) || ( this.getRecommendZuanshi()!=null && castOther.getRecommendZuanshi()!=null && this.getRecommendZuanshi().equals(castOther.getRecommendZuanshi()) ) )
 && ( (this.getRecommendShenghuoguan()==castOther.getRecommendShenghuoguan()) || ( this.getRecommendShenghuoguan()!=null && castOther.getRecommendShenghuoguan()!=null && this.getRecommendShenghuoguan().equals(castOther.getRecommendShenghuoguan()) ) )
 && ( (this.getShenghuoguanCheck()==castOther.getShenghuoguanCheck()) || ( this.getShenghuoguanCheck()!=null && castOther.getShenghuoguanCheck()!=null && this.getShenghuoguanCheck().equals(castOther.getShenghuoguanCheck()) ) )
 && ( (this.getTotal()==castOther.getTotal()) || ( this.getTotal()!=null && castOther.getTotal()!=null && this.getTotal().equals(castOther.getTotal()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getUserName() == null ? 0 : this.getUserName().hashCode() );
         result = 37 * result + ( getPhone() == null ? 0 : this.getPhone().hashCode() );
         result = 37 * result + ( getPassStar1() == null ? 0 : this.getPassStar1().hashCode() );
         result = 37 * result + ( getPassStar2() == null ? 0 : this.getPassStar2().hashCode() );
         result = 37 * result + ( getPassStar3() == null ? 0 : this.getPassStar3().hashCode() );
         result = 37 * result + ( getPassStar4() == null ? 0 : this.getPassStar4().hashCode() );
         result = 37 * result + ( getRecommendZuanshi() == null ? 0 : this.getRecommendZuanshi().hashCode() );
         result = 37 * result + ( getRecommendShenghuoguan() == null ? 0 : this.getRecommendShenghuoguan().hashCode() );
         result = 37 * result + ( getShenghuoguanCheck() == null ? 0 : this.getShenghuoguanCheck().hashCode() );
         result = 37 * result + ( getTotal() == null ? 0 : this.getTotal().hashCode() );
         return result;
   }   





}
