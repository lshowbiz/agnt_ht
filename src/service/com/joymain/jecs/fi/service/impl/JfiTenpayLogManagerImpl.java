
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.JfiTenpayLog;
import com.joymain.jecs.fi.dao.JfiTenpayLogDao;
import com.joymain.jecs.fi.service.JfiTenpayLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JfiTenpayLogManagerImpl extends BaseManager implements JfiTenpayLogManager {
    private JfiTenpayLogDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJfiTenpayLogDao(JfiTenpayLogDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiTenpayLogManager#getJfiTenpayLogs(com.joymain.jecs.fi.model.JfiTenpayLog)
     */
    public List getJfiTenpayLogs(final JfiTenpayLog jfiTenpayLog) {
        return dao.getJfiTenpayLogs(jfiTenpayLog);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiTenpayLogManager#getJfiTenpayLog(String logId)
     */
    public JfiTenpayLog getJfiTenpayLog(final String logId) {
        return dao.getJfiTenpayLog(new Long(logId));
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiTenpayLogManager#saveJfiTenpayLog(JfiTenpayLog jfiTenpayLog)
     */
    public void saveJfiTenpayLog(JfiTenpayLog jfiTenpayLog) {
        dao.saveJfiTenpayLog(jfiTenpayLog);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiTenpayLogManager#removeJfiTenpayLog(String logId)
     */
    public void removeJfiTenpayLog(final String logId) {
        dao.removeJfiTenpayLog(new Long(logId));
    }
    //added for getJfiTenpayLogsByCrm
    public List getJfiTenpayLogsByCrm(CommonRecord crm, Pager pager){
	return dao.getJfiTenpayLogsByCrm(crm,pager);
    }
}
