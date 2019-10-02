
package com.joymain.jecs.mi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.mi.model.JmiPrizeTourism;
import com.joymain.jecs.mi.dao.JmiPrizeTourismDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JmiPrizeTourismManager extends Manager {
    /**
     * Retrieves all of the jmiPrizeTourisms
     */
    public List getJmiPrizeTourisms(JmiPrizeTourism jmiPrizeTourism);

    /**
     * Gets jmiPrizeTourism's information based on userCode.
     * @param userCode the jmiPrizeTourism's userCode
     * @return jmiPrizeTourism populated jmiPrizeTourism object
     */
    public JmiPrizeTourism getJmiPrizeTourism(final Long prizeTouismId);

    /**
     * Saves a jmiPrizeTourism's information
     * @param jmiPrizeTourism the object to be saved
     */
    public void saveJmiPrizeTourism(JmiPrizeTourism jmiPrizeTourism);

    /**
     * Removes a jmiPrizeTourism from the database by userCode
     * @param userCode the jmiPrizeTourism's userCode
     */
    public void removeJmiPrizeTourism(final Long prizeTouismId);
    //added for getJmiPrizeTourismsByCrm
    public List getJmiPrizeTourismsByCrm(CommonRecord crm, Pager pager);
    
    public String getPassStarByUserCode(String userCode);
}

