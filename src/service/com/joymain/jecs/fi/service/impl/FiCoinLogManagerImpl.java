
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.FiCoinLog;
import com.joymain.jecs.fi.dao.FiCoinLogDao;
import com.joymain.jecs.fi.service.FiCoinLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class FiCoinLogManagerImpl extends BaseManager implements FiCoinLogManager {
    private FiCoinLogDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setFiCoinLogDao(FiCoinLogDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.FiCoinLogManager#getFiCoinLogs(com.joymain.jecs.fi.model.FiCoinLog)
     */
    public List getFiCoinLogs(final FiCoinLog fiCoinLog) {
        return dao.getFiCoinLogs(fiCoinLog);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiCoinLogManager#getFiCoinLog(String fclId)
     */
    public FiCoinLog getFiCoinLog(final String fclId) {
        return dao.getFiCoinLog(new Long(fclId));
    }

    /**
     * @see com.joymain.jecs.fi.service.FiCoinLogManager#saveFiCoinLog(FiCoinLog fiCoinLog)
     */
    public void saveFiCoinLog(FiCoinLog fiCoinLog) {
        dao.saveFiCoinLog(fiCoinLog);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiCoinLogManager#removeFiCoinLog(String fclId)
     */
    public void removeFiCoinLog(final String fclId) {
        dao.removeFiCoinLog(new Long(fclId));
    }
    //added for getFiCoinLogsByCrm
    public List getFiCoinLogsByCrm(CommonRecord crm, Pager pager){
	return dao.getFiCoinLogsByCrm(crm,pager);
    }

    /**
     * 获取赠送的所有积分
     * @param userCode
     * @param coinType
     * @return
     */
    public BigDecimal getFiCoinLogAmtByUserCode(final String userCode, final String coinType){
    	return dao.getFiCoinLogAmtByUserCode(userCode,coinType);
    }
}
