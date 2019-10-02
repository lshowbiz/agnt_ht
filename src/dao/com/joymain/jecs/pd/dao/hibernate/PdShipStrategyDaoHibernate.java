
package com.joymain.jecs.pd.dao.hibernate;

import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.dao.PdShipStrategyDao;
import com.joymain.jecs.pd.model.PdShipStrategy;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class PdShipStrategyDaoHibernate extends BaseDaoHibernate implements PdShipStrategyDao {

    /**
     * @see com.joymain.jecs.pd.dao.PdShipStrategyDao#getPdShipStrategys(com.joymain.jecs.pd.model.PdShipStrategy)
     */
    public List getPdShipStrategys(final PdShipStrategy pdShipStrategy) {
        return getHibernateTemplate().find("from PdShipStrategy");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (pdShipStrategy == null) {
            return getHibernateTemplate().find("from PdShipStrategy");
        } else {
            // filter on properties set in the pdShipStrategy
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(pdShipStrategy).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(PdShipStrategy.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdShipStrategyDao#getPdShipStrategy(Long ssId)
     */
    public PdShipStrategy getPdShipStrategy(final Long ssId) {
        PdShipStrategy pdShipStrategy = (PdShipStrategy) getHibernateTemplate().get(PdShipStrategy.class, ssId);
        if (pdShipStrategy == null) {
            log.warn("uh oh, pdShipStrategy with ssId '" + ssId + "' not found...");
            throw new ObjectRetrievalFailureException(PdShipStrategy.class, ssId);
        }

        return pdShipStrategy;
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdShipStrategyDao#savePdShipStrategy(PdShipStrategy pdShipStrategy)
     */    
    public void savePdShipStrategy(final PdShipStrategy pdShipStrategy) {
        getHibernateTemplate().saveOrUpdate(pdShipStrategy);
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdShipStrategyDao#removePdShipStrategy(Long ssId)
     */
    public void removePdShipStrategy(final Long ssId) {
        getHibernateTemplate().delete(getPdShipStrategy(ssId));
    }
    //added for getPdShipStrategysByCrm
    public List getPdShipStrategysByCrm(CommonRecord crm, Pager pager){
    	String hql = "from PdShipStrategy pdShipStrategy where 1=1";
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
			hql += " order by ssId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
