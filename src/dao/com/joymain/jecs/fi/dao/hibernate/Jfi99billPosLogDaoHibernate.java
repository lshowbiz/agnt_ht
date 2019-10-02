
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.Jfi99billPosLog;
import com.joymain.jecs.fi.dao.Jfi99billPosLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class Jfi99billPosLogDaoHibernate extends BaseDaoHibernate implements Jfi99billPosLogDao {

    /**
     * @see com.joymain.jecs.fi.dao.Jfi99billPosLogDao#getJfi99billPosLogs(com.joymain.jecs.fi.model.Jfi99billPosLog)
     */
    public List getJfi99billPosLogs(final Jfi99billPosLog jfi99billPosLog) {
        return getHibernateTemplate().find("from Jfi99billPosLog");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jfi99billPosLog == null) {
            return getHibernateTemplate().find("from Jfi99billPosLog");
        } else {
            // filter on properties set in the jfi99billPosLog
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jfi99billPosLog).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(Jfi99billPosLog.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.Jfi99billPosLogDao#getJfi99billPosLog(long logId)
     */
    public Jfi99billPosLog getJfi99billPosLog(final long logId) {
        Jfi99billPosLog jfi99billPosLog = (Jfi99billPosLog) getHibernateTemplate().get(Jfi99billPosLog.class, logId);
        if (jfi99billPosLog == null) {
            log.warn("uh oh, jfi99billPosLog with logId '" + logId + "' not found...");
            throw new ObjectRetrievalFailureException(Jfi99billPosLog.class, logId);
        }

        return jfi99billPosLog;
    }

    /**
     * @see com.joymain.jecs.fi.dao.Jfi99billPosLogDao#saveJfi99billPosLog(Jfi99billPosLog jfi99billPosLog)
     */    
    public void saveJfi99billPosLog(final Jfi99billPosLog jfi99billPosLog) {
        getHibernateTemplate().saveOrUpdate(jfi99billPosLog);
    }

    /**
     * @see com.joymain.jecs.fi.dao.Jfi99billPosLogDao#removeJfi99billPosLog(long logId)
     */
    public void removeJfi99billPosLog(final long logId) {
        getHibernateTemplate().delete(getJfi99billPosLog(logId));
    }
    //added for getJfi99billPosLogsByCrm
    public List getJfi99billPosLogsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from Jfi99billPosLog jfi99billPosLog where 1=1";
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
