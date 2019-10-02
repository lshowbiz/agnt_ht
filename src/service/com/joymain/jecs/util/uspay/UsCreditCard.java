package com.joymain.jecs.util.uspay;

public class UsCreditCard {
	private String type;
	private String username;
	private String password;
	private String ccnumber;
	private String ccexp;
	private String amount;
	private String cvv;
	private String payment;
	private String firstname;
	private String lastname;
	private String address1;
	private String city;
	private String state;
	private String zip;
	private String country;
	private String phone;
	private String email;
	private String inc;
	
	/////////////////////////////////
	
	private String response;
	private String responseText;
	private String authCode;
	private String transactionId;
	private String avsResponse;
	private String cvvResponse;
	private String orderId;
	private String typeResponse;
	private String responseCode;
	private String responseStr;
	
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCcexp() {
		return ccexp;
	}
	public void setCcexp(String ccexp) {
		this.ccexp = ccexp;
	}
	public String getCcnumber() {
		return ccnumber;
	}
	public void setCcnumber(String ccnumber) {
		this.ccnumber = ccnumber;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	public String getAvsResponse() {
		return avsResponse;
	}
	public void setAvsResponse(String avsResponse) {
		this.avsResponse = avsResponse;
	}
	public String getCvvResponse() {
		return cvvResponse;
	}
	public void setCvvResponse(String cvvResponse) {
		this.cvvResponse = cvvResponse;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseText() {
		return responseText;
	}
	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getTypeResponse() {
		return typeResponse;
	}
	public void setTypeResponse(String typeResponse) {
		this.typeResponse = typeResponse;
	}
	public String getResponseStr() {
		return responseStr;
	}
	public void setResponseStr(String responseStr) {
		this.responseStr = responseStr;
	}
	public String getInc() {
		return inc;
	}
	public void setInc(String inc) {
		this.inc = inc;
	}
}
