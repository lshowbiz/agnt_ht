package com.joymain.jecs.pr.model;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author fu 2016-03-08
 *
 */
public class LogisticsQueryResponse extends com.joymain.jecs.model.BaseObject implements java.io.Serializable{
    
	private String clientID;
	private List<Order> orders = new ArrayList<Order>();
	
	public LogisticsQueryResponse(){

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
