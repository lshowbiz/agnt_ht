
package com.joymain.jecs.sys.dao.hibernate;

import java.util.List;
import java.io.Serializable;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.sys.model.UpsInteractiveLog;
import com.joymain.jecs.sys.dao.UpsInteractiveLogDao;
import com.joymain.jecs.util.data.AbstractDAO;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class UpsInteractiveLogDaoHibernate extends AbstractDAO implements UpsInteractiveLogDao {

    /**
     * @see com.joymain.jecs.sys.dao.UpsInteractiveLogDao#getUpsInteractiveLogs(com.joymain.jecs.sys.model.UpsInteractiveLog)
     */
    public List getUpsInteractiveLogs(final UpsInteractiveLog upsInteractiveLog) {
        return getHibernateTemplate().find("from UpsInteractiveLog");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (upsInteractiveLog == null) {
            return getHibernateTemplate().find("from UpsInteractiveLog");
        } else {
            // filter on properties set in the upsInteractiveLog
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(upsInteractiveLog).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(UpsInteractiveLog.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.sys.dao.UpsInteractiveLogDao#getUpsInteractiveLog(Long uniId)
     */
    public UpsInteractiveLog getUpsInteractiveLog(final Long uniId) {
        UpsInteractiveLog upsInteractiveLog = (UpsInteractiveLog) getHibernateTemplate().get(UpsInteractiveLog.class, uniId);
        if (upsInteractiveLog == null) {
//            log.warn("uh oh, upsInteractiveLog with uniId '" + uniId + "' not found...");
            throw new ObjectRetrievalFailureException(UpsInteractiveLog.class, uniId);
        }

        return upsInteractiveLog;
    }

    /**
     * @see com.joymain.jecs.sys.dao.UpsInteractiveLogDao#saveUpsInteractiveLog(UpsInteractiveLog upsInteractiveLog)
     */    
    public void saveUpsInteractiveLog(final UpsInteractiveLog upsInteractiveLog) {
        getHibernateTemplate().saveOrUpdate(upsInteractiveLog);
    }

    /**
     * @see com.joymain.jecs.sys.dao.UpsInteractiveLogDao#removeUpsInteractiveLog(Long uniId)
     */
    public void removeUpsInteractiveLog(final Long uniId) {
        getHibernateTemplate().delete(getUpsInteractiveLog(uniId));
    }
    //added for getUpsInteractiveLogsByCrm
    public List getUpsInteractiveLogsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from UpsInteractiveLog upsInteractiveLog where 1=1";
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
			hql += " order by uniId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

    public void saveObject(Object o) {
        getHibernateTemplate().saveOrUpdate(o);
    }

    /**
     * @see com.joymain.jecs.dao.Dao#getObject(java.lang.Class, java.io.Serializable)
     */
    public Object getObject(Class clazz, Serializable id) {
        Object o = getHibernateTemplate().get(clazz, id);

        if (o == null) {
            throw new ObjectRetrievalFailureException(clazz, id);
        }

        return o;
    }

    /**
     * @see com.joymain.jecs.dao.Dao#getObjects(java.lang.Class)
     */
    public List getObjects(Class clazz) {
        return getHibernateTemplate().loadAll(clazz);
    }

    /**
     * @see com.joymain.jecs.dao.Dao#removeObject(java.lang.Class, java.io.Serializable)
     */
    public void removeObject(Class clazz, Serializable id) {
        getHibernateTemplate().delete(getObject(clazz, id));
    }


}
