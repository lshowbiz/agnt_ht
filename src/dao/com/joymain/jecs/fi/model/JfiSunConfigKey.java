package com.joymain.jecs.fi.model;
// Generated 2010-1-14 14:35:27 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JFI_SUN_CONFIG_KEY"
 *     
 */

public class JfiSunConfigKey extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private String configCode;
    private String keyDesc;
    private String defaultValue;


    // Constructors

    /** default constructor */
    public JfiSunConfigKey() {
    }

    
    /** full constructor */
    public JfiSunConfigKey(String keyDesc, String defaultValue) {
        this.keyDesc = keyDesc;
        this.defaultValue = defaultValue;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.String"
     *             column="CONFIG_CODE"
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
     *             length="150"
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
     */

    public String getDefaultValue() {
        return this.defaultValue;
    }
    
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("keyDesc").append("='").append(getKeyDesc()).append("' ");			
      buffer.append("defaultValue").append("='").append(getDefaultValue()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JfiSunConfigKey) ) return false;
		 JfiSunConfigKey castOther = ( JfiSunConfigKey ) other; 
         
		 return ( (this.getConfigCode()==castOther.getConfigCode()) || ( this.getConfigCode()!=null && castOther.getConfigCode()!=null && this.getConfigCode().equals(castOther.getConfigCode()) ) )
 && ( (this.getKeyDesc()==castOther.getKeyDesc()) || ( this.getKeyDesc()!=null && castOther.getKeyDesc()!=null && this.getKeyDesc().equals(castOther.getKeyDesc()) ) )
 && ( (this.getDefaultValue()==castOther.getDefaultValue()) || ( this.getDefaultValue()!=null && castOther.getDefaultValue()!=null && this.getDefaultValue().equals(castOther.getDefaultValue()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getConfigCode() == null ? 0 : this.getConfigCode().hashCode() );
         result = 37 * result + ( getKeyDesc() == null ? 0 : this.getKeyDesc().hashCode() );
         result = 37 * result + ( getDefaultValue() == null ? 0 : this.getDefaultValue().hashCode() );
         return result;
   }   





}
