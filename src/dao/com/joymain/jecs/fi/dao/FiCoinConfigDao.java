
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.FiCoinConfig;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface FiCoinConfigDao extends Dao {

    /**
     * Retrieves all of the fiCoinConfigs
     */
    public List getFiCoinConfigs(FiCoinConfig fiCoinConfig);

    /**
     * Gets fiCoinConfig's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param configId the fiCoinConfig's configId
     * @return fiCoinConfig populated fiCoinConfig object
     */
    public FiCoinConfig getFiCoinConfig(final Long configId);

    /**
     * Saves a fiCoinConfig's information
     * @param fiCoinConfig the object to be saved
     */    
    public void saveFiCoinConfig(FiCoinConfig fiCoinConfig);

    /**
     * Removes a fiCoinConfig from the database by configId
     * @param configId the fiCoinConfig's configId
     */
    public void removeFiCoinConfig(final Long configId);
    //added for getFiCoinConfigsByCrm
    public List getFiCoinConfigsByCrm(CommonRecord crm, Pager pager);
}

