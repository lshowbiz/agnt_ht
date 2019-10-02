
package com.joymain.jecs.pd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pd.model.PdLogisticsBaseNum;
import com.joymain.jecs.pd.dao.PdLogisticsBaseNumDao;
import com.joymain.jecs.pd.service.PdLogisticsBaseNumManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class PdLogisticsBaseNumManagerImpl extends BaseManager implements PdLogisticsBaseNumManager {
    private PdLogisticsBaseNumDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setPdLogisticsBaseNumDao(PdLogisticsBaseNumDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pd.service.PdLogisticsBaseNumManager#getPdLogisticsBaseNums(com.joymain.jecs.pd.model.PdLogisticsBaseNum)
     */
    public List getPdLogisticsBaseNums(final PdLogisticsBaseNum pdLogisticsBaseNum) {
        return dao.getPdLogisticsBaseNums(pdLogisticsBaseNum);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdLogisticsBaseNumManager#getPdLogisticsBaseNum(String numId)
     */
    public PdLogisticsBaseNum getPdLogisticsBaseNum(final String numId) {
        return dao.getPdLogisticsBaseNum(new BigDecimal(numId));
    }

    /**
     * @see com.joymain.jecs.pd.service.PdLogisticsBaseNumManager#savePdLogisticsBaseNum(PdLogisticsBaseNum pdLogisticsBaseNum)
     */
    public void savePdLogisticsBaseNum(PdLogisticsBaseNum pdLogisticsBaseNum) {
        dao.savePdLogisticsBaseNum(pdLogisticsBaseNum);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdLogisticsBaseNumManager#removePdLogisticsBaseNum(String numId)
     */
    public void removePdLogisticsBaseNum(final String numId) {
        dao.removePdLogisticsBaseNum(new BigDecimal(numId));
    }
    //added for getPdLogisticsBaseNumsByCrm
    public List getPdLogisticsBaseNumsByCrm(CommonRecord crm, Pager pager){
	return dao.getPdLogisticsBaseNumsByCrm(crm,pager);
    }
    
    /**
	 * 获取未完整获取物流单号信息的PdLogisticsBaseNum-定时器用到
	 * @author gw 2016-02-16
	 * @return
	 */
	public List<PdLogisticsBaseNum> getPdLogisticsBaseNumForPdMailReceipt(){
		return dao.getPdLogisticsBaseNumForPdMailReceipt();
	}
	
	/**
	 * 物流查询定时器用到---获取DO相关的信息
	 * @author fu 2016-02-16
	 * @return list
	 */
	public List getDoForSql(){
		return dao.getDoForSql();
	}
}
