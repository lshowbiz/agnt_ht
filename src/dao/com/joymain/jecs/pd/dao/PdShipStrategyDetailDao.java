
package com.joymain.jecs.pd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdShipStrategyDetail;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdShipStrategyDetailDao extends Dao {
	
	public List findArea(CommonRecord crm);

    /**
     * Retrieves all of the pdShipStrategyDetails
     */
    public List getPdShipStrategyDetails(PdShipStrategyDetail pdShipStrategyDetail);

    /**
     * Gets pdShipStrategyDetail's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param ssdId the pdShipStrategyDetail's ssdId
     * @return pdShipStrategyDetail populated pdShipStrategyDetail object
     */
    public PdShipStrategyDetail getPdShipStrategyDetail(final Long ssdId);

    /**
     * Saves a pdShipStrategyDetail's information
     * @param pdShipStrategyDetail the object to be saved
     */    
    public void savePdShipStrategyDetail(PdShipStrategyDetail pdShipStrategyDetail);

    /**
     * Removes a pdShipStrategyDetail from the database by ssdId
     * @param ssdId the pdShipStrategyDetail's ssdId
     */
    public void removePdShipStrategyDetail(final Long ssdId);
    
    public PdShipStrategyDetail getPdShipStrategyDetail(String ssId,String ssdId);
    //added for getPdShipStrategyDetailsByCrm
    public List getPdShipStrategyDetailsByCrm(CommonRecord crm, Pager pager);
    
    /**
     * 批量审核发货策略
     * @param uniNoStr:编码字符串：用逗号隔开
     * @return
     */
    public int batchAuditPdShipNews(String uniNoStr,String status);
}

