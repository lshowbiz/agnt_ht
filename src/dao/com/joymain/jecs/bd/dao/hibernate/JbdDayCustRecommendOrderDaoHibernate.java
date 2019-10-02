package com.joymain.jecs.bd.dao.hibernate;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.bd.dao.JbdDayCustRecommendOrderDao;
import com.joymain.jecs.bd.model.JbdDayCustRecommendOrder;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
public class JbdDayCustRecommendOrderDaoHibernate extends BaseDaoHibernate implements JbdDayCustRecommendOrderDao {
	/**
	 * @see com.joymain.jecs.bd.dao.JbdDayCustRecommendOrderDaoDao#getJbdDayCustRecommendOrderDaos(com.joymain.jecs.bd.model.JbdDayCustRecommendOrderDao)
	 */
	public List getJbdDayCustRecommendOrders(final JbdDayCustRecommendOrder jbdDayCustRecommend) {
		return getHibernateTemplate().findByExample(jbdDayCustRecommend);
	}

	/**
	 * @see com.joymain.jecs.bd.dao.JbdDayCustRecommendOrderDaoDao#getJbdDayCustRecommendOrderDao(BigDecimal id)
	 */
	public JbdDayCustRecommendOrder getJbdDayCustRecommendOrder(final Long id) {
		JbdDayCustRecommendOrder jbdDayCustRecommend = (JbdDayCustRecommendOrder) getHibernateTemplate().get(JbdDayCustRecommendOrder.class, id);
		if (jbdDayCustRecommend == null) {
			log.warn("uh oh, jbdDayCustRecommend with id '" + id + "' not found...");
			throw new ObjectRetrievalFailureException(JbdDayCustRecommendOrder.class, id);
		}

		return jbdDayCustRecommend;
	}

	//added for getJbdDayCustRecommendOrderDaosByCrm
/*	public List getJbdDayCustRecommendOrderDaosByCrm(CommonRecord crm, Pager pager) {
		String hql = "from JbdDayCustRecommendOrderDao jbdDayCustRecommendDaoOrder where 1=1";

		String status = crm.getString("status", "");
		if (!StringUtil.isEmpty(status)) {
			hql += " and jbdDayCustRecommendDaoOrder.status='" + status + "'";
		}
		String userCode = crm.getString("userCode", "");
		if (!StringUtil.isEmpty(userCode)) {
			hql += " and jbdDayCustRecommendDaoOrder.jmiMember.userCode='" + userCode + "'";
		}
		String name = crm.getString("name", "");
		if (!StringUtil.isEmpty(name)) {
			hql += " and jbdDayCustRecommendDaoOrder.jmiMember.miUserName='" + name + "'";
		}
		String createName = crm.getString("createName", "");
		if (!StringUtil.isEmpty(createName)) {
			hql += " and jbdDayCustRecommendDaoOrder.createName='" + createName + "'";
		}

		String createCode = crm.getString("createCode", "");
		if (!StringUtil.isEmpty(createCode)) {
			hql += " and jbdDayCustRecommendDaoOrder.createCode='" + createCode + "'";
		}

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String startCreateTime = crm.getString("startCreateTime", "");
		String endCreateTime = crm.getString("endCreateTime", "");
		if (!StringUtils.isEmpty(startCreateTime)) {
			try {
				hql += " and jbdDayCustRecommendDaoOrder.createTime >=to_date('" + dateFormat.format(dateFormat.parse(startCreateTime)) + " 00:00:00','yyyy-MM-dd hh24:mi:ss') ";
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (!StringUtils.isEmpty(endCreateTime)) {
			try {
				hql += " and jbdDayCustRecommendDaoOrder.createTime <=to_date('" + dateFormat.format(dateFormat.parse(endCreateTime)) + " 23:59:59','yyyy-MM-dd hh24:mi:ss')  ";
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		String startCheckTime = crm.getString("startCheckTime", "");
		String endCheckTime = crm.getString("endCheckTime", "");
		if (!StringUtils.isEmpty(startCheckTime)) {
			try {
				hql += " and jbdDayCustRecommendDaoOrder.checkTime >=to_date('" + dateFormat.format(dateFormat.parse(startCheckTime)) + " 00:00:00','yyyy-MM-dd hh24:mi:ss') ";
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (!StringUtils.isEmpty(endCheckTime)) {
			try {
				hql += " and jbdDayCustRecommendDaoOrder.checkTime <=to_date('" + dateFormat.format(dateFormat.parse(endCheckTime)) + " 23:59:59','yyyy-MM-dd hh24:mi:ss')  ";
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		
		String startMoney = crm.getString("startMoney", "");
		if (!StringUtil.isEmpty(startMoney) && StringUtil.isInteger(startMoney)) {
			hql += " and jbdDayCustRecommendDaoOrder.money>=" + new BigDecimal(startMoney);
		}

		String endMoney = crm.getString("endMoney", "");
		if (!StringUtil.isEmpty(endMoney) && StringUtil.isInteger(endMoney)) {
			hql += " and jbdDayCustRecommendDaoOrder.money<=" + new BigDecimal(endMoney);
		}

		String companyCode = crm.getString("companyCode", "");
		if (!StringUtil.isEmpty(companyCode)) {
			hql += " and jbdDayCustRecommendDaoOrder.companyCode='" + companyCode + "'";
		}
		if (!pager.getLimit().getSort().isSorted()) {
			//
			hql += " order by id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
	}*/


	public List getJbdDayCustRecommendOrdersByCrmBySql(CommonRecord crm, Pager pager) {
		String sql = "select jbdDayCustRecommendOrder.* from jbd_day_cust_recommend_order jbdDayCustRecommendOrder  where 1=1 ";

		String wweek = crm.getString("wweek", "");
		if (!StringUtil.isEmpty(wweek)) {
			sql += " and jbdDayCustRecommendOrder.w_week='" + wweek + "'";
		}
		
		String sendFlag = "1";
		if (!StringUtil.isEmpty(sendFlag)) {
			sql += " and jbdDayCustRecommendOrder.send_flag='" + sendFlag + "'";
		}
		
		String userCode = crm.getString("userCode", "");
		if (!StringUtil.isEmpty(userCode)) {
			sql += " and jbdDayCustRecommendOrder.user_code='" + userCode + "'";
		}


		if (!pager.getLimit().getSort().isSorted()) {
			//
			sql += " order by id desc";
		} else {
			sql += " ORDER BY jbdDayCustRecommendOrder." + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsBySQL(sql, pager);
	}
	
	
	
	
	public List getJbdDayCustRecommendOrdersByCrmByHql(CommonRecord crm, Pager pager) {
		String hql = "from JbdDayCustRecommendOrder jbdDayCustRecommendOrder  where 1=1 ";
		
		String wweek = crm.getString("wweek", "");
		if (!StringUtil.isEmpty(wweek)) {
			hql += " and jbdDayCustRecommendOrder.wweek='" + wweek + "'";
		}
		
		String sendFlag = "1";
		if (!StringUtil.isEmpty(sendFlag)) {
			hql += " and jbdDayCustRecommendOrder.sendFlag='" + sendFlag + "'";
		}
		
		String userCode = crm.getString("userCode", "");
		if (!StringUtil.isEmpty(userCode)) {
			hql += " and jbdDayCustRecommendOrder.userCode='" + userCode + "'";
		}
		
		
		if (!pager.getLimit().getSort().isSorted()) {
			//
			hql += " order by id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
	}

}