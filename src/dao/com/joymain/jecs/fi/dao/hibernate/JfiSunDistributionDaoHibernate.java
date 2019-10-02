
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.JfiSunDistribution;
import com.joymain.jecs.fi.dao.JfiSunDistributionDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JfiSunDistributionDaoHibernate extends BaseDaoHibernate implements JfiSunDistributionDao {

    /**
     * @see com.joymain.jecs.fi.dao.JfiSunDistributionDao#getJfiSunDistributions(com.joymain.jecs.fi.model.JfiSunDistribution)
     */
    public List getJfiSunDistributions(final JfiSunDistribution jfiSunDistribution) {
        return getHibernateTemplate().find("from JfiSunDistribution");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jfiSunDistribution == null) {
            return getHibernateTemplate().find("from JfiSunDistribution");
        } else {
            // filter on properties set in the jfiSunDistribution
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jfiSunDistribution).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JfiSunDistribution.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiSunDistributionDao#getJfiSunDistribution(String userCode)
     */
    public JfiSunDistribution getJfiSunDistribution(final String userCode) {
        JfiSunDistribution jfiSunDistribution = (JfiSunDistribution) getHibernateTemplate().get(JfiSunDistribution.class, userCode);
        if (jfiSunDistribution == null) {
            log.warn("uh oh, jfiSunDistribution with userCode '" + userCode + "' not found...");
            throw new ObjectRetrievalFailureException(JfiSunDistribution.class, userCode);
        }

        return jfiSunDistribution;
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiSunDistributionDao#saveJfiSunDistribution(JfiSunDistribution jfiSunDistribution)
     */    
    public void saveJfiSunDistribution(final JfiSunDistribution jfiSunDistribution) {
        getHibernateTemplate().saveOrUpdate(jfiSunDistribution);
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiSunDistributionDao#removeJfiSunDistribution(String userCode)
     */
    public void removeJfiSunDistribution(final String userCode) {
        getHibernateTemplate().delete(getJfiSunDistribution(userCode));
    }
    //added for getJfiSunDistributionsByCrm
    public List getJfiSunDistributionsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JfiSunDistribution jfiSunDistribution where 1=1";
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
			hql += " order by userCode desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
