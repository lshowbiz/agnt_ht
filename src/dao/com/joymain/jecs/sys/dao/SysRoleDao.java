package com.joymain.jecs.sys.dao;

import java.util.List;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.sys.model.SysRole;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface SysRoleDao extends Dao {

	//	public List getPiProductsByHql(String hql);

	//	public List getPiProductsBySql(String sql);
	/**
	 * Retrieves all of the sysRoles
	 */
	public List getSysRoles(SysRole sysRole);

	/**
	 * Gets sysRole's information based on primary key. An ObjectRetrievalFailureException Runtime Exception is thrown if nothing is found.
	 * 
	 * @param roleId the sysRole's roleId
	 * @return sysRole populated sysRole object
	 */
	public SysRole getSysRole(final Long roleId);

	/**
	 * Saves a sysRole's information
	 * @param sysRole the object to be saved
	 */
	public void saveSysRole(SysRole sysRole);

	/**
	 * Removes a sysRole from the database by roleId
	 * @param roleId the sysRole's roleId
	 */
	public void removeSysRole(final Long roleId);

	//added for getSysRolesByCrm
	public List getSysRolesByCrm(CommonRecord crm, Pager pager);
	
	/**
     * 根据角色编码获取对应的角色记录
     * @param roleCode
     * @return
     */
    public SysRole getSysRoleByCode(final String roleCode);
}
