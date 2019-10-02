package com.joymain.jecs.sys.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.dao.SysRolePowerDao;
import com.joymain.jecs.sys.model.SysRolePower;
import com.joymain.jecs.sys.service.SysRolePowerManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class SysRolePowerManagerImpl extends BaseManager implements SysRolePowerManager {
	private SysRolePowerDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * @param dao
	 */
	public void setSysRolePowerDao(SysRolePowerDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysRolePowerManager#getSysRolePowers(com.joymain.jecs.sys.model.SysRolePower)
	 */
	public List getSysRolePowers(final SysRolePower sysRolePower) {
		return dao.getSysRolePowers(sysRolePower);
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysRolePowerManager#getSysRolePower(String rpId)
	 */
	public SysRolePower getSysRolePower(final String rpId) {
		return dao.getSysRolePower(new Long(rpId));
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysRolePowerManager#saveSysRolePower(SysRolePower sysRolePower)
	 */
	public void saveSysRolePower(SysRolePower sysRolePower) {
		dao.saveSysRolePower(sysRolePower);
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysRolePowerManager#removeSysRolePower(String rpId)
	 */
	public void removeSysRolePower(final String rpId) {
		dao.removeSysRolePower(new Long(rpId));
	}

	//added for getSysRolePowersByCrm
	public List getSysRolePowersByCrm(CommonRecord crm, Pager pager) {
		return dao.getSysRolePowersByCrm(crm, pager);
	}

	/**
	 * 获取某一角色对应的记录列表
	 * @param roleId
	 * @return
	 */
	public List getSysRolePowersByRoleId(final String roleId) {
		if (StringUtils.isEmpty(roleId)) {
			return null;
		} else {
			return dao.getSysRolePowersByRoleId(new Long(roleId));
		}
	}
    
    /**
     * 删除角色ID对应的模块权限
     * @param roleId
     */
    public void removeSysRolePowersByRoleId(final String roleId){
    	dao.removeSysRolePowersByRoleId(new Long(roleId));
    }
    
    /**
     * 删除角色指定的模块以外的所有权限
     * @param roleId
     * @param moduleId
     */
    public void removeSysRolePowersNotIn(final String roleId, final String[] moduleId){
    	dao.removeSysRolePowersNotIn(new Long(roleId), moduleId);
    }
    
    /**
     * 批量保存多条记录
     * @param sysRolePowers
     */
    public void saveSysRolePowers(List sysRolePowers){
    	dao.saveSysRolePowers(sysRolePowers);
    }
    
    /**
     * 根据角色和模块获取对应的唯一的记录
     * @param roleId
     * @param moduleId
     * @return
     */
    public SysRolePower getSysRolePower(final String roleId, final String moduleId) {
    	return dao.getSysRolePower(new Long(roleId), new Long(moduleId));
    }
}