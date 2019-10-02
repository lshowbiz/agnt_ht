package com.joymain.jecs.fi.model;
// Generated 2015-7-23 15:38:53 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="FI_PAY_ACCOUNT_CONFIG"
 *     
 */

public class FiPayAccountConfig extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private String province;
    private String provinceName;
    
    private FiPayAccount fiPayAccount;


    // Constructors

    /** default constructor */
    public FiPayAccountConfig() {
    }



   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.String"
     *             column="PROVINCE"
     *         
     */

    public String getProvince() {
        return this.province;
    }
    
    public void setProvince(String province) {
        this.province = province;
    }

   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      //buffer.append("accountId").append("='").append(getAccountId()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof FiPayAccountConfig) ) return false;
		 FiPayAccountConfig castOther = ( FiPayAccountConfig ) other; 
         
		 return ( (this.getProvince()==castOther.getProvince()) || ( this.getProvince()!=null && castOther.getProvince()!=null && this.getProvince().equals(castOther.getProvince()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getProvince() == null ? 0 : this.getProvince().hashCode() );
         //result = 37 * result + ( getAccountId() == null ? 0 : this.getAccountId().hashCode() );
         return result;
   }

   /**
    * *
    * @hibernate.many-to-one not-null="true"
    * @hibernate.column name="ACCOUNT_ID"
    * 
    */
	public FiPayAccount getFiPayAccount() {
		return fiPayAccount;
	}
	
	
	public void setFiPayAccount(FiPayAccount fiPayAccount) {
		this.fiPayAccount = fiPayAccount;
	}



	 /**       
     *      *            @hibernate.property
     *             column="PROVINCE_NAME"
     *             length="50"
     *             not-null="true"
     *         
     */
	public String getProvinceName() {
		return provinceName;
	}




	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}   





}
