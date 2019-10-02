
package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.bd.model.JbdTravelPointDetail;
import com.joymain.jecs.bd.model.JbdTravelPointLog;
import com.joymain.jecs.bd.dao.JbdTravelPointDetailDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdTravelPointDetailManager extends Manager {
    /**
     * Retrieves all of the jbdTravelPointDetails
     */
    public List getJbdTravelPointDetails(JbdTravelPointDetail jbdTravelPointDetail);

    /**
     * Gets jbdTravelPointDetail's information based on id.
     * @param id the jbdTravelPointDetail's id
     * @return jbdTravelPointDetail populated jbdTravelPointDetail object
     */
    public JbdTravelPointDetail getJbdTravelPointDetail(final String id);

    /**
     * Saves a jbdTravelPointDetail's information
     * @param jbdTravelPointDetail the object to be saved
     */
    public void saveJbdTravelPointDetail(JbdTravelPointDetail jbdTravelPointDetail);

    /**
     * Removes a jbdTravelPointDetail from the database by id
     * @param id the jbdTravelPointDetail's id
     */
    public void removeJbdTravelPointDetail(final String id);
    //added for getJbdTravelPointDetailsByCrm
    public List getJbdTravelPointDetailsByCrm(CommonRecord crm, Pager pager);
    
    
    public void addJbdTravelPointRecord(String userCode,String travelType,SysUser defSysUser);
    public void removeJbdTravelPointRecord(String userCode,String travelType,SysUser defSysUser,String id);
    
    
    public void changeJbdTravelPoint(JbdTravelPointLog jbdTravelPointLog, SysUser defSysUser);
}

