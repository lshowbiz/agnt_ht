
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdSummaryList;
import com.joymain.jecs.bd.dao.JbdSummaryListDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdSummaryListDaoHibernate extends BaseDaoHibernate implements JbdSummaryListDao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdSummaryListDao#getJbdSummaryLists(com.joymain.jecs.bd.model.JbdSummaryList)
     */
    public List getJbdSummaryLists(final JbdSummaryList jbdSummaryList) {
        return getHibernateTemplate().find("from JbdSummaryList");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdSummaryList == null) {
            return getHibernateTemplate().find("from JbdSummaryList");
        } else {
            // filter on properties set in the jbdSummaryList
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdSummaryList).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdSummaryList.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdSummaryListDao#getJbdSummaryList(BigDecimal id)
     */
    public JbdSummaryList getJbdSummaryList(final BigDecimal id) {
        JbdSummaryList jbdSummaryList = (JbdSummaryList) getHibernateTemplate().get(JbdSummaryList.class, id);
        if (jbdSummaryList == null) {
            log.warn("uh oh, jbdSummaryList with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JbdSummaryList.class, id);
        }

        return jbdSummaryList;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdSummaryListDao#saveJbdSummaryList(JbdSummaryList jbdSummaryList)
     */    
    public void saveJbdSummaryList(final JbdSummaryList jbdSummaryList) {
        getHibernateTemplate().saveOrUpdate(jbdSummaryList);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdSummaryListDao#removeJbdSummaryList(BigDecimal id)
     */
    public void removeJbdSummaryList(final BigDecimal id) {
        getHibernateTemplate().delete(getJbdSummaryList(id));
    }
    //added for getJbdSummaryListsByCrm
    public List getJbdSummaryListsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdSummaryList jbdSummaryList where 1=1";
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
