package com.joymain.jecs.sys.dao;

import java.util.List;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.sys.model.SysManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface SysManagerDao extends Dao {

	//	public List getPiProductsByHql(String hql);

	//	public List getPiProductsBySql(String sql);
	/**
	 * Retrieves all of the sysManagers
	 */
	public List getSysManagers(SysManager sysManager);

	/**
	 * Gets sysManager's information based on primary key. An ObjectRetrievalFailureException Runtime Exception is thrown if nothing is found.
	 * 
	 * @param userCode the sysManager's userCode
	 * @return sysManager populated sysManager object
	 */
	public SysManager getSysManager(final String userCode);

	/**
	 * Saves a sysManager's information
	 * @param sysManager the object to be saved
	 */
	public void saveSysManager(SysManager sysManager);

	/**
	 * Removes a sysManager from the database by userCode
	 * @param userCode the sysManager's userCode
	 */
	public void removeSysManager(final String userCode);

	//added for getSysManagersByCrm
	public List getSysManagersByCrm(CommonRecord crm, Pager pager);
	
	/**
     * 获取部门下所有人员
     * @param departmentId
     * @return
     */
    public List getSysManagersByDepartment(final Long departmentId);
}