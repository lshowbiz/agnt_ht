
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.FiFundbookTemp;
import com.joymain.jecs.fi.dao.FiFundbookTempDao;
import com.joymain.jecs.fi.service.FiFundbookTempManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class FiFundbookTempManagerImpl extends BaseManager implements FiFundbookTempManager {
    private FiFundbookTempDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setFiFundbookTempDao(FiFundbookTempDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.FiFundbookTempManager#getFiFundbookTemps(com.joymain.jecs.fi.model.FiFundbookTemp)
     */
    public List getFiFundbookTemps(final FiFundbookTemp fiFundbookTemp) {
        return dao.getFiFundbookTemps(fiFundbookTemp);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiFundbookTempManager#getFiFundbookTemp(String tempId)
     */
    public FiFundbookTemp getFiFundbookTemp(final String tempId) {
        return dao.getFiFundbookTemp(new Long(tempId));
    }

    /**
     * @see com.joymain.jecs.fi.service.FiFundbookTempManager#saveFiFundbookTemp(FiFundbookTemp fiFundbookTemp)
     */
    public void saveFiFundbookTemp(FiFundbookTemp fiFundbookTemp) {
        dao.saveFiFundbookTemp(fiFundbookTemp);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiFundbookTempManager#removeFiFundbookTemp(String tempId)
     */
    public void removeFiFundbookTemp(final String tempId) {
        dao.removeFiFundbookTemp(new Long(tempId));
    }
    //added for getFiFundbookTempsByCrm
    public List getFiFundbookTempsByCrm(CommonRecord crm, Pager pager){
    	return dao.getFiFundbookTempsByCrm(crm,pager);
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
	 * 批量保存多条记录
	 * @param fiBankbookTemps
	 */
	public void saveFiFundbookTemps(List fiFundbookTemps) {
		dao.saveFiFundbookTemps(fiFundbookTemps);
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
}
