package com.joymain.jecs.mi.model;
// Generated 2014-11-17 15:17:56 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JMI_LEVEL_LOCK"
 *     
 */

public class JmiLevelLock extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private String userCode;
    private Integer memberLevel;
    private String createNo;
    private String isValid;


    // Constructors

    /** default constructor */
    public JmiLevelLock() {
    }

    
    /** full constructor */
    public JmiLevelLock(String userCode, Integer memberLevel, String createNo, String isValid) {
        this.userCode = userCode;
        this.memberLevel = memberLevel;
        this.createNo = createNo;
        this.isValid = isValid;
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
     *             column="MEMBER_LEVEL"
     *             length="22"
     *         
     */

    public Integer getMemberLevel() {
        return this.memberLevel;
    }
    
    public void setMemberLevel(Integer memberLevel) {
        this.memberLevel = memberLevel;
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
     *             column="IS_VALID"
     *             length="1"
     *         
     */

    public String getIsValid() {
        return this.isValid;
    }
    
    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("memberLevel").append("='").append(getMemberLevel()).append("' ");			
      buffer.append("createNo").append("='").append(getCreateNo()).append("' ");			
      buffer.append("isValid").append("='").append(getIsValid()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JmiLevelLock) ) return false;
		 JmiLevelLock castOther = ( JmiLevelLock ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getMemberLevel()==castOther.getMemberLevel()) || ( this.getMemberLevel()!=null && castOther.getMemberLevel()!=null && this.getMemberLevel().equals(castOther.getMemberLevel()) ) )
 && ( (this.getCreateNo()==castOther.getCreateNo()) || ( this.getCreateNo()!=null && castOther.getCreateNo()!=null && this.getCreateNo().equals(castOther.getCreateNo()) ) )
 && ( (this.getIsValid()==castOther.getIsValid()) || ( this.getIsValid()!=null && castOther.getIsValid()!=null && this.getIsValid().equals(castOther.getIsValid()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getMemberLevel() == null ? 0 : this.getMemberLevel().hashCode() );
         result = 37 * result + ( getCreateNo() == null ? 0 : this.getCreateNo().hashCode() );
         result = 37 * result + ( getIsValid() == null ? 0 : this.getIsValid().hashCode() );
         return result;
   }   





}
