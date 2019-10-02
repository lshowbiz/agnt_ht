package com.joymain.jecs.vt.model;
// Generated 2009-12-11 11:21:41 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="VT_VOTE_POW"
 *     
 */

public class VtVotePow extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long vpId;
    private String companyCode;
    private String userType;
    private VtVote vtVote;

    // Constructors

	/**
     * *
     * @hibernate.many-to-one not-null="true"
     * @hibernate.column name="VT_ID"
     * 
     */
    public VtVote getVtVote() {
		return vtVote;
	}


	public void setVtVote(VtVote vtVote) {
		this.vtVote = vtVote;
	}


	/** default constructor */
    public VtVotePow() {
    }

    
    /** full constructor */
    public VtVotePow(String companyCode, String userType, Long vtId) {
        this.companyCode = companyCode;
        this.userType = userType;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.Long"
     *             column="VP_ID"
     *         
     */

    public Long getVpId() {
        return this.vpId;
    }
    
    public void setVpId(Long vpId) {
        this.vpId = vpId;
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
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("userType").append("='").append(getUserType()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof VtVotePow) ) return false;
		 VtVotePow castOther = ( VtVotePow ) other; 
         
		 return ( (this.getVpId()==castOther.getVpId()) || ( this.getVpId()!=null && castOther.getVpId()!=null && this.getVpId().equals(castOther.getVpId()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getUserType()==castOther.getUserType()) || ( this.getUserType()!=null && castOther.getUserType()!=null && this.getUserType().equals(castOther.getUserType()) )  );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getVpId() == null ? 0 : this.getVpId().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getUserType() == null ? 0 : this.getUserType().hashCode() );
         return result;
   }   





}
