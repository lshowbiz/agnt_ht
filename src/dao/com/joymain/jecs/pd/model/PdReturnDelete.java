package com.joymain.jecs.pd.model;

/**
 * 发货退回单删除接口实体类
 * @author fu  20150906
 *
 */
public class PdReturnDelete extends com.joymain.jecs.model.BaseObject implements java.io.Serializable{

	private String rpNo;//发货退回单号
	private String IsDelete;//是否删除,Y表示已删除
	
	public PdReturnDelete(){
		
	}
	
    public PdReturnDelete(String rpNo,String IsDelete){
		this.rpNo = rpNo;
		this.IsDelete = IsDelete;
	}
	
	public String getRpNo() {
		return rpNo;
	}

	public void setRpNo(String rpNo) {
		this.rpNo = rpNo;
	}

	public String getIsDelete() {
		return IsDelete;
	}

	public void setIsDelete(String isDelete) {
		IsDelete = isDelete;
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
