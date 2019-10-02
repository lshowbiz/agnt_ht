
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.FiFundbookBalance;
import com.joymain.jecs.fi.model.JfiDepositBalance;
import com.joymain.jecs.fi.dao.JfiDepositBalanceDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.hibernate.LockMode;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JfiDepositBalanceDaoHibernate extends BaseDaoHibernate implements JfiDepositBalanceDao {

    /**
     * @see com.joymain.jecs.fi.dao.JfiDepositBalanceDao#getJfiDepositBalances(com.joymain.jecs.fi.model.JfiDepositBalance)
     */
    public List getJfiDepositBalances(final JfiDepositBalance jfiDepositBalance) {
        return getHibernateTemplate().find("from JfiDepositBalance");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jfiDepositBalance == null) {
            return getHibernateTemplate().find("from JfiDepositBalance");
        } else {
            // filter on properties set in the jfiDepositBalance
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jfiDepositBalance).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JfiDepositBalance.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiDepositBalanceDao#getJfiDepositBalance(BigDecimal fdbId)
     */
    public JfiDepositBalance getJfiDepositBalance(final Long fdbId) {
        JfiDepositBalance jfiDepositBalance = (JfiDepositBalance) getHibernateTemplate().get(JfiDepositBalance.class, fdbId);
        if (jfiDepositBalance == null) {
            log.warn("uh oh, jfiDepositBalance with fdbId '" + fdbId + "' not found...");
            throw new ObjectRetrievalFailureException(JfiDepositBalance.class, fdbId);
        }

        return jfiDepositBalance;
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiDepositBalanceDao#saveJfiDepositBalance(JfiDepositBalance jfiDepositBalance)
     */    
    public void saveJfiDepositBalance(final JfiDepositBalance jfiDepositBalance) {
        getHibernateTemplate().saveOrUpdate(jfiDepositBalance);
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiDepositBalanceDao#removeJfiDepositBalance(BigDecimal fdbId)
     */
    public void removeJfiDepositBalance(final Long fdbId) {
        getHibernateTemplate().delete(getJfiDepositBalance(fdbId));
    }
    //added for getJfiDepositBalancesByCrm
    public List getJfiDepositBalancesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JfiDepositBalance jfiDepositBalance where 1=1";
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
			hql += " order by fdbId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
    
  public JfiDepositBalance getJfiDepositBalanceByUserCodeAndDepositType(final String userCode, final String depositType){
    	
    	return (JfiDepositBalance)this.getObjectByHqlQuery("from JfiDepositBalance where userCode='" + userCode + "' and depositType='" + depositType + "'");
    }
  
  public JfiDepositBalance getJfiDepositBalanceForUpdate(Long fdbId){

	  JfiDepositBalance jfiDepositBalance = (JfiDepositBalance) getHibernateTemplate().get(JfiDepositBalance.class, fdbId, LockMode.UPGRADE);

      return jfiDepositBalance;
  }
}
