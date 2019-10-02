
package com.joymain.jecs.am.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.am.model.InwDepartCompetence;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface InwDepartCompetenceDao extends Dao {

    /**
     * Retrieves all of the inwDepartCompetences
     */
    public List getInwDepartCompetences(InwDepartCompetence inwDepartCompetence);

    /**
     * Gets inwDepartCompetence's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the inwDepartCompetence's id
     * @return inwDepartCompetence populated inwDepartCompetence object
     */
    public InwDepartCompetence getInwDepartCompetence(final Long id);

    /**
     * Saves a inwDepartCompetence's information
     * @param inwDepartCompetence the object to be saved
     */    
    public void saveInwDepartCompetence(InwDepartCompetence inwDepartCompetence);

    /**
     * Removes a inwDepartCompetence from the database by id
     * @param id the inwDepartCompetence's id
     */
    public void removeInwDepartCompetence(final Long id);
    
    /**
     * 部门权限的查询(初始化查询或有条件查询)
     * @author gw  2014-05-27 
     * @param crm
     * @param pager
     * @return list
     */
    public List getInwDepartCompetencesByCrm(CommonRecord crm, Pager pager);

    /**
	 * 根据部门表的主键departmentId查询该部门所拥有建议查看的权限
	 * @author yxzz 2014-07-01
	 * @param departmentId
	 * @return String
	 */
	public String getDemandIdListByDepartmentId(String departmentId);
    
    
}

