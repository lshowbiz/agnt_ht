
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.JfiChinapayPosLog;
import com.joymain.jecs.fi.dao.JfiChinapayPosLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JfiChinapayPosLogDaoHibernate extends BaseDaoHibernate implements JfiChinapayPosLogDao {

    /**
     * @see com.joymain.jecs.fi.dao.JfiChinapayPosLogDao#getJfiChinapayPosLogs(com.joymain.jecs.fi.model.JfiChinapayPosLog)
     */
    public List getJfiChinapayPosLogs(final JfiChinapayPosLog jfiChinapayPosLog) {
        return getHibernateTemplate().find("from JfiChinapayPosLog");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jfiChinapayPosLog == null) {
            return getHibernateTemplate().find("from JfiChinapayPosLog");
        } else {
            // filter on properties set in the jfiChinapayPosLog
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jfiChinapayPosLog).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JfiChinapayPosLog.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiChinapayPosLogDao#getJfiChinapayPosLog(Long logId)
     */
    public JfiChinapayPosLog getJfiChinapayPosLog(final Long logId) {
        JfiChinapayPosLog jfiChinapayPosLog = (JfiChinapayPosLog) getHibernateTemplate().get(JfiChinapayPosLog.class, logId);
        if (jfiChinapayPosLog == null) {
            log.warn("uh oh, jfiChinapayPosLog with logId '" + logId + "' not found...");
            throw new ObjectRetrievalFailureException(JfiChinapayPosLog.class, logId);
        }

        return jfiChinapayPosLog;
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiChinapayPosLogDao#saveJfiChinapayPosLog(JfiChinapayPosLog jfiChinapayPosLog)
     */    
    public void saveJfiChinapayPosLog(final JfiChinapayPosLog jfiChinapayPosLog) {
        getHibernateTemplate().save(jfiChinapayPosLog);
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiChinapayPosLogDao#removeJfiChinapayPosLog(Long logId)
     */
    public void removeJfiChinapayPosLog(final Long logId) {
        getHibernateTemplate().delete(getJfiChinapayPosLog(logId));
    }
    //added for getJfiChinapayPosLogsByCrm
    public List getJfiChinapayPosLogsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JfiChinapayPosLog jfiChinapayPosLog where 1=1";
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
