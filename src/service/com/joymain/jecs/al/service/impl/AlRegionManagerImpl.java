
package com.joymain.jecs.al.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.al.model.AlRegion;
import com.joymain.jecs.al.dao.AlRegionDao;
import com.joymain.jecs.al.service.AlRegionManager;

public class AlRegionManagerImpl extends BaseManager implements AlRegionManager {
    private AlRegionDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setAlRegionDao(AlRegionDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.al.service.AlRegionManager#getAlRegions(com.joymain.jecs.al.model.AlRegion)
     */
    public List getAlRegions(final AlRegion alRegion) {
        return dao.getAlRegions(alRegion);
    }

    /**
     * @see com.joymain.jecs.al.service.AlRegionManager#getAlRegion(String regionId)
     */
    public AlRegion getAlRegion(final String regionId) {
        return dao.getAlRegion(new Long(regionId));
    }

    /**
     * @see com.joymain.jecs.al.service.AlRegionManager#saveAlRegion(AlRegion alRegion)
     */
    public void saveAlRegion(AlRegion alRegion) {
        dao.saveAlRegion(alRegion);
    }

    /**
     * @see com.joymain.jecs.al.service.AlRegionManager#removeAlRegion(String regionId)
     */
    public void removeAlRegion(final String regionId) {
        dao.removeAlRegion(new Long(regionId));
    }
}
