package com.joymain.jecs.mi.model;
// Generated 2015-4-8 10:19:25 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JMI_GRADE_LOCK"
 *     
 */

public class JmiGradeLock extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private String userCode;
    private Integer gradeType;
    private String createNo;
    private Date createTime;
    private Integer validWeek;


    // Constructors

    /** default constructor */
    public JmiGradeLock() {
    }

    
    /** full constructor */
    public JmiGradeLock(String userCode, Integer gradeType, String createNo, Date createTime, Integer validWeek) {
        this.userCode = userCode;
        this.gradeType = gradeType;
        this.createNo = createNo;
        this.createTime = createTime;
        this.validWeek = validWeek;
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
     *             column="GRADE_TYPE"
     *             length="22"
     *         
     */

    public Integer getGradeType() {
        return this.gradeType;
    }
    
    public void setGradeType(Integer gradeType) {
        this.gradeType = gradeType;
    }
    /**       
     *      *            @hibernate.property
     *             column="CREATE_NO"
     *             length="20"
     *         
     */

    public String getCreateNo() {
        return this.createNo;
    }
    
    public void setCreateNo(String createNo) {
        this.createNo = createNo;
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
     *             column="VALID_WEEK"
     *             length="22"
     *         
     */

    public Integer getValidWeek() {
        return this.validWeek;
    }
    
    public void setValidWeek(Integer validWeek) {
        this.validWeek = validWeek;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("gradeType").append("='").append(getGradeType()).append("' ");			
      buffer.append("createNo").append("='").append(getCreateNo()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("validWeek").append("='").append(getValidWeek()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JmiGradeLock) ) return false;
		 JmiGradeLock castOther = ( JmiGradeLock ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getGradeType()==castOther.getGradeType()) || ( this.getGradeType()!=null && castOther.getGradeType()!=null && this.getGradeType().equals(castOther.getGradeType()) ) )
 && ( (this.getCreateNo()==castOther.getCreateNo()) || ( this.getCreateNo()!=null && castOther.getCreateNo()!=null && this.getCreateNo().equals(castOther.getCreateNo()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getValidWeek()==castOther.getValidWeek()) || ( this.getValidWeek()!=null && castOther.getValidWeek()!=null && this.getValidWeek().equals(castOther.getValidWeek()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getGradeType() == null ? 0 : this.getGradeType().hashCode() );
         result = 37 * result + ( getCreateNo() == null ? 0 : this.getCreateNo().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getValidWeek() == null ? 0 : this.getValidWeek().hashCode() );
         return result;
   }   





}
