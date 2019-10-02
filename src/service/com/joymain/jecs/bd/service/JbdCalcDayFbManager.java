
package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.bd.model.JbdCalcDayFb;
import com.joymain.jecs.bd.dao.JbdCalcDayFbDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdCalcDayFbManager extends Manager {
    /**
     * Retrieves all of the jbdCalcDayFbs
     */
    public List getJbdCalcDayFbs(JbdCalcDayFb jbdCalcDayFb);

    /**
     * Gets jbdCalcDayFb's information based on id.
     * @param id the jbdCalcDayFb's id
     * @return jbdCalcDayFb populated jbdCalcDayFb object
     */
    public JbdCalcDayFb getJbdCalcDayFb(final String id);

    /**
     * Saves a jbdCalcDayFb's information
     * @param jbdCalcDayFb the object to be saved
     */
    public void saveJbdCalcDayFb(JbdCalcDayFb jbdCalcDayFb);

    /**
     * Removes a jbdCalcDayFb from the database by id
     * @param id the jbdCalcDayFb's id
     */
    public void removeJbdCalcDayFb(final String id);
    //added for getJbdCalcDayFbsByCrm
    public List getJbdCalcDayFbsByCrm(CommonRecord crm, Pager pager);
    public List getJbdCalcDayFbList(String userCode,String wweek);
	public void saveInProductPointFiBook(SysUser defSysUser,String id);
}

