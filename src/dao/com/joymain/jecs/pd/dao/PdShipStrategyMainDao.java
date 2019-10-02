
package com.joymain.jecs.pd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdShipStrategyMain;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdShipStrategyMainDao extends Dao {

    /**
     * Retrieves all of the pdShipStrategyMains
     */
    public List getPdShipStrategyMains(PdShipStrategyMain pdShipStrategyMain);

    /**
     * Gets pdShipStrategyMain's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param valueId the pdShipStrategyMain's valueId
     * @return pdShipStrategyMain populated pdShipStrategyMain object
     */
    public PdShipStrategyMain getPdShipStrategyMain(final Long valueId);

    /**
     * Saves a pdShipStrategyMain's information
     * @param pdShipStrategyMain the object to be saved
     */    
    public void savePdShipStrategyMain(PdShipStrategyMain pdShipStrategyMain);

    /**
     * Removes a pdShipStrategyMain from the database by valueId
     * @param valueId the pdShipStrategyMain's valueId
     */
    public void removePdShipStrategyMain(final Long valueId);
    //added for getPdShipStrategyMainsByCrm
    public List getPdShipStrategyMainsByCrm(CommonRecord crm, Pager pager);
    
    /**
     * 判断主策略是否有相同优先级
     * @param pdShipStrategyMain
     * @return
     */
    public Integer getPriorityIsExists(PdShipStrategyMain pdShipStrategyMain);
}

