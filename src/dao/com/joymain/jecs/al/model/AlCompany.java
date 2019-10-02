package com.joymain.jecs.al.model;
// Generated 2008-3-8 15:17:36 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JAL_COMPANY"
 *     
 */

public class AlCompany extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long companyId;
    private String companyCode;
    private String companyName;
    private String characterCode;
    private String currencyCode;
    private String preAgentCode;
    private String regName;
    private Double taxRate;
    private String taxType;
    private String zipCode;
    private String countryCode;
    private String stateProvinceCode;
    private String city;
    private String address;
    private String phone;
    private BigDecimal timeZone;
    private String dateFormat;
    private String timeFormat;
    private BigDecimal exchangeRate;
    private String productNo;

    // Constructors

    /** default constructor */
    public AlCompany() {
    }

	/** minimal constructor */
    public AlCompany(String companyCode, String companyName, String characterCode) {
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.characterCode = characterCode;
    }
    
    /** full constructor */
    public AlCompany(String companyCode, String companyName, String characterCode, String currencyCode, String preAgentCode, String regName, Double taxRate, String taxType, String zipCode, String countryCode, String stateProvinceCode, String city, String address, String phone, BigDecimal timeZone, String dateFormat, String timeFormat) {
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.characterCode = characterCode;
        this.currencyCode = currencyCode;
        this.preAgentCode = preAgentCode;
        this.regName = regName;
        this.taxRate = taxRate;
        this.taxType = taxType;
        this.zipCode = zipCode;
        this.countryCode = countryCode;
        this.stateProvinceCode = stateProvinceCode;
        this.city = city;
        this.address = address;
        this.phone = phone;
        this.timeZone = timeZone;
        this.dateFormat = dateFormat;
        this.timeFormat = timeFormat;
    }
    

   
    // Property accessors   
    /**
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="COMPANY_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_AL"
     */

    public Long getCompanyId() {
        return this.companyId;
    }
    
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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
    
    /**
     * @spring.validator type="required"
     */
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="COMPANY_NAME"
     *             length="250"
     *             not-null="true"
     *         
     */

    public String getCompanyName() {
        return this.companyName;
    }
    
    /**
     * @spring.validator type="required"
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    /**       
     *      *            @hibernate.property
     *             column="CHARACTER_CODE"
     *             length="10"
     *             not-null="true"
     *         
     */

    public String getCharacterCode() {
        return this.characterCode;
    }
    
    public void setCharacterCode(String characterCode) {
        this.characterCode = characterCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="CURRENCY_CODE"
     *             length="10"
     *         
     */

    public String getCurrencyCode() {
        return this.currencyCode;
    }
    
    /**
     * @spring.validator type="required"
     */
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="PRE_AGENT_CODE"
     *             length="30"
     *         
     */

    public String getPreAgentCode() {
        return this.preAgentCode;
    }
    
    public void setPreAgentCode(String preAgentCode) {
        this.preAgentCode = preAgentCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="REG_NAME"
     *             length="250"
     *         
     */

    public String getRegName() {
        return this.regName;
    }
    
    /**
     * @spring.validator type="required"
     */
    public void setRegName(String regName) {
        this.regName = regName;
    }
    /**       
     *      *            @hibernate.property
     *             column="TAX_RATE"
     *             length="126"
     *         
     */

    public Double getTaxRate() {
        return this.taxRate;
    }
    
    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }
    /**       
     *      *            @hibernate.property
     *             column="TAX_TYPE"
     *             length="2"
     *         
     */

    public String getTaxType() {
        return this.taxType;
    }
    
    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }
    /**       
     *      *            @hibernate.property
     *             column="ZIP_CODE"
     *             length="10"
     *         
     */

    public String getZipCode() {
        return this.zipCode;
    }
    
    /**
     * @spring.validator type="required"
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="COUNTRY_CODE"
     *             length="2"
     *         
     */

    public String getCountryCode() {
        return this.countryCode;
    }
    
    /**
     * @spring.validator type="required"
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="STATE_PROVINCE_CODE"
     *             length="50"
     *         
     */

    public String getStateProvinceCode() {
        return this.stateProvinceCode;
    }
    
    public void setStateProvinceCode(String stateProvinceCode) {
        this.stateProvinceCode = stateProvinceCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="CITY"
     *             length="50"
     *         
     */

    public String getCity() {
        return this.city;
    }
    
    /**
     * @spring.validator type="required"
     */
    public void setCity(String city) {
        this.city = city;
    }
    /**       
     *      *            @hibernate.property
     *             column="ADDRESS"
     *             length="250"
     *         
     */

    public String getAddress() {
        return this.address;
    }
    
    /**
     * @spring.validator type="required"
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**       
     *      *            @hibernate.property
     *             column="PHONE"
     *             length="30"
     *         
     */

    public String getPhone() {
        return this.phone;
    }
    
    /**
     * @spring.validator type="required"
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    /**       
     *      *            @hibernate.property
     *             column="TIME_ZONE"
     *             length="22"
     *         
     */

    public BigDecimal getTimeZone() {
        return this.timeZone;
    }
    
    public void setTimeZone(BigDecimal timeZone) {
        this.timeZone = timeZone;
    }
    /**       
     *      *            @hibernate.property
     *             column="DATE_FORMAT"
     *             length="50"
     *         
     */

    public String getDateFormat() {
        return this.dateFormat;
    }
    
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }
    /**       
     *      *            @hibernate.property
     *             column="TIME_FORMAT"
     *             length="50"
     *         
     */

    public String getTimeFormat() {
        return this.timeFormat;
    }
    
    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }
   
    /**       
     *      *            @hibernate.property
     *             column="EXCHANGE_RATE"
     *             length="18"
     *         
     */
    public BigDecimal getExchangeRate() {
    	return exchangeRate;
    }

	public void setExchangeRate(BigDecimal exchangeRate) {
    	this.exchangeRate = exchangeRate;
    }

	/**       
     *      *            @hibernate.property
     *             column="PRODUCT_NO"
     *             length="20"
     *         
     */
	public String getProductNo() {
    	return productNo;
    }

	public void setProductNo(String productNo) {
    	this.productNo = productNo;
    }

	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("companyName").append("='").append(getCompanyName()).append("' ");			
      buffer.append("characterCode").append("='").append(getCharacterCode()).append("' ");			
      buffer.append("currencyCode").append("='").append(getCurrencyCode()).append("' ");			
      buffer.append("preAgentCode").append("='").append(getPreAgentCode()).append("' ");			
      buffer.append("regName").append("='").append(getRegName()).append("' ");			
      buffer.append("taxRate").append("='").append(getTaxRate()).append("' ");			
      buffer.append("taxType").append("='").append(getTaxType()).append("' ");			
      buffer.append("zipCode").append("='").append(getZipCode()).append("' ");			
      buffer.append("countryCode").append("='").append(getCountryCode()).append("' ");			
      buffer.append("stateProvinceCode").append("='").append(getStateProvinceCode()).append("' ");			
      buffer.append("city").append("='").append(getCity()).append("' ");			
      buffer.append("address").append("='").append(getAddress()).append("' ");			
      buffer.append("phone").append("='").append(getPhone()).append("' ");			
      buffer.append("timeZone").append("='").append(getTimeZone()).append("' ");			
      buffer.append("dateFormat").append("='").append(getDateFormat()).append("' ");			
      buffer.append("timeFormat").append("='").append(getTimeFormat()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AlCompany) ) return false;
		 AlCompany castOther = ( AlCompany ) other; 
         
		 return ( (this.getCompanyId()==castOther.getCompanyId()) || ( this.getCompanyId()!=null && castOther.getCompanyId()!=null && this.getCompanyId().equals(castOther.getCompanyId()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getCompanyName()==castOther.getCompanyName()) || ( this.getCompanyName()!=null && castOther.getCompanyName()!=null && this.getCompanyName().equals(castOther.getCompanyName()) ) )
 && ( (this.getCharacterCode()==castOther.getCharacterCode()) || ( this.getCharacterCode()!=null && castOther.getCharacterCode()!=null && this.getCharacterCode().equals(castOther.getCharacterCode()) ) )
 && ( (this.getCurrencyCode()==castOther.getCurrencyCode()) || ( this.getCurrencyCode()!=null && castOther.getCurrencyCode()!=null && this.getCurrencyCode().equals(castOther.getCurrencyCode()) ) )
 && ( (this.getPreAgentCode()==castOther.getPreAgentCode()) || ( this.getPreAgentCode()!=null && castOther.getPreAgentCode()!=null && this.getPreAgentCode().equals(castOther.getPreAgentCode()) ) )
 && ( (this.getRegName()==castOther.getRegName()) || ( this.getRegName()!=null && castOther.getRegName()!=null && this.getRegName().equals(castOther.getRegName()) ) )
 && ( (this.getTaxRate()==castOther.getTaxRate()) || ( this.getTaxRate()!=null && castOther.getTaxRate()!=null && this.getTaxRate().equals(castOther.getTaxRate()) ) )
 && ( (this.getTaxType()==castOther.getTaxType()) || ( this.getTaxType()!=null && castOther.getTaxType()!=null && this.getTaxType().equals(castOther.getTaxType()) ) )
 && ( (this.getZipCode()==castOther.getZipCode()) || ( this.getZipCode()!=null && castOther.getZipCode()!=null && this.getZipCode().equals(castOther.getZipCode()) ) )
 && ( (this.getCountryCode()==castOther.getCountryCode()) || ( this.getCountryCode()!=null && castOther.getCountryCode()!=null && this.getCountryCode().equals(castOther.getCountryCode()) ) )
 && ( (this.getStateProvinceCode()==castOther.getStateProvinceCode()) || ( this.getStateProvinceCode()!=null && castOther.getStateProvinceCode()!=null && this.getStateProvinceCode().equals(castOther.getStateProvinceCode()) ) )
 && ( (this.getCity()==castOther.getCity()) || ( this.getCity()!=null && castOther.getCity()!=null && this.getCity().equals(castOther.getCity()) ) )
 && ( (this.getAddress()==castOther.getAddress()) || ( this.getAddress()!=null && castOther.getAddress()!=null && this.getAddress().equals(castOther.getAddress()) ) )
 && ( (this.getPhone()==castOther.getPhone()) || ( this.getPhone()!=null && castOther.getPhone()!=null && this.getPhone().equals(castOther.getPhone()) ) )
 && ( (this.getTimeZone()==castOther.getTimeZone()) || ( this.getTimeZone()!=null && castOther.getTimeZone()!=null && this.getTimeZone().equals(castOther.getTimeZone()) ) )
 && ( (this.getDateFormat()==castOther.getDateFormat()) || ( this.getDateFormat()!=null && castOther.getDateFormat()!=null && this.getDateFormat().equals(castOther.getDateFormat()) ) )
 && ( (this.getTimeFormat()==castOther.getTimeFormat()) || ( this.getTimeFormat()!=null && castOther.getTimeFormat()!=null && this.getTimeFormat().equals(castOther.getTimeFormat()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getCompanyId() == null ? 0 : this.getCompanyId().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getCompanyName() == null ? 0 : this.getCompanyName().hashCode() );
         result = 37 * result + ( getCharacterCode() == null ? 0 : this.getCharacterCode().hashCode() );
         result = 37 * result + ( getCurrencyCode() == null ? 0 : this.getCurrencyCode().hashCode() );
         result = 37 * result + ( getPreAgentCode() == null ? 0 : this.getPreAgentCode().hashCode() );
         result = 37 * result + ( getRegName() == null ? 0 : this.getRegName().hashCode() );
         result = 37 * result + ( getTaxRate() == null ? 0 : this.getTaxRate().hashCode() );
         result = 37 * result + ( getTaxType() == null ? 0 : this.getTaxType().hashCode() );
         result = 37 * result + ( getZipCode() == null ? 0 : this.getZipCode().hashCode() );
         result = 37 * result + ( getCountryCode() == null ? 0 : this.getCountryCode().hashCode() );
         result = 37 * result + ( getStateProvinceCode() == null ? 0 : this.getStateProvinceCode().hashCode() );
         result = 37 * result + ( getCity() == null ? 0 : this.getCity().hashCode() );
         result = 37 * result + ( getAddress() == null ? 0 : this.getAddress().hashCode() );
         result = 37 * result + ( getPhone() == null ? 0 : this.getPhone().hashCode() );
         result = 37 * result + ( getTimeZone() == null ? 0 : this.getTimeZone().hashCode() );
         result = 37 * result + ( getDateFormat() == null ? 0 : this.getDateFormat().hashCode() );
         result = 37 * result + ( getTimeFormat() == null ? 0 : this.getTimeFormat().hashCode() );
         return result;
   }   





}
