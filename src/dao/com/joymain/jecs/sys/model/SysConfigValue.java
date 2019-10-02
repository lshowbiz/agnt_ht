package com.joymain.jecs.sys.model;
// Generated 2008-3-17 12:37:30 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JSYS_CONFIG_VALUE"
 *     
 */

public class SysConfigValue extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long valueId;
    private SysConfigKey sysConfigKey;
    private String companyCode;
    private String configValue;


    // Constructors

    /** default constructor */
    public SysConfigValue() {
    }

    
    /** full constructor */
    public SysConfigValue(String companyCode, String configValue) {
        this.companyCode = companyCode;
        this.configValue = configValue;
    }
    

   
    // Property accessors
    /**
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="VALUE_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_SYS"
     */
    public Long getValueId() {
        return this.valueId;
    }
    
    public void setValueId(Long valueId) {
        this.valueId = valueId;
    }
    
    /**
	 * *
	 * @hibernate.many-to-one not-null="true"
	 * @hibernate.column name="KEY_ID"
	 * 
	 */

	public SysConfigKey getSysConfigKey() {
		return this.sysConfigKey;
	}

	public void setSysConfigKey(SysConfigKey sysConfigKey) {
		this.sysConfigKey = sysConfigKey;
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
     *             column="CONFIG_VALUE"
     *             length="100"
     *             not-null="true"
     *         
     */

    public String getConfigValue() {
        return this.configValue;
    }
    
    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("configValue").append("='").append(getConfigValue()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SysConfigValue) ) return false;
		 SysConfigValue castOther = ( SysConfigValue ) other; 
         
		 return ( (this.getValueId()==castOther.getValueId()) || ( this.getValueId()!=null && castOther.getValueId()!=null && this.getValueId().equals(castOther.getValueId()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getConfigValue()==castOther.getConfigValue()) || ( this.getConfigValue()!=null && castOther.getConfigValue()!=null && this.getConfigValue().equals(castOther.getConfigValue()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getValueId() == null ? 0 : this.getValueId().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getConfigValue() == null ? 0 : this.getConfigValue().hashCode() );
         return result;
   }   





}
