
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.JfiCreditCardLog;
import com.joymain.jecs.fi.dao.JfiCreditCardLogDao;
import com.joymain.jecs.fi.service.JfiCreditCardLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JfiCreditCardLogManagerImpl extends BaseManager implements JfiCreditCardLogManager {
    private JfiCreditCardLogDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJfiCreditCardLogDao(JfiCreditCardLogDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiCreditCardLogManager#getJfiCreditCardLogs(com.joymain.jecs.fi.model.JfiCreditCardLog)
     */
    public List getJfiCreditCardLogs(final JfiCreditCardLog jfiCreditCardLog) {
        return dao.getJfiCreditCardLogs(jfiCreditCardLog);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiCreditCardLogManager#getJfiCreditCardLog(String logId)
     */
    public JfiCreditCardLog getJfiCreditCardLog(final String logId) {
        return dao.getJfiCreditCardLog(new Long(logId));
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiCreditCardLogManager#saveJfiCreditCardLog(JfiCreditCardLog jfiCreditCardLog)
     */
    public void saveJfiCreditCardLog(JfiCreditCardLog jfiCreditCardLog) {
        dao.saveJfiCreditCardLog(jfiCreditCardLog);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiCreditCardLogManager#removeJfiCreditCardLog(String logId)
     */
    public void removeJfiCreditCardLog(final String logId) {
        dao.removeJfiCreditCardLog(new Long(logId));
    }
    //added for getJfiCreditCardLogsByCrm
    public List getJfiCreditCardLogsByCrm(CommonRecord crm, Pager pager){
	return dao.getJfiCreditCardLogsByCrm(crm,pager);
    }
}
