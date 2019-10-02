package com.joymain.jecs.bd.model;
// Generated 2013-11-1 10:07:36 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JBD_TRAVEL_POINT_LOG2014"
 *     
 */

public class JbdTravelPointLog2014 extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long logId;
    private String userCode;
    private String createUser;
    private Date createTime;
    private BigDecimal usePoint;
    private String dealType;
    private String remark;
    private Integer wmonth;
    private Integer passStar;


    // Constructors

    /** default constructor */
    public JbdTravelPointLog2014() {
    }

    
    /** full constructor */
    public JbdTravelPointLog2014(String userCode, String createUser, Date createTime, BigDecimal usePoint, String dealType, String remark, Integer wmonth, BigDecimal passStar) {
        this.userCode = userCode;
        this.createUser = createUser;
        this.createTime = createTime;
        this.usePoint = usePoint;
        this.dealType = dealType;
        this.remark = remark;
        this.wmonth = wmonth;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="LOG_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_BD"
     *         
     */

    public Long getLogId() {
        return this.logId;
    }
    
    public void setLogId(Long logId) {
        this.logId = logId;
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
     *             column="CREATE_USER"
     *             length="20"
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
     *             column="USE_POINT"
     *             length="22"
     *         
     */

    public BigDecimal getUsePoint() {
        return this.usePoint;
    }
    
    public void setUsePoint(BigDecimal usePoint) {
        this.usePoint = usePoint;
    }
    /**       
     *      *            @hibernate.property
     *             column="DEAL_TYPE"
     *             length="1"
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
     *             column="REMARK"
     *             length="500"
     *         
     */

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
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
     *             column="PASS_STAR"
     *             length="22"
     *         
     */

    public Integer getPassStar() {
        return this.passStar;
    }
    
    public void setPassStar(Integer passStar) {
        this.passStar = passStar;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("createUser").append("='").append(getCreateUser()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("usePoint").append("='").append(getUsePoint()).append("' ");			
      buffer.append("dealType").append("='").append(getDealType()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");			
      buffer.append("wmonth").append("='").append(getWmonth()).append("' ");			
      buffer.append("passStar").append("='").append(getPassStar()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JbdTravelPointLog2014) ) return false;
		 JbdTravelPointLog2014 castOther = ( JbdTravelPointLog2014 ) other; 
         
		 return ( (this.getLogId()==castOther.getLogId()) || ( this.getLogId()!=null && castOther.getLogId()!=null && this.getLogId().equals(castOther.getLogId()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getCreateUser()==castOther.getCreateUser()) || ( this.getCreateUser()!=null && castOther.getCreateUser()!=null && this.getCreateUser().equals(castOther.getCreateUser()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getUsePoint()==castOther.getUsePoint()) || ( this.getUsePoint()!=null && castOther.getUsePoint()!=null && this.getUsePoint().equals(castOther.getUsePoint()) ) )
 && ( (this.getDealType()==castOther.getDealType()) || ( this.getDealType()!=null && castOther.getDealType()!=null && this.getDealType().equals(castOther.getDealType()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
 && ( (this.getWmonth()==castOther.getWmonth()) || ( this.getWmonth()!=null && castOther.getWmonth()!=null && this.getWmonth().equals(castOther.getWmonth()) ) )
 && ( (this.getPassStar()==castOther.getPassStar()) || ( this.getPassStar()!=null && castOther.getPassStar()!=null && this.getPassStar().equals(castOther.getPassStar()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getLogId() == null ? 0 : this.getLogId().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getCreateUser() == null ? 0 : this.getCreateUser().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getUsePoint() == null ? 0 : this.getUsePoint().hashCode() );
         result = 37 * result + ( getDealType() == null ? 0 : this.getDealType().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         result = 37 * result + ( getWmonth() == null ? 0 : this.getWmonth().hashCode() );
         result = 37 * result + ( getPassStar() == null ? 0 : this.getPassStar().hashCode() );
         return result;
   }   





}
