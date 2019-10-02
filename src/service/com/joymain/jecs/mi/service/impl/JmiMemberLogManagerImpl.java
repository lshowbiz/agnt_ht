
package com.joymain.jecs.mi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.mi.model.JmiMemberLog;
import com.joymain.jecs.mi.dao.JmiMemberLogDao;
import com.joymain.jecs.mi.service.JmiMemberLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JmiMemberLogManagerImpl extends BaseManager implements JmiMemberLogManager {
    private JmiMemberLogDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJmiMemberLogDao(JmiMemberLogDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiMemberLogManager#getJmiMemberLogs(com.joymain.jecs.mi.model.JmiMemberLog)
     */
    public List getJmiMemberLogs(final JmiMemberLog jmiMemberLog) {
        return dao.getJmiMemberLogs(jmiMemberLog);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiMemberLogManager#getJmiMemberLog(String logId)
     */
    public JmiMemberLog getJmiMemberLog(final String logId) {
        return dao.getJmiMemberLog(new String(logId));
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiMemberLogManager#saveJmiMemberLog(JmiMemberLog jmiMemberLog)
     */
    public void saveJmiMemberLog(JmiMemberLog jmiMemberLog) {
        dao.saveJmiMemberLog(jmiMemberLog);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiMemberLogManager#removeJmiMemberLog(String logId)
     */
    public void removeJmiMemberLog(final String logId) {
        dao.removeJmiMemberLog(new String(logId));
    }
    //added for getJmiMemberLogsByCrm
    public List getJmiMemberLogsByCrm(CommonRecord crm, Pager pager){
	return dao.getJmiMemberLogsByCrm(crm,pager);
    }
}
