
package com.joymain.jecs.po.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.po.model.JpoMemberNycLog;
import com.joymain.jecs.po.dao.JpoMemberNycLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JpoMemberNycLogDaoHibernate extends BaseDaoHibernate implements JpoMemberNycLogDao {

    /**
     * @see com.joymain.jecs.po.dao.JpoMemberNycLogDao#getJpoMemberNycLogs(com.joymain.jecs.po.model.JpoMemberNycLog)
     */
    public List getJpoMemberNycLogs(final JpoMemberNycLog jpoMemberNycLog) {
        return getHibernateTemplate().find("from JpoMemberNycLog");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jpoMemberNycLog == null) {
            return getHibernateTemplate().find("from JpoMemberNycLog");
        } else {
            // filter on properties set in the jpoMemberNycLog
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jpoMemberNycLog).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JpoMemberNycLog.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.po.dao.JpoMemberNycLogDao#getJpoMemberNycLog(String id)
     */
    public JpoMemberNycLog getJpoMemberNycLog(final String id) {
        JpoMemberNycLog jpoMemberNycLog = (JpoMemberNycLog) getHibernateTemplate().get(JpoMemberNycLog.class, id);
        if (jpoMemberNycLog == null) {
            log.warn("uh oh, jpoMemberNycLog with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JpoMemberNycLog.class, id);
        }

        return jpoMemberNycLog;
    }

    /**
     * @see com.joymain.jecs.po.dao.JpoMemberNycLogDao#saveJpoMemberNycLog(JpoMemberNycLog jpoMemberNycLog)
     */    
    public void saveJpoMemberNycLog(final JpoMemberNycLog jpoMemberNycLog) {
        getHibernateTemplate().saveOrUpdate(jpoMemberNycLog);
    }

    /**
     * @see com.joymain.jecs.po.dao.JpoMemberNycLogDao#removeJpoMemberNycLog(String id)
     */
    public void removeJpoMemberNycLog(final String id) {
        getHibernateTemplate().delete(getJpoMemberNycLog(id));
    }
    //added for getJpoMemberNycLogsByCrm
    public List getJpoMemberNycLogsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JpoMemberNycLog jpoMemberNycLog where 1=1";
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
			hql += " order by id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
