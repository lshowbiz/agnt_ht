
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdTravelPointLog;
import com.joymain.jecs.bd.dao.JbdTravelPointLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdTravelPointLogDaoHibernate extends BaseDaoHibernate implements JbdTravelPointLogDao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointLogDao#getJbdTravelPointLogs(com.joymain.jecs.bd.model.JbdTravelPointLog)
     */
    public List getJbdTravelPointLogs(final JbdTravelPointLog jbdTravelPointLog) {
        return getHibernateTemplate().find("from JbdTravelPointLog");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdTravelPointLog == null) {
            return getHibernateTemplate().find("from JbdTravelPointLog");
        } else {
            // filter on properties set in the jbdTravelPointLog
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdTravelPointLog).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdTravelPointLog.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointLogDao#getJbdTravelPointLog(Long logId)
     */
    public JbdTravelPointLog getJbdTravelPointLog(final Long logId) {
        JbdTravelPointLog jbdTravelPointLog = (JbdTravelPointLog) getHibernateTemplate().get(JbdTravelPointLog.class, logId);
        if (jbdTravelPointLog == null) {
            log.warn("uh oh, jbdTravelPointLog with logId '" + logId + "' not found...");
            throw new ObjectRetrievalFailureException(JbdTravelPointLog.class, logId);
        }

        return jbdTravelPointLog;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointLogDao#saveJbdTravelPointLog(JbdTravelPointLog jbdTravelPointLog)
     */    
    public void saveJbdTravelPointLog(final JbdTravelPointLog jbdTravelPointLog) {
        getHibernateTemplate().saveOrUpdate(jbdTravelPointLog);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointLogDao#removeJbdTravelPointLog(Long logId)
     */
    public void removeJbdTravelPointLog(final Long logId) {
        getHibernateTemplate().delete(getJbdTravelPointLog(logId));
    }
    //added for getJbdTravelPointLogsByCrm
    public List getJbdTravelPointLogsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdTravelPointLog jbdTravelPointLog where 1=1";
		String userCode = crm.getString("userCode", "");
		if(!StringUtil.isEmpty(userCode)){
			hql += " and userCode='"+userCode+"'";
		}String dealType = crm.getString("dealType", "");
		if(!StringUtil.isEmpty(dealType)){
			hql += " and dealType='"+dealType+"'";
		}
		String startTime = crm.getString("startTime", "");
		if(!StringUtil.isEmpty(startTime)){
			hql += " and createTime >= to_date('" + startTime
					+ " 00:00:00','yyyy-MM-dd hh24:mi:ss')";
		}

		String endTime = crm.getString("endTime", "");
		if(!StringUtil.isEmpty(endTime)){
			hql += " and createTime <= to_date('" + endTime
			+ " 23:59:59','yyyy-MM-dd hh24:mi:ss')";
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
