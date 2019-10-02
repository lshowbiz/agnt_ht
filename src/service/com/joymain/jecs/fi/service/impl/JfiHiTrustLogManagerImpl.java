
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.JfiHiTrustLog;
import com.joymain.jecs.fi.dao.JfiHiTrustLogDao;
import com.joymain.jecs.fi.service.JfiHiTrustLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JfiHiTrustLogManagerImpl extends BaseManager implements JfiHiTrustLogManager {
    private JfiHiTrustLogDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJfiHiTrustLogDao(JfiHiTrustLogDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiHiTrustLogManager#getJfiHiTrustLogs(com.joymain.jecs.fi.model.JfiHiTrustLog)
     */
    public List getJfiHiTrustLogs(final JfiHiTrustLog jfiHiTrustLog) {
        return dao.getJfiHiTrustLogs(jfiHiTrustLog);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiHiTrustLogManager#getJfiHiTrustLog(String logId)
     */
    public JfiHiTrustLog getJfiHiTrustLog(final String logId) {
        return dao.getJfiHiTrustLog(new Long(logId));
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiHiTrustLogManager#saveJfiHiTrustLog(JfiHiTrustLog jfiHiTrustLog)
     */
    public void saveJfiHiTrustLog(JfiHiTrustLog jfiHiTrustLog) {
        dao.saveJfiHiTrustLog(jfiHiTrustLog);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiHiTrustLogManager#removeJfiHiTrustLog(String logId)
     */
    public void removeJfiHiTrustLog(final String logId) {
        dao.removeJfiHiTrustLog(new Long(logId));
    }
    //added for getJfiHiTrustLogsByCrm
    public List getJfiHiTrustLogsByCrm(CommonRecord crm, Pager pager){
	return dao.getJfiHiTrustLogsByCrm(crm,pager);
    }
}
