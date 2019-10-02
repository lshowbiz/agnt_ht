package com.joymain.jecs.al.model;

// Generated 2010-11-25 16:26:24 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JAL_POSTALCODE"
 *     
 */

public class JalPostalcode extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long postalcodeId;
    private AlCity alCity;
    private String postalcode;


    // Constructors

    /** default constructor */
    public JalPostalcode() {
    }

    
    /** full constructor */
    public JalPostalcode(Long cityId, String postalcode) {
        this.postalcode = postalcode;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="POSTALCODE_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_AL"
     *         
     */

    public Long getPostalcodeId() {
        return this.postalcodeId;
    }
    
    public void setPostalcodeId(Long postalcodeId) {
        this.postalcodeId = postalcodeId;
    }

    /**       
     *      *            @hibernate.property
     *             column="POSTALCODE"
     *             length="10"
     *         
     */

    public String getPostalcode() {
        return this.postalcode;
    }
    
    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");	
      buffer.append("postalcode").append("='").append(getPostalcode()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JalPostalcode) ) return false;
		 JalPostalcode castOther = ( JalPostalcode ) other; 
         
		 return ( (this.getPostalcodeId()==castOther.getPostalcodeId()) || ( this.getPostalcodeId()!=null && castOther.getPostalcodeId()!=null && this.getPostalcodeId().equals(castOther.getPostalcodeId()) ) )
 && ( (this.getPostalcode()==castOther.getPostalcode()) || ( this.getPostalcode()!=null && castOther.getPostalcode()!=null && this.getPostalcode().equals(castOther.getPostalcode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getPostalcodeId() == null ? 0 : this.getPostalcodeId().hashCode() );
         result = 37 * result + ( getPostalcode() == null ? 0 : this.getPostalcode().hashCode() );
         return result;
   }


   /**
	 * *
	 * @hibernate.many-to-one not-null="true"
	 * @hibernate.column name="CITY_ID"
	 * 
	 */
	public AlCity getAlCity() {
		return alCity;
	}
	
	
	public void setAlCity(AlCity alCity) {
		this.alCity = alCity;
	}   





}
