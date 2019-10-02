
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.FiPayAccountConfig;
import com.joymain.jecs.fi.dao.FiPayAccountConfigDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class FiPayAccountConfigDaoHibernate extends BaseDaoHibernate implements FiPayAccountConfigDao {

    /**
     * @see com.joymain.jecs.fi.dao.FiPayAccountConfigDao#getFiPayAccountConfigs(com.joymain.jecs.fi.model.FiPayAccountConfig)
     */
    public List getFiPayAccountConfigs(final FiPayAccountConfig fiPayAccountConfig) {
        return getHibernateTemplate().find("from FiPayAccountConfig");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (fiPayAccountConfig == null) {
            return getHibernateTemplate().find("from FiPayAccountConfig");
        } else {
            // filter on properties set in the fiPayAccountConfig
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(fiPayAccountConfig).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(FiPayAccountConfig.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiPayAccountConfigDao#getFiPayAccountConfig(String province)
     */
    public FiPayAccountConfig getFiPayAccountConfig(final String province) {
        FiPayAccountConfig fiPayAccountConfig = (FiPayAccountConfig) getHibernateTemplate().get(FiPayAccountConfig.class, province);
        if (fiPayAccountConfig == null) {
            log.warn("uh oh, fiPayAccountConfig with province '" + province + "' not found...");
            throw new ObjectRetrievalFailureException(FiPayAccountConfig.class, province);
        }

        return fiPayAccountConfig;
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiPayAccountConfigDao#saveFiPayAccountConfig(FiPayAccountConfig fiPayAccountConfig)
     */    
    public void saveFiPayAccountConfig(final FiPayAccountConfig fiPayAccountConfig) {
        getHibernateTemplate().saveOrUpdate(fiPayAccountConfig);
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiPayAccountConfigDao#removeFiPayAccountConfig(String province)
     */
    public void removeFiPayAccountConfig(final String province) {
        getHibernateTemplate().delete(getFiPayAccountConfig(province));
    }
    //added for getFiPayAccountConfigsByCrm
    public List getFiPayAccountConfigsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from FiPayAccountConfig fiPayAccountConfig where 1=1";
    
		String accountCode = crm.getString("accountCode", "");
		if(StringUtils.isNotEmpty(accountCode)){
			hql += " and fiPayAccount.accountCode='"+accountCode+"'";
		}
		
		String province = crm.getString("province", "");
		if(StringUtils.isNotEmpty(province)){
			hql += " and province='"+province+"'";
		}
	
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by province asc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
