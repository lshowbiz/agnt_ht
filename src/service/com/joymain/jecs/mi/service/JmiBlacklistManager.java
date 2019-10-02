
package com.joymain.jecs.mi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.mi.model.JmiBlacklist;
import com.joymain.jecs.mi.dao.JmiBlacklistDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JmiBlacklistManager extends Manager {
    /**
     * Retrieves all of the jmiBlacklists
     */
    public List getJmiBlacklists(JmiBlacklist jmiBlacklist);

    /**
     * Gets jmiBlacklist's information based on blId.
     * @param blId the jmiBlacklist's blId
     * @return jmiBlacklist populated jmiBlacklist object
     */
    public JmiBlacklist getJmiBlacklist(final String blId);

    /**
     * Saves a jmiBlacklist's information
     * @param jmiBlacklist the object to be saved
     */
    public void saveJmiBlacklist(JmiBlacklist jmiBlacklist);

    /**
     * Removes a jmiBlacklist from the database by blId
     * @param blId the jmiBlacklist's blId
     */
    public void removeJmiBlacklist(final String blId);
    //added for getJmiBlacklistsByCrm
    public List getJmiBlacklistsByCrm(CommonRecord crm, Pager pager);

    public boolean getCheckJmiBlacklist(String papertype,String papernumber);
}

