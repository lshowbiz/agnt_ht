package com.joymain.jecs.sys.service.impl;

import java.util.List;

import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.dao.SysCompanyPowDao;
import com.joymain.jecs.sys.model.SysCompanyPow;
import com.joymain.jecs.sys.service.SysCompanyPowManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class SysCompanyPowManagerImpl extends BaseManager implements SysCompanyPowManager {
	private SysCompanyPowDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * @param dao
	 */
	public void setSysCompanyPowDao(SysCompanyPowDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysCompanyPowManager#getSysCompanyPows(com.joymain.jecs.sys.model.SysCompanyPow)
	 */
	public List getSysCompanyPows(final SysCompanyPow sysCompanyPow) {
		return dao.getSysCompanyPows(sysCompanyPow);
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysCompanyPowManager#getSysCompanyPow(String id)
	 */
	public SysCompanyPow getSysCompanyPow(final String id) {
		return dao.getSysCompanyPow(new Long(id));
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysCompanyPowManager#saveSysCompanyPow(SysCompanyPow sysCompanyPow)
	 */
	public void saveSysCompanyPow(SysCompanyPow sysCompanyPow) {
		dao.saveSysCompanyPow(sysCompanyPow);
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysCompanyPowManager#removeSysCompanyPow(String id)
	 */
	public void removeSysCompanyPow(final String id) {
		dao.removeSysCompanyPow(new Long(id));
	}

	//added for getSysCompanyPowsByCrm
	public List getSysCompanyPowsByCrm(CommonRecord crm, Pager pager) {
		return dao.getSysCompanyPowsByCrm(crm, pager);
	}

	/**
     * 删除某模块下的记录
     * @param moduleId
     */
    public void removeSysCompanyPows(final String moduleId){
		dao.removeSysCompanyPows(new Long(moduleId));
	}
    
    /**
     * 删除某模块不在所对应公司中的权限
     * @param moduleId
     * @param companyCodes
     */
    public void removeSysCompanyPowsNotInCompany(final String moduleId, final String[] companyCodes){
    	dao.removeSysCompanyPowsNotInCompany(new Long(moduleId), companyCodes);
    }
    
    /**
     * 根据模块ID和公司获取对应的唯一的权限
     * @param moduleId
     * @param companyCode
     * @return
     */
    public SysCompanyPow getSysCompanyPow(final String moduleId, final String companyCode) {
    	return dao.getSysCompanyPow(new Long(moduleId), companyCode);
    }
    
    /**
     * 获取某公司所能使用的所有权限
     * @param companyCode
     * @return
     */
    public List getSysCompanyPowsByCompany(final String companyCode) {
    	return dao.getSysCompanyPowsByCompany(companyCode);
    }
    
    /**
     * 批量保存公司权限
     * @param sysCompanyPows
     */
    public void saveSysCompanyPows(final List sysCompanyPows){
    	dao.saveSysCompanyPows(sysCompanyPows);
    }
}