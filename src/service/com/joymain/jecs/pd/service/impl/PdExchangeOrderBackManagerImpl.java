
package com.joymain.jecs.pd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pd.model.PdExchangeOrderBack;
import com.joymain.jecs.pd.dao.PdExchangeOrderBackDao;
import com.joymain.jecs.pd.service.PdExchangeOrderBackManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class PdExchangeOrderBackManagerImpl extends BaseManager implements PdExchangeOrderBackManager {
    private PdExchangeOrderBackDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setPdExchangeOrderBackDao(PdExchangeOrderBackDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pd.service.PdExchangeOrderBackManager#getPdExchangeOrderBacks(com.joymain.jecs.pd.model.PdExchangeOrderBack)
     */
    public List getPdExchangeOrderBacks(final PdExchangeOrderBack pdExchangeOrderBack) {
        return dao.getPdExchangeOrderBacks(pdExchangeOrderBack);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdExchangeOrderBackManager#getPdExchangeOrderBack(String uniNo)
     */
    public PdExchangeOrderBack getPdExchangeOrderBack(final String uniNo) {
        return dao.getPdExchangeOrderBack(new Long(uniNo));
    }

    /**
     * @see com.joymain.jecs.pd.service.PdExchangeOrderBackManager#savePdExchangeOrderBack(PdExchangeOrderBack pdExchangeOrderBack)
     */
    public void savePdExchangeOrderBack(PdExchangeOrderBack pdExchangeOrderBack) {
        dao.savePdExchangeOrderBack(pdExchangeOrderBack);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdExchangeOrderBackManager#removePdExchangeOrderBack(String uniNo)
     */
    public void removePdExchangeOrderBack(final String uniNo) {
        dao.removePdExchangeOrderBack(new Long(uniNo));
    }
    //added for getPdExchangeOrderBacksByCrm
    public List getPdExchangeOrderBacksByCrm(CommonRecord crm, Pager pager){
	return dao.getPdExchangeOrderBacksByCrm(crm,pager);
    }
}
