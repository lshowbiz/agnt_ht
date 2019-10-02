package com.joymain.jecs.sun.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.sun.model.SunDistShip;
import com.joymain.jecs.sun.dao.SunDistShipDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class SunDistShipDaoHibernate extends BaseDaoHibernate implements
		SunDistShipDao {

	/**
	 * @see com.joymain.jecs.sun.dao.SunDistShipDao#getSunDistShips(com.joymain.jecs.sun.model.SunDistShip)
	 */
	public List getSunDistShips(final SunDistShip sunDistShip) {
		return getHibernateTemplate().find("from SunDistShip");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (sunDistShip == null) {
		 * return getHibernateTemplate().find("from SunDistShip"); } else { //
		 * filter on properties set in the sunDistShip HibernateCallback
		 * callback = new HibernateCallback() { public Object
		 * doInHibernate(Session session) throws HibernateException { Example ex
		 * =
		 * Example.create(sunDistShip).ignoreCase().enableLike(MatchMode.ANYWHERE
		 * ); return session.createCriteria(SunDistShip.class).add(ex).list(); }
		 * }; return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.joymain.jecs.sun.dao.SunDistShipDao#getSunDistShip(Long sdsId)
	 */
	public SunDistShip getSunDistShip(final Long sdsId) {
		SunDistShip sunDistShip = (SunDistShip) getHibernateTemplate().get(
				SunDistShip.class, sdsId);
		if (sunDistShip == null) {
			log.warn("uh oh, sunDistShip with sdsId '" + sdsId
					+ "' not found...");
			throw new ObjectRetrievalFailureException(SunDistShip.class, sdsId);
		}

		return sunDistShip;
	}

	/**
	 * @see com.joymain.jecs.sun.dao.SunDistShipDao#saveSunDistShip(SunDistShip
	 *      sunDistShip)
	 */
	public void saveSunDistShip(final SunDistShip sunDistShip) {
		getHibernateTemplate().saveOrUpdate(sunDistShip);
	}

	/**
	 * @see com.joymain.jecs.sun.dao.SunDistShipDao#removeSunDistShip(Long
	 *      sdsId)
	 */
	public void removeSunDistShip(final Long sdsId) {
		getHibernateTemplate().delete(getSunDistShip(sdsId));
	}

	public void createSunShipData(CommonRecord crm) {
		String sql = "insert into sun_dist_ship(sds_id,si_no,dist_code) select seq_pd.nextval,pd.si_no,sun.user_code " +
				"from pd_send_info pd,jfi_sun_agent_area sun where pd.recipient_ca_no=sun.area_id";
		String createTimeStart = crm.getString("createTimeStart", "");
		String createTimeEnd = crm.getString("createTimeEnd", "");
		if (StringUtils.isNotEmpty(createTimeStart)) {
			sql += " and pd.create_Time >=to_date('"
					+ createTimeStart + " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
		}
		if (StringUtils.isNotEmpty(createTimeEnd)) {
			sql += " and pd.create_Time <=to_date('" + createTimeEnd
					+ " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
		}
		this.getJdbcTemplate().execute(sql);
	}

	public void autoCreateSunShipDate(){
		//Modify By WuCF 20130830 修改为not exists 代替 not in
		String sql ="insert into sun_dist_ship(sds_id,si_no,dist_code,create_time) select seq_pd.nextval,pd.si_no,sun.user_code,pd.create_time "+
		"from pd_send_info pd,jfi_sun_agent_area sun where pd.recipient_ca_no=sun.area_id                                          "+
		" and pd.create_Time >=sysDate-2 and   not exists(                                                                    "+
		"     select 1 from sun_dist_ship ss where ss.create_time >=sysDate-3 and pd.si_no=ss.si_no                                              "+
		" )                                                                                                                        ";
		this.getJdbcTemplate().execute(sql);
	}
	// added for getSunDistShipsByCrm
	public List getSunDistShipsByCrm(CommonRecord crm, Pager pager) {
		String hql = "from SunDistShip s where 1=1";
		/**
		 * ---example--- String userCode = crm.getString("userCode", "");
		 * if(StringUtils.isNotEmpty(userCode)){ hql += "???????????"; }
		 ***/
		String distCode = crm.getString("distCode", "");
		if (StringUtils.isNotEmpty(distCode)) {
			hql += " and s.distCode='" + distCode + "'";
		}
		
		String orderType = crm.getString("orderType", "");
		if (StringUtils.isNotEmpty(orderType)) {
			hql += " and s.pdSendInfo.orderType = '" + orderType + "'";
		}

		String orderNo = crm.getString("orderNo", "");
		if (StringUtils.isNotEmpty(orderNo)) {
			hql += " and s.pdSendInfo.orderNo = '" + orderNo + "'";
		}
		String createTimeStart = crm.getString("createTimeStart", "");
		String createTimeEnd = crm.getString("createTimeEnd", "");
		if (StringUtils.isNotEmpty(createTimeStart)) {
			hql += " and s.pdSendInfo.createTime >=to_date('" + createTimeStart
					+ " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
		}
		if (StringUtils.isNotEmpty(createTimeEnd)) {
			hql += " and s.pdSendInfo.createTime <=to_date('" + createTimeEnd
					+ " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
		}

		String checkTimeStart = crm.getString("checkTimeStart", "");
		String checkTimeEnd = crm.getString("checkTimeEnd", "");
		if (StringUtils.isNotEmpty(checkTimeStart)) {
			hql += " and s.pdSendInfo.checkTime >=to_date('" + checkTimeStart
					+ " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
		}
		if (StringUtils.isNotEmpty(checkTimeEnd)) {
			hql += " and s.pdSendInfo.checkTime <=to_date('" + checkTimeEnd
					+ " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
		}

		String okTimeStart = crm.getString("okTimeStart", "");
		String okTimeEnd = crm.getString("okTimeEnd", "");
		if (StringUtils.isNotEmpty(okTimeStart)) {
			hql += " and s.pdSendInfo.okTime >=to_date('" + okTimeStart
					+ " 00:00:00','yyyy-mm-dd HH24:MI:SS')";
		}
		if (StringUtils.isNotEmpty(okTimeEnd)) {
			hql += " and s.pdSendInfo.okTime <=to_date('" + okTimeEnd
					+ " 23:59:59','yyyy-mm-dd HH24:MI:SS')";
		}
		// 
		if (!pager.getLimit().getSort().isSorted()) {
			// sort
			hql += " order by sdsId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty()
					+ " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
	}
}
