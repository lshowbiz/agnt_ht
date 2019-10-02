
package com.joymain.jecs.pd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pd.model.PdShipStrategy;
import com.joymain.jecs.pd.dao.PdShipStrategyDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface PdShipStrategyManager extends Manager {
    /**
     * Retrieves all of the pdShipStrategys
     */
    public List getPdShipStrategys(PdShipStrategy pdShipStrategy);

    /**
     * Gets pdShipStrategy's information based on ssId.
     * @param ssId the pdShipStrategy's ssId
     * @return pdShipStrategy populated pdShipStrategy object
     */
    public PdShipStrategy getPdShipStrategy(final String ssId);

    /**
     * Saves a pdShipStrategy's information
     * @param pdShipStrategy the object to be saved
     */
    public void savePdShipStrategy(PdShipStrategy pdShipStrategy);

    /**
     * Removes a pdShipStrategy from the database by ssId
     * @param ssId the pdShipStrategy's ssId
     */
    public void removePdShipStrategy(final String ssId);
    //added for getPdShipStrategysByCrm
    public List getPdShipStrategysByCrm(CommonRecord crm, Pager pager);
}

