package com.smsgate.server.jms.model;

public class SmsSendLog implements java.io.Serializable {

	private static final long serialVersionUID = -123456789098765L;
	private Long sslId;
	private String mobile;
	private String sendMsg;
	private String status;
	private String sendNum;
	private String userCode;

	// Constructors

	/** default constructor */
	public SmsSendLog() {
	}

	/** full constructor */
	public SmsSendLog(Long sslId, String mobile, String sendMsg, String status,
			String sendNum) {
		this.sslId = sslId;
		this.mobile = mobile;
		this.sendMsg = sendMsg;
		this.status = status;
		this.sendNum = sendNum;
	}

	public Long getSslId() {
		return this.sslId;
	}

	public void setSslId(Long sslId) {
		this.sslId = sslId;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSendMsg() {
		return this.sendMsg;
	}

	public void setSendMsg(String sendMsg) {
		this.sendMsg = sendMsg;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSendNum() {
		return this.sendNum;
	}

	public void setSendNum(String sendNum) {
		this.sendNum = sendNum;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((sendMsg == null) ? 0 : sendMsg.hashCode());
		result = prime * result + ((sendNum == null) ? 0 : sendNum.hashCode());
		result = prime * result + ((sslId == null) ? 0 : sslId.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((userCode == null) ? 0 : userCode.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SmsSendLog other = (SmsSendLog) obj;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (sendMsg == null) {
			if (other.sendMsg != null)
				return false;
		} else if (!sendMsg.equals(other.sendMsg))
			return false;
		if (sendNum == null) {
			if (other.sendNum != null)
				return false;
		} else if (!sendNum.equals(other.sendNum))
			return false;
		if (sslId == null) {
			if (other.sslId != null)
				return false;
		} else if (!sslId.equals(other.sslId))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (userCode == null) {
			if (other.userCode != null)
				return false;
		} else if (!userCode.equals(other.userCode))
			return false;
		return true;
	}

	public String toString() {
		return "SmsSendLog [mobile=" + mobile + ", sendMsg=" + sendMsg
				+ ", sendNum=" + sendNum + ", sslId=" + sslId + ", status="
				+ status + ", userCode=" + userCode + "]";
	}

}