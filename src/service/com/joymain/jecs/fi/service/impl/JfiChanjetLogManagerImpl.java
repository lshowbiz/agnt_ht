
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.JfiChanjetLog;
import com.joymain.jecs.fi.dao.JfiChanjetLogDao;
import com.joymain.jecs.fi.service.JfiChanjetLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JfiChanjetLogManagerImpl extends BaseManager implements JfiChanjetLogManager {
    private JfiChanjetLogDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJfiChanjetLogDao(JfiChanjetLogDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiChanjetLogManager#getJfiChanjetLogs(com.joymain.jecs.fi.model.JfiChanjetLog)
     */
    public List getJfiChanjetLogs(final JfiChanjetLog jfiChanjetLog) {
        return dao.getJfiChanjetLogs(jfiChanjetLog);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiChanjetLogManager#getJfiChanjetLog(String logId)
     */
    public JfiChanjetLog getJfiChanjetLog(final String logId) {
        return dao.getJfiChanjetLog(new Long(logId));
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiChanjetLogManager#saveJfiChanjetLog(JfiChanjetLog jfiChanjetLog)
     */
    public void saveJfiChanjetLog(JfiChanjetLog jfiChanjetLog) {
        dao.saveJfiChanjetLog(jfiChanjetLog);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiChanjetLogManager#removeJfiChanjetLog(String logId)
     */
    public void removeJfiChanjetLog(final String logId) {
        dao.removeJfiChanjetLog(new Long(logId));
    }
    //added for getJfiChanjetLogsByCrm
    public List getJfiChanjetLogsByCrm(CommonRecord crm, Pager pager){
	return dao.getJfiChanjetLogsByCrm(crm,pager);
    }

	@Override
	public List getJfiChanjetLogsByDealId(String dealId) {
		// TODO Auto-generated method stub
		return dao.getJfiChanjetLogsByDealId(dealId);
	}
}
