package com.joymain.jecs.am.model;
// Generated 2013-8-13 16:40:18 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="INW_SUGGESTION"
 *     
 */

public class InwSuggestion extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private String demandId;
    private String subject;
    
    private Date createTime;
    private Date replyTime;
    private String replyTontent;
    private String userCode;
    //reply_user_Code
    private String replyUserCode;
    //proposed_adoption
    private String proposedAdoption;
    
    //content字段的数量类型发生了改变
    private String content;
    //下面是创新共赢需求表新添加的字段
    private String suggestionAccept;
    private String suggestionSort;
    
    //initialAudit 建议分流到具体业务部门前的审核   yxzz 2014-06-24 
    private String initialAudit;
    
    private String feasibilityAudit;
    private String status;
    private String phone;
    private String address;
    private String demandsortId;
    //下面还是新加的字段
    private String integration;
    //2013-11-18新加的字段
    private String microMessage;
    private String qq;
    //2014-05-12新加的字段
    //reply_content_second 建议回复(项目建议初次审核回复或第二次回复)
    private String replyContentSecond;
    //reply_content_third 建议回复(项目建议可行性审核回复或第三次回复)
    private String replyContentThird;
    //REPLY_USER_CODE_SECOND VARCHAR2(20)  建议回复(项目建议初次审核回复或第二次回复)的回复人   
    private String replyUserCodeSecond;
    //REPLY_TIME_SECOND      DATE          建议回复(项目建议初次审核回复或第二次回复)的回复时间    
    private Date replyTimeSecond;
    //REPLY_USER_CODE_THIRD  VARCHAR2(20)  建议回复(项目建议可行性审核回复或第三次回复)的回复人     
    private String replyUserCodeThird;
    //REPLY_TIME_THIRD       DATE          建议回复(项目建议可行性审核回复或第三次回复)的回复时间
    private Date replyTimeThird;
 
    //2014-05-19新加的字段   add by gw
    //REPLY_YES_NO           VARCHAR2(2)   是否有建议回复(Y表示有新的建议回复,N或空表示没有新的建议回复)   
    private String replyYesNo;

    //2014-06-30新加的字段   对建议的回复内容进行多次审核的相关字段   add by gw 
    private String firstReplyAudit;
    private String firseReplyPerson;
    private Date firstReplyTime;
    
    private String secondReplyAudit;
    private String secondReplyPerson;
    private Date secondReplyTime;
    
    private String thirdReplyAudit;
    private String thirdReplyPerson;
    private Date thirdReplyTime;     
    //2014-06-30新加的字段   对建议的回复内容进行多次审核的相关字段   add by gw 
    
    //UPDATE_FLAG 2014-07-08新加的字段  如果对建议进行回复或对建议内容有审核的情况下  add by gw 
    private String updateFlag;
    
    //2014-08-19 添加字段
    private Date initialAuditTime;
    private String initialAuditUser;
    
    
    
    // Constructors

    /** default constructor */
    public InwSuggestion() {
    }

	/** minimal constructor */
    public InwSuggestion(String demandId) {
        this.demandId = demandId;
    }
    
    /** full constructor */
    public InwSuggestion(String demandId, String subject, Date createTime, Date replyTime, String replyTontent, String userCode, String replyUserCode, String proposedAdoption, String content,
    		String suggestionAccept,String suggestionSort,String initialAudit,String feasibilityAudit,String status,String phone,String address,String demandsortId,String integration,
    		String microMessage,String qq,String replyContentSecond,String replyContentThird,
    		String replyUserCodeSecond,Date replyTimeSecond,String replyUserCodeThird,Date replyTimeThird,String replyYesNo,
    		String firstReplyAudit,String firseReplyPerson,Date firstReplyTime,
    		String secondReplyAudit,String secondReplyPerson,Date secondReplyTime,
    		String thirdReplyAudit,String thirdReplyPerson,Date thirdReplyTime,String updateFlag,Date initialAuditTime,String initialAuditUser) {
        this.demandId = demandId;
        this.subject = subject;
        this.createTime = createTime;
        this.replyTime = replyTime;
        this.replyTontent = replyTontent;
        this.userCode = userCode;
        this.replyUserCode = replyUserCode;
        this.proposedAdoption  = proposedAdoption;
        this.content = content;
        this.suggestionAccept = suggestionAccept;
        this.suggestionSort = suggestionSort;
        this.initialAudit = initialAudit;
        this.feasibilityAudit = feasibilityAudit;
        this.status = status;
        this.phone = phone;
        this.address = address;
        this.demandsortId = demandsortId;
        this.integration = integration;
        this.microMessage = microMessage; 
        this.qq = qq;
        this.replyContentSecond = replyContentSecond;
        this.replyContentThird = replyContentThird;      
        this.replyUserCodeSecond = replyUserCodeSecond;
        this.replyTimeSecond = replyTimeSecond;
        this.replyUserCodeThird = replyUserCodeThird;
        this.replyTimeThird = replyTimeThird;
        this.replyYesNo = replyYesNo;
        
        this.firstReplyAudit = firstReplyAudit;
        this.firseReplyPerson = firseReplyPerson;
        this.firstReplyTime = firstReplyTime;
        this.secondReplyAudit = secondReplyAudit;
        this.secondReplyPerson = secondReplyPerson;
        this.secondReplyTime = secondReplyTime;
        this.thirdReplyAudit = thirdReplyAudit;
        this.thirdReplyPerson = thirdReplyPerson;
        this.thirdReplyTime = thirdReplyTime;
        
        this.updateFlag = updateFlag;
        this.initialAuditTime = initialAuditTime;
        this.initialAuditUser = initialAuditUser;
        
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
     *             column="DEMAND_ID"
     *             length="13"
     *         
     */

    public String getDemandId() {
        return this.demandId;
    }
    
    public void setDemandId(String demandId) {
        this.demandId = demandId;
    }
    /**       
     *      *            @hibernate.property
     *             column="SUBJECT"
     *             length="100"
     *         
     */

    public String getSubject() {
        return this.subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    /**       
     *      *            @hibernate.property
     *             column="CONTENT"
     *             length="4000"
     *         
     */

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
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
     *             column="REPLY_TONTENT"
     *             length="1000"
     *         
     */

    public String getReplyTontent() {
        return this.replyTontent;
    }
    
    public void setReplyTontent(String replyTontent) {
        this.replyTontent = replyTontent;
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
     *             column="REPLY_USER_CODE"
     *             length="20"
     *         
     */
	public String getReplyUserCode() {
		return replyUserCode;
	}

	public void setReplyUserCode(String replyUserCode) {
		this.replyUserCode = replyUserCode;
	}

	/**       
     *      *            @hibernate.property
     *             column="PROPOSED_ADOPTION"
     *             length="2"
     *         
     */
	public String getProposedAdoption() {
		return proposedAdoption;
	}

	public void setProposedAdoption(String proposedAdoption) {
		this.proposedAdoption = proposedAdoption;
	}

	
	/**       
     *      *            @hibernate.property
     *             column="SUGGESTION_ACCEPT"
     *             length="2"
     *         
     */
	public String getSuggestionAccept() {
		return suggestionAccept;
	}

	public void setSuggestionAccept(String suggestionAccept) {
		this.suggestionAccept = suggestionAccept;
	}

	/**       
     *      *            @hibernate.property
     *             column="SUGGESTION_SORT"
     *             length="2"
     *         
     */
	public String getSuggestionSort() {
		return suggestionSort;
	}

	public void setSuggestionSort(String suggestionSort) {
		this.suggestionSort = suggestionSort;
	}

	/**       
     *      *            @hibernate.property
     *             column="INITIAL_AUDIT"
     *             length="2"
     *         
     */
	public String getInitialAudit() {
		return initialAudit;
	}

	public void setInitialAudit(String initialAudit) {
		this.initialAudit = initialAudit;
	}

	/**       
     *      *            @hibernate.property
     *             column="FEASIBILITY_AUDIT"
     *             length="2"
     *         
     */
	public String getFeasibilityAudit() {
		return feasibilityAudit;
	}

	public void setFeasibilityAudit(String feasibilityAudit) {
		this.feasibilityAudit = feasibilityAudit;
	}

	/**       
     *      *            @hibernate.property
     *             column="STATUS"
     *             length="2"
     *         
     */
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**       
     *      *            @hibernate.property
     *             column="PHONE"
     *             length="30"
     *         
     */
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**       
     *      *            @hibernate.property
     *             column="ADDRESS"
     *             length="200"
     *         
     */
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/**       
     *      *            @hibernate.property
     *             column="DEMANDSORT_ID"
     *             length="32"
     *         
     */
	public String getDemandsortId() {
		return demandsortId;
	}

	public void setDemandsortId(String demandsortId) {
		this.demandsortId = demandsortId;
	}
	
	/**       
     *      *            @hibernate.property
     *             column="INTEGRATION"
     *             length="5"
     *         
     */
	public String getIntegration() {
		return integration;
	}

	public void setIntegration(String integration) {
		this.integration = integration;
	}

	/**       
     *      *            @hibernate.property
     *             column="MICRO_MESSAGE"
     *             length="80"
     *         
     */
	public String getMicroMessage() {
		return microMessage;
	}

	public void setMicroMessage(String microMessage) {
		this.microMessage = microMessage;
	}

	 /**       
     *      *            @hibernate.property
     *             column="QQ"
     *             length="11"
     *         
     */
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	 /**       
     *      *            @hibernate.property
     *             column="REPLY_CONTENT_SECOND"
     *             length="1000"
     *         
     */
	public String getReplyContentSecond() {
		return replyContentSecond;
	}

	public void setReplyContentSecond(String replyContentSecond) {
		this.replyContentSecond = replyContentSecond;
	}

	 /**       
     *      *            @hibernate.property
     *             column="REPLY_CONTENT_THIRD"
     *             length="1000"
     *         
     */
	public String getReplyContentThird() {
		return replyContentThird;
	}

	public void setReplyContentThird(String replyContentThird) {
		this.replyContentThird = replyContentThird;
	} 
	
	/**       
     *      *            @hibernate.property
     *             column="REPLY_USER_CODE_SECOND"
     *             length="20"
     *         
     */
	public String getReplyUserCodeSecond() {
		return replyUserCodeSecond;
	}

	public void setReplyUserCodeSecond(String replyUserCodeSecond) {
		this.replyUserCodeSecond = replyUserCodeSecond;
	}

	 /**       
     *      *            @hibernate.property
     *             column="REPLY_TIME_SECOND"
     *             length="7"
     *         
     */
	public Date getReplyTimeSecond() {
		return replyTimeSecond;
	}

	public void setReplyTimeSecond(Date replyTimeSecond) {
		this.replyTimeSecond = replyTimeSecond;
	}

	/**       
     *      *            @hibernate.property
     *             column="REPLY_USER_CODE_THIRD"
     *             length="20"
     *         
     */
	public String getReplyUserCodeThird() {
		return replyUserCodeThird;
	}

	public void setReplyUserCodeThird(String replyUserCodeThird) {
		this.replyUserCodeThird = replyUserCodeThird;
	}

	 /**       
     *      *            @hibernate.property
     *             column="REPLY_TIME_THIRD"
     *             length="7"
     *         
     */
	public Date getReplyTimeThird() {
		return replyTimeThird;
	}

	public void setReplyTimeThird(Date replyTimeThird) {
		this.replyTimeThird = replyTimeThird;
	}

	
	
	 /**       
     *      *            @hibernate.property
     *             column="REPLY_YES_NO"
     *             length="2"
     *         
     */
	public String getReplyYesNo() {
		return replyYesNo;
	}

	public void setReplyYesNo(String replyYesNo) {
		this.replyYesNo = replyYesNo;
	}
	
	/**       
     *      *            @hibernate.property
     *             column="FIRST_REPLY_AUDIT"
     *             length="2"
     *         
     */
	public String getFirstReplyAudit() {
		return firstReplyAudit;
	}

	public void setFirstReplyAudit(String firstReplyAudit) {
		this.firstReplyAudit = firstReplyAudit;
	}

	/**       
     *      *            @hibernate.property
     *             column="FIRST_REPLY_PERSON"
     *             length="20"
     *         
     */
	public String getFirseReplyPerson() {
		return firseReplyPerson;
	}

	public void setFirseReplyPerson(String firseReplyPerson) {
		this.firseReplyPerson = firseReplyPerson;
	}

	/**       
     *      *            @hibernate.property
     *             column="FIRST_REPLY_TIME"
     *             length="7"
     *         
     */
	public Date getFirstReplyTime() {
		return firstReplyTime;
	}

	public void setFirstReplyTime(Date firstReplyTime) {
		this.firstReplyTime = firstReplyTime;
	}

	/**       
     *      *            @hibernate.property
     *             column="SECOND_REPLY_AUDIT"
     *             length="2"
     *         
     */
	public String getSecondReplyAudit() {
		return secondReplyAudit;
	}

	public void setSecondReplyAudit(String secondReplyAudit) {
		this.secondReplyAudit = secondReplyAudit;
	}

	/**       
     *      *            @hibernate.property
     *             column="SECOND_REPLY_PERSON"
     *             length="20"
     *         
     */
	public String getSecondReplyPerson() {
		return secondReplyPerson;
	}

	public void setSecondReplyPerson(String secondReplyPerson) {
		this.secondReplyPerson = secondReplyPerson;
	}

	/**       
     *      *            @hibernate.property
     *             column="SECOND_REPLY_TIME"
     *             length="7"
     *         
     */
	public Date getSecondReplyTime() {
		return secondReplyTime;
	}

	public void setSecondReplyTime(Date secondReplyTime) {
		this.secondReplyTime = secondReplyTime;
	}

	/**       
     *      *            @hibernate.property
     *             column="THIRD_REPLY_AUDIT"
     *             length="2"
     *         
     */
	public String getThirdReplyAudit() {
		return thirdReplyAudit;
	}

	public void setThirdReplyAudit(String thirdReplyAudit) {
		this.thirdReplyAudit = thirdReplyAudit;
	}

	/**       
     *      *            @hibernate.property
     *             column="THIRD_REPLY_PERSON"
     *             length="20"
     *         
     */
	public String getThirdReplyPerson() {
		return thirdReplyPerson;
	}

	public void setThirdReplyPerson(String thirdReplyPerson) {
		this.thirdReplyPerson = thirdReplyPerson;
	}

	/**       
     *      *            @hibernate.property
     *             column="THIRD_REPLY_TIME"
     *             length="7"
     *         
     */
	public Date getThirdReplyTime() {
		return thirdReplyTime;
	}

	public void setThirdReplyTime(Date thirdReplyTime) {
		this.thirdReplyTime = thirdReplyTime;
	}
	
	/**       
     *      *            @hibernate.property
     *             column="UPDATE_FLAG"
     *             length="2"
     *         
     */
	public String getUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
	}
	
	
	
	/**       
     *      *            @hibernate.property
     *             column="INITIAL_AUDIT_TIME"
     *             length="7"
     *         
     */
	public Date getInitialAuditTime() {
		return initialAuditTime;
	}

	public void setInitialAuditTime(Date initialAuditTime) {
		this.initialAuditTime = initialAuditTime;
	}
		
	/**       
     *      *            @hibernate.property
     *             column="INITIAL_AUDIT_USER"
     *             length="30"
     *         
     */
	public String getInitialAuditUser() {
		return initialAuditUser;
	}

	public void setInitialAuditUser(String initialAuditUser) {
		this.initialAuditUser = initialAuditUser;
	}

	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("demandId").append("='").append(getDemandId()).append("' ");			
      buffer.append("subject").append("='").append(getSubject()).append("' ");			
      buffer.append("content").append("='").append(getContent()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("replyTime").append("='").append(getReplyTime()).append("' ");			
      buffer.append("replyTontent").append("='").append(getReplyTontent()).append("' ");			
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");	
      buffer.append("replyUserCode").append("='").append(getReplyUserCode()).append("' ");
      buffer.append("proposedAdoption").append("='").append(getProposedAdoption()).append("' ");
      
      buffer.append("suggestionAccept").append("='").append(getSuggestionAccept()).append("' ");
      buffer.append("suggestionSort").append("='").append(getSuggestionSort()).append("' ");
      buffer.append("initialAudit").append("='").append(getInitialAudit()).append("' ");
      buffer.append("feasibilityAudit").append("='").append(getFeasibilityAudit()).append("' ");
      buffer.append("status").append("='").append(getStatus()).append("' ");
      buffer.append("phone").append("='").append(getPhone()).append("' ");
      buffer.append("address").append("='").append(getAddress()).append("' ");
      buffer.append("demandsortId").append("='").append(getDemandsortId()).append("' ");
      buffer.append("integration").append("='").append(getIntegration()).append("' ");
      buffer.append("microMessage").append("='").append(getMicroMessage()).append("' ");
      buffer.append("qq").append("='").append(getQq()).append("' ");
      buffer.append("replyContentSecond").append("='").append(getReplyContentSecond()).append("' ");
      buffer.append("replyContentThird").append("='").append(getReplyContentThird()).append("' ");
      buffer.append("replyUserCodeSecond").append("='").append(getReplyUserCodeSecond()).append("' ");
      buffer.append("replyTimeSecond").append("='").append(getReplyTimeSecond()).append("' ");
      buffer.append("replyUserCodeThird").append("='").append(getReplyUserCodeThird()).append("' ");
      buffer.append("replyTimeThird").append("='").append(getReplyTimeThird()).append("' ");
      buffer.append("replyYesNo").append("='").append(getReplyYesNo()).append("' ");
     
      buffer.append("firstReplyAudit").append("='").append(getFirstReplyAudit()).append("' ");
      buffer.append("firseReplyPerson").append("='").append(getFirseReplyPerson()).append("' ");
      buffer.append("firstReplyTime").append("='").append(getFirstReplyTime()).append("' ");
      buffer.append("secondReplyAudit").append("='").append(getSecondReplyAudit()).append("' ");
      buffer.append("secondReplyPerson").append("='").append(getSecondReplyPerson()).append("' ");
      buffer.append("secondReplyTime").append("='").append(getSecondReplyTime()).append("' ");
      buffer.append("thirdReplyAudit").append("='").append(getThirdReplyAudit()).append("' ");
      buffer.append("thirdReplyPerson").append("='").append(getThirdReplyPerson()).append("' ");
      buffer.append("thirdReplyTime").append("='").append(getThirdReplyTime()).append("' ");
      
      buffer.append("updateFlag").append("='").append(getUpdateFlag()).append("' ");
      buffer.append("initialAuditTime").append("='").append(getInitialAuditTime()).append("' ");
      
      buffer.append("initialAuditUser").append("='").append(getInitialAuditUser()).append("' ");


      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof InwSuggestion) ) return false;
		 InwSuggestion castOther = ( InwSuggestion ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getDemandId()==castOther.getDemandId()) || ( this.getDemandId()!=null && castOther.getDemandId()!=null && this.getDemandId().equals(castOther.getDemandId()) ) )
 && ( (this.getSubject()==castOther.getSubject()) || ( this.getSubject()!=null && castOther.getSubject()!=null && this.getSubject().equals(castOther.getSubject()) ) )
 && ( (this.getContent()==castOther.getContent()) || ( this.getContent()!=null && castOther.getContent()!=null && this.getContent().equals(castOther.getContent()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getReplyTime()==castOther.getReplyTime()) || ( this.getReplyTime()!=null && castOther.getReplyTime()!=null && this.getReplyTime().equals(castOther.getReplyTime()) ) )
 && ( (this.getReplyTontent()==castOther.getReplyTontent()) || ( this.getReplyTontent()!=null && castOther.getReplyTontent()!=null && this.getReplyTontent().equals(castOther.getReplyTontent()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getReplyUserCode()==castOther.getReplyUserCode()) || ( this.getReplyUserCode()!=null && castOther.getReplyUserCode()!=null && this.getReplyUserCode().equals(castOther.getReplyUserCode()) ) )
 && ( (this.getProposedAdoption()==castOther.getProposedAdoption()) || ( this.getProposedAdoption()!=null && castOther.getProposedAdoption()!=null && this.getProposedAdoption().equals(castOther.getProposedAdoption()) ) )
 
 && ( (this.getSuggestionAccept()==castOther.getSuggestionAccept()) || ( this.getSuggestionAccept()!=null && castOther.getSuggestionAccept()!=null && this.getSuggestionAccept().equals(castOther.getSuggestionAccept()) ) )
 && ( (this.getSuggestionSort()==castOther.getSuggestionSort()) || ( this.getSuggestionSort()!=null && castOther.getSuggestionSort()!=null && this.getSuggestionSort().equals(castOther.getSuggestionSort()) ) )
 && ( (this.getInitialAudit()==castOther.getInitialAudit()) || ( this.getInitialAudit()!=null && castOther.getInitialAudit()!=null && this.getInitialAudit().equals(castOther.getInitialAudit()) ) )
 && ( (this.getFeasibilityAudit()==castOther.getFeasibilityAudit()) || ( this.getFeasibilityAudit()!=null && castOther.getFeasibilityAudit()!=null && this.getFeasibilityAudit().equals(castOther.getFeasibilityAudit()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
 && ( (this.getPhone()==castOther.getPhone()) || ( this.getPhone()!=null && castOther.getPhone()!=null && this.getPhone().equals(castOther.getPhone()) ) )
 && ( (this.getAddress()==castOther.getAddress()) || ( this.getAddress()!=null && castOther.getAddress()!=null && this.getAddress().equals(castOther.getAddress()) ) )
 && ( (this.getDemandsortId()==castOther.getDemandsortId()) || ( this.getDemandsortId()!=null && castOther.getDemandsortId()!=null && this.getDemandsortId().equals(castOther.getDemandsortId()) ) )
 && ( (this.getIntegration()==castOther.getIntegration()) || ( this.getIntegration()!=null && castOther.getIntegration()!=null && this.getIntegration().equals(castOther.getIntegration()) ) )
 && ( (this.getMicroMessage()==castOther.getMicroMessage()) || ( this.getMicroMessage()!=null && castOther.getMicroMessage()!=null && this.getMicroMessage().equals(castOther.getMicroMessage()) ) )
 && ( (this.getQq()==castOther.getQq()) || ( this.getQq()!=null && castOther.getQq()!=null && this.getQq().equals(castOther.getQq()) ) )
 && ( (this.getReplyContentSecond()==castOther.getReplyContentSecond()) || ( this.getReplyContentSecond()!=null && castOther.getReplyContentSecond()!=null && this.getReplyContentSecond().equals(castOther.getReplyContentSecond()) ) )
 && ( (this.getReplyContentThird()==castOther.getReplyContentThird()) || ( this.getReplyContentThird()!=null && castOther.getReplyContentThird()!=null && this.getReplyContentThird().equals(castOther.getReplyContentThird()) ) )
 && ( (this.getReplyUserCodeSecond()==castOther.getReplyUserCodeSecond()) || ( this.getReplyUserCodeSecond()!=null && castOther.getReplyUserCodeSecond()!=null && this.getReplyUserCodeSecond().equals(castOther.getReplyUserCodeSecond()) ) )
 && ( (this.getReplyTimeSecond()==castOther.getReplyTimeSecond()) || ( this.getReplyTimeSecond()!=null && castOther.getReplyTimeSecond()!=null && this.getReplyTimeSecond().equals(castOther.getReplyTimeSecond()) ) )
 && ( (this.getReplyUserCodeThird()==castOther.getReplyUserCodeThird()) || ( this.getReplyUserCodeThird()!=null && castOther.getReplyUserCodeThird()!=null && this.getReplyUserCodeThird().equals(castOther.getReplyUserCodeThird()) ) )
 && ( (this.getReplyTimeThird()==castOther.getReplyTimeThird()) || ( this.getReplyTimeThird()!=null && castOther.getReplyTimeThird()!=null && this.getReplyTimeThird().equals(castOther.getReplyTimeThird()) ) )
 && ( (this.getReplyYesNo()==castOther.getReplyYesNo()) || ( this.getReplyYesNo()!=null && castOther.getReplyYesNo()!=null && this.getReplyYesNo().equals(castOther.getReplyYesNo()) ) )
 
 && ( (this.getFirstReplyAudit()==castOther.getFirstReplyAudit()) || ( this.getFirstReplyAudit()!=null && castOther.getFirstReplyAudit()!=null && this.getFirstReplyAudit().equals(castOther.getFirstReplyAudit()) ) )
 && ( (this.getFirseReplyPerson()==castOther.getFirseReplyPerson()) || ( this.getFirseReplyPerson()!=null && castOther.getFirseReplyPerson()!=null && this.getFirseReplyPerson().equals(castOther.getFirseReplyPerson()) ) )
 && ( (this.getFirstReplyTime()==castOther.getFirstReplyTime()) || ( this.getFirstReplyTime()!=null && castOther.getFirstReplyTime()!=null && this.getFirstReplyTime().equals(castOther.getFirstReplyTime()) ) )
 && ( (this.getSecondReplyAudit()==castOther.getSecondReplyAudit()) || ( this.getSecondReplyAudit()!=null && castOther.getSecondReplyAudit()!=null && this.getSecondReplyAudit().equals(castOther.getSecondReplyAudit()) ) )
 && ( (this.getSecondReplyPerson()==castOther.getSecondReplyPerson()) || ( this.getSecondReplyPerson()!=null && castOther.getSecondReplyPerson()!=null && this.getSecondReplyPerson().equals(castOther.getSecondReplyPerson()) ) )
 && ( (this.getSecondReplyTime()==castOther.getSecondReplyTime()) || ( this.getSecondReplyTime()!=null && castOther.getSecondReplyTime()!=null && this.getSecondReplyTime().equals(castOther.getSecondReplyTime()) ) )
 && ( (this.getThirdReplyAudit()==castOther.getThirdReplyAudit()) || ( this.getThirdReplyAudit()!=null && castOther.getThirdReplyAudit()!=null && this.getThirdReplyAudit().equals(castOther.getThirdReplyAudit()) ) )
 && ( (this.getThirdReplyPerson()==castOther.getThirdReplyPerson()) || ( this.getThirdReplyPerson()!=null && castOther.getThirdReplyPerson()!=null && this.getThirdReplyPerson().equals(castOther.getThirdReplyPerson()) ) )
 && ( (this.getThirdReplyTime()==castOther.getThirdReplyTime()) || ( this.getThirdReplyTime()!=null && castOther.getThirdReplyTime()!=null && this.getThirdReplyTime().equals(castOther.getThirdReplyTime()) ) )

 && ( (this.getUpdateFlag()==castOther.getUpdateFlag()) || ( this.getUpdateFlag()!=null && castOther.getUpdateFlag()!=null && this.getUpdateFlag().equals(castOther.getUpdateFlag()) ) )
 && ( (this.getInitialAuditTime()==castOther.getInitialAuditTime()) || ( this.getInitialAuditTime()!=null && castOther.getInitialAuditTime()!=null && this.getInitialAuditTime().equals(castOther.getInitialAuditTime()) ) )
 && ( (this.getInitialAuditUser()==castOther.getInitialAuditUser()) || ( this.getInitialAuditUser()!=null && castOther.getInitialAuditUser()!=null && this.getInitialAuditUser().equals(castOther.getInitialAuditUser()) ) );

   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getDemandId() == null ? 0 : this.getDemandId().hashCode() );
         result = 37 * result + ( getSubject() == null ? 0 : this.getSubject().hashCode() );
         result = 37 * result + ( getContent() == null ? 0 : this.getContent().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getReplyTime() == null ? 0 : this.getReplyTime().hashCode() );
         result = 37 * result + ( getReplyTontent() == null ? 0 : this.getReplyTontent().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getReplyUserCode() == null ? 0 : this.getReplyUserCode().hashCode() );
         result = 37 * result + ( getProposedAdoption() == null ? 0 : this.getProposedAdoption().hashCode() );
         
         result = 37 * result + ( getSuggestionAccept() == null ? 0 : this.getSuggestionAccept().hashCode() );
         result = 37 * result + ( getSuggestionSort() == null ? 0 : this.getSuggestionSort().hashCode() );
         result = 37 * result + ( getInitialAudit() == null ? 0 : this.getInitialAudit().hashCode() );
         result = 37 * result + ( getFeasibilityAudit() == null ? 0 : this.getFeasibilityAudit().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         result = 37 * result + ( getPhone() == null ? 0 : this.getPhone().hashCode() );
         result = 37 * result + ( getAddress() == null ? 0 : this.getAddress().hashCode() );
         result = 37 * result + ( getDemandsortId() == null ? 0 : this.getDemandsortId().hashCode() );
         result = 37 * result + ( getIntegration() == null ? 0 : this.getIntegration().hashCode() );
         result = 37 * result + ( getMicroMessage() == null ? 0 : this.getMicroMessage().hashCode() );
         result = 37 * result + ( getQq() == null ? 0 : this.getQq().hashCode() );
         result = 37 * result + ( getReplyContentSecond() == null ? 0 : this.getReplyContentSecond().hashCode() );
         result = 37 * result + ( getReplyContentThird() == null ? 0 : this.getReplyContentThird().hashCode() );          
         result = 37 * result + ( getReplyUserCodeSecond() == null ? 0 : this.getReplyUserCodeSecond().hashCode() );        
         result = 37 * result + ( getReplyTimeSecond() == null ? 0 : this.getReplyTimeSecond().hashCode() );        
         result = 37 * result + ( getReplyUserCodeThird() == null ? 0 : this.getReplyUserCodeThird().hashCode() );        
         result = 37 * result + ( getReplyTimeThird() == null ? 0 : this.getReplyTimeThird().hashCode() );        
         result = 37 * result + ( getReplyYesNo() == null ? 0 : this.getReplyYesNo().hashCode() );    
         
         result = 37 * result + ( getFirstReplyAudit() == null ? 0 : this.getFirstReplyAudit().hashCode() );        
         result = 37 * result + ( getFirseReplyPerson() == null ? 0 : this.getFirseReplyPerson().hashCode() );        
         result = 37 * result + ( getFirstReplyTime() == null ? 0 : this.getFirstReplyTime().hashCode() );        
         result = 37 * result + ( getSecondReplyAudit() == null ? 0 : this.getSecondReplyAudit().hashCode() );        
         result = 37 * result + ( getSecondReplyPerson() == null ? 0 : this.getSecondReplyPerson().hashCode() );        
         result = 37 * result + ( getSecondReplyTime() == null ? 0 : this.getSecondReplyTime().hashCode() );        
         result = 37 * result + ( getThirdReplyAudit() == null ? 0 : this.getThirdReplyAudit().hashCode() );        
         result = 37 * result + ( getThirdReplyPerson() == null ? 0 : this.getThirdReplyPerson().hashCode() );        
         result = 37 * result + ( getThirdReplyTime() == null ? 0 : this.getThirdReplyTime().hashCode() );    
         
         result = 37 * result + ( getUpdateFlag() == null ? 0 : this.getUpdateFlag().hashCode() ); 
         result = 37 * result + ( getInitialAuditTime() == null ? 0 : this.getInitialAuditTime().hashCode() ); 
         result = 37 * result + ( getInitialAuditUser() == null ? 0 : this.getInitialAuditUser().hashCode() );        




         return result;
   }   

}
