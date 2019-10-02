
package com.joymain.jecs.pd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.model.PdWarehouseFrozenDetail;
import com.joymain.jecs.pd.dao.PdWarehouseFrozenDetailDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class PdWarehouseFrozenDetailDaoHibernate extends BaseDaoHibernate implements PdWarehouseFrozenDetailDao {

    /**
     * @see com.joymain.jecs.pd.dao.PdWarehouseFrozenDetailDao#getPdWarehouseFrozenDetails(com.joymain.jecs.pd.model.PdWarehouseFrozenDetail)
     */
    public List getPdWarehouseFrozenDetails(final PdWarehouseFrozenDetail pdWarehouseFrozenDetail) {
        return getHibernateTemplate().find("from PdWarehouseFrozenDetail");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (pdWarehouseFrozenDetail == null) {
            return getHibernateTemplate().find("from PdWarehouseFrozenDetail");
        } else {
            // filter on properties set in the pdWarehouseFrozenDetail
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(pdWarehouseFrozenDetail).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(PdWarehouseFrozenDetail.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdWarehouseFrozenDetailDao#getPdWarehouseFrozenDetail(Long uniNo)
     */
    public PdWarehouseFrozenDetail getPdWarehouseFrozenDetail(final Long uniNo) {
        PdWarehouseFrozenDetail pdWarehouseFrozenDetail = (PdWarehouseFrozenDetail) getHibernateTemplate().get(PdWarehouseFrozenDetail.class, uniNo);
        if (pdWarehouseFrozenDetail == null) {
            log.warn("uh oh, pdWarehouseFrozenDetail with uniNo '" + uniNo + "' not found...");
            throw new ObjectRetrievalFailureException(PdWarehouseFrozenDetail.class, uniNo);
        }

        return pdWarehouseFrozenDetail;
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdWarehouseFrozenDetailDao#savePdWarehouseFrozenDetail(PdWarehouseFrozenDetail pdWarehouseFrozenDetail)
     */    
    public void savePdWarehouseFrozenDetail(final PdWarehouseFrozenDetail pdWarehouseFrozenDetail) {
        getHibernateTemplate().saveOrUpdate(pdWarehouseFrozenDetail);
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdWarehouseFrozenDetailDao#removePdWarehouseFrozenDetail(Long uniNo)
     */
    public void removePdWarehouseFrozenDetail(final Long uniNo) {
        getHibernateTemplate().delete(getPdWarehouseFrozenDetail(uniNo));
    }
    //added for getPdWarehouseFrozenDetailsByCrm
    public List getPdWarehouseFrozenDetailsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from PdWarehouseFrozenDetail pdWarehouseFrozenDetail where 1=1";
    	/** 
	---example---
	String userCode = crm.getString("userCode", "");
	if(StringUtils.isNotEmpty(userCode)){
		hql += "???????????";
	}
	 ***/
    	String batchId = crm.getString("batchId", "");
    	if(StringUtils.isNotEmpty(batchId)){
    		hql += " and pdWarehouseFrozenDetail.batchId = " + batchId;
    	}
    	
    	String warehouseNo = crm.getString("warehouseNo", "");
    	if(StringUtils.isNotEmpty(warehouseNo.trim())){
    		hql += " and pdWarehouseFrozenDetail.warehouseNo like '%" + warehouseNo + "%'";
    	}
    	
    	String productNo = crm.getString("productNo", "");
    	if(StringUtils.isNotEmpty(productNo.trim())){
    		hql += " and pdWarehouseFrozenDetail.productNo like '%" + productNo + "%'";
    	}
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by pdWarehouseFrozenDetail.warehouseNo desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
