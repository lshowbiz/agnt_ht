package com.joymain.jecs.fi.model;
// Generated 2013-12-2 11:24:01 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JMI_ZCW_MEMBER"
 *     
 */

public class JmiZcwMember extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long zcwId;
    private String zcwDept;
    private String zcwDeptname;
    private String userCode;
    private String userName;
    private String tel;
    private String email;


    // Constructors

    /** default constructor */
    public JmiZcwMember() {
    }

	/** minimal constructor */
    public JmiZcwMember(String userCode) {
        this.userCode = userCode;
    }
    
    /** full constructor */
    public JmiZcwMember(String zcwDept, String zcwDeptname, String userCode, String userName, String tel, String email) {
        this.zcwDept = zcwDept;
        this.zcwDeptname = zcwDeptname;
        this.userCode = userCode;
        this.userName = userName;
        this.tel = tel;
        this.email = email;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ZCW_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_FI"
     *         
     */

    public Long getZcwId() {
        return this.zcwId;
    }
    
    public void setZcwId(Long zcwId) {
        this.zcwId = zcwId;
    }
    /**       
     *      *            @hibernate.property
     *             column="ZCW_DEPT"
     *             length="20"
     *         
     */

    public String getZcwDept() {
        return this.zcwDept;
    }
    
    public void setZcwDept(String zcwDept) {
        this.zcwDept = zcwDept;
    }
    /**       
     *      *            @hibernate.property
     *             column="ZCW_DEPTNAME"
     *             length="100"
     *         
     */

    public String getZcwDeptname() {
        return this.zcwDeptname;
    }
    
    public void setZcwDeptname(String zcwDeptname) {
        this.zcwDeptname = zcwDeptname;
    }
    /**       
     *      *            @hibernate.property
     *             column="USER_CODE"
     *             length="40"
     *             not-null="true"
     *         
     */

    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="USER_NAME"
     *             length="40"
     *         
     */

    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**       
     *      *            @hibernate.property
     *             column="TEL"
     *             length="40"
     *         
     */

    public String getTel() {
        return this.tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }
    /**       
     *      *            @hibernate.property
     *             column="EMAIL"
     *             length="80"
     *         
     */

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("zcwDept").append("='").append(getZcwDept()).append("' ");			
      buffer.append("zcwDeptname").append("='").append(getZcwDeptname()).append("' ");			
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("userName").append("='").append(getUserName()).append("' ");			
      buffer.append("tel").append("='").append(getTel()).append("' ");			
      buffer.append("email").append("='").append(getEmail()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JmiZcwMember) ) return false;
		 JmiZcwMember castOther = ( JmiZcwMember ) other; 
         
		 return ( (this.getZcwId()==castOther.getZcwId()) || ( this.getZcwId()!=null && castOther.getZcwId()!=null && this.getZcwId().equals(castOther.getZcwId()) ) )
 && ( (this.getZcwDept()==castOther.getZcwDept()) || ( this.getZcwDept()!=null && castOther.getZcwDept()!=null && this.getZcwDept().equals(castOther.getZcwDept()) ) )
 && ( (this.getZcwDeptname()==castOther.getZcwDeptname()) || ( this.getZcwDeptname()!=null && castOther.getZcwDeptname()!=null && this.getZcwDeptname().equals(castOther.getZcwDeptname()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getUserName()==castOther.getUserName()) || ( this.getUserName()!=null && castOther.getUserName()!=null && this.getUserName().equals(castOther.getUserName()) ) )
 && ( (this.getTel()==castOther.getTel()) || ( this.getTel()!=null && castOther.getTel()!=null && this.getTel().equals(castOther.getTel()) ) )
 && ( (this.getEmail()==castOther.getEmail()) || ( this.getEmail()!=null && castOther.getEmail()!=null && this.getEmail().equals(castOther.getEmail()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getZcwId() == null ? 0 : this.getZcwId().hashCode() );
         result = 37 * result + ( getZcwDept() == null ? 0 : this.getZcwDept().hashCode() );
         result = 37 * result + ( getZcwDeptname() == null ? 0 : this.getZcwDeptname().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getUserName() == null ? 0 : this.getUserName().hashCode() );
         result = 37 * result + ( getTel() == null ? 0 : this.getTel().hashCode() );
         result = 37 * result + ( getEmail() == null ? 0 : this.getEmail().hashCode() );
         return result;
   }   





}
