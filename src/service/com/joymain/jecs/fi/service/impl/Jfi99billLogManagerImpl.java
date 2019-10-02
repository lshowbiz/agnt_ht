
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.Jfi99billLog;
import com.joymain.jecs.fi.dao.Jfi99billLogDao;
import com.joymain.jecs.fi.service.Jfi99billLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.mpos.CerEncode;
public class Jfi99billLogManagerImpl extends BaseManager implements Jfi99billLogManager {
    private Jfi99billLogDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJfi99billLogDao(Jfi99billLogDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.Jfi99billLogManager#getJfi99billLogs(com.joymain.jecs.fi.model.Jfi99billLog)
     */
    public List getJfi99billLogs(final Jfi99billLog jfi99billLog) {
        return dao.getJfi99billLogs(jfi99billLog);
    }

    /**
     * @see com.joymain.jecs.fi.service.Jfi99billLogManager#getJfi99billLog(String logId)
     */
    public Jfi99billLog getJfi99billLog(final String logId) {
        return dao.getJfi99billLog(new Long(logId));
    }

    /**
     * @see com.joymain.jecs.fi.service.Jfi99billLogManager#saveJfi99billLog(Jfi99billLog jfi99billLog)
     */
    public void saveJfi99billLog(Jfi99billLog jfi99billLog) {
        dao.saveJfi99billLog(jfi99billLog);
    }

    /**
     * @see com.joymain.jecs.fi.service.Jfi99billLogManager#removeJfi99billLog(String logId)
     */
    public void removeJfi99billLog(final String logId) {
        dao.removeJfi99billLog(new Long(logId));
    }
    //added for getJfi99billLogsByCrm
    public List getJfi99billLogsByCrm(CommonRecord crm, Pager pager){
	return dao.getJfi99billLogsByCrm(crm,pager);
    }

	@Override
	public List getSuccessJfi99billLogsByConditions(String dealId) {
		// TODO Auto-generated method stub
		return dao.getSuccessJfi99billLogsByConditions(dealId);
	}
}
