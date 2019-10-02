package com.joymain.jecs.sys.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysReportLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface SysReportLogManager extends Manager {
	/**
	 * Retrieves all of the sysReportLogs
	 */
	public List getSysReportLogs(SysReportLog sysReportLog);

	/**
	 * Gets sysReportLog's information based on reportId.
	 * @param reportId the sysReportLog's reportId
	 * @return sysReportLog populated sysReportLog object
	 */
	public SysReportLog getSysReportLog(final String reportId);

	/**
	 * Saves a sysReportLog's information
	 * @param sysReportLog the object to be saved
	 */
	public void saveSysReportLog(SysReportLog sysReportLog);

	/**
	 * Removes a sysReportLog from the database by reportId
	 * @param reportId the sysReportLog's reportId
	 */
	public void removeSysReportLog(final String reportId);

	//added for getSysReportLogsByCrm
	public List getSysReportLogsByCrm(CommonRecord crm, Pager pager);

	/**
	 * 获取报表日志
	 * @param companyCode
	 * @param userCode
	 * @return
	 */
	public List getSysReportLogs(final String companyCode, final String userCode);
}
