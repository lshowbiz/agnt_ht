
package com.joymain.jecs.mi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.mi.model.JmiYdSendList;
import com.joymain.jecs.mi.dao.JmiYdSendListDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JmiYdSendListManager extends Manager {
    /**
     * Retrieves all of the jmiYdSendLists
     */
    public List getJmiYdSendLists(JmiYdSendList jmiYdSendList);

    /**
     * Gets jmiYdSendList's information based on id.
     * @param id the jmiYdSendList's id
     * @return jmiYdSendList populated jmiYdSendList object
     */
    public JmiYdSendList getJmiYdSendList(final String id);

    /**
     * Saves a jmiYdSendList's information
     * @param jmiYdSendList the object to be saved
     */
    public void saveJmiYdSendList(JmiYdSendList jmiYdSendList);

    /**
     * Removes a jmiYdSendList from the database by id
     * @param id the jmiYdSendList's id
     */
    public void removeJmiYdSendList(final String id);
    //added for getJmiYdSendListsByCrm
    public List getJmiYdSendListsByCrm(CommonRecord crm, Pager pager);
}

