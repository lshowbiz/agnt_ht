package com.joymain.jecs.bd.model;
// Generated 2012-7-2 10:37:17 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JBD_GRADE_NOTE"
 *     
 */

public class JbdGradeNote extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private String userCode;
    private Integer oldHonorStar;
    private Integer newHonorStar;
    private Integer oldPassStar;
    private Integer newPassStar;
    private Date createTime;
    private String createNo;
    private Integer wweek;


    // Constructors

    /** default constructor */
    public JbdGradeNote() {
    }

    
    /** full constructor */
    public JbdGradeNote(String userCode, Integer oldHonorStar, Integer newHonorStar, Integer oldPassStar, Integer newPassStar, Date createTime, String createNo, Integer wweek) {
        this.userCode = userCode;
        this.oldHonorStar = oldHonorStar;
        this.newHonorStar = newHonorStar;
        this.oldPassStar = oldPassStar;
        this.newPassStar = newPassStar;
        this.createTime = createTime;
        this.createNo = createNo;
        this.wweek = wweek;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ID"
     *@hibernate.generator-param name="sequence" value="SEQ_BD"
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
     *             column="OLD_HONOR_STAR"
     *             length="2"
     *         
     */

    public Integer getOldHonorStar() {
        return this.oldHonorStar;
    }
    
    public void setOldHonorStar(Integer oldHonorStar) {
        this.oldHonorStar = oldHonorStar;
    }
    /**       
     *      *            @hibernate.property
     *             column="NEW_HONOR_STAR"
     *             length="2"
     *         
     */

    public Integer getNewHonorStar() {
        return this.newHonorStar;
    }
    
    public void setNewHonorStar(Integer newHonorStar) {
        this.newHonorStar = newHonorStar;
    }
    /**       
     *      *            @hibernate.property
     *             column="OLD_PASS_STAR"
     *             length="2"
     *         
     */

    public Integer getOldPassStar() {
        return this.oldPassStar;
    }
    
    public void setOldPassStar(Integer oldPassStar) {
        this.oldPassStar = oldPassStar;
    }
    /**       
     *      *            @hibernate.property
     *             column="NEW_PASS_STAR"
     *             length="2"
     *         
     */

    public Integer getNewPassStar() {
        return this.newPassStar;
    }
    
    public void setNewPassStar(Integer newPassStar) {
        this.newPassStar = newPassStar;
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
     *             column="W_WEEK"
     *             length="8"
     *         
     */

    public Integer getWweek() {
        return this.wweek;
    }
    
    public void setWweek(Integer wweek) {
        this.wweek = wweek;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("oldHonorStar").append("='").append(getOldHonorStar()).append("' ");			
      buffer.append("newHonorStar").append("='").append(getNewHonorStar()).append("' ");			
      buffer.append("oldPassStar").append("='").append(getOldPassStar()).append("' ");			
      buffer.append("newPassStar").append("='").append(getNewPassStar()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("createNo").append("='").append(getCreateNo()).append("' ");			
      buffer.append("wweek").append("='").append(getWweek()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JbdGradeNote) ) return false;
		 JbdGradeNote castOther = ( JbdGradeNote ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getOldHonorStar()==castOther.getOldHonorStar()) || ( this.getOldHonorStar()!=null && castOther.getOldHonorStar()!=null && this.getOldHonorStar().equals(castOther.getOldHonorStar()) ) )
 && ( (this.getNewHonorStar()==castOther.getNewHonorStar()) || ( this.getNewHonorStar()!=null && castOther.getNewHonorStar()!=null && this.getNewHonorStar().equals(castOther.getNewHonorStar()) ) )
 && ( (this.getOldPassStar()==castOther.getOldPassStar()) || ( this.getOldPassStar()!=null && castOther.getOldPassStar()!=null && this.getOldPassStar().equals(castOther.getOldPassStar()) ) )
 && ( (this.getNewPassStar()==castOther.getNewPassStar()) || ( this.getNewPassStar()!=null && castOther.getNewPassStar()!=null && this.getNewPassStar().equals(castOther.getNewPassStar()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getCreateNo()==castOther.getCreateNo()) || ( this.getCreateNo()!=null && castOther.getCreateNo()!=null && this.getCreateNo().equals(castOther.getCreateNo()) ) )
 && ( (this.getWweek()==castOther.getWweek()) || ( this.getWweek()!=null && castOther.getWweek()!=null && this.getWweek().equals(castOther.getWweek()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getOldHonorStar() == null ? 0 : this.getOldHonorStar().hashCode() );
         result = 37 * result + ( getNewHonorStar() == null ? 0 : this.getNewHonorStar().hashCode() );
         result = 37 * result + ( getOldPassStar() == null ? 0 : this.getOldPassStar().hashCode() );
         result = 37 * result + ( getNewPassStar() == null ? 0 : this.getNewPassStar().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getCreateNo() == null ? 0 : this.getCreateNo().hashCode() );
         result = 37 * result + ( getWweek() == null ? 0 : this.getWweek().hashCode() );
         return result;
   }   





}
