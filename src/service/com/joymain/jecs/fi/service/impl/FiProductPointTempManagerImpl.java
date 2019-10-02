
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.util.Map;

import com.joymain.jecs.fi.dao.FiProductPointTempDao;
import com.joymain.jecs.fi.model.FiProductPointTemp;
import com.joymain.jecs.fi.service.FiProductPointTempManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class FiProductPointTempManagerImpl extends BaseManager implements FiProductPointTempManager {
    private FiProductPointTempDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setFiProductPointTempDao(FiProductPointTempDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.sp.spms.fi.service.FiProductPointTempManager#getFiProductPointTemps(com.sp.spms.fi.model.FiProductPointTemp)
     */
    public List getFiProductPointTemps(final FiProductPointTemp fiProductPointTemp) {
        return dao.getFiProductPointTemps(fiProductPointTemp);
    }

    /**
     * @see com.sp.spms.fi.service.FiProductPointTempManager#getFiProductPointTemp(String tempId)
     */
    public FiProductPointTemp getFiProductPointTemp(final String tempId) {
        return dao.getFiProductPointTemp(new Long(tempId));
    }

    /**
     * @see com.sp.spms.fi.service.FiProductPointTempManager#saveFiProductPointTemp(FiProductPointTemp fiProductPointTemp)
     */
    public void saveFiProductPointTemp(FiProductPointTemp fiProductPointTemp) {
        dao.saveFiProductPointTemp(fiProductPointTemp);
    }

    /**
     * @see com.sp.spms.fi.service.FiProductPointTempManager#removeFiProductPointTemp(String tempId)
     */
    public void removeFiProductPointTemp(final String tempId) {
        dao.removeFiProductPointTemp(new Long(tempId));
    }
    //added for getFiProductPointTempsByCrm
    public List getFiProductPointTempsByCrm(CommonRecord crm, Pager pager){
	return dao.getFiProductPointTempsByCrm(crm,pager);
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
	public long getCountByDate(final String companyCode, final String userCode, final String ProductPointType){
		return dao.getCountByDate(companyCode, userCode, ProductPointType);
	}

	/**
	 * 批量保存多条记录
	 * @param fiProductPointTemps
	 */
	public void saveFiProductPointTemps(List fiProductPointTemps) {
		dao.saveFiProductPointTemps(fiProductPointTemps);
	}
}
