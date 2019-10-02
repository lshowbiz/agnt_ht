
package com.joymain.jecs.pm.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pm.model.JpmCouponRelationship;
import com.joymain.jecs.pm.dao.JpmCouponRelationshipDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JpmCouponRelationshipDaoHibernate extends BaseDaoHibernate implements JpmCouponRelationshipDao {

    /**
     * @see com.joymain.jecs.pm.dao.JpmCouponRelationshipDao#getJpmCouponRelationships(com.joymain.jecs.pm.model.JpmCouponRelationship)
     */
    public List getJpmCouponRelationships(final JpmCouponRelationship jpmCouponRelationship) {
        return getHibernateTemplate().find("from JpmCouponRelationship");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jpmCouponRelationship == null) {
            return getHibernateTemplate().find("from JpmCouponRelationship");
        } else {
            // filter on properties set in the jpmCouponRelationship
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jpmCouponRelationship).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JpmCouponRelationship.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmCouponRelationshipDao#getJpmCouponRelationship(BigDecimal id)
     */
    public JpmCouponRelationship getJpmCouponRelationship(final BigDecimal id) {
        JpmCouponRelationship jpmCouponRelationship = (JpmCouponRelationship) getHibernateTemplate().get(JpmCouponRelationship.class, id);
        if (jpmCouponRelationship == null) {
            log.warn("uh oh, jpmCouponRelationship with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JpmCouponRelationship.class, id);
        }

        return jpmCouponRelationship;
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmCouponRelationshipDao#saveJpmCouponRelationship(JpmCouponRelationship jpmCouponRelationship)
     */    
    public void saveJpmCouponRelationship(final JpmCouponRelationship jpmCouponRelationship) {
        getHibernateTemplate().saveOrUpdate(jpmCouponRelationship);
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmCouponRelationshipDao#removeJpmCouponRelationship(BigDecimal id)
     */
    public void removeJpmCouponRelationship(final BigDecimal id) {
        getHibernateTemplate().delete(getJpmCouponRelationship(id));
    }
    //added for getJpmCouponRelationshipsByCrm
    public List getJpmCouponRelationshipsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JpmCouponRelationship jpmCouponRelationship where 1=1";
    	/** 
	---example---
	String userCode = crm.getString("userCode", "");
	if(StringUtils.isNotEmpty(userCode)){
		hql += "???????????";
	}
	 ***/
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
