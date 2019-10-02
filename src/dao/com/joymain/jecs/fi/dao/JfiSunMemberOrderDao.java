
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.JfiSunMemberOrder;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JfiSunMemberOrderDao extends Dao {

    /**
     * Retrieves all of the jfiSunMemberOrders
     */
    public List getJfiSunMemberOrders(JfiSunMemberOrder jfiSunMemberOrder);

    /**
     * Gets jfiSunMemberOrder's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param moId the jfiSunMemberOrder's moId
     * @return jfiSunMemberOrder populated jfiSunMemberOrder object
     */
    public JfiSunMemberOrder getJfiSunMemberOrder(final Long moId);

    /**
     * Saves a jfiSunMemberOrder's information
     * @param jfiSunMemberOrder the object to be saved
     */    
    public void saveJfiSunMemberOrder(JfiSunMemberOrder jfiSunMemberOrder);

    public List getJfiSunMemberOrderReportByCrm(CommonRecord crm);

    /**
     * Removes a jfiSunMemberOrder from the database by moId
     * @param moId the jfiSunMemberOrder's moId
     */
    public void removeJfiSunMemberOrder(final Long moId);
    //added for getJfiSunMemberOrdersByCrm
    public List getJfiSunMemberOrdersByCrm(CommonRecord crm, Pager pager);
}

