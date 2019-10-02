package com.joymain.jecs.al.model;

import java.util.HashSet;
import java.util.Set;
// Generated 2009-9-26 17:15:36 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JAL_CITY"
 *     
 */

public class AlCity extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long cityId;
    private String cityCode;
    private String cityName;
    private AlStateProvince alStateProvince;
    private Set alDistricts=new HashSet(0);

    // Constructors

    /** default constructor */
    public AlCity() {
    }

    
    /** full constructor */
    public AlCity(String cityCode, String cityName) {
        this.cityCode = cityCode;
        this.cityName = cityName;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.Long"
     *             column="CITY_ID"
     *         
     */

    public Long getCityId() {
        return this.cityId;
    }
    
    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }
    /**       
     *      *            @hibernate.property
     *             column="CITY_CODE"
     *             length="30"
     *         
     */

    public String getCityCode() {
        return this.cityCode;
    }
    
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="CITY_NAME"
     *             length="200"
     *         
     */

    public String getCityName() {
        return this.cityName;
    }
    
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
	 * *
	 * @hibernate.many-to-one not-null="true"
	 * @hibernate.column name="STATE_PROVINCE_ID"
	 * 
	 */
    public AlStateProvince getAlStateProvince() {
		return alStateProvince;
	}


	public void setAlStateProvince(AlStateProvince alStateProvince) {
		this.alStateProvince = alStateProvince;
	}


    /**
	 * *
	 * 
	 * @hibernate.set lazy="true" inverse="true" cascade="all" order-by="DISTRICT_CODE asc"
	 * @hibernate.collection-key column="CITY_ID"
	 * @hibernate.collection-one-to-many class="com.joymain.jecs.al.model.AlDistrict"
	 * 
	 */
	public Set getAlDistricts() {
		return alDistricts;
	}


	public void setAlDistricts(Set alDistricts) {
		this.alDistricts = alDistricts;
	}


	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("cityCode").append("='").append(getCityCode()).append("' ");			
      buffer.append("cityName").append("='").append(getCityName()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AlCity) ) return false;
		 AlCity castOther = ( AlCity ) other; 
         
		 return ( (this.getCityId()==castOther.getCityId()) || ( this.getCityId()!=null && castOther.getCityId()!=null && this.getCityId().equals(castOther.getCityId()) ) )
 && ( (this.getCityCode()==castOther.getCityCode()) || ( this.getCityCode()!=null && castOther.getCityCode()!=null && this.getCityCode().equals(castOther.getCityCode()) ) )
 && ( (this.getCityName()==castOther.getCityName()) || ( this.getCityName()!=null && castOther.getCityName()!=null && this.getCityName().equals(castOther.getCityName()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getCityId() == null ? 0 : this.getCityId().hashCode() );
         result = 37 * result + ( getCityCode() == null ? 0 : this.getCityCode().hashCode() );
         result = 37 * result + ( getCityName() == null ? 0 : this.getCityName().hashCode() );
         return result;
   }   





}
