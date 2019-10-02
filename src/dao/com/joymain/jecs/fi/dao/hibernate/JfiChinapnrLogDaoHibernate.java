package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.dao.JfiChinapnrLogDao;
import com.joymain.jecs.fi.model.JfiChinapnrLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

@SuppressWarnings("rawtypes")
public class JfiChinapnrLogDaoHibernate extends BaseDaoHibernate implements JfiChinapnrLogDao {

	/**
	 * @see com.joymain.jecs.fi.dao.JfiChinapnrLogDao#getJfiChinapnrLogs(com.joymain.jecs.fi.model.JfiChinapnrLog)
	 */
	public List getJfiChinapnrLogs(final JfiChinapnrLog jfiChinapnrLog) {
		return getHibernateTemplate().find("from JfiChinapnrLog");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (jfiChinapnrLog == null) {
		 * return getHibernateTemplate().find("from JfiChinapnrLog"); } else {
		 * // filter on properties set in the jfiChinapnrLog HibernateCallback
		 * callback = new HibernateCallback() { public Object
		 * doInHibernate(Session session) throws HibernateException { Example ex
		 * =
		 * Example.create(jfiChinapnrLog).ignoreCase().enableLike(MatchMode.ANYWHERE
		 * ); return
		 * session.createCriteria(JfiChinapnrLog.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	public List getChinapnrLogsByMerId(String merId) {
		String hql = "from JfiChinapnrLog where merorderid='" + merId + "' AND inc='1'";
		return this.findObjectsByHqlQuery(hql);
	}

	/**
	 * @see com.joymain.jecs.fi.dao.JfiChinapnrLogDao#getJfiChinapnrLog(Long
	 *      logId)
	 */
	public JfiChinapnrLog getJfiChinapnrLog(final Long logId) {
		JfiChinapnrLog jfiChinapnrLog = (JfiChinapnrLog) getHibernateTemplate().get(JfiChinapnrLog.class, logId);
		if (jfiChinapnrLog == null) {
			log.warn("uh oh, jfiChinapnrLog with logId '" + logId + "' not found...");
			throw new ObjectRetrievalFailureException(JfiChinapnrLog.class, logId);
		}

		return jfiChinapnrLog;
	}

	/**
	 * @see com.joymain.jecs.fi.dao.JfiChinapnrLogDao#saveJfiChinapnrLog(JfiChinapnrLog
	 *      jfiChinapnrLog)
	 */
	public void saveJfiChinapnrLog(final JfiChinapnrLog jfiChinapnrLog) {
		getHibernateTemplate().saveOrUpdate(jfiChinapnrLog);
	}

	/**
	 * @see com.joymain.jecs.fi.dao.JfiChinapnrLogDao#removeJfiChinapnrLog(Long
	 *      logId)
	 */
	public void removeJfiChinapnrLog(final Long logId) {
		getHibernateTemplate().delete(getJfiChinapnrLog(logId));
	}

	// added for getJfiChinapnrLogsByCrm
	public List getJfiChinapnrLogsByCrm(CommonRecord crm, Pager pager) {
		String hql = "from JfiChinapnrLog jfiChinapnrLog where 1=1";
		String userCode = crm.getString("userCode", "");
		if (StringUtils.isNotEmpty(userCode)) {
			hql += "and userCode = '" + userCode + "'";
		}
		String merchantId = crm.getString("merchantId", "");
		if (StringUtils.isNotEmpty(merchantId)) {
			hql += "and merchantId = '" + merchantId + "'";
		}
		String payorderid = crm.getString("payorderid", "");
		if (StringUtils.isNotEmpty(payorderid)) {
			hql += "and payorderid = '" + payorderid + "'";
		}
		String inc = crm.getString("inc", "");
		if (StringUtils.isNotEmpty(inc)) {
			hql += "and inc = '" + inc + "'";
		}
		String startCreateTime = crm.getString("startCreateTime", "");
		if (StringUtils.isNotEmpty(startCreateTime)) {
			hql += " and createTime>=to_date('" + startCreateTime + "','yyyy-mm-dd hh24:mi:ss')";
		}
		String endCreateTime = crm.getString("endCreateTime", "");
		if (StringUtils.isNotEmpty(endCreateTime)) {
			hql += " and createTime<=to_date('" + endCreateTime + "','yyyy-mm-dd hh24:mi:ss')";
		}

		String dataType = crm.getString("dataType", "");
		if (StringUtils.isNotEmpty(dataType)) {
			hql += " and dataType = '" + dataType + "'";
		}

		//
		if (!pager.getLimit().getSort().isSorted()) {
			// sort
			hql += " order by logId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
	}
}
