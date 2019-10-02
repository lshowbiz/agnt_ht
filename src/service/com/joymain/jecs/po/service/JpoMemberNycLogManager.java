
package com.joymain.jecs.po.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.po.model.JpoMemberNycLog;
import com.joymain.jecs.po.dao.JpoMemberNycLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JpoMemberNycLogManager extends Manager {
    /**
     * Retrieves all of the jpoMemberNycLogs
     */
    public List getJpoMemberNycLogs(JpoMemberNycLog jpoMemberNycLog);

    /**
     * Gets jpoMemberNycLog's information based on id.
     * @param id the jpoMemberNycLog's id
     * @return jpoMemberNycLog populated jpoMemberNycLog object
     */
    public JpoMemberNycLog getJpoMemberNycLog(final String id);

    /**
     * Saves a jpoMemberNycLog's information
     * @param jpoMemberNycLog the object to be saved
     */
    public void saveJpoMemberNycLog(JpoMemberNycLog jpoMemberNycLog);

    /**
     * Removes a jpoMemberNycLog from the database by id
     * @param id the jpoMemberNycLog's id
     */
    public void removeJpoMemberNycLog(final String id);
    //added for getJpoMemberNycLogsByCrm
    public List getJpoMemberNycLogsByCrm(CommonRecord crm, Pager pager);
}

