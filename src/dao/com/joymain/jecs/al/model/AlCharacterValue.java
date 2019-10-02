package com.joymain.jecs.al.model;
// Generated 2008-3-8 15:17:35 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JAL_CHARACTER_VALUE"
 *     
 */

public class AlCharacterValue extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long valueId;
    private AlCharacterKey alCharacterKey;
    private String characterValue;
    private String characterCode;


    // Constructors

    /** default constructor */
    public AlCharacterValue() {
    }

    
    /** full constructor */
    public AlCharacterValue(String characterValue, String characterCode) {
        this.characterValue = characterValue;
        this.characterCode = characterCode;
    }
    

   
    // Property accessors
    /**
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="VALUE_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_AL"
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

	public AlCharacterKey getAlCharacterKey() {
		return this.alCharacterKey;
	}

	public void setAlCharacterKey(AlCharacterKey alCharacterKey) {
		this.alCharacterKey = alCharacterKey;
	}

	/**       
     *      *            @hibernate.property
     *             column="CHARACTER_VALUE"
     *             length="500"
     *         
     */

    public String getCharacterValue() {
        return this.characterValue;
    }
    
    public void setCharacterValue(String characterValue) {
        this.characterValue = characterValue;
    }
    /**       
     *      *            @hibernate.property
     *             column="CHARACTER_CODE"
     *             length="10"
     *         
     */

    public String getCharacterCode() {
        return this.characterCode;
    }
    
    public void setCharacterCode(String characterCode) {
        this.characterCode = characterCode;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("characterValue").append("='").append(getCharacterValue()).append("' ");			
      buffer.append("characterCode").append("='").append(getCharacterCode()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AlCharacterValue) ) return false;
		 AlCharacterValue castOther = ( AlCharacterValue ) other; 
         
		 return ( (this.getValueId()==castOther.getValueId()) || ( this.getValueId()!=null && castOther.getValueId()!=null && this.getValueId().equals(castOther.getValueId()) ) )
 && ( (this.getCharacterValue()==castOther.getCharacterValue()) || ( this.getCharacterValue()!=null && castOther.getCharacterValue()!=null && this.getCharacterValue().equals(castOther.getCharacterValue()) ) )
 && ( (this.getCharacterCode()==castOther.getCharacterCode()) || ( this.getCharacterCode()!=null && castOther.getCharacterCode()!=null && this.getCharacterCode().equals(castOther.getCharacterCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getValueId() == null ? 0 : this.getValueId().hashCode() );
         result = 37 * result + ( getCharacterValue() == null ? 0 : this.getCharacterValue().hashCode() );
         result = 37 * result + ( getCharacterCode() == null ? 0 : this.getCharacterCode().hashCode() );
         return result;
   }   





}
