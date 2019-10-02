
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.FiPayAccountConfig;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface FiPayAccountConfigDao extends Dao {

    /**
     * Retrieves all of the fiPayAccountConfigs
     */
    public List getFiPayAccountConfigs(FiPayAccountConfig fiPayAccountConfig);

    /**
     * Gets fiPayAccountConfig's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
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
}

