package com.joymain.jecs.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.dao.SysModuleDao;
import com.joymain.jecs.sys.model.SysModule;
import com.joymain.jecs.sys.model.SysRole;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysModuleManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class SysModuleManagerImpl extends BaseManager implements SysModuleManager {
	private SysModuleDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * @param dao
	 */
	public void setSysModuleDao(SysModuleDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysModuleManager#getSysModules(com.joymain.jecs.sys.model.SysModule)
	 */
	public List getSysModules(final SysModule sysModule) {
		return dao.getSysModules(sysModule);
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysModuleManager#getSysModule(String moduleId)
	 */
	public SysModule getSysModule(final String moduleId) {
		return dao.getSysModule(new Long(moduleId));
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysModuleManager#saveSysModule(SysModule sysModule)
	 */
	public void saveSysModule(SysModule sysModule) {
		dao.saveSysModule(sysModule);
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysModuleManager#removeSysModule(String moduleId)
	 */
	public void removeSysModule(final String moduleId) {
		dao.removeSysModule(new Long(moduleId));
	}

	//added for getSysModulesByCrm
	public List getSysModulesByCrm(CommonRecord crm, Pager pager) {
		return dao.getSysModulesByCrm(crm, pager);
	}

	/**
     * 获取直接下级的模块列表
     * @param moduleId
     * @param orderField
     * @return
     */
    public List getDirectChildModules(final String moduleId, final String orderField){
		if (StringUtils.isEmpty(moduleId)) {
			return dao.getDirectChildModules(new Long(0),orderField);
		} else {
			return dao.getDirectChildModules(new Long(moduleId),orderField);
		}
	}
	
	/**
     * 根据模块编码获取对应的唯一的模块
     * @param moduleCode
     * @return
     */
    public SysModule getSysModuleByCode(final String moduleCode){
    	return dao.getSysModuleByCode(moduleCode);
    }
    
    /**
     * 根据用户获取对应的菜单
     * @param sysUser
     * @return
     */
    public List getSysMenus(final SysUser sysUser,String parentId,String startWithParentId){
    	return dao.getSysMenus(sysUser,parentId, startWithParentId);
    }
    
    /**
     * 获取masterUserCode所管理的模块清单,包含slaveUserCode所管理的模块清单,其所返回的slave_power_id为两者的交集记录ID
     * @param masterUserCode
     * @param slaveUserCode
     * @param companyCode
     * @return
     */
    public List getMyManSysModules(final String masterUserCode, final String slaveUserCode, final String companyCode){
    	return dao.getMyManSysModules(masterUserCode, slaveUserCode, companyCode);
    }
    
    /**
     * 获取某个角色最多可拥有的模块,其中如果已经有相应模块的权限,则rp_id不为空 
     * @param sysRole
     * @return
     */
    public List getSysModulesJoinRole(final SysRole sysRole){
    	return dao.getSysModulesJoinRole(sysRole);
    }
    
    /**
     * 重新生成模块树结构
     */
    public void saveSysModulesRebuild(){
    	dao.saveSysModulesRebuild();
    }
    
    /**
     * 获取所有有权限的模块
     * @param sysUser
     * @param moduleType
     * @return
     */
    public List getSysModules(SysUser sysUser, final Integer moduleType,String parentId,String startWithParentId){
    	return dao.getSysModules(sysUser, moduleType, parentId,startWithParentId);
    }
    
    /**
     * 根据链接URL获取对应的模块列表
     * @param linkUrl
     * @return
     */
    public List getSysModulesByUrl(final String linkUrl){
    	return dao.getSysModulesByUrl(linkUrl);
    }
    
    /**
	 * 根据模块编码和链接地址获取对应的模块
	 * @param moduleCode
	 * @param linkUrl
	 * @return
	 */
	public SysModule getSysModuleByCode(final String moduleCode,final String linkUrl) {
		return dao.getSysModuleByCode(moduleCode, linkUrl);
	}
    
    /**
     * 获取对应用户有权限的模块
     * @param sysUser
     * @return
     */
    public Map<String, String> getSysPowerMap(final SysUser sysUser){
    	Map<String, String> powerMap=new HashMap<String, String>();
		List<HashMap> sysModules=this.getSysModules(sysUser, null,null,null);
		if(sysModules!=null && sysModules.size()>0){
			for(Map moduleMap:sysModules){
				powerMap.put(moduleMap.get("module_code").toString(), moduleMap.get("link_url")==null?"":moduleMap.get("link_url").toString());
			}
		}
		
		return powerMap;
    }
    
    /**
	 * 判断某用户对某模块是否有权限
	 * @param sysUser
	 * @param sysModule
	 * @return
	 */
	public boolean getSysUserPower(SysUser sysUser, SysModule sysModule) {
		return dao.getSysUserPower(sysUser, sysModule);
	}
	
	/**
	 * 判断某用户对某模块是否有权限
	 * @param sysUser
	 * @param moduleCode
	 * @return
	 */
	public boolean getSysUserPowerByCode(SysUser sysUser, final String moduleCode){
		return dao.getSysUserPowerByCode(sysUser, moduleCode);
	}
}