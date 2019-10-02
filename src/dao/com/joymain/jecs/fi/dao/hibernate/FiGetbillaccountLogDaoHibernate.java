
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.FiGetbillaccountLog;
import com.joymain.jecs.fi.dao.FiGetbillaccountLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class FiGetbillaccountLogDaoHibernate extends BaseDaoHibernate implements FiGetbillaccountLogDao {

    /**
     * @see com.joymain.jecs.fi.dao.FiGetbillaccountLogDao#getFiGetbillaccountLogs(com.joymain.jecs.fi.model.FiGetbillaccountLog)
     */
    public List getFiGetbillaccountLogs(final FiGetbillaccountLog fiGetbillaccountLog) {
        return getHibernateTemplate().find("from FiGetbillaccountLog");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (fiGetbillaccountLog == null) {
            return getHibernateTemplate().find("from FiGetbillaccountLog");
        } else {
            // filter on properties set in the fiGetbillaccountLog
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(fiGetbillaccountLog).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(FiGetbillaccountLog.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiGetbillaccountLogDao#getFiGetbillaccountLog(Long logId)
     */
    public FiGetbillaccountLog getFiGetbillaccountLog(final Long logId) {
        FiGetbillaccountLog fiGetbillaccountLog = (FiGetbillaccountLog) getHibernateTemplate().get(FiGetbillaccountLog.class, logId);
        if (fiGetbillaccountLog == null) {
            log.warn("uh oh, fiGetbillaccountLog with logId '" + logId + "' not found...");
            throw new ObjectRetrievalFailureException(FiGetbillaccountLog.class, logId);
        }

        return fiGetbillaccountLog;
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiGetbillaccountLogDao#saveFiGetbillaccountLog(FiGetbillaccountLog fiGetbillaccountLog)
     */    
    public void saveFiGetbillaccountLog(final FiGetbillaccountLog fiGetbillaccountLog) {
        getHibernateTemplate().saveOrUpdate(fiGetbillaccountLog);
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiGetbillaccountLogDao#removeFiGetbillaccountLog(Long logId)
     */
    public void removeFiGetbillaccountLog(final Long logId) {
        getHibernateTemplate().delete(getFiGetbillaccountLog(logId));
    }
    //added for getFiGetbillaccountLogsByCrm
    public List getFiGetbillaccountLogsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from FiGetbillaccountLog fiGetbillaccountLog where 1=1";


		String userCode = crm.getString("userCode", "");
		if(StringUtils.isNotEmpty(userCode)){
			hql += " and userCode='"+userCode.trim()+"' ";
		}
		String billAccountCode = crm.getString("billAccountCode", "");
		if(StringUtils.isNotEmpty(billAccountCode)){
			hql += " and billAccountCode='"+billAccountCode.trim()+"' ";
		}

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
