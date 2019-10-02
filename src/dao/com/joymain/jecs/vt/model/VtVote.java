package com.joymain.jecs.vt.model;
// Generated 2009-12-11 11:16:35 by Hibernate Tools 3.1.0.beta4

import java.util.Date;
import java.util.HashSet;
import java.util.Set;



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="VT_VOTE"
 *     
 */

public class VtVote extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long vtId;
    private String subject;
    private String description;
    private Integer optionNum;
    private Date startTime;
    private Date endTime;
    private String status;
    private Date createTime;
    private String createUser;
    private String companyCode;
    private String state;
    private Set<VtVoteDetail> vtVoteDetails= new HashSet<VtVoteDetail>(0);
    private Set<VtVotePow> vtVotePows= new HashSet<VtVotePow>(0);

    // Constructors

    /**       
     *      *            @hibernate.property
     *             column="STATE"
     *             length="1"
     *         
     */

    public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	/** default constructor */
    public VtVote() {
    }

    
    /** full constructor */
    public VtVote(String subject, String description, Integer optionNum, Date startTime, Date endTime, String status, Date createTime, String createUser, String companyCode) {
        this.subject = subject;
        this.description = description;
        this.optionNum = optionNum;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.createTime = createTime;
        this.createUser = createUser;
        this.companyCode = companyCode;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.Long"
     *             column="VT_ID"
     *         
     */

    public Long getVtId() {
        return this.vtId;
    }
    
    public void setVtId(Long vtId) {
        this.vtId = vtId;
    }
    /**       
     *      *            @hibernate.property
     *             column="SUBJECT"
     *             length="250"
     *         
     */

    public String getSubject() {
        return this.subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    /**       
     *      *            @hibernate.property
     *             column="DESCRIPTION"
     *             length="4000"
     *         
     */

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    /**       
     *      *            @hibernate.property
     *             column="OPTION_NUM"
     *             length="2"
     *         
     */

    public Integer getOptionNum() {
        return this.optionNum;
    }
    
    public void setOptionNum(Integer optionNum) {
        this.optionNum = optionNum;
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
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("subject").append("='").append(getSubject()).append("' ");			
      buffer.append("description").append("='").append(getDescription()).append("' ");			
      buffer.append("optionNum").append("='").append(getOptionNum()).append("' ");			
      buffer.append("startTime").append("='").append(getStartTime()).append("' ");			
      buffer.append("endTime").append("='").append(getEndTime()).append("' ");			
      buffer.append("status").append("='").append(getStatus()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("createUser").append("='").append(getCreateUser()).append("' ");			
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof VtVote) ) return false;
		 VtVote castOther = ( VtVote ) other; 
         
		 return ( (this.getVtId()==castOther.getVtId()) || ( this.getVtId()!=null && castOther.getVtId()!=null && this.getVtId().equals(castOther.getVtId()) ) )
 && ( (this.getSubject()==castOther.getSubject()) || ( this.getSubject()!=null && castOther.getSubject()!=null && this.getSubject().equals(castOther.getSubject()) ) )
 && ( (this.getDescription()==castOther.getDescription()) || ( this.getDescription()!=null && castOther.getDescription()!=null && this.getDescription().equals(castOther.getDescription()) ) )
 && ( (this.getOptionNum()==castOther.getOptionNum()) || ( this.getOptionNum()!=null && castOther.getOptionNum()!=null && this.getOptionNum().equals(castOther.getOptionNum()) ) )
 && ( (this.getStartTime()==castOther.getStartTime()) || ( this.getStartTime()!=null && castOther.getStartTime()!=null && this.getStartTime().equals(castOther.getStartTime()) ) )
 && ( (this.getEndTime()==castOther.getEndTime()) || ( this.getEndTime()!=null && castOther.getEndTime()!=null && this.getEndTime().equals(castOther.getEndTime()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getCreateUser()==castOther.getCreateUser()) || ( this.getCreateUser()!=null && castOther.getCreateUser()!=null && this.getCreateUser().equals(castOther.getCreateUser()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getVtId() == null ? 0 : this.getVtId().hashCode() );
         result = 37 * result + ( getSubject() == null ? 0 : this.getSubject().hashCode() );
         result = 37 * result + ( getDescription() == null ? 0 : this.getDescription().hashCode() );
         result = 37 * result + ( getOptionNum() == null ? 0 : this.getOptionNum().hashCode() );
         result = 37 * result + ( getStartTime() == null ? 0 : this.getStartTime().hashCode() );
         result = 37 * result + ( getEndTime() == null ? 0 : this.getEndTime().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getCreateUser() == null ? 0 : this.getCreateUser().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         return result;
   }


	/**
	 * *
	 * 
	 * @hibernate.set lazy="true" inverse="true" cascade="all" order-by="ORDER_NO asc"
	 * @hibernate.collection-key column="VT_ID"
	 * @hibernate.collection-one-to-many class="com.joymain.jecs.vt.model.VtVoteDetail"
	 * 
	 */
public Set<VtVoteDetail> getVtVoteDetails() {
	return vtVoteDetails;
}


public void setVtVoteDetails(Set<VtVoteDetail> vtVoteDetails) {
	this.vtVoteDetails = vtVoteDetails;
}


/**
 * *
 * 
 * @hibernate.set lazy="true" inverse="true" cascade="all"
 * @hibernate.collection-key column="VT_ID"
 * @hibernate.collection-one-to-many class="com.joymain.jecs.vt.model.VtVotePow"
 * 
 */
public Set<VtVotePow> getVtVotePows() {
	return vtVotePows;
}


public void setVtVotePows(Set<VtVotePow> vtVotePows) {
	this.vtVotePows = vtVotePows;
}   





}
