
package com.joymain.jecs.pd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.model.PdStatusExcStockDetail;
import com.joymain.jecs.pd.dao.PdStatusExcStockDetailDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class PdStatusExcStockDetailDaoHibernate extends BaseDaoHibernate implements PdStatusExcStockDetailDao {

    /**
     * @see com.joymain.jecs.pd.dao.PdStatusExcStockDetailDao#getPdStatusExcStockDetails(com.joymain.jecs.pd.model.PdStatusExcStockDetail)
     */
    public List getPdStatusExcStockDetails(final PdStatusExcStockDetail pdStatusExcStockDetail) {
        return getHibernateTemplate().find("from PdStatusExcStockDetail");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (pdStatusExcStockDetail == null) {
            return getHibernateTemplate().find("from PdStatusExcStockDetail");
        } else {
            // filter on properties set in the pdStatusExcStockDetail
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(pdStatusExcStockDetail).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(PdStatusExcStockDetail.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdStatusExcStockDetailDao#getPdStatusExcStockDetail(Long uniNo)
     */
    public PdStatusExcStockDetail getPdStatusExcStockDetail(final Long uniNo) {
        PdStatusExcStockDetail pdStatusExcStockDetail = (PdStatusExcStockDetail) getHibernateTemplate().get(PdStatusExcStockDetail.class, uniNo);
        if (pdStatusExcStockDetail == null) {
            log.warn("uh oh, pdStatusExcStockDetail with uniNo '" + uniNo + "' not found...");
            throw new ObjectRetrievalFailureException(PdStatusExcStockDetail.class, uniNo);
        }

        return pdStatusExcStockDetail;
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdStatusExcStockDetailDao#savePdStatusExcStockDetail(PdStatusExcStockDetail pdStatusExcStockDetail)
     */    
    public void savePdStatusExcStockDetail(final PdStatusExcStockDetail pdStatusExcStockDetail) {
        getHibernateTemplate().saveOrUpdate(pdStatusExcStockDetail);
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdStatusExcStockDetailDao#removePdStatusExcStockDetail(Long uniNo)
     */
    public void removePdStatusExcStockDetail(final Long uniNo) {
        getHibernateTemplate().delete(getPdStatusExcStockDetail(uniNo));
    }
    //added for getPdStatusExcStockDetailsByCrm
    public List getPdStatusExcStockDetailsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from PdStatusExcStockDetail pdStatusExcStockDetail where 1=1";
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
