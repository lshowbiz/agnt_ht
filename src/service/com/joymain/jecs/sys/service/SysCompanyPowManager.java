package com.joymain.jecs.sys.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysCompanyPow;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface SysCompanyPowManager extends Manager {
	/**
	 * Retrieves all of the sysCompanyPows
	 */
	public List getSysCompanyPows(SysCompanyPow sysCompanyPow);

	/**
	 * Gets sysCompanyPow's information based on id.
	 * @param id the sysCompanyPow's id
	 * @return sysCompanyPow populated sysCompanyPow object
	 */
	public SysCompanyPow getSysCompanyPow(final String id);

	/**
	 * Saves a sysCompanyPow's information
	 * @param sysCompanyPow the object to be saved
	 */
	public void saveSysCompanyPow(SysCompanyPow sysCompanyPow);

	/**
	 * Removes a sysCompanyPow from the database by id
	 * @param id the sysCompanyPow's id
	 */
	public void removeSysCompanyPow(final String id);

	//added for getSysCompanyPowsByCrm
	public List getSysCompanyPowsByCrm(CommonRecord crm, Pager pager);

	/**
     * 删除某模块下的记录
     * @param moduleId
     */
    public void removeSysCompanyPows(final String moduleId);
    
    /**
     * 删除某模块不在所对应公司中的权限
     * @param moduleId
     * @param companyCodes
     */
    public void removeSysCompanyPowsNotInCompany(final String moduleId, final String[] companyCodes);
    
    /**
     * 根据模块ID和公司获取对应的唯一的权限
     * @param moduleId
     * @param companyCode
     * @return
     */
    public SysCompanyPow getSysCompanyPow(final String moduleId, final String companyCode);
    
    /**
     * 获取某公司所能使用的所有权限
     * @param companyCode
     * @return
     */
    public List getSysCompanyPowsByCompany(final String companyCode);
    
    /**
     * 批量保存公司权限
     * @param sysCompanyPows
     */
    public void saveSysCompanyPows(final List sysCompanyPows);
}