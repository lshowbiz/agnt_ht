
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.BillAccount;
import com.joymain.jecs.fi.dao.BillAccountDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class BillAccountDaoHibernate extends BaseDaoHibernate implements BillAccountDao {

    /**
     * @see com.joymain.jecs.fi.dao.BillAccountDao#getBillAccounts(com.joymain.jecs.fi.model.BillAccount)
     */
    public List getBillAccounts(final BillAccount billAccount) {
        return getHibernateTemplate().find("from BillAccount");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (billAccount == null) {
            return getHibernateTemplate().find("from BillAccount");
        } else {
            // filter on properties set in the billAccount
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(billAccount).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(BillAccount.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.BillAccountDao#getBillAccount(Long baId)
     */
    public BillAccount getBillAccount(final Long baId) {
        BillAccount billAccount = (BillAccount) getHibernateTemplate().get(BillAccount.class, baId);
        if (billAccount == null) {
            log.warn("uh oh, billAccount with baId '" + baId + "' not found...");
            throw new ObjectRetrievalFailureException(BillAccount.class, baId);
        }

        return billAccount;
    }

    /**
     * @see com.joymain.jecs.fi.dao.BillAccountDao#saveBillAccount(BillAccount billAccount)
     */    
    public void saveBillAccount(final BillAccount billAccount) {
        getHibernateTemplate().saveOrUpdate(billAccount);
    }

    /**
     * @see com.joymain.jecs.fi.dao.BillAccountDao#removeBillAccount(Long baId)
     */
    public void removeBillAccount(final Long baId) {
        getHibernateTemplate().delete(getBillAccount(baId));
    }
    //added for getBillAccountsByCrm
    public List getBillAccountsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from BillAccount billAccount where 1=1";
		String baCode = crm.getString("baCode", "");
		if(!StringUtil.isEmpty(baCode)){
			hql += " and baCode='"+baCode+"'";
		}
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by baId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	public List getBillAccountsByBaCode(String baCode) {
		return this.getHibernateTemplate().find(" from BillAccount where baCode='"+baCode+"'");
	}

	public BigDecimal getBilllAccountsPersent(Long baId) {
		String hql="select nvl(sum(persent),0) from BillAccount where 1=1";
		if(null!=baId){
			hql+=" and baId !="+baId;
		}
		return (BigDecimal) ((List)this.getHibernateTemplate().find(hql)).get(0);
	}
}
