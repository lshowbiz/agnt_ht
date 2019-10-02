
package com.joymain.jecs.sms.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.sms.model.SmsSendLog;
import com.joymain.jecs.sms.dao.SmsSendLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class SmsSendLogDaoHibernate extends BaseDaoHibernate implements SmsSendLogDao {

    /**
     * @see com.joymain.jecs.sms.dao.SmsSendLogDao#getSmsSendLogs(com.joymain.jecs.sms.model.SmsSendLog)
     */
    public List getSmsSendLogs(final SmsSendLog smsSendLog) {
        return getHibernateTemplate().find("from SmsSendLog");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (smsSendLog == null) {
            return getHibernateTemplate().find("from SmsSendLog");
        } else {
            // filter on properties set in the smsSendLog
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(smsSendLog).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(SmsSendLog.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.sms.dao.SmsSendLogDao#getSmsSendLog(Long sslId)
     */
    public SmsSendLog getSmsSendLog(final Long sslId) {
        SmsSendLog smsSendLog = (SmsSendLog) getHibernateTemplate().get(SmsSendLog.class, sslId);
        if (smsSendLog == null) {
            log.warn("uh oh, smsSendLog with sslId '" + sslId + "' not found...");
            throw new ObjectRetrievalFailureException(SmsSendLog.class, sslId);
        }

        return smsSendLog;
    }

    /**
     * @see com.joymain.jecs.sms.dao.SmsSendLogDao#saveSmsSendLog(SmsSendLog smsSendLog)
     */    
    public void saveSmsSendLog(final SmsSendLog smsSendLog) {
        getHibernateTemplate().saveOrUpdate(smsSendLog);
    }

    /**
     * @see com.joymain.jecs.sms.dao.SmsSendLogDao#removeSmsSendLog(Long sslId)
     */
    public void removeSmsSendLog(final Long sslId) {
        getHibernateTemplate().delete(getSmsSendLog(sslId));
    }
    //added for getSmsSendLogsByCrm
    public List getSmsSendLogsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from SmsSendLog smsSendLog where 1=1";
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
			hql += " order by sslId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
