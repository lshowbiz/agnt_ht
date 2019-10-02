package com.joymain.jecs.sys.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.sys.model.SysLoginLog;
import com.joymain.jecs.sys.dao.SysLoginLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class SysLoginLogDaoHibernate extends BaseDaoHibernate implements
		SysLoginLogDao {

	public List getLogsByType(String userCode, String type) {
		// TODO Auto-generated method stub
		String queryString = "from SysLoginLog log where  log.userCode='" + userCode
				+ "' and log.operationType='" + type
				+ "' order by log.operateTime desc ";
		return this.getHibernateTemplate().find(queryString);
	}

	/**
	 * @see com.joymain.jecs.sys.dao.SysLoginLogDao#getSysLoginLogs(com.joymain.jecs.sys.model.SysLoginLog)
	 */
	public List getSysLoginLogs(final SysLoginLog sysLoginLog) {
		return getHibernateTemplate().find("from SysLoginLog");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (sysLoginLog == null) {
		 * return getHibernateTemplate().find("from SysLoginLog"); } else { //
		 * filter on properties set in the sysLoginLog HibernateCallback
		 * callback = new HibernateCallback() { public Object
		 * doInHibernate(Session session) throws HibernateException { Example ex
		 * =
		 * Example.create(sysLoginLog).ignoreCase().enableLike(MatchMode.ANYWHERE
		 * ); return session.createCriteria(SysLoginLog.class).add(ex).list(); }
		 * }; return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.joymain.jecs.sys.dao.SysLoginLogDao#getSysLoginLog(Long llId)
	 */
	public SysLoginLog getSysLoginLog(final Long llId) {
		SysLoginLog sysLoginLog = (SysLoginLog) getHibernateTemplate().get(
				SysLoginLog.class, llId);
		if (sysLoginLog == null) {
			log
					.warn("uh oh, sysLoginLog with llId '" + llId
							+ "' not found...");
			throw new ObjectRetrievalFailureException(SysLoginLog.class, llId);
		}

		return sysLoginLog;
	}

	/**
	 * @see com.joymain.jecs.sys.dao.SysLoginLogDao#saveSysLoginLog(SysLoginLog
	 *      sysLoginLog)
	 */
	public void saveSysLoginLog(final SysLoginLog sysLoginLog) {
		getHibernateTemplate().saveOrUpdate(sysLoginLog);
	}

	/**
	 * @see com.joymain.jecs.sys.dao.SysLoginLogDao#removeSysLoginLog(Long llId)
	 */
	public void removeSysLoginLog(final Long llId) {
		getHibernateTemplate().delete(getSysLoginLog(llId));
	}

	// added for getSysLoginLogsByCrm
	public List getSysLoginLogsByCrm(CommonRecord crm, Pager pager) {
		String hql = "from SysLoginLog sysLoginLog where 1=1";
		/**
		 * ---example--- String userCode = crm.getString("userCode", "");
		 * if(StringUtils.isNotEmpty(userCode)){ hql += "???????????"; }
		 ***/
		// 
		if (!pager.getLimit().getSort().isSorted()) {
			// sort
			hql += " order by llId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty()
					+ " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
	}
}
