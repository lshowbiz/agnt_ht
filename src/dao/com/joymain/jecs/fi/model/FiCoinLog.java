package com.joymain.jecs.fi.model;
// Generated 2010-8-4 15:17:28 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="FI_COIN_LOG"
 *     
 */

public class FiCoinLog extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long fclId;
    private String userCode;
    private String coinType;
    private String dealType;
    private Date createTime;
    private String createUser;
    private String receiveCoin;
    private BigDecimal coin;
    private String uniqueCode;
    private String status;


    // Constructors

    /** default constructor */
    public FiCoinLog() {
    }

    
    /** full constructor */
    public FiCoinLog(String userCode, String coinType, String dealType, Date createTime, String createUser, String receiveCoin, BigDecimal coin, String uniqueCode, String status) {
        this.userCode = userCode;
        this.coinType = coinType;
        this.dealType = dealType;
        this.createTime = createTime;
        this.createUser = createUser;
        this.receiveCoin = receiveCoin;
        this.coin = coin;
        this.uniqueCode = uniqueCode;
        this.status = status;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="FCL_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_BANKBOOK"
     *         
     */

    public Long getFclId() {
        return this.fclId;
    }
    
    public void setFclId(Long fclId) {
        this.fclId = fclId;
    }
    /**       
     *      *            @hibernate.property
     *             column="USER_CODE"
     *             length="20"
     *             not-null="true"
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
     *             column="COIN_TYPE"
     *             length="2"
     *             not-null="true"
     *         
     */

    public String getCoinType() {
        return this.coinType;
    }
    
    public void setCoinType(String coinType) {
        this.coinType = coinType;
    }
    /**       
     *      *            @hibernate.property
     *             column="DEAL_TYPE"
     *             length="2"
     *             not-null="true"
     *         
     */

    public String getDealType() {
        return this.dealType;
    }
    
    public void setDealType(String dealType) {
        this.dealType = dealType;
    }
    /**       
     *      *            @hibernate.property
     *             column="CREATE_TIME"
     *             length="7"
     *             not-null="true"
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
     *             column="CREATE_USER"
     *             length="20"
     *             not-null="true"
     *         
     */

    public String getCreateUser() {
        return this.createUser;
    }
    
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    /**       
     *      *            @hibernate.property
     *             column="RECEIVE_COIN"
     *             length="4000"
     *             not-null="true"
     *         
     */

    public String getReceiveCoin() {
        return this.receiveCoin;
    }
    
    public void setReceiveCoin(String receiveCoin) {
        this.receiveCoin = receiveCoin;
    }
    /**       
     *      *            @hibernate.property
     *             column="COIN"
     *             length="18"
     *             not-null="true"
     *         
     */

    public BigDecimal getCoin() {
        return this.coin;
    }
    
    public void setCoin(BigDecimal coin) {
        this.coin = coin;
    }
    /**       
     *      *            @hibernate.property
     *             column="UNIQUE_CODE"
     *             length="4000"
     *             not-null="true"
     *         
     */

    public String getUniqueCode() {
        return this.uniqueCode;
    }
    
    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="STATUS"
     *             length="2"
     *             not-null="true"
     *         
     */

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("coinType").append("='").append(getCoinType()).append("' ");			
      buffer.append("dealType").append("='").append(getDealType()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("createUser").append("='").append(getCreateUser()).append("' ");			
      buffer.append("receiveCoin").append("='").append(getReceiveCoin()).append("' ");			
      buffer.append("coin").append("='").append(getCoin()).append("' ");			
      buffer.append("uniqueCode").append("='").append(getUniqueCode()).append("' ");			
      buffer.append("status").append("='").append(getStatus()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof FiCoinLog) ) return false;
		 FiCoinLog castOther = ( FiCoinLog ) other; 
         
		 return ( (this.getFclId()==castOther.getFclId()) || ( this.getFclId()!=null && castOther.getFclId()!=null && this.getFclId().equals(castOther.getFclId()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getCoinType()==castOther.getCoinType()) || ( this.getCoinType()!=null && castOther.getCoinType()!=null && this.getCoinType().equals(castOther.getCoinType()) ) )
 && ( (this.getDealType()==castOther.getDealType()) || ( this.getDealType()!=null && castOther.getDealType()!=null && this.getDealType().equals(castOther.getDealType()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getCreateUser()==castOther.getCreateUser()) || ( this.getCreateUser()!=null && castOther.getCreateUser()!=null && this.getCreateUser().equals(castOther.getCreateUser()) ) )
 && ( (this.getReceiveCoin()==castOther.getReceiveCoin()) || ( this.getReceiveCoin()!=null && castOther.getReceiveCoin()!=null && this.getReceiveCoin().equals(castOther.getReceiveCoin()) ) )
 && ( (this.getCoin()==castOther.getCoin()) || ( this.getCoin()!=null && castOther.getCoin()!=null && this.getCoin().equals(castOther.getCoin()) ) )
 && ( (this.getUniqueCode()==castOther.getUniqueCode()) || ( this.getUniqueCode()!=null && castOther.getUniqueCode()!=null && this.getUniqueCode().equals(castOther.getUniqueCode()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getFclId() == null ? 0 : this.getFclId().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getCoinType() == null ? 0 : this.getCoinType().hashCode() );
         result = 37 * result + ( getDealType() == null ? 0 : this.getDealType().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getCreateUser() == null ? 0 : this.getCreateUser().hashCode() );
         result = 37 * result + ( getReceiveCoin() == null ? 0 : this.getReceiveCoin().hashCode() );
         result = 37 * result + ( getCoin() == null ? 0 : this.getCoin().hashCode() );
         result = 37 * result + ( getUniqueCode() == null ? 0 : this.getUniqueCode().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         return result;
   }   





}
