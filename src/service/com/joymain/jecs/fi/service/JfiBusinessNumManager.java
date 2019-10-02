
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.JfiBusinessNum;
import com.joymain.jecs.fi.dao.JfiBusinessNumDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JfiBusinessNumManager extends Manager {
    /**
     * Retrieves all of the jfiBusinessNums
     */
    public List getJfiBusinessNums(JfiBusinessNum jfiBusinessNum);

    /**
     * Gets jfiBusinessNum's information based on businessId.
     * @param businessId the jfiBusinessNum's businessId
     * @return jfiBusinessNum populated jfiBusinessNum object
     */
    public JfiBusinessNum getJfiBusinessNum(final String businessId);

    /**
     * Saves a jfiBusinessNum's information
     * @param jfiBusinessNum the object to be saved
     */
    public void saveJfiBusinessNum(JfiBusinessNum jfiBusinessNum);

    /**
     * Removes a jfiBusinessNum from the database by businessId
     * @param businessId the jfiBusinessNum's businessId
     */
    public void removeJfiBusinessNum(final String businessId);
    //added for getJfiBusinessNumsByCrm
    public List getJfiBusinessNumsByCrm(CommonRecord crm, Pager pager);
}

