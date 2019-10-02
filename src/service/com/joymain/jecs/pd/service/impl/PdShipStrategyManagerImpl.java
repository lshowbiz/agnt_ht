
package com.joymain.jecs.pd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pd.model.PdShipStrategy;
import com.joymain.jecs.pd.dao.PdShipStrategyDao;
import com.joymain.jecs.pd.service.PdShipStrategyManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class PdShipStrategyManagerImpl extends BaseManager implements PdShipStrategyManager {
    private PdShipStrategyDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setPdShipStrategyDao(PdShipStrategyDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pd.service.PdShipStrategyManager#getPdShipStrategys(com.joymain.jecs.pd.model.PdShipStrategy)
     */
    public List getPdShipStrategys(final PdShipStrategy pdShipStrategy) {
        return dao.getPdShipStrategys(pdShipStrategy);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdShipStrategyManager#getPdShipStrategy(String ssId)
     */
    public PdShipStrategy getPdShipStrategy(final String ssId) {
        return dao.getPdShipStrategy(new Long(ssId));
    }

    /**
     * @see com.joymain.jecs.pd.service.PdShipStrategyManager#savePdShipStrategy(PdShipStrategy pdShipStrategy)
     */
    public void savePdShipStrategy(PdShipStrategy pdShipStrategy) {
        dao.savePdShipStrategy(pdShipStrategy);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdShipStrategyManager#removePdShipStrategy(String ssId)
     */
    public void removePdShipStrategy(final String ssId) {
        dao.removePdShipStrategy(new Long(ssId));
    }
    //added for getPdShipStrategysByCrm
    public List getPdShipStrategysByCrm(CommonRecord crm, Pager pager){
	return dao.getPdShipStrategysByCrm(crm,pager);
    }
}
