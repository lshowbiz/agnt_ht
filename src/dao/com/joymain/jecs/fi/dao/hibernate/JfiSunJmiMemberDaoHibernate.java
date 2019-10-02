
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.JfiSunJmiMember;
import com.joymain.jecs.fi.dao.JfiSunJmiMemberDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JfiSunJmiMemberDaoHibernate extends BaseDaoHibernate implements JfiSunJmiMemberDao {

    /**
     * @see com.joymain.jecs.fi.dao.JfiSunJmiMemberDao#getJfiSunJmiMembers(com.joymain.jecs.fi.model.JfiSunJmiMember)
     */
    public List getJfiSunJmiMembers(final JfiSunJmiMember jfiSunJmiMember) {
        return getHibernateTemplate().find("from JfiSunJmiMember");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jfiSunJmiMember == null) {
            return getHibernateTemplate().find("from JfiSunJmiMember");
        } else {
            // filter on properties set in the jfiSunJmiMember
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jfiSunJmiMember).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JfiSunJmiMember.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiSunJmiMemberDao#getJfiSunJmiMember(String userCode)
     */
    public JfiSunJmiMember getJfiSunJmiMember(final String userCode) {
        JfiSunJmiMember jfiSunJmiMember = (JfiSunJmiMember) getHibernateTemplate().get(JfiSunJmiMember.class, userCode);
        if (jfiSunJmiMember == null) {
            log.warn("uh oh, jfiSunJmiMember with userCode '" + userCode + "' not found...");
            throw new ObjectRetrievalFailureException(JfiSunJmiMember.class, userCode);
        }

        return jfiSunJmiMember;
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiSunJmiMemberDao#saveJfiSunJmiMember(JfiSunJmiMember jfiSunJmiMember)
     */    
    public void saveJfiSunJmiMember(final JfiSunJmiMember jfiSunJmiMember) {
        getHibernateTemplate().saveOrUpdate(jfiSunJmiMember);
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiSunJmiMemberDao#removeJfiSunJmiMember(String userCode)
     */
    public void removeJfiSunJmiMember(final String userCode) {
        getHibernateTemplate().delete(getJfiSunJmiMember(userCode));
    }
    //added for getJfiSunJmiMembersByCrm
    public List getJfiSunJmiMembersByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JfiSunJmiMember jfiSunJmiMember where 1=1";
    	
		String villageAddr = crm.getString("villageAddr", "");
		if (!StringUtils.isEmpty(villageAddr)) {
			hql += " and villageAddr = '" + villageAddr + "'";
		}
		String townAddr = crm.getString("townAddr", "");
		if (!StringUtils.isEmpty(townAddr)) {
			hql += " and townAddr = '" + townAddr + "'";
		}
		
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by userCode desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
