
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.FiGetbillaccountLog;
import com.joymain.jecs.fi.dao.FiGetbillaccountLogDao;
import com.joymain.jecs.fi.service.FiGetbillaccountLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class FiGetbillaccountLogManagerImpl extends BaseManager implements FiGetbillaccountLogManager {
    private FiGetbillaccountLogDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setFiGetbillaccountLogDao(FiGetbillaccountLogDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.FiGetbillaccountLogManager#getFiGetbillaccountLogs(com.joymain.jecs.fi.model.FiGetbillaccountLog)
     */
    public List getFiGetbillaccountLogs(final FiGetbillaccountLog fiGetbillaccountLog) {
        return dao.getFiGetbillaccountLogs(fiGetbillaccountLog);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiGetbillaccountLogManager#getFiGetbillaccountLog(String logId)
     */
    public FiGetbillaccountLog getFiGetbillaccountLog(final String logId) {
        return dao.getFiGetbillaccountLog(new Long(logId));
    }

    /**
     * @see com.joymain.jecs.fi.service.FiGetbillaccountLogManager#saveFiGetbillaccountLog(FiGetbillaccountLog fiGetbillaccountLog)
     */
    public void saveFiGetbillaccountLog(FiGetbillaccountLog fiGetbillaccountLog) {
        dao.saveFiGetbillaccountLog(fiGetbillaccountLog);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiGetbillaccountLogManager#removeFiGetbillaccountLog(String logId)
     */
    public void removeFiGetbillaccountLog(final String logId) {
        dao.removeFiGetbillaccountLog(new Long(logId));
    }
    //added for getFiGetbillaccountLogsByCrm
    public List getFiGetbillaccountLogsByCrm(CommonRecord crm, Pager pager){
	return dao.getFiGetbillaccountLogsByCrm(crm,pager);
    }
}
