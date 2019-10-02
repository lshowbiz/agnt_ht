
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.Jfi99billmsLog;
import com.joymain.jecs.fi.dao.Jfi99billmsLogDao;
import com.joymain.jecs.fi.service.Jfi99billmsLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class Jfi99billmsLogManagerImpl extends BaseManager implements Jfi99billmsLogManager {
    private Jfi99billmsLogDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJfi99billmsLogDao(Jfi99billmsLogDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.Jfi99billmsLogManager#getJfi99billmsLogs(com.joymain.jecs.fi.model.Jfi99billmsLog)
     */
    public List getJfi99billmsLogs(final Jfi99billmsLog jfi99billmsLog) {
        return dao.getJfi99billmsLogs(jfi99billmsLog);
    }

    /**
     * @see com.joymain.jecs.fi.service.Jfi99billmsLogManager#getJfi99billmsLog(String logId)
     */
    public Jfi99billmsLog getJfi99billmsLog(final String logId) {
        return dao.getJfi99billmsLog(new Long(logId));
    }

    /**
     * @see com.joymain.jecs.fi.service.Jfi99billmsLogManager#saveJfi99billmsLog(Jfi99billmsLog jfi99billmsLog)
     */
    public void saveJfi99billmsLog(Jfi99billmsLog jfi99billmsLog) {
        dao.saveJfi99billmsLog(jfi99billmsLog);
    }

    /**
     * @see com.joymain.jecs.fi.service.Jfi99billmsLogManager#removeJfi99billmsLog(String logId)
     */
    public void removeJfi99billmsLog(final String logId) {
        dao.removeJfi99billmsLog(new Long(logId));
    }
    //added for getJfi99billmsLogsByCrm
    public List getJfi99billmsLogsByCrm(CommonRecord crm, Pager pager){
	return dao.getJfi99billmsLogsByCrm(crm,pager);
    }
}
