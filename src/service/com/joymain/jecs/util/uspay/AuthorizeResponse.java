package com.joymain.jecs.util.uspay;

import java.util.Vector;

public class AuthorizeResponse {
	private static char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	private String originalText;
	private String responseCode;
	private String responseReasonText;
	private String authorizationCode;
	private String transactionId;
	private String md5Hash;

	public AuthorizeResponse(String originalText) {
		this.originalText = originalText;
		Vector ccrep = split("|", this.originalText);
		//封装结果
		if(ccrep.elementAt(0)!=null){
			this.responseCode=ccrep.elementAt(0).toString();
		}
		if(ccrep.elementAt(3)!=null){
			this.responseReasonText=ccrep.elementAt(3).toString();
		}
		
		if(ccrep.elementAt(4)!=null){
			this.authorizationCode=ccrep.elementAt(4).toString();
		}
		
		if(ccrep.elementAt(6)!=null){
			this.transactionId=ccrep.elementAt(6).toString();
		}
		
		if(ccrep.elementAt(37)!=null){
			this.md5Hash=ccrep.elementAt(37).toString();
		}
	}

	public String getOriginalText() {
		return originalText;
	}

	public void setOriginalText(String originalText) {
		this.originalText = originalText;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseReasonText() {
		return responseReasonText;
	}

	public void setResponseReasonText(String responseReasonText) {
		this.responseReasonText = responseReasonText;
	}

	public String getAuthorizationCode() {
		return authorizationCode;
	}

	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getMd5Hash() {
		return md5Hash;
	}

	public void setMd5Hash(String md5Hash) {
		this.md5Hash = md5Hash;
	}

	/**
	 * 分割字符串成Vector类型
	 * @param pattern
	 * @param in
	 * @return
	 */
	private Vector split(String pattern, String in) {
		int s1 = 0, s2 = -1;
		Vector out = new Vector(30);
		while (true) {
			s2 = in.indexOf(pattern, s1);
			if (s2 != -1) {
				out.addElement(in.substring(s1, s2));
			} else {
				//the end part of the string (string not pattern terminated)
				String _ = in.substring(s1);
				if (_ != null && !_.equals("")) {
					out.addElement(_);
				}
				break;
			}
			s1 = s2;
			s1 += pattern.length();
		}
		return out;
	}

	private String toHexString(byte[] b) {
		StringBuffer sb = new StringBuffer(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			// look up high nibble char
			sb.append(hexChar[(b[i] & 0xf0) >>> 4]);

			// look up low nibble char
			sb.append(hexChar[b[i] & 0x0f]);
		}
		return sb.toString();
	}
}
