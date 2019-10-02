
package com.joymain.jecs.pm.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pm.model.JpmWineSettingInf;
import com.joymain.jecs.pm.dao.JpmWineSettingInfDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JpmWineSettingInfDaoHibernate extends BaseDaoHibernate implements JpmWineSettingInfDao {

    /**
     * @see com.joymain.jecs.pm.dao.JpmWineSettingInfDao#getJpmWineSettingInfs(com.joymain.jecs.pm.model.JpmWineSettingInf)
     */
    public List getJpmWineSettingInfs(final JpmWineSettingInf jpmWineSettingInf) {
        return getHibernateTemplate().find("from JpmWineSettingInf");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jpmWineSettingInf == null) {
            return getHibernateTemplate().find("from JpmWineSettingInf");
        } else {
            // filter on properties set in the jpmWineSettingInf
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jpmWineSettingInf).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JpmWineSettingInf.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmWineSettingInfDao#getJpmWineSettingInf(Long settingId)
     */
    public JpmWineSettingInf getJpmWineSettingInf(final Long settingId) {
        JpmWineSettingInf jpmWineSettingInf = (JpmWineSettingInf) getHibernateTemplate().get(JpmWineSettingInf.class, settingId);
        if (jpmWineSettingInf == null) {
            log.warn("uh oh, jpmWineSettingInf with settingId '" + settingId + "' not found...");
            throw new ObjectRetrievalFailureException(JpmWineSettingInf.class, settingId);
        }

        return jpmWineSettingInf;
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmWineSettingInfDao#saveJpmWineSettingInf(JpmWineSettingInf jpmWineSettingInf)
     */    
    public void saveJpmWineSettingInf(final JpmWineSettingInf jpmWineSettingInf) {
        getHibernateTemplate().saveOrUpdate(jpmWineSettingInf);
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmWineSettingInfDao#removeJpmWineSettingInf(Long settingId)
     */
    public void removeJpmWineSettingInf(final Long settingId) {
        getHibernateTemplate().delete(getJpmWineSettingInf(settingId));
    }
    //added for getJpmWineSettingInfsByCrm
    public List getJpmWineSettingInfsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JpmWineSettingInf jpmWineSettingInf where 1=1";
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
			hql += " order by settingId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
