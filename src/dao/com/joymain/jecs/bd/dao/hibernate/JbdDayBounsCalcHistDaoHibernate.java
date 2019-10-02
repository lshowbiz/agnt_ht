
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;

import com.joymain.jecs.bd.dao.JbdDayBounsCalcHistDao;
import com.joymain.jecs.bd.model.JbdDayBounsCalcHist;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdDayBounsCalcHistDaoHibernate extends BaseDaoHibernate implements JbdDayBounsCalcHistDao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdDayBounsCalcHistDao#getJbdDayBounsCalcHists(com.joymain.jecs.bd.model.JbdDayBounsCalcHist)
     */
    public List getJbdDayBounsCalcHists(final JbdDayBounsCalcHist jbdDayBounsCalcHist) {
        return getHibernateTemplate().find("from JbdDayBounsCalcHist");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdDayBounsCalcHist == null) {
            return getHibernateTemplate().find("from JbdDayBounsCalcHist");
        } else {
            // filter on properties set in the jbdDayBounsCalcHist
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdDayBounsCalcHist).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdDayBounsCalcHist.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdDayBounsCalcHistDao#getJbdDayBounsCalcHist(Long id)
     */
    public JbdDayBounsCalcHist getJbdDayBounsCalcHist(final Long id) {
        JbdDayBounsCalcHist jbdDayBounsCalcHist = (JbdDayBounsCalcHist) getHibernateTemplate().get(JbdDayBounsCalcHist.class, id);
        if (jbdDayBounsCalcHist == null) {
            log.warn("uh oh, jbdDayBounsCalcHist with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JbdDayBounsCalcHist.class, id);
        }

        return jbdDayBounsCalcHist;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdDayBounsCalcHistDao#saveJbdDayBounsCalcHist(JbdDayBounsCalcHist jbdDayBounsCalcHist)
     */    
    public void saveJbdDayBounsCalcHist(final JbdDayBounsCalcHist jbdDayBounsCalcHist) {
        getHibernateTemplate().saveOrUpdate(jbdDayBounsCalcHist);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdDayBounsCalcHistDao#removeJbdDayBounsCalcHist(Long id)
     */
    public void removeJbdDayBounsCalcHist(final Long id) {
        getHibernateTemplate().delete(getJbdDayBounsCalcHist(id));
    }
    //added for getJbdDayBounsCalcHistsByCrm
    public List getJbdDayBounsCalcHistsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdDayBounsCalcHist jbdDayBounsCalcHist where 1=1";
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
