
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.JfiSunOrderList;
import com.joymain.jecs.fi.dao.JfiSunOrderListDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JfiSunOrderListDaoHibernate extends BaseDaoHibernate implements JfiSunOrderListDao {

    /**
     * @see com.joymain.jecs.fi.dao.JfiSunOrderListDao#getJfiSunOrderLists(com.joymain.jecs.fi.model.JfiSunOrderList)
     */
    public List getJfiSunOrderLists(final JfiSunOrderList jfiSunOrderList) {
        return getHibernateTemplate().find("from JfiSunOrderList");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jfiSunOrderList == null) {
            return getHibernateTemplate().find("from JfiSunOrderList");
        } else {
            // filter on properties set in the jfiSunOrderList
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jfiSunOrderList).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JfiSunOrderList.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiSunOrderListDao#getJfiSunOrderList(Long molId)
     */
    public JfiSunOrderList getJfiSunOrderList(final Long molId) {
        JfiSunOrderList jfiSunOrderList = (JfiSunOrderList) getHibernateTemplate().get(JfiSunOrderList.class, molId);
        if (jfiSunOrderList == null) {
            log.warn("uh oh, jfiSunOrderList with molId '" + molId + "' not found...");
            throw new ObjectRetrievalFailureException(JfiSunOrderList.class, molId);
        }

        return jfiSunOrderList;
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiSunOrderListDao#saveJfiSunOrderList(JfiSunOrderList jfiSunOrderList)
     */    
    public void saveJfiSunOrderList(final JfiSunOrderList jfiSunOrderList) {
        getHibernateTemplate().saveOrUpdate(jfiSunOrderList);
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiSunOrderListDao#removeJfiSunOrderList(Long molId)
     */
    public void removeJfiSunOrderList(final Long molId) {
        getHibernateTemplate().delete(getJfiSunOrderList(molId));
    }
    //added for getJfiSunOrderListsByCrm
    public List getJfiSunOrderListsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JfiSunOrderList jfiSunOrderList where 1=1";
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
