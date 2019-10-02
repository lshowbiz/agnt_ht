
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.bd.dao.BdOutwardBankDao;
import com.joymain.jecs.bd.model.BdOutwardBank;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

public class BdOutwardBankDaoHibernate extends BaseDaoHibernate implements BdOutwardBankDao {

    /**
     * @see com.joymain.jecs.bd.dao.BdOutwardBankDao#getBdOutwardBanks(com.joymain.jecs.bd.model.BdOutwardBank)
     */
    public List getBdOutwardBanks(final BdOutwardBank bdOutwardBank) {
        return getHibernateTemplate().findByExample(bdOutwardBank);

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (bdOutwardBank == null) {
            return getHibernateTemplate().find("from BdOutwardBank");
        } else {
            // filter on properties set in the bdOutwardBank
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(bdOutwardBank).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(BdOutwardBank.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.BdOutwardBankDao#getBdOutwardBank(Long bankId)
     */
    public BdOutwardBank getBdOutwardBank(final Long bankId) {
        BdOutwardBank bdOutwardBank = (BdOutwardBank) getHibernateTemplate().get(BdOutwardBank.class, bankId);
        if (bdOutwardBank == null) {
            log.warn("uh oh, bdOutwardBank with bankId '" + bankId + "' not found...");
            throw new ObjectRetrievalFailureException(BdOutwardBank.class, bankId);
        }

        return bdOutwardBank;
    }

    /**
     * @see com.joymain.jecs.bd.dao.BdOutwardBankDao#saveBdOutwardBank(BdOutwardBank bdOutwardBank)
     */    
    public void saveBdOutwardBank(final BdOutwardBank bdOutwardBank) {
        getHibernateTemplate().saveOrUpdate(bdOutwardBank);
    }

    /**
     * @see com.joymain.jecs.bd.dao.BdOutwardBankDao#removeBdOutwardBank(Long bankId)
     */
    public void removeBdOutwardBank(final Long bankId) {
        getHibernateTemplate().delete(getBdOutwardBank(bankId));
    }
    //added for getBdOutwardBanksByCrm
    public List getBdOutwardBanksByCrm(CommonRecord crm, Pager pager){
    	String hql = "from BdOutwardBank bdOutwardBank where 1=1";
    	String bankCode=crm.getString("bankCode", "");
    	if(!StringUtil.isEmpty(bankCode)){
    		hql+=" and bdOutwardBank.bankCode='"+bankCode+"'";
    	}
    	
    	String companyCode=crm.getString("companyCode", "");
    	if(!StringUtil.isEmpty(companyCode)){
    		hql+=" and bdOutwardBank.companyCode='"+companyCode+"'";
    	}
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by bankId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
