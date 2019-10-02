
package com.joymain.jecs.sms.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sms.model.SmsSendLog;
import com.joymain.jecs.sms.dao.SmsSendLogDao;
import com.joymain.jecs.sms.service.SmsSendLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class SmsSendLogManagerImpl extends BaseManager implements SmsSendLogManager {
    private SmsSendLogDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setSmsSendLogDao(SmsSendLogDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.sms.service.SmsSendLogManager#getSmsSendLogs(com.joymain.jecs.sms.model.SmsSendLog)
     */
    public List getSmsSendLogs(final SmsSendLog smsSendLog) {
        return dao.getSmsSendLogs(smsSendLog);
    }

    /**
     * @see com.joymain.jecs.sms.service.SmsSendLogManager#getSmsSendLog(String sslId)
     */
    public SmsSendLog getSmsSendLog(final String sslId) {
        return dao.getSmsSendLog(new Long(sslId));
    }

    /**
     * @see com.joymain.jecs.sms.service.SmsSendLogManager#saveSmsSendLog(SmsSendLog smsSendLog)
     */
    public void saveSmsSendLog(SmsSendLog smsSendLog) {
        dao.saveSmsSendLog(smsSendLog);
    }

    /**
     * @see com.joymain.jecs.sms.service.SmsSendLogManager#removeSmsSendLog(String sslId)
     */
    public void removeSmsSendLog(final String sslId) {
        dao.removeSmsSendLog(new Long(sslId));
    }
    //added for getSmsSendLogsByCrm
    public List getSmsSendLogsByCrm(CommonRecord crm, Pager pager){
	return dao.getSmsSendLogsByCrm(crm,pager);
    }
}
