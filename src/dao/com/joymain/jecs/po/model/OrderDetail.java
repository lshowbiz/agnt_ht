package com.joymain.jecs.po.model;

/**
 * 会员退回商品信息
 * @author houxyu
 * @date 2016-3-27
 *
 */
public class OrderDetail implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;

	private String productCode;//商品编号
	private String productName;//商品名称
	private Integer qty;//数量
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	
	
}