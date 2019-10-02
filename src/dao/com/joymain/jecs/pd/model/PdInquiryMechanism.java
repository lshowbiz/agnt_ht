package com.joymain.jecs.pd.model;

/**
 * 物流接口询问机制用到的实体类
 * @author fu 2016-03-21
 *
 */
public class PdInquiryMechanism extends com.joymain.jecs.model.BaseObject implements java.io.Serializable{
	
	private String pdLogisticsNumbers;//物流单号类型,可以是退货单号，也可以是换货单号，也可以是发货单号
	private String type;
	//用数字表示询问类型
	//11发货单编辑、12发货单的暂不发货、13发货单的暂停发货、14发货单的发货作废；
    //21退货单编辑、22退货单删除；
    //31换货单编辑、32换货单删除；
	
	public String getPdLogisticsNumbers() {
		return pdLogisticsNumbers;
	}

	public void setPdLogisticsNumbers(String pdLogisticsNumbers) {
		this.pdLogisticsNumbers = pdLogisticsNumbers;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
