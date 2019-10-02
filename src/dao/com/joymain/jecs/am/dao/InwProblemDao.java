
package com.joymain.jecs.am.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.am.model.InwProblem;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface InwProblemDao extends Dao {

    /**
     * Retrieves all of the inwProblems
     */
    public List getInwProblems(InwProblem inwProblem);

    /**
     * Gets inwProblem's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the inwProblem's id
     * @return inwProblem populated inwProblem object
     */
    public InwProblem getInwProblem(final Long id);

    /**
     * Saves a inwProblem's information
     * @param inwProblem the object to be saved
     */    
    public void saveInwProblem(InwProblem inwProblem);

    /**
     * Removes a inwProblem from the database by id
     * @param id the inwProblem's id
     */
    public void removeInwProblem(final Long id);
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
}

