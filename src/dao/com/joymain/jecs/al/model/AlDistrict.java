package com.joymain.jecs.al.model;
// Generated 2009-9-26 16:58:03 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JAL_DISTRICT"
 *     
 */

public class AlDistrict extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long districtId;
    private String districtCode;
    private String districtName;
    private AlCity alCity;
    private String postalcode;
    // Constructors

    /** default constructor */
    public AlDistrict() {
    }

    
    /** full constructor */
    public AlDistrict(String districtCode, String districtName) {
        this.districtCode = districtCode;
        this.districtName = districtName;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.Long"
     *             column="DISTRICT_ID"
     *         
     */

    public Long getDistrictId() {
        return this.districtId;
    }
    
    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }
    /**       
     *      *            @hibernate.property
     *             column="DISTRICT_CODE"
     *             length="30"
     *         
     */

    public String getDistrictCode() {
        return this.districtCode;
    }
    
    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="DISTRICT_NAME"
     *             length="200"
     *         
     */

    public String getDistrictName() {
        return this.districtName;
    }
    
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    /**
	 * *
	 * @hibernate.many-to-one not-null="true"
	 * @hibernate.column name="CITY_ID"
	 * 
	 */
    public AlCity getAlCity() {
		return alCity;
	}


	public void setAlCity(AlCity alCity) {
		this.alCity = alCity;
	}


	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("districtCode").append("='").append(getDistrictCode()).append("' ");			
      buffer.append("districtName").append("='").append(getDistrictName()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AlDistrict) ) return false;
		 AlDistrict castOther = ( AlDistrict ) other; 
         
		 return ( (this.getDistrictId()==castOther.getDistrictId()) || ( this.getDistrictId()!=null && castOther.getDistrictId()!=null && this.getDistrictId().equals(castOther.getDistrictId()) ) )
 && ( (this.getDistrictCode()==castOther.getDistrictCode()) || ( this.getDistrictCode()!=null && castOther.getDistrictCode()!=null && this.getDistrictCode().equals(castOther.getDistrictCode()) ) )
 && ( (this.getDistrictName()==castOther.getDistrictName()) || ( this.getDistrictName()!=null && castOther.getDistrictName()!=null && this.getDistrictName().equals(castOther.getDistrictName()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getDistrictId() == null ? 0 : this.getDistrictId().hashCode() );
         result = 37 * result + ( getDistrictCode() == null ? 0 : this.getDistrictCode().hashCode() );
         result = 37 * result + ( getDistrictName() == null ? 0 : this.getDistrictName().hashCode() );
         return result;
   }


   /**       
    *      *            @hibernate.property
    *             column="POSTALCODE"
    *             length="10"
    *         
    */
	public String getPostalcode() {
		return postalcode;
	}
	
	
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}   





}
