
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.JfiSunOrderList;
import com.joymain.jecs.fi.dao.JfiSunOrderListDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JfiSunOrderListManager extends Manager {
    /**
     * Retrieves all of the jfiSunOrderLists
     */
    public List getJfiSunOrderLists(JfiSunOrderList jfiSunOrderList);

    /**
     * Gets jfiSunOrderList's information based on molId.
     * @param molId the jfiSunOrderList's molId
     * @return jfiSunOrderList populated jfiSunOrderList object
     */
    public JfiSunOrderList getJfiSunOrderList(final String molId);

    /**
     * Saves a jfiSunOrderList's information
     * @param jfiSunOrderList the object to be saved
     */
    public void saveJfiSunOrderList(JfiSunOrderList jfiSunOrderList);

    /**
     * Removes a jfiSunOrderList from the database by molId
     * @param molId the jfiSunOrderList's molId
     */
    public void removeJfiSunOrderList(final String molId);
    //added for getJfiSunOrderListsByCrm
    public List getJfiSunOrderListsByCrm(CommonRecord crm, Pager pager);
}

