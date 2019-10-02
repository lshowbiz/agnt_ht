
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdCalcDayKbList;
import com.joymain.jecs.bd.dao.JbdCalcDayKbListDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdCalcDayKbListDaoHibernate extends BaseDaoHibernate implements JbdCalcDayKbListDao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdCalcDayKbListDao#getJbdCalcDayKbLists(com.joymain.jecs.bd.model.JbdCalcDayKbList)
     */
    public List getJbdCalcDayKbLists(final JbdCalcDayKbList jbdCalcDayKbList) {
        return getHibernateTemplate().find("from JbdCalcDayKbList");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdCalcDayKbList == null) {
            return getHibernateTemplate().find("from JbdCalcDayKbList");
        } else {
            // filter on properties set in the jbdCalcDayKbList
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdCalcDayKbList).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdCalcDayKbList.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdCalcDayKbListDao#getJbdCalcDayKbList(BigDecimal id)
     */
    public JbdCalcDayKbList getJbdCalcDayKbList(final Long id) {
        JbdCalcDayKbList jbdCalcDayKbList = (JbdCalcDayKbList) getHibernateTemplate().get(JbdCalcDayKbList.class, id);


        return jbdCalcDayKbList;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdCalcDayKbListDao#saveJbdCalcDayKbList(JbdCalcDayKbList jbdCalcDayKbList)
     */    
    public void saveJbdCalcDayKbList(final JbdCalcDayKbList jbdCalcDayKbList) {
        getHibernateTemplate().saveOrUpdate(jbdCalcDayKbList);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdCalcDayKbListDao#removeJbdCalcDayKbList(BigDecimal id)
     */
    public void removeJbdCalcDayKbList(final Long id) {
        getHibernateTemplate().delete(getJbdCalcDayKbList(id));
    }
    //added for getJbdCalcDayKbListsByCrm
    public List getJbdCalcDayKbListsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdCalcDayKbList jbdCalcDayKbList where 1=1";
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
