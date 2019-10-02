
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.JfiAmountDetail;
import com.joymain.jecs.fi.dao.JfiAmountDetailDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JfiAmountDetailManager extends Manager {
    /**
     * Retrieves all of the jfiAmountDetails
     */
    public List getJfiAmountDetails(JfiAmountDetail jfiAmountDetail);

    /**
     * Gets jfiAmountDetail's information based on id.
     * @param id the jfiAmountDetail's id
     * @return jfiAmountDetail populated jfiAmountDetail object
     */
    public JfiAmountDetail getJfiAmountDetail(final String id);

    /**
     * Saves a jfiAmountDetail's information
     * @param jfiAmountDetail the object to be saved
     */
    public void saveJfiAmountDetail(JfiAmountDetail jfiAmountDetail);

    /**
     * Removes a jfiAmountDetail from the database by id
     * @param id the jfiAmountDetail's id
     */
    public void removeJfiAmountDetail(final String id);
    //added for getJfiAmountDetailsByCrm
    public List getJfiAmountDetailsByCrm(CommonRecord crm, Pager pager);
}

