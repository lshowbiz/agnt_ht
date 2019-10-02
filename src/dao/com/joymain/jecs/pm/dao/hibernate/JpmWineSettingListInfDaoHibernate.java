
package com.joymain.jecs.pm.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pm.model.JpmWineSettingListInf;
import com.joymain.jecs.pm.dao.JpmWineSettingListInfDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JpmWineSettingListInfDaoHibernate extends BaseDaoHibernate implements JpmWineSettingListInfDao {

    /**
     * @see com.joymain.jecs.pm.dao.JpmWineSettingListInfDao#getJpmWineSettingListInfs(com.joymain.jecs.pm.model.JpmWineSettingListInf)
     */
    public List getJpmWineSettingListInfs(final JpmWineSettingListInf jpmWineSettingListInf) {
        return getHibernateTemplate().find("from JpmWineSettingListInf");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jpmWineSettingListInf == null) {
            return getHibernateTemplate().find("from JpmWineSettingListInf");
        } else {
            // filter on properties set in the jpmWineSettingListInf
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jpmWineSettingListInf).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JpmWineSettingListInf.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmWineSettingListInfDao#getJpmWineSettingListInf(Long idf)
     */
    public JpmWineSettingListInf getJpmWineSettingListInf(final Long idf) {
        JpmWineSettingListInf jpmWineSettingListInf = (JpmWineSettingListInf) getHibernateTemplate().get(JpmWineSettingListInf.class, idf);
        if (jpmWineSettingListInf == null) {
            log.warn("uh oh, jpmWineSettingListInf with idf '" + idf + "' not found...");
            throw new ObjectRetrievalFailureException(JpmWineSettingListInf.class, idf);
        }

        return jpmWineSettingListInf;
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmWineSettingListInfDao#saveJpmWineSettingListInf(JpmWineSettingListInf jpmWineSettingListInf)
     */    
    public void saveJpmWineSettingListInf(final JpmWineSettingListInf jpmWineSettingListInf) {
        getHibernateTemplate().saveOrUpdate(jpmWineSettingListInf);
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmWineSettingListInfDao#removeJpmWineSettingListInf(Long idf)
     */
    public void removeJpmWineSettingListInf(final Long idf) {
        getHibernateTemplate().delete(getJpmWineSettingListInf(idf));
    }
    //added for getJpmWineSettingListInfsByCrm
    public List getJpmWineSettingListInfsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JpmWineSettingListInf jpmWineSettingListInf where 1=1";
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
			hql += " order by idf desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
