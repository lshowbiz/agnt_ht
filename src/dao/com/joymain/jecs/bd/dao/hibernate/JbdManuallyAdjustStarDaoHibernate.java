
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdManuallyAdjustStar;
import com.joymain.jecs.bd.dao.JbdManuallyAdjustStarDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdManuallyAdjustStarDaoHibernate extends BaseDaoHibernate implements JbdManuallyAdjustStarDao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdManuallyAdjustStarDao#getJbdManuallyAdjustStars(com.joymain.jecs.bd.model.JbdManuallyAdjustStar)
     */
    public List getJbdManuallyAdjustStars(final JbdManuallyAdjustStar jbdManuallyAdjustStar) {
        return getHibernateTemplate().find("from JbdManuallyAdjustStar");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdManuallyAdjustStar == null) {
            return getHibernateTemplate().find("from JbdManuallyAdjustStar");
        } else {
            // filter on properties set in the jbdManuallyAdjustStar
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdManuallyAdjustStar).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdManuallyAdjustStar.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdManuallyAdjustStarDao#getJbdManuallyAdjustStar(BigDecimal id)
     */
    public JbdManuallyAdjustStar getJbdManuallyAdjustStar(final Long id) {
        JbdManuallyAdjustStar jbdManuallyAdjustStar = (JbdManuallyAdjustStar) getHibernateTemplate().get(JbdManuallyAdjustStar.class, id);
        if (jbdManuallyAdjustStar == null) {
            log.warn("uh oh, jbdManuallyAdjustStar with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JbdManuallyAdjustStar.class, id);
        }

        return jbdManuallyAdjustStar;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdManuallyAdjustStarDao#saveJbdManuallyAdjustStar(JbdManuallyAdjustStar jbdManuallyAdjustStar)
     */    
    public void saveJbdManuallyAdjustStar(final JbdManuallyAdjustStar jbdManuallyAdjustStar) {
        getHibernateTemplate().saveOrUpdate(jbdManuallyAdjustStar);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdManuallyAdjustStarDao#removeJbdManuallyAdjustStar(BigDecimal id)
     */
    public void removeJbdManuallyAdjustStar(final Long id) {
        getHibernateTemplate().delete(getJbdManuallyAdjustStar(id));
    }
    //added for getJbdManuallyAdjustStarsByCrm
    public List getJbdManuallyAdjustStarsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdManuallyAdjustStar jbdManuallyAdjustStar where 1=1";

		String userCode = crm.getString("userCode", "");
		if(!StringUtil.isEmpty(userCode)){
			hql += " and userCode='"+userCode+"'";
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

	public JbdManuallyAdjustStar getJbdManuallyAdjustStarByUserCodeAndWeek(String userCode,String wweek) {
		return (JbdManuallyAdjustStar) this.getObjectByHqlQuery("from JbdManuallyAdjustStar where userCode='"+userCode+"' and wweek='"+wweek+"'");
	}
    
}
