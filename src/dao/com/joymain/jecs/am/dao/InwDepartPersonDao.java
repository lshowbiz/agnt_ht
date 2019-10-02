
package com.joymain.jecs.am.dao;

import java.util.List;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.am.model.InwDepartPerson;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface InwDepartPersonDao extends Dao {

    /**
     * Retrieves all of the inwDepartPersons
     */
    public List getInwDepartPersons(InwDepartPerson inwDepartPerson);

    /**
     * Gets inwDepartPerson's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the inwDepartPerson's id
     * @return inwDepartPerson populated inwDepartPerson object
     */
    public InwDepartPerson getInwDepartPerson(final Long id);

    /**
     * Saves a inwDepartPerson's information
     * @param inwDepartPerson the object to be saved
     */    
    public void saveInwDepartPerson(InwDepartPerson inwDepartPerson);

    /**
     * Removes a inwDepartPerson from the database by id
     * @param id the inwDepartPerson's id
     */
    public void removeInwDepartPerson(final Long id);
    //added for getInwDepartPersonsByCrm
    public List getInwDepartPersonsByCrm(CommonRecord crm, Pager pager);

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

