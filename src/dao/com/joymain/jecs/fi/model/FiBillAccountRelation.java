package com.joymain.jecs.fi.model;
// Generated 2014-3-18 17:11:06 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="FI_BILL_ACCOUNT_RELATION"
 *     
 */

public class FiBillAccountRelation extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long relationId;
    private String province;
    private String city;
    private String district;

    private FiBillAccount fiBillAccount;

    // Constructors

    /** default constructor */
    public FiBillAccountRelation() {
    }

    
    /** full constructor */
    public FiBillAccountRelation(String province, String city, String district) {
        this.province = province;
        this.city = city;
        this.district = district;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="RELATION_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_FI"
     *         
     */

    public Long getRelationId() {
        return this.relationId;
    }
    
    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }
    /**       
     *      *            @hibernate.property
     *             column="PROVINCE"
     *             length="20"
     *         
     */

    public String getProvince() {
        return this.province;
    }
    
    /**
	 * @spring.validator type="required"
	 */
    public void setProvince(String province) {
        this.province = province;
    }
    /**       
     *      *            @hibernate.property
     *             column="CITY"
     *             length="20"
     *         
     */

    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    /**       
     *      *            @hibernate.property
     *             column="DISTRICT"
     *             length="20"
     *         
     */

    public String getDistrict() {
        return this.district;
    }
    
    public void setDistrict(String district) {
        this.district = district;
    }
   
    /**
     * *
     * @hibernate.many-to-one not-null="true"
     * @hibernate.column name="BILL_ACCOUNT_CODE"
     * 
     */
    public FiBillAccount getFiBillAccount() {
		return fiBillAccount;
	}


	public void setFiBillAccount(FiBillAccount fiBillAccount) {
		this.fiBillAccount = fiBillAccount;
	}


	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("province").append("='").append(getProvince()).append("' ");			
      buffer.append("city").append("='").append(getCity()).append("' ");			
      buffer.append("district").append("='").append(getDistrict()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof FiBillAccountRelation) ) return false;
		 FiBillAccountRelation castOther = ( FiBillAccountRelation ) other; 
         
		 return ( (this.getRelationId()==castOther.getRelationId()) || ( this.getRelationId()!=null && castOther.getRelationId()!=null && this.getRelationId().equals(castOther.getRelationId()) ) )
 && ( (this.getProvince()==castOther.getProvince()) || ( this.getProvince()!=null && castOther.getProvince()!=null && this.getProvince().equals(castOther.getProvince()) ) )
 && ( (this.getCity()==castOther.getCity()) || ( this.getCity()!=null && castOther.getCity()!=null && this.getCity().equals(castOther.getCity()) ) )
 && ( (this.getDistrict()==castOther.getDistrict()) || ( this.getDistrict()!=null && castOther.getDistrict()!=null && this.getDistrict().equals(castOther.getDistrict()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getRelationId() == null ? 0 : this.getRelationId().hashCode() );
         result = 37 * result + ( getProvince() == null ? 0 : this.getProvince().hashCode() );
         result = 37 * result + ( getCity() == null ? 0 : this.getCity().hashCode() );
         result = 37 * result + ( getDistrict() == null ? 0 : this.getDistrict().hashCode() );
         return result;
   }   





}
