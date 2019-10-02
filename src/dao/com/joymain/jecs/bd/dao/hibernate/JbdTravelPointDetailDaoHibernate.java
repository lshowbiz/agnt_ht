
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdTravelPointDetail;
import com.joymain.jecs.bd.dao.JbdTravelPointDetailDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdTravelPointDetailDaoHibernate extends BaseDaoHibernate implements JbdTravelPointDetailDao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointDetailDao#getJbdTravelPointDetails(com.joymain.jecs.bd.model.JbdTravelPointDetail)
     */
    public List getJbdTravelPointDetails(final JbdTravelPointDetail jbdTravelPointDetail) {
        return getHibernateTemplate().find("from JbdTravelPointDetail");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdTravelPointDetail == null) {
            return getHibernateTemplate().find("from JbdTravelPointDetail");
        } else {
            // filter on properties set in the jbdTravelPointDetail
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdTravelPointDetail).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdTravelPointDetail.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointDetailDao#getJbdTravelPointDetail(BigDecimal id)
     */
    public JbdTravelPointDetail getJbdTravelPointDetail(final Long id) {
        JbdTravelPointDetail jbdTravelPointDetail = (JbdTravelPointDetail) getHibernateTemplate().get(JbdTravelPointDetail.class, id);
//        if (jbdTravelPointDetail == null) {
//            log.warn("uh oh, jbdTravelPointDetail with id '" + id + "' not found...");
//            throw new ObjectRetrievalFailureException(JbdTravelPointDetail.class, id);
//        }

        return jbdTravelPointDetail;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointDetailDao#saveJbdTravelPointDetail(JbdTravelPointDetail jbdTravelPointDetail)
     */    
    public void saveJbdTravelPointDetail(final JbdTravelPointDetail jbdTravelPointDetail) {
        getHibernateTemplate().saveOrUpdate(jbdTravelPointDetail);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointDetailDao#removeJbdTravelPointDetail(BigDecimal id)
     */
    public void removeJbdTravelPointDetail(final Long id) {
        getHibernateTemplate().delete(getJbdTravelPointDetail(id));
    }
    //added for getJbdTravelPointDetailsByCrm
    public List getJbdTravelPointDetailsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdTravelPointDetail jbdTravelPointDetail where 1=1";
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
