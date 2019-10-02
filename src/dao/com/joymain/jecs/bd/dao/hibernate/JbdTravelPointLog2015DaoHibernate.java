
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdTravelPointLog2015;
import com.joymain.jecs.bd.dao.JbdTravelPointLog2015Dao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdTravelPointLog2015DaoHibernate extends BaseDaoHibernate implements JbdTravelPointLog2015Dao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointLog2015Dao#getJbdTravelPointLog2015s(com.joymain.jecs.bd.model.JbdTravelPointLog2015)
     */
    public List getJbdTravelPointLog2015s(final JbdTravelPointLog2015 jbdTravelPointLog2015) {
        return getHibernateTemplate().find("from JbdTravelPointLog2015");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdTravelPointLog2015 == null) {
            return getHibernateTemplate().find("from JbdTravelPointLog2015");
        } else {
            // filter on properties set in the jbdTravelPointLog2015
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdTravelPointLog2015).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdTravelPointLog2015.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointLog2015Dao#getJbdTravelPointLog2015(Long logId)
     */
    public JbdTravelPointLog2015 getJbdTravelPointLog2015(final Long logId) {
        JbdTravelPointLog2015 jbdTravelPointLog2015 = (JbdTravelPointLog2015) getHibernateTemplate().get(JbdTravelPointLog2015.class, logId);
        if (jbdTravelPointLog2015 == null) {
            log.warn("uh oh, jbdTravelPointLog2015 with logId '" + logId + "' not found...");
            throw new ObjectRetrievalFailureException(JbdTravelPointLog2015.class, logId);
        }

        return jbdTravelPointLog2015;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointLog2015Dao#saveJbdTravelPointLog2015(JbdTravelPointLog2015 jbdTravelPointLog2015)
     */    
    public void saveJbdTravelPointLog2015(final JbdTravelPointLog2015 jbdTravelPointLog2015) {
        getHibernateTemplate().saveOrUpdate(jbdTravelPointLog2015);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointLog2015Dao#removeJbdTravelPointLog2015(Long logId)
     */
    public void removeJbdTravelPointLog2015(final Long logId) {
        getHibernateTemplate().delete(getJbdTravelPointLog2015(logId));
    }
    //added for getJbdTravelPointLog2015sByCrm
    public List getJbdTravelPointLog2015sByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdTravelPointLog2015 jbdTravelPointLog2015 where 1=1";
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
