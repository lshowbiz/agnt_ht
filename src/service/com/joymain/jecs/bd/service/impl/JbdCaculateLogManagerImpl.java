
package com.joymain.jecs.bd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.bd.model.JbdCaculateLog;
import com.joymain.jecs.bd.dao.JbdCaculateLogDao;
import com.joymain.jecs.bd.service.JbdCaculateLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JbdCaculateLogManagerImpl extends BaseManager implements JbdCaculateLogManager {
    private JbdCaculateLogDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdCaculateLogDao(JbdCaculateLogDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdCaculateLogManager#getJbdCaculateLogs(com.joymain.jecs.bd.model.JbdCaculateLog)
     */
    public List getJbdCaculateLogs(final JbdCaculateLog jbdCaculateLog) {
        return dao.getJbdCaculateLogs(jbdCaculateLog);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdCaculateLogManager#getJbdCaculateLog(String id)
     */
    public JbdCaculateLog getJbdCaculateLog(final String id) {
        return dao.getJbdCaculateLog(new BigDecimal(id));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdCaculateLogManager#saveJbdCaculateLog(JbdCaculateLog jbdCaculateLog)
     */
    public void saveJbdCaculateLog(JbdCaculateLog jbdCaculateLog) {
        dao.saveJbdCaculateLog(jbdCaculateLog);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdCaculateLogManager#removeJbdCaculateLog(String id)
     */
    public void removeJbdCaculateLog(final String id) {
        dao.removeJbdCaculateLog(new BigDecimal(id));
    }
    //added for getJbdCaculateLogsByCrm
    public List getJbdCaculateLogsByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdCaculateLogsByCrm(crm,pager);
    }
}
