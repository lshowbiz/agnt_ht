package com.joymain.jecs.mi.model;
// Generated 2016-7-28 14:27:00 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JMI_ASSURE"
 *     
 */

public class JmiAssure extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private String assureType;
    private String assureContent;
    private String userCode;
    private Date startTime;
    private Date endTime;
    private String isFlag;
    private String memo;
    private Date createTime;
    private String createUserCode;
    private Date updateTime;
    private String updateUserCode;
    
    //担保订单编号
    private String assureOrderCode;


    // Constructors

    /** default constructor */
    public JmiAssure() {
    }

	/** minimal constructor */
    public JmiAssure(String assureType, String userCode, Date startTime) {
        this.assureType = assureType;
        this.userCode = userCode;
        this.startTime = startTime;
    }
    
    /** full constructor */
    public JmiAssure(String assureType, String assureContent, String userCode, Date startTime, Date endTime, String isFlag, String memo, Date createTime, String createUserCode, Date updateTime, String updateUserCode) {
        this.assureType = assureType;
        this.assureContent = assureContent;
        this.userCode = userCode;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isFlag = isFlag;
        this.memo = memo;
        this.createTime = createTime;
        this.createUserCode = createUserCode;
        this.updateTime = updateTime;
        this.updateUserCode = updateUserCode;
    }
    

   
    // Property accessors
    
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ID"
     *@hibernate.generator-param name="sequence" value="SEQ_MI"
     */

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    /**       
     *      *            @hibernate.property
     *             column="ASSURE_TYPE"
     *             length="1"
     *             not-null="true"
     *         
     */

    public String getAssureType() {
        return this.assureType;
    }
    
    public void setAssureType(String assureType) {
        this.assureType = assureType;
    }
    /**       
     *      *            @hibernate.property
     *             column="ASSURE_CONTENT"
     *             length="1"
     *         
     */

    public String getAssureContent() {
        return this.assureContent;
    }
    
    public void setAssureContent(String assureContent) {
        this.assureContent = assureContent;
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
     *             column="IS_FLAG"
     *             length="1"
     *         
     */

    public String getIsFlag() {
        return this.isFlag;
    }
    
    public void setIsFlag(String isFlag) {
        this.isFlag = isFlag;
    }
    /**       
     *      *            @hibernate.property
     *             column="MEMO"
     *             length="200"
     *         
     */

    public String getMemo() {
        return this.memo;
    }
    
    public void setMemo(String memo) {
        this.memo = memo;
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
     *             column="ASSURE_ORDER_CODE"
     *             length="1000"
     *         
     */
    public String getAssureOrderCode() {
		return assureOrderCode;
	}

	public void setAssureOrderCode(String assureOrderCode) {
		this.assureOrderCode = assureOrderCode;
	}

	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("assureType").append("='").append(getAssureType()).append("' ");			
      buffer.append("assureContent").append("='").append(getAssureContent()).append("' ");			
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("startTime").append("='").append(getStartTime()).append("' ");			
      buffer.append("endTime").append("='").append(getEndTime()).append("' ");			
      buffer.append("isFlag").append("='").append(getIsFlag()).append("' ");			
      buffer.append("memo").append("='").append(getMemo()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("createUserCode").append("='").append(getCreateUserCode()).append("' ");			
      buffer.append("updateTime").append("='").append(getUpdateTime()).append("' ");			
      buffer.append("updateUserCode").append("='").append(getUpdateUserCode()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JmiAssure) ) return false;
		 JmiAssure castOther = ( JmiAssure ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getAssureType()==castOther.getAssureType()) || ( this.getAssureType()!=null && castOther.getAssureType()!=null && this.getAssureType().equals(castOther.getAssureType()) ) )
 && ( (this.getAssureContent()==castOther.getAssureContent()) || ( this.getAssureContent()!=null && castOther.getAssureContent()!=null && this.getAssureContent().equals(castOther.getAssureContent()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getStartTime()==castOther.getStartTime()) || ( this.getStartTime()!=null && castOther.getStartTime()!=null && this.getStartTime().equals(castOther.getStartTime()) ) )
 && ( (this.getEndTime()==castOther.getEndTime()) || ( this.getEndTime()!=null && castOther.getEndTime()!=null && this.getEndTime().equals(castOther.getEndTime()) ) )
 && ( (this.getIsFlag()==castOther.getIsFlag()) || ( this.getIsFlag()!=null && castOther.getIsFlag()!=null && this.getIsFlag().equals(castOther.getIsFlag()) ) )
 && ( (this.getMemo()==castOther.getMemo()) || ( this.getMemo()!=null && castOther.getMemo()!=null && this.getMemo().equals(castOther.getMemo()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getCreateUserCode()==castOther.getCreateUserCode()) || ( this.getCreateUserCode()!=null && castOther.getCreateUserCode()!=null && this.getCreateUserCode().equals(castOther.getCreateUserCode()) ) )
 && ( (this.getUpdateTime()==castOther.getUpdateTime()) || ( this.getUpdateTime()!=null && castOther.getUpdateTime()!=null && this.getUpdateTime().equals(castOther.getUpdateTime()) ) )
 && ( (this.getUpdateUserCode()==castOther.getUpdateUserCode()) || ( this.getUpdateUserCode()!=null && castOther.getUpdateUserCode()!=null && this.getUpdateUserCode().equals(castOther.getUpdateUserCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getAssureType() == null ? 0 : this.getAssureType().hashCode() );
         result = 37 * result + ( getAssureContent() == null ? 0 : this.getAssureContent().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getStartTime() == null ? 0 : this.getStartTime().hashCode() );
         result = 37 * result + ( getEndTime() == null ? 0 : this.getEndTime().hashCode() );
         result = 37 * result + ( getIsFlag() == null ? 0 : this.getIsFlag().hashCode() );
         result = 37 * result + ( getMemo() == null ? 0 : this.getMemo().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getCreateUserCode() == null ? 0 : this.getCreateUserCode().hashCode() );
         result = 37 * result + ( getUpdateTime() == null ? 0 : this.getUpdateTime().hashCode() );
         result = 37 * result + ( getUpdateUserCode() == null ? 0 : this.getUpdateUserCode().hashCode() );
         return result;
   }   





}
