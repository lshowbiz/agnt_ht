package com.joymain.jecs.bd.model;
// Generated 2010-11-1 14:09:20 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JBD_VENTURE_FUND_LIST"
 *     
 */

public class JbdVentureFundList extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private String userCode;
    private Integer wweek;
    private String recommendNo;
    private String orderNo;
    private String orderType;
    private BigDecimal pvAmt;
    private BigDecimal percentage;
    private Integer createWeek;
    private Integer calcStatus;
    private String remark;
    private Integer status;


    // Constructors

    /** default constructor */
    public JbdVentureFundList() {
    }

    
    /** full constructor */
    public JbdVentureFundList(String userCode, Integer wweek, String recommendNo, String orderNo, String orderType, BigDecimal pvAmt, BigDecimal percentage, Integer createWeek, Integer calcStatus, String remark) {
        this.userCode = userCode;
        this.wweek = wweek;
        this.recommendNo = recommendNo;
        this.orderNo = orderNo;
        this.orderType = orderType;
        this.pvAmt = pvAmt;
        this.percentage = percentage;
        this.createWeek = createWeek;
        this.calcStatus = calcStatus;
        this.remark = remark;
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
     *             column="RECOMMEND_NO"
     *             length="20"
     *         
     */

    public String getRecommendNo() {
        return this.recommendNo;
    }
    
    public void setRecommendNo(String recommendNo) {
        this.recommendNo = recommendNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="ORDER_NO"
     *             length="500"
     *         
     */

    public String getOrderNo() {
        return this.orderNo;
    }
    
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="ORDER_TYPE"
     *             length="2"
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
     *             column="PV_AMT"
     *             length="22"
     *         
     */

    public BigDecimal getPvAmt() {
        return this.pvAmt;
    }
    
    public void setPvAmt(BigDecimal pvAmt) {
        this.pvAmt = pvAmt;
    }
    /**       
     *      *            @hibernate.property
     *             column="PERCENTAGE"
     *             length="22"
     *         
     */

    public BigDecimal getPercentage() {
        return this.percentage;
    }
    
    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }
    /**       
     *      *            @hibernate.property
     *             column="CREATE_WEEK"
     *             length="22"
     *         
     */

    public Integer getCreateWeek() {
        return this.createWeek;
    }
    
    public void setCreateWeek(Integer createWeek) {
        this.createWeek = createWeek;
    }
    /**       
     *      *            @hibernate.property
     *             column="CALC_STATUS"
     *             length="22"
     *         
     */

    public Integer getCalcStatus() {
        return this.calcStatus;
    }
    
    public void setCalcStatus(Integer calcStatus) {
        this.calcStatus = calcStatus;
    }
    /**       
     *      *            @hibernate.property
     *             column="REMARK"
     *             length="2000"
     *         
     */

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("wweek").append("='").append(getWweek()).append("' ");			
      buffer.append("recommendNo").append("='").append(getRecommendNo()).append("' ");			
      buffer.append("orderNo").append("='").append(getOrderNo()).append("' ");			
      buffer.append("orderType").append("='").append(getOrderType()).append("' ");			
      buffer.append("pvAmt").append("='").append(getPvAmt()).append("' ");			
      buffer.append("percentage").append("='").append(getPercentage()).append("' ");			
      buffer.append("createWeek").append("='").append(getCreateWeek()).append("' ");			
      buffer.append("calcStatus").append("='").append(getCalcStatus()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JbdVentureFundList) ) return false;
		 JbdVentureFundList castOther = ( JbdVentureFundList ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getWweek()==castOther.getWweek()) || ( this.getWweek()!=null && castOther.getWweek()!=null && this.getWweek().equals(castOther.getWweek()) ) )
 && ( (this.getRecommendNo()==castOther.getRecommendNo()) || ( this.getRecommendNo()!=null && castOther.getRecommendNo()!=null && this.getRecommendNo().equals(castOther.getRecommendNo()) ) )
 && ( (this.getOrderNo()==castOther.getOrderNo()) || ( this.getOrderNo()!=null && castOther.getOrderNo()!=null && this.getOrderNo().equals(castOther.getOrderNo()) ) )
 && ( (this.getOrderType()==castOther.getOrderType()) || ( this.getOrderType()!=null && castOther.getOrderType()!=null && this.getOrderType().equals(castOther.getOrderType()) ) )
 && ( (this.getPvAmt()==castOther.getPvAmt()) || ( this.getPvAmt()!=null && castOther.getPvAmt()!=null && this.getPvAmt().equals(castOther.getPvAmt()) ) )
 && ( (this.getPercentage()==castOther.getPercentage()) || ( this.getPercentage()!=null && castOther.getPercentage()!=null && this.getPercentage().equals(castOther.getPercentage()) ) )
 && ( (this.getCreateWeek()==castOther.getCreateWeek()) || ( this.getCreateWeek()!=null && castOther.getCreateWeek()!=null && this.getCreateWeek().equals(castOther.getCreateWeek()) ) )
 && ( (this.getCalcStatus()==castOther.getCalcStatus()) || ( this.getCalcStatus()!=null && castOther.getCalcStatus()!=null && this.getCalcStatus().equals(castOther.getCalcStatus()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getWweek() == null ? 0 : this.getWweek().hashCode() );
         result = 37 * result + ( getRecommendNo() == null ? 0 : this.getRecommendNo().hashCode() );
         result = 37 * result + ( getOrderNo() == null ? 0 : this.getOrderNo().hashCode() );
         result = 37 * result + ( getOrderType() == null ? 0 : this.getOrderType().hashCode() );
         result = 37 * result + ( getPvAmt() == null ? 0 : this.getPvAmt().hashCode() );
         result = 37 * result + ( getPercentage() == null ? 0 : this.getPercentage().hashCode() );
         result = 37 * result + ( getCreateWeek() == null ? 0 : this.getCreateWeek().hashCode() );
         result = 37 * result + ( getCalcStatus() == null ? 0 : this.getCalcStatus().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         return result;
   }


   /**       
    *      *            @hibernate.property
    *             column="STATUS"
    *             length="22"
    *         
    */

public Integer getStatus() {
	return status;
}


public void setStatus(Integer status) {
	this.status = status;
}   





}
