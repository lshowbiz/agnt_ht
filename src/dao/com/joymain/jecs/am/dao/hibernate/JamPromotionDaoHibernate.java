
package com.joymain.jecs.am.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.am.model.JamPromotion;
import com.joymain.jecs.am.dao.JamPromotionDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JamPromotionDaoHibernate extends BaseDaoHibernate implements JamPromotionDao {

    /**
     * @see com.joymain.jecs.am.dao.JamPromotionDao#getJamPromotions(com.joymain.jecs.am.model.JamPromotion)
     */
    public List getJamPromotions(final JamPromotion jamPromotion) {
        return getHibernateTemplate().find("from JamPromotion");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jamPromotion == null) {
            return getHibernateTemplate().find("from JamPromotion");
        } else {
            // filter on properties set in the jamPromotion
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jamPromotion).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JamPromotion.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.am.dao.JamPromotionDao#getJamPromotion(Long id)
     */
    public JamPromotion getJamPromotion(final Long id) {
        JamPromotion jamPromotion = (JamPromotion) getHibernateTemplate().get(JamPromotion.class, id);
        if (jamPromotion == null) {
            log.warn("uh oh, jamPromotion with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JamPromotion.class, id);
        }

        return jamPromotion;
    }

    /**
     * @see com.joymain.jecs.am.dao.JamPromotionDao#saveJamPromotion(JamPromotion jamPromotion)
     */    
    public void saveJamPromotion(final JamPromotion jamPromotion) {
        getHibernateTemplate().saveOrUpdate(jamPromotion);
    }

    /**
     * @see com.joymain.jecs.am.dao.JamPromotionDao#removeJamPromotion(Long id)
     */
    public void removeJamPromotion(final Long id) {
        getHibernateTemplate().delete(getJamPromotion(id));
    }
    //added for getJamPromotionsByCrm
    public List getJamPromotionsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JamPromotion jamPromotion where 1=1";

		String companyCode = crm.getString("companyCode", "");
		if (!"AA".equals(companyCode)) {
			hql += " and companyCode = '" + companyCode + "'";
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
