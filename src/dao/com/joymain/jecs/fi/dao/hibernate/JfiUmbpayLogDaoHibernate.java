package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.dao.JfiUmbpayLogDao;
import com.joymain.jecs.fi.model.JfiUmbpayLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
@SuppressWarnings("rawtypes")
public class JfiUmbpayLogDaoHibernate extends BaseDaoHibernate implements JfiUmbpayLogDao {

	/**
	 * @see com.joymain.jecs.fi.dao.JfiUmbpayLogDao#getJfiUmbpayLogs(com.joymain.jecs.fi.model.JfiUmbpayLog)
	 */
	public List getJfiUmbpayLogs(final JfiUmbpayLog jfiUmbpayLog) {
		return getHibernateTemplate().find("from JfiUmbpayLog");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (jfiUmbpayLog == null) {
		 * return getHibernateTemplate().find("from JfiUmbpayLog"); } else { //
		 * filter on properties set in the jfiUmbpayLog HibernateCallback
		 * callback = new HibernateCallback() { public Object
		 * doInHibernate(Session session) throws HibernateException { Example ex
		 * =
		 * Example.create(jfiUmbpayLog).ignoreCase().enableLike(MatchMode.ANYWHERE
		 * ); return session.createCriteria(JfiUmbpayLog.class).add(ex).list();
		 * } }; return (List) getHibernateTemplate().execute(callback); }
		 */
	}
	//订单号相同的并且支付成功的
	public List getJfiUmbpayLogsByMerId(String merId){
		//String hql="FROM JfiUmbpayLog where payorderid='"+ merId +"' AND inc='1'";
		String hql="FROM JfiUmbpayLog where merorderid='"+ merId +"' AND inc='1'"; 
		return this.findObjectsByHqlQuery(hql);
	}
	
	
	/**
	 * @see com.joymain.jecs.fi.dao.JfiUmbpayLogDao#getJfiUmbpayLog(Long logId)
	 */
	public JfiUmbpayLog getJfiUmbpayLog(final Long logId) {
		JfiUmbpayLog jfiUmbpayLog = (JfiUmbpayLog) getHibernateTemplate().get(JfiUmbpayLog.class, logId);
		if (jfiUmbpayLog == null) {
			log.warn("uh oh, jfiUmbpayLog with logId '" + logId + "' not found...");
			throw new ObjectRetrievalFailureException(JfiUmbpayLog.class, logId);
		}

		return jfiUmbpayLog;
	}

	/**
	 * @see com.joymain.jecs.fi.dao.JfiUmbpayLogDao#saveJfiUmbpayLog(JfiUmbpayLog
	 *      jfiUmbpayLog)
	 */
	public void saveJfiUmbpayLog(final JfiUmbpayLog jfiUmbpayLog) {
		getHibernateTemplate().saveOrUpdate(jfiUmbpayLog);
	}

	/**
	 * @see com.joymain.jecs.fi.dao.JfiUmbpayLogDao#removeJfiUmbpayLog(Long
	 *      logId)
	 */
	public void removeJfiUmbpayLog(final Long logId) {
		getHibernateTemplate().delete(getJfiUmbpayLog(logId));
	}

	// added for getJfiUmbpayLogsByCrm
	public List getJfiUmbpayLogsByCrm(CommonRecord crm, Pager pager) {
		String hql = "from JfiUmbpayLog jfiUmbpayLog where 1=1";

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
		/**
		 * ---example--- String userCode = crm.getString("userCode", "");
		 * if(StringUtils.isNotEmpty(userCode)){ hql += "???????????"; }
		 ***/
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
