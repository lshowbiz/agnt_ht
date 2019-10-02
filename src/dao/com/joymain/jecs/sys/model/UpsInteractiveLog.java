package com.joymain.jecs.sys.model;
// Generated 2010-10-18 17:00:29 by Hibernate Tools 3.1.0.beta4

import java.sql.Clob;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="UPS_INTERACTIVE_LOG"
 *     
 */

public class UpsInteractiveLog extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long uniId;
    private String type;
    private String orderNo;
    private String send;
    private String receive;
    private Date createTime;


    // Constructors

    /** default constructor */
    public UpsInteractiveLog() {
    }

    
    /** full constructor */
    public UpsInteractiveLog(String type, String orderNo, String send, String receive, Date createTime) {
        this.type = type;
        this.orderNo = orderNo;
        this.send = send;
        this.receive = receive;
        this.createTime = createTime;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             column="UNI_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_LOG"        
     */

    public Long getUniId() {
        return this.uniId;
    }
    
    public void setUniId(Long uniId) {
        this.uniId = uniId;
    }
    /**       
     *      *            @hibernate.property
     *             column="TYPE"
     *             length="20"
     *         
     */

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    /**       
     *      *            @hibernate.property
     *             column="ORDER_NO"
     *             length="20"
     *         
     */

    public String getOrderNo() {
        return this.orderNo;
    }
    
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="SEND"
     *             length="4000"
     *         
     */

    public String getSend() {
        return this.send;
    }
    
    public void setSend(String send) {
        this.send = send;
    }
    /**       
     *      *            @hibernate.property
     *             column="RECEIVE"
     *             length="4000"
     *         
     */

    public String getReceive() {
        return this.receive;
    }
    
    public void setReceive(String receive) {
        this.receive = receive;
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
      buffer.append("type").append("='").append(getType()).append("' ");			
      buffer.append("orderNo").append("='").append(getOrderNo()).append("' ");			
      buffer.append("send").append("='").append(getSend()).append("' ");			
      buffer.append("receive").append("='").append(getReceive()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof UpsInteractiveLog) ) return false;
		 UpsInteractiveLog castOther = ( UpsInteractiveLog ) other; 
         
		 return ( (this.getUniId()==castOther.getUniId()) || ( this.getUniId()!=null && castOther.getUniId()!=null && this.getUniId().equals(castOther.getUniId()) ) )
 && ( (this.getType()==castOther.getType()) || ( this.getType()!=null && castOther.getType()!=null && this.getType().equals(castOther.getType()) ) )
 && ( (this.getOrderNo()==castOther.getOrderNo()) || ( this.getOrderNo()!=null && castOther.getOrderNo()!=null && this.getOrderNo().equals(castOther.getOrderNo()) ) )
 && ( (this.getSend()==castOther.getSend()) || ( this.getSend()!=null && castOther.getSend()!=null && this.getSend().equals(castOther.getSend()) ) )
 && ( (this.getReceive()==castOther.getReceive()) || ( this.getReceive()!=null && castOther.getReceive()!=null && this.getReceive().equals(castOther.getReceive()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getUniId() == null ? 0 : this.getUniId().hashCode() );
         result = 37 * result + ( getType() == null ? 0 : this.getType().hashCode() );
         result = 37 * result + ( getOrderNo() == null ? 0 : this.getOrderNo().hashCode() );
         result = 37 * result + ( getSend() == null ? 0 : this.getSend().hashCode() );
         result = 37 * result + ( getReceive() == null ? 0 : this.getReceive().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         return result;
   }   





}
