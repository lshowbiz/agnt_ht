
package com.joymain.jecs.am.service;

import java.util.List;

import org.springframework.validation.BindException;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.am.model.InwDepartment;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface InwDepartmentManager extends Manager {
    /**
     * Retrieves all of the inwDepartments
     */
    public List getInwDepartments(InwDepartment inwDepartment);

    /**
     * Gets inwDepartment's information based on id.
     * @param id the inwDepartment's id
     * @return inwDepartment populated inwDepartment object
     */
    public InwDepartment getInwDepartment(final String id);

    /**
     * Saves a inwDepartment's information
     * @param inwDepartment the object to be saved
     */
    public void saveInwDepartment(InwDepartment inwDepartment);

    /**
     * Removes a inwDepartment from the database by id
     * @param id the inwDepartment's id
     */
    public void removeInwDepartment(final String id);
    //added for getInwDepartmentsByCrm
    public List getInwDepartmentsByCrm(CommonRecord crm, Pager pager);

    /**
     * 创新共赢的部门录入或编辑之前的不为空的校验
     * @author gw  2014-05-21
     * @param inwDepartment
     * @param errors
     * @param characterCoding
     * @return boolean
     */
	public boolean getCheckInwDepartmentEmpty(InwDepartment inwDepartment,BindException errors,String characterCoding);

	/**
     * 创新共赢的部门录入或编辑之前的部门名称唯一性校验
	 * @author gw  2014-05-21
	 * @param inwDepartment
	 * @return boolean
	 */
	public boolean getNameUniqueCheckResult(InwDepartment inwDepartment);

	/**
     * 创新共赢的部门-----删除
	 * @author gw  2014-05-21
	 * @param inwDepartment
	 * @return boolean
	 */
	public void inwDepartmentRemove(InwDepartment inwDepartment);

	/**
	 * 创新共赢----指定部门---获取部门查询建议的权限(需求)
	 * @author gw 2014-05-30
	 * @param departmentId
	 * @return string 
	 */
	public String getInwDepartmentIdListById(String departmentId);

	/**
	 * 查询出所有部门的信息列表
	 * @author yxzz 2014-07-02
	 * @return list 
	 */
	public List getInwDepartmentList();
	
}

