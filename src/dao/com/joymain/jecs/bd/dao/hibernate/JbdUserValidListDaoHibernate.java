
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdUserValidList;
import com.joymain.jecs.bd.dao.JbdUserValidListDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdUserValidListDaoHibernate extends BaseDaoHibernate implements JbdUserValidListDao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdUserValidListDao#getJbdUserValidLists(com.joymain.jecs.bd.model.JbdUserValidList)
     */
    public List getJbdUserValidLists(final JbdUserValidList jbdUserValidList) {
        return getHibernateTemplate().find("from JbdUserValidList");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdUserValidList == null) {
            return getHibernateTemplate().find("from JbdUserValidList");
        } else {
            // filter on properties set in the jbdUserValidList
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdUserValidList).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdUserValidList.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdUserValidListDao#getJbdUserValidList(BigDecimal id)
     */
    public JbdUserValidList getJbdUserValidList(final BigDecimal id) {
        JbdUserValidList jbdUserValidList = (JbdUserValidList) getHibernateTemplate().get(JbdUserValidList.class, id);
        if (jbdUserValidList == null) {
            log.warn("uh oh, jbdUserValidList with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JbdUserValidList.class, id);
        }

        return jbdUserValidList;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdUserValidListDao#saveJbdUserValidList(JbdUserValidList jbdUserValidList)
     */    
    public void saveJbdUserValidList(final JbdUserValidList jbdUserValidList) {
        getHibernateTemplate().saveOrUpdate(jbdUserValidList);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdUserValidListDao#removeJbdUserValidList(BigDecimal id)
     */
    public void removeJbdUserValidList(final BigDecimal id) {
        getHibernateTemplate().delete(getJbdUserValidList(id));
    }
    //added for getJbdUserValidListsByCrm
    public List getJbdUserValidListsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdUserValidList jbdUserValidList where 1=1";
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
