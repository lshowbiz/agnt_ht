package com.joymain.jecs.sys.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysManagerModlPow;
import com.joymain.jecs.sys.model.SysRole;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface SysManagerModlPowManager extends Manager {
	/**
	 * Retrieves all of the sysManagerModlPows
	 */
	public List getSysManagerModlPows(SysManagerModlPow sysManagerModlPow);

	/**
	 * Gets sysManagerModlPow's information based on powerId.
	 * @param powerId the sysManagerModlPow's powerId
	 * @return sysManagerModlPow populated sysManagerModlPow object
	 */
	public SysManagerModlPow getSysManagerModlPow(final String powerId);

	/**
	 * Saves a sysManagerModlPow's information
	 * @param sysManagerModlPow the object to be saved
	 */
	public void saveSysManagerModlPow(SysManagerModlPow sysManagerModlPow);

	/**
	 * Removes a sysManagerModlPow from the database by powerId
	 * @param powerId the sysManagerModlPow's powerId
	 */
	public void removeSysManagerModlPow(final String powerId);

	//added for getSysManagerModlPowsByCrm
	public List getSysManagerModlPowsByCrm(CommonRecord crm, Pager pager);

	/**
	 * 删除用户指定的模块以外的所有权限,如果公司编码不为空,则此公司以外的模块不删除
	 * @param operatorCode
	 * @param userCode
	 * @param companyCode
	 * @param moduleIds
	 */
	public void removeSysManagerModlPowsByIds(final String operatorCode,final String userCode, final String companyCode, final String[] moduleIds);
	
    /**
     * 获取用户指定模块所对应的权限
     * @param userCode
     * @param companyCode
     * @param moduleId
     * @return
     */
    public SysManagerModlPow getSysManagerModlPow(final String userCode, final String companyCode, final String moduleId);
    
    /**
     * 批量保存多条记录
     * @param sysManagerModlPows
     */
    public void saveSysManagerModlPows(List sysManagerModlPows);
    
    /**
     * 删除一定用户范围内, 对应模块所指定的用外的所有权限,如果公司编码不为空,则此公司以外的模块不删除
     * @param manageredUserCodes
     * @param userCodes
     * @param companyCode
     * @param moduleId
     */
    public void removeSysManagerModlPowsByUserCodes(final String[] manageredUserCodes,final String[] userCodes, final String companyCode, final String moduleId);
    
    /**
     * 删除用户在角色以外模块的所有权限
     * @param userCode
     * @param companyCode
     * @param sysRole
     */
    public void removeSysManagerModlPowsByRole(final String userCode, final String companyCode, final SysRole sysRole);
    
    /**
	 * 授权主代码
	 * @param operatorCode
	 * @param masterUserCode
	 * @param selectedModule
	 * @param selectedSalveUserCode
	 * @param companyCode
	 */
	public void saveSysManagerModlPowsAssigned(final String operatorCode, final String masterUserCode, final String selectedModule,
	        final String selectedSalveUserCode, String companyCode);
	
	/**
	 * 保存用户权限
	 * @param operatorCode
	 * @param masterUserCode
	 * @param selectedModule
	 * @param companyCode
	 */
	public void saveSysManagerPowers(final String operatorCode, final String masterUserCode, final String selectedModule, String companyCode);

	/**
	 * 保存所管理的用户关系
	 * @param masterUserCode
	 * @param selectedModule
	 * @param selectedSalveUserCode
	 * @param companyCode
	 */
	public void saveSysManagerUsers(final String masterUserCode, final String selectedModule, final String selectedSalveUserCode,
	        final String companyCode);
		
	/**
	 * 按模块授权
	 * @param currentSysUser
	 * @param selectedUserCode
	 * @param moduleId
	 * @param companyCode
	 */
	public void saveSysManagerModlPowsByModule(final SysUser currentSysUser, final String selectedUserCode, final String moduleId,
	        final String companyCode);
	
	/**
	 * Add By WuCF 20140507
	 * 仓库批量授权
	 * @param currentSysUser：当前登录用户
	 * @param selectedUserCode：选择的用户数据
	 * @param warehouseNo：仓库编码
	 * @param companyCode：分公司编码
	 */
	public void savePdWarehousePowsByModule(final SysUser currentSysUser, final String selectedUserCode, final String warehouseNo,
	        final String companyCode);
	/**
	 * 保存用户受权仓库
	 * @param userCode
	 * @param selectedWarehouseCode
	 */
	public void savePdWarehousePowsByUser(String userCode,String selectedWarehouseCode);
	
	/**
	 * 复制某用户在某些公司的权限至其它用户
	 * @param fromUser
	 * @param fromUserCompanys
	 * @param toUsers
	 */
	public void saveSysPowerCloneByUser(final String fromUser, final String[] fromUserCompanys, final String[] toUsers);
	
	/**
	 * 复制某用户在某公司的权限至其它公司
	 * @param fromUser
	 * @param fromUserCompany
	 * @param toUserCompanys
	 */
	public void saveSysPowerCloneByCompany(final String fromUser, final String fromUserCompany, final String[] toUserCompanys);
}