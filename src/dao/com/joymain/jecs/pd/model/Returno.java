package com.joymain.jecs.pd.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 退回单对象
 * @author www
 *
 */
public class Returno extends com.joymain.jecs.model.BaseObject implements java.io.Serializable{
	private String order_bn;//订单号
	private String eo_bo;//退单号
	private String returnWarehouse;// modify fu 2015-09-10 添加退货入库仓库的字段
	private double repair_Fee;//维修费
	private double renovation_Fee;//翻新费
	private double logistics_Fee;//物流费
	private double settled_Bonus;//不能扣回的奖金
	private double fill_Freight;//回补运费
	
	private List<Returno_items> returno_items = new ArrayList();//退货单明细数据
	
	public String getOrder_bn() {
		return order_bn;
	}
	public void setOrder_bn(String orderBn) {
		order_bn = orderBn;
	}
	public String getEo_bo() {
		return eo_bo;
	}
	public void setEo_bo(String eoBo) {
		eo_bo = eoBo;
	}
	public String getReturnWarehouse() {
		return returnWarehouse;
	}
	public void setReturnWarehouse(String returnWarehouse) {
		this.returnWarehouse = returnWarehouse;
	}
	public List<Returno_items> getReturno_items() {
		return returno_items;
	}
	public void setReturno_items(List<Returno_items> returnoItems) {
		returno_items = returnoItems;
	}
	public double getRepair_Fee() {
		return repair_Fee;
	}
	public void setRepair_Fee(double repairFee) {
		repair_Fee = repairFee;
	}
	public double getRenovation_Fee() {
		return renovation_Fee;
	}
	public void setRenovation_Fee(double renovationFee) {
		renovation_Fee = renovationFee;
	}
	public double getLogistics_Fee() {
		return logistics_Fee;
	}
	public void setLogistics_Fee(double logisticsFee) {
		logistics_Fee = logisticsFee;
	}
	public double getSettled_Bonus() {
		return settled_Bonus;
	}
	public void setSettled_Bonus(double settledBonus) {
		settled_Bonus = settledBonus;
	}
	public double getFill_Freight() {
		return fill_Freight;
	}
	public void setFill_Freight(double fillFreight) {
		fill_Freight = fillFreight;
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
