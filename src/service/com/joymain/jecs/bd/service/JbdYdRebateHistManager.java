
package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.bd.model.JbdYdRebateHist;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdYdRebateHistManager extends Manager {
    /**
     * Retrieves all of the jbdYdRebateHists
     */
    public List getJbdYdRebateHists(JbdYdRebateHist jbdYdRebateHist);

    /**
     * Gets jbdYdRebateHist's information based on id.
     * @param id the jbdYdRebateHist's id
     * @return jbdYdRebateHist populated jbdYdRebateHist object
     */
    public JbdYdRebateHist getJbdYdRebateHist(final String id);

    /**
     * Saves a jbdYdRebateHist's information
     * @param jbdYdRebateHist the object to be saved
     */
    public void saveJbdYdRebateHist(JbdYdRebateHist jbdYdRebateHist);

    /**
     * Removes a jbdYdRebateHist from the database by id
     * @param id the jbdYdRebateHist's id
     */
    public void removeJbdYdRebateHist(final String id);
    //added for getJbdYdRebateHistsByCrm
    public List getJbdYdRebateHistsByCrm(CommonRecord crm, Pager pager);
    public void saveInJdFiBook(SysUser defSysUser,String id);
}

