
package com.joymain.jecs.am.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.am.model.AmMessagePermit;
import com.joymain.jecs.am.dao.AmMessagePermitDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface AmMessagePermitManager extends Manager {
    /**
     * Retrieves all of the amMessagePermits
     */
    public List getAmMessagePermits(AmMessagePermit amMessagePermit);

    /**
     * Gets amMessagePermit's information based on ampNo.
     * @param ampNo the amMessagePermit's ampNo
     * @return amMessagePermit populated amMessagePermit object
     */
    public AmMessagePermit getAmMessagePermit(final String ampNo);

    /**
     * Saves a amMessagePermit's information
     * @param amMessagePermit the object to be saved
     */
    public void saveAmMessagePermit(AmMessagePermit amMessagePermit);

    /**
     * Removes a amMessagePermit from the database by ampNo
     * @param ampNo the amMessagePermit's ampNo
     */
    public void removeAmMessagePermit(final String ampNo);
    //added for getAmMessagePermitsByCrm
    public List getAmMessagePermitsByCrm(CommonRecord crm, Pager pager);
    public List getAmMessagePermitsByUserCode(String userCode);
}

