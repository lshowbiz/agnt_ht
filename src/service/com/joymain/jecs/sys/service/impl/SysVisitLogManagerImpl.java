package com.joymain.jecs.sys.service.impl;

import java.util.List;

import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.dao.SysVisitLogDao;
import com.joymain.jecs.sys.model.SysVisitLog;
import com.joymain.jecs.sys.service.SysVisitLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class SysVisitLogManagerImpl extends BaseManager implements SysVisitLogManager {
	private SysVisitLogDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * @param dao
	 */
	public void setSysVisitLogDao(SysVisitLogDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysVisitLogManager#getSysVisitLogs(com.joymain.jecs.sys.model.SysVisitLog)
	 */
	public List getSysVisitLogs(final SysVisitLog sysVisitLog) {
		return dao.getSysVisitLogs(sysVisitLog);
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysVisitLogManager#getSysVisitLog(String logId)
	 */
	public SysVisitLog getSysVisitLog(final String logId) {
		return dao.getSysVisitLog(new Long(logId));
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysVisitLogManager#saveSysVisitLog(SysVisitLog sysVisitLog)
	 */
	public void saveSysVisitLog(SysVisitLog sysVisitLog) {
		dao.saveSysVisitLog(sysVisitLog);
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysVisitLogManager#removeSysVisitLog(String logId)
	 */
	public void removeSysVisitLog(final String logId) {
		dao.removeSysVisitLog(new Long(logId));
	}

	//added for getSysVisitLogsByCrm
	public List getSysVisitLogsByCrm(CommonRecord crm, Pager pager) {
		return dao.getSysVisitLogsByCrm(crm, pager);
	}
	
	/**
     * 根据用户编码,模块编码及访问地址获取唯一的记录
     * @param userCode
     * @param moduleCode
     * @param visitUrl
     * @return
     */
    public SysVisitLog getSysVisitLog(final String userCode, final String moduleCode, final String visitUrl){
    	return dao.getSysVisitLog(userCode, moduleCode, visitUrl);
    }
    
    /**
     * 清除访问记录
     */
    public void removeAllSysVisitLog(){
    	dao.removeAllSysVisitLog();
    }
}