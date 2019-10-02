
package com.joymain.jecs.mi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.mi.model.JmiStateLog;
import com.joymain.jecs.mi.dao.JmiStateLogDao;
import com.joymain.jecs.mi.service.JmiStateLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JmiStateLogManagerImpl extends BaseManager implements JmiStateLogManager {
    private JmiStateLogDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJmiStateLogDao(JmiStateLogDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiStateLogManager#getJmiStateLogs(com.joymain.jecs.mi.model.JmiStateLog)
     */
    public List getJmiStateLogs(final JmiStateLog jmiStateLog) {
        return dao.getJmiStateLogs(jmiStateLog);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiStateLogManager#getJmiStateLog(String id)
     */
    public JmiStateLog getJmiStateLog(final String id) {
        return dao.getJmiStateLog(new BigDecimal(id));
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiStateLogManager#saveJmiStateLog(JmiStateLog jmiStateLog)
     */
    public void saveJmiStateLog(JmiStateLog jmiStateLog) {
        dao.saveJmiStateLog(jmiStateLog);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiStateLogManager#removeJmiStateLog(String id)
     */
    public void removeJmiStateLog(final String id) {
        dao.removeJmiStateLog(new BigDecimal(id));
    }
    //added for getJmiStateLogsByCrm
    public List getJmiStateLogsByCrm(CommonRecord crm, Pager pager){
	return dao.getJmiStateLogsByCrm(crm,pager);
    }
}
