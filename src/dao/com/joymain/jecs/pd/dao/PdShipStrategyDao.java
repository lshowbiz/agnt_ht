
package com.joymain.jecs.pd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdShipStrategy;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdShipStrategyDao extends Dao {

    /**
     * Retrieves all of the pdShipStrategys
     */
    public List getPdShipStrategys(PdShipStrategy pdShipStrategy);

    /**
     * Gets pdShipStrategy's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param ssId the pdShipStrategy's ssId
     * @return pdShipStrategy populated pdShipStrategy object
     */
    public PdShipStrategy getPdShipStrategy(final Long ssId);

    /**
     * Saves a pdShipStrategy's information
     * @param pdShipStrategy the object to be saved
     */    
    public void savePdShipStrategy(PdShipStrategy pdShipStrategy);

    /**
     * Removes a pdShipStrategy from the database by ssId
     * @param ssId the pdShipStrategy's ssId
     */
    public void removePdShipStrategy(final Long ssId);
    //added for getPdShipStrategysByCrm
    public List getPdShipStrategysByCrm(CommonRecord crm, Pager pager);
}

