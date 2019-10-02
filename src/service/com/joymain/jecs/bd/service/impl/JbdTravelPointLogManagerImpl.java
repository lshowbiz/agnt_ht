
package com.joymain.jecs.bd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.bd.model.JbdTravelPointLog;
import com.joymain.jecs.bd.dao.JbdTravelPointLogDao;
import com.joymain.jecs.bd.service.JbdTravelPointLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JbdTravelPointLogManagerImpl extends BaseManager implements JbdTravelPointLogManager {
    private JbdTravelPointLogDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdTravelPointLogDao(JbdTravelPointLogDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointLogManager#getJbdTravelPointLogs(com.joymain.jecs.bd.model.JbdTravelPointLog)
     */
    public List getJbdTravelPointLogs(final JbdTravelPointLog jbdTravelPointLog) {
        return dao.getJbdTravelPointLogs(jbdTravelPointLog);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointLogManager#getJbdTravelPointLog(String logId)
     */
    public JbdTravelPointLog getJbdTravelPointLog(final String logId) {
        return dao.getJbdTravelPointLog(new Long(logId));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointLogManager#saveJbdTravelPointLog(JbdTravelPointLog jbdTravelPointLog)
     */
    public void saveJbdTravelPointLog(JbdTravelPointLog jbdTravelPointLog) {
        dao.saveJbdTravelPointLog(jbdTravelPointLog);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointLogManager#removeJbdTravelPointLog(String logId)
     */
    public void removeJbdTravelPointLog(final String logId) {
        dao.removeJbdTravelPointLog(new Long(logId));
    }
    //added for getJbdTravelPointLogsByCrm
    public List getJbdTravelPointLogsByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdTravelPointLogsByCrm(crm,pager);
    }
}
