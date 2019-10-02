package com.joymain.jecs.sys.service.impl;

import java.util.List;

import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.dao.SysReportLogDao;
import com.joymain.jecs.sys.model.SysReportLog;
import com.joymain.jecs.sys.service.SysReportLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class SysReportLogManagerImpl extends BaseManager implements SysReportLogManager {
	private SysReportLogDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * @param dao
	 */
	public void setSysReportLogDao(SysReportLogDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysReportLogManager#getSysReportLogs(com.joymain.jecs.sys.model.SysReportLog)
	 */
	public List getSysReportLogs(final SysReportLog sysReportLog) {
		return dao.getSysReportLogs(sysReportLog);
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysReportLogManager#getSysReportLog(String reportId)
	 */
	public SysReportLog getSysReportLog(final String reportId) {
		return dao.getSysReportLog(new String(reportId));
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysReportLogManager#saveSysReportLog(SysReportLog sysReportLog)
	 */
	public void saveSysReportLog(SysReportLog sysReportLog) {
		dao.saveSysReportLog(sysReportLog);
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysReportLogManager#removeSysReportLog(String reportId)
	 */
	public void removeSysReportLog(final String reportId) {
		dao.removeSysReportLog(new String(reportId));
	}

	//added for getSysReportLogsByCrm
	public List getSysReportLogsByCrm(CommonRecord crm, Pager pager) {
		return dao.getSysReportLogsByCrm(crm, pager);
	}

	/**
	 * 获取报表日志
	 * @param companyCode
	 * @param userCode
	 * @return
	 */
	public List getSysReportLogs(final String companyCode, final String userCode) {
		return dao.getSysReportLogs(companyCode, userCode);
	}
}
