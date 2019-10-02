
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdTravelPoint2013;
import com.joymain.jecs.bd.model.JbdTravelPoint2014;
import com.joymain.jecs.bd.dao.JbdTravelPoint2014Dao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdTravelPoint2014DaoHibernate extends BaseDaoHibernate implements JbdTravelPoint2014Dao {

	 /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPoint2014Dao#getJbdTravelPoint2014s(com.joymain.jecs.bd.model.JbdTravelPoint2014)
     */
    public List getJbdTravelPoint2014s(final JbdTravelPoint2014 jbdTravelPoint2014) {
        return getHibernateTemplate().find("from JbdTravelPoint2014");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdTravelPoint2014 == null) {
            return getHibernateTemplate().find("from JbdTravelPoint2014");
        } else {
            // filter on properties set in the jbdTravelPoint2014
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdTravelPoint2014).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdTravelPoint2014.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPoint2014Dao#getJbdTravelPoint2014(String userCode)
     */
    public JbdTravelPoint2014 getJbdTravelPoint2014(final String userCode) {
        JbdTravelPoint2014 jbdTravelPoint2014 = (JbdTravelPoint2014) getHibernateTemplate().get(JbdTravelPoint2014.class, userCode);
//        if (jbdTravelPoint2014 == null) {
//            log.warn("uh oh, jbdTravelPoint2014 with userCode '" + userCode + "' not found...");
//            throw new ObjectRetrievalFailureException(JbdTravelPoint2014.class, userCode);
//        }

        return jbdTravelPoint2014;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPoint2014Dao#saveJbdTravelPoint2014(JbdTravelPoint2014 jbdTravelPoint2014)
     */    
    public void saveJbdTravelPoint2014(final JbdTravelPoint2014 jbdTravelPoint2014) {
        getHibernateTemplate().saveOrUpdate(jbdTravelPoint2014);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPoint2014Dao#removeJbdTravelPoint2014(String userCode)
     */
    public void removeJbdTravelPoint2014(final String userCode) {
        getHibernateTemplate().delete(getJbdTravelPoint2014(userCode));
    }
    //added for getJbdTravelPoint2014sByCrm
    public List getJbdTravelPoint2014sByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdTravelPoint2014 jbdTravelPoint2014 where 1=1";
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

	public List getJbdTravelPoint2014sByUserCode(String userCode) {
		return this.findObjectsBySQL("select concat(w_year, Lpad(w_month, 2, 0)) as w_week,pass_star from jbd_member_link_calc_hist where w_week " +
				"in (select concat(t.w_year, Lpad(t.w_week, 2, 0)) from jbd_period t where t.f_year=2014 and t.month_status='1') and user_code='"+userCode+"' order by w_week");
	}
}
