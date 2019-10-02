
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.FiCoinConfig;
import com.joymain.jecs.fi.dao.FiCoinConfigDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class FiCoinConfigDaoHibernate extends BaseDaoHibernate implements FiCoinConfigDao {

    /**
     * @see com.joymain.jecs.fi.dao.FiCoinConfigDao#getFiCoinConfigs(com.joymain.jecs.fi.model.FiCoinConfig)
     */
    public List getFiCoinConfigs(final FiCoinConfig fiCoinConfig) {
        return getHibernateTemplate().find("from FiCoinConfig");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (fiCoinConfig == null) {
            return getHibernateTemplate().find("from FiCoinConfig");
        } else {
            // filter on properties set in the fiCoinConfig
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(fiCoinConfig).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(FiCoinConfig.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiCoinConfigDao#getFiCoinConfig(Long configId)
     */
    public FiCoinConfig getFiCoinConfig(final Long configId) {
        FiCoinConfig fiCoinConfig = (FiCoinConfig) getHibernateTemplate().get(FiCoinConfig.class, configId);
        if (fiCoinConfig == null) {
            log.warn("uh oh, fiCoinConfig with configId '" + configId + "' not found...");
            throw new ObjectRetrievalFailureException(FiCoinConfig.class, configId);
        }

        return fiCoinConfig;
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiCoinConfigDao#saveFiCoinConfig(FiCoinConfig fiCoinConfig)
     */    
    public void saveFiCoinConfig(final FiCoinConfig fiCoinConfig) {
        getHibernateTemplate().saveOrUpdate(fiCoinConfig);
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiCoinConfigDao#removeFiCoinConfig(Long configId)
     */
    public void removeFiCoinConfig(final Long configId) {
        getHibernateTemplate().delete(getFiCoinConfig(configId));
    }
    //added for getFiCoinConfigsByCrm
    public List getFiCoinConfigsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from FiCoinConfig fiCoinConfig where 1=1";
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
			hql += " order by configId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
