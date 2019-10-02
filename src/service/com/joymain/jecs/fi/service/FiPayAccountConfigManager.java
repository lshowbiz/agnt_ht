
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.FiPayAccountConfig;
import com.joymain.jecs.fi.dao.FiPayAccountConfigDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface FiPayAccountConfigManager extends Manager {
    /**
     * Retrieves all of the fiPayAccountConfigs
     */
    public List getFiPayAccountConfigs(FiPayAccountConfig fiPayAccountConfig);

    /**
     * Gets fiPayAccountConfig's information based on province.
     * @param province the fiPayAccountConfig's province
     * @return fiPayAccountConfig populated fiPayAccountConfig object
     */
    public FiPayAccountConfig getFiPayAccountConfig(final String province);

    /**
     * Saves a fiPayAccountConfig's information
     * @param fiPayAccountConfig the object to be saved
     */
    public void saveFiPayAccountConfig(FiPayAccountConfig fiPayAccountConfig);

    /**
     * Removes a fiPayAccountConfig from the database by province
     * @param province the fiPayAccountConfig's province
     */
    public void removeFiPayAccountConfig(final String province);
    //added for getFiPayAccountConfigsByCrm
    public List getFiPayAccountConfigsByCrm(CommonRecord crm, Pager pager);
    
    public void saveFiPayAccountConfigs(List<FiPayAccountConfig> list);
}

