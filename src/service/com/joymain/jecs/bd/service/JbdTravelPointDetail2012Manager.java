
package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.bd.model.JbdTravelPointDetail2012;
import com.joymain.jecs.bd.model.JbdTravelPointLog2012;
import com.joymain.jecs.bd.dao.JbdTravelPointDetail2012Dao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdTravelPointDetail2012Manager extends Manager {
    /**
     * Retrieves all of the jbdTravelPointDetail2012s
     */
    public List getJbdTravelPointDetail2012s(JbdTravelPointDetail2012 jbdTravelPointDetail2012);

    /**
     * Gets jbdTravelPointDetail2012's information based on id.
     * @param id the jbdTravelPointDetail2012's id
     * @return jbdTravelPointDetail2012 populated jbdTravelPointDetail2012 object
     */
    public JbdTravelPointDetail2012 getJbdTravelPointDetail2012(final String id);

    /**
     * Saves a jbdTravelPointDetail2012's information
     * @param jbdTravelPointDetail2012 the object to be saved
     */
    public void saveJbdTravelPointDetail2012(JbdTravelPointDetail2012 jbdTravelPointDetail2012);

    /**
     * Removes a jbdTravelPointDetail2012 from the database by id
     * @param id the jbdTravelPointDetail2012's id
     */
    public void removeJbdTravelPointDetail2012(final String id);
    //added for getJbdTravelPointDetail2012sByCrm
    public List getJbdTravelPointDetail2012sByCrm(CommonRecord crm, Pager pager);

	public void addJbdTravelPointRecord2012(String userCode,String travelType,SysUser defSysUser) ;
	public void removeJbdTravelPointRecord2012(String userCode,String travelType,SysUser defSysUser,String id);

	public void changeJbdTravelPoint2012(JbdTravelPointLog2012 jbdTravelPointLog2012, SysUser defSysUser);
}

