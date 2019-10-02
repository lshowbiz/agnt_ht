package com.joymain.jecs.al.model;

import java.util.HashSet;
import java.util.Set;
// Generated 2008-3-8 15:17:36 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JAL_STATE_PROVINCE"
 *     
 */

public class AlStateProvince extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long stateProvinceId;
    private AlCountry alCountry;
    private String stateProvinceCode;
    private String stateProvinceName;
    private Set alCitys = new HashSet(0);
    private String belongArea;
    
    

    // Constructors

    /**       
     *      *            @hibernate.property
     *             column="BELONG_AREA"
     *             length="50"
     *         
     */
    public String getBelongArea()
    {
        return belongArea;
    }

    public void setBelongArea(String belongArea)
    {
        this.belongArea = belongArea;
    }

    /** default constructor */
    public AlStateProvince() {
    }
   
    // Property accessors
    /**
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="STATE_PROVINCE_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_AL"
     */
    public Long getStateProvinceId() {
        return this.stateProvinceId;
    }
    
    public void setStateProvinceId(Long stateProvinceId) {
        this.stateProvinceId = stateProvinceId;
    }
    
    /**
	 * *
	 * @hibernate.many-to-one not-null="true"
	 * @hibernate.column name="COUNTRY_ID"
	 * 
	 */
    public AlCountry getAlCountry() {
    	return alCountry;
    }

	public void setAlCountry(AlCountry alCountry) {
    	this.alCountry = alCountry;
    }

    /**       
     *      *            @hibernate.property
     *             column="STATE_PROVINCE_CODE"
     *             length="30"
     *             not-null="true"
     *         
     */

    public String getStateProvinceCode() {
        return this.stateProvinceCode;
    }
    
    /**
	 * @spring.validator type="required"
	 * @param typeName String
	 */
    public void setStateProvinceCode(String stateProvinceCode) {
        this.stateProvinceCode = stateProvinceCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="STATE_PROVINCE_NAME"
     *             length="150"
     *             not-null="true"
     *         
     */

    public String getStateProvinceName() {
        return this.stateProvinceName;
    }
    
    /**
	 * @spring.validator type="required"
	 */
    public void setStateProvinceName(String stateProvinceName) {
        this.stateProvinceName = stateProvinceName;
    }
   

    /**
	 * *
	 * 
	 * @hibernate.set lazy="true" inverse="true" cascade="all" order-by="CITY_CODE asc"
	 * @hibernate.collection-key column="STATE_PROVINCE_ID"
	 * @hibernate.collection-one-to-many class="com.joymain.jecs.al.model.AlCity"
	 * 
	 */
	public Set getAlCitys() {
		return alCitys;
	}

	public void setAlCitys(Set alCitys) {
		this.alCitys = alCitys;
	}

	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");		
      buffer.append("stateProvinceCode").append("='").append(getStateProvinceCode()).append("' ");			
      buffer.append("stateProvinceKey").append("='").append(getStateProvinceName()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AlStateProvince) ) return false;
		 AlStateProvince castOther = ( AlStateProvince ) other; 
         
		 return ( (this.getStateProvinceId()==castOther.getStateProvinceId()) || ( this.getStateProvinceId()!=null && castOther.getStateProvinceId()!=null && this.getStateProvinceId().equals(castOther.getStateProvinceId()) ) )
 && ( (this.getStateProvinceCode()==castOther.getStateProvinceCode()) || ( this.getStateProvinceCode()!=null && castOther.getStateProvinceCode()!=null && this.getStateProvinceCode().equals(castOther.getStateProvinceCode()) ) )
 && ( (this.getStateProvinceName()==castOther.getStateProvinceName()) || ( this.getStateProvinceName()!=null && castOther.getStateProvinceName()!=null && this.getStateProvinceName().equals(castOther.getStateProvinceName()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getStateProvinceId() == null ? 0 : this.getStateProvinceId().hashCode() );
         result = 37 * result + ( getStateProvinceCode() == null ? 0 : this.getStateProvinceCode().hashCode() );
         result = 37 * result + ( getStateProvinceName() == null ? 0 : this.getStateProvinceName().hashCode() );
         return result;
   }   





}
