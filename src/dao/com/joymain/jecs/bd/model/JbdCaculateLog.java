package com.joymain.jecs.bd.model;
// Generated 2009-11-18 14:38:02 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JBD_CACULATE_LOG"
 *     
 */

public class JbdCaculateLog extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private BigDecimal id;
    private Integer wyear;
    private Integer wmonth;
    private Integer wweek;
    private String currentStep;
    private Integer errno;
    private String userCode;
    private String errmsg;
    private Date createTime;
    private String productNo;


    // Constructors

    /** default constructor */
    public JbdCaculateLog() {
    }

    
    /** full constructor */
    public JbdCaculateLog(Integer wyear, Integer wmonth, Integer wweek, String currentStep, Integer errno, String userCode, String errmsg, Date createTime, String productNo) {
        this.wyear = wyear;
        this.wmonth = wmonth;
        this.wweek = wweek;
        this.currentStep = currentStep;
        this.errno = errno;
        this.userCode = userCode;
        this.errmsg = errmsg;
        this.createTime = createTime;
        this.productNo = productNo;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.math.BigDecimal"
     *             column="ID"
     *         
     */

    public BigDecimal getId() {
        return this.id;
    }
    
    public void setId(BigDecimal id) {
        this.id = id;
    }
    /**       
     *      *            @hibernate.property
     *             column="W_YEAR"
     *             length="8"
     *         
     */

    public Integer getWyear() {
        return this.wyear;
    }
    
    public void setWyear(Integer wyear) {
        this.wyear = wyear;
    }
    /**       
     *      *            @hibernate.property
     *             column="W_MONTH"
     *             length="8"
     *         
     */

    public Integer getWmonth() {
        return this.wmonth;
    }
    
    public void setWmonth(Integer wmonth) {
        this.wmonth = wmonth;
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
     *      *            @hibernate.property
     *             column="CURRENT_STEP"
     *             length="60"
     *         
     */

    public String getCurrentStep() {
        return this.currentStep;
    }
    
    public void setCurrentStep(String currentStep) {
        this.currentStep = currentStep;
    }
    /**       
     *      *            @hibernate.property
     *             column="ERRNO"
     *             length="8"
     *         
     */

    public Integer getErrno() {
        return this.errno;
    }
    
    public void setErrno(Integer errno) {
        this.errno = errno;
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
     *             column="ERRMSG"
     *             length="200"
     *         
     */

    public String getErrmsg() {
        return this.errmsg;
    }
    
    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
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
     *             column="PRODUCT_NO"
     *             length="30"
     *         
     */

    public String getProductNo() {
        return this.productNo;
    }
    
    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("wyear").append("='").append(getWyear()).append("' ");			
      buffer.append("wmonth").append("='").append(getWmonth()).append("' ");			
      buffer.append("wweek").append("='").append(getWweek()).append("' ");			
      buffer.append("currentStep").append("='").append(getCurrentStep()).append("' ");			
      buffer.append("errno").append("='").append(getErrno()).append("' ");			
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("errmsg").append("='").append(getErrmsg()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("productNo").append("='").append(getProductNo()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JbdCaculateLog) ) return false;
		 JbdCaculateLog castOther = ( JbdCaculateLog ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getWyear()==castOther.getWyear()) || ( this.getWyear()!=null && castOther.getWyear()!=null && this.getWyear().equals(castOther.getWyear()) ) )
 && ( (this.getWmonth()==castOther.getWmonth()) || ( this.getWmonth()!=null && castOther.getWmonth()!=null && this.getWmonth().equals(castOther.getWmonth()) ) )
 && ( (this.getWweek()==castOther.getWweek()) || ( this.getWweek()!=null && castOther.getWweek()!=null && this.getWweek().equals(castOther.getWweek()) ) )
 && ( (this.getCurrentStep()==castOther.getCurrentStep()) || ( this.getCurrentStep()!=null && castOther.getCurrentStep()!=null && this.getCurrentStep().equals(castOther.getCurrentStep()) ) )
 && ( (this.getErrno()==castOther.getErrno()) || ( this.getErrno()!=null && castOther.getErrno()!=null && this.getErrno().equals(castOther.getErrno()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getErrmsg()==castOther.getErrmsg()) || ( this.getErrmsg()!=null && castOther.getErrmsg()!=null && this.getErrmsg().equals(castOther.getErrmsg()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getProductNo()==castOther.getProductNo()) || ( this.getProductNo()!=null && castOther.getProductNo()!=null && this.getProductNo().equals(castOther.getProductNo()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getWyear() == null ? 0 : this.getWyear().hashCode() );
         result = 37 * result + ( getWmonth() == null ? 0 : this.getWmonth().hashCode() );
         result = 37 * result + ( getWweek() == null ? 0 : this.getWweek().hashCode() );
         result = 37 * result + ( getCurrentStep() == null ? 0 : this.getCurrentStep().hashCode() );
         result = 37 * result + ( getErrno() == null ? 0 : this.getErrno().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getErrmsg() == null ? 0 : this.getErrmsg().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getProductNo() == null ? 0 : this.getProductNo().hashCode() );
         return result;
   }   





}
