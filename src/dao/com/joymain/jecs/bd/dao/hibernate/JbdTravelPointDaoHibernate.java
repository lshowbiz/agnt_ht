
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdTravelPoint;
import com.joymain.jecs.bd.dao.JbdTravelPointDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdTravelPointDaoHibernate extends BaseDaoHibernate implements JbdTravelPointDao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointDao#getJbdTravelPoints(com.joymain.jecs.bd.model.JbdTravelPoint)
     */
    public List getJbdTravelPoints(final JbdTravelPoint jbdTravelPoint) {
        return getHibernateTemplate().find("from JbdTravelPoint");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdTravelPoint == null) {
            return getHibernateTemplate().find("from JbdTravelPoint");
        } else {
            // filter on properties set in the jbdTravelPoint
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdTravelPoint).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdTravelPoint.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointDao#getJbdTravelPoint(String userCode)
     */
    public JbdTravelPoint getJbdTravelPoint(final String userCode) {
        JbdTravelPoint jbdTravelPoint = (JbdTravelPoint) getHibernateTemplate().get(JbdTravelPoint.class, userCode);
//        if (jbdTravelPoint == null) {
//            log.warn("uh oh, jbdTravelPoint with userCode '" + userCode + "' not found...");
//            throw new ObjectRetrievalFailureException(JbdTravelPoint.class, userCode);
//        }

        return jbdTravelPoint;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointDao#saveJbdTravelPoint(JbdTravelPoint jbdTravelPoint)
     */    
    public void saveJbdTravelPoint(final JbdTravelPoint jbdTravelPoint) {
        getHibernateTemplate().saveOrUpdate(jbdTravelPoint);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointDao#removeJbdTravelPoint(String userCode)
     */
    public void removeJbdTravelPoint(final String userCode) {
        getHibernateTemplate().delete(getJbdTravelPoint(userCode));
    }
    //added for getJbdTravelPointsByCrm
    public List getJbdTravelPointsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdTravelPoint jbdTravelPoint where 1=1";

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

	public List getRecommendVip(String userCode, String startTime, String endTime) {
		return this.findObjectsBySQL("select r.user_code, sum(o.pv_amt) from jmi_recommend_ref r, jpo_member_order o where r.recommend_no = '"+userCode+"' " +
				"and r.user_code = o.user_code and o.order_type in ('1', '2') and o.status = '2' and o.mo_id not in (select f.mo_id from jpr_refund f" +
				" where f.user_code = r.user_code and f.refund_status = '2') and o.check_time >= to_date('"+startTime+" 00:00:00', 'yyyy-mm-dd hh24:mi:ss') " +
				"and o.check_time <= to_date('"+endTime+" 23:59:59', 'yyyy-mm-dd hh24:mi:ss') group by r.user_code having sum(o.pv_amt) >= 2100");
	}
}
