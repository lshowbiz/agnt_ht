
package com.joymain.jecs.pd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pd.model.PdCheckstockOrderDetail;
import com.joymain.jecs.pd.dao.PdCheckstockOrderDetailDao;
import com.joymain.jecs.pd.service.PdCheckstockOrderDetailManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class PdCheckstockOrderDetailManagerImpl extends BaseManager implements PdCheckstockOrderDetailManager {
    private PdCheckstockOrderDetailDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setPdCheckstockOrderDetailDao(PdCheckstockOrderDetailDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pd.service.PdCheckstockOrderDetailManager#getPdCheckstockOrderDetails(com.joymain.jecs.pd.model.PdCheckstockOrderDetail)
     */
    public List getPdCheckstockOrderDetails(final PdCheckstockOrderDetail pdCheckstockOrderDetail) {
        return dao.getPdCheckstockOrderDetails(pdCheckstockOrderDetail);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdCheckstockOrderDetailManager#getPdCheckstockOrderDetail(String uniNo)
     */
    public PdCheckstockOrderDetail getPdCheckstockOrderDetail(final String uniNo) {
        return dao.getPdCheckstockOrderDetail(new Long(uniNo));
    }

    /**
     * @see com.joymain.jecs.pd.service.PdCheckstockOrderDetailManager#savePdCheckstockOrderDetail(PdCheckstockOrderDetail pdCheckstockOrderDetail)
     */
    public void savePdCheckstockOrderDetail(PdCheckstockOrderDetail pdCheckstockOrderDetail) {
        dao.savePdCheckstockOrderDetail(pdCheckstockOrderDetail);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdCheckstockOrderDetailManager#removePdCheckstockOrderDetail(String uniNo)
     */
    public void removePdCheckstockOrderDetail(final String uniNo) {
        dao.removePdCheckstockOrderDetail(new Long(uniNo));
    }
    //added for getPdCheckstockOrderDetailsByCrm
    public List getPdCheckstockOrderDetailsByCrm(CommonRecord crm, Pager pager){
	return dao.getPdCheckstockOrderDetailsByCrm(crm,pager);
    }
}
