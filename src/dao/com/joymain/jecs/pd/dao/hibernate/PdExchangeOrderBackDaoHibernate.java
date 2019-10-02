
package com.joymain.jecs.pd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.model.PdExchangeOrderBack;
import com.joymain.jecs.pd.dao.PdExchangeOrderBackDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class PdExchangeOrderBackDaoHibernate extends BaseDaoHibernate implements PdExchangeOrderBackDao {

    /**
     * @see com.joymain.jecs.pd.dao.PdExchangeOrderBackDao#getPdExchangeOrderBacks(com.joymain.jecs.pd.model.PdExchangeOrderBack)
     */
    public List getPdExchangeOrderBacks(final PdExchangeOrderBack pdExchangeOrderBack) {
        return getHibernateTemplate().find("from PdExchangeOrderBack");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (pdExchangeOrderBack == null) {
            return getHibernateTemplate().find("from PdExchangeOrderBack");
        } else {
            // filter on properties set in the pdExchangeOrderBack
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(pdExchangeOrderBack).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(PdExchangeOrderBack.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdExchangeOrderBackDao#getPdExchangeOrderBack(Long uniNo)
     */
    public PdExchangeOrderBack getPdExchangeOrderBack(final Long uniNo) {
        PdExchangeOrderBack pdExchangeOrderBack = (PdExchangeOrderBack) getHibernateTemplate().get(PdExchangeOrderBack.class, uniNo);
        if (pdExchangeOrderBack == null) {
            log.warn("uh oh, pdExchangeOrderBack with uniNo '" + uniNo + "' not found...");
            throw new ObjectRetrievalFailureException(PdExchangeOrderBack.class, uniNo);
        }

        return pdExchangeOrderBack;
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdExchangeOrderBackDao#savePdExchangeOrderBack(PdExchangeOrderBack pdExchangeOrderBack)
     */    
    public void savePdExchangeOrderBack(final PdExchangeOrderBack pdExchangeOrderBack) {
        getHibernateTemplate().saveOrUpdate(pdExchangeOrderBack);
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdExchangeOrderBackDao#removePdExchangeOrderBack(Long uniNo)
     */
    public void removePdExchangeOrderBack(final Long uniNo) {
        getHibernateTemplate().delete(getPdExchangeOrderBack(uniNo));
    }
    //added for getPdExchangeOrderBacksByCrm
    public List getPdExchangeOrderBacksByCrm(CommonRecord crm, Pager pager){
    	String hql = "from PdExchangeOrderBack pdExchangeOrderBack where 1=1";
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
			hql += " order by uniNo desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
