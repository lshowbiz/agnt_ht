
package com.joymain.jecs.sun.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sun.model.SunDistShip;
import com.joymain.jecs.sun.dao.SunDistShipDao;
import com.joymain.jecs.sun.service.SunDistShipManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class SunDistShipManagerImpl extends BaseManager implements SunDistShipManager {
    private SunDistShipDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setSunDistShipDao(SunDistShipDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.sun.service.SunDistShipManager#getSunDistShips(com.joymain.jecs.sun.model.SunDistShip)
     */
    public List getSunDistShips(final SunDistShip sunDistShip) {
        return dao.getSunDistShips(sunDistShip);
    }

    /**
     * @see com.joymain.jecs.sun.service.SunDistShipManager#getSunDistShip(String sdsId)
     */
    public SunDistShip getSunDistShip(final String sdsId) {
        return dao.getSunDistShip(new Long(sdsId));
    }

    /**
     * @see com.joymain.jecs.sun.service.SunDistShipManager#saveSunDistShip(SunDistShip sunDistShip)
     */
    public void saveSunDistShip(SunDistShip sunDistShip) {
        dao.saveSunDistShip(sunDistShip);
    }

    /**
     * @see com.joymain.jecs.sun.service.SunDistShipManager#removeSunDistShip(String sdsId)
     */
    public void removeSunDistShip(final String sdsId) {
        dao.removeSunDistShip(new Long(sdsId));
    }
    //added for getSunDistShipsByCrm
    public List getSunDistShipsByCrm(CommonRecord crm, Pager pager){
	return dao.getSunDistShipsByCrm(crm,pager);
	
    }

	public void createSunShipData(CommonRecord crm) {
		// TODO Auto-generated method stub
		dao.createSunShipData(crm);
	}
	public void autoCreateSunShipDate(){
		dao.autoCreateSunShipDate();
	}
}
