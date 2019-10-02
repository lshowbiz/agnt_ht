
package com.joymain.jecs.am.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.am.model.AmNew;
import com.joymain.jecs.am.dao.AmNewDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class AmNewDaoHibernate extends BaseDaoHibernate implements AmNewDao {

    /**
     * @see com.joymain.jecs.am.dao.AmNewDao#getAmNews(com.joymain.jecs.am.model.AmNew)
     */
    public List getAmNews(final AmNew amNew) {
        return getHibernateTemplate().find("from AmNew");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (amNew == null) {
            return getHibernateTemplate().find("from AmNew");
        } else {
            // filter on properties set in the amNew
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(amNew).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(AmNew.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.am.dao.AmNewDao#getAmNew(String newId)
     */
    public AmNew getAmNew(final String newId) {
        AmNew amNew = (AmNew) getHibernateTemplate().get(AmNew.class, newId);
        if (amNew == null) {
            log.warn("uh oh, amNew with newId '" + newId + "' not found...");
            throw new ObjectRetrievalFailureException(AmNew.class, newId);
        }

        return amNew;
    }

    /**
     * @see com.joymain.jecs.am.dao.AmNewDao#saveAmNew(AmNew amNew)
     */    
    public void saveAmNew(final AmNew amNew) {
        getHibernateTemplate().saveOrUpdate(amNew);
    }

    /**
     * @see com.joymain.jecs.am.dao.AmNewDao#removeAmNew(String newId)
     */
    public void removeAmNew(final String newId) {
        getHibernateTemplate().delete(getAmNew(newId));
    }
    //added for getAmNewsByCrm
    public List getAmNewsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from AmNew amNew where 1=1";
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
			hql += " order by newOrder desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
