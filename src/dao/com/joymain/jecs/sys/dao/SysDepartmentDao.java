package com.joymain.jecs.sys.dao;

import java.util.List;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.sys.model.SysDepartment;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface SysDepartmentDao extends Dao {

	//	public List getPiProductsByHql(String hql);

	//	public List getPiProductsBySql(String sql);
	/**
	 * Retrieves all of the sysDepartments
	 */
	public List getSysDepartments(SysDepartment sysDepartment);

	/**
	 * Gets sysDepartment's information based on primary key. An ObjectRetrievalFailureException Runtime Exception is thrown if nothing is found.
	 * 
	 * @param departmentId the sysDepartment's departmentId
	 * @return sysDepartment populated sysDepartment object
	 */
	public SysDepartment getSysDepartment(final Long departmentId);

	/**
	 * Saves a sysDepartment's information
	 * @param sysDepartment the object to be saved
	 */
	public void saveSysDepartment(SysDepartment sysDepartment);

	/**
	 * Removes a sysDepartment from the database by departmentId
	 * @param departmentId the sysDepartment's departmentId
	 */
	public void removeSysDepartment(final Long departmentId);

	//added for getSysDepartmentsByCrm
	public List getSysDepartmentsByCrm(CommonRecord crm, Pager pager);

	/**
	 * 获取公司下所有的部门
	 * @param companyCode
	 * @return
	 */
	public List getSysDepartmentsByCompany(final String companyCode);
	
	/**
     * 获取在某公司有权限的部门
     * @param companyCode
     * @param sysUser
     * @return
     */
    public List getSysDepartmentsByCompany(final String companyCode, final SysUser sysUser);
	
	/**
     * 获取直接下级的部门列表
     * @param moduleId
     * @param orderField
     * @return
     */
    public List getDirectChildDepartments(final Long departmentId, final String orderField);
    
    /**
     * 获取某用户所管理的人员的所有的部门
     * @param sysUser
     * @param companyCode
     * @param limitCompany 是否限制于指定的公司
     * @return
     */
    public List getMyAllDepartments(final SysUser sysUser, final String companyCode, boolean limitCompany);
    
    /**
     * 重新生成部门树结构
     * @param companyCode
     */
    public void saveSysDepartmentsRebuild(final String companyCode);
}