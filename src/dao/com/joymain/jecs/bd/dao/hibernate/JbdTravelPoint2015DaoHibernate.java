
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdTravelPoint2015;
import com.joymain.jecs.bd.dao.JbdTravelPoint2015Dao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdTravelPoint2015DaoHibernate extends BaseDaoHibernate implements JbdTravelPoint2015Dao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPoint2015Dao#getJbdTravelPoint2015s(com.joymain.jecs.bd.model.JbdTravelPoint2015)
     */
    public List getJbdTravelPoint2015s(final JbdTravelPoint2015 jbdTravelPoint2015) {
        return getHibernateTemplate().find("from JbdTravelPoint2015");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdTravelPoint2015 == null) {
            return getHibernateTemplate().find("from JbdTravelPoint2015");
        } else {
            // filter on properties set in the jbdTravelPoint2015
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdTravelPoint2015).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdTravelPoint2015.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPoint2015Dao#getJbdTravelPoint2015(String userCode)
     */
    public JbdTravelPoint2015 getJbdTravelPoint2015(final String userCode) {
        JbdTravelPoint2015 jbdTravelPoint2015 = (JbdTravelPoint2015) getHibernateTemplate().get(JbdTravelPoint2015.class, userCode);
        if (jbdTravelPoint2015 == null) {
            log.warn("uh oh, jbdTravelPoint2015 with userCode '" + userCode + "' not found...");
            throw new ObjectRetrievalFailureException(JbdTravelPoint2015.class, userCode);
        }

        return jbdTravelPoint2015;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPoint2015Dao#saveJbdTravelPoint2015(JbdTravelPoint2015 jbdTravelPoint2015)
     */    
    public void saveJbdTravelPoint2015(final JbdTravelPoint2015 jbdTravelPoint2015) {
        getHibernateTemplate().saveOrUpdate(jbdTravelPoint2015);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPoint2015Dao#removeJbdTravelPoint2015(String userCode)
     */
    public void removeJbdTravelPoint2015(final String userCode) {
        getHibernateTemplate().delete(getJbdTravelPoint2015(userCode));
    }
    //added for getJbdTravelPoint2015sByCrm
    public List getJbdTravelPoint2015sByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdTravelPoint2015 jbdTravelPoint2015 where 1=1";
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
			hql += " order by userCode desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
