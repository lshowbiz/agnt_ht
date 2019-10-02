
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.FiInvoiceBalance;
import com.joymain.jecs.fi.dao.FiInvoiceBalanceDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class FiInvoiceBalanceDaoHibernate extends BaseDaoHibernate implements FiInvoiceBalanceDao {

    /**
     * @see com.joymain.jecs.fi.dao.FiInvoiceBalanceDao#getFiInvoiceBalances(com.joymain.jecs.fi.model.FiInvoiceBalance)
     */
    public List getFiInvoiceBalances(final FiInvoiceBalance fiInvoiceBalance) {
        return getHibernateTemplate().find("from FiInvoiceBalance");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (fiInvoiceBalance == null) {
            return getHibernateTemplate().find("from FiInvoiceBalance");
        } else {
            // filter on properties set in the fiInvoiceBalance
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(fiInvoiceBalance).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(FiInvoiceBalance.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiInvoiceBalanceDao#getFiInvoiceBalance(BigDecimal id)
     */
    public FiInvoiceBalance getFiInvoiceBalance(final BigDecimal id) {
        FiInvoiceBalance fiInvoiceBalance = (FiInvoiceBalance) getHibernateTemplate().get(FiInvoiceBalance.class, id);
        if (fiInvoiceBalance == null) {
            log.warn("uh oh, fiInvoiceBalance with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(FiInvoiceBalance.class, id);
        }

        return fiInvoiceBalance;
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiInvoiceBalanceDao#saveFiInvoiceBalance(FiInvoiceBalance fiInvoiceBalance)
     */    
    public void saveFiInvoiceBalance(final FiInvoiceBalance fiInvoiceBalance) {
        getHibernateTemplate().saveOrUpdate(fiInvoiceBalance);
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiInvoiceBalanceDao#removeFiInvoiceBalance(BigDecimal id)
     */
    public void removeFiInvoiceBalance(final BigDecimal id) {
        getHibernateTemplate().delete(getFiInvoiceBalance(id));
    }
    //added for getFiInvoiceBalancesByCrm
    public List getFiInvoiceBalancesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from FiInvoiceBalance fiInvoiceBalance where 1=1";
    	
    	String userCode = crm.getString("userCode", "");
	    if(!StringUtil.isEmpty(userCode)){
	    	   hql += " and fiInvoiceBalance.userCode = '"+userCode+"'";
	    }
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
    
    /**
     * 根据会员编号，获取会员的可用发票余额对象
     * @author fu 2015-11-30
     * @param userCode
     * @return fiInvoiceBalance
     */
	public FiInvoiceBalance getFiInvoiceBalanceByUserCode(String userCode){
		if(!StringUtil.isEmpty(userCode)){
		    String hql = "from FiInvoiceBalance fiInvoiceBalance where 1=1 and fiInvoiceBalance.userCode= '"+userCode+"'";
		    List<FiInvoiceBalance> list = this.findObjectsByHqlQuery(hql);
		    if(null!=list){
		    	if(list.size()>0){
		    		return list.get(0);
		    	}
		    }
		}
		return null;
	}
	
}
