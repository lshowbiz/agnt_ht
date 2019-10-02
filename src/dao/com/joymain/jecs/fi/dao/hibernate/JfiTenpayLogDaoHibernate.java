
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.JfiTenpayLog;
import com.joymain.jecs.fi.dao.JfiTenpayLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JfiTenpayLogDaoHibernate extends BaseDaoHibernate implements JfiTenpayLogDao {

    /**
     * @see com.joymain.jecs.fi.dao.JfiTenpayLogDao#getJfiTenpayLogs(com.joymain.jecs.fi.model.JfiTenpayLog)
     */
    public List getJfiTenpayLogs(final JfiTenpayLog jfiTenpayLog) {
    	return getHibernateTemplate().findByExample(jfiTenpayLog);

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jfiTenpayLog == null) {
            return getHibernateTemplate().find("from JfiTenpayLog");
        } else {
            // filter on properties set in the jfiTenpayLog
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jfiTenpayLog).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JfiTenpayLog.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiTenpayLogDao#getJfiTenpayLog(Long logId)
     */
    public JfiTenpayLog getJfiTenpayLog(final Long logId) {
        JfiTenpayLog jfiTenpayLog = (JfiTenpayLog) getHibernateTemplate().get(JfiTenpayLog.class, logId);
        if (jfiTenpayLog == null) {
            log.warn("uh oh, jfiTenpayLog with logId '" + logId + "' not found...");
            throw new ObjectRetrievalFailureException(JfiTenpayLog.class, logId);
        }

        return jfiTenpayLog;
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiTenpayLogDao#saveJfiTenpayLog(JfiTenpayLog jfiTenpayLog)
     */    
    public void saveJfiTenpayLog(final JfiTenpayLog jfiTenpayLog) {
        getHibernateTemplate().saveOrUpdate(jfiTenpayLog);
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiTenpayLogDao#removeJfiTenpayLog(Long logId)
     */
    public void removeJfiTenpayLog(final Long logId) {
        getHibernateTemplate().delete(getJfiTenpayLog(logId));
    }
    //added for getJfiTenpayLogsByCrm
    public List getJfiTenpayLogsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JfiTenpayLog jfiTenpayLog where 1=1";

		String userCode = crm.getString("userCode", "");
		if(StringUtils.isNotEmpty(userCode)){
			hql += " and userCode = '" + userCode + "'";
		}

		String inc = crm.getString("inc", "");
		if(StringUtils.isNotEmpty(inc)){
			hql += " and inc = '" + inc + "'";
		}

		String bargainorId = crm.getString("bargainorId", "");
		if(StringUtils.isNotEmpty(bargainorId)){
			hql += " and bargainorId = '" + bargainorId + "'";
		}

		String transactionId = crm.getString("transactionId", "");
		if(StringUtils.isNotEmpty(transactionId)){
			hql += " and transactionId = '" + transactionId + "'";
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
