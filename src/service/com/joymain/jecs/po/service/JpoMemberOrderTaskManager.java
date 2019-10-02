
package com.joymain.jecs.po.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.po.model.JpoMemberOrderTask;
import com.joymain.jecs.po.dao.JpoMemberOrderTaskDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JpoMemberOrderTaskManager extends Manager {
    /**
     * Retrieves all of the jpoMemberOrderTasks
     */
    public List getJpoMemberOrderTasks(JpoMemberOrderTask jpoMemberOrderTask);

    /**
     * Gets jpoMemberOrderTask's information based on motId.
     * @param motId the jpoMemberOrderTask's motId
     * @return jpoMemberOrderTask populated jpoMemberOrderTask object
     */
    public JpoMemberOrderTask getJpoMemberOrderTask(final String motId);

    /**
     * Saves a jpoMemberOrderTask's information
     * @param jpoMemberOrderTask the object to be saved
     */
    public void saveJpoMemberOrderTask(JpoMemberOrderTask jpoMemberOrderTask);

    /**
     * Removes a jpoMemberOrderTask from the database by motId
     * @param motId the jpoMemberOrderTask's motId
     */
    public void removeJpoMemberOrderTask(final String motId);
    //added for getJpoMemberOrderTasksByCrm
    public List getJpoMemberOrderTasksByCrm(CommonRecord crm, Pager pager);
	public JpoMemberOrderTask getMainJpoMemberOrderTask(String userCode);
}

