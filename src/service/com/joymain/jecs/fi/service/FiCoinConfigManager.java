
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.FiCoinConfig;
import com.joymain.jecs.fi.dao.FiCoinConfigDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface FiCoinConfigManager extends Manager {
    /**
     * Retrieves all of the fiCoinConfigs
     */
    public List getFiCoinConfigs(FiCoinConfig fiCoinConfig);

    /**
     * Gets fiCoinConfig's information based on configId.
     * @param configId the fiCoinConfig's configId
     * @return fiCoinConfig populated fiCoinConfig object
     */
    public FiCoinConfig getFiCoinConfig(final String configId);

    /**
     * Saves a fiCoinConfig's information
     * @param fiCoinConfig the object to be saved
     */
    public void saveFiCoinConfig(FiCoinConfig fiCoinConfig);

    /**
     * Removes a fiCoinConfig from the database by configId
     * @param configId the fiCoinConfig's configId
     */
    public void removeFiCoinConfig(final String configId);
    //added for getFiCoinConfigsByCrm
    public List getFiCoinConfigsByCrm(CommonRecord crm, Pager pager);
}

