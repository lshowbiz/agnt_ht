package com.joymain.jecs.mi.model;
// Generated 2015-8-3 16:33:25 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JMI_STATE_LOG"
 *     
 */

public class JmiStateLog extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private String userCode;
    private Date createTime;
    private String createNo;
    private String logType;
    private String state;

    // Constructors

    /**       
     *      *            @hibernate.property
     *             column="STATE"
     *         
     */

    public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	/** default constructor */
    public JmiStateLog() {
    }

    
    /** full constructor */
    public JmiStateLog(String userCode, Date createTime, String createNo, String logType) {
        this.userCode = userCode;
        this.createTime = createTime;
        this.createNo = createNo;
        this.logType = logType;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ID"
     *@hibernate.generator-param name="sequence" value="SEQ_MI"
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
     *             column="LOG_TYPE"
     *             length="1"
     *         
     */

    public String getLogType() {
        return this.logType;
    }
    
    public void setLogType(String logType) {
        this.logType = logType;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("createNo").append("='").append(getCreateNo()).append("' ");			
      buffer.append("logType").append("='").append(getLogType()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JmiStateLog) ) return false;
		 JmiStateLog castOther = ( JmiStateLog ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getCreateNo()==castOther.getCreateNo()) || ( this.getCreateNo()!=null && castOther.getCreateNo()!=null && this.getCreateNo().equals(castOther.getCreateNo()) ) )
 && ( (this.getLogType()==castOther.getLogType()) || ( this.getLogType()!=null && castOther.getLogType()!=null && this.getLogType().equals(castOther.getLogType()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getCreateNo() == null ? 0 : this.getCreateNo().hashCode() );
         result = 37 * result + ( getLogType() == null ? 0 : this.getLogType().hashCode() );
         return result;
   }   





}
