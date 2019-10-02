package com.joymain.jecs.al.model;
// Generated 2008-3-8 15:17:36 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JAL_BRANCH"
 *     
 */

public class AlBranch extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long branchId;
    private String companyCode;
    private String branchCode;
    private String branchName;


    // Constructors

    /** default constructor */
    public AlBranch() {
    }

    
    /** full constructor */
    public AlBranch(String companyCode, String branchCode, String branchName) {
        this.companyCode = companyCode;
        this.branchCode = branchCode;
        this.branchName = branchName;
    }
    

   
    // Property accessors
    /**
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="BRANCH_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_AL"
     */

    public Long getBranchId() {
        return this.branchId;
    }
    
    public void setBranchId(Long branchId) {
        this.branchId = branchId;
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
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="BRANCH_CODE"
     *             length="30"
     *             not-null="true"
     *         
     */

    public String getBranchCode() {
        return this.branchCode;
    }
    
    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="BRANCH_NAME"
     *             length="150"
     *             not-null="true"
     *         
     */

    public String getBranchName() {
        return this.branchName;
    }
    
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("branchCode").append("='").append(getBranchCode()).append("' ");			
      buffer.append("branchName").append("='").append(getBranchName()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AlBranch) ) return false;
		 AlBranch castOther = ( AlBranch ) other; 
         
		 return ( (this.getBranchId()==castOther.getBranchId()) || ( this.getBranchId()!=null && castOther.getBranchId()!=null && this.getBranchId().equals(castOther.getBranchId()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getBranchCode()==castOther.getBranchCode()) || ( this.getBranchCode()!=null && castOther.getBranchCode()!=null && this.getBranchCode().equals(castOther.getBranchCode()) ) )
 && ( (this.getBranchName()==castOther.getBranchName()) || ( this.getBranchName()!=null && castOther.getBranchName()!=null && this.getBranchName().equals(castOther.getBranchName()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getBranchId() == null ? 0 : this.getBranchId().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getBranchCode() == null ? 0 : this.getBranchCode().hashCode() );
         result = 37 * result + ( getBranchName() == null ? 0 : this.getBranchName().hashCode() );
         return result;
   }   





}
