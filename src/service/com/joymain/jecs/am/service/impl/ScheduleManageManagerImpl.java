
package com.joymain.jecs.am.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.am.model.ScheduleManage;
import com.joymain.jecs.am.dao.ScheduleManageDao;
import com.joymain.jecs.am.service.ScheduleManageManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class ScheduleManageManagerImpl extends BaseManager implements ScheduleManageManager {
    private ScheduleManageDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setScheduleManageDao(ScheduleManageDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.am.service.ScheduleManageManager#getScheduleManages(com.joymain.jecs.am.model.ScheduleManage)
     */
    public List getScheduleManages(final ScheduleManage scheduleManage) {
        return dao.getScheduleManages(scheduleManage);
    }

    /**
     * @see com.joymain.jecs.am.service.ScheduleManageManager#getScheduleManage(String id)
     */
    public ScheduleManage getScheduleManage(final String id) {
        return dao.getScheduleManage(new BigDecimal(id));
    }

    /**
     * @see com.joymain.jecs.am.service.ScheduleManageManager#saveScheduleManage(ScheduleManage scheduleManage)
     */
    public void saveScheduleManage(ScheduleManage scheduleManage) {
        dao.saveScheduleManage(scheduleManage);
    }

    /**
     * @see com.joymain.jecs.am.service.ScheduleManageManager#removeScheduleManage(String id)
     */
    public void removeScheduleManage(final String id) {
        dao.removeScheduleManage(new BigDecimal(id));
    }
    //added for getScheduleManagesByCrm
    public List getScheduleManagesByCrm(CommonRecord crm, Pager pager){
	return dao.getScheduleManagesByCrm(crm,pager);
    }
}
