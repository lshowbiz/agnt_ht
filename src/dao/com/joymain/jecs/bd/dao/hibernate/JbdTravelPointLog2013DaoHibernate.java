
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdTravelPointLog2013;
import com.joymain.jecs.bd.dao.JbdTravelPointLog2013Dao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdTravelPointLog2013DaoHibernate extends BaseDaoHibernate implements JbdTravelPointLog2013Dao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointLog2013Dao#getJbdTravelPointLog2013s(com.joymain.jecs.bd.model.JbdTravelPointLog2013)
     */
    public List getJbdTravelPointLog2013s(final JbdTravelPointLog2013 jbdTravelPointLog2013) {
        return getHibernateTemplate().find("from JbdTravelPointLog2013");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdTravelPointLog2013 == null) {
            return getHibernateTemplate().find("from JbdTravelPointLog2013");
        } else {
            // filter on properties set in the jbdTravelPointLog2013
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdTravelPointLog2013).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdTravelPointLog2013.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointLog2013Dao#getJbdTravelPointLog2013(Long logId)
     */
    public JbdTravelPointLog2013 getJbdTravelPointLog2013(final Long logId) {
        JbdTravelPointLog2013 jbdTravelPointLog2013 = (JbdTravelPointLog2013) getHibernateTemplate().get(JbdTravelPointLog2013.class, logId);
        if (jbdTravelPointLog2013 == null) {
            log.warn("uh oh, jbdTravelPointLog2013 with logId '" + logId + "' not found...");
            throw new ObjectRetrievalFailureException(JbdTravelPointLog2013.class, logId);
        }

        return jbdTravelPointLog2013;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointLog2013Dao#saveJbdTravelPointLog2013(JbdTravelPointLog2013 jbdTravelPointLog2013)
     */    
    public void saveJbdTravelPointLog2013(final JbdTravelPointLog2013 jbdTravelPointLog2013) {
        getHibernateTemplate().saveOrUpdate(jbdTravelPointLog2013);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointLog2013Dao#removeJbdTravelPointLog2013(Long logId)
     */
    public void removeJbdTravelPointLog2013(final Long logId) {
        getHibernateTemplate().delete(getJbdTravelPointLog2013(logId));
    }
    //added for getJbdTravelPointLog2013sByCrm
    public List getJbdTravelPointLog2013sByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdTravelPointLog2013 jbdTravelPointLog2013 where 1=1";

		String userCode = crm.getString("userCode", "");
		if(!StringUtil.isEmpty(userCode)){
			hql += " and userCode='"+userCode+"'";
		}
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by logId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	public List getJbdTravelPointLogByPassStar(String userCode, String passStar) {
		return this.findObjectsByHqlQuery("from JbdTravelPointLog2013 where userCode='"+userCode+"' and passStar >='"+passStar+"'");
	}
}
