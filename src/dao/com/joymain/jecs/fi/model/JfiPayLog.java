package com.joymain.jecs.fi.model;
// Generated 2015-10-23 10:51:47 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JFI_PAY_LOG"
 *     
 */

public class JfiPayLog extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long logId;
    private String companyCode;
    private String payType;
    private String dealId;
    private String flag;
    private String userCode;
    private String merchantId;
    private String version;
    private String signType;
    private String sign;
    private String merkey;
    private String bankId;
    private String bankDealId;
    private String amtType;
    private String orderId;
    private String orderDate;
    private String orderAmount;
    private String orderTime;
    private String expireTime;
    private String payResult;
    private String errCode;
    private String errMsg;
    private String remark;
    private String returnMsg;
    private String createTime;
    private String url;
    private String urlRemark;
    private String inc;
    private String ip;
    private String dataType;


    // Constructors

    /** default constructor */
    public JfiPayLog() {
    }

    
    /** full constructor */
    public JfiPayLog(String companyCode, String payType, String dealId, String flag, String userCode, String merchantId, String version, String signType, String sign, String merkey, String bankId, String bankDealId, String amtType, String orderId, String orderDate, String orderAmount, String orderTime, String expireTime, String payResult, String errCode, String errMsg, String remark, String returnMsg, String createTime, String url, String urlRemark, String inc, String ip, String dataType) {
        this.companyCode = companyCode;
        this.payType = payType;
        this.dealId = dealId;
        this.flag = flag;
        this.userCode = userCode;
        this.merchantId = merchantId;
        this.version = version;
        this.signType = signType;
        this.sign = sign;
        this.merkey = merkey;
        this.bankId = bankId;
        this.bankDealId = bankDealId;
        this.amtType = amtType;
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderAmount = orderAmount;
        this.orderTime = orderTime;
        this.expireTime = expireTime;
        this.payResult = payResult;
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.remark = remark;
        this.returnMsg = returnMsg;
        this.createTime = createTime;
        this.url = url;
        this.urlRemark = urlRemark;
        this.inc = inc;
        this.ip = ip;
        this.dataType = dataType;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.Long"
     *             column="LOG_ID"
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
     *             column="PAY_TYPE"
     *             length="258"
     *         
     */

    public String getPayType() {
        return this.payType;
    }
    
    public void setPayType(String payType) {
        this.payType = payType;
    }
    /**       
     *      *            @hibernate.property
     *             column="DEAL_ID"
     *             length="258"
     *         
     */

    public String getDealId() {
        return this.dealId;
    }
    
    public void setDealId(String dealId) {
        this.dealId = dealId;
    }
    /**       
     *      *            @hibernate.property
     *             column="FLAG"
     *             length="2"
     *         
     */

    public String getFlag() {
        return this.flag;
    }
    
    public void setFlag(String flag) {
        this.flag = flag;
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
     *             column="MERCHANT_ID"
     *             length="258"
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
     *             column="VERSION"
     *             length="258"
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
     *             column="SIGN_TYPE"
     *             length="258"
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
     *             column="SIGN"
     *             length="258"
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
     *             column="MERKEY"
     *             length="258"
     *         
     */

    public String getMerkey() {
        return this.merkey;
    }
    
    public void setMerkey(String merkey) {
        this.merkey = merkey;
    }
    /**       
     *      *            @hibernate.property
     *             column="BANK_ID"
     *             length="258"
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
     *             length="258"
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
     *             column="AMT_TYPE"
     *             length="258"
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
     *             column="ORDER_ID"
     *             length="258"
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
     *             length="258"
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
     *             length="258"
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
     *             length="258"
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
     *             length="258"
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
     *             column="PAY_RESULT"
     *             length="258"
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
     *             length="258"
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
     *             length="258"
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
     *             column="REMARK"
     *             length="4000"
     *         
     */

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
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
     *             length="258"
     *         
     */

    public String getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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
     *             column="URL_REMARK"
     *             length="4000"
     *         
     */

    public String getUrlRemark() {
        return this.urlRemark;
    }
    
    public void setUrlRemark(String urlRemark) {
        this.urlRemark = urlRemark;
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
     *             column="IP"
     *             length="35"
     *         
     */

    public String getIp() {
        return this.ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
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
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("payType").append("='").append(getPayType()).append("' ");			
      buffer.append("dealId").append("='").append(getDealId()).append("' ");			
      buffer.append("flag").append("='").append(getFlag()).append("' ");			
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("merchantId").append("='").append(getMerchantId()).append("' ");			
      buffer.append("version").append("='").append(getVersion()).append("' ");			
      buffer.append("signType").append("='").append(getSignType()).append("' ");			
      buffer.append("sign").append("='").append(getSign()).append("' ");			
      buffer.append("merkey").append("='").append(getMerkey()).append("' ");			
      buffer.append("bankId").append("='").append(getBankId()).append("' ");			
      buffer.append("bankDealId").append("='").append(getBankDealId()).append("' ");			
      buffer.append("amtType").append("='").append(getAmtType()).append("' ");			
      buffer.append("orderId").append("='").append(getOrderId()).append("' ");			
      buffer.append("orderDate").append("='").append(getOrderDate()).append("' ");			
      buffer.append("orderAmount").append("='").append(getOrderAmount()).append("' ");			
      buffer.append("orderTime").append("='").append(getOrderTime()).append("' ");			
      buffer.append("expireTime").append("='").append(getExpireTime()).append("' ");			
      buffer.append("payResult").append("='").append(getPayResult()).append("' ");			
      buffer.append("errCode").append("='").append(getErrCode()).append("' ");			
      buffer.append("errMsg").append("='").append(getErrMsg()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");			
      buffer.append("returnMsg").append("='").append(getReturnMsg()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("url").append("='").append(getUrl()).append("' ");			
      buffer.append("urlRemark").append("='").append(getUrlRemark()).append("' ");			
      buffer.append("inc").append("='").append(getInc()).append("' ");			
      buffer.append("ip").append("='").append(getIp()).append("' ");			
      buffer.append("dataType").append("='").append(getDataType()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JfiPayLog) ) return false;
		 JfiPayLog castOther = ( JfiPayLog ) other; 
         
		 return ( (this.getLogId()==castOther.getLogId()) || ( this.getLogId()!=null && castOther.getLogId()!=null && this.getLogId().equals(castOther.getLogId()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getPayType()==castOther.getPayType()) || ( this.getPayType()!=null && castOther.getPayType()!=null && this.getPayType().equals(castOther.getPayType()) ) )
 && ( (this.getDealId()==castOther.getDealId()) || ( this.getDealId()!=null && castOther.getDealId()!=null && this.getDealId().equals(castOther.getDealId()) ) )
 && ( (this.getFlag()==castOther.getFlag()) || ( this.getFlag()!=null && castOther.getFlag()!=null && this.getFlag().equals(castOther.getFlag()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getMerchantId()==castOther.getMerchantId()) || ( this.getMerchantId()!=null && castOther.getMerchantId()!=null && this.getMerchantId().equals(castOther.getMerchantId()) ) )
 && ( (this.getVersion()==castOther.getVersion()) || ( this.getVersion()!=null && castOther.getVersion()!=null && this.getVersion().equals(castOther.getVersion()) ) )
 && ( (this.getSignType()==castOther.getSignType()) || ( this.getSignType()!=null && castOther.getSignType()!=null && this.getSignType().equals(castOther.getSignType()) ) )
 && ( (this.getSign()==castOther.getSign()) || ( this.getSign()!=null && castOther.getSign()!=null && this.getSign().equals(castOther.getSign()) ) )
 && ( (this.getMerkey()==castOther.getMerkey()) || ( this.getMerkey()!=null && castOther.getMerkey()!=null && this.getMerkey().equals(castOther.getMerkey()) ) )
 && ( (this.getBankId()==castOther.getBankId()) || ( this.getBankId()!=null && castOther.getBankId()!=null && this.getBankId().equals(castOther.getBankId()) ) )
 && ( (this.getBankDealId()==castOther.getBankDealId()) || ( this.getBankDealId()!=null && castOther.getBankDealId()!=null && this.getBankDealId().equals(castOther.getBankDealId()) ) )
 && ( (this.getAmtType()==castOther.getAmtType()) || ( this.getAmtType()!=null && castOther.getAmtType()!=null && this.getAmtType().equals(castOther.getAmtType()) ) )
 && ( (this.getOrderId()==castOther.getOrderId()) || ( this.getOrderId()!=null && castOther.getOrderId()!=null && this.getOrderId().equals(castOther.getOrderId()) ) )
 && ( (this.getOrderDate()==castOther.getOrderDate()) || ( this.getOrderDate()!=null && castOther.getOrderDate()!=null && this.getOrderDate().equals(castOther.getOrderDate()) ) )
 && ( (this.getOrderAmount()==castOther.getOrderAmount()) || ( this.getOrderAmount()!=null && castOther.getOrderAmount()!=null && this.getOrderAmount().equals(castOther.getOrderAmount()) ) )
 && ( (this.getOrderTime()==castOther.getOrderTime()) || ( this.getOrderTime()!=null && castOther.getOrderTime()!=null && this.getOrderTime().equals(castOther.getOrderTime()) ) )
 && ( (this.getExpireTime()==castOther.getExpireTime()) || ( this.getExpireTime()!=null && castOther.getExpireTime()!=null && this.getExpireTime().equals(castOther.getExpireTime()) ) )
 && ( (this.getPayResult()==castOther.getPayResult()) || ( this.getPayResult()!=null && castOther.getPayResult()!=null && this.getPayResult().equals(castOther.getPayResult()) ) )
 && ( (this.getErrCode()==castOther.getErrCode()) || ( this.getErrCode()!=null && castOther.getErrCode()!=null && this.getErrCode().equals(castOther.getErrCode()) ) )
 && ( (this.getErrMsg()==castOther.getErrMsg()) || ( this.getErrMsg()!=null && castOther.getErrMsg()!=null && this.getErrMsg().equals(castOther.getErrMsg()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
 && ( (this.getReturnMsg()==castOther.getReturnMsg()) || ( this.getReturnMsg()!=null && castOther.getReturnMsg()!=null && this.getReturnMsg().equals(castOther.getReturnMsg()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getUrl()==castOther.getUrl()) || ( this.getUrl()!=null && castOther.getUrl()!=null && this.getUrl().equals(castOther.getUrl()) ) )
 && ( (this.getUrlRemark()==castOther.getUrlRemark()) || ( this.getUrlRemark()!=null && castOther.getUrlRemark()!=null && this.getUrlRemark().equals(castOther.getUrlRemark()) ) )
 && ( (this.getInc()==castOther.getInc()) || ( this.getInc()!=null && castOther.getInc()!=null && this.getInc().equals(castOther.getInc()) ) )
 && ( (this.getIp()==castOther.getIp()) || ( this.getIp()!=null && castOther.getIp()!=null && this.getIp().equals(castOther.getIp()) ) )
 && ( (this.getDataType()==castOther.getDataType()) || ( this.getDataType()!=null && castOther.getDataType()!=null && this.getDataType().equals(castOther.getDataType()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getLogId() == null ? 0 : this.getLogId().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getPayType() == null ? 0 : this.getPayType().hashCode() );
         result = 37 * result + ( getDealId() == null ? 0 : this.getDealId().hashCode() );
         result = 37 * result + ( getFlag() == null ? 0 : this.getFlag().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getMerchantId() == null ? 0 : this.getMerchantId().hashCode() );
         result = 37 * result + ( getVersion() == null ? 0 : this.getVersion().hashCode() );
         result = 37 * result + ( getSignType() == null ? 0 : this.getSignType().hashCode() );
         result = 37 * result + ( getSign() == null ? 0 : this.getSign().hashCode() );
         result = 37 * result + ( getMerkey() == null ? 0 : this.getMerkey().hashCode() );
         result = 37 * result + ( getBankId() == null ? 0 : this.getBankId().hashCode() );
         result = 37 * result + ( getBankDealId() == null ? 0 : this.getBankDealId().hashCode() );
         result = 37 * result + ( getAmtType() == null ? 0 : this.getAmtType().hashCode() );
         result = 37 * result + ( getOrderId() == null ? 0 : this.getOrderId().hashCode() );
         result = 37 * result + ( getOrderDate() == null ? 0 : this.getOrderDate().hashCode() );
         result = 37 * result + ( getOrderAmount() == null ? 0 : this.getOrderAmount().hashCode() );
         result = 37 * result + ( getOrderTime() == null ? 0 : this.getOrderTime().hashCode() );
         result = 37 * result + ( getExpireTime() == null ? 0 : this.getExpireTime().hashCode() );
         result = 37 * result + ( getPayResult() == null ? 0 : this.getPayResult().hashCode() );
         result = 37 * result + ( getErrCode() == null ? 0 : this.getErrCode().hashCode() );
         result = 37 * result + ( getErrMsg() == null ? 0 : this.getErrMsg().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         result = 37 * result + ( getReturnMsg() == null ? 0 : this.getReturnMsg().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getUrl() == null ? 0 : this.getUrl().hashCode() );
         result = 37 * result + ( getUrlRemark() == null ? 0 : this.getUrlRemark().hashCode() );
         result = 37 * result + ( getInc() == null ? 0 : this.getInc().hashCode() );
         result = 37 * result + ( getIp() == null ? 0 : this.getIp().hashCode() );
         result = 37 * result + ( getDataType() == null ? 0 : this.getDataType().hashCode() );
         return result;
   }   





}
