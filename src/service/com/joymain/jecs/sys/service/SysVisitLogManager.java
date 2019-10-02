package com.joymain.jecs.sys.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysVisitLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface SysVisitLogManager extends Manager {
	/**
	 * Retrieves all of the sysVisitLogs
	 */
	public List getSysVisitLogs(SysVisitLog sysVisitLog);

	/**
	 * Gets sysVisitLog's information based on logId.
	 * @param logId the sysVisitLog's logId
	 * @return sysVisitLog populated sysVisitLog object
	 */
	public SysVisitLog getSysVisitLog(final String logId);

	/**
	 * Saves a sysVisitLog's information
	 * @param sysVisitLog the object to be saved
	 */
	public void saveSysVisitLog(SysVisitLog sysVisitLog);

	/**
	 * Removes a sysVisitLog from the database by logId
	 * @param logId the sysVisitLog's logId
	 */
	public void removeSysVisitLog(final String logId);

	//added for getSysVisitLogsByCrm
	public List getSysVisitLogsByCrm(CommonRecord crm, Pager pager);
	
	/**
     * 根据用户编码,模块编码及访问地址获取唯一的记录
     * @param userCode
     * @param moduleCode
     * @param visitUrl
     * @return
     */
    public SysVisitLog getSysVisitLog(final String userCode, final String moduleCode, final String visitUrl);
    
    /**
     * 清除访问记录
     */
    public void removeAllSysVisitLog();
}