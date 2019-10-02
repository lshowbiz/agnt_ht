package com.joymain.jecs.sys.dao;

import java.util.Collection;
import java.util.List;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.sys.model.SysRoleMenu;

public interface SysRoleMenuDao extends Dao {

	/**
	 * 获取指定角色的所有菜单列表
	 * 
	 * @param roleId
	 * @return
	 */
	public List getSysMenusOfRole(String roleId);

	/**
	 * 获取多个角色共同拥有的菜单列表，列表节点不重复
	 * 
	 * @param roles
	 * @return
	 */
	public List getAllAvailableMenusOfRoles(Collection roles,String userType);

	/**
	 * 获取指定菜单正在被各角色引用的总数
	 * 
	 * @param menuId
	 * @return
	 */
	public int getUseingCountsOfMenu(String menuId);

	/**
	 * 添加一条角色菜单关系
	 * 
	 * @param sysRoleMenu
	 */
	public void saveSysMenuOfRole(SysRoleMenu sysRoleMenu);

	/**
	 * 删除指定角色的所有菜单关系
	 * 
	 * @param roleId
	 *            需删除的所有菜单的角色ID
	 */
	public void removeAllSysMenuOfRole(String roleId);
}
