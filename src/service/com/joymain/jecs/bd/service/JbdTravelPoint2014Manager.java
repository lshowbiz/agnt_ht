
package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.bd.model.JbdTravelPoint2014;
import com.joymain.jecs.bd.dao.JbdTravelPoint2014Dao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdTravelPoint2014Manager extends Manager {
    /**
     * Retrieves all of the jbdTravelPoint2014s
     */
    public List getJbdTravelPoint2014s(JbdTravelPoint2014 jbdTravelPoint2014);

    /**
     * Gets jbdTravelPoint2014's information based on userCode.
     * @param userCode the jbdTravelPoint2014's userCode
     * @return jbdTravelPoint2014 populated jbdTravelPoint2014 object
     */
    public JbdTravelPoint2014 getJbdTravelPoint2014(final String userCode);

    /**
     * Saves a jbdTravelPoint2014's information
     * @param jbdTravelPoint2014 the object to be saved
     */
    public void saveJbdTravelPoint2014(JbdTravelPoint2014 jbdTravelPoint2014);

    /**
     * Removes a jbdTravelPoint2014 from the database by userCode
     * @param userCode the jbdTravelPoint2014's userCode
     */
    public void removeJbdTravelPoint2014(final String userCode);
    //added for getJbdTravelPoint2014sByCrm
    public List getJbdTravelPoint2014sByCrm(CommonRecord crm, Pager pager);

	public List getJbdTravelPoint2014sByUserCode(String userCode) ;
}

