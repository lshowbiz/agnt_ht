
package com.joymain.jecs.pd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.model.PdCheckstockOrder;
import com.joymain.jecs.pd.dao.PdCheckstockOrderDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class PdCheckstockOrderDaoHibernate extends BaseDaoHibernate implements PdCheckstockOrderDao {

    /**
     * @see com.joymain.jecs.pd.dao.PdCheckstockOrderDao#getPdCheckstockOrders(com.joymain.jecs.pd.model.PdCheckstockOrder)
     */
    public List getPdCheckstockOrders(final PdCheckstockOrder pdCheckstockOrder) {
        return getHibernateTemplate().find("from PdCheckstockOrder");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (pdCheckstockOrder == null) {
            return getHibernateTemplate().find("from PdCheckstockOrder");
        } else {
            // filter on properties set in the pdCheckstockOrder
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(pdCheckstockOrder).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(PdCheckstockOrder.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdCheckstockOrderDao#getPdCheckstockOrder(String pcoNo)
     */
    public PdCheckstockOrder getPdCheckstockOrder(final String pcoNo) {
        PdCheckstockOrder pdCheckstockOrder = (PdCheckstockOrder) getHibernateTemplate().get(PdCheckstockOrder.class, pcoNo);
        if (pdCheckstockOrder == null) {
            log.warn("uh oh, pdCheckstockOrder with pcoNo '" + pcoNo + "' not found...");
            throw new ObjectRetrievalFailureException(PdCheckstockOrder.class, pcoNo);
        }

        return pdCheckstockOrder;
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdCheckstockOrderDao#savePdCheckstockOrder(PdCheckstockOrder pdCheckstockOrder)
     */    
    public void savePdCheckstockOrder(final PdCheckstockOrder pdCheckstockOrder) {
        getHibernateTemplate().saveOrUpdate(pdCheckstockOrder);
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdCheckstockOrderDao#removePdCheckstockOrder(String pcoNo)
     */
    public void removePdCheckstockOrder(final String pcoNo) {
        getHibernateTemplate().delete(getPdCheckstockOrder(pcoNo));
    }
    //added for getPdCheckstockOrdersByCrm
    public List getPdCheckstockOrdersByCrm(CommonRecord crm, Pager pager){
    	String hql = "from PdCheckstockOrder pdCheckstockOrder where 1=1";
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
			hql += " order by pcoNo desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
