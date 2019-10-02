
package com.joymain.jecs.po.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.po.model.JpoMemberOrderListTask;
import com.joymain.jecs.po.dao.JpoMemberOrderListTaskDao;
import com.joymain.jecs.po.service.JpoMemberOrderListTaskManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JpoMemberOrderListTaskManagerImpl extends BaseManager implements JpoMemberOrderListTaskManager {
    private JpoMemberOrderListTaskDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJpoMemberOrderListTaskDao(JpoMemberOrderListTaskDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.po.service.JpoMemberOrderListTaskManager#getJpoMemberOrderListTasks(com.joymain.jecs.po.model.JpoMemberOrderListTask)
     */
    public List getJpoMemberOrderListTasks(final JpoMemberOrderListTask jpoMemberOrderListTask) {
        return dao.getJpoMemberOrderListTasks(jpoMemberOrderListTask);
    }

    /**
     * @see com.joymain.jecs.po.service.JpoMemberOrderListTaskManager#getJpoMemberOrderListTask(String moltId)
     */
    public JpoMemberOrderListTask getJpoMemberOrderListTask(final String moltId) {
        return dao.getJpoMemberOrderListTask(new Long(moltId));
    }

    /**
     * @see com.joymain.jecs.po.service.JpoMemberOrderListTaskManager#saveJpoMemberOrderListTask(JpoMemberOrderListTask jpoMemberOrderListTask)
     */
    public void saveJpoMemberOrderListTask(JpoMemberOrderListTask jpoMemberOrderListTask) {
        dao.saveJpoMemberOrderListTask(jpoMemberOrderListTask);
    }

    /**
     * @see com.joymain.jecs.po.service.JpoMemberOrderListTaskManager#removeJpoMemberOrderListTask(String moltId)
     */
    public void removeJpoMemberOrderListTask(final String moltId) {
        dao.removeJpoMemberOrderListTask(new Long(moltId));
    }
    //added for getJpoMemberOrderListTasksByCrm
    public List getJpoMemberOrderListTasksByCrm(CommonRecord crm, Pager pager){
	return dao.getJpoMemberOrderListTasksByCrm(crm,pager);
    }
}
