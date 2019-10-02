package com.joymain.jecs.am.model;

import java.util.HashSet;
import java.util.Set;
// Generated 2008-6-14 15:01:05 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="AM_PERMIT"
 *     
 */

public class AmPermit extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private String permitNo;
    private String permitName;
    private String permitClass;
    private Set amAnnounces = new HashSet();

    // Constructors

    /** default constructor */
    public AmPermit() {
    }

    
    /** full constructor */
    public AmPermit(String permitName, String permitClass) {
        this.permitName = permitName;
        this.permitClass = permitClass;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.String"
     *             column="PERMIT_NO"
     *         
     */

    public String getPermitNo() {
        return this.permitNo;
    }
    
    public void setPermitNo(String permitNo) {
        this.permitNo = permitNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="PERMIT_NAME"
     *             length="50"
     *         
     */

    public String getPermitName() {
        return this.permitName;
    }
    
    public void setPermitName(String permitName) {
        this.permitName = permitName;
    }
    /**       
     *      *            @hibernate.property
     *             column="PERMIT_CLASS"
     *             length="2"
     *         
     */

    public String getPermitClass() {
        return this.permitClass;
    }
    
    public void setPermitClass(String permitClass) {
        this.permitClass = permitClass;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("permitName").append("='").append(getPermitName()).append("' ");			
      buffer.append("permitClass").append("='").append(getPermitClass()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AmPermit) ) return false;
		 AmPermit castOther = ( AmPermit ) other; 
         
		 return ( (this.getPermitNo()==castOther.getPermitNo()) || ( this.getPermitNo()!=null && castOther.getPermitNo()!=null && this.getPermitNo().equals(castOther.getPermitNo()) ) )
 && ( (this.getPermitName()==castOther.getPermitName()) || ( this.getPermitName()!=null && castOther.getPermitName()!=null && this.getPermitName().equals(castOther.getPermitName()) ) )
 && ( (this.getPermitClass()==castOther.getPermitClass()) || ( this.getPermitClass()!=null && castOther.getPermitClass()!=null && this.getPermitClass().equals(castOther.getPermitClass()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getPermitNo() == null ? 0 : this.getPermitNo().hashCode() );
         result = 37 * result + ( getPermitName() == null ? 0 : this.getPermitName().hashCode() );
         result = 37 * result + ( getPermitClass() == null ? 0 : this.getPermitClass().hashCode() );
         return result;
   }

   /**
    * @hibernate.set table="AM_ANNOUNCE_PERMIT" inverse="false" cascade="save-update" lazy="true"
    * @hibernate.collection-key column="PERMIT_NO"
    * @hibernate.collection-many-to-many class="com.joymain.jecs.am.model.AmAnnounce" column="AA_NO"
    */
public Set getAmAnnounces() {
	return amAnnounces;
}


public void setAmAnnounces(Set amAnnounces) {
	this.amAnnounces = amAnnounces;
}   





}
