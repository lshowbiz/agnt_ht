
package com.joymain.jecs.mi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.mi.model.JmiGradeLock;
import com.joymain.jecs.mi.model.JmiValidLevelList;
import com.joymain.jecs.mi.dao.JmiGradeLockDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class JmiGradeLockDaoHibernate extends BaseDaoHibernate implements JmiGradeLockDao {

    /**
     * @see com.joymain.jecs.mi.dao.JmiGradeLockDao#getJmiGradeLocks(com.joymain.jecs.mi.model.JmiGradeLock)
     */
    public List getJmiGradeLocks(final JmiGradeLock jmiGradeLock) {
        return getHibernateTemplate().find("from JmiGradeLock");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jmiGradeLock == null) {
            return getHibernateTemplate().find("from JmiGradeLock");
        } else {
            // filter on properties set in the jmiGradeLock
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jmiGradeLock).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JmiGradeLock.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiGradeLockDao#getJmiGradeLock(BigDecimal id)
     */
    public JmiGradeLock getJmiGradeLock(final Long id) {
        JmiGradeLock jmiGradeLock = (JmiGradeLock) getHibernateTemplate().get(JmiGradeLock.class, id);
/*        if (jmiGradeLock == null) {
            log.warn("uh oh, jmiGradeLock with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JmiGradeLock.class, id);
        }*/

        return jmiGradeLock;
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiGradeLockDao#saveJmiGradeLock(JmiGradeLock jmiGradeLock)
     */    
    public void saveJmiGradeLock(final JmiGradeLock jmiGradeLock) {
        getHibernateTemplate().saveOrUpdate(jmiGradeLock);
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiGradeLockDao#removeJmiGradeLock(BigDecimal id)
     */
    public void removeJmiGradeLock(final Long id) {
        getHibernateTemplate().delete(getJmiGradeLock(id));
    }
    //added for getJmiGradeLocksByCrm
    public List getJmiGradeLocksByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JmiGradeLock jmiGradeLock where 1=1";
    	
    	String userCode = crm.getString("userCode", "");
		if(!StringUtil.isEmpty(userCode)){
			hql += " and  userCode='"+userCode+"'";
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
    
    public JmiGradeLock getJmiGradeLockByUserCode(String userCode, Integer validWeek) {
		return (JmiGradeLock) this.getObjectByHqlQuery("from JmiGradeLock where userCode='"+userCode+"' and validWeek="+validWeek);
	}
}
