
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.FiPayAccount;
import com.joymain.jecs.fi.dao.FiPayAccountDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class FiPayAccountDaoHibernate extends BaseDaoHibernate implements FiPayAccountDao {

    /**
     * @see com.joymain.jecs.fi.dao.FiPayAccountDao#getFiPayAccounts(com.joymain.jecs.fi.model.FiPayAccount)
     */
    public List getFiPayAccounts(final FiPayAccount fiPayAccount) {
        return getHibernateTemplate().find("from FiPayAccount");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (fiPayAccount == null) {
            return getHibernateTemplate().find("from FiPayAccount");
        } else {
            // filter on properties set in the fiPayAccount
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(fiPayAccount).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(FiPayAccount.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiPayAccountDao#getFiPayAccount(Long accountId)
     */
    public FiPayAccount getFiPayAccount(final Long accountId) {
        FiPayAccount fiPayAccount = (FiPayAccount) getHibernateTemplate().get(FiPayAccount.class, accountId);
        if (fiPayAccount == null) {
            log.warn("uh oh, fiPayAccount with accountId '" + accountId + "' not found...");
            throw new ObjectRetrievalFailureException(FiPayAccount.class, accountId);
        }

        return fiPayAccount;
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiPayAccountDao#saveFiPayAccount(FiPayAccount fiPayAccount)
     */    
    public void saveFiPayAccount(final FiPayAccount fiPayAccount) {
        getHibernateTemplate().saveOrUpdate(fiPayAccount);
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiPayAccountDao#removeFiPayAccount(Long accountId)
     */
    public void removeFiPayAccount(final Long accountId) {
        getHibernateTemplate().delete(getFiPayAccount(accountId));
    }
    //added for getFiPayAccountsByCrm
    public List getFiPayAccountsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from FiPayAccount fiPayAccount where 1=1";
  
		String accountCode = crm.getString("accountCode", "");
		if(StringUtils.isNotEmpty(accountCode)){
			hql += " and accountCode='"+accountCode+"'";
		}
		
		String providerType = crm.getString("providerType", "");
		if(StringUtils.isNotEmpty(providerType)){
			hql += " and providerType='"+providerType+"'";
		}
		
		String accountType = crm.getString("accountType", "");
		if(StringUtils.isNotEmpty(accountType)){
			hql += " and accountType='"+accountType+"'";
		}
		
		String isDefault = crm.getString("isDefault", "");
		if(StringUtils.isNotEmpty(isDefault)){
			hql += " and isDefault='"+isDefault+"'";
		}
		
		String status = crm.getString("status", "");
		if(StringUtils.isNotEmpty(status)){
			hql += " and status='"+status+"'";
		}
	
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by providerType,accountId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

    /**
     * 查询默认商户号
     * @param accountId
     * @return
     */
	@Override
	public List getDefaultAccounts(Long accountId) {
		
		String hql = "";
			
		if(accountId == null)
			hql = "from FiPayAccount fiPayAccount where isDefault='1'";
		else
			hql = "from FiPayAccount fiPayAccount where isDefault='1' and accountId!="+accountId;
		
		return this.findObjectsByHqlQuery(hql);
	}
}
