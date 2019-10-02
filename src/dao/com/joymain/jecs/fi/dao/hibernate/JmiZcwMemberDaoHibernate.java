
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.JmiZcwMember;
import com.joymain.jecs.fi.dao.JmiZcwMemberDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.apache.commons.lang.StringUtils;

public class JmiZcwMemberDaoHibernate extends BaseDaoHibernate implements JmiZcwMemberDao {

    /**
     * @see com.joymain.jecs.fi.dao.JmiZcwMemberDao#getJmiZcwMembers(com.joymain.jecs.fi.model.JmiZcwMember)
     */
    public List getJmiZcwMembers(final JmiZcwMember jmiZcwMember) {
        return getHibernateTemplate().find("from JmiZcwMember");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jmiZcwMember == null) {
            return getHibernateTemplate().find("from JmiZcwMember");
        } else {
            // filter on properties set in the jmiZcwMember
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jmiZcwMember).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JmiZcwMember.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.JmiZcwMemberDao#getJmiZcwMember(BigDecimal zcwId)
     */
    public JmiZcwMember getJmiZcwMember(final BigDecimal zcwId) {
        JmiZcwMember jmiZcwMember = (JmiZcwMember) getHibernateTemplate().get(JmiZcwMember.class, zcwId);
        if (jmiZcwMember == null) {
            log.warn("uh oh, jmiZcwMember with zcwId '" + zcwId + "' not found...");
            throw new ObjectRetrievalFailureException(JmiZcwMember.class, zcwId);
        }

        return jmiZcwMember;
    }

    /**
     * @see com.joymain.jecs.fi.dao.JmiZcwMemberDao#saveJmiZcwMember(JmiZcwMember jmiZcwMember)
     */    
    public void saveJmiZcwMember(final JmiZcwMember jmiZcwMember) {
        getHibernateTemplate().saveOrUpdate(jmiZcwMember);
    }

    /**
     * @see com.joymain.jecs.fi.dao.JmiZcwMemberDao#removeJmiZcwMember(BigDecimal zcwId)
     */
    public void removeJmiZcwMember(final BigDecimal zcwId) {
        getHibernateTemplate().delete(getJmiZcwMember(zcwId));
    }
    //added for getJmiZcwMembersByCrm
    public List getJmiZcwMembersByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JmiZcwMember jmiZcwMember where 1=1";
    	
    	String userCode = crm.getString("userCode", "");
    	if(StringUtils.isNotEmpty(userCode)){
    		hql += " and userCode='"+userCode+"'";
    	}

		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by zcwId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
