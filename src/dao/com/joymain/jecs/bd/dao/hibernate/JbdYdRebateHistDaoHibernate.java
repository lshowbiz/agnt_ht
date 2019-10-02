
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdYdRebateHist;
import com.joymain.jecs.bd.dao.JbdYdRebateHistDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdYdRebateHistDaoHibernate extends BaseDaoHibernate implements JbdYdRebateHistDao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdYdRebateHistDao#getJbdYdRebateHists(com.joymain.jecs.bd.model.JbdYdRebateHist)
     */
    public List getJbdYdRebateHists(final JbdYdRebateHist jbdYdRebateHist) {
        return getHibernateTemplate().find("from JbdYdRebateHist");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdYdRebateHist == null) {
            return getHibernateTemplate().find("from JbdYdRebateHist");
        } else {
            // filter on properties set in the jbdYdRebateHist
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdYdRebateHist).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdYdRebateHist.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdYdRebateHistDao#getJbdYdRebateHist(BigDecimal id)
     */
    public JbdYdRebateHist getJbdYdRebateHist(final Long id) {
        JbdYdRebateHist jbdYdRebateHist = (JbdYdRebateHist) getHibernateTemplate().get(JbdYdRebateHist.class, id);
        if (jbdYdRebateHist == null) {
            log.warn("uh oh, jbdYdRebateHist with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JbdYdRebateHist.class, id);
        }

        return jbdYdRebateHist;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdYdRebateHistDao#saveJbdYdRebateHist(JbdYdRebateHist jbdYdRebateHist)
     */    
    public void saveJbdYdRebateHist(final JbdYdRebateHist jbdYdRebateHist) {
        getHibernateTemplate().saveOrUpdate(jbdYdRebateHist);
    }
    public void save(final JbdYdRebateHist jbdYdRebateHist) {
        getHibernateTemplate().save(jbdYdRebateHist);
    }
    /**
     * @see com.joymain.jecs.bd.dao.JbdYdRebateHistDao#removeJbdYdRebateHist(BigDecimal id)
     */
    public void removeJbdYdRebateHist(final Long id) {
        getHibernateTemplate().delete(getJbdYdRebateHist(id));
    }
    //added for getJbdYdRebateHistsByCrm
    public List getJbdYdRebateHistsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdYdRebateHist jbdYdRebateHist where 1=1";
    	/** 
	---example---
	String userCode = crm.getString("userCode", "");
	if(StringUtils.isNotEmpty(userCode)){
		hql += "???????????";
	}
	 ***/
    	
    	
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
