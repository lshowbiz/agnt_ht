package com.joymain.jecs.bd.model;
// Generated 2010-10-26 11:36:45 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JBD_SUMMARY_LIST"
 *     
 */

public class JbdSummaryList extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private String userCode;
    private String cardType;
    private Integer inType;
    private Date createTime;
    private String orderType;
    private Date oldCheckDate;
    private Date newCheckDate;
    private BigDecimal pvAmt;
    private String oldLinkNo;
    private String newLinkNo;
    private String oldRecommendNo;
    private String newRecommendNo;
    private String newCompanyCode;
    private Date userCreateTime;
    private Integer wweek;
    private String orderNo; //订单mo_id 、退单编号


    // Constructors

    /** default constructor */
    public JbdSummaryList() {
    }

    
    /** full constructor */
    public JbdSummaryList(String userCode, String cardType, Integer inType, Date createTime, String orderType, Date oldCheckDate, Date newCheckDate, BigDecimal pvAmt, String oldLinkNo, String newLinkNo, String oldRecommendNo, String newRecommendNo, String newCompanyCode, Date userCreateTime, Integer wweek) {
        this.userCode = userCode;
        this.cardType = cardType;
        this.inType = inType;
        this.createTime = createTime;
        this.orderType = orderType;
        this.oldCheckDate = oldCheckDate;
        this.newCheckDate = newCheckDate;
        this.pvAmt = pvAmt;
        this.oldLinkNo = oldLinkNo;
        this.newLinkNo = newLinkNo;
        this.oldRecommendNo = oldRecommendNo;
        this.newRecommendNo = newRecommendNo;
        this.newCompanyCode = newCompanyCode;
        this.userCreateTime = userCreateTime;
        this.wweek = wweek;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ID"
     *@hibernate.generator-param name="sequence" value="JD_SEQ"
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
     *             column="CARD_TYPE"
     *             length="20"
     *         
     */

    public String getCardType() {
        return this.cardType;
    }
    
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
    /**       
     *      *            @hibernate.property
     *             column="IN_TYPE"
     *             length="22"
     *         
     */

    public Integer getInType() {
        return this.inType;
    }
    
    public void setInType(Integer inType) {
        this.inType = inType;
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
     *             column="OLD_CHECK_DATE"
     *             length="7"
     *         
     */

    public Date getOldCheckDate() {
        return this.oldCheckDate;
    }
    
    public void setOldCheckDate(Date oldCheckDate) {
        this.oldCheckDate = oldCheckDate;
    }
    /**       
     *      *            @hibernate.property
     *             column="NEW_CHECK_DATE"
     *             length="7"
     *         
     */

    public Date getNewCheckDate() {
        return this.newCheckDate;
    }
    
    public void setNewCheckDate(Date newCheckDate) {
        this.newCheckDate = newCheckDate;
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
     *             column="OLD_LINK_NO"
     *             length="20"
     *         
     */

    public String getOldLinkNo() {
        return this.oldLinkNo;
    }
    
    public void setOldLinkNo(String oldLinkNo) {
        this.oldLinkNo = oldLinkNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="NEW_LINK_NO"
     *             length="20"
     *         
     */

    public String getNewLinkNo() {
        return this.newLinkNo;
    }
    
    public void setNewLinkNo(String newLinkNo) {
        this.newLinkNo = newLinkNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="OLD_RECOMMEND_NO"
     *             length="20"
     *         
     */

    public String getOldRecommendNo() {
        return this.oldRecommendNo;
    }
    
    public void setOldRecommendNo(String oldRecommendNo) {
        this.oldRecommendNo = oldRecommendNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="NEW_RECOMMEND_NO"
     *             length="20"
     *         
     */

    public String getNewRecommendNo() {
        return this.newRecommendNo;
    }
    
    public void setNewRecommendNo(String newRecommendNo) {
        this.newRecommendNo = newRecommendNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="NEW_COMPANY_CODE"
     *             length="2"
     *         
     */

    public String getNewCompanyCode() {
        return this.newCompanyCode;
    }
    
    public void setNewCompanyCode(String newCompanyCode) {
        this.newCompanyCode = newCompanyCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="USER_CREATE_TIME"
     *             length="7"
     *         
     */

    public Date getUserCreateTime() {
        return this.userCreateTime;
    }
    
    public void setUserCreateTime(Date userCreateTime) {
        this.userCreateTime = userCreateTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="W_WEEK"
     *             length="22"
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
     *             column="ORDER_NO"
     *             length="20"
     *         
     */
    public String getOrderNo() {
		return orderNo;
	}


	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("cardType").append("='").append(getCardType()).append("' ");			
      buffer.append("inType").append("='").append(getInType()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("orderType").append("='").append(getOrderType()).append("' ");			
      buffer.append("oldCheckDate").append("='").append(getOldCheckDate()).append("' ");			
      buffer.append("newCheckDate").append("='").append(getNewCheckDate()).append("' ");			
      buffer.append("pvAmt").append("='").append(getPvAmt()).append("' ");			
      buffer.append("oldLinkNo").append("='").append(getOldLinkNo()).append("' ");			
      buffer.append("newLinkNo").append("='").append(getNewLinkNo()).append("' ");			
      buffer.append("oldRecommendNo").append("='").append(getOldRecommendNo()).append("' ");			
      buffer.append("newRecommendNo").append("='").append(getNewRecommendNo()).append("' ");			
      buffer.append("newCompanyCode").append("='").append(getNewCompanyCode()).append("' ");			
      buffer.append("userCreateTime").append("='").append(getUserCreateTime()).append("' ");			
      buffer.append("wweek").append("='").append(getWweek()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JbdSummaryList) ) return false;
		 JbdSummaryList castOther = ( JbdSummaryList ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getCardType()==castOther.getCardType()) || ( this.getCardType()!=null && castOther.getCardType()!=null && this.getCardType().equals(castOther.getCardType()) ) )
 && ( (this.getInType()==castOther.getInType()) || ( this.getInType()!=null && castOther.getInType()!=null && this.getInType().equals(castOther.getInType()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getOrderType()==castOther.getOrderType()) || ( this.getOrderType()!=null && castOther.getOrderType()!=null && this.getOrderType().equals(castOther.getOrderType()) ) )
 && ( (this.getOldCheckDate()==castOther.getOldCheckDate()) || ( this.getOldCheckDate()!=null && castOther.getOldCheckDate()!=null && this.getOldCheckDate().equals(castOther.getOldCheckDate()) ) )
 && ( (this.getNewCheckDate()==castOther.getNewCheckDate()) || ( this.getNewCheckDate()!=null && castOther.getNewCheckDate()!=null && this.getNewCheckDate().equals(castOther.getNewCheckDate()) ) )
 && ( (this.getPvAmt()==castOther.getPvAmt()) || ( this.getPvAmt()!=null && castOther.getPvAmt()!=null && this.getPvAmt().equals(castOther.getPvAmt()) ) )
 && ( (this.getOldLinkNo()==castOther.getOldLinkNo()) || ( this.getOldLinkNo()!=null && castOther.getOldLinkNo()!=null && this.getOldLinkNo().equals(castOther.getOldLinkNo()) ) )
 && ( (this.getNewLinkNo()==castOther.getNewLinkNo()) || ( this.getNewLinkNo()!=null && castOther.getNewLinkNo()!=null && this.getNewLinkNo().equals(castOther.getNewLinkNo()) ) )
 && ( (this.getOldRecommendNo()==castOther.getOldRecommendNo()) || ( this.getOldRecommendNo()!=null && castOther.getOldRecommendNo()!=null && this.getOldRecommendNo().equals(castOther.getOldRecommendNo()) ) )
 && ( (this.getNewRecommendNo()==castOther.getNewRecommendNo()) || ( this.getNewRecommendNo()!=null && castOther.getNewRecommendNo()!=null && this.getNewRecommendNo().equals(castOther.getNewRecommendNo()) ) )
 && ( (this.getNewCompanyCode()==castOther.getNewCompanyCode()) || ( this.getNewCompanyCode()!=null && castOther.getNewCompanyCode()!=null && this.getNewCompanyCode().equals(castOther.getNewCompanyCode()) ) )
 && ( (this.getUserCreateTime()==castOther.getUserCreateTime()) || ( this.getUserCreateTime()!=null && castOther.getUserCreateTime()!=null && this.getUserCreateTime().equals(castOther.getUserCreateTime()) ) )
 && ( (this.getWweek()==castOther.getWweek()) || ( this.getWweek()!=null && castOther.getWweek()!=null && this.getWweek().equals(castOther.getWweek()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getCardType() == null ? 0 : this.getCardType().hashCode() );
         result = 37 * result + ( getInType() == null ? 0 : this.getInType().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getOrderType() == null ? 0 : this.getOrderType().hashCode() );
         result = 37 * result + ( getOldCheckDate() == null ? 0 : this.getOldCheckDate().hashCode() );
         result = 37 * result + ( getNewCheckDate() == null ? 0 : this.getNewCheckDate().hashCode() );
         result = 37 * result + ( getPvAmt() == null ? 0 : this.getPvAmt().hashCode() );
         result = 37 * result + ( getOldLinkNo() == null ? 0 : this.getOldLinkNo().hashCode() );
         result = 37 * result + ( getNewLinkNo() == null ? 0 : this.getNewLinkNo().hashCode() );
         result = 37 * result + ( getOldRecommendNo() == null ? 0 : this.getOldRecommendNo().hashCode() );
         result = 37 * result + ( getNewRecommendNo() == null ? 0 : this.getNewRecommendNo().hashCode() );
         result = 37 * result + ( getNewCompanyCode() == null ? 0 : this.getNewCompanyCode().hashCode() );
         result = 37 * result + ( getUserCreateTime() == null ? 0 : this.getUserCreateTime().hashCode() );
         result = 37 * result + ( getWweek() == null ? 0 : this.getWweek().hashCode() );
         return result;
   }   





}
