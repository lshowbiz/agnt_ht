package com.joymain.jecs.bd.model;
// Generated 2013-11-26 15:39:19 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JBD_MANUAL_CON"
 *     
 */

public class JbdManualCon extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private String userCode;
    private Integer startWeek;
    private Integer endWeek;
    private Integer salesStatus;
    private Integer consumerStatus;
    private String createNo;
    private Date createTime;


    // Constructors

    /** default constructor */
    public JbdManualCon() {
    }

    
    /** full constructor */
    public JbdManualCon(String userCode, Integer startWeek, Integer endWeek, Integer salesStatus, Integer consumerStatus, String createNo, Date createTime) {
        this.userCode = userCode;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.salesStatus = salesStatus;
        this.consumerStatus = consumerStatus;
        this.createNo = createNo;
        this.createTime = createTime;
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
     *             length="50"
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
     *             column="START_WEEK"
     *             length="22"
     *         
     */

    public Integer getStartWeek() {
        return this.startWeek;
    }
    
    public void setStartWeek(Integer startWeek) {
        this.startWeek = startWeek;
    }
    /**       
     *      *            @hibernate.property
     *             column="END_WEEK"
     *             length="22"
     *         
     */

    public Integer getEndWeek() {
        return this.endWeek;
    }
    
    public void setEndWeek(Integer endWeek) {
        this.endWeek = endWeek;
    }
    /**       
     *      *            @hibernate.property
     *             column="SALES_STATUS"
     *             length="22"
     *         
     */

    public Integer getSalesStatus() {
        return this.salesStatus;
    }
    
    public void setSalesStatus(Integer salesStatus) {
        this.salesStatus = salesStatus;
    }
    /**       
     *      *            @hibernate.property
     *             column="CONSUMER_STATUS"
     *             length="22"
     *         
     */

    public Integer getConsumerStatus() {
        return this.consumerStatus;
    }
    
    public void setConsumerStatus(Integer consumerStatus) {
        this.consumerStatus = consumerStatus;
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
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("startWeek").append("='").append(getStartWeek()).append("' ");			
      buffer.append("endWeek").append("='").append(getEndWeek()).append("' ");			
      buffer.append("salesStatus").append("='").append(getSalesStatus()).append("' ");			
      buffer.append("consumerStatus").append("='").append(getConsumerStatus()).append("' ");			
      buffer.append("createNo").append("='").append(getCreateNo()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JbdManualCon) ) return false;
		 JbdManualCon castOther = ( JbdManualCon ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getStartWeek()==castOther.getStartWeek()) || ( this.getStartWeek()!=null && castOther.getStartWeek()!=null && this.getStartWeek().equals(castOther.getStartWeek()) ) )
 && ( (this.getEndWeek()==castOther.getEndWeek()) || ( this.getEndWeek()!=null && castOther.getEndWeek()!=null && this.getEndWeek().equals(castOther.getEndWeek()) ) )
 && ( (this.getSalesStatus()==castOther.getSalesStatus()) || ( this.getSalesStatus()!=null && castOther.getSalesStatus()!=null && this.getSalesStatus().equals(castOther.getSalesStatus()) ) )
 && ( (this.getConsumerStatus()==castOther.getConsumerStatus()) || ( this.getConsumerStatus()!=null && castOther.getConsumerStatus()!=null && this.getConsumerStatus().equals(castOther.getConsumerStatus()) ) )
 && ( (this.getCreateNo()==castOther.getCreateNo()) || ( this.getCreateNo()!=null && castOther.getCreateNo()!=null && this.getCreateNo().equals(castOther.getCreateNo()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getStartWeek() == null ? 0 : this.getStartWeek().hashCode() );
         result = 37 * result + ( getEndWeek() == null ? 0 : this.getEndWeek().hashCode() );
         result = 37 * result + ( getSalesStatus() == null ? 0 : this.getSalesStatus().hashCode() );
         result = 37 * result + ( getConsumerStatus() == null ? 0 : this.getConsumerStatus().hashCode() );
         result = 37 * result + ( getCreateNo() == null ? 0 : this.getCreateNo().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         return result;
   }   





}
