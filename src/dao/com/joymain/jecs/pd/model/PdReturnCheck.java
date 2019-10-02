package com.joymain.jecs.pd.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 物流模块-发货退回单接口实体类之一
 * @author  2015-08-20i
 *
 */
public class PdReturnCheck extends com.joymain.jecs.model.BaseObject implements java.io.Serializable{

	private String rpNo;//发货退回单号
	private String customerCode;//客户编号
	private String returnWarehouseNo;//退回仓库
	
	//modify by fu 2016-02-25 制单备注
	private String remark;
	
	private List<ReturnProduct> returnProduct = new ArrayList();
	
	public PdReturnCheck(){
		
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
	
	public String getReturnWarehouseNo() {
		return returnWarehouseNo;
	}

	public void setReturnWarehouseNo(String returnWarehouseNo) {
		this.returnWarehouseNo = returnWarehouseNo;
	}

	public List<ReturnProduct> getReturnProduct() {
		return returnProduct;
	}

	public void setReturnProduct(List<ReturnProduct> returnProduct) {
		this.returnProduct = returnProduct;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
