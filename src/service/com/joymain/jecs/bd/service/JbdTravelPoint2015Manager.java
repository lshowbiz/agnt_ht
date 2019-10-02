
package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.bd.model.JbdTravelPoint2015;
import com.joymain.jecs.bd.dao.JbdTravelPoint2015Dao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdTravelPoint2015Manager extends Manager {
    /**
     * Retrieves all of the jbdTravelPoint2015s
     */
    public List getJbdTravelPoint2015s(JbdTravelPoint2015 jbdTravelPoint2015);

    /**
     * Gets jbdTravelPoint2015's information based on userCode.
     * @param userCode the jbdTravelPoint2015's userCode
     * @return jbdTravelPoint2015 populated jbdTravelPoint2015 object
     */
    public JbdTravelPoint2015 getJbdTravelPoint2015(final String userCode);

    /**
     * Saves a jbdTravelPoint2015's information
     * @param jbdTravelPoint2015 the object to be saved
     */
    public void saveJbdTravelPoint2015(JbdTravelPoint2015 jbdTravelPoint2015);

    /**
     * Removes a jbdTravelPoint2015 from the database by userCode
     * @param userCode the jbdTravelPoint2015's userCode
     */
    public void removeJbdTravelPoint2015(final String userCode);
    //added for getJbdTravelPoint2015sByCrm
    public List getJbdTravelPoint2015sByCrm(CommonRecord crm, Pager pager);
}

