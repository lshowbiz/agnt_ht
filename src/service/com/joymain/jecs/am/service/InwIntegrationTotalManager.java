
package com.joymain.jecs.am.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.am.model.InwIntegrationTotal;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface InwIntegrationTotalManager extends Manager {
    /**
     * Retrieves all of the inwIntegrationTotals
     */
    public List getInwIntegrationTotals(InwIntegrationTotal inwIntegrationTotal);

    /**
     * Gets inwIntegrationTotal's information based on id.
     * @param id the inwIntegrationTotal's id
     * @return inwIntegrationTotal populated inwIntegrationTotal object
     */
    public InwIntegrationTotal getInwIntegrationTotal(final String id);

    /**
     * Saves a inwIntegrationTotal's information
     * @param inwIntegrationTotal the object to be saved
     */
    public void saveInwIntegrationTotal(InwIntegrationTotal inwIntegrationTotal);

    /**
     * Removes a inwIntegrationTotal from the database by id
     * @param id the inwIntegrationTotal's id
     */
    public void removeInwIntegrationTotal(final String id);
    //added for getInwIntegrationTotalsByCrm
    public List getInwIntegrationTotalsByCrm(CommonRecord crm, Pager pager);
    
    /**
     * 用户减掉积分的操作
     * @author yxzz  2014-06-05
     * @param userCode
     * @param integratotal
     * @param uniqueCode
     * @return boolean
     */
    public boolean minusIntegrationTotal(String userCode,String integratotal,String uniqueCode);

    /**
     * 建议回复时给于积分对总积分的同步操作
     * @author yxzz  2014-06-05
     * @param userCode
     * @param parseInt
     * @return 
     */
	public void updateOrSaveInwIntegrationTotal(String userCode, int parseInt);
    
}

