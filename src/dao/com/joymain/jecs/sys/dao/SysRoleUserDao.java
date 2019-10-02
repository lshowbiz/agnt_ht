package com.joymain.jecs.sys.dao;

import java.util.List;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.sys.model.SysRoleUser;

public interface SysRoleUserDao extends Dao {

	/**
	 * 获取指定角色的所有用户列表
	 * 
	 * @param roleId
	 * @return
	 */
	public List getSysUsersOfRole(String roleId);

	/**
	 * 获取指定用户的所有角色列表
	 * 
	 * @param userId
	 * @return
	 */
	public List getSysRolesOfUser(String loginCode);

	/**
	 * 获取指定用户的所有有效角色列表
	 * 
	 * @param userId
	 * @return
	 */
	public List getAllAvailableRolesOfUser(String loginCode);

	/**
	 * 获取指定用户正在被角色引用的总数
	 * 
	 * @param userId
	 * @return
	 */
	public int getUseingCountsOfRoleByUser(String userId);

	/**
	 * 获取指定角色权限正在被用户引用的总数
	 * 
	 * @param roleId
	 * @return
	 */
	public int getUseingCountsOfUserByRole(String roleId);

	/**
	 * 添加一条用户角色关系
	 * 
	 * @param sysRolePower
	 */
	public void saveSysUserOfRole(SysRoleUser sysRoleUser);

	/**
	 * 删除指定角色的所有用户关系
	 * 
	 * @param roleId
	 */
	public void removeAllSysUserOfRole(String roleId);

	/**
	 * 删除指定用户的所有角色关系
	 * 
	 * @param userId
	 */
	public void removeAllSysRoleOfUser(String loginCode);
}
