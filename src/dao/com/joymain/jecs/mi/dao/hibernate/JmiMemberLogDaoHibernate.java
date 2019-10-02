
package com.joymain.jecs.mi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.mi.model.JmiMemberLog;
import com.joymain.jecs.mi.dao.JmiMemberLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JmiMemberLogDaoHibernate extends BaseDaoHibernate implements JmiMemberLogDao {

    /**
     * @see com.joymain.jecs.mi.dao.JmiMemberLogDao#getJmiMemberLogs(com.joymain.jecs.mi.model.JmiMemberLog)
     */
    public List getJmiMemberLogs(final JmiMemberLog jmiMemberLog) {
        return getHibernateTemplate().find("from JmiMemberLog");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jmiMemberLog == null) {
            return getHibernateTemplate().find("from JmiMemberLog");
        } else {
            // filter on properties set in the jmiMemberLog
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jmiMemberLog).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JmiMemberLog.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiMemberLogDao#getJmiMemberLog(String logId)
     */
    public JmiMemberLog getJmiMemberLog(final String logId) {
        JmiMemberLog jmiMemberLog = (JmiMemberLog) getHibernateTemplate().get(JmiMemberLog.class, logId);
        if (jmiMemberLog == null) {
            log.warn("uh oh, jmiMemberLog with logId '" + logId + "' not found...");
            throw new ObjectRetrievalFailureException(JmiMemberLog.class, logId);
        }

        return jmiMemberLog;
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiMemberLogDao#saveJmiMemberLog(JmiMemberLog jmiMemberLog)
     */    
    public void saveJmiMemberLog(final JmiMemberLog jmiMemberLog) {
        getHibernateTemplate().saveOrUpdate(jmiMemberLog);
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiMemberLogDao#removeJmiMemberLog(String logId)
     */
    public void removeJmiMemberLog(final String logId) {
        getHibernateTemplate().delete(getJmiMemberLog(logId));
    }
    //added for getJmiMemberLogsByCrm
    public List getJmiMemberLogsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JmiMemberLog jmiMemberLog where 1=1";
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
