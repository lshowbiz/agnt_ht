package com.joymain.jecs.mi.model;
// Generated 2010-1-4 17:08:13 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JMI_MEMBER_COMPANY_NOTE"
 *     
 */

public class JmiMemberCompanyNote extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private String userCode;
    private String newCompany;
    private String oldCompany;
    private String updateType;
    private String remark;
    private String createUser;
    private Date createTime;


    // Constructors

    /** default constructor */
    public JmiMemberCompanyNote() {
    }

    
    /** full constructor */
    public JmiMemberCompanyNote(String userCode, String newCompany, String oldCompany, String updateType, String remark, String createUser, Date createTime) {
        this.userCode = userCode;
        this.newCompany = newCompany;
        this.oldCompany = oldCompany;
        this.updateType = updateType;
        this.remark = remark;
        this.createUser = createUser;
        this.createTime = createTime;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ID"
     *@hibernate.generator-param name="sequence" value="SEQ_MI"
     *         
     */

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    /**       
     *      *            @hibernate.property
     *             column="USER_CODE"
     *             length="20"
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
     *             column="NEW_COMPANY"
     *             length="1"
     *         
     */

    public String getNewCompany() {
        return this.newCompany;
    }
    
    public void setNewCompany(String newCompany) {
        this.newCompany = newCompany;
    }
    /**       
     *      *            @hibernate.property
     *             column="OLD_COMPANY"
     *             length="1"
     *         
     */

    public String getOldCompany() {
        return this.oldCompany;
    }
    
    public void setOldCompany(String oldCompany) {
        this.oldCompany = oldCompany;
    }
    /**       
     *      *            @hibernate.property
     *             column="UPDATE_TYPE"
     *             length="1"
     *         
     */

    public String getUpdateType() {
        return this.updateType;
    }
    
    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }
    /**       
     *      *            @hibernate.property
     *             column="REMARK"
     *             length="200"
     *         
     */

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    /**       
     *      *            @hibernate.property
     *             column="CREATE_USER"
     *             length="20"
     *         
     */

    public String getCreateUser() {
        return this.createUser;
    }
    
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
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
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("newCompany").append("='").append(getNewCompany()).append("' ");			
      buffer.append("oldCompany").append("='").append(getOldCompany()).append("' ");			
      buffer.append("updateType").append("='").append(getUpdateType()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");			
      buffer.append("createUser").append("='").append(getCreateUser()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JmiMemberCompanyNote) ) return false;
		 JmiMemberCompanyNote castOther = ( JmiMemberCompanyNote ) other; 
         
		 return  ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getNewCompany()==castOther.getNewCompany()) || ( this.getNewCompany()!=null && castOther.getNewCompany()!=null && this.getNewCompany().equals(castOther.getNewCompany()) ) )
 && ( (this.getOldCompany()==castOther.getOldCompany()) || ( this.getOldCompany()!=null && castOther.getOldCompany()!=null && this.getOldCompany().equals(castOther.getOldCompany()) ) )
 && ( (this.getUpdateType()==castOther.getUpdateType()) || ( this.getUpdateType()!=null && castOther.getUpdateType()!=null && this.getUpdateType().equals(castOther.getUpdateType()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
 && ( (this.getCreateUser()==castOther.getCreateUser()) || ( this.getCreateUser()!=null && castOther.getCreateUser()!=null && this.getCreateUser().equals(castOther.getCreateUser()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getNewCompany() == null ? 0 : this.getNewCompany().hashCode() );
         result = 37 * result + ( getOldCompany() == null ? 0 : this.getOldCompany().hashCode() );
         result = 37 * result + ( getUpdateType() == null ? 0 : this.getUpdateType().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         result = 37 * result + ( getCreateUser() == null ? 0 : this.getCreateUser().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         return result;
   }   





}
