
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.JfiCreditCardLog;
import com.joymain.jecs.fi.dao.JfiCreditCardLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JfiCreditCardLogDaoHibernate extends BaseDaoHibernate implements JfiCreditCardLogDao {

    /**
     * @see com.joymain.jecs.fi.dao.JfiCreditCardLogDao#getJfiCreditCardLogs(com.joymain.jecs.fi.model.JfiCreditCardLog)
     */
    public List getJfiCreditCardLogs(final JfiCreditCardLog jfiCreditCardLog) {
        return getHibernateTemplate().find("from JfiCreditCardLog");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jfiCreditCardLog == null) {
            return getHibernateTemplate().find("from JfiCreditCardLog");
        } else {
            // filter on properties set in the jfiCreditCardLog
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jfiCreditCardLog).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JfiCreditCardLog.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiCreditCardLogDao#getJfiCreditCardLog(Long logId)
     */
    public JfiCreditCardLog getJfiCreditCardLog(final Long logId) {
        JfiCreditCardLog jfiCreditCardLog = (JfiCreditCardLog) getHibernateTemplate().get(JfiCreditCardLog.class, logId);
        if (jfiCreditCardLog == null) {
            log.warn("uh oh, jfiCreditCardLog with logId '" + logId + "' not found...");
            throw new ObjectRetrievalFailureException(JfiCreditCardLog.class, logId);
        }

        return jfiCreditCardLog;
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiCreditCardLogDao#saveJfiCreditCardLog(JfiCreditCardLog jfiCreditCardLog)
     */    
    public void saveJfiCreditCardLog(final JfiCreditCardLog jfiCreditCardLog) {
        getHibernateTemplate().saveOrUpdate(jfiCreditCardLog);
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiCreditCardLogDao#removeJfiCreditCardLog(Long logId)
     */
    public void removeJfiCreditCardLog(final Long logId) {
        getHibernateTemplate().delete(getJfiCreditCardLog(logId));
    }
    //added for getJfiCreditCardLogsByCrm
    public List getJfiCreditCardLogsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JfiCreditCardLog jfiCreditCardLog where 1=1";
    	/** 
	---example---
	String userCode = crm.getString("userCode", "");
	if(StringUtils.isNotEmpty(userCode)){
		hql += "???????????";
	}
	 ***/
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by logId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
