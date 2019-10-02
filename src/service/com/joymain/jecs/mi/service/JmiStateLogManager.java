
package com.joymain.jecs.mi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.mi.model.JmiStateLog;
import com.joymain.jecs.mi.dao.JmiStateLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JmiStateLogManager extends Manager {
    /**
     * Retrieves all of the jmiStateLogs
     */
    public List getJmiStateLogs(JmiStateLog jmiStateLog);

    /**
     * Gets jmiStateLog's information based on id.
     * @param id the jmiStateLog's id
     * @return jmiStateLog populated jmiStateLog object
     */
    public JmiStateLog getJmiStateLog(final String id);

    /**
     * Saves a jmiStateLog's information
     * @param jmiStateLog the object to be saved
     */
    public void saveJmiStateLog(JmiStateLog jmiStateLog);

    /**
     * Removes a jmiStateLog from the database by id
     * @param id the jmiStateLog's id
     */
    public void removeJmiStateLog(final String id);
    //added for getJmiStateLogsByCrm
    public List getJmiStateLogsByCrm(CommonRecord crm, Pager pager);
}

