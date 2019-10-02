
package com.joymain.jecs.po.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.po.model.JpoMemberOrderListTask;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpoMemberOrderListTaskDao extends Dao {

    /**
     * Retrieves all of the jpoMemberOrderListTasks
     */
    public List getJpoMemberOrderListTasks(JpoMemberOrderListTask jpoMemberOrderListTask);

    /**
     * Gets jpoMemberOrderListTask's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param moltId the jpoMemberOrderListTask's moltId
     * @return jpoMemberOrderListTask populated jpoMemberOrderListTask object
     */
    public JpoMemberOrderListTask getJpoMemberOrderListTask(final Long moltId);

    /**
     * Saves a jpoMemberOrderListTask's information
     * @param jpoMemberOrderListTask the object to be saved
     */    
    public void saveJpoMemberOrderListTask(JpoMemberOrderListTask jpoMemberOrderListTask);

    /**
     * Removes a jpoMemberOrderListTask from the database by moltId
     * @param moltId the jpoMemberOrderListTask's moltId
     */
    public void removeJpoMemberOrderListTask(final Long moltId);
    //added for getJpoMemberOrderListTasksByCrm
    public List getJpoMemberOrderListTasksByCrm(CommonRecord crm, Pager pager);
}

