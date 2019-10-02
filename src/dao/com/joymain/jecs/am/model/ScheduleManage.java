package com.joymain.jecs.am.model;
// Generated 2013-7-19 15:35:03 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="SCHEDULE_MANAGE"
 *     
 */

public class ScheduleManage extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private String scheduleName;
    private Date startTime;
    private Date endTime;
    private Integer priority;
    private Integer status;
    private BigDecimal linkmanId;
    private String remark;
    private Integer remind;
    private Integer repeat;
    private String loginUserNo;
    private Integer eventType;
    private String place;


    // Constructors

    /** default constructor */
    public ScheduleManage() {
    }

    
    /** full constructor */
    public ScheduleManage(String scheduleName, Date startTime, Date endTime, Integer priority, Integer status, BigDecimal linkmanId, String remark, Integer remind, Integer repeat, String loginUserNo, Integer eventType, String place) {
        this.scheduleName = scheduleName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
        this.status = status;
        this.linkmanId = linkmanId;
        this.remark = remark;
        this.remind = remind;
        this.repeat = repeat;
        this.loginUserNo = loginUserNo;
        this.eventType = eventType;
        this.place = place;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ID"
     *@hibernate.generator-param name="sequence" value="SEQ_SD"
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
     *             column="SCHEDULE_NAME"
     *             length="20"
     *         
     */

    public String getScheduleName() {
        return this.scheduleName;
    }
    
    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
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
     *             column="PRIORITY"
     *             length="2"
     *         
     */

    public Integer getPriority() {
        return this.priority;
    }
    
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
    /**       
     *      *            @hibernate.property
     *             column="STATUS"
     *             length="2"
     *         
     */

    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    /**       
     *      *            @hibernate.property
     *             column="LINKMAN_ID"
     *             length="22"
     *         
     */

    public BigDecimal getLinkmanId() {
        return this.linkmanId;
    }
    
    public void setLinkmanId(BigDecimal linkmanId) {
        this.linkmanId = linkmanId;
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
     *             column="REMIND"
     *             length="2"
     *         
     */

    public Integer getRemind() {
        return this.remind;
    }
    
    public void setRemind(Integer remind) {
        this.remind = remind;
    }
    /**       
     *      *            @hibernate.property
     *             column="REPEAT"
     *             length="2"
     *         
     */

    public Integer getRepeat() {
        return this.repeat;
    }
    
    public void setRepeat(Integer repeat) {
        this.repeat = repeat;
    }
    /**       
     *      *            @hibernate.property
     *             column="LOGIN_USER_NO"
     *             length="50"
     *         
     */

    public String getLoginUserNo() {
        return this.loginUserNo;
    }
    
    public void setLoginUserNo(String loginUserNo) {
        this.loginUserNo = loginUserNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="EVENT_TYPE"
     *             length="2"
     *         
     */

    public Integer getEventType() {
        return this.eventType;
    }
    
    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }
    /**       
     *      *            @hibernate.property
     *             column="PLACE"
     *             length="500"
     *         
     */

    public String getPlace() {
        return this.place;
    }
    
    public void setPlace(String place) {
        this.place = place;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("scheduleName").append("='").append(getScheduleName()).append("' ");			
      buffer.append("startTime").append("='").append(getStartTime()).append("' ");			
      buffer.append("endTime").append("='").append(getEndTime()).append("' ");			
      buffer.append("priority").append("='").append(getPriority()).append("' ");			
      buffer.append("status").append("='").append(getStatus()).append("' ");			
      buffer.append("linkmanId").append("='").append(getLinkmanId()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");			
      buffer.append("remind").append("='").append(getRemind()).append("' ");			
      buffer.append("repeat").append("='").append(getRepeat()).append("' ");			
      buffer.append("loginUserNo").append("='").append(getLoginUserNo()).append("' ");			
      buffer.append("eventType").append("='").append(getEventType()).append("' ");			
      buffer.append("place").append("='").append(getPlace()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ScheduleManage) ) return false;
		 ScheduleManage castOther = ( ScheduleManage ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getScheduleName()==castOther.getScheduleName()) || ( this.getScheduleName()!=null && castOther.getScheduleName()!=null && this.getScheduleName().equals(castOther.getScheduleName()) ) )
 && ( (this.getStartTime()==castOther.getStartTime()) || ( this.getStartTime()!=null && castOther.getStartTime()!=null && this.getStartTime().equals(castOther.getStartTime()) ) )
 && ( (this.getEndTime()==castOther.getEndTime()) || ( this.getEndTime()!=null && castOther.getEndTime()!=null && this.getEndTime().equals(castOther.getEndTime()) ) )
 && ( (this.getPriority()==castOther.getPriority()) || ( this.getPriority()!=null && castOther.getPriority()!=null && this.getPriority().equals(castOther.getPriority()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
 && ( (this.getLinkmanId()==castOther.getLinkmanId()) || ( this.getLinkmanId()!=null && castOther.getLinkmanId()!=null && this.getLinkmanId().equals(castOther.getLinkmanId()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
 && ( (this.getRemind()==castOther.getRemind()) || ( this.getRemind()!=null && castOther.getRemind()!=null && this.getRemind().equals(castOther.getRemind()) ) )
 && ( (this.getRepeat()==castOther.getRepeat()) || ( this.getRepeat()!=null && castOther.getRepeat()!=null && this.getRepeat().equals(castOther.getRepeat()) ) )
 && ( (this.getLoginUserNo()==castOther.getLoginUserNo()) || ( this.getLoginUserNo()!=null && castOther.getLoginUserNo()!=null && this.getLoginUserNo().equals(castOther.getLoginUserNo()) ) )
 && ( (this.getEventType()==castOther.getEventType()) || ( this.getEventType()!=null && castOther.getEventType()!=null && this.getEventType().equals(castOther.getEventType()) ) )
 && ( (this.getPlace()==castOther.getPlace()) || ( this.getPlace()!=null && castOther.getPlace()!=null && this.getPlace().equals(castOther.getPlace()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getScheduleName() == null ? 0 : this.getScheduleName().hashCode() );
         result = 37 * result + ( getStartTime() == null ? 0 : this.getStartTime().hashCode() );
         result = 37 * result + ( getEndTime() == null ? 0 : this.getEndTime().hashCode() );
         result = 37 * result + ( getPriority() == null ? 0 : this.getPriority().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         result = 37 * result + ( getLinkmanId() == null ? 0 : this.getLinkmanId().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         result = 37 * result + ( getRemind() == null ? 0 : this.getRemind().hashCode() );
         result = 37 * result + ( getRepeat() == null ? 0 : this.getRepeat().hashCode() );
         result = 37 * result + ( getLoginUserNo() == null ? 0 : this.getLoginUserNo().hashCode() );
         result = 37 * result + ( getEventType() == null ? 0 : this.getEventType().hashCode() );
         result = 37 * result + ( getPlace() == null ? 0 : this.getPlace().hashCode() );
         return result;
   }   





}
