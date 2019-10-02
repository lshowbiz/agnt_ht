package com.joymain.jecs.po.dao.hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.po.dao.YDorderDao;
import com.joymain.jecs.po.model.YDOrder;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class YDorderDaoHibernate extends BaseDaoHibernate implements YDorderDao {

	public List<YDOrder> getYDorder(Map<String,String> map,Pager page){
		String orderNo = map.get("orderNo");
		String userCode = map.get("userCode");
		String star_date = map.get("startDate");
		String end_date = map.get("endDate");
		String hql = "from YDOrder t where 1=1";
		
		if(StringUtils.isNotBlank(orderNo)){
			hql += " and t.orderNo ='"+orderNo+"' ";
		}
		
		if(StringUtils.isNotBlank(userCode)){
			hql += " and t.userCode ='"+userCode+"' ";
		}
		
		if(StringUtils.isNotBlank(star_date)){
			hql += " and t.createTime >= to_date('"+star_date+"','yyyy-MM-dd HH24:mi:ss')";
		}
		
		if(StringUtils.isNotBlank(end_date)){
			hql += " and t.createTime <= to_date('"+end_date+"','yyyy-MM-dd HH24:mi:ss')";
		}
		
		if (!page.getLimit().getSort().isSorted()) {
			hql += " order by moid desc";
		} else {
			hql += " ORDER BY " + page.getLimit().getSort().getProperty()
					+ " " + page.getLimit().getSort().getSortOrder();
		}
		
		List<YDOrder> orderlist = new ArrayList<YDOrder>();
		orderlist = this.findObjectsByHqlQuery(hql, page);	
		
		
		return orderlist;
	}
	public List<Map> getYDorders(Map<String,String> map,Pager page){
		String orderNo = map.get("orderNo");
		String userCode = map.get("userCode");
		String star_date = map.get("startDate");
		String end_date = map.get("endDate");
		String teamCode=map.get("teamCode");
		String orderPaystartDate = map.get("orderPaystartDate");
		String orderPayendDate = map.get("orderPayendDate");
		
		String sql="select * from(select t.*,fn_get_team_no(t.usercode) teamCode from yd_order t ) where 1=1  ";
		if(StringUtils.isNotBlank(orderNo)){
		sql += " and orderNo ='"+orderNo+"' ";
		}
	
		if(StringUtils.isNotBlank(userCode)){
			sql += " and userCode ='"+userCode+"' ";
		}
	
	if(StringUtils.isNotBlank(teamCode)){
		sql+=" and teamCode='"+teamCode+"'";
	}
	
	if(StringUtils.isNotBlank(star_date)){
		sql += " and orderTime >= to_date('"+star_date+" 00:00:00','yyyy-MM-dd HH24:mi:ss')";
	}
	
	if(StringUtils.isNotBlank(end_date)){
		sql += " and orderTime <= to_date('"+end_date+" 23:59:59','yyyy-MM-dd HH24:mi:ss')";
	}
	
	if(StringUtils.isNotBlank(orderPaystartDate)){
		sql += " and orderpaytime >= to_date('"+orderPaystartDate+" 00:00:00','yyyy-MM-dd HH24:mi:ss')";
	}
	
	if(StringUtils.isNotBlank(orderPayendDate)){
		sql += " and orderpaytime <= to_date('"+orderPayendDate+" 23:59:59','yyyy-MM-dd HH24:mi:ss')";
	}
	
	if (!page.getLimit().getSort().isSorted()) {
		sql += " order by mo_id desc";
	} else {
		sql += " ORDER BY " + page.getLimit().getSort().getProperty()
				+ " " + page.getLimit().getSort().getSortOrder();
	}
	
	
	List<Map> orderlist = new ArrayList<Map>();
	orderlist = this.findObjectsBySQL(sql, page);		
		return orderlist;
	}
	
	public Map getSumAmount(Map<String,String> map) {
		String orderNo = map.get("orderNo");
		String userCode = map.get("userCode");
		String star_date = map.get("startDate");
		String end_date = map.get("endDate");
		String teamCode=map.get("teamCode");
		String orderPaystartDate = map.get("orderPaystartDate");
		String orderPayendDate = map.get("orderPayendDate");
		
		String sql = "select nvl(sum(t.amount),0) as amount,nvl(sum(t.pvamt),0) as pvamt,nvl(sum(yd_pv),0) as yd_pv     ,nvl(sum(yd_rebate),0) as yd_rebate ,nvl(sum(ec_rebate),0) as ec_rebate , nvl(sum(total_rebate),0) as total_rebate  from YD_order t where 1=1 ";
		if(StringUtils.isNotBlank(orderNo)){
			sql += " and t.orderNo ='"+orderNo+"' ";
		}
		
		if(StringUtils.isNotBlank(userCode)){
			sql += " and t.userCode ='"+userCode+"' ";
		}
		
		if(StringUtils.isNotBlank(star_date)){
			sql += " and t.orderTime >= to_date('"+star_date+" 00:00:00','yyyy-MM-dd HH24:mi:ss')";
		}
		
		if(StringUtils.isNotBlank(end_date)){
			sql += " and t.orderTime <= to_date('"+end_date+" 23:59:59','yyyy-MM-dd HH24:mi:ss')";
		}
		
		if(StringUtils.isNotBlank(orderPaystartDate)){
			sql += " and t.orderpaytime >= to_date('"+orderPaystartDate+" 00:00:00','yyyy-MM-dd HH24:mi:ss')";
		}
		
		if(StringUtils.isNotBlank(orderPayendDate)){
			sql += " and t.orderpaytime <= to_date('"+orderPayendDate+" 23:59:59','yyyy-MM-dd HH24:mi:ss')";
		}
		
		if(StringUtils.isNotBlank(teamCode)){
			sql += " and t.mo_id in(select mo_id from(select t.*,fn_get_team_no(t.usercode) teamCode from yd_order t) where teamCode='"+teamCode+"')";
		}
		
		List sumList=this.findObjectsBySQL(sql);
		if(sumList.size()>0){
		Map<String,String> sum=(Map<String,String>) sumList.get(0);
		Map<String, BigDecimal> sumMap = new HashMap<String, BigDecimal>();
		BigDecimal amount=new BigDecimal(sum.get("amount"));
		BigDecimal pvamt=new BigDecimal(sum.get("pvamt"));
		BigDecimal yd_pv=new BigDecimal(sum.get("yd_pv"));
		BigDecimal yd_rebate=new BigDecimal(sum.get("yd_rebate"));
		BigDecimal ec_rebate=new BigDecimal(sum.get("ec_rebate"));
		BigDecimal total_rebate=new BigDecimal(sum.get("total_rebate"));
		sumMap.put("amount", amount);
		sumMap.put("pvamt", pvamt );
		sumMap.put("yd_pv", yd_pv);
		sumMap.put("yd_rebate", yd_rebate);
		sumMap.put("ec_rebate", ec_rebate);
		sumMap.put("total_rebate", total_rebate);
		return sumMap;
		}
		return null;
	}

}
