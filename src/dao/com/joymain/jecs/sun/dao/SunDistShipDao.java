
package com.joymain.jecs.sun.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.sun.model.SunDistShip;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface SunDistShipDao extends Dao {

    /**
     * Retrieves all of the sunDistShips
     */
    public List getSunDistShips(SunDistShip sunDistShip);

    /**
     * Gets sunDistShip's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param sdsId the sunDistShip's sdsId
     * @return sunDistShip populated sunDistShip object
     */
    public SunDistShip getSunDistShip(final Long sdsId);

    /**
     * Saves a sunDistShip's information
     * @param sunDistShip the object to be saved
     */    
    public void saveSunDistShip(SunDistShip sunDistShip);

    /**
     * Removes a sunDistShip from the database by sdsId
     * @param sdsId the sunDistShip's sdsId
     */
    public void removeSunDistShip(final Long sdsId);
    public void createSunShipData(CommonRecord crm);
    //added for getSunDistShipsByCrm
    public List getSunDistShipsByCrm(CommonRecord crm, Pager pager);
    public void autoCreateSunShipDate();
}

