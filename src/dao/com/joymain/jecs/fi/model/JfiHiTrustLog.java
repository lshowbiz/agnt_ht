package com.joymain.jecs.fi.model;
// Generated 2010-9-8 18:00:06 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JFI_HI_TRUST_LOG"
 *     
 */

public class JfiHiTrustLog extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long logId;
    private String orderNo;
    private String orderDesc;
    private String retCode;
    private String currency;
    private String orderDate;
    private String orderStatus;
    private String approveAmount;
    private String authCode;
    private String authRrn;
    private String captureAmount;
    private String payBatchNum;
    private String captureDate;
    private String refundAmount;
    private String refundBatch;
    private String refundRrn;
    private String refundCode;
    private String refundDate;
    private String acquirer;
    private String cardType;
    private String url;
    private String ttype;
    private String eci;
    private String inc;
    private String referer;
    private String returnMsg;
    private Date createTime;
    private String pageType;


    // Constructors

    /** default constructor */
    public JfiHiTrustLog() {
    }

	/** minimal constructor */
    public JfiHiTrustLog(String inc, String referer, String returnMsg, Date createTime, String pageType) {
        this.inc = inc;
        this.referer = referer;
        this.returnMsg = returnMsg;
        this.createTime = createTime;
        this.pageType = pageType;
    }
    
    /** full constructor */
    public JfiHiTrustLog(String orderNo, String retCode, String currency, String orderDate, String orderStatus, String approveAmount, String authCode, String authRrn, String captureAmount, String payBatchNum, String captureDate, String refundAmount, String refundBatch, String refundRrn, String refundCode, String refundDate, String acquirer, String cardType, String url, String ttype, String eci, String inc, String referer, String returnMsg, Date createTime, String pageType) {
        this.orderNo = orderNo;
        this.retCode = retCode;
        this.currency = currency;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.approveAmount = approveAmount;
        this.authCode = authCode;
        this.authRrn = authRrn;
        this.captureAmount = captureAmount;
        this.payBatchNum = payBatchNum;
        this.captureDate = captureDate;
        this.refundAmount = refundAmount;
        this.refundBatch = refundBatch;
        this.refundRrn = refundRrn;
        this.refundCode = refundCode;
        this.refundDate = refundDate;
        this.acquirer = acquirer;
        this.cardType = cardType;
        this.url = url;
        this.ttype = ttype;
        this.eci = eci;
        this.inc = inc;
        this.referer = referer;
        this.returnMsg = returnMsg;
        this.createTime = createTime;
        this.pageType = pageType;
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
     *             column="ORDER_NO"
     *             length="4000"
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
     *             column="RET_CODE"
     *             length="4000"
     *         
     */

    public String getRetCode() {
        return this.retCode;
    }
    
    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="CURRENCY"
     *             length="4000"
     *         
     */

    public String getCurrency() {
        return this.currency;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    /**       
     *      *            @hibernate.property
     *             column="ORDER_DATE"
     *             length="4000"
     *         
     */

    public String getOrderDate() {
        return this.orderDate;
    }
    
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
    /**       
     *      *            @hibernate.property
     *             column="ORDER_STATUS"
     *             length="4000"
     *         
     */

    public String getOrderStatus() {
        return this.orderStatus;
    }
    
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    /**       
     *      *            @hibernate.property
     *             column="APPROVE_AMOUNT"
     *             length="4000"
     *         
     */

    public String getApproveAmount() {
        return this.approveAmount;
    }
    
    public void setApproveAmount(String approveAmount) {
        this.approveAmount = approveAmount;
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
     *             column="AUTH_RRN"
     *             length="4000"
     *         
     */

    public String getAuthRrn() {
        return this.authRrn;
    }
    
    public void setAuthRrn(String authRrn) {
        this.authRrn = authRrn;
    }
    /**       
     *      *            @hibernate.property
     *             column="CAPTURE_AMOUNT"
     *             length="4000"
     *         
     */

    public String getCaptureAmount() {
        return this.captureAmount;
    }
    
    public void setCaptureAmount(String captureAmount) {
        this.captureAmount = captureAmount;
    }
    /**       
     *      *            @hibernate.property
     *             column="PAY_BATCH_NUM"
     *             length="4000"
     *         
     */

    public String getPayBatchNum() {
        return this.payBatchNum;
    }
    
    public void setPayBatchNum(String payBatchNum) {
        this.payBatchNum = payBatchNum;
    }
    /**       
     *      *            @hibernate.property
     *             column="CAPTURE_DATE"
     *             length="4000"
     *         
     */

    public String getCaptureDate() {
        return this.captureDate;
    }
    
    public void setCaptureDate(String captureDate) {
        this.captureDate = captureDate;
    }
    /**       
     *      *            @hibernate.property
     *             column="REFUND_AMOUNT"
     *             length="4000"
     *         
     */

    public String getRefundAmount() {
        return this.refundAmount;
    }
    
    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
    }
    /**       
     *      *            @hibernate.property
     *             column="REFUND_BATCH"
     *             length="4000"
     *         
     */

    public String getRefundBatch() {
        return this.refundBatch;
    }
    
    public void setRefundBatch(String refundBatch) {
        this.refundBatch = refundBatch;
    }
    /**       
     *      *            @hibernate.property
     *             column="REFUND_RRN"
     *             length="4000"
     *         
     */

    public String getRefundRrn() {
        return this.refundRrn;
    }
    
    public void setRefundRrn(String refundRrn) {
        this.refundRrn = refundRrn;
    }
    /**       
     *      *            @hibernate.property
     *             column="REFUND_CODE"
     *             length="4000"
     *         
     */

    public String getRefundCode() {
        return this.refundCode;
    }
    
    public void setRefundCode(String refundCode) {
        this.refundCode = refundCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="REFUND_DATE"
     *             length="4000"
     *         
     */

    public String getRefundDate() {
        return this.refundDate;
    }
    
    public void setRefundDate(String refundDate) {
        this.refundDate = refundDate;
    }
    /**       
     *      *            @hibernate.property
     *             column="ACQUIRER"
     *             length="4000"
     *         
     */

    public String getAcquirer() {
        return this.acquirer;
    }
    
    public void setAcquirer(String acquirer) {
        this.acquirer = acquirer;
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
     *             column="URL"
     *             length="4000"
     *         
     */

    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    /**       
     *      *            @hibernate.property
     *             column="T_TYPE"
     *             length="4000"
     *         
     */

    public String getTtype() {
        return this.ttype;
    }
    
    public void setTtype(String ttype) {
        this.ttype = ttype;
    }
    /**       
     *      *            @hibernate.property
     *             column="ECI"
     *             length="4000"
     *         
     */

    public String getEci() {
        return this.eci;
    }
    
    public void setEci(String eci) {
        this.eci = eci;
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
     *             not-null="true"
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
     *             not-null="true"
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
     *             column="PAGE_TYPE"
     *             length="4000"
     *             not-null="true"
     *         
     */

    public String getPageType() {
        return this.pageType;
    }
    
    public void setPageType(String pageType) {
        this.pageType = pageType;
    }
    /**       
     *      *            @hibernate.property
     *             column="ORDER_DESC"
     *             length="2"
     *         
     */

    public String getOrderDesc() {
    	return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
    	this.orderDesc = orderDesc;
    }  
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("orderNo").append("='").append(getOrderNo()).append("' ");			
      buffer.append("retCode").append("='").append(getRetCode()).append("' ");			
      buffer.append("currency").append("='").append(getCurrency()).append("' ");			
      buffer.append("orderDate").append("='").append(getOrderDate()).append("' ");			
      buffer.append("orderStatus").append("='").append(getOrderStatus()).append("' ");			
      buffer.append("approveAmount").append("='").append(getApproveAmount()).append("' ");			
      buffer.append("authCode").append("='").append(getAuthCode()).append("' ");			
      buffer.append("authRrn").append("='").append(getAuthRrn()).append("' ");			
      buffer.append("captureAmount").append("='").append(getCaptureAmount()).append("' ");			
      buffer.append("payBatchNum").append("='").append(getPayBatchNum()).append("' ");			
      buffer.append("captureDate").append("='").append(getCaptureDate()).append("' ");			
      buffer.append("refundAmount").append("='").append(getRefundAmount()).append("' ");			
      buffer.append("refundBatch").append("='").append(getRefundBatch()).append("' ");			
      buffer.append("refundRrn").append("='").append(getRefundRrn()).append("' ");			
      buffer.append("refundCode").append("='").append(getRefundCode()).append("' ");			
      buffer.append("refundDate").append("='").append(getRefundDate()).append("' ");			
      buffer.append("acquirer").append("='").append(getAcquirer()).append("' ");			
      buffer.append("cardType").append("='").append(getCardType()).append("' ");			
      buffer.append("url").append("='").append(getUrl()).append("' ");			
      buffer.append("ttype").append("='").append(getTtype()).append("' ");			
      buffer.append("eci").append("='").append(getEci()).append("' ");			
      buffer.append("inc").append("='").append(getInc()).append("' ");			
      buffer.append("referer").append("='").append(getReferer()).append("' ");			
      buffer.append("returnMsg").append("='").append(getReturnMsg()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("pageType").append("='").append(getPageType()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JfiHiTrustLog) ) return false;
		 JfiHiTrustLog castOther = ( JfiHiTrustLog ) other; 
         
		 return ( (this.getLogId()==castOther.getLogId()) || ( this.getLogId()!=null && castOther.getLogId()!=null && this.getLogId().equals(castOther.getLogId()) ) )
 && ( (this.getOrderNo()==castOther.getOrderNo()) || ( this.getOrderNo()!=null && castOther.getOrderNo()!=null && this.getOrderNo().equals(castOther.getOrderNo()) ) )
 && ( (this.getRetCode()==castOther.getRetCode()) || ( this.getRetCode()!=null && castOther.getRetCode()!=null && this.getRetCode().equals(castOther.getRetCode()) ) )
 && ( (this.getCurrency()==castOther.getCurrency()) || ( this.getCurrency()!=null && castOther.getCurrency()!=null && this.getCurrency().equals(castOther.getCurrency()) ) )
 && ( (this.getOrderDate()==castOther.getOrderDate()) || ( this.getOrderDate()!=null && castOther.getOrderDate()!=null && this.getOrderDate().equals(castOther.getOrderDate()) ) )
 && ( (this.getOrderStatus()==castOther.getOrderStatus()) || ( this.getOrderStatus()!=null && castOther.getOrderStatus()!=null && this.getOrderStatus().equals(castOther.getOrderStatus()) ) )
 && ( (this.getApproveAmount()==castOther.getApproveAmount()) || ( this.getApproveAmount()!=null && castOther.getApproveAmount()!=null && this.getApproveAmount().equals(castOther.getApproveAmount()) ) )
 && ( (this.getAuthCode()==castOther.getAuthCode()) || ( this.getAuthCode()!=null && castOther.getAuthCode()!=null && this.getAuthCode().equals(castOther.getAuthCode()) ) )
 && ( (this.getAuthRrn()==castOther.getAuthRrn()) || ( this.getAuthRrn()!=null && castOther.getAuthRrn()!=null && this.getAuthRrn().equals(castOther.getAuthRrn()) ) )
 && ( (this.getCaptureAmount()==castOther.getCaptureAmount()) || ( this.getCaptureAmount()!=null && castOther.getCaptureAmount()!=null && this.getCaptureAmount().equals(castOther.getCaptureAmount()) ) )
 && ( (this.getPayBatchNum()==castOther.getPayBatchNum()) || ( this.getPayBatchNum()!=null && castOther.getPayBatchNum()!=null && this.getPayBatchNum().equals(castOther.getPayBatchNum()) ) )
 && ( (this.getCaptureDate()==castOther.getCaptureDate()) || ( this.getCaptureDate()!=null && castOther.getCaptureDate()!=null && this.getCaptureDate().equals(castOther.getCaptureDate()) ) )
 && ( (this.getRefundAmount()==castOther.getRefundAmount()) || ( this.getRefundAmount()!=null && castOther.getRefundAmount()!=null && this.getRefundAmount().equals(castOther.getRefundAmount()) ) )
 && ( (this.getRefundBatch()==castOther.getRefundBatch()) || ( this.getRefundBatch()!=null && castOther.getRefundBatch()!=null && this.getRefundBatch().equals(castOther.getRefundBatch()) ) )
 && ( (this.getRefundRrn()==castOther.getRefundRrn()) || ( this.getRefundRrn()!=null && castOther.getRefundRrn()!=null && this.getRefundRrn().equals(castOther.getRefundRrn()) ) )
 && ( (this.getRefundCode()==castOther.getRefundCode()) || ( this.getRefundCode()!=null && castOther.getRefundCode()!=null && this.getRefundCode().equals(castOther.getRefundCode()) ) )
 && ( (this.getRefundDate()==castOther.getRefundDate()) || ( this.getRefundDate()!=null && castOther.getRefundDate()!=null && this.getRefundDate().equals(castOther.getRefundDate()) ) )
 && ( (this.getAcquirer()==castOther.getAcquirer()) || ( this.getAcquirer()!=null && castOther.getAcquirer()!=null && this.getAcquirer().equals(castOther.getAcquirer()) ) )
 && ( (this.getCardType()==castOther.getCardType()) || ( this.getCardType()!=null && castOther.getCardType()!=null && this.getCardType().equals(castOther.getCardType()) ) )
 && ( (this.getUrl()==castOther.getUrl()) || ( this.getUrl()!=null && castOther.getUrl()!=null && this.getUrl().equals(castOther.getUrl()) ) )
 && ( (this.getTtype()==castOther.getTtype()) || ( this.getTtype()!=null && castOther.getTtype()!=null && this.getTtype().equals(castOther.getTtype()) ) )
 && ( (this.getEci()==castOther.getEci()) || ( this.getEci()!=null && castOther.getEci()!=null && this.getEci().equals(castOther.getEci()) ) )
 && ( (this.getInc()==castOther.getInc()) || ( this.getInc()!=null && castOther.getInc()!=null && this.getInc().equals(castOther.getInc()) ) )
 && ( (this.getReferer()==castOther.getReferer()) || ( this.getReferer()!=null && castOther.getReferer()!=null && this.getReferer().equals(castOther.getReferer()) ) )
 && ( (this.getReturnMsg()==castOther.getReturnMsg()) || ( this.getReturnMsg()!=null && castOther.getReturnMsg()!=null && this.getReturnMsg().equals(castOther.getReturnMsg()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getPageType()==castOther.getPageType()) || ( this.getPageType()!=null && castOther.getPageType()!=null && this.getPageType().equals(castOther.getPageType()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getLogId() == null ? 0 : this.getLogId().hashCode() );
         result = 37 * result + ( getOrderNo() == null ? 0 : this.getOrderNo().hashCode() );
         result = 37 * result + ( getRetCode() == null ? 0 : this.getRetCode().hashCode() );
         result = 37 * result + ( getCurrency() == null ? 0 : this.getCurrency().hashCode() );
         result = 37 * result + ( getOrderDate() == null ? 0 : this.getOrderDate().hashCode() );
         result = 37 * result + ( getOrderStatus() == null ? 0 : this.getOrderStatus().hashCode() );
         result = 37 * result + ( getApproveAmount() == null ? 0 : this.getApproveAmount().hashCode() );
         result = 37 * result + ( getAuthCode() == null ? 0 : this.getAuthCode().hashCode() );
         result = 37 * result + ( getAuthRrn() == null ? 0 : this.getAuthRrn().hashCode() );
         result = 37 * result + ( getCaptureAmount() == null ? 0 : this.getCaptureAmount().hashCode() );
         result = 37 * result + ( getPayBatchNum() == null ? 0 : this.getPayBatchNum().hashCode() );
         result = 37 * result + ( getCaptureDate() == null ? 0 : this.getCaptureDate().hashCode() );
         result = 37 * result + ( getRefundAmount() == null ? 0 : this.getRefundAmount().hashCode() );
         result = 37 * result + ( getRefundBatch() == null ? 0 : this.getRefundBatch().hashCode() );
         result = 37 * result + ( getRefundRrn() == null ? 0 : this.getRefundRrn().hashCode() );
         result = 37 * result + ( getRefundCode() == null ? 0 : this.getRefundCode().hashCode() );
         result = 37 * result + ( getRefundDate() == null ? 0 : this.getRefundDate().hashCode() );
         result = 37 * result + ( getAcquirer() == null ? 0 : this.getAcquirer().hashCode() );
         result = 37 * result + ( getCardType() == null ? 0 : this.getCardType().hashCode() );
         result = 37 * result + ( getUrl() == null ? 0 : this.getUrl().hashCode() );
         result = 37 * result + ( getTtype() == null ? 0 : this.getTtype().hashCode() );
         result = 37 * result + ( getEci() == null ? 0 : this.getEci().hashCode() );
         result = 37 * result + ( getInc() == null ? 0 : this.getInc().hashCode() );
         result = 37 * result + ( getReferer() == null ? 0 : this.getReferer().hashCode() );
         result = 37 * result + ( getReturnMsg() == null ? 0 : this.getReturnMsg().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getPageType() == null ? 0 : this.getPageType().hashCode() );
         return result;
   } 





}
