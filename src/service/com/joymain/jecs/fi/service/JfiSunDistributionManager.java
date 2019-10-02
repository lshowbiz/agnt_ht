
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.JfiSunDistribution;
import com.joymain.jecs.fi.dao.JfiSunDistributionDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JfiSunDistributionManager extends Manager {
    /**
     * Retrieves all of the jfiSunDistributions
     */
    public List getJfiSunDistributions(JfiSunDistribution jfiSunDistribution);

    /**
     * Gets jfiSunDistribution's information based on userCode.
     * @param userCode the jfiSunDistribution's userCode
     * @return jfiSunDistribution populated jfiSunDistribution object
     */
    public JfiSunDistribution getJfiSunDistribution(final String userCode);

    /**
     * Saves a jfiSunDistribution's information
     * @param jfiSunDistribution the object to be saved
     */
    public void saveJfiSunDistribution(JfiSunDistribution jfiSunDistribution);

    /**
     * Removes a jfiSunDistribution from the database by userCode
     * @param userCode the jfiSunDistribution's userCode
     */
    public void removeJfiSunDistribution(final String userCode);
    //added for getJfiSunDistributionsByCrm
    public List getJfiSunDistributionsByCrm(CommonRecord crm, Pager pager);
}

