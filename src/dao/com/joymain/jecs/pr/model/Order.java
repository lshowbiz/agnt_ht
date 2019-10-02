package com.joymain.jecs.pr.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 物流跟踪查询去WMS查询时用到的对象
 * @author modify by fu 2016-03-17
 *
 */
public class Order extends com.joymain.jecs.model.BaseObject implements java.io.Serializable{

	private String mailNo;
	
	//modify by fu 2016-03-08
	private String logisticProviderID;//物流公司
	private boolean success;
	//private String reason;
	private List<Step> steps = new ArrayList<Step>();
	//modify by fu 2016-03-08
	
	
	public Order(){
		
	}
	
	public Order(String mailNo,boolean success,String reason,String logisticProviderID){
		this.mailNo = mailNo;
		this.success = success;
		//this.reason = reason;
		this.logisticProviderID = logisticProviderID;
	}
	
	public String getMailNo() {
		return mailNo;
	}

	public void setMailNo(String mailNo) {
		this.mailNo = mailNo;
	}

	public String getLogisticProviderID() {
		return logisticProviderID;
	}

	public void setLogisticProviderID(String logisticProviderID) {
		this.logisticProviderID = logisticProviderID;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	/*public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}*/

	public List<Step> getSteps() {
		return steps;
	}

	public void setSteps(List<Step> steps) {
		this.steps = steps;
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
