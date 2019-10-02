
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdUserCardList;
import com.joymain.jecs.bd.dao.JbdUserCardListDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdUserCardListDaoHibernate extends BaseDaoHibernate implements JbdUserCardListDao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdUserCardListDao#getJbdUserCardLists(com.joymain.jecs.bd.model.JbdUserCardList)
     */
    public List getJbdUserCardLists(final JbdUserCardList jbdUserCardList) {
        return getHibernateTemplate().find("from JbdUserCardList");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdUserCardList == null) {
            return getHibernateTemplate().find("from JbdUserCardList");
        } else {
            // filter on properties set in the jbdUserCardList
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdUserCardList).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdUserCardList.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdUserCardListDao#getJbdUserCardList(BigDecimal id)
     */
    public JbdUserCardList getJbdUserCardList(final Long id) {
        JbdUserCardList jbdUserCardList = (JbdUserCardList) getHibernateTemplate().get(JbdUserCardList.class, id);
        if (jbdUserCardList == null) {
            log.warn("uh oh, jbdUserCardList with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JbdUserCardList.class, id);
        }

        return jbdUserCardList;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdUserCardListDao#saveJbdUserCardList(JbdUserCardList jbdUserCardList)
     */    
    public void saveJbdUserCardList(final JbdUserCardList jbdUserCardList) {
        getHibernateTemplate().saveOrUpdate(jbdUserCardList);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdUserCardListDao#removeJbdUserCardList(BigDecimal id)
     */
    public void removeJbdUserCardList(final Long id) {
        getHibernateTemplate().delete(getJbdUserCardList(id));
    }
    //added for getJbdUserCardListsByCrm
    public List getJbdUserCardListsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdUserCardList jbdUserCardList where 1=1";
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

	public JbdUserCardList getJbdUserCardListByUserCodeAndWeek(String userCode, Integer wweek) {
		return (JbdUserCardList) this.getObjectByHqlQuery("from JbdUserCardList where userCode='"+userCode+"' and wweek="+wweek);
	}

	public JbdUserCardList getPreviousJbdUserCardList(String userCode,Integer wweek) {
		return (JbdUserCardList) this.getObjectByHqlQuery("from JbdUserCardList where userCode='"+userCode+"' and wweek < "+wweek +" order by wweek desc");
	}

	public List getJbdUserCardListByRange(Integer sweek, Integer eweek,String userCode) {
		return this.getHibernateTemplate().find("from JbdUserCardList where userCode='"+userCode+"' and wweek>"+sweek+" and wweek<="+eweek);
	}

	public List getJbdUserCardListByUserCode(String userCode) {
		return this.getHibernateTemplate().find("from JbdUserCardList where userCode='"+userCode+"' order by wweek ");
	}

	public List getNextJbdUserCardList(String userCode,Integer wweek) {
		return this.getHibernateTemplate().find("from JbdUserCardList where userCode='"+userCode+"' and wweek > "+wweek +" order by wweek asc");
	}
}
