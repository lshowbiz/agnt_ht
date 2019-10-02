package com.joymain.jecs.pd.model;

/**
 * 物理模块接口-发货退回单用到的商品实体类
 * @author 2015-08-20
 *
 */
public class ReturnProduct extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {
    
	private String productNo;
	private String qty;
	private String erpProductNo;
	
	public ReturnProduct(){
		
	}
	
	public ReturnProduct(String productNo,String qty,String erpProductNo){
		this.productNo = productNo;
		this.qty = qty;
		this.erpProductNo = erpProductNo;
	}
	
	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getErpProductNo() {
		return erpProductNo;
	}

	public void setErpProductNo(String erpProductNo) {
		this.erpProductNo = erpProductNo;
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
