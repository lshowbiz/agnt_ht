
package com.joymain.jecs.pd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.model.PdStatusExcStock;
import com.joymain.jecs.pd.dao.PdStatusExcStockDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class PdStatusExcStockDaoHibernate extends BaseDaoHibernate implements PdStatusExcStockDao {

    /**
     * @see com.joymain.jecs.pd.dao.PdStatusExcStockDao#getPdStatusExcStocks(com.joymain.jecs.pd.model.PdStatusExcStock)
     */
    public List getPdStatusExcStocks(final PdStatusExcStock pdStatusExcStock) {
        return getHibernateTemplate().find("from PdStatusExcStock");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (pdStatusExcStock == null) {
            return getHibernateTemplate().find("from PdStatusExcStock");
        } else {
            // filter on properties set in the pdStatusExcStock
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(pdStatusExcStock).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(PdStatusExcStock.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdStatusExcStockDao#getPdStatusExcStock(String seNo)
     */
    public PdStatusExcStock getPdStatusExcStock(final String seNo) {
        PdStatusExcStock pdStatusExcStock = (PdStatusExcStock) getHibernateTemplate().get(PdStatusExcStock.class, seNo);
        if (pdStatusExcStock == null) {
            log.warn("uh oh, pdStatusExcStock with seNo '" + seNo + "' not found...");
            throw new ObjectRetrievalFailureException(PdStatusExcStock.class, seNo);
        }

        return pdStatusExcStock;
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdStatusExcStockDao#savePdStatusExcStock(PdStatusExcStock pdStatusExcStock)
     */    
    public void savePdStatusExcStock(final PdStatusExcStock pdStatusExcStock) {
        getHibernateTemplate().saveOrUpdate(pdStatusExcStock);
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdStatusExcStockDao#removePdStatusExcStock(String seNo)
     */
    public void removePdStatusExcStock(final String seNo) {
        getHibernateTemplate().delete(getPdStatusExcStock(seNo));
    }
    //added for getPdStatusExcStocksByCrm
    public List getPdStatusExcStocksByCrm(CommonRecord crm, Pager pager){
    	String hql = "from PdStatusExcStock pdStatusExcStock where 1=1";
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
			hql += " order by seNo desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
