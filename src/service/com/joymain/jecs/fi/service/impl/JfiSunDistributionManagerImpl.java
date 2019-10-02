
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.JfiSunDistribution;
import com.joymain.jecs.fi.dao.JfiSunDistributionDao;
import com.joymain.jecs.fi.service.JfiSunDistributionManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JfiSunDistributionManagerImpl extends BaseManager implements JfiSunDistributionManager {
    private JfiSunDistributionDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJfiSunDistributionDao(JfiSunDistributionDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiSunDistributionManager#getJfiSunDistributions(com.joymain.jecs.fi.model.JfiSunDistribution)
     */
    public List getJfiSunDistributions(final JfiSunDistribution jfiSunDistribution) {
        return dao.getJfiSunDistributions(jfiSunDistribution);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiSunDistributionManager#getJfiSunDistribution(String userCode)
     */
    public JfiSunDistribution getJfiSunDistribution(final String userCode) {
        return dao.getJfiSunDistribution(new String(userCode));
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiSunDistributionManager#saveJfiSunDistribution(JfiSunDistribution jfiSunDistribution)
     */
    public void saveJfiSunDistribution(JfiSunDistribution jfiSunDistribution) {
        dao.saveJfiSunDistribution(jfiSunDistribution);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiSunDistributionManager#removeJfiSunDistribution(String userCode)
     */
    public void removeJfiSunDistribution(final String userCode) {
        dao.removeJfiSunDistribution(new String(userCode));
    }
    //added for getJfiSunDistributionsByCrm
    public List getJfiSunDistributionsByCrm(CommonRecord crm, Pager pager){
	return dao.getJfiSunDistributionsByCrm(crm,pager);
    }
}
