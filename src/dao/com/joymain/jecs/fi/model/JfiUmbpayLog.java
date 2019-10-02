package com.joymain.jecs.fi.model;
// Generated 2015-6-29 16:17:29 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JFI_UMBPAY_LOG"
 *     
 */

@SuppressWarnings("serial")
public class JfiUmbpayLog extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {

	
	// Fields    

    private Long logId;
    private String companyCode;
    private String userCode;
    private String signType;
    private String payType;
    private String payorderid;
    private String merchantid;
    private String merorderid;
    private String amountsum;
    private String currencytype;
    private String subject;
    private String returnState;
    private String paybank;
    private String banksendtime;
    private String merrecvtime;
    private String version;
    private String merkey;
    private String mac;
    private String remark;
    private String url;
    private String inc;
    private String ip;
    private String dataType;
    private String returnMsg;
    private Date createTime;

    


	/** default constructor */
    public JfiUmbpayLog() {
    }

	/** minimal constructor */
    public JfiUmbpayLog(String companyCode, String userCode, String inc) {
        this.companyCode = companyCode;
        this.userCode = userCode;
        this.inc = inc;
    }
    
    /** full constructor */
    public JfiUmbpayLog(String companyCode, String userCode, String signType, String payType, String payorderid, String merchantid, String merorderid, String amountsum, String currencytype, String subject, String returnState, String paybank, String banksendtime, String merrecvtime, String version, String merkey, String mac, String remark, String url, String inc, String ip, String dataType) {
        this.companyCode = companyCode;
        this.userCode = userCode;
        this.signType = signType;
        this.payType = payType;
        this.payorderid = payorderid;
        this.merchantid = merchantid;
        this.merorderid = merorderid;
        this.amountsum = amountsum;
        this.currencytype = currencytype;
        this.subject = subject;
        this.returnState = returnState;
        this.paybank = paybank;
        this.banksendtime = banksendtime;
        this.merrecvtime = merrecvtime;
        this.version = version;
        this.merkey = merkey;
        this.mac = mac;
        this.remark = remark;
        this.url = url;
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
     *             column="SIGN_TYPE"
     *             length="255"
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
     *             length="255"
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
     *             column="PAYORDERID"
     *             length="255"
     *         
     */

    public String getPayorderid() {
        return this.payorderid;
    }
    
    public void setPayorderid(String payorderid) {
        this.payorderid = payorderid;
    }
    /**       
     *      *            @hibernate.property
     *             column="MERCHANTID"
     *             length="255"
     *         
     */

    public String getMerchantid() {
        return this.merchantid;
    }
    
    public void setMerchantid(String merchantid) {
        this.merchantid = merchantid;
    }
    /**       
     *      *            @hibernate.property
     *             column="MERORDERID"
     *             length="255"
     *         
     */

    public String getMerorderid() {
        return this.merorderid;
    }
    
    public void setMerorderid(String merorderid) {
        this.merorderid = merorderid;
    }
    /**       
     *      *            @hibernate.property
     *             column="AMOUNTSUM"
     *             length="255"
     *         
     */

    public String getAmountsum() {
        return this.amountsum;
    }
    
    public void setAmountsum(String amountsum) {
        this.amountsum = amountsum;
    }
    /**       
     *      *            @hibernate.property
     *             column="CURRENCYTYPE"
     *             length="255"
     *         
     */

    public String getCurrencytype() {
        return this.currencytype;
    }
    
    public void setCurrencytype(String currencytype) {
        this.currencytype = currencytype;
    }
    /**       
     *      *            @hibernate.property
     *             column="SUBJECT"
     *             length="255"
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
     *             column="RETURN_STATE"
     *             length="255"
     *         
     */

    public String getReturnState() {
        return this.returnState;
    }
    
    public void setReturnState(String returnState) {
        this.returnState = returnState;
    }
    /**       
     *      *            @hibernate.property
     *             column="PAYBANK"
     *             length="255"
     *         
     */

    public String getPaybank() {
        return this.paybank;
    }
    
    public void setPaybank(String paybank) {
        this.paybank = paybank;
    }
    /**       
     *      *            @hibernate.property
     *             column="BANKSENDTIME"
     *             length="7"
     *         
     */

    public String getBanksendtime() {
        return this.banksendtime;
    }
    
    public void setBanksendtime(String banksendtime) {
        this.banksendtime = banksendtime;
    }
    /**       
     *      *            @hibernate.property
     *             column="MERRECVTIME"
     *             length="7"
     *         
     */

    public String getMerrecvtime() {
        return this.merrecvtime;
    }
    
    public void setMerrecvtime(String merrecvtime) {
        this.merrecvtime = merrecvtime;
    }
    /**       
     *      *            @hibernate.property
     *             column="VERSION"
     *             length="255"
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
     *             column="MERKEY"
     *             length="255"
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
     *             column="MAC"
     *             length="4000"
     *         
     */

    public String getMac() {
        return this.mac;
    }
    
    public void setMac(String mac) {
        this.mac = mac;
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
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("signType").append("='").append(getSignType()).append("' ");			
      buffer.append("payType").append("='").append(getPayType()).append("' ");			
      buffer.append("payorderid").append("='").append(getPayorderid()).append("' ");			
      buffer.append("merchantid").append("='").append(getMerchantid()).append("' ");			
      buffer.append("merorderid").append("='").append(getMerorderid()).append("' ");			
      buffer.append("amountsum").append("='").append(getAmountsum()).append("' ");			
      buffer.append("currencytype").append("='").append(getCurrencytype()).append("' ");			
      buffer.append("subject").append("='").append(getSubject()).append("' ");			
      buffer.append("returnState").append("='").append(getReturnState()).append("' ");			
      buffer.append("paybank").append("='").append(getPaybank()).append("' ");			
      buffer.append("banksendtime").append("='").append(getBanksendtime()).append("' ");			
      buffer.append("merrecvtime").append("='").append(getMerrecvtime()).append("' ");			
      buffer.append("version").append("='").append(getVersion()).append("' ");			
      buffer.append("merkey").append("='").append(getMerkey()).append("' ");			
      buffer.append("mac").append("='").append(getMac()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");			
      buffer.append("url").append("='").append(getUrl()).append("' ");			
      buffer.append("inc").append("='").append(getInc()).append("' ");			
      buffer.append("ip").append("='").append(getIp()).append("' ");			
      buffer.append("dataType").append("='").append(getDataType()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JfiUmbpayLog) ) return false;
		 JfiUmbpayLog castOther = ( JfiUmbpayLog ) other; 
         
		 return ( (this.getLogId()==castOther.getLogId()) || ( this.getLogId()!=null && castOther.getLogId()!=null && this.getLogId().equals(castOther.getLogId()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getSignType()==castOther.getSignType()) || ( this.getSignType()!=null && castOther.getSignType()!=null && this.getSignType().equals(castOther.getSignType()) ) )
 && ( (this.getPayType()==castOther.getPayType()) || ( this.getPayType()!=null && castOther.getPayType()!=null && this.getPayType().equals(castOther.getPayType()) ) )
 && ( (this.getPayorderid()==castOther.getPayorderid()) || ( this.getPayorderid()!=null && castOther.getPayorderid()!=null && this.getPayorderid().equals(castOther.getPayorderid()) ) )
 && ( (this.getMerchantid()==castOther.getMerchantid()) || ( this.getMerchantid()!=null && castOther.getMerchantid()!=null && this.getMerchantid().equals(castOther.getMerchantid()) ) )
 && ( (this.getMerorderid()==castOther.getMerorderid()) || ( this.getMerorderid()!=null && castOther.getMerorderid()!=null && this.getMerorderid().equals(castOther.getMerorderid()) ) )
 && ( (this.getAmountsum()==castOther.getAmountsum()) || ( this.getAmountsum()!=null && castOther.getAmountsum()!=null && this.getAmountsum().equals(castOther.getAmountsum()) ) )
 && ( (this.getCurrencytype()==castOther.getCurrencytype()) || ( this.getCurrencytype()!=null && castOther.getCurrencytype()!=null && this.getCurrencytype().equals(castOther.getCurrencytype()) ) )
 && ( (this.getSubject()==castOther.getSubject()) || ( this.getSubject()!=null && castOther.getSubject()!=null && this.getSubject().equals(castOther.getSubject()) ) )
 && ( (this.getReturnState()==castOther.getReturnState()) || ( this.getReturnState()!=null && castOther.getReturnState()!=null && this.getReturnState().equals(castOther.getReturnState()) ) )
 && ( (this.getPaybank()==castOther.getPaybank()) || ( this.getPaybank()!=null && castOther.getPaybank()!=null && this.getPaybank().equals(castOther.getPaybank()) ) )
 && ( (this.getBanksendtime()==castOther.getBanksendtime()) || ( this.getBanksendtime()!=null && castOther.getBanksendtime()!=null && this.getBanksendtime().equals(castOther.getBanksendtime()) ) )
 && ( (this.getMerrecvtime()==castOther.getMerrecvtime()) || ( this.getMerrecvtime()!=null && castOther.getMerrecvtime()!=null && this.getMerrecvtime().equals(castOther.getMerrecvtime()) ) )
 && ( (this.getVersion()==castOther.getVersion()) || ( this.getVersion()!=null && castOther.getVersion()!=null && this.getVersion().equals(castOther.getVersion()) ) )
 && ( (this.getMerkey()==castOther.getMerkey()) || ( this.getMerkey()!=null && castOther.getMerkey()!=null && this.getMerkey().equals(castOther.getMerkey()) ) )
 && ( (this.getMac()==castOther.getMac()) || ( this.getMac()!=null && castOther.getMac()!=null && this.getMac().equals(castOther.getMac()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
 && ( (this.getUrl()==castOther.getUrl()) || ( this.getUrl()!=null && castOther.getUrl()!=null && this.getUrl().equals(castOther.getUrl()) ) )
 && ( (this.getInc()==castOther.getInc()) || ( this.getInc()!=null && castOther.getInc()!=null && this.getInc().equals(castOther.getInc()) ) )
 && ( (this.getIp()==castOther.getIp()) || ( this.getIp()!=null && castOther.getIp()!=null && this.getIp().equals(castOther.getIp()) ) )
 && ( (this.getDataType()==castOther.getDataType()) || ( this.getDataType()!=null && castOther.getDataType()!=null && this.getDataType().equals(castOther.getDataType()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getLogId() == null ? 0 : this.getLogId().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getSignType() == null ? 0 : this.getSignType().hashCode() );
         result = 37 * result + ( getPayType() == null ? 0 : this.getPayType().hashCode() );
         result = 37 * result + ( getPayorderid() == null ? 0 : this.getPayorderid().hashCode() );
         result = 37 * result + ( getMerchantid() == null ? 0 : this.getMerchantid().hashCode() );
         result = 37 * result + ( getMerorderid() == null ? 0 : this.getMerorderid().hashCode() );
         result = 37 * result + ( getAmountsum() == null ? 0 : this.getAmountsum().hashCode() );
         result = 37 * result + ( getCurrencytype() == null ? 0 : this.getCurrencytype().hashCode() );
         result = 37 * result + ( getSubject() == null ? 0 : this.getSubject().hashCode() );
         result = 37 * result + ( getReturnState() == null ? 0 : this.getReturnState().hashCode() );
         result = 37 * result + ( getPaybank() == null ? 0 : this.getPaybank().hashCode() );
         result = 37 * result + ( getBanksendtime() == null ? 0 : this.getBanksendtime().hashCode() );
         result = 37 * result + ( getMerrecvtime() == null ? 0 : this.getMerrecvtime().hashCode() );
         result = 37 * result + ( getVersion() == null ? 0 : this.getVersion().hashCode() );
         result = 37 * result + ( getMerkey() == null ? 0 : this.getMerkey().hashCode() );
         result = 37 * result + ( getMac() == null ? 0 : this.getMac().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         result = 37 * result + ( getUrl() == null ? 0 : this.getUrl().hashCode() );
         result = 37 * result + ( getInc() == null ? 0 : this.getInc().hashCode() );
         result = 37 * result + ( getIp() == null ? 0 : this.getIp().hashCode() );
         result = 37 * result + ( getDataType() == null ? 0 : this.getDataType().hashCode() );
         return result;
   }   





}
