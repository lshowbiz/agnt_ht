
package com.joymain.jecs.am.service;

import java.util.List;

import org.springframework.validation.BindException;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.am.model.InwDepartCompetence;
import com.joymain.jecs.am.dao.InwDepartCompetenceDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface InwDepartCompetenceManager extends Manager {
    /**
     * Retrieves all of the inwDepartCompetences
     */
    public List getInwDepartCompetences(InwDepartCompetence inwDepartCompetence);

    /**
     * Gets inwDepartCompetence's information based on id.
     * @param id the inwDepartCompetence's id
     * @return inwDepartCompetence populated inwDepartCompetence object
     */
    public InwDepartCompetence getInwDepartCompetence(final String id);

    /**
     * Saves a inwDepartCompetence's information
     * @param inwDepartCompetence the object to be saved
     */
    public void saveInwDepartCompetence(InwDepartCompetence inwDepartCompetence);

    /**
     * Removes a inwDepartCompetence from the database by id
     * @param id the inwDepartCompetence's id
     */
    public void removeInwDepartCompetence(final String id);
    
    /**
     * 部门权限的查询(初始化查询或有条件查询)
     * @author gw  2014-05-27 
     * @param crm
     * @param pager
     * @return list
     */
    public List getInwDepartCompetencesByCrm(CommonRecord crm, Pager pager);

    /**
     * 部门权限的编辑之前不为空的校验
     * @author gw 2014-05-28
     * @param inwDepartCompetence
     * @param errors
     * @param characterCoding
     * @return boolean
     */
	public boolean getCheckEmptyResult(InwDepartCompetence inwDepartCompetence,BindException errors,String characterCoding);

	/**
	 * 根据部门表的主键departmentId查询该部门所拥有建议查看的权限
	 * @author yxzz 2014-07-01
	 * @param departmentId
	 * @return String
	 */
	public String getDemandIdListByDepartmentId(String departmentId);
		
}

