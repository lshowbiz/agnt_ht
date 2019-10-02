package com.joymain.jecs.pd.model;

/**
 * 退货单删除接口用到的实体类
 * @author fx 20150814
 *
 */
public class PdJprRefundDelete extends com.joymain.jecs.model.BaseObject implements java.io.Serializable{

	private String roNo;//退货单号
	private String whetherDelete;//是否删除：Y已删除
	
	public PdJprRefundDelete(){
		
	}
	
	public PdJprRefundDelete(String roNo,String whetherDelete){
		this.roNo = roNo;
		this.whetherDelete = whetherDelete;
	}
	
	public String getRoNo() {
		return roNo;
	}

	public void setRoNo(String roNo) {
		this.roNo = roNo;
	}

	public String getWhetherDelete() {
		return whetherDelete;
	}

	public void setWhetherDelete(String whetherDelete) {
		this.whetherDelete = whetherDelete;
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
