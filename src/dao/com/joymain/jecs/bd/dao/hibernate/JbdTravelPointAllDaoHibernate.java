
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.bd.dao.JbdTravelPointAllDao;
import com.joymain.jecs.bd.model.JbdTravelPointAll;
import com.joymain.jecs.bd.model.JbdTravelPointAllPK;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

public class JbdTravelPointAllDaoHibernate extends BaseDaoHibernate implements JbdTravelPointAllDao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointAllDao#getJbdTravelPointAlls(com.joymain.jecs.bd.model.JbdTravelPointAll)
     */
    public List getJbdTravelPointAlls(final JbdTravelPointAll jbdTravelPointAll) {
        return getHibernateTemplate().find("from JbdTravelPointAll");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdTravelPointAll == null) {
            return getHibernateTemplate().find("from JbdTravelPointAll");
        } else {
            // filter on properties set in the jbdTravelPointAll
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdTravelPointAll).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdTravelPointAll.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointAllDao#getJbdTravelPointAll(JbdTravelPointAllPK comp_id)
     */
    public JbdTravelPointAll getJbdTravelPointAll(final JbdTravelPointAllPK comp_id) {
        JbdTravelPointAll jbdTravelPointAll = (JbdTravelPointAll) getHibernateTemplate().get(JbdTravelPointAll.class, comp_id);
        if (jbdTravelPointAll == null) {
            log.warn("uh oh, jbdTravelPointAll with comp_id '" + comp_id + "' not found...");
            throw new ObjectRetrievalFailureException(JbdTravelPointAll.class, comp_id);
        }

        return jbdTravelPointAll;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointAllDao#saveJbdTravelPointAll(JbdTravelPointAll jbdTravelPointAll)
     */    
    public void saveJbdTravelPointAll(final JbdTravelPointAll jbdTravelPointAll) {
        getHibernateTemplate().saveOrUpdate(jbdTravelPointAll);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointAllDao#removeJbdTravelPointAll(JbdTravelPointAllPK comp_id)
     */
    public void removeJbdTravelPointAll(final JbdTravelPointAllPK comp_id) {
        getHibernateTemplate().delete(getJbdTravelPointAll(comp_id));
    }
    //added for getJbdTravelPointAllsByCrm
    public List getJbdTravelPointAllsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdTravelPointAll jbdTravelPointAll where 1=1 and jbdTravelPointAll.comp_id.fyear>=2015";
		
    	String userCode = crm.getString("userCode", "");
    	if(!StringUtil.isEmpty(userCode)){
			hql += " and jbdTravelPointAll.comp_id.userCode='"+userCode+"'";
		}
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by comp_id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
    
    public JbdTravelPointAll getJbdTravelPointAll(final String userCode,final Integer fyear) {
    	List list = getHibernateTemplate().find("from JbdTravelPointAll jbdTravelPointAll where jbdTravelPointAll.comp_id.userCode='"+userCode+"' and jbdTravelPointAll.comp_id.fyear="+fyear );
        if (null!=list && list.size()>0) {
            return (JbdTravelPointAll)list.get(0);
        }
        return null;
    }
}
