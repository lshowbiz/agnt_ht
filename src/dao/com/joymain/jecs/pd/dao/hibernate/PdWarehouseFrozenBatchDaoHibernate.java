
package com.joymain.jecs.pd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.model.PdWarehouseFrozenBatch;
import com.joymain.jecs.pd.dao.PdWarehouseFrozenBatchDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class PdWarehouseFrozenBatchDaoHibernate extends BaseDaoHibernate implements PdWarehouseFrozenBatchDao {

    /**
     * @see com.joymain.jecs.pd.dao.PdWarehouseFrozenBatchDao#getPdWarehouseFrozenBatchs(com.joymain.jecs.pd.model.PdWarehouseFrozenBatch)
     */
    public List getPdWarehouseFrozenBatchs(final PdWarehouseFrozenBatch pdWarehouseFrozenBatch) {
        return getHibernateTemplate().find("from PdWarehouseFrozenBatch");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (pdWarehouseFrozenBatch == null) {
            return getHibernateTemplate().find("from PdWarehouseFrozenBatch");
        } else {
            // filter on properties set in the pdWarehouseFrozenBatch
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(pdWarehouseFrozenBatch).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(PdWarehouseFrozenBatch.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdWarehouseFrozenBatchDao#getPdWarehouseFrozenBatch(Long batchId)
     */
    public PdWarehouseFrozenBatch getPdWarehouseFrozenBatch(final Long batchId) {
        PdWarehouseFrozenBatch pdWarehouseFrozenBatch = (PdWarehouseFrozenBatch) getHibernateTemplate().get(PdWarehouseFrozenBatch.class, batchId);
        if (pdWarehouseFrozenBatch == null) {
            log.warn("uh oh, pdWarehouseFrozenBatch with batchId '" + batchId + "' not found...");
            throw new ObjectRetrievalFailureException(PdWarehouseFrozenBatch.class, batchId);
        }

        return pdWarehouseFrozenBatch;
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdWarehouseFrozenBatchDao#savePdWarehouseFrozenBatch(PdWarehouseFrozenBatch pdWarehouseFrozenBatch)
     */    
    public void savePdWarehouseFrozenBatch(final PdWarehouseFrozenBatch pdWarehouseFrozenBatch) {
        getHibernateTemplate().saveOrUpdate(pdWarehouseFrozenBatch);
    }

    /**
     * @see com.joymain.jecs.pd.dao.PdWarehouseFrozenBatchDao#removePdWarehouseFrozenBatch(Long batchId)
     */
    public void removePdWarehouseFrozenBatch(final Long batchId) {
        getHibernateTemplate().delete(getPdWarehouseFrozenBatch(batchId));
    }
    //added for getPdWarehouseFrozenBatchsByCrm
    public List getPdWarehouseFrozenBatchsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from PdWarehouseFrozenBatch pdWarehouseFrozenBatch where 1=1";
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
			hql += " order by batchId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	/* (non-Javadoc)
	 * @see com.joymain.jecs.pd.dao.PdWarehouseFrozenBatchDao#addFrozenDetail(java.lang.String)
	 */
    public void addFrozenDetail(Long batchId) {
		String sql = "insert into PD_WAREHOUSE_frozen_detail(UNI_NO,Batch_Id,Company_Code,Warehouse_No,Product_No,Normal_Qty,Damage_Qty,Unknown_Qty)select seq_pd.nextval,"
				+ batchId
				+ ",p.company_code,p.warehouse_no,p.product_no,p.normal_qty,p.damage_qty,p.unknown_qty from pd_warehouse_stock p";
		this.getJdbcTemplate().execute(sql);
	}
    
}
