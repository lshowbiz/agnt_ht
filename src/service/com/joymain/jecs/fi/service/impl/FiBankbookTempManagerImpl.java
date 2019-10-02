
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.FiBankbookTemp;
import com.joymain.jecs.fi.dao.FiBankbookTempDao;
import com.joymain.jecs.fi.service.FiBankbookTempManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class FiBankbookTempManagerImpl extends BaseManager implements FiBankbookTempManager {
    private FiBankbookTempDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setFiBankbookTempDao(FiBankbookTempDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.sp.spms.fi.service.FiBankbookTempManager#getFiBankbookTemps(com.sp.spms.fi.model.FiBankbookTemp)
     */
    public List getFiBankbookTemps(final FiBankbookTemp fiBankbookTemp) {
        return dao.getFiBankbookTemps(fiBankbookTemp);
    }

    /**
     * @see com.sp.spms.fi.service.FiBankbookTempManager#getFiBankbookTemp(String tempId)
     */
    public FiBankbookTemp getFiBankbookTemp(final String tempId) {
        return dao.getFiBankbookTemp(new Long(tempId));
    }

    /**
     * @see com.sp.spms.fi.service.FiBankbookTempManager#saveFiBankbookTemp(FiBankbookTemp fiBankbookTemp)
     */
    public void saveFiBankbookTemp(FiBankbookTemp fiBankbookTemp) {
        dao.saveFiBankbookTemp(fiBankbookTemp);
    }

    /**
     * @see com.sp.spms.fi.service.FiBankbookTempManager#removeFiBankbookTemp(String tempId)
     */
    public void removeFiBankbookTemp(final String tempId) {
        dao.removeFiBankbookTemp(new Long(tempId));
    }
    //added for getFiBankbookTempsByCrm
    public List getFiBankbookTempsByCrm(CommonRecord crm, Pager pager){
	return dao.getFiBankbookTempsByCrm(crm,pager);
    }
    
	/**
	 * 获取收支统计金额 incTotal:收入  expTotal:支出
	 * @param crm
	 * @return
	 */
	public Map getIncExpStatMap(CommonRecord crm){
		return dao.getIncExpStatMap(crm);
	}

	/**
	 * 获取某用户的存折条数
	 * @param companyCode
	 * @param agentNo
	 * @return
	 */
	public long getCountByDate(final String companyCode, final String userCode, final String bankbookType){
		return dao.getCountByDate(companyCode, userCode, bankbookType);
	}

	/**
	 * 批量保存多条记录
	 * @param fiBankbookTemps
	 */
	public void saveFiBankbookTemps(List fiBankbookTemps) {
		dao.saveFiBankbookTemps(fiBankbookTemps);
	}

	@Override
	public List getFiProductPointTempsByCrm(CommonRecord crm, Pager pager) {
		return dao.getFiProductPointTempsByCrm(crm, pager);
	}

	@Override
	public Map getPpIncExpStatMap(CommonRecord crm) {
		return dao.getPpIncExpStatMap(crm);
	}
}
