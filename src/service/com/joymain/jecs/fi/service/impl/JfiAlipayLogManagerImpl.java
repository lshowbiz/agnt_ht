
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.JfiAlipayLog;
import com.joymain.jecs.fi.dao.JfiAlipayLogDao;
import com.joymain.jecs.fi.service.JfiAlipayLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JfiAlipayLogManagerImpl extends BaseManager implements JfiAlipayLogManager {
    private JfiAlipayLogDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJfiAlipayLogDao(JfiAlipayLogDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiAlipayLogManager#getJfiAlipayLogs(com.joymain.jecs.fi.model.JfiAlipayLog)
     */
    public List getJfiAlipayLogs(final JfiAlipayLog jfiAlipayLog) {
        return dao.getJfiAlipayLogs(jfiAlipayLog);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiAlipayLogManager#getJfiAlipayLog(String logId)
     */
    public JfiAlipayLog getJfiAlipayLog(final String logId) {
        return dao.getJfiAlipayLog(new Long(logId));
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiAlipayLogManager#saveJfiAlipayLog(JfiAlipayLog jfiAlipayLog)
     */
    public void saveJfiAlipayLog(JfiAlipayLog jfiAlipayLog) {
        dao.saveJfiAlipayLog(jfiAlipayLog);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiAlipayLogManager#removeJfiAlipayLog(String logId)
     */
    public void removeJfiAlipayLog(final String logId) {
        dao.removeJfiAlipayLog(new Long(logId));
    }
    //added for getJfiAlipayLogsByCrm
    public List getJfiAlipayLogsByCrm(CommonRecord crm, Pager pager){
	return dao.getJfiAlipayLogsByCrm(crm,pager);
    }
}
