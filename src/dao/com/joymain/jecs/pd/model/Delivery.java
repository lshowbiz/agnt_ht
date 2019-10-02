package com.joymain.jecs.pd.model;

import java.util.ArrayList;
import java.util.List;

public class Delivery  extends com.joymain.jecs.model.BaseObject implements java.io.Serializable{

	private String lo_bn;//发货单号
	private String logistics;//物流公司
	private String branch;//发货仓库
	//modify by fu 2016-1-13 添加换货单确认时生成的发货单的物流详细信息
	private String consignee;//收货人                                                         是否为空: 否
	private String consignee_state;//收货省                                         是否为空: 否
	private String consignee_city;//string                是否为空: 否
	private String consignee_area;//地区                                                 是否为空: 是
	private String consignee_address;//详细地址                               是否为空: 否
	private String consignee_zip;//邮编                                                    是否为空: 否
	private String consignee_mobile;//手机                                            是否为空: 否
	private String consignee_phone;//电话                                               是否为空: 是


	private List<Delivery_items> delivery_items = new ArrayList();//发货单明细

    public Delivery(){
		
	}
    public Delivery(String lo_bn,String logistics,String branch,String consignee,String consignee_state,String consignee_city,String consignee_area,
    		String consignee_address,String consignee_zip,String consignee_mobile,String consignee_phone,List delivery_items){
        this.lo_bn = lo_bn;
        this.logistics = logistics;
        this.branch = branch;
        this.consignee = consignee;
        this.consignee_state= consignee_state;
        this.consignee_city= consignee_city;
        this.consignee_area= consignee_area;
        this.consignee_address= consignee_address;
        this.consignee_zip= consignee_zip;
        this.consignee_mobile= consignee_mobile;
        this.consignee_phone= consignee_phone;
        this.delivery_items = delivery_items;
    }
	
	public String getLo_bn() {
		return lo_bn;
	}

	public void setLo_bn(String loBn) {
		lo_bn = loBn;
	}

	public String getLogistics() {
		return logistics;
	}

	public void setLogistics(String logistics) {
		this.logistics = logistics;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}
	
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getConsignee_state() {
		return consignee_state;
	}
	public void setConsignee_state(String consigneeState) {
		consignee_state = consigneeState;
	}
	public String getConsignee_city() {
		return consignee_city;
	}
	public void setConsignee_city(String consigneeCity) {
		consignee_city = consigneeCity;
	}
	public String getConsignee_area() {
		return consignee_area;
	}
	public void setConsignee_area(String consigneeArea) {
		consignee_area = consigneeArea;
	}
	public String getConsignee_address() {
		return consignee_address;
	}
	public void setConsignee_address(String consigneeAddress) {
		consignee_address = consigneeAddress;
	}
	public String getConsignee_zip() {
		return consignee_zip;
	}
	public void setConsignee_zip(String consigneeZip) {
		consignee_zip = consigneeZip;
	}
	public String getConsignee_mobile() {
		return consignee_mobile;
	}
	public void setConsignee_mobile(String consigneeMobile) {
		consignee_mobile = consigneeMobile;
	}
	public String getConsignee_phone() {
		return consignee_phone;
	}
	public void setConsignee_phone(String consigneePhone) {
		consignee_phone = consigneePhone;
	}
	public List<Delivery_items> getDelivery_items() {
		return delivery_items;
	}

	public void setDelivery_items(List<Delivery_items> deliveryItems) {
		delivery_items = deliveryItems;
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
