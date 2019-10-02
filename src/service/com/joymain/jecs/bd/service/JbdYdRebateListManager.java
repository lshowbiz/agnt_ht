
package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.bd.model.JbdYdRebateList;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdYdRebateListManager extends Manager {
    /**
     * Retrieves all of the jbdYdRebateLists
     */
    public List getJbdYdRebateLists(JbdYdRebateList jbdYdRebateList);

    /**
     * Gets jbdYdRebateList's information based on id.
     * @param id the jbdYdRebateList's id
     * @return jbdYdRebateList populated jbdYdRebateList object
     */
    public JbdYdRebateList getJbdYdRebateList(final String id);

    /**
     * Saves a jbdYdRebateList's information
     * @param jbdYdRebateList the object to be saved
     */
    public void saveJbdYdRebateList(JbdYdRebateList jbdYdRebateList);

    /**
     * Removes a jbdYdRebateList from the database by id
     * @param id the jbdYdRebateList's id
     */
    public void removeJbdYdRebateList(final String id);
    //added for getJbdYdRebateListsByCrm
    public List getJbdYdRebateListsByCrm(CommonRecord crm, Pager pager);
    public List getVJbdYdRebateListsByCrm(CommonRecord crm, Pager pager);
    public void saveInJdFiBook(SysUser defSysUser,String id);
}

