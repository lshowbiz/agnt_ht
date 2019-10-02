
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdTravelPointDetail2013;
import com.joymain.jecs.bd.dao.JbdTravelPointDetail2013Dao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdTravelPointDetail2013DaoHibernate extends BaseDaoHibernate implements JbdTravelPointDetail2013Dao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointDetail2013Dao#getJbdTravelPointDetail2013s(com.joymain.jecs.bd.model.JbdTravelPointDetail2013)
     */
    public List getJbdTravelPointDetail2013s(final JbdTravelPointDetail2013 jbdTravelPointDetail2013) {
        return getHibernateTemplate().find("from JbdTravelPointDetail2013");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdTravelPointDetail2013 == null) {
            return getHibernateTemplate().find("from JbdTravelPointDetail2013");
        } else {
            // filter on properties set in the jbdTravelPointDetail2013
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdTravelPointDetail2013).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdTravelPointDetail2013.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointDetail2013Dao#getJbdTravelPointDetail2013(Long id)
     */
    public JbdTravelPointDetail2013 getJbdTravelPointDetail2013(final Long id) {
        JbdTravelPointDetail2013 jbdTravelPointDetail2013 = (JbdTravelPointDetail2013) getHibernateTemplate().get(JbdTravelPointDetail2013.class, id);
        if (jbdTravelPointDetail2013 == null) {
            log.warn("uh oh, jbdTravelPointDetail2013 with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JbdTravelPointDetail2013.class, id);
        }

        return jbdTravelPointDetail2013;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointDetail2013Dao#saveJbdTravelPointDetail2013(JbdTravelPointDetail2013 jbdTravelPointDetail2013)
     */    
    public void saveJbdTravelPointDetail2013(final JbdTravelPointDetail2013 jbdTravelPointDetail2013) {
        getHibernateTemplate().saveOrUpdate(jbdTravelPointDetail2013);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointDetail2013Dao#removeJbdTravelPointDetail2013(Long id)
     */
    public void removeJbdTravelPointDetail2013(final Long id) {
        getHibernateTemplate().delete(getJbdTravelPointDetail2013(id));
    }
    //added for getJbdTravelPointDetail2013sByCrm
    public List getJbdTravelPointDetail2013sByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdTravelPointDetail2013 jbdTravelPointDetail2013 where 1=1";
		String userCode = crm.getString("userCode", "");
		if(!StringUtil.isEmpty(userCode)){
			hql += " and userCode='"+userCode+"'";
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
			hql += " order by id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
