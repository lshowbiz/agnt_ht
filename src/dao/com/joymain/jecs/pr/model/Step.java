package com.joymain.jecs.pr.model;

/**
 * 接收物流实时信息用到的实体类三
 * @author fu 2016-03-08
 *
 */
public class Step extends com.joymain.jecs.model.BaseObject implements java.io.Serializable{

	private String acceptTime;
	private String acceptAddress;
	private String remark;
	
	public Step(){
		
	}
	
	public String getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(String acceptTime) {
		this.acceptTime = acceptTime;
	}

	public String getAcceptAddress() {
		return acceptAddress;
	}

	public void setAcceptAddress(String acceptAddress) {
		this.acceptAddress = acceptAddress;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public boolean equals(Object o) {
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public String toString() {
		return null;
	}

}
