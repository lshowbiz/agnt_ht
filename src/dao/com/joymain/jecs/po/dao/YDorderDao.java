package com.joymain.jecs.po.dao;

import java.util.List;
import java.util.Map;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.po.model.YDOrder;
import com.joymain.jecs.util.data.Pager;

public interface YDorderDao extends Dao{

	public List<YDOrder> getYDorder(Map<String, String> map,Pager page);
	/**
	 * 查询云店订单
	 * @param map
	 * @param page
	 * @return
	 */
	public List<Map> getYDorders(Map<String, String> map, Pager page);
	/**
	 * 求总金额 总pv 峰值的和
	 * @param map
	 * @return
	 */
	public Map getSumAmount(Map<String, String> map);
}
