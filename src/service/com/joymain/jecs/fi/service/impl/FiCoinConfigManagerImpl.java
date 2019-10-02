
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.FiCoinConfig;
import com.joymain.jecs.fi.dao.FiCoinConfigDao;
import com.joymain.jecs.fi.service.FiCoinConfigManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class FiCoinConfigManagerImpl extends BaseManager implements FiCoinConfigManager {
    private FiCoinConfigDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setFiCoinConfigDao(FiCoinConfigDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.FiCoinConfigManager#getFiCoinConfigs(com.joymain.jecs.fi.model.FiCoinConfig)
     */
    public List getFiCoinConfigs(final FiCoinConfig fiCoinConfig) {
        return dao.getFiCoinConfigs(fiCoinConfig);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiCoinConfigManager#getFiCoinConfig(String configId)
     */
    public FiCoinConfig getFiCoinConfig(final String configId) {
        return dao.getFiCoinConfig(new Long(configId));
    }

    /**
     * @see com.joymain.jecs.fi.service.FiCoinConfigManager#saveFiCoinConfig(FiCoinConfig fiCoinConfig)
     */
    public void saveFiCoinConfig(FiCoinConfig fiCoinConfig) {
        dao.saveFiCoinConfig(fiCoinConfig);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiCoinConfigManager#removeFiCoinConfig(String configId)
     */
    public void removeFiCoinConfig(final String configId) {
        dao.removeFiCoinConfig(new Long(configId));
    }
    //added for getFiCoinConfigsByCrm
    public List getFiCoinConfigsByCrm(CommonRecord crm, Pager pager){
	return dao.getFiCoinConfigsByCrm(crm,pager);
    }
}
