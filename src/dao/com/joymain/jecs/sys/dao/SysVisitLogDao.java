package com.joymain.jecs.sys.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.sys.model.SysVisitLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface SysVisitLogDao extends Dao {

	//	public List getPiProductsByHql(String hql);

	//	public List getPiProductsBySql(String sql);
	/**
	 * Retrieves all of the sysVisitLogs
	 */
	public List getSysVisitLogs(SysVisitLog sysVisitLog);

	/**
	 * Gets sysVisitLog's information based on primary key. An ObjectRetrievalFailureException Runtime Exception is thrown if nothing is found.
	 * 
	 * @param logId the sysVisitLog's logId
	 * @return sysVisitLog populated sysVisitLog object
	 */
	public SysVisitLog getSysVisitLog(final Long logId);

	/**
	 * Saves a sysVisitLog's information
	 * @param sysVisitLog the object to be saved
	 */
	public void saveSysVisitLog(SysVisitLog sysVisitLog);

	/**
	 * Removes a sysVisitLog from the database by logId
	 * @param logId the sysVisitLog's logId
	 */
	public void removeSysVisitLog(final Long logId);

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
