
package com.joymain.jecs.pd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pd.model.PdWarehouseFrozenDetail;
import com.joymain.jecs.pd.dao.PdWarehouseFrozenDetailDao;
import com.joymain.jecs.pd.service.PdWarehouseFrozenDetailManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class PdWarehouseFrozenDetailManagerImpl extends BaseManager implements PdWarehouseFrozenDetailManager {
    private PdWarehouseFrozenDetailDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setPdWarehouseFrozenDetailDao(PdWarehouseFrozenDetailDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pd.service.PdWarehouseFrozenDetailManager#getPdWarehouseFrozenDetails(com.joymain.jecs.pd.model.PdWarehouseFrozenDetail)
     */
    public List getPdWarehouseFrozenDetails(final PdWarehouseFrozenDetail pdWarehouseFrozenDetail) {
        return dao.getPdWarehouseFrozenDetails(pdWarehouseFrozenDetail);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdWarehouseFrozenDetailManager#getPdWarehouseFrozenDetail(String uniNo)
     */
    public PdWarehouseFrozenDetail getPdWarehouseFrozenDetail(final String uniNo) {
        return dao.getPdWarehouseFrozenDetail(new Long(uniNo));
    }

    /**
     * @see com.joymain.jecs.pd.service.PdWarehouseFrozenDetailManager#savePdWarehouseFrozenDetail(PdWarehouseFrozenDetail pdWarehouseFrozenDetail)
     */
    public void savePdWarehouseFrozenDetail(PdWarehouseFrozenDetail pdWarehouseFrozenDetail) {
        dao.savePdWarehouseFrozenDetail(pdWarehouseFrozenDetail);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdWarehouseFrozenDetailManager#removePdWarehouseFrozenDetail(String uniNo)
     */
    public void removePdWarehouseFrozenDetail(final String uniNo) {
        dao.removePdWarehouseFrozenDetail(new Long(uniNo));
    }
    //added for getPdWarehouseFrozenDetailsByCrm
    public List getPdWarehouseFrozenDetailsByCrm(CommonRecord crm, Pager pager){
	return dao.getPdWarehouseFrozenDetailsByCrm(crm,pager);
    }
}
