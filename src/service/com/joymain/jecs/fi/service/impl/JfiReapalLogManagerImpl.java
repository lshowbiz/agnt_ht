
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.JfiReapalLog;
import com.joymain.jecs.fi.dao.JfiReapalLogDao;
import com.joymain.jecs.fi.service.JfiReapalLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JfiReapalLogManagerImpl extends BaseManager implements JfiReapalLogManager {
    private JfiReapalLogDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJfiReapalLogDao(JfiReapalLogDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiReapalLogManager#getJfiReapalLogs(com.joymain.jecs.fi.model.JfiReapalLog)
     */
    public List getJfiReapalLogs(final JfiReapalLog jfiReapalLog) {
        return dao.getJfiReapalLogs(jfiReapalLog);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiReapalLogManager#getJfiReapalLog(String logId)
     */
    public JfiReapalLog getJfiReapalLog(final String logId) {
        return dao.getJfiReapalLog(new Long(logId));
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiReapalLogManager#saveJfiReapalLog(JfiReapalLog jfiReapalLog)
     */
    public void saveJfiReapalLog(JfiReapalLog jfiReapalLog) {
        dao.saveJfiReapalLog(jfiReapalLog);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiReapalLogManager#removeJfiReapalLog(String logId)
     */
    public void removeJfiReapalLog(final String logId) {
        dao.removeJfiReapalLog(new Long(logId));
    }
    //added for getJfiReapalLogsByCrm
    public List getJfiReapalLogsByCrm(CommonRecord crm, Pager pager){
	return dao.getJfiReapalLogsByCrm(crm,pager);
    }
    
    /**
     * Modify By WuCF 20150923 判断
     * @param dealId
     * @return
     */
    public List getJfiReapalLogsByDealId(String dealId){
    	return dao.getJfiReapalLogsByDealId(dealId);
    }
}
