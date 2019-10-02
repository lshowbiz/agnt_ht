
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.JfiYeepayLog;
import com.joymain.jecs.fi.dao.JfiYeepayLogDao;
import com.joymain.jecs.fi.service.JfiYeepayLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JfiYeepayLogManagerImpl extends BaseManager implements JfiYeepayLogManager {
    private JfiYeepayLogDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJfiYeepayLogDao(JfiYeepayLogDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiYeepayLogManager#getJfiYeepayLogs(com.joymain.jecs.fi.model.JfiYeepayLog)
     */
    public List getJfiYeepayLogs(final JfiYeepayLog jfiYeepayLog) {
        return dao.getJfiYeepayLogs(jfiYeepayLog);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiYeepayLogManager#getJfiYeepayLog(String logId)
     */
    public JfiYeepayLog getJfiYeepayLog(final String logId) {
        return dao.getJfiYeepayLog(new Long(logId));
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiYeepayLogManager#saveJfiYeepayLog(JfiYeepayLog jfiYeepayLog)
     */
    public void saveJfiYeepayLog(JfiYeepayLog jfiYeepayLog) {
        dao.saveJfiYeepayLog(jfiYeepayLog);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiYeepayLogManager#removeJfiYeepayLog(String logId)
     */
    public void removeJfiYeepayLog(final String logId) {
        dao.removeJfiYeepayLog(new Long(logId));
    }
    //added for getJfiYeepayLogsByCrm
    public List getJfiYeepayLogsByCrm(CommonRecord crm, Pager pager){
	return dao.getJfiYeepayLogsByCrm(crm,pager);
    }

	@Override
	public List getJfiYeepayLogsByDealId(String dealId) {
		// TODO Auto-generated method stub
		return dao.getJfiChanjetLogsByDealId(dealId);
	}
}
