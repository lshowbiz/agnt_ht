
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;

import org.hibernate.LockMode;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.dao.FiBcoinBalanceDao;
import com.joymain.jecs.fi.model.FiBcoinBalance;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class FiBcoinBalanceDaoHibernate extends BaseDaoHibernate implements FiBcoinBalanceDao {

    /**
     * @see com.joymain.jecs.fi.dao.FiBcoinBalanceDao#getFiBcoinBalances(com.joymain.jecs.fi.model.FiBcoinBalance)
     */
    public List getFiBcoinBalances(final FiBcoinBalance fiBcoinBalance) {
        return getHibernateTemplate().find("from FiBcoinBalance");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (fiBcoinBalance == null) {
            return getHibernateTemplate().find("from FiBcoinBalance");
        } else {
            // filter on properties set in the fiBcoinBalance
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(fiBcoinBalance).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(FiBcoinBalance.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiBcoinBalanceDao#getFiBcoinBalance(String userCode)
     */
    public FiBcoinBalance getFiBcoinBalance(final String userCode) {
        FiBcoinBalance fiBcoinBalance = (FiBcoinBalance) getHibernateTemplate().get(FiBcoinBalance.class, userCode);
//        if (fiBcoinBalance == null) {
//            log.warn("uh oh, fiBcoinBalance with userCode '" + userCode + "' not found...");
//            throw new ObjectRetrievalFailureException(FiBcoinBalance.class, userCode);
//        }

        return fiBcoinBalance;
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiBcoinBalanceDao#saveFiBcoinBalance(FiBcoinBalance fiBcoinBalance)
     */    
    public void saveFiBcoinBalance(final FiBcoinBalance fiBcoinBalance) {
        getHibernateTemplate().saveOrUpdate(fiBcoinBalance);
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiBcoinBalanceDao#removeFiBcoinBalance(String userCode)
     */
    public void removeFiBcoinBalance(final String userCode) {
        getHibernateTemplate().delete(getFiBcoinBalance(userCode));
    }
    //added for getFiBcoinBalancesByCrm
    public List getFiBcoinBalancesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from FiBcoinBalance fiBcoinBalance where 1=1";
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
    public FiBcoinBalance getFiBcoinBalanceForUpdate(final String userCode){
    	FiBcoinBalance fiBcoinBalance = (FiBcoinBalance) getHibernateTemplate().get(FiBcoinBalance.class, userCode, LockMode.UPGRADE);

        return fiBcoinBalance;
    }
}
