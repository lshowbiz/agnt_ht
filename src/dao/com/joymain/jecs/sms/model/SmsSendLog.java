package com.joymain.jecs.sms.model;
// Generated 2009-11-24 14:37:53 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="SMS_SEND_LOG"
 *     
 */

public class SmsSendLog extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long sslId;
    private String mobile;
    private String sendMsg;
    private String status;
    private String sendNum;


    // Constructors

    /** default constructor */
    public SmsSendLog() {
    }

    
    /** full constructor */
    public SmsSendLog(String mobile, String sendMsg, String status, String sendNum) {
        this.mobile = mobile;
        this.sendMsg = sendMsg;
        this.status = status;
        this.sendNum = sendNum;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="SSL_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_PO"
     *         
     */

    public Long getSslId() {
        return this.sslId;
    }
    
    public void setSslId(Long sslId) {
        this.sslId = sslId;
    }
    /**       
     *      *            @hibernate.property
     *             column="MOBILE"
     *             length="4000"
     *             not-null="true"
     *         
     */

    public String getMobile() {
        return this.mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    /**       
     *      *            @hibernate.property
     *             column="SEND_MSG"
     *             length="4000"
     *             not-null="true"
     *         
     */

    public String getSendMsg() {
        return this.sendMsg;
    }
    
    public void setSendMsg(String sendMsg) {
        this.sendMsg = sendMsg;
    }
    /**       
     *      *            @hibernate.property
     *             column="STATUS"
     *             length="1"
     *             not-null="true"
     *         
     */

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    /**       
     *      *            @hibernate.property
     *             column="SEND_NUM"
     *             length="4000"
     *             not-null="true"
     *         
     */

    public String getSendNum() {
        return this.sendNum;
    }
    
    public void setSendNum(String sendNum) {
        this.sendNum = sendNum;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("mobile").append("='").append(getMobile()).append("' ");			
      buffer.append("sendMsg").append("='").append(getSendMsg()).append("' ");			
      buffer.append("status").append("='").append(getStatus()).append("' ");			
      buffer.append("sendNum").append("='").append(getSendNum()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SmsSendLog) ) return false;
		 SmsSendLog castOther = ( SmsSendLog ) other; 
         
		 return ( (this.getSslId()==castOther.getSslId()) || ( this.getSslId()!=null && castOther.getSslId()!=null && this.getSslId().equals(castOther.getSslId()) ) )
 && ( (this.getMobile()==castOther.getMobile()) || ( this.getMobile()!=null && castOther.getMobile()!=null && this.getMobile().equals(castOther.getMobile()) ) )
 && ( (this.getSendMsg()==castOther.getSendMsg()) || ( this.getSendMsg()!=null && castOther.getSendMsg()!=null && this.getSendMsg().equals(castOther.getSendMsg()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
 && ( (this.getSendNum()==castOther.getSendNum()) || ( this.getSendNum()!=null && castOther.getSendNum()!=null && this.getSendNum().equals(castOther.getSendNum()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getSslId() == null ? 0 : this.getSslId().hashCode() );
         result = 37 * result + ( getMobile() == null ? 0 : this.getMobile().hashCode() );
         result = 37 * result + ( getSendMsg() == null ? 0 : this.getSendMsg().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         result = 37 * result + ( getSendNum() == null ? 0 : this.getSendNum().hashCode() );
         return result;
   }   





}
