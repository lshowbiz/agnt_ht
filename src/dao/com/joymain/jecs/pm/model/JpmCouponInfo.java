package com.joymain.jecs.pm.model;
// Generated 2017-5-18 17:12:48 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JPM_COUPON_INFO"
 *     
 */

public class JpmCouponInfo extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long cpId;
    private String cpName;
    private BigDecimal cpValue;
    private String status;
    private Date startTime;
    private Date endTime;
    private Date createTime;
    private String createUserCode;
    private Date updateTime;
    private String updateUserCode;
    private String remark;

    private Set<JpmCouponRelationship> jpmCouponRelationships = new HashSet(); 
    // Constructors

    /** default constructor */
    public JpmCouponInfo() {
    }

    
    /** full constructor */
    public JpmCouponInfo(String cpName, BigDecimal cpValue, String status, Date startTime, Date endTime, Date createTime, String createUserCode, Date updateTime, String updateUserCode, String remark) {
        this.cpName = cpName;
        this.cpValue = cpValue;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createTime = createTime;
        this.createUserCode = createUserCode;
        this.updateTime = updateTime;
        this.updateUserCode = updateUserCode;
        this.remark = remark;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="CP_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_PM"
     *         
     */
    public Long getCpId() {
        return this.cpId;
    }
    
    public void setCpId(Long cpId) {
        this.cpId = cpId;
    }
    /**       
     *      *            @hibernate.property
     *             column="CP_NAME"
     *             length="50"
     *         
     */

    public String getCpName() {
        return this.cpName;
    }
    
    public void setCpName(String cpName) {
        this.cpName = cpName;
    }
    /**       
     *      *            @hibernate.property
     *             column="CP_VALUE"
     *             length="22"
     *         
     */

    public BigDecimal getCpValue() {
        return this.cpValue;
    }
    
    public void setCpValue(BigDecimal cpValue) {
        this.cpValue = cpValue;
    }
    /**       
     *      *            @hibernate.property
     *             column="STATUS"
     *             length="1"
     *         
     */

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    /**       
     *      *            @hibernate.property
     *             column="START_TIME"
     *             length="7"
     *         
     */

    public Date getStartTime() {
        return this.startTime;
    }
    
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="END_TIME"
     *             length="7"
     *         
     */

    public Date getEndTime() {
        return this.endTime;
    }
    
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
     *             column="UPDATE_TIME"
     *             length="7"
     *         
     */

    public Date getUpdateTime() {
        return this.updateTime;
    }
    
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="UPDATE_USER_CODE"
     *             length="20"
     *         
     */

    public String getUpdateUserCode() {
        return this.updateUserCode;
    }
    
    public void setUpdateUserCode(String updateUserCode) {
        this.updateUserCode = updateUserCode;
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
      buffer.append("cpName").append("='").append(getCpName()).append("' ");			
      buffer.append("cpValue").append("='").append(getCpValue()).append("' ");			
      buffer.append("status").append("='").append(getStatus()).append("' ");			
      buffer.append("startTime").append("='").append(getStartTime()).append("' ");			
      buffer.append("endTime").append("='").append(getEndTime()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("createUserCode").append("='").append(getCreateUserCode()).append("' ");			
      buffer.append("updateTime").append("='").append(getUpdateTime()).append("' ");			
      buffer.append("updateUserCode").append("='").append(getUpdateUserCode()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JpmCouponInfo) ) return false;
		 JpmCouponInfo castOther = ( JpmCouponInfo ) other; 
         
		 return ( (this.getCpId()==castOther.getCpId()) || ( this.getCpId()!=null && castOther.getCpId()!=null && this.getCpId().equals(castOther.getCpId()) ) )
 && ( (this.getCpName()==castOther.getCpName()) || ( this.getCpName()!=null && castOther.getCpName()!=null && this.getCpName().equals(castOther.getCpName()) ) )
 && ( (this.getCpValue()==castOther.getCpValue()) || ( this.getCpValue()!=null && castOther.getCpValue()!=null && this.getCpValue().equals(castOther.getCpValue()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
 && ( (this.getStartTime()==castOther.getStartTime()) || ( this.getStartTime()!=null && castOther.getStartTime()!=null && this.getStartTime().equals(castOther.getStartTime()) ) )
 && ( (this.getEndTime()==castOther.getEndTime()) || ( this.getEndTime()!=null && castOther.getEndTime()!=null && this.getEndTime().equals(castOther.getEndTime()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getCreateUserCode()==castOther.getCreateUserCode()) || ( this.getCreateUserCode()!=null && castOther.getCreateUserCode()!=null && this.getCreateUserCode().equals(castOther.getCreateUserCode()) ) )
 && ( (this.getUpdateTime()==castOther.getUpdateTime()) || ( this.getUpdateTime()!=null && castOther.getUpdateTime()!=null && this.getUpdateTime().equals(castOther.getUpdateTime()) ) )
 && ( (this.getUpdateUserCode()==castOther.getUpdateUserCode()) || ( this.getUpdateUserCode()!=null && castOther.getUpdateUserCode()!=null && this.getUpdateUserCode().equals(castOther.getUpdateUserCode()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getCpId() == null ? 0 : this.getCpId().hashCode() );
         result = 37 * result + ( getCpName() == null ? 0 : this.getCpName().hashCode() );
         result = 37 * result + ( getCpValue() == null ? 0 : this.getCpValue().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         result = 37 * result + ( getStartTime() == null ? 0 : this.getStartTime().hashCode() );
         result = 37 * result + ( getEndTime() == null ? 0 : this.getEndTime().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getCreateUserCode() == null ? 0 : this.getCreateUserCode().hashCode() );
         result = 37 * result + ( getUpdateTime() == null ? 0 : this.getUpdateTime().hashCode() );
         result = 37 * result + ( getUpdateUserCode() == null ? 0 : this.getUpdateUserCode().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         return result;
   }   

   /**
	 * *
	 * 
	 * @hibernate.set lazy="true" inverse="true" cascade="all-delete-orphan"
	 *                order-by="CP_ID"
	 * @hibernate.collection-key column="CP_ID"
	 * @hibernate.collection-one-to-many 
	 * class="com.joymain.jecs.pm.model.JpmCouponRelationship"
	 * 
	 */
	public Set<JpmCouponRelationship> getJpmCouponRelationships() {
		return jpmCouponRelationships;
	}


	public void setJpmCouponRelationships(
			Set<JpmCouponRelationship> jpmCouponRelationships) {
		this.jpmCouponRelationships = jpmCouponRelationships;
	}
}
