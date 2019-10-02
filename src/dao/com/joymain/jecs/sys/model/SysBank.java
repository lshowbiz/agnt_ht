package com.joymain.jecs.sys.model;
// Generated 2008-9-17 15:59:34 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JSYS_BANK"
 *     
 */

public class SysBank extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long bankId;
    private String bankKey;
    private String bankValue;
    private String companyCode;
    private Integer orderNo;
    private String bankNo;
    private String bankKana;


    // Constructors

    /**       
     *      *            @hibernate.property
     *             column="BANK_KANA"
     *             length="300"
     *         
     */

    public String getBankKana() {
		return bankKana;
	}

	public void setBankKana(String bankKana) {
		this.bankKana = bankKana;
	}

    /**       
     *      *            @hibernate.property
     *             column="BANK_NO"
     *             length="300"
     *         
     */

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	/** default constructor */
    public SysBank() {
    }

	/** minimal constructor */
    public SysBank(String bankKey, String bankValue) {
        this.bankKey = bankKey;
        this.bankValue = bankValue;
    }
    
    /** full constructor */
    public SysBank(String bankKey, String bankValue, String companyCode, Integer orderNo) {
        this.bankKey = bankKey;
        this.bankValue = bankValue;
        this.companyCode = companyCode;
        this.orderNo = orderNo;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="BANK_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_SYS"
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
     *             column="BANK_KEY"
     *             length="300"
     *             not-null="true"
     *         
     */

    public String getBankKey() {
        return this.bankKey;
    }

    public void setBankKey(String bankKey) {
        this.bankKey = bankKey;
    }
    /**       
     *      *            @hibernate.property
     *             column="BANK_VALUE"
     *             length="300"
     *             not-null="true"
     *         
     */

    public String getBankValue() {
        return this.bankValue;
    }

    public void setBankValue(String bankValue) {
        this.bankValue = bankValue;
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
     *             column="ORDER_NO"
     *             length="8"
     *         
     */

    public Integer getOrderNo() {
        return this.orderNo;
    }
    
    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("bankKey").append("='").append(getBankKey()).append("' ");			
      buffer.append("bankValue").append("='").append(getBankValue()).append("' ");			
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("orderNo").append("='").append(getOrderNo()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SysBank) ) return false;
		 SysBank castOther = ( SysBank ) other; 
         
		 return ( (this.getBankId()==castOther.getBankId()) || ( this.getBankId()!=null && castOther.getBankId()!=null && this.getBankId().equals(castOther.getBankId()) ) )
 && ( (this.getBankKey()==castOther.getBankKey()) || ( this.getBankKey()!=null && castOther.getBankKey()!=null && this.getBankKey().equals(castOther.getBankKey()) ) )
 && ( (this.getBankValue()==castOther.getBankValue()) || ( this.getBankValue()!=null && castOther.getBankValue()!=null && this.getBankValue().equals(castOther.getBankValue()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getOrderNo()==castOther.getOrderNo()) || ( this.getOrderNo()!=null && castOther.getOrderNo()!=null && this.getOrderNo().equals(castOther.getOrderNo()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getBankId() == null ? 0 : this.getBankId().hashCode() );
         result = 37 * result + ( getBankKey() == null ? 0 : this.getBankKey().hashCode() );
         result = 37 * result + ( getBankValue() == null ? 0 : this.getBankValue().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getOrderNo() == null ? 0 : this.getOrderNo().hashCode() );
         return result;
   }   





}
