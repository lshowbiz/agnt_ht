package com.joymain.jecs.mi.model;
// Generated 2014-10-28 13:48:00 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JMI_VALID_LEVEL_LIST"
 *     
 */

public class JmiValidLevelList extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private String userCode;
    private Integer wweek;
    private Integer oldMemberLevel;
    private Integer newMemberLevel;
    private String isLock;
    private String createNo;


    // Constructors

    /** default constructor */
    public JmiValidLevelList() {
    }

    
    /** full constructor */
    public JmiValidLevelList(String userCode, Integer wweek, Integer oldMemberLevel, Integer newMemberLevel, String isLock, Date createTime, String createNo) {
        this.userCode = userCode;
        this.wweek = wweek;
        this.oldMemberLevel = oldMemberLevel;
        this.newMemberLevel = newMemberLevel;
        this.isLock = isLock;
        this.createNo = createNo;
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
     *             column="W_WEEK"
     *             length="22"
     *         
     */

    public Integer getWweek() {
        return this.wweek;
    }
    
    public void setWweek(Integer wweek) {
        this.wweek = wweek;
    }
    /**       
     *      *            @hibernate.property
     *             column="OLD_MEMBER_LEVEL"
     *             length="22"
     *         
     */

    public Integer getOldMemberLevel() {
        return this.oldMemberLevel;
    }
    
    public void setOldMemberLevel(Integer oldMemberLevel) {
        this.oldMemberLevel = oldMemberLevel;
    }
    /**       
     *      *            @hibernate.property
     *             column="NEW_MEMBER_LEVEL"
     *             length="22"
     *         
     */

    public Integer getNewMemberLevel() {
        return this.newMemberLevel;
    }
    
    public void setNewMemberLevel(Integer newMemberLevel) {
        this.newMemberLevel = newMemberLevel;
    }
    /**       
     *      *            @hibernate.property
     *             column="IS_LOCK"
     *             length="2"
     *         
     */

    public String getIsLock() {
        return this.isLock;
    }
    
    public void setIsLock(String isLock) {
        this.isLock = isLock;
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
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("wweek").append("='").append(getWweek()).append("' ");			
      buffer.append("oldMemberLevel").append("='").append(getOldMemberLevel()).append("' ");			
      buffer.append("newMemberLevel").append("='").append(getNewMemberLevel()).append("' ");			
      buffer.append("isLock").append("='").append(getIsLock()).append("' ");					
      buffer.append("createNo").append("='").append(getCreateNo()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JmiValidLevelList) ) return false;
		 JmiValidLevelList castOther = ( JmiValidLevelList ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getWweek()==castOther.getWweek()) || ( this.getWweek()!=null && castOther.getWweek()!=null && this.getWweek().equals(castOther.getWweek()) ) )
 && ( (this.getOldMemberLevel()==castOther.getOldMemberLevel()) || ( this.getOldMemberLevel()!=null && castOther.getOldMemberLevel()!=null && this.getOldMemberLevel().equals(castOther.getOldMemberLevel()) ) )
 && ( (this.getNewMemberLevel()==castOther.getNewMemberLevel()) || ( this.getNewMemberLevel()!=null && castOther.getNewMemberLevel()!=null && this.getNewMemberLevel().equals(castOther.getNewMemberLevel()) ) )
 && ( (this.getIsLock()==castOther.getIsLock()) || ( this.getIsLock()!=null && castOther.getIsLock()!=null && this.getIsLock().equals(castOther.getIsLock()) ) )
 && ( (this.getCreateNo()==castOther.getCreateNo()) || ( this.getCreateNo()!=null && castOther.getCreateNo()!=null && this.getCreateNo().equals(castOther.getCreateNo()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getWweek() == null ? 0 : this.getWweek().hashCode() );
         result = 37 * result + ( getOldMemberLevel() == null ? 0 : this.getOldMemberLevel().hashCode() );
         result = 37 * result + ( getNewMemberLevel() == null ? 0 : this.getNewMemberLevel().hashCode() );
         result = 37 * result + ( getIsLock() == null ? 0 : this.getIsLock().hashCode() );
         result = 37 * result + ( getCreateNo() == null ? 0 : this.getCreateNo().hashCode() );
         return result;
   }   





}
