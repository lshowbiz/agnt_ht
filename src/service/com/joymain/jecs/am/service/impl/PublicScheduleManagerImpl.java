
package com.joymain.jecs.am.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.am.model.PublicSchedule;
import com.joymain.jecs.am.dao.PublicScheduleDao;
import com.joymain.jecs.am.service.PublicScheduleManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class PublicScheduleManagerImpl extends BaseManager implements PublicScheduleManager {
    private PublicScheduleDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setPublicScheduleDao(PublicScheduleDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.am.service.PublicScheduleManager#getPublicSchedules(com.joymain.jecs.am.model.PublicSchedule)
     */
    public List getPublicSchedules(final PublicSchedule publicSchedule) {
        return dao.getPublicSchedules(publicSchedule);
    }

    /**
     * @see com.joymain.jecs.am.service.PublicScheduleManager#getPublicSchedule(String psId)
     */
    public PublicSchedule getPublicSchedule(final String psId) {
        return dao.getPublicSchedule(new Long(psId));
    }

    /**
     * @see com.joymain.jecs.am.service.PublicScheduleManager#savePublicSchedule(PublicSchedule publicSchedule)
     */
    public void savePublicSchedule(PublicSchedule publicSchedule) {
        dao.savePublicSchedule(publicSchedule);
    }

    /**
     * @see com.joymain.jecs.am.service.PublicScheduleManager#removePublicSchedule(String psId)
     */
    public void removePublicSchedule(final String psId) {
        dao.removePublicSchedule(new Long(psId));
    }
    //added for getPublicSchedulesByCrm
    public List getPublicSchedulesByCrm(CommonRecord crm, Pager pager){
	return dao.getPublicSchedulesByCrm(crm,pager);
    }
}
