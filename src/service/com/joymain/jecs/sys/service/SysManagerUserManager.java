package com.joymain.jecs.sys.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysManagerUser;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface SysManagerUserManager extends Manager {
	/**
	 * Retrieves all of the sysManagerUsers
	 */
	public List getSysManagerUsers(SysManagerUser sysManagerUser);

	/**
	 * Gets sysManagerUser's information based on rollId.
	 * @param rollId the sysManagerUser's rollId
	 * @return sysManagerUser populated sysManagerUser object
	 */
	public SysManagerUser getSysManagerUser(final String rollId);

	/**
	 * Saves a sysManagerUser's information
	 * @param sysManagerUser the object to be saved
	 */
	public void saveSysManagerUser(SysManagerUser sysManagerUser);

	/**
	 * Removes a sysManagerUser from the database by rollId
	 * @param rollId the sysManagerUser's rollId
	 */
	public void removeSysManagerUser(final String rollId);

	//added for getSysManagerUsersByCrm
	public List getSysManagerUsersByCrm(CommonRecord crm, Pager pager);

	/**
	 * 获取masterUserCode所管理的用户清单,包含slaveUserCode所管理的用户清单,其所返回的slave_roll_id为两者的交集记录ID
	 * @param masterSysUser
	 * @param slaveUserCode
	 * @param companyCode
	 * @param limitCompany 是否限定于指定的公司
	 * @return
	 */
	public List getSysManagersWithJoin(final SysUser masterSysUser, final String slaveUserCode, final String companyCode, boolean limitCompany);
	
	/**
     * 删除用户指定的被管理用户以外的所有记录
     * @param masterUserCode
     * @param slaveUserCodes
     */
    public void removeSysManagerUsersBySlaveCodes(final String masterUserCode, final String companyCode, final String[] slaveUserCodes);
    
    /**
     * 获取用户与被管理用户所对应的记录
     * @param masterUserCode
     * @param slaveUserCode
     * @return
     */
    public SysManagerUser getSysManagerUser(final String masterUserCode, final String slaveUserCode);
    
    /**
     * 批量保存多条记录
     * @param sysManagerModlPows
     */
    public void saveSysManagerUsers(List sysManagerUsers);
    
    /**
	 * 获取masterUserCode所管理的用户清单,包含被管理用户对指定模块是否拥有权限的信息,其所返回的power_id不为空则代表用户有权限
	 * @param masterSysUser
	 * @param moduleId
	 * @param companyCode
	 * @param limitCompany 是否限定于指定的公司
	 * @return
	 */
	public List getSysManagersWithPowerJoin(final SysUser masterSysUser, final String moduleId, final String companyCode, boolean limitCompany);
	
	/**
	 * Add By WuCF 20140506
	 * 获取指定仓库包含被管理用户对指定模块是否拥有权限的信息,其所返回的power_id不为空则代表用户有权限
	 * @param masterSysUser
	 * @param moduleId
	 * @param companyCode
	 * @param limitCompany 是否限定于指定的公司
	 * @return
	 */
	public List getPdWarehouseWithPowerJoin(final SysUser masterSysUser, final String warehouseNo, final String companyCode, boolean limitCompany);

	
	/**
	 * 获取用户所管理的用户列表
	 * @param masterUserCode
	 * @return
	 */
	public List getSysManagerUsersByMaster(final String masterUserCode);
	
	/**
	 * 获取可管理某用户的用户列表
	 * @param slaveUserCode
	 * @return
	 */
	public List getSysManagerUsersBySlave(final String slaveUserCode);
}
