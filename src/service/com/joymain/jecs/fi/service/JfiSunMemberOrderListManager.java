
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.JfiSunMemberOrderList;
import com.joymain.jecs.fi.dao.JfiSunMemberOrderListDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JfiSunMemberOrderListManager extends Manager {
    /**
     * Retrieves all of the jfiSunMemberOrderLists
     */
    public List getJfiSunMemberOrderLists(JfiSunMemberOrderList jfiSunMemberOrderList);

    /**
     * Gets jfiSunMemberOrderList's information based on molId.
     * @param molId the jfiSunMemberOrderList's molId
     * @return jfiSunMemberOrderList populated jfiSunMemberOrderList object
     */
    public JfiSunMemberOrderList getJfiSunMemberOrderList(final String molId);

    /**
     * Saves a jfiSunMemberOrderList's information
     * @param jfiSunMemberOrderList the object to be saved
     */
    public void saveJfiSunMemberOrderList(JfiSunMemberOrderList jfiSunMemberOrderList);

    /**
     * Removes a jfiSunMemberOrderList from the database by molId
     * @param molId the jfiSunMemberOrderList's molId
     */
    public void removeJfiSunMemberOrderList(final String molId);
    //added for getJfiSunMemberOrderListsByCrm
    public List getJfiSunMemberOrderListsByCrm(CommonRecord crm, Pager pager);
}

