
package com.joymain.jecs.fi.service.impl;

import java.util.List;

import com.joymain.jecs.fi.dao.JfiChinapnrLogDao;
import com.joymain.jecs.fi.model.JfiChinapnrLog;
import com.joymain.jecs.fi.service.JfiChinapnrLogManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
@SuppressWarnings("unchecked")
public class JfiChinapnrLogManagerImpl extends BaseManager implements JfiChinapnrLogManager {
    private JfiChinapnrLogDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJfiChinapnrLogDao(JfiChinapnrLogDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiChinapnrLogManager#getJfiChinapnrLogs(com.joymain.jecs.fi.model.JfiChinapnrLog)
     */
    public List getJfiChinapnrLogs(final JfiChinapnrLog jfiChinapnrLog) {
        return dao.getJfiChinapnrLogs(jfiChinapnrLog);
    }
    public List getChinapnrLogsByMerId(String MerId) {
    	 return dao.getChinapnrLogsByMerId(MerId);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiChinapnrLogManager#getJfiChinapnrLog(String logId)
     */
    public JfiChinapnrLog getJfiChinapnrLog(final String logId) {
        return dao.getJfiChinapnrLog(new Long(logId));
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiChinapnrLogManager#saveJfiChinapnrLog(JfiChinapnrLog jfiChinapnrLog)
     */
    public void saveJfiChinapnrLog(JfiChinapnrLog jfiChinapnrLog) {
        dao.saveJfiChinapnrLog(jfiChinapnrLog);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiChinapnrLogManager#removeJfiChinapnrLog(String logId)
     */
    public void removeJfiChinapnrLog(final String logId) {
        dao.removeJfiChinapnrLog(new Long(logId));
    }
    //added for getJfiChinapnrLogsByCrm
    public List getJfiChinapnrLogsByCrm(CommonRecord crm, Pager pager){
	return dao.getJfiChinapnrLogsByCrm(crm,pager);
    }
}
