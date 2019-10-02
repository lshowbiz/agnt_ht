
package com.joymain.jecs.am.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.am.model.JamMsnType;
import com.joymain.jecs.am.dao.JamMsnTypeDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JamMsnTypeDaoHibernate extends BaseDaoHibernate implements JamMsnTypeDao {

    /**
     * @see com.joymain.jecs.am.dao.JamMsnTypeDao#getJamMsnTypes(com.joymain.jecs.am.model.JamMsnType)
     */
    public List getJamMsnTypes(final JamMsnType jamMsnType) {
        return getHibernateTemplate().findByExample(jamMsnType);

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jamMsnType == null) {
            return getHibernateTemplate().find("from JamMsnType");
        } else {
            // filter on properties set in the jamMsnType
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jamMsnType).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JamMsnType.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.am.dao.JamMsnTypeDao#getJamMsnType(Long mtId)
     */
    public JamMsnType getJamMsnType(final Long mtId) {
        JamMsnType jamMsnType = (JamMsnType) getHibernateTemplate().get(JamMsnType.class, mtId);
        if (jamMsnType == null) {
            log.warn("uh oh, jamMsnType with mtId '" + mtId + "' not found...");
            throw new ObjectRetrievalFailureException(JamMsnType.class, mtId);
        }

        return jamMsnType;
    }

    /**
     * @see com.joymain.jecs.am.dao.JamMsnTypeDao#saveJamMsnType(JamMsnType jamMsnType)
     */    
    public void saveJamMsnType(final JamMsnType jamMsnType) {
        getHibernateTemplate().saveOrUpdate(jamMsnType);
    }

    /**
     * @see com.joymain.jecs.am.dao.JamMsnTypeDao#removeJamMsnType(Long mtId)
     */
    public void removeJamMsnType(final Long mtId) {
        getHibernateTemplate().delete(getJamMsnType(mtId));
    }
    //added for getJamMsnTypesByCrm
    public List getJamMsnTypesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JamMsnType jamMsnType where 1=1";
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
			hql += " order by mtId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
