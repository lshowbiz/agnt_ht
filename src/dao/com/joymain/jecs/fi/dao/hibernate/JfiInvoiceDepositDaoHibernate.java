
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.JfiInvoiceDeposit;
import com.joymain.jecs.fi.dao.JfiInvoiceDepositDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JfiInvoiceDepositDaoHibernate extends BaseDaoHibernate implements JfiInvoiceDepositDao {

    /**
     * @see com.joymain.jecs.fi.dao.JfiInvoiceDepositDao#getJfiInvoiceDeposits(com.joymain.jecs.fi.model.JfiInvoiceDeposit)
     */
    public List getJfiInvoiceDeposits(final JfiInvoiceDeposit jfiInvoiceDeposit) {
        return getHibernateTemplate().find("from JfiInvoiceDeposit");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jfiInvoiceDeposit == null) {
            return getHibernateTemplate().find("from JfiInvoiceDeposit");
        } else {
            // filter on properties set in the jfiInvoiceDeposit
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jfiInvoiceDeposit).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JfiInvoiceDeposit.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiInvoiceDepositDao#getJfiInvoiceDeposit(BigDecimal id)
     */
    public JfiInvoiceDeposit getJfiInvoiceDeposit(final Long id) {
        JfiInvoiceDeposit jfiInvoiceDeposit = (JfiInvoiceDeposit) getHibernateTemplate().get(JfiInvoiceDeposit.class, id);
        if (jfiInvoiceDeposit == null) {
            log.warn("uh oh, jfiInvoiceDeposit with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JfiInvoiceDeposit.class, id);
        }

        return jfiInvoiceDeposit;
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiInvoiceDepositDao#saveJfiInvoiceDeposit(JfiInvoiceDeposit jfiInvoiceDeposit)
     */    
    public void saveJfiInvoiceDeposit(final JfiInvoiceDeposit jfiInvoiceDeposit) {
        getHibernateTemplate().saveOrUpdate(jfiInvoiceDeposit);
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiInvoiceDepositDao#removeJfiInvoiceDeposit(BigDecimal id)
     */
    public void removeJfiInvoiceDeposit(final Long id) {
        getHibernateTemplate().delete(getJfiInvoiceDeposit(id));
    }
    //added for getJfiInvoiceDepositsByCrm
    public List getJfiInvoiceDepositsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JfiInvoiceDeposit jfiInvoiceDeposit where 1=1";
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
			hql += " order by id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
