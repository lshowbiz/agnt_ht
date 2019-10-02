package com.joymain.jecs.am.model;
// Generated May 20, 2014 4:00:54 PM by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="INW_DEPART_COMPETENCE"
 *     
 */

public class InwDepartCompetence extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private String departmentId;
    private String demandId;
    private Date createTime;
    private String createUserCode;


    // Constructors

    /** default constructor */
    public InwDepartCompetence() {
    }

    
    /** full constructor */
    public InwDepartCompetence(String departmentId, String demandId, Date createTime, String createUserCode) {
        this.departmentId = departmentId;
        this.demandId = demandId;
        this.createTime = createTime;
        this.createUserCode = createUserCode;
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
     *             column="DEPARTMENT_ID"
     *             length="50"
     *         
     */

    public String getDepartmentId() {
        return this.departmentId;
    }
    
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
    /**       
     *      *            @hibernate.property
     *             column="DEMAND_ID"
     *             length="50"
     *         
     */

    public String getDemandId() {
        return this.demandId;
    }
    
    public void setDemandId(String demandId) {
        this.demandId = demandId;
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
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("departmentId").append("='").append(getDepartmentId()).append("' ");			
      buffer.append("demandId").append("='").append(getDemandId()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("createUserCode").append("='").append(getCreateUserCode()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof InwDepartCompetence) ) return false;
		 InwDepartCompetence castOther = ( InwDepartCompetence ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getDepartmentId()==castOther.getDepartmentId()) || ( this.getDepartmentId()!=null && castOther.getDepartmentId()!=null && this.getDepartmentId().equals(castOther.getDepartmentId()) ) )
 && ( (this.getDemandId()==castOther.getDemandId()) || ( this.getDemandId()!=null && castOther.getDemandId()!=null && this.getDemandId().equals(castOther.getDemandId()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getCreateUserCode()==castOther.getCreateUserCode()) || ( this.getCreateUserCode()!=null && castOther.getCreateUserCode()!=null && this.getCreateUserCode().equals(castOther.getCreateUserCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getDepartmentId() == null ? 0 : this.getDepartmentId().hashCode() );
         result = 37 * result + ( getDemandId() == null ? 0 : this.getDemandId().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getCreateUserCode() == null ? 0 : this.getCreateUserCode().hashCode() );
         return result;
   }   





}
