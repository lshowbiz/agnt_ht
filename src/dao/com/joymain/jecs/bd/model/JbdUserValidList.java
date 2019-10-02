package com.joymain.jecs.bd.model;
// Generated 2010-6-28 7:33:59 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;

import com.joymain.jecs.mi.model.JmiMember;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JBD_USER_VALID_LIST"
 *     
 */

public class JbdUserValidList extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private JmiMember jmiMember;
    private Integer oldFreezeStatus;
    private Integer newFreezeStatus;
    private Integer wweek;


    // Constructors

    /** default constructor */
    public JbdUserValidList() {
    }

    
    /** full constructor */
    public JbdUserValidList(Integer oldFreezeStatus, Integer newFreezeStatus, Integer wweek) {
        this.oldFreezeStatus = oldFreezeStatus;
        this.newFreezeStatus = newFreezeStatus;
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
     * *
     * @hibernate.many-to-one not-null="true"
     * @hibernate.column name="USER_CODE"
     * 
     */
    public JmiMember getJmiMember() {
		return jmiMember;
	}

	public void setJmiMember(JmiMember jmiMember) {
		this.jmiMember = jmiMember;
	}
    /**       
     *      *            @hibernate.property
     *             column="OLD_FREEZE_STATUS"
     *             length="8"
     *         
     */

    public Integer getOldFreezeStatus() {
        return this.oldFreezeStatus;
    }
    
    public void setOldFreezeStatus(Integer oldFreezeStatus) {
        this.oldFreezeStatus = oldFreezeStatus;
    }
    /**       
     *      *            @hibernate.property
     *             column="NEW_FREEZE_STATUS"
     *             length="8"
     *         
     */

    public Integer getNewFreezeStatus() {
        return this.newFreezeStatus;
    }
    
    public void setNewFreezeStatus(Integer newFreezeStatus) {
        this.newFreezeStatus = newFreezeStatus;
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
      buffer.append("userCode").append("='").append(getJmiMember().getUserCode()).append("' ");			
      buffer.append("oldFreezeStatus").append("='").append(getOldFreezeStatus()).append("' ");			
      buffer.append("newFreezeStatus").append("='").append(getNewFreezeStatus()).append("' ");			
      buffer.append("wweek").append("='").append(getWweek()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JbdUserValidList) ) return false;
		 JbdUserValidList castOther = ( JbdUserValidList ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getJmiMember().getUserCode()==castOther.getJmiMember().getUserCode()) || ( this.getJmiMember().getUserCode()!=null && castOther.getJmiMember().getUserCode()!=null && this.getJmiMember().getUserCode().equals(castOther.getJmiMember().getUserCode()) ) )
 && ( (this.getOldFreezeStatus()==castOther.getOldFreezeStatus()) || ( this.getOldFreezeStatus()!=null && castOther.getOldFreezeStatus()!=null && this.getOldFreezeStatus().equals(castOther.getOldFreezeStatus()) ) )
 && ( (this.getNewFreezeStatus()==castOther.getNewFreezeStatus()) || ( this.getNewFreezeStatus()!=null && castOther.getNewFreezeStatus()!=null && this.getNewFreezeStatus().equals(castOther.getNewFreezeStatus()) ) )
 && ( (this.getWweek()==castOther.getWweek()) || ( this.getWweek()!=null && castOther.getWweek()!=null && this.getWweek().equals(castOther.getWweek()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getJmiMember().getUserCode() == null ? 0 : this.getJmiMember().getUserCode().hashCode() );
         result = 37 * result + ( getOldFreezeStatus() == null ? 0 : this.getOldFreezeStatus().hashCode() );
         result = 37 * result + ( getNewFreezeStatus() == null ? 0 : this.getNewFreezeStatus().hashCode() );
         result = 37 * result + ( getWweek() == null ? 0 : this.getWweek().hashCode() );
         return result;
   }   





}
