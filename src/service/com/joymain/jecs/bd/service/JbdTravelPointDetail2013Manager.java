
package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.bd.model.JbdTravelPointDetail2013;
import com.joymain.jecs.bd.model.JbdTravelPointLog2013;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdTravelPointDetail2013Manager extends Manager {
    /**
     * Retrieves all of the jbdTravelPointDetail2013s
     */
    public List getJbdTravelPointDetail2013s(JbdTravelPointDetail2013 jbdTravelPointDetail2013);

    /**
     * Gets jbdTravelPointDetail2013's information based on id.
     * @param id the jbdTravelPointDetail2013's id
     * @return jbdTravelPointDetail2013 populated jbdTravelPointDetail2013 object
     */
    public JbdTravelPointDetail2013 getJbdTravelPointDetail2013(final String id);

    /**
     * Saves a jbdTravelPointDetail2013's information
     * @param jbdTravelPointDetail2013 the object to be saved
     */
    public void saveJbdTravelPointDetail2013(JbdTravelPointDetail2013 jbdTravelPointDetail2013);

    /**
     * Removes a jbdTravelPointDetail2013 from the database by id
     * @param id the jbdTravelPointDetail2013's id
     */
    public void removeJbdTravelPointDetail2013(final String id);
    //added for getJbdTravelPointDetail2013sByCrm
    public List getJbdTravelPointDetail2013sByCrm(CommonRecord crm, Pager pager);
	public void addJbdTravelPointRecord2013(String userCode,String travelType,SysUser defSysUser) ;
	public void removeJbdTravelPointRecord2013(String userCode,String travelType,SysUser defSysUser,String id);

	public void changeJbdTravelPoint2013(JbdTravelPointLog2013 jbdTravelPointLog2013, SysUser defSysUser);

	public void saveJbdTravelPointDetails(List list,SysUser defSysUser) ;
	
	public void changeJbdTravelPoints(List list,SysUser defSysUser);
}

