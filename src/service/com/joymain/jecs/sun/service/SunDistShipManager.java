
package com.joymain.jecs.sun.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sun.model.SunDistShip;
import com.joymain.jecs.sun.dao.SunDistShipDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface SunDistShipManager extends Manager {
    /**
     * Retrieves all of the sunDistShips
     */
    public List getSunDistShips(SunDistShip sunDistShip);

    /**
     * Gets sunDistShip's information based on sdsId.
     * @param sdsId the sunDistShip's sdsId
     * @return sunDistShip populated sunDistShip object
     */
    public SunDistShip getSunDistShip(final String sdsId);

    /**
     * Saves a sunDistShip's information
     * @param sunDistShip the object to be saved
     */
    public void saveSunDistShip(SunDistShip sunDistShip);

    /**
     * Removes a sunDistShip from the database by sdsId
     * @param sdsId the sunDistShip's sdsId
     */
    public void removeSunDistShip(final String sdsId);
    //added for getSunDistShipsByCrm
    public List getSunDistShipsByCrm(CommonRecord crm, Pager pager);
    public void createSunShipData(CommonRecord crm);
    public void autoCreateSunShipDate();
}

