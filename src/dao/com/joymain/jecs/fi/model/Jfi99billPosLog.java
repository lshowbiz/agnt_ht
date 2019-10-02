package com.joymain.jecs.fi.model;
// Generated 2010-1-5 5:26:38 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JFI_99BILL_POS_LOG"
 *     
 */

public class Jfi99billPosLog extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long logId;
    private String companyCode;
    private String userCode;
    private String actionType;
    private String processFlag;
    private String txnType;
    private String amt;
    private String externalTraceNo;
    private String terminalOperId;
    private String authCode;
    private String rrn;
    private String txnTime;
    private String shortPan;
    private String responseCode;
    private String responseMessage;
    private String cardType;
    private String issuerId;
    private String issuerIdView;
    private String signature;
    private String inc;
    private String referer;
    private String returnMsg;
    private Date createTime;
    private String orgTxnType;
    private String orgExternalTraceNo;
    private String url;
    private String actionNo;
    private String actionId;
    private String isBackService;


    // Constructors

	/** default constructor */
    public Jfi99billPosLog() {
    }

	/** minimal constructor */
    public Jfi99billPosLog(String companyCode, String userCode, String actionType, String inc, String returnMsg) {
        this.companyCode = companyCode;
        this.userCode = userCode;
        this.actionType = actionType;
        this.inc = inc;
        this.returnMsg = returnMsg;
    }
    
    /** full constructor */
    public Jfi99billPosLog(String companyCode, String userCode, String actionType, String processFlag, String txnType, String amt, String externalTraceNo, String terminalOperId, String authCode, String rrn, String txnTime, String shortPan, String responseCode, String responseMessage, String cardType, String issuerId, String issuerIdView, String signature, String inc, String referer, String returnMsg, Date createTime) {
        this.companyCode = companyCode;
        this.userCode = userCode;
        this.actionType = actionType;
        this.processFlag = processFlag;
        this.txnType = txnType;
        this.amt = amt;
        this.externalTraceNo = externalTraceNo;
        this.terminalOperId = terminalOperId;
        this.authCode = authCode;
        this.rrn = rrn;
        this.txnTime = txnTime;
        this.shortPan = shortPan;
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.cardType = cardType;
        this.issuerId = issuerId;
        this.issuerIdView = issuerIdView;
        this.signature = signature;
        this.inc = inc;
        this.referer = referer;
        this.returnMsg = returnMsg;
        this.createTime = createTime;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="LOG_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_FI"
     *         
     */

    public Long getLogId() {
        return this.logId;
    }
    
    public void setLogId(Long logId) {
        this.logId = logId;
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
     *             column="USER_CODE"
     *             length="20"
     *             not-null="true"
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
     *             column="ACTION_TYPE"
     *             length="1"
     *             not-null="true"
     *         
     */

    public String getActionType() {
        return this.actionType;
    }
    
    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    /**       
     *      *            @hibernate.property
     *             column="ACTION_NO"
     *             length="4000"
     *             not-null="true"
     *         
     */
	public String getActionNo() {
		return actionNo;
	}

	public void setActionNo(String actionNo) {
		this.actionNo = actionNo;
	}

    /**       
     *      *            @hibernate.property
     *             column="ACTION_ID"
     *             length="4000"
     *             not-null="true"
     *         
     */
	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
    /**       
     *      *            @hibernate.property
     *             column="PROCESS_FLAG"
     *             length="4000"
     *         
     */

    public String getProcessFlag() {
        return this.processFlag;
    }
    
    public void setProcessFlag(String processFlag) {
        this.processFlag = processFlag;
    }
    /**       
     *      *            @hibernate.property
     *             column="TXN_TYPE"
     *             length="4000"
     *         
     */

    public String getTxnType() {
        return this.txnType;
    }
    
    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }
    /**       
     *      *            @hibernate.property
     *             column="AMT"
     *             length="4000"
     *         
     */

    public String getAmt() {
        return this.amt;
    }
    
    public void setAmt(String amt) {
        this.amt = amt;
    }
    /**       
     *      *            @hibernate.property
     *             column="EXTERNAL_TRACE_NO"
     *             length="4000"
     *         
     */

    public String getExternalTraceNo() {
        return this.externalTraceNo;
    }
    
    public void setExternalTraceNo(String externalTraceNo) {
        this.externalTraceNo = externalTraceNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="TERMINAL_OPER_ID"
     *             length="4000"
     *         
     */

    public String getTerminalOperId() {
        return this.terminalOperId;
    }
    
    public void setTerminalOperId(String terminalOperId) {
        this.terminalOperId = terminalOperId;
    }
    /**       
     *      *            @hibernate.property
     *             column="AUTH_CODE"
     *             length="4000"
     *         
     */

    public String getAuthCode() {
        return this.authCode;
    }
    
    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="RRN"
     *             length="4000"
     *         
     */

    public String getRrn() {
        return this.rrn;
    }
    
    public void setRrn(String rrn) {
        this.rrn = rrn;
    }
    /**       
     *      *            @hibernate.property
     *             column="TXN_TIME"
     *             length="4000"
     *         
     */

    public String getTxnTime() {
        return this.txnTime;
    }
    
    public void setTxnTime(String txnTime) {
        this.txnTime = txnTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="SHORT_PAN"
     *             length="4000"
     *         
     */

    public String getShortPan() {
        return this.shortPan;
    }
    
    public void setShortPan(String shortPan) {
        this.shortPan = shortPan;
    }
    /**       
     *      *            @hibernate.property
     *             column="RESPONSE_CODE"
     *             length="4000"
     *         
     */

    public String getResponseCode() {
        return this.responseCode;
    }
    
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="RESPONSE_MESSAGE"
     *             length="4000"
     *         
     */

    public String getResponseMessage() {
        return this.responseMessage;
    }
    
    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
    /**       
     *      *            @hibernate.property
     *             column="CARD_TYPE"
     *             length="4000"
     *         
     */

    public String getCardType() {
        return this.cardType;
    }
    
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
    /**       
     *      *            @hibernate.property
     *             column="ISSUER_ID"
     *             length="4000"
     *         
     */

    public String getIssuerId() {
        return this.issuerId;
    }
    
    public void setIssuerId(String issuerId) {
        this.issuerId = issuerId;
    }
    /**       
     *      *            @hibernate.property
     *             column="ISSUER_ID_VIEW"
     *             length="4000"
     *         
     */

    public String getIssuerIdView() {
        return this.issuerIdView;
    }
    
    public void setIssuerIdView(String issuerIdView) {
        this.issuerIdView = issuerIdView;
    }
    /**       
     *      *            @hibernate.property
     *             column="SIGNATURE"
     *             length="4000"
     *         
     */

    public String getSignature() {
        return this.signature;
    }
    
    public void setSignature(String signature) {
        this.signature = signature;
    }
    /**       
     *      *            @hibernate.property
     *             column="INC"
     *             length="1"
     *             not-null="true"
     *         
     */

    public String getInc() {
        return this.inc;
    }
    
    public void setInc(String inc) {
        this.inc = inc;
    }
    /**       
     *      *            @hibernate.property
     *             column="REFERER"
     *             length="4000"
     *         
     */

    public String getReferer() {
        return this.referer;
    }
    
    public void setReferer(String referer) {
        this.referer = referer;
    }
    /**       
     *      *            @hibernate.property
     *             column="RETURN_MSG"
     *             length="2"
     *         
     */

    public String getReturnMsg() {
        return this.returnMsg;
    }
    
    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
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
     *             column="ORG_TXN_TYPE"
     *             length="4000"
     *         
     */
    public String getOrgTxnType() {
		return orgTxnType;
	}

	public void setOrgTxnType(String orgTxnType) {
		this.orgTxnType = orgTxnType;
	}

    /**       
     *      *            @hibernate.property
     *             column="ORG_EXTERNAL_TRACE_NO"
     *             length="4000"
     *         
     */
	public String getOrgExternalTraceNo() {
		return orgExternalTraceNo;
	}

	public void setOrgExternalTraceNo(String orgExternalTraceNo) {
		this.orgExternalTraceNo = orgExternalTraceNo;
	}

    /**       
     *      *            @hibernate.property
     *             column="URL"
     *             length="4000"
     *         
     */
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

    /**       
     *      *            @hibernate.property
     *             column="IS_BACK_SERVICE"
     *             length="4000"
     *         
     */
	public String getIsBackService() {
		return isBackService;
	}

	public void setIsBackService(String isBackService) {
		this.isBackService = isBackService;
	}
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("actionType").append("='").append(getActionType()).append("' ");			
      buffer.append("processFlag").append("='").append(getProcessFlag()).append("' ");			
      buffer.append("txnType").append("='").append(getTxnType()).append("' ");			
      buffer.append("amt").append("='").append(getAmt()).append("' ");			
      buffer.append("externalTraceNo").append("='").append(getExternalTraceNo()).append("' ");			
      buffer.append("terminalOperId").append("='").append(getTerminalOperId()).append("' ");			
      buffer.append("authCode").append("='").append(getAuthCode()).append("' ");			
      buffer.append("rrn").append("='").append(getRrn()).append("' ");			
      buffer.append("txnTime").append("='").append(getTxnTime()).append("' ");			
      buffer.append("shortPan").append("='").append(getShortPan()).append("' ");			
      buffer.append("responseCode").append("='").append(getResponseCode()).append("' ");			
      buffer.append("responseMessage").append("='").append(getResponseMessage()).append("' ");			
      buffer.append("cardType").append("='").append(getCardType()).append("' ");			
      buffer.append("issuerId").append("='").append(getIssuerId()).append("' ");			
      buffer.append("issuerIdView").append("='").append(getIssuerIdView()).append("' ");			
      buffer.append("signature").append("='").append(getSignature()).append("' ");			
      buffer.append("inc").append("='").append(getInc()).append("' ");			
      buffer.append("referer").append("='").append(getReferer()).append("' ");			
      buffer.append("returnMsg").append("='").append(getReturnMsg()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Jfi99billPosLog) ) return false;
		 Jfi99billPosLog castOther = ( Jfi99billPosLog ) other; 
         
		 return (this.getLogId()==castOther.getLogId())
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getActionType()==castOther.getActionType()) || ( this.getActionType()!=null && castOther.getActionType()!=null && this.getActionType().equals(castOther.getActionType()) ) )
 && ( (this.getProcessFlag()==castOther.getProcessFlag()) || ( this.getProcessFlag()!=null && castOther.getProcessFlag()!=null && this.getProcessFlag().equals(castOther.getProcessFlag()) ) )
 && ( (this.getTxnType()==castOther.getTxnType()) || ( this.getTxnType()!=null && castOther.getTxnType()!=null && this.getTxnType().equals(castOther.getTxnType()) ) )
 && ( (this.getAmt()==castOther.getAmt()) || ( this.getAmt()!=null && castOther.getAmt()!=null && this.getAmt().equals(castOther.getAmt()) ) )
 && ( (this.getExternalTraceNo()==castOther.getExternalTraceNo()) || ( this.getExternalTraceNo()!=null && castOther.getExternalTraceNo()!=null && this.getExternalTraceNo().equals(castOther.getExternalTraceNo()) ) )
 && ( (this.getTerminalOperId()==castOther.getTerminalOperId()) || ( this.getTerminalOperId()!=null && castOther.getTerminalOperId()!=null && this.getTerminalOperId().equals(castOther.getTerminalOperId()) ) )
 && ( (this.getAuthCode()==castOther.getAuthCode()) || ( this.getAuthCode()!=null && castOther.getAuthCode()!=null && this.getAuthCode().equals(castOther.getAuthCode()) ) )
 && ( (this.getRrn()==castOther.getRrn()) || ( this.getRrn()!=null && castOther.getRrn()!=null && this.getRrn().equals(castOther.getRrn()) ) )
 && ( (this.getTxnTime()==castOther.getTxnTime()) || ( this.getTxnTime()!=null && castOther.getTxnTime()!=null && this.getTxnTime().equals(castOther.getTxnTime()) ) )
 && ( (this.getShortPan()==castOther.getShortPan()) || ( this.getShortPan()!=null && castOther.getShortPan()!=null && this.getShortPan().equals(castOther.getShortPan()) ) )
 && ( (this.getResponseCode()==castOther.getResponseCode()) || ( this.getResponseCode()!=null && castOther.getResponseCode()!=null && this.getResponseCode().equals(castOther.getResponseCode()) ) )
 && ( (this.getResponseMessage()==castOther.getResponseMessage()) || ( this.getResponseMessage()!=null && castOther.getResponseMessage()!=null && this.getResponseMessage().equals(castOther.getResponseMessage()) ) )
 && ( (this.getCardType()==castOther.getCardType()) || ( this.getCardType()!=null && castOther.getCardType()!=null && this.getCardType().equals(castOther.getCardType()) ) )
 && ( (this.getIssuerId()==castOther.getIssuerId()) || ( this.getIssuerId()!=null && castOther.getIssuerId()!=null && this.getIssuerId().equals(castOther.getIssuerId()) ) )
 && ( (this.getIssuerIdView()==castOther.getIssuerIdView()) || ( this.getIssuerIdView()!=null && castOther.getIssuerIdView()!=null && this.getIssuerIdView().equals(castOther.getIssuerIdView()) ) )
 && ( (this.getSignature()==castOther.getSignature()) || ( this.getSignature()!=null && castOther.getSignature()!=null && this.getSignature().equals(castOther.getSignature()) ) )
 && ( (this.getInc()==castOther.getInc()) || ( this.getInc()!=null && castOther.getInc()!=null && this.getInc().equals(castOther.getInc()) ) )
 && ( (this.getReferer()==castOther.getReferer()) || ( this.getReferer()!=null && castOther.getReferer()!=null && this.getReferer().equals(castOther.getReferer()) ) )
 && ( (this.getReturnMsg()==castOther.getReturnMsg()) || ( this.getReturnMsg()!=null && castOther.getReturnMsg()!=null && this.getReturnMsg().equals(castOther.getReturnMsg()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getLogId() == null ? 0 : this.getLogId().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getActionType() == null ? 0 : this.getActionType().hashCode() );
         result = 37 * result + ( getProcessFlag() == null ? 0 : this.getProcessFlag().hashCode() );
         result = 37 * result + ( getTxnType() == null ? 0 : this.getTxnType().hashCode() );
         result = 37 * result + ( getAmt() == null ? 0 : this.getAmt().hashCode() );
         result = 37 * result + ( getExternalTraceNo() == null ? 0 : this.getExternalTraceNo().hashCode() );
         result = 37 * result + ( getTerminalOperId() == null ? 0 : this.getTerminalOperId().hashCode() );
         result = 37 * result + ( getAuthCode() == null ? 0 : this.getAuthCode().hashCode() );
         result = 37 * result + ( getRrn() == null ? 0 : this.getRrn().hashCode() );
         result = 37 * result + ( getTxnTime() == null ? 0 : this.getTxnTime().hashCode() );
         result = 37 * result + ( getShortPan() == null ? 0 : this.getShortPan().hashCode() );
         result = 37 * result + ( getResponseCode() == null ? 0 : this.getResponseCode().hashCode() );
         result = 37 * result + ( getResponseMessage() == null ? 0 : this.getResponseMessage().hashCode() );
         result = 37 * result + ( getCardType() == null ? 0 : this.getCardType().hashCode() );
         result = 37 * result + ( getIssuerId() == null ? 0 : this.getIssuerId().hashCode() );
         result = 37 * result + ( getIssuerIdView() == null ? 0 : this.getIssuerIdView().hashCode() );
         result = 37 * result + ( getSignature() == null ? 0 : this.getSignature().hashCode() );
         result = 37 * result + ( getInc() == null ? 0 : this.getInc().hashCode() );
         result = 37 * result + ( getReferer() == null ? 0 : this.getReferer().hashCode() );
         result = 37 * result + ( getReturnMsg() == null ? 0 : this.getReturnMsg().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         return result;
   }   





}
