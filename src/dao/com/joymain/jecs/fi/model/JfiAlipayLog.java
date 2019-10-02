package com.joymain.jecs.fi.model;
// Generated 2010-8-5 12:21:44 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JFI_ALIPAY_LOG"
 *     
 */

public class JfiAlipayLog extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long logId;
    private String companyCode;
    private String userCode;
    private String isSuccess;
    private String sign;
    private String signType;
    private String exterface;
    private String notifyTime;
    private String notifyId;
    private String notifyType;
    private String tradeNo;
    private String paymentType;
    private String outTradeNo;
    private String body;
    private String subject;
    private String totalFee;
    private String buyerEmail;
    private String buyerId;
    private String sellerEmail;
    private String sellerId;
    private String tradeStatus;
    private String extraCommonParam;
    private String bankSeqNo;
    private String inc;
    private String referer;
    private String returnMsg;
    private String url;
    private String ip;
    private Date createTime;
    private String discount;
    private String price;
    private String isTotalFeeAdjust;
    private String gmtCreate;
    private String gmtPayment;
    private String gmtClose;
    private String gmtRefund;
    private String refundTrade;
    private String useCoupon;
    private String quantity;


    // Constructors

    /** default constructor */
    public JfiAlipayLog() {
    }

    
    /** full constructor */
    public JfiAlipayLog(String companyCode, String userCode, String isSuccess, String sign, String signType, String exterface, String notifyTime, String notifyId, String notifyType, String tradeNo, String paymentType, String outTradeNo, String body, String subject, String totalFee, String buyerEmail, String buyerId, String sellerEmail, String sellerId, String tradeStatus, String extraCommonParam, String bankSeqNo, String inc, String referer, String returnMsg, Date createTime) {
        this.companyCode = companyCode;
        this.userCode = userCode;
        this.isSuccess = isSuccess;
        this.sign = sign;
        this.signType = signType;
        this.exterface = exterface;
        this.notifyTime = notifyTime;
        this.notifyId = notifyId;
        this.notifyType = notifyType;
        this.tradeNo = tradeNo;
        this.paymentType = paymentType;
        this.outTradeNo = outTradeNo;
        this.body = body;
        this.subject = subject;
        this.totalFee = totalFee;
        this.buyerEmail = buyerEmail;
        this.buyerId = buyerId;
        this.sellerEmail = sellerEmail;
        this.sellerId = sellerId;
        this.tradeStatus = tradeStatus;
        this.extraCommonParam = extraCommonParam;
        this.bankSeqNo = bankSeqNo;
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
     *             column="IS_SUCCESS"
     *             length="4000"
     *         
     */

    public String getIsSuccess() {
        return this.isSuccess;
    }
    
    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }
    /**       
     *      *            @hibernate.property
     *             column="SIGN"
     *             length="4000"
     *         
     */

    public String getSign() {
        return this.sign;
    }
    
    public void setSign(String sign) {
        this.sign = sign;
    }
    /**       
     *      *            @hibernate.property
     *             column="SIGN_TYPE"
     *             length="4000"
     *         
     */

    public String getSignType() {
        return this.signType;
    }
    
    public void setSignType(String signType) {
        this.signType = signType;
    }
    /**       
     *      *            @hibernate.property
     *             column="EXTERFACE"
     *             length="4000"
     *         
     */

    public String getExterface() {
        return this.exterface;
    }
    
    public void setExterface(String exterface) {
        this.exterface = exterface;
    }
    /**       
     *      *            @hibernate.property
     *             column="NOTIFY_TIME"
     *             length="4000"
     *         
     */

    public String getNotifyTime() {
        return this.notifyTime;
    }
    
    public void setNotifyTime(String notifyTime) {
        this.notifyTime = notifyTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="NOTIFY_ID"
     *             length="4000"
     *         
     */

    public String getNotifyId() {
        return this.notifyId;
    }
    
    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }
    /**       
     *      *            @hibernate.property
     *             column="NOTIFY_TYPE"
     *             length="4000"
     *         
     */

    public String getNotifyType() {
        return this.notifyType;
    }
    
    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }
    /**       
     *      *            @hibernate.property
     *             column="TRADE_NO"
     *             length="4000"
     *         
     */

    public String getTradeNo() {
        return this.tradeNo;
    }
    
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="PAYMENT_TYPE"
     *             length="4000"
     *         
     */

    public String getPaymentType() {
        return this.paymentType;
    }
    
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
    /**       
     *      *            @hibernate.property
     *             column="OUT_TRADE_NO"
     *             length="4000"
     *         
     */

    public String getOutTradeNo() {
        return this.outTradeNo;
    }
    
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="BODY"
     *             length="4000"
     *         
     */

    public String getBody() {
        return this.body;
    }
    
    public void setBody(String body) {
        this.body = body;
    }
    /**       
     *      *            @hibernate.property
     *             column="SUBJECT"
     *             length="4000"
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
     *             column="TOTAL_FEE"
     *             length="4000"
     *         
     */

    public String getTotalFee() {
        return this.totalFee;
    }
    
    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }
    /**       
     *      *            @hibernate.property
     *             column="BUYER_EMAIL"
     *             length="4000"
     *         
     */

    public String getBuyerEmail() {
        return this.buyerEmail;
    }
    
    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }
    /**       
     *      *            @hibernate.property
     *             column="BUYER_ID"
     *             length="4000"
     *         
     */

    public String getBuyerId() {
        return this.buyerId;
    }
    
    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }
    /**       
     *      *            @hibernate.property
     *             column="SELLER_EMAIL"
     *             length="4000"
     *         
     */

    public String getSellerEmail() {
        return this.sellerEmail;
    }
    
    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }
    /**       
     *      *            @hibernate.property
     *             column="SELLER_ID"
     *             length="4000"
     *         
     */

    public String getSellerId() {
        return this.sellerId;
    }
    
    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }
    /**       
     *      *            @hibernate.property
     *             column="TRADE_STATUS"
     *             length="4000"
     *         
     */

    public String getTradeStatus() {
        return this.tradeStatus;
    }
    
    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }
    /**       
     *      *            @hibernate.property
     *             column="EXTRA_COMMON_PARAM"
     *             length="4000"
     *         
     */

    public String getExtraCommonParam() {
        return this.extraCommonParam;
    }
    
    public void setExtraCommonParam(String extraCommonParam) {
        this.extraCommonParam = extraCommonParam;
    }
    /**       
     *      *            @hibernate.property
     *             column="BANK_SEQ_NO"
     *             length="4000"
     *         
     */

    public String getBankSeqNo() {
        return this.bankSeqNo;
    }
    
    public void setBankSeqNo(String bankSeqNo) {
        this.bankSeqNo = bankSeqNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="INC"
     *             length="1"
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
     *             column="IP"
     *             length="4000"
     *         
     */
    public String getIp() {
    	return ip;
    }


    public void setIp(String ip) {
    	this.ip = ip;
    }

    /**       
     *      *            @hibernate.property
     *             column="DISCOUNT"
     *             length="4000"
     *         
     */
    public String getDiscount() {
    	return discount;
    }


    public void setDiscount(String discount) {
    	this.discount = discount;
    }

    /**       
     *      *            @hibernate.property
     *             column="GMT_CLOSE"
     *             length="4000"
     *         
     */
    public String getGmtClose() {
    	return gmtClose;
    }


    public void setGmtClose(String gmtClose) {
    	this.gmtClose = gmtClose;
    }

    /**       
     *      *            @hibernate.property
     *             column="GMT_CREATE"
     *             length="4000"
     *         
     */
    public String getGmtCreate() {
    	return gmtCreate;
    }


    public void setGmtCreate(String gmtCreate) {
    	this.gmtCreate = gmtCreate;
    }

    /**       
     *      *            @hibernate.property
     *             column="GMT_PAYMENT"
     *             length="4000"
     *         
     */
    public String getGmtPayment() {
    	return gmtPayment;
    }


    public void setGmtPayment(String gmtPayment) {
    	this.gmtPayment = gmtPayment;
    }

    /**       
     *      *            @hibernate.property
     *             column="GMT_REFUND"
     *             length="4000"
     *         
     */
    public String getGmtRefund() {
    	return gmtRefund;
    }


    public void setGmtRefund(String gmtRefund) {
    	this.gmtRefund = gmtRefund;
    }

    /**       
     *      *            @hibernate.property
     *             column="IS_TOTAL_FEE_ADJUST"
     *             length="4000"
     *         
     */
    public String getIsTotalFeeAdjust() {
    	return isTotalFeeAdjust;
    }


    public void setIsTotalFeeAdjust(String isTotalFeeAdjust) {
    	this.isTotalFeeAdjust = isTotalFeeAdjust;
    }

    /**       
     *      *            @hibernate.property
     *             column="PRICE"
     *             length="4000"
     *         
     */
    public String getPrice() {
    	return price;
    }


    public void setPrice(String price) {
    	this.price = price;
    }

    /**       
     *      *            @hibernate.property
     *             column="USE_COUPON"
     *             length="4000"
     *         
     */
    public String getUseCoupon() {
    	return useCoupon;
    }

    public void setUseCoupon(String useCoupon) {
    	this.useCoupon = useCoupon;
    }
    
    /**       
     *      *            @hibernate.property
     *             column="QUANTITY"
     *             length="4000"
     *         
     */
    public String getQuantity() {
    	return quantity;
    }


    public void setQuantity(String quantity) {
    	this.quantity = quantity;
    }

    /**       
     *      *            @hibernate.property
     *             column="REFUND_TRADE"
     *             length="4000"
     *         
     */
    public String getRefundTrade() {
    	return refundTrade;
    }


    public void setRefundTrade(String refundTrade) {
    	this.refundTrade = refundTrade;
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
      buffer.append("isSuccess").append("='").append(getIsSuccess()).append("' ");			
      buffer.append("sign").append("='").append(getSign()).append("' ");			
      buffer.append("signType").append("='").append(getSignType()).append("' ");			
      buffer.append("exterface").append("='").append(getExterface()).append("' ");			
      buffer.append("notifyTime").append("='").append(getNotifyTime()).append("' ");			
      buffer.append("notifyId").append("='").append(getNotifyId()).append("' ");			
      buffer.append("notifyType").append("='").append(getNotifyType()).append("' ");			
      buffer.append("tradeNo").append("='").append(getTradeNo()).append("' ");			
      buffer.append("paymentType").append("='").append(getPaymentType()).append("' ");			
      buffer.append("outTradeNo").append("='").append(getOutTradeNo()).append("' ");			
      buffer.append("body").append("='").append(getBody()).append("' ");			
      buffer.append("subject").append("='").append(getSubject()).append("' ");			
      buffer.append("totalFee").append("='").append(getTotalFee()).append("' ");			
      buffer.append("buyerEmail").append("='").append(getBuyerEmail()).append("' ");			
      buffer.append("buyerId").append("='").append(getBuyerId()).append("' ");			
      buffer.append("sellerEmail").append("='").append(getSellerEmail()).append("' ");			
      buffer.append("sellerId").append("='").append(getSellerId()).append("' ");			
      buffer.append("tradeStatus").append("='").append(getTradeStatus()).append("' ");			
      buffer.append("extraCommonParam").append("='").append(getExtraCommonParam()).append("' ");			
      buffer.append("bankSeqNo").append("='").append(getBankSeqNo()).append("' ");			
      buffer.append("inc").append("='").append(getInc()).append("' ");			
      buffer.append("referer").append("='").append(getReferer()).append("' ");		
      buffer.append("url").append("='").append(getUrl()).append("' ");			
      buffer.append("ip").append("='").append(getIp()).append("' ");			
      buffer.append("returnMsg").append("='").append(getReturnMsg()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JfiAlipayLog) ) return false;
		 JfiAlipayLog castOther = ( JfiAlipayLog ) other; 
         
		 return ( (this.getLogId()==castOther.getLogId()) || ( this.getLogId()!=null && castOther.getLogId()!=null && this.getLogId().equals(castOther.getLogId()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getIsSuccess()==castOther.getIsSuccess()) || ( this.getIsSuccess()!=null && castOther.getIsSuccess()!=null && this.getIsSuccess().equals(castOther.getIsSuccess()) ) )
 && ( (this.getSign()==castOther.getSign()) || ( this.getSign()!=null && castOther.getSign()!=null && this.getSign().equals(castOther.getSign()) ) )
 && ( (this.getSignType()==castOther.getSignType()) || ( this.getSignType()!=null && castOther.getSignType()!=null && this.getSignType().equals(castOther.getSignType()) ) )
 && ( (this.getExterface()==castOther.getExterface()) || ( this.getExterface()!=null && castOther.getExterface()!=null && this.getExterface().equals(castOther.getExterface()) ) )
 && ( (this.getNotifyTime()==castOther.getNotifyTime()) || ( this.getNotifyTime()!=null && castOther.getNotifyTime()!=null && this.getNotifyTime().equals(castOther.getNotifyTime()) ) )
 && ( (this.getNotifyId()==castOther.getNotifyId()) || ( this.getNotifyId()!=null && castOther.getNotifyId()!=null && this.getNotifyId().equals(castOther.getNotifyId()) ) )
 && ( (this.getNotifyType()==castOther.getNotifyType()) || ( this.getNotifyType()!=null && castOther.getNotifyType()!=null && this.getNotifyType().equals(castOther.getNotifyType()) ) )
 && ( (this.getTradeNo()==castOther.getTradeNo()) || ( this.getTradeNo()!=null && castOther.getTradeNo()!=null && this.getTradeNo().equals(castOther.getTradeNo()) ) )
 && ( (this.getPaymentType()==castOther.getPaymentType()) || ( this.getPaymentType()!=null && castOther.getPaymentType()!=null && this.getPaymentType().equals(castOther.getPaymentType()) ) )
 && ( (this.getOutTradeNo()==castOther.getOutTradeNo()) || ( this.getOutTradeNo()!=null && castOther.getOutTradeNo()!=null && this.getOutTradeNo().equals(castOther.getOutTradeNo()) ) )
 && ( (this.getBody()==castOther.getBody()) || ( this.getBody()!=null && castOther.getBody()!=null && this.getBody().equals(castOther.getBody()) ) )
 && ( (this.getSubject()==castOther.getSubject()) || ( this.getSubject()!=null && castOther.getSubject()!=null && this.getSubject().equals(castOther.getSubject()) ) )
 && ( (this.getTotalFee()==castOther.getTotalFee()) || ( this.getTotalFee()!=null && castOther.getTotalFee()!=null && this.getTotalFee().equals(castOther.getTotalFee()) ) )
 && ( (this.getBuyerEmail()==castOther.getBuyerEmail()) || ( this.getBuyerEmail()!=null && castOther.getBuyerEmail()!=null && this.getBuyerEmail().equals(castOther.getBuyerEmail()) ) )
 && ( (this.getBuyerId()==castOther.getBuyerId()) || ( this.getBuyerId()!=null && castOther.getBuyerId()!=null && this.getBuyerId().equals(castOther.getBuyerId()) ) )
 && ( (this.getSellerEmail()==castOther.getSellerEmail()) || ( this.getSellerEmail()!=null && castOther.getSellerEmail()!=null && this.getSellerEmail().equals(castOther.getSellerEmail()) ) )
 && ( (this.getSellerId()==castOther.getSellerId()) || ( this.getSellerId()!=null && castOther.getSellerId()!=null && this.getSellerId().equals(castOther.getSellerId()) ) )
 && ( (this.getTradeStatus()==castOther.getTradeStatus()) || ( this.getTradeStatus()!=null && castOther.getTradeStatus()!=null && this.getTradeStatus().equals(castOther.getTradeStatus()) ) )
 && ( (this.getExtraCommonParam()==castOther.getExtraCommonParam()) || ( this.getExtraCommonParam()!=null && castOther.getExtraCommonParam()!=null && this.getExtraCommonParam().equals(castOther.getExtraCommonParam()) ) )
 && ( (this.getBankSeqNo()==castOther.getBankSeqNo()) || ( this.getBankSeqNo()!=null && castOther.getBankSeqNo()!=null && this.getBankSeqNo().equals(castOther.getBankSeqNo()) ) )
 && ( (this.getInc()==castOther.getInc()) || ( this.getInc()!=null && castOther.getInc()!=null && this.getInc().equals(castOther.getInc()) ) )
 && ( (this.getReferer()==castOther.getReferer()) || ( this.getReferer()!=null && castOther.getReferer()!=null && this.getReferer().equals(castOther.getReferer()) ) )
 && ( (this.getUrl()==castOther.getUrl()) || ( this.getUrl()!=null && castOther.getUrl()!=null && this.getUrl().equals(castOther.getUrl()) ) )
 && ( (this.getIp()==castOther.getIp()) || ( this.getIp()!=null && castOther.getIp()!=null && this.getIp().equals(castOther.getIp()) ) )
 && ( (this.getReturnMsg()==castOther.getReturnMsg()) || ( this.getReturnMsg()!=null && castOther.getReturnMsg()!=null && this.getReturnMsg().equals(castOther.getReturnMsg()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getLogId() == null ? 0 : this.getLogId().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getIsSuccess() == null ? 0 : this.getIsSuccess().hashCode() );
         result = 37 * result + ( getSign() == null ? 0 : this.getSign().hashCode() );
         result = 37 * result + ( getSignType() == null ? 0 : this.getSignType().hashCode() );
         result = 37 * result + ( getExterface() == null ? 0 : this.getExterface().hashCode() );
         result = 37 * result + ( getNotifyTime() == null ? 0 : this.getNotifyTime().hashCode() );
         result = 37 * result + ( getNotifyId() == null ? 0 : this.getNotifyId().hashCode() );
         result = 37 * result + ( getNotifyType() == null ? 0 : this.getNotifyType().hashCode() );
         result = 37 * result + ( getTradeNo() == null ? 0 : this.getTradeNo().hashCode() );
         result = 37 * result + ( getPaymentType() == null ? 0 : this.getPaymentType().hashCode() );
         result = 37 * result + ( getOutTradeNo() == null ? 0 : this.getOutTradeNo().hashCode() );
         result = 37 * result + ( getBody() == null ? 0 : this.getBody().hashCode() );
         result = 37 * result + ( getSubject() == null ? 0 : this.getSubject().hashCode() );
         result = 37 * result + ( getTotalFee() == null ? 0 : this.getTotalFee().hashCode() );
         result = 37 * result + ( getBuyerEmail() == null ? 0 : this.getBuyerEmail().hashCode() );
         result = 37 * result + ( getBuyerId() == null ? 0 : this.getBuyerId().hashCode() );
         result = 37 * result + ( getSellerEmail() == null ? 0 : this.getSellerEmail().hashCode() );
         result = 37 * result + ( getSellerId() == null ? 0 : this.getSellerId().hashCode() );
         result = 37 * result + ( getTradeStatus() == null ? 0 : this.getTradeStatus().hashCode() );
         result = 37 * result + ( getExtraCommonParam() == null ? 0 : this.getExtraCommonParam().hashCode() );
         result = 37 * result + ( getBankSeqNo() == null ? 0 : this.getBankSeqNo().hashCode() );
         result = 37 * result + ( getInc() == null ? 0 : this.getInc().hashCode() );
         result = 37 * result + ( getReferer() == null ? 0 : this.getReferer().hashCode() );
         result = 37 * result + ( getUrl() == null ? 0 : this.getUrl().hashCode() );
         result = 37 * result + ( getIp() == null ? 0 : this.getIp().hashCode() );
         result = 37 * result + ( getReturnMsg() == null ? 0 : this.getReturnMsg().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         return result;
   }









}
