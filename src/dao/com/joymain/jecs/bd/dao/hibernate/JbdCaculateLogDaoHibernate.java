
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdCaculateLog;
import com.joymain.jecs.bd.dao.JbdCaculateLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdCaculateLogDaoHibernate extends BaseDaoHibernate implements JbdCaculateLogDao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdCaculateLogDao#getJbdCaculateLogs(com.joymain.jecs.bd.model.JbdCaculateLog)
     */
    public List getJbdCaculateLogs(final JbdCaculateLog jbdCaculateLog) {
        return getHibernateTemplate().find("from JbdCaculateLog");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdCaculateLog == null) {
            return getHibernateTemplate().find("from JbdCaculateLog");
        } else {
            // filter on properties set in the jbdCaculateLog
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdCaculateLog).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdCaculateLog.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdCaculateLogDao#getJbdCaculateLog(BigDecimal id)
     */
    public JbdCaculateLog getJbdCaculateLog(final BigDecimal id) {
        JbdCaculateLog jbdCaculateLog = (JbdCaculateLog) getHibernateTemplate().get(JbdCaculateLog.class, id);
        if (jbdCaculateLog == null) {
            log.warn("uh oh, jbdCaculateLog with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JbdCaculateLog.class, id);
        }

        return jbdCaculateLog;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdCaculateLogDao#saveJbdCaculateLog(JbdCaculateLog jbdCaculateLog)
     */    
    public void saveJbdCaculateLog(final JbdCaculateLog jbdCaculateLog) {
        getHibernateTemplate().saveOrUpdate(jbdCaculateLog);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdCaculateLogDao#removeJbdCaculateLog(BigDecimal id)
     */
    public void removeJbdCaculateLog(final BigDecimal id) {
        getHibernateTemplate().delete(getJbdCaculateLog(id));
    }
    //added for getJbdCaculateLogsByCrm
    public List getJbdCaculateLogsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdCaculateLog jbdCaculateLog where 1=1";

    	String wweek=crm.getString("wweek", "");
    	if(!StringUtil.isEmpty(wweek)){
    		hql+=" and wweek='"+wweek+"'";
    	}
    	String currentStep=crm.getString("currentStep", "");
    	if(!StringUtil.isEmpty(currentStep)){
    		hql+=" and currentStep='"+currentStep+"'";
    	}
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by id ";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
