
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdManualCon;
import com.joymain.jecs.bd.dao.JbdManualConDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdManualConDaoHibernate extends BaseDaoHibernate implements JbdManualConDao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdManualConDao#getJbdManualCons(com.joymain.jecs.bd.model.JbdManualCon)
     */
    public List getJbdManualCons(final JbdManualCon jbdManualCon) {
        return getHibernateTemplate().find("from JbdManualCon");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdManualCon == null) {
            return getHibernateTemplate().find("from JbdManualCon");
        } else {
            // filter on properties set in the jbdManualCon
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdManualCon).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdManualCon.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdManualConDao#getJbdManualCon(BigDecimal id)
     */
    public JbdManualCon getJbdManualCon(final Long id) {
        JbdManualCon jbdManualCon = (JbdManualCon) getHibernateTemplate().get(JbdManualCon.class, id);
/*        if (jbdManualCon == null) {
            log.warn("uh oh, jbdManualCon with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JbdManualCon.class, id);
        }*/

        return jbdManualCon;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdManualConDao#saveJbdManualCon(JbdManualCon jbdManualCon)
     */    
    public void saveJbdManualCon(final JbdManualCon jbdManualCon) {
        getHibernateTemplate().saveOrUpdate(jbdManualCon);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdManualConDao#removeJbdManualCon(BigDecimal id)
     */
    public void removeJbdManualCon(final Long id) {
        getHibernateTemplate().delete(getJbdManualCon(id));
    }
    //added for getJbdManualConsByCrm
    public List getJbdManualConsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdManualCon jbdManualCon where 1=1";
    	
    	String userCode=crm.getString("userCode", "");
    	if(!StringUtil.isEmpty(userCode)){
    		hql+=" and userCode='"+userCode+"'";
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
    
    public void saveJbdManualCons(List<JbdManualCon> jbdManualCons){
    	getHibernateTemplate().saveOrUpdateAll(jbdManualCons);
    }
}
