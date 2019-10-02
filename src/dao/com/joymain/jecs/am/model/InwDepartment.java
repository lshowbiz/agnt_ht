package com.joymain.jecs.am.model;
// Generated May 20, 2014 3:56:17 PM by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="INW_DEPARTMENT"
 *     
 */

public class InwDepartment extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private String name;
    private String category;
    private String higerId;
    private String higerDepartName;
    private Date createTime;
    private String createUserCode;
    private Date auditTime;
    private String auditUserCode;
    private String status;


    // Constructors

    /** default constructor */
    public InwDepartment() {
    }

    
    /** full constructor */
    public InwDepartment(String name, String category, String higerId, String higerDepartName, Date createTime, String createUserCode, Date auditTime, String auditUserCode, String status) {
        this.name = name;
        this.category = category;
        this.higerId = higerId;
        this.higerDepartName = higerDepartName;
        this.createTime = createTime;
        this.createUserCode = createUserCode;
        this.auditTime = auditTime;
        this.auditUserCode = auditUserCode;
        this.status = status;
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
     *             length="300"
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
     *             column="CATEGORY"
     *             length="50"
     *         
     */

    public String getCategory() {
        return this.category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    /**       
     *      *            @hibernate.property
     *             column="HIGER_ID"
     *             length="30"
     *         
     */

    public String getHigerId() {
        return this.higerId;
    }
    
    public void setHigerId(String higerId) {
        this.higerId = higerId;
    }
    /**       
     *      *            @hibernate.property
     *             column="HIGER_DEPART_NAME"
     *             length="300"
     *         
     */

    public String getHigerDepartName() {
        return this.higerDepartName;
    }
    
    public void setHigerDepartName(String higerDepartName) {
        this.higerDepartName = higerDepartName;
    }
    /**       
     *      *            @hibernate.property
     *             column="CREATE_TIME"
     *             length="7"
     *         
     */

    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="CREATE_USER_CODE"
     *             length="20"
     *         
     */

    public String getCreateUserCode() {
        return this.createUserCode;
    }
    
    public void setCreateUserCode(String createUserCode) {
        this.createUserCode = createUserCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="AUDIT_TIME"
     *             length="7"
     *         
     */

    public Date getAuditTime() {
        return this.auditTime;
    }
    
    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="AUDIT_USER_CODE"
     *             length="20"
     *         
     */

    public String getAuditUserCode() {
        return this.auditUserCode;
    }
    
    public void setAuditUserCode(String auditUserCode) {
        this.auditUserCode = auditUserCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="STATUS"
     *             length="2"
     *         
     */

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("name").append("='").append(getName()).append("' ");			
      buffer.append("category").append("='").append(getCategory()).append("' ");			
      buffer.append("higerId").append("='").append(getHigerId()).append("' ");			
      buffer.append("higerDepartName").append("='").append(getHigerDepartName()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("createUserCode").append("='").append(getCreateUserCode()).append("' ");			
      buffer.append("auditTime").append("='").append(getAuditTime()).append("' ");			
      buffer.append("auditUserCode").append("='").append(getAuditUserCode()).append("' ");			
      buffer.append("status").append("='").append(getStatus()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof InwDepartment) ) return false;
		 InwDepartment castOther = ( InwDepartment ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getName()==castOther.getName()) || ( this.getName()!=null && castOther.getName()!=null && this.getName().equals(castOther.getName()) ) )
 && ( (this.getCategory()==castOther.getCategory()) || ( this.getCategory()!=null && castOther.getCategory()!=null && this.getCategory().equals(castOther.getCategory()) ) )
 && ( (this.getHigerId()==castOther.getHigerId()) || ( this.getHigerId()!=null && castOther.getHigerId()!=null && this.getHigerId().equals(castOther.getHigerId()) ) )
 && ( (this.getHigerDepartName()==castOther.getHigerDepartName()) || ( this.getHigerDepartName()!=null && castOther.getHigerDepartName()!=null && this.getHigerDepartName().equals(castOther.getHigerDepartName()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getCreateUserCode()==castOther.getCreateUserCode()) || ( this.getCreateUserCode()!=null && castOther.getCreateUserCode()!=null && this.getCreateUserCode().equals(castOther.getCreateUserCode()) ) )
 && ( (this.getAuditTime()==castOther.getAuditTime()) || ( this.getAuditTime()!=null && castOther.getAuditTime()!=null && this.getAuditTime().equals(castOther.getAuditTime()) ) )
 && ( (this.getAuditUserCode()==castOther.getAuditUserCode()) || ( this.getAuditUserCode()!=null && castOther.getAuditUserCode()!=null && this.getAuditUserCode().equals(castOther.getAuditUserCode()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getName() == null ? 0 : this.getName().hashCode() );
         result = 37 * result + ( getCategory() == null ? 0 : this.getCategory().hashCode() );
         result = 37 * result + ( getHigerId() == null ? 0 : this.getHigerId().hashCode() );
         result = 37 * result + ( getHigerDepartName() == null ? 0 : this.getHigerDepartName().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getCreateUserCode() == null ? 0 : this.getCreateUserCode().hashCode() );
         result = 37 * result + ( getAuditTime() == null ? 0 : this.getAuditTime().hashCode() );
         result = 37 * result + ( getAuditUserCode() == null ? 0 : this.getAuditUserCode().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         return result;
   }   





}
