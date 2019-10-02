package com.joymain.jecs.fi.model;

import java.util.Date;

// Generated 2009-9-18 14:18:43 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JFI_99BILL_LOG"
 *     
 */

public class Jfi99billLog extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long logId;
    private String companyCode;
    private String userCode;
    private String merchantAcctId;
    private String version;
    private String billLanguage;
    private String signType;
    private String payType;
    private String bankId;
    private String orderId;
    private String orderTime;
    private String orderAmount;
    private String dealId;
    private String bankDealId;
    private String dealTime;
    private String payAmount;
    private String fee;
    private String ext1;
    private String ext2;
    private String payResult;
    private String errCode;
    private String signMsg;
    private String url;
    private String verifySignResult;
    private String inc;
    private String referer;
    private String returnMsg;
    private Date createTime;
    private String ip;
    private String dataType;

    // Constructors

	/** default constructor */
    public Jfi99billLog() {
    }

	/** minimal constructor */
    public Jfi99billLog(String companyCode, String userCode, String inc) {
        this.companyCode = companyCode;
        this.userCode = userCode;
        this.inc = inc;
    }
    
    /** full constructor */
    public Jfi99billLog(String companyCode, String userCode, String merchantAcctId, String version, String billLanguage, String signType, String payType, String bankId, String orderId, String orderTime, String orderAmount, String dealId, String bankDealId, String dealTime, String payAmount, String fee, String ext1, String ext2, String payResult, String errCode, String signMsg, String url, String verifySignResult, String inc) {
        this.companyCode = companyCode;
        this.userCode = userCode;
        this.merchantAcctId = merchantAcctId;
        this.version = version;
        this.billLanguage = billLanguage;
        this.signType = signType;
        this.payType = payType;
        this.bankId = bankId;
        this.orderId = orderId;
        this.orderTime = orderTime;
        this.orderAmount = orderAmount;
        this.dealId = dealId;
        this.bankDealId = bankDealId;
        this.dealTime = dealTime;
        this.payAmount = payAmount;
        this.fee = fee;
        this.ext1 = ext1;
        this.ext2 = ext2;
        this.payResult = payResult;
        this.errCode = errCode;
        this.signMsg = signMsg;
        this.url = url;
        this.verifySignResult = verifySignResult;
        this.inc = inc;
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
     *             column="MERCHANT_ACCT_ID"
     *             length="4000"
     *         
     */

    public String getMerchantAcctId() {
        return this.merchantAcctId;
    }
    
    public void setMerchantAcctId(String merchantAcctId) {
        this.merchantAcctId = merchantAcctId;
    }
    /**       
     *      *            @hibernate.property
     *             column="VERSION"
     *             length="4000"
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
     *             column="BILL_LANGUAGE"
     *             length="4000"
     *         
     */

    public String getBillLanguage() {
        return this.billLanguage;
    }
    
    public void setBillLanguage(String billLanguage) {
        this.billLanguage = billLanguage;
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
     *             column="PAY_TYPE"
     *             length="4000"
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
     *             column="BANK_ID"
     *             length="4000"
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
     *             column="ORDER_ID"
     *             length="4000"
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
     *             column="ORDER_TIME"
     *             length="4000"
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
     *             column="ORDER_AMOUNT"
     *             length="4000"
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
     *             column="DEAL_ID"
     *             length="4000"
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
     *             column="BANK_DEAL_ID"
     *             length="4000"
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
     *             column="DEAL_TIME"
     *             length="4000"
     *         
     */

    public String getDealTime() {
        return this.dealTime;
    }
    
    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="PAY_AMOUNT"
     *             length="4000"
     *         
     */

    public String getPayAmount() {
        return this.payAmount;
    }
    
    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }
    /**       
     *      *            @hibernate.property
     *             column="FEE"
     *             length="4000"
     *         
     */

    public String getFee() {
        return this.fee;
    }
    
    public void setFee(String fee) {
        this.fee = fee;
    }
    /**       
     *      *            @hibernate.property
     *             column="EXT1"
     *             length="4000"
     *         
     */

    public String getExt1() {
        return this.ext1;
    }
    
    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }
    /**       
     *      *            @hibernate.property
     *             column="EXT2"
     *             length="4000"
     *         
     */

    public String getExt2() {
        return this.ext2;
    }
    
    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }
    /**       
     *      *            @hibernate.property
     *             column="PAY_RESULT"
     *             length="4000"
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
     *             length="4000"
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
     *             column="SIGN_MSG"
     *             length="4000"
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
     *             column="VERIFY_SIGN_RESULT"
     *             length="4000"
     *         
     */

    public String getVerifySignResult() {
        return this.verifySignResult;
    }
    
    public void setVerifySignResult(String verifySignResult) {
        this.verifySignResult = verifySignResult;
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
		return referer;
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
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

    /**       
     *      *            @hibernate.property
     *             column="CREATE_TIME"
     *             length="30"
     *         
     */
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

    /**       
     *      *            @hibernate.property
     *             column="IP"
     *             length="30"
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
     *             column="DATA_TYPE"
     *             length="1"
     *         
     */
	public String getDataType() {
		return dataType;
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
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("merchantAcctId").append("='").append(getMerchantAcctId()).append("' ");			
      buffer.append("version").append("='").append(getVersion()).append("' ");			
      buffer.append("billLanguage").append("='").append(getBillLanguage()).append("' ");			
      buffer.append("signType").append("='").append(getSignType()).append("' ");			
      buffer.append("payType").append("='").append(getPayType()).append("' ");			
      buffer.append("bankId").append("='").append(getBankId()).append("' ");			
      buffer.append("orderId").append("='").append(getOrderId()).append("' ");			
      buffer.append("orderTime").append("='").append(getOrderTime()).append("' ");			
      buffer.append("orderAmount").append("='").append(getOrderAmount()).append("' ");			
      buffer.append("dealId").append("='").append(getDealId()).append("' ");			
      buffer.append("bankDealId").append("='").append(getBankDealId()).append("' ");			
      buffer.append("dealTime").append("='").append(getDealTime()).append("' ");			
      buffer.append("payAmount").append("='").append(getPayAmount()).append("' ");			
      buffer.append("fee").append("='").append(getFee()).append("' ");			
      buffer.append("ext1").append("='").append(getExt1()).append("' ");			
      buffer.append("ext2").append("='").append(getExt2()).append("' ");			
      buffer.append("payResult").append("='").append(getPayResult()).append("' ");			
      buffer.append("errCode").append("='").append(getErrCode()).append("' ");			
      buffer.append("signMsg").append("='").append(getSignMsg()).append("' ");			
      buffer.append("url").append("='").append(getUrl()).append("' ");			
      buffer.append("verifySignResult").append("='").append(getVerifySignResult()).append("' ");			
      buffer.append("inc").append("='").append(getInc()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Jfi99billLog) ) return false;
		 Jfi99billLog castOther = ( Jfi99billLog ) other; 
         
		 return ( (this.getLogId()==castOther.getLogId()) || ( this.getLogId()!=null && castOther.getLogId()!=null && this.getLogId().equals(castOther.getLogId()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getMerchantAcctId()==castOther.getMerchantAcctId()) || ( this.getMerchantAcctId()!=null && castOther.getMerchantAcctId()!=null && this.getMerchantAcctId().equals(castOther.getMerchantAcctId()) ) )
 && ( (this.getVersion()==castOther.getVersion()) || ( this.getVersion()!=null && castOther.getVersion()!=null && this.getVersion().equals(castOther.getVersion()) ) )
 && ( (this.getBillLanguage()==castOther.getBillLanguage()) || ( this.getBillLanguage()!=null && castOther.getBillLanguage()!=null && this.getBillLanguage().equals(castOther.getBillLanguage()) ) )
 && ( (this.getSignType()==castOther.getSignType()) || ( this.getSignType()!=null && castOther.getSignType()!=null && this.getSignType().equals(castOther.getSignType()) ) )
 && ( (this.getPayType()==castOther.getPayType()) || ( this.getPayType()!=null && castOther.getPayType()!=null && this.getPayType().equals(castOther.getPayType()) ) )
 && ( (this.getBankId()==castOther.getBankId()) || ( this.getBankId()!=null && castOther.getBankId()!=null && this.getBankId().equals(castOther.getBankId()) ) )
 && ( (this.getOrderId()==castOther.getOrderId()) || ( this.getOrderId()!=null && castOther.getOrderId()!=null && this.getOrderId().equals(castOther.getOrderId()) ) )
 && ( (this.getOrderTime()==castOther.getOrderTime()) || ( this.getOrderTime()!=null && castOther.getOrderTime()!=null && this.getOrderTime().equals(castOther.getOrderTime()) ) )
 && ( (this.getOrderAmount()==castOther.getOrderAmount()) || ( this.getOrderAmount()!=null && castOther.getOrderAmount()!=null && this.getOrderAmount().equals(castOther.getOrderAmount()) ) )
 && ( (this.getDealId()==castOther.getDealId()) || ( this.getDealId()!=null && castOther.getDealId()!=null && this.getDealId().equals(castOther.getDealId()) ) )
 && ( (this.getBankDealId()==castOther.getBankDealId()) || ( this.getBankDealId()!=null && castOther.getBankDealId()!=null && this.getBankDealId().equals(castOther.getBankDealId()) ) )
 && ( (this.getDealTime()==castOther.getDealTime()) || ( this.getDealTime()!=null && castOther.getDealTime()!=null && this.getDealTime().equals(castOther.getDealTime()) ) )
 && ( (this.getPayAmount()==castOther.getPayAmount()) || ( this.getPayAmount()!=null && castOther.getPayAmount()!=null && this.getPayAmount().equals(castOther.getPayAmount()) ) )
 && ( (this.getFee()==castOther.getFee()) || ( this.getFee()!=null && castOther.getFee()!=null && this.getFee().equals(castOther.getFee()) ) )
 && ( (this.getExt1()==castOther.getExt1()) || ( this.getExt1()!=null && castOther.getExt1()!=null && this.getExt1().equals(castOther.getExt1()) ) )
 && ( (this.getExt2()==castOther.getExt2()) || ( this.getExt2()!=null && castOther.getExt2()!=null && this.getExt2().equals(castOther.getExt2()) ) )
 && ( (this.getPayResult()==castOther.getPayResult()) || ( this.getPayResult()!=null && castOther.getPayResult()!=null && this.getPayResult().equals(castOther.getPayResult()) ) )
 && ( (this.getErrCode()==castOther.getErrCode()) || ( this.getErrCode()!=null && castOther.getErrCode()!=null && this.getErrCode().equals(castOther.getErrCode()) ) )
 && ( (this.getSignMsg()==castOther.getSignMsg()) || ( this.getSignMsg()!=null && castOther.getSignMsg()!=null && this.getSignMsg().equals(castOther.getSignMsg()) ) )
 && ( (this.getUrl()==castOther.getUrl()) || ( this.getUrl()!=null && castOther.getUrl()!=null && this.getUrl().equals(castOther.getUrl()) ) )
 && ( (this.getVerifySignResult()==castOther.getVerifySignResult()) || ( this.getVerifySignResult()!=null && castOther.getVerifySignResult()!=null && this.getVerifySignResult().equals(castOther.getVerifySignResult()) ) )
 && ( (this.getInc()==castOther.getInc()) || ( this.getInc()!=null && castOther.getInc()!=null && this.getInc().equals(castOther.getInc()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getLogId() == null ? 0 : this.getLogId().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getMerchantAcctId() == null ? 0 : this.getMerchantAcctId().hashCode() );
         result = 37 * result + ( getVersion() == null ? 0 : this.getVersion().hashCode() );
         result = 37 * result + ( getBillLanguage() == null ? 0 : this.getBillLanguage().hashCode() );
         result = 37 * result + ( getSignType() == null ? 0 : this.getSignType().hashCode() );
         result = 37 * result + ( getPayType() == null ? 0 : this.getPayType().hashCode() );
         result = 37 * result + ( getBankId() == null ? 0 : this.getBankId().hashCode() );
         result = 37 * result + ( getOrderId() == null ? 0 : this.getOrderId().hashCode() );
         result = 37 * result + ( getOrderTime() == null ? 0 : this.getOrderTime().hashCode() );
         result = 37 * result + ( getOrderAmount() == null ? 0 : this.getOrderAmount().hashCode() );
         result = 37 * result + ( getDealId() == null ? 0 : this.getDealId().hashCode() );
         result = 37 * result + ( getBankDealId() == null ? 0 : this.getBankDealId().hashCode() );
         result = 37 * result + ( getDealTime() == null ? 0 : this.getDealTime().hashCode() );
         result = 37 * result + ( getPayAmount() == null ? 0 : this.getPayAmount().hashCode() );
         result = 37 * result + ( getFee() == null ? 0 : this.getFee().hashCode() );
         result = 37 * result + ( getExt1() == null ? 0 : this.getExt1().hashCode() );
         result = 37 * result + ( getExt2() == null ? 0 : this.getExt2().hashCode() );
         result = 37 * result + ( getPayResult() == null ? 0 : this.getPayResult().hashCode() );
         result = 37 * result + ( getErrCode() == null ? 0 : this.getErrCode().hashCode() );
         result = 37 * result + ( getSignMsg() == null ? 0 : this.getSignMsg().hashCode() );
         result = 37 * result + ( getUrl() == null ? 0 : this.getUrl().hashCode() );
         result = 37 * result + ( getVerifySignResult() == null ? 0 : this.getVerifySignResult().hashCode() );
         result = 37 * result + ( getInc() == null ? 0 : this.getInc().hashCode() );
         return result;
   }   





}
