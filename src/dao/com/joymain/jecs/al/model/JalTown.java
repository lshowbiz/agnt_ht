package com.joymain.jecs.al.model;
// Generated 2010-7-26 10:10:44 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JAL_TOWN"
 *     
 */

public class JalTown extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long townId;
    private String townCode;
    private String townName;
    private AlDistrict alDistrict;


    // Constructors

    /** default constructor */
    public JalTown() {
    }

    
    /** full constructor */
    public JalTown(String townCode, String townName) {
        this.townCode = townCode;
        this.townName = townName;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.Long"
     *             column="TOWN_ID"
     *         
     */

    public Long getTownId() {
        return this.townId;
    }
    
    public void setTownId(Long townId) {
        this.townId = townId;
    }
    /**       
     *      *            @hibernate.property
     *             column="TOWN_CODE"
     *             length="30"
     *         
     */

    public String getTownCode() {
        return this.townCode;
    }
    
    public void setTownCode(String townCode) {
        this.townCode = townCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="TOWN_NAME"
     *             length="200"
     *         
     */

    public String getTownName() {
        return this.townName;
    }
    
    public void setTownName(String townName) {
        this.townName = townName;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("townCode").append("='").append(getTownCode()).append("' ");			
      buffer.append("townName").append("='").append(getTownName()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JalTown) ) return false;
		 JalTown castOther = ( JalTown ) other; 
         
		 return ( (this.getTownId()==castOther.getTownId()) || ( this.getTownId()!=null && castOther.getTownId()!=null && this.getTownId().equals(castOther.getTownId()) ) )
 && ( (this.getTownCode()==castOther.getTownCode()) || ( this.getTownCode()!=null && castOther.getTownCode()!=null && this.getTownCode().equals(castOther.getTownCode()) ) )
 && ( (this.getTownName()==castOther.getTownName()) || ( this.getTownName()!=null && castOther.getTownName()!=null && this.getTownName().equals(castOther.getTownName()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getTownId() == null ? 0 : this.getTownId().hashCode() );
         result = 37 * result + ( getTownCode() == null ? 0 : this.getTownCode().hashCode() );
         result = 37 * result + ( getTownName() == null ? 0 : this.getTownName().hashCode() );
         return result;
   }


   /**
	 * *
	 * @hibernate.many-to-one not-null="true"
	 * @hibernate.column name="DISTRICT_ID"
	 * 
	 */
public AlDistrict getAlDistrict() {
	return alDistrict;
}


public void setAlDistrict(AlDistrict alDistrict) {
	this.alDistrict = alDistrict;
}   





}
