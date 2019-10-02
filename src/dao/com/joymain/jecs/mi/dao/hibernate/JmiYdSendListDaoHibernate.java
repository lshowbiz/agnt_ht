
package com.joymain.jecs.mi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.mi.model.JmiYdSendList;
import com.joymain.jecs.mi.dao.JmiYdSendListDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JmiYdSendListDaoHibernate extends BaseDaoHibernate implements JmiYdSendListDao {

    /**
     * @see com.joymain.jecs.mi.dao.JmiYdSendListDao#getJmiYdSendLists(com.joymain.jecs.mi.model.JmiYdSendList)
     */
    public List getJmiYdSendLists(final JmiYdSendList jmiYdSendList) {
        return getHibernateTemplate().find("from JmiYdSendList");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jmiYdSendList == null) {
            return getHibernateTemplate().find("from JmiYdSendList");
        } else {
            // filter on properties set in the jmiYdSendList
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jmiYdSendList).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JmiYdSendList.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiYdSendListDao#getJmiYdSendList(BigDecimal id)
     */
    public JmiYdSendList getJmiYdSendList(final Long id) {
        JmiYdSendList jmiYdSendList = (JmiYdSendList) getHibernateTemplate().get(JmiYdSendList.class, id);
        if (jmiYdSendList == null) {
            log.warn("uh oh, jmiYdSendList with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JmiYdSendList.class, id);
        }

        return jmiYdSendList;
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiYdSendListDao#saveJmiYdSendList(JmiYdSendList jmiYdSendList)
     */    
    public void saveJmiYdSendList(final JmiYdSendList jmiYdSendList) {
        getHibernateTemplate().saveOrUpdate(jmiYdSendList);
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiYdSendListDao#removeJmiYdSendList(BigDecimal id)
     */
    public void removeJmiYdSendList(final Long id) {
        getHibernateTemplate().delete(getJmiYdSendList(id));
    }
    //added for getJmiYdSendListsByCrm
    public List getJmiYdSendListsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JmiYdSendList jmiYdSendList where 1=1";
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
