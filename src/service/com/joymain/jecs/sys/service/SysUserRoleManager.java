
package com.joymain.jecs.sys.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUserRole;
import com.joymain.jecs.sys.dao.SysUserRoleDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface SysUserRoleManager extends Manager {
	 public void saveSysUserRoleBatch(String users,String roleId);
	 public void saveSysUserRoleByUcode(String roles,String userCode);
	 public int removeSysUserRoleByUcode(String userCode);	
	 public int removeSysUserRoleBatch(String users);
    /**
     * Retrieves all of the sysUserRoles
     */
    public List getSysUserRoles(SysUserRole sysUserRole);

    /**
     * Gets sysUserRole's information based on ruId.
     * @param ruId the sysUserRole's ruId
     * @return sysUserRole populated sysUserRole object
     */
    public SysUserRole getSysUserRole(final String ruId);

    /**
     * Saves a sysUserRole's information
     * @param sysUserRole the object to be saved
     */
    public void saveSysUserRole(SysUserRole sysUserRole);

    /**
     * Removes a sysUserRole from the database by ruId
     * @param ruId the sysUserRole's ruId
     */
    public void removeSysUserRole(final String ruId);
    //added for getSysUserRolesByCrm
		public List getSysUserRolesByCrm(CommonRecord crm, Pager pager);
		public SysUserRole getSysUserRoleByUserCode(String userCode);
}

