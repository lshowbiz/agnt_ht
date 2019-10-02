package com.joymain.jecs.pd.model;
// Generated 2016-4-5 11:07:54 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="PD_NOT_CHANGE_PRODUCT"
 *     
 */

public class PdNotChangeProduct extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private String productNo;
    private String teamCode;
    private String orderType;
    private String companyCode;
    private String createUserCode;
    private Date createTime;
    private String isAvailable;
    private String other;


    // Constructors

    /** default constructor */
    public PdNotChangeProduct() {
    }

    
    /** full constructor */
    public PdNotChangeProduct(String productNo, String teamCode, String orderType, String companyCode, String createUserCode, Date createTime, String isAvailable, String other) {
        this.productNo = productNo;
        this.teamCode = teamCode;
        this.orderType = orderType;
        this.companyCode = companyCode;
        this.createUserCode = createUserCode;
        this.createTime = createTime;
        this.isAvailable = isAvailable;
        this.other = other;
    }
    

   
    // Property accessors
    /**
	 * * @hibernate.id generator-class="sequence"
	 * 
	 * column="ID"
	 * 
	 * @hibernate.generator-param name="sequence" value="SEQ_PD"
	 */
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    /**       
     *      *            @hibernate.property
     *             column="PRODUCT_NO"
     *             length="20"
     *         
     */

    public String getProductNo() {
        return this.productNo;
    }
    
    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="TEAM_CODE"
     *             length="10"
     *         
     */

    public String getTeamCode() {
        return this.teamCode;
    }
    
    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="ORDER_TYPE"
     *             length="3"
     *         
     */

    public String getOrderType() {
        return this.orderType;
    }
    
    public void setOrderType(String orderType) {
        this.orderType = orderType;
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
     *             column="CREATE_USER_CODE"
     *             length="20"
     *         
     */

    public String getCreateUserCode() {
        return this.createUserCode;
    }
    
    public void setCreateUserCode(String createUserCode) {
        this.createUserCode = createUserCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="CREATE_TIME"
     *             length="7"
     *         
     */

    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="IS_AVAILABLE"
     *             length="20"
     *         
     */

    public String getIsAvailable() {
        return this.isAvailable;
    }
    
    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }
    /**       
     *      *            @hibernate.property
     *             column="OTHER"
     *             length="200"
     *         
     */

    public String getOther() {
        return this.other;
    }
    
    public void setOther(String other) {
        this.other = other;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("productNo").append("='").append(getProductNo()).append("' ");			
      buffer.append("teamCode").append("='").append(getTeamCode()).append("' ");			
      buffer.append("orderType").append("='").append(getOrderType()).append("' ");			
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("createUserCode").append("='").append(getCreateUserCode()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("isAvailable").append("='").append(getIsAvailable()).append("' ");			
      buffer.append("other").append("='").append(getOther()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PdNotChangeProduct) ) return false;
		 PdNotChangeProduct castOther = ( PdNotChangeProduct ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getProductNo()==castOther.getProductNo()) || ( this.getProductNo()!=null && castOther.getProductNo()!=null && this.getProductNo().equals(castOther.getProductNo()) ) )
 && ( (this.getTeamCode()==castOther.getTeamCode()) || ( this.getTeamCode()!=null && castOther.getTeamCode()!=null && this.getTeamCode().equals(castOther.getTeamCode()) ) )
 && ( (this.getOrderType()==castOther.getOrderType()) || ( this.getOrderType()!=null && castOther.getOrderType()!=null && this.getOrderType().equals(castOther.getOrderType()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getCreateUserCode()==castOther.getCreateUserCode()) || ( this.getCreateUserCode()!=null && castOther.getCreateUserCode()!=null && this.getCreateUserCode().equals(castOther.getCreateUserCode()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getIsAvailable()==castOther.getIsAvailable()) || ( this.getIsAvailable()!=null && castOther.getIsAvailable()!=null && this.getIsAvailable().equals(castOther.getIsAvailable()) ) )
 && ( (this.getOther()==castOther.getOther()) || ( this.getOther()!=null && castOther.getOther()!=null && this.getOther().equals(castOther.getOther()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getProductNo() == null ? 0 : this.getProductNo().hashCode() );
         result = 37 * result + ( getTeamCode() == null ? 0 : this.getTeamCode().hashCode() );
         result = 37 * result + ( getOrderType() == null ? 0 : this.getOrderType().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getCreateUserCode() == null ? 0 : this.getCreateUserCode().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getIsAvailable() == null ? 0 : this.getIsAvailable().hashCode() );
         result = 37 * result + ( getOther() == null ? 0 : this.getOther().hashCode() );
         return result;
   }   





}
