package com.joymain.jecs.bd.model;
// Generated 2009-9-23 11:31:59 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JBD_MEMBER_FROZEN"
 *     
 */

public class JbdMemberFrozen extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private String userCode;


    // Constructors

    /** default constructor */
    public JbdMemberFrozen() {
    }

    
    /** full constructor */
    public JbdMemberFrozen(String userCode) {
        this.userCode = userCode;
    }
    

   
    // Property accessors
   
    /**       
     *      *            @hibernate.id
     *      generator-class="assigned"
     *             type="java.lang.String"
     *             column="USER_CODE"
     *         
     */
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JbdMemberFrozen) ) return false;
		 JbdMemberFrozen castOther = ( JbdMemberFrozen ) other; 
         
		 return ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode())));
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         return result;
   }   





}
