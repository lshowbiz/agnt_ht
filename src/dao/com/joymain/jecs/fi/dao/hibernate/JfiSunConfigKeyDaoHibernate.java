
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.JfiSunConfigKey;
import com.joymain.jecs.fi.dao.JfiSunConfigKeyDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JfiSunConfigKeyDaoHibernate extends BaseDaoHibernate implements JfiSunConfigKeyDao {

    /**
     * @see com.joymain.jecs.fi.dao.JfiSunConfigKeyDao#getJfiSunConfigKeys(com.joymain.jecs.fi.model.JfiSunConfigKey)
     */
    public List getJfiSunConfigKeys(final JfiSunConfigKey jfiSunConfigKey) {
        return getHibernateTemplate().find("from JfiSunConfigKey");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jfiSunConfigKey == null) {
            return getHibernateTemplate().find("from JfiSunConfigKey");
        } else {
            // filter on properties set in the jfiSunConfigKey
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jfiSunConfigKey).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JfiSunConfigKey.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiSunConfigKeyDao#getJfiSunConfigKey(String configCode)
     */
    public JfiSunConfigKey getJfiSunConfigKey(final String configCode) {
        JfiSunConfigKey jfiSunConfigKey = (JfiSunConfigKey) getHibernateTemplate().get(JfiSunConfigKey.class, configCode);
        if (jfiSunConfigKey == null) {
            log.warn("uh oh, jfiSunConfigKey with configCode '" + configCode + "' not found...");
            throw new ObjectRetrievalFailureException(JfiSunConfigKey.class, configCode);
        }

        return jfiSunConfigKey;
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiSunConfigKeyDao#saveJfiSunConfigKey(JfiSunConfigKey jfiSunConfigKey)
     */    
    public void saveJfiSunConfigKey(final JfiSunConfigKey jfiSunConfigKey) {
        getHibernateTemplate().saveOrUpdate(jfiSunConfigKey);
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiSunConfigKeyDao#removeJfiSunConfigKey(String configCode)
     */
    public void removeJfiSunConfigKey(final String configCode) {
        getHibernateTemplate().delete(getJfiSunConfigKey(configCode));
    }
    //added for getJfiSunConfigKeysByCrm
    public List getJfiSunConfigKeysByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JfiSunConfigKey jfiSunConfigKey where 1=1";
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
			hql += " order by configCode desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
