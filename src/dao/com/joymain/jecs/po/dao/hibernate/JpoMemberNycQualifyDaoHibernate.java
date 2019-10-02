
package com.joymain.jecs.po.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.po.model.JpoMemberNycQualify;
import com.joymain.jecs.po.dao.JpoMemberNycQualifyDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JpoMemberNycQualifyDaoHibernate extends BaseDaoHibernate implements JpoMemberNycQualifyDao {

    /**
     * @see com.joymain.jecs.po.dao.JpoMemberNycQualifyDao#getJpoMemberNycQualifys(com.joymain.jecs.po.model.JpoMemberNycQualify)
     */
    public List getJpoMemberNycQualifys(final JpoMemberNycQualify jpoMemberNycQualify) {
        return getHibernateTemplate().find("from JpoMemberNycQualify");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jpoMemberNycQualify == null) {
            return getHibernateTemplate().find("from JpoMemberNycQualify");
        } else {
            // filter on properties set in the jpoMemberNycQualify
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jpoMemberNycQualify).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JpoMemberNycQualify.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.po.dao.JpoMemberNycQualifyDao#getJpoMemberNycQualify(Long id)
     */
    public JpoMemberNycQualify getJpoMemberNycQualify(final Long id) {
        JpoMemberNycQualify jpoMemberNycQualify = (JpoMemberNycQualify) getHibernateTemplate().get(JpoMemberNycQualify.class, id);
        if (jpoMemberNycQualify == null) {
            log.warn("uh oh, jpoMemberNycQualify with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JpoMemberNycQualify.class, id);
        }

        return jpoMemberNycQualify;
    }

    /**
     * @see com.joymain.jecs.po.dao.JpoMemberNycQualifyDao#saveJpoMemberNycQualify(JpoMemberNycQualify jpoMemberNycQualify)
     */    
    public void saveJpoMemberNycQualify(final JpoMemberNycQualify jpoMemberNycQualify) {
        getHibernateTemplate().saveOrUpdate(jpoMemberNycQualify);
    }

    /**
     * @see com.joymain.jecs.po.dao.JpoMemberNycQualifyDao#removeJpoMemberNycQualify(BigDecimal id)
     */
    public void removeJpoMemberNycQualify(final Long id) {
        getHibernateTemplate().delete(getJpoMemberNycQualify(id));
    }
    //added for getJpoMemberNycQualifysByCrm
    public List getJpoMemberNycQualifysByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JpoMemberNycQualify jpoMemberNycQualify where 1=1";
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
