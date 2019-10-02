
package com.joymain.jecs.pd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pd.model.PdShipStrategyDetail;
import com.joymain.jecs.pd.dao.PdShipStrategyDetailDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface PdShipStrategyDetailManager extends Manager {
	
	public List findArea(CommonRecord crm);
    /**
     * Retrieves all of the pdShipStrategyDetails
     */
    public List getPdShipStrategyDetails(PdShipStrategyDetail pdShipStrategyDetail);

    /**
     * Gets pdShipStrategyDetail's information based on ssdId.
     * @param ssdId the pdShipStrategyDetail's ssdId
     * @return pdShipStrategyDetail populated pdShipStrategyDetail object
     */
    public PdShipStrategyDetail getPdShipStrategyDetail(final String ssdId);

    /**
     * Saves a pdShipStrategyDetail's information
     * @param pdShipStrategyDetail the object to be saved
     */
    public void savePdShipStrategyDetail(PdShipStrategyDetail pdShipStrategyDetail);

    /**
     * Removes a pdShipStrategyDetail from the database by ssdId
     * @param ssdId the pdShipStrategyDetail's ssdId
     */
    public void removePdShipStrategyDetail(final String ssdId);
    //added for getPdShipStrategyDetailsByCrm
    public List getPdShipStrategyDetailsByCrm(CommonRecord crm, Pager pager);
    public PdShipStrategyDetail getPdShipStrategyDetail(String ssId,
			String shipArea);
    
    /**
     * 批量审核发货策略
     * @param uniNoStr:编码字符串：用逗号隔开
     * @return
     */
    public int batchAuditPdShipNews(String uniNoStr,String status);
}

