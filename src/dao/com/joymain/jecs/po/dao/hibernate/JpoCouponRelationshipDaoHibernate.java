
package com.joymain.jecs.po.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.po.model.JpoCouponRelationship;
import com.joymain.jecs.po.dao.JpoCouponRelationshipDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JpoCouponRelationshipDaoHibernate extends BaseDaoHibernate implements JpoCouponRelationshipDao {

    /**
     * @see com.joymain.jecs.po.dao.JpoCouponRelationshipDao#getJpoCouponRelationships(com.joymain.jecs.po.model.JpoCouponRelationship)
     */
    public List getJpoCouponRelationships(final JpoCouponRelationship jpoCouponRelationship) {
        return getHibernateTemplate().find("from JpoCouponRelationship");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jpoCouponRelationship == null) {
            return getHibernateTemplate().find("from JpoCouponRelationship");
        } else {
            // filter on properties set in the jpoCouponRelationship
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jpoCouponRelationship).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JpoCouponRelationship.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.po.dao.JpoCouponRelationshipDao#getJpoCouponRelationship(BigDecimal id)
     */
    public JpoCouponRelationship getJpoCouponRelationship(final BigDecimal id) {
        JpoCouponRelationship jpoCouponRelationship = (JpoCouponRelationship) getHibernateTemplate().get(JpoCouponRelationship.class, id);
        if (jpoCouponRelationship == null) {
            log.warn("uh oh, jpoCouponRelationship with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JpoCouponRelationship.class, id);
        }

        return jpoCouponRelationship;
    }

    /**
     * @see com.joymain.jecs.po.dao.JpoCouponRelationshipDao#saveJpoCouponRelationship(JpoCouponRelationship jpoCouponRelationship)
     */    
    public void saveJpoCouponRelationship(final JpoCouponRelationship jpoCouponRelationship) {
        getHibernateTemplate().saveOrUpdate(jpoCouponRelationship);
    }

    /**
     * @see com.joymain.jecs.po.dao.JpoCouponRelationshipDao#removeJpoCouponRelationship(BigDecimal id)
     */
    public void removeJpoCouponRelationship(final BigDecimal id) {
        getHibernateTemplate().delete(getJpoCouponRelationship(id));
    }
    //added for getJpoCouponRelationshipsByCrm
    public List getJpoCouponRelationshipsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JpoCouponRelationship jpoCouponRelationship where 1=1";
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
