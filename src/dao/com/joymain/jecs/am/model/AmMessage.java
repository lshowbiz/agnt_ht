package com.joymain.jecs.am.model;
// Generated 2008-6-24 14:22:30 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="AM_MESSAGE"
 *     
 */

public class AmMessage extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private String uniNo;
    private Date issueTime;
    private Date replyTime;
    private String msgClassNo;
    private String subject;
    private String senderNo;
    private String agentNo;
    private String content;
    private String receiverNo;
    private String replyContent;
    private String checkUserNo;
    private Date checkMsgTime;
    private String companyCode;
    private Integer status;
    //查询
    private String sendRoute;//发送路径 0：公司发送；1：代理商发送
    private String startDate;
    private String endDate;
    private String agentName;
    private String receiverName;
    private String senderName;
    private String discuss;

    /**
	 * *
	 * 
	 * @hibernate.property column="SENDER_NAME" length="16"
	 * 
	 */
    public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	// Constructors
    /**
	 * *
	 * 
	 * @hibernate.property column="AGENT_NAME" length="16"
	 * 
	 */
    public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	/**
	 * *
	 * 
	 * @hibernate.property column="RECEIVER_NAME" length="16"
	 * 
	 */
	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/** default constructor */
    public AmMessage() {
    }

	/** minimal constructor */
    public AmMessage(Date issueTime, String msgClassNo, String subject, String senderNo, String content, String companyCode) {
        this.issueTime = issueTime;
        this.msgClassNo = msgClassNo;
        this.subject = subject;
        this.senderNo = senderNo;
        this.content = content;
        this.companyCode = companyCode;
    }
    
    /** full constructor */
    public AmMessage(Date issueTime, Date replyTime, String msgClassNo, String subject, String senderNo, String agentNo, String content, String receiverNo, String replyContent, String checkUserNo, Date checkMsgTime, String companyCode, Integer status, String angentName, String receiverName, String senderName) {
        this.issueTime = issueTime;
        this.replyTime = replyTime;
        this.msgClassNo = msgClassNo;
        this.subject = subject;
        this.senderNo = senderNo;
        this.agentNo = agentNo;
        this.content = content;
        this.receiverNo = receiverNo;
        this.replyContent = replyContent;
        this.checkUserNo = checkUserNo;
        this.checkMsgTime = checkMsgTime;
        this.companyCode = companyCode;
        this.status = status;
        this.agentName = angentName;
        this.receiverName = receiverName;
        this.senderName = senderName;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.String"
     *             column="UNI_NO"
     *         @hibernate.generator-param name="sequence" value="SEQ_AM" 
     */

    public String getUniNo() {
        return this.uniNo;
    }
    
    public void setUniNo(String uniNo) {
        this.uniNo = uniNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="ISSUE_TIME"
     *             length="7"
     *             not-null="true"
     *         
     */

    public Date getIssueTime() {
        return this.issueTime;
    }
    
    public void setIssueTime(Date issueTime) {
        this.issueTime = issueTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="REPLY_TIME"
     *             length="7"
     *         
     */

    public Date getReplyTime() {
        return this.replyTime;
    }
    
    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="MSG_CLASS_NO"
     *             length="2"
     *             not-null="true"
     *         
     */

    public String getMsgClassNo() {
        return this.msgClassNo;
    }
    
    public void setMsgClassNo(String msgClassNo) {
        this.msgClassNo = msgClassNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="SUBJECT"
     *             length="250"
     *             not-null="true"
     *         
     */

    public String getSubject() {
        return this.subject;
    }
    
    /**
	 * *
	 * 
	 * @spring.validator type="required,maxlength"
	 * @spring.validator-var name="maxlength" value="200"
	 * @spring.validator-args arg1value="200"
	 */
    public void setSubject(String subject) {
        this.subject = subject;
    }
    /**       
     *      *            @hibernate.property
     *             column="SENDER_NO"
     *             length="20"
     *             not-null="true"
     *         
     */

    public String getSenderNo() {
        return this.senderNo;
    }
    
    public void setSenderNo(String senderNo) {
        this.senderNo = senderNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="AGENT_NO"
     *             length="20"
     *         
     */

    public String getAgentNo() {
        return this.agentNo;
    }
    
    public void setAgentNo(String agentNo) {
        this.agentNo = agentNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="CONTENT"
     *             type="org.springframework.orm.hibernate3.support.ClobStringType"
     *             length="4000"
     *             not-null="true"
     *         
     */

    public String getContent() {
        return this.content;
    }
    /**
     * @spring.validator type="required"
     */
    public void setContent(String content) {
        this.content = content;
    }
    /**       
     *      *            @hibernate.property
     *             column="RECEIVER_NO"
     *             length="20"
     *         
     */

    public String getReceiverNo() {
        return this.receiverNo;
    }
    
    public void setReceiverNo(String receiverNo) {
        this.receiverNo = receiverNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="REPLY_CONTENT"
     *             type="org.springframework.orm.hibernate3.support.ClobStringType"
     *             length="4000"
     *         
     */

    public String getReplyContent() {
        return this.replyContent;
    }
    
    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }
    /**       
     *      *            @hibernate.property
     *             column="CHECK_USER_NO"
     *             length="20"
     *         
     */

    public String getCheckUserNo() {
        return this.checkUserNo;
    }
    
    public void setCheckUserNo(String checkUserNo) {
        this.checkUserNo = checkUserNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="CHECK_MSG_TIME"
     *             length="7"
     *         
     */

    public Date getCheckMsgTime() {
        return this.checkMsgTime;
    }
    
    public void setCheckMsgTime(Date checkMsgTime) {
        this.checkMsgTime = checkMsgTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="COMPANY_CODE"
     *             length="2"
     *             not-null="true"
     *         
     */

    public String getCompanyCode() {
        return this.companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="STATUS"
     *             length="1"
     *         
     */

    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("issueTime").append("='").append(getIssueTime()).append("' ");			
      buffer.append("replyTime").append("='").append(getReplyTime()).append("' ");			
      buffer.append("msgClassNo").append("='").append(getMsgClassNo()).append("' ");			
      buffer.append("subject").append("='").append(getSubject()).append("' ");			
      buffer.append("senderNo").append("='").append(getSenderNo()).append("' ");			
      buffer.append("agentNo").append("='").append(getAgentNo()).append("' ");			
      buffer.append("content").append("='").append(getContent()).append("' ");			
      buffer.append("receiverNo").append("='").append(getReceiverNo()).append("' ");			
      buffer.append("replyContent").append("='").append(getReplyContent()).append("' ");			
      buffer.append("checkUserNo").append("='").append(getCheckUserNo()).append("' ");			
      buffer.append("checkMsgTime").append("='").append(getCheckMsgTime()).append("' ");			
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("agentName").append("='").append(getAgentName()).append("' ");
      buffer.append("receiverName").append("='").append(getReceiverName()).append("' ");
      buffer.append("senderName").append("='").append(getSenderName()).append("' ");
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AmMessage) ) return false;
		 AmMessage castOther = ( AmMessage ) other; 
         
		 return ( (this.getUniNo()==castOther.getUniNo()) || ( this.getUniNo()!=null && castOther.getUniNo()!=null && this.getUniNo().equals(castOther.getUniNo()) ) )
 && ( (this.getIssueTime()==castOther.getIssueTime()) || ( this.getIssueTime()!=null && castOther.getIssueTime()!=null && this.getIssueTime().equals(castOther.getIssueTime()) ) )
 && ( (this.getReplyTime()==castOther.getReplyTime()) || ( this.getReplyTime()!=null && castOther.getReplyTime()!=null && this.getReplyTime().equals(castOther.getReplyTime()) ) )
 && ( (this.getMsgClassNo()==castOther.getMsgClassNo()) || ( this.getMsgClassNo()!=null && castOther.getMsgClassNo()!=null && this.getMsgClassNo().equals(castOther.getMsgClassNo()) ) )
 && ( (this.getSubject()==castOther.getSubject()) || ( this.getSubject()!=null && castOther.getSubject()!=null && this.getSubject().equals(castOther.getSubject()) ) )
 && ( (this.getSenderNo()==castOther.getSenderNo()) || ( this.getSenderNo()!=null && castOther.getSenderNo()!=null && this.getSenderNo().equals(castOther.getSenderNo()) ) )
 && ( (this.getAgentNo()==castOther.getAgentNo()) || ( this.getAgentNo()!=null && castOther.getAgentNo()!=null && this.getAgentNo().equals(castOther.getAgentNo()) ) )
 && ( (this.getContent()==castOther.getContent()) || ( this.getContent()!=null && castOther.getContent()!=null && this.getContent().equals(castOther.getContent()) ) )
 && ( (this.getReceiverNo()==castOther.getReceiverNo()) || ( this.getReceiverNo()!=null && castOther.getReceiverNo()!=null && this.getReceiverNo().equals(castOther.getReceiverNo()) ) )
 && ( (this.getReplyContent()==castOther.getReplyContent()) || ( this.getReplyContent()!=null && castOther.getReplyContent()!=null && this.getReplyContent().equals(castOther.getReplyContent()) ) )
 && ( (this.getCheckUserNo()==castOther.getCheckUserNo()) || ( this.getCheckUserNo()!=null && castOther.getCheckUserNo()!=null && this.getCheckUserNo().equals(castOther.getCheckUserNo()) ) )
 && ( (this.getCheckMsgTime()==castOther.getCheckMsgTime()) || ( this.getCheckMsgTime()!=null && castOther.getCheckMsgTime()!=null && this.getCheckMsgTime().equals(castOther.getCheckMsgTime()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
 && ( (this.getAgentName()==castOther.getAgentName()) || ( this.getAgentName()!=null && castOther.getAgentName()!=null && this.getAgentName().equals(castOther.getAgentName()) ) )
 && ( (this.getReceiverName()==castOther.getReceiverName()) || ( this.getReceiverName()!=null && castOther.getReceiverName()!=null && this.getReceiverName().equals(castOther.getReceiverName()) ) )
 && ( (this.getSenderName()==castOther.getSenderName()) || ( this.getSenderName()!=null && castOther.getSenderName()!=null && this.getSenderName().equals(castOther.getSenderName()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getUniNo() == null ? 0 : this.getUniNo().hashCode() );
         result = 37 * result + ( getIssueTime() == null ? 0 : this.getIssueTime().hashCode() );
         result = 37 * result + ( getReplyTime() == null ? 0 : this.getReplyTime().hashCode() );
         result = 37 * result + ( getMsgClassNo() == null ? 0 : this.getMsgClassNo().hashCode() );
         result = 37 * result + ( getSubject() == null ? 0 : this.getSubject().hashCode() );
         result = 37 * result + ( getSenderNo() == null ? 0 : this.getSenderNo().hashCode() );
         result = 37 * result + ( getAgentNo() == null ? 0 : this.getAgentNo().hashCode() );
         result = 37 * result + ( getContent() == null ? 0 : this.getContent().hashCode() );
         result = 37 * result + ( getReceiverNo() == null ? 0 : this.getReceiverNo().hashCode() );
         result = 37 * result + ( getReplyContent() == null ? 0 : this.getReplyContent().hashCode() );
         result = 37 * result + ( getCheckUserNo() == null ? 0 : this.getCheckUserNo().hashCode() );
         result = 37 * result + ( getCheckMsgTime() == null ? 0 : this.getCheckMsgTime().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         result = 37 * result + ( getAgentName() == null ? 0 : this.getAgentName().hashCode() );
         result = 37 * result + ( getReceiverName() == null ? 0 : this.getReceiverName().hashCode() );
         result = 37 * result + ( getSenderName() == null ? 0 : this.getSenderName().hashCode() );
         return result;
   }

   /**       
    *      *            @hibernate.property
    *             column="SEND_ROUTE"
    *             length="2"
    *            
    *         
    */
public String getSendRoute() {
	return sendRoute;
}

public void setSendRoute(String sendRoute) {
	this.sendRoute = sendRoute;
}

/**       
 *      *            @hibernate.property
 *             column="DISCUSS"
 *             length="1"
 *            
 *         
 */
public String getDiscuss() {
	return discuss;
}

public void setDiscuss(String discuss) {
	this.discuss = discuss;
}   





}
