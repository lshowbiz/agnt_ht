
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdTravelPointDetail2014;
import com.joymain.jecs.bd.dao.JbdTravelPointDetail2014Dao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdTravelPointDetail2014DaoHibernate extends BaseDaoHibernate implements JbdTravelPointDetail2014Dao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointDetail2014Dao#getJbdTravelPointDetail2014s(com.joymain.jecs.bd.model.JbdTravelPointDetail2014)
     */
    public List getJbdTravelPointDetail2014s(final JbdTravelPointDetail2014 jbdTravelPointDetail2014) {
        return getHibernateTemplate().find("from JbdTravelPointDetail2014");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdTravelPointDetail2014 == null) {
            return getHibernateTemplate().find("from JbdTravelPointDetail2014");
        } else {
            // filter on properties set in the jbdTravelPointDetail2014
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdTravelPointDetail2014).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdTravelPointDetail2014.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointDetail2014Dao#getJbdTravelPointDetail2014(Long id)
     */
    public JbdTravelPointDetail2014 getJbdTravelPointDetail2014(final Long id) {
        JbdTravelPointDetail2014 jbdTravelPointDetail2014 = (JbdTravelPointDetail2014) getHibernateTemplate().get(JbdTravelPointDetail2014.class, id);
/*        if (jbdTravelPointDetail2014 == null) {
            log.warn("uh oh, jbdTravelPointDetail2014 with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JbdTravelPointDetail2014.class, id);
        }*/

        return jbdTravelPointDetail2014;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointDetail2014Dao#saveJbdTravelPointDetail2014(JbdTravelPointDetail2014 jbdTravelPointDetail2014)
     */    
    public void saveJbdTravelPointDetail2014(final JbdTravelPointDetail2014 jbdTravelPointDetail2014) {
        getHibernateTemplate().saveOrUpdate(jbdTravelPointDetail2014);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointDetail2014Dao#removeJbdTravelPointDetail2014(Long id)
     */
    public void removeJbdTravelPointDetail2014(final Long id) {
        getHibernateTemplate().delete(getJbdTravelPointDetail2014(id));
    }
    //added for getJbdTravelPointDetail2014sByCrm
    public List getJbdTravelPointDetail2014sByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdTravelPointDetail2014 jbdTravelPointDetail2014 where 1=1";
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
