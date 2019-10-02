
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.FiChannelLog;
import com.joymain.jecs.fi.dao.FiChannelLogDao;
import com.joymain.jecs.fi.service.FiChannelLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class FiChannelLogManagerImpl extends BaseManager implements FiChannelLogManager {
    private FiChannelLogDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setFiChannelLogDao(FiChannelLogDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.FiChannelLogManager#getFiChannelLogs(com.joymain.jecs.fi.model.FiChannelLog)
     */
    public List getFiChannelLogs(final FiChannelLog fiChannelLog) {
        return dao.getFiChannelLogs(fiChannelLog);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiChannelLogManager#getFiChannelLog(String logId)
     */
    public FiChannelLog getFiChannelLog(final String logId) {
        return dao.getFiChannelLog(new Long(logId));
    }

    /**
     * @see com.joymain.jecs.fi.service.FiChannelLogManager#saveFiChannelLog(FiChannelLog jFiChannelLog)
     */
    public void saveFiChannelLog(FiChannelLog fiChannelLog) {
        dao.saveFiChannelLog(fiChannelLog);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiChannelLogManager#removeFiChannelLog(String logId)
     */
    public void removeFiChannelLog(final String logId) {
        dao.removeFiChannelLog(new Long(logId));
    }
    //added for getFiChannelLogsByCrm
    public List getFiChannelLogsByCrm(CommonRecord crm, Pager pager){
	return dao.getFiChannelLogsByCrm(crm,pager);
    }
}
