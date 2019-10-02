package com.joymain.jecs.fi.model;
// Generated 2015-1-8 15:43:16 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JFI_CHANJET_LOG"
 *     
 */

public class JfiChanjetLog extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long logId;
    private String companyCode;
    private String userCode;//会员编号
    private String merchantId;//商户号
    private String signtype;//签名类型，2机构证书签名, 0 MD5签名
    private String version;//网关版本
    private String businessId;//业务号，00WGFKH20012
    private String platidtfy;//平台身份标识，T系列哪个产品
    private String orderId;//商户订单号
    private String orderDate;//商户订单日期
    private String orderAmount;//商户订单金额，整形数字 以分为单位。
    private String orderTime;//商户订单提交时间
    private String expireTime;//订单过期时间
    private String deviceId;//设备Id，终端id，二维码信息，重点确认下
    private String detailId;//畅捷通平台流水
    private String detailTime;//畅捷通平台交易时间
    private String bankId;//银行编号
    private String bankDealId;//银行处理流水
    private String amount;//付款金额
    private String amtType;//付款币种。01为人民币
    private String payResult;//支付结果，00：为成功；  01：为失败
    private String errCode;//错误代码
    private String errMsg;//错误描述
    private String signMsg;//签名字符串
    private String dataType;//数据来源，1：PC，2：手机终端
    private String inc;//是否进账成功，1：代表进账成功，0：代表否
    private String returnMsg;//验签结果，10：验签成功，0：数据被篡改，1：扣款失败，3：数据重复发送 ，4：验签不通过 
    private String url;//畅捷通发送付款通知的URL
    private Date createTime;//数据记录时间


    // Constructors

    /** default constructor */
    public JfiChanjetLog() {
    }

	/** minimal constructor */
    public JfiChanjetLog(String userCode, String merchantId) {
        this.userCode = userCode;
        this.merchantId = merchantId;
    }
    
    /** full constructor */
    public JfiChanjetLog(String companyCode, String userCode, String merchantId, String signtype, String version, String businessId, String platidtfy, String orderId, String orderDate, String orderAmount, String orderTime, String expireTime, String deviceId, String detailId, String detailTime, String bankId, String bankDealId, String amount, String amtType, String payResult, String errCode, String errMsg, String signMsg, String dataType, String inc, String returnMsg, String url, Date createTime) {
        this.companyCode = companyCode;
        this.userCode = userCode;
        this.merchantId = merchantId;
        this.signtype = signtype;
        this.version = version;
        this.businessId = businessId;
        this.platidtfy = platidtfy;
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderAmount = orderAmount;
        this.orderTime = orderTime;
        this.expireTime = expireTime;
        this.deviceId = deviceId;
        this.detailId = detailId;
        this.detailTime = detailTime;
        this.bankId = bankId;
        this.bankDealId = bankDealId;
        this.amount = amount;
        this.amtType = amtType;
        this.payResult = payResult;
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.signMsg = signMsg;
        this.dataType = dataType;
        this.inc = inc;
        this.returnMsg = returnMsg;
        this.url = url;
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
     *             column="MERCHANT_ID"
     *             length="50"
     *             not-null="true"
     *         
     */

    public String getMerchantId() {
        return this.merchantId;
    }
    
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
    /**       
     *      *            @hibernate.property
     *             column="SIGNTYPE"
     *             length="2"
     *         
     */

    public String getSigntype() {
        return this.signtype;
    }
    
    public void setSigntype(String signtype) {
        this.signtype = signtype;
    }
    /**       
     *      *            @hibernate.property
     *             column="VERSION"
     *             length="50"
     *         
     */

    public String getVersion() {
        return this.version;
    }
    
    public void setVersion(String version) {
        this.version = version;
    }
    /**       
     *      *            @hibernate.property
     *             column="BUSINESS_ID"
     *             length="50"
     *         
     */

    public String getBusinessId() {
        return this.businessId;
    }
    
    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }
    /**       
     *      *            @hibernate.property
     *             column="PLATIDTFY"
     *             length="50"
     *         
     */

    public String getPlatidtfy() {
        return this.platidtfy;
    }
    
    public void setPlatidtfy(String platidtfy) {
        this.platidtfy = platidtfy;
    }
    /**       
     *      *            @hibernate.property
     *             column="ORDER_ID"
     *             length="50"
     *         
     */

    public String getOrderId() {
        return this.orderId;
    }
    
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    /**       
     *      *            @hibernate.property
     *             column="ORDER_DATE"
     *             length="50"
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
     *             column="ORDER_AMOUNT"
     *             length="50"
     *         
     */

    public String getOrderAmount() {
        return this.orderAmount;
    }
    
    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }
    /**       
     *      *            @hibernate.property
     *             column="ORDER_TIME"
     *             length="50"
     *         
     */

    public String getOrderTime() {
        return this.orderTime;
    }
    
    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="EXPIRE_TIME"
     *             length="50"
     *         
     */

    public String getExpireTime() {
        return this.expireTime;
    }
    
    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="DEVICE_ID"
     *             length="50"
     *         
     */

    public String getDeviceId() {
        return this.deviceId;
    }
    
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    /**       
     *      *            @hibernate.property
     *             column="DETAIL_ID"
     *             length="50"
     *         
     */

    public String getDetailId() {
        return this.detailId;
    }
    
    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }
    /**       
     *      *            @hibernate.property
     *             column="DETAIL_TIME"
     *             length="50"
     *         
     */

    public String getDetailTime() {
        return this.detailTime;
    }
    
    public void setDetailTime(String detailTime) {
        this.detailTime = detailTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="BANK_ID"
     *             length="50"
     *         
     */

    public String getBankId() {
        return this.bankId;
    }
    
    public void setBankId(String bankId) {
        this.bankId = bankId;
    }
    /**       
     *      *            @hibernate.property
     *             column="BANK_DEAL_ID"
     *             length="50"
     *         
     */

    public String getBankDealId() {
        return this.bankDealId;
    }
    
    public void setBankDealId(String bankDealId) {
        this.bankDealId = bankDealId;
    }
    /**       
     *      *            @hibernate.property
     *             column="AMOUNT"
     *             length="50"
     *         
     */

    public String getAmount() {
        return this.amount;
    }
    
    public void setAmount(String amount) {
        this.amount = amount;
    }
    /**       
     *      *            @hibernate.property
     *             column="AMT_TYPE"
     *             length="2"
     *         
     */

    public String getAmtType() {
        return this.amtType;
    }
    
    public void setAmtType(String amtType) {
        this.amtType = amtType;
    }
    /**       
     *      *            @hibernate.property
     *             column="PAY_RESULT"
     *             length="2"
     *         
     */

    public String getPayResult() {
        return this.payResult;
    }
    
    public void setPayResult(String payResult) {
        this.payResult = payResult;
    }
    /**       
     *      *            @hibernate.property
     *             column="ERR_CODE"
     *             length="50"
     *         
     */

    public String getErrCode() {
        return this.errCode;
    }
    
    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="ERR_MSG"
     *             length="50"
     *         
     */

    public String getErrMsg() {
        return this.errMsg;
    }
    
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
    /**       
     *      *            @hibernate.property
     *             column="SIGN_MSG"
     *             length="500"
     *         
     */

    public String getSignMsg() {
        return this.signMsg;
    }
    
    public void setSignMsg(String signMsg) {
        this.signMsg = signMsg;
    }
    /**       
     *      *            @hibernate.property
     *             column="DATA_TYPE"
     *             length="2"
     *         
     */

    public String getDataType() {
        return this.dataType;
    }
    
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
    /**       
     *      *            @hibernate.property
     *             column="INC"
     *             length="2"
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
     *             column="RETURN_MSG"
     *             length="50"
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
     *             column="URL"
     *             length="1000"
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
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("merchantId").append("='").append(getMerchantId()).append("' ");			
      buffer.append("signtype").append("='").append(getSigntype()).append("' ");			
      buffer.append("version").append("='").append(getVersion()).append("' ");			
      buffer.append("businessId").append("='").append(getBusinessId()).append("' ");			
      buffer.append("platidtfy").append("='").append(getPlatidtfy()).append("' ");			
      buffer.append("orderId").append("='").append(getOrderId()).append("' ");			
      buffer.append("orderDate").append("='").append(getOrderDate()).append("' ");			
      buffer.append("orderAmount").append("='").append(getOrderAmount()).append("' ");			
      buffer.append("orderTime").append("='").append(getOrderTime()).append("' ");			
      buffer.append("expireTime").append("='").append(getExpireTime()).append("' ");			
      buffer.append("deviceId").append("='").append(getDeviceId()).append("' ");			
      buffer.append("detailId").append("='").append(getDetailId()).append("' ");			
      buffer.append("detailTime").append("='").append(getDetailTime()).append("' ");			
      buffer.append("bankId").append("='").append(getBankId()).append("' ");			
      buffer.append("bankDealId").append("='").append(getBankDealId()).append("' ");			
      buffer.append("amount").append("='").append(getAmount()).append("' ");			
      buffer.append("amtType").append("='").append(getAmtType()).append("' ");			
      buffer.append("payResult").append("='").append(getPayResult()).append("' ");			
      buffer.append("errCode").append("='").append(getErrCode()).append("' ");			
      buffer.append("errMsg").append("='").append(getErrMsg()).append("' ");			
      buffer.append("signMsg").append("='").append(getSignMsg()).append("' ");			
      buffer.append("dataType").append("='").append(getDataType()).append("' ");			
      buffer.append("inc").append("='").append(getInc()).append("' ");			
      buffer.append("returnMsg").append("='").append(getReturnMsg()).append("' ");			
      buffer.append("url").append("='").append(getUrl()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JfiChanjetLog) ) return false;
		 JfiChanjetLog castOther = ( JfiChanjetLog ) other; 
         
		 return ( (this.getLogId()==castOther.getLogId()) || ( this.getLogId()!=null && castOther.getLogId()!=null && this.getLogId().equals(castOther.getLogId()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getMerchantId()==castOther.getMerchantId()) || ( this.getMerchantId()!=null && castOther.getMerchantId()!=null && this.getMerchantId().equals(castOther.getMerchantId()) ) )
 && ( (this.getSigntype()==castOther.getSigntype()) || ( this.getSigntype()!=null && castOther.getSigntype()!=null && this.getSigntype().equals(castOther.getSigntype()) ) )
 && ( (this.getVersion()==castOther.getVersion()) || ( this.getVersion()!=null && castOther.getVersion()!=null && this.getVersion().equals(castOther.getVersion()) ) )
 && ( (this.getBusinessId()==castOther.getBusinessId()) || ( this.getBusinessId()!=null && castOther.getBusinessId()!=null && this.getBusinessId().equals(castOther.getBusinessId()) ) )
 && ( (this.getPlatidtfy()==castOther.getPlatidtfy()) || ( this.getPlatidtfy()!=null && castOther.getPlatidtfy()!=null && this.getPlatidtfy().equals(castOther.getPlatidtfy()) ) )
 && ( (this.getOrderId()==castOther.getOrderId()) || ( this.getOrderId()!=null && castOther.getOrderId()!=null && this.getOrderId().equals(castOther.getOrderId()) ) )
 && ( (this.getOrderDate()==castOther.getOrderDate()) || ( this.getOrderDate()!=null && castOther.getOrderDate()!=null && this.getOrderDate().equals(castOther.getOrderDate()) ) )
 && ( (this.getOrderAmount()==castOther.getOrderAmount()) || ( this.getOrderAmount()!=null && castOther.getOrderAmount()!=null && this.getOrderAmount().equals(castOther.getOrderAmount()) ) )
 && ( (this.getOrderTime()==castOther.getOrderTime()) || ( this.getOrderTime()!=null && castOther.getOrderTime()!=null && this.getOrderTime().equals(castOther.getOrderTime()) ) )
 && ( (this.getExpireTime()==castOther.getExpireTime()) || ( this.getExpireTime()!=null && castOther.getExpireTime()!=null && this.getExpireTime().equals(castOther.getExpireTime()) ) )
 && ( (this.getDeviceId()==castOther.getDeviceId()) || ( this.getDeviceId()!=null && castOther.getDeviceId()!=null && this.getDeviceId().equals(castOther.getDeviceId()) ) )
 && ( (this.getDetailId()==castOther.getDetailId()) || ( this.getDetailId()!=null && castOther.getDetailId()!=null && this.getDetailId().equals(castOther.getDetailId()) ) )
 && ( (this.getDetailTime()==castOther.getDetailTime()) || ( this.getDetailTime()!=null && castOther.getDetailTime()!=null && this.getDetailTime().equals(castOther.getDetailTime()) ) )
 && ( (this.getBankId()==castOther.getBankId()) || ( this.getBankId()!=null && castOther.getBankId()!=null && this.getBankId().equals(castOther.getBankId()) ) )
 && ( (this.getBankDealId()==castOther.getBankDealId()) || ( this.getBankDealId()!=null && castOther.getBankDealId()!=null && this.getBankDealId().equals(castOther.getBankDealId()) ) )
 && ( (this.getAmount()==castOther.getAmount()) || ( this.getAmount()!=null && castOther.getAmount()!=null && this.getAmount().equals(castOther.getAmount()) ) )
 && ( (this.getAmtType()==castOther.getAmtType()) || ( this.getAmtType()!=null && castOther.getAmtType()!=null && this.getAmtType().equals(castOther.getAmtType()) ) )
 && ( (this.getPayResult()==castOther.getPayResult()) || ( this.getPayResult()!=null && castOther.getPayResult()!=null && this.getPayResult().equals(castOther.getPayResult()) ) )
 && ( (this.getErrCode()==castOther.getErrCode()) || ( this.getErrCode()!=null && castOther.getErrCode()!=null && this.getErrCode().equals(castOther.getErrCode()) ) )
 && ( (this.getErrMsg()==castOther.getErrMsg()) || ( this.getErrMsg()!=null && castOther.getErrMsg()!=null && this.getErrMsg().equals(castOther.getErrMsg()) ) )
 && ( (this.getSignMsg()==castOther.getSignMsg()) || ( this.getSignMsg()!=null && castOther.getSignMsg()!=null && this.getSignMsg().equals(castOther.getSignMsg()) ) )
 && ( (this.getDataType()==castOther.getDataType()) || ( this.getDataType()!=null && castOther.getDataType()!=null && this.getDataType().equals(castOther.getDataType()) ) )
 && ( (this.getInc()==castOther.getInc()) || ( this.getInc()!=null && castOther.getInc()!=null && this.getInc().equals(castOther.getInc()) ) )
 && ( (this.getReturnMsg()==castOther.getReturnMsg()) || ( this.getReturnMsg()!=null && castOther.getReturnMsg()!=null && this.getReturnMsg().equals(castOther.getReturnMsg()) ) )
 && ( (this.getUrl()==castOther.getUrl()) || ( this.getUrl()!=null && castOther.getUrl()!=null && this.getUrl().equals(castOther.getUrl()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getLogId() == null ? 0 : this.getLogId().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getMerchantId() == null ? 0 : this.getMerchantId().hashCode() );
         result = 37 * result + ( getSigntype() == null ? 0 : this.getSigntype().hashCode() );
         result = 37 * result + ( getVersion() == null ? 0 : this.getVersion().hashCode() );
         result = 37 * result + ( getBusinessId() == null ? 0 : this.getBusinessId().hashCode() );
         result = 37 * result + ( getPlatidtfy() == null ? 0 : this.getPlatidtfy().hashCode() );
         result = 37 * result + ( getOrderId() == null ? 0 : this.getOrderId().hashCode() );
         result = 37 * result + ( getOrderDate() == null ? 0 : this.getOrderDate().hashCode() );
         result = 37 * result + ( getOrderAmount() == null ? 0 : this.getOrderAmount().hashCode() );
         result = 37 * result + ( getOrderTime() == null ? 0 : this.getOrderTime().hashCode() );
         result = 37 * result + ( getExpireTime() == null ? 0 : this.getExpireTime().hashCode() );
         result = 37 * result + ( getDeviceId() == null ? 0 : this.getDeviceId().hashCode() );
         result = 37 * result + ( getDetailId() == null ? 0 : this.getDetailId().hashCode() );
         result = 37 * result + ( getDetailTime() == null ? 0 : this.getDetailTime().hashCode() );
         result = 37 * result + ( getBankId() == null ? 0 : this.getBankId().hashCode() );
         result = 37 * result + ( getBankDealId() == null ? 0 : this.getBankDealId().hashCode() );
         result = 37 * result + ( getAmount() == null ? 0 : this.getAmount().hashCode() );
         result = 37 * result + ( getAmtType() == null ? 0 : this.getAmtType().hashCode() );
         result = 37 * result + ( getPayResult() == null ? 0 : this.getPayResult().hashCode() );
         result = 37 * result + ( getErrCode() == null ? 0 : this.getErrCode().hashCode() );
         result = 37 * result + ( getErrMsg() == null ? 0 : this.getErrMsg().hashCode() );
         result = 37 * result + ( getSignMsg() == null ? 0 : this.getSignMsg().hashCode() );
         result = 37 * result + ( getDataType() == null ? 0 : this.getDataType().hashCode() );
         result = 37 * result + ( getInc() == null ? 0 : this.getInc().hashCode() );
         result = 37 * result + ( getReturnMsg() == null ? 0 : this.getReturnMsg().hashCode() );
         result = 37 * result + ( getUrl() == null ? 0 : this.getUrl().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         return result;
   }   





}
