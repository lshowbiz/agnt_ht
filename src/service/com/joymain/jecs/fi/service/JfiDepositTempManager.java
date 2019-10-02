
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.JfiDepositTemp;
import com.joymain.jecs.fi.dao.JfiDepositTempDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JfiDepositTempManager extends Manager {
    /**
     * Retrieves all of the jfiDepositTemps
     */
    public List getJfiDepositTemps(JfiDepositTemp jfiDepositTemp);

    /**
     * Gets jfiDepositTemp's information based on tempId.
     * @param tempId the jfiDepositTemp's tempId
     * @return jfiDepositTemp populated jfiDepositTemp object
     */
    public JfiDepositTemp getJfiDepositTemp(final String tempId);

    /**
     * Saves a jfiDepositTemp's information
     * @param jfiDepositTemp the object to be saved
     */
    public void saveJfiDepositTemp(JfiDepositTemp jfiDepositTemp);

    /**
     * Removes a jfiDepositTemp from the database by tempId
     * @param tempId the jfiDepositTemp's tempId
     */
    public void removeJfiDepositTemp(final String tempId);
    //added for getJfiDepositTempsByCrm
    public List getJfiDepositTempsByCrm(CommonRecord crm, Pager pager);
}

