
package com.joymain.jecs.am.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.am.model.PublicSchedule;
import com.joymain.jecs.am.dao.PublicScheduleDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface PublicScheduleManager extends Manager {
    /**
     * Retrieves all of the publicSchedules
     */
    public List getPublicSchedules(PublicSchedule publicSchedule);

    /**
     * Gets publicSchedule's information based on psId.
     * @param psId the publicSchedule's psId
     * @return publicSchedule populated publicSchedule object
     */
    public PublicSchedule getPublicSchedule(final String psId);

    /**
     * Saves a publicSchedule's information
     * @param publicSchedule the object to be saved
     */
    public void savePublicSchedule(PublicSchedule publicSchedule);

    /**
     * Removes a publicSchedule from the database by psId
     * @param psId the publicSchedule's psId
     */
    public void removePublicSchedule(final String psId);
    //added for getPublicSchedulesByCrm
    public List getPublicSchedulesByCrm(CommonRecord crm, Pager pager);
}

