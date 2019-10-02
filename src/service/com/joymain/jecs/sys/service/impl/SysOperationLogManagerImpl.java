package com.joymain.jecs.sys.service.impl;

import java.util.List;
import java.util.Map;

import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.dao.SysOperationLogDao;
import com.joymain.jecs.sys.model.SysOperationLog;
import com.joymain.jecs.sys.service.SysOperationLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class SysOperationLogManagerImpl extends BaseManager implements SysOperationLogManager {
	private SysOperationLogDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * @param dao
	 */
	public void setSysOperationLogDao(SysOperationLogDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysOperationLogManager#getSysOperationLogs(com.joymain.jecs.sys.model.SysOperationLog)
	 */
	public List getSysOperationLogs(final SysOperationLog sysOperationLog) {
		return dao.getSysOperationLogs(sysOperationLog);
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysOperationLogManager#getSysOperationLog(String logId)
	 */
	public SysOperationLog getSysOperationLog(final String logId) {
		return dao.getSysOperationLog(new Long(logId));
	}
	
	/**
	 * 根据ID获取对应的日志
	 * @param logId
	 * @param month
	 * @return
	 */
	public Map getSysOperationLog(final String logId, final String month) {
		return dao.getSysOperationLog(new Long(logId), month);
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysOperationLogManager#saveSysOperationLog(SysOperationLog sysOperationLog)
	 */
	public void saveSysOperationLog(SysOperationLog sysOperationLog) {
		dao.saveSysOperationLog(sysOperationLog);
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysOperationLogManager#removeSysOperationLog(String logId)
	 */
	public void removeSysOperationLog(final String logId) {
		dao.removeSysOperationLog(new Long(logId));
	}

	/**
	 * 根据外部参数分页获取用户操作日志
	 * @param crm
	 * @param pager
	 * @return List
	 */
	public List getSysOperationLogsByCrm(CommonRecord crm, Pager pager) {
		return dao.getSysOperationLogsByCrm(crm, pager);
	}
	
	/**
	 * 根据外部参数分页获取用户操作日志
	 * @param crm
	 * @param pager
	 * @return List
	 */
	public List getSysOperationLogsNewVersion(CommonRecord crm, Pager pager) {
		return dao.getSysOperationLogsNewVersion(crm, pager);
	}
	
	/**
	 * 通过SQL保存日志并且返回新插入的日志的ID
	 * @param sysOperationLog
	 * @param month yyyymm
	 * @return
	 */
	public Long saveSysOperationLogBySql(final SysOperationLog sysOperationLog, final String month) {
		return dao.saveSysOperationLogBySql(sysOperationLog,month);
	}
}
