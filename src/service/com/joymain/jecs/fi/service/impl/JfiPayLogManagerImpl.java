
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.JfiPayLog;
import com.joymain.jecs.fi.dao.JfiPayLogDao;
import com.joymain.jecs.fi.service.JfiPayLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JfiPayLogManagerImpl extends BaseManager implements JfiPayLogManager {
    private JfiPayLogDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJfiPayLogDao(JfiPayLogDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiPayLogManager#getJfiPayLogs(com.joymain.jecs.fi.model.JfiPayLog)
     */
    public List getJfiPayLogs(final JfiPayLog jfiPayLog) {
        return dao.getJfiPayLogs(jfiPayLog);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiPayLogManager#getJfiPayLog(String logId)
     */
    public JfiPayLog getJfiPayLog(final String logId) {
        return dao.getJfiPayLog(new Long(logId));
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiPayLogManager#saveJfiPayLog(JfiPayLog jfiPayLog)
     */
    public void saveJfiPayLog(JfiPayLog jfiPayLog) {
        dao.saveJfiPayLog(jfiPayLog);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiPayLogManager#removeJfiPayLog(String logId)
     */
    public void removeJfiPayLog(final String logId) {
        dao.removeJfiPayLog(new Long(logId));
    }
    //added for getJfiPayLogsByCrm
    public List getJfiPayLogsByCrm(CommonRecord crm, Pager pager){
	return dao.getJfiPayLogsByCrm(crm,pager);
    }
}
