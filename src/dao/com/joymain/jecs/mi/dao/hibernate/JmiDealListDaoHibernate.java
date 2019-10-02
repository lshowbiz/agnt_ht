
package com.joymain.jecs.mi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.mi.model.JmiDealList;
import com.joymain.jecs.mi.dao.JmiDealListDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JmiDealListDaoHibernate extends BaseDaoHibernate implements JmiDealListDao {

    /**
     * @see com.joymain.jecs.mi.dao.JmiDealListDao#getJmiDealLists(com.joymain.jecs.mi.model.JmiDealList)
     */
    public List getJmiDealLists(final JmiDealList jmiDealList) {
        return getHibernateTemplate().find("from JmiDealList");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jmiDealList == null) {
            return getHibernateTemplate().find("from JmiDealList");
        } else {
            // filter on properties set in the jmiDealList
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jmiDealList).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JmiDealList.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiDealListDao#getJmiDealList(BigDecimal id)
     */
    public JmiDealList getJmiDealList(final Long id) {
        JmiDealList jmiDealList = (JmiDealList) getHibernateTemplate().get(JmiDealList.class, id);
        if (jmiDealList == null) {
            log.warn("uh oh, jmiDealList with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JmiDealList.class, id);
        }

        return jmiDealList;
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiDealListDao#saveJmiDealList(JmiDealList jmiDealList)
     */    
    public void saveJmiDealList(final JmiDealList jmiDealList) {
        getHibernateTemplate().saveOrUpdate(jmiDealList);
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiDealListDao#removeJmiDealList(BigDecimal id)
     */
    public void removeJmiDealList(final Long id) {
        getHibernateTemplate().delete(getJmiDealList(id));
    }
    //added for getJmiDealListsByCrm
    public List getJmiDealListsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JmiDealList jmiDealList where 1=1";
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
