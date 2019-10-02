package com.joymain.jecs.pd.model;

/**
 * 发货退回入库接口实体类
 * @author fu 20150820
 *
 */
public class PdReturnStatus extends com.joymain.jecs.model.BaseObject implements java.io.Serializable{
	
	private String rpNo;//发货退回单号
	private String intoStatus;//入库状态:Y：已入库;N：未入库

	public PdReturnStatus(){
		
	}

	public String getRpNo() {
		return rpNo;
	}

	public void setRpNo(String rpNo) {
		this.rpNo = rpNo;
	}

	public String getIntoStatus() {
		return intoStatus;
	}

	public void setIntoStatus(String intoStatus) {
		this.intoStatus = intoStatus;
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
