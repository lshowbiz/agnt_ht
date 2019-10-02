package com.joymain.jecs.sys.dao;

import java.util.List;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.sys.model.SysUserRole;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface SysUserRoleDao extends Dao {
	
	/**
	 * 批量保存用户角色
	 * @param users,用户编号，以','分开
	 * @param roleId
	 */
	 public void saveSysUserRoleBatch(String users,String roleId);
	/**
	 * Retrieves all of the sysUserRoles
	 */
	public List getSysUserRoles(SysUserRole sysUserRole);

	/**
	 * Gets sysUserRole's information based on primary key. An ObjectRetrievalFailureException Runtime Exception is thrown if nothing is found.
	 * 
	 * @param ruId the sysUserRole's ruId
	 * @return sysUserRole populated sysUserRole object
	 */
	public SysUserRole getSysUserRole(final Long ruId);

	/**
	 * Saves a sysUserRole's information
	 * @param sysUserRole the object to be saved
	 */
	public void saveSysUserRole(SysUserRole sysUserRole);

	/**
	 * Removes a sysUserRole from the database by ruId
	 * @param ruId the sysUserRole's ruId
	 */
	public void removeSysUserRole(final Long ruId);

	//added for getSysUserRolesByCrm
	public List getSysUserRolesByCrm(CommonRecord crm, Pager pager);
	
	
	
	public SysUserRole getSysUserRoleByUserCode(String userCode);
	public void saveSysUserRoleByUcode(String roles,String userCode);
	public int removeSysUserRoleBatch(String users);
	public int removeSysUserRoleByUcode(String userCode);
}
