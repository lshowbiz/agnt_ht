package com.joymain.jecs.fi.model;
// Generated 2014-7-30 10:56:09 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JFI_CHINAPAY_POS_LOG"
 *     
 */

public class JfiChinapayPosLog extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // 银联POS支付实体    

    private Long logId;
    private String payDealCode;//交易参考号
    private BigDecimal payAmount;//金额
    private BigDecimal fee;//手续费
    private String userCode;
    private String mobileNumber;//手机号码
    private String ext2;
    private String payResult;//支付结果
    private String inc;//是否进存折 1：进
    private String returnMsg;//验签结果，10：验签成功，0：数据被篡改，1：扣款失败，3：数据重复发送 ，4：验签不通过 
    private Date createTime;
    private String errCode;
    private String companyCode;
    private String company;
    private String isBack;
    
	/**交易卡号*/
    private String transCard;

    /**终端号*/
    private String terminalNo;
    
    /**检索参考号*/
    private String transRetrievalNo;
    
    /**POS终端交易流水号*/
    private String transSerialNo;
    
    /**MD5加密的签名说明*/
    private String signData;
    
    /**交易类型：1.扫码支付2.会员号支付3.手机号支付*/
    private String msgCode;
    
    /**商户号*/
    private String merchantNo;
    
    /**交易时间*/
    private Date transTime;

    // Constructors

    /** default constructor */
    public JfiChinapayPosLog() {
    }

	/** minimal constructor */
    public JfiChinapayPosLog(String payDealCode, BigDecimal payAmount, String userCode) {
        this.payDealCode = payDealCode;
        this.payAmount = payAmount;
        this.userCode = userCode;
    }
    
    /** full constructor */
    public JfiChinapayPosLog(String payDealCode, BigDecimal payAmount, BigDecimal fee, String userCode, String mobileNumber, String ext2, String payResult, String inc, String returnMsg, Date createTime, String errCode, String companyCode,String company,String isBack) {
        this.payDealCode = payDealCode;
        this.payAmount = payAmount;
        this.fee = fee;
        this.userCode = userCode;
        this.mobileNumber = mobileNumber;
        this.ext2 = ext2;
        this.payResult = payResult;
        this.inc = inc;
        this.returnMsg = returnMsg;
        this.createTime = createTime;
        this.errCode = errCode;
        this.companyCode = companyCode;
        this.company = company;
        this.isBack = isBack;
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
     *             column="COMPANY"
     *             length="2"
     *         
     */

    public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
    /**       
     *      *            @hibernate.property
     *             column="PAY_DEAL_CODE"
     *             length="100"
     *             not-null="true"
     *         
     */

    public String getPayDealCode() {
        return this.payDealCode;
    }
    
    public void setPayDealCode(String payDealCode) {
        this.payDealCode = payDealCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="PAY_AMOUNT"
     *             length="18"
     *             not-null="true"
     *         
     */

    public BigDecimal getPayAmount() {
        return this.payAmount;
    }
    
    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }
    /**       
     *      *            @hibernate.property
     *             column="FEE"
     *             length="18"
     *         
     */

    public BigDecimal getFee() {
        return this.fee;
    }
    
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }
    /**       
     *      *            @hibernate.property
     *             column="USER_CODE"
     *             length="30"
     */

    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="MOBILE_NUMBER"
     *             length="30"
     *         
     */

    public String getMobileNumber() {
        return this.mobileNumber;
    }
    
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    /**       
     *      *            @hibernate.property
     *             column="EXT2"
     *             length="1000"
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
     *             length="30"
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
     *             column="ERR_CODE"
     *             length="30"
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
     *             column="TRANSCARD"
     *             length="100"
     *         
     */
    public String getTransCard()
    {
        return transCard;
    }

    public void setTransCard(String transCard)
    {
        this.transCard = transCard;
    }

    /**       
     *      *            @hibernate.property
     *             column="TERMINALNO"
     *             length="100"
     *         
     */
    public String getTerminalNo()
    {
        return terminalNo;
    }

    public void setTerminalNo(String terminalNo)
    {
        this.terminalNo = terminalNo;
    }

    /**       
     *      *            @hibernate.property
     *             column="TRANS_RETRIEVAL_NO"
     *             length="100"
     *         
     */
    public String getTransRetrievalNo()
    {
        return transRetrievalNo;
    }

    public void setTransRetrievalNo(String transRetrievalNo)
    {
        this.transRetrievalNo = transRetrievalNo;
    }

    /**       
     *      *            @hibernate.property
     *             column="TRANS_SERIA_NO"
     *             length="100"
     *         
     */
    public String getTransSerialNo()
    {
        return transSerialNo;
    }

    public void setTransSerialNo(String transSerialNo)
    {
        this.transSerialNo = transSerialNo;
    }

    public String getSignData()
    {
        return signData;
    }

    public void setSignData(String signData)
    {
        this.signData = signData;
    }
    
    /**       
     *      *            @hibernate.property
     *             column="MSG_CODE"
     *             length="100"
     *         
     */
    public String getMsgCode()
    {
        return msgCode;
    }

    public void setMsgCode(String msgCode)
    {
        this.msgCode = msgCode;
    }

    /**       
     *      *            @hibernate.property
     *             column="MERCHANT_NO"
     *             length="100"
     *         
     */
    public String getMerchantNo()
    {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo)
    {
        this.merchantNo = merchantNo;
    }

    /**       
     *      *            @hibernate.property
     *             column="TRANS_TIME"
     *             length="7"
     *         
     */
    public Date getTransTime()
    {
        return transTime;
    }

    public void setTransTime(Date transTime)
    {
        this.transTime = transTime;
    }

    /**       
     *      *            @hibernate.property
     *             column="isBack"
     *             length="2"
     */

    public String getIsBack() {
        return this.isBack;
    }
    
    public void setIsBack(String isBack) {
        this.isBack = isBack;
    }
    
    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("payDealCode").append("='").append(getPayDealCode()).append("' ");			
      buffer.append("payAmount").append("='").append(getPayAmount()).append("' ");			
      buffer.append("fee").append("='").append(getFee()).append("' ");			
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("mobileNumber").append("='").append(getMobileNumber()).append("' ");			
      buffer.append("ext2").append("='").append(getExt2()).append("' ");			
      buffer.append("payResult").append("='").append(getPayResult()).append("' ");			
      buffer.append("inc").append("='").append(getInc()).append("' ");			
      buffer.append("returnMsg").append("='").append(getReturnMsg()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("errCode").append("='").append(getErrCode()).append("' ");			
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JfiChinapayPosLog) ) return false;
		 JfiChinapayPosLog castOther = ( JfiChinapayPosLog ) other; 
         
		 return ( (this.getLogId()==castOther.getLogId()) || ( this.getLogId()!=null && castOther.getLogId()!=null && this.getLogId().equals(castOther.getLogId()) ) )
 && ( (this.getPayDealCode()==castOther.getPayDealCode()) || ( this.getPayDealCode()!=null && castOther.getPayDealCode()!=null && this.getPayDealCode().equals(castOther.getPayDealCode()) ) )
 && ( (this.getPayAmount()==castOther.getPayAmount()) || ( this.getPayAmount()!=null && castOther.getPayAmount()!=null && this.getPayAmount().equals(castOther.getPayAmount()) ) )
 && ( (this.getFee()==castOther.getFee()) || ( this.getFee()!=null && castOther.getFee()!=null && this.getFee().equals(castOther.getFee()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getMobileNumber()==castOther.getMobileNumber()) || ( this.getMobileNumber()!=null && castOther.getMobileNumber()!=null && this.getMobileNumber().equals(castOther.getMobileNumber()) ) )
 && ( (this.getExt2()==castOther.getExt2()) || ( this.getExt2()!=null && castOther.getExt2()!=null && this.getExt2().equals(castOther.getExt2()) ) )
 && ( (this.getPayResult()==castOther.getPayResult()) || ( this.getPayResult()!=null && castOther.getPayResult()!=null && this.getPayResult().equals(castOther.getPayResult()) ) )
 && ( (this.getInc()==castOther.getInc()) || ( this.getInc()!=null && castOther.getInc()!=null && this.getInc().equals(castOther.getInc()) ) )
 && ( (this.getReturnMsg()==castOther.getReturnMsg()) || ( this.getReturnMsg()!=null && castOther.getReturnMsg()!=null && this.getReturnMsg().equals(castOther.getReturnMsg()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getErrCode()==castOther.getErrCode()) || ( this.getErrCode()!=null && castOther.getErrCode()!=null && this.getErrCode().equals(castOther.getErrCode()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getLogId() == null ? 0 : this.getLogId().hashCode() );
         result = 37 * result + ( getPayDealCode() == null ? 0 : this.getPayDealCode().hashCode() );
         result = 37 * result + ( getPayAmount() == null ? 0 : this.getPayAmount().hashCode() );
         result = 37 * result + ( getFee() == null ? 0 : this.getFee().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getMobileNumber() == null ? 0 : this.getMobileNumber().hashCode() );
         result = 37 * result + ( getExt2() == null ? 0 : this.getExt2().hashCode() );
         result = 37 * result + ( getPayResult() == null ? 0 : this.getPayResult().hashCode() );
         result = 37 * result + ( getInc() == null ? 0 : this.getInc().hashCode() );
         result = 37 * result + ( getReturnMsg() == null ? 0 : this.getReturnMsg().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getErrCode() == null ? 0 : this.getErrCode().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         return result;
   }   





}
