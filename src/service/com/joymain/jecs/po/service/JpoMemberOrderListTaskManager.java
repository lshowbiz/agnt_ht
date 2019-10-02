
package com.joymain.jecs.po.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.po.model.JpoMemberOrderListTask;
import com.joymain.jecs.po.dao.JpoMemberOrderListTaskDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JpoMemberOrderListTaskManager extends Manager {
    /**
     * Retrieves all of the jpoMemberOrderListTasks
     */
    public List getJpoMemberOrderListTasks(JpoMemberOrderListTask jpoMemberOrderListTask);

    /**
     * Gets jpoMemberOrderListTask's information based on moltId.
     * @param moltId the jpoMemberOrderListTask's moltId
     * @return jpoMemberOrderListTask populated jpoMemberOrderListTask object
     */
    public JpoMemberOrderListTask getJpoMemberOrderListTask(final String moltId);

    /**
     * Saves a jpoMemberOrderListTask's information
     * @param jpoMemberOrderListTask the object to be saved
     */
    public void saveJpoMemberOrderListTask(JpoMemberOrderListTask jpoMemberOrderListTask);

    /**
     * Removes a jpoMemberOrderListTask from the database by moltId
     * @param moltId the jpoMemberOrderListTask's moltId
     */
    public void removeJpoMemberOrderListTask(final String moltId);
    //added for getJpoMemberOrderListTasksByCrm
    public List getJpoMemberOrderListTasksByCrm(CommonRecord crm, Pager pager);
}

