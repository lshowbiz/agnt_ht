package com.joymain.jecs.al.model;

import java.util.HashSet;
import java.util.Set;

// Generated 2008-3-7 13:56:42 by Hibernate Tools 3.1.0.beta4
/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JAL_CHARACTER_KEY"
 *     
 */

public class AlCharacterKey extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long keyId;
    private AlCharacterType alCharacterType;
    private String characterKey;
    private String keyDesc;
    private Set alCharacterValues = new HashSet(0);


    // Constructors

    /** default constructor */
    public AlCharacterKey() {
    }

    
    /** full constructor */
    public AlCharacterKey(String characterKey, String keyDesc) {
        this.characterKey = characterKey;
        this.keyDesc = keyDesc;
    }
    

   
    // Property accessors
    /**
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="KEY_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_AL"
     */

    public Long getKeyId() {
        return this.keyId;
    }
    
    public void setKeyId(Long keyId) {
        this.keyId = keyId;
    }
    
    /**
	 * *
	 * @hibernate.many-to-one
	 * @hibernate.column name="TYPE_ID" not-null="false"
	 * 
	 */
    public AlCharacterType getAlCharacterType() {
    	return alCharacterType;
    }


	public void setAlCharacterType(AlCharacterType alCharacterType) {
    	this.alCharacterType = alCharacterType;
    }


	/**       
     *      *            @hibernate.property
     *             column="CHARACTER_KEY"
     *             length="150"
     *         
     */

    public String getCharacterKey() {
        return this.characterKey;
    }
    
    public void setCharacterKey(String characterKey) {
        this.characterKey = characterKey;
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
	 * *
	 * 
	 * @hibernate.set lazy="true" inverse="true" cascade="all"
	 * @hibernate.collection-key column="KEY_ID"
	 * @hibernate.collection-one-to-many class="com.joymain.jecs.al.model.AlCharacterValue"
	 * 
	 */
    public Set getAlCharacterValues() {
    	return alCharacterValues;
    }

	public void setAlCharacterValues(Set alCharacterValues) {
    	this.alCharacterValues = alCharacterValues;
    }

	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("characterKey").append("='").append(getCharacterKey()).append("' ");			
      buffer.append("keyDesc").append("='").append(getKeyDesc()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AlCharacterKey) ) return false;
		 AlCharacterKey castOther = ( AlCharacterKey ) other; 
         
		 return ( (this.getKeyId()==castOther.getKeyId()) || ( this.getKeyId()!=null && castOther.getKeyId()!=null && this.getKeyId().equals(castOther.getKeyId()) ) )
 && ( (this.getCharacterKey()==castOther.getCharacterKey()) || ( this.getCharacterKey()!=null && castOther.getCharacterKey()!=null && this.getCharacterKey().equals(castOther.getCharacterKey()) ) )
 && ( (this.getKeyDesc()==castOther.getKeyDesc()) || ( this.getKeyDesc()!=null && castOther.getKeyDesc()!=null && this.getKeyDesc().equals(castOther.getKeyDesc()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getKeyId() == null ? 0 : this.getKeyId().hashCode() );
         result = 37 * result + ( getCharacterKey() == null ? 0 : this.getCharacterKey().hashCode() );
         result = 37 * result + ( getKeyDesc() == null ? 0 : this.getKeyDesc().hashCode() );
         return result;
   }   





}
