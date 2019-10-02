
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.FiBcoinPayconfigDetail;
import com.joymain.jecs.fi.dao.FiBcoinPayconfigDetailDao;
import com.joymain.jecs.fi.service.FiBcoinPayconfigDetailManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class FiBcoinPayconfigDetailManagerImpl extends BaseManager implements FiBcoinPayconfigDetailManager {
    private FiBcoinPayconfigDetailDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setFiBcoinPayconfigDetailDao(FiBcoinPayconfigDetailDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.FiBcoinPayconfigDetailManager#getFiBcoinPayconfigDetails(com.joymain.jecs.fi.model.FiBcoinPayconfigDetail)
     */
    public List getFiBcoinPayconfigDetails(final FiBcoinPayconfigDetail fiBcoinPayconfigDetail) {
        return dao.getFiBcoinPayconfigDetails(fiBcoinPayconfigDetail);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiBcoinPayconfigDetailManager#getFiBcoinPayconfigDetail(String detailId)
     */
    public FiBcoinPayconfigDetail getFiBcoinPayconfigDetail(final String detailId) {
        return dao.getFiBcoinPayconfigDetail(new Long(detailId));
    }

    /**
     * @see com.joymain.jecs.fi.service.FiBcoinPayconfigDetailManager#saveFiBcoinPayconfigDetail(FiBcoinPayconfigDetail fiBcoinPayconfigDetail)
     */
    public void saveFiBcoinPayconfigDetail(FiBcoinPayconfigDetail fiBcoinPayconfigDetail) {
        dao.saveFiBcoinPayconfigDetail(fiBcoinPayconfigDetail);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiBcoinPayconfigDetailManager#removeFiBcoinPayconfigDetail(String detailId)
     */
    public void removeFiBcoinPayconfigDetail(final String detailId) {
        dao.removeFiBcoinPayconfigDetail(new Long(detailId));
    }
    //added for getFiBcoinPayconfigDetailsByCrm
    public List getFiBcoinPayconfigDetailsByCrm(CommonRecord crm, Pager pager){
	return dao.getFiBcoinPayconfigDetailsByCrm(crm,pager);
    }

	@Override
	public List getFiBcoinPayconfigDetailsByConfigId(String configId) {
		
		return dao.getFiBcoinPayconfigDetailsByConfigId(configId);
	}

	/**
     * 查询换购明细
     * @param configId
     * @param productNo
     * @return
     */
	@Override
	public FiBcoinPayconfigDetail getFiBcoinPayconfigDetailsByProductNo(String configId, String productNo) {
		// TODO Auto-generated method stub
		return dao.getFiBcoinPayconfigDetailsByProductNo(configId, productNo);
	}

	@Override
	public void saveFiBcoinPayconfigDetails(
			List<FiBcoinPayconfigDetail> detailTemps) {
		// TODO Auto-generated method stub
		dao.saveFiBcoinPayconfigDetails(detailTemps);
	}
}
