package com.joymain.jecs.al.model;

import java.util.HashSet;
import java.util.Set;
// Generated 2008-3-8 15:17:35 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JAL_COUNTRY"
 *     
 */

public class AlCountry extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long countryId;
    private String countryCode;
    private String countryName;
    private String companyCode;
    private Set alStateProvinces = new HashSet(0);

    // Constructors

    /** default constructor */
    public AlCountry() {
    }

    
    /** full constructor */
    public AlCountry(String countryCode, String countryName) {
        this.countryCode = countryCode;
        this.countryName = countryName;
    }
    

   
    // Property accessors
    /**
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="COUNTRY_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_AL"
     */
    public Long getCountryId() {
        return this.countryId;
    }
    
    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }
    /**       
     *      *            @hibernate.property
     *             column="COUNTRY_CODE"
     *             length="2"
     *             not-null="true"
     *         
     */

    public String getCountryCode() {
        return this.countryCode;
    }
    
    /**
	 * @spring.validator type="required"
	 * @param typeName String
	 */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="COUNTRY_NAME"
     *             length="150"
     *             not-null="true"
     *         
     */

    public String getCountryName() {
        return this.countryName;
    }
    
    /**
	 * @spring.validator type="required"
	 * @param typeName String
	 */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
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
	 * *
	 * 
	 * @hibernate.set lazy="true" inverse="true" cascade="all" order-by="STATE_PROVINCE_CODE asc"
	 * @hibernate.collection-key column="COUNTRY_ID"
	 * @hibernate.collection-one-to-many class="com.joymain.jecs.al.model.AlStateProvince"
	 * 
	 */
    public Set getAlStateProvinces() {
    	return alStateProvinces;
    }


	public void setAlStateProvinces(Set alStateProvinces) {
    	this.alStateProvinces = alStateProvinces;
    }


	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("countryCode").append("='").append(getCountryCode()).append("' ");			
      buffer.append("countryName").append("='").append(getCountryName()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AlCountry) ) return false;
		 AlCountry castOther = ( AlCountry ) other; 
         
		 return ( (this.getCountryId()==castOther.getCountryId()) || ( this.getCountryId()!=null && castOther.getCountryId()!=null && this.getCountryId().equals(castOther.getCountryId()) ) )
 && ( (this.getCountryCode()==castOther.getCountryCode()) || ( this.getCountryCode()!=null && castOther.getCountryCode()!=null && this.getCountryCode().equals(castOther.getCountryCode()) ) )
 && ( (this.getCountryName()==castOther.getCountryName()) || ( this.getCountryName()!=null && castOther.getCountryName()!=null && this.getCountryName().equals(castOther.getCountryName()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getCountryId() == null ? 0 : this.getCountryId().hashCode() );
         result = 37 * result + ( getCountryCode() == null ? 0 : this.getCountryCode().hashCode() );
         result = 37 * result + ( getCountryName() == null ? 0 : this.getCountryName().hashCode() );
         return result;
   }   





}
