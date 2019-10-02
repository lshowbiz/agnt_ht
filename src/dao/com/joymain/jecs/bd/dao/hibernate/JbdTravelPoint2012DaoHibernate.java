
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdTravelPoint2012;
import com.joymain.jecs.bd.dao.JbdTravelPoint2012Dao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdTravelPoint2012DaoHibernate extends BaseDaoHibernate implements JbdTravelPoint2012Dao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPoint2012Dao#getJbdTravelPoint2012s(com.joymain.jecs.bd.model.JbdTravelPoint2012)
     */
    public List getJbdTravelPoint2012s(final JbdTravelPoint2012 jbdTravelPoint2012) {
        return getHibernateTemplate().find("from JbdTravelPoint2012");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdTravelPoint2012 == null) {
            return getHibernateTemplate().find("from JbdTravelPoint2012");
        } else {
            // filter on properties set in the jbdTravelPoint2012
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdTravelPoint2012).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdTravelPoint2012.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPoint2012Dao#getJbdTravelPoint2012(String userCode)
     */
    public JbdTravelPoint2012 getJbdTravelPoint2012(final String userCode) {
        JbdTravelPoint2012 jbdTravelPoint2012 = (JbdTravelPoint2012) getHibernateTemplate().get(JbdTravelPoint2012.class, userCode);
//        if (jbdTravelPoint2012 == null) {
//            log.warn("uh oh, jbdTravelPoint2012 with userCode '" + userCode + "' not found...");
//            throw new ObjectRetrievalFailureException(JbdTravelPoint2012.class, userCode);
//        }

        return jbdTravelPoint2012;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPoint2012Dao#saveJbdTravelPoint2012(JbdTravelPoint2012 jbdTravelPoint2012)
     */    
    public void saveJbdTravelPoint2012(final JbdTravelPoint2012 jbdTravelPoint2012) {
        getHibernateTemplate().saveOrUpdate(jbdTravelPoint2012);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPoint2012Dao#removeJbdTravelPoint2012(String userCode)
     */
    public void removeJbdTravelPoint2012(final String userCode) {
        getHibernateTemplate().delete(getJbdTravelPoint2012(userCode));
    }
    //added for getJbdTravelPoint2012sByCrm
    public List getJbdTravelPoint2012sByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdTravelPoint2012 jbdTravelPoint2012 where 1=1";
    	
		String userCode = crm.getString("userCode", "");
		if(!StringUtil.isEmpty(userCode)){
			hql += " and userCode='"+userCode+"'";
		}
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by userCode desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
