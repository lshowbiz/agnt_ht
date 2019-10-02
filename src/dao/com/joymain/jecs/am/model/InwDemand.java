package com.joymain.jecs.am.model;
// Generated 2013-8-13 16:39:28 by Hibernate Tools 3.1.0.beta4

import java.util.Date;



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="INW_DEMAND"
 *     
 */

public class InwDemand extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private String name;
    private String summary;
    private String detailExplanation;
    private String demandImage;
    private String showOrHide;
    private String verify;
    private String other;
    private String postUserCode;
    private Date postTime;
    private String reviewedUserCode;
    private Date reviewedTime;
    //新增加的字段
    private String demandsortId;
    

    // Constructors

    /** default constructor */
    public InwDemand() {
    }

	/** minimal constructor */
    public InwDemand(String name, String summary) {
        this.name = name;
        this.summary = summary;
    }
    
    /** full constructor */
    public InwDemand(String name, String summary, String detailExplanation, String demandImage, String showOrHide, String verify, String other,String postUserCode,Date postTime,String reviewedUserCode,Date reviewedTime,String demandsortId) {
        this.name = name;
        this.summary = summary;
        this.detailExplanation = detailExplanation;
        this.demandImage = demandImage;
        this.showOrHide = showOrHide;
        this.verify = verify;
        this.other = other;
        this.postUserCode = postUserCode;
        this.postTime = postTime;
        this.reviewedUserCode = reviewedUserCode;
        this.reviewedTime = reviewedTime;
        this.demandsortId = demandsortId;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ID"
     *         @hibernate.generator-param name="sequence" value="SEQ_AM" 
     */
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    /**       
     *      *            @hibernate.property
     *             column="NAME"
     *             length="100"
     *             not-null="true"
     *         
     */

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    /**       
     *      *            @hibernate.property
     *             column="SUMMARY"
     *             length="300"
     *             not-null="true"
     *         
     */

    public String getSummary() {
        return this.summary;
    }
    
    public void setSummary(String summary) {
        this.summary = summary;
    }
    /**       
     *      *            @hibernate.property
     *             column="DETAIL_EXPLANATION"
     *             length="4000"
     *         
     */

    public String getDetailExplanation() {
        return this.detailExplanation;
    }
    
    public void setDetailExplanation(String detailExplanation) {
        this.detailExplanation = detailExplanation;
    }
    /**       
     *      *            @hibernate.property
     *             column="DEMAND_IMAGE"
     *             length="4000"
     *         
     */

    public String getDemandImage() {
        return this.demandImage;
    }
    
    public void setDemandImage(String demandImage) {
        this.demandImage = demandImage;
    }
    /**       
     *      *            @hibernate.property
     *             column="SHOW_OR_HIDE"
     *             length="2"
     *         
     */

    public String getShowOrHide() {
        return this.showOrHide;
    }
    
    public void setShowOrHide(String showOrHide) {
        this.showOrHide = showOrHide;
    }
    /**       
     *      *            @hibernate.property
     *             column="VERIFY"
     *             length="200"
     *         
     */

    public String getVerify() {
        return this.verify;
    }
    
    public void setVerify(String verify) {
        this.verify = verify;
    }
    /**       
     *      *            @hibernate.property
     *             column="OTHER"
     *             length="200"
     *         
     */

    public String getOther() {
        return this.other;
    }
    
    public void setOther(String other) {
        this.other = other;
    }
   

    
    /**       
     *      *            @hibernate.property
     *             column="POST_USER_CODE"
     *             length="20"
     *         
     */
	public String getPostUserCode() {
		return postUserCode;
	}

	/**
	 * @param postUserCode the postUserCode to set
	 */
	public void setPostUserCode(String postUserCode) {
		this.postUserCode = postUserCode;
	}

	 /**       
     *      *            @hibernate.property
     *             column="POST_TIME"
     *             length="7"
     *         
     */
	public Date getPostTime() {
		return postTime;
	}

	/**
	 * @param postTime the postTime to set
	 */
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	/**       
     *      *            @hibernate.property
     *             column="REVIEWED_USER_CODE"
     *             length="20"
     *         
     */
	public String getReviewedUserCode() {
		return reviewedUserCode;
	}

	/**
	 * @param reviewedUserCode the reviewedUserCode to set
	 */
	public void setReviewedUserCode(String reviewedUserCode) {
		this.reviewedUserCode = reviewedUserCode;
	}

	/**       
     *      *            @hibernate.property
     *             column="REVIEWED_TIME"
     *             length="7"
     *         
     */
	public Date getReviewedTime() {
		return reviewedTime;
	}

	/**
	 * @param reviewedTime the reviewedTime to set
	 */
	public void setReviewedTime(Date reviewedTime) {
		this.reviewedTime = reviewedTime;
	}
	
	
	
	/**       
     *      *            @hibernate.property
     *             column="DEMANDSORT_ID"
     *             length="32"
     *         
     */
	public String getDemandsortId() {
		return demandsortId;
	}

	/**
	 * @param demandsortId the demandsortId to set
	 */
	public void setDemandsortId(String demandsortId) {
		this.demandsortId = demandsortId;
	}

	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("name").append("='").append(getName()).append("' ");			
      buffer.append("summary").append("='").append(getSummary()).append("' ");			
      buffer.append("detailExplanation").append("='").append(getDetailExplanation()).append("' ");			
      buffer.append("demandImage").append("='").append(getDemandImage()).append("' ");			
      buffer.append("showOrHide").append("='").append(getShowOrHide()).append("' ");			
      buffer.append("verify").append("='").append(getVerify()).append("' ");			
      buffer.append("other").append("='").append(getOther()).append("' ");	
      buffer.append("postUserCode").append("='").append(getPostUserCode()).append("' ");	
      buffer.append("postTime").append("='").append(getPostTime()).append("' ");
      buffer.append("reviewedUserCode").append("='").append(getReviewedUserCode()).append("' ");	
      buffer.append("reviewedTime").append("='").append(getReviewedTime()).append("' ");
      buffer.append("demandsortId").append("='").append(getDemandsortId()).append("' ");
      buffer.append("]");
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof InwDemand) ) return false;
		 InwDemand castOther = ( InwDemand ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getName()==castOther.getName()) || ( this.getName()!=null && castOther.getName()!=null && this.getName().equals(castOther.getName()) ) )
 && ( (this.getSummary()==castOther.getSummary()) || ( this.getSummary()!=null && castOther.getSummary()!=null && this.getSummary().equals(castOther.getSummary()) ) )
 && ( (this.getDetailExplanation()==castOther.getDetailExplanation()) || ( this.getDetailExplanation()!=null && castOther.getDetailExplanation()!=null && this.getDetailExplanation().equals(castOther.getDetailExplanation()) ) )
 && ( (this.getDemandImage()==castOther.getDemandImage()) || ( this.getDemandImage()!=null && castOther.getDemandImage()!=null && this.getDemandImage().equals(castOther.getDemandImage()) ) )
 && ( (this.getShowOrHide()==castOther.getShowOrHide()) || ( this.getShowOrHide()!=null && castOther.getShowOrHide()!=null && this.getShowOrHide().equals(castOther.getShowOrHide()) ) )
 && ( (this.getVerify()==castOther.getVerify()) || ( this.getVerify()!=null && castOther.getVerify()!=null && this.getVerify().equals(castOther.getVerify()) ) )
 && ( (this.getOther()==castOther.getOther()) || ( this.getOther()!=null && castOther.getOther()!=null && this.getOther().equals(castOther.getOther()) ) )
 && ( (this.getPostUserCode()==castOther.getPostUserCode()) || ( this.getPostUserCode()!=null && castOther.getPostUserCode()!=null && this.getPostUserCode().equals(castOther.getPostUserCode()) ) )
 && ( (this.getPostTime()==castOther.getPostTime()) || ( this.getPostTime()!=null && castOther.getPostTime()!=null && this.getPostTime().equals(castOther.getPostTime()) ) )	 
 && ( (this.getReviewedUserCode()==castOther.getReviewedUserCode()) || ( this.getReviewedUserCode()!=null && castOther.getReviewedUserCode()!=null && this.getReviewedUserCode().equals(castOther.getReviewedUserCode()) ) )
 && ( (this.getReviewedTime()==castOther.getReviewedTime()) || ( this.getReviewedTime()!=null && castOther.getReviewedTime()!=null && this.getReviewedTime().equals(castOther.getReviewedTime()) ) )
 && ( (this.getDemandsortId()==castOther.getDemandsortId()) || ( this.getDemandsortId()!=null && castOther.getDemandsortId()!=null && this.getDemandsortId().equals(castOther.getDemandsortId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getName() == null ? 0 : this.getName().hashCode() );
         result = 37 * result + ( getSummary() == null ? 0 : this.getSummary().hashCode() );
         result = 37 * result + ( getDetailExplanation() == null ? 0 : this.getDetailExplanation().hashCode() );
         result = 37 * result + ( getDemandImage() == null ? 0 : this.getDemandImage().hashCode() );
         result = 37 * result + ( getShowOrHide() == null ? 0 : this.getShowOrHide().hashCode() );
         result = 37 * result + ( getVerify() == null ? 0 : this.getVerify().hashCode() );
         result = 37 * result + ( getOther() == null ? 0 : this.getOther().hashCode() );
         result = 37 * result + ( getPostUserCode() == null ? 0 : this.getPostUserCode().hashCode() );
         result = 37 * result + ( getPostTime() == null ? 0 : this.getPostTime().hashCode() );
         result = 37 * result + ( getReviewedUserCode() == null ? 0 : this.getReviewedUserCode().hashCode() );
         result = 37 * result + ( getReviewedTime() == null ? 0 : this.getReviewedTime().hashCode() );
         result = 37 * result + ( getDemandsortId() == null ? 0 : this.getDemandsortId().hashCode() );
         return result;
   }


}
