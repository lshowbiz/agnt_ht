
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.JfiReapalLog;
import com.joymain.jecs.fi.dao.JfiReapalLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JfiReapalLogDaoHibernate extends BaseDaoHibernate implements JfiReapalLogDao {

    /**
     * @see com.joymain.jecs.fi.dao.JfiReapalLogDao#getJfiReapalLogs(com.joymain.jecs.fi.model.JfiReapalLog)
     */
    public List getJfiReapalLogs(final JfiReapalLog jfiReapalLog) {
        return getHibernateTemplate().find("from JfiReapalLog");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jfiReapalLog == null) {
            return getHibernateTemplate().find("from JfiReapalLog");
        } else {
            // filter on properties set in the jfiReapalLog
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jfiReapalLog).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JfiReapalLog.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiReapalLogDao#getJfiReapalLog(Long logId)
     */
    public JfiReapalLog getJfiReapalLog(final Long logId) {
        JfiReapalLog jfiReapalLog = (JfiReapalLog) getHibernateTemplate().get(JfiReapalLog.class, logId);
        if (jfiReapalLog == null) {
            log.warn("uh oh, jfiReapalLog with logId '" + logId + "' not found...");
            throw new ObjectRetrievalFailureException(JfiReapalLog.class, logId);
        }

        return jfiReapalLog;
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiReapalLogDao#saveJfiReapalLog(JfiReapalLog jfiReapalLog)
     */    
    public void saveJfiReapalLog(final JfiReapalLog jfiReapalLog) {
        getHibernateTemplate().saveOrUpdate(jfiReapalLog);
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiReapalLogDao#removeJfiReapalLog(Long logId)
     */
    public void removeJfiReapalLog(final Long logId) {
        getHibernateTemplate().delete(getJfiReapalLog(logId));
    }
    //added for getJfiReapalLogsByCrm
    public List getJfiReapalLogsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JfiReapalLog jfiReapalLog where 1=1";
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
    
    /**
     * Modify By WuCF 20150923 判断流水是否重复
     * @param dealId
     * @return
     */
    @Override
	public List getJfiReapalLogsByDealId(String dealId) {
		
		String hql="from JfiReapalLog t where t.detailId='"+ dealId +"' and t.inc='1'";
		
		return this.findObjectsByHqlQuery(hql);
	}
}
