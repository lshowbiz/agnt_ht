
package com.joymain.jecs.pd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pd.model.PdShipStrategyDetail;
import com.joymain.jecs.pd.dao.PdShipStrategyDetailDao;
import com.joymain.jecs.pd.service.PdShipStrategyDetailManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class PdShipStrategyDetailManagerImpl extends BaseManager implements PdShipStrategyDetailManager {
    private PdShipStrategyDetailDao dao;
    
    public List findArea(CommonRecord crm) {
    	return dao.findArea(crm);
    }

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setPdShipStrategyDetailDao(PdShipStrategyDetailDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pd.service.PdShipStrategyDetailManager#getPdShipStrategyDetails(com.joymain.jecs.pd.model.PdShipStrategyDetail)
     */
    public List getPdShipStrategyDetails(final PdShipStrategyDetail pdShipStrategyDetail) {
        return dao.getPdShipStrategyDetails(pdShipStrategyDetail);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdShipStrategyDetailManager#getPdShipStrategyDetail(String ssdId)
     */
    public PdShipStrategyDetail getPdShipStrategyDetail(final String ssdId) {
        return dao.getPdShipStrategyDetail(new Long(ssdId));
    }

    /**
     * @see com.joymain.jecs.pd.service.PdShipStrategyDetailManager#savePdShipStrategyDetail(PdShipStrategyDetail pdShipStrategyDetail)
     */
    public void savePdShipStrategyDetail(PdShipStrategyDetail pdShipStrategyDetail) {
        dao.savePdShipStrategyDetail(pdShipStrategyDetail);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdShipStrategyDetailManager#removePdShipStrategyDetail(String ssdId)
     */
    public void removePdShipStrategyDetail(final String ssdId) {
        dao.removePdShipStrategyDetail(new Long(ssdId));
    }
    //added for getPdShipStrategyDetailsByCrm
    public List getPdShipStrategyDetailsByCrm(CommonRecord crm, Pager pager){
	return dao.getPdShipStrategyDetailsByCrm(crm,pager);
    }

	/* (non-Javadoc)
	 * @see com.joymain.jecs.pd.service.PdShipStrategyDetailManager#getPdShipStrategyDetail(java.lang.String, java.lang.String)
	 */
	public PdShipStrategyDetail getPdShipStrategyDetail(String ssId,
			String shipArea) {
		// TODO Auto-generated method stub
		return dao.getPdShipStrategyDetail(ssId, shipArea);
	}
    
	/**
     * 批量审核发货策略
     * @param uniNoStr:编码字符串：用逗号隔开
     * @return
     */
    public int batchAuditPdShipNews(String uniNoStr,String status){
    	return dao.batchAuditPdShipNews(uniNoStr,status);
    }
}
