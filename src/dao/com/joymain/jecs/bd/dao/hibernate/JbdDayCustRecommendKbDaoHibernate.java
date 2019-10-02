
package com.joymain.jecs.bd.dao.hibernate;

import java.math.BigDecimal;
import java.util.List;

import com.joymain.jecs.bd.dao.JbdDayCustRecommendKbDao;
import com.joymain.jecs.bd.model.JbdDayCustRecommendKb;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

public class JbdDayCustRecommendKbDaoHibernate extends BaseDaoHibernate implements JbdDayCustRecommendKbDao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdDayCustRecommendKbDao#getJbdDayCustRecommendKbs(com.joymain.jecs.bd.model.JbdDayCustRecommendKb)
     */
    public List getJbdDayCustRecommendKbs(final JbdDayCustRecommendKb jbdDayCustRecommendKb) {
        return getHibernateTemplate().find("from JbdDayCustRecommendKb");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdDayCustRecommendKb == null) {
            return getHibernateTemplate().find("from JbdDayCustRecommendKb");
        } else {
            // filter on properties set in the jbdDayCustRecommendKb
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdDayCustRecommendKb).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdDayCustRecommendKb.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdDayCustRecommendKbDao#getJbdDayCustRecommendKb(BigDecimal id)
     */
    public JbdDayCustRecommendKb getJbdDayCustRecommendKb(final Long id) {
        JbdDayCustRecommendKb jbdDayCustRecommendKb = (JbdDayCustRecommendKb) getHibernateTemplate().get(JbdDayCustRecommendKb.class, id);
/*        if (jbdDayCustRecommendKb == null) {
            log.warn("uh oh, jbdDayCustRecommendKb with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JbdDayCustRecommendKb.class, id);
        }*/

        return jbdDayCustRecommendKb;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdDayCustRecommendKbDao#saveJbdDayCustRecommendKb(JbdDayCustRecommendKb jbdDayCustRecommendKb)
     */    
    public void saveJbdDayCustRecommendKb(final JbdDayCustRecommendKb jbdDayCustRecommendKb) {
        getHibernateTemplate().saveOrUpdate(jbdDayCustRecommendKb);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdDayCustRecommendKbDao#removeJbdDayCustRecommendKb(BigDecimal id)
     */
    public void removeJbdDayCustRecommendKb(final Long id) {
        getHibernateTemplate().delete(getJbdDayCustRecommendKb(id));
    }
    //added for getJbdDayCustRecommendKbsByCrm
    public List getJbdDayCustRecommendKbsByCrm(CommonRecord crm, Pager pager){
    	String hql = "select * from jbd_day_cust_recommend_kb where 1=1";

    	
    	String userCode=crm.getString("userCode", "");
    	if(!StringUtil.isEmpty(userCode)){
    		hql+=" and user_code='"+userCode+"'";
    	}

    	String startWeek=crm.getString("startwweek", "");
    	if(!StringUtil.isEmpty(startWeek)){
    		hql+=" and w_week>="+startWeek;
    	}
    	String endWeek=crm.getString("endwweek", "");
    	if(!StringUtil.isEmpty(endWeek)){
    		hql+=" and w_week<="+endWeek;
    	}
    	    	
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsBySQL(hql, pager);
    }
}
