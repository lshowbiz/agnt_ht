package com.joymain.jecs.am.model;
// Generated 2013-8-20 15:30:06 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="INW_VIEWPEOPLE"
 *     
 */

public class InwViewpeople extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private String suggestionid;
    private String viewStatus;
    private String userCode;
    private Date viewTime;

    //viewStatus字段暂时不用,作扩展字段,INW_VIEWPEOPLE该表里面有关于相关建议的信息,则表明该建议已阅;
    // Constructors

    /** default constructor */
    public InwViewpeople() {
    }

	/** minimal constructor */
    public InwViewpeople(String suggestionid) {
        this.suggestionid = suggestionid;
    }
    
    /** full constructor */
    public InwViewpeople(String suggestionid, String viewStatus, String userCode, Date viewTime) {
        this.suggestionid = suggestionid;
        this.viewStatus = viewStatus;
        this.userCode = userCode;
        this.viewTime = viewTime;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ID"
     *          @hibernate.generator-param name="sequence" value="SEQ_AM" 
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
     *             column="SUGGESTIONID"
     *             length="13"
     *             not-null="true"
     *         
     */

    public String getSuggestionid() {
        return this.suggestionid;
    }
    
    public void setSuggestionid(String suggestionid) {
        this.suggestionid = suggestionid;
    }
    /**       
     *      *            @hibernate.property
     *             column="VIEW_STATUS"
     *             length="2"
     *         
     */

    public String getViewStatus() {
        return this.viewStatus;
    }
    
    public void setViewStatus(String viewStatus) {
        this.viewStatus = viewStatus;
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
     *             column="VIEW_TIME"
     *             length="7"
     *         
     */

    public Date getViewTime() {
        return this.viewTime;
    }
    
    public void setViewTime(Date viewTime) {
        this.viewTime = viewTime;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("suggestionid").append("='").append(getSuggestionid()).append("' ");			
      buffer.append("viewStatus").append("='").append(getViewStatus()).append("' ");			
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("viewTime").append("='").append(getViewTime()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof InwViewpeople) ) return false;
		 InwViewpeople castOther = ( InwViewpeople ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getSuggestionid()==castOther.getSuggestionid()) || ( this.getSuggestionid()!=null && castOther.getSuggestionid()!=null && this.getSuggestionid().equals(castOther.getSuggestionid()) ) )
 && ( (this.getViewStatus()==castOther.getViewStatus()) || ( this.getViewStatus()!=null && castOther.getViewStatus()!=null && this.getViewStatus().equals(castOther.getViewStatus()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getViewTime()==castOther.getViewTime()) || ( this.getViewTime()!=null && castOther.getViewTime()!=null && this.getViewTime().equals(castOther.getViewTime()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getSuggestionid() == null ? 0 : this.getSuggestionid().hashCode() );
         result = 37 * result + ( getViewStatus() == null ? 0 : this.getViewStatus().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getViewTime() == null ? 0 : this.getViewTime().hashCode() );
         return result;
   }   





}
