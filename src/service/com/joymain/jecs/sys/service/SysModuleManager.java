package com.joymain.jecs.sys.service;

import java.util.List;
import java.util.Map;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysModule;
import com.joymain.jecs.sys.model.SysRole;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface SysModuleManager extends Manager {
	/**
	 * Retrieves all of the sysModules
	 */
	public List getSysModules(SysModule sysModule);

	/**
	 * Gets sysModule's information based on moduleId.
	 * @param moduleId the sysModule's moduleId
	 * @return sysModule populated sysModule object
	 */
	public SysModule getSysModule(final String moduleId);

	/**
	 * Saves a sysModule's information
	 * @param sysModule the object to be saved
	 */
	public void saveSysModule(SysModule sysModule);

	/**
	 * Removes a sysModule from the database by moduleId
	 * @param moduleId the sysModule's moduleId
	 */
	public void removeSysModule(final String moduleId);

	//added for getSysModulesByCrm
	public List getSysModulesByCrm(CommonRecord crm, Pager pager);

	/**
     * 获取直接下级的模块列表
     * @param moduleId
     * @param orderField
     * @return
     */
    public List getDirectChildModules(final String moduleId, final String orderField);
	
	/**
     * 根据模块编码获取对应的唯一的模块
     * @param moduleCode
     * @return
     */
    public SysModule getSysModuleByCode(final String moduleCode);
    
    /**
     * 根据用户获取对应的菜单
     * @param sysUser
     * @return
     */
    public List getSysMenus(final SysUser sysUser,String parentId,String startWithParentId);
    
    /**
     * 获取masterUserCode所管理的模块清单,包含slaveUserCode所管理的模块清单,其所返回的slave_power_id为两者的交集记录ID
     * @param masterUserCode
     * @param slaveUserCode
     * @param companyCode
     * @return
     */
    public List getMyManSysModules(final String masterUserCode, final String slaveUserCode, final String companyCode);
    
    /**
     * 获取某个角色最多可拥有的模块,其中如果已经有相应模块的权限,则rp_id不为空 
     * @param sysRole
     * @return
     */
    public List getSysModulesJoinRole(final SysRole sysRole);
    
    /**
     * 重新生成模块树结构
     */
    public void saveSysModulesRebuild();
    
    /**
     * 获取所有有权限的模块
     * @param sysUser
     * @param moduleType
     * @return
     */
    public List getSysModules(SysUser sysUser, final Integer moduleType,String parentId,String startWithParentId);
    
    /**
     * 根据链接URL获取对应的模块列表
     * @param linkUrl
     * @return
     */
    public List getSysModulesByUrl(final String linkUrl);
    
    /**
	 * 根据模块编码和链接地址获取对应的模块
	 * @param moduleCode
	 * @param linkUrl
	 * @return
	 */
	public SysModule getSysModuleByCode(final String moduleCode,final String linkUrl);
    
    /**
     * 获取对应用户有权限的模块
     * @param sysUser
     * @return
     */
    public Map<String, String> getSysPowerMap(final SysUser sysUser);
    
    /**
	 * 判断某用户对某模块是否有权限
	 * @param sysUser
	 * @param sysModule
	 * @return
	 */
	public boolean getSysUserPower(SysUser sysUser, SysModule sysModule);
	
	/**
	 * 判断某用户对某模块是否有权限
	 * @param sysUser
	 * @param moduleCode
	 * @return
	 */
	public boolean getSysUserPowerByCode(SysUser sysUser, final String moduleCode);
}