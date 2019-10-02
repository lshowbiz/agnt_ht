package com.joymain.jecs.bd.model;
// Generated 2009-9-23 11:31:59 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JBD_OUTWARD_BANK"
 *     
 */

public class BdOutwardBank extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long bankId;
    private String companyCode;
    private String bankCode;
    private String bankName;
    private String cityName;
    private String accountName;
    private String accountCode;


    // Constructors

    /** default constructor */
    public BdOutwardBank() {
    }

    
    /** full constructor */
    public BdOutwardBank(String companyCode, String bankCode, String bankName, String cityName, String accountName, String accountCode) {
        this.companyCode = companyCode;
        this.bankCode = bankCode;
        this.bankName = bankName;
        this.cityName = cityName;
        this.accountName = accountName;
        this.accountCode = accountCode;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="BANK_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_BD"
     *         
     */

    public Long getBankId() {
        return this.bankId;
    }
    
    public void setBankId(Long bankId) {
        this.bankId = bankId;
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
     *      *            @hibernate.property
     *             column="BANK_CODE"
     *             length="200"
     *         
     */

    public String getBankCode() {
        return this.bankCode;
    }
    
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="BANK_NAME"
     *             length="200"
     *         
     */

    public String getBankName() {
        return this.bankName;
    }
    
    public void setBankName(String bankName) {
        this.bankName = bankName;
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
     *      *            @hibernate.property
     *             column="ACCOUNT_NAME"
     *             length="200"
     *         
     */

    public String getAccountName() {
        return this.accountName;
    }
    
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    /**       
     *      *            @hibernate.property
     *             column="ACCOUNT_CODE"
     *             length="200"
     *         
     */

    public String getAccountCode() {
        return this.accountCode;
    }
    
    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("bankCode").append("='").append(getBankCode()).append("' ");			
      buffer.append("bankName").append("='").append(getBankName()).append("' ");			
      buffer.append("cityName").append("='").append(getCityName()).append("' ");			
      buffer.append("accountName").append("='").append(getAccountName()).append("' ");			
      buffer.append("accountCode").append("='").append(getAccountCode()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof BdOutwardBank) ) return false;
		 BdOutwardBank castOther = ( BdOutwardBank ) other; 
         
		 return ( (this.getBankId()==castOther.getBankId()) || ( this.getBankId()!=null && castOther.getBankId()!=null && this.getBankId().equals(castOther.getBankId()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getBankCode()==castOther.getBankCode()) || ( this.getBankCode()!=null && castOther.getBankCode()!=null && this.getBankCode().equals(castOther.getBankCode()) ) )
 && ( (this.getBankName()==castOther.getBankName()) || ( this.getBankName()!=null && castOther.getBankName()!=null && this.getBankName().equals(castOther.getBankName()) ) )
 && ( (this.getCityName()==castOther.getCityName()) || ( this.getCityName()!=null && castOther.getCityName()!=null && this.getCityName().equals(castOther.getCityName()) ) )
 && ( (this.getAccountName()==castOther.getAccountName()) || ( this.getAccountName()!=null && castOther.getAccountName()!=null && this.getAccountName().equals(castOther.getAccountName()) ) )
 && ( (this.getAccountCode()==castOther.getAccountCode()) || ( this.getAccountCode()!=null && castOther.getAccountCode()!=null && this.getAccountCode().equals(castOther.getAccountCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getBankId() == null ? 0 : this.getBankId().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getBankCode() == null ? 0 : this.getBankCode().hashCode() );
         result = 37 * result + ( getBankName() == null ? 0 : this.getBankName().hashCode() );
         result = 37 * result + ( getCityName() == null ? 0 : this.getCityName().hashCode() );
         result = 37 * result + ( getAccountName() == null ? 0 : this.getAccountName().hashCode() );
         result = 37 * result + ( getAccountCode() == null ? 0 : this.getAccountCode().hashCode() );
         return result;
   }   





}
