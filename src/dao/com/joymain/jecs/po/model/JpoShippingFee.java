package com.joymain.jecs.po.model;
// Generated 2009-9-29 13:09:29 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JPO_SHIPPING_FEE"
 *     
 */

public class JpoShippingFee extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long jpsId;
    private String province;
    private String city;
    private String district;
    private String countryCode;
    private String shippingType;
    private BigDecimal shippingFee;
    private BigDecimal shippingWeight;
    private BigDecimal shippingRefee;
    private BigDecimal shippingReweight;


    // Constructors

	/** default constructor */
    public JpoShippingFee() {
    }

	/** minimal constructor */
    public JpoShippingFee(String countryCode, String shippingType, BigDecimal shippingFee) {
        this.countryCode = countryCode;
        this.shippingType = shippingType;
        this.shippingFee = shippingFee;
    }
    
    /** full constructor */
    public JpoShippingFee(String province, String city, String district, String countryCode, String shippingType, BigDecimal shippingFee) {
        this.province = province;
        this.city = city;
        this.district = district;
        this.countryCode = countryCode;
        this.shippingType = shippingType;
        this.shippingFee = shippingFee;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="JPS_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_PO"
     *         
     */

    public Long getJpsId() {
        return this.jpsId;
    }
    
    public void setJpsId(Long jpsId) {
        this.jpsId = jpsId;
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
	 * @spring.validator type="required,maxlength"
	 * @spring.validator-var name="maxlength" value="20"
	 * @spring.validator-args arg1value="20"
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

    /**
	 * @spring.validator type="required,maxlength"
	 * @spring.validator-var name="maxlength" value="20"
	 * @spring.validator-args arg1value="20"
	 */
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

    /**
	 * @spring.validator type="required,maxlength"
	 * @spring.validator-var name="maxlength" value="20"
	 * @spring.validator-args arg1value="20"
	 */
    public void setDistrict(String district) {
        this.district = district;
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
    
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="SHIPPING_TYPE"
     *             length="10"
     *             not-null="true"
     *         
     */

    public String getShippingType() {
        return this.shippingType;
    }
    
    public void setShippingType(String shippingType) {
        this.shippingType = shippingType;
    }
    /**       
     *      *            @hibernate.property
     *             column="SHIPPING_FEE"
     *             length="18"
     *             not-null="true"
     *         
     */

    public BigDecimal getShippingFee() {
        return this.shippingFee;
    }
    
    public void setShippingFee(BigDecimal shippingFee) {
        this.shippingFee = shippingFee;
    }
    /**       
     *      *            @hibernate.property
     *             column="SHIPPING_REFEE"
     *             length="18"
     *             not-null="true"
     *         
     */

    public BigDecimal getShippingRefee() {
        return this.shippingRefee;
    }
    
    public void setShippingRefee(BigDecimal shippingRefee) {
        this.shippingRefee = shippingRefee;
    }
    /**       
     *      *            @hibernate.property
     *             column="SHIPPING_WEIGHT"
     *             length="18"
     *             not-null="true"
     *         
     */
    public BigDecimal getShippingWeight() {
		return shippingWeight;
	}

	public void setShippingWeight(BigDecimal shippingWeight) {
		this.shippingWeight = shippingWeight;
	}
    /**       
     *      *            @hibernate.property
     *             column="SHIPPING_REWEIGHT"
     *             length="18"
     *             not-null="true"
     *         
     */
	public BigDecimal getShippingReweight() {
		return shippingReweight;
	}

	public void setShippingReweight(BigDecimal shippingReweight) {
		this.shippingReweight = shippingReweight;
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
      buffer.append("countryCode").append("='").append(getCountryCode()).append("' ");			
      buffer.append("shippingType").append("='").append(getShippingType()).append("' ");			
      buffer.append("shippingFee").append("='").append(getShippingFee()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JpoShippingFee) ) return false;
		 JpoShippingFee castOther = ( JpoShippingFee ) other; 
         
		 return ( (this.getJpsId()==castOther.getJpsId()) || ( this.getJpsId()!=null && castOther.getJpsId()!=null && this.getJpsId().equals(castOther.getJpsId()) ) )
 && ( (this.getProvince()==castOther.getProvince()) || ( this.getProvince()!=null && castOther.getProvince()!=null && this.getProvince().equals(castOther.getProvince()) ) )
 && ( (this.getCity()==castOther.getCity()) || ( this.getCity()!=null && castOther.getCity()!=null && this.getCity().equals(castOther.getCity()) ) )
 && ( (this.getDistrict()==castOther.getDistrict()) || ( this.getDistrict()!=null && castOther.getDistrict()!=null && this.getDistrict().equals(castOther.getDistrict()) ) )
 && ( (this.getCountryCode()==castOther.getCountryCode()) || ( this.getCountryCode()!=null && castOther.getCountryCode()!=null && this.getCountryCode().equals(castOther.getCountryCode()) ) )
 && ( (this.getShippingType()==castOther.getShippingType()) || ( this.getShippingType()!=null && castOther.getShippingType()!=null && this.getShippingType().equals(castOther.getShippingType()) ) )
 && ( (this.getShippingFee()==castOther.getShippingFee()) || ( this.getShippingFee()!=null && castOther.getShippingFee()!=null && this.getShippingFee().equals(castOther.getShippingFee()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getJpsId() == null ? 0 : this.getJpsId().hashCode() );
         result = 37 * result + ( getProvince() == null ? 0 : this.getProvince().hashCode() );
         result = 37 * result + ( getCity() == null ? 0 : this.getCity().hashCode() );
         result = 37 * result + ( getDistrict() == null ? 0 : this.getDistrict().hashCode() );
         result = 37 * result + ( getCountryCode() == null ? 0 : this.getCountryCode().hashCode() );
         result = 37 * result + ( getShippingType() == null ? 0 : this.getShippingType().hashCode() );
         result = 37 * result + ( getShippingFee() == null ? 0 : this.getShippingFee().hashCode() );
         return result;
   }   





}
