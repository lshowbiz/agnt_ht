package com.joymain.jecs.pd.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成发货单的发货退回单接口实体类一
 * @author fu  20150821
 *
 */
public class PdReturnSend extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {

	private String rpNo;//发货退回单号
	private String customerCode;//客户编号
	private String recipientName;//收货人姓名
	private String recipientCaNo;//省份
	private String city;//城市
	private String district;//地区
	private String recipientAddr;//详细地址
	private String recipientZip;//邮编
	private String recipientMobile;//手机
	private String recipientPhone;//电话
	private String siNo;//发货单号
	private String shNo;//物流公司
	private String warehouseNo;//发货仓库
	private List<PdReturnSendProduct>  pdReturnSendProduct = new ArrayList<PdReturnSendProduct>();
	
	public PdReturnSend(){
		
	}
	
	
	
	public String getRpNo() {
		return rpNo;
	}



	public void setRpNo(String rpNo) {
		this.rpNo = rpNo;
	}



	public String getCustomerCode() {
		return customerCode;
	}



	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}



	public String getRecipientName() {
		return recipientName;
	}



	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}



	public String getRecipientCaNo() {
		return recipientCaNo;
	}



	public void setRecipientCaNo(String recipientCaNo) {
		this.recipientCaNo = recipientCaNo;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getDistrict() {
		return district;
	}



	public void setDistrict(String district) {
		this.district = district;
	}



	public String getRecipientAddr() {
		return recipientAddr;
	}



	public void setRecipientAddr(String recipientAddr) {
		this.recipientAddr = recipientAddr;
	}



	public String getRecipientZip() {
		return recipientZip;
	}



	public void setRecipientZip(String recipientZip) {
		this.recipientZip = recipientZip;
	}



	public String getRecipientMobile() {
		return recipientMobile;
	}



	public void setRecipientMobile(String recipientMobile) {
		this.recipientMobile = recipientMobile;
	}



	public String getRecipientPhone() {
		return recipientPhone;
	}



	public void setRecipientPhone(String recipientPhone) {
		this.recipientPhone = recipientPhone;
	}



	public String getSiNo() {
		return siNo;
	}



	public void setSiNo(String siNo) {
		this.siNo = siNo;
	}



	public String getShNo() {
		return shNo;
	}



	public void setShNo(String shNo) {
		this.shNo = shNo;
	}



	public String getWarehouseNo() {
		return warehouseNo;
	}



	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}



	public List<PdReturnSendProduct> getPdReturnSendProduct() {
		return pdReturnSendProduct;
	}



	public void setPdReturnSendProduct(List<PdReturnSendProduct> pdReturnSendProduct) {
		this.pdReturnSendProduct = pdReturnSendProduct;
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
