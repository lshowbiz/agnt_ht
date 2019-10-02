
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.JfiUsCreditCardLog;
import com.joymain.jecs.fi.dao.JfiUsCreditCardLogDao;
import com.joymain.jecs.fi.service.JfiUsCreditCardLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JfiUsCreditCardLogManagerImpl extends BaseManager implements JfiUsCreditCardLogManager {
    private JfiUsCreditCardLogDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJfiUsCreditCardLogDao(JfiUsCreditCardLogDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiUsCreditCardLogManager#getJfiUsCreditCardLogs(com.joymain.jecs.fi.model.JfiUsCreditCardLog)
     */
    public List getJfiUsCreditCardLogs(final JfiUsCreditCardLog jfiUsCreditCardLog) {
        return dao.getJfiUsCreditCardLogs(jfiUsCreditCardLog);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiUsCreditCardLogManager#getJfiUsCreditCardLog(String jucclId)
     */
    public JfiUsCreditCardLog getJfiUsCreditCardLog(final String jucclId) {
        return dao.getJfiUsCreditCardLog(new Long(jucclId));
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiUsCreditCardLogManager#saveJfiUsCreditCardLog(JfiUsCreditCardLog jfiUsCreditCardLog)
     */
    public void saveJfiUsCreditCardLog(JfiUsCreditCardLog jfiUsCreditCardLog) {
        dao.saveJfiUsCreditCardLog(jfiUsCreditCardLog);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiUsCreditCardLogManager#removeJfiUsCreditCardLog(String jucclId)
     */
    public void removeJfiUsCreditCardLog(final String jucclId) {
        dao.removeJfiUsCreditCardLog(new Long(jucclId));
    }
    //added for getJfiUsCreditCardLogsByCrm
    public List getJfiUsCreditCardLogsByCrm(CommonRecord crm, Pager pager){
	return dao.getJfiUsCreditCardLogsByCrm(crm,pager);
    }
}
