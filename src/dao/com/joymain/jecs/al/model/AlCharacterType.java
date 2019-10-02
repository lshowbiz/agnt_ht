package com.joymain.jecs.al.model;
// Generated 2008-3-22 11:20:28 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JAL_CHARACTER_TYPE"
 *     
 */

public class AlCharacterType extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long typeId;
    private String typeName;
    private Long parentId;
    private Integer typeLevel;


    // Constructors

    /** default constructor */
    public AlCharacterType() {
    }
  
    // Property accessors
    /**
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="TYPE_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_AL"
     */
    public Long getTypeId() {
        return this.typeId;
    }
    
    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }
    /**       
     *      *            @hibernate.property
     *             column="TYPE_NAME"
     *             length="150"
     *             not-null="true"
     *         
     */

    public String getTypeName() {
        return this.typeName;
    }
    
    /**
	 * @spring.validator type="required"
	 * @param typeName String
	 */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    
    /**       
     *      *            @hibernate.property
     *             column="PARENT_ID"
     *             length="12"
     *             not-null="true"
     *         
     */

    public Long getParentId() {
        return this.parentId;
    }

    /**
     *
     * @param parentId AlCharacterType
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    /**       
     *      *            @hibernate.property
     *             column="TYPE_LEVEL"
     *             length="8"
     *             not-null="true"
     *         
     */

    public Integer getTypeLevel() {
        return this.typeLevel;
    }
    
    public void setTypeLevel(Integer typeLevel) {
        this.typeLevel = typeLevel;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("typeName").append("='").append(getTypeName()).append("' ");			
      buffer.append("typeLevel").append("='").append(getTypeLevel()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AlCharacterType) ) return false;
		 AlCharacterType castOther = ( AlCharacterType ) other; 
         
		 return ( (this.getTypeId()==castOther.getTypeId()) || ( this.getTypeId()!=null && castOther.getTypeId()!=null && this.getTypeId().equals(castOther.getTypeId()) ) )
 && ( (this.getTypeName()==castOther.getTypeName()) || ( this.getTypeName()!=null && castOther.getTypeName()!=null && this.getTypeName().equals(castOther.getTypeName()) ) )
 && (this.getTypeLevel()==castOther.getTypeLevel());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getTypeId() == null ? 0 : this.getTypeId().hashCode() );
         result = 37 * result + ( getTypeName() == null ? 0 : this.getTypeName().hashCode() );
         result = 37 * result + ( getTypeLevel() == null ? 0 : this.getTypeLevel().hashCode() );
         return result;
   }   





}
