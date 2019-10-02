
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdTravelPointLog2012;
import com.joymain.jecs.bd.dao.JbdTravelPointLog2012Dao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdTravelPointLog2012DaoHibernate extends BaseDaoHibernate implements JbdTravelPointLog2012Dao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointLog2012Dao#getJbdTravelPointLog2012s(com.joymain.jecs.bd.model.JbdTravelPointLog2012)
     */
    public List getJbdTravelPointLog2012s(final JbdTravelPointLog2012 jbdTravelPointLog2012) {
        return getHibernateTemplate().find("from JbdTravelPointLog2012");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdTravelPointLog2012 == null) {
            return getHibernateTemplate().find("from JbdTravelPointLog2012");
        } else {
            // filter on properties set in the jbdTravelPointLog2012
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdTravelPointLog2012).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdTravelPointLog2012.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointLog2012Dao#getJbdTravelPointLog2012(Long logId)
     */
    public JbdTravelPointLog2012 getJbdTravelPointLog2012(final Long logId) {
        JbdTravelPointLog2012 jbdTravelPointLog2012 = (JbdTravelPointLog2012) getHibernateTemplate().get(JbdTravelPointLog2012.class, logId);
        if (jbdTravelPointLog2012 == null) {
            log.warn("uh oh, jbdTravelPointLog2012 with logId '" + logId + "' not found...");
            throw new ObjectRetrievalFailureException(JbdTravelPointLog2012.class, logId);
        }

        return jbdTravelPointLog2012;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointLog2012Dao#saveJbdTravelPointLog2012(JbdTravelPointLog2012 jbdTravelPointLog2012)
     */    
    public void saveJbdTravelPointLog2012(final JbdTravelPointLog2012 jbdTravelPointLog2012) {
        getHibernateTemplate().saveOrUpdate(jbdTravelPointLog2012);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointLog2012Dao#removeJbdTravelPointLog2012(Long logId)
     */
    public void removeJbdTravelPointLog2012(final Long logId) {
        getHibernateTemplate().delete(getJbdTravelPointLog2012(logId));
    }
    //added for getJbdTravelPointLog2012sByCrm
    public List getJbdTravelPointLog2012sByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdTravelPointLog2012 jbdTravelPointLog2012 where 1=1";
    	
		String userCode = crm.getString("userCode", "");
		if(!StringUtil.isEmpty(userCode)){
			hql += " and userCode='"+userCode+"'";
		}
		String dealType = crm.getString("dealType", "");
		if(!StringUtil.isEmpty(dealType)){
			hql += " and dealType='"+dealType+"'";
		}
		String startTime = crm.getString("startTime", "");
		if(!StringUtil.isEmpty(startTime)){
			hql += " and createTime >= to_date('" + startTime
					+ " 00:00:00','yyyy-MM-dd hh24:mi:ss')";
		}

		String endTime = crm.getString("endTime", "");
		if(!StringUtil.isEmpty(endTime)){
			hql += " and createTime <= to_date('" + endTime
			+ " 23:59:59','yyyy-MM-dd hh24:mi:ss')";
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
}
