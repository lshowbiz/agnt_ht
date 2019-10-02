
package com.joymain.jecs.al.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.al.model.AlRegion;
import com.joymain.jecs.al.dao.AlRegionDao;

public interface AlRegionManager extends Manager {
    /**
     * Retrieves all of the alRegions
     */
    public List getAlRegions(AlRegion alRegion);

    /**
     * Gets alRegion's information based on regionId.
     * @param regionId the alRegion's regionId
     * @return alRegion populated alRegion object
     */
    public AlRegion getAlRegion(final String regionId);

    /**
     * Saves a alRegion's information
     * @param alRegion the object to be saved
     */
    public void saveAlRegion(AlRegion alRegion);

    /**
     * Removes a alRegion from the database by regionId
     * @param regionId the alRegion's regionId
     */
    public void removeAlRegion(final String regionId);
}

