
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdTravelPointLog2014;
import com.joymain.jecs.bd.dao.JbdTravelPointLog2014Dao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdTravelPointLog2014DaoHibernate extends BaseDaoHibernate implements JbdTravelPointLog2014Dao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointLog2014Dao#getJbdTravelPointLog2014s(com.joymain.jecs.bd.model.JbdTravelPointLog2014)
     */
    public List getJbdTravelPointLog2014s(final JbdTravelPointLog2014 jbdTravelPointLog2014) {
        return getHibernateTemplate().find("from JbdTravelPointLog2014");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdTravelPointLog2014 == null) {
            return getHibernateTemplate().find("from JbdTravelPointLog2014");
        } else {
            // filter on properties set in the jbdTravelPointLog2014
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdTravelPointLog2014).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdTravelPointLog2014.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointLog2014Dao#getJbdTravelPointLog2014(Long logId)
     */
    public JbdTravelPointLog2014 getJbdTravelPointLog2014(final Long logId) {
        JbdTravelPointLog2014 jbdTravelPointLog2014 = (JbdTravelPointLog2014) getHibernateTemplate().get(JbdTravelPointLog2014.class, logId);
/*        if (jbdTravelPointLog2014 == null) {
            log.warn("uh oh, jbdTravelPointLog2014 with logId '" + logId + "' not found...");
            throw new ObjectRetrievalFailureException(JbdTravelPointLog2014.class, logId);
        }*/

        return jbdTravelPointLog2014;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointLog2014Dao#saveJbdTravelPointLog2014(JbdTravelPointLog2014 jbdTravelPointLog2014)
     */    
    public void saveJbdTravelPointLog2014(final JbdTravelPointLog2014 jbdTravelPointLog2014) {
        getHibernateTemplate().saveOrUpdate(jbdTravelPointLog2014);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointLog2014Dao#removeJbdTravelPointLog2014(Long logId)
     */
    public void removeJbdTravelPointLog2014(final Long logId) {
        getHibernateTemplate().delete(getJbdTravelPointLog2014(logId));
    }
    //added for getJbdTravelPointLog2014sByCrm
    public List getJbdTravelPointLog2014sByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdTravelPointLog2014 jbdTravelPointLog2014 where 1=1";

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
		return this.findObjectsByHqlQuery("from JbdTravelPointLog2014 where userCode='"+userCode+"' and passStar >='"+passStar+"'");
	}
}
