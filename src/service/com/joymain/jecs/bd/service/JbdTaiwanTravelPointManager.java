
package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.bd.model.JbdTaiwanTravelPoint;
import com.joymain.jecs.bd.dao.JbdTaiwanTravelPointDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdTaiwanTravelPointManager extends Manager {
    /**
     * Retrieves all of the jbdTaiwanTravelPoints
     */
    public List getJbdTaiwanTravelPoints(JbdTaiwanTravelPoint jbdTaiwanTravelPoint);

    /**
     * Gets jbdTaiwanTravelPoint's information based on id.
     * @param id the jbdTaiwanTravelPoint's id
     * @return jbdTaiwanTravelPoint populated jbdTaiwanTravelPoint object
     */
    public JbdTaiwanTravelPoint getJbdTaiwanTravelPoint(final String id);

    /**
     * Saves a jbdTaiwanTravelPoint's information
     * @param jbdTaiwanTravelPoint the object to be saved
     */
    public void saveJbdTaiwanTravelPoint(JbdTaiwanTravelPoint jbdTaiwanTravelPoint);

    /**
     * Removes a jbdTaiwanTravelPoint from the database by id
     * @param id the jbdTaiwanTravelPoint's id
     */
    public void removeJbdTaiwanTravelPoint(final String id);
    //added for getJbdTaiwanTravelPointsByCrm
    public List getJbdTaiwanTravelPointsByCrm(CommonRecord crm, Pager pager);
}

