package com.joymain.jecs.util.bill99ms;


public class Bill99ms implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String postUrl;
	private String encoding;
	private String inputCharset;
	private String pageUrl;
	private String bgUrl;
    private String version;
    private String language;
    private String signType;
    private String payeeContactType;
    private String payeeContact;
    private String payerContactType;
    private String payerContact;
    private String orderId;
    private String payeeAmount;
    private String orderAmount;
    private String orderTime;
    private String ext1;
    private String ext2;
    private String payType;
    private String bankId;
    private String pid = "";
    private String sharingData;
    private String sharingPayFlag;

    private String productName;
    private String productNum;
    private String productDesc;
    private String payerName;
    
    private String memberCode;
    private String signMsg;

	public void setPostUrl(String postUrl) {
		this.postUrl = postUrl;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	public void setInputCharset(String inputCharset) {
		this.inputCharset = inputCharset;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	public void setBgUrl(String bgUrl) {
		this.bgUrl = bgUrl;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	public void setPayerContactType(String payerContactType) {
		this.payerContactType = payerContactType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPostUrl() {
		return postUrl;
	}
	public String getEncoding() {
		return encoding;
	}
	public String getInputCharset() {
		return inputCharset;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public String getBgUrl() {
		return bgUrl;
	}
	public String getVersion() {
		return version;
	}
	public String getLanguage() {
		return language;
	}
	public String getSignType() {
		return signType;
	}
	public String getMemberCode() {
		return memberCode;
	}
	public String getPayerContactType() {
		return payerContactType;
	}
	public String getPayType() {
		return payType;
	}
	public String getPid() {
		return pid;
	}
	public String getSignMsg() {
		return this.signMsg;
	}
	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}
	public String getPayerName() {
		return payerName;
	}
	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}
	public String getPayerContact() {
		return payerContact;
	}
	public void setPayerContact(String payerContact) {
		this.payerContact = payerContact;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductNum() {
		return productNum;
	}
	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getExt1() {
		return ext1;
	}
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
	public String getExt2() {
		return ext2;
	}
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getPayeeAmount() {
		return payeeAmount;
	}
	public void setPayeeAmount(String payeeAmount) {
		this.payeeAmount = payeeAmount;
	}
	public String getPayeeContact() {
		return payeeContact;
	}
	public void setPayeeContact(String payeeContact) {
		this.payeeContact = payeeContact;
	}
	public String getPayeeContactType() {
		return payeeContactType;
	}
	public void setPayeeContactType(String payeeContactType) {
		this.payeeContactType = payeeContactType;
	}
	public String getSharingData() {
		return sharingData;
	}
	public void setSharingData(String sharingData) {
		this.sharingData = sharingData;
	}
	public String getSharingPayFlag() {
		return sharingPayFlag;
	}
	public void setSharingPayFlag(String sharingPayFlag) {
		this.sharingPayFlag = sharingPayFlag;
	}
	public String getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}
}
