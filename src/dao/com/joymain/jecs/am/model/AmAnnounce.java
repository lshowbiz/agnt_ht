package com.joymain.jecs.am.model;
// Generated 2008-6-14 14:56:22 by Hibernate Tools 3.1.0.beta4

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="AM_ANNOUNCE"
 *     
 */

public class AmAnnounce extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private String aaNo;
    private String companyCode;
    private String subject;
    private String content;
    private String issueUserNo;
    private Date createTime;
    private String checkUserNo;
    private Date checkTime;
    private Date startTime;
    private Date endTime;
    private Integer status ;
    private Set amAnnounceRecords = new HashSet();
    private Set amPermits = new HashSet();
    
    private String issuerName;
    private String checkerName;
    
    private String annoClassNo;
    private String highlight;
    private String outAnnounce;
    

    /**
	 * *
	 * 
	 * @hibernate.property column="HIGHLIGHT" length="1"
	 * 
	 */
	public String getHighlight() {
		return highlight;
	}

	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}

	/**
	 * *
	 * 
	 * @hibernate.property column="OUT_ANNOUNCE" length="2"
	 * 
	 */
	public String getOutAnnounce() {
		return outAnnounce;
	}

	public void setOutAnnounce(String outAnnounce) {
		this.outAnnounce = outAnnounce;
	}

	// Constructors
    /**
	 * *
	 * 
	 * @hibernate.property column="ISSUER_NAME" length="16"
	 * 
	 */
    public String getIssuerName() {
		return issuerName;
	}

	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}
	/**
	 * *
	 * 
	 * @hibernate.property column="CHECKER_NAME" length="16"
	 * 
	 */
	public String getCheckerName() {
		return checkerName;
	}

	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}

	/** default constructor */
    public AmAnnounce() {
    }

	/** minimal constructor */
    public AmAnnounce(String companyCode, String subject, String content, String issueUserNo, Date startTime, int status) {
        this.companyCode = companyCode;
        this.subject = subject;
        this.content = content;
        this.issueUserNo = issueUserNo;
        this.startTime = startTime;
        this.status = status;
    }
    
    /** full constructor */
    public AmAnnounce(String companyCode, String subject, String content, String issueUserNo, Date createTime, String checkUserNo, Date checkTime, Date startTime, Date endTime, int status, String issuerName, String checkerName) {
        this.companyCode = companyCode;
        this.subject = subject;
        this.content = content;
        this.issueUserNo = issueUserNo;
        this.createTime = createTime;
        this.checkUserNo = checkUserNo;
        this.checkTime = checkTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.issuerName = issuerName;
        this.checkerName = checkerName;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.String"
     *             column="AA_NO"
     *      @hibernate.generator-param name="sequence" value="SEQ_AM"    
     */

    public String getAaNo() {
        return this.aaNo;
    }
    
    public void setAaNo(String aaNo) {
        this.aaNo = aaNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="COMPANY_CODE"
     *             length="2"
     *             not-null="true"
     *         
     */

    public String getCompanyCode() {
        return this.companyCode;
    }
    /**
     * @spring.validator type="required"
     */
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="SUBJECT"
     *             length="250"             
     *             not-null="true"
     *         
     */

    public String getSubject() {
        return this.subject;
    }
    /**
     * @spring.validator type="required"
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }
    /**       
     *      *            @hibernate.property
     *             column="CONTENT"
     *             type="org.springframework.orm.hibernate3.support.ClobStringType"
     *             length="100000000"
     *             not-null="true"
     *         
     */

    public String getContent() {
        return this.content;
    }
    /**
     * @spring.validator type="required"
     */
    public void setContent(String content) {
        this.content = content;
    }
    /**       
     *      *            @hibernate.property
     *             column="ISSUE_USER_NO"
     *             length="20"
     *             not-null="true"
     *         
     */

    public String getIssueUserNo() {
        return this.issueUserNo;
    }
    
    public void setIssueUserNo(String issueUserNo) {
        this.issueUserNo = issueUserNo;
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
     *             column="CHECK_USER_NO"
     *             length="20"
     *         
     */

    public String getCheckUserNo() {
        return this.checkUserNo;
    }
    
    public void setCheckUserNo(String checkUserNo) {
        this.checkUserNo = checkUserNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="CHECK_TIME"
     *             length="7"
     *         
     */

    public Date getCheckTime() {
        return this.checkTime;
    }
    
    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="START_TIME"
     *             length="7"
     *             
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
     *             not-null="true"
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
     *             column="ANNO_CLASS_NO"
     *             length="2"
     *             not-null="true"
     *         
     */
    
    public String getAnnoClassNo() {
		return annoClassNo;
	}

	public void setAnnoClassNo(String annoClassNo) {
		this.annoClassNo = annoClassNo;
	}
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("subject").append("='").append(getSubject()).append("' ");			
      buffer.append("content").append("='").append(getContent()).append("' ");			
      buffer.append("issueUserNo").append("='").append(getIssueUserNo()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("checkUserNo").append("='").append(getCheckUserNo()).append("' ");			
      buffer.append("checkTime").append("='").append(getCheckTime()).append("' ");			
      buffer.append("startTime").append("='").append(getStartTime()).append("' ");			
      buffer.append("endTime").append("='").append(getEndTime()).append("' ");			
      buffer.append("status").append("='").append(getStatus()).append("' ");
      buffer.append("issuerName").append("='").append(getIssuerName()).append("' ");
      buffer.append("checkerName").append("='").append(getCheckerName()).append("' ");
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AmAnnounce) ) return false;
		 AmAnnounce castOther = ( AmAnnounce ) other; 
         
		 return ( (this.getAaNo()==castOther.getAaNo()) || ( this.getAaNo()!=null && castOther.getAaNo()!=null && this.getAaNo().equals(castOther.getAaNo()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getSubject()==castOther.getSubject()) || ( this.getSubject()!=null && castOther.getSubject()!=null && this.getSubject().equals(castOther.getSubject()) ) )
 && ( (this.getContent()==castOther.getContent()) || ( this.getContent()!=null && castOther.getContent()!=null && this.getContent().equals(castOther.getContent()) ) )
 && ( (this.getIssueUserNo()==castOther.getIssueUserNo()) || ( this.getIssueUserNo()!=null && castOther.getIssueUserNo()!=null && this.getIssueUserNo().equals(castOther.getIssueUserNo()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getCheckUserNo()==castOther.getCheckUserNo()) || ( this.getCheckUserNo()!=null && castOther.getCheckUserNo()!=null && this.getCheckUserNo().equals(castOther.getCheckUserNo()) ) )
 && ( (this.getCheckTime()==castOther.getCheckTime()) || ( this.getCheckTime()!=null && castOther.getCheckTime()!=null && this.getCheckTime().equals(castOther.getCheckTime()) ) )
 && ( (this.getStartTime()==castOther.getStartTime()) || ( this.getStartTime()!=null && castOther.getStartTime()!=null && this.getStartTime().equals(castOther.getStartTime()) ) )
 && ( (this.getEndTime()==castOther.getEndTime()) || ( this.getEndTime()!=null && castOther.getEndTime()!=null && this.getEndTime().equals(castOther.getEndTime()) ) )
 && ( (this.getIssuerName()==castOther.getIssuerName()) || ( this.getIssuerName()!=null && castOther.getIssuerName()!=null && this.getIssuerName().equals(castOther.getIssuerName()) ) )
 && ( (this.getCheckerName()==castOther.getCheckerName()) || ( this.getCheckerName()!=null && castOther.getCheckerName()!=null && this.getCheckerName().equals(castOther.getCheckerName()) ) )
 && (this.getStatus()==castOther.getStatus());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getAaNo() == null ? 0 : this.getAaNo().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getSubject() == null ? 0 : this.getSubject().hashCode() );
         result = 37 * result + ( getContent() == null ? 0 : this.getContent().hashCode() );
         result = 37 * result + ( getIssueUserNo() == null ? 0 : this.getIssueUserNo().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getCheckUserNo() == null ? 0 : this.getCheckUserNo().hashCode() );
         result = 37 * result + ( getCheckTime() == null ? 0 : this.getCheckTime().hashCode() );
         result = 37 * result + ( getStartTime() == null ? 0 : this.getStartTime().hashCode() );
         result = 37 * result + ( getEndTime() == null ? 0 : this.getEndTime().hashCode() );
         result = 37 * result + ( getIssuerName() == null ? 0 : this.getIssuerName().hashCode() );
         result = 37 * result + ( getCheckerName() == null ? 0 : this.getCheckerName().hashCode() );
         result = 37 * result + this.getStatus();
         return result;
   }

   
   /**
    * @hibernate.set table="AM_ANNOUNCE_PERMIT"  lazy="true"
    * @hibernate.collection-key column="AA_NO"
    * @hibernate.collection-many-to-many class="com.joymain.jecs.am.model.AmPermit" column="PERMIT_NO"
    */
public Set getAmPermits() {
	return amPermits;
}

public void setAmPermits(Set amPermits) {
	this.amPermits = amPermits;
}   

public void addAmPermit(AmPermit amPermit){
	getAmPermits().add(amPermit);
}

/**
 * *
 * 
 * @hibernate.set lazy="true" inverse="true" cascade="all-delete-orphan"
 * @hibernate.collection-key column="AA_NO"
 * @hibernate.collection-one-to-many class="com.joymain.jecs.am.model.AmAnnounceRecord"
 * 
 */
public Set getAmAnnounceRecords() {
	return amAnnounceRecords;
}

public void setAmAnnounceRecords(Set amAnnounceRecords) {
	this.amAnnounceRecords = amAnnounceRecords;
}



}
