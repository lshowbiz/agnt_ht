
package com.joymain.jecs.al.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.al.model.AlRegion;

public interface AlRegionDao extends Dao {

    /**
     * Retrieves all of the alRegions
     */
    public List getAlRegions(AlRegion alRegion);

    /**
     * Gets alRegion's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param regionId the alRegion's regionId
     * @return alRegion populated alRegion object
     */
    public AlRegion getAlRegion(final Long regionId);

    /**
     * Saves a alRegion's information
     * @param alRegion the object to be saved
     */    
    public void saveAlRegion(AlRegion alRegion);

    /**
     * Removes a alRegion from the database by regionId
     * @param regionId the alRegion's regionId
     */
    public void removeAlRegion(final Long regionId);
}

