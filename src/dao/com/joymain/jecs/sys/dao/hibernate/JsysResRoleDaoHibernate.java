
package com.joymain.jecs.sys.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.sys.model.JsysResRole;
import com.joymain.jecs.sys.dao.JsysResRoleDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JsysResRoleDaoHibernate extends BaseDaoHibernate implements JsysResRoleDao {

    /**
     * @see com.joymain.jecs.sys.dao.JsysResRoleDao#getJsysResRoles(com.joymain.jecs.sys.model.JsysResRole)
     */
    public List getJsysResRoles(final JsysResRole jsysResRole) {
        return getHibernateTemplate().find("from JsysResRole");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jsysResRole == null) {
            return getHibernateTemplate().find("from JsysResRole");
        } else {
            // filter on properties set in the jsysResRole
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jsysResRole).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JsysResRole.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.sys.dao.JsysResRoleDao#getJsysResRole(BigDecimal pid)
     */
    public JsysResRole getJsysResRole(final Long pid) {
        JsysResRole jsysResRole = (JsysResRole) getHibernateTemplate().get(JsysResRole.class, pid);
        if (jsysResRole == null) {
            log.warn("uh oh, jsysResRole with pid '" + pid + "' not found...");
            throw new ObjectRetrievalFailureException(JsysResRole.class, pid);
        }

        return jsysResRole;
    }

    /**
     * @see com.joymain.jecs.sys.dao.JsysResRoleDao#saveJsysResRole(JsysResRole jsysResRole)
     */    
    public void saveJsysResRole(final JsysResRole jsysResRole) {
        getHibernateTemplate().saveOrUpdate(jsysResRole);
    }

    /**
     * @see com.joymain.jecs.sys.dao.JsysResRoleDao#removeJsysResRole(Long pid)
     */
    public void removeJsysResRole(final Long pid) {
        getHibernateTemplate().delete(getJsysResRole(pid));
    }
    
    public void saveSysRoleResList(List<JsysResRole> jSysRoleRes){
    	
    	getHibernateTemplate().saveOrUpdateAll(jSysRoleRes);
    }
    
    //added for getJsysResRolesByCrm
    public List getJsysResRolesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JsysResRole jsysResRole where 1=1";
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
			hql += " order by pid desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
