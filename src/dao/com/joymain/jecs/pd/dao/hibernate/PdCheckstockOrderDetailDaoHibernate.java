
package com.joymain.jecs.pd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.model.PdCheckstockOrderDetail;
import com.joymain.jecs.pd.dao.PdCheckstockOrderDetailDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class PdCheckstockOrderDetailDaoHibernate extends BaseDaoHibernate implements PdCheckstockOrderDetailDao {

    /**
     * @see com.joymain.jecs.pd.dao.PdCheckstockOrderDetailDao#getPdCheckstockOrderDetails(com.joymain.jecs.pd.model.PdCheckstockOrderDetail)
     */
    public List getPdCheckstockOrderDetails(final PdCheckstockOrderDetail pdCheckstockOrderDetail) {
        return getHibernateTemplate().find("from PdCheckstockOrderDetail");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (pdCheckstockOrderDetail == null) {
            return getHibernateTemplate().find("from PdCheckstockOrderDetail");
        } else {
            // filter on properties set in the pdCheckstockOrderDetail
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(pdCheckstockOrderDetail).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(PdCheckstockOrderDetail.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdCheckstockOrderDetailDao#getPdCheckstockOrderDetail(Long uniNo)
     */
    public PdCheckstockOrderDetail getPdCheckstockOrderDetail(final Long uniNo) {
        PdCheckstockOrderDetail pdCheckstockOrderDetail = (PdCheckstockOrderDetail) getHibernateTemplate().get(PdCheckstockOrderDetail.class, uniNo);
        if (pdCheckstockOrderDetail == null) {
            log.warn("uh oh, pdCheckstockOrderDetail with uniNo '" + uniNo + "' not found...");
            throw new ObjectRetrievalFailureException(PdCheckstockOrderDetail.class, uniNo);
        }

        return pdCheckstockOrderDetail;
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdCheckstockOrderDetailDao#savePdCheckstockOrderDetail(PdCheckstockOrderDetail pdCheckstockOrderDetail)
     */    
    public void savePdCheckstockOrderDetail(final PdCheckstockOrderDetail pdCheckstockOrderDetail) {
        getHibernateTemplate().saveOrUpdate(pdCheckstockOrderDetail);
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdCheckstockOrderDetailDao#removePdCheckstockOrderDetail(Long uniNo)
     */
    public void removePdCheckstockOrderDetail(final Long uniNo) {
        getHibernateTemplate().delete(getPdCheckstockOrderDetail(uniNo));
    }
    //added for getPdCheckstockOrderDetailsByCrm
    public List getPdCheckstockOrderDetailsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from PdCheckstockOrderDetail pdCheckstockOrderDetail where 1=1";
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
