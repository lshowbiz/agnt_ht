package com.joymain.jecs.pd.model;

/**
 * 物流跟踪查询去WMS查询时用到的对象
 * @author modify by fu 2016-03-17
 *
 */
public class Order extends com.joymain.jecs.model.BaseObject implements java.io.Serializable{

	private String mailNo;

	public Order(){
		
	}
	
	public Order(String mailNo){
		this.mailNo = mailNo;
	}
	
	public String getMailNo() {
		return mailNo;
	}

	public void setMailNo(String mailNo) {
		this.mailNo = mailNo;
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
