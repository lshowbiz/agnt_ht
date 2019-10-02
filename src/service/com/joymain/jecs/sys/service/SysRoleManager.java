package com.joymain.jecs.sys.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysRole;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface SysRoleManager extends Manager {
	/**
	 * Retrieves all of the sysRoles
	 */
	public List getSysRoles(SysRole sysRole);

	/**
	 * Gets sysRole's information based on roleId.
	 * @param roleId the sysRole's roleId
	 * @return sysRole populated sysRole object
	 */
	public SysRole getSysRole(final String roleId);

	/**
	 * Saves a sysRole's information
	 * @param sysRole the object to be saved
	 */
	public void saveSysRole(SysRole sysRole);

	/**
	 * Removes a sysRole from the database by roleId
	 * @param roleId the sysRole's roleId
	 */
	public void removeSysRole(final String roleId);

	//added for getSysRolesByCrm
	public List getSysRolesByCrm(CommonRecord crm, Pager pager);
	
	/**
     * 根据角色编码获取对应的角色记录
     * @param roleCode
     * @return
     */
    public SysRole getSysRoleByCode(final String roleCode);
}
