package com.joymain.jecs.sys.dao;

import java.util.List;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.sys.model.SysUsrTypePow;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface SysUsrTypePowDao extends Dao {

	//	public List getPiProductsByHql(String hql);

	//	public List getPiProductsBySql(String sql);
	/**
	 * Retrieves all of the sysUsrTypePows
	 */
	public List getSysUsrTypePows(SysUsrTypePow sysUsrTypePow);

	/**
	 * Gets sysUsrTypePow's information based on primary key. An ObjectRetrievalFailureException Runtime Exception is thrown if nothing is found.
	 * 
	 * @param id the sysUsrTypePow's id
	 * @return sysUsrTypePow populated sysUsrTypePow object
	 */
	public SysUsrTypePow getSysUsrTypePow(final Long id);

	/**
	 * Saves a sysUsrTypePow's information
	 * @param sysUsrTypePow the object to be saved
	 */
	public void saveSysUsrTypePow(SysUsrTypePow sysUsrTypePow);

	/**
	 * Removes a sysUsrTypePow from the database by id
	 * @param id the sysUsrTypePow's id
	 */
	public void removeSysUsrTypePow(final Long id);

	//added for getSysUsrTypePowsByCrm
	public List getSysUsrTypePowsByCrm(CommonRecord crm, Pager pager);

	/**
     * 删除某模块下的记录
     * @param moduleId
     */
    public void removeSysUsrTypePows(final Long moduleId);
    
    /**
     * 删除某模块不在所对应用户类别中的权限
     * @param moduleId
     * @param userTypes
     */
    public void removeSysUsrTypePowsNotInUsrType(final Long moduleId, final String[] userTypes);
    
    /**
     * 根据模块ID和公司获取对应的唯一的权限
     * @param moduleId
     * @param userType
     * @return
     */
    public SysUsrTypePow getSysUsrTypePow(final Long moduleId, final String userType) ;
}