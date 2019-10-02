package com.joymain.jecs.bd.dao.hibernate;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.bd.dao.JbdDayCustRecommendDao;
import com.joymain.jecs.bd.model.JbdDayCustRecommend;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
public class JbdDayCustRecommendDaoHibernate extends BaseDaoHibernate implements JbdDayCustRecommendDao {
	/**
	 * @see com.joymain.jecs.bd.dao.JbdDayCustRecommendDao#getJbdDayCustRecommends(com.joymain.jecs.bd.model.JbdDayCustRecommend)
	 */
	public List getJbdDayCustRecommends(final JbdDayCustRecommend jbdDayCustRecommend) {
		return getHibernateTemplate().findByExample(jbdDayCustRecommend);
	}

	/**
	 * @see com.joymain.jecs.bd.dao.JbdDayCustRecommendDao#getJbdDayCustRecommend(BigDecimal id)
	 */
	public JbdDayCustRecommend getJbdDayCustRecommend(final Long id) {
		JbdDayCustRecommend jbdDayCustRecommend = (JbdDayCustRecommend) getHibernateTemplate().get(JbdDayCustRecommend.class, id);
		if (jbdDayCustRecommend == null) {
			log.warn("uh oh, jbdDayCustRecommend with id '" + id + "' not found...");
			throw new ObjectRetrievalFailureException(JbdDayCustRecommend.class, id);
		}

		return jbdDayCustRecommend;
	}

	/**
	 * @see com.joymain.jecs.bd.dao.JbdDayCustRecommendDao#saveJbdDayCustRecommend(JbdDayCustRecommend jbdDayCustRecommend)
	 */
	public void saveJbdDayCustRecommend(final JbdDayCustRecommend jbdDayCustRecommend) {
		getHibernateTemplate().saveOrUpdate(jbdDayCustRecommend);
	}

	/**
	 * 批量保存奖金扣补数据
	 * @param jbdDayCustRecommends
	 */
	public void saveJbdDayCustRecommends(List jbdDayCustRecommends) {
		getHibernateTemplate().saveOrUpdateAll(jbdDayCustRecommends);
	}


	//added for getJbdDayCustRecommendsByCrm
	public List getJbdDayCustRecommendsByCrm(CommonRecord crm, Pager pager) {
		String hql = "from JbdDayCustRecommend jbdDayCustRecommend where 1=1";
		
		String userCode = crm.getString("userCode", "");
		if (!StringUtil.isEmpty(userCode)) {
			hql += " and jbdDayCustRecommend.jmiMember.userCode='" + userCode + "'";
		}
		
		String status = crm.getString("status", "");
		if (!StringUtil.isEmpty(status)) {
			hql += " and jbdDayCustRecommend.status='" + status + "'";
		}
		
		String freezeStatus = crm.getString("freezeStatus", "");
		if (!StringUtils.isEmpty(freezeStatus)) {
			hql += " and jbdDayCustRecommend.freezeStatus = " + freezeStatus;
		}
		
		String startCreateTime = crm.getString("startCreateTime", "");
		if (!StringUtils.isEmpty(startCreateTime)) {
			hql += " and jbdDayCustRecommend.wweek >= " + startCreateTime;
		}

		String endCreateTime = crm.getString("endCreateTime", "");
		if (!StringUtils.isEmpty(endCreateTime)) {
			hql += " and jbdDayCustRecommend.wweek <= " + endCreateTime;
		}
		
		if (!pager.getLimit().getSort().isSorted()) {
			//
			hql += " order by id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
	}
	
	
	public List getJbdDayCustRecommendsByCrmBySql(CommonRecord crm, Pager pager) {
		String sql = "select jbdDayCustRecommend.* from jbd_day_cust_recommend jbdDayCustRecommend where 1=1 ";

		String userCode = crm.getString("userCode", "");
		if (!StringUtil.isEmpty(userCode)) {
			sql += " and jbdDayCustRecommend.user_code='" + userCode + "'";
		}
		
		String startCreateTime = crm.getString("startCreateTime", "");
		if (!StringUtils.isEmpty(startCreateTime)) {
			sql += " and jbdDayCustRecommend.w_week >= " + startCreateTime;
		}

		String endCreateTime = crm.getString("endCreateTime", "");
		if (!StringUtils.isEmpty(endCreateTime)) {
			sql += " and jbdDayCustRecommend.w_week <= " + endCreateTime;
		}
		
		String freezeStatus = crm.getString("freezeStatus", "");
		if (!StringUtils.isEmpty(freezeStatus)) {
			sql += " and jbdDayCustRecommend.freeze_status = " + freezeStatus;
		}
		
		String status = crm.getString("status", "");
		if (!StringUtils.isEmpty(status)) {
			sql += " and jbdDayCustRecommend.status = " + status;
		}

		if (!pager.getLimit().getSort().isSorted()) {
			//
			sql += " order by id desc";
		} else {
			sql += " ORDER BY jbdDayCustRecommend." + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsBySQL(sql, pager);
	}
}