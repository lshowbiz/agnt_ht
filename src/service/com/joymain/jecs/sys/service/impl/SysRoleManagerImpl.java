package com.joymain.jecs.sys.service.impl;

import java.util.List;

import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.dao.SysRoleDao;
import com.joymain.jecs.sys.model.SysRole;
import com.joymain.jecs.sys.service.SysRoleManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class SysRoleManagerImpl extends BaseManager implements SysRoleManager {
	private SysRoleDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * @param dao
	 */
	public void setSysRoleDao(SysRoleDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysRoleManager#getSysRoles(com.joymain.jecs.sys.model.SysRole)
	 */
	public List getSysRoles(final SysRole sysRole) {
		return dao.getSysRoles(sysRole);
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysRoleManager#getSysRole(String roleId)
	 */
	public SysRole getSysRole(final String roleId) {
		return dao.getSysRole(new Long(roleId));
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysRoleManager#saveSysRole(SysRole sysRole)
	 */
	public void saveSysRole(SysRole sysRole) {
		dao.saveSysRole(sysRole);
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysRoleManager#removeSysRole(String roleId)
	 */
	public void removeSysRole(final String roleId) {
		dao.removeSysRole(new Long(roleId));
	}

	//added for getSysRolesByCrm
	public List getSysRolesByCrm(CommonRecord crm, Pager pager) {
		return dao.getSysRolesByCrm(crm, pager);
	}
	
	/**
     * 根据角色编码获取对应的角色记录
     * @param roleCode
     * @return
     */
    public SysRole getSysRoleByCode(final String roleCode) {
    	return dao.getSysRoleByCode(roleCode);
    }
}
