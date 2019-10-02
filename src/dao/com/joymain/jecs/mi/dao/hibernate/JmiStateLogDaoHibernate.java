
package com.joymain.jecs.mi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.mi.model.JmiStateLog;
import com.joymain.jecs.mi.dao.JmiStateLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.springframework.orm.ObjectRetrievalFailureException;

public class JmiStateLogDaoHibernate extends BaseDaoHibernate implements JmiStateLogDao {

    /**
     * @see com.joymain.jecs.mi.dao.JmiStateLogDao#getJmiStateLogs(com.joymain.jecs.mi.model.JmiStateLog)
     */
    public List getJmiStateLogs(final JmiStateLog jmiStateLog) {
        return getHibernateTemplate().find("from JmiStateLog");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jmiStateLog == null) {
            return getHibernateTemplate().find("from JmiStateLog");
        } else {
            // filter on properties set in the jmiStateLog
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jmiStateLog).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JmiStateLog.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiStateLogDao#getJmiStateLog(BigDecimal id)
     */
    public JmiStateLog getJmiStateLog(final BigDecimal id) {
        JmiStateLog jmiStateLog = (JmiStateLog) getHibernateTemplate().get(JmiStateLog.class, id);
        if (jmiStateLog == null) {
            log.warn("uh oh, jmiStateLog with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JmiStateLog.class, id);
        }

        return jmiStateLog;
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiStateLogDao#saveJmiStateLog(JmiStateLog jmiStateLog)
     */    
    public void saveJmiStateLog(final JmiStateLog jmiStateLog) {
        getHibernateTemplate().saveOrUpdate(jmiStateLog);
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiStateLogDao#removeJmiStateLog(BigDecimal id)
     */
    public void removeJmiStateLog(final BigDecimal id) {
        getHibernateTemplate().delete(getJmiStateLog(id));
    }
    //added for getJmiStateLogsByCrm
    public List getJmiStateLogsByCrm(CommonRecord crm, Pager pager){
    	
	   String netType = crm.getString("netType", "");
	   String treeIndex = crm.getString("treeIndex", "");
       if("link_no".equals(netType)) {
    	   netType="jmi_link_ref";
       } else if("recommend_no".equals(netType)) {
    	   netType="jmi_recommend_ref";
       }
    	String hql = "select b.* from "+netType+" a,jmi_state_log b  where a.tree_index like '"+treeIndex+"%' and a.user_code=b.user_code";

    	
    	
    	
    	// 

		return this.findObjectsBySQL(hql, pager);
    }
}
