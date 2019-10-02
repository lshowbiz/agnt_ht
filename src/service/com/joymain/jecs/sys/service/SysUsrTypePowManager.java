package com.joymain.jecs.sys.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUsrTypePow;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface SysUsrTypePowManager extends Manager {
	/**
	 * Retrieves all of the sysUsrTypePows
	 */
	public List getSysUsrTypePows(SysUsrTypePow sysUsrTypePow);

	/**
	 * Gets sysUsrTypePow's information based on id.
	 * @param id the sysUsrTypePow's id
	 * @return sysUsrTypePow populated sysUsrTypePow object
	 */
	public SysUsrTypePow getSysUsrTypePow(final String id);

	/**
	 * Saves a sysUsrTypePow's information
	 * @param sysUsrTypePow the object to be saved
	 */
	public void saveSysUsrTypePow(SysUsrTypePow sysUsrTypePow);

	/**
	 * Removes a sysUsrTypePow from the database by id
	 * @param id the sysUsrTypePow's id
	 */
	public void removeSysUsrTypePow(final String id);

	//added for getSysUsrTypePowsByCrm
	public List getSysUsrTypePowsByCrm(CommonRecord crm, Pager pager);

	/**
     * 删除某模块下的记录
     * @param moduleId
     */
    public void removeSysUsrTypePows(final String moduleId);
    
    /**
     * 删除某模块不在所对应用户类别中的权限
     * @param moduleId
     * @param userTypes
     */
    public void removeSysUsrTypePowsNotInUsrType(final String moduleId, final String[] userTypes);
    
    /**
     * 根据模块ID和公司获取对应的唯一的权限
     * @param moduleId
     * @param userType
     * @return
     */
    public SysUsrTypePow getSysUsrTypePow(final String moduleId, final String userType);
}