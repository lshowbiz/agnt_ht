
package com.joymain.jecs.po.dao.hibernate;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.po.dao.JpoUserCouponDao;
import com.joymain.jecs.po.model.JpoUserCoupon;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class JpoUserCouponDaoHibernate extends BaseDaoHibernate implements JpoUserCouponDao {

	/**
	 * @see com.joymain.jecs.po.dao.JpoUserCouponDao#getJpoUserCoupons(com.joymain.jecs.po.model.JpoUserCoupon)
	 */
	public List getJpoUserCoupons(final JpoUserCoupon jpoUserCoupon) {
		return getHibernateTemplate().find("from JpoUserCoupon");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (jpoUserCoupon == null) {
		 * return getHibernateTemplate().find("from JpoUserCoupon"); } else { //
		 * filter on properties set in the jpoUserCoupon HibernateCallback
		 * callback = new HibernateCallback() { public Object
		 * doInHibernate(Session session) throws HibernateException { Example ex
		 * = Example.create(jpoUserCoupon).ignoreCase().enableLike(MatchMode.
		 * ANYWHERE); return
		 * session.createCriteria(JpoUserCoupon.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.joymain.jecs.po.dao.JpoUserCouponDao#getJpoUserCoupon(BigDecimal
	 *      id)
	 */
	public JpoUserCoupon getJpoUserCoupon(final BigDecimal id) {
		JpoUserCoupon jpoUserCoupon = (JpoUserCoupon) getHibernateTemplate().get(JpoUserCoupon.class, id);
		if (jpoUserCoupon == null) {
			log.warn("uh oh, jpoUserCoupon with id '" + id + "' not found...");
			throw new ObjectRetrievalFailureException(JpoUserCoupon.class, id);
		}

		return jpoUserCoupon;
	}

	/**
	 * @see com.joymain.jecs.po.dao.JpoUserCouponDao#saveJpoUserCoupon(JpoUserCoupon
	 *      jpoUserCoupon)
	 */
	public void saveJpoUserCoupon(final JpoUserCoupon jpoUserCoupon) {
		getHibernateTemplate().saveOrUpdate(jpoUserCoupon);
	}

	/**
	 * @see com.joymain.jecs.po.dao.JpoUserCouponDao#removeJpoUserCoupon(BigDecimal
	 *      id)
	 */
	public void removeJpoUserCoupon(final BigDecimal id) {
		getHibernateTemplate().delete(getJpoUserCoupon(id));
	}

	// added for getJpoUserCouponsByCrm
	public List getJpoUserCouponsByCrm(CommonRecord crm, Pager pager) {
		String hql = "from JpoUserCoupon jpoUserCoupon where 1=1";
		String userCode = crm.getString("userCode", "");
		String cpId = crm.getString("cpId", "");
		String status = crm.getString("status", "");
		String startTime = crm.getString("startTime", "");
		String endTime = crm.getString("endTime", "");
		
		if (StringUtils.isNotEmpty(userCode)) {
			hql += " and userCode='"+userCode+"'";
		}
		if (StringUtils.isNotEmpty(cpId)) {
			hql += " and cpId='"+cpId+"'";
		}
		if (StringUtils.isNotEmpty(status)) {
			hql += " and status='"+status+"'";
		}
		if (StringUtils.isNotEmpty(startTime)) {
			hql += " and startTime >= to_date('"+startTime+"', 'YYYY-MM-DD')";
		}
		if (StringUtils.isNotEmpty(endTime)) {
			hql += " and endTime >= to_date('"+endTime+"', 'YYYY-MM-DD')";
		}
		
		if (!pager.getLimit().getSort().isSorted()) {
			hql += " order by id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " "
					+ pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
	}

	@Override
	public List getJpoUserCouponsByCrmToSql(CommonRecord crm, Pager pager) {
		String sql ="select juc.id          id, "+
						"juc.user_code   user_code, "+
						"juc.status      status, "+
						"juc.remark      remark, "+
						"juc.create_time create_time, "+
						"juc.able_status able_status, "+
						"jci.cp_name     cp_name, "+
						"jci.cp_value    cp_value, "+
						"jcr.create_time sy_time "+
					"from JPO_USER_COUPON juc "+
					"left join JPM_COUPON_INFO jci on juc.cp_id = jci.cp_id "+
					"left join JPO_COUPON_RELATIONSHIP jcr on juc.id = jcr.cp_id "+
					"where 1 = 1 ";
		
		String userCode = crm.getString("userCode", "");
		String cpName = crm.getString("cpName", "");
		String status = crm.getString("status", "");
		String avlestatus = crm.getString("avlestatus", "");
		String createStartTime = crm.getString("createStartTime", "");
		String createEndTime = crm.getString("createEndTime", "");
		String syStartTime = crm.getString("syStartTime", "");
		String syEndTime = crm.getString("syEndTime", "");
		
		
		if (StringUtils.isNotEmpty(userCode)) {
			sql += " and juc.user_code='"+userCode.trim()+"'";
		}
		if (StringUtils.isNotEmpty(cpName)) {
			sql += " and jci.cp_name='"+cpName.trim()+"'";
		}
		if (StringUtils.isNotEmpty(status)) {
			sql += " and juc.status='"+status+"'";
		}
		if (StringUtils.isNotEmpty(avlestatus)) {
			if (avlestatus.equals("Y")) {
				sql += " and (juc.able_status is null  or juc.able_status='"+avlestatus+"')";
			}else{
				sql += " and juc.able_status='"+avlestatus+"'";
			}
		}
		if (StringUtils.isNotEmpty(createStartTime)||StringUtils.isNotEmpty(createEndTime)) {
			if (StringUtils.isEmpty(createStartTime)) {
				createStartTime="2001-01-01 00:00:00";
			}
			if (StringUtils.isEmpty(createEndTime)) {
				createEndTime="2050-01-01 00:00:00";
			}
			sql += " and (juc.create_time >= to_date('"+createStartTime+"', 'yyyy-MM-dd hh24:mi:ss') and juc.create_time <= to_date('"+createEndTime+"', 'yyyy-MM-dd hh24:mi:ss'))";
		}
		if (StringUtils.isNotEmpty(syStartTime)||StringUtils.isNotEmpty(syEndTime)) {
			if (StringUtils.isEmpty(syStartTime)) {
				syStartTime="2001-01-01 00:00:00";
			}
			if (StringUtils.isEmpty(syEndTime)) {
				syEndTime="2050-01-01 00:00:00";
			}
			sql += "  and (jcr.create_time >= to_date('"+syStartTime+"',  'yyyy-MM-dd hh24:mi:ss') and jcr.create_time <= to_date('"+syEndTime+"',  'yyyy-MM-dd hh24:mi:ss'))";
		}
		
		if (!pager.getLimit().getSort().isSorted()) {
			sql += " order by juc.id desc";
		} else {
			sql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " "
					+ pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsBySQL(sql, pager);
	}

	@Override
	public void updateUserCouponByIds(String ids) {
		ids=ids.substring(0,ids.length()-1);
		String replace = ids.replace(",", "','");
		String hql="from JpoUserCoupon where id in('"+replace+"') and status=0";
		List<JpoUserCoupon> find = (List<JpoUserCoupon>)this.getHibernateTemplate().find(hql);
		for (JpoUserCoupon juc : find) {
			String astatus=StringUtils.isEmpty(juc.getAbleStatus())?"Y":juc.getAbleStatus();
			if ("Y".equals(astatus)) {
				juc.setAbleStatus("N");
			}
			if ("N".equals(astatus)) {
				juc.setAbleStatus("Y");
			}
			this.getHibernateTemplate().update(juc);
		}
	}
}
