
package com.joymain.jecs.am.service;

import java.util.List;

import org.springframework.validation.BindException;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.am.model.InwDepartPerson;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface InwDepartPersonManager extends Manager {
    /**
     * Retrieves all of the inwDepartPersons
     */
    public List getInwDepartPersons(InwDepartPerson inwDepartPerson);

    /**
     * Gets inwDepartPerson's information based on id.
     * @param id the inwDepartPerson's id
     * @return inwDepartPerson populated inwDepartPerson object
     */
    public InwDepartPerson getInwDepartPerson(final String id);

    /**
     * Saves a inwDepartPerson's information
     * @param inwDepartPerson the object to be saved
     */
    public void saveInwDepartPerson(InwDepartPerson inwDepartPerson);

    /**
     * Removes a inwDepartPerson from the database by id
     * @param id the inwDepartPerson's id
     */
    public void removeInwDepartPerson(final String id);
    //added for getInwDepartPersonsByCrm
    public List getInwDepartPersonsByCrm(CommonRecord crm, Pager pager);

    /**
     * 部门人员编辑之前不为空的校验
     * @author yxzz 2014-07-02
     * @param inwDepartPerson
     * @param errors
     * @param characterCoding
     * @return boolean
     */
	public boolean getCheckEmpty(InwDepartPerson inwDepartPerson,BindException errors, String characterCoding);

	/**
	 * 获取部门表的主键
	 * @author yxzz 2014-07-02
	 * @param userCodeHD  会员编号(登录系统的账号)
	 * @return string 
	 */
	public String getDepartmentId(String userCodeHD);

	/**
	 * 会员编号唯一性校验
	 * @author yxzz 2014-07-02
	 * @param inwDepartPerson
	 * @return boolean 
	 */
	public boolean getUserCodeUniqueCheckResult(InwDepartPerson inwDepartPerson);
    
    
}

