package com.joymain.jecs.vt.model;
// Generated 2009-12-11 11:21:04 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="VT_VOTE_NOTE"
 *     
 */

public class VtVoteNote extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long vnId;
    private String userCode;
    private Date createTime;
    private String companyCode;
    private String userType;
    private VtVoteDetail vtVoteDetail;

    // Constructors

	/**
     * *
     * @hibernate.many-to-one not-null="true"
     * @hibernate.column name="VD_ID"
     * 
     */
    public VtVoteDetail getVtVoteDetail() {
		return vtVoteDetail;
	}


	public void setVtVoteDetail(VtVoteDetail vtVoteDetail) {
		this.vtVoteDetail = vtVoteDetail;
	}


	/** default constructor */
    public VtVoteNote() {
    }

    
    /** full constructor */
    public VtVoteNote(Long vdId, String userCode, Date createTime, String companyCode, String userType) {
        this.userCode = userCode;
        this.createTime = createTime;
        this.companyCode = companyCode;
        this.userType = userType;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.Long"
     *             column="VN_ID"
     *         
     */

    public Long getVnId() {
        return this.vnId;
    }
    
    public void setVnId(Long vnId) {
        this.vnId = vnId;
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
     *             column="USER_TYPE"
     *             length="1"
     *         
     */

    public String getUserType() {
        return this.userType;
    }
    
    public void setUserType(String userType) {
        this.userType = userType;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("userType").append("='").append(getUserType()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof VtVoteNote) ) return false;
		 VtVoteNote castOther = ( VtVoteNote ) other; 
         
		 return ( (this.getVnId()==castOther.getVnId()) || ( this.getVnId()!=null && castOther.getVnId()!=null && this.getVnId().equals(castOther.getVnId()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getUserType()==castOther.getUserType()) || ( this.getUserType()!=null && castOther.getUserType()!=null && this.getUserType().equals(castOther.getUserType()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getVnId() == null ? 0 : this.getVnId().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getUserType() == null ? 0 : this.getUserType().hashCode() );
         return result;
   }   





}
