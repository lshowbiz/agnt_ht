
package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.bd.model.JbdDayCustRecommendKb;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdDayCustRecommendKbManager extends Manager {
    /**
     * Retrieves all of the jbdDayCustRecommendKbs
     */
    public List getJbdDayCustRecommendKbs(JbdDayCustRecommendKb jbdDayCustRecommendKb);

    /**
     * Gets jbdDayCustRecommendKb's information based on id.
     * @param id the jbdDayCustRecommendKb's id
     * @return jbdDayCustRecommendKb populated jbdDayCustRecommendKb object
     */
    public JbdDayCustRecommendKb getJbdDayCustRecommendKb(final String id);

    /**
     * Saves a jbdDayCustRecommendKb's information
     * @param jbdDayCustRecommendKb the object to be saved
     */
    public void saveJbdDayCustRecommendKb(JbdDayCustRecommendKb jbdDayCustRecommendKb);

    /**
     * Removes a jbdDayCustRecommendKb from the database by id
     * @param id the jbdDayCustRecommendKb's id
     */
    public void removeJbdDayCustRecommendKb(final String id);
    //added for getJbdDayCustRecommendKbsByCrm
    public List getJbdDayCustRecommendKbsByCrm(CommonRecord crm, Pager pager);
    public void saveInJdFiBook(SysUser defSysUser,String id);
}

