
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdManuallyAdjustAlgebra;
import com.joymain.jecs.bd.model.JbdManuallyAdjustStar;
import com.joymain.jecs.bd.dao.JbdManuallyAdjustAlgebraDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdManuallyAdjustAlgebraDaoHibernate extends BaseDaoHibernate implements JbdManuallyAdjustAlgebraDao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdManuallyAdjustAlgebraDao#getJbdManuallyAdjustAlgebras(com.joymain.jecs.bd.model.JbdManuallyAdjustAlgebra)
     */
    public List getJbdManuallyAdjustAlgebras(final JbdManuallyAdjustAlgebra jbdManuallyAdjustAlgebra) {
        return getHibernateTemplate().find("from JbdManuallyAdjustAlgebra");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdManuallyAdjustAlgebra == null) {
            return getHibernateTemplate().find("from JbdManuallyAdjustAlgebra");
        } else {
            // filter on properties set in the jbdManuallyAdjustAlgebra
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdManuallyAdjustAlgebra).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdManuallyAdjustAlgebra.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdManuallyAdjustAlgebraDao#getJbdManuallyAdjustAlgebra(Long id)
     */
    public JbdManuallyAdjustAlgebra getJbdManuallyAdjustAlgebra(final Long id) {
        JbdManuallyAdjustAlgebra jbdManuallyAdjustAlgebra = (JbdManuallyAdjustAlgebra) getHibernateTemplate().get(JbdManuallyAdjustAlgebra.class, id);
        if (jbdManuallyAdjustAlgebra == null) {
            log.warn("uh oh, jbdManuallyAdjustAlgebra with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JbdManuallyAdjustAlgebra.class, id);
        }

        return jbdManuallyAdjustAlgebra;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdManuallyAdjustAlgebraDao#saveJbdManuallyAdjustAlgebra(JbdManuallyAdjustAlgebra jbdManuallyAdjustAlgebra)
     */    
    public void saveJbdManuallyAdjustAlgebra(final JbdManuallyAdjustAlgebra jbdManuallyAdjustAlgebra) {
        getHibernateTemplate().saveOrUpdate(jbdManuallyAdjustAlgebra);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdManuallyAdjustAlgebraDao#removeJbdManuallyAdjustAlgebra(Long id)
     */
    public void removeJbdManuallyAdjustAlgebra(final Long id) {
        getHibernateTemplate().delete(getJbdManuallyAdjustAlgebra(id));
    }
    //added for getJbdManuallyAdjustAlgebrasByCrm
    public List getJbdManuallyAdjustAlgebrasByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdManuallyAdjustAlgebra jbdManuallyAdjustAlgebra where 1=1";
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
	public JbdManuallyAdjustAlgebra getJbdManuallyAdjustAlgebraByUserCodeAndWeek(String userCode,String wweek) {
		return (JbdManuallyAdjustAlgebra) this.getObjectByHqlQuery("from JbdManuallyAdjustAlgebra where userCode='"+userCode+"' and wweek='"+wweek+"'");
	}
}
