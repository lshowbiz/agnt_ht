
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.JfiSunMemberOrderList;
import com.joymain.jecs.fi.dao.JfiSunMemberOrderListDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JfiSunMemberOrderListDaoHibernate extends BaseDaoHibernate implements JfiSunMemberOrderListDao {

    /**
     * @see com.joymain.jecs.fi.dao.JfiSunMemberOrderListDao#getJfiSunMemberOrderLists(com.joymain.jecs.fi.model.JfiSunMemberOrderList)
     */
    public List getJfiSunMemberOrderLists(final JfiSunMemberOrderList jfiSunMemberOrderList) {
        return getHibernateTemplate().find("from JfiSunMemberOrderList");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jfiSunMemberOrderList == null) {
            return getHibernateTemplate().find("from JfiSunMemberOrderList");
        } else {
            // filter on properties set in the jfiSunMemberOrderList
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jfiSunMemberOrderList).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JfiSunMemberOrderList.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiSunMemberOrderListDao#getJfiSunMemberOrderList(Long molId)
     */
    public JfiSunMemberOrderList getJfiSunMemberOrderList(final Long molId) {
        JfiSunMemberOrderList jfiSunMemberOrderList = (JfiSunMemberOrderList) getHibernateTemplate().get(JfiSunMemberOrderList.class, molId);
        if (jfiSunMemberOrderList == null) {
            log.warn("uh oh, jfiSunMemberOrderList with molId '" + molId + "' not found...");
            throw new ObjectRetrievalFailureException(JfiSunMemberOrderList.class, molId);
        }

        return jfiSunMemberOrderList;
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiSunMemberOrderListDao#saveJfiSunMemberOrderList(JfiSunMemberOrderList jfiSunMemberOrderList)
     */    
    public void saveJfiSunMemberOrderList(final JfiSunMemberOrderList jfiSunMemberOrderList) {
        getHibernateTemplate().saveOrUpdate(jfiSunMemberOrderList);
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiSunMemberOrderListDao#removeJfiSunMemberOrderList(Long molId)
     */
    public void removeJfiSunMemberOrderList(final Long molId) {
        getHibernateTemplate().delete(getJfiSunMemberOrderList(molId));
    }
    //added for getJfiSunMemberOrderListsByCrm
    public List getJfiSunMemberOrderListsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JfiSunMemberOrderList jfiSunMemberOrderList where 1=1";
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
			hql += " order by molId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
