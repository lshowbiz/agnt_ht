
package com.joymain.jecs.pd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pd.model.PdShipStrategyMain;
import com.joymain.jecs.pd.dao.PdShipStrategyMainDao;
import com.joymain.jecs.pd.service.PdShipStrategyMainManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class PdShipStrategyMainManagerImpl extends BaseManager implements PdShipStrategyMainManager {
    private PdShipStrategyMainDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setPdShipStrategyMainDao(PdShipStrategyMainDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pd.service.PdShipStrategyMainManager#getPdShipStrategyMains(com.joymain.jecs.pd.model.PdShipStrategyMain)
     */
    public List getPdShipStrategyMains(final PdShipStrategyMain pdShipStrategyMain) {
        return dao.getPdShipStrategyMains(pdShipStrategyMain);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdShipStrategyMainManager#getPdShipStrategyMain(String valueId)
     */
    public PdShipStrategyMain getPdShipStrategyMain(final String valueId) {
        return dao.getPdShipStrategyMain(new Long(valueId));
    }

    /**
     * @see com.joymain.jecs.pd.service.PdShipStrategyMainManager#savePdShipStrategyMain(PdShipStrategyMain pdShipStrategyMain)
     */
    public void savePdShipStrategyMain(PdShipStrategyMain pdShipStrategyMain) {
        dao.savePdShipStrategyMain(pdShipStrategyMain);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdShipStrategyMainManager#removePdShipStrategyMain(String valueId)
     */
    public void removePdShipStrategyMain(final String valueId) {
        dao.removePdShipStrategyMain(new Long(valueId));
    }
    //added for getPdShipStrategyMainsByCrm
    public List getPdShipStrategyMainsByCrm(CommonRecord crm, Pager pager){
	return dao.getPdShipStrategyMainsByCrm(crm,pager);
    }
    
    /**
     * 判断主策略是否有相同优先级
     * @param pdShipStrategyMain
     * @return
     */
    public Integer getPriorityIsExists(PdShipStrategyMain pdShipStrategyMain){
    	return dao.getPriorityIsExists(pdShipStrategyMain);
    }
}
