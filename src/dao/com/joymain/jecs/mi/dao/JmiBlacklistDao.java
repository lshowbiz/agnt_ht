
package com.joymain.jecs.mi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.mi.model.JmiBlacklist;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JmiBlacklistDao extends Dao {

    /**
     * Retrieves all of the jmiBlacklists
     */
    public List getJmiBlacklists(JmiBlacklist jmiBlacklist);

    /**
     * Gets jmiBlacklist's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param blId the jmiBlacklist's blId
     * @return jmiBlacklist populated jmiBlacklist object
     */
    public JmiBlacklist getJmiBlacklist(final Long blId);

    /**
     * Saves a jmiBlacklist's information
     * @param jmiBlacklist the object to be saved
     */    
    public void saveJmiBlacklist(JmiBlacklist jmiBlacklist);

    /**
     * Removes a jmiBlacklist from the database by blId
     * @param blId the jmiBlacklist's blId
     */
    public void removeJmiBlacklist(final Long blId);
    //added for getJmiBlacklistsByCrm
    public List getJmiBlacklistsByCrm(CommonRecord crm, Pager pager);
    
    public boolean getCheckJmiBlacklist(String papertype,String papernumber);
}

