
package com.joymain.jecs.pd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pd.model.PdStatusExcStock;
import com.joymain.jecs.pd.dao.PdStatusExcStockDao;
import com.joymain.jecs.pd.service.PdStatusExcStockManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class PdStatusExcStockManagerImpl extends BaseManager implements PdStatusExcStockManager {
    private PdStatusExcStockDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setPdStatusExcStockDao(PdStatusExcStockDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pd.service.PdStatusExcStockManager#getPdStatusExcStocks(com.joymain.jecs.pd.model.PdStatusExcStock)
     */
    public List getPdStatusExcStocks(final PdStatusExcStock pdStatusExcStock) {
        return dao.getPdStatusExcStocks(pdStatusExcStock);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdStatusExcStockManager#getPdStatusExcStock(String seNo)
     */
    public PdStatusExcStock getPdStatusExcStock(final String seNo) {
        return dao.getPdStatusExcStock(new String(seNo));
    }

    /**
     * @see com.joymain.jecs.pd.service.PdStatusExcStockManager#savePdStatusExcStock(PdStatusExcStock pdStatusExcStock)
     */
    public void savePdStatusExcStock(PdStatusExcStock pdStatusExcStock) {
        dao.savePdStatusExcStock(pdStatusExcStock);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdStatusExcStockManager#removePdStatusExcStock(String seNo)
     */
    public void removePdStatusExcStock(final String seNo) {
        dao.removePdStatusExcStock(new String(seNo));
    }
    //added for getPdStatusExcStocksByCrm
    public List getPdStatusExcStocksByCrm(CommonRecord crm, Pager pager){
	return dao.getPdStatusExcStocksByCrm(crm,pager);
    }
}
