package com.joymain.jecs.bd.model;
// Generated 2013-11-1 10:07:19 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JBD_TRAVEL_POINT_DETAIL2014"
 *     
 */

public class JbdTravelPointDetail2014 extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private String userCode;
    private String travelType;
    private BigDecimal usePoint;
    private String createUser;
    private Date createTime;


    // Constructors

    /** default constructor */
    public JbdTravelPointDetail2014() {
    }

    
    /** full constructor */
    public JbdTravelPointDetail2014(String userCode, String travelType, BigDecimal usePoint, String createUser, Date createTime) {
        this.userCode = userCode;
        this.travelType = travelType;
        this.usePoint = usePoint;
        this.createUser = createUser;
        this.createTime = createTime;
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
     *             column="TRAVEL_TYPE"
     *             length="1"
     *         
     */

    public String getTravelType() {
        return this.travelType;
    }
    
    public void setTravelType(String travelType) {
        this.travelType = travelType;
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
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("travelType").append("='").append(getTravelType()).append("' ");			
      buffer.append("usePoint").append("='").append(getUsePoint()).append("' ");			
      buffer.append("createUser").append("='").append(getCreateUser()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JbdTravelPointDetail2014) ) return false;
		 JbdTravelPointDetail2014 castOther = ( JbdTravelPointDetail2014 ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getTravelType()==castOther.getTravelType()) || ( this.getTravelType()!=null && castOther.getTravelType()!=null && this.getTravelType().equals(castOther.getTravelType()) ) )
 && ( (this.getUsePoint()==castOther.getUsePoint()) || ( this.getUsePoint()!=null && castOther.getUsePoint()!=null && this.getUsePoint().equals(castOther.getUsePoint()) ) )
 && ( (this.getCreateUser()==castOther.getCreateUser()) || ( this.getCreateUser()!=null && castOther.getCreateUser()!=null && this.getCreateUser().equals(castOther.getCreateUser()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getTravelType() == null ? 0 : this.getTravelType().hashCode() );
         result = 37 * result + ( getUsePoint() == null ? 0 : this.getUsePoint().hashCode() );
         result = 37 * result + ( getCreateUser() == null ? 0 : this.getCreateUser().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         return result;
   }   





}
