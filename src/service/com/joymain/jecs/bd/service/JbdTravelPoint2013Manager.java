
package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.bd.model.JbdTravelPoint2013;
import com.joymain.jecs.bd.dao.JbdTravelPoint2013Dao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdTravelPoint2013Manager extends Manager {
    /**
     * Retrieves all of the jbdTravelPoint2013s
     */
    public List getJbdTravelPoint2013s(JbdTravelPoint2013 jbdTravelPoint2013);

    /**
     * Gets jbdTravelPoint2013's information based on userCode.
     * @param userCode the jbdTravelPoint2013's userCode
     * @return jbdTravelPoint2013 populated jbdTravelPoint2013 object
     */
    public JbdTravelPoint2013 getJbdTravelPoint2013(final String userCode);

    /**
     * Saves a jbdTravelPoint2013's information
     * @param jbdTravelPoint2013 the object to be saved
     */
    public void saveJbdTravelPoint2013(JbdTravelPoint2013 jbdTravelPoint2013);

    /**
     * Removes a jbdTravelPoint2013 from the database by userCode
     * @param userCode the jbdTravelPoint2013's userCode
     */
    public void removeJbdTravelPoint2013(final String userCode);
    //added for getJbdTravelPoint2013sByCrm
    public List getJbdTravelPoint2013sByCrm(CommonRecord crm, Pager pager);
	public List getJbdTravelPoint2013sByUserCode(String userCode);
}

