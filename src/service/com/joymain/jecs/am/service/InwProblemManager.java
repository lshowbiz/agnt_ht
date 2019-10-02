
package com.joymain.jecs.am.service;

import java.util.List;

import org.springframework.validation.BindException;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.am.model.InwProblem;
import com.joymain.jecs.am.dao.InwProblemDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface InwProblemManager extends Manager {
    /**
     * Retrieves all of the inwProblems
     */
    public List getInwProblems(InwProblem inwProblem);

    /**
     * Gets inwProblem's information based on id.
     * @param id the inwProblem's id
     * @return inwProblem populated inwProblem object
     */
    public InwProblem getInwProblem(final String id);

    /**
     * Saves a inwProblem's information
     * @param inwProblem the object to be saved
     */
    public void saveInwProblem(InwProblem inwProblem);

    /**
     * Removes a inwProblem from the database by id
     * @param id the inwProblem's id
     */
    public void removeInwProblem(final String id);
    //added for getInwProblemsByCrm
    public List getInwProblemsByCrm(CommonRecord crm, Pager pager);

    /**
     * 创新共赢的共赢帮助的查询
     * @author gw  2013-08-26
     * @param crm
     * @param pager
     * @return　list
     */
	public List getInwProblemList(CommonRecord crm, Pager pager);
	
	/**
     * 创新共赢的共赢帮助的审核(审核过的那些问题可以在前台显示)
     * @author gw  2013-08-28
     * @param idList
     */
	public void inwProblemAudit(String idList);

	/**
	 * 创新共赢的共赢帮助 录入功能之前的不为空的校验
	 * @author gw 2013-09-16
	 * @param inwProblem
	 * @param errors
	 * @param defCharacterCoding
	 * @return boolean
	 */
	public boolean getEmptyCheck(InwProblem inwProblem, BindException errors,String defCharacterCoding);
			
}

