package com.joymain.jecs.am.model;
// Generated 2013-11-4 10:40:04 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="INW_DEMANDSORT"
 *     
 */

public class InwDemandsort extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private String name;
    private String requireIntroduction;
    private String image;
    private String other;


    // Constructors

    /** default constructor */
    public InwDemandsort() {
    }

    
    /** full constructor */
    public InwDemandsort(String name, String requireIntroduction, String image, String other) {
        this.name = name;
        this.requireIntroduction = requireIntroduction;
        this.image = image;
        this.other = other;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ID"
     *         @hibernate.generator-param name="sequence" value="SEQ_AM" 
     */
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    /**       
     *      *            @hibernate.property
     *             column="NAME"
     *             length="100"
     *         
     */

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    /**       
     *      *            @hibernate.property
     *             column="REQUIRE_INTRODUCTION"
     *             length="500"
     *         
     */

    public String getRequireIntroduction() {
        return this.requireIntroduction;
    }
    
    public void setRequireIntroduction(String requireIntroduction) {
        this.requireIntroduction = requireIntroduction;
    }
    /**       
     *      *            @hibernate.property
     *             column="IMAGE"
     *             length="300"
     *         
     */

    public String getImage() {
        return this.image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }
    /**       
     *      *            @hibernate.property
     *             column="OTHER"
     *             length="100"
     *         
     */

    public String getOther() {
        return this.other;
    }
    
    public void setOther(String other) {
        this.other = other;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("name").append("='").append(getName()).append("' ");			
      buffer.append("requireIntroduction").append("='").append(getRequireIntroduction()).append("' ");			
      buffer.append("image").append("='").append(getImage()).append("' ");			
      buffer.append("other").append("='").append(getOther()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof InwDemandsort) ) return false;
		 InwDemandsort castOther = ( InwDemandsort ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getName()==castOther.getName()) || ( this.getName()!=null && castOther.getName()!=null && this.getName().equals(castOther.getName()) ) )
 && ( (this.getRequireIntroduction()==castOther.getRequireIntroduction()) || ( this.getRequireIntroduction()!=null && castOther.getRequireIntroduction()!=null && this.getRequireIntroduction().equals(castOther.getRequireIntroduction()) ) )
 && ( (this.getImage()==castOther.getImage()) || ( this.getImage()!=null && castOther.getImage()!=null && this.getImage().equals(castOther.getImage()) ) )
 && ( (this.getOther()==castOther.getOther()) || ( this.getOther()!=null && castOther.getOther()!=null && this.getOther().equals(castOther.getOther()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getName() == null ? 0 : this.getName().hashCode() );
         result = 37 * result + ( getRequireIntroduction() == null ? 0 : this.getRequireIntroduction().hashCode() );
         result = 37 * result + ( getImage() == null ? 0 : this.getImage().hashCode() );
         result = 37 * result + ( getOther() == null ? 0 : this.getOther().hashCode() );
         return result;
   }   





}
