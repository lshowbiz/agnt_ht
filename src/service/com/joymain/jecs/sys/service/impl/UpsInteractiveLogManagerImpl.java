
package com.joymain.jecs.sys.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.UpsInteractiveLog;
import com.joymain.jecs.sys.dao.UpsInteractiveLogDao;
import com.joymain.jecs.sys.service.UpsInteractiveLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class UpsInteractiveLogManagerImpl extends BaseManager implements UpsInteractiveLogManager {
    private UpsInteractiveLogDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setUpsInteractiveLogDao(UpsInteractiveLogDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.sys.service.UpsInteractiveLogManager#getUpsInteractiveLogs(com.joymain.jecs.sys.model.UpsInteractiveLog)
     */
    public List getUpsInteractiveLogs(final UpsInteractiveLog upsInteractiveLog) {
        return dao.getUpsInteractiveLogs(upsInteractiveLog);
    }

    /**
     * @see com.joymain.jecs.sys.service.UpsInteractiveLogManager#getUpsInteractiveLog(String uniId)
     */
    public UpsInteractiveLog getUpsInteractiveLog(final String uniId) {
        return dao.getUpsInteractiveLog(new Long(uniId));
    }

    /**
     * @see com.joymain.jecs.sys.service.UpsInteractiveLogManager#saveUpsInteractiveLog(UpsInteractiveLog upsInteractiveLog)
     */
    public void saveUpsInteractiveLog(UpsInteractiveLog upsInteractiveLog) {
        dao.saveUpsInteractiveLog(upsInteractiveLog);
    }

    /**
     * @see com.joymain.jecs.sys.service.UpsInteractiveLogManager#removeUpsInteractiveLog(String uniId)
     */
    public void removeUpsInteractiveLog(final String uniId) {
        dao.removeUpsInteractiveLog(new Long(uniId));
    }
    //added for getUpsInteractiveLogsByCrm
    public List getUpsInteractiveLogsByCrm(CommonRecord crm, Pager pager){
	return dao.getUpsInteractiveLogsByCrm(crm,pager);
    }
}
