package com.joymain.jecs.pd.model;

import java.util.ArrayList;
import java.util.List;

public class LogisticsQueryRequest extends com.joymain.jecs.model.BaseObject implements java.io.Serializable{
    
	private String clientID;
    private List<Order> orders = new ArrayList();
	
    public LogisticsQueryRequest(){
    	this.clientID = "ZM";//与WMS朋友沟通，这个clientID是个固定值ZM
    }
    
    public LogisticsQueryRequest(String clientID){
    	this.clientID = clientID;
    }
	
	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
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
