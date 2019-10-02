package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.dao.JfiPayLogDao;
import com.joymain.jecs.fi.model.JfiPayLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

@SuppressWarnings("rawtypes")
public class JfiPayLogDaoHibernate extends BaseDaoHibernate implements JfiPayLogDao {

	/**
	 * @see com.joymain.jecs.fi.dao.JfiPayLogDao#getJfiPayLogs(com.joymain.jecs.fi.model.JfiPayLog)
	 */

	public List getJfiPayLogs(final JfiPayLog jfiPayLog) {
		return getHibernateTemplate().findByExample(jfiPayLog);
		/* Remove the line above and uncomment this code block if you want 
        to use Hibernate's Query by Example API.
	     if (jfiPayLog == null) {
	         return getHibernateTemplate().find("from JfiPayLog");
	     } else {
	         // filter on properties set in the jfiPayLog
	         HibernateCallback callback = new HibernateCallback() {
	             public Object doInHibernate(Session session) throws HibernateException {
	                 Example ex = Example.create(jfiPayLog).ignoreCase().enableLike(MatchMode.ANYWHERE);
	                 return session.createCriteria(JfiPayLog.class).add(ex).list();
	             }
	         };
	         return (List) getHibernateTemplate().execute(callback);
	     }*/
	}

	/**
	 * @see com.joymain.jecs.fi.dao.JfiPayLogDao#getJfiPayLog(Long logId)
	 */
	public JfiPayLog getJfiPayLog(final Long logId) {
		JfiPayLog jfiPayLog = (JfiPayLog) getHibernateTemplate().get(JfiPayLog.class, logId);
		if (jfiPayLog == null) {
			log.warn("uh oh, jfiPayLog with logId '" + logId + "' not found...");
			throw new ObjectRetrievalFailureException(JfiPayLog.class, logId);
		}

		return jfiPayLog;
	}

	/**
	 * @see com.joymain.jecs.fi.dao.JfiPayLogDao#saveJfiPayLog(JfiPayLog
	 *      jfiPayLog)
	 */
	public void saveJfiPayLog(final JfiPayLog jfiPayLog) {
		getHibernateTemplate().saveOrUpdate(jfiPayLog);
	}

	/**
	 * @see com.joymain.jecs.fi.dao.JfiPayLogDao#removeJfiPayLog(Long logId)
	 */
	public void removeJfiPayLog(final Long logId) {
		getHibernateTemplate().delete(getJfiPayLog(logId));
	}

	// added for getJfiPayLogsByCrm
	public List getJfiPayLogsByCrm(CommonRecord crm, Pager pager) {
		String hql = "from JfiPayLog jfiPayLog where 1=1";
		
		String userCode = crm.getString("userCode", "");
		if (StringUtils.isNotEmpty(userCode)) {
			hql += "and userCode = '" + userCode + "'";
		}
		String merchantId = crm.getString("merchantId", "");
		if (StringUtils.isNotEmpty(merchantId)) {
			hql += "and merchantId = '" + merchantId + "'";
		}
		String inc = crm.getString("inc", "");
		if (StringUtils.isNotEmpty(inc)) {
			hql += "and inc = '" + inc + "'";
		}
		String startCreateTime = crm.getString("startCreateTime", "");
		if (StringUtils.isNotEmpty(startCreateTime)) {
			hql += " and to_date(createTime,'yyyy-mm-dd hh24:mi:ss')>=to_date('" + startCreateTime + "','yyyy-mm-dd hh24:mi:ss')";
		}
		String endCreateTime = crm.getString("endCreateTime", "");
		if (StringUtils.isNotEmpty(endCreateTime)) {
			hql += " and to_date(createTime,'yyyy-mm-dd hh24:mi:ss')<=to_date('" + endCreateTime + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
		
		String dataType = crm.getString("dataType", "");
		if (StringUtils.isNotEmpty(dataType)) {
			hql += " and dataType= '" + dataType + "'";
		}
		String payType = crm.getString("payType", "");
		if (StringUtils.isNotEmpty(payType)) {
			hql += " and payType= '" + payType + "'";
		}
		if (!pager.getLimit().getSort().isSorted()) {
			hql += " order by logId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
	}
}
