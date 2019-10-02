
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;

import org.hibernate.LockMode;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.dao.FiCcoinBalanceDao;
import com.joymain.jecs.fi.model.FiCcoinBalance;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class FiCcoinBalanceDaoHibernate extends BaseDaoHibernate implements FiCcoinBalanceDao {

    /**
     * @see com.joymain.jecs.fi.dao.FiCcoinBalanceDao#getFiCcoinBalances(com.joymain.jecs.fi.model.FiCcoinBalance)
     */
    public List getFiCcoinBalances(final FiCcoinBalance fiCcoinBalance) {
        return getHibernateTemplate().find("from FiCcoinBalance");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (fiCcoinBalance == null) {
            return getHibernateTemplate().find("from FiCcoinBalance");
        } else {
            // filter on properties set in the fiCcoinBalance
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(fiCcoinBalance).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(FiCcoinBalance.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiCcoinBalanceDao#getFiCcoinBalance(String userCode)
     */
    public FiCcoinBalance getFiCcoinBalance(final String userCode) {
        FiCcoinBalance fiCcoinBalance = (FiCcoinBalance) getHibernateTemplate().get(FiCcoinBalance.class, userCode);
        if (fiCcoinBalance == null) {
            log.warn("uh oh, fiCcoinBalance with userCode '" + userCode + "' not found...");
            throw new ObjectRetrievalFailureException(FiCcoinBalance.class, userCode);
        }

        return fiCcoinBalance;
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiCcoinBalanceDao#saveFiCcoinBalance(FiCcoinBalance fiCcoinBalance)
     */    
    public void saveFiCcoinBalance(final FiCcoinBalance fiCcoinBalance) {
        getHibernateTemplate().saveOrUpdate(fiCcoinBalance);
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiCcoinBalanceDao#removeFiCcoinBalance(String userCode)
     */
    public void removeFiCcoinBalance(final String userCode) {
        getHibernateTemplate().delete(getFiCcoinBalance(userCode));
    }
    //added for getFiCcoinBalancesByCrm
    public List getFiCcoinBalancesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from FiCcoinBalance fiCcoinBalance where 1=1";
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
    public FiCcoinBalance getFiCcoinBalanceForUpdate(final String userCode){
    	FiCcoinBalance fiCcoinBalance = (FiCcoinBalance) getHibernateTemplate().get(FiCcoinBalance.class, userCode, LockMode.UPGRADE);

        return fiCcoinBalance;
    }
}
