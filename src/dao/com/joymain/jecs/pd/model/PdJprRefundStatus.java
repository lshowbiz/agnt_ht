package com.joymain.jecs.pd.model;

/**
 * 退货入库接口用到的实体类
 * @author gw 2015-08-04
 *
 */
public class PdJprRefundStatus  extends com.joymain.jecs.model.BaseObject implements java.io.Serializable{
	
	private String roNo;//退货单号
	private String intoStatus;//入库状态,Y：已入库 ;  N：未入库
	
	public PdJprRefundStatus(){
		
	}
	
	public PdJprRefundStatus(String roNo,String intoStatus){
		this.roNo = roNo;
		this.intoStatus = intoStatus;
	}
	
	
	public String getRoNo() {
		return roNo;
	}
	public void setRoNo(String roNo) {
		this.roNo = roNo;
	}
	public String getIntoStatus() {
		return intoStatus;
	}
	public void setIntoStatus(String intoStatus) {
		this.intoStatus = intoStatus;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
