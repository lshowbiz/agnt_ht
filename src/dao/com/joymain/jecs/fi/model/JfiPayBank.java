package com.joymain.jecs.fi.model;
// Generated 2009-9-14 16:20:11 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JFI_PAY_BANK"
 *     
 */

public class JfiPayBank extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private String accountCode;
    private String companyCode;
    private String bankName;
    private String bankCity;
    private String accountName;
    private String accountNo;
    private String serialNo;


    // Constructors

    /** default constructor */
    public JfiPayBank() {
    }

	/** minimal constructor */
    public JfiPayBank(String companyCode, String bankName, String accountName, String accountNo) {
        this.companyCode = companyCode;
        this.bankName = bankName;
        this.accountName = accountName;
        this.accountNo = accountNo;
    }
    
    /** full constructor */
    public JfiPayBank(String companyCode, String bankName, String bankCity, String accountName, String accountNo, String serialNo) {
        this.companyCode = companyCode;
        this.bankName = bankName;
        this.bankCity = bankCity;
        this.accountName = accountName;
        this.accountNo = accountNo;
        this.serialNo = serialNo;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.String"
     *             column="ACCOUNT_CODE"
     *             not-null="true"
     *         
     */

    public String getAccountCode() {
        return this.accountCode;
    }
    
    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
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
     *             column="BANK_NAME"
     *             length="100"
     *             not-null="true"
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
     *             column="BANK_CITY"
     *             length="50"
     *         
     */

    public String getBankCity() {
        return this.bankCity;
    }
    
    public void setBankCity(String bankCity) {
        this.bankCity = bankCity;
    }
    /**       
     *      *            @hibernate.property
     *             column="ACCOUNT_NAME"
     *             length="50"
     *             not-null="true"
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
     *             column="ACCOUNT_NO"
     *             length="50"
     *             not-null="true"
     *         
     */

    public String getAccountNo() {
        return this.accountNo;
    }
    
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="SERIAL_NO"
     *             length="10"
     *         
     */

    public String getSerialNo() {
        return this.serialNo;
    }
    
    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("bankName").append("='").append(getBankName()).append("' ");			
      buffer.append("bankCity").append("='").append(getBankCity()).append("' ");			
      buffer.append("accountName").append("='").append(getAccountName()).append("' ");			
      buffer.append("accountNo").append("='").append(getAccountNo()).append("' ");			
      buffer.append("serialNo").append("='").append(getSerialNo()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JfiPayBank) ) return false;
		 JfiPayBank castOther = ( JfiPayBank ) other; 
         
		 return ( (this.getAccountCode()==castOther.getAccountCode()) || ( this.getAccountCode()!=null && castOther.getAccountCode()!=null && this.getAccountCode().equals(castOther.getAccountCode()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getBankName()==castOther.getBankName()) || ( this.getBankName()!=null && castOther.getBankName()!=null && this.getBankName().equals(castOther.getBankName()) ) )
 && ( (this.getBankCity()==castOther.getBankCity()) || ( this.getBankCity()!=null && castOther.getBankCity()!=null && this.getBankCity().equals(castOther.getBankCity()) ) )
 && ( (this.getAccountName()==castOther.getAccountName()) || ( this.getAccountName()!=null && castOther.getAccountName()!=null && this.getAccountName().equals(castOther.getAccountName()) ) )
 && ( (this.getAccountNo()==castOther.getAccountNo()) || ( this.getAccountNo()!=null && castOther.getAccountNo()!=null && this.getAccountNo().equals(castOther.getAccountNo()) ) )
 && ( (this.getSerialNo()==castOther.getSerialNo()) || ( this.getSerialNo()!=null && castOther.getSerialNo()!=null && this.getSerialNo().equals(castOther.getSerialNo()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getAccountCode() == null ? 0 : this.getAccountCode().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getBankName() == null ? 0 : this.getBankName().hashCode() );
         result = 37 * result + ( getBankCity() == null ? 0 : this.getBankCity().hashCode() );
         result = 37 * result + ( getAccountName() == null ? 0 : this.getAccountName().hashCode() );
         result = 37 * result + ( getAccountNo() == null ? 0 : this.getAccountNo().hashCode() );
         result = 37 * result + ( getSerialNo() == null ? 0 : this.getSerialNo().hashCode() );
         return result;
   }   





}
