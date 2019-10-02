
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.JfiHiTrustLog;
import com.joymain.jecs.fi.dao.JfiHiTrustLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JfiHiTrustLogDaoHibernate extends BaseDaoHibernate implements JfiHiTrustLogDao {

    /**
     * @see com.joymain.jecs.fi.dao.JfiHiTrustLogDao#getJfiHiTrustLogs(com.joymain.jecs.fi.model.JfiHiTrustLog)
     */
    public List getJfiHiTrustLogs(final JfiHiTrustLog jfiHiTrustLog) {
    	
        return getHibernateTemplate().findByExample(jfiHiTrustLog);

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jfiHiTrustLog == null) {
            return getHibernateTemplate().find("from JfiHiTrustLog");
        } else {
            // filter on properties set in the jfiHiTrustLog
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jfiHiTrustLog).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JfiHiTrustLog.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiHiTrustLogDao#getJfiHiTrustLog(Long logId)
     */
    public JfiHiTrustLog getJfiHiTrustLog(final Long logId) {
        JfiHiTrustLog jfiHiTrustLog = (JfiHiTrustLog) getHibernateTemplate().get(JfiHiTrustLog.class, logId);
        if (jfiHiTrustLog == null) {
            log.warn("uh oh, jfiHiTrustLog with logId '" + logId + "' not found...");
            throw new ObjectRetrievalFailureException(JfiHiTrustLog.class, logId);
        }

        return jfiHiTrustLog;
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiHiTrustLogDao#saveJfiHiTrustLog(JfiHiTrustLog jfiHiTrustLog)
     */    
    public void saveJfiHiTrustLog(final JfiHiTrustLog jfiHiTrustLog) {
        getHibernateTemplate().saveOrUpdate(jfiHiTrustLog);
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiHiTrustLogDao#removeJfiHiTrustLog(Long logId)
     */
    public void removeJfiHiTrustLog(final Long logId) {
        getHibernateTemplate().delete(getJfiHiTrustLog(logId));
    }
    //added for getJfiHiTrustLogsByCrm
    public List getJfiHiTrustLogsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JfiHiTrustLog jfiHiTrustLog where 1=1";
    	
    	String orderNo = crm.getString("orderNo", "");
		if(StringUtils.isNotEmpty(orderNo)){
			hql += " and orderNo like '%" + orderNo + "%'";
		}
		
    	String authRrn = crm.getString("authRrn", "");
		if(StringUtils.isNotEmpty(authRrn)){
			hql += " and authRrn = '" + authRrn + "'";
		}
		
    	String inc = crm.getString("inc", "");
		if(StringUtils.isNotEmpty(inc)){
			hql += " and inc = '" + inc + "'";
		}

		String startCreateTime = crm.getString("startCreateTime", "");
		if(StringUtils.isNotEmpty(startCreateTime)){
			hql += " and createTime >= to_date('" + startCreateTime + "','yyyy-mm-dd hh24:mi:ss')";
		}

		String endCreateTime = crm.getString("endCreateTime", "");
		if(StringUtils.isNotEmpty(endCreateTime)){
			hql += " and createTime < to_date('" + endCreateTime + "','yyyy-mm-dd hh24:mi:ss')";
		}
    	
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by logId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
