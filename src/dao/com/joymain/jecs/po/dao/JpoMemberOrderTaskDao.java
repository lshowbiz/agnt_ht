
package com.joymain.jecs.po.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.po.model.JpoMemberOrderTask;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpoMemberOrderTaskDao extends Dao {

    /**
     * Retrieves all of the jpoMemberOrderTasks
     */
    public List getJpoMemberOrderTasks(JpoMemberOrderTask jpoMemberOrderTask);

    /**
     * Gets jpoMemberOrderTask's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param motId the jpoMemberOrderTask's motId
     * @return jpoMemberOrderTask populated jpoMemberOrderTask object
     */
    public JpoMemberOrderTask getJpoMemberOrderTask(final Long motId);

    /**
     * Saves a jpoMemberOrderTask's information
     * @param jpoMemberOrderTask the object to be saved
     */    
    public void saveJpoMemberOrderTask(JpoMemberOrderTask jpoMemberOrderTask);

    /**
     * Removes a jpoMemberOrderTask from the database by motId
     * @param motId the jpoMemberOrderTask's motId
     */
    public void removeJpoMemberOrderTask(final Long motId);
    //added for getJpoMemberOrderTasksByCrm
    public List getJpoMemberOrderTasksByCrm(CommonRecord crm, Pager pager);
    
    public JpoMemberOrderTask getMainJpoMemberOrderTask(String userCode);
}

