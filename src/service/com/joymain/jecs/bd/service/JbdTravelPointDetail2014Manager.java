
package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.bd.model.JbdTravelPointDetail2014;
import com.joymain.jecs.bd.model.JbdTravelPointLog2013;
import com.joymain.jecs.bd.model.JbdTravelPointLog2014;
import com.joymain.jecs.bd.dao.JbdTravelPointDetail2014Dao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdTravelPointDetail2014Manager extends Manager {
    /**
     * Retrieves all of the jbdTravelPointDetail2014s
     */
    public List getJbdTravelPointDetail2014s(JbdTravelPointDetail2014 jbdTravelPointDetail2014);

    /**
     * Gets jbdTravelPointDetail2014's information based on id.
     * @param id the jbdTravelPointDetail2014's id
     * @return jbdTravelPointDetail2014 populated jbdTravelPointDetail2014 object
     */
    public JbdTravelPointDetail2014 getJbdTravelPointDetail2014(final String id);

    /**
     * Saves a jbdTravelPointDetail2014's information
     * @param jbdTravelPointDetail2014 the object to be saved
     */
    public void saveJbdTravelPointDetail2014(JbdTravelPointDetail2014 jbdTravelPointDetail2014);

    /**
     * Removes a jbdTravelPointDetail2014 from the database by id
     * @param id the jbdTravelPointDetail2014's id
     */
    public void removeJbdTravelPointDetail2014(final String id);
    //added for getJbdTravelPointDetail2014sByCrm
    public List getJbdTravelPointDetail2014sByCrm(CommonRecord crm, Pager pager);

    

	public void addJbdTravelPointRecord2014(String userCode,String travelType,SysUser defSysUser) ;
	public void removeJbdTravelPointRecord2014(String userCode,String travelType,SysUser defSysUser,String id);

	public void changeJbdTravelPoint2014(JbdTravelPointLog2014 jbdTravelPointLog2014, SysUser defSysUser);

	public void saveJbdTravelPointDetails(List list,SysUser defSysUser) ;
	
	public void changeJbdTravelPoints(List list,SysUser defSysUser);
}

