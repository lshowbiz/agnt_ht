package com.joymain.jecs.sys.service.impl;

import java.util.List;

import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.dao.SysDepartmentDao;
import com.joymain.jecs.sys.model.SysDepartment;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysDepartmentManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class SysDepartmentManagerImpl extends BaseManager implements SysDepartmentManager {
	private SysDepartmentDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * @param dao
	 */
	public void setSysDepartmentDao(SysDepartmentDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysDepartmentManager#getSysDepartments(com.joymain.jecs.sys.model.SysDepartment)
	 */
	public List getSysDepartments(final SysDepartment sysDepartment) {
		return dao.getSysDepartments(sysDepartment);
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysDepartmentManager#getSysDepartment(String departmentId)
	 */
	public SysDepartment getSysDepartment(final String departmentId) {
		return dao.getSysDepartment(new Long(departmentId));
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysDepartmentManager#saveSysDepartment(SysDepartment sysDepartment)
	 */
	public void saveSysDepartment(SysDepartment sysDepartment) {
		dao.saveSysDepartment(sysDepartment);
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysDepartmentManager#removeSysDepartment(String departmentId)
	 */
	public void removeSysDepartment(final String departmentId) {
		dao.removeSysDepartment(new Long(departmentId));
	}

	//added for getSysDepartmentsByCrm
	public List getSysDepartmentsByCrm(CommonRecord crm, Pager pager) {
		return dao.getSysDepartmentsByCrm(crm, pager);
	}

	/**
	 * 获取公司下所有的部门
	 * @param companyCode
	 * @return
	 */
	public List getSysDepartmentsByCompany(final String companyCode) {
		return dao.getSysDepartmentsByCompany(companyCode);
	}
	
	/**
     * 获取在某公司有权限的部门
     * @param companyCode
     * @param sysUser
     * @return
     */
    public List getSysDepartmentsByCompany(final String companyCode, final SysUser sysUser){
    	return dao.getSysDepartmentsByCompany(companyCode, sysUser);
    }
	
	/**
     * 获取直接下级的部门列表
     * @param moduleId
     * @param orderField
     * @return
     */
    public List getDirectChildDepartments(final String departmentId, final String orderField){
    	return dao.getDirectChildDepartments(new Long(departmentId), orderField);
    }
    
    /**
     * 获取某用户所管理的人员的所有的部门
     * @param sysUser
     * @param companyCode
     * @param limitCompany 是否限制于指定的公司
     * @return
     */
    public List getMyAllDepartments(final SysUser sysUser, final String companyCode, boolean limitCompany){
    	return dao.getMyAllDepartments(sysUser, companyCode, limitCompany);
    }
    
    /**
     * 重新生成部门树结构
     * @param companyCode
     */
    public void saveSysDepartmentsRebuild(final String companyCode){
    	dao.saveSysDepartmentsRebuild(companyCode);
    }
}