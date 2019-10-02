
package com.joymain.jecs.pr.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pr.model.JprRefundList;
import com.joymain.jecs.pr.dao.JprRefundListDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JprRefundListManager extends Manager {
    /**
     * Retrieves all of the jprRefundLists
     */
    public List getJprRefundLists(JprRefundList jprRefundList);

    /**
     * Gets jprRefundList's information based on prlId.
     * @param prlId the jprRefundList's prlId
     * @return jprRefundList populated jprRefundList object
     */
    public JprRefundList getJprRefundList(final String prlId);

    /**
     * Saves a jprRefundList's information
     * @param jprRefundList the object to be saved
     */
    public void saveJprRefundList(JprRefundList jprRefundList);

    /**
     * Removes a jprRefundList from the database by prlId
     * @param prlId the jprRefundList's prlId
     */
    public void removeJprRefundList(final String prlId);
    //added for getJprRefundListsByCrm
    public List getJprRefundListsByCrm(CommonRecord crm, Pager pager);
}

