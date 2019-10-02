package com.joymain.jecs.sys.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.dao.SysUsrTypePowDao;
import com.joymain.jecs.sys.model.SysUsrTypePow;
import com.joymain.jecs.sys.service.SysUsrTypePowManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class SysUsrTypePowManagerImpl extends BaseManager implements SysUsrTypePowManager {
	private SysUsrTypePowDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * @param dao
	 */
	public void setSysUsrTypePowDao(SysUsrTypePowDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysUsrTypePowManager#getSysUsrTypePows(com.joymain.jecs.sys.model.SysUsrTypePow)
	 */
	public List getSysUsrTypePows(final SysUsrTypePow sysUsrTypePow) {
		return dao.getSysUsrTypePows(sysUsrTypePow);
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysUsrTypePowManager#getSysUsrTypePow(String id)
	 */
	public SysUsrTypePow getSysUsrTypePow(final String id) {
		return dao.getSysUsrTypePow(new Long(id));
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysUsrTypePowManager#saveSysUsrTypePow(SysUsrTypePow sysUsrTypePow)
	 */
	public void saveSysUsrTypePow(SysUsrTypePow sysUsrTypePow) {
		dao.saveSysUsrTypePow(sysUsrTypePow);
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysUsrTypePowManager#removeSysUsrTypePow(String id)
	 */
	public void removeSysUsrTypePow(final String id) {
		dao.removeSysUsrTypePow(new Long(id));
	}

	//added for getSysUsrTypePowsByCrm
	public List getSysUsrTypePowsByCrm(CommonRecord crm, Pager pager) {
		return dao.getSysUsrTypePowsByCrm(crm, pager);
	}

	/**
     * 删除某模块下的记录
     * @param moduleId
     */
    public void removeSysUsrTypePows(final String moduleId){
		dao.removeSysUsrTypePows(new Long(moduleId));
	}
    
    /**
     * 删除某模块不在所对应用户类别中的权限
     * @param moduleId
     * @param userTypes
     */
    public void removeSysUsrTypePowsNotInUsrType(final String moduleId, final String[] userTypes){
    	dao.removeSysUsrTypePowsNotInUsrType(new Long(moduleId), userTypes);
    }
    
    /**
     * 根据模块ID和公司获取对应的唯一的权限
     * @param moduleId
     * @param userType
     * @return
     */
    public SysUsrTypePow getSysUsrTypePow(final String moduleId, final String userType) {
    	return dao.getSysUsrTypePow(new Long(moduleId), userType);
    }
}