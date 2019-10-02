
package com.joymain.jecs.po.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.po.model.JpoMemberNycLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpoMemberNycLogDao extends Dao {

    /**
     * Retrieves all of the jpoMemberNycLogs
     */
    public List getJpoMemberNycLogs(JpoMemberNycLog jpoMemberNycLog);

    /**
     * Gets jpoMemberNycLog's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
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

