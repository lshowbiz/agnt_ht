package com.joymain.jecs.sys.service.impl;

import java.util.List;

import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.dao.SysUserRoleDao;
import com.joymain.jecs.sys.model.SysUserRole;
import com.joymain.jecs.sys.service.SysUserRoleManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class SysUserRoleManagerImpl extends BaseManager implements SysUserRoleManager {
	public void saveSysUserRoleBatch(String users, String roleId) {
		// TODO Auto-generated method stub
		dao.saveSysUserRoleBatch(users, roleId);
	}
	
	public void saveSysUserRoleByUcode(String roles, String userCode) {
		// TODO Auto-generated method stub
		dao.saveSysUserRoleByUcode(roles, userCode);
	}
	
	public int removeSysUserRoleByUcode(String userCode){
		return dao.removeSysUserRoleByUcode(userCode);
	}

	public int removeSysUserRoleBatch(String users) {
		// TODO Auto-generated method stub
		return dao.removeSysUserRoleBatch(users);
	}

	private SysUserRoleDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * @param dao
	 */
	public void setSysUserRoleDao(SysUserRoleDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysUserRoleManager#getSysUserRoles(com.joymain.jecs.sys.model.SysUserRole)
	 */
	public List getSysUserRoles(final SysUserRole sysUserRole) {
		return dao.getSysUserRoles(sysUserRole);
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysUserRoleManager#getSysUserRole(String ruId)
	 */
	public SysUserRole getSysUserRole(final String ruId) {
		return dao.getSysUserRole(new Long(ruId));
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysUserRoleManager#saveSysUserRole(SysUserRole sysUserRole)
	 */
	public void saveSysUserRole(SysUserRole sysUserRole) {
		dao.saveSysUserRole(sysUserRole);
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysUserRoleManager#removeSysUserRole(String ruId)
	 */
	public void removeSysUserRole(final String ruId) {
		dao.removeSysUserRole(new Long(ruId));
	}

	//added for getSysUserRolesByCrm
	public List getSysUserRolesByCrm(CommonRecord crm, Pager pager) {
		return dao.getSysUserRolesByCrm(crm, pager);
	}

	public SysUserRole getSysUserRoleByUserCode(String userCode) {
		return dao.getSysUserRoleByUserCode(userCode);
	}

}
