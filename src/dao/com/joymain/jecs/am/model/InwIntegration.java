package com.joymain.jecs.am.model;
// Generated 2013-9-3 9:19:26 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="INW_INTEGRATION"
 *     
 */

public class InwIntegration extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private String suggestionUserCode;
    private String integrationAdd;
    private Date integrationAddTime;
    private String suggestionid;
    private String integrationMinus;
    private String integrationActivity;
    private Date integrationMinusTime;
    private String operatorUserCode;
    private String other;


    // Constructors

    /** default constructor */
    public InwIntegration() {
    }

    
    /** full constructor */
    public InwIntegration(String suggestionUserCode, String integrationAdd, Date integrationAddTime, String suggestionid, String integrationMinus, String integrationActivity, Date integrationMinusTime, String operatorUserCode, String other) {
        this.suggestionUserCode = suggestionUserCode;
        this.integrationAdd = integrationAdd;
        this.integrationAddTime = integrationAddTime;
        this.suggestionid = suggestionid;
        this.integrationMinus = integrationMinus;
        this.integrationActivity = integrationActivity;
        this.integrationMinusTime = integrationMinusTime;
        this.operatorUserCode = operatorUserCode;
        this.other = other;
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
     *             column="SUGGESTION_USER_CODE"
     *             length="20"
     *         
     */

    public String getSuggestionUserCode() {
        return this.suggestionUserCode;
    }
    
    public void setSuggestionUserCode(String suggestionUserCode) {
        this.suggestionUserCode = suggestionUserCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="INTEGRATION_ADD"
     *             length="5"
     *         
     */

    public String getIntegrationAdd() {
        return this.integrationAdd;
    }
    
    public void setIntegrationAdd(String integrationAdd) {
        this.integrationAdd = integrationAdd;
    }
    /**       
     *      *            @hibernate.property
     *             column="INTEGRATION_ADD_TIME"
     *             length="7"
     *         
     */

    public Date getIntegrationAddTime() {
        return this.integrationAddTime;
    }
    
    public void setIntegrationAddTime(Date integrationAddTime) {
        this.integrationAddTime = integrationAddTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="SUGGESTIONID"
     *             length="13"
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
     *             column="INTEGRATION_MINUS"
     *             length="5"
     *         
     */

    public String getIntegrationMinus() {
        return this.integrationMinus;
    }
    
    public void setIntegrationMinus(String integrationMinus) {
        this.integrationMinus = integrationMinus;
    }
    /**       
     *      *            @hibernate.property
     *             column="INTEGRATION_ACTIVITY"
     *             length="13"
     *         
     */

    public String getIntegrationActivity() {
        return this.integrationActivity;
    }
    
    public void setIntegrationActivity(String integrationActivity) {
        this.integrationActivity = integrationActivity;
    }
    /**       
     *      *            @hibernate.property
     *             column="INTEGRATION_MINUS_TIME"
     *             length="7"
     *         
     */

    public Date getIntegrationMinusTime() {
        return this.integrationMinusTime;
    }
    
    public void setIntegrationMinusTime(Date integrationMinusTime) {
        this.integrationMinusTime = integrationMinusTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="OPERATOR_USER_CODE"
     *             length="20"
     *         
     */

    public String getOperatorUserCode() {
        return this.operatorUserCode;
    }
    
    public void setOperatorUserCode(String operatorUserCode) {
        this.operatorUserCode = operatorUserCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="OTHER"
     *             length="100"
     *         
     */

    public String getOther() {
        return this.other;
    }
    
    public void setOther(String other) {
        this.other = other;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("suggestionUserCode").append("='").append(getSuggestionUserCode()).append("' ");			
      buffer.append("integrationAdd").append("='").append(getIntegrationAdd()).append("' ");			
      buffer.append("integrationAddTime").append("='").append(getIntegrationAddTime()).append("' ");			
      buffer.append("suggestionid").append("='").append(getSuggestionid()).append("' ");			
      buffer.append("integrationMinus").append("='").append(getIntegrationMinus()).append("' ");			
      buffer.append("integrationActivity").append("='").append(getIntegrationActivity()).append("' ");			
      buffer.append("integrationMinusTime").append("='").append(getIntegrationMinusTime()).append("' ");			
      buffer.append("operatorUserCode").append("='").append(getOperatorUserCode()).append("' ");			
      buffer.append("other").append("='").append(getOther()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof InwIntegration) ) return false;
		 InwIntegration castOther = ( InwIntegration ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getSuggestionUserCode()==castOther.getSuggestionUserCode()) || ( this.getSuggestionUserCode()!=null && castOther.getSuggestionUserCode()!=null && this.getSuggestionUserCode().equals(castOther.getSuggestionUserCode()) ) )
 && ( (this.getIntegrationAdd()==castOther.getIntegrationAdd()) || ( this.getIntegrationAdd()!=null && castOther.getIntegrationAdd()!=null && this.getIntegrationAdd().equals(castOther.getIntegrationAdd()) ) )
 && ( (this.getIntegrationAddTime()==castOther.getIntegrationAddTime()) || ( this.getIntegrationAddTime()!=null && castOther.getIntegrationAddTime()!=null && this.getIntegrationAddTime().equals(castOther.getIntegrationAddTime()) ) )
 && ( (this.getSuggestionid()==castOther.getSuggestionid()) || ( this.getSuggestionid()!=null && castOther.getSuggestionid()!=null && this.getSuggestionid().equals(castOther.getSuggestionid()) ) )
 && ( (this.getIntegrationMinus()==castOther.getIntegrationMinus()) || ( this.getIntegrationMinus()!=null && castOther.getIntegrationMinus()!=null && this.getIntegrationMinus().equals(castOther.getIntegrationMinus()) ) )
 && ( (this.getIntegrationActivity()==castOther.getIntegrationActivity()) || ( this.getIntegrationActivity()!=null && castOther.getIntegrationActivity()!=null && this.getIntegrationActivity().equals(castOther.getIntegrationActivity()) ) )
 && ( (this.getIntegrationMinusTime()==castOther.getIntegrationMinusTime()) || ( this.getIntegrationMinusTime()!=null && castOther.getIntegrationMinusTime()!=null && this.getIntegrationMinusTime().equals(castOther.getIntegrationMinusTime()) ) )
 && ( (this.getOperatorUserCode()==castOther.getOperatorUserCode()) || ( this.getOperatorUserCode()!=null && castOther.getOperatorUserCode()!=null && this.getOperatorUserCode().equals(castOther.getOperatorUserCode()) ) )
 && ( (this.getOther()==castOther.getOther()) || ( this.getOther()!=null && castOther.getOther()!=null && this.getOther().equals(castOther.getOther()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getSuggestionUserCode() == null ? 0 : this.getSuggestionUserCode().hashCode() );
         result = 37 * result + ( getIntegrationAdd() == null ? 0 : this.getIntegrationAdd().hashCode() );
         result = 37 * result + ( getIntegrationAddTime() == null ? 0 : this.getIntegrationAddTime().hashCode() );
         result = 37 * result + ( getSuggestionid() == null ? 0 : this.getSuggestionid().hashCode() );
         result = 37 * result + ( getIntegrationMinus() == null ? 0 : this.getIntegrationMinus().hashCode() );
         result = 37 * result + ( getIntegrationActivity() == null ? 0 : this.getIntegrationActivity().hashCode() );
         result = 37 * result + ( getIntegrationMinusTime() == null ? 0 : this.getIntegrationMinusTime().hashCode() );
         result = 37 * result + ( getOperatorUserCode() == null ? 0 : this.getOperatorUserCode().hashCode() );
         result = 37 * result + ( getOther() == null ? 0 : this.getOther().hashCode() );
         return result;
   }   





}
