package com.joymain.jecs.util.data;

import java.util.ArrayList;
import java.util.List;

public class WarehouseTraceActions {
	
	public static List getAllTraceActions(){
		List list = new ArrayList();
		list.add("warehousing");//入库
		list.add("flittingIn");//调入
		list.add("flittingOut");//调出
		list.add("unnormalChange");//异动
		list.add("sold");//出售
		list.add("shipped");//发货
		list.add("rejected");//发货拒收
		list.add("purchaseReturned");//退货
		return list;
		
	}
	
	public static List getTraceActions(){
		List list = new ArrayList();
//		list.add("warehousing");//入库
		list.add("flittingIn");//调入
		list.add("flittingOut");//调出
		list.add("unnormalChange");//异动
		list.add("sold");//出售
		list.add("shipped");//发货
		list.add("rejected");//发货拒收
		list.add("purchaseReturned");//退货
		return list;
		
	}
}
