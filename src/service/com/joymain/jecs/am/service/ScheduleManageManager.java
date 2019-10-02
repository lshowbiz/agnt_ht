
package com.joymain.jecs.am.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.am.model.ScheduleManage;
import com.joymain.jecs.am.dao.ScheduleManageDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface ScheduleManageManager extends Manager {
    /**
     * Retrieves all of the scheduleManages
     */
    public List getScheduleManages(ScheduleManage scheduleManage);

    /**
     * Gets scheduleManage's information based on id.
     * @param id the scheduleManage's id
     * @return scheduleManage populated scheduleManage object
     */
    public ScheduleManage getScheduleManage(final String id);

    /**
     * Saves a scheduleManage's information
     * @param scheduleManage the object to be saved
     */
    public void saveScheduleManage(ScheduleManage scheduleManage);

    /**
     * Removes a scheduleManage from the database by id
     * @param id the scheduleManage's id
     */
    public void removeScheduleManage(final String id);
    //added for getScheduleManagesByCrm
    public List getScheduleManagesByCrm(CommonRecord crm, Pager pager);
}

