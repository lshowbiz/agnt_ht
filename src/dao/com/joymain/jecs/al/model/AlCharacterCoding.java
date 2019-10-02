package com.joymain.jecs.al.model;
// Generated 2008-3-8 15:17:37 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JAL_CHARACTER_CODING"
 *     
 */

public class AlCharacterCoding extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {
    // Fields    
    private Long characterId;
    private String characterCode;
    private String characterName;
    private String allowedUser;

    // Constructors

    /** default constructor */
    public AlCharacterCoding() {
    }

    
    /** full constructor */
    public AlCharacterCoding(String characterCode, String characterName) {
        this.characterCode = characterCode;
        this.characterName = characterName;
    }
    

   
    // Property accessors
    /**
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="CHARACTER_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_AL"
     */

    public Long getCharacterId() {
        return this.characterId;
    }
    
    public void setCharacterId(Long characterId) {
        this.characterId = characterId;
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
    
    /**
     * @spring.validator type="required"
     */
    public void setCharacterCode(String characterCode) {
        this.characterCode = characterCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="CHARACTER_NAME"
     *             length="100"
     *         
     */
    public String getCharacterName() {
        return this.characterName;
    }
    
    /**
     * @spring.validator type="required"
     */
    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }
   
    /**       
     *      *            @hibernate.property
     *             column="ALLOWED_USER"
     *             length="500"
     *         
     */
    public String getAllowedUser() {
    	return allowedUser;
    }


	public void setAllowedUser(String allowedUser) {
    	this.allowedUser = allowedUser;
    }


	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("characterCode").append("='").append(getCharacterCode()).append("' ");			
      buffer.append("characterName").append("='").append(getCharacterName()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AlCharacterCoding) ) return false;
		 AlCharacterCoding castOther = ( AlCharacterCoding ) other; 
         
		 return ( (this.getCharacterId()==castOther.getCharacterId()) || ( this.getCharacterId()!=null && castOther.getCharacterId()!=null && this.getCharacterId().equals(castOther.getCharacterId()) ) )
 && ( (this.getCharacterCode()==castOther.getCharacterCode()) || ( this.getCharacterCode()!=null && castOther.getCharacterCode()!=null && this.getCharacterCode().equals(castOther.getCharacterCode()) ) )
 && ( (this.getCharacterName()==castOther.getCharacterName()) || ( this.getCharacterName()!=null && castOther.getCharacterName()!=null && this.getCharacterName().equals(castOther.getCharacterName()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getCharacterId() == null ? 0 : this.getCharacterId().hashCode() );
         result = 37 * result + ( getCharacterCode() == null ? 0 : this.getCharacterCode().hashCode() );
         result = 37 * result + ( getCharacterName() == null ? 0 : this.getCharacterName().hashCode() );
         return result;
   }   
}