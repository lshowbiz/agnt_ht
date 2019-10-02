
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.JfiSunMemberOrder;
import com.joymain.jecs.fi.dao.JfiSunMemberOrderDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JfiSunMemberOrderManager extends Manager {
    /**
     * Retrieves all of the jfiSunMemberOrders
     */
    public List getJfiSunMemberOrders(JfiSunMemberOrder jfiSunMemberOrder);

    /**
     * Gets jfiSunMemberOrder's information based on moId.
     * @param moId the jfiSunMemberOrder's moId
     * @return jfiSunMemberOrder populated jfiSunMemberOrder object
     */
    public JfiSunMemberOrder getJfiSunMemberOrder(final String moId);

    public List getJfiSunMemberOrderReportByCrm(CommonRecord crm);

    /**
     * Saves a jfiSunMemberOrder's information
     * @param jfiSunMemberOrder the object to be saved
     */
    public void saveJfiSunMemberOrder(JfiSunMemberOrder jfiSunMemberOrder);

    /**
     * Removes a jfiSunMemberOrder from the database by moId
     * @param moId the jfiSunMemberOrder's moId
     */
    public void removeJfiSunMemberOrder(final String moId);
    //added for getJfiSunMemberOrdersByCrm
    public List getJfiSunMemberOrdersByCrm(CommonRecord crm, Pager pager);
}

