
package com.joymain.jecs.pd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pd.model.PdStatusExcStockDetail;
import com.joymain.jecs.pd.dao.PdStatusExcStockDetailDao;
import com.joymain.jecs.pd.service.PdStatusExcStockDetailManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class PdStatusExcStockDetailManagerImpl extends BaseManager implements PdStatusExcStockDetailManager {
    private PdStatusExcStockDetailDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setPdStatusExcStockDetailDao(PdStatusExcStockDetailDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pd.service.PdStatusExcStockDetailManager#getPdStatusExcStockDetails(com.joymain.jecs.pd.model.PdStatusExcStockDetail)
     */
    public List getPdStatusExcStockDetails(final PdStatusExcStockDetail pdStatusExcStockDetail) {
        return dao.getPdStatusExcStockDetails(pdStatusExcStockDetail);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdStatusExcStockDetailManager#getPdStatusExcStockDetail(String uniNo)
     */
    public PdStatusExcStockDetail getPdStatusExcStockDetail(final String uniNo) {
        return dao.getPdStatusExcStockDetail(new Long(uniNo));
    }

    /**
     * @see com.joymain.jecs.pd.service.PdStatusExcStockDetailManager#savePdStatusExcStockDetail(PdStatusExcStockDetail pdStatusExcStockDetail)
     */
    public void savePdStatusExcStockDetail(PdStatusExcStockDetail pdStatusExcStockDetail) {
        dao.savePdStatusExcStockDetail(pdStatusExcStockDetail);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdStatusExcStockDetailManager#removePdStatusExcStockDetail(String uniNo)
     */
    public void removePdStatusExcStockDetail(final String uniNo) {
        dao.removePdStatusExcStockDetail(new Long(uniNo));
    }
    //added for getPdStatusExcStockDetailsByCrm
    public List getPdStatusExcStockDetailsByCrm(CommonRecord crm, Pager pager){
	return dao.getPdStatusExcStockDetailsByCrm(crm,pager);
    }
}
