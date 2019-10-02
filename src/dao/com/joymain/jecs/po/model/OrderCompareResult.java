package com.joymain.jecs.po.model;

/**
 * 比对结果信息对象
 * @author houxyu
 * @date 2016-3-29
 */
public class OrderCompareResult implements java.io.Serializable {
//会员编号	会员姓名	订单编号	订单类型	商品编号	商品名称	套餐代码	套餐名称	套餐价格	套餐PV	套餐数量	套餐内产品编号	套餐内产品名称	单价	单个PV值	订购数量
//	退货产品编码	退货产品名称	退货产品数量	实退金额	实退PV

	private static final long serialVersionUID = 1L;

	private String memberUserCode;//会员编号
	private String memberUserName;//会员姓名
	private String orderCode;//订单编号
	private String orderType;//订单类型
	private String productCode;//商品编号
	private String productName;//商品名称
	private String productPackageCode;//套餐代码
	private String productPackageName;//套餐名称
	private String productPackagePrice;//套餐价格
	private String productPackagePv;//套餐PV
	private String productPackageQty;//套餐数量
	private String packageProductCode;//套餐内产品编号
	private String packageProductName;//套餐内产品名称
	private String packageProductPrice;//单价
	private String packageProductPv;//单个PV值
	private String qty;//订购数量
	private String returnProductCode;//退货产品编码
	private String returnProductName;//退货产品名称
	private String returnQty;//退货产品数量
	private String returnMoney;//实退金额
	private String returnPv;//实退PV
	private Float showorder;//排序
	
	
	public String getProductPackageQty() {
		return productPackageQty;
	}
	public void setProductPackageQty(String productPackageQty) {
		this.productPackageQty = productPackageQty;
	}
	public String getPackageProductCode() {
		return packageProductCode;
	}
	public void setPackageProductCode(String packageProductCode) {
		this.packageProductCode = packageProductCode;
	}
	public String getPackageProductName() {
		return packageProductName;
	}
	public void setPackageProductName(String packageProductName) {
		this.packageProductName = packageProductName;
	}
	public String getPackageProductPrice() {
		return packageProductPrice;
	}
	public void setPackageProductPrice(String packageProductPrice) {
		this.packageProductPrice = packageProductPrice;
	}
	public String getPackageProductPv() {
		return packageProductPv;
	}
	public void setPackageProductPv(String packageProductPv) {
		this.packageProductPv = packageProductPv;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getReturnProductCode() {
		return returnProductCode;
	}
	public void setReturnProductCode(String returnProductCode) {
		this.returnProductCode = returnProductCode;
	}
	public String getReturnProductName() {
		return returnProductName;
	}
	public void setReturnProductName(String returnProductName) {
		this.returnProductName = returnProductName;
	}
	public String getReturnQty() {
		return returnQty;
	}
	public void setReturnQty(String returnQty) {
		this.returnQty = returnQty;
	}
	public String getReturnMoney() {
		return returnMoney;
	}
	public void setReturnMoney(String returnMoney) {
		this.returnMoney = returnMoney;
	}
	public String getReturnPv() {
		return returnPv;
	}
	public void setReturnPv(String returnPv) {
		this.returnPv = returnPv;
	}
	public String getMemberUserCode() {
		return memberUserCode;
	}
	public void setMemberUserCode(String memberUserCode) {
		this.memberUserCode = memberUserCode;
	}
	public String getMemberUserName() {
		return memberUserName;
	}
	public void setMemberUserName(String memberUserName) {
		this.memberUserName = memberUserName;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
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
	public String getProductPackageCode() {
		return productPackageCode;
	}
	public void setProductPackageCode(String productPackageCode) {
		this.productPackageCode = productPackageCode;
	}
	public String getProductPackageName() {
		return productPackageName;
	}
	public void setProductPackageName(String productPackageName) {
		this.productPackageName = productPackageName;
	}
	public String getProductPackagePrice() {
		return productPackagePrice;
	}
	public void setProductPackagePrice(String productPackagePrice) {
		this.productPackagePrice = productPackagePrice;
	}
	public String getProductPackagePv() {
		return productPackagePv;
	}
	public void setProductPackagePv(String productPackagePv) {
		this.productPackagePv = productPackagePv;
	}
	public Float getShoworder() {
		return showorder;
	}
	public void setShoworder(Float showorder) {
		this.showorder = showorder;
	}
	
}