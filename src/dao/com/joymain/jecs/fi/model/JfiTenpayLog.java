package com.joymain.jecs.fi.model;
// Generated 2011-10-17 11:49:28 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JFI_TENPAY_LOG"
 *     
 */

public class JfiTenpayLog extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long logId;
    private String companyCode;
    private String userCode;
    private String cmdno;
    private String payResult;
    private String payInfo;
    private String tpDate;
    private String purchaserId;
    private String bargainorId;
    private String transactionId;
    private String spBillno;
    private String totalFee;
    private String feeType;
    private String attach;
    private String sign;
    private String busType;
    private String busArgs;
    private String version;
    private String inc;
    private String referer;
    private String returnMsg;
    private Date createTime;
    private String ip;
    private String url;


    // Constructors

    /** default constructor */
    public JfiTenpayLog() {
    }

	/** minimal constructor */
    public JfiTenpayLog(String companyCode, String userCode, String inc, String returnMsg) {
        this.companyCode = companyCode;
        this.userCode = userCode;
        this.inc = inc;
        this.returnMsg = returnMsg;
    }
    
    /** full constructor */
    public JfiTenpayLog(String companyCode, String userCode, String cmdno, String payResult, String payInfo, String tpDate, String purchaserId, String bargainorId, String transactionId, String spBillno, String totalFee, String feeType, String attach, String sign, String busType, String busArgs, String version, String inc, String referer, String returnMsg, Date createTime) {
        this.companyCode = companyCode;
        this.userCode = userCode;
        this.cmdno = cmdno;
        this.payResult = payResult;
        this.payInfo = payInfo;
        this.tpDate = tpDate;
        this.purchaserId = purchaserId;
        this.bargainorId = bargainorId;
        this.transactionId = transactionId;
        this.spBillno = spBillno;
        this.totalFee = totalFee;
        this.feeType = feeType;
        this.attach = attach;
        this.sign = sign;
        this.busType = busType;
        this.busArgs = busArgs;
        this.version = version;
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
     *             column="CMDNO"
     *             length="500"
     *         
     */

    public String getCmdno() {
        return this.cmdno;
    }
    
    public void setCmdno(String cmdno) {
        this.cmdno = cmdno;
    }
    /**       
     *      *            @hibernate.property
     *             column="PAY_RESULT"
     *             length="500"
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
     *             column="PAY_INFO"
     *             length="500"
     *         
     */

    public String getPayInfo() {
        return this.payInfo;
    }
    
    public void setPayInfo(String payInfo) {
        this.payInfo = payInfo;
    }
    /**       
     *      *            @hibernate.property
     *             column="TP_DATE"
     *             length="500"
     *         
     */

    public String getTpDate() {
        return this.tpDate;
    }
    
    public void setTpDate(String tpDate) {
        this.tpDate = tpDate;
    }
    /**       
     *      *            @hibernate.property
     *             column="PURCHASER_ID"
     *             length="500"
     *         
     */

    public String getPurchaserId() {
        return this.purchaserId;
    }
    
    public void setPurchaserId(String purchaserId) {
        this.purchaserId = purchaserId;
    }
    /**       
     *      *            @hibernate.property
     *             column="BARGAINOR_ID"
     *             length="500"
     *         
     */

    public String getBargainorId() {
        return this.bargainorId;
    }
    
    public void setBargainorId(String bargainorId) {
        this.bargainorId = bargainorId;
    }
    /**       
     *      *            @hibernate.property
     *             column="TRANSACTION_ID"
     *             length="500"
     *         
     */

    public String getTransactionId() {
        return this.transactionId;
    }
    
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    /**       
     *      *            @hibernate.property
     *             column="SP_BILLNO"
     *             length="500"
     *         
     */

    public String getSpBillno() {
        return this.spBillno;
    }
    
    public void setSpBillno(String spBillno) {
        this.spBillno = spBillno;
    }
    /**       
     *      *            @hibernate.property
     *             column="TOTAL_FEE"
     *             length="500"
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
     *             column="FEE_TYPE"
     *             length="500"
     *         
     */

    public String getFeeType() {
        return this.feeType;
    }
    
    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }
    /**       
     *      *            @hibernate.property
     *             column="ATTACH"
     *             length="500"
     *         
     */

    public String getAttach() {
        return this.attach;
    }
    
    public void setAttach(String attach) {
        this.attach = attach;
    }
    /**       
     *      *            @hibernate.property
     *             column="SIGN"
     *             length="500"
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
     *             column="BUS_TYPE"
     *             length="500"
     *         
     */

    public String getBusType() {
        return this.busType;
    }
    
    public void setBusType(String busType) {
        this.busType = busType;
    }
    /**       
     *      *            @hibernate.property
     *             column="BUS_ARGS"
     *             length="500"
     *         
     */

    public String getBusArgs() {
        return this.busArgs;
    }
    
    public void setBusArgs(String busArgs) {
        this.busArgs = busArgs;
    }
    /**       
     *      *            @hibernate.property
     *             column="VERSION"
     *             length="500"
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
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("cmdno").append("='").append(getCmdno()).append("' ");			
      buffer.append("payResult").append("='").append(getPayResult()).append("' ");			
      buffer.append("payInfo").append("='").append(getPayInfo()).append("' ");			
      buffer.append("tpDate").append("='").append(getTpDate()).append("' ");			
      buffer.append("purchaserId").append("='").append(getPurchaserId()).append("' ");			
      buffer.append("bargainorId").append("='").append(getBargainorId()).append("' ");			
      buffer.append("transactionId").append("='").append(getTransactionId()).append("' ");			
      buffer.append("spBillno").append("='").append(getSpBillno()).append("' ");			
      buffer.append("totalFee").append("='").append(getTotalFee()).append("' ");			
      buffer.append("feeType").append("='").append(getFeeType()).append("' ");			
      buffer.append("attach").append("='").append(getAttach()).append("' ");			
      buffer.append("sign").append("='").append(getSign()).append("' ");			
      buffer.append("busType").append("='").append(getBusType()).append("' ");			
      buffer.append("busArgs").append("='").append(getBusArgs()).append("' ");			
      buffer.append("version").append("='").append(getVersion()).append("' ");			
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
		 if ( !(other instanceof JfiTenpayLog) ) return false;
		 JfiTenpayLog castOther = ( JfiTenpayLog ) other; 
         
		 return ( (this.getLogId()==castOther.getLogId()) || ( this.getLogId()!=null && castOther.getLogId()!=null && this.getLogId().equals(castOther.getLogId()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getCmdno()==castOther.getCmdno()) || ( this.getCmdno()!=null && castOther.getCmdno()!=null && this.getCmdno().equals(castOther.getCmdno()) ) )
 && ( (this.getPayResult()==castOther.getPayResult()) || ( this.getPayResult()!=null && castOther.getPayResult()!=null && this.getPayResult().equals(castOther.getPayResult()) ) )
 && ( (this.getPayInfo()==castOther.getPayInfo()) || ( this.getPayInfo()!=null && castOther.getPayInfo()!=null && this.getPayInfo().equals(castOther.getPayInfo()) ) )
 && ( (this.getTpDate()==castOther.getTpDate()) || ( this.getTpDate()!=null && castOther.getTpDate()!=null && this.getTpDate().equals(castOther.getTpDate()) ) )
 && ( (this.getPurchaserId()==castOther.getPurchaserId()) || ( this.getPurchaserId()!=null && castOther.getPurchaserId()!=null && this.getPurchaserId().equals(castOther.getPurchaserId()) ) )
 && ( (this.getBargainorId()==castOther.getBargainorId()) || ( this.getBargainorId()!=null && castOther.getBargainorId()!=null && this.getBargainorId().equals(castOther.getBargainorId()) ) )
 && ( (this.getTransactionId()==castOther.getTransactionId()) || ( this.getTransactionId()!=null && castOther.getTransactionId()!=null && this.getTransactionId().equals(castOther.getTransactionId()) ) )
 && ( (this.getSpBillno()==castOther.getSpBillno()) || ( this.getSpBillno()!=null && castOther.getSpBillno()!=null && this.getSpBillno().equals(castOther.getSpBillno()) ) )
 && ( (this.getTotalFee()==castOther.getTotalFee()) || ( this.getTotalFee()!=null && castOther.getTotalFee()!=null && this.getTotalFee().equals(castOther.getTotalFee()) ) )
 && ( (this.getFeeType()==castOther.getFeeType()) || ( this.getFeeType()!=null && castOther.getFeeType()!=null && this.getFeeType().equals(castOther.getFeeType()) ) )
 && ( (this.getAttach()==castOther.getAttach()) || ( this.getAttach()!=null && castOther.getAttach()!=null && this.getAttach().equals(castOther.getAttach()) ) )
 && ( (this.getSign()==castOther.getSign()) || ( this.getSign()!=null && castOther.getSign()!=null && this.getSign().equals(castOther.getSign()) ) )
 && ( (this.getBusType()==castOther.getBusType()) || ( this.getBusType()!=null && castOther.getBusType()!=null && this.getBusType().equals(castOther.getBusType()) ) )
 && ( (this.getBusArgs()==castOther.getBusArgs()) || ( this.getBusArgs()!=null && castOther.getBusArgs()!=null && this.getBusArgs().equals(castOther.getBusArgs()) ) )
 && ( (this.getVersion()==castOther.getVersion()) || ( this.getVersion()!=null && castOther.getVersion()!=null && this.getVersion().equals(castOther.getVersion()) ) )
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
         result = 37 * result + ( getCmdno() == null ? 0 : this.getCmdno().hashCode() );
         result = 37 * result + ( getPayResult() == null ? 0 : this.getPayResult().hashCode() );
         result = 37 * result + ( getPayInfo() == null ? 0 : this.getPayInfo().hashCode() );
         result = 37 * result + ( getTpDate() == null ? 0 : this.getTpDate().hashCode() );
         result = 37 * result + ( getPurchaserId() == null ? 0 : this.getPurchaserId().hashCode() );
         result = 37 * result + ( getBargainorId() == null ? 0 : this.getBargainorId().hashCode() );
         result = 37 * result + ( getTransactionId() == null ? 0 : this.getTransactionId().hashCode() );
         result = 37 * result + ( getSpBillno() == null ? 0 : this.getSpBillno().hashCode() );
         result = 37 * result + ( getTotalFee() == null ? 0 : this.getTotalFee().hashCode() );
         result = 37 * result + ( getFeeType() == null ? 0 : this.getFeeType().hashCode() );
         result = 37 * result + ( getAttach() == null ? 0 : this.getAttach().hashCode() );
         result = 37 * result + ( getSign() == null ? 0 : this.getSign().hashCode() );
         result = 37 * result + ( getBusType() == null ? 0 : this.getBusType().hashCode() );
         result = 37 * result + ( getBusArgs() == null ? 0 : this.getBusArgs().hashCode() );
         result = 37 * result + ( getVersion() == null ? 0 : this.getVersion().hashCode() );
         result = 37 * result + ( getInc() == null ? 0 : this.getInc().hashCode() );
         result = 37 * result + ( getReferer() == null ? 0 : this.getReferer().hashCode() );
         result = 37 * result + ( getReturnMsg() == null ? 0 : this.getReturnMsg().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         return result;
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





}
