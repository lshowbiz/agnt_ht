
package com.joymain.jecs.mi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.mi.model.JmiTaiwanTravel;
import com.joymain.jecs.mi.dao.JmiTaiwanTravelDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JmiTaiwanTravelManager extends Manager {
    /**
     * Retrieves all of the jmiTaiwanTravels
     */
    public List getJmiTaiwanTravels(JmiTaiwanTravel jmiTaiwanTravel);

    /**
     * Gets jmiTaiwanTravel's information based on userCode.
     * @param userCode the jmiTaiwanTravel's userCode
     * @return jmiTaiwanTravel populated jmiTaiwanTravel object
     */
    public JmiTaiwanTravel getJmiTaiwanTravel(final String userCode);

    /**
     * Saves a jmiTaiwanTravel's information
     * @param jmiTaiwanTravel the object to be saved
     */
    public void saveJmiTaiwanTravel(JmiTaiwanTravel jmiTaiwanTravel);

    /**
     * Removes a jmiTaiwanTravel from the database by userCode
     * @param userCode the jmiTaiwanTravel's userCode
     */
    public void removeJmiTaiwanTravel(final String userCode);
    //added for getJmiTaiwanTravelsByCrm
    public List getJmiTaiwanTravelsByCrm(CommonRecord crm, Pager pager);

    public boolean getCheckJmiTaiwanTravelExist(JmiTaiwanTravel jmiTaiwanTravel);
}

