package com.joymain.jecs.pd.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.joymain.jecs.pd.model.Delivery;


public class Reship extends com.joymain.jecs.model.BaseObject implements java.io.Serializable{
	
	private String order_bn;//订单号
	
	private String order_bn_ex;//换货单号 
	
	private List<Returno_items> returno_items = new ArrayList();//退货明细
	
	//private Delivery delivery;//发货信息
	private List<Delivery> deliverys  = new ArrayList();//2016-04-01 自助换货的发货信息；

    public Reship(){
		
	}
	
	  public Reship(String order_bn, String order_bn_ex,List returno_items,List deliverys) {
	        this.order_bn = order_bn;
	        this.order_bn_ex = order_bn_ex;
	        this.returno_items = returno_items;
	        //this.delivery = delivery;
	        this.deliverys = deliverys;
	  }
	
	public String getOrder_bn() {
		return order_bn;
	}

	public void setOrder_bn(String orderBn) {
		order_bn = orderBn;
	}

	public String getOrder_bn_ex() {
		return order_bn_ex;
	}

	public void setOrder_bn_ex(String orderBnEx) {
		order_bn_ex = orderBnEx;
	}
	
	public List<Returno_items> getReturno_items() {
		return returno_items;
	}

	public void setReturno_items(List<Returno_items> returnoItems) {
		returno_items = returnoItems;
	}

	/*public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}*/

	public List<Delivery> getDeliverys() {
		return deliverys;
	}

	public void setDeliverys(List<Delivery> deliverys) {
		this.deliverys = deliverys;
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
