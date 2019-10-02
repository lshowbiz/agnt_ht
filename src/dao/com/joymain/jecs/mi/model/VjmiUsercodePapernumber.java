package com.joymain.jecs.mi.model;
// Generated 2016-9-20 14:49:45 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="V_JMI_USERCODE_PAPERNUMBER"
 *     
 */

public class VjmiUsercodePapernumber extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    
	// Fields    
	private String userCode;
    private String papernumber;


    // Constructors

    /** default constructor */
    public VjmiUsercodePapernumber() {
    }

    
    /** full constructor */
    public VjmiUsercodePapernumber(String userCode, String papernumber) {
        this.userCode = userCode;
        this.papernumber = papernumber;
    }
    

   
    // Property accessors
    /**       
     *      *      
     *         
     *                @hibernate.property
     *                 column="USER_CODE"
     *             
     */

    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *         
     *                @hibernate.property
     *                 column="PAPERNUMBER"
     *             
     */

    public String getPapernumber() {
        return this.papernumber;
    }
    
    public void setPapernumber(String papernumber) {
        this.papernumber = papernumber;
    }


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}


   








}
