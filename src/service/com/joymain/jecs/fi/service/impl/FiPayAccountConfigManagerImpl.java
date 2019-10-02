
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.FiPayAccountConfig;
import com.joymain.jecs.fi.dao.FiPayAccountConfigDao;
import com.joymain.jecs.fi.service.FiPayAccountConfigManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class FiPayAccountConfigManagerImpl extends BaseManager implements FiPayAccountConfigManager {
    private FiPayAccountConfigDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setFiPayAccountConfigDao(FiPayAccountConfigDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.FiPayAccountConfigManager#getFiPayAccountConfigs(com.joymain.jecs.fi.model.FiPayAccountConfig)
     */
    public List getFiPayAccountConfigs(final FiPayAccountConfig fiPayAccountConfig) {
        return dao.getFiPayAccountConfigs(fiPayAccountConfig);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiPayAccountConfigManager#getFiPayAccountConfig(String province)
     */
    public FiPayAccountConfig getFiPayAccountConfig(final String province) {
        return dao.getFiPayAccountConfig(new String(province));
    }

    /**
     * @see com.joymain.jecs.fi.service.FiPayAccountConfigManager#saveFiPayAccountConfig(FiPayAccountConfig fiPayAccountConfig)
     */
    public void saveFiPayAccountConfig(FiPayAccountConfig fiPayAccountConfig) {
        dao.saveFiPayAccountConfig(fiPayAccountConfig);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiPayAccountConfigManager#removeFiPayAccountConfig(String province)
     */
    public void removeFiPayAccountConfig(final String province) {
        dao.removeFiPayAccountConfig(new String(province));
    }
    //added for getFiPayAccountConfigsByCrm
    public List getFiPayAccountConfigsByCrm(CommonRecord crm, Pager pager){
	return dao.getFiPayAccountConfigsByCrm(crm,pager);
    }

	@Override
	public void saveFiPayAccountConfigs(List<FiPayAccountConfig> list) {
		
		for(int i=0;i<list.size();i++){
			
			FiPayAccountConfig fiPayAccountConfig = list.get(i);
			
			this.saveFiPayAccountConfig(fiPayAccountConfig);
		}
	}
}
