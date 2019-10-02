package com.joymain.jecs.am.model;
// Generated 2010-9-30 9:12:24 by Hibernate Tools 3.1.0.beta4

import java.sql.Clob;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JAM_PROMOTION"
 *     
 */

public class JamPromotion extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private String companyCode;
    private String pmName;
    private String pmTarget;
    private String pmWay;
    private String pmType;
    private Date noticeDate;
    private Date startTime;
    private Date endTime;
    private String status;
    private String targetGroup;
    private String detail;
    private String proposer;
    private String analyzed;
    private String createUser;
    private Date createDate;


    // Constructors

    /** default constructor */
    public JamPromotion() {
    }

    
    /** full constructor */
    public JamPromotion(String companyCode, String pmName, String pmTarget, String pmWay, String pmType, Date noticeDate, Date startTime, Date endTime, String status, String targetGroup, String detail, String proposer, String analyzed, String createUser, Date createDate) {
        this.companyCode = companyCode;
        this.pmName = pmName;
        this.pmTarget = pmTarget;
        this.pmWay = pmWay;
        this.pmType = pmType;
        this.noticeDate = noticeDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.targetGroup = targetGroup;
        this.detail = detail;
        this.proposer = proposer;
        this.analyzed = analyzed;
        this.createUser = createUser;
        this.createDate = createDate;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ID"
     *         @hibernate.generator-param name="sequence" value="SEQ_AM" 
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
     *             column="PM_NAME"
     *             length="50"
     *         
     */

    public String getPmName() {
        return this.pmName;
    }
    
    public void setPmName(String pmName) {
        this.pmName = pmName;
    }
    /**       
     *      *            @hibernate.property
     *             column="PM_TARGET"
     *             length="50"
     *         
     */

    public String getPmTarget() {
        return this.pmTarget;
    }
    
    public void setPmTarget(String pmTarget) {
        this.pmTarget = pmTarget;
    }
    /**       
     *      *            @hibernate.property
     *             column="PM_WAY"
     *             length="2"
     *         
     */

    public String getPmWay() {
        return this.pmWay;
    }
    
    public void setPmWay(String pmWay) {
        this.pmWay = pmWay;
    }
    /**       
     *      *            @hibernate.property
     *             column="PM_TYPE"
     *             length="2"
     *         
     */

    public String getPmType() {
        return this.pmType;
    }
    
    public void setPmType(String pmType) {
        this.pmType = pmType;
    }
    /**       
     *      *            @hibernate.property
     *             column="NOTICE_DATE"
     *             length="7"
     *         
     */

    public Date getNoticeDate() {
        return this.noticeDate;
    }
    
    public void setNoticeDate(Date noticeDate) {
        this.noticeDate = noticeDate;
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
     *             column="TARGET_GROUP"
     *             length="200"
     *         
     */

    public String getTargetGroup() {
        return this.targetGroup;
    }
    
    public void setTargetGroup(String targetGroup) {
        this.targetGroup = targetGroup;
    }
    /**       
     *      *            @hibernate.property
     *             column="DETAIL"
     *             length="4000"
     *         
     */

    public String getDetail() {
        return this.detail;
    }
    
    public void setDetail(String detail) {
        this.detail = detail;
    }
    /**       
     *      *            @hibernate.property
     *             column="PROPOSER"
     *             length="200"
     *         
     */

    public String getProposer() {
        return this.proposer;
    }
    
    public void setProposer(String proposer) {
        this.proposer = proposer;
    }
    /**       
     *      *            @hibernate.property
     *             column="ANALYZED"
     *             length="4000"
     *         
     */

    public String getAnalyzed() {
        return this.analyzed;
    }
    
    public void setAnalyzed(String analyzed) {
        this.analyzed = analyzed;
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
     *             column="CREATE_DATE"
     *             length="7"
     *         
     */

    public Date getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("pmName").append("='").append(getPmName()).append("' ");			
      buffer.append("pmTarget").append("='").append(getPmTarget()).append("' ");			
      buffer.append("pmWay").append("='").append(getPmWay()).append("' ");			
      buffer.append("pmType").append("='").append(getPmType()).append("' ");			
      buffer.append("noticeDate").append("='").append(getNoticeDate()).append("' ");			
      buffer.append("startTime").append("='").append(getStartTime()).append("' ");			
      buffer.append("endTime").append("='").append(getEndTime()).append("' ");			
      buffer.append("status").append("='").append(getStatus()).append("' ");			
      buffer.append("targetGroup").append("='").append(getTargetGroup()).append("' ");			
      buffer.append("detail").append("='").append(getDetail()).append("' ");			
      buffer.append("proposer").append("='").append(getProposer()).append("' ");			
      buffer.append("analyzed").append("='").append(getAnalyzed()).append("' ");			
      buffer.append("createUser").append("='").append(getCreateUser()).append("' ");			
      buffer.append("createDate").append("='").append(getCreateDate()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JamPromotion) ) return false;
		 JamPromotion castOther = ( JamPromotion ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getPmName()==castOther.getPmName()) || ( this.getPmName()!=null && castOther.getPmName()!=null && this.getPmName().equals(castOther.getPmName()) ) )
 && ( (this.getPmTarget()==castOther.getPmTarget()) || ( this.getPmTarget()!=null && castOther.getPmTarget()!=null && this.getPmTarget().equals(castOther.getPmTarget()) ) )
 && ( (this.getPmWay()==castOther.getPmWay()) || ( this.getPmWay()!=null && castOther.getPmWay()!=null && this.getPmWay().equals(castOther.getPmWay()) ) )
 && ( (this.getPmType()==castOther.getPmType()) || ( this.getPmType()!=null && castOther.getPmType()!=null && this.getPmType().equals(castOther.getPmType()) ) )
 && ( (this.getNoticeDate()==castOther.getNoticeDate()) || ( this.getNoticeDate()!=null && castOther.getNoticeDate()!=null && this.getNoticeDate().equals(castOther.getNoticeDate()) ) )
 && ( (this.getStartTime()==castOther.getStartTime()) || ( this.getStartTime()!=null && castOther.getStartTime()!=null && this.getStartTime().equals(castOther.getStartTime()) ) )
 && ( (this.getEndTime()==castOther.getEndTime()) || ( this.getEndTime()!=null && castOther.getEndTime()!=null && this.getEndTime().equals(castOther.getEndTime()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
 && ( (this.getTargetGroup()==castOther.getTargetGroup()) || ( this.getTargetGroup()!=null && castOther.getTargetGroup()!=null && this.getTargetGroup().equals(castOther.getTargetGroup()) ) )
 && ( (this.getDetail()==castOther.getDetail()) || ( this.getDetail()!=null && castOther.getDetail()!=null && this.getDetail().equals(castOther.getDetail()) ) )
 && ( (this.getProposer()==castOther.getProposer()) || ( this.getProposer()!=null && castOther.getProposer()!=null && this.getProposer().equals(castOther.getProposer()) ) )
 && ( (this.getAnalyzed()==castOther.getAnalyzed()) || ( this.getAnalyzed()!=null && castOther.getAnalyzed()!=null && this.getAnalyzed().equals(castOther.getAnalyzed()) ) )
 && ( (this.getCreateUser()==castOther.getCreateUser()) || ( this.getCreateUser()!=null && castOther.getCreateUser()!=null && this.getCreateUser().equals(castOther.getCreateUser()) ) )
 && ( (this.getCreateDate()==castOther.getCreateDate()) || ( this.getCreateDate()!=null && castOther.getCreateDate()!=null && this.getCreateDate().equals(castOther.getCreateDate()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getPmName() == null ? 0 : this.getPmName().hashCode() );
         result = 37 * result + ( getPmTarget() == null ? 0 : this.getPmTarget().hashCode() );
         result = 37 * result + ( getPmWay() == null ? 0 : this.getPmWay().hashCode() );
         result = 37 * result + ( getPmType() == null ? 0 : this.getPmType().hashCode() );
         result = 37 * result + ( getNoticeDate() == null ? 0 : this.getNoticeDate().hashCode() );
         result = 37 * result + ( getStartTime() == null ? 0 : this.getStartTime().hashCode() );
         result = 37 * result + ( getEndTime() == null ? 0 : this.getEndTime().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         result = 37 * result + ( getTargetGroup() == null ? 0 : this.getTargetGroup().hashCode() );
         result = 37 * result + ( getDetail() == null ? 0 : this.getDetail().hashCode() );
         result = 37 * result + ( getProposer() == null ? 0 : this.getProposer().hashCode() );
         result = 37 * result + ( getAnalyzed() == null ? 0 : this.getAnalyzed().hashCode() );
         result = 37 * result + ( getCreateUser() == null ? 0 : this.getCreateUser().hashCode() );
         result = 37 * result + ( getCreateDate() == null ? 0 : this.getCreateDate().hashCode() );
         return result;
   }   





}
