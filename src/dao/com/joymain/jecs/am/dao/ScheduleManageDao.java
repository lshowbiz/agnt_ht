
package com.joymain.jecs.am.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.am.model.ScheduleManage;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface ScheduleManageDao extends Dao {

    /**
     * Retrieves all of the scheduleManages
     */
    public List getScheduleManages(ScheduleManage scheduleManage);

    /**
     * Gets scheduleManage's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the scheduleManage's id
     * @return scheduleManage populated scheduleManage object
     */
    public ScheduleManage getScheduleManage(final BigDecimal id);

    /**
     * Saves a scheduleManage's information
     * @param scheduleManage the object to be saved
     */    
    public void saveScheduleManage(ScheduleManage scheduleManage);

    /**
     * Removes a scheduleManage from the database by id
     * @param id the scheduleManage's id
     */
    public void removeScheduleManage(final BigDecimal id);
    //added for getScheduleManagesByCrm
    public List getScheduleManagesByCrm(CommonRecord crm, Pager pager);
}

