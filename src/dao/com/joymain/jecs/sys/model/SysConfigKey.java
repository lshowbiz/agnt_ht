package com.joymain.jecs.sys.model;

import java.util.HashSet;
import java.util.Set;
// Generated 2008-3-17 12:02:16 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JSYS_CONFIG_KEY"
 *     
 */

public class SysConfigKey extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long keyId;
    private String configCode;
    private String keyDesc;
    private String defaultValue;
    private Set sysConfigValues = new HashSet(0);

    // Constructors

    /** default constructor */
    public SysConfigKey() {
    }

	/** minimal constructor */
    public SysConfigKey(String configCode, String defaultValue) {
        this.configCode = configCode;
        this.defaultValue = defaultValue;
    }
    
    /** full constructor */
    public SysConfigKey(String configCode, String keyDesc, String defaultValue) {
        this.configCode = configCode;
        this.keyDesc = keyDesc;
        this.defaultValue = defaultValue;
    }
    

   
    // Property accessors
    /**
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="KEY_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_SYS"
     */
    public Long getKeyId() {
        return this.keyId;
    }
    
    public void setKeyId(Long keyId) {
        this.keyId = keyId;
    }
    /**       
     *      *            @hibernate.property
     *             column="CONFIG_CODE"
     *             length="50"
     *             not-null="true"
     *         
     */

    public String getConfigCode() {
        return this.configCode;
    }
    
    public void setConfigCode(String configCode) {
        this.configCode = configCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="KEY_DESC"
     *             length="250"
     *         
     */

    public String getKeyDesc() {
        return this.keyDesc;
    }
    
    public void setKeyDesc(String keyDesc) {
        this.keyDesc = keyDesc;
    }
    /**       
     *      *            @hibernate.property
     *             column="DEFAULT_VALUE"
     *             length="100"
     *             
     *         
     */

    public String getDefaultValue() {
        return this.defaultValue;
    }
    
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
   
    /**
	 * *
	 * 
	 * @hibernate.set lazy="true" inverse="true" cascade="all"
	 * @hibernate.collection-key column="KEY_ID"
	 * @hibernate.collection-one-to-many class="com.joymain.jecs.sys.model.SysConfigValue"
	 * 
	 */
    public Set getSysConfigValues() {
    	return sysConfigValues;
    }

	public void setSysConfigValues(Set sysConfigValues) {
    	this.sysConfigValues = sysConfigValues;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("configCode").append("='").append(getConfigCode()).append("' ");			
      buffer.append("keyDesc").append("='").append(getKeyDesc()).append("' ");			
      buffer.append("defaultValue").append("='").append(getDefaultValue()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SysConfigKey) ) return false;
		 SysConfigKey castOther = ( SysConfigKey ) other; 
         
		 return ( (this.getKeyId()==castOther.getKeyId()) || ( this.getKeyId()!=null && castOther.getKeyId()!=null && this.getKeyId().equals(castOther.getKeyId()) ) )
 && ( (this.getConfigCode()==castOther.getConfigCode()) || ( this.getConfigCode()!=null && castOther.getConfigCode()!=null && this.getConfigCode().equals(castOther.getConfigCode()) ) )
 && ( (this.getKeyDesc()==castOther.getKeyDesc()) || ( this.getKeyDesc()!=null && castOther.getKeyDesc()!=null && this.getKeyDesc().equals(castOther.getKeyDesc()) ) )
 && ( (this.getDefaultValue()==castOther.getDefaultValue()) || ( this.getDefaultValue()!=null && castOther.getDefaultValue()!=null && this.getDefaultValue().equals(castOther.getDefaultValue()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getKeyId() == null ? 0 : this.getKeyId().hashCode() );
         result = 37 * result + ( getConfigCode() == null ? 0 : this.getConfigCode().hashCode() );
         result = 37 * result + ( getKeyDesc() == null ? 0 : this.getKeyDesc().hashCode() );
         result = 37 * result + ( getDefaultValue() == null ? 0 : this.getDefaultValue().hashCode() );
         return result;
   }   





}
