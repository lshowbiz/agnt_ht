
package com.joymain.jecs.pd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pd.model.PdSendExtra;
import com.joymain.jecs.pd.dao.PdSendExtraDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface PdSendExtraManager extends Manager {
    /**
     * Retrieves all of the pdSendExtras
     */
    public List getPdSendExtras(PdSendExtra pdSendExtra);

    /**
     * Gets pdSendExtra's information based on uniId.
     * @param uniId the pdSendExtra's uniId
     * @return pdSendExtra populated pdSendExtra object
     */
    public PdSendExtra getPdSendExtra(final String uniId);

    /**
     * Saves a pdSendExtra's information
     * @param pdSendExtra the object to be saved
     */
    public void savePdSendExtra(PdSendExtra pdSendExtra);

    /**
     * Removes a pdSendExtra from the database by uniId
     * @param uniId the pdSendExtra's uniId
     */
    public void removePdSendExtra(final String uniId);
    //added for getPdSendExtrasByCrm
    public List getPdSendExtrasByCrm(CommonRecord crm, Pager pager);
}

