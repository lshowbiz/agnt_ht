
package com.joymain.jecs.pd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pd.model.PdShipStrategyMain;
import com.joymain.jecs.pd.dao.PdShipStrategyMainDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface PdShipStrategyMainManager extends Manager {
    /**
     * Retrieves all of the pdShipStrategyMains
     */
    public List getPdShipStrategyMains(PdShipStrategyMain pdShipStrategyMain);

    /**
     * Gets pdShipStrategyMain's information based on valueId.
     * @param valueId the pdShipStrategyMain's valueId
     * @return pdShipStrategyMain populated pdShipStrategyMain object
     */
    public PdShipStrategyMain getPdShipStrategyMain(final String valueId);

    /**
     * Saves a pdShipStrategyMain's information
     * @param pdShipStrategyMain the object to be saved
     */
    public void savePdShipStrategyMain(PdShipStrategyMain pdShipStrategyMain);

    /**
     * Removes a pdShipStrategyMain from the database by valueId
     * @param valueId the pdShipStrategyMain's valueId
     */
    public void removePdShipStrategyMain(final String valueId);
    //added for getPdShipStrategyMainsByCrm
    public List getPdShipStrategyMainsByCrm(CommonRecord crm, Pager pager);
    
    /**
     * 判断主策略是否有相同优先级
     * @param pdShipStrategyMain
     * @return
     */
    public Integer getPriorityIsExists(PdShipStrategyMain pdShipStrategyMain);
}

