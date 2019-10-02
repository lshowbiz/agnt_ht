package com.joymain.jecs.sys.model;
// Generated 2013-5-20 11:05:42 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JSYS_RES_ROLE"
 *     
 */

public class JsysResRole extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    
    private Long pid;
	private Long roleId;
	private Long resId;


    // Constructors

    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.Long"
     *             column="P_ID"
     *         
     */

    public Long getPid() {
        return this.pid;
    }
    
    public void setPid(Long pid) {
        this.pid = pid;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JsysResRole) ) return false;
		 JsysResRole castOther = ( JsysResRole ) other; 
         
		 return ( (this.getPid()==castOther.getPid()) || ( this.getPid()!=null && castOther.getPid()!=null && this.getPid().equals(castOther.getPid()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getPid() == null ? 0 : this.getPid().hashCode() );
         return result;
   }

@Override
public String toString() {
	// TODO Auto-generated method stub
	return null;
}
/**       
 *      *            @hibernate.property
 *             column="ROLE_ID"
 *             
 *             not-null="true"
 *         
 */
public Long getRoleId() {
	return roleId;
}

public void setRoleId(Long roleId) {
	this.roleId = roleId;
}
/**       
 *      *            @hibernate.property
 *             column="RES_ID"
 *          
 *             not-null="true"
 *         
 */
public Long getResId() {
	return resId;
}

public void setResId(Long resId) {
	this.resId = resId;
}   





}
