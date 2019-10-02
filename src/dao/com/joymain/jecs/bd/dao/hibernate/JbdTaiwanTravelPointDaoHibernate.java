
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdTaiwanTravelPoint;
import com.joymain.jecs.bd.dao.JbdTaiwanTravelPointDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdTaiwanTravelPointDaoHibernate extends BaseDaoHibernate implements JbdTaiwanTravelPointDao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdTaiwanTravelPointDao#getJbdTaiwanTravelPoints(com.joymain.jecs.bd.model.JbdTaiwanTravelPoint)
     */
    public List getJbdTaiwanTravelPoints(final JbdTaiwanTravelPoint jbdTaiwanTravelPoint) {
        return getHibernateTemplate().find("from JbdTaiwanTravelPoint");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdTaiwanTravelPoint == null) {
            return getHibernateTemplate().find("from JbdTaiwanTravelPoint");
        } else {
            // filter on properties set in the jbdTaiwanTravelPoint
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdTaiwanTravelPoint).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdTaiwanTravelPoint.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTaiwanTravelPointDao#getJbdTaiwanTravelPoint(BigDecimal id)
     */
    public JbdTaiwanTravelPoint getJbdTaiwanTravelPoint(final BigDecimal id) {
        JbdTaiwanTravelPoint jbdTaiwanTravelPoint = (JbdTaiwanTravelPoint) getHibernateTemplate().get(JbdTaiwanTravelPoint.class, id);
        if (jbdTaiwanTravelPoint == null) {
            log.warn("uh oh, jbdTaiwanTravelPoint with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JbdTaiwanTravelPoint.class, id);
        }

        return jbdTaiwanTravelPoint;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTaiwanTravelPointDao#saveJbdTaiwanTravelPoint(JbdTaiwanTravelPoint jbdTaiwanTravelPoint)
     */    
    public void saveJbdTaiwanTravelPoint(final JbdTaiwanTravelPoint jbdTaiwanTravelPoint) {
        getHibernateTemplate().saveOrUpdate(jbdTaiwanTravelPoint);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTaiwanTravelPointDao#removeJbdTaiwanTravelPoint(BigDecimal id)
     */
    public void removeJbdTaiwanTravelPoint(final BigDecimal id) {
        getHibernateTemplate().delete(getJbdTaiwanTravelPoint(id));
    }
    //added for getJbdTaiwanTravelPointsByCrm
    public List getJbdTaiwanTravelPointsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdTaiwanTravelPoint jbdTaiwanTravelPoint where 1=1";

		String userCode = crm.getString("userCode", "");
		if(!StringUtil.isEmpty(userCode)){
			hql += " and userCode='"+userCode+"'";
		}
		String companyCode = crm.getString("companyCode", "");
		if(!StringUtil.isEmpty(companyCode)&&!"AA".equals(companyCode)){
			hql += " and companyCode='"+companyCode+"'";
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
