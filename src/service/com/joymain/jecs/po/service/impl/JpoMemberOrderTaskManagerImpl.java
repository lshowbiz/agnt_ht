
package com.joymain.jecs.po.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.po.model.JpoMemberOrderTask;
import com.joymain.jecs.po.dao.JpoMemberOrderTaskDao;
import com.joymain.jecs.po.service.JpoMemberOrderTaskManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JpoMemberOrderTaskManagerImpl extends BaseManager implements JpoMemberOrderTaskManager {
    private JpoMemberOrderTaskDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJpoMemberOrderTaskDao(JpoMemberOrderTaskDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.po.service.JpoMemberOrderTaskManager#getJpoMemberOrderTasks(com.joymain.jecs.po.model.JpoMemberOrderTask)
     */
    public List getJpoMemberOrderTasks(final JpoMemberOrderTask jpoMemberOrderTask) {
        return dao.getJpoMemberOrderTasks(jpoMemberOrderTask);
    }

    /**
     * @see com.joymain.jecs.po.service.JpoMemberOrderTaskManager#getJpoMemberOrderTask(String motId)
     */
    public JpoMemberOrderTask getJpoMemberOrderTask(final String motId) {
        return dao.getJpoMemberOrderTask(new Long(motId));
    }

    /**
     * @see com.joymain.jecs.po.service.JpoMemberOrderTaskManager#saveJpoMemberOrderTask(JpoMemberOrderTask jpoMemberOrderTask)
     */
    public void saveJpoMemberOrderTask(JpoMemberOrderTask jpoMemberOrderTask) {
        dao.saveJpoMemberOrderTask(jpoMemberOrderTask);
    }

    /**
     * @see com.joymain.jecs.po.service.JpoMemberOrderTaskManager#removeJpoMemberOrderTask(String motId)
     */
    public void removeJpoMemberOrderTask(final String motId) {
        dao.removeJpoMemberOrderTask(new Long(motId));
    }
    //added for getJpoMemberOrderTasksByCrm
    public List getJpoMemberOrderTasksByCrm(CommonRecord crm, Pager pager){
	return dao.getJpoMemberOrderTasksByCrm(crm,pager);
    }

	public JpoMemberOrderTask getMainJpoMemberOrderTask(String userCode) {
		return dao.getMainJpoMemberOrderTask(userCode);
	}
}
