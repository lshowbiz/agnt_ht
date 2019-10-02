package com.joymain.jecs.al.model;
// Generated 2008-3-8 15:17:36 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JAL_CURRENCY"
 *     
 */

public class AlCurrency extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long currencyId;
    private String currencyCode;
    private String currencyName;
    private Double exchangeRate;
    private String currencySymbol;
    private String currencyStyle;
    private String currencyKey;


    // Constructors

    /** default constructor */
    public AlCurrency() {
    }

	/** minimal constructor */
    public AlCurrency(String currencyCode, String currencyKey) {
        this.currencyCode = currencyCode;
        this.currencyKey = currencyKey;
    }
    
    /** full constructor */
    public AlCurrency(String currencyCode, String currencyName, Double exchangeRate, String currencySymbol, String currencyStyle, String currencyKey) {
        this.currencyCode = currencyCode;
        this.currencyName = currencyName;
        this.exchangeRate = exchangeRate;
        this.currencySymbol = currencySymbol;
        this.currencyStyle = currencyStyle;
        this.currencyKey = currencyKey;
    }
    

   
    // Property accessors
    /**
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="CURRENCY_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_AL"
     */
    public Long getCurrencyId() {
        return this.currencyId;
    }
    
    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }
    /**       
     *      *            @hibernate.property
     *             column="CURRENCY_CODE"
     *             length="10"
     *             not-null="true"
     *         
     */

    public String getCurrencyCode() {
        return this.currencyCode;
    }
    
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="CURRENCY_NAME"
     *             length="100"
     *         
     */

    public String getCurrencyName() {
        return this.currencyName;
    }
    
    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }
    /**       
     *      *            @hibernate.property
     *             column="EXCHANGE_RATE"
     *             length="126"
     *         
     */

    public Double getExchangeRate() {
        return this.exchangeRate;
    }
    
    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
    /**       
     *      *            @hibernate.property
     *             column="CURRENCY_SYMBOL"
     *             length="2"
     *         
     */

    public String getCurrencySymbol() {
        return this.currencySymbol;
    }
    
    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }
    /**       
     *      *            @hibernate.property
     *             column="CURRENCY_STYLE"
     *             length="50"
     *         
     */

    public String getCurrencyStyle() {
        return this.currencyStyle;
    }
    
    public void setCurrencyStyle(String currencyStyle) {
        this.currencyStyle = currencyStyle;
    }
    /**       
     *      *            @hibernate.property
     *             column="CURRENCY_KEY"
     *             length="150"
     *             not-null="true"
     *         
     */

    public String getCurrencyKey() {
        return this.currencyKey;
    }
    
    public void setCurrencyKey(String currencyKey) {
        this.currencyKey = currencyKey;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("currencyCode").append("='").append(getCurrencyCode()).append("' ");			
      buffer.append("currencyName").append("='").append(getCurrencyName()).append("' ");			
      buffer.append("exchangeRate").append("='").append(getExchangeRate()).append("' ");			
      buffer.append("currencySymbol").append("='").append(getCurrencySymbol()).append("' ");			
      buffer.append("currencyStyle").append("='").append(getCurrencyStyle()).append("' ");			
      buffer.append("currencyKey").append("='").append(getCurrencyKey()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AlCurrency) ) return false;
		 AlCurrency castOther = ( AlCurrency ) other; 
         
		 return ( (this.getCurrencyId()==castOther.getCurrencyId()) || ( this.getCurrencyId()!=null && castOther.getCurrencyId()!=null && this.getCurrencyId().equals(castOther.getCurrencyId()) ) )
 && ( (this.getCurrencyCode()==castOther.getCurrencyCode()) || ( this.getCurrencyCode()!=null && castOther.getCurrencyCode()!=null && this.getCurrencyCode().equals(castOther.getCurrencyCode()) ) )
 && ( (this.getCurrencyName()==castOther.getCurrencyName()) || ( this.getCurrencyName()!=null && castOther.getCurrencyName()!=null && this.getCurrencyName().equals(castOther.getCurrencyName()) ) )
 && ( (this.getExchangeRate()==castOther.getExchangeRate()) || ( this.getExchangeRate()!=null && castOther.getExchangeRate()!=null && this.getExchangeRate().equals(castOther.getExchangeRate()) ) )
 && ( (this.getCurrencySymbol()==castOther.getCurrencySymbol()) || ( this.getCurrencySymbol()!=null && castOther.getCurrencySymbol()!=null && this.getCurrencySymbol().equals(castOther.getCurrencySymbol()) ) )
 && ( (this.getCurrencyStyle()==castOther.getCurrencyStyle()) || ( this.getCurrencyStyle()!=null && castOther.getCurrencyStyle()!=null && this.getCurrencyStyle().equals(castOther.getCurrencyStyle()) ) )
 && ( (this.getCurrencyKey()==castOther.getCurrencyKey()) || ( this.getCurrencyKey()!=null && castOther.getCurrencyKey()!=null && this.getCurrencyKey().equals(castOther.getCurrencyKey()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getCurrencyId() == null ? 0 : this.getCurrencyId().hashCode() );
         result = 37 * result + ( getCurrencyCode() == null ? 0 : this.getCurrencyCode().hashCode() );
         result = 37 * result + ( getCurrencyName() == null ? 0 : this.getCurrencyName().hashCode() );
         result = 37 * result + ( getExchangeRate() == null ? 0 : this.getExchangeRate().hashCode() );
         result = 37 * result + ( getCurrencySymbol() == null ? 0 : this.getCurrencySymbol().hashCode() );
         result = 37 * result + ( getCurrencyStyle() == null ? 0 : this.getCurrencyStyle().hashCode() );
         result = 37 * result + ( getCurrencyKey() == null ? 0 : this.getCurrencyKey().hashCode() );
         return result;
   }   





}
