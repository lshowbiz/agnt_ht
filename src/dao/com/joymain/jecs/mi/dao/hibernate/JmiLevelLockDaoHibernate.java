
package com.joymain.jecs.mi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.mi.model.JmiLevelLock;
import com.joymain.jecs.mi.dao.JmiLevelLockDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JmiLevelLockDaoHibernate extends BaseDaoHibernate implements JmiLevelLockDao {

    /**
     * @see com.joymain.jecs.mi.dao.JmiLevelLockDao#getJmiLevelLocks(com.joymain.jecs.mi.model.JmiLevelLock)
     */
    public List getJmiLevelLocks(final JmiLevelLock jmiLevelLock) {
        return getHibernateTemplate().find("from JmiLevelLock");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jmiLevelLock == null) {
            return getHibernateTemplate().find("from JmiLevelLock");
        } else {
            // filter on properties set in the jmiLevelLock
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jmiLevelLock).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JmiLevelLock.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiLevelLockDao#getJmiLevelLock(BigDecimal id)
     */
    public JmiLevelLock getJmiLevelLock(final Long id) {
        JmiLevelLock jmiLevelLock = (JmiLevelLock) getHibernateTemplate().get(JmiLevelLock.class, id);
/*        if (jmiLevelLock == null) {
            log.warn("uh oh, jmiLevelLock with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JmiLevelLock.class, id);
        }*/

        return jmiLevelLock;
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiLevelLockDao#saveJmiLevelLock(JmiLevelLock jmiLevelLock)
     */    
    public void saveJmiLevelLock(final JmiLevelLock jmiLevelLock) {
        getHibernateTemplate().saveOrUpdate(jmiLevelLock);
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiLevelLockDao#removeJmiLevelLock(BigDecimal id)
     */
    public void removeJmiLevelLock(final Long id) {
        getHibernateTemplate().delete(getJmiLevelLock(id));
    }
    //added for getJmiLevelLocksByCrm
    public List getJmiLevelLocksByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JmiLevelLock jmiLevelLock where 1=1";

		String userCode = crm.getString("userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			hql += " and userCode = '" + userCode + "'";
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
    
    public JmiLevelLock getJmiLevelLockByUserCode(String userCode){
    	return (JmiLevelLock) this.getObjectByHqlQuery("from JmiLevelLock where userCode='"+userCode+"'");
    }
}
