package com.joymain.jecs.al.model;
// Generated 2008-3-8 15:17:35 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JAL_REGION"
 *     
 */

public class AlRegion extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long regionId;
    private String companyCode;
    private String regionCode;
    private String regionName;


    // Constructors

    /** default constructor */
    public AlRegion() {
    }

    
    /** full constructor */
    public AlRegion(String companyCode, String regionCode, String regionName) {
        this.companyCode = companyCode;
        this.regionCode = regionCode;
        this.regionName = regionName;
    }
    

   
    // Property accessors
    /**
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="REGION_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_AL"
     */
    public Long getRegionId() {
        return this.regionId;
    }
    
    public void setRegionId(Long regionId) {
        this.regionId = regionId;
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
     *             column="REGION_CODE"
     *             length="30"
     *             not-null="true"
     *         
     */

    public String getRegionCode() {
        return this.regionCode;
    }
    
    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="REGION_NAME"
     *             length="150"
     *             not-null="true"
     *         
     */

    public String getRegionName() {
        return this.regionName;
    }
    
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("regionCode").append("='").append(getRegionCode()).append("' ");			
      buffer.append("regionName").append("='").append(getRegionName()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AlRegion) ) return false;
		 AlRegion castOther = ( AlRegion ) other; 
         
		 return ( (this.getRegionId()==castOther.getRegionId()) || ( this.getRegionId()!=null && castOther.getRegionId()!=null && this.getRegionId().equals(castOther.getRegionId()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getRegionCode()==castOther.getRegionCode()) || ( this.getRegionCode()!=null && castOther.getRegionCode()!=null && this.getRegionCode().equals(castOther.getRegionCode()) ) )
 && ( (this.getRegionName()==castOther.getRegionName()) || ( this.getRegionName()!=null && castOther.getRegionName()!=null && this.getRegionName().equals(castOther.getRegionName()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getRegionId() == null ? 0 : this.getRegionId().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getRegionCode() == null ? 0 : this.getRegionCode().hashCode() );
         result = 37 * result + ( getRegionName() == null ? 0 : this.getRegionName().hashCode() );
         return result;
   }   





}
