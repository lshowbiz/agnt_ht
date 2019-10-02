
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.Jfi99billPosSendLog;
import com.joymain.jecs.fi.dao.Jfi99billPosSendLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class Jfi99billPosSendLogDaoHibernate extends BaseDaoHibernate implements Jfi99billPosSendLogDao {

    /**
     * @see com.joymain.jecs.fi.dao.Jfi99billPosSendLogDao#getJfi99billPosSendLogs(com.joymain.jecs.fi.model.Jfi99billPosSendLog)
     */
    public List getJfi99billPosSendLogs(final Jfi99billPosSendLog jfi99billPosSendLog) {
    	String hql = "from Jfi99billPosSendLog jfi99billPosSendLog where hashMd5Code='" + jfi99billPosSendLog.getHashMd5Code() + "'";
    	return this.findObjectsByHqlQuery(hql);
        //return getHibernateTemplate().find("from Jfi99billPosSendLog");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jfi99billPosSendLog == null) {
            return getHibernateTemplate().find("from Jfi99billPosSendLog");
        } else {
            // filter on properties set in the jfi99billPosSendLog
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jfi99billPosSendLog).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(Jfi99billPosSendLog.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.Jfi99billPosSendLogDao#getJfi99billPosSendLog(Long logId)
     */
    public Jfi99billPosSendLog getJfi99billPosSendLog(final Long logId) {
        Jfi99billPosSendLog jfi99billPosSendLog = (Jfi99billPosSendLog) getHibernateTemplate().get(Jfi99billPosSendLog.class, logId);
        if (jfi99billPosSendLog == null) {
            log.warn("uh oh, jfi99billPosSendLog with logId '" + logId + "' not found...");
            throw new ObjectRetrievalFailureException(Jfi99billPosSendLog.class, logId);
        }

        return jfi99billPosSendLog;
    }

    /**
     * @see com.joymain.jecs.fi.dao.Jfi99billPosSendLogDao#saveJfi99billPosSendLog(Jfi99billPosSendLog jfi99billPosSendLog)
     */    
    public void saveJfi99billPosSendLog(final Jfi99billPosSendLog jfi99billPosSendLog) {
        getHibernateTemplate().saveOrUpdate(jfi99billPosSendLog);
    }

    /**
     * @see com.joymain.jecs.fi.dao.Jfi99billPosSendLogDao#removeJfi99billPosSendLog(Long logId)
     */
    public void removeJfi99billPosSendLog(final Long logId) {
        getHibernateTemplate().delete(getJfi99billPosSendLog(logId));
    }
    //added for getJfi99billPosSendLogsByCrm
    public List getJfi99billPosSendLogsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from Jfi99billPosSendLog jfi99billPosSendLog where 1=1";
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
