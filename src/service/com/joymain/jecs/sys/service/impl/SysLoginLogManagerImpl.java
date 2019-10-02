
package com.joymain.jecs.sys.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysLoginLog;
import com.joymain.jecs.sys.dao.SysLoginLogDao;
import com.joymain.jecs.sys.service.SysLoginLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class SysLoginLogManagerImpl extends BaseManager implements SysLoginLogManager {
    private SysLoginLogDao dao;

    public SysLoginLog getLastLogByType(String userCode, String type) {
		// TODO Auto-generated method stub
    	SysLoginLog sysLoginLog = new SysLoginLog();
    	List list = dao.getLogsByType( userCode,  type);
    	if(!list.isEmpty() && list.size()>=2){
    		sysLoginLog= (SysLoginLog) list.get(1);
    	}
		return sysLoginLog;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setSysLoginLogDao(SysLoginLogDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.sys.service.SysLoginLogManager#getSysLoginLogs(com.joymain.jecs.sys.model.SysLoginLog)
     */
    public List getSysLoginLogs(final SysLoginLog sysLoginLog) {
        return dao.getSysLoginLogs(sysLoginLog);
    }

    /**
     * @see com.joymain.jecs.sys.service.SysLoginLogManager#getSysLoginLog(String llId)
     */
    public SysLoginLog getSysLoginLog(final String llId) {
        return dao.getSysLoginLog(new Long(llId));
    }

    /**
     * @see com.joymain.jecs.sys.service.SysLoginLogManager#saveSysLoginLog(SysLoginLog sysLoginLog)
     */
    public void saveSysLoginLog(SysLoginLog sysLoginLog) {
        dao.saveSysLoginLog(sysLoginLog);
    }

    /**
     * @see com.joymain.jecs.sys.service.SysLoginLogManager#removeSysLoginLog(String llId)
     */
    public void removeSysLoginLog(final String llId) {
        dao.removeSysLoginLog(new Long(llId));
    }
    //added for getSysLoginLogsByCrm
    public List getSysLoginLogsByCrm(CommonRecord crm, Pager pager){
	return dao.getSysLoginLogsByCrm(crm,pager);
    }
}
