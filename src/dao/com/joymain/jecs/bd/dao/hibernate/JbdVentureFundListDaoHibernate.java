
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdVentureFundList;
import com.joymain.jecs.bd.dao.JbdVentureFundListDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdVentureFundListDaoHibernate extends BaseDaoHibernate implements JbdVentureFundListDao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdVentureFundListDao#getJbdVentureFundLists(com.joymain.jecs.bd.model.JbdVentureFundList)
     */
    public List getJbdVentureFundLists(final JbdVentureFundList jbdVentureFundList) {
        return getHibernateTemplate().find("from JbdVentureFundList");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdVentureFundList == null) {
            return getHibernateTemplate().find("from JbdVentureFundList");
        } else {
            // filter on properties set in the jbdVentureFundList
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdVentureFundList).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdVentureFundList.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdVentureFundListDao#getJbdVentureFundList(BigDecimal id)
     */
    public JbdVentureFundList getJbdVentureFundList(final Long id) {
        JbdVentureFundList jbdVentureFundList = (JbdVentureFundList) getHibernateTemplate().get(JbdVentureFundList.class, id);
        if (jbdVentureFundList == null) {
            log.warn("uh oh, jbdVentureFundList with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JbdVentureFundList.class, id);
        }

        return jbdVentureFundList;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdVentureFundListDao#saveJbdVentureFundList(JbdVentureFundList jbdVentureFundList)
     */    
    public void saveJbdVentureFundList(final JbdVentureFundList jbdVentureFundList) {
        getHibernateTemplate().saveOrUpdate(jbdVentureFundList);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdVentureFundListDao#removeJbdVentureFundList(BigDecimal id)
     */
    public void removeJbdVentureFundList(final Long id) {
        getHibernateTemplate().delete(getJbdVentureFundList(id));
    }
    //added for getJbdVentureFundListsByCrm
    public List getJbdVentureFundListsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdVentureFundList jbdVentureFundList where 1=1";
    	
		String userCode = crm.getString("userCode", "");
		if(!StringUtil.isEmpty(userCode)){
			hql += " and userCode='"+userCode+"'";
		}
		String calcStatus = crm.getString("calcStatus", "");
		if(!StringUtil.isEmpty(calcStatus)){
			hql += " and calcStatus="+calcStatus;
		}

		String status = crm.getString("status", "");
		if(!StringUtil.isEmpty(status)){
			hql += " and status="+status;
		}
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
