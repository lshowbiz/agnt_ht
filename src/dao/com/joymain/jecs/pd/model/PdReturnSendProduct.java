package com.joymain.jecs.pd.model;

/**
 * 生成发货单的发货退回单接口实体二
 * @author fu  20150821
 *
 */
public class PdReturnSendProduct extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {

	private String productNo;//商品编码
	private String erpProductNo;//ERP商品编码
	private int qty;//商品数量
	
	public PdReturnSendProduct(){
		
	}
	
	
	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getErpProductNo() {
		return erpProductNo;
	}

	public void setErpProductNo(String erpProductNo) {
		this.erpProductNo = erpProductNo;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
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
