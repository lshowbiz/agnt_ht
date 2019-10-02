package com.joymain.jecs.pd.model;

import java.util.List;

public class Delivery_items extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {
	
	private String goods_bn;//商品编码
	private String name;//商品名称
	private String erp_goods_bn;//ERP商品编码
	private int nums;//商品数量
	private double price;//商品单价
	//modify gw 2015-04-14 套餐拆开发货新加的字段:所属套餐编码
    private String combination_product_no;//combination_product_no
 
	public Delivery_items(){
		
	}
	
	
	public Delivery_items(String goods_bn,String name,String erp_goods_bn,int nums,double price,String combination_product_no){
        this.goods_bn = goods_bn;
        this.name = name;
        this.erp_goods_bn = erp_goods_bn;
        this.nums = nums;
        this.price = price;
        this.combination_product_no = combination_product_no;
    }

	public String getGoods_bn() {
		return goods_bn;
	}

	public void setGoods_bn(String goodsBn) {
		goods_bn = goodsBn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getErp_goods_bn() {
		return erp_goods_bn;
	}

	public void setErp_goods_bn(String erpGoodsBn) {
		erp_goods_bn = erpGoodsBn;
	}

	public int getNums() {
		return nums;
	}

	public void setNums(int nums) {
		this.nums = nums;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCombination_product_no() {
		return combination_product_no;
	}

	public void setCombination_product_no(String combinationProductNo) {
		combination_product_no = combinationProductNo;
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
