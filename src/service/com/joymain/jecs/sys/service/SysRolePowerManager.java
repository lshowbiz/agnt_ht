package com.joymain.jecs.sys.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysRolePower;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface SysRolePowerManager extends Manager {
	/**
	 * Retrieves all of the sysRolePowers
	 */
	public List getSysRolePowers(SysRolePower sysRolePower);

	/**
	 * Gets sysRolePower's information based on rpId.
	 * @param rpId the sysRolePower's rpId
	 * @return sysRolePower populated sysRolePower object
	 */
	public SysRolePower getSysRolePower(final String rpId);

	/**
	 * Saves a sysRolePower's information
	 * @param sysRolePower the object to be saved
	 */
	public void saveSysRolePower(SysRolePower sysRolePower);

	/**
	 * Removes a sysRolePower from the database by rpId
	 * @param rpId the sysRolePower's rpId
	 */
	public void removeSysRolePower(final String rpId);

	//added for getSysRolePowersByCrm
	public List getSysRolePowersByCrm(CommonRecord crm, Pager pager);

	/**
	 * 获取某一角色对应的记录列表
	 * @param roleId
	 * @return
	 */
	public List getSysRolePowersByRoleId(final String roleId);
	
	/**
     * 删除角色ID对应的模块权限
     * @param roleId
     */
    public void removeSysRolePowersByRoleId(final String roleId);
    
    /**
     * 删除角色指定的模块以外的所有权限
     * @param roleId
     * @param moduleId
     */
    public void removeSysRolePowersNotIn(final String roleId, final String[] moduleId);
    
    /**
     * 批量保存多条记录
     * @param sysRolePowers
     */
    public void saveSysRolePowers(List sysRolePowers);
    
    /**
     * 根据角色和模块获取对应的唯一的记录
     * @param roleId
     * @param moduleId
     * @return
     */
    public SysRolePower getSysRolePower(final String roleId, final String moduleId);
}
