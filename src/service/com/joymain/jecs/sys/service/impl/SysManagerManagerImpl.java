package com.joymain.jecs.sys.service.impl;

import java.util.List;

import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.dao.SysManagerDao;
import com.joymain.jecs.sys.model.SysManager;
import com.joymain.jecs.sys.service.SysManagerManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class SysManagerManagerImpl extends BaseManager implements SysManagerManager {
	private SysManagerDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * @param dao
	 */
	public void setSysManagerDao(SysManagerDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysManagerManager#getSysManagers(com.joymain.jecs.sys.model.SysManager)
	 */
	public List getSysManagers(final SysManager sysManager) {
		return dao.getSysManagers(sysManager);
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysManagerManager#getSysManager(String userCode)
	 */
	public SysManager getSysManager(final String userCode) {
		return dao.getSysManager(new String(userCode));
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysManagerManager#saveSysManager(SysManager sysManager)
	 */
	public void saveSysManager(SysManager sysManager) {
		dao.saveSysManager(sysManager);
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysManagerManager#removeSysManager(String userCode)
	 */
	public void removeSysManager(final String userCode) {
		dao.removeSysManager(new String(userCode));
	}

	//added for getSysManagersByCrm
	public List getSysManagersByCrm(CommonRecord crm, Pager pager) {
		return dao.getSysManagersByCrm(crm, pager);
	}
	
	/**
     * 获取部门下所有人员
     * @param departmentId
     * @return
     */
    public List getSysManagersByDepartment(final String departmentId){
    	return dao.getSysManagersByDepartment(new Long(departmentId));
    }
}