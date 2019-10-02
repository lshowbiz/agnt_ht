
package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.bd.model.JbdTravelPointLog2015;
import com.joymain.jecs.bd.dao.JbdTravelPointLog2015Dao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdTravelPointLog2015Manager extends Manager {
    /**
     * Retrieves all of the jbdTravelPointLog2015s
     */
    public List getJbdTravelPointLog2015s(JbdTravelPointLog2015 jbdTravelPointLog2015);

    /**
     * Gets jbdTravelPointLog2015's information based on logId.
     * @param logId the jbdTravelPointLog2015's logId
     * @return jbdTravelPointLog2015 populated jbdTravelPointLog2015 object
     */
    public JbdTravelPointLog2015 getJbdTravelPointLog2015(final String logId);

    /**
     * Saves a jbdTravelPointLog2015's information
     * @param jbdTravelPointLog2015 the object to be saved
     */
    public void saveJbdTravelPointLog2015(JbdTravelPointLog2015 jbdTravelPointLog2015);

    /**
     * Removes a jbdTravelPointLog2015 from the database by logId
     * @param logId the jbdTravelPointLog2015's logId
     */
    public void removeJbdTravelPointLog2015(final String logId);
    //added for getJbdTravelPointLog2015sByCrm
    public List getJbdTravelPointLog2015sByCrm(CommonRecord crm, Pager pager);
    public void changeJbdTravelPoint2015(JbdTravelPointLog2015 jbdTravelPointLog2015, SysUser defSysUser);
}

