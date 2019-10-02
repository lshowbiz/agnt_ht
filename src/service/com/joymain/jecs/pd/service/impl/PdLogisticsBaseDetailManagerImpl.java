
package com.joymain.jecs.pd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pd.model.PdLogisticsBaseDetail;
import com.joymain.jecs.pd.dao.PdLogisticsBaseDetailDao;
import com.joymain.jecs.pd.service.PdLogisticsBaseDetailManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class PdLogisticsBaseDetailManagerImpl extends BaseManager implements PdLogisticsBaseDetailManager {
    private PdLogisticsBaseDetailDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setPdLogisticsBaseDetailDao(PdLogisticsBaseDetailDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pd.service.PdLogisticsBaseDetailManager#getPdLogisticsBaseDetails(com.joymain.jecs.pd.model.PdLogisticsBaseDetail)
     */
    public List getPdLogisticsBaseDetails(final PdLogisticsBaseDetail pdLogisticsBaseDetail) {
        return dao.getPdLogisticsBaseDetails(pdLogisticsBaseDetail);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdLogisticsBaseDetailManager#getPdLogisticsBaseDetail(String detailId)
     */
    public PdLogisticsBaseDetail getPdLogisticsBaseDetail(final String detailId) {
        return dao.getPdLogisticsBaseDetail(new BigDecimal(detailId));
    }

    /**
     * @see com.joymain.jecs.pd.service.PdLogisticsBaseDetailManager#savePdLogisticsBaseDetail(PdLogisticsBaseDetail pdLogisticsBaseDetail)
     */
    public void savePdLogisticsBaseDetail(PdLogisticsBaseDetail pdLogisticsBaseDetail) {
        dao.savePdLogisticsBaseDetail(pdLogisticsBaseDetail);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdLogisticsBaseDetailManager#removePdLogisticsBaseDetail(String detailId)
     */
    public void removePdLogisticsBaseDetail(final String detailId) {
        dao.removePdLogisticsBaseDetail(new BigDecimal(detailId));
    }
    //added for getPdLogisticsBaseDetailsByCrm
    public List getPdLogisticsBaseDetailsByCrm(CommonRecord crm, Pager pager){
	return dao.getPdLogisticsBaseDetailsByCrm(crm,pager);
    }
}
