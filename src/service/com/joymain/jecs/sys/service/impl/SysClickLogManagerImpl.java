
package com.joymain.jecs.sys.service.impl;

import java.util.List;
import java.util.Map;

import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.dao.SysClickLogDao;
import com.joymain.jecs.sys.model.SysClickLog;
import com.joymain.jecs.sys.service.SysClickLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class SysClickLogManagerImpl extends BaseManager implements SysClickLogManager {
    private SysClickLogDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setSysClickLogDao(SysClickLogDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.sys.service.SysClickLogManager#getSysClickLogs(com.joymain.jecs.sys.model.SysClickLog)
     */
    public List getSysClickLogs(final SysClickLog sysClickLog) {
        return dao.getSysClickLogs(sysClickLog);
    }

    /**
     * @see com.joymain.jecs.sys.service.SysClickLogManager#getSysClickLog(String logId)
     */
    public SysClickLog getSysClickLog(final String logId) {
        return dao.getSysClickLog(new Long(logId));
    }
    
    public Map getSysClickLog(final String logId, final String month) {
        return dao.getSysClickLog(new Long(logId), month);
    }

    /**
     * @see com.joymain.jecs.sys.service.SysClickLogManager#saveSysClickLog(SysClickLog sysClickLog)
     */
    public void saveSysClickLog(SysClickLog sysClickLog) {
        dao.saveSysClickLog(sysClickLog);
    }

    /**
     * @see com.joymain.jecs.sys.service.SysClickLogManager#removeSysClickLog(String logId)
     */
    public void removeSysClickLog(final String logId) {
        dao.removeSysClickLog(new Long(logId));
    }
    
    /**
	 * 根据外部参数分页获取用户访问日志
	 * @param crm
	 * @param pager
	 * @return List
	 */
	public List getSysClickLogsByCrm(CommonRecord crm, Pager pager) {
		return dao.getSysClickLogsByCrm(crm, pager);
	}
	public List getSysClickLogsNewVersion(CommonRecord crm, Pager pager) {
		return dao.getSysClickLogsNewVersion(crm, pager);
	}
}