
package com.joymain.jecs.mi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.mi.model.JmiTaiwanTravel;
import com.joymain.jecs.mi.dao.JmiTaiwanTravelDao;
import com.joymain.jecs.mi.service.JmiTaiwanTravelManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JmiTaiwanTravelManagerImpl extends BaseManager implements JmiTaiwanTravelManager {
    private JmiTaiwanTravelDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJmiTaiwanTravelDao(JmiTaiwanTravelDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiTaiwanTravelManager#getJmiTaiwanTravels(com.joymain.jecs.mi.model.JmiTaiwanTravel)
     */
    public List getJmiTaiwanTravels(final JmiTaiwanTravel jmiTaiwanTravel) {
        return dao.getJmiTaiwanTravels(jmiTaiwanTravel);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiTaiwanTravelManager#getJmiTaiwanTravel(String userCode)
     */
    public JmiTaiwanTravel getJmiTaiwanTravel(final String userCode) {
        return dao.getJmiTaiwanTravel(new String(userCode));
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiTaiwanTravelManager#saveJmiTaiwanTravel(JmiTaiwanTravel jmiTaiwanTravel)
     */
    public void saveJmiTaiwanTravel(JmiTaiwanTravel jmiTaiwanTravel) {
        dao.saveJmiTaiwanTravel(jmiTaiwanTravel);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiTaiwanTravelManager#removeJmiTaiwanTravel(String userCode)
     */
    public void removeJmiTaiwanTravel(final String userCode) {
        dao.removeJmiTaiwanTravel(new String(userCode));
    }
    //added for getJmiTaiwanTravelsByCrm
    public List getJmiTaiwanTravelsByCrm(CommonRecord crm, Pager pager){
	return dao.getJmiTaiwanTravelsByCrm(crm,pager);
    }

	public boolean getCheckJmiTaiwanTravelExist(JmiTaiwanTravel jmiTaiwanTravel) {
		return dao.getCheckJmiTaiwanTravelExist(jmiTaiwanTravel);
	}
}
