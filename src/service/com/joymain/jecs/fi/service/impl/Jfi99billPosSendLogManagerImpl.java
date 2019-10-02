
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.Jfi99billPosSendLog;
import com.joymain.jecs.fi.dao.Jfi99billPosSendLogDao;
import com.joymain.jecs.fi.service.Jfi99billPosSendLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class Jfi99billPosSendLogManagerImpl extends BaseManager implements Jfi99billPosSendLogManager {
    private Jfi99billPosSendLogDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJfi99billPosSendLogDao(Jfi99billPosSendLogDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.Jfi99billPosSendLogManager#getJfi99billPosSendLogs(com.joymain.jecs.fi.model.Jfi99billPosSendLog)
     */
    public List getJfi99billPosSendLogs(final Jfi99billPosSendLog jfi99billPosSendLog) {
        return dao.getJfi99billPosSendLogs(jfi99billPosSendLog);
    }

    /**
     * @see com.joymain.jecs.fi.service.Jfi99billPosSendLogManager#getJfi99billPosSendLog(String logId)
     */
    public Jfi99billPosSendLog getJfi99billPosSendLog(final String logId) {
        return dao.getJfi99billPosSendLog(new Long(logId));
    }

    /**
     * @see com.joymain.jecs.fi.service.Jfi99billPosSendLogManager#saveJfi99billPosSendLog(Jfi99billPosSendLog jfi99billPosSendLog)
     */
    public void saveJfi99billPosSendLog(Jfi99billPosSendLog jfi99billPosSendLog) {
        dao.saveJfi99billPosSendLog(jfi99billPosSendLog);
    }

    /**
     * @see com.joymain.jecs.fi.service.Jfi99billPosSendLogManager#removeJfi99billPosSendLog(String logId)
     */
    public void removeJfi99billPosSendLog(final String logId) {
        dao.removeJfi99billPosSendLog(new Long(logId));
    }
    //added for getJfi99billPosSendLogsByCrm
    public List getJfi99billPosSendLogsByCrm(CommonRecord crm, Pager pager){
	return dao.getJfi99billPosSendLogsByCrm(crm,pager);
    }
}
